/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.sysmigration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import com.helger.dao.DAOException;
import com.helger.photon.app.mock.PhotonAppTestRule;

public final class SystemMigrationManagerTest
{
  @Rule
  public final PhotonAppTestRule m_aRule = new PhotonAppTestRule ();

  @Test
  public void testBasicSuccess () throws DAOException
  {
    final SystemMigrationManager aMgr = new SystemMigrationManager (null);
    assertTrue (aMgr.getAllMigrationIDs ().isEmpty ());
    assertFalse (aMgr.wasMigrationExecutedSuccessfully ("mig1"));
    assertTrue (aMgr.getAllMigrationResults ("mig1").isEmpty ());
    assertTrue (aMgr.getAllFailedMigrationResults ("mig1").isEmpty ());

    aMgr.addMigrationResult (SystemMigrationResult.createSuccess ("mig1"));
    assertEquals (1, aMgr.getAllMigrationIDs ().size ());
    assertTrue (aMgr.wasMigrationExecutedSuccessfully ("mig1"));
    assertEquals (1, aMgr.getAllMigrationResults ("mig1").size ());
    assertEquals (0, aMgr.getAllFailedMigrationResults ("mig1").size ());

    aMgr.addMigrationResult (SystemMigrationResult.createFailure ("mig2", "oops"));
    assertEquals (2, aMgr.getAllMigrationIDs ().size ());
    assertTrue (aMgr.wasMigrationExecutedSuccessfully ("mig1"));
    assertFalse (aMgr.wasMigrationExecutedSuccessfully ("mig2"));
    assertEquals (1, aMgr.getAllMigrationResults ("mig1").size ());
    assertEquals (0, aMgr.getAllFailedMigrationResults ("mig1").size ());
    assertEquals (1, aMgr.getAllMigrationResults ("mig2").size ());
    assertEquals (1, aMgr.getAllFailedMigrationResults ("mig2").size ());

    aMgr.addMigrationResult (SystemMigrationResult.createFailure ("mig2", "oops2"));
    assertEquals (2, aMgr.getAllMigrationIDs ().size ());
    assertTrue (aMgr.wasMigrationExecutedSuccessfully ("mig1"));
    assertFalse (aMgr.wasMigrationExecutedSuccessfully ("mig2"));
    assertEquals (1, aMgr.getAllMigrationResults ("mig1").size ());
    assertEquals (0, aMgr.getAllFailedMigrationResults ("mig1").size ());
    assertEquals (2, aMgr.getAllMigrationResults ("mig2").size ());
    assertEquals (2, aMgr.getAllFailedMigrationResults ("mig2").size ());

    aMgr.addMigrationResult (SystemMigrationResult.createSuccess ("mig2"));
    assertEquals (2, aMgr.getAllMigrationIDs ().size ());
    assertTrue (aMgr.wasMigrationExecutedSuccessfully ("mig1"));
    assertTrue (aMgr.wasMigrationExecutedSuccessfully ("mig2"));
    assertEquals (1, aMgr.getAllMigrationResults ("mig1").size ());
    assertEquals (0, aMgr.getAllFailedMigrationResults ("mig1").size ());
    assertEquals (3, aMgr.getAllMigrationResults ("mig2").size ());
    assertEquals (2, aMgr.getAllFailedMigrationResults ("mig2").size ());
  }
}
