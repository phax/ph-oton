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
package com.helger.photon.core.csrf;

import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * Global CSRF manager keeping track of the available nonces.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class CSRFManager extends AbstractGlobalWebSingleton
{
  public static final int NONCE_BYTES = 64;

  private static final Logger LOGGER = LoggerFactory.getLogger (CSRFManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <String> m_aNonces = new CommonsHashSet <> ();

  @Deprecated
  @UsedViaReflection
  public CSRFManager ()
  {}

  @Nonnull
  public static CSRFManager getInstance ()
  {
    return getGlobalSingleton (CSRFManager.class);
  }

  @Nullable
  public static CSRFManager getInstanceIfInstantiated ()
  {
    return getGlobalSingletonIfInstantiated (CSRFManager.class);
  }

  @Nonnull
  @Nonempty
  public String createNewNonce ()
  {
    return m_aRWLock.writeLocked ( () -> {
      String sNonce;
      int nCount = 0;
      final Random aRandom = new Random ();
      do
      {
        // Ensure a unique nonce
        final byte [] aNonce = new byte [NONCE_BYTES];
        aRandom.nextBytes (aNonce);
        sNonce = StringHelper.getHexEncoded (aNonce);

        // Avoid endless loop
        if (++nCount > 100)
          throw new IllegalStateException ("Failed to create a unique nonce after " + nCount + " tries!");
      } while (!m_aNonces.add (sNonce));
      return sNonce;
    });
  }

  public void removeNonce (@Nonnull @Nonempty final String sNonce)
  {
    ValueEnforcer.notEmpty (sNonce, "Nonce");

    m_aRWLock.writeLocked ( () -> {
      if (!m_aNonces.remove (sNonce))
        LOGGER.error ("Failed to remove nonce '" + sNonce + "'");
    });
  }

  public boolean isValidNonce (@Nullable final String sNonce)
  {
    if (StringHelper.hasNoText (sNonce))
      return false;

    return m_aRWLock.readLocked ( () -> m_aNonces.contains (sNonce));
  }

  @Nonnegative
  public int getNonceCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aNonces.size ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllNonces ()
  {
    return m_aRWLock.readLocked ( () -> m_aNonces.getClone ());
  }
}
