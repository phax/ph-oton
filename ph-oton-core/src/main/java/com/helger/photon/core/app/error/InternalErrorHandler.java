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

import java.io.File;
import java.net.InetAddress;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.base64.Base64;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.scope.mgr.ScopeSessionManager;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.datetime.util.PDTWebDateHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.thread.ThreadDescriptor;
import com.helger.photon.basic.thread.ThreadDescriptorList;
import com.helger.photon.core.app.error.callback.IInternalErrorCallback;
import com.helger.photon.core.app.error.uihandler.IUIInternalErrorHandler;
import com.helger.photon.security.login.LoggedInUserManager;
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
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.ISessionWebScope;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * A handler for internal errors
 *
 * @author Philip Helger
 * @see InternalErrorBuilder
 */
@ThreadSafe
public final class InternalErrorHandler
{
  @NotThreadSafe
  public static final class EmailSettings implements ICloneable <EmailSettings>
  {
    private ISMTPSettings m_aSMTPSettings;
    private IEmailAddress m_aSenderAddress;
    private ICommonsList <IEmailAddress> m_aReceiverAddresses;
    private IEmailAttachmentList m_aAttachmentList;

    public EmailSettings ()
    {}

    public EmailSettings (@Nonnull final EmailSettings aOther)
    {
      ValueEnforcer.notNull (aOther, "Other");
      m_aSMTPSettings = aOther.m_aSMTPSettings;
      m_aSenderAddress = aOther.m_aSenderAddress;
      m_aReceiverAddresses = aOther.m_aReceiverAddresses;
      m_aAttachmentList = aOther.m_aAttachmentList;
    }

    @Nonnull
    public EmailSettings setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
    {
      m_aSMTPSettings = aSMTPSettings;
      return this;
    }

    @Nullable
    public ISMTPSettings getSMTPSettings ()
    {
      return m_aSMTPSettings;
    }

    @Nonnull
    public EmailSettings setSenderAddress (@Nullable final IEmailAddress aSenderAddress)
    {
      m_aSenderAddress = aSenderAddress;
      return this;
    }

    @Nullable
    public IEmailAddress getSenderAddress ()
    {
      return m_aSenderAddress;
    }

    @Nonnull
    public EmailSettings setReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
    {
      return setReceiverAddresses (aReceiverAddress == null ? null : new CommonsArrayList <> (aReceiverAddress));
    }

    @Nonnull
    public EmailSettings setReceiverAddresses (@Nullable final List <? extends IEmailAddress> aReceiverAddresses)
    {
      if (aReceiverAddresses != null && CollectionHelper.containsAnyNullElement (aReceiverAddresses))
        throw new IllegalArgumentException ("The list of receiver addresses may not contain any null element!");

      m_aReceiverAddresses = new CommonsArrayList <> (aReceiverAddresses);
      return this;
    }

    @Nonnull
    public EmailSettings setReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
    {
      if (aReceiverAddresses != null && ArrayHelper.containsAnyNullElement (aReceiverAddresses))
        throw new IllegalArgumentException ("The array of receiver addresses may not contain any null element!");

      m_aReceiverAddresses = new CommonsArrayList <> (aReceiverAddresses);
      return this;
    }

    @Nonnull
    @ReturnsMutableCopy
    public ICommonsList <IEmailAddress> getReceiverAddresses ()
    {
      // May be null
      return new CommonsArrayList <> (m_aReceiverAddresses);
    }

    @Nonnull
    public EmailSettings setAttachmentList (@Nullable final IEmailAttachmentList aAttachmentList)
    {
      m_aAttachmentList = aAttachmentList;
      return this;
    }

    @Nullable
    public IEmailAttachmentList getAttachmentList ()
    {
      return m_aAttachmentList;
    }

    @Nonnull
    @ReturnsMutableCopy
    public EmailSettings getClone ()
    {
      return new EmailSettings (this);
    }
  }

