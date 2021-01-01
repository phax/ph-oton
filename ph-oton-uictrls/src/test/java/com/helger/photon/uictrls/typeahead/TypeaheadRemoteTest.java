/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.commons.url.SimpleURL;
import com.helger.html.js.JSWriterSettings;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.html.JSHtml;

/**
 * Test class for class {@link TypeaheadRemote}.
 *
 * @author Philip Helger
 */
public final class TypeaheadRemoteTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aJSWS = new JSWriterSettings ().setIndentAndAlign (false);
    final TypeaheadRemote p = new TypeaheadRemote (new SimpleURL ("/a.json"));
    assertEquals ("{url:'\\/a.json',wildcard:'_query_'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setDataType ("js");
    assertEquals ("{url:'\\/a.json',dataType:'js',wildcard:'_query_'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setCache (false);
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,wildcard:'_query_'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setTimeout (1000);
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'_query_'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setWildcard ("$Q");
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q'}", p.getAsJSObject ().getJSCode (aJSWS));
    p.setReplace (new JSAnonymousFunction (JSHtml.windowAlert ("x")));
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');}}",
                  p.getAsJSObject ().getJSCode (aJSWS));
    p.setRateLimitFn (ETypeaheadRemoteRateLimitFunction.THROTTLE);
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');},rateLimitFn:'throttle'}",
                  p.getAsJSObject ().getJSCode (aJSWS));
    p.setRateLimitWait (100);
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');},rateLimitFn:'throttle',rateLimitWait:100}",
                  p.getAsJSObject ().getJSCode (aJSWS));
    p.setMaxParallelRequests (3);
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');},rateLimitFn:'throttle',rateLimitWait:100,maxParallelRequests:3}",
                  p.getAsJSObject ().getJSCode (aJSWS));
    p.setBeforeSend (new JSAnonymousFunction (JSHtml.windowAlert ("y")));
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');},rateLimitFn:'throttle',rateLimitWait:100,maxParallelRequests:3,beforeSend:function(){window.alert('y');}}",
                  p.getAsJSObject ().getJSCode (aJSWS));
    p.setFilter (new JSAnonymousFunction (JSHtml.windowAlert ("z")));
    assertEquals ("{url:'\\/a.json',dataType:'js',cache:false,timeout:1000,wildcard:'$Q',replace:function(){window.alert('x');},rateLimitFn:'throttle',rateLimitWait:100,maxParallelRequests:3,beforeSend:function(){window.alert('y');},filter:function(){window.alert('z');}}",
                  p.getAsJSObject ().getJSCode (aJSWS));
  }
}
