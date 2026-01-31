/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.interror;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.interror.uihandler.IUIInternalErrorHandler;
import com.helger.photon.core.interror.uihandler.UIInternalErrorHandler;
import com.helger.photon.io.WebFileIO;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.smtp.data.EmailAttachmentList;
import com.helger.smtp.data.IEmailAttachment;
import com.helger.smtp.data.IEmailAttachmentList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Builder class for internal error triggering. Call all relevant setters and
 * call {@link #handle()} at the end to trigger the internal error.
 *
 * @author Philip Helger
 * @see InternalErrorHandler
 */
@NotThreadSafe
public class InternalErrorBuilder
{
  /**
   * By default the class path entries are not added, because the Tomcat
   * classpath is not very interesting.
   *
   * @since 7.0.4
   */
  public static final boolean DEFAULT_ADD_CLASS_PATH = false;
  /**
   * By default custom exception handlers are invoked too
   */
  public static final boolean DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER = true;
  /**
   * By default only ever 100th internal errors with the same stack trace is
   * send be email.
   *
   * @since 7.0.6
   */
  public static final int DEFAULT_DUPLICATE_ELIMINIATION_COUNTER = 100;
  /**
   * Special custom data key for the error message.
   *
   * @since 7.0.4
   */
  public static final String KEY_ERROR_MSG = "Error Message";

  protected boolean m_bSendEmail = InternalErrorSettings.isSendEmail ();
  protected boolean m_bSaveAsXML = InternalErrorSettings.isSaveAsXML ();
  protected IUIInternalErrorHandler m_aUIErrorHandler;
  protected Throwable m_aThrowable;
  protected IRequestWebScopeWithoutResponse m_aRequestScope;
  protected final ICommonsOrderedMap <String, String> m_aCustomData = new CommonsLinkedHashMap <> ();
  protected EmailAttachmentList m_aEmailAttachments;
  protected Locale m_aDisplayLocale;
  protected boolean m_bInvokeCustomExceptionHandler = DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER;
  protected boolean m_bAddClassPath = DEFAULT_ADD_CLASS_PATH;
  protected int m_nDuplicateEliminiationCounter = DEFAULT_DUPLICATE_ELIMINIATION_COUNTER;
  protected InternalErrorEmailSettings m_aCustomEmailSettings;

  public InternalErrorBuilder ()
  {
    addCustomData ("GlobalDebug.debug", GlobalDebug.isDebugMode ());
    addCustomData ("GlobalDebug.production", GlobalDebug.isProductionMode ());

    // Disk space info
    final File aBasePath = WebFileIO.getDataIO ().getBasePathFile ();
    addCustomData ("Data Directory", aBasePath.getAbsolutePath ());
    addCustomData ("Usable bytes", Long.toString (aBasePath.getUsableSpace ()));
    addCustomData ("Free bytes", Long.toString (aBasePath.getFreeSpace ()));
    addCustomData ("ServletContext Base path", WebFileIO.getServletContextIO ().getBasePath ());

    // User ID
    try
    {
      addCustomData ("User", LoggedInUserManager.getInstance ().getCurrentUserID ());
    }
    catch (final Exception ex2)
    {
      // Happens if no scope is available (or what so ever)
      addCustomData ("User", "Unknown");
    }
  }

  /**
   * Send the internal error by email?
   *
   * @param bSendEmail
   *        <code>true</code> to do so, <code>false</code> to not do it.
   * @return this for chaining
   * @since 7.0.6
   */
  @NonNull
  public final InternalErrorBuilder setSendEmail (final boolean bSendEmail)
  {
    m_bSendEmail = bSendEmail;
    return this;
  }

  /**
   * @return <code>true</code> if the internal error should be send by email
   *         (default), or <code>false</code> if it should not.
   * @since 7.0.6
   */
  public boolean isSendEmail ()
  {
    return m_bSendEmail;
  }

  /**
   * Save the internal error also as XML?
   *
   * @param bSaveAsXML
   *        <code>true</code> to do so, <code>false</code> to not do it.
   * @return this for chaining
   * @since 7.0.6
   */
  @NonNull
  public final InternalErrorBuilder setSaveAsXML (final boolean bSaveAsXML)
  {
    m_bSaveAsXML = bSaveAsXML;
    return this;
  }

  /**
   * @return <code>true</code> if the internal error should also be saved as XML
   *         (default), or <code>false</code> if it should not.
   * @since 7.0.6
   */
  public boolean isSaveAsXML ()
  {
    return m_bSaveAsXML;
  }

  @NonNull
  public final InternalErrorBuilder setUIErrorHandlerFor (@NonNull final IHCNodeWithChildren <?> aParentNode)
  {
    return setUIErrorHandler (new UIInternalErrorHandler (aParentNode));
  }

  @NonNull
  public final InternalErrorBuilder setUIErrorHandler (@Nullable final IUIInternalErrorHandler aUIErrorHandler)
  {
    m_aUIErrorHandler = aUIErrorHandler;
    return this;
  }

  @Nullable
  public IUIInternalErrorHandler getUIErrorHandler ()
  {
    return m_aUIErrorHandler;
  }

  @NonNull
  public final InternalErrorBuilder setThrowable (@Nullable final Throwable t)
  {
    m_aThrowable = t;
    return this;
  }

  @Nullable
  public Throwable getThrowable ()
  {
    return m_aThrowable;
  }

  @NonNull
  public final InternalErrorBuilder setRequestScope (@Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    m_aRequestScope = aRequestScope;
    return this;
  }

  @Nullable
  public IRequestWebScopeWithoutResponse getRequestScope ()
  {
    return m_aRequestScope;
  }

  @NonNull
  public final InternalErrorBuilder addErrorMessage (@Nullable final String sErrorMessage)
  {
    return addCustomData (KEY_ERROR_MSG, sErrorMessage);
  }

  @Nullable
  public String getErrorMessage (@Nullable final String sDefaultValue)
  {
    return m_aCustomData.getOrDefault (KEY_ERROR_MSG, sDefaultValue);
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@NonNull final String sKey, final boolean bValue)
  {
    return addCustomData (sKey, Boolean.toString (bValue));
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@NonNull final String sKey, final int nValue)
  {
    return addCustomData (sKey, Integer.toString (nValue));
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@NonNull final String sKey, final long nValue)
  {
    return addCustomData (sKey, Long.toString (nValue));
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@NonNull final String sKey, @NonNull final Object aValue)
  {
    return addCustomData (sKey, String.valueOf (aValue));
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@NonNull final String sKey, @Nullable final String sValue)
  {
    m_aCustomData.put (sKey, sValue);
    return this;
  }

  @NonNull
  public final InternalErrorBuilder addCustomData (@Nullable final Map <String, String> aCustomData)
  {
    if (aCustomData != null)
      m_aCustomData.putAll (aCustomData);
    return this;
  }

  @NonNull
  public final InternalErrorBuilder setCustomData (@Nullable final Map <String, String> aCustomData)
  {
    m_aCustomData.setAll (aCustomData);
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomData ()
  {
    return m_aCustomData.getClone ();
  }

  public void forEachCustomData (@NonNull final BiConsumer <String, String> aConsumer)
  {
    m_aCustomData.forEach (aConsumer);
  }

  @NonNull
  public final InternalErrorBuilder addEmailAttachment (@NonNull final IEmailAttachment aEmailAttachment)
  {
    if (m_aEmailAttachments == null)
      m_aEmailAttachments = new EmailAttachmentList ();
    m_aEmailAttachments.addAttachment (aEmailAttachment);
    return this;
  }

  @NonNull
  public final InternalErrorBuilder setEmailAttachmentList (@Nullable final IEmailAttachmentList aEmailAttachments)
  {
    m_aEmailAttachments = aEmailAttachments == null ? null : new EmailAttachmentList (aEmailAttachments);
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public EmailAttachmentList getEmailAttachmentList ()
  {
    return new EmailAttachmentList (m_aEmailAttachments);
  }

  @NonNull
  public final InternalErrorBuilder setDisplayLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
    return this;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @NonNull
  public final InternalErrorBuilder setInvokeCustomExceptionHandler (final boolean bInvokeCustomExceptionHandler)
  {
    m_bInvokeCustomExceptionHandler = bInvokeCustomExceptionHandler;
    return this;
  }

  public boolean isInvokeCustomExceptionHandler ()
  {
    return m_bInvokeCustomExceptionHandler;
  }

  /**
   * Add the class path to the internal errors?
   *
   * @param bAddClassPath
   *        <code>true</code> to add the class path entries, <code>false</code>
   *        to not do it.
   * @return this for chaining
   * @since 7.0.4
   */
  @NonNull
  public final InternalErrorBuilder setAddClassPath (final boolean bAddClassPath)
  {
    m_bAddClassPath = bAddClassPath;
    return this;
  }

  /**
   * @return <code>true</code> if the class path should be added to the internal
   *         error (default), or <code>false</code> if it should not.
   */
  public boolean isAddClassPath ()
  {
    return m_bAddClassPath;
  }

  /**
   * Set the duplicate elimination counter.
   *
   * @param nDuplicateEliminiationCounter
   *        The value to set. Must be &ge; 0. Pass 0 to disable any duplicate
   *        elimination and send all errors by email.
   * @return this for chaining
   * @since 7.0.6
   */
  @NonNull
  public final InternalErrorBuilder setDuplicateEliminiationCounter (@Nonnegative final int nDuplicateEliminiationCounter)
  {
    ValueEnforcer.isGE0 (nDuplicateEliminiationCounter, "DuplicateEliminiationCounter");
    m_nDuplicateEliminiationCounter = nDuplicateEliminiationCounter;
    return this;
  }

  /**
   * @return The duplicate elimination counter. Defaults to
   *         {@link #DEFAULT_DUPLICATE_ELIMINIATION_COUNTER}.
   * @since 7.0.6
   */
  @Nonnegative
  public int getDuplicateEliminationCounter ()
  {
    return m_nDuplicateEliminiationCounter;
  }

  /**
   * Shortcut for setting display locale and request scope at once from a web
   * execution context
   *
   * @param aSWEC
   *        The web execution context to use. May not be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public final InternalErrorBuilder setFromWebExecutionContext (@NonNull final ISimpleWebExecutionContext aSWEC)
  {
    setDisplayLocale (aSWEC.getDisplayLocale ());
    setRequestScope (aSWEC.getRequestScope ());
    return this;
  }

  /**
   * Define custom internal email settings to use.
   *
   * @param aCustomEmailSettings
   *        The email settings to use. May be <code>null</code>.
   * @return this for chaining
   * @since 9.3.0
   */
  @NonNull
  public final InternalErrorBuilder setCustomEmailSettings (@Nullable final InternalErrorEmailSettings aCustomEmailSettings)
  {
    m_aCustomEmailSettings = aCustomEmailSettings;
    return this;
  }

  /**
   * The main handling routine
   *
   * @return The created error ID. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public String handle ()
  {
    return InternalErrorHandler.handleInternalError (m_bSendEmail,
                                                     m_bSaveAsXML,
                                                     m_aUIErrorHandler,
                                                     m_aThrowable,
                                                     m_aRequestScope,
                                                     m_aCustomData,
                                                     m_aEmailAttachments,
                                                     m_aDisplayLocale,
                                                     m_bInvokeCustomExceptionHandler,
                                                     m_bAddClassPath,
                                                     m_nDuplicateEliminiationCounter,
                                                     m_aCustomEmailSettings);
  }
}
