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

import org.junit.Test;

import com.helger.html.js.JSWriterSettings;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.html.JSHtml;
import com.helger.http.url.SimpleURL;

/**
 * Test class for class {@link TypeaheadPrefetch}.
 *
 * @author Philip Helger
 */
public final class TypeaheadPrefetchTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final TypeaheadPrefetch p = new TypeaheadPrefetch (new SimpleURL ("/a.json"));
    assertEquals ("{url:'\\/a.json'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setTTL (3600);
    assertEquals ("{url:'\\/a.json',ttl:3600}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setFilter (new JSAnonymousFunction (JSHtml.windowAlert ("x")));
    assertEquals ("{url:'\\/a.json',ttl:3600,filter:function(){window.alert('x');}}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setTTL (TypeaheadPrefetch.DEFAULT_TTL);
    assertEquals ("{url:'\\/a.json',filter:function(){window.alert('x');}}", p.getAsJSObject ().getJSCode (aJSWS));
  }
}
