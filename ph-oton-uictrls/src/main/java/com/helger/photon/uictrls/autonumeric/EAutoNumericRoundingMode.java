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
package com.helger.photon.uictrls.autonumeric;

import com.helger.annotation.Nonempty;
import com.helger.base.id.IHasID;

import jakarta.annotation.Nonnull;

/**
 * Rounding modes to be used in {@link HCAutoNumeric}.
 *
 * @author Philip Helger
 */
public enum EAutoNumericRoundingMode implements IHasID <String>
{
  /** Round-Half-Up Symmetric */
  ROUND_HALF_UP_SYMMETRIC ("S"),
  /** Round-Half-Up Asymmetric */
  ROUND_HALF_UP_ASYMMETRIC ("A"),
  /** Round-Half-Down Symmetric */
  ROUND_HALF_DOWN_SYMMETRIC ("s"),
  /** Round-Half-Down Asymmetric */
  ROUND_HALF_DOWN_ASYMMETRIC ("a"),
  /** Round-Half-Even "Bankers Rounding" */
  ROUND_HALF_EVEN ("B"),
  /** Round Up "Round-Away-From-Zero" */
  ROUND_UP ("U"),
  /** Round Down "Round-Toward-Zero" - same as truncate */
  ROUND_DOWN ("D"),
  /** Round to Ceiling "Toward Positive Infinity" */
  ROUND_CEILING ("C"),
  /** Round to Floor "Toward Negative Infinity" */
  ROUND_FLOOR ("F"),
  /** Rounding for Swiss currency "to the nearest .00 or .05" */
  ROUND_CHF ("CHF");

  public static final EAutoNumericRoundingMode DEFAULT = ROUND_HALF_UP_SYMMETRIC;

  private final String m_sID;

  EAutoNumericRoundingMode (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }
}
