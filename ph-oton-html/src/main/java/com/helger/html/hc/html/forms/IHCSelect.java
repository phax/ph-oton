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

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.equals.EqualsHelper;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsSet;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Interface for definition items
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCSelect <IMPLTYPE extends IHCSelect <IMPLTYPE>> extends IHCControl <IMPLTYPE>
{
  boolean isAutoFocus ();

  @NonNull
  IMPLTYPE setAutoFocus (final boolean bAutoFocus);

  @Nullable
  String getForm ();

  @NonNull
  IMPLTYPE setForm (@Nullable String sForm);

  boolean isMultiple ();

  @NonNull
  IMPLTYPE setMultiple (final boolean bMultiple);

  int getSize ();

  @NonNull
  IMPLTYPE setSize (final int nSize);

  @NonNull
  @ReturnsMutableCopy
  ICommonsSet <String> getAllPreselectedValues ();

  boolean isPreselectedValue (@Nullable String sValue);

  @NonNull
  HCOption addOption (@NonNull HCOption aOption);

  /**
   * Add a new option at the specified index.
   *
   * @param nIndex
   *        The index to use. Should be &ge; 0.
   * @param aOption
   *        The option to be added. May not be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  HCOption addOptionAt (@Nonnegative int nIndex, @NonNull HCOption aOption);

  @NonNull
  default HCOption addOption (@Nullable final String sText)
  {
    return addOption (sText, sText);
  }

  @NonNull
  default HCOption addOption (@Nullable final String sText, final boolean bSelected)
  {
    return addOption (sText, sText, bSelected);
  }

  @NonNull
  default HCOption addOption (@Nullable final String sValue, @Nullable final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOption (aOption);
  }

  @NonNull
  default HCOption addOption (@Nullable final String sValue, @Nullable final String sText, final boolean bSelected)
  {
    return addOption (sValue, sText).setSelected (bSelected);
  }

  @NonNull
  default HCOption addOption (@Nullable final String sValue, @Nullable final String sText, @Nullable final String sSelectedValue)
  {
    return addOption (sValue, sText, EqualsHelper.equals (sValue, sSelectedValue));
  }

  /**
   * Add a new option at the specified index.
   *
   * @param nIndex
   *        The index to use. Should be &ge; 0.
   * @param sValue
   *        The value of the option to be added. May be <code>null</code>.
   * @param sText
   *        The text of the option to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  default HCOption addOptionAt (@Nonnegative final int nIndex, @Nullable final String sValue, @Nullable final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOptionAt (nIndex, aOption);
  }

  @NonNull
  IMPLTYPE addOptionGroup (@Nullable HCOptGroup aOptGroup);

  @NonNull
  IMPLTYPE removeAllOptions ();

  @NonNull
  IMPLTYPE removeAllOptionGroups ();

  /**
   * Remove the option at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  IMPLTYPE removeOptionAt (@Nonnegative int nIndex);

  /**
   * Remove the option group at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  IMPLTYPE removeOptionGroupAt (@Nonnegative int nIndex);

  /**
   * @return The number of available options.
   */
  @Nonnegative
  int getOptionCount ();

  /**
   * @return The number of available option groups.
   */
  @Nonnegative
  int getOptionGroupCount ();

  /**
   * @return A non-<code>null</code> list of all available options.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <HCOption> getAllOptions ();

  /**
   * @return A non-<code>null</code> list of all available option groups.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <HCOptGroup> getAllOptionGroups ();

  /**
   * Get the option at the specified index
   *
   * @param nIndex
   *        The index to retrieve. Should always be &ge; 0.
   * @return <code>null</code> if no option is available for the specified
   *         index.
   */
  @Nullable
  HCOption getOptionAtIndex (@Nonnegative int nIndex);

  /**
   * Get the option group at the specified index
   *
   * @param nIndex
   *        The index to retrieve. Should always be &ge; 0.
   * @return <code>null</code> if no option group is available for the specified
   *         index.
   */
  @Nullable
  HCOptGroup getOptionGroupAtIndex (@Nonnegative int nIndex);

  /**
   * @return <code>true</code> if this select has at least one option.
   */
  boolean hasOptions ();

  /**
   * @return <code>true</code> if this select has at least one option group.
   */
  boolean hasOptionGroups ();

  /**
   * @return A non-<code>null</code> list of all selected options.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <HCOption> getAllSelectedOptions ();

  /**
   * @return The first selected option. May be <code>null</code>.
   */
  @Nullable
  HCOption getFirstSelectedOption ();

  @Nullable
  default String getFirstSelectedOptionValue ()
  {
    final HCOption aOption = getFirstSelectedOption ();
    return aOption == null ? null : aOption.getValue ();
  }

  /**
   * @return The number of selected options. Always &ge; 0.
   */
  @Nonnegative
  int getSelectedOptionCount ();

  /**
   * Check if this select has at least one selected option
   *
   * @return <code>true</code> if at least one option is selected
   */
  boolean hasSelectedOption ();
}
