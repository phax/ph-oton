/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.AbstractHCLabel;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCTextNode;

public class HCFormLabel extends AbstractHCLabel <HCFormLabel> implements IFormLabel
{
  public static final ICSSClassProvider CSS_CLASS_FORM_LABEL = DefaultCSSClassProvider.create ("form-label");

  private final ELabelType m_eType;
  private final boolean m_bTextLabel;
  private final String m_sPlainText;

  public static void assignFormLabelClasses (@Nonnull final IHCElement <?> aElement, @Nonnull final ELabelType eType)
  {
    aElement.addClass (CSS_CLASS_FORM_LABEL).addClass (eType.getCSSClass ());
  }

  /**
   * This constructor is only meant for internal use because it does not apply
   * form styles.
   *
   * @param aNode
   *        The node to add. May be <code>null</code>.
   */
  public HCFormLabel (@Nullable final IHCNode aNode)
  {
    m_eType = ELabelType.NONE;
    m_bTextLabel = false;
    m_sPlainText = aNode != null ? aNode.getPlainText () : "";
    addChild (aNode);
  }

  public HCFormLabel (@Nonnull final String sText, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (sText, "Text");
    ValueEnforcer.notNull (eType, "Type");
    assignFormLabelClasses (this, eType);

    // Trim optional trailing label end
    final String sPlainText = StringHelper.trimEnd (sText.trim (), HCFormLabelHelper.getDefaultLabelEnd ());
    final String sUIText = HCFormLabelHelper.getTextWithState (sPlainText, eType);
    addChild (new HCTextNode (sUIText));
    m_eType = eType;
    m_bTextLabel = true;
    m_sPlainText = sPlainText;
  }

  public HCFormLabel (@Nonnull final IHCNodeWithChildren <?> aNode, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (eType, "Type");
    assignFormLabelClasses (this, eType);

    m_eType = eType;
    m_bTextLabel = false;
    // Set the label text, before the suffixes are appended!
    m_sPlainText = StringHelper.trimEnd (aNode.getPlainText ().trim (), HCFormLabelHelper.getDefaultLabelEnd ());
    // Append if necessary
    addChild (HCFormLabelHelper.getNodeWithState (aNode, eType));
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
                            .append ("PlainText", m_sPlainText)
                            .append ("TextLabel", m_bTextLabel)
                            .append ("Type", m_eType)
                            .getToString ();
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

  @Nonnull
  public static HCFormLabel createPlain (@Nonnull final String sText)
  {
    // Avoid adding ":" or "?" at the end
    return new HCFormLabel (sText, ELabelType.NONE);
  }

  @Nonnull
  public static HCFormLabel createPlain (@Nonnull final IHCElementWithChildren <?> aNode)
  {
    // Avoid adding ":" or "?" at the end
    return new HCFormLabel (aNode, ELabelType.NONE);
  }

  @Nonnull
  public static HCFormLabel createForCheckBox (@Nonnull final String sText)
  {
    // Avoid adding ":" or "?" at the end
    if (false)
      return createPlain (sText);

    return new HCFormLabel (new HCSpan ().addChild (sText));
  }
}
