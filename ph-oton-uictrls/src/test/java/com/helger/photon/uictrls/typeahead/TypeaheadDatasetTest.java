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
package com.helger.photon.uictrls.typeahead;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.helger.base.state.ETriState;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.html.js.JSWriterSettings;
import com.helger.url.SimpleURL;

/**
 * Test class for class {@link TypeaheadDataset}.
 *
 * @author Philip Helger
 */
public final class TypeaheadDatasetTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final TypeaheadDatum d1 = new TypeaheadDatum ("v1", new CommonsArrayList <> ("a"));
    final TypeaheadDatum d2 = new TypeaheadDatum ("v2", new CommonsArrayList <> ("b"));
    final TypeaheadDataset d = new TypeaheadDataset ("test");
    assertEquals ("{name:'test'}", d.getAsJSObject ().getJSCode (new JSWriterSettings ().setIndentAndAlign (false)));
    d.setLocal (d1, d2);
    assertEquals ("{name:'test',local:[{value:'v1',tokens:['a']},{value:'v2',tokens:['b']}]}", d.getAsJSObject ().getJSCode (aJSWS));
    d.setLocal ((List <? extends TypeaheadDatum>) null);
    d.setPrefetch (new SimpleURL ("prefetch.x"));
    assertEquals ("{name:'test',prefetch:{url:'prefetch.x'}}", d.getAsJSObject ().getJSCode (aJSWS));
    d.prefetch ().setTTL (1234);
    assertEquals ("{name:'test',prefetch:{url:'prefetch.x',ttl:1234}}", d.getAsJSObject ().getJSCode (aJSWS));
    d.setPrefetch ((TypeaheadPrefetch) null);
    d.setRemote (new TypeaheadRemote (new SimpleURL ("remote.x")));
    assertEquals ("{name:'test',remote:{url:'remote.x',wildcard:'_query_'}}", d.getAsJSObject ().getJSCode (aJSWS));
    d.remote ().setCache (false);
    assertEquals ("{name:'test',remote:{url:'remote.x',cache:false,wildcard:'_query_'}}", d.getAsJSObject ().getJSCode (aJSWS));
    d.remote ().setCache (ETriState.UNDEFINED);
    d.remote ().setWildcard ("%q");
    assertEquals ("{name:'test',remote:{url:'remote.x',wildcard:'%q'}}", d.getAsJSObject ().getJSCode (aJSWS));
  }
}
