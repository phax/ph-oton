/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.security.password.constraint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;

/**
 * Test class for class {@link PasswordConstraintList}.
 *
 * @author Philip Helger
 */
public final class PasswordConstraintListTest
{
  @Test
  public void testBasic ()
  {
    PasswordConstraintList aPCL = new PasswordConstraintList ();
    assertFalse (aPCL.hasConstraints ());
    assertEquals (0, aPCL.getConstraintCount ());
    assertTrue (aPCL.isPasswordValid (""));
    assertTrue (aPCL.isPasswordValid ("abc"));
    assertTrue (aPCL.isPasswordValid ("123456789"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3));
    assertTrue (aPCL.hasConstraints ());
    assertEquals (1, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertTrue (aPCL.isPasswordValid ("abc"));
    assertTrue (aPCL.isPasswordValid ("123456789"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3), new PasswordConstraintMaxLength (9));
    assertTrue (aPCL.hasConstraints ());
    assertEquals (2, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertTrue (aPCL.isPasswordValid ("abc"));
    assertTrue (aPCL.isPasswordValid ("123456789"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3), new PasswordConstraintMustContainDigit (1));
    assertTrue (aPCL.hasConstraints ());
    assertEquals (2, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertFalse (aPCL.isPasswordValid ("abc"));
    assertTrue (aPCL.isPasswordValid ("123456789"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3),
                                       new PasswordConstraintMustContainDigit (1),
                                       new PasswordConstraintMustContainLetter (1));
    CommonsTestHelper.testGetClone (aPCL);
    assertTrue (aPCL.hasConstraints ());
    assertEquals (3, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertFalse (aPCL.isPasswordValid ("abc"));
    assertFalse (aPCL.isPasswordValid ("123456789"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3),
                                       new PasswordConstraintMustContainDigit (1),
                                       new PasswordConstraintMustContainLetterLowerCase (1));
    CommonsTestHelper.testGetClone (aPCL);
    assertTrue (aPCL.hasConstraints ());
    assertEquals (3, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertFalse (aPCL.isPasswordValid ("abc"));
    assertFalse (aPCL.isPasswordValid ("123456789"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertTrue (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));

    aPCL = new PasswordConstraintList (new PasswordConstraintMinLength (3),
                                       new PasswordConstraintMustContainDigit (1),
                                       new PasswordConstraintMustContainLetterUpperCase (1));
    CommonsTestHelper.testGetClone (aPCL);
    assertTrue (aPCL.hasConstraints ());
    assertEquals (3, aPCL.getConstraintCount ());
    assertFalse (aPCL.isPasswordValid (""));
    assertFalse (aPCL.isPasswordValid ("abc"));
    assertFalse (aPCL.isPasswordValid ("123456789"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzeliefimschnee"));
    assertFalse (aPCL.isPasswordValid ("abcdiekatzelief2014nochimmerimschnee"));
  }
}
