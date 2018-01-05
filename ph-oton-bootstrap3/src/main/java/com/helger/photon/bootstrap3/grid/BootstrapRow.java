/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.grid;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Defines a Bootstrap row that contains columns.
 *
 * @author Philip Helger
 */
public class BootstrapRow extends AbstractHCDiv <BootstrapRow>
{
  public BootstrapRow ()
  {
    addClass (CBootstrapCSS.ROW);
  }

  @Nonnull
  public HCDiv createColumn (final int nParts)
  {
    return createColumn (BootstrapGridSpec.create (nParts));
  }

  @Nonnull
  public HCDiv createColumn (final int nPartsXS, final int nPartsSM, final int nPartsMD, final int nPartsLG)
  {
    return createColumn (BootstrapGridSpec.create (nPartsXS, nPartsSM, nPartsMD, nPartsLG));
  }

  @Nonnull
  public HCDiv createColumn (@Nullable final EBootstrapGridXS eXS,
                             @Nullable final EBootstrapGridSM eSM,
                             @Nullable final EBootstrapGridMD eMD,
                             @Nullable final EBootstrapGridLG eLG)
  {
    return createColumn (new BootstrapGridSpec (eXS, eSM, eMD, eLG));
  }

  @Nonnull
  public HCDiv createColumn (@Nonnull final BootstrapGridSpec aGridSpec)
  {
    ValueEnforcer.notNull (aGridSpec, "GridSpec");

    final HCDiv aDiv = addAndReturnChild (new HCDiv ());
    aGridSpec.applyTo (aDiv);
    return aDiv;
  }

  @Nullable
  public HCDiv getFirstColumn ()
  {
    return (HCDiv) getFirstChild ();
  }

  @Nullable
  public HCDiv getColumnAtIndex (@Nonnegative final int nIndex)
  {
    return (HCDiv) getChildAtIndex (nIndex);
  }

  @Nullable
  public HCDiv getLastColumn ()
  {
    return (HCDiv) getLastChild ();
  }
}
