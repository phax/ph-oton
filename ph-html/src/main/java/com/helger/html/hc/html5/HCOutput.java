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
package com.helger.html.hc.html5;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML5 &lt;output&gt; element
 * 
 * @author Philip Helger
 */
@SinceHTML5
public class HCOutput extends AbstractHCElementWithChildren <HCOutput>
{
  private String m_sFor;
  private String m_sForm;
  private String m_sName;

  public HCOutput ()
  {
    super (EHTMLElement.OUTPUT);
  }

  @Nullable
  public String getFor ()
  {
    return m_sFor;
  }

  /**
   * Specifies the relationship between the result of the calculation, and the
   * elements used in the calculation
   * 
   * @param sFor
   *        The HTML ID of the other object.
   * @return this
   */
  @Nonnull
  public HCOutput setFor (@Nullable final String sFor)
  {
    m_sFor = sFor;
    return this;
  }

  /**
   * Specifies the relationship between the result of the calculation, and the
   * elements used in the calculation
   * 
   * @param aFor
   *        The HTML of the other object.
   * @return this
   */
  @Nonnull
  public HCOutput setFor (@Nullable final IHCElement <?> aFor)
  {
    if (aFor == null)
      m_sFor = null;
    else
      m_sFor = aFor.ensureID ().getID ();
    return this;
  }

  @Nullable
  public String getForm ()
  {
    return m_sForm;
  }

  /**
   * Specifies one or more forms the output element belongs to
   * 
   * @param sForm
   *        The HTML ID of the form.
   * @return this
   */
  @Nonnull
  public HCOutput setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
    return this;
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public final HCOutput setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sFor))
      aElement.setAttribute (CHTMLAttributes.FOR, m_sFor);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("for", m_sFor)
                            .appendIfNotNull ("form", m_sForm)
                            .appendIfNotNull ("name", m_sName)
                            .toString ();
  }
}
