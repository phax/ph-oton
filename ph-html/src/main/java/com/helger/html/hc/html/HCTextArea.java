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
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.api.EHCTextAreaWrap;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCControl;
import com.helger.html.request.IHCRequestField;

/**
 * Represents an HTML &lt;textarea&gt; element
 *
 * @author Philip Helger
 */
public class HCTextArea extends AbstractHCControl <HCTextArea>
{
  /** By default no auto complete setting is active */
  public static final ETriState DEFAULT_AUTO_COMPLETE = ETriState.UNDEFINED;

  /** By default auto focus is disabled */
  public static final boolean DEFAULT_AUTO_FOCUS = false;

  private ETriState m_eAutoComplete = DEFAULT_AUTO_COMPLETE;
  private boolean m_bAutoFocus = DEFAULT_AUTO_FOCUS;
  private int m_nCols = CGlobal.ILLEGAL_UINT;
  private String m_sDirName;
  // disabled is inherited
  private String m_sForm;
  private int m_nMaxLength = CGlobal.ILLEGAL_UINT;
  private int m_nMinLength = CGlobal.ILLEGAL_UINT;
  // name is inherited
  private String m_sPlaceholder;
  // readonly is inherited
  // required is inherited
  private int m_nRows = CGlobal.ILLEGAL_UINT;
  private String m_sValue;
  private EHCTextAreaWrap m_eWrap;

  public HCTextArea ()
  {
    super (EHTMLElement.TEXTAREA);
  }

  public HCTextArea (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }

  public HCTextArea (@Nullable final String sName, @Nullable final String sValue)
  {
    this (sName);
    setValue (sValue);
  }

  public HCTextArea (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getFieldName (), aRF.getRequestValue ());
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
  public final HCTextArea setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @Nonnull
  public final HCTextArea setAutoComplete (@Nonnull final ETriState eAutoComplete)
  {
    m_eAutoComplete = ValueEnforcer.notNull (eAutoComplete, "AutoComplete");
    return thisAsT ();
  }

  public final boolean isAutoFocus ()
  {
    return m_bAutoFocus;
  }

  @Nonnull
  public final HCTextArea setAutoFocus (final boolean bAutoFocus)
  {
    m_bAutoFocus = bAutoFocus;
    return thisAsT ();
  }

  public final int getCols ()
  {
    return m_nCols;
  }

  @Nonnull
  public final HCTextArea setCols (final int nCols)
  {
    m_nCols = nCols;
    return this;
  }

  @Nullable
  public final String getDirName ()
  {
    return m_sDirName;
  }

  @Nonnull
  public final HCTextArea setDirName (@Nullable final String sDirName)
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
  public final HCTextArea setForm (@Nullable final String sForm)
  {
    m_sForm = sForm;
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
  public final HCTextArea setMaxLength (final int nMaxLength)
  {
    m_nMaxLength = nMaxLength;
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
  public final HCTextArea setMinLength (final int nMinLength)
  {
    m_nMinLength = nMinLength;
    return thisAsT ();
  }

  @Nullable
  public final String getPlaceholder ()
  {
    return m_sPlaceholder;
  }

  @Nonnull
  public final HCTextArea setPlaceholder (@Nullable final String sPlaceholder)
  {
    m_sPlaceholder = sPlaceholder;
    return thisAsT ();
  }

  public final int getRows ()
  {
    return m_nRows;
  }

  @Nonnull
  public final HCTextArea setRows (final int nRows)
  {
    m_nRows = nRows;
    return this;
  }

  @Nullable
  public final String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public final HCTextArea setValue (@Nullable final String sValue)
  {
    m_sValue = sValue;
    return this;
  }

  @Nullable
  public final EHCTextAreaWrap getWrap ()
  {
    return m_eWrap;
  }

  @Nonnull
  public final HCTextArea setValue (@Nullable final EHCTextAreaWrap eWrap)
  {
    m_eWrap = eWrap;
    return this;
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return StringHelper.getNotNull (m_sValue);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_eAutoComplete.isDefined ())
      aElement.setAttribute (CHTMLAttributes.AUTOCOMPLETE, m_eAutoComplete.isTrue () ? CHTMLAttributeValues.ON
                                                                                    : CHTMLAttributeValues.OFF);
    if (m_bAutoFocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
    if (m_nCols > 0)
      aElement.setAttribute (CHTMLAttributes.COLS, m_nCols);
    if (StringHelper.hasText (m_sDirName))
      aElement.setAttribute (CHTMLAttributes.DIRNAME, m_sDirName);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    if (m_nMaxLength > 0)
      aElement.setAttribute (CHTMLAttributes.MAXLENGTH, m_nMaxLength);
    if (m_nMinLength > 0)
      aElement.setAttribute (CHTMLAttributes.MINLENGTH, m_nMinLength);
    if (StringHelper.hasText (m_sPlaceholder))
      aElement.setAttribute (CHTMLAttributes.PLACEHOLDER, m_sPlaceholder);
    if (m_nRows > 0)
      aElement.setAttribute (CHTMLAttributes.ROWS, m_nRows);
    if (m_eWrap != null)
      aElement.setAttribute (CHTMLAttributes.WRAP, m_eWrap);

    // If no children are present, add an empty text node so that an opening
    // and a closing tag are written separately
    aElement.appendText (getPlainText ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("autoComplete", m_eAutoComplete)
                            .append ("autoFocus", m_bAutoFocus)
                            .append ("cols", m_nCols)
                            .appendIfNotNull ("dirname", m_sDirName)
                            .appendIfNotNull ("form", m_sForm)
                            .append ("maxLength", m_nMaxLength)
                            .append ("minLength", m_nMinLength)
                            .appendIfNotNull ("placeholder", m_sPlaceholder)
                            .append ("rows", m_nRows)
                            .appendIfNotNull ("value", m_sValue)
                            .appendIfNotNull ("wrap", m_eWrap)
                            .toString ();
  }
}
