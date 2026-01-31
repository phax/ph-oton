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
package com.helger.photon.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.collection.helper.CollectionHelperExt;
import com.helger.datetime.helper.PDTFactory;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.security.password.salt.PasswordSalt;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * Test class for class {@link User}.
 *
 * @author Philip Helger
 */
public final class UserTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @Test
  public void testBasic ()
  {
    final User aUser = new User (StubObject.createForCurrentUserAndID ("id1"),
                                 "MyName",
                                 "me@example.org",
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (PasswordSalt.createRandom (),
                                                                                       "ABCDEF"),
                                 "Philip",
                                 "Helger",
                                 "bla",
                                 Locale.GERMANY,
                                 PDTFactory.getCurrentLocalDateTime (),
                                 0,
                                 0,
                                 false);
    assertEquals ("id1", aUser.getID ());
    assertEquals ("me@example.org", aUser.getEmailAddress ());
    assertNotNull (aUser.getPasswordHash ());
    assertEquals ("Philip", aUser.getFirstName ());
    assertEquals ("Helger", aUser.getLastName ());
    assertEquals ("bla", aUser.getDescription ());
    assertEquals (Locale.GERMANY, aUser.getDesiredLocale ());
    assertEquals (0, aUser.getLoginCount ());
    assertEquals (0, aUser.getConsecutiveFailedLoginCount ());
    assertNotNull (aUser.getCreationDateTime ());
    assertNull (aUser.getLastModificationDateTime ());
    assertNull (aUser.getDeletionDateTime ());
    assertFalse (aUser.isDeleted ());
    assertFalse (aUser.isDisabled ());
    assertTrue (aUser.isEnabled ());
  }

  @Test
  public void testBasic2 ()
  {
    final User aUser = new User (StubObject.createForCurrentUserAndID ("id1"),
                                 "MyName",
                                 null,
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (PasswordSalt.createRandom (),
                                                                                       "ABCDEF"),
                                 null,
                                 null,
                                 null,
                                 Locale.GERMANY,
                                 PDTFactory.getCurrentLocalDateTime (),
                                 0,
                                 0,
                                 false);
    assertEquals ("id1", aUser.getID ());
    assertNull (aUser.getEmailAddress ());
    assertNotNull (aUser.getPasswordHash ());
    assertNull (aUser.getFirstName ());
    assertNull (aUser.getLastName ());
    assertNull (aUser.getDescription ());
    assertEquals (Locale.GERMANY, aUser.getDesiredLocale ());
    assertEquals (0, aUser.getLoginCount ());
    assertEquals (0, aUser.getConsecutiveFailedLoginCount ());
    assertNotNull (aUser.getCreationDateTime ());
    assertNull (aUser.getLastModificationDateTime ());
    assertNull (aUser.getDeletionDateTime ());
    assertFalse (aUser.isDeleted ());
    assertFalse (aUser.isDisabled ());
    assertTrue (aUser.isEnabled ());

    // To XML
    final IMicroElement aElement = MicroTypeConverter.convertToMicroElement (aUser, "user");
    assertNotNull (aElement);

    // From XML
    final User aUser2 = MicroTypeConverter.convertToNative (aElement, User.class);
    assertNotNull (aUser2);
    assertEquals (aUser, aUser2);
  }

  @Test
  public void testMicroConversion ()
  {
    final User aUser = new User (StubObject.createForCurrentUserAndID ("id1",
                                                                       CollectionHelperExt.createMap ("locale",
                                                                                                      "de_DE")),
                                 "MyName",
                                 "me@example.org",
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (PasswordSalt.createRandom (),
                                                                                       "ABCDEF"),
                                 "Philip",
                                 "Helger",
                                 "bla",
                                 Locale.GERMANY,
                                 PDTFactory.getCurrentLocalDateTime (),
                                 0,
                                 0,
                                 false);

    // To XML
    final IMicroElement aElement = MicroTypeConverter.convertToMicroElement (aUser, "user");
    assertNotNull (aElement);

    // From XML
    final User aUser2 = MicroTypeConverter.convertToNative (aElement, User.class);
    assertNotNull (aUser2);
    assertEquals ("id1", aUser2.getID ());
    assertEquals ("me@example.org", aUser2.getEmailAddress ());
    assertNotNull (aUser2.getPasswordHash ());
    assertEquals ("Philip", aUser2.getFirstName ());
    assertEquals ("Helger", aUser2.getLastName ());
    assertEquals ("bla", aUser2.getDescription ());
    assertEquals (Locale.GERMANY, aUser2.getDesiredLocale ());
    assertEquals (1, aUser2.attrs ().size ());
    assertEquals ("de_DE", aUser2.attrs ().getAsString ("locale"));
  }
}
