/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.appbasics.security.password.hash;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.security.password.salt.IPasswordSalt;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class manages multiple {@link IPasswordHashCreator} instances
 *
 * @author Philip Helger
 */
@ThreadSafe
public class PasswordHashCreatorManager
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PasswordHashCreatorManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, IPasswordHashCreator> m_aPasswordHashCreators = new HashMap <String, IPasswordHashCreator> ();
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aPasswordHashCreators.containsKey (sAlgorithmName))
        throw new IllegalArgumentException ("Another PasswordHashCreator for algorithm '" +
                                            sAlgorithmName +
                                            "' is already registered!");
      m_aPasswordHashCreators.put (sAlgorithmName, aPasswordHashCreator);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    s_aLogger.info ("Registered password hash creator algorithm '" + sAlgorithmName + "' to " + aPasswordHashCreator);
  }

  public void unregisterPasswordHashCreator (@Nullable final IPasswordHashCreator aPasswordHashCreator)
  {
    if (aPasswordHashCreator != null)
    {
      final String sAlgorithmName = aPasswordHashCreator.getAlgorithmName ();
      if (StringHelper.hasText (sAlgorithmName))
      {
        m_aRWLock.writeLock ().lock ();
        try
        {
          if (m_aPasswordHashCreators.remove (sAlgorithmName) != null)
            s_aLogger.info ("Unregistered password hash creator algorithm '" + sAlgorithmName + "'");
        }
        finally
        {
          m_aRWLock.writeLock ().unlock ();
        }
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aPasswordHashCreators.get (sAlgorithmName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllPasswordHashCreatorAlgorithms ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newSet (m_aPasswordHashCreators.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <IPasswordHashCreator> getAllPasswordHashCreators ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aPasswordHashCreators.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      final IPasswordHashCreator aPHC = m_aPasswordHashCreators.get (sAlgorithm);
      if (aPHC == null)
        throw new IllegalArgumentException ("No PasswordHashCreator registered for algorithm '" + sAlgorithm + "'");
      m_aDefaultPasswordHashCreator = aPHC;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    s_aLogger.info ("Default PasswordHashCreator algorithm set to '" + sAlgorithm + "'");
  }

  /**
   * @return The default {@link IPasswordHashCreator} algorithm to use. Never
   *         <code>null</code>.
   */
  @Nonnull
  public IPasswordHashCreator getDefaultPasswordHashCreator ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final IPasswordHashCreator ret = m_aDefaultPasswordHashCreator;
      if (ret == null)
        throw new IllegalStateException ("No default PasswordHashCreator present!");
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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
  public PasswordHash createUserDefaultPasswordHash (@Nullable final IPasswordSalt aSalt,
                                                     @Nonnull final String sPlainTextPassword)
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
                                       .toString ();
  }
}
