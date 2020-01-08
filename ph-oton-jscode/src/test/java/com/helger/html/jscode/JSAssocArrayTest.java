/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.jscode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.helger.html.js.JSWriterDefaultSettings;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

public final class JSAssocArrayTest
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
    assertEquals (2, a.size ());
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
