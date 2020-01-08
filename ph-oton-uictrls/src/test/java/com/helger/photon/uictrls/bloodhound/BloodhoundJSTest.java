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
package com.helger.photon.uictrls.bloodhound;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.js.JSWriterSettings;
import com.helger.html.jscode.JSPackage;

public final class BloodhoundJSTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final JSPackage p = new JSPackage ();
    p.add (BloodhoundJS.newBloodhound (new BloodhoundOptions ().setLocal (new BloodhoundDatum ("test"))));
    assertEquals ("new Bloodhound({local:[{value:'test',tokens:['test']}]});", p.getJSCode (aJSWS));
  }

  @Test
  public void testBasic2 ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final JSPackage p = new JSPackage ();
    p.add (BloodhoundJS.newBloodhound (new BloodhoundOptions ().setDatumTokenizerPreTokenized ()
                                                               .setLocal (new BloodhoundDatum ("test"))));
    assertEquals ("new Bloodhound({datumTokenizer:function(d){return d.tokens;},local:[{value:'test',tokens:['test']}]});",
                  p.getJSCode (aJSWS));
  }

  @Test
  public void testBasic3 ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final JSPackage p = new JSPackage ();
    p.add (BloodhoundJS.newBloodhound (new BloodhoundOptions ().setDatumTokenizerPreTokenized ()
                                                               .setQueryTokenizerNonword ()
                                                               .setLocal (new BloodhoundDatum ("test"))));
    assertEquals ("new Bloodhound({datumTokenizer:function(d){return d.tokens;},queryTokenizer:Bloodhound.tokenizers.nonword,local:[{value:'test',tokens:['test']}]});",
                  p.getJSCode (aJSWS));
  }
}
