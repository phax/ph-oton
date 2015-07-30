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
package com.helger.photon.uicore.html.formlabel;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hcapi.IHCNodeWithChildren;
import com.helger.html.hcapi.impl.HCTextNode;
import com.helger.html.hchtml.IHCElement;
import com.helger.html.hchtml.IHCElementWithChildren;
import com.helger.html.hchtml.base.AbstractHCLabel;

public class HCFormLabel extends AbstractHCLabel <HCFormLabel> implements IFormLabel
{
  public static final ICSSClassProvider CSS_CLASS_FORM_LABEL = DefaultCSSClassProvider.create ("form-label");
  public static final ICSSClassProvider CSS_CLASS_FORM_LABEL_OPTIONAL = DefaultCSSClassProvider.create ("form-label-optional");
  public static final ICSSClassProvider CSS_CLASS_FORM_LABEL_MANDATORY = DefaultCSSClassProvider.create ("form-label-mandatory");
  public static final ICSSClassProvider CSS_CLASS_FORM_LABEL_ALTERNATIVE = DefaultCSSClassProvider.create ("form-label-alternative");

  private final ELabelType m_eType;
  private final boolean m_bTextLabel;
  private final String m_sPlainText;

  public static void assignFormLabelClasses (@Nonnull final IHCElement <?> aElement, @Nonnull final ELabelType eType)
  {
    aElement.addClass (CSS_CLASS_FORM_LABEL);
    switch (eType)
    {
      case OPTIONAL:
        aElement.addClass (CSS_CLASS_FORM_LABEL_OPTIONAL);
        break;
      case MANDATORY:
        aElement.addClass (CSS_CLASS_FORM_LABEL_MANDATORY);
        break;
      case ALTERNATIVE:
        aElement.addClass (CSS_CLASS_FORM_LABEL_ALTERNATIVE);
        break;
      default:
        break;
    }
  }

  public HCFormLabel (@Nonnull final String sText, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (sText, "Text");
    ValueEnforcer.notNull (eType, "Type");
    assignFormLabelClasses (this, eType);
    addChild (new HCTextNode (HCFormLabelHelper.getTextWithState (sText, eType)));
    m_eType = eType;
    m_bTextLabel = true;
    m_sPlainText = sText;
  }

  public HCFormLabel (@Nonnull final IHCNodeWithChildren <?> aNode, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (eType, "Type");
    assignFormLabelClasses (this, eType);
    // Set the label text, before the signs are appended!
    m_sPlainText = aNode.getPlainText ();
    addChild (HCFormLabelHelper.getNodeWithState (aNode, eType));
    m_eType = eType;
    m_bTextLabel = false;
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return m_sPlainText;
  }

  public boolean isTextLabel ()
  {
    return m_bTextLabel;
  }

  @Nonnull
  public ELabelType getType ()
  {
    return m_eType;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("labelText", m_sPlainText)
                            .append ("isTextLabel", m_bTextLabel)
                            .append ("type", m_eType)
                            .toString ();
  }

  @Nonnull
  public static HCFormLabel createOptional (@Nonnull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.OPTIONAL);
  }

  @Nonnull
  public static HCFormLabel createOptional (@Nonnull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.OPTIONAL);
  }

  @Nonnull
  public static HCFormLabel createMandatory (@Nonnull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.MANDATORY);
  }

  @Nonnull
  public static HCFormLabel createMandatory (@Nonnull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.MANDATORY);
  }

  @Nonnull
  public static HCFormLabel createAlternative (@Nonnull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.ALTERNATIVE);
  }

  @Nonnull
  public static HCFormLabel createAlternative (@Nonnull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.ALTERNATIVE);
  }
}
