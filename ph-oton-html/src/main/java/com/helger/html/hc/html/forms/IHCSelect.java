/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.equals.EqualsHelper;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Interface for definition items
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public interface IHCSelect <THISTYPE extends IHCSelect <THISTYPE>> extends IHCControl <THISTYPE>
{
  boolean isAutoFocus ();

  @Nonnull
  THISTYPE setAutoFocus (final boolean bAutoFocus);

  @Nullable
  String getForm ();

  @Nonnull
  THISTYPE setForm (@Nullable String sForm);

  boolean isMultiple ();

  @Nonnull
  THISTYPE setMultiple (final boolean bMultiple);

  int getSize ();

  @Nonnull
  THISTYPE setSize (final int nSize);

  @Nonnull
  @ReturnsMutableCopy
  ICommonsSet <String> getAllPreselectedValues ();

  boolean isPreselectedValue (@Nullable String sValue);

  @Nonnull
  HCOption addOption (@Nonnull HCOption aOption);

  /**
   * Add a new option at the specified index.
   *
   * @param nIndex
   *        The index to use. Should be &ge; 0.
   * @param aOption
   *        The option to be added. May not be <code>null</code>.
   * @return this for chaining
   * @deprecated Use {@link #addOptionAt(int,HCOption)} instead
   */
  @Deprecated
  @Nonnull
  default HCOption addOptionAtIndex (@Nonnegative final int nIndex, @Nonnull final HCOption aOption)
  {
    return addOptionAt (nIndex, aOption);
  }

  /**
   * Add a new option at the specified index.
   *
   * @param nIndex
   *        The index to use. Should be &ge; 0.
   * @param aOption
   *        The option to be added. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  HCOption addOptionAt (@Nonnegative int nIndex, @Nonnull HCOption aOption);

  @Nonnull
  default HCOption addOption (@Nullable final String sText)
  {
    return addOption (sText, sText);
  }

  @Nonnull
  default HCOption addOption (@Nullable final String sText, final boolean bSelected)
  {
    return addOption (sText, sText, bSelected);
  }

  @Nonnull
  default HCOption addOption (@Nullable final String sValue, @Nullable final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOption (aOption);
  }

  @Nonnull
  default HCOption addOption (@Nullable final String sValue, @Nullable final String sText, final boolean bSelected)
  {
    return addOption (sValue, sText).setSelected (bSelected);
  }

  @Nonnull
  default HCOption addOption (@Nullable final String sValue,
                              @Nullable final String sText,
                              @Nullable final String sSelectedValue)
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
   * @deprecated Use {@link #addOptionAt(int,String,String)} instead
   */
  @Deprecated
  @Nonnull
  default HCOption addOptionAtIndex (@Nonnegative final int nIndex,
                                     @Nullable final String sValue,
                                     @Nullable final String sText)
  {
    return addOptionAt (nIndex, sValue, sText);
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
  @Nonnull
  default HCOption addOptionAt (@Nonnegative final int nIndex,
                                @Nullable final String sValue,
                                @Nullable final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOptionAt (nIndex, aOption);
  }

  @Nonnull
  THISTYPE addOptionGroup (@Nullable HCOptGroup aOptGroup);

  @Nonnull
  THISTYPE removeAllOptions ();

  @Nonnull
  THISTYPE removeAllOptionGroups ();

  /**
   * Remove the option at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   * @deprecated Use {@link #removeOptionAt(int)} instead
   */
  @Deprecated
  @Nonnull
  default THISTYPE removeOptionAtIndex (@Nonnegative final int nIndex)
  {
    return removeOptionAt (nIndex);
  }

  /**
   * Remove the option at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  THISTYPE removeOptionAt (@Nonnegative int nIndex);

  /**
   * Remove the option group at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   * @deprecated Use {@link #removeOptionGroupAt(int)} instead
   */
  @Deprecated
  @Nonnull
  default THISTYPE removeOptionGroupAtIndex (@Nonnegative final int nIndex)
  {
    return removeOptionGroupAt (nIndex);
  }

  /**
   * Remove the option group at the specified index.
   *
   * @param nIndex
   *        The index to be removed. Should be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  THISTYPE removeOptionGroupAt (@Nonnegative int nIndex);

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
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <HCOption> getAllOptions ();

  /**
   * @return A non-<code>null</code> list of all available option groups.
   */
  @Nonnull
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
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <HCOption> getAllSelectedOptions ();

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
