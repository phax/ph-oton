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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

/**
 * The type of an {@link com.helger.html.hc.html.forms.AbstractHCInput} element.
 *
 * @author Philip Helger
 */
public enum EHCInputType implements IHCHasHTMLAttributeValue
{
  HIDDEN ("hidden", false),
  TEXT ("text", true),
  @SinceHTML5
  SEARCH ("search", true),
  @SinceHTML5
  TEL ("tel", true),
  @SinceHTML5
  URL ("url", true),
  @SinceHTML5
  EMAIL ("email", true),
  PASSWORD ("password", true),
  @SinceHTML5
  DATE ("date", true),
  @SinceHTML5
  TIME ("time", true),
  @SinceHTML5
  NUMBER ("number", true),
  @SinceHTML5
  RANGE ("range", false),
  @SinceHTML5
  COLOR ("color", true),
  CHECKBOX ("checkbox", false),
  RADIO ("radio", false),
  FILE ("file", true),
  SUBMIT ("submit", false),
  IMAGE ("image", false),
  RESET ("reset", false),
  BUTTON ("button", false);

  private final String m_sAttrValue;
  private final boolean m_bHasPlaceholder;

  EHCInputType (@Nonnull @Nonempty final String sAttrValue, final boolean bHasPlaceholder)
  {
    m_sAttrValue = sAttrValue;
    m_bHasPlaceholder = bHasPlaceholder;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  public boolean hasPlaceholder ()
  {
    return m_bHasPlaceholder;
  }

  @Nullable
  public static EHCInputType getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.hasNoText (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCInputType.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
