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
package com.helger.photon.security.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.state.EChange;
import com.helger.photon.app.mock.PhotonAppWebTestRule;

/**
 * Test class for class {@link LoginThrottlePerIP}
 *
 * @author Philip Helger
 */
public final class LoginThrottlePerIPTest
{
  private static final String IP1 = "192.168.0.1";
  private static final String IP2 = "192.168.0.2";

  // A fresh global scope - and therefore a fresh throttle - per test
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Test
  public void testDefaults ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    assertEquals (LoginThrottlePerIP.DEFAULT_TIME_TO_LIVE, aThrottle.getTimeToLive ());
    assertEquals (0, aThrottle.getFailedLoginCount (IP1));
    assertEquals (0, aThrottle.getFailedLoginCount (null));
  }

  @Test
  public void testFailedLoginIncrements ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();

    assertEquals (1, aThrottle.onFailedLogin (IP1));
    assertEquals (1, aThrottle.getFailedLoginCount (IP1));
    assertEquals (2, aThrottle.onFailedLogin (IP1));
    assertEquals (3, aThrottle.onFailedLogin (IP1));
    assertEquals (3, aThrottle.getFailedLoginCount (IP1));

    // A different IP is counted independently
    assertEquals (1, aThrottle.onFailedLogin (IP2));
    assertEquals (3, aThrottle.getFailedLoginCount (IP1));
  }

  @Test
  public void testSuccessfulLoginRemovesEntry ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();

    aThrottle.onFailedLogin (IP1);
    aThrottle.onFailedLogin (IP1);
    assertEquals (2, aThrottle.getFailedLoginCount (IP1));

    assertTrue (aThrottle.onSuccessfulLogin (IP1).isChanged ());
    assertEquals (0, aThrottle.getFailedLoginCount (IP1));

    // Removing again does nothing
    assertTrue (aThrottle.onSuccessfulLogin (IP1).isUnchanged ());
    // Removing a null IP does nothing
    assertEquals (EChange.UNCHANGED, aThrottle.onSuccessfulLogin (null));

    // After reset the counter starts from 1 again
    assertEquals (1, aThrottle.onFailedLogin (IP1));
  }

  @Test
  public void testChangingTimeToLiveClearsCounters ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    aThrottle.onFailedLogin (IP1);
    assertEquals (1, aThrottle.getFailedLoginCount (IP1));

    aThrottle.setTimeToLive (Duration.ofMinutes (30));
    assertEquals (Duration.ofMinutes (30), aThrottle.getTimeToLive ());
    // Changing the TTL discards all held counters
    assertEquals (0, aThrottle.getFailedLoginCount (IP1));
  }

  @Test
  public void testClear ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    aThrottle.onFailedLogin (IP1);
    aThrottle.onFailedLogin (IP2);

    aThrottle.clear ();
    assertEquals (0, aThrottle.getFailedLoginCount (IP1));
    assertEquals (0, aThrottle.getFailedLoginCount (IP2));
  }

  @Test
  public void testExpiration ()
  {
    // Zero/negative TTL disables expiration - the counter never expires
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    aThrottle.setTimeToLive (Duration.ZERO);
    aThrottle.onFailedLogin (IP1);
    assertFalse (aThrottle.getFailedLoginCount (IP1) == 0);
  }
}
