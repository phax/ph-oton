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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.LongSupplier;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.collection.commons.ICommonsList;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurityTelemetry;
import com.helger.photon.security.login.RecordingTelemetryMeterSPI.Measurement;

/**
 * Test class for the ph-telemetry integration of {@link LoginThrottlePerIP}.
 *
 * @author Philip Helger
 */
public final class LoginThrottleTelemetryTest
{
  private static final String IP = "1.2.3.4";

  // A fresh global scope - and therefore a fresh throttle - per test
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Before
  public void clearRecordings ()
  {
    RecordingTelemetryMeterSPI.clearRecordings ();
  }

  @Test
  public void testFailedLoginIsCounted ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    assertEquals (1, aThrottle.onFailedLogin (IP));
    assertEquals (2, aThrottle.onFailedLogin (IP));
    assertEquals (1, aThrottle.onFailedLogin ("5.6.7.8"));

    final ICommonsList <Measurement> aFailed = RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_FAILED);
    assertEquals (3, aFailed.size ());
    for (final Measurement aMeasurement : aFailed)
    {
      assertEquals (1, (int) aMeasurement.dValue ());
      // The IP address is unbounded and personal data - it must never be a metric attribute
      assertTrue (aMeasurement.aAttrs ().isEmpty ());
    }
  }

  @Test
  public void testSuccessfulLoginIsNotCounted ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();
    aThrottle.onFailedLogin (IP);
    RecordingTelemetryMeterSPI.clearRecordings ();

    assertTrue (aThrottle.onSuccessfulLogin (IP).isChanged ());
    assertTrue (RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_FAILED).isEmpty ());
  }

  @Test
  public void testTrackedIPsGauge ()
  {
    final LoginThrottlePerIP aThrottle = LoginThrottlePerIP.getInstance ();

    // The gauge is created when the global singleton is instantiated
    final LongSupplier aGauge = RecordingTelemetryMeterSPI.getGaugeSupplier (CSecurityTelemetry.METRIC_LOGIN_THROTTLE_TRACKED_IPS);
    assertNotNull (aGauge);
    assertEquals (0, aGauge.getAsLong ());

    // Two failures of the same IP are one tracked IP
    aThrottle.onFailedLogin (IP);
    aThrottle.onFailedLogin (IP);
    assertEquals (1, aGauge.getAsLong ());

    aThrottle.onFailedLogin ("5.6.7.8");
    assertEquals (2, aGauge.getAsLong ());

    // A successful login removes the counter of that IP again
    assertTrue (aThrottle.onSuccessfulLogin (IP).isChanged ());
    assertEquals (1, aGauge.getAsLong ());
  }
}
