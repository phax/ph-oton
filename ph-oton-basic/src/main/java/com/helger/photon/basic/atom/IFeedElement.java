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
package com.helger.photon.basic.atom;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;

/**
 * Base interface for all ATOM feeds
 *
 * @author Philip Helger
 */
public interface IFeedElement
{
  /**
   * Any element defined by this specification MAY have an xml:lang attribute,
   * whose content indicates the natural language for the element and its
   * descendants. The language context is only significant for elements and
   * attributes declared to be "Language-Sensitive" by this specification.
   * Requirements regarding the content and interpretation of xml:lang are
   * specified in XML 1.0 [W3C.REC-xml-20040204], Section 2.12.
   *
   * @return <code>null</code> or the current language
   */
  @Nullable
  String getLanguage ();

  /**
   * Set the language for this element.
   *
   * @param aContentLocale
   *        The language to set. May be <code>null</code>.
   */
  void setLanguage (@Nullable Locale aContentLocale);

  /**
   * Set the language for this element.
   *
   * @param sContentLanguage
   *        The language to set. May be <code>null</code>.
   */
  void setLanguage (@Nullable String sContentLanguage);

  /**
   * Convert this news feed element to an MicroElement with the given element
   * name. In case this element is not valid according to {@link #isValid()} it
   * should be safe anyway to create an element.
   *
   * @param sElementName
   *        The element name to use. May neither be <code>null</code> nor empty.
   * @return The created {@link IMicroElement} and never <code>null</code>.
   */
  @Nonnull
  IMicroElement getAsElement (String sElementName);

  /**
   * Check if this element matches the specification. This check method should
   * only be called once the feed is assembled completely since there are
   * interdependencies between the main feed and its entries.
   *
   * @return <code>true</code> if the element is valid, and <code>false</code>
   *         otherwise.
   */
  boolean isValid ();
}
