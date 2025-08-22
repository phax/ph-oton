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

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.CGlobal;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HC_Action;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.http.url.ISimpleURL;
import com.helger.mime.IMimeType;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@NotThreadSafe
public abstract class AbstractHCInput <IMPLTYPE extends AbstractHCInput <IMPLTYPE>> extends AbstractHCControl <IMPLTYPE>
                                      implements
                                      IHCInput <IMPLTYPE>
{
  /** Not checked by default */
  public static final boolean DEFAULT_CHECKED = false;

  /** Default value */
  public static final boolean DEFAULT_FORMNOVALIDATE = false;

  /** By default multi select is disabled */
  public static final boolean DEFAULT_MULTIPLE = false;

  private EHCInputType m_eType;
  private String m_sAccept;
  private String m_sAlt;
  private String m_sAutoComplete;
  private boolean m_bChecked = DEFAULT_CHECKED;
  private String m_sDirName;
  // disabled is inherited
  private String m_sForm;
  private final HC_Action m_aFormAction = new HC_Action ();
  private IMimeType m_aFormEncType;
  private EHCFormMethod m_eFormMethod;
  private boolean m_bFormNoValidate = DEFAULT_FORMNOVALIDATE;
  private HC_Target m_aFormTarget;
  private int m_nHeight = CGlobal.ILLEGAL_UINT;
  // TODO inputmode
  private String m_sList;
  private String m_sMaxValue;
  private int m_nMaxLength = CGlobal.ILLEGAL_UINT;
  private String m_sMinValue;
  private int m_nMinLength = CGlobal.ILLEGAL_UINT;
  private boolean m_bMultiple = DEFAULT_MULTIPLE;
  // name is inherited
  private String m_sPattern;
  private String m_sPlaceholder;
  // readonly is inherited
  // required is inherited
  private int m_nSize = CGlobal.ILLEGAL_UINT;
  private ISimpleURL m_aSrc;
  private String m_sStep;
  private String m_sValue;
  private int m_nWidth = CGlobal.ILLEGAL_UINT;

  /**
   * Default ctor
   */
  public AbstractHCInput ()
  {
    super (EHTMLElement.INPUT);
  }

  /**
   * Default ctor
   *
   * @param eType
   *        Type of input. May not be <code>null</code>.
   */
  public AbstractHCInput (@Nonnull final EHCInputType eType)
  {
    this ();
    setType (eType);
  }

  @Nullable
  public final EHCInputType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nullable final EHCInputType eType)
  {
    m_eType = eType;
    return thisAsT ();
  }

  @Nullable
  public final String getAccept ()
  {
    return m_sAccept;
  }

  @Nonnull
  public final IMPLTYPE setAccept (@Nullable final String sAccept)
  {
    m_sAccept = sAccept;
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setAccept (@Nullable final IMimeType aAccept)
  {
    return setAccept (aAccept == null ? null : aAccept.getAsString ());
  }

  @Nullable
  public final String getAlt ()
  {
    return m_sAlt;
  }

  @Nonnull
  public final IMPLTYPE setAlt (@Nullable final String sAlt)
  {
    m_sAlt = sAlt;
    return thisAsT ();
  }

  @Nullable
  public final String getAutoComplete ()
  {
    return m_sAutoComplete;
  }

  @Nonnull
  public final IMPLTYPE setAutoComplete (@Nullable final String sAutoComplete)
  {
    m_sAutoComplete = sAutoComplete;
    return thisAsT ();
  }

  public final boolean isChecked ()
  {
    return m_bChecked;
  }

  @Nonnull
  public final IMPLTYPE setChecked (final boolean bChecked)
  {
    m_bChecked = bChecked;
    return thisAsT ();
  }

  @Nullable
  public final String getDirName ()
  {
    return m_sDirName;
  }

  @Nonnull
  public final IMPLTYPE setDirName (@Nullable final String sDirName)
  {
    m_sDirName = sDirName;
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

  public final int getHeight ()
  {
    return m_nHeight;
  }

  @Nonnull
  public final IMPLTYPE setHeight (final int nHeight)
  {
    m_nHeight = nHeight;
    return thisAsT ();
  }

  @Nullable
  public final String getList ()
  {
    return m_sList;
  }

  @Nonnull
  public final IMPLTYPE setList (@Nullable final String sList)
  {
    m_sList = sList;
    return thisAsT ();
  }

  @Nullable
  public final String getMaxValue ()
  {
    return m_sMaxValue;
  }

  @Nonnull
  public final IMPLTYPE setMaxValue (@Nullable final String sMaxValue)
  {
    m_sMaxValue = sMaxValue;
    return thisAsT ();
  }

  public final int getMaxLength ()
  {
    return m_nMaxLength;
  }

  @Nonnull
  public final IMPLTYPE setMaxLength (final int nMaxLength)
  {
    m_nMaxLength = nMaxLength;
    return thisAsT ();
  }

  @Nullable
  public final String getMinValue ()
  {
    return m_sMinValue;
  }

  @Nonnull
  public final IMPLTYPE setMinValue (@Nullable final String sMinValue)
  {
    m_sMinValue = sMinValue;
    return thisAsT ();
  }

  public final int getMinLength ()
  {
    return m_nMinLength;
  }

  @Nonnull
  public final IMPLTYPE setMinLength (final int nMinLength)
  {
    m_nMinLength = nMinLength;
    return thisAsT ();
  }

  public final boolean isMultiple ()
  {
    return m_bMultiple;
  }

  @Nonnull
  public final IMPLTYPE setMultiple (final boolean bMultiple)
  {
    m_bMultiple = bMultiple;
    return thisAsT ();
  }

  @Nullable
  public final String getPattern ()
  {
    return m_sPattern;
  }

  @Nonnull
  public final IMPLTYPE setPattern (@Nullable final String sPattern)
  {
    m_sPattern = sPattern;
    return thisAsT ();
  }

  @Nullable
  public final String getPlaceholder ()
  {
    return m_sPlaceholder;
  }

  @Nonnull
  public final IMPLTYPE setPlaceholder (@Nullable final String sPlaceholder)
  {
    m_sPlaceholder = sPlaceholder;
    return thisAsT ();
  }

  public final int getSize ()
  {
    return m_nSize;
  }

  @Nonnull
  public final IMPLTYPE setSize (final int nSize)
  {
    m_nSize = nSize;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final IMPLTYPE setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return thisAsT ();
  }

  @Nullable
  public final String getStep ()
  {
    return m_sStep;
  }

  @Nonnull
  public final IMPLTYPE setStep (@Nullable final String sStep)
  {
    m_sStep = sStep;
    return thisAsT ();
  }

  /**
   * @return The field value, maybe <code>null</code>
   */
  @Nullable
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

  public final int getWidth ()
  {
    return m_nWidth;
  }

  @Nonnull
  public final IMPLTYPE setWidth (final int nWidth)
  {
    m_nWidth = nWidth;
    return thisAsT ();
  }

  @Override
  public String getPlainText ()
  {
    return StringHelper.getNotNull (getValue ());
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
    if (StringHelper.isNotEmpty (m_sAccept))
      aElement.setAttribute (CHTMLAttributes.ACCEPT, m_sAccept);
    if (StringHelper.isNotEmpty (m_sAlt))
      aElement.setAttribute (CHTMLAttributes.ALT, m_sAlt);
    if (StringHelper.isNotEmpty (m_sAutoComplete))
      aElement.setAttribute (CHTMLAttributes.AUTOCOMPLETE, m_sAutoComplete);
    if (m_bChecked)
      aElement.setAttribute (CHTMLAttributes.CHECKED, CHTMLAttributeValues.CHECKED);
    if (StringHelper.isNotEmpty (m_sDirName))
      aElement.setAttribute (CHTMLAttributes.DIRNAME, m_sDirName);
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
    if (m_nHeight > 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (StringHelper.isNotEmpty (m_sList))
      aElement.setAttribute (CHTMLAttributes.LIST, m_sList);
    if (StringHelper.isNotEmpty (m_sMaxValue))
      aElement.setAttribute (CHTMLAttributes.MAX, m_sMaxValue);
    if (m_nMaxLength > 0)
      aElement.setAttribute (CHTMLAttributes.MAXLENGTH, m_nMaxLength);
    if (StringHelper.isNotEmpty (m_sMinValue))
      aElement.setAttribute (CHTMLAttributes.MIN, m_sMinValue);
    if (m_nMinLength > 0)
      aElement.setAttribute (CHTMLAttributes.MINLENGTH, m_nMinLength);
    if (m_bMultiple)
      aElement.setAttribute (CHTMLAttributes.MULTIPLE, CHTMLAttributeValues.MULTIPLE);
    if (StringHelper.isNotEmpty (m_sPattern))
      aElement.setAttribute (CHTMLAttributes.PATTERN, m_sPattern);
    if (StringHelper.isNotEmpty (m_sPlaceholder))
      aElement.setAttribute (CHTMLAttributes.PLACEHOLDER, m_sPlaceholder);
    if (m_nSize > 0)
      aElement.setAttribute (CHTMLAttributes.SIZE, m_nSize);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC,
                             m_aSrc.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (StringHelper.isNotEmpty (m_sStep))
      aElement.setAttribute (CHTMLAttributes.STEP, m_sStep);
    if (m_sValue != null)
      aElement.setAttribute (CHTMLAttributes.VALUE, m_sValue);
    if (m_nWidth > 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Type", m_eType)
                            .appendIfNotNull ("Accept", m_sAccept)
                            .appendIfNotNull ("Alt", m_sAlt)
                            .append ("AutoComplete", m_sAutoComplete)
                            .append ("Checked", m_bChecked)
                            .appendIfNotNull ("DirName", m_sDirName)
                            .appendIfNotNull ("Form", m_sForm)
                            .append ("FormAction", m_aFormAction)
                            .appendIfNotNull ("FormEncType", m_aFormEncType)
                            .appendIfNotNull ("FormMethod", m_eFormMethod)
                            .append ("FormNoValidate", m_bFormNoValidate)
                            .appendIfNotNull ("FormTarget", m_aFormTarget)
                            .append ("Height", m_nHeight)
                            .appendIfNotNull ("List", m_sList)
                            .appendIfNotNull ("MaxValue", m_sMaxValue)
                            .append ("MaxLength", m_nMaxLength)
                            .appendIfNotNull ("MinValue", m_sMinValue)
                            .append ("MinLength", m_nMinLength)
                            .append ("Multiple", m_bMultiple)
                            .appendIfNotNull ("Pattern", m_sPattern)
                            .appendIfNotNull ("Placeholder", m_sPlaceholder)
                            .append ("Size", m_nSize)
                            .appendIfNotNull ("Src", m_aSrc)
                            .appendIfNotNull ("Step", m_sStep)
                            .appendIfNotNull ("Value", m_sValue)
                            .append ("Width", m_nWidth)
                            .getToString ();
  }
}
