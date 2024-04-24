package com.helger.photon.uictrls.datatables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for class {@link EDataTablesOrderDirectionType}.
 *
 * @author Philip Helger
 */
public class EDataTablesOrderDirectionTypeTest
{
  @Test
  public void testBasic ()
  {
    for (final EDataTablesOrderDirectionType e : EDataTablesOrderDirectionType.values ())
    {
      assertNotNull (e.getName ());
      assertEquals (e.getName (), EDataTablesOrderDirectionType.getNameFromSortOrderOrNull (e.getSortOrder ()));
      assertSame (e.getSortOrder (), EDataTablesOrderDirectionType.getSortOrderFromNameOrNull (e.getName ()));
    }
  }
}
