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
package com.helger.photon.uicore.html.formlabel;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
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

  public static void assignFormLabelClasses (@NonNull final IHCElement <?> aElement, @NonNull final ELabelType eType)
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

  public HCFormLabel (@NonNull final String sText, @NonNull final ELabelType eType)
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

  public HCFormLabel (@NonNull final IHCNodeWithChildren <?> aNode, @NonNull final ELabelType eType)
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
  @NonNull
  public String getPlainText ()
  {
    return m_sPlainText;
  }

  public boolean isTextLabel ()
  {
    return m_bTextLabel;
  }

  @NonNull
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

  @NonNull
  public static HCFormLabel createOptional (@NonNull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.OPTIONAL);
  }

  @NonNull
  public static HCFormLabel createOptional (@NonNull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.OPTIONAL);
  }

  @NonNull
  public static HCFormLabel createMandatory (@NonNull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.MANDATORY);
  }

  @NonNull
  public static HCFormLabel createMandatory (@NonNull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.MANDATORY);
  }

  @NonNull
  public static HCFormLabel createAlternative (@NonNull final String sText)
  {
    return new HCFormLabel (sText, ELabelType.ALTERNATIVE);
  }

  @NonNull
  public static HCFormLabel createAlternative (@NonNull final IHCElementWithChildren <?> aNode)
  {
    return new HCFormLabel (aNode, ELabelType.ALTERNATIVE);
  }

  @NonNull
  public static HCFormLabel createPlain (@NonNull final String sText)
  {
    // Avoid adding ":" or "?" at the end
    return new HCFormLabel (sText, ELabelType.NONE);
  }

  @NonNull
  public static HCFormLabel createPlain (@NonNull final IHCElementWithChildren <?> aNode)
  {
    // Avoid adding ":" or "?" at the end
    return new HCFormLabel (aNode, ELabelType.NONE);
  }

  @NonNull
  public static HCFormLabel createForCheckBox (@NonNull final String sText)
  {
    // Avoid adding ":" or "?" at the end
    if (false)
      return createPlain (sText);

    return new HCFormLabel (new HCSpan ().addChild (sText));
  }
}
