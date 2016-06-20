/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.login;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.photon.basic.auth.credentials.ICredentialValidationResult;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.html.AbstractHTMLProvider;
import com.helger.photon.uicore.page.IWebPageCSRFHandler;
import com.helger.photon.uicore.page.WebPageCSRFHandler;

/**
 * Abstract base class for providing an HTML login screen.
 *
 * @author Philip Helger
 */
public abstract class AbstractLoginHTMLProvider extends AbstractHTMLProvider
{
  private final boolean m_bLoginError;
  private final ICredentialValidationResult m_aLoginResult;
  private IWebPageCSRFHandler m_aCSRFHandler = WebPageCSRFHandler.INSTANCE;

  public AbstractLoginHTMLProvider (final boolean bLoginError, @Nonnull final ICredentialValidationResult aLoginResult)
  {
    m_bLoginError = bLoginError;
    m_aLoginResult = ValueEnforcer.notNull (aLoginResult, "LoginResult");
  }

  /**
   * @return <code>true</code> if the login screen is shown for an error,
   *         <code>false</code> if the login screen is shown for the first time.
   */
  public final boolean isLoginError ()
  {
    return m_bLoginError;
  }

  @Nonnull
  public final ICredentialValidationResult getLoginResult ()
  {
    return m_aLoginResult;
  }

  /**
   * @return The current CSRF handler. Never <code>null</code>.
   */
  public final IWebPageCSRFHandler getCSRFHandler ()
  {
    return m_aCSRFHandler;
  }

  /**
   * Set the CSRF handler to be used.
   *
   * @param aCSRFHandler
   *        The new handler. May not be <code>null</code>.
   */
  public final void setCSRFHandler (@Nonnull final IWebPageCSRFHandler aCSRFHandler)
  {
    m_aCSRFHandler = ValueEnforcer.notNull (aCSRFHandler, "CSRFHandler");
  }

  @Nullable
  @OverrideOnDemand
  protected String getTextHeader (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.LOGIN_HEADER.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected String getTextErrorMessage (@Nonnull final Locale aDisplayLocale,
                                        @Nonnull final ICredentialValidationResult aLoginResult)
  {
    return EPhotonCoreText.LOGIN_ERROR_MSG.getDisplayText (aDisplayLocale) +
           " " +
           aLoginResult.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected String getTextFieldUserName (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.LOGIN_FIELD_USERNAME.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected String getTextFieldPassword (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.LOGIN_FIELD_PASSWORD.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected String getLoginButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.LOGIN_BUTTON_SUBMIT.getDisplayText (aDisplayLocale);
  }
}
