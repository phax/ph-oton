/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.GlobalDebug;
import com.helger.commons.ICloneable;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.base64.Base64;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.scopes.mgr.ScopeSessionManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.io.PDTIOHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.security.login.LoggedInUserManager;
import com.helger.photon.basic.thread.ThreadDescriptor;
import com.helger.photon.basic.thread.ThreadDescriptorList;
import com.helger.photon.core.app.error.callback.IInternalErrorCallback;
import com.helger.photon.core.app.error.uihandler.IUIInternalErrorHandler;
import com.helger.smtp.EEmailType;
import com.helger.smtp.IEmailAttachmentDataSource;
import com.helger.smtp.IEmailAttachmentList;
import com.helger.smtp.IReadonlyEmailAttachmentList;
import com.helger.smtp.ISMTPSettings;
import com.helger.smtp.impl.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.smtp.transport.MailAPI;
import com.helger.web.datetime.PDTWebDateUtils;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.domain.ISessionWebScope;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.web.servlet.request.RequestLogger;
import com.helger.web.useragent.UserAgentDatabase;
import com.helger.web.useragent.uaprofile.UAProfile;
import com.helger.web.useragent.uaprofile.UAProfileDatabase;

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
    private List <IEmailAddress> m_aReceiverAddresses;
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
      return setReceiverAddresses (aReceiverAddress == null ? null : CollectionHelper.newList (aReceiverAddress));
    }

    @Nonnull
    public EmailSettings setReceiverAddresses (@Nullable final List <? extends IEmailAddress> aReceiverAddresses)
    {
      if (aReceiverAddresses != null && CollectionHelper.containsAnyNullElement (aReceiverAddresses))
        throw new IllegalArgumentException ("The list of receiver addresses may not contain any null element!");

      m_aReceiverAddresses = CollectionHelper.newList (aReceiverAddresses);
      return this;
    }

    @Nonnull
    public EmailSettings setReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
    {
      if (aReceiverAddresses != null && ArrayHelper.containsAnyNullElement (aReceiverAddresses))
        throw new IllegalArgumentException ("The array of receiver addresses may not contain any null element!");

      m_aReceiverAddresses = CollectionHelper.newList (aReceiverAddresses);
      return this;
    }

    @Nonnull
    @ReturnsMutableCopy
    public List <IEmailAddress> getReceiverAddresses ()
    {
      return CollectionHelper.newList (m_aReceiverAddresses);
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
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  private static final EmailSettings s_aEmailSettings = new EmailSettings ();
  private static IInternalErrorCallback s_aCustomExceptionHandler;
  private static boolean s_bEnableFullThreadDumps = DEFAULT_ENABLE_FULL_THREAD_DUMPS;

  private static final Map <String, MutableInt> s_aIntErrCache = new HashMap <String, MutableInt> ();

  private InternalErrorHandler ()
  {}

  public static void setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aEmailSettings.setSMTPSettings (aSMTPSettings);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nullable
  public static ISMTPSettings getSMTPSettings ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aEmailSettings.getSMTPSettings ();
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setSMTPSenderAddress (@Nullable final IEmailAddress aSenderAddress)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aEmailSettings.setSenderAddress (aSenderAddress);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nullable
  public static IEmailAddress getSMTPSenderAddress ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aEmailSettings.getSenderAddress ();
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setSMTPReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aEmailSettings.setReceiverAddress (aReceiverAddress);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public static void setSMTPReceiverAddresses (@Nullable final List <? extends IEmailAddress> aReceiverAddresses)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aEmailSettings.setReceiverAddresses (aReceiverAddresses);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public static void setSMTPReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aEmailSettings.setReceiverAddresses (aReceiverAddresses);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IEmailAddress> getSMTPReceiverAddresses ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aEmailSettings.getReceiverAddresses ();
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
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
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_bEnableFullThreadDumps = bEnableFullThreadDumps;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public static boolean isEnableFullThreadDumps ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bEnableFullThreadDumps;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The current custom exception handler or <code>null</code> if none
   *         is set.
   */
  @Nullable
  public static IInternalErrorCallback getCustomExceptionHandler ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aCustomExceptionHandler;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
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
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aCustomExceptionHandler = aCustomExceptionHandler;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
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
          s_aLogger.warn ("Not sending internal error mail, because this error occurred " + nOccurranceCount + " times");
          return;
        }
      }
      else
        s_aIntErrCache.put (sThrowableStackTrace, new MutableInt (1));
    }

    final IEmailAddress aSender = aEmailSettings.getSenderAddress ();
    final List <IEmailAddress> aReceiver = aEmailSettings.getReceiverAddresses ();
    final ISMTPSettings aSMTPSettings = aEmailSettings.getSMTPSettings ();

    if (aSender != null && !aReceiver.isEmpty () && aSMTPSettings != null)
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
        if (MailAPI.queueMail (aSMTPSettings, aEmailData).isFailure ())
          s_aLogger.warn ("Failed to send via MailAPI as well");
      }
    }
    else
      s_aLogger.warn ("Not sending internal error mail, because required fields are not set!");
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

    final IReadonlyEmailAttachmentList aEmailAttachments = aEmailSettings.getAttachmentList ();
    if (aEmailAttachments != null)
    {
      final List <IEmailAttachmentDataSource> aAttachments = aEmailAttachments.getAsDataSourceList ();
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
            eAttachment.appendText (Base64.encodeBytes (StreamUtils.getAllBytes (aDS.getInputStream ())));
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
    final String sFilename = StringHelper.getConcatenatedOnDemand (PDTIOHelper.getCurrentDateTimeForFilename (),
                                                                   "-",
                                                                   aMetadata.getErrorID ()) + ".xml";
    SimpleFileIO.writeFile (WebFileIO.getFile ("internal-errors/" + PDTFactory.getCurrentYear () + "/" + sFilename),
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
      aMetadata.addField ("Time", PDTWebDateUtils.getAsStringXSD (PDTFactory.getCurrentDateTime ()));
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

      aMetadata.addField ("User agent", UserAgentDatabase.getUserAgent (aRequestScope.getRequest ()).getAsString ());

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
      final UAProfile aProfile = UAProfileDatabase.getUAProfile (aRequestScope.getRequest ());
      if (!aProfile.equals (UAProfile.EMPTY))
        aMetadata.addField ("UAProfile", aProfile.toString ());

      // Add all request attributes
      for (final Map.Entry <String, Object> aEntry : CollectionHelper.getSortedByKey (aRequestScope.getAllAttributes ())
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
        for (final Map.Entry <String, Object> aEntry : CollectionHelper.getSortedByKey (aSessionScope.getAllAttributes ())
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
    final File aBasePath = WebFileIO.getBasePathFile ();
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
          final Cookie [] aCookies = aHttpRequest.getCookies ();
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
