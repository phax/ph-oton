/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.state.EContinue;
import com.helger.html.hc.html.forms.HCHiddenField;

/**
 * Handle CSRF (Cross Site Request Forgery) issues on an {@link AbstractWebPage}
 * .
 *
 * @author Philip Helger
 */
public interface IWebPageCSRFHandler extends Serializable
{
  /**
   * @return <code>true</code> if CSRF prevention is enabled, <code>false</code>
   *         otherwise.
   */
  boolean isCSRFPreventionEnabled ();

  /**
   * Check if the nonce if the passed WPEC is correct. The failure handling is
   * implementation dependent.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @return {@link EContinue#CONTINUE} if CSRF checking is disabled or was
   *         successful.
   */
  @Nonnull
  EContinue checkCSRFNonce (@Nonnull IWebPageExecutionContext aWPEC);

  /**
   * @return The HTML nonce hidden field or <code>null</code> if CSRF prevention
   *         is disabled.
   */
  @Nullable
  HCHiddenField createCSRFNonceField ();
}
