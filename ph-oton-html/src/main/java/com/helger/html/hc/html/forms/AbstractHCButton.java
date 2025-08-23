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
package com.helger.html.hc.html.forms;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.HCHTMLHelper;
import com.helger.html.hc.html.HC_Action;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;button&gt; element with type "button"
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCButton <IMPLTYPE extends AbstractHCButton <IMPLTYPE>> extends
                                       AbstractHCElementWithChildren <IMPLTYPE> implements
                                       IHCButton <IMPLTYPE>
{
  public static final boolean DEFAULT_DISABLED = false;

  /** Default value */
  public static final boolean DEFAULT_FORMNOVALIDATE = false;

  private boolean m_bDisabled = DEFAULT_DISABLED;
  private String m_sForm;
  private final HC_Action m_aFormAction = new HC_Action ();
  private IMimeType m_aFormEncType;
  private EHCFormMethod m_eFormMethod;
  private boolean m_bFormNoValidate = DEFAULT_FORMNOVALIDATE;
  private HC_Target m_aFormTarget;
  // TODO menu
  private String m_sName;
  private EHCButtonType m_eType = EHCButtonType.BUTTON;
  private String m_sValue;

  public AbstractHCButton ()
  {
    super (EHTMLElement.BUTTON);
  }

  public AbstractHCButton (@Nullable final String sLabel)
  {
    this ();
    addChild (sLabel);
  }

  public AbstractHCButton (@Nullable final String sLabel, @Nullable final IHasJSCode aOnClick)
  {
    this (sLabel);
    setOnClick (aOnClick);
  }

  public final boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public final IMPLTYPE setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return thisAsT ();
  }

  @Nullable
  public final String getForm ()
  {
    return m_sForm;
  }

  @Nonnull
  public final IMPLTYPE setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getFormActionURL ()
  {
    return m_aFormAction.getActionURL ();
  }

  @Nullable
  public final IHasJSCode getFormActionJS ()
  {
    return m_aFormAction.getActionJS ();
  }

  @Nonnull
  public final IMPLTYPE setFormAction (@Nullable final ISimpleURL aAction)
  {
    m_aFormAction.setAction (aAction);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setFormAction (@Nullable final IHasJSCodeWithSettings aAction)
  {
    m_aFormAction.setAction (aAction);
    return thisAsT ();
  }

  @Nullable
  public final IMimeType getFormEncType ()
  {
    return m_aFormEncType;
  }

  @Nonnull
  public final IMPLTYPE setFormEncType (@Nullable final IMimeType aFormEncType)
  {
    m_aFormEncType = aFormEncType;
    return thisAsT ();
  }

  @Nullable
  public final EHCFormMethod getFormMethod ()
  {
    return m_eFormMethod;
  }

  @Nonnull
  public final IMPLTYPE setFormMethod (@Nullable final EHCFormMethod eFormMethod)
  {
    m_eFormMethod = eFormMethod;
    return thisAsT ();
  }

  public final boolean isFormNoValidate ()
  {
    return m_bFormNoValidate;
  }

  @Nonnull
  public final IMPLTYPE setFormNoValidate (final boolean bFormNoValidate)
  {
    m_bFormNoValidate = bFormNoValidate;
    return thisAsT ();
  }

  @Nullable
  public final HC_Target getFormTarget ()
  {
    return m_aFormTarget;
  }

  @Nonnull
  public final IMPLTYPE setFormTarget (@Nullable final HC_Target aFormTarget)
  {
    m_aFormTarget = aFormTarget;
    return thisAsT ();
  }

  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public final IMPLTYPE setName (@Nullable final String sName)
  {
    m_sName = sName;
    return thisAsT ();
  }

  @Nonnull
  public final EHCButtonType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nonnull final EHCButtonType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return thisAsT ();
  }

  public final String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public final IMPLTYPE setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return thisAsT ();
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    final IHCElement <?> aChild = HCHTMLHelper.recursiveGetFirstChildWithTagName (this,
                                                                                  EHTMLElement.A,
                                                                                  EHTMLElement.INPUT,
                                                                                  EHTMLElement.SELECT,
                                                                                  EHTMLElement.TEXTAREA,
                                                                                  EHTMLElement.LABEL,
                                                                                  EHTMLElement.BUTTON,
                                                                                  EHTMLElement.FORM,
                                                                                  EHTMLElement.FIELDSET,
                                                                                  EHTMLElement.IFRAME);
    if (aChild != null)
      HCConsistencyChecker.consistencyError ("BUTTON element contains forbidden tag " + aChild.getElement ());
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (StringHelper.isNotEmpty (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    m_aFormAction.applyProperties (CHTMLAttributes.FORMACTION,
                                   aElement,
                                   aConversionSettings.getJSWriterSettings (),
                                   aConversionSettings.getCharset ());
    if (m_aFormEncType != null)
      aElement.setAttribute (CHTMLAttributes.FORMENCTYPE, m_aFormEncType.getAsString ());
    if (m_eFormMethod != null)
      aElement.setAttribute (CHTMLAttributes.FORMMETHOD, m_eFormMethod);
    if (m_bFormNoValidate)
      aElement.setAttribute (CHTMLAttributes.FORMNOVALIDATE, CHTMLAttributeValues.FORMNOVALIDATE);
    if (m_aFormTarget != null)
      aElement.setAttribute (CHTMLAttributes.FORMTARGET, m_aFormTarget);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
    if (StringHelper.isNotEmpty (m_sValue))
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Disabled", m_bDisabled)
                            .appendIfNotNull ("Form", m_sForm)
                            .append ("FormAction", m_aFormAction)
                            .appendIfNotNull ("FormEncType", m_aFormEncType)
                            .appendIfNotNull ("FormMethod", m_eFormMethod)
                            .append ("FormNoValidate", m_bFormNoValidate)
                            .appendIfNotNull ("FormTarget", m_aFormTarget)
                            .appendIfNotNull ("Name", m_sName)
                            .append ("Type", m_eType)
                            .appendIfNotNull ("Value", m_sValue)
                            .getToString ();
  }
}
