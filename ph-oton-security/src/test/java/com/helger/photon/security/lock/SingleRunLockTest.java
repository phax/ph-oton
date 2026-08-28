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
package com.helger.photon.security.lock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for class {@link SingleRunLock}.
 *
 * @author Philip Helger
 */
public final class SingleRunLockTest
{
  @Test
  public void testBasic ()
  {
    final SingleRunLock aLock = new SingleRunLock ("Unit test");
    assertEquals ("Unit test", aLock.getName ());

    // Nothing running
    assertFalse (aLock.isRunning ());
    assertNull (aLock.getStartDateTime ());
    assertNull (aLock.getUserID ());

    // Acquire
    assertTrue (aLock.tryAcquire ("user1"));
    assertTrue (aLock.isRunning ());
    assertNotNull (aLock.getStartDateTime ());
    assertEquals ("user1", aLock.getUserID ());

    // A second acquire must fail and must not alter the state
    assertFalse (aLock.tryAcquire ("user2"));
    assertTrue (aLock.isRunning ());
    assertEquals ("user1", aLock.getUserID ());

    // Release
    aLock.release ();
    assertFalse (aLock.isRunning ());
    assertNull (aLock.getStartDateTime ());
    assertNull (aLock.getUserID ());

    // Reusable afterwards
    assertTrue (aLock.tryAcquire (null));
    assertTrue (aLock.isRunning ());
    assertNull (aLock.getUserID ());
    aLock.release ();
    assertFalse (aLock.isRunning ());
  }

  @Test
  public void testTwoLocksAreIndependent ()
  {
    final SingleRunLock aLock1 = new SingleRunLock ("Lock 1");
    final SingleRunLock aLock2 = new SingleRunLock ("Lock 2");

    assertTrue (aLock1.tryAcquire ("user"));
    assertTrue (aLock2.tryAcquire ("user"));
    assertTrue (aLock1.isRunning ());
    assertTrue (aLock2.isRunning ());

    aLock1.release ();
    assertFalse (aLock1.isRunning ());
    assertTrue (aLock2.isRunning ());
    aLock2.release ();
  }

  @Test
  public void testInvalidName ()
  {
    try
    {
      new SingleRunLock ("");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {
      // expected
    }
  }
}
