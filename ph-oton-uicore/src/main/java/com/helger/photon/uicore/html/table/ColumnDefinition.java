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
package com.helger.photon.uicore.html.table;

import java.io.Serializable;
import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.html.HCCol;

@Immutable
@Deprecated
public class ColumnDefinition implements Serializable
{
  private final String m_sName;
  private final HCCol m_aCol;
  private final String m_sFieldID;
  private final Comparator <IHCCell <?>> m_aComparator;
  private String m_sToolTip;

  public ColumnDefinition (@Nonnull final String sName, @Nonnull final HCCol aCol)
  {
    this (sName, aCol, null, null);
  }

  public ColumnDefinition (@Nonnull final String sName, @Nonnull final HCCol aCol, @Nullable final String sFieldID)
  {
    this (sName, aCol, sFieldID, null);
  }

  public ColumnDefinition (@Nullable final String sName,
                           @Nonnull final HCCol aCol,
                           @Nullable final String sFieldID,
                           @Nullable final Comparator <IHCCell <?>> aComparator)
  {
    if (StringHelper.hasNoText (sFieldID) && aComparator != null)
      throw new IllegalArgumentException ("field ID cannot be empty for sortable columns");
    ValueEnforcer.notNull (aCol, "Col");
    m_sName = sName;
    m_aCol = aCol;
    m_sFieldID = sFieldID;
    m_aComparator = aComparator;
  }

  public boolean isFieldMapped ()
  {
    return m_sFieldID != null;
  }

  @Nonnull
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public ColumnDefinition setToolTip (@Nullable final String sToolTip)
  {
    m_sToolTip = sToolTip;
    return this;
  }

  @Nullable
  public String getToolTip ()
  {
    return m_sToolTip;
  }

  @Nonnull
  public HCCol getCol ()
  {
    return m_aCol;
  }

  @Nullable
  public String getFieldID ()
  {
    return m_sFieldID;
  }

  @Nullable
  public Comparator <IHCCell <?>> getComparator ()
  {
    return m_aComparator;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sName)
                                       .append ("column", m_aCol)
                                       .append ("fieldID", m_sFieldID)
                                       .append ("comparator", m_aComparator)
                                       .append ("tooltip", m_sToolTip)
                                       .toString ();
  }
}
