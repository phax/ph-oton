/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.core.app.error;

import java.net.InetAddress;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.base64.Base64;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTWebDateHelper;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.ClassPathHelper;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.photon.basic.app.appid.RequestSettings;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.core.app.error.uihandler.IUIInternalErrorHandler;
import com.helger.scope.mgr.ScopeSessionManager;
import com.helger.servlet.ServletHelper;
import com.helger.servlet.request.RequestHelper;
import com.helger.servlet.request.RequestLogger;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.data.IEmailAttachmentDataSource;
import com.helger.smtp.data.IEmailAttachmentList;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.transport.MailAPI;
import com.helger.useragent.uaprofile.UAProfile;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.ISessionWebScope;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.XMLWriterSettings;
import com.helger.xml.util.thread.ThreadDescriptor;
import com.helger.xml.util.thread.ThreadDescriptorList;

/**
 * A handler for internal errors
 *
 * @author Philip Helger
 * @see InternalErrorBuilder
 * @see InternalErrorEmailSettings
 */
@ThreadSafe
public final class InternalErrorHandler
{
  public static final boolean DEFAULT_ENABLE_FULL_THREAD_DUMPS = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (InternalErrorHandler.class);
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static final ICommonsMap <String, MutableInt> s_aIntErrCache = new CommonsHashMap <> ();

  private InternalErrorHandler ()
  {}

