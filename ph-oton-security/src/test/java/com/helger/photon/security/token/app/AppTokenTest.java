package com.helger.photon.security.token.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.photon.basic.mock.PhotonBasicTestRule;

/**
 * Test class for class {@link AppToken}.
 *
 * @author Philip Helger
 */
public final class AppTokenTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicTestRule ();

  @Test
  public void testBasic ()
  {
    final AppToken aAppToken = new AppToken ("Unit test", "http://junit.org", "Unit Tester", "ut@example.org");
    CommonsTestHelper.testMicroTypeConversion (aAppToken);
  }
}
