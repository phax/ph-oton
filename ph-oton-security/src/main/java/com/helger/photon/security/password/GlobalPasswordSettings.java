/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.lang.ServiceLoaderHelper;
import com.helger.photon.security.password.constraint.IPasswordConstraintList;
import com.helger.photon.security.password.constraint.PasswordConstraintList;
import com.helger.photon.security.password.hash.IPasswordHashCreatorRegistrarSPI;
import com.helger.photon.security.password.hash.PasswordHashCreatorManager;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.hash.PasswordHashCreatorPBKDF2_1000_48;
import com.helger.security.password.hash.PasswordHashCreatorSHA512;
import com.helger.security.password.salt.IPasswordSalt;

/**
 * Central class for all password related elements.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class GlobalPasswordSettings
{
  private static final Logger LOGGER = LoggerFactory.getLogger (GlobalPasswordSettings.class);
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static IPasswordConstraintList s_aPasswordConstraintList = new PasswordConstraintList ();

  private static final PasswordHashCreatorManager s_aPHCMgr = new PasswordHashCreatorManager ();

  static
  {
    // Register default implementation so that something is present
    s_aPHCMgr.registerPasswordHashCreator (new PasswordHashCreatorSHA512 ());
    s_aPHCMgr.registerPasswordHashCreator (new PasswordHashCreatorPBKDF2_1000_48 ());

    // Set the default password hash creator
    s_aPHCMgr.setDefaultPasswordHashCreatorAlgorithm (PasswordHashCreatorPBKDF2_1000_48.ALGORITHM);

    // Register all custom SPI implementations
    for (final IPasswordHashCreatorRegistrarSPI aSPI : ServiceLoaderHelper.getAllSPIImplementations (IPasswordHashCreatorRegistrarSPI.class))
      aSPI.registerPasswordHashCreators (s_aPHCMgr);
  }

  @PresentForCodeCoverage
  private static final GlobalPasswordSettings s_aInstance = new GlobalPasswordSettings ();

  private GlobalPasswordSettings ()
  {}

  /**
   * @return The current password constraint list. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static IPasswordConstraintList getPasswordConstraintList ()
  {
    return s_aRWLock.readLockedGet (s_aPasswordConstraintList::getClone);
  }

  /**
   * Set the global password constraint list.
   *
   * @param aPasswordConstraintList
   *        The list to be set. May not be <code>null</code>.
   */
  public static void setPasswordConstraintList (@Nonnull final IPasswordConstraintList aPasswordConstraintList)
  {
    ValueEnforcer.notNull (aPasswordConstraintList, "PasswordConstraintList");

    // Create a copy
    final IPasswordConstraintList aRealPasswordConstraints = aPasswordConstraintList.getClone ();
    s_aRWLock.writeLocked ( () -> {
      s_aPasswordConstraintList = aRealPasswordConstraints;
    });
    LOGGER.info ("Set global password constraints to " + aRealPasswordConstraints);
  }

  /**
   * @return <code>true</code> if any password constraint is defined,
   *         <code>false</code> if not.
   */
  public static boolean isAnyPasswordConstraintDefined ()
  {
    return getPasswordConstraintList ().hasConstraints ();
  }

  /**
   * @return The central {@link PasswordHashCreatorManager}.
   */
  @Nonnull
  public static PasswordHashCreatorManager getPasswordHashCreatorManager ()
  {
    return s_aPHCMgr;
  }

  /**
   * Create the password hash from the passed plain text password, using the
   * default password hash creator.
   *
   * @param aSalt
   *        Optional salt to be used. This parameter is only <code>null</code>
   *        for backwards compatibility reasons.
   * @param sPlainTextPassword
   *        Plain text password. May not be <code>null</code>.
   * @return The password hash. Never <code>null</code>.
   */
  @Nonnull
  public static PasswordHash createUserDefaultPasswordHash (@Nullable final IPasswordSalt aSalt, @Nonnull final String sPlainTextPassword)
  {
    return s_aPHCMgr.createUserDefaultPasswordHash (aSalt, sPlainTextPassword);
  }

  /**
   * Create the password hash from the passed plain text password, using the
   * default password hash creator.
   *
   * @param sAlgorithmName
   *        The password hash creator algorithm name to query. May neither be
   *        <code>null</code> nor empty.
   * @param aSalt
   *        Optional salt to be used. This parameter is only <code>null</code>
   *        for backwards compatibility reasons.
   * @param sPlainTextPassword
   *        Plain text password. May not be <code>null</code>.
   * @return The password hash. Never <code>null</code>.
   */
  @Nonnull
  public static PasswordHash createUserPasswordHash (@Nonnull @Nonempty final String sAlgorithmName,
                                                     @Nullable final IPasswordSalt aSalt,
                                                     @Nonnull final String sPlainTextPassword)
  {
    return s_aPHCMgr.createUserPasswordHash (sAlgorithmName, aSalt, sPlainTextPassword);
  }
}
