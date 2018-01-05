/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import com.helger.commons.state.ETriState;

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

  @Nonnull
  default IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @Nonnull
  IMPLTYPE setAutoComplete (@Nonnull ETriState eAutoComplete);

  boolean isAutoFocus ();

  @Nonnull
  IMPLTYPE setAutoFocus (boolean bAutoFocus);

  int getCols ();

  @Nonnull
  IMPLTYPE setCols (int nCols);

  @Nullable
  String getDirName ();

  @Nonnull
  IMPLTYPE setDirName (@Nullable String sDirName);

  @Nullable
  String getForm ();

  @Nonnull
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
  @Nonnull
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
  @Nonnull
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
  @Nonnull
  IMPLTYPE setPlaceholder (@Nullable String sPlaceholder);

  int getRows ();

  @Nonnull
  IMPLTYPE setRows (int nRows);

  @Nullable
  String getValue ();

  @Nonnull
  IMPLTYPE setValue (@Nullable String sValue);

  @Nullable
  EHCTextAreaWrap getWrap ();

  @Nonnull
  IMPLTYPE setWrap (@Nullable EHCTextAreaWrap eWrap);
}
