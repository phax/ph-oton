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

import java.util.Locale;

import com.helger.html.request.IHCRequestField;

import jakarta.annotation.Nonnull;

/**
 * Special numeric edit for years from {@value #DEFAULT_MIN} to
 * {@value #DEFAULT_MAX}.
 *
 * @author Philip Helger
 */
public class HCEditYear extends AbstractHCAutoNumeric <HCEditYear>
{
  /** Default minimum value is 0 */
  public static final int DEFAULT_MIN = 0;
  /** Default maximum value is 0 */
  public static final int DEFAULT_MAX = 9999;

  public HCEditYear (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    super (aRF, aDisplayLocale);
    setDecimalPlaces (0);
    setMin (DEFAULT_MIN);
    setMax (DEFAULT_MAX);
  }
}
