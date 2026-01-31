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
package com.helger.html.hc.html.embedded;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.lang.EnumHelper;
import com.helger.base.string.StringHelper;
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

/**
 * Enumeration for {@link com.helger.html.hc.html.embedded.HCIFrame} alignment.
 *
 * @author Philip Helger
 */
public enum EHCIFrameAlign implements IHCHasHTMLAttributeValue
{
  TOP ("top"),
  MIDDLE ("middle"),
  BOTTOM ("bottom"),
  LEFT ("left"),
  RIGHT ("right");

  private final String m_sAttrValue;

  EHCIFrameAlign (@NonNull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @NonNull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  @Nullable
  public static EHCIFrameAlign getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.isEmpty (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCIFrameAlign.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
