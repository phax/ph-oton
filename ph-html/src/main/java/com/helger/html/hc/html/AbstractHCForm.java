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

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.api.EHCFormMethod;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.builder.IJSStatement;

/**
 * Represents an HTML &lt;form&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCForm <IMPLTYPE extends AbstractHCForm <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE>
{
  /** By default no auto complete setting is active */
  public static final ETriState DEFAULT_AUTO_COMPLETE = ETriState.UNDEFINED;

  /** Default form submission method is POST */
  public static final EHCFormMethod DEFAULT_METHOD = EHCFormMethod.POST;

  /** Default value */
  public static final boolean DEFAULT_NOVALIDATE = false;

  /** By default form are not submitted by pressing Enter key */
  public static final boolean DEFAULT_SUBMIT_PRESSING_ENTER = false;

  private String m_sAcceptCharset;
  private final HC_Action m_aAction = new HC_Action ();
  private ETriState m_eAutoComplete = DEFAULT_AUTO_COMPLETE;
  private IMimeType m_aEncType;
  private EHCFormMethod m_eMethod = DEFAULT_METHOD;
  private String m_sName;
  private boolean m_bNoValidate = DEFAULT_NOVALIDATE;
  private HC_Target m_aTarget;

  // Must be handled externally!
  private boolean m_bSubmitPressingEnter = DEFAULT_SUBMIT_PRESSING_ENTER;
  private int m_nSubmitButtonTabIndex = CGlobal.ILLEGAL_UINT;

  public AbstractHCForm ()
  {
    super (EHTMLElement.FORM);
  }

  public AbstractHCForm (@Nullable final String sAction)
  {
    this ();
    setAction (sAction);
  }

  public AbstractHCForm (@Nullable final ISimpleURL aAction)
  {
    this ();
    setAction (aAction);
  }

  public AbstractHCForm (@Nullable final IJSStatement aAction)
  {
    this ();
    setAction (aAction);
  }

  @Nullable
  public final String getAcceptCharset ()
  {
    return m_sAcceptCharset;
  }

  @Nonnull
  public final IMPLTYPE setAcceptCharset (@Nullable final String sAcceptCharset)
  {
    m_sAcceptCharset = sAcceptCharset;
    return thisAsT ();
  }

  @Nullable
  public final String getActionURL ()
  {
    return m_aAction.getActionURL ();
  }

  @Nullable
  public final IJSCodeProvider getActionJS ()
  {
    return m_aAction.getActionJS ();
  }

  @Nonnull
  public final IMPLTYPE setAction (@Nullable final ISimpleURL aAction)
  {
    m_aAction.setAction (aAction);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setAction (@Nullable final String sAction)
  {
    m_aAction.setAction (sAction);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setAction (@Nullable final IJSStatement aAction)
  {
    m_aAction.setAction (aAction);
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

  @Nullable
  public IMimeType getEncType ()
  {
    return m_aEncType;
  }

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @Nonnull
  @Deprecated
  public IMPLTYPE setFileUploadEncType ()
  {
    return setEncTypeFileUpload ();
  }

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE setEncTypeFileUpload ()
  {
    return setEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE setEncTypeTextPlain ()
  {
    return setEncType (CMimeType.TEXT_PLAIN);
  }

  @Nonnull
  public IMPLTYPE setEncType (@Nullable final IMimeType aEncType)
  {
    m_aEncType = aEncType;
    return thisAsT ();
  }

  @Nullable
  public final EHCFormMethod getMethod ()
  {
    return m_eMethod;
  }

  @Nonnull
  public final IMPLTYPE setMethod (@Nullable final EHCFormMethod eMethod)
  {
    m_eMethod = eMethod;
    return thisAsT ();
  }

  @Nullable
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

  public final boolean isNoValidate ()
  {
    return m_bNoValidate;
  }

  @Nonnull
  public final IMPLTYPE setNoValidate (final boolean bNoValidate)
  {
    m_bNoValidate = bNoValidate;
    return thisAsT ();
  }

  @Nullable
  public final HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  public final IMPLTYPE setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return thisAsT ();
  }

  public final boolean isSubmitPressingEnter ()
  {
    return m_bSubmitPressingEnter;
  }

  public final int getSubmitButtonTabIndex ()
  {
    return m_nSubmitButtonTabIndex;
  }

  @Nonnull
  public final IMPLTYPE setSubmitPressingEnter (final boolean bSubmitPressingEnter)
  {
    return setSubmitPressingEnter (bSubmitPressingEnter, CGlobal.ILLEGAL_UINT);
  }

  @Nonnull
  public final IMPLTYPE setSubmitPressingEnter (final boolean bSubmitPressingEnter, final int nSubmitButtonTabIndex)
  {
    m_bSubmitPressingEnter = bSubmitPressingEnter;
    m_nSubmitButtonTabIndex = nSubmitButtonTabIndex;
    return thisAsT ();
  }

  @Override
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    if (StringHelper.hasText (m_sAcceptCharset))
      aElement.setAttribute (CHTMLAttributes.ACCEPTCHARSET, m_sAcceptCharset);
    m_aAction.applyProperties (CHTMLAttributes.ACTION, aElement, aConversionSettings.getJSWriterSettings ());
    if (m_eAutoComplete.isDefined ())
      aElement.setAttribute (CHTMLAttributes.AUTOCOMPLETE, m_eAutoComplete.isTrue () ? CHTMLAttributeValues.ON
                                                                                    : CHTMLAttributeValues.OFF);
    if (m_aEncType != null)
      aElement.setAttribute (CHTMLAttributes.ENCTYPE, m_aEncType.getAsString ());
    if (m_eMethod != null)
      aElement.setAttribute (CHTMLAttributes.METHOD, m_eMethod);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_aTarget != null)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("acceptCharset", m_sAcceptCharset)
                            .append ("action", m_aAction)
                            .append ("autoComplete", m_eAutoComplete)
                            .appendIfNotNull ("encType", m_aEncType)
                            .appendIfNotNull ("method", m_eMethod)
                            .appendIfNotNull ("name", m_sName)
                            .appendIfNotNull ("target", m_aTarget)
                            .append ("submitPressingEnter", m_bSubmitPressingEnter)
                            .append ("submitButtonTabIndex", m_nSubmitButtonTabIndex)
                            .toString ();
  }
}
