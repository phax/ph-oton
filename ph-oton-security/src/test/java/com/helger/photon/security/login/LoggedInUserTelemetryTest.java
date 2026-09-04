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

import org.jspecify.annotations.NonNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.CSecurityTelemetry;
import com.helger.photon.security.login.RecordingTelemetryMeterSPI.Measurement;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;

/**
 * Test class for the ph-telemetry integration of {@link LoggedInUserManager}.
 *
 * @author Philip Helger
 */
public final class LoggedInUserTelemetryTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Before
  public void clearRecordings ()
  {
    RecordingTelemetryMeterSPI.clearRecordings ();
    PhotonSecurityManager.getUserMgr ().createDefaultsForTest ();
  }

  @NonNull
  private static ICommonsList <Measurement> _getFailures ()
  {
    return RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGIN_FAILED);
  }

  private static void _assertSingleFailure (@NonNull final ELoginResult eExpected)
  {
    final ICommonsList <Measurement> aFailures = _getFailures ();
    assertEquals (1, aFailures.size ());
    final Measurement aFailure = aFailures.getFirstOrNull ();
    assertNotNull (aFailure);
    assertEquals (1, (int) aFailure.dValue ());
    // The login result name is the only attribute - no user ID, no login name, no IP address
    assertEquals (eExpected.name (), aFailure.aAttrs ().get (CSecurityTelemetry.ATTR_LOGIN_RESULT));
    assertEquals (1, aFailure.aAttrs ().size ());
  }

  @Test
  public void testUserNotExistingByLoginName ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.USER_NOT_EXISTING, aUM.loginUser ("no-such-login-name", "mypw"));
    // This path does not go through _onLoginError, so it needs its own increment
    _assertSingleFailure (ELoginResult.USER_NOT_EXISTING);
  }

  @Test
  public void testUserNotExistingByNullUser ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.USER_NOT_EXISTING, aUM.loginUser ((IUser) null, "mypw", null));
    _assertSingleFailure (ELoginResult.USER_NOT_EXISTING);
  }

  @Test
  public void testInvalidPassword ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.INVALID_PASSWORD,
                  aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, "definitely-the-wrong-password"));
    _assertSingleFailure (ELoginResult.INVALID_PASSWORD);
  }

  @Test
  public void testMissingRole ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.USER_IS_MISSING_ROLE,
                  aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN,
                                 CSecurity.USER_ADMINISTRATOR_PASSWORD,
                                 new CommonsArrayList <> ("no-such-role")));
    _assertSingleFailure (ELoginResult.USER_IS_MISSING_ROLE);
  }

  @Test
  public void testSessionAlreadyHasUser ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.SUCCESS,
                  aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));
    RecordingTelemetryMeterSPI.clearRecordings ();

    assertEquals (ELoginResult.SESSION_ALREADY_HAS_USER,
                  aUM.loginUser (CSecurity.USER_USER_LOGIN, CSecurity.USER_USER_PASSWORD));
    _assertSingleFailure (ELoginResult.SESSION_ALREADY_HAS_USER);
  }

  @Test
  public void testSuccessAndLogout ()
  {
    final LoggedInUserManager aUM = LoggedInUserManager.getInstance ();
    assertEquals (ELoginResult.SUCCESS,
                  aUM.loginUser (CSecurity.USER_ADMINISTRATOR_LOGIN, CSecurity.USER_ADMINISTRATOR_PASSWORD));

    final ICommonsList <Measurement> aSuccess = RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGIN_SUCCESS);
    assertEquals (1, aSuccess.size ());
    assertEquals (ELoginResult.SUCCESS.name (),
                  aSuccess.getFirstOrNull ().aAttrs ().get (CSecurityTelemetry.ATTR_LOGIN_RESULT));
    assertTrue (_getFailures ().isEmpty ());

    // The gauge observes the real number of logged in users
    final LongSupplier aGauge = RecordingTelemetryMeterSPI.getGaugeSupplier (CSecurityTelemetry.METRIC_USERS_LOGGED_IN);
    assertNotNull (aGauge);
    assertEquals (1, aGauge.getAsLong ());

    // A logout of a user that is not logged in must not be counted
    assertTrue (aUM.logoutUser (CSecurity.USER_USER_ID).isUnchanged ());
    assertTrue (RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGOUT).isEmpty ());

    // The real logout is counted, including the session duration
    assertTrue (aUM.logoutUser (CSecurity.USER_ADMINISTRATOR_ID).isChanged ());
    assertEquals (1, RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_LOGOUT).size ());
    assertEquals (1, RecordingTelemetryMeterSPI.getMeasurements (CSecurityTelemetry.METRIC_SESSION_DURATION).size ());
    assertEquals (0, aGauge.getAsLong ());
  }
}
