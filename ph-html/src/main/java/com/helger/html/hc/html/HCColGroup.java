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

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsImmutableObject;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;

/**
 * Represents an HTML &lt;colgroup&gt; element
 *
 * @author Philip Helger
 */
public class HCColGroup extends AbstractHCElementWithInternalChildren <HCColGroup, HCCol>
{
  public HCColGroup ()
  {
    super (EHTMLElement.COLGROUP);
  }

  @Nonnull
  public HCColGroup addColumns (@Nullable final HCCol... aCols)
  {
    if (aCols != null)
      for (final HCCol aCol : aCols)
        addColumn (aCol);
    return this;
  }

  @Nonnull
  public HCColGroup addColumns (@Nullable final Iterable <? extends HCCol> aCols)
  {
    if (aCols != null)
      for (final HCCol aCol : aCols)
        addColumn (aCol);
    return this;
  }

  /**
   * Add a new column.
   *
   * @param aCol
   *        the column to add
   * @return this
   */
  @Nonnull
  public HCColGroup addColumn (@Nullable final HCCol aCol)
  {
    if (aCol != null)
      addChild (aCol);
    return this;
  }

  /**
   * Add a new column.
   *
   * @param nIndex
   *        The index where the column should be added
   * @param aCol
   *        the column to add
   * @return this
   */
  @Nonnull
  public HCColGroup addColumn (@Nonnegative final int nIndex, @Nullable final HCCol aCol)
  {
    if (aCol != null)
      addChild (nIndex, aCol);
    return this;
  }

  /**
   * @return <code>true</code> if at least one column is present
   */
  public boolean hasColumns ()
  {
    return hasChildren ();
  }

  /**
   * @return <code>true</code> if not a single column is present
   */
  public boolean hasNoColumns ()
  {
    return !hasChildren ();
  }

  /**
   * @return The number of contained columns. Always &ge; 0.
   */
  @Nonnegative
  public int getColumnCount ()
  {
    return getChildCount ();
  }

  /**
   * @return A list of all contained columns. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsImmutableObject
  public List <HCCol> getAllColumns ()
  {
    return getAllChildren ();
  }

  /**
   * Get the column at the specified index
   *
   * @param nIndex
   *        The index to retrieve the column from
   * @return <code>null</code> if no such column exists
   */
  @Nullable
  public HCCol getColumnOfIndex (final int nIndex)
  {
    return getChildAtIndex (nIndex);
  }

  /**
   * Remove the column at the specified index
   *
   * @param nIndex
   *        The index of the column to be removed
   * @return this
   */
  @Nonnull
  public HCColGroup removeColumnAtIndex (final int nIndex)
  {
    removeChild (nIndex);
    return this;
  }

  /**
   * Remove all contained columns
   *
   * @return this
   */
  @Nonnull
  public HCColGroup removeAllColumns ()
  {
    removeAllChildren ();
    return this;
  }
}
