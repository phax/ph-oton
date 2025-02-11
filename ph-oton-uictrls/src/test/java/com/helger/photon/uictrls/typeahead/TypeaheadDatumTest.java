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
package com.helger.photon.uictrls.typeahead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.html.js.JSWriterSettings;
import com.helger.html.jscode.JSExpr;

/**
 * Test class for class {@link TypeaheadDatum}.
 *
 * @author Philip Helger
 */
public final class TypeaheadDatumTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final TypeaheadDatum p = new TypeaheadDatum ("Value", new CommonsArrayList <> ("Token", "for", "this", "value"), null);
    assertEquals ("Value", p.getValue ());
    assertEquals (new CommonsArrayList <> ("Token", "for", "this", "value"), p.getAllTokens ());
    assertEquals ("{\"value\":\"Value\",\"tokens\":[\"Token\",\"for\",\"this\",\"value\"]}", p.getAsJson ().getAsJsonString ());
    assertEquals ("{value:'Value',tokens:['Token','for','this','value']}", JSExpr.json (p.getAsJson ()).getJSCode (aJSWS));
    assertNull (p.getID ());

    p.setID ("abc");
    assertEquals ("Value", p.getValue ());
    assertEquals (new CommonsArrayList <> ("Token", "for", "this", "value"), p.getAllTokens ());
    assertEquals ("{\"value\":\"Value\",\"tokens\":[\"Token\",\"for\",\"this\",\"value\"],\"id\":\"abc\"}",
                  p.getAsJson ().getAsJsonString ());
    assertEquals ("{value:'Value',tokens:['Token','for','this','value'],id:'abc'}", JSExpr.json (p.getAsJson ()).getJSCode (aJSWS));
    assertEquals ("abc", p.getID ());
  }
}
