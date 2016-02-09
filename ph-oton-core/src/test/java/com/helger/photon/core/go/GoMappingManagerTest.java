/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.go;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.core.mock.PhotonCoreTestRule;

/**
 * Test class for class {@link GoMappingManager}.
 *
 * @author Philip Helger
 */
public final class GoMappingManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonCoreTestRule ();

  @Test
  public void testBasic () throws DAOException
  {
    final GoMappingManager aMgr = new GoMappingManager ("test-go.xml");
    assertEquals (5, aMgr.getItemCount ());
    assertNotNull (aMgr.getItemOfKey ("internal1"));
    assertNotNull (aMgr.getItemOfKey ("internal2"));
    assertNull (aMgr.getItemOfKey ("internal3"));
    assertNotNull (aMgr.getItemOfKey ("external1"));
    assertNotNull (aMgr.getItemOfKey ("external2"));
    assertNotNull (aMgr.getItemOfKey ("external3"));
    assertNull (aMgr.getItemOfKey ("external4"));
    assertEquals ("/MockContext/app?p=internal1", aMgr.getItemOfKey ("internal1").getTargetURLAsString ());
    assertEquals ("/MockContext/app?p=internal2", aMgr.getItemOfKey ("internal2").getTargetURLAsString ());
    assertEquals ("https://joinup.ec.europa.eu/software/cipaedelivery/description",
                  aMgr.getItemOfKey ("external1").getTargetURLAsString ());
    assertEquals ("https://www.google.at", aMgr.getItemOfKey ("external2").getTargetURLAsString ());
    assertEquals ("http://eur-lex.europa.eu/legal-content/DE/TXT/?uri=CELEX%3A32010L0045",
                  aMgr.getItemOfKey ("external3").getTargetURLAsString ());
  }

  @Test
  public void testAdd () throws DAOException
  {
    final GoMappingManager aMgr = new GoMappingManager ("test-go.xml");
    try
    {
      assertEquals (5, aMgr.getItemCount ());
      assertFalse (aMgr.containsItemWithKey ("bla"));
      assertTrue (aMgr.addItem ("BLA", true, "/dummy", true).isChanged ());
      assertEquals (6, aMgr.getItemCount ());
      assertFalse (aMgr.addItem ("BLa", true, "/dummy", true).isChanged ());
      assertFalse (aMgr.addItem ("bla", true, "/dummy", true).isChanged ());
      assertEquals (6, aMgr.getItemCount ());
      assertTrue (aMgr.containsItemWithKey ("bla"));
      assertTrue (aMgr.containsItemWithKey ("BLA"));
      assertNotNull (aMgr.getItemOfKey ("bla"));
      assertNotNull (aMgr.getItemOfKey ("BLA"));
      assertEquals ("/MockContext/dummy", aMgr.getItemOfKey ("bla").getTargetURLAsString ());
    }
    finally
    {
      assertTrue (aMgr.removeItem ("BLA").isChanged ());
      assertFalse (aMgr.removeItem ("BLA").isChanged ());
      assertEquals (5, aMgr.getItemCount ());
    }
  }
}
