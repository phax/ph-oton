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
package com.helger.html.js.builder.jquery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.js.builder.JSExpr;

/**
 * Test class for class {@link JQuerySelector}.
 *
 * @author Philip Helger
 */
public final class JQuerySelectorTest
{
  @Test
  public void testBasic ()
  {
    assertEquals ("':animated'", JQuerySelector.animated.getJSCode ());
    assertEquals ("':gt(5)'", JQuerySelector.gt (5).getJSCode ());
    assertEquals ("'ul'", JQuerySelector.element (EHTMLElement.UL).getJSCode ());
    assertEquals ("'#abc'", JQuerySelector.id ("abc").getJSCode ());
    assertEquals ("'ul#abc'", JQuerySelector.element (EHTMLElement.UL).chain (JQuerySelector.id ("abc")).getJSCode ());
    assertEquals ("'ul,li'", JQuerySelector.element (EHTMLElement.UL)
                                           .multiple (JQuerySelector.element ("li"))
                                           .getJSCode ());
    assertEquals ("'ul > li'", JQuerySelector.element (EHTMLElement.UL)
                                             .child (JQuerySelector.element ("li"))
                                             .getJSCode ());
    assertEquals ("'ul li'", JQuerySelector.element (EHTMLElement.UL)
                                           .descendant (JQuerySelector.element ("li"))
                                           .getJSCode ());
    assertEquals ("'ul + li'", JQuerySelector.element (EHTMLElement.UL)
                                             .nextAdjacent (JQuerySelector.element ("li"))
                                             .getJSCode ());
    assertEquals ("'ul ~ li'", JQuerySelector.element (EHTMLElement.UL)
                                             .nextSiblings (JQuerySelector.element ("li"))
                                             .getJSCode ());

    assertEquals ("'ul.any > li,ol#bla'",
                  JQuerySelector.element (EHTMLElement.UL)
                                .chain (JQuerySelector.clazz (DefaultCSSClassProvider.create ("any")))
                                .child (JQuerySelector.element ("li"))
                                .multiple (JQuerySelector.element ("ol").chain (JQuerySelector.id ("bla")))
                                .getJSCode ());
  }

  @Test
  public void testExpr ()
  {
    assertEquals ("('#prefix_'+any)", JQuerySelector.id ("prefix_")
                                                    .chain (new JQuerySelector (JSExpr.ref ("any")))
                                                    .getJSCode ());
  }

  @Test
  public void testInvoke ()
  {
    assertEquals ("$(':checked');", JQuerySelector.checked.invoke ().getJSCode ());
    assertEquals ("$(':submit','form');", JQuerySelector.submit.invoke ().arg ("form").getJSCode ());
    assertEquals ("$(':checked');", new JQuerySelectorList (JQuerySelector.checked).invoke ().getJSCode ());
    assertEquals ("$(':checked :animated');",
                  new JQuerySelectorList (JQuerySelector.checked, JQuerySelector.animated).invoke ().getJSCode ());
  }
}
