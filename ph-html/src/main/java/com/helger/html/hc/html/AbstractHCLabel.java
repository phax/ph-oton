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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;label&gt; element
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public abstract class AbstractHCLabel <THISTYPE extends AbstractHCLabel <THISTYPE>> extends AbstractHCElementWithChildren <THISTYPE>
{
  private String m_sFor;
  private String m_sForm;

  public AbstractHCLabel ()
  {
    super (EHTMLElement.LABEL);
  }

  @Nullable
  public String getFor ()
  {
    return m_sFor;
  }

  /**
   * Indicates that this label is used as the description for another object.
   *
   * @param sFor
   *        The HTML ID of the other object.
   * @return this
   */
  @Nonnull
  public THISTYPE setFor (@Nullable final String sFor)
  {
    m_sFor = sFor;
    return thisAsT ();
  }

  /**
   * Indicates that this label is used as the description for another object.
   *
   * @param aFor
   *        The HTML of the other object.
   * @return this
   */
  @Nonnull
  public THISTYPE setFor (@Nullable final IHCElement <?> aFor)
  {
    if (aFor == null)
      m_sFor = null;
    else
      m_sFor = aFor.ensureID ().getID ();
    return thisAsT ();
  }

  @Nullable
  public String getForm ()
  {
    return m_sForm;
  }

  /**
   * The value of the id attribute on the form with which to associate the
   * element.
   *
   * @param sForm
   *        The HTML ID of the form.
   * @return this
   */
  @Nonnull
  public THISTYPE setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
    return thisAsT ();
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sFor))
      aElement.setAttribute (CHTMLAttributes.FOR, m_sFor);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("for", m_sFor)
                            .appendIfNotNull ("form", m_sForm)
                            .toString ();
  }
}
