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
package com.helger.photon.tinymce4;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.html.js.JSWriterSettings;
import com.helger.photon.tinymce4.type.ETinyMCE4Resize;
import com.helger.web.scope.mock.WebScopeTestRule;

/**
 * Test class for class {@link HCTinyMCE4}.
 *
 * @author Philip Helger
 */
public final class HCTinyMCE4Test
{
  @Rule
  public final TestRule m_aRule = new WebScopeTestRule ();

  @Test
  public void testBasic ()
  {
    final HCTinyMCE4 aEditor = new HCTinyMCE4 ();
    final JSWriterSettings aJSSettings = new JSWriterSettings ().setMinimumCodeSize (true);
    assertEquals ("{selector:'#" + aEditor.getID () + "'}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
    aEditor.setSelector ("textarea");
    assertEquals ("{selector:'textarea'}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
    aEditor.setResize (ETinyMCE4Resize.DISABLE);
    assertEquals ("{selector:'textarea',resize:false}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
    aEditor.setResize (ETinyMCE4Resize.VERTICALLY);
    assertEquals ("{selector:'textarea',resize:true}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
    aEditor.setResize (ETinyMCE4Resize.BOTH);
    assertEquals ("{selector:'textarea',resize:'both'}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
    aEditor.setResize (null);
    assertEquals ("{selector:'textarea'}", aEditor.getJSInitOptions ().getJSCode (aJSSettings));
  }
}
