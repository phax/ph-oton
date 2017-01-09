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
package com.helger.html.meta;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.state.EChange;

/**
 * Represents a single meta element
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IMutableMetaElement extends IMetaElement
{
  /**
   * Set the value of the metatag in an unspecified locale.
   *
   * @param sContent
   *        The value to be set. If the content is <code>null</code> the value
   *        is removed.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setContent (@Nullable String sContent);

  /**
   * Set the value of the metatag in the given locale.
   *
   * @param aContentLocale
   *        The locale to set. May be <code>null</code>.
   * @param sContent
   *        The value to be set. If the content is <code>null</code> the value
   *        is removed.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setContent (@Nullable Locale aContentLocale, @Nullable String sContent);

  /**
   * Remove the content in the unspecified locale.
   *
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeContent ();

  /**
   * Remove the value of the given locale.
   *
   * @param aContentLocale
   *        The locale to be removed. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeContent (Locale aContentLocale);

  /**
   * Change the definition of "is http-equiv"
   *
   * @param bIsHttpEquiv
   *        the new value
   * @return {@link EChange}.
   */
  @Nonnull
  EChange setHttpEquiv (boolean bIsHttpEquiv);
}