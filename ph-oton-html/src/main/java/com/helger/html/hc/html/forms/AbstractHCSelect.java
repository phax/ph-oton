/**
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

import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsIterable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.request.IHCRequestField;
import com.helger.html.request.IHCRequestFieldMultiValue;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;select&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        the implementation type
 */
public abstract class AbstractHCSelect <IMPLTYPE extends AbstractHCSelect <IMPLTYPE>> extends
                                       AbstractHCControl <IMPLTYPE> implements
                                       IHCSelect <IMPLTYPE>
{
  /** By default multi select is disabled */
  public static final boolean DEFAULT_MULTIPLE = false;

  // autofocus is inherited
  // disabled is inherited
  private String m_sForm;
  private boolean m_bMultiple = DEFAULT_MULTIPLE;
  // name is inherited
  // required is inherited
  private int m_nSize = CGlobal.ILLEGAL_UINT;

  private ICommonsList <IHCNode> m_aOptions = new CommonsArrayList <> ();
  private final ICommonsSet <String> m_aPreselectedValues = new CommonsHashSet <> ();

  public AbstractHCSelect ()
  {
    super (EHTMLElement.SELECT);
  }

  public AbstractHCSelect (@Nonnull final IHCRequestField aRF)
  {
    this ();
    // Single request value only
    m_aPreselectedValues.add (aRF.getRequestValue ());
    setName (aRF.getFieldName ());
  }

  public AbstractHCSelect (@Nonnull final IHCRequestFieldMultiValue aRF)
  {
    this ();
    // Multiple request values possible
    m_aPreselectedValues.addAll (aRF.getRequestValues ());
    // Implicitly multiple
    setMultiple (true);
    setName (aRF.getFieldName ());
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
  public final ICommonsSet <String> getAllPreselectedValues ()
  {
    return m_aPreselectedValues.getClone ();
  }

  public final boolean isPreselectedValue (@Nullable final String sValue)
  {
    return m_aPreselectedValues.contains (sValue);
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

    // Handle preselection (if no manual selection state was defined so far)
    if (!aOption.isSelectionDefined ())
      aOption.setSelected (isPreselectedValue (aOption.getValue ()));

    onAddOption (aOption);
    m_aOptions.add (aOption);
    return aOption;
  }

  @Nonnull
  public final HCOption addOptionAt (@Nonnegative final int nIndex, @Nonnull final HCOption aOption)
  {
    ValueEnforcer.notNull (aOption, "Option");

    // Handle preselection (if no manual selection state was defined so far)
    if (!aOption.isSelectionDefined ())
      aOption.setSelected (isPreselectedValue (aOption.getValue ()));

    onAddOption (aOption);
    m_aOptions.add (nIndex, aOption);
    return aOption;
  }

  @Nonnull
  public final IMPLTYPE addOptionGroup (@Nullable final HCOptGroup aOptGroup)
  {
    if (aOptGroup != null)
      m_aOptions.add (aOptGroup);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllOptions ()
  {
    final ICommonsList <IHCNode> aRest = new CommonsArrayList <> ();
    for (final IHCNode aChild : m_aOptions)
      if (!(aChild instanceof HCOption))
        aRest.add (aChild);
    m_aOptions = aRest;
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllOptionGroups ()
  {
    final ICommonsList <IHCNode> aRest = new CommonsArrayList <> ();
    for (final IHCNode aChild : m_aOptions)
      if (!(aChild instanceof HCOptGroup))
        aRest.add (aChild);
    m_aOptions = aRest;
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeOptionAt (@Nonnegative final int nIndex)
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
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeOptionGroupAt (@Nonnegative final int nIndex)
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
    return thisAsT ();
  }

  @Nonnegative
  public final int getOptionCount ()
  {
    return m_aOptions.getCount (c -> c instanceof HCOption);
  }

  @Nonnegative
  public final int getOptionGroupCount ()
  {
    return m_aOptions.getCount (c -> c instanceof HCOptGroup);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <HCOption> getAllOptions ()
  {
    return m_aOptions.getAllInstanceOf (HCOption.class);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <HCOptGroup> getAllOptionGroups ()
  {
    return m_aOptions.getAllInstanceOf (HCOptGroup.class);
  }

  @Nullable
  public final HCOption getOptionAtIndex (@Nonnegative final int nIndex)
  {
    return m_aOptions.getAtIndexMapped (aChild -> aChild instanceof HCOption, nIndex, aChild -> (HCOption) aChild);
  }

  @Nullable
  public final HCOptGroup getOptionGroupAtIndex (@Nonnegative final int nIndex)
  {
    return m_aOptions.getAtIndexMapped (aChild -> aChild instanceof HCOptGroup, nIndex, aChild -> (HCOptGroup) aChild);
  }

  public final boolean hasOptions ()
  {
    return m_aOptions.containsAny (c -> c instanceof HCOption);
  }

  public final boolean hasOptionGroups ()
  {
    return m_aOptions.containsAny (c -> c instanceof HCOptGroup);
  }

  private static final Predicate <IHCNode> PRED_SELECTED_OPTION = aChild -> aChild instanceof HCOption &&
                                                                            ((HCOption) aChild).isSelected ();

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <HCOption> getAllSelectedOptions ()
  {
    return m_aOptions.getAllMapped (PRED_SELECTED_OPTION, HCOption.class::cast);
  }

  @Nullable
  public HCOption getFirstSelectedOption ()
  {
    return m_aOptions.findFirstMapped (PRED_SELECTED_OPTION, HCOption.class::cast);
  }

  @Nonnegative
  public final int getSelectedOptionCount ()
  {
    return m_aOptions.getCount (PRED_SELECTED_OPTION);
  }

  public final boolean hasSelectedOption ()
  {
    return m_aOptions.containsAny (PRED_SELECTED_OPTION);
  }

  @Nullable
  @ReturnsMutableObject
  public final ICommonsList <IHCNode> children ()
  {
    return m_aOptions;
  }

  @Override
  @Nullable
  @ReturnsMutableCopy
  public final ICommonsList <IHCNode> getAllChildren ()
  {
    return m_aOptions.getClone ();
  }

  @Override
  @Nonnull
  @ReturnsImmutableObject
  public final ICommonsIterable <IHCNode> getChildren ()
  {
    return m_aOptions;
  }

  @Override
  @Nullable
  public final IHCNode getChildAtIndex (final int nIndex)
  {
    return m_aOptions.getAtIndex (nIndex);
  }

  @Override
  @Nullable
  public final IHCNode getFirstChild ()
  {
    return m_aOptions.getFirst ();
  }

  @Override
  @Nullable
  public final IHCNode getLastChild ()
  {
    return m_aOptions.getLast ();
  }

  @Override
  public final boolean hasChildren ()
  {
    return m_aOptions.isNotEmpty ();
  }

  @Override
  public final int getChildCount ()
  {
    return m_aOptions.size ();
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sForm))
      aElement.setAttribute (CHTMLAttributes.FORM, m_sForm);
    if (m_bMultiple)
      aElement.setAttribute (CHTMLAttributes.MULTIPLE, CHTMLAttributeValues.MULTIPLE);
    if (m_nSize > 1)
      aElement.setAttribute (CHTMLAttributes.SIZE, m_nSize);

    if (m_aOptions.isNotEmpty ())
    {
      for (final IHCNode aOption : m_aOptions)
        aElement.appendChild (aOption.convertToMicroNode (aConversionSettings));
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
                            .appendIfNotNull ("form", m_sForm)
                            .append ("multiple", m_bMultiple)
                            .append ("size", m_nSize)
                            .append ("options", m_aOptions)
                            .appendIf ("preselectedValues", m_aPreselectedValues, CollectionHelper::isNotEmpty)
                            .getToString ();
  }
}
