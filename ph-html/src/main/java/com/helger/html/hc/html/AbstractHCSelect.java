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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCControl;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.request.IHCRequestField;

/**
 * Represents an HTML &lt;select&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        the implementation type
 */
public abstract class AbstractHCSelect <IMPLTYPE extends AbstractHCSelect <IMPLTYPE>> extends AbstractHCControl <IMPLTYPE>
{
  /** By default auto focus is disabled */
  public static final boolean DEFAULT_AUTO_FOCUS = false;

  /** By default multi select is disabled */
  public static final boolean DEFAULT_MULTIPLE = false;

  private boolean m_bAutoFocus = DEFAULT_AUTO_FOCUS;
  // disabled is inherited
  private String m_sForm;
  private boolean m_bMultiple = DEFAULT_MULTIPLE;
  // name is inherited
  // required is inherited
  private int m_nSize = CGlobal.ILLEGAL_UINT;

  private List <IHCNode> m_aOptions;
  private Set <String> m_aPreselectedValues;

  public AbstractHCSelect ()
  {
    super (EHTMLElement.SELECT);
  }

  public AbstractHCSelect (@Nullable final Collection <String> aPreselectedValues)
  {
    this ();
    m_aPreselectedValues = aPreselectedValues == null ? new HashSet <String> ()
                                                     : new HashSet <String> (aPreselectedValues);
  }

  public AbstractHCSelect (@Nonnull final IHCRequestField aRF)
  {
    this (aRF.getRequestValues ());
    setName (aRF.getFieldName ());
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

  @Nonnull
  @ReturnsMutableCopy
  @Deprecated
  public Set <String> getPreselectedValues ()
  {
    return getAllPreselectedValues ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllPreselectedValues ()
  {
    return CollectionHelper.newSet (m_aPreselectedValues);
  }

  public boolean isPreselectedValue (@Nullable final String sValue)
  {
    return m_aPreselectedValues != null && m_aPreselectedValues.contains (sValue);
  }

  /**
   * Callback
   *
   * @param aOption
   *        The added option. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onAddOption (@Nonnull final HCOption aOption)
  {}

  @Nonnull
  public final HCOption addOption (@Nonnull final HCOption aOption)
  {
    ValueEnforcer.notNull (aOption, "Option");

    // Ensure list is present
    if (m_aOptions == null)
      m_aOptions = new ArrayList <IHCNode> ();

    // Handle preselection (if no manual selection state was defined so far)
    if (!aOption.isSelectionDefined ())
      aOption.setSelected (isPreselectedValue (aOption.getValue ()));

    onAddOption (aOption);
    m_aOptions.add (aOption);
    return aOption;
  }

  @Nonnull
  public final HCOption addOptionAtIndex (@Nonnegative final int nIndex, @Nonnull final HCOption aOption)
  {
    ValueEnforcer.notNull (aOption, "Option");

    // Ensure list is present
    if (m_aOptions == null)
      m_aOptions = new ArrayList <IHCNode> ();

    // Handle preselection (if no manual selection state was defined so far)
    if (!aOption.isSelectionDefined ())
      aOption.setSelected (isPreselectedValue (aOption.getValue ()));

    onAddOption (aOption);
    m_aOptions.add (nIndex, aOption);
    return aOption;
  }

  @Nonnull
  public final HCOption addOption (@Nullable final String sText)
  {
    return addOption (sText, sText);
  }

  @Nonnull
  public final HCOption addOption (@Nullable final String sText, final boolean bSelected)
  {
    return addOption (sText, sText, bSelected);
  }

  @Nonnull
  public final HCOption addOption (@Nullable final String sValue, @Nullable final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOption (aOption);
  }

  @Nonnull
  public final HCOption addOption (@Nullable final String sValue, @Nullable final String sText, final boolean bSelected)
  {
    return addOption (sValue, sText).setSelected (bSelected);
  }

  @Nonnull
  public final HCOption addOption (@Nullable final String sValue,
                                   @Nullable final String sText,
                                   @Nullable final String sSelectedValue)
  {
    return addOption (sValue, sText, EqualsUtils.equals (sValue, sSelectedValue));
  }

  @Nonnull
  public final HCOption addOptionAtIndex (final int nIndex, final String sValue, final String sText)
  {
    final HCOption aOption = new HCOption ().setValue (sValue).addChild (HCTextNode.createOnDemand (sText));
    return addOptionAtIndex (nIndex, aOption);
  }

  @Nonnull
  public final IMPLTYPE addOptionGroup (@Nullable final HCOptGroup aOptGroup)
  {
    if (aOptGroup != null)
    {
      // Ensure list is present
      if (m_aOptions == null)
        m_aOptions = new ArrayList <IHCNode> ();
      m_aOptions.add (aOptGroup);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllOptions ()
  {
    if (m_aOptions != null)
    {
      final List <IHCNode> aRest = new ArrayList <IHCNode> ();
      for (final IHCNode aChild : m_aOptions)
        if (!(aChild instanceof HCOption))
          aRest.add (aChild);
      m_aOptions = aRest;
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllOptionGroups ()
  {
    if (m_aOptions != null)
    {
      final List <IHCNode> aRest = new ArrayList <IHCNode> ();
      for (final IHCNode aChild : m_aOptions)
        if (!(aChild instanceof HCOptGroup))
          aRest.add (aChild);
      m_aOptions = aRest;
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeOptionAtIndex (@Nonnegative final int nIndex)
  {
    if (m_aOptions != null)
    {
      int nMatch = 0;
      int nTotalIndex = 0;
      for (final IHCNode aChild : m_aOptions)
      {
        if (aChild instanceof HCOption)
          if (nMatch++ == nIndex)
          {
            m_aOptions.remove (nTotalIndex);
            break;
          }
        ++nTotalIndex;
      }
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeOptionGroupAtIndex (@Nonnegative final int nIndex)
  {
    if (m_aOptions != null)
    {
      int nMatch = 0;
      int nTotalIndex = 0;
      for (final IHCNode aChild : m_aOptions)
      {
        if (aChild instanceof HCOptGroup)
          if (nMatch++ == nIndex)
          {
            m_aOptions.remove (nTotalIndex);
            break;
          }
        ++nTotalIndex;
      }
    }
    return thisAsT ();
  }

  /**
   * @return The number of available options.
   */
  @Nonnegative
  public final int getOptionCount ()
  {
    int ret = 0;
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption)
          ret++;
    return ret;
  }

  /**
   * @return The number of available option groups.
   */
  @Nonnegative
  public final int getOptionGroupCount ()
  {
    int ret = 0;
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOptGroup)
          ret++;
    return ret;
  }

