/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.css.property.CCSSProperties;
import com.helger.css.propertyvalue.CCSSValue;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

public class PrismPluginLineNumbers implements IPrismPlugin
{
  public static final ICSSClassProvider CSS_CLASS_LINE_NUMBERS = DefaultCSSClassProvider.create ("line-numbers");
  public static final ICSSClassProvider CSS_CLASS_NO_LINE_NUMBERS = DefaultCSSClassProvider.create ("no-line-numbers");

  private boolean m_bLineNumbers = true;
  private Integer m_aStart = null;
  private boolean m_bSoftWrap = false;

  public PrismPluginLineNumbers ()
  {}

  @Nonnull
  public PrismPluginLineNumbers setLineNumbers (final boolean b)
  {
    m_bLineNumbers = b;
    return this;
  }

  @Nonnull
  public PrismPluginLineNumbers setStart (final int n)
  {
    return setStart (Integer.valueOf (n));
  }

  @Nonnull
  public PrismPluginLineNumbers setStart (@Nullable final Integer a)
  {
    m_aStart = a;
    return this;
  }

  @Nonnull
  public PrismPluginLineNumbers setSoftWrap (final boolean b)
  {
    m_bSoftWrap = b;
    return this;
  }

  public void applyOnPre (@Nonnull final IMicroElement aPreElement,
                          @Nonnull final HCHasCSSClasses aPreClasses,
                          @Nonnull final HCHasCSSStyles aPreStyles)
  {
    aPreClasses.addClass (m_bLineNumbers ? CSS_CLASS_LINE_NUMBERS : CSS_CLASS_NO_LINE_NUMBERS);
    if (m_aStart != null)
      aPreElement.setAttribute ("data-start", m_aStart.intValue ());
    if (m_bSoftWrap)
      aPreStyles.addStyle (CCSSProperties.WHITE_SPACE.newValue (CCSSValue.PRE_WRAP));
  }
}
