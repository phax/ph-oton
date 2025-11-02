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
package com.helger.photon.uictrls.bloodhound;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.string.StringHelper;

/**
 * All possible values for the rateLimitBy parameter used in Bloodhound options
 * remote object.
 *
 * @author Philip Helger
 */
public enum EBloodhoundRemoteRateLimitBy
{
  DEBOUNCE ("debounce"),
  THROTTLE ("throttle");

  private final String m_sValue;

  EBloodhoundRemoteRateLimitBy (@NonNull @Nonempty final String sValue)
  {
    m_sValue = sValue;
  }

  @NonNull
  @Nonempty
  public String getValue ()
  {
    return m_sValue;
  }

  @Nullable
  public static EBloodhoundRemoteRateLimitBy getFromValueOrNull (@Nullable final String sValue)
  {
    return getFromValueOrDefault (sValue, null);
  }

  @Nullable
  public static EBloodhoundRemoteRateLimitBy getFromValueOrDefault (@Nullable final String sValue,
                                                                    @Nullable final EBloodhoundRemoteRateLimitBy eDefault)
  {
    if (StringHelper.isNotEmpty (sValue))
      for (final EBloodhoundRemoteRateLimitBy e : values ())
        if (sValue.equals (e.m_sValue))
          return e;
    return eDefault;
  }
}