  /**
   * @return A non-<code>null</code> list of all available options.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final List <HCOption> getOptions ()
  {
    final List <HCOption> ret = new ArrayList <HCOption> ();
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption)
          ret.add ((HCOption) aChild);
    return ret;
  }

  /**
   * @return A non-<code>null</code> list of all available option groups.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final List <HCOptGroup> getOptionGroups ()
  {
    final List <HCOptGroup> ret = new ArrayList <HCOptGroup> ();
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOptGroup)
          ret.add ((HCOptGroup) aChild);
    return ret;
  }

  /**
   * Get the option at the specified index
   *
   * @param nIndex
   *        The index to retrieve. Should always be &ge; 0.
   * @return <code>null</code> if no option is available for the specified
   *         index.
   */
  @Nullable
  public final HCOption getOptionAtIndex (@Nonnegative final int nIndex)
  {
    HCOption ret = null;
    if (m_aOptions != null)
    {
      int nMatch = 0;
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption)
          if (nMatch++ == nIndex)
          {
            ret = (HCOption) aChild;
            break;
          }
    }
    return ret;
  }

  /**
   * Get the option group at the specified index
   *
   * @param nIndex
   *        The index to retrieve. Should always be &ge; 0.
   * @return <code>null</code> if no option group is available for the specified
   *         index.
   */
  @Nullable
  public final HCOptGroup getOptionGroupAtIndex (@Nonnegative final int nIndex)
  {
    HCOptGroup ret = null;
    if (m_aOptions != null)
    {
      int nMatch = 0;
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOptGroup)
          if (nMatch++ == nIndex)
          {
            ret = (HCOptGroup) aChild;
            break;
          }
    }
    return ret;
  }

  /**
   * @return <code>true</code> if this select has at least one option.
   */
  public final boolean hasOptions ()
  {
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption)
          return true;
    return false;
  }

  /**
   * @return <code>true</code> if this select has at least one option group.
   */
  public final boolean hasOptionGroups ()
  {
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOptGroup)
          return true;
    return false;
  }

  /**
   * @return A non-<code>null</code> list of all selected options.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final List <HCOption> getSelectedOptions ()
  {
    final List <HCOption> ret = new ArrayList <HCOption> ();
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption && ((HCOption) aChild).isSelected ())
          ret.add ((HCOption) aChild);
    return ret;
  }

  /**
   * @return The number of selected options. Always &ge; 0.
   */
  @Nonnegative
  public final int getSelectedOptionCount ()
  {
    int ret = 0;
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption && ((HCOption) aChild).isSelected ())
          ++ret;
    return ret;
  }

  /**
   * Check if this select has at least one selected option
   *
   * @return <code>true</code> if at least one option is selected
   */
  public final boolean hasSelectedOption ()
  {
    if (m_aOptions != null)
      for (final IHCNode aChild : m_aOptions)
        if (aChild instanceof HCOption && ((HCOption) aChild).isSelected ())
          return true;
    return false;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_bAutoFocus)
      aElement.setAttribute (CHTMLAttributes.AUTOFOCUS, CHTMLAttributeValues.AUTOFOCUS);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    if (m_bMultiple)
      aElement.setAttribute (CHTMLAttributes.MULTIPLE, CHTMLAttributeValues.MULTIPLE);
    if (m_nSize > 1)
      aElement.setAttribute (CHTMLAttributes.SIZE, m_nSize);

    if (CollectionHelper.isNotEmpty (m_aOptions))
    {
      for (final IHCNode aOption : m_aOptions)
        aElement.appendChild (aOption.convertToNode (aConversionSettings));
    }
    else
    {
      // Special check, as this is not derived from
      // AbstractHCElementWithChildren
      if (!getElement ().mayBeSelfClosed ())
        aElement.appendText ("");
    }
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("autoFocus", m_bAutoFocus)
                            .appendIfNotNull ("form", m_sForm)
                            .append ("multiple", m_bMultiple)
                            .append ("size", m_nSize)
                            .appendIfNotNull ("options", m_aOptions)
                            .appendIfNotNull ("preselectedValues", m_aPreselectedValues)
                            .toString ();
  }
}
