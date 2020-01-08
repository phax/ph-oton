/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.grouping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for class {@link HCP}
 *
 * @author Philip Helger
 */
public final class HCPTest
{
  @Test
  public void testCreate ()
  {
    assertFalse (new HCP ().hasChildren ());
  }

  @Test
  public void testSetID ()
  {
    HCP aElement = new HCP ();
    assertNull (aElement.getID ());
    assertNotNull (aElement.ensureID ().getID ());
    assertSame (aElement, aElement.ensureID ());
    try
    {
      assertSame (aElement, aElement.setID ("foo"));
      assertEquals ("foo", aElement.getID ());
    }
    catch (final IllegalStateException ex)
    {
      // ID already set - in debug mode
    }

    aElement = new HCP ();
    assertSame (aElement, aElement.setID ("foo"));
    assertEquals ("foo", aElement.getID ());

    try
    {
      // ID with whitespace is not allowed
      aElement.setID ("ID blank");
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    try
    {
      // ID with whitespace is not allowed
      aElement.setID (" IDblank");
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    try
    {
      // ID with whitespace is not allowed
      aElement.setID ("ID\tblank");
      fail ();
    }
    catch (final IllegalStateException ex)
    {}
    assertEquals ("foo", aElement.getID ());
  }
}
