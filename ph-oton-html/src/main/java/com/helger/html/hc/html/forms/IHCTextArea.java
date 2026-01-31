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
package com.helger.html.hc.html.forms;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.state.ETriState;

/**
 * Interface for TEXTAREAs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCTextArea <IMPLTYPE extends IHCTextArea <IMPLTYPE>> extends IHCControl <IMPLTYPE>
{
  boolean isAutoCompleteOn ();

  boolean isAutoCompleteOff ();

  boolean isAutoCompleteUndefined ();

  @NonNull
  default IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @NonNull
  IMPLTYPE setAutoComplete (@NonNull ETriState eAutoComplete);

  boolean isAutoFocus ();

  @NonNull
  IMPLTYPE setAutoFocus (boolean bAutoFocus);

  int getCols ();

  @NonNull
  IMPLTYPE setCols (int nCols);

  @Nullable
  String getDirName ();

  @NonNull
  IMPLTYPE setDirName (@Nullable String sDirName);

  @Nullable
  String getForm ();

  @NonNull
  IMPLTYPE setForm (@Nullable String sForm);

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
  @NonNull
  IMPLTYPE setMaxLength (int nMaxLength);

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
  @NonNull
  IMPLTYPE setMinLength (int nMinLength);

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
  @NonNull
  IMPLTYPE setPlaceholder (@Nullable String sPlaceholder);

  int getRows ();

  @NonNull
  IMPLTYPE setRows (int nRows);

  @Nullable
  String getValue ();

  @NonNull
  IMPLTYPE setValue (@Nullable String sValue);

  @Nullable
  EHCTextAreaWrap getWrap ();

  @NonNull
  IMPLTYPE setWrap (@Nullable EHCTextAreaWrap eWrap);
}
