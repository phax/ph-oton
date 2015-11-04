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
package com.helger.photon.security.password.constraint;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.microdom.IMicroElement;

/**
 * Base interface for a single password constraint type.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IPasswordConstraint extends Serializable
{
  /**
   * Check if the supplied password matches this constraint
   *
   * @param sPlainTextPassword
   *        The plain text password to check. May be <code>null</code>.
   * @return <code>true</code> if it is valid, <code>false</code> if it is not
   */
  boolean isPasswordValid (@Nullable String sPlainTextPassword);

  /**
   * Get a description of this constraint, so that it can be displayed to the
   * user
   *
   * @param aContentLocale
   *        The locale to get the description in. Never <code>null</code>.
   * @return <code>null</code> if no text is present in the provided locale
   */
  @Nullable
  String getDescription (@Nonnull Locale aContentLocale);

  /**
   * This method is responsible for filling a micro element for serializing the
   * password constraints.
   *
   * @param aElement
   *        The element to be filled. Never <code>null</code>.
   */
  void fillMicroElement (@Nonnull IMicroElement aElement);
}
