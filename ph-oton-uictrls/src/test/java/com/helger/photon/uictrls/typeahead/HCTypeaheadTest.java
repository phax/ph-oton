/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import org.junit.Test;

import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.html.JSHtml;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.html.js.writer.JSWriterSettings;

/**
 * Test class for class {@link HCTypeahead}.
 *
 * @author Philip Helger
 */
public class HCTypeaheadTest
{
  @Test
  public void testBasic ()
  {
    final TypeaheadDatum d1 = new TypeaheadDatum ("v1", "a");
    final TypeaheadDataset d = new TypeaheadDataset ("test").setLocal (d1);
    final HCTypeahead t = new HCTypeahead (JQuery.idRef ("abc"));
    t.addDataset (d);
    assertEquals ("$('#abc').typeahead({name:'test',local:[{value:'v1',tokens:['a']}]});",
                  t.getAsJSObject ().getJSCode (new JSWriterSettings ().setIndentAndAlign (false)));
    t.setOnOpened (new JSAnonymousFunction (JSHtml.windowAlert ("o")));
    t.setOnAutoCompleted (new JSAnonymousFunction (JSHtml.windowAlert ("a")));
    assertEquals ("$('#abc').typeahead({name:'test',local:[{value:'v1',tokens:['a']}]})"
                      + ".on('typeahead:opened',function(){window.alert('o');})"
                      + ".on('typeahead:autocompleted',function(){window.alert('a');});",
                  t.getAsJSObject ().getJSCode (new JSWriterSettings ().setIndentAndAlign (false)));
  }
}
