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
package com.helger.photon.security.password.hash;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsCollection;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.security.password.hash.IPasswordHashCreator;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;

/**
 * This class manages multiple {@link IPasswordHashCreator} instances
 *
 * @author Philip Helger
 */
@ThreadSafe
public class PasswordHashCreatorManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PasswordHashCreatorManager.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, IPasswordHashCreator> m_aPasswordHashCreators = new CommonsHashMap <> ();
  @GuardedBy ("m_aRWLock")
  private IPasswordHashCreator m_aDefaultPasswordHashCreator;

  public PasswordHashCreatorManager ()
  {}

  /**
   * Register a new password hash creator. No other password hash creator with
   * the same algorithm name may be registered.
   *
   * @param aPasswordHashCreator
   *        The password hash creator to be registered. May not be
   *        <code>null</code>.
   */
  public void registerPasswordHashCreator (@Nonnull final IPasswordHashCreator aPasswordHashCreator)
  {
    ValueEnforcer.notNull (aPasswordHashCreator, "PasswordHashCreator");

    final String sAlgorithmName = aPasswordHashCreator.getAlgorithmName ();
    if (StringHelper.hasNoText (sAlgorithmName))
      throw new IllegalArgumentException ("PasswordHashCreator algorithm '" + aPasswordHashCreator + "' is empty!");

    m_aRWLock.writeLocked ( () -> {
      if (m_aPasswordHashCreators.containsKey (sAlgorithmName))
        throw new IllegalArgumentException ("Another PasswordHashCreator for algorithm '" + sAlgorithmName + "' is already registered!");
      m_aPasswordHashCreators.put (sAlgorithmName, aPasswordHashCreator);
    });

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Registered password hash creator algorithm '" + sAlgorithmName + "' to " + aPasswordHashCreator);
  }

  public void unregisterPasswordHashCreator (@Nullable final IPasswordHashCreator aPasswordHashCreator)
  {
    if (aPasswordHashCreator != null)
    {
      final String sAlgorithmName = aPasswordHashCreator.getAlgorithmName ();
      if (StringHelper.hasText (sAlgorithmName))
      {
        m_aRWLock.writeLocked ( () -> {
          if (m_aPasswordHashCreators.remove (sAlgorithmName) != null)
            LOGGER.info ("Unregistered password hash creator algorithm '" + sAlgorithmName + "'");
        });
      }
    }
  }

  /**
   * Get the password hash creator of the specified algorithm name.
   *
   * @param sAlgorithmName
   *        The algorithm name to query. May be <code>null</code>.
   * @return <code>null</code> if no such hash creator is registered.
   */
  @Nullable
  public IPasswordHashCreator getPasswordHashCreatorOfAlgorithm (@Nullable final String sAlgorithmName)
  {
    return m_aRWLock.readLockedGet ( () -> m_aPasswordHashCreators.get (sAlgorithmName));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllPasswordHashCreatorAlgorithms ()
  {
    return m_aRWLock.readLockedGet (m_aPasswordHashCreators::copyOfKeySet);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsCollection <IPasswordHashCreator> getAllPasswordHashCreators ()
  {
    return m_aRWLock.readLockedGet (m_aPasswordHashCreators::copyOfValues);
  }

  /**
   * Set the default password hash creator algorithm. A matching
   * {@link IPasswordHashCreator} object must be registered previously using
   * {@link #registerPasswordHashCreator(IPasswordHashCreator)}.
   *
   * @param sAlgorithm
   *        The name of the algorithm to use as the default. May neither be
   *        <code>null</code> nor empty.
   */
  public void setDefaultPasswordHashCreatorAlgorithm (@Nonnull @Nonempty final String sAlgorithm)
  {
    ValueEnforcer.notEmpty (sAlgorithm, "Algorithm");

    m_aRWLock.writeLocked ( () -> {
      final IPasswordHashCreator aPHC = m_aPasswordHashCreators.get (sAlgorithm);
      if (aPHC == null)
        throw new IllegalArgumentException ("No PasswordHashCreator registered for algorithm '" + sAlgorithm + "'");
      m_aDefaultPasswordHashCreator = aPHC;
    });
    LOGGER.info ("Default PasswordHashCreator algorithm set to '" + sAlgorithm + "'");
  }

  /**
   * @return The default {@link IPasswordHashCreator} algorithm to use. Never
   *         <code>null</code>.
   */
  @Nonnull
  public IPasswordHashCreator getDefaultPasswordHashCreator ()
  {
    final IPasswordHashCreator ret = m_aRWLock.readLockedGet ( () -> m_aDefaultPasswordHashCreator);
    if (ret == null)
      throw new IllegalStateException ("No default PasswordHashCreator present!");
    return ret;
  }

  /**
   * @return The default password hash creator algorithm name currently in use.
   */
  @Nonnull
  public String getDefaultPasswordHashCreatorAlgorithmName ()
  {
    return getDefaultPasswordHashCreator ().getAlgorithmName ();
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
   * @see #getDefaultPasswordHashCreator()
   */
  @Nonnull
  public PasswordHash createUserDefaultPasswordHash (@Nullable final IPasswordSalt aSalt, @Nonnull final String sPlainTextPassword)
  {
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    final IPasswordHashCreator aPHC = getDefaultPasswordHashCreator ();
    final String sPasswordHash = aPHC.createPasswordHash (aSalt, sPlainTextPassword);
    return new PasswordHash (aPHC.getAlgorithmName (), aSalt, sPasswordHash);
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
   * @see #getDefaultPasswordHashCreator()
   */
  @Nonnull
  public PasswordHash createUserPasswordHash (@Nonnull @Nonempty final String sAlgorithmName,
                                              @Nullable final IPasswordSalt aSalt,
                                              @Nonnull final String sPlainTextPassword)
  {
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    final IPasswordHashCreator aPHC = getPasswordHashCreatorOfAlgorithm (sAlgorithmName);
    if (aPHC == null)
      throw new IllegalArgumentException ("No password hash creator for algorithm '" + sAlgorithmName + "' registered!");
    final String sPasswordHash = aPHC.createPasswordHash (aSalt, sPlainTextPassword);
    return new PasswordHash (sAlgorithmName, aSalt, sPasswordHash);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aPasswordHashCreators)
                                       .append ("default", m_aDefaultPasswordHashCreator)
                                       .getToString ();
  }
}
