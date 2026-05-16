/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.totp;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;

/**
 * Default replay-protection scheme. Stores the last accepted TOTP counter on the
 * {@link UserTotpRecord} and rejects any counter less-than-or-equal to it. Sufficient for
 * single-node deployments; replace via
 * {@link UserTotpService#setReplayCallback(IUserTotpReplayCallback)} for multi-node setups that
 * need a shared anti-replay cache.
 *
 * @author Philip Helger
 */
public final class DefaultUserTotpReplayCallback implements IUserTotpReplayCallback
{
  private final IUserTotpManager m_aManager;

  public DefaultUserTotpReplayCallback (@NonNull final IUserTotpManager aManager)
  {
    ValueEnforcer.notNull (aManager, "Manager");
    m_aManager = aManager;
  }

  public boolean acceptAndRecord (@NonNull @Nonempty final String sUserID, final long nMatchedCounter)
  {
    final UserTotpRecord aRecord = m_aManager.getRecordOfUserID (sUserID);
    if (aRecord == null)
      return false;
    if (nMatchedCounter <= aRecord.getLastAcceptedCounter ())
      return false;
    m_aManager.setLastAcceptedCounter (sUserID, nMatchedCounter);
    return true;
  }
}
