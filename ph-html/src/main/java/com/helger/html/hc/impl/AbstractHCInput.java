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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCInput;
import com.helger.html.hc.api.EHCFormMethod;
import com.helger.html.hc.api.EHCInputType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HC_Action;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.builder.IJSStatement;

@NotThreadSafe
public abstract class AbstractHCInput <IMPLTYPE extends AbstractHCInput <IMPLTYPE>> extends AbstractHCControl <IMPLTYPE> implements IHCInput <IMPLTYPE>
{
  /** By default no auto complete setting is active */
  public static final ETriState DEFAULT_AUTO_COMPLETE = ETriState.UNDEFINED;

  /** By default auto focus is disabled */
  public static final boolean DEFAULT_AUTO_FOCUS = false;

  /** Not checked by default */
  public static final boolean DEFAULT_CHECKED = false;

  /** Default value */
  public static final boolean DEFAULT_FORMNOVALIDATE = false;

  /** By default multi select is disabled */
  public static final boolean DEFAULT_MULTIPLE = false;

  private EHCInputType m_eType;
  private String m_sAccept;
  private String m_sAlt;
  private ETriState m_eAutoComplete = DEFAULT_AUTO_COMPLETE;
  private boolean m_bAutoFocus = DEFAULT_AUTO_FOCUS;
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

  public AbstractHCInput (@Nonnull final EHCInputType eType)
  {
    this ();
    setType (eType);
  }

  @Nonnull
  public final EHCInputType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nonnull final EHCInputType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
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

  public final boolean isAutoCompleteOn ()
  {
    return m_eAutoComplete.isTrue ();
  }

  public final boolean isAutoCompleteOff ()
  {
    return m_eAutoComplete.isFalse ();
  }

  public final boolean isAutoCompleteUndefined ()
  {
    return m_eAutoComplete.isUndefined ();
  }