  /**
   * Create a new unique error ID.
   *
   * @return This is either a new persistent int ID or a non-persistent ID
   *         together with the timestamp. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public static String createNewErrorID ()
  {
    try
    {
      return Integer.toString (GlobalIDFactory.getNewPersistentIntID ());
    }
    catch (final IllegalStateException ex)
    {
      // happens when no persistent ID factory is present
      return "t" + GlobalIDFactory.getNewIntID () + "_" + System.currentTimeMillis ();
    }
  }

  /**
   * Create a new unique internal error ID.
   *
   * @return The created error ID. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public static String createNewInternalErrorID ()
  {
    return "internal-error-" + createNewErrorID ();
  }

  @Nonnull
  @Nonempty
  private static String _getThrowableAsString (@Nonnull final Throwable t)
  {
    return t.getMessage () + " -- " + t.getClass ().getName ();
  }

  @Nonnull
  private static String _createMailSubject (@Nonnull final InternalErrorMetadata aMetadata)
  {
    final StringBuilder aSubject = new StringBuilder ();
    if (GlobalDebug.isDebugMode ())
      aSubject.append ("[DEBUG] ");
    if (GlobalDebug.isProductionMode ())
      aSubject.append ("[PRODUCTION] ");
    aSubject.append ("Internal error");
    final String sErrorMsg = aMetadata.getFieldValue (InternalErrorBuilder.KEY_ERROR_MSG, null);
    if (StringHelper.hasText (sErrorMsg))
      aSubject.append (": ").append (sErrorMsg);
    aSubject.append (" [").append (aMetadata.getErrorID ()).append (']');
    final String sMailSubject = aSubject.toString ();
    return sMailSubject;
  }

  private static void _sendInternalErrorMailToVendor (@Nonnull final InternalErrorMetadata aMetadata,
                                                      @Nonnull final ThreadDescriptor aCurrentThreadDescriptor,
                                                      @Nullable final ThreadDescriptorList aAllThreads,
                                                      @Nonnull final InternalErrorEmailSettings aEmailSettings,
                                                      @Nullable final IEmailAttachmentList aEmailAttachments,
                                                      final boolean bAddClassPath,
                                                      @Nonnegative final int nDuplicateEliminiationCount)
  {
    int nOccurranceCount = 1;
    final String sThrowableStackTrace = aCurrentThreadDescriptor.getStackTrace ();
    if (StringHelper.hasText (sThrowableStackTrace) && nDuplicateEliminiationCount > 1)
    {
      // Check if an internal error was already sent for this stack trace
      // Init with -1 so that it gets send the first time
      final MutableInt aMI = s_aRWLock.writeLocked ( () -> s_aIntErrCache.computeIfAbsent (sThrowableStackTrace,
                                                                                           k -> new MutableInt (-1)));
      aMI.inc ();

      // Send only every Nth invocation!
      nOccurranceCount = aMI.intValue ();
      if ((nOccurranceCount % nDuplicateEliminiationCount) != 0)
      {
        s_aLogger.warn ("Not sending internal error mail, because this error occurred " + nOccurranceCount + " times");
        return;
      }
    }

    final IEmailAddress aSender = aEmailSettings.getSenderAddress ();
    final ICommonsList <IEmailAddress> aReceiver = aEmailSettings.getAllReceiverAddresses ();
    final ISMTPSettings aSMTPSettings = aEmailSettings.getSMTPSettings ();

    boolean bCanSend = true;
    if (aSender == null)
    {
      s_aLogger.warn ("Not sending internal error mail, because 'sender' is not set!");
      bCanSend = false;
    }
    else
      if (aReceiver.isEmpty ())
      {
        s_aLogger.warn ("Not sending internal error mail, because 'receiver' is not set!");
        bCanSend = false;
      }
      else
        if (aSMTPSettings == null)
        {
          s_aLogger.warn ("Not sending internal error mail, because 'SMTP settings' is not set!");
          bCanSend = false;
        }

    if (bCanSend)
    {
      final String sMailSubject = _createMailSubject (aMetadata);

      // Main error thread dump
      final String sSeparator = "\n---------------------------------------------------------------\n";
      String sMailBody = aMetadata.getAsString () + sSeparator + aCurrentThreadDescriptor.getAsString () + sSeparator;
      if (aAllThreads != null)
      {
        // Add dump of all threads
        sMailBody += aAllThreads.getAsString () + sSeparator;
      }

      if (bAddClassPath)
      {
        // Add all classpath entries
        final StringBuilder aSB = new StringBuilder ("ClassPath:\n");
        for (final String sClassPathEntry : ClassPathHelper.getAllClassPathEntries ())
          aSB.append ("  ").append (sClassPathEntry).append ('\n');
        aSB.append (sSeparator);
        sMailBody += aSB.toString ();
      }

      final EmailData aEmailData = new EmailData (EEmailType.TEXT);
      aEmailData.setFrom (aSender);
      aEmailData.setTo (aReceiver);
      aEmailData.setSubject (sMailSubject);
      aEmailData.setBody (sMailBody);
      aEmailData.setAttachments (aEmailAttachments);

      try
      {
        // Try default error communication data
        if (ScopedMailAPI.getInstance ().queueMail (aSMTPSettings, aEmailData).isFailure ())
          s_aLogger.warn ("Failed to send via ScopedMailAPI");
      }
      catch (final Throwable t2)
      {
        // E.g. if no scopes are present
        s_aLogger.warn ("Failed to send via ScopedMailAPI: " + _getThrowableAsString (t2));

        // Try to send directly
        if (MailAPI.queueMail (aSMTPSettings, aEmailData).isFailure ())
          s_aLogger.warn ("Failed to send via MailAPI as well");
      }
    }
  }

  private static void _saveInternalErrorToXML (@Nonnull final InternalErrorMetadata aMetadata,
                                               @Nonnull final ThreadDescriptor aCurrentDescriptor,
                                               @Nullable final ThreadDescriptorList aAllThreads,
                                               @Nullable final IEmailAttachmentList aEmailAttachments)
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("internalerror");
    eRoot.appendChild (aMetadata.getAsMicroNode ());
    eRoot.appendChild (aCurrentDescriptor.getAsMicroNode ());
    if (aAllThreads != null)
      eRoot.appendChild (aAllThreads.getAsMicroNode ());

    if (aEmailAttachments != null)
    {
      final ICommonsList <IEmailAttachmentDataSource> aAttachments = aEmailAttachments.getAsDataSourceList ();
      if (aAttachments.isNotEmpty ())
      {
        final IMicroElement eAttachments = eRoot.appendElement ("attachments");
        for (final IEmailAttachmentDataSource aDS : aAttachments)
        {
          final IMicroElement eAttachment = eAttachments.appendElement ("attachment");
          eAttachment.setAttribute ("name", aDS.getName ());
          eAttachment.setAttribute ("contenttype", aDS.getContentType ());
          try
          {
            eAttachment.appendText (Base64.encodeBytes (StreamHelper.getAllBytes (aDS.getInputStream ())));
          }
          catch (final Exception ex)
          {
            s_aLogger.error ("Failed to get content of attachment '" + aDS.getName () + "'", ex);
            eAttachment.setAttribute ("contentsavefailure", "true");
          }
        }
      }
    }

    // Start saving
    final String sFilename = StringHelper.getConcatenatedOnDemand (PDTIOHelper.getCurrentLocalDateTimeForFilename (),
                                                                   "-",
                                                                   aMetadata.getErrorID ()) +
                             ".xml";
    SimpleFileIO.writeFile (WebFileIO.getDataIO ()
                                     .getFile ("internal-errors/" + PDTFactory.getCurrentYear () + "/" + sFilename),
                            MicroWriter.getNodeAsString (aDoc),
                            XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ);
  }

  @Nonnull
  public static InternalErrorMetadata fillInternalErrorMetaData (@Nullable final IRequestWebScopeWithoutResponse aProvidedRequestScope,
                                                                 @Nullable final String sErrorID,
                                                                 @Nullable final Map <String, String> aCustomData)
  {
    final InternalErrorMetadata aMetadata = new InternalErrorMetadata (sErrorID);

    // Date and time
    try
    {
      aMetadata.addField ("Time", PDTWebDateHelper.getAsStringXSD (ZonedDateTime.now (Clock.systemUTC ())));
    }
    catch (final Throwable t2)
    {
      aMetadata.addField ("Time", "System.currentTimeMillis=" + Long.toString (System.currentTimeMillis ()));
    }

    // Custom data
    if (aCustomData != null)
      for (final Map.Entry <String, String> aEntry : aCustomData.entrySet ())
        aMetadata.addField ("[Custom] " + aEntry.getKey (), aEntry.getValue ());

    // Get request scope if none is provided
    IRequestWebScopeWithoutResponse aRequestScope = aProvidedRequestScope;
    if (aRequestScope == null)
      try
      {
        aRequestScope = WebScopeManager.getRequestScope ();
      }
      catch (final Throwable t2)
      {
        // Happens if no scope is available (or what so ever)
        s_aLogger.warn ("Failed to get request scope: " + _getThrowableAsString (t2));
      }
    if (aRequestScope != null)
    {
      if (!aRequestScope.isValid ())
        aMetadata.addField ("Request scope", "!!!Present but invalid!!!");

      try
      {
        aMetadata.addField ("Request URL", aRequestScope.getURL ());
      }
      catch (final Throwable t2)
      {
        // fall-through - happens in a weird case
        aMetadata.addFieldRetrievalError ("Request URL", t2);
      }

      aMetadata.addField ("User agent", RequestHelper.getUserAgent (aRequestScope.getRequest ()).getAsString ());

      try
      {
        aMetadata.addField ("Remote IP address", aRequestScope.getRemoteAddr ());
      }
      catch (final Throwable t2)
      {
        // fall-through - happens in a weird case
        aMetadata.addFieldRetrievalError ("Remote IP address", t2);
      }

      // Mobile browser?
      final UAProfile aProfile = RequestHelper.getUAProfile (aRequestScope.getRequest ());
      if (!aProfile.equals (UAProfile.EMPTY))
        aMetadata.addField ("UAProfile", aProfile.toString ());

      // Add all request attributes
      for (final Map.Entry <String, Object> aEntry : aRequestScope.attrs ()
                                                                  .getSortedByKey (Comparator.naturalOrder ())
                                                                  .entrySet ())
        aMetadata.addField ("[Request Attr] " + aEntry.getKey (), String.valueOf (aEntry.getValue ()));

      // Add all request params
      for (final Map.Entry <String, Object> aEntry : aRequestScope.params ()
                                                                  .getSortedByKey (Comparator.naturalOrder ())
                                                                  .entrySet ())
        aMetadata.addField ("[Request Param] " + aEntry.getKey (), String.valueOf (aEntry.getValue ()));
    }
    else
    {
      aMetadata.addField ("Request scope", "!!!Not present!!!");
    }

    // Session scopes
    {
      ISessionWebScope aSessionScope = null;
      if (aRequestScope != null)
        try
        {
          // Try to get from request scope (if one is provided)
          aSessionScope = (ISessionWebScope) ScopeSessionManager.getInstance ()
                                                                .getSessionScopeOfID (aRequestScope.getSessionID ());
        }
        catch (final Throwable t2)
        {
          s_aLogger.warn ("Failed to get session scope from request scope: " + _getThrowableAsString (t2));
        }
      if (aSessionScope == null)
        try
        {
          aSessionScope = WebScopeManager.getSessionScope (false);
        }
        catch (final Throwable t2)
        {
          // Happens if no scope is available (or what so ever)
          s_aLogger.warn ("Failed to get request scope: " + _getThrowableAsString (t2));
        }
      if (aSessionScope != null)
      {
        aMetadata.addField ("SessionID", aSessionScope.getID ());

        // Add all session attributes
        for (final Map.Entry <String, Object> aEntry : aSessionScope.attrs ()
                                                                    .getSortedByKey (Comparator.naturalOrder ())
                                                                    .entrySet ())
          aMetadata.addField ("[Session] " + aEntry.getKey (), String.valueOf (aEntry.getValue ()));
      }
    }

    // Network info
    try
    {
      final InetAddress aAddress = InetAddress.getLocalHost ();
      aMetadata.addField ("My host name", aAddress.getHostName ());
      aMetadata.addField ("My IP address", aAddress.getHostAddress ());
    }
    catch (final Exception ex)
    {
      // fall through
    }

    if (aRequestScope != null)
    {
      final HttpServletRequest aHttpRequest = aRequestScope.getRequest ();
      if (aHttpRequest != null)
      {
        try
        {
          for (final Map.Entry <String, String> aEntry : RequestLogger.getRequestFieldMap (aHttpRequest).entrySet ())
            aMetadata.addRequestField (aEntry.getKey (), aEntry.getValue ());
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Failed to get request fields from " + aHttpRequest, t2);
        }
        try
        {
          RequestHelper.getRequestHeaderMap (aHttpRequest)
                       .forEachSingleHeader ( (n, v) -> aMetadata.addRequestHeader (n, v));
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Failed to get request headers from " + aHttpRequest, t2);
        }
        try
        {
          for (final Map.Entry <String, String> aEntry : RequestLogger.getRequestParameterMap (aHttpRequest)
                                                                      .entrySet ())
            aMetadata.addRequestParameter (aEntry.getKey (), aEntry.getValue ());
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Failed to get request parameters from " + aHttpRequest, t2);
        }

        try
        {
          final Cookie [] aCookies = ServletHelper.getRequestCookies (aHttpRequest);
          if (aCookies != null)
            for (final Cookie aCookie : aCookies)
              aMetadata.addRequestCookie (aCookie.getName (), aCookie.getValue ());
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Failed to get request cookies from " + aHttpRequest, t2);
        }
      }
      else
        aMetadata.addField ("HttpServletRequest", "RequestScope does not contain an HttpServletRequest");
    }
    else
      aMetadata.addField ("RequestScope", "None available");
    return aMetadata;
  }

  private static void _notifyVendor (final boolean bSendEmail,
                                     final boolean bSaveAsXML,
                                     @Nullable final Throwable t,
                                     @Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nullable final String sErrorID,
                                     @Nullable final Map <String, String> aCustomData,
                                     @Nonnull final InternalErrorEmailSettings aEmailSettings,
                                     @Nullable final IEmailAttachmentList aEmailAttachments,
                                     final boolean bAddClassPath,
                                     @Nonnegative final int nDuplicateEliminiationCount)
  {
    // Create all metadata from the request
    final InternalErrorMetadata aMetadata = fillInternalErrorMetaData (aRequestScope, sErrorID, aCustomData);

    // Get descriptor for crashed thread
    final ThreadDescriptor aCurrentThreadDescriptor = ThreadDescriptor.createForCurrentThread (t);

    // Get all thread descriptors
    ThreadDescriptorList aAllThreads = null;
    if (InternalErrorSettings.isDumpAllThreads ())
      aAllThreads = ThreadDescriptorList.createWithAllThreads ();

    // Main mail sending
    if (bSendEmail)
      _sendInternalErrorMailToVendor (aMetadata,
                                      aCurrentThreadDescriptor,
                                      aAllThreads,
                                      aEmailSettings,
                                      aEmailAttachments,
                                      bAddClassPath,
                                      nDuplicateEliminiationCount);

    // Save as XML too
    if (bSaveAsXML)
      _saveInternalErrorToXML (aMetadata, aCurrentThreadDescriptor, aAllThreads, aEmailAttachments);
  }

  @Nullable
  private static Locale _getSafeDisplayLocale ()
  {
    final IRequestWebScope aRequestScope = WebScopeManager.getRequestScopeOrNull ();
    if (aRequestScope != null)
      try
      {
        // This may fail, if a weird application context is used
        return RequestSettings.getDisplayLocale (aRequestScope);
      }
      catch (final RuntimeException ex)
      {
        // This happens e.g. on internal errors on startup
      }
    return InternalErrorSettings.getFallbackLocale ();
  }

  /**
   * Triggering of an internal error. This method should not be called manually
   * but instead {@link InternalErrorBuilder} should be used, as this is the
   * builder class for this method.
   *
   * @param bSendEmail
   *        <code>true</code> to send the internal error as email
   * @param bSaveAsXML
   *        <code>true</code> to also save the internal error as XML
   * @param aUIErrorHandler
   *        Show an internal error on the screen. May be <code>null</code>.
   * @param t
   *        The exception that occurred. May be <code>null</code>.
   * @param aRequestScope
   *        The request scope in which the error occurred. May be
   *        <code>null</code>.
   * @param aCustomData
   *        Custom data to be put into the error. May be <code>null</code>.
   * @param aEmailAttachments
   *        Email attachments to be added. May be <code>null</code>.
   * @param aDisplayLocale
   *        The display locale to use for the texts. May be <code>null</code> in
   *        which case it will be the request locale or the fallback locale from
   *        the internal error settings.
   * @param bInvokeCustomExceptionHandler
   *        <code>true</code> to invoke the custom exception handler (if any is
   *        present), <code>false</code> to not do so.
   * @param bAddClassPath
   *        Add the class path entries to the email message.
   * @param nDuplicateEliminiationCounter
   *        A modulo value to be used for sending emails every Nth time an error
   *        occurs. So if the same exception occurs 2mio times, that an email is
   *        send out only every "2mio % value" times. Only values &gt; 1 are
   *        considered.
   * @return The created unique error ID
   */
  @Nonnull
  @Nonempty
  static String handleInternalError (final boolean bSendEmail,
                                     final boolean bSaveAsXML,
                                     @Nullable final IUIInternalErrorHandler aUIErrorHandler,
                                     @Nullable final Throwable t,
                                     @Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nullable final Map <String, String> aCustomData,
                                     @Nullable final IEmailAttachmentList aEmailAttachments,
                                     @Nullable final Locale aDisplayLocale,
                                     final boolean bInvokeCustomExceptionHandler,
                                     final boolean bAddClassPath,
                                     @Nonnegative final int nDuplicateEliminiationCounter)
  {
    final Locale aRealDisplayLocale = aDisplayLocale != null ? aDisplayLocale : _getSafeDisplayLocale ();

    final String sErrorID = createNewInternalErrorID ();

    // Log the error, to ensure the data is persisted!
    s_aLogger.error ("handleInternalError " + sErrorID, t);

    // Print on UI
    if (aUIErrorHandler != null)
      aUIErrorHandler.onInternalError (t, sErrorID, aRealDisplayLocale);

    if (GlobalDebug.isDebugMode ())
    {
      // Debug mode - log and eventually throw exception (except we're in unit
      // tests)
      if (aCustomData != null)
        s_aLogger.error ("Custom data: " + aCustomData);

      // In case an unexpected error occurs in the UnitTest, make the test fail!
      if (t != null && StackTraceHelper.containsUnitTestElement (t.getStackTrace ()))
        throw new IllegalStateException ("Error executing unit test", t);
    }
    else
    {
      // Send mail with attachments
      _notifyVendor (bSendEmail,
                     bSaveAsXML,
                     t,
                     aRequestScope,
                     sErrorID,
                     aCustomData,
                     InternalErrorSettings.getCopyOfEmailSettings (),
                     aEmailAttachments,
                     bAddClassPath,
                     nDuplicateEliminiationCounter);
    }

    if (bInvokeCustomExceptionHandler)
    {
      // Invoke custom exception handler (if present)
      InternalErrorSettings.callbacks ()
                           .forEach (x -> x.onInternalError (t, aRequestScope, sErrorID, aRealDisplayLocale));
    }

    return sErrorID;
  }
}
