/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

/**
 * Keygen type?
 *
 * @author Philip Helger
 */
public enum EHCKeyGenType implements IHCHasHTMLAttributeValue
{
  /**
   * Specifies an RSA security algorithm. The user may be given a choice of RSA
   * key strengths
   */
  RSA ("rsa"),

  /**
   * Specifies a DSA security algorithm. The user may be given a choice of DSA
   * key sizes
   */
  DSA ("dsa"),

  /**
   * Specifies an Elliptic Curve security algorithm. The user may be given a
   * choice of EC key strengths
   */
  EC ("ec");

  /** Default KeyGen type: RSA */
  public static final EHCKeyGenType DEFAULT = RSA;

  private final String m_sAttrValue;

  private EHCKeyGenType (@Nonnull @Nonempty final String sAttrValue)
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
  public static EHCKeyGenType getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.hasNoText (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCKeyGenType.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
