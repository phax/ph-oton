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
