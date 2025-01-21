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
package com.helger.html.hc.html.tabular;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.junit.DebugModeTestRule;
import com.helger.html.hc.render.HCRenderer;

/**
 * Test class for class {@link HCTable}.
 *
 * @author Philip Helger
 */
public final class HCTableTest
{
  @Rule
  public final TestRule m_aRule = new DebugModeTestRule ();

  @Test
  public void testConsistencySimple ()
  {
    // Simplest version
    HCTable aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencySimple1");
    aTable.addHeaderRow ().addCells ("h1", "h2");
    aTable.addFooterRow ().addCells ("f1", "f2");
    aTable.addBodyRow ().addCells ("a", "b");
    aTable.addBodyRow ().addCells ("c", "d");
    assertNotNull (HCRenderer.getAsNode (aTable));

    aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencySimple2");
    aTable.addBodyRow ().addCells ("a", "b");
    aTable.addBodyRow ().addCells ("c", "d");
    assertNotNull (HCRenderer.getAsNode (aTable));
  }

  @Test
  public void testConsistencyColspan ()
  {
    // Use colspan=2
    final HCTable aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyColspan1");
    aTable.addHeaderRow ().addAndReturnCell ("h1").setColspan (2);
    aTable.addFooterRow ().addAndReturnCell ("f1").setColspan (2);
    aTable.addBodyRow ().addAndReturnCell ("a").setColspan (2);
    aTable.addBodyRow ().addCells ("c", "d");
    assertNotNull (HCRenderer.getAsNode (aTable));
  }

  @Test
  public void testConsistencyRowspan ()
  {
    // Use rowspan=2 on first column
    HCTable aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan1");
    aTable.addHeaderRow ().addCells ("h1", "h2");
    aTable.addFooterRow ().addCells ("f1", "f2");
    // row 1
    HCRow aRow = aTable.addBodyRow ();
    aRow.addAndReturnCell ("a").setRowspan (2);
    aRow.addCell ("b");
    // row 2
    aTable.addBodyRow ().addCell ("d");
    // row 3
    aTable.addBodyRow ().addCells ("e", "f");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=3 on first column
    aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan2");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addAndReturnCell ("a").setRowspan (3);
    aRow.addCell ("b");
    // row 2
    aTable.addBodyRow ().addCell ("d");
    // row 3
    aTable.addBodyRow ().addCell ("f");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=3 on last column
    aTable = new HCTable (new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan3");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addCell ("a");
    aRow.addAndReturnCell ("b").setRowspan (3);
    // row 2
    aTable.addBodyRow ().addCell ("c");
    // row 3
    aTable.addBodyRow ().addCell ("e");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=3 on middle column
    aTable = new HCTable (new HCCol (170), new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan4");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addCell ("a");
    aRow.addAndReturnCell ("b").setRowspan (3);
    aRow.addCell ("c0");
    // row 2
    aTable.addBodyRow ().addCells ("c", "e0");
    // row 3
    aTable.addBodyRow ().addCells ("e", "g0");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=2 on first column
    aTable = new HCTable (new HCCol (170), new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan5");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addAndReturnCell ("a").setRowspan (2);
    aRow.addCells ("b", "c0");
    // row 2
    aTable.addBodyRow ().addCells ("d", "e0");
    // row 3
    aTable.addBodyRow ().addCells ("e", "f", "g0");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=2 on middle column
    aTable = new HCTable (new HCCol (170), new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan6");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addCell ("a");
    aRow.addAndReturnCell ("b").setRowspan (2);
    aRow.addCell ("c0");
    // row 2
    aTable.addBodyRow ().addCells ("c", "e0");
    // row 3
    aTable.addBodyRow ().addCells ("e", "f", "g0");
    assertNotNull (HCRenderer.getAsNode (aTable));

    // Use rowspan=2 on last column
    aTable = new HCTable (new HCCol (170), new HCCol (170), HCCol.star ());
    aTable.setID ("testConsistencyRowspan7");
    // row 1
    aRow = aTable.addBodyRow ();
    aRow.addCells ("a", "b");
    aRow.addAndReturnCell ("c0").setRowspan (2);
    // row 2
    aTable.addBodyRow ().addCells ("c", "d");
    // row 3
    aTable.addBodyRow ().addCells ("e", "f", "g0");
    assertNotNull (HCRenderer.getAsNode (aTable));
  }

  @Test
  public void testEmpty ()
  {
    assertNull (HCRenderer.getAsNode (new HCTable ()));
    assertNotNull (HCRenderer.getAsNode (new HCTable ().setBodyID ("any")));
    assertNotNull (HCRenderer.getAsNode (new HCTable (new HCCol (), new HCCol (), new HCCol ()).setBodyID ("any")));
    assertNotNull (HCRenderer.getAsNode (new HCTable (new HCCol (15), new HCCol (), new HCCol ()).setBodyID ("any")));
    assertNotNull (HCRenderer.getAsNode (new HCTable (new HCCol (), new HCCol (), new HCCol (27)).setBodyID ("any")));
    assertNotNull (HCRenderer.getAsNode (new HCTable (HCCol.star (), new HCCol (), new HCCol ()).setBodyID ("any")));
    assertNotNull (HCRenderer.getAsNode (new HCTable (new HCCol (), new HCCol (), HCCol.star ()).setBodyID ("any")));
  }

  @Test
  public void testHeaderAndFooter ()
  {
    final HCTable t = new HCTable (new HCCol (170), HCCol.star ());
    assertNull (t.getHeaderID ());
    assertFalse (t.hasHeaderID ());
    assertFalse (t.hasHeaderRows ());
    assertSame (t, t.removeHeaderRowAt (0));
    assertSame (t, t.removeHeaderRowAt (0));
    assertSame (t, t.setHeaderID ("any"));
    assertEquals ("any", t.getHeaderID ());
    assertFalse (t.hasHeaderRows ());
    assertNotNull (t.addHeaderRow ());
    assertTrue (t.hasHeaderRows ());
    // Already present
    assertNotNull (t.addHeaderRow ());
    assertEquals (2, t.getHeaderRowCount ());
    assertTrue (t.hasHeaderRows ());
    assertSame (t, t.removeHeaderRowAt (0));
    assertEquals (1, t.getHeaderRowCount ());
    assertSame (t, t.removeHeaderRowAt (0));
    assertFalse (t.hasHeaderRows ());
    assertNotNull (t.addHeaderRow ());
    assertTrue (t.hasHeaderRows ());

    assertNull (t.getFooterID ());
    assertFalse (t.hasFooterID ());
    assertFalse (t.hasFooterRows ());
    assertSame (t, t.removeFooterRowAt (0));
    assertSame (t, t.removeFooterRowAt (0));
    assertSame (t, t.setFooterID ("any"));
    assertEquals ("any", t.getFooterID ());
    assertFalse (t.hasFooterRows ());
    assertNotNull (t.addFooterRow ());
    assertTrue (t.hasFooterRows ());
    // Already present
    assertNotNull (t.addFooterRow ());
    assertEquals (2, t.getFooterRowCount ());
    assertTrue (t.hasFooterRows ());
    assertSame (t, t.removeFooterRowAt (0));
    assertEquals (1, t.getFooterRowCount ());
    assertSame (t, t.removeFooterRowAt (0));
    assertFalse (t.hasFooterRows ());
    assertNotNull (t.addFooterRow ());
    assertTrue (t.hasFooterRows ());
  }
}
