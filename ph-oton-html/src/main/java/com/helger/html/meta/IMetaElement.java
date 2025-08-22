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
package com.helger.html.meta;

import java.util.Locale;
import java.util.Map;

import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroNode;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents a single meta element
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
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
  ICommonsOrderedSet <Locale> getAllLocales ();

  /**
   * @return The content which was specified locale independent.
   */
  @Nullable
  String getContentLanguageIndependent ();

  /**
   * @return A non-<code>null</code> map from language to value.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedMap <Locale, String> getContentMap ();

  /**
   * @return A non-<code>null</code> map from language to value.
   */
  @Nonnull
  Iterable <Map.Entry <Locale, String>> getContent ();

  /**
   * Get the contents of this meta tag as a self contained list of meta tag
   * values.
   *
   * @return A non-<code>null</code> modifiable list of meta tags.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IMetaElementValue> getAsMetaElementValueList ();

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
