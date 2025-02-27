/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.attr.IAttributeContainer;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroQName;

/**
 * Special attribute container for HC elements
 *
 * @author Philip Helger
 */
public interface IHCAttrContainer extends IAttributeContainer <IMicroQName, String>
{
  @Nonnull
  default EChange putIn (@Nonnull final String sName, @Nullable final String sNewValue)
  {
    return putIn (new MicroQName (sName), sNewValue);
  }

  /**
   * @return <code>true</code> if at least one data attribute is contained
   */
  default boolean hasDataAttrs ()
  {
    return CollectionHelper.containsAny (keySet (), x -> CHTMLAttributes.isDataAttrName (x.getName ()));
  }

  /**
   * Check if a certain data attribute is contained. Shortcut for
   * <code>containsCustomAttr ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to check
   * @return <code>true</code> if such a data attribute is contained.
   */
  default boolean containsDataAttr (@Nullable final String sName)
  {
    return containsKey (CHTMLAttributes.makeDataAttrName (sName));
  }

  /**
   * Get the value of a certain data attribute. Shortcut for
   * <code>getCustomAttrValue ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to retrieve the value from
   * @return <code>null</code> if no such data attribute is contained.
   */
  @Nullable
  default String getDataAttrValue (@Nullable final String sName)
  {
    return getValue (CHTMLAttributes.makeDataAttrName (sName));
  }

  /**
   * @return All data attributes contained. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  default ICommonsOrderedMap <IMicroQName, String> getAllDataAttrs ()
  {
    final ICommonsOrderedMap <IMicroQName, String> ret = new CommonsLinkedHashMap <> ();
    for (final Map.Entry <IMicroQName, String> aEntry : entrySet ())
      if (CHTMLAttributes.isDataAttrName (aEntry.getKey ().getName ()))
        ret.put (aEntry.getKey (), aEntry.getValue ());
    return ret;
  }

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, nValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return {@link EChange}
   */
  @Nonnull
  default EChange setDataAttr (@Nullable final String sName, final int nValue)
  {
    return setDataAttr (sName, Integer.toString (nValue));
  }

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, nValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return {@link EChange}
   */
  @Nonnull
  default EChange setDataAttr (@Nullable final String sName, final long nValue)
  {
    return setDataAttr (sName, Long.toString (nValue));
  }

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, sValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param sValue
   *        The value of the attribute. If it is <code>null</code> nothing
   *        happens
   * @return {@link EChange}
   */
  @Nonnull
  default EChange setDataAttr (@Nullable final String sName, @Nullable final String sValue)
  {
    return putIn (CHTMLAttributes.makeDataAttrName (sName), sValue);
  }

  /**
   * Remove the data attribute with the specified name. Shortcut for
   * <code>removeCustomAttr ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to be removed
   * @return this
   */
  @Nonnull
  default EChange removeDataAttr (@Nullable final String sName)
  {
    return removeObject (CHTMLAttributes.makeDataAttrName (sName));
  }

  @Nonnull
  default EChange setAriaControls (@Nonnull final String sControls)
  {
    return putIn (CHTMLAttributes.ARIA_CONTROLS, sControls);
  }

  default boolean containsAriaDescription ()
  {
    return containsKey (CHTMLAttributes.ARIA_DESCRIPTION);
  }

  @Nonnull
  default EChange setAriaDescription (@Nonnull final String sDescription)
  {
    return putIn (CHTMLAttributes.ARIA_DESCRIPTION, sDescription);
  }

  default boolean containsAriaDescribedBy ()
  {
    return containsKey (CHTMLAttributes.ARIA_DESCRIBEDBY);
  }

  @Nonnull
  default EChange setAriaDescribedBy (@Nonnull final String sDescribedBy)
  {
    return putIn (CHTMLAttributes.ARIA_DESCRIBEDBY, sDescribedBy);
  }

  @Nonnull
  default EChange addToAriaDescribedBy (@Nonnull final String sDescribedBy)
  {
    final String sOldValue = getValue (CHTMLAttributes.ARIA_DESCRIBEDBY);
    final boolean bAppend = StringHelper.hasText (sOldValue) && !sOldValue.contains (sDescribedBy);
    return setAriaDescribedBy (bAppend ? sOldValue + ' ' + sDescribedBy : sDescribedBy);
  }

  @Nonnull
  default EChange setAriaDescribedBy (@Nonnull final IHCElement <?> aDescribedBy)
  {
    return setAriaDescribedBy (aDescribedBy.ensureID ().getID ());
  }

  @Nonnull
  default EChange addToAriaDescribedBy (@Nonnull final IHCElement <?> aDescribedBy)
  {
    return addToAriaDescribedBy (aDescribedBy.ensureID ().getID ());
  }

  @Nonnull
  default EChange setAriaDescribedBy (@Nonnull final Iterable <? extends IHCElement <?>> aDescribedByMultiple)
  {
    return setAriaDescribedBy (StringHelper.imploder ()
                                           .source (aDescribedByMultiple, x -> x.ensureID ().getID ())
                                           .separator (' ')
                                           .build ());
  }

  @Nonnull
  default EChange addToAriaDescribedBy (@Nonnull final Iterable <? extends IHCElement <?>> aDescribedByMultiple)
  {
    EChange eChange = EChange.UNCHANGED;
    for (final IHCElement <?> aItem : aDescribedByMultiple)
      eChange = eChange.or (addToAriaDescribedBy (aItem));
    return eChange;
  }

  @Nonnull
  default EChange setAriaDescribedBy (@Nonnull final IHCElement <?>... aDescribedByMultiple)
  {
    return setAriaDescribedBy (StringHelper.imploder ()
                                           .source (aDescribedByMultiple, x -> x.ensureID ().getID ())
                                           .separator (' ')
                                           .build ());
  }

