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
package com.helger.html.meta;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.base.state.EChange;

/**
 * Represents a single meta element
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IMutableMetaElement extends IMetaElement
{
  /**
   * Set the type of the meta element.
   *
   * @param eType
   *        The type to use. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @NonNull
  EChange setType (@NonNull EMetaElementType eType);

  /**
   * Set the name of the meta element. May usually neither be <code>null</code>
   * nor empty, except for {@link EMetaElementType#CHARSET} where the name does
   * not matter.
   * 
   * @param sName
   *        The name to use. May not be <code>null</code>.
   * @return {@link EChange}.
   */
  @NonNull
  EChange setName (@NonNull String sName);

  /**
   * Set the value of the meta element in an unspecified locale.
   *
   * @param sContent
   *        The value to be set. If the content is <code>null</code> the value
   *        is removed.
   * @return {@link EChange}
   */
  @NonNull
  default EChange setContent (@Nullable final String sContent)
  {
    return setContent ((Locale) null, sContent);
  }

  /**
   * Set the value of the meta element in the given locale.
   *
   * @param aContentLocale
   *        The locale to set. May be <code>null</code>.
   * @param sContent
   *        The value to be set. If the content is <code>null</code> the value
   *        is removed.
   * @return {@link EChange}
   */
  @NonNull
  EChange setContent (@Nullable Locale aContentLocale, @Nullable String sContent);

  /**
   * Remove the content in the unspecified locale.
   *
   * @return {@link EChange}
   */
  @NonNull
  default EChange removeContent ()
  {
    return removeContent ((Locale) null);
  }

  /**
   * Remove the value of the given locale.
   *
   * @param aContentLocale
   *        The locale to be removed. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @NonNull
  EChange removeContent (Locale aContentLocale);
}
