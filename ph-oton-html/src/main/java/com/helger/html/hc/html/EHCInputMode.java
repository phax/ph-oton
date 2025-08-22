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
package com.helger.html.hc.html;

import com.helger.annotation.Nonempty;
import com.helger.base.lang.EnumHelper;
import com.helger.base.string.StringHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Defines the allowed values for the "inputmode" attribute
 *
 * @author Philip Helger
 * @since 9.3.0
 */
public enum EHCInputMode implements IHCHasHTMLAttributeValue
{
  /**
   * No virtual keyboard. For when the page implements its own keyboard input
   * control.
   */
  NONE ("none"),
  /**
   * Standard input keyboard for the user's current locale. Default value.
   */
  TEXT ("text"),
  /**
   * Fractional numeric input keyboard containing the digits and decimal
   * separator for the user's locale (typically . or ,). Devices may or may not
   * show a minus key (-).
   */
  DECIMAL ("decimal"),
  /**
   * Numeric input keyboard, but only requires the digits 0–9. Devices may or
   * may not show a minus key.
   */
  NUMERIC ("numeric"),
  /**
   * A telephone keypad input, including the digits 0–9, the asterisk (*), and
   * the pound (#) key. Inputs that require a telephone number should typically
   * use &lt;input type="tel"&gt; instead.
   */
  TEL ("tel"),
  /**
   * A virtual keyboard optimized for search input. For instance, the
   * return/submit key may be labeled "Search", along with possible other
   * optimizations. Inputs that require a search query should typically use
   * &lt;input type="search"&gt; instead.
   */
  SEARCH ("search"),
  /**
   * A virtual keyboard optimized for entering email addresses. Typically
   * includes the @ character as well as other optimizations. Inputs that
   * require email addresses should typically use &lt;input type="email"&gt;
   * instead.
   */
  EMAIL ("email"),
  /**
   * A keypad optimized for entering URLs. This may have the / key more
   * prominent, for example. Enhanced features could include history access and
   * so on. Inputs that require a URL should typically use &lt;input
   * type="url"&gt; instead.
   */
  URL ("url");

  public static final EHCInputMode DEFAULT = TEXT;

  private final String m_sAttrValue;

  EHCInputMode (@Nonnull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  @Nullable
  public static EHCInputMode getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.isEmpty (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCInputMode.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
