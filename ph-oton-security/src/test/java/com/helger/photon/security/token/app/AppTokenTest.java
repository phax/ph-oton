package com.helger.photon.security.token.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;

/**
 * Test class for class {@link AppToken}.
 *
 * @author Philip Helger
 */
public final class AppTokenTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testNewTokenString ()
  {
    final AppToken aAppToken = new AppToken ("Unit test", "http://junit.org", "Unit Tester", "ut@example.org", null);
    CommonsTestHelper.testMicroTypeConversion (aAppToken);
    assertTrue (StringHelper.hasText (aAppToken.getActiveTokenString ()));
  }

  @Test
  public void testExistingTokenString ()
  {
    final AppToken aAppToken = new AppToken ("Unit test", "http://junit.org", "Unit Tester", "ut@example.org", "blafootoken");
    CommonsTestHelper.testMicroTypeConversion (aAppToken);
    assertEquals ("blafootoken", aAppToken.getActiveTokenString ());
  }
}