  @Nonnull
  public final IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @Nonnull
  public final IMPLTYPE setAutoComplete (@Nonnull final ETriState eAutoComplete)
  {
    m_eAutoComplete = ValueEnforcer.notNull (eAutoComplete, "AutoComplete");
    return thisAsT ();
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

  /**
   * @return Whether or not the check-box is currently checked
   */
  public final boolean isChecked ()
  {
    return m_bChecked;
  }

  /**
   * Set the checked state according to the passed value
   *
   * @param bChecked
   *        new checked state
   * @return This object for chaining
   */
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

  /**
   * @return The currently set max length or -1.
   */
  public final int getMaxLength ()
  {
    return m_nMaxLength;
  }

  /**
   * Set the maximum number of characters to be entered.
   *
   * @param nMaxLength
   *        The max length. Should be &gt; 0.
   * @return this
   */
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

  /**
   * @return The currently set min length or -1.
   */
  public final int getMinLength ()
  {
    return m_nMinLength;
  }

  /**
   * Set the minimum number of characters to be entered.
   *
   * @param nMinLength
   *        The min length. Should be &gt; 0.
   * @return this
   */
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

  /**
   * @return The currently set max length.
   */
  public final int getSize ()
  {
    return m_nSize;
  }

  /**
   * Sets the visible size of the edit. Should not be necessary in most cases,
   * as styling via CSS is the preferred way. If you want to limit the number of
   * available characters use {@link #setMaxLength(int)} instead.
   *
   * @param nSize
   *        The width of the edit in characters.
   * @return this
   */
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

  @Nullable
  public final String getSrcAsString ()
  {
    return m_aSrc == null ? null : m_aSrc.getAsString ();
  }

  @Nonnull
  public final IMPLTYPE setSrc (@Nullable final String sSrc)
  {
    return setSrc (sSrc == null ? null : new SimpleURL (sSrc));
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

  /**
   * Sets the passed field value
   *
   * @param nValue
   *        Value to use.
   * @return This object for chaining
   */
  @Nonnull
  public final IMPLTYPE setValue (final int nValue)
  {
    return setValue (Integer.toString (nValue));
  }

  /**
   * Sets the passed field value
   *
   * @param nValue
   *        Value to use.
   * @return This object for chaining
   */
  @Nonnull
  public final IMPLTYPE setValue (final long nValue)
  {
    return setValue (Long.toString (nValue));
  }

  /**
   * Sets the passed field value
   *
   * @param sValue
   *        Value to use.
   * @return This object for chaining
   */
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
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
    if (StringHelper.hasText (m_sAccept))
      aElement.setAttribute (CHTMLAttributes.ACCEPT, m_sAccept);
    if (StringHelper.hasText (m_sAlt))
      aElement.setAttribute (CHTMLAttributes.ALT, m_sAlt);
    if (m_eAutoComplete.isDefined ())
      aElement.setAttribute (CHTMLAttributes.AUTOCOMPLETE, m_eAutoComplete.isTrue () ? CHTMLAttributeValues.ON
                                                                                    : CHTMLAttributeValues.OFF);
    if (m_bAutoFocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
    if (m_bChecked)
      aElement.setAttribute (CHTMLAttributes.CHECKED, CHTMLAttributeValues.CHECKED);
    if (StringHelper.hasText (m_sDirName))
      aElement.setAttribute (CHTMLAttributes.DIRNAME, m_sDirName);
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
    if (m_nHeight > 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (StringHelper.hasText (m_sList))
      aElement.setAttribute (CHTMLAttributes.LIST, m_sList);
    if (StringHelper.hasText (m_sMaxValue))
      aElement.setAttribute (CHTMLAttributes.MAX, m_sMaxValue);
    if (m_nMaxLength > 0)
      aElement.setAttribute (CHTMLAttributes.MAXLENGTH, m_nMaxLength);
    if (StringHelper.hasText (m_sMinValue))
      aElement.setAttribute (CHTMLAttributes.MIN, m_sMinValue);
    if (m_nMinLength > 0)
      aElement.setAttribute (CHTMLAttributes.MINLENGTH, m_nMinLength);
    if (m_bMultiple)
      aElement.setAttribute (CHTMLAttributes.MULTIPLE, CHTMLAttributeValues.MULTIPLE);
    if (StringHelper.hasText (m_sPattern))
      aElement.setAttribute (CHTMLAttributes.PATTERN, m_sPattern);
    if (StringHelper.hasText (m_sPlaceholder))
      aElement.setAttribute (CHTMLAttributes.PLACEHOLDER, m_sPlaceholder);
    if (m_nSize > 0)
      aElement.setAttribute (CHTMLAttributes.SIZE, m_nSize);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC, m_aSrc.getAsString ());
    if (StringHelper.hasText (m_sStep))
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
                            .appendIfNotNull ("type", m_eType)
                            .appendIfNotNull ("accept", m_sAccept)
                            .appendIfNotNull ("alt", m_sAlt)
                            .append ("autoComplete", m_eAutoComplete)
                            .append ("autoFocus", m_bAutoFocus)
                            .append ("checked", m_bChecked)
                            .appendIfNotNull ("dirname", m_sDirName)
                            .appendIfNotNull ("form", m_sForm)
                            .append ("formaction", m_aFormAction)
                            .appendIfNotNull ("formenctype", m_aFormEncType)
                            .appendIfNotNull ("formmethod", m_eFormMethod)
                            .append ("formnovalidate", m_bFormNoValidate)
                            .appendIfNotNull ("formtarget", m_aFormTarget)
                            .append ("height", m_nHeight)
                            .appendIfNotNull ("list", m_sList)
                            .appendIfNotNull ("maxValue", m_sMaxValue)
                            .append ("maxLength", m_nMaxLength)
                            .appendIfNotNull ("minValue", m_sMinValue)
                            .append ("minLength", m_nMinLength)
                            .append ("multiple", m_bMultiple)
                            .appendIfNotNull ("pattern", m_sPattern)
                            .appendIfNotNull ("placeholder", m_sPlaceholder)
                            .append ("size", m_nSize)
                            .appendIfNotNull ("src", m_aSrc)
                            .appendIfNotNull ("step", m_sStep)
                            .appendIfNotNull ("value", m_sValue)
                            .append ("width", m_nWidth)
                            .toString ();
  }
}
