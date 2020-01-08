/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.core.locale;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;

/**
 * Base interface for an application Locale manager.
 *
 * @author Philip Helger
 */
public interface ILocaleManager
{
  /**
   * Register a new locale
   *
   * @param aLocale
   *        The locale to be added. May not be <code>null</code>.
   * @return {@link EChange}.
   */
  @Nonnull
  EChange registerLocale (@Nonnull Locale aLocale);

  /**
   * Set the default locale. Must be one of the previously registered locales!
   *
   * @param aDefaultLocale
   *        The locale to be used as the default. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setDefaultLocale (@Nonnull Locale aDefaultLocale);

  /**
   * @return The application default locale. May be <code>null</code> if non is
   *         defined
   */
  @Nullable
  Locale getDefaultLocale ();

  /**
   * @return All available locales for the application.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <Locale> getAllAvailableLocales ();

  /**
   * @return <code>true</code> if at least one locale is present,
   *         <code>false</code> otherwise
   */
  boolean hasLocales ();

  /**
   * Check if the passed locale is a supported locale.
   *
   * @param aLocale
   *        The locale to check
   * @return <code>true</code> if the passed locale is supported,
   *         <code>false</code> otherwise.
   */
  boolean isSupportedLocale (@Nullable Locale aLocale);
}
