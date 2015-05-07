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
package com.helger.html.hc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.CheckForSigned;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.htmlext.HCUtils;

/**
 * Base class for elements with special children.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 * @param <CHILDTYPE>
 *        Contained child type
 */
@NotThreadSafe
public abstract class AbstractHCElementWithInternalChildren <THISTYPE extends AbstractHCElementWithInternalChildren <THISTYPE, CHILDTYPE>, CHILDTYPE extends IHCNode> extends AbstractHCElement <THISTYPE> implements IHCHasChildrenMutable <THISTYPE, CHILDTYPE>
{
  private List <CHILDTYPE> m_aChildren;

  protected AbstractHCElementWithInternalChildren (@Nonnull final EHTMLElement aElement)
  {
    super (aElement);
  }

  public final boolean hasChildren ()
  {
    return CollectionHelper.isNotEmpty (m_aChildren);
  }

  /**
   * Callback
   *
   * @param aChild
   *        The child that was added
   */
  @OverrideOnDemand
  protected void beforeAddChild (@Nonnull final CHILDTYPE aChild)
  {}

  /**
   * Callback
   *
   * @param nIndex
   *        Index where the child was added. Always &ge; 0.
   * @param aChild
   *        The child that was added
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void afterAddChild (@Nonnegative final int nIndex, @Nonnull final CHILDTYPE aChild)
  {
    aChild.onAdded (nIndex, this);
  }

  private void _addChild (@CheckForSigned final int nIndex, @Nullable final CHILDTYPE aChild)
  {
    if (aChild == this)
      throw new IllegalArgumentException ("Cannot append to self!");

    if (aChild != null)
    {
      beforeAddChild (aChild);
      if (m_aChildren == null)
        m_aChildren = new ArrayList <CHILDTYPE> ();
      int nAddIndex;
      if (nIndex < 0)
      {
        nAddIndex = m_aChildren.size ();
        m_aChildren.add (aChild);
      }
      else
      {
        nAddIndex = nIndex;
        m_aChildren.add (nIndex, aChild);
      }
      afterAddChild (nAddIndex, aChild);
    }
  }

  @Nonnull
  public final THISTYPE addChild (@Nullable final CHILDTYPE aChild)
  {
    _addChild (CGlobal.ILLEGAL_UINT, aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final CHILDTYPE aChild)
  {
    ValueEnforcer.isBetweenInclusive (nIndex, "Index", 0, getChildCount ());
    _addChild (nIndex, aChild);
    return thisAsT ();
  }

  @Nonnull
  @DevelopersNote ("Use addChild instead!")
  @Deprecated
  public final THISTYPE addChildren (@Nullable final CHILDTYPE aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final CHILDTYPE... aChildren)
  {
    if (aChildren != null)
      for (final CHILDTYPE aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addChildren (@Nullable final Iterable <? extends CHILDTYPE> aChildren)
  {
    if (aChildren != null)
      for (final CHILDTYPE aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }

  @Nullable
  @CheckReturnValue
  public final <V extends CHILDTYPE> V addAndReturnChild (@Nullable final V aChild)
  {
    addChild (aChild);
    return aChild;
  }

  @Nullable
  @CheckReturnValue
  public final <V extends CHILDTYPE> V addAndReturnChild (@Nonnegative final int nIndex, @Nullable final V aChild)
  {
    addChild (nIndex, aChild);
    return aChild;
  }

  /**
   * Invoked after an element was removed.
   *
   * @param nIndex
   *        The index of the child relative to the parent.
   * @param aChild
   *        The child that was removed. Never <code>null</code>.
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void afterRemoveChild (@Nonnegative final int nIndex, @Nonnull final CHILDTYPE aChild)
  {
    aChild.onRemoved (nIndex, this);
  }

  @Nonnull
  public final THISTYPE removeChild (@Nullable final CHILDTYPE aChild)
  {
    if (aChild != null && m_aChildren != null)
    {
      final int nChildIndex = m_aChildren.indexOf (aChild);
      if (nChildIndex >= 0)
        removeChild (nChildIndex);
    }
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeChild (@Nonnegative final int nIndex)
  {
    final CHILDTYPE aRemovedChild = CollectionHelper.removeAndReturnElementAtIndex (m_aChildren, nIndex);
    if (aRemovedChild != null)
      afterRemoveChild (nIndex, aRemovedChild);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE removeAllChildren ()
  {
    if (m_aChildren != null)
    {
      while (!m_aChildren.isEmpty ())
        removeChild (0);
      m_aChildren = null;
    }
    return thisAsT ();
  }

  @Nullable
  public final CHILDTYPE getChild (final int nIndex)
  {
    return CollectionHelper.getSafe (m_aChildren, nIndex, null);
  }

  @Nonnegative
  public final int getChildCount ()
  {
    return CollectionHelper.getSize (m_aChildren);
  }

  @Nullable
  @ReturnsMutableObject (reason = "speed")
  protected final List <CHILDTYPE> directGetChildren ()
  {
    return m_aChildren;
  }

  @Nonnull
  @ReturnsMutableCopy
  @Deprecated
  public final List <CHILDTYPE> getChildren ()
  {
    return getAllChildren ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <CHILDTYPE> getAllChildren ()
  {
    return CollectionHelper.newList (m_aChildren);
  }

  @Nullable
  public final CHILDTYPE getChildAtIndex (@Nonnegative final int nIndex)
  {
    return CollectionHelper.getSafe (m_aChildren, nIndex);
  }

  @Nullable
  public final CHILDTYPE getFirstChild ()
  {
    return CollectionHelper.getFirstElement (m_aChildren);
  }

  @Nullable
  public final CHILDTYPE getLastChild ()
  {
    return CollectionHelper.getLastElement (m_aChildren);
  }

  public final boolean recursiveContainsChildWithTagName (@Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return HCUtils.recursiveGetFirstChildWithTagName (this, aElements) != null;
  }

  @Nonnull
  public final THISTYPE sortAllChildren (@Nonnull final Comparator <? super CHILDTYPE> aComparator)
  {
    ValueEnforcer.notNull (aComparator, "Comparator");
    if (m_aChildren != null)
      Collections.sort (m_aChildren, aComparator);
    return thisAsT ();
  }

  @Nonnull
  public final HCNodeList getAllChildrenAsNodeList ()
  {
    return new HCNodeList ().addChildren (m_aChildren);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Can always be converted to a node
    return true;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Propagate to children
    if (hasChildren ())
      for (final CHILDTYPE aChild : m_aChildren)
        aChild.beforeConvertToNode (aConversionSettings);
  }

  /**
   * Helper method that returns the elements in the correct order for emitting.
   * This can e.g. be used for sorting or ordering.
   *
   * @param aChildren
   *        The children to be emitted. Is a direct reference to the container
   *        where the children are stored. So handle with care!
   * @return The non-<code>null</code> list with all child elements to be
   *         emitted.
   */
  @Nonnull
  @Nonempty
  @OverrideOnDemand
  protected List <? extends CHILDTYPE> getChildrenFormEmitting (@Nonnull @Nonempty final List <CHILDTYPE> aChildren)
  {
    return aChildren;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (hasChildren ())
      for (final CHILDTYPE aChild : getChildrenFormEmitting (m_aChildren))
        aElement.appendChild (aChild.convertToNode (aConversionSettings));

    if (!aElement.hasChildren ())
    {
      // If no children are present, add an empty text node so that an opening
      // and a closing tag are written separately
      // Note: just using "hasChildren" is not enough, in case a child is
      // present, that is not rendered!
      if (!getElement ().mayBeSelfClosed ())
        aElement.appendText ("");
    }
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    if (!hasChildren ())
      return "";

    final StringBuilder ret = new StringBuilder ();
    for (final CHILDTYPE aChild : getChildrenFormEmitting (m_aChildren))
    {
      final String sPlainText = aChild.getPlainText ();
      if (StringHelper.hasText (sPlainText))
      {
        if (ret.length () > 0)
          ret.append (' ');
        ret.append (sPlainText);
      }
    }
    return ret.toString ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("children", m_aChildren).toString ();
  }
}
