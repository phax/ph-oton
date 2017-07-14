package com.helger.html.jscode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

public class JSAssocArrayTest
{
  @Test
  public void testJson ()
  {
    final JSAssocArray a = new JSAssocArray ();
    a.addJson (new JsonObject ().add ("a", 1)
                                .add ("b",
                                      new JsonArray ().add (1)
                                                      .add ("value2")
                                                      .add (new JsonObject ().add ("c", 1)
                                                                             .add ("d",
                                                                                   new BigDecimal ("123456789123456789123456789")))));
    assertEquals (2, a.getCount ());
    assertNotNull (a.get ("a"));
    assertTrue (a.get ("a") instanceof JSAtomInt);
    assertNotNull (a.get ("b"));
    assertTrue (a.get ("b") instanceof JSArray);
    assertEquals ("{\r\n" +
                  "  a:1,\r\n" +
                  "  b:[1,'value2',{\r\n" +
                  "    c:1,\r\n" +
                  "    d:123456789123456789123456789\r\n" +
                  "  }]\r\n" +
                  "}",
                  a.getJSCode ());
  }
}
