/*
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
package com.helger.html.hc.html.sections;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.UnparsedJSCodeProvider;

public final class HCBodyTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testBody ()
  {
    final HCBody aBody = new HCBody ();
    assertEquals ("<body xmlns=\"http://www.w3.org/1999/xhtml\"></body>", HCRenderer.getAsHTMLString (aBody));

    // With semicolon at the end
    aBody.addEventHandler (EJSEvent.LOAD, new UnparsedJSCodeProvider ("onLoad();"));
    // Empty event handler - ignored
    aBody.addEventHandler (EJSEvent.MOUSEDOWN, null);
    // With prefix
    aBody.setEventHandler (EJSEvent.CLICK, new UnparsedJSCodeProvider ("onClick();"));
    aBody.customAttrs ().putIn ("bla", "foo");
    assertEquals ("<body xmlns=\"http://www.w3.org/1999/xhtml\" onload=\"javascript:onLoad();\" onclick=\"javascript:onClick();\" bla=\"foo\"></body>",
                  HCRenderer.getAsHTMLString (aBody));
  }
}
