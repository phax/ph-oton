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
package com.helger.photon.app.csrf;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.codec.base64.Base64;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.ICommonsSet;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Global CSRF manager keeping track of the available nonces. All nonces are provided as Base64
 * encoded strings.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class CSRFManager extends AbstractGlobalWebSingleton
{
  public static final int NONCE_BYTES = 32;

  private static final Logger LOGGER = LoggerFactory.getLogger (CSRFManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsSet <String> m_aNoncesBase64 = new CommonsHashSet <> ();

  @Deprecated (forRemoval = false)
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

  /**
   * Create a new unique nonce with {@link #NONCE_BYTES} bytes and return the response as Base64
   * encoded String.
   *
   * @return A new Base64 encoded nonce string.
   */
  @Nonnull
  @Nonempty
  public String createNewNonce ()
  {
    return m_aRWLock.writeLockedGet ( () -> {
      String sNonce;
      int nCount = 0;
      final Random aRandom = ThreadLocalRandom.current ();
      do
      {
        // Ensure a unique nonce
        final byte [] aNonce = new byte [NONCE_BYTES];
        aRandom.nextBytes (aNonce);
        // Must be Base64 encoded for HTML
        sNonce = Base64.encodeBytes (aNonce);

        // Avoid endless loop
        if (++nCount > 100)
          throw new IllegalStateException ("Failed to create a unique nonce after " + nCount + " tries!");
      } while (!m_aNoncesBase64.add (sNonce));
      return sNonce;
    });
  }

  public void removeNonce (@Nonnull @Nonempty final String sNonce)
  {
    ValueEnforcer.notEmpty (sNonce, "Nonce");

    m_aRWLock.writeLocked ( () -> {
      if (!m_aNoncesBase64.remove (sNonce))
        LOGGER.error ("Failed to remove nonce '" + sNonce + "'");
    });
  }

  public boolean isValidNonce (@Nullable final String sNonce)
  {
    if (StringHelper.isEmpty (sNonce))
      return false;

    return m_aRWLock.readLockedBoolean ( () -> m_aNoncesBase64.contains (sNonce));
  }

  @Nonnegative
  public int getNonceCount ()
  {
    return m_aRWLock.readLockedInt (m_aNoncesBase64::size);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllNonces ()
  {
    return m_aRWLock.readLockedGet (m_aNoncesBase64::getClone);
  }
}
