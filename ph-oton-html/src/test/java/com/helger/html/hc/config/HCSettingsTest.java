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
package com.helger.html.hc.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;

import com.helger.html.hc.html.textlevel.HCBR;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.hc.render.HCRenderer;

/**
 * Test class for class {final @link HCSettings}.
 *
 * @author Philip Helger
 */
public final class HCSettingsTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  @Test
  public void testGetAsString ()
  {
    final HCBR aBR = new HCBR ();
    final String s = HCRenderer.getAsHTMLString (aBR);
    assertNotNull (s);
    assertEquals ("<br xmlns=\"http://www.w3.org/1999/xhtml\" />", s);
  }

  @Test
  public void testGetAsStringWithoutNamespaces ()
  {
    final HCBR aBR = new HCBR ();
    final String s = HCRenderer.getAsHTMLStringWithoutNamespaces (aBR);
    assertNotNull (s);
    assertEquals ("<br />", s);
  }
}
