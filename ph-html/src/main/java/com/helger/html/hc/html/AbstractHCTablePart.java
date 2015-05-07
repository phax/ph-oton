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
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;

/**
 * This is the base class for thead, tbody and tfoot
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public abstract class AbstractHCTablePart <THISTYPE extends AbstractHCTablePart <THISTYPE>> extends AbstractHCElementWithInternalChildren <THISTYPE, HCRow>
{
  private final boolean m_bHeaderOrFooter;

  public AbstractHCTablePart (@Nonnull final EHTMLElement eHTMLElement, final boolean bHeaderOrFooter)
  {
    super (eHTMLElement);
    m_bHeaderOrFooter = bHeaderOrFooter;
  }

  public final boolean isHeaderOrFooter ()
  {
    return m_bHeaderOrFooter;
  }

  @Nonnull
  public final HCRow addRow ()
  {
    final HCRow aRow = new HCRow (m_bHeaderOrFooter);
    addChild (aRow);
    return aRow;
  }

  @Nonnull
  public final HCRow addRow (@Nonnegative final int nIndex)
  {
    final HCRow aRow = new HCRow (m_bHeaderOrFooter);
    addChild (nIndex, aRow);
    return aRow;
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object. May be <code>null</code>.
   */
  @Nullable
  @ReturnsMutableObject (reason = "speed")
  final List <HCRow> directGetRowList ()
  {
    return directGetChildren ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Avoid creating an empty part
    return hasChildren () || hasID () || hasAnyClass () || hasAnyStyle () || hasCustomAttrs ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("headerOrFooter", m_bHeaderOrFooter).toString ();
  }
}
