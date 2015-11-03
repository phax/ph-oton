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
package com.helger.photon.basic.auth.identify;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ISuccessIndicator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.auth.credentials.CredentialValidationResult;
import com.helger.photon.basic.auth.token.IAuthToken;

public class AuthIdentificationResult implements ISuccessIndicator, Serializable
{
  private final CredentialValidationResult m_aCredentialValidationResult;
  private final IAuthToken m_aAuthToken;

  /**
   * Constructor for a failure.
   *
   * @param aCredentialValidationResult
   *        The validation result. May not be <code>null</code> and must be a
   *        failure!
   */
  public AuthIdentificationResult (@Nonnull final CredentialValidationResult aCredentialValidationResult)
  {
    ValueEnforcer.notNull (aCredentialValidationResult, "CredentialValidationResult");
    if (aCredentialValidationResult.isSuccess ())
      throw new IllegalStateException ("Don't call this method for successfuly credential validation!");
    m_aCredentialValidationResult = aCredentialValidationResult;
    m_aAuthToken = null;
  }

  /**
   * Constructor for success.
   *
   * @param aAuthToken
   *        The auth token. May not be <code>null</code>.
   */
  public AuthIdentificationResult (@Nonnull final IAuthToken aAuthToken)
  {
    ValueEnforcer.notNull (aAuthToken, "AuthToken");
    m_aCredentialValidationResult = null;
    m_aAuthToken = aAuthToken;
  }

  public boolean isSuccess ()
  {
    return m_aCredentialValidationResult == null;
  }

  public boolean isFailure ()
  {
    return m_aCredentialValidationResult != null;
  }

  /**
   * @return The credential validation error message or <code>null</code> in
   *         case of successful identification
   */
  @Nullable
  public String getCredentialValidationErrorMessage ()
  {
    return m_aCredentialValidationResult == null ? null : m_aCredentialValidationResult.getErrorMessage ();
  }

  /**
   * @return The auth token in case of successful identification or
   *         <code>null</code> in case of an error.
   */
  @Nullable
  public IAuthToken getAuthToken ()
  {
    return m_aAuthToken;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("CredentialValidationResult", m_aCredentialValidationResult)
                                       .append ("AuthToken", m_aAuthToken)
                                       .toString ();
  }
}
