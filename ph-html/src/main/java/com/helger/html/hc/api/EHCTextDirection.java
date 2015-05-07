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
package com.helger.html.hc.api;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.annotations.SinceHTML5;

/**
 * Enum for HTML text directions.
 * 
 * @author Philip Helger
 */
public enum EHCTextDirection implements IHCHasHTMLAttributeValue
{
  /** left-to-right */
  LTR ("ltr"),

  /** right-to-left */
  RTL ("rtl"),

  /** automatic */
  @SinceHTML5
  AUTO ("auto");

  /**
   * Default text direction: left-to-right
   */
  public static final EHCTextDirection DEFAULT = LTR;

  /**
   * Default text direction for HTML5: left-to-right
   */
  public static final EHCTextDirection DEFAULT_HTML5 = AUTO;

  private final String m_sAttrValue;

  private EHCTextDirection (@Nonnull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }
}
