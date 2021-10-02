package com.helger.photon.audit;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class {@link EAuditActionType}.
 *
 * @author Philip Helger
 */
public final class EAuditActionTypeTest
{
  @Test
  public void testBasic ()
  {
    for (final EAuditActionType e : EAuditActionType.values ())
    {
      assertSame (e, EAuditActionType.getFromIDOrNull (e.getID ()));
      assertTrue (e.getID ().length () <= EAuditActionType.MAX_ID_LENGTH);
    }
  }
}
