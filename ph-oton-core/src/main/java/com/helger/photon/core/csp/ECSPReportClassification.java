/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.csp;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.id.IHasID;
import com.helger.base.lang.EnumHelper;

/**
 * The classification of a received CSP report, as determined by an {@link ICSPReportClassifier}.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
public enum ECSPReportClassification implements IHasID <String>
{
  /** The report is assumed to be caused by the own markup and needs attention. */
  GENUINE ("genuine"),
  /**
   * The report is assumed to be caused by something outside of the own markup - like browser
   * internal code or a browser extension - and is therefore not actionable.
   */
  LIKELY_NOISE ("likelynoise");

  private final String m_sID;

  ECSPReportClassification (@NonNull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return <code>true</code> if this is {@link #LIKELY_NOISE}, <code>false</code> otherwise.
   */
  public boolean isLikelyNoise ()
  {
    return this == LIKELY_NOISE;
  }

  @Nullable
  public static ECSPReportClassification getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (ECSPReportClassification.class, sID);
  }
}
