package com.helger.html.jscode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.helger.html.js.JSWriterDefaultSettings;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

public class JSAssocArrayTest
{
  @Test
  public void testJson ()
  {
    final JSAssocArray a = new JSAssocArray ();
    a.addAll (new JsonObject ().add ("a", 1)
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

    final String sEOL = JSWriterDefaultSettings.getNewLineMode ().getText ();
    assertEquals ("{" +
                  sEOL +
                  "  a:1," +
                  sEOL +
                  "  b:[1,'value2',{" +
                  sEOL +
                  "    c:1," +
                  sEOL +
                  "    d:123456789123456789123456789" +
                  sEOL +
                  "  }]" +
                  sEOL +
                  "}",
                  a.getJSCode ());
    assertEquals ("{}", new JSAssocArray ().getJSCode ());
  }
}
