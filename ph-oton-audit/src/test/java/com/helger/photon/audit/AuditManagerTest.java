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
package com.helger.photon.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.junit.Rule;
import org.junit.Test;

import com.helger.base.state.ESuccess;
import com.helger.datetime.helper.PDTFactory;
import com.helger.io.file.FileOperations;
import com.helger.photon.audit.mock.MockCurrentUserIDProvider;
import com.helger.photon.io.WebFileIO;
import com.helger.photon.io.mock.PhotonIOTestRule;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * Test class for class {@link AuditManager}.
 *
 * @author Philip Helger
 */
public final class AuditManagerTest
{
  private static final String BASE_DIR = "audit-range-test/";

  @Rule
  public final PhotonIOTestRule m_aRule = new PhotonIOTestRule (new File ("target/junittest-audit").getAbsoluteFile ());

  private static void _writeFile (@NonNull final LocalDate aFileDate, @NonNull final IAuditItem... aItems)
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.addElement (AuditManager.ELEMENT_ITEMS);
    for (final IAuditItem aItem : aItems)
      eRoot.addChild (MicroTypeConverter.convertToMicroElement (aItem, AuditManager.ELEMENT_ITEM));
    final File aFile = WebFileIO.getDataIO ().getFile (BASE_DIR + AuditManager.getRelativeAuditFilename (aFileDate));
    aFile.getParentFile ().mkdirs ();
    assertTrue (MicroWriter.writeToFile (aDoc, aFile).isSuccess ());
  }

  @NonNull
  private static IAuditItem _item (@NonNull final LocalDateTime aDT, @NonNull final String sAction)
  {
    return new AuditItem (aDT, "user", EAuditActionType.EXECUTE, ESuccess.SUCCESS, sAction);
  }

  @Test
  public void testGetAllAuditItemsOfDateRange () throws Exception
  {
    final LocalDate aDay1 = LocalDate.of (2020, Month.FEBRUARY, 28);
    final LocalDate aDay2 = aDay1.plusDays (1);
    final LocalDate aDay3 = aDay2.plusDays (1);
    final LocalDate aDay4 = aDay3.plusDays (1);

    FileOperations.deleteDirRecursiveIfExisting (WebFileIO.getDataIO ().getFile (BASE_DIR));
    _writeFile (aDay1, _item (aDay1.atTime (10, 0), "d1"));
    // The last item of day 2 was written to the file of day 3
    _writeFile (aDay2, _item (aDay2.atTime (12, 0), "d2a"));
    _writeFile (aDay3, _item (aDay2.atTime (23, 59, 59), "d2b"), _item (aDay3.atTime (8, 0), "d3"));
    _writeFile (aDay4, _item (aDay4.atTime (9, 0), "d4"));

    final AuditManager aMgr = new AuditManager (BASE_DIR, new MockCurrentUserIDProvider ("userid"));
    try
    {
      List <IAuditItem> aItems = aMgr.getAllAuditItemsOfDateRange (aDay2, aDay2);
      assertNotNull (aItems);
      assertEquals (2, aItems.size ());
      assertEquals ("d2a", aItems.get (0).getAction ());
      assertEquals ("d2b", aItems.get (1).getAction ());

      aItems = aMgr.getAllAuditItemsOfDateRange (aDay1, aDay3);
      assertEquals (4, aItems.size ());
      assertEquals ("d1", aItems.get (0).getAction ());
      assertEquals ("d3", aItems.get (3).getAction ());

      // Nothing before the first file
      assertTrue (aMgr.getAllAuditItemsOfDateRange (aDay1.minusDays (5), aDay1.minusDays (1)).isEmpty ());

      // Day without file
      assertNull (aMgr.getAllAuditItemsOfDate (aDay4.plusDays (1)));
    }
    finally
    {
      aMgr.stop ();
    }
  }

  @Test
  public void testDoNothing ()
  {
    final IAuditManager aMgr = new DoNothingAuditManager ();
    final LocalDate aNow = PDTFactory.getCurrentLocalDate ();
    assertNotNull (aMgr.getAllAuditItemsOfDateRange (aNow, aNow));
    assertTrue (aMgr.getAllAuditItemsOfDateRange (aNow, aNow).isEmpty ());
  }
}
