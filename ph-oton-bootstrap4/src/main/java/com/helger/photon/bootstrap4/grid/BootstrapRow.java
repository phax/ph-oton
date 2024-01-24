/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

/**
 * Defines a Bootstrap row that contains columns.
 *
 * @author Philip Helger
 */
public class BootstrapRow extends AbstractBootstrapDiv <BootstrapRow>
{
  private EBootstrapRowVerticalAlign m_eVertAlign;
  private boolean m_bWithPadding = true;

  public BootstrapRow ()
  {
    addClass (CBootstrapCSS.ROW);
  }

  @Nonnull
  public BootstrapRow setVerticalAlign (@Nullable final EBootstrapRowVerticalAlign eVertAlign)
  {
    m_eVertAlign = eVertAlign;
    return this;
  }

  @Nullable
  public EBootstrapRowVerticalAlign getVertAlign ()
  {
    return m_eVertAlign;
  }

  @Nonnull
  public BootstrapRow setWithPadding (final boolean bWithPadding)
  {
    m_bWithPadding = bWithPadding;
    return this;
  }

  public boolean isWithPadding ()
  {
    return m_bWithPadding;
  }

  @Nonnull
  public BootstrapCol createColumn (final int nParts)
  {
    return createColumn (BootstrapGridSpec.create (nParts));
  }

  @Nonnull
  public BootstrapCol createColumn (final int nPartsXS, final int nPartsSM, final int nPartsMD, final int nPartsLG, final int nPartsXL)
  {
    return createColumn (BootstrapGridSpec.create (nPartsXS, nPartsSM, nPartsMD, nPartsLG, nPartsXL));
  }

  @Nonnull
  public BootstrapCol createColumn (@Nullable final EBootstrapGridXS eXS,
                                    @Nullable final EBootstrapGridSM eSM,
                                    @Nullable final EBootstrapGridMD eMD,
                                    @Nullable final EBootstrapGridLG eLG,
                                    @Nullable final EBootstrapGridXL eXL)
  {
    return createColumn (new BootstrapGridSpec (eXS, eSM, eMD, eLG, eXL));
  }

  @Nonnull
  public BootstrapCol createColumn (@Nonnull final BootstrapGridSpec aGridSpec)
  {
    ValueEnforcer.notNull (aGridSpec, "GridSpec");

    final BootstrapCol aDiv = addAndReturnChild (new BootstrapCol ());
    aGridSpec.applyTo (aDiv);
    return aDiv;
  }

  @Nonnull
  public HCDiv createNewLine ()
  {
    return addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.W_100));
  }

  @Nullable
  public BootstrapCol getFirstColumn ()
  {
    return getColumnAtIndex (0);
  }

  @Nullable
  public BootstrapCol getColumnAtIndex (@Nonnegative final int nIndex)
  {
    int nCols = 0;
    for (final IHCNode aChild : getChildren ())
      if (aChild instanceof BootstrapCol)
      {
        if (nCols == nIndex)
          return (BootstrapCol) aChild;
        ++nCols;
      }
    return null;
  }

  @Nullable
  public BootstrapCol getLastColumn ()
  {
    for (final IHCNode aChild : getAllChildren ().reverse ())
      if (aChild instanceof BootstrapCol)
        return (BootstrapCol) aChild;
    return null;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_eVertAlign);
    if (!m_bWithPadding)
      addClass (CBootstrapCSS.NO_GUTTERS);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static BootstrapRow createRowWithOneColumn (final int nParts, @Nonnull final IHCNode aCtrl)
  {
    final BootstrapRow aRow = new BootstrapRow ();
    aRow.createColumn (nParts).addChild (aCtrl);
    return aRow;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static BootstrapRow createRowWithOneColumn (final int nPartsXS,
                                                     final int nPartsSM,
                                                     final int nPartsMD,
                                                     final int nPartsLG,
                                                     final int nPartsXL,
                                                     @Nonnull final IHCNode aCtrl)
  {
    final BootstrapRow aRow = new BootstrapRow ();
    aRow.createColumn (nPartsXS, nPartsSM, nPartsMD, nPartsLG, nPartsXL).addChild (aCtrl);
    return aRow;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static BootstrapRow createRowWithOneColumn (@Nonnull final BootstrapGridSpec aParts, @Nonnull final IHCNode aCtrl)
  {
    final BootstrapRow aRow = new BootstrapRow ();
    aRow.createColumn (aParts).addChild (aCtrl);
    return aRow;
  }
}
