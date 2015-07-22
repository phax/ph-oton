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

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.error.uihandler.IUIInternalErrorHandler;
import com.helger.photon.core.app.error.uihandler.UIInternalErrorHandler;
import com.helger.smtp.IEmailAttachment;
import com.helger.smtp.IEmailAttachmentList;
import com.helger.smtp.impl.EmailAttachmentList;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;

/**
 * Builder class for internal error triggering. Call all relevant setters and
 * call {@link #handle()} at the end to trigger the internal error.
 *
 * @author Philip Helger
 * @see InternalErrorHandler
 */
public class InternalErrorBuilder
{
  public static final boolean DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER = true;

  private IUIInternalErrorHandler m_aUIErrorHandler;
  private Throwable m_aThrowable;
  private IRequestWebScopeWithoutResponse m_aRequestScope;
  private Map <String, String> m_aCustomData;
  private EmailAttachmentList m_aEmailAttachments;
  private Locale m_aDisplayLocale;
  private boolean m_bInvokeCustomExceptionHandler = DEFAULT_INVOKE_CUSTOM_EXCEPTION_HANDLER;

  public InternalErrorBuilder ()
  {
    addCustomData ("GlobalDebug.debug", Boolean.toString (GlobalDebug.isDebugMode ()));
    addCustomData ("GlobalDebug.production", Boolean.toString (GlobalDebug.isProductionMode ()));
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

  @Nonnull
  public InternalErrorBuilder setThrowable (@Nullable final Throwable t)
  {
    m_aThrowable = t;
    return this;
  }

  @Nonnull
  public InternalErrorBuilder setRequestScope (@Nullable final IRequestWebScopeWithoutResponse aRequestScope)
  {
    m_aRequestScope = aRequestScope;
    return this;
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, @Nonnull final Object aValue)
  {
    return addCustomData (sKey, String.valueOf (aValue));
  }

  @Nonnull
  public InternalErrorBuilder addCustomData (@Nonnull final String sKey, @Nullable final String sValue)
  {
    if (m_aCustomData == null)
      m_aCustomData = new LinkedHashMap <String, String> ();
    m_aCustomData.put (sKey, sValue);
    return this;
  }

  @Nonnull
  public InternalErrorBuilder setCustomData (@Nullable final Map <String, String> aCustomData)
  {
    m_aCustomData = aCustomData;
    return this;
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
  public InternalErrorBuilder setDisplayLocale (@Nullable final Locale aDisplayLocale)
  {
    m_aDisplayLocale = aDisplayLocale;
    return this;
  }

  @Nonnull
  public InternalErrorBuilder setInvokeCustomExceptionHandler (final boolean bInvokeCustomExceptionHandler)
  {
    m_bInvokeCustomExceptionHandler = bInvokeCustomExceptionHandler;
    return this;
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
                                                     m_bInvokeCustomExceptionHandler);
  }
}
