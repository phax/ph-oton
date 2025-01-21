/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;

/**
 * Callback interface for CSRF error handling.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface ICSRFErrorHandler extends Serializable
{
  /**
   * Callback method that is executed if a CSRF nonce mismatch occurs.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param sProvidedNonce
   *        The provided nonce. May be <code>null</code>.
   * @param sExpectedNone
   *        The expected nonce. May neither be <code>null</code> nor empty.
   */
  void onCSRFError (@Nonnull IWebPageExecutionContext aWPEC, @Nullable String sProvidedNonce, @Nonnull @Nonempty String sExpectedNone);
}
