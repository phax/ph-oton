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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.state.ETriState;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;

/**
 * Base interface for controls base on &lt;input&gt; area.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCInput <IMPLTYPE extends IHCInput <IMPLTYPE>> extends IHCControl <IMPLTYPE>
{
  /**
   * @return The type used for this <code>input</code> element. May be
   *         <code>null</code>.
   */
  @Nullable
  EHCInputType getType ();

  @Nonnull
  IMPLTYPE setType (@Nonnull EHCInputType eType);

  @Nullable
  String getAccept ();

  @Nonnull
  IMPLTYPE setAccept (@Nullable String sAccept);

  @Nonnull
  IMPLTYPE setAccept (@Nullable IMimeType aAccept);

  @Nullable
  String getAlt ();

  @Nonnull
  IMPLTYPE setAlt (@Nullable String sAlt);

  boolean isAutoCompleteOn ();

  boolean isAutoCompleteOff ();

  boolean isAutoCompleteUndefined ();

  @Nonnull
  default IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @Nonnull
  IMPLTYPE setAutoComplete (@Nonnull ETriState eAutoComplete);

  boolean isAutoFocus ();

  @Nonnull
  IMPLTYPE setAutoFocus (final boolean bAutoFocus);

  /**
   * @return Whether or not the check-box is currently checked
   */
  boolean isChecked ();

  /**
   * Set the checked state according to the passed value
   *
   * @param bChecked
   *        new checked state
   * @return This object for chaining
   */
  @Nonnull
  IMPLTYPE setChecked (final boolean bChecked);

  @Nullable
  String getDirName ();

  @Nonnull
  IMPLTYPE setDirName (@Nullable String sDirName);

  @Nullable
  String getForm ();

  @Nonnull
  IMPLTYPE setForm (@Nullable String sForm);

  @Nullable
  ISimpleURL getFormActionURL ();

  @Nullable
  IHasJSCode getFormActionJS ();

  @Nonnull
  IMPLTYPE setFormAction (@Nullable ISimpleURL aAction);

  @Nonnull
  IMPLTYPE setFormAction (@Nullable IHasJSCodeWithSettings aAction);

  @Nullable
  IMimeType getFormEncType ();

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE setFormEncTypeFileUpload ()
  {
    return setFormEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE setFormEncTypeTextPlain ()
  {
    return setFormEncType (CMimeType.TEXT_PLAIN);
  }

  @Nonnull
  IMPLTYPE setFormEncType (@Nullable IMimeType aFormEncType);

  @Nullable
  EHCFormMethod getFormMethod ();

  @Nonnull
  IMPLTYPE setFormMethod (@Nullable EHCFormMethod eFormMethod);

  boolean isFormNoValidate ();

  @Nonnull
  IMPLTYPE setFormNoValidate (final boolean bFormNoValidate);

  @Nullable
  HC_Target getFormTarget ();

  @Nonnull
  default IMPLTYPE setFormTargetBlank ()
  {
    return setFormTarget (HC_Target.BLANK);
  }

  @Nonnull
  IMPLTYPE setFormTarget (@Nullable HC_Target aFormTarget);

  int getHeight ();

  @Nonnull
  IMPLTYPE setHeight (final int nHeight);

  @Nullable
  String getList ();

  @Nonnull
  IMPLTYPE setList (@Nullable String sList);

  @Nullable
  String getMaxValue ();

  @Nonnull
  IMPLTYPE setMaxValue (@Nullable String sMaxValue);

  /**
   * @return The currently set max length or -1.
   */
  int getMaxLength ();

  /**
   * Set the maximum number of characters to be entered.
   *
   * @param nMaxLength
   *        The max length. Should be &gt; 0.
   * @return this
   */
  @Nonnull
  IMPLTYPE setMaxLength (final int nMaxLength);

  @Nullable
  String getMinValue ();

  @Nonnull
  IMPLTYPE setMinValue (@Nullable String sMinValue);

  /**
   * @return The currently set min length or -1.
   */
  int getMinLength ();

  /**
   * Set the minimum number of characters to be entered.
   *
   * @param nMinLength
   *        The min length. Should be &gt; 0.
   * @return this
   */
  @Nonnull
  IMPLTYPE setMinLength (final int nMinLength);

  boolean isMultiple ();

  @Nonnull
  IMPLTYPE setMultiple (final boolean bMultiple);

  @Nullable
  String getPattern ();

  @Nonnull
  IMPLTYPE setPattern (@Nullable String sPattern);

  /**
   * @return The current value of the HTML <code>placeholder</code> attribute.
   *         May be <code>null</code>.
   */
  @Nullable
  String getPlaceholder ();

  /**
   * @return <code>true</code> if a placeholder is present, <code>false</code>
   *         otherwise.
   */
  default boolean hasPlaceholder ()
  {
    // Only check for null, so that empty string overrides this
    // default behaviour
    return getPlaceholder () != null;
  }

  /**
   * Set the new value for the HTML <code>placeholder</code> attribute.
   *
   * @param sPlaceholder
   *        The new value. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setPlaceholder (@Nullable String sPlaceholder);

  /**
   * @return The currently set max length.
   */
  int getSize ();

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
  IMPLTYPE setSize (final int nSize);

  @Nullable
  ISimpleURL getSrc ();

  @Nonnull
  IMPLTYPE setSrc (@Nullable ISimpleURL aSrc);

  @Nullable
  String getStep ();

  @Nonnull
  IMPLTYPE setStep (@Nullable String sStep);

  /**
   * @return The field value, maybe <code>null</code>
   */
  @Nullable
  String getValue ();

  /**
   * Sets the passed field value
   *
   * @param nValue
   *        Value to use.
   * @return This object for chaining
   */
  @Nonnull
  default IMPLTYPE setValue (final int nValue)
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
  default IMPLTYPE setValue (final long nValue)
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
  IMPLTYPE setValue (@Nullable String sValue);

  int getWidth ();

  @Nonnull
  IMPLTYPE setWidth (final int nWidth);
}
