/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.jquery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.css.DefaultCSSClassProvider;

/**
 * Test class for class {@link JQuerySelectorList}.
 *
 * @author Philip Helger
 */
public final class JQuerySelectorListTest
{
  @Test
  public void testBasic ()
  {
    assertEquals ("'#id5'", new JQuerySelectorList ().addSelector (JQuerySelector.id ("id5")).getJSCode ());
    // Check masking
    assertEquals ("'#id\\\\.value'", new JQuerySelectorList ().addSelector (JQuerySelector.id ("id.value")).getJSCode ());
    assertEquals ("'.basic'",
                  new JQuerySelectorList ().addSelector (JQuerySelector.clazz (DefaultCSSClassProvider.create ("basic"))).getJSCode ());
    assertEquals ("'td'", new JQuerySelectorList ().addSelector (JQuerySelector.element ("td")).getJSCode ());
    assertEquals ("'td:gt(3)'",
                  new JQuerySelectorList ().addSelector (JQuerySelector.element ("td").chain (JQuerySelector.gt (3))).getJSCode ());
    assertEquals ("'td:checked'",
                  new JQuerySelectorList ().addSelector (JQuerySelector.element ("td").chain (JQuerySelector.checked)).getJSCode ());
    assertEquals ("'td span'",
                  new JQuerySelectorList ().addSelector (JQuerySelector.element ("td"))
                                           .addSelector (JQuerySelector.element ("span"))
                                           .getJSCode ());
  }
}
