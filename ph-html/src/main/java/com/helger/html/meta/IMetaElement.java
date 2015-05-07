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
package com.helger.html.meta;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.state.EChange;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents a single meta element
 *
 * @author Philip Helger
 */
public interface IMetaElement extends IMetaElementDeclaration
{
  /**
   * Check if the meta tag is language independent.
   *
   * @return <code>true</code> if the meta tag is language independent,
   *         <code>false</code> if it is not
   */
  boolean isLanguageIndependent ();

  /**
   * @return A set with all locales a value is present
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <Locale> getAllLocales ();

  /**
   * @return A non-<code>null</code> map from language to value.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <Locale, String> getContent ();

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

  /**
   * Get the contents of this meta tag as a self contained list of meta tag
   * values.
   *
   * @return A non-<code>null</code> modifiable list of meta tags.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <IMetaElementValue> getAsMetaElementValueList ();

  /**
   * Convert this meta element to an {@link IMicroNode}
   *
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return <code>null</code> if this meta element contains no content.
   */
  @Nullable
  IMicroNode convertToNode (@Nonnull IHCConversionSettingsToNode aConversionSettings);
}
