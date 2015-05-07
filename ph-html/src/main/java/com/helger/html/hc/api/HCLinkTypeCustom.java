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
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Implementation of custom {@link IHCLinkType} objects that cannot be found in
 * {@link EHCLinkType}.
 * 
 * @author Philip Helger
 */
public final class HCLinkTypeCustom implements IHCLinkType
{
  private final String m_sAttrValue;
  private final boolean m_bAllowedMoreThanOnce;

  public HCLinkTypeCustom (@Nonnull @Nonempty final String sAttrValue)
  {
    this (sAttrValue, false);
  }

  public HCLinkTypeCustom (@Nonnull @Nonempty final String sAttrValue, final boolean bAllowedMoreThanOnce)
  {
    if (StringHelper.hasNoText (sAttrValue))
      throw new IllegalArgumentException ("attrValue");

    m_sAttrValue = sAttrValue;
    m_bAllowedMoreThanOnce = bAllowedMoreThanOnce;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  public boolean isAllowedMoreThanOnce ()
  {
    return m_bAllowedMoreThanOnce;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("attrValue", m_sAttrValue)
                                       .append ("allowedMoreThanOnce", m_bAllowedMoreThanOnce)
                                       .toString ();
  }
}
