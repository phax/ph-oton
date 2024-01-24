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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.html.IHCElement;

@Immutable
public final class BootstrapGridSpec implements Serializable
{
  public static final BootstrapGridSpec NONE = new BootstrapGridSpec (null, null, null, null, null);
  public static final BootstrapGridSpec EVENLY = new BootstrapGridSpec (EBootstrapGridXS.EVENLY, null, null, null, null);

  private final EBootstrapGridXS m_eXS;
  private final EBootstrapGridSM m_eSM;
  private final EBootstrapGridMD m_eMD;
  private final EBootstrapGridLG m_eLG;
  private final EBootstrapGridXL m_eXL;

  public BootstrapGridSpec (@Nullable final EBootstrapGridXS eXS,
                            @Nullable final EBootstrapGridSM eSM,
                            @Nullable final EBootstrapGridMD eMD,
                            @Nullable final EBootstrapGridLG eLG,
                            @Nullable final EBootstrapGridXL eXL)
  {
    m_eXS = eXS;
    m_eSM = eSM;
    m_eMD = eMD;
    m_eLG = eLG;
    m_eXL = eXL;
  }

  @Nullable
  public EBootstrapGridXS getXS ()
  {
    return m_eXS;
  }

  @Nullable
  public EBootstrapGridSM getSM ()
  {
    return m_eSM;
  }

  @Nullable
  public EBootstrapGridMD getMD ()
  {
    return m_eMD;
  }

  @Nullable
  public EBootstrapGridLG getLG ()
  {
    return m_eLG;
  }

  @Nullable
  public EBootstrapGridXL getXL ()
  {
    return m_eXL;
  }

  @Nonnull
  public <T extends IHCElement <T>> T applyTo (@Nonnull final T aElement)
  {
    ValueEnforcer.notNull (aElement, "Element");

    int nLastPartCount = IBootstrapGridElement.PARTS_NONE;
    if (m_eXS != null)
    {
      aElement.addClass (m_eXS);
      nLastPartCount = m_eXS.getParts ();
    }
    // Apply only if different from the previous part count
    if (m_eSM != null && m_eSM.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eSM);
      nLastPartCount = m_eSM.getParts ();
    }
    if (m_eMD != null && m_eMD.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eMD);
      nLastPartCount = m_eMD.getParts ();
    }
    if (m_eLG != null && m_eLG.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eLG);
      nLastPartCount = m_eLG.getParts ();
    }
    if (m_eXL != null && m_eXL.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eXL);
      // nLastPartCount = m_eXL.getParts ();
    }
    return aElement;
  }

  @Nonnull
  public <T extends IHCElement <T>> T applyOffsetTo (@Nonnull final T aElement)
  {
    ValueEnforcer.notNull (aElement, "Element");

    int nLastPartCount = IBootstrapGridElement.PARTS_NONE;
    if (m_eXS != null && m_eXS.getParts () > 0)
    {
      aElement.addClass (m_eXS.getCSSClassOffset ());
      nLastPartCount = m_eXS.getParts ();
    }
    // Apply only if different from the previous part count
    if (m_eSM != null && m_eSM.getParts () > 0 && m_eSM.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eSM.getCSSClassOffset ());
      nLastPartCount = m_eSM.getParts ();
    }
    if (m_eMD != null && m_eMD.getParts () > 0 && m_eMD.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eMD.getCSSClassOffset ());
      nLastPartCount = m_eMD.getParts ();
    }
    if (m_eLG != null && m_eLG.getParts () > 0 && m_eLG.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eLG.getCSSClassOffset ());
      nLastPartCount = m_eLG.getParts ();
    }
    if (m_eXL != null && m_eXL.getParts () > 0 && m_eXL.getParts () != nLastPartCount)
    {
      aElement.addClass (m_eXL.getCSSClassOffset ());
      // nLastPartCount = m_eXL.getParts ();
    }
    return aElement;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("xs", m_eXS)
                                       .append ("sm", m_eSM)
                                       .append ("md", m_eMD)
                                       .append ("lg", m_eLG)
                                       .append ("xl", m_eXL)
                                       .getToString ();
  }

  @Nonnull
  public static BootstrapGridSpec create (final int nParts)
  {
    // The larger sizes inherit from the smaller sizes
    return create (nParts,
                   IBootstrapGridElement.PARTS_NONE,
                   IBootstrapGridElement.PARTS_NONE,
                   IBootstrapGridElement.PARTS_NONE,
                   IBootstrapGridElement.PARTS_NONE);
  }

  @Nonnull
  public static BootstrapGridSpec create (final int nPartsXS,
                                          final int nPartsSM,
                                          final int nPartsMD,
                                          final int nPartsLG,
                                          final int nPartsXL)
  {
    return new BootstrapGridSpec (EBootstrapGridXS.getFromParts (nPartsXS),
                                  EBootstrapGridSM.getFromParts (nPartsSM),
                                  EBootstrapGridMD.getFromParts (nPartsMD),
                                  EBootstrapGridLG.getFromParts (nPartsLG),
                                  EBootstrapGridXL.getFromParts (nPartsXL));
  }
}
