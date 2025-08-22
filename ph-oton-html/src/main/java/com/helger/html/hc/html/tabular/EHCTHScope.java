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
package com.helger.html.hc.html.tabular;

import com.helger.annotation.Nonempty;
import com.helger.base.lang.EnumHelper;
import com.helger.base.string.StringHelper;
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Scope of an {@link com.helger.html.hc.html.tabular.HCTH} element.
 *
 * @author Philip Helger
 * @since 9.2.5
 */
public enum EHCTHScope implements IHCHasHTMLAttributeValue
{
  ROW ("row"),
  COL ("col"),
  ROWGROUP ("rowgroup"),
  COLGROUP ("colgroup");

  private final String m_sAttrValue;

  EHCTHScope (@Nonnull @Nonempty final String sAttrValue)
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
  public static EHCTHScope getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.hasNoText (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCTHScope.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
