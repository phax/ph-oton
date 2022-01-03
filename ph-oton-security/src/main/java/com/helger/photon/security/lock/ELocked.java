/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.security.lock;

import javax.annotation.Nonnull;

/**
 * Locked enumeration
 *
 * @author Philip Helger
 */
public enum ELocked implements ILockedIndicator
{
  LOCKED,
  NOT_LOCKED;

  public boolean isLocked ()
  {
    return this == LOCKED;
  }

  public boolean isNotLocked ()
  {
    return this == NOT_LOCKED;
  }

  @Nonnull
  public static ELocked valueOf (final boolean bLocked)
  {
    return bLocked ? LOCKED : NOT_LOCKED;
  }

  @Nonnull
  public static ELocked valueOf (@Nonnull final ILockedIndicator eLockingIndicator)
  {
    return valueOf (eLockingIndicator.isLocked ());
  }
}
