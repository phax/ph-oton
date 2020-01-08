/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.jquery;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.jscode.IJSExpression;

/**
 * A list of jQuery selectors that are chained with ' ' (space)
 *
 * @author Philip Helger
 */
public class JQuerySelectorList implements IHasJSCodeWithSettings
{
  private final ICommonsList <IJQuerySelector> m_aElements = new CommonsArrayList <> ();

  public JQuerySelectorList ()
  {}

  public JQuerySelectorList (@Nonnull final IJQuerySelector aSelector)
  {
    addSelector (aSelector);
  }

  public JQuerySelectorList (@Nullable final IJQuerySelector... aSelectors)
  {
    if (aSelectors != null)
      for (final IJQuerySelector aSelector : aSelectors)
        addSelector (aSelector);
  }

  public JQuerySelectorList (@Nullable final Iterable <? extends IJQuerySelector> aSelectors)
  {
    if (aSelectors != null)
      for (final IJQuerySelector aSelector : aSelectors)
        addSelector (aSelector);
  }

  public boolean hasSelectors ()
  {
    return m_aElements.isNotEmpty ();
  }

  @Nonnegative
  public int getSelectorCount ()
  {
    return m_aElements.size ();
  }

  @Nonnull
  public JQuerySelectorList addSelector (@Nonnull final IJQuerySelector aSelector)
  {
    ValueEnforcer.notNull (aSelector, "Selector");
    m_aElements.add (aSelector);
    return this;
  }

  @Nonnull
  public EChange removeSelector (@Nonnegative final int nIndex)
  {
    return EChange.valueOf (m_aElements.remove (nIndex) != null);
  }

  @Nonnull
  public EChange removeSelector (@Nonnegative final IJQuerySelector aSelector)
  {
    return EChange.valueOf (m_aElements.remove (aSelector));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IJQuerySelector> getAllSelectors ()
  {
    return m_aElements.getClone ();
  }

  @Nonnull
  public EChange clear ()
  {
    return m_aElements.removeAll ();
  }

  @Nonnull
  public IJSExpression getAsExpression ()
  {
    final int nSize = m_aElements.size ();
    if (nSize == 0)
      throw new IllegalStateException ("Empty jQuery selector is not allowed!");

    if (nSize == 1)
      return m_aElements.getFirst ().getExpression ();

    // Concatenate with ' '
    IJSExpression ret = m_aElements.getFirst ().getExpression ();
    for (int i = 1; i < nSize; ++i)
      ret = ret.plus (' ').plus (m_aElements.get (i).getExpression ());
    return ret;
  }

  /**
   * @return <code>$(selectorString)</code>
   */
  @Nonnull
  public JQueryInvocation invoke ()
  {
    return JQuery.jQuery (getAsExpression ());
  }

  @Nonnull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return getAsExpression ().getJSCode (aSettings);
  }
}