  @Nonnull
  default EChange addToAriaDescribedBy (@Nonnull final IHCElement <?>... aDescribedByMultiple)
  {
    EChange eChange = EChange.UNCHANGED;
    for (final IHCElement <?> aItem : aDescribedByMultiple)
      eChange = eChange.or (addToAriaDescribedBy (aItem));
    return eChange;
  }

  @Nonnull
  default EChange setAriaExpanded (final boolean bIsExpanded)
  {
    return putIn (CHTMLAttributes.ARIA_EXPANDED, Boolean.toString (bIsExpanded));
  }

  @Nonnull
  default EChange setAriaHasPopup (final boolean bHasPopup)
  {
    return putIn (CHTMLAttributes.ARIA_HASPOPUP, Boolean.toString (bHasPopup));
  }

  @Nonnull
  default EChange setAriaHidden (final boolean bHidden)
  {
    return putIn (CHTMLAttributes.ARIA_HIDDEN, Boolean.toString (bHidden));
  }

  @Nonnull
  default EChange setAriaInvalid (final boolean bInvalid)
  {
    return putIn (CHTMLAttributes.ARIA_INVALID, Boolean.toString (bInvalid));
  }

  default boolean containsAriaLabel ()
  {
    return containsKey (CHTMLAttributes.ARIA_LABEL);
  }

  @Nonnull
  default EChange setAriaLabel (@Nonnull final String sLabel)
  {
    return putIn (CHTMLAttributes.ARIA_LABEL, sLabel);
  }

  default boolean containsAriaLabeledBy ()
  {
    return containsKey (CHTMLAttributes.ARIA_LABELLEDBY);
  }

  @Nonnull
  default EChange setAriaLabeledBy (@Nonnull final String sLabeledBy)
  {
    return putIn (CHTMLAttributes.ARIA_LABELLEDBY, sLabeledBy);
  }

  /**
   * @param sLabeledBy
   *        labeled by
   * @return {@link EChange}.
   * @deprecated Use {@link #addToAriaLabeledBy(String)} instead
   */
  @Nonnull
  @Deprecated (forRemoval = true, since = "9.2.7")
  default EChange addAriaLabeledBy (@Nonnull final String sLabeledBy)
  {
    return addToAriaLabeledBy (sLabeledBy);
  }

  @Nonnull
  default EChange addToAriaLabeledBy (@Nonnull final String sLabeledBy)
  {
    final String sOldValue = getValue (CHTMLAttributes.ARIA_LABELLEDBY);
    final boolean bAppend = StringHelper.hasText (sOldValue) && !sOldValue.contains (sLabeledBy);
    return setAriaLabeledBy (bAppend ? sOldValue + ' ' + sLabeledBy : sLabeledBy);
  }

  @Nonnull
  default EChange setAriaLabeledBy (@Nonnull final IHCElement <?> aLabeledBy)
  {
    return setAriaLabeledBy (aLabeledBy.ensureID ().getID ());
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.2.7")
  default EChange addAriaLabeledBy (@Nonnull final IHCElement <?> aLabeledBy)
  {
    return addToAriaLabeledBy (aLabeledBy);
  }

  @Nonnull
  default EChange addToAriaLabeledBy (@Nonnull final IHCElement <?> aLabeledBy)
  {
    return addToAriaLabeledBy (aLabeledBy.ensureID ().getID ());
  }

  @Nonnull
  default EChange setAriaLabeledBy (@Nonnull final Iterable <? extends IHCElement <?>> aLabeledByMultiple)
  {
    return setAriaLabeledBy (StringHelper.imploder ()
                                         .source (aLabeledByMultiple, x -> x.ensureID ().getID ())
                                         .separator (' ')
                                         .build ());
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.2.7")
  default EChange addAriaLabeledBy (@Nonnull final Iterable <? extends IHCElement <?>> aLabeledByMultiple)
  {
    return addToAriaLabeledBy (aLabeledByMultiple);
  }

  @Nonnull
  default EChange addToAriaLabeledBy (@Nonnull final Iterable <? extends IHCElement <?>> aLabeledByMultiple)
  {
    EChange eChange = EChange.UNCHANGED;
    for (final IHCElement <?> aItem : aLabeledByMultiple)
      eChange = eChange.or (addToAriaLabeledBy (aItem));
    return eChange;
  }

  @Nonnull
  default EChange setAriaLabeledBy (@Nonnull final IHCElement <?>... aLabeledByMultiple)
  {
    return setAriaLabeledBy (StringHelper.imploder ()
                                         .source (aLabeledByMultiple, x -> x.ensureID ().getID ())
                                         .separator (' ')
                                         .build ());
  }

  @Nonnull
  @Deprecated (forRemoval = true, since = "9.2.7")
  default EChange addAriaLabeledBy (@Nonnull final IHCElement <?>... aLabeledByMultiple)
  {
    return addToAriaLabeledBy (aLabeledByMultiple);
  }

  @Nonnull
  default EChange addToAriaLabeledBy (@Nonnull final IHCElement <?>... aLabeledByMultiple)
  {
    EChange eChange = EChange.UNCHANGED;
    for (final IHCElement <?> aItem : aLabeledByMultiple)
      eChange = eChange.or (addToAriaLabeledBy (aItem));
    return eChange;
  }

  @Nonnull
  default EChange setAriaRequired (final boolean bIsRequired)
  {
    return putIn (CHTMLAttributes.ARIA_REQUIRED, Boolean.toString (bIsRequired));
  }
}
