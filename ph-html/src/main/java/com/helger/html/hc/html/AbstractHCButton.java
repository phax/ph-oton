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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCButton;
import com.helger.html.hc.api.EHCButtonType;
import com.helger.html.hc.api.EHCFormMethod;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.builder.IJSStatement;
import com.helger.html.js.builder.html.JSHtml;

/**
 * Represents an HTML &lt;button&gt; element with type "button"
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCButton <IMPLTYPE extends AbstractHCButton <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE> implements IHCButton <IMPLTYPE>
{
  /** By default auto focus is disabled */
  public static final boolean DEFAULT_AUTO_FOCUS = false;

  public static final boolean DEFAULT_DISABLED = false;

  /** Default value */
  public static final boolean DEFAULT_FORMNOVALIDATE = false;

  private boolean m_bAutoFocus = DEFAULT_AUTO_FOCUS;
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

  public AbstractHCButton (@Nullable final String sLabel, @Nullable final IJSCodeProvider aOnClick)
  {
    this (sLabel);
    setOnClick (aOnClick);
  }

  public final boolean isAutoFocus ()
  {
    return m_bAutoFocus;
  }

  @Nonnull
  public final IMPLTYPE setAutoFocus (final boolean bAutoFocus)
  {
    m_bAutoFocus = bAutoFocus;
    return thisAsT ();
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
  public final String getFormActionURL ()
  {
    return m_aFormAction.getActionURL ();
  }

  @Nullable
  public final IJSCodeProvider getFormActionJS ()
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
  public final IMPLTYPE setFormAction (@Nullable final String sAction)
  {
    m_aFormAction.setAction (sAction);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setFormAction (@Nullable final IJSStatement aAction)
  {
    m_aFormAction.setAction (aAction);
    return thisAsT ();
  }

  @Nullable
  public IMimeType getFormEncType ()
  {
    return m_aFormEncType;
  }

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE setFormEncTypeFileUpload ()
  {
    return setFormEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE setFormEncTypeTextPlain ()
  {
    return setFormEncType (CMimeType.TEXT_PLAIN);
  }

  @Nonnull
  public IMPLTYPE setFormEncType (@Nullable final IMimeType aFormEncType)
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
  public final IMPLTYPE setFormTargetBlank ()
  {
    return setFormTarget (HC_Target.BLANK);
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
  public IMPLTYPE setType (@Nonnull final EHCButtonType eType)
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

  @Nonnull
  public IMPLTYPE setOnClick (@Nullable final IJSCodeProvider aOnClick)
  {
    return setEventHandler (EJSEvent.ONCLICK, aOnClick);
  }

  @Nonnull
  public IMPLTYPE setOnClick (@Nonnull final ISimpleURL aURL)
  {
    return setOnClick (JSHtml.windowLocationHref (aURL));
  }

  @Nonnull
  public IMPLTYPE addOnClick (@Nullable final IJSCodeProvider aOnClick)
  {
    return addEventHandler (EJSEvent.ONCLICK, aOnClick);
  }

  @Nonnull
  public IMPLTYPE addOnClick (@Nonnull final ISimpleURL aURL)
  {
    return addOnClick (JSHtml.windowLocationHref (aURL));
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_bAutoFocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
    if (m_bDisabled)
      aElement.setAttribute (CHTMLAttributes.DISABLED, CHTMLAttributeValues.DISABLED);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    m_aFormAction.applyProperties (CHTMLAttributes.FORMACTION, aElement, aConversionSettings.getJSWriterSettings ());
    if (m_aFormEncType != null)
      aElement.setAttribute (CHTMLAttributes.FORMENCTYPE, m_aFormEncType.getAsString ());
    if (m_eFormMethod != null)
      aElement.setAttribute (CHTMLAttributes.FORMMETHOD, m_eFormMethod);
    if (m_bFormNoValidate)
      aElement.setAttribute (CHTMLAttributes.FORMNOVALIDATE, CHTMLAttributeValues.FORMNOVALIDATE);
    if (m_aFormTarget != null)
      aElement.setAttribute (CHTMLAttributes.FORMTARGET, m_aFormTarget);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
    if (StringHelper.hasText (m_sValue))
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("autoFocus", m_bAutoFocus)
                            .append ("disabled", m_bDisabled)
                            .appendIfNotNull ("form", m_sForm)
                            .append ("formaction", m_aFormAction)
                            .appendIfNotNull ("formenctype", m_aFormEncType)
                            .appendIfNotNull ("formmethod", m_eFormMethod)
                            .append ("formnovalidate", m_bFormNoValidate)
                            .appendIfNotNull ("formtarget", m_aFormTarget)
                            .appendIfNotNull ("name", m_sName)
                            .append ("type", m_eType)
                            .appendIfNotNull ("value", m_sValue)
                            .toString ();
  }
}
