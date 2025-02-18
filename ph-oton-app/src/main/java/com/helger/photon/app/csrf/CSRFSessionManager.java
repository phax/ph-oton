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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.scope.IScope;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * Per-session nonce manager.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class CSRFSessionManager extends AbstractSessionWebSingleton
{
  @GuardedBy ("m_aRWLock")
  private String m_sNonce;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public CSRFSessionManager ()
  {
    // Create the nonce per session
    m_sNonce = CSRFManager.getInstance ().createNewNonce ();
  }

  @Nonnull
  public static CSRFSessionManager getInstance ()
  {
    return getSessionSingleton (CSRFSessionManager.class);
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    // Remove the nonce to avoid it is reused
    m_aRWLock.writeLocked ( () -> {
      // May be null on global shutdown
      final CSRFManager aMgr = CSRFManager.getInstanceIfInstantiated ();
      if (aMgr != null)
        aMgr.removeNonce (m_sNonce);
      m_sNonce = null;
    });
  }

  /**
   * @return The nonce of this session.
   */
  @Nonnull
  @Nonempty
  public String getNonce ()
  {
    return m_aRWLock.readLockedGet ( () -> m_sNonce);
  }

  /**
   * Check if the passed nonce is the expected one for this session.
   *
   * @param sNonceToCheck
   *        The nonce to be checked. May be <code>null</code>.
   * @return <code>true</code> if the nonce is as expected, <code>false</code>
   *         otherwise.
   */
  public boolean isExpectedNonce (@Nullable final String sNonceToCheck)
  {
    final String sThisNonce = getNonce ();
    return StringHelper.hasText (sThisNonce) &&
           sThisNonce.equals (sNonceToCheck) &&
           CSRFManager.getInstance ().isValidNonce (sNonceToCheck);
  }

  /**
   * Generate a new nonce for this session.
   */
  public void generateNewNonce ()
  {
    final CSRFManager aCSRFMgr = CSRFManager.getInstance ();
    m_aRWLock.writeLocked ( () -> {
      aCSRFMgr.removeNonce (m_sNonce);
      m_sNonce = aCSRFMgr.createNewNonce ();
    });
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Nonce", m_sNonce).getToString ();
  }
}
