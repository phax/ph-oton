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
package com.helger.photon.basic.security.lock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.helger.photon.basic.mock.MockCurrentUserIDProvider;
import com.helger.photon.basic.security.lock.DefaultLockManager;
import com.helger.photon.basic.security.lock.LockResult;

/**
 * Test class for class {@link DefaultLockManager}.
 * 
 * @author Philip Helger
 */
public final class DefaultLockManagerTest
{
  @Test
  public void testBasicLocking ()
  {
    final String sUser1 = "user1";
    final String sObjID1 = "obj1";
    final DefaultLockManager <String> aLM = new DefaultLockManager <String> (MockCurrentUserIDProvider.getInstance ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Lock object
    assertTrue (aLM.lockObject (sObjID1).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Lock object again
    assertTrue (aLM.lockObject (sObjID1).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Try to lock object with other user - failure
    assertFalse (aLM.lockObject (sObjID1, sUser1).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Unlock object
    assertTrue (aLM.unlockObject (sObjID1).isChanged ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Unlock object again
    assertTrue (aLM.unlockObject (sObjID1).isUnchanged ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Try to lock object with other user - success
    assertTrue (aLM.lockObject (sObjID1, sUser1).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertTrue (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).size () == 1);

    // Try to lock object with other user again - success
    assertTrue (aLM.lockObject (sObjID1, sUser1).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertTrue (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).size () == 1);

    // Unlock object using current user - failure
    assertTrue (aLM.unlockObject (sObjID1).isUnchanged ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertTrue (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).size () == 1);

    // Unlock object with explicit user - success
    assertTrue (aLM.unlockObject (sUser1, sObjID1).isChanged ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());

    // Unlock object with explicit user - failure
    assertTrue (aLM.unlockObject (sUser1, sObjID1).isUnchanged ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfUser (sUser1).isEmpty ());
  }

  @Test
  public void testMultipleObjectLocking ()
  {
    final String sObjID1 = "obj1";
    final String sObjID2 = "obj2";
    final String sObjID3 = "obj3";
    final DefaultLockManager <String> aLM = new DefaultLockManager <String> (MockCurrentUserIDProvider.getInstance ());
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());

    // Lock objects
    assertTrue (aLM.lockObject (sObjID1).isLocked ());
    assertTrue (aLM.lockObject (sObjID2).isLocked ());
    assertTrue (aLM.lockObject (sObjID3).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID2));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID2));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID2));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID3));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID3));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID3));
    assertTrue (aLM.getAllLockedObjects ().size () == 3);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 3);

    // Lock object again
    assertTrue (aLM.lockObject (sObjID1).isLocked ());
    assertTrue (aLM.lockObject (sObjID2).isLocked ());
    assertTrue (aLM.lockObject (sObjID3).isLocked ());
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID2));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID2));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID2));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID3));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID3));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID3));
    assertTrue (aLM.getAllLockedObjects ().size () == 3);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 3);

    // Unlock object 2
    assertTrue (aLM.unlockObject (sObjID2).isChanged ());
    assertTrue (aLM.getAllLockedObjects ().size () == 2);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 2);
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID1));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID2));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID2));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID2));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID3));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID3));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID3));

    // Lock object 2 and unlock all other objects
    final LockResult <String> aResult = aLM.lockObjectAndUnlockAllOthers (sObjID2);
    assertNotNull (aResult);
    assertEquals (sObjID2, aResult.getLockedObjectID ());
    assertTrue (aResult.isLocked ());
    assertTrue (aResult.isNewLock ());
    assertEquals (2, aResult.getUnlockedObjects ().size ());
    assertTrue (aResult.getUnlockedObjects ().contains (sObjID1));
    assertTrue (aResult.getUnlockedObjects ().contains (sObjID3));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertTrue (aLM.isObjectLockedByAnyUser (sObjID2));
    assertTrue (aLM.isObjectLockedByCurrentUser (sObjID2));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID2));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID3));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID3));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID3));
    assertTrue (aLM.getAllLockedObjects ().size () == 1);
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().size () == 1);

    // Unlock all objects (= obj2)
    final List <String> aUnlockedObjects = aLM.unlockAllObjectsOfCurrentUser ();
    assertNotNull (aUnlockedObjects);
    assertEquals (1, aUnlockedObjects.size ());
    assertTrue (aUnlockedObjects.contains (sObjID2));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID1));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID1));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID1));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID2));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID2));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID2));
    assertFalse (aLM.isObjectLockedByAnyUser (sObjID3));
    assertFalse (aLM.isObjectLockedByCurrentUser (sObjID3));
    assertFalse (aLM.isObjectLockedByOtherUser (sObjID3));
    assertTrue (aLM.getAllLockedObjects ().isEmpty ());
    assertTrue (aLM.getAllLockedObjectsOfCurrentUser ().isEmpty ());
  }
}