  public static final boolean DEFAULT_ENABLE_FULL_THREAD_DUMPS = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (InternalErrorHandler.class);
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  private static final EmailSettings s_aEmailSettings = new EmailSettings ();
  private static IInternalErrorCallback s_aCustomExceptionHandler;
  private static boolean s_bEnableFullThreadDumps = DEFAULT_ENABLE_FULL_THREAD_DUMPS;

  private static final ICommonsMap <String, MutableInt> s_aIntErrCache = new CommonsHashMap <> ();

  private InternalErrorHandler ()
  {}

  public static void setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setSMTPSettings (aSMTPSettings));
  }

  @Nullable
  public static ISMTPSettings getSMTPSettings ()
  {
    return s_aRWLock.readLocked ( () -> s_aEmailSettings.getSMTPSettings ());
  }

  public static void setSMTPSenderAddress (@Nullable final IEmailAddress aSenderAddress)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setSenderAddress (aSenderAddress));
  }

  @Nullable
  public static IEmailAddress getSMTPSenderAddress ()
  {
    return s_aRWLock.readLocked (s_aEmailSettings::getSenderAddress);
  }

  public static void setSMTPReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddress (aReceiverAddress));
  }

  public static void setSMTPReceiverAddresses (@Nullable final List <? extends IEmailAddress> aReceiverAddresses)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddresses (aReceiverAddresses));
  }

  public static void setSMTPReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
  {
    s_aRWLock.writeLocked ( () -> s_aEmailSettings.setReceiverAddresses (aReceiverAddresses));
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IEmailAddress> getSMTPReceiverAddresses ()
  {
    return s_aRWLock.readLocked (s_aEmailSettings::getReceiverAddresses);
  }

  /**
   * Enable the creation of a dump of all threads. Warning: this takes a lot of
   * CPU, so enable this only when you are not running a performance critical
   * application! The default is {@value #DEFAULT_ENABLE_FULL_THREAD_DUMPS}.
   *
   * @param bEnableFullThreadDumps
   *        <code>true</code> to enabled, <code>false</code> to disable.
   */
  public static void setEnableFullThreadDumps (final boolean bEnableFullThreadDumps)
  {
    s_aRWLock.writeLocked ( () -> s_bEnableFullThreadDumps = bEnableFullThreadDumps);
  }

  public static boolean isEnableFullThreadDumps ()
  {
    return s_aRWLock.readLocked ( () -> s_bEnableFullThreadDumps);
  }

  /**
   * @return The current custom exception handler or <code>null</code> if none
   *         is set.
   */
  @Nullable
  public static IInternalErrorCallback getCustomExceptionHandler ()
  {
    return s_aRWLock.readLocked ( () -> s_aCustomExceptionHandler);
  }

  /**
   * Set the custom exception handler.
   *
   * @param aCustomExceptionHandler
   *        The exception handler to be used. May be <code>null</code> to
   *        indicate none.
   */
  public static void setCustomExceptionHandler (@Nullable final IInternalErrorCallback aCustomExceptionHandler)
  {
    s_aRWLock.writeLocked ( () -> s_aCustomExceptionHandler = aCustomExceptionHandler);
  }

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

  private static void _sendInternalErrorMailToVendor (@Nonnull final InternalErrorMetadata aMetadata,
                                                      @Nonnull final ThreadDescriptor aCurrentThreadDescriptor,
                                                      @Nullable final ThreadDescriptorList aAllThreads,
                                                      @Nonnull final EmailSettings aEmailSettings)
  {
    int nOccurranceCount = 1;
    final String sThrowableStackTrace = aCurrentThreadDescriptor.getStackTrace ();
    if (StringHelper.hasText (sThrowableStackTrace))
    {
      // Check if an internal error was already sent for this stack trace
      final MutableInt aMI = s_aIntErrCache.get (sThrowableStackTrace);
      if (aMI != null)
      {
        // This stack trace was already found!
        aMI.inc ();

        // Send only every 100th invocation!
        nOccurranceCount = aMI.intValue ();
        if ((nOccurranceCount % 100) != 0)
        {
          s_aLogger.warn ("Not sending internal error mail, because this error occurred " +
                          nOccurranceCount +
                          " times");
          return;
        }
      }
      else
        s_aIntErrCache.put (sThrowableStackTrace, new MutableInt (1));
    }

    final IEmailAddress aSender = aEmailSettings.getSenderAddress ();
    final ICommonsList <IEmailAddress> aReceiver = aEmailSettings.getReceiverAddresses ();
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
      final String sMailSubject = StringHelper.getConcatenatedOnDemand ("Internal error", ' ', aMetadata.getErrorID ());

      // Main error thread dump
      final String sSeparator = "\n---------------------------------------------------------------\n";
      String sMailBody = aMetadata.getAsString () + sSeparator + aCurrentThreadDescriptor.getAsString () + sSeparator;
      if (aAllThreads != null)
        sMailBody += aAllThreads.getAsString () + sSeparator;

      final EmailData aEmailData = new EmailData (EEmailType.TEXT);
      aEmailData.setFrom (aSender);
      aEmailData.setTo (aReceiver);
      aEmailData.setSubject (sMailSubject);
      aEmailData.setBody (sMailBody);
      aEmailData.setAttachments (aEmailSettings.getAttachmentList ());

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
                                               @Nonnull final EmailSettings aEmailSettings)
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("internalerror");
    eRoot.appendChild (aMetadata.getAsMicroNode ());
    eRoot.appendChild (aCurrentDescriptor.getAsMicroNode ());
    if (aAllThreads != null)
      eRoot.appendChild (aAllThreads.getAsMicroNode ());

    final IEmailAttachmentList aEmailAttachments = aEmailSettings.getAttachmentList ();
    if (aEmailAttachments != null)
    {
      final ICommonsList <IEmailAttachmentDataSource> aAttachments = aEmailAttachments.getAsDataSourceList ();
      if (CollectionHelper.isNotEmpty (aAttachments))
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
                            MicroWriter.getXMLString (aDoc),
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
        aMetadata.addField ("Request URL", t2);
      }

      aMetadata.addField ("User agent", RequestHelper.getUserAgent (aRequestScope.getRequest ()).getAsString ());

      try
      {
        aMetadata.addField ("Remote IP address", aRequestScope.getRemoteAddr ());
      }
      catch (final Throwable t2)
      {
        // fall-through - happens in a weird case
        aMetadata.addField ("Remote IP address", t2);
      }

      // Mobile browser?
      final UAProfile aProfile = RequestHelper.getUAProfile (aRequestScope.getRequest ());
      if (!aProfile.equals (UAProfile.EMPTY))
        aMetadata.addField ("UAProfile", aProfile.toString ());

      // Add all request attributes
      for (final Map.Entry <String, Object> aEntry : aRequestScope.getAllAttributes ()
                                                                  .getSortedByKey (Comparator.naturalOrder ())
                                                                  .entrySet ())
        aMetadata.addField ("[Request] " + aEntry.getKey (), String.valueOf (aEntry.getValue ()));
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
        for (final Map.Entry <String, Object> aEntry : aSessionScope.getAllAttributes ()
                                                                    .getSortedByKey (Comparator.naturalOrder ())
                                                                    .entrySet ())
          aMetadata.addField ("[Session] " + aEntry.getKey (), String.valueOf (aEntry.getValue ()));
      }
    }

    // User ID
    try
    {
      aMetadata.addField ("User", LoggedInUserManager.getInstance ().getCurrentUserID ());
    }
    catch (final Throwable t2)
    {
      // Happens if no scope is available (or what so ever)
      aMetadata.addField ("User", t2);
    }

    // Disk space info
    final File aBasePath = WebFileIO.getDataIO ().getBasePathFile ();
    aMetadata.addField ("BaseDirectory", aBasePath.getAbsolutePath ());
    aMetadata.addField ("Usable bytes", Long.toString (aBasePath.getUsableSpace ()));
    aMetadata.addField ("Free bytes", Long.toString (aBasePath.getFreeSpace ()));

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
          for (final Map.Entry <String, String> aEntry : RequestLogger.getHTTPHeaderMap (aHttpRequest).entrySet ())
            aMetadata.addRequestHeader (aEntry.getKey (), aEntry.getValue ());
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
    }
    return aMetadata;
  }

  public static void sendInternalErrorMailToVendor (@Nullable final Throwable t,
                                                    @Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                                                    @Nullable final String sErrorID,
                                                    @Nullable final Map <String, String> aCustomData,
                                                    @Nonnull final EmailSettings aEmailSettings)
  {
    // Create all metadata from the request
    final InternalErrorMetadata aMetadata = fillInternalErrorMetaData (aRequestScope, sErrorID, aCustomData);

    // Get descriptor for crashed thread
    final ThreadDescriptor aCurrentThreadDescriptor = ThreadDescriptor.createForCurrentThread (t);

    // Get all thread descriptors
    ThreadDescriptorList aAllThreads = null;
    if (isEnableFullThreadDumps ())
      aAllThreads = ThreadDescriptorList.createWithAllThreads ();

    // Main mail sending
    _sendInternalErrorMailToVendor (aMetadata, aCurrentThreadDescriptor, aAllThreads, aEmailSettings);

    // Save as XML too
    _saveInternalErrorToXML (aMetadata, aCurrentThreadDescriptor, aAllThreads, aEmailSettings);
  }

  /**
   * Triggering of an internal error. This method should not be called manually
   * but instead {@link InternalErrorBuilder} should be used, as this is the
   * builder class for this method.
   *
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
   *        which case it defaults to {@link CGlobal#DEFAULT_LOCALE}.
   * @param bInvokeCustomExceptionHandler
   *        <code>true</code> to invoke the custom exception handler (if any is
   *        present), <code>false</code> to not do so.
   * @return The created unique error ID
   */
  @Nonnull
  @Nonempty
  public static String handleInternalError (@Nullable final IUIInternalErrorHandler aUIErrorHandler,
                                            @Nullable final Throwable t,
                                            @Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                                            @Nullable final Map <String, String> aCustomData,
                                            @Nullable final IEmailAttachmentList aEmailAttachments,
                                            @Nullable final Locale aDisplayLocale,
                                            final boolean bInvokeCustomExceptionHandler)
  {
    final Locale aRealDisplayLocale = aDisplayLocale != null ? aDisplayLocale : CGlobal.DEFAULT_LOCALE;

    final String sErrorID = createNewInternalErrorID ();

    // Log the error, to ensure the data is persisted!
    s_aLogger.error ("handleInternalError " + sErrorID, t);

    // Print on UI
    if (aUIErrorHandler != null)
      aUIErrorHandler.onInternalError (t, sErrorID, aRealDisplayLocale);

    if (GlobalDebug.isDebugMode ())
    {
      if (aCustomData != null)
        s_aLogger.error ("Custom data: " + aCustomData);

      // In case an unexpected error occurs in the UnitTest, make the test fail!
      if (t != null && StackTraceHelper.containsUnitTestElement (t.getStackTrace ()))
        throw new IllegalStateException ("Error executing unit test", t);
    }
    else
    {
      // GlobalDebug is disabled -> send mail with attachments
      sendInternalErrorMailToVendor (t,
                                     aRequestScope,
                                     sErrorID,
                                     aCustomData,
                                     s_aEmailSettings.getClone ().setAttachmentList (aEmailAttachments));
    }

    if (bInvokeCustomExceptionHandler)
    {
      // Invoke custom exception handler (if present)
      final IInternalErrorCallback aCustomExceptionHandler = getCustomExceptionHandler ();
      if (aCustomExceptionHandler != null)
        try
        {
          aCustomExceptionHandler.onInternalError (t, aRequestScope, sErrorID, aRealDisplayLocale);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Internal error in custom exception handler " + aCustomExceptionHandler, t2);
        }
    }

    return sErrorID;
  }
}
