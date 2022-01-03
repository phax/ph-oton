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
package com.helger.html.hc.html;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.xml.microdom.IHasAttributeValue;

/**
 * Base interface for an HTML attribute value provider.
 *
 * @author Philip Helger
 */
public interface IHCHasHTMLAttributeValue extends IHasAttributeValue, Serializable
{
  /**
   * Check if the attribute values matches the passed on.
   * 
   * @param sAttrValue
   *        The attribute value to check. May be <code>null</code>.
   * @return <code>true</code> if the attribute values are equal
   * @see #getAttrValue()
   * @since 8.0.2
   */
  default boolean hasAttrValue (@Nullable final String sAttrValue)
  {
    return EqualsHelper.equals (sAttrValue, getAttrValue ());
  }
}
