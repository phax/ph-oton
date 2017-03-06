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

import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.commons.debug.GlobalDebug;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.error.uihandler.IUIInternalErrorHandler;
import com.helger.photon.core.app.error.uihandler.UIInternalErrorHandler;
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
  /** By default custom exception handlers are invoked too */
  public static final boolean DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER = true;
  /**
   * By default the class path entries are send by mail.
   *
   * @since 7.0.4
   */
  public static final boolean DEFAULT_ADD_CLASS_PATH = true;
  /**
   * Special custom data key for the error message.
   *
   * @since 7.0.4
   */
  public static final String KEY_ERROR_MSG = "Error Message";

  protected IUIInternalErrorHandler m_aUIErrorHandler;
  protected Throwable m_aThrowable;
  protected IRequestWebScopeWithoutResponse m_aRequestScope;
  protected final ICommonsOrderedMap <String, String> m_aCustomData = new CommonsLinkedHashMap<> ();
  protected EmailAttachmentList m_aEmailAttachments;
  protected Locale m_aDisplayLocale;
  protected boolean m_bInvokeCustomExceptionHandler = DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER;
  protected boolean m_bAddClassPath = DEFAULT_ADD_CLASS_PATH;

  public InternalErrorBuilder ()
  {
    addCustomData ("GlobalDebug.debug", GlobalDebug.isDebugMode ());
    addCustomData ("GlobalDebug.production", GlobalDebug.isProductionMode ());
  }

  @Nonnull
  public InternalErrorBuilder setUIErrorHandlerFor (@Nonnull final IHCNodeWithChildren <?> aParentNode)
  {
    return setUIErrorHandler (new UIInternalErrorHandler (aParentNode));
  }

  @Nonnull
  public InternalErrorBuilder setUIErrorHandler (@Nullable final IUIInternalErrorHandler aUIErrorHandler)
  {
    m_aUIErrorHandler = aUIErrorHandler;
    return this;
  }

  @Nullable
  public IUIInternalErrorHandler getUIErrorHandler ()
  {
    return m_aUIErrorHandler;
  }

  @Nonnull
  public InternalErrorBuilder setThrowable (@Nullable final Throwable t)
  {
    m_aThrowable = t;
    return this;
  }

  @Nullable
  public Throwable getThrowable ()
  {
    return m_aThrowable;
  }

  @Nonnull
  public InternalErrorBuilder setRequestScope (@Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    m_aRequestScope = aRequestScope;
    return this;
  }

  @Nullable
  public IRequestWebScopeWithoutResponse getRequestScope ()
  {
    return m_aRequestScope;
  }

  @Nonnull
  public InternalErrorBuilder addErrorMessage (@Nonnull @Nonempty final String sErrorMessage)
  {
    return addCustomData (KEY_ERROR_MSG, sErrorMessage);
  }

  @Nullable
  public String getErrorMessage (@Nullable final String sDefaultValue)
  {
    return m_aCustomData.getOrDefault (KEY_ERROR_MSG, sDefaultValue);
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, final boolean bValue)
  {
    return addCustomData (sKey, Boolean.toString (bValue));
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, final int nValue)
  {
    return addCustomData (sKey, Integer.toString (nValue));
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, final long nValue)
  {
    return addCustomData (sKey, Long.toString (nValue));
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, @Nonnull final Object aValue)
  {
    return addCustomData (sKey, String.valueOf (aValue));
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, @Nullable final String sValue)
  {
    m_aCustomData.put (sKey, sValue);
    return this;
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nullable final Map <String, String> aMap)
  {
    if (aMap != null)
      m_aCustomData.putAll (aMap);
    return this;
  }

  @Nonnull
  public InternalErrorBuilder setCustomData (@Nullable final Map <String, String> aCustomData)
  {
    m_aCustomData.setAll (aCustomData);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomData ()
  {
    return m_aCustomData.getClone ();
  }

  public void forEachCustomData (@Nonnull final BiConsumer <String, String> aConsumer)
  {
    m_aCustomData.forEach (aConsumer);
  }

  @Nonnull
  public InternalErrorBuilder addEmailAttachment (@Nullable final IEmailAttachment aEmailAttachment)
  {
    if (m_aEmailAttachments == null)
      m_aEmailAttachments = new EmailAttachmentList ();
    m_aEmailAttachments.addAttachment (aEmailAttachment);
    return this;
  }

  @Nonnull
  public InternalErrorBuilder setEmailAttachmentList (@Nullable final IEmailAttachmentList aEmailAttachments)
  {
    m_aEmailAttachments = aEmailAttachments == null ? null : new EmailAttachmentList (aEmailAttachments);
    return this;
  }

  @Nonnull
  public EmailAttachmentList getEmailAttachmentList ()
  {
    return new EmailAttachmentList (m_aEmailAttachments);
  }

  @Nonnull
  public InternalErrorBuilder setDisplayLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
    return this;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public InternalErrorBuilder setInvokeCustomExceptionHandler (final boolean bInvokeCustomExceptionHandler)
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
  @Nonnull
  public InternalErrorBuilder setAddClassPath (final boolean bAddClassPath)
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
   * Shortcut for setting display locale and request scope at once from a web
   * execution context
   *
   * @param aSWEC
   *        The web execution context to use. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public InternalErrorBuilder setFromWebExecutionContext (@Nullable final ISimpleWebExecutionContext aSWEC)
  {
    setDisplayLocale (aSWEC.getDisplayLocale ());
    setRequestScope (aSWEC.getRequestScope ());
    return this;
  }

  /**
   * The main handling routine
   *
   * @return The created error ID. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String handle ()
  {
    return InternalErrorHandler.handleInternalError (m_aUIErrorHandler,
                                                     m_aThrowable,
                                                     m_aRequestScope,
                                                     m_aCustomData,
                                                     m_aEmailAttachments,
                                                     m_aDisplayLocale,
                                                     m_bInvokeCustomExceptionHandler,
                                                     m_bAddClassPath);
  }
}
