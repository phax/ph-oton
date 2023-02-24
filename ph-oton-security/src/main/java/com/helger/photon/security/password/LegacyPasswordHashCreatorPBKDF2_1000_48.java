/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.security.password;

import com.helger.security.password.hash.AbstractPasswordHashCreatorPBKDF2;

/**
 * This is an old, deprecated algorithm that was once used. It is contained
 * here, to allow password verifications based on this algorithm.
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = false)
final class LegacyPasswordHashCreatorPBKDF2_1000_48 extends AbstractPasswordHashCreatorPBKDF2
{
  public static final String ALGORITHM = "PBKDF2_1000_48";
  public static final int PBKDF2_ITERATIONS = 1000;
  public static final int HASH_BYTE_SIZE = 48;

  public LegacyPasswordHashCreatorPBKDF2_1000_48 ()
  {
    super (ALGORITHM, "PBKDF2WithHmacSHA1", PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
  }
}
