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
package com.helger.photon.security.token.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SMap;
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
    final AppToken aAppToken = new AppToken (null, (Map <String, String>) null, "Unit test", "http://junit.org", "Unit Tester", "ut@example.org");
    CommonsTestHelper.testMicroTypeConversion (aAppToken);
    assertTrue (StringHelper.hasText (aAppToken.getActiveTokenString ()));
  }

  @Test
  public void testExistingTokenString ()
  {
    final AppToken aAppToken = new AppToken ("blafootoken",
                                             new SMap ().add ("a", "b").add ("c", "def"),
                                             "Unit test",
                                             "http://junit.org",
                                             "Unit Tester",
                                             "ut@example.org");
    CommonsTestHelper.testMicroTypeConversion (aAppToken);
    assertEquals ("blafootoken", aAppToken.getActiveTokenString ());
  }
}
