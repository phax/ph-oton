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
package com.helger.html.jquery;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;


import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSExpr;
import com.helger.json.JsonObject;
import com.helger.xml.microdom.MicroQName;

/**
 * Unit test class for class @{link IJQueryInvocationExtended}
 *
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_JQueryInvocationExtendedFuncTest
*/
public final class JQueryInvocationExtendedFuncTest
{
@Test
public void testAdd0() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"))); }

@Test
public void testAdd1() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0))); }

@Test
public void testAdd2() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testAdd3() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV)); }

@Test
public void testAdd4() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testAdd5() { assertNotNull (JQuery.idRef ("any").add ("foo")); }

@Test
public void testAdd6() { assertNotNull (JQuery.idRef ("any").add (new HCDiv ().addChild ("foo"))); }

@Test
public void testAdd7() { assertNotNull (JQuery.idRef ("any").add (JQuery.idRef ("foo"))); }

@Test
public void testAdd8() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAdd9() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testAdd10() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testAdd11() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testAdd12() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testAdd13() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testAdd14() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testAdd15() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testAdd16() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testAdd17() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testAdd18() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), "foo")); }

@Test
public void testAdd19() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), "foo")); }

@Test
public void testAdd20() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), "foo")); }

@Test
public void testAdd21() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, "foo")); }

@Test
public void testAdd22() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), "foo")); }

@Test
public void testAddBack23() { assertNotNull (JQuery.idRef ("any").addBack (JSExpr.lit ("foo"))); }

@Test
public void testAddBack24() { assertNotNull (JQuery.idRef ("any").addBack (JQuerySelector.eq (0))); }

@Test
public void testAddBack25() { assertNotNull (JQuery.idRef ("any").addBack (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testAddBack26() { assertNotNull (JQuery.idRef ("any").addBack (EHTMLElement.DIV)); }

@Test
public void testAddBack27() { assertNotNull (JQuery.idRef ("any").addBack (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testAddClass28() { assertNotNull (JQuery.idRef ("any").addClass (JSExpr.lit ("foo"))); }

@Test
public void testAddClass29() { assertNotNull (JQuery.idRef ("any").addClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testAddClass30() { assertNotNull (JQuery.idRef ("any").addClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testAddClass31() { assertNotNull (JQuery.idRef ("any").addClass ("foo")); }

@Test
public void testAddClass32() { assertNotNull (JQuery.idRef ("any").addClass (new JSAnonymousFunction ())); }

@Test
public void testAfter33() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"))); }

@Test
public void testAfter34() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter35() { assertNotNull (JQuery.idRef ("any").after ("foo")); }

@Test
public void testAfter36() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV)); }

@Test
public void testAfter37() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter38() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2))); }

@Test
public void testAfter39() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"))); }

@Test
public void testAfter40() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAfter41() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAfter42() { assertNotNull (JQuery.idRef ("any").after ("foo", JSExpr.lit ("foo"))); }

@Test
public void testAfter43() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testAfter44() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testAfter45() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testAfter46() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAfter47() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter48() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter49() { assertNotNull (JQuery.idRef ("any").after ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter50() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter51() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter52() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter53() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAfter54() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), "foo")); }

@Test
public void testAfter55() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testAfter56() { assertNotNull (JQuery.idRef ("any").after ("foo", "foo")); }

@Test
public void testAfter57() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, "foo")); }

@Test
public void testAfter58() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testAfter59() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testAfter60() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), "foo")); }

@Test
public void testAfter61() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testAfter62() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testAfter63() { assertNotNull (JQuery.idRef ("any").after ("foo", EHTMLElement.DIV)); }

@Test
public void testAfter64() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testAfter65() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), EHTMLElement.DIV)); }

@Test
public void testAfter66() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testAfter67() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testAfter68() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter69() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter70() { assertNotNull (JQuery.idRef ("any").after ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter71() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter72() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter73() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter74() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAfter75() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAfter76() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAfter77() { assertNotNull (JQuery.idRef ("any").after ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testAfter78() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testAfter79() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testAfter80() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testAfter81() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAfter82() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAfter83() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAfter84() { assertNotNull (JQuery.idRef ("any").after ("foo", JQuery.idRef ("foo"))); }

@Test
public void testAfter85() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testAfter86() { assertNotNull (JQuery.idRef ("any").after (new JsonObject ().add ("foo", 5), JQuery.idRef ("foo"))); }

@Test
public void testAfter87() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testAfter88() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAfter89() { assertNotNull (JQuery.idRef ("any").after (new JSAnonymousFunction ())); }

@Test
public void testAjaxComplete90() { assertNotNull (JQuery.idRef ("any").ajaxComplete (JSExpr.lit ("foo"))); }

@Test
public void testAjaxComplete91() { assertNotNull (JQuery.idRef ("any").ajaxComplete (new JSAnonymousFunction ())); }

@Test
public void testAjaxError92() { assertNotNull (JQuery.idRef ("any").ajaxError (JSExpr.lit ("foo"))); }

@Test
public void testAjaxError93() { assertNotNull (JQuery.idRef ("any").ajaxError (new JSAnonymousFunction ())); }

@Test
public void testAjaxSend94() { assertNotNull (JQuery.idRef ("any").ajaxSend (JSExpr.lit ("foo"))); }

@Test
public void testAjaxSend95() { assertNotNull (JQuery.idRef ("any").ajaxSend (new JSAnonymousFunction ())); }

@Test
public void testAjaxStart96() { assertNotNull (JQuery.idRef ("any").ajaxStart (JSExpr.lit ("foo"))); }

@Test
public void testAjaxStart97() { assertNotNull (JQuery.idRef ("any").ajaxStart (new JSAnonymousFunction ())); }

@Test
public void testAjaxStop98() { assertNotNull (JQuery.idRef ("any").ajaxStop (JSExpr.lit ("foo"))); }

@Test
public void testAjaxStop99() { assertNotNull (JQuery.idRef ("any").ajaxStop (new JSAnonymousFunction ())); }

@Test
public void testAjaxSuccess100() { assertNotNull (JQuery.idRef ("any").ajaxSuccess (JSExpr.lit ("foo"))); }

@Test
public void testAjaxSuccess101() { assertNotNull (JQuery.idRef ("any").ajaxSuccess (new JSAnonymousFunction ())); }

@Test
public void testAnimate102() { assertNotNull (JQuery.idRef ("any").animate (JSExpr.lit ("foo"))); }

@Test
public void testAppend103() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"))); }

@Test
public void testAppend104() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend105() { assertNotNull (JQuery.idRef ("any").append ("foo")); }

@Test
public void testAppend106() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV)); }

@Test
public void testAppend107() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend108() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2))); }

@Test
public void testAppend109() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"))); }

@Test
public void testAppend110() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAppend111() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAppend112() { assertNotNull (JQuery.idRef ("any").append ("foo", JSExpr.lit ("foo"))); }

@Test
public void testAppend113() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testAppend114() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testAppend115() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testAppend116() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAppend117() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend118() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend119() { assertNotNull (JQuery.idRef ("any").append ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend120() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend121() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend122() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend123() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAppend124() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), "foo")); }

@Test
public void testAppend125() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testAppend126() { assertNotNull (JQuery.idRef ("any").append ("foo", "foo")); }

@Test
public void testAppend127() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, "foo")); }

@Test
public void testAppend128() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testAppend129() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testAppend130() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), "foo")); }

@Test
public void testAppend131() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testAppend132() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testAppend133() { assertNotNull (JQuery.idRef ("any").append ("foo", EHTMLElement.DIV)); }

@Test
public void testAppend134() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testAppend135() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), EHTMLElement.DIV)); }

@Test
public void testAppend136() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testAppend137() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testAppend138() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend139() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend140() { assertNotNull (JQuery.idRef ("any").append ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend141() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend142() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend143() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend144() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAppend145() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAppend146() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAppend147() { assertNotNull (JQuery.idRef ("any").append ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testAppend148() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testAppend149() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testAppend150() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testAppend151() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testAppend152() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAppend153() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAppend154() { assertNotNull (JQuery.idRef ("any").append ("foo", JQuery.idRef ("foo"))); }

@Test
public void testAppend155() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testAppend156() { assertNotNull (JQuery.idRef ("any").append (new JsonObject ().add ("foo", 5), JQuery.idRef ("foo"))); }

@Test
public void testAppend157() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testAppend158() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAppend159() { assertNotNull (JQuery.idRef ("any").append (new JSAnonymousFunction ())); }

@Test
public void testAppendTo160() { assertNotNull (JQuery.idRef ("any").appendTo (JSExpr.lit ("foo"))); }

@Test
public void testAppendTo161() { assertNotNull (JQuery.idRef ("any").appendTo (JQuerySelector.eq (0))); }

@Test
public void testAppendTo162() { assertNotNull (JQuery.idRef ("any").appendTo (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testAppendTo163() { assertNotNull (JQuery.idRef ("any").appendTo (EHTMLElement.DIV)); }

@Test
public void testAppendTo164() { assertNotNull (JQuery.idRef ("any").appendTo (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testAppendTo165() { assertNotNull (JQuery.idRef ("any").appendTo (new HCDiv ().addChild ("foo"))); }

@Test
public void testAppendTo166() { assertNotNull (JQuery.idRef ("any").appendTo ("foo")); }

@Test
public void testAppendTo167() { assertNotNull (JQuery.idRef ("any").appendTo (new JSArray ().add (1).add (2))); }

@Test
public void testAppendTo168() { assertNotNull (JQuery.idRef ("any").appendTo (JQuery.idRef ("foo"))); }

@Test
public void testAttr169() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"))); }

@Test
public void testAttr170() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr171() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr172() { assertNotNull (JQuery.idRef ("any").attr ("foo")); }

@Test
public void testAttr173() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"))); }

@Test
public void testAttr174() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAttr175() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testAttr176() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAttr177() { assertNotNull (JQuery.idRef ("any").attr ("foo", JSExpr.lit ("foo"))); }

@Test
public void testAttr178() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testAttr179() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr180() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr181() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr182() { assertNotNull (JQuery.idRef ("any").attr ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr183() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testAttr184() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr185() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr186() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr187() { assertNotNull (JQuery.idRef ("any").attr ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr188() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testAttr189() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), "foo")); }

@Test
public void testAttr190() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testAttr191() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testAttr192() { assertNotNull (JQuery.idRef ("any").attr ("foo", "foo")); }

@Test
public void testAttr193() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), "foo")); }

@Test
public void testAttr194() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 3456)); }

@Test
public void testAttr195() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testAttr196() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testAttr197() { assertNotNull (JQuery.idRef ("any").attr ("foo", 3456)); }

@Test
public void testAttr198() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), 3456)); }

@Test
public void testAttr199() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testAttr200() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testAttr201() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testAttr202() { assertNotNull (JQuery.idRef ("any").attr ("foo", 87654321L)); }

@Test
public void testAttr203() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), 87654321L)); }

@Test
public void testAttr204() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testAttr205() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testAttr206() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testAttr207() { assertNotNull (JQuery.idRef ("any").attr ("foo", BigInteger.valueOf (3456))); }

@Test
public void testAttr208() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testAttr209() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testAttr210() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testAttr211() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testAttr212() { assertNotNull (JQuery.idRef ("any").attr ("foo", 123.456)); }

@Test
public void testAttr213() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), 123.456)); }

@Test
public void testAttr214() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testAttr215() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testAttr216() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testAttr217() { assertNotNull (JQuery.idRef ("any").attr ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testAttr218() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testAttr219() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAttr220() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), JQuery.idRef ("foo"))); }

@Test
public void testAttr221() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAttr222() { assertNotNull (JQuery.idRef ("any").attr ("foo", JQuery.idRef ("foo"))); }

@Test
public void testAttr223() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testAttr224() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testAttr225() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testAttr226() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testAttr227() { assertNotNull (JQuery.idRef ("any").attr ("foo", new JSAnonymousFunction ())); }

@Test
public void testAttr228() { assertNotNull (JQuery.idRef ("any").attr (new MicroQName ("foo"), new JSAnonymousFunction ())); }

@Test
public void testBefore229() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"))); }

@Test
public void testBefore230() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore231() { assertNotNull (JQuery.idRef ("any").before ("foo")); }

@Test
public void testBefore232() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV)); }

@Test
public void testBefore233() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore234() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2))); }

@Test
public void testBefore235() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"))); }

@Test
public void testBefore236() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testBefore237() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testBefore238() { assertNotNull (JQuery.idRef ("any").before ("foo", JSExpr.lit ("foo"))); }

@Test
public void testBefore239() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testBefore240() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testBefore241() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testBefore242() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testBefore243() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore244() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore245() { assertNotNull (JQuery.idRef ("any").before ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore246() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore247() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore248() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore249() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testBefore250() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), "foo")); }

@Test
public void testBefore251() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testBefore252() { assertNotNull (JQuery.idRef ("any").before ("foo", "foo")); }

@Test
public void testBefore253() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, "foo")); }

@Test
public void testBefore254() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testBefore255() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testBefore256() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), "foo")); }

@Test
public void testBefore257() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testBefore258() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testBefore259() { assertNotNull (JQuery.idRef ("any").before ("foo", EHTMLElement.DIV)); }

@Test
public void testBefore260() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testBefore261() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), EHTMLElement.DIV)); }

@Test
public void testBefore262() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testBefore263() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testBefore264() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore265() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore266() { assertNotNull (JQuery.idRef ("any").before ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore267() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore268() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore269() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore270() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testBefore271() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testBefore272() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testBefore273() { assertNotNull (JQuery.idRef ("any").before ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testBefore274() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testBefore275() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testBefore276() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testBefore277() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testBefore278() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testBefore279() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testBefore280() { assertNotNull (JQuery.idRef ("any").before ("foo", JQuery.idRef ("foo"))); }

@Test
public void testBefore281() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testBefore282() { assertNotNull (JQuery.idRef ("any").before (new JsonObject ().add ("foo", 5), JQuery.idRef ("foo"))); }

@Test
public void testBefore283() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testBefore284() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testBefore285() { assertNotNull (JQuery.idRef ("any").before (new JSAnonymousFunction ())); }

@Test
public void testBlur286() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"))); }

@Test
public void testBlur287() { assertNotNull (JQuery.idRef ("any").blur (new JSAnonymousFunction ())); }

@Test
public void testBlur288() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testBlur289() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testCallbacks_add290() { assertNotNull (JQuery.idRef ("any").callbacks_add (JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_add291() { assertNotNull (JQuery.idRef ("any").callbacks_add (new JSAnonymousFunction ())); }

@Test
public void testCallbacks_add292() { assertNotNull (JQuery.idRef ("any").callbacks_add (new JSArray ().add (1).add (2))); }

@Test
public void testCallbacks_fire293() { assertNotNull (JQuery.idRef ("any").callbacks_fire (JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_fireWith294() { assertNotNull (JQuery.idRef ("any").callbacks_fireWith (JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_fireWith295() { assertNotNull (JQuery.idRef ("any").callbacks_fireWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_has296() { assertNotNull (JQuery.idRef ("any").callbacks_has (JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_has297() { assertNotNull (JQuery.idRef ("any").callbacks_has (new JSAnonymousFunction ())); }

@Test
public void testCallbacks_remove298() { assertNotNull (JQuery.idRef ("any").callbacks_remove (JSExpr.lit ("foo"))); }

@Test
public void testCallbacks_remove299() { assertNotNull (JQuery.idRef ("any").callbacks_remove (new JSAnonymousFunction ())); }

@Test
public void testCallbacks_remove300() { assertNotNull (JQuery.idRef ("any").callbacks_remove (new JSArray ().add (1).add (2))); }

@Test
public void testChange301() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"))); }

@Test
public void testChange302() { assertNotNull (JQuery.idRef ("any").change (new JSAnonymousFunction ())); }

@Test
public void testChange303() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testChange304() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testChildren305() { assertNotNull (JQuery.idRef ("any").children (JSExpr.lit ("foo"))); }

@Test
public void testChildren306() { assertNotNull (JQuery.idRef ("any").children (JQuerySelector.eq (0))); }

@Test
public void testChildren307() { assertNotNull (JQuery.idRef ("any").children (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testChildren308() { assertNotNull (JQuery.idRef ("any").children (EHTMLElement.DIV)); }

@Test
public void testChildren309() { assertNotNull (JQuery.idRef ("any").children (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testClearQueue310() { assertNotNull (JQuery.idRef ("any").clearQueue (JSExpr.lit ("foo"))); }

@Test
public void testClearQueue311() { assertNotNull (JQuery.idRef ("any").clearQueue (new JsonObject ().add ("foo", 5))); }

@Test
public void testClearQueue312() { assertNotNull (JQuery.idRef ("any").clearQueue (new HCDiv ().addChild ("foo"))); }

@Test
public void testClearQueue313() { assertNotNull (JQuery.idRef ("any").clearQueue ("foo")); }

@Test
public void testClick314() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"))); }

@Test
public void testClick315() { assertNotNull (JQuery.idRef ("any").click (new JSAnonymousFunction ())); }

@Test
public void testClick316() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testClick317() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void test_clone318() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"))); }

@Test
public void test_clone319() { assertNotNull (JQuery.idRef ("any")._clone (true)); }

@Test
public void test_clone320() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void test_clone321() { assertNotNull (JQuery.idRef ("any")._clone (true, JSExpr.lit ("foo"))); }

@Test
public void test_clone322() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"), true)); }

@Test
public void test_clone323() { assertNotNull (JQuery.idRef ("any")._clone (true, true)); }

@Test
public void testClosest324() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"))); }

@Test
public void testClosest325() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0))); }

@Test
public void testClosest326() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testClosest327() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV)); }

@Test
public void testClosest328() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testClosest329() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testClosest330() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testClosest331() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testClosest332() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testClosest333() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testClosest334() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testClosest335() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testClosest336() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testClosest337() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testClosest338() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testClosest339() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), "foo")); }

@Test
public void testClosest340() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), "foo")); }

@Test
public void testClosest341() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), "foo")); }

@Test
public void testClosest342() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, "foo")); }

@Test
public void testClosest343() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), "foo")); }

@Test
public void testClosest344() { assertNotNull (JQuery.idRef ("any").closest (JQuery.idRef ("foo"))); }

@Test
public void testClosest345() { assertNotNull (JQuery.idRef ("any").closest ("foo")); }

@Test
public void testContextmenu346() { assertNotNull (JQuery.idRef ("any").contextmenu (JSExpr.lit ("foo"))); }

@Test
public void testContextmenu347() { assertNotNull (JQuery.idRef ("any").contextmenu (new JSAnonymousFunction ())); }

@Test
public void testContextmenu348() { assertNotNull (JQuery.idRef ("any").contextmenu (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testContextmenu349() { assertNotNull (JQuery.idRef ("any").contextmenu (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testCss350() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"))); }

@Test
public void testCss351() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5))); }

@Test
public void testCss352() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"))); }

@Test
public void testCss353() { assertNotNull (JQuery.idRef ("any").css ("foo")); }

@Test
public void testCss354() { assertNotNull (JQuery.idRef ("any").css (new JSArray ().add (1).add (2))); }

@Test
public void testCss355() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testCss356() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testCss357() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testCss358() { assertNotNull (JQuery.idRef ("any").css ("foo", JSExpr.lit ("foo"))); }

@Test
public void testCss359() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testCss360() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testCss361() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testCss362() { assertNotNull (JQuery.idRef ("any").css ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testCss363() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testCss364() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testCss365() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testCss366() { assertNotNull (JQuery.idRef ("any").css ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testCss367() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), "foo")); }

@Test
public void testCss368() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testCss369() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testCss370() { assertNotNull (JQuery.idRef ("any").css ("foo", "foo")); }

@Test
public void testCss371() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 3456)); }

@Test
public void testCss372() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testCss373() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testCss374() { assertNotNull (JQuery.idRef ("any").css ("foo", 3456)); }

@Test
public void testCss375() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testCss376() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testCss377() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testCss378() { assertNotNull (JQuery.idRef ("any").css ("foo", 87654321L)); }

@Test
public void testCss379() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testCss380() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testCss381() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testCss382() { assertNotNull (JQuery.idRef ("any").css ("foo", BigInteger.valueOf (3456))); }

@Test
public void testCss383() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testCss384() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testCss385() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testCss386() { assertNotNull (JQuery.idRef ("any").css ("foo", 123.456)); }

@Test
public void testCss387() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testCss388() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testCss389() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testCss390() { assertNotNull (JQuery.idRef ("any").css ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testCss391() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testCss392() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testCss393() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testCss394() { assertNotNull (JQuery.idRef ("any").css ("foo", new JSAnonymousFunction ())); }

@Test
public void testData395() { assertNotNull (JQuery.idRef ("any").data (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testData396() { assertNotNull (JQuery.idRef ("any").data (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testData397() { assertNotNull (JQuery.idRef ("any").data (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testData398() { assertNotNull (JQuery.idRef ("any").data ("foo", JSExpr.lit ("foo"))); }

@Test
public void testData399() { assertNotNull (JQuery.idRef ("any").data (JSExpr.lit ("foo"))); }

@Test
public void testData400() { assertNotNull (JQuery.idRef ("any").data (new JsonObject ().add ("foo", 5))); }

@Test
public void testData401() { assertNotNull (JQuery.idRef ("any").data (new HCDiv ().addChild ("foo"))); }

@Test
public void testData402() { assertNotNull (JQuery.idRef ("any").data ("foo")); }

@Test
public void testDblclick403() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"))); }

@Test
public void testDblclick404() { assertNotNull (JQuery.idRef ("any").dblclick (new JSAnonymousFunction ())); }

@Test
public void testDblclick405() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDblclick406() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_always407() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_always408() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction ())); }

@Test
public void testDeferred_always409() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_always410() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_always411() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_always412() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_done413() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_done414() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction ())); }

@Test
public void testDeferred_done415() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_done416() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_done417() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_done418() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_fail419() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_fail420() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction ())); }

@Test
public void testDeferred_fail421() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_fail422() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_fail423() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_fail424() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_notify425() { assertNotNull (JQuery.idRef ("any").deferred_notify (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_notifyWith426() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_notifyWith427() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_notifyWith428() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_progress429() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_progress430() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction ())); }

@Test
public void testDeferred_progress431() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_progress432() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_progress433() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_progress434() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_progress435() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_progress436() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_progress437() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), new JSAnonymousFunction ())); }

@Test
public void testDeferred_progress438() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_progress439() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_progress440() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_promise441() { assertNotNull (JQuery.idRef ("any").deferred_promise (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_reject442() { assertNotNull (JQuery.idRef ("any").deferred_reject (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_rejectWith443() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_rejectWith444() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_rejectWith445() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_resolve446() { assertNotNull (JQuery.idRef ("any").deferred_resolve (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_resolveWith447() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_resolveWith448() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_resolveWith449() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testDeferred_then450() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then451() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction ())); }

@Test
public void testDeferred_then452() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then453() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then454() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then455() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then456() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_then457() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_then458() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_then459() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDeferred_then460() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then461() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testDeferred_then462() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testDeferred_then463() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testDelay464() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"))); }

@Test
public void testDelay465() { assertNotNull (JQuery.idRef ("any").delay (3456)); }

@Test
public void testDelay466() { assertNotNull (JQuery.idRef ("any").delay (87654321L)); }

@Test
public void testDelay467() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456))); }

@Test
public void testDelay468() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testDelay469() { assertNotNull (JQuery.idRef ("any").delay (3456, JSExpr.lit ("foo"))); }

@Test
public void testDelay470() { assertNotNull (JQuery.idRef ("any").delay (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testDelay471() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testDelay472() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testDelay473() { assertNotNull (JQuery.idRef ("any").delay (3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testDelay474() { assertNotNull (JQuery.idRef ("any").delay (87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testDelay475() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testDelay476() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testDelay477() { assertNotNull (JQuery.idRef ("any").delay (3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testDelay478() { assertNotNull (JQuery.idRef ("any").delay (87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testDelay479() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testDelay480() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), "foo")); }

@Test
public void testDelay481() { assertNotNull (JQuery.idRef ("any").delay (3456, "foo")); }

@Test
public void testDelay482() { assertNotNull (JQuery.idRef ("any").delay (87654321L, "foo")); }

@Test
public void testDelay483() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), "foo")); }

@Test
public void testDequeue484() { assertNotNull (JQuery.idRef ("any").dequeue (JSExpr.lit ("foo"))); }

@Test
public void testDequeue485() { assertNotNull (JQuery.idRef ("any").dequeue (new JsonObject ().add ("foo", 5))); }

@Test
public void testDequeue486() { assertNotNull (JQuery.idRef ("any").dequeue (new HCDiv ().addChild ("foo"))); }

@Test
public void testDequeue487() { assertNotNull (JQuery.idRef ("any").dequeue ("foo")); }

@Test
public void testDetach488() { assertNotNull (JQuery.idRef ("any").detach (JSExpr.lit ("foo"))); }

@Test
public void testDetach489() { assertNotNull (JQuery.idRef ("any").detach (JQuerySelector.eq (0))); }

@Test
public void testDetach490() { assertNotNull (JQuery.idRef ("any").detach (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testDetach491() { assertNotNull (JQuery.idRef ("any").detach (EHTMLElement.DIV)); }

@Test
public void testDetach492() { assertNotNull (JQuery.idRef ("any").detach (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testEach493() { assertNotNull (JQuery.idRef ("any").each (JSExpr.lit ("foo"))); }

@Test
public void testEach494() { assertNotNull (JQuery.idRef ("any").each (new JSAnonymousFunction ())); }

@Test
public void test_eq495() { assertNotNull (JQuery.idRef ("any")._eq (JSExpr.lit ("foo"))); }

@Test
public void test_eq496() { assertNotNull (JQuery.idRef ("any")._eq (3456)); }

@Test
public void test_eq497() { assertNotNull (JQuery.idRef ("any")._eq (87654321L)); }

@Test
public void test_eq498() { assertNotNull (JQuery.idRef ("any")._eq (BigInteger.valueOf (3456))); }

@Test
public void testFadeTo499() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo500() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo501() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo502() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo503() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo504() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo505() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo506() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo507() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo508() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456)); }

@Test
public void testFadeTo509() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testFadeTo510() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testFadeTo511() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456)); }

@Test
public void testFadeTo512() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456)); }

@Test
public void testFadeTo513() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456)); }

@Test
public void testFadeTo514() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456)); }

@Test
public void testFadeTo515() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456)); }

@Test
public void testFadeTo516() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456)); }

@Test
public void testFadeTo517() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testFadeTo518() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testFadeTo519() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testFadeTo520() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L)); }

@Test
public void testFadeTo521() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L)); }

@Test
public void testFadeTo522() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L)); }

@Test
public void testFadeTo523() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L)); }

@Test
public void testFadeTo524() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L)); }

@Test
public void testFadeTo525() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L)); }

@Test
public void testFadeTo526() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testFadeTo527() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testFadeTo528() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testFadeTo529() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456))); }

@Test
public void testFadeTo530() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456))); }

@Test
public void testFadeTo531() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456))); }

@Test
public void testFadeTo532() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456))); }

@Test
public void testFadeTo533() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456))); }

@Test
public void testFadeTo534() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456))); }

@Test
public void testFadeTo535() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testFadeTo536() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testFadeTo537() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testFadeTo538() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456)); }

@Test
public void testFadeTo539() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456)); }

@Test
public void testFadeTo540() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456)); }

@Test
public void testFadeTo541() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456)); }

@Test
public void testFadeTo542() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456)); }

@Test
public void testFadeTo543() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456)); }

@Test
public void testFadeTo544() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo545() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo546() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo547() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo548() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo549() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo550() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo551() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo552() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456))); }

@Test
public void testFadeTo553() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo554() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo555() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo556() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo557() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo558() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo559() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo560() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo561() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo562() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo563() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo564() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo565() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo566() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo567() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo568() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo569() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo570() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo571() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo572() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo573() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo574() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo575() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo576() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo577() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo578() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo579() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo580() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo581() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo582() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo583() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo584() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo585() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo586() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo587() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo588() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo589() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo590() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo591() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo592() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo593() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo594() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo595() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo596() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo597() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testFadeTo598() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo599() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo600() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo601() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo602() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo603() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo604() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo605() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo606() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo607() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo608() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo609() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo610() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo611() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo612() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo613() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo614() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo615() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo616() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo617() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo618() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo619() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo620() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo621() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo622() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo623() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo624() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo625() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo626() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo627() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo628() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo629() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo630() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo631() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo632() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo633() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testFadeTo634() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo635() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo636() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo637() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo638() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo639() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo640() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo641() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo642() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo643() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo644() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo645() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo646() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo647() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo648() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo649() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo650() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo651() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JSAnonymousFunction ())); }

@Test
public void testFadeTo652() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo653() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo654() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo655() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo656() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo657() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo658() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo659() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo660() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testFadeTo661() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo662() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo663() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo664() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo665() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo666() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo667() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo668() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo669() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo670() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo671() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo672() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo673() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo674() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo675() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo676() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo677() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo678() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo679() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo680() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo681() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo682() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo683() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo684() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo685() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo686() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo687() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo688() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo689() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo690() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo691() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo692() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo693() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo694() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo695() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo696() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo697() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo698() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo699() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo700() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo701() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo702() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo703() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo704() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo705() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo706() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo707() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo708() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo709() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo710() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo711() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo712() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo713() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo714() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testFadeTo715() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo716() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo717() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo718() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo719() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo720() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo721() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo722() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo723() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo724() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo725() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo726() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo727() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo728() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo729() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo730() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo731() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo732() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo733() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo734() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo735() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo736() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo737() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo738() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo739() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo740() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo741() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo742() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo743() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo744() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo745() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo746() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo747() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo748() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo749() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo750() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo751() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo752() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo753() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo754() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo755() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo756() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo757() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo758() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo759() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo760() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo761() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo762() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo763() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo764() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo765() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo766() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo767() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo768() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testFadeTo769() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo770() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo771() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo772() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo773() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo774() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo775() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo776() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo777() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo")); }

@Test
public void testFadeTo778() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo")); }

@Test
public void testFadeTo779() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo")); }

@Test
public void testFadeTo780() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo")); }

@Test
public void testFadeTo781() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo")); }

@Test
public void testFadeTo782() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo")); }

@Test
public void testFadeTo783() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo")); }

@Test
public void testFadeTo784() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo")); }

@Test
public void testFadeTo785() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo")); }

@Test
public void testFadeTo786() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo")); }

@Test
public void testFadeTo787() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo")); }

@Test
public void testFadeTo788() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo")); }

@Test
public void testFadeTo789() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo")); }

@Test
public void testFadeTo790() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo")); }

@Test
public void testFadeTo791() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo")); }

@Test
public void testFadeTo792() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo")); }

@Test
public void testFadeTo793() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo")); }

@Test
public void testFadeTo794() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo")); }

@Test
public void testFadeTo795() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo")); }

@Test
public void testFadeTo796() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo797() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo798() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo799() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo800() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo801() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo802() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo803() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo804() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo")); }

@Test
public void testFadeTo805() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo")); }

@Test
public void testFadeTo806() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo")); }

@Test
public void testFadeTo807() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo")); }

@Test
public void testFadeTo808() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo")); }

@Test
public void testFadeTo809() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo")); }

@Test
public void testFadeTo810() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo")); }

@Test
public void testFadeTo811() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo")); }

@Test
public void testFadeTo812() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo")); }

@Test
public void testFadeTo813() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo")); }

@Test
public void testFadeTo814() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo815() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo816() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo817() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo818() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo819() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo820() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo821() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo822() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testFadeTo823() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo824() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo825() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo826() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo827() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo828() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo829() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo830() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo831() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo832() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo833() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo834() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo835() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo836() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo837() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo838() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo839() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo840() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo841() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo842() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo843() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo844() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo845() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo846() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo847() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo848() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo849() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo850() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo851() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo852() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo853() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo854() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo855() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo856() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo857() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo858() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo859() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo860() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo861() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo862() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo863() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo864() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo865() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo866() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo867() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo868() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo869() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo870() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo871() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo872() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo873() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo874() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo875() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo876() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo877() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo878() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo879() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo880() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo881() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo882() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo883() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo884() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo885() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo886() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo887() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo888() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo889() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo890() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo891() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo892() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo893() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo894() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo895() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo896() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo897() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo898() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo899() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo900() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo901() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo902() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo903() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo904() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo905() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo906() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo907() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo908() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo909() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo910() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo911() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo912() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo913() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo914() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo915() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo916() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo917() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo918() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo919() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo920() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo921() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo922() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo923() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo924() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo925() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo926() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo927() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo928() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo929() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo930() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo931() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo932() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo933() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo934() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo935() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo936() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo937() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo938() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo939() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo940() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo941() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo942() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo943() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo944() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo945() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo946() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo947() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo948() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo949() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo950() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo951() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo952() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo953() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo954() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo955() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo956() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo957() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo958() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo959() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo960() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo961() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo962() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo963() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo964() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo965() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo966() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo967() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo968() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo969() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo970() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo971() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo972() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo973() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo974() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo975() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo976() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo977() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo978() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo979() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo980() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo981() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo982() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo983() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo984() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFadeTo985() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo986() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo987() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo988() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo989() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo990() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo991() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo992() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo993() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo994() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo995() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo996() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo997() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo998() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo999() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1000() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1001() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1002() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1003() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1004() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1005() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1006() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1007() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1008() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1009() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1010() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1011() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1012() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1013() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1014() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1015() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1016() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1017() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1018() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1019() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1020() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1021() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1022() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1023() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1024() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1025() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1026() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1027() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1028() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1029() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1030() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1031() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1032() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1033() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1034() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1035() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1036() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1037() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1038() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testFadeTo1039() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1040() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1041() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1042() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1043() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1044() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1045() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1046() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1047() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1048() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1049() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1050() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1051() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1052() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1053() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1054() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1055() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1056() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1057() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1058() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1059() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1060() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1061() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1062() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1063() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1064() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1065() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1066() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1067() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1068() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1069() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1070() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1071() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1072() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1073() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1074() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1075() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1076() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1077() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1078() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1079() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1080() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1081() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1082() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1083() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1084() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1085() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1086() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1087() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1088() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1089() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1090() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1091() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1092() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1093() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1094() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1095() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1096() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1097() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1098() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1099() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1100() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1101() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1102() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1103() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1104() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1105() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1106() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1107() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1108() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1109() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1110() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1111() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1112() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1113() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1114() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1115() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1116() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1117() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1118() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1119() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1120() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1121() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1122() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1123() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1124() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1125() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1126() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1127() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1128() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1129() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1130() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1131() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1132() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1133() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1134() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1135() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1136() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1137() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1138() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1139() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1140() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1141() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1142() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1143() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1144() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1145() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1146() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1147() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1148() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1149() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1150() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1151() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1152() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1153() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1154() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1155() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1156() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1157() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1158() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1159() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1160() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1161() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1162() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1163() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1164() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1165() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1166() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1167() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1168() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1169() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1170() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1171() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1172() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1173() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1174() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1175() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1176() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1177() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1178() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1179() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1180() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1181() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1182() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1183() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1184() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1185() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1186() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1187() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1188() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1189() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1190() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1191() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1192() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1193() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1194() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1195() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1196() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1197() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1198() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1199() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1200() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFadeTo1201() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1202() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1203() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1204() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1205() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1206() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1207() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1208() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1209() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1210() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1211() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1212() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1213() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1214() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1215() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1216() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1217() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1218() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1219() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1220() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1221() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1222() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1223() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1224() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1225() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1226() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1227() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1228() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1229() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1230() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1231() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1232() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1233() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1234() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1235() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1236() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1237() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1238() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1239() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1240() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1241() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1242() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1243() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1244() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1245() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1246() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1247() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1248() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1249() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1250() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1251() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1252() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1253() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFadeTo1254() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testFilter1255() { assertNotNull (JQuery.idRef ("any").filter (JSExpr.lit ("foo"))); }

@Test
public void testFilter1256() { assertNotNull (JQuery.idRef ("any").filter (JQuerySelector.eq (0))); }

@Test
public void testFilter1257() { assertNotNull (JQuery.idRef ("any").filter (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testFilter1258() { assertNotNull (JQuery.idRef ("any").filter (EHTMLElement.DIV)); }

@Test
public void testFilter1259() { assertNotNull (JQuery.idRef ("any").filter (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testFilter1260() { assertNotNull (JQuery.idRef ("any").filter (new JSAnonymousFunction ())); }

@Test
public void testFilter1261() { assertNotNull (JQuery.idRef ("any").filter ("foo")); }

@Test
public void testFilter1262() { assertNotNull (JQuery.idRef ("any").filter (JQuery.idRef ("foo"))); }

@Test
public void testFind1263() { assertNotNull (JQuery.idRef ("any").find (JSExpr.lit ("foo"))); }

@Test
public void testFind1264() { assertNotNull (JQuery.idRef ("any").find (JQuerySelector.eq (0))); }

@Test
public void testFind1265() { assertNotNull (JQuery.idRef ("any").find (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testFind1266() { assertNotNull (JQuery.idRef ("any").find (EHTMLElement.DIV)); }

@Test
public void testFind1267() { assertNotNull (JQuery.idRef ("any").find (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testFind1268() { assertNotNull (JQuery.idRef ("any").find ("foo")); }

@Test
public void testFind1269() { assertNotNull (JQuery.idRef ("any").find (JQuery.idRef ("foo"))); }

@Test
public void testFinish1270() { assertNotNull (JQuery.idRef ("any").finish (JSExpr.lit ("foo"))); }

@Test
public void testFinish1271() { assertNotNull (JQuery.idRef ("any").finish (new JsonObject ().add ("foo", 5))); }

@Test
public void testFinish1272() { assertNotNull (JQuery.idRef ("any").finish (new HCDiv ().addChild ("foo"))); }

@Test
public void testFinish1273() { assertNotNull (JQuery.idRef ("any").finish ("foo")); }

@Test
public void testFocus1274() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"))); }

@Test
public void testFocus1275() { assertNotNull (JQuery.idRef ("any").focus (new JSAnonymousFunction ())); }

@Test
public void testFocus1276() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFocus1277() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFocusin1278() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"))); }

@Test
public void testFocusin1279() { assertNotNull (JQuery.idRef ("any").focusin (new JSAnonymousFunction ())); }

@Test
public void testFocusin1280() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFocusin1281() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testFocusout1282() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"))); }

@Test
public void testFocusout1283() { assertNotNull (JQuery.idRef ("any").focusout (new JSAnonymousFunction ())); }

@Test
public void testFocusout1284() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testFocusout1285() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testGet1286() { assertNotNull (JQuery.idRef ("any").get (JSExpr.lit ("foo"))); }

@Test
public void testGet1287() { assertNotNull (JQuery.idRef ("any").get (3456)); }

@Test
public void testGet1288() { assertNotNull (JQuery.idRef ("any").get (87654321L)); }

@Test
public void testGet1289() { assertNotNull (JQuery.idRef ("any").get (BigInteger.valueOf (3456))); }

@Test
public void testHas1290() { assertNotNull (JQuery.idRef ("any").has (JSExpr.lit ("foo"))); }

@Test
public void testHas1291() { assertNotNull (JQuery.idRef ("any").has (new JsonObject ().add ("foo", 5))); }

@Test
public void testHas1292() { assertNotNull (JQuery.idRef ("any").has (new HCDiv ().addChild ("foo"))); }

@Test
public void testHas1293() { assertNotNull (JQuery.idRef ("any").has ("foo")); }

@Test
public void testHas1294() { assertNotNull (JQuery.idRef ("any").has (EHTMLElement.DIV)); }

@Test
public void testHasClass1295() { assertNotNull (JQuery.idRef ("any").hasClass (JSExpr.lit ("foo"))); }

@Test
public void testHasClass1296() { assertNotNull (JQuery.idRef ("any").hasClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testHasClass1297() { assertNotNull (JQuery.idRef ("any").hasClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testHasClass1298() { assertNotNull (JQuery.idRef ("any").hasClass ("foo")); }

@Test
public void testHeight1299() { assertNotNull (JQuery.idRef ("any").height (JSExpr.lit ("foo"))); }

@Test
public void testHeight1300() { assertNotNull (JQuery.idRef ("any").height (new JsonObject ().add ("foo", 5))); }

@Test
public void testHeight1301() { assertNotNull (JQuery.idRef ("any").height (new HCDiv ().addChild ("foo"))); }

@Test
public void testHeight1302() { assertNotNull (JQuery.idRef ("any").height ("foo")); }

@Test
public void testHeight1303() { assertNotNull (JQuery.idRef ("any").height (3456)); }

@Test
public void testHeight1304() { assertNotNull (JQuery.idRef ("any").height (87654321L)); }

@Test
public void testHeight1305() { assertNotNull (JQuery.idRef ("any").height (BigInteger.valueOf (3456))); }

@Test
public void testHeight1306() { assertNotNull (JQuery.idRef ("any").height (123.456)); }

@Test
public void testHeight1307() { assertNotNull (JQuery.idRef ("any").height (BigDecimal.valueOf (12.3456))); }

@Test
public void testHeight1308() { assertNotNull (JQuery.idRef ("any").height (new JSAnonymousFunction ())); }

@Test
public void testHide1309() { assertNotNull (JQuery.idRef ("any").hide (JSExpr.lit ("foo"))); }

@Test
public void testHide1310() { assertNotNull (JQuery.idRef ("any").hide (3456)); }

@Test
public void testHide1311() { assertNotNull (JQuery.idRef ("any").hide (87654321L)); }

@Test
public void testHide1312() { assertNotNull (JQuery.idRef ("any").hide (BigInteger.valueOf (3456))); }

@Test
public void testHide1313() { assertNotNull (JQuery.idRef ("any").hide (123.456)); }

@Test
public void testHide1314() { assertNotNull (JQuery.idRef ("any").hide (BigDecimal.valueOf (12.3456))); }

@Test
public void testHide1315() { assertNotNull (JQuery.idRef ("any").hide (new JsonObject ().add ("foo", 5))); }

@Test
public void testHide1316() { assertNotNull (JQuery.idRef ("any").hide (new HCDiv ().addChild ("foo"))); }

@Test
public void testHide1317() { assertNotNull (JQuery.idRef ("any").hide ("foo")); }

@Test
public void testHover1318() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testHover1319() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testHover1320() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testHover1321() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testHover1322() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"))); }

@Test
public void testHover1323() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction ())); }

@Test
public void testHtml1324() { assertNotNull (JQuery.idRef ("any").html (JSExpr.lit ("foo"))); }

@Test
public void testHtml1325() { assertNotNull (JQuery.idRef ("any").html (new HCDiv ().addChild ("foo"))); }

@Test
public void testHtml1326() { assertNotNull (JQuery.idRef ("any").html ("foo")); }

@Test
public void testHtml1327() { assertNotNull (JQuery.idRef ("any").html (new JSAnonymousFunction ())); }

@Test
public void testIndex1328() { assertNotNull (JQuery.idRef ("any").index (JSExpr.lit ("foo"))); }

@Test
public void testIndex1329() { assertNotNull (JQuery.idRef ("any").index (JQuerySelector.eq (0))); }

@Test
public void testIndex1330() { assertNotNull (JQuery.idRef ("any").index (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testIndex1331() { assertNotNull (JQuery.idRef ("any").index (EHTMLElement.DIV)); }

@Test
public void testIndex1332() { assertNotNull (JQuery.idRef ("any").index (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testIndex1333() { assertNotNull (JQuery.idRef ("any").index ("foo")); }

@Test
public void testIndex1334() { assertNotNull (JQuery.idRef ("any").index (JQuery.idRef ("foo"))); }

@Test
public void testInnerHeight1335() { assertNotNull (JQuery.idRef ("any").innerHeight (JSExpr.lit ("foo"))); }

@Test
public void testInnerHeight1336() { assertNotNull (JQuery.idRef ("any").innerHeight (new JsonObject ().add ("foo", 5))); }

@Test
public void testInnerHeight1337() { assertNotNull (JQuery.idRef ("any").innerHeight (new HCDiv ().addChild ("foo"))); }

@Test
public void testInnerHeight1338() { assertNotNull (JQuery.idRef ("any").innerHeight ("foo")); }

@Test
public void testInnerHeight1339() { assertNotNull (JQuery.idRef ("any").innerHeight (3456)); }

@Test
public void testInnerHeight1340() { assertNotNull (JQuery.idRef ("any").innerHeight (87654321L)); }

@Test
public void testInnerHeight1341() { assertNotNull (JQuery.idRef ("any").innerHeight (BigInteger.valueOf (3456))); }

@Test
public void testInnerHeight1342() { assertNotNull (JQuery.idRef ("any").innerHeight (123.456)); }

@Test
public void testInnerHeight1343() { assertNotNull (JQuery.idRef ("any").innerHeight (BigDecimal.valueOf (12.3456))); }

@Test
public void testInnerHeight1344() { assertNotNull (JQuery.idRef ("any").innerHeight (new JSAnonymousFunction ())); }

@Test
public void testInnerWidth1345() { assertNotNull (JQuery.idRef ("any").innerWidth (JSExpr.lit ("foo"))); }

@Test
public void testInnerWidth1346() { assertNotNull (JQuery.idRef ("any").innerWidth (new JsonObject ().add ("foo", 5))); }

@Test
public void testInnerWidth1347() { assertNotNull (JQuery.idRef ("any").innerWidth (new HCDiv ().addChild ("foo"))); }

@Test
public void testInnerWidth1348() { assertNotNull (JQuery.idRef ("any").innerWidth ("foo")); }

@Test
public void testInnerWidth1349() { assertNotNull (JQuery.idRef ("any").innerWidth (3456)); }

@Test
public void testInnerWidth1350() { assertNotNull (JQuery.idRef ("any").innerWidth (87654321L)); }

@Test
public void testInnerWidth1351() { assertNotNull (JQuery.idRef ("any").innerWidth (BigInteger.valueOf (3456))); }

@Test
public void testInnerWidth1352() { assertNotNull (JQuery.idRef ("any").innerWidth (123.456)); }

@Test
public void testInnerWidth1353() { assertNotNull (JQuery.idRef ("any").innerWidth (BigDecimal.valueOf (12.3456))); }

@Test
public void testInnerWidth1354() { assertNotNull (JQuery.idRef ("any").innerWidth (new JSAnonymousFunction ())); }

@Test
public void testInsertAfter1355() { assertNotNull (JQuery.idRef ("any").insertAfter (JSExpr.lit ("foo"))); }

@Test
public void testInsertAfter1356() { assertNotNull (JQuery.idRef ("any").insertAfter (JQuerySelector.eq (0))); }

@Test
public void testInsertAfter1357() { assertNotNull (JQuery.idRef ("any").insertAfter (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testInsertAfter1358() { assertNotNull (JQuery.idRef ("any").insertAfter (EHTMLElement.DIV)); }

@Test
public void testInsertAfter1359() { assertNotNull (JQuery.idRef ("any").insertAfter (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testInsertAfter1360() { assertNotNull (JQuery.idRef ("any").insertAfter (new HCDiv ().addChild ("foo"))); }

@Test
public void testInsertAfter1361() { assertNotNull (JQuery.idRef ("any").insertAfter ("foo")); }

@Test
public void testInsertAfter1362() { assertNotNull (JQuery.idRef ("any").insertAfter (new JSArray ().add (1).add (2))); }

@Test
public void testInsertAfter1363() { assertNotNull (JQuery.idRef ("any").insertAfter (JQuery.idRef ("foo"))); }

@Test
public void testInsertBefore1364() { assertNotNull (JQuery.idRef ("any").insertBefore (JSExpr.lit ("foo"))); }

@Test
public void testInsertBefore1365() { assertNotNull (JQuery.idRef ("any").insertBefore (JQuerySelector.eq (0))); }

@Test
public void testInsertBefore1366() { assertNotNull (JQuery.idRef ("any").insertBefore (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testInsertBefore1367() { assertNotNull (JQuery.idRef ("any").insertBefore (EHTMLElement.DIV)); }

@Test
public void testInsertBefore1368() { assertNotNull (JQuery.idRef ("any").insertBefore (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testInsertBefore1369() { assertNotNull (JQuery.idRef ("any").insertBefore (new HCDiv ().addChild ("foo"))); }

@Test
public void testInsertBefore1370() { assertNotNull (JQuery.idRef ("any").insertBefore ("foo")); }

@Test
public void testInsertBefore1371() { assertNotNull (JQuery.idRef ("any").insertBefore (new JSArray ().add (1).add (2))); }

@Test
public void testInsertBefore1372() { assertNotNull (JQuery.idRef ("any").insertBefore (JQuery.idRef ("foo"))); }

@Test
public void testIs1373() { assertNotNull (JQuery.idRef ("any").is (JSExpr.lit ("foo"))); }

@Test
public void testIs1374() { assertNotNull (JQuery.idRef ("any").is (JQuerySelector.eq (0))); }

@Test
public void testIs1375() { assertNotNull (JQuery.idRef ("any").is (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testIs1376() { assertNotNull (JQuery.idRef ("any").is (EHTMLElement.DIV)); }

@Test
public void testIs1377() { assertNotNull (JQuery.idRef ("any").is (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testIs1378() { assertNotNull (JQuery.idRef ("any").is (new JSAnonymousFunction ())); }

@Test
public void testIs1379() { assertNotNull (JQuery.idRef ("any").is (JQuery.idRef ("foo"))); }

@Test
public void testIs1380() { assertNotNull (JQuery.idRef ("any").is ("foo")); }

@Test
public void testKeydown1381() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"))); }

@Test
public void testKeydown1382() { assertNotNull (JQuery.idRef ("any").keydown (new JSAnonymousFunction ())); }

@Test
public void testKeydown1383() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testKeydown1384() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testKeypress1385() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"))); }

@Test
public void testKeypress1386() { assertNotNull (JQuery.idRef ("any").keypress (new JSAnonymousFunction ())); }

@Test
public void testKeypress1387() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testKeypress1388() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testKeyup1389() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"))); }

@Test
public void testKeyup1390() { assertNotNull (JQuery.idRef ("any").keyup (new JSAnonymousFunction ())); }

@Test
public void testKeyup1391() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testKeyup1392() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1393() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"))); }

@Test
public void testLoad1394() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5))); }

@Test
public void testLoad1395() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"))); }

@Test
public void testLoad1396() { assertNotNull (JQuery.idRef ("any").load ("foo")); }

@Test
public void testLoad1397() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1398() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testLoad1399() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1400() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"))); }

@Test
public void testLoad1401() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testLoad1402() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testLoad1403() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testLoad1404() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testLoad1405() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testLoad1406() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testLoad1407() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testLoad1408() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testLoad1409() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo")); }

@Test
public void testLoad1410() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testLoad1411() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testLoad1412() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo")); }

@Test
public void testLoad1413() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1414() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1415() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1416() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1417() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testLoad1418() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testLoad1419() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testLoad1420() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testLoad1421() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1422() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1423() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1424() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testLoad1425() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testLoad1426() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testLoad1427() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testLoad1428() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testLoad1429() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1430() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1431() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1432() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1433() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testLoad1434() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testLoad1435() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testLoad1436() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testLoad1437() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1438() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1439() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1440() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testLoad1441() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testLoad1442() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testLoad1443() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testLoad1444() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testMap1445() { assertNotNull (JQuery.idRef ("any").map (JSExpr.lit ("foo"))); }

@Test
public void testMap1446() { assertNotNull (JQuery.idRef ("any").map (new JSAnonymousFunction ())); }

@Test
public void testMousedown1447() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"))); }

@Test
public void testMousedown1448() { assertNotNull (JQuery.idRef ("any").mousedown (new JSAnonymousFunction ())); }

@Test
public void testMousedown1449() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMousedown1450() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMouseenter1451() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"))); }

@Test
public void testMouseenter1452() { assertNotNull (JQuery.idRef ("any").mouseenter (new JSAnonymousFunction ())); }

@Test
public void testMouseenter1453() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMouseenter1454() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMouseleave1455() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"))); }

@Test
public void testMouseleave1456() { assertNotNull (JQuery.idRef ("any").mouseleave (new JSAnonymousFunction ())); }

@Test
public void testMouseleave1457() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMouseleave1458() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMousemove1459() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"))); }

@Test
public void testMousemove1460() { assertNotNull (JQuery.idRef ("any").mousemove (new JSAnonymousFunction ())); }

@Test
public void testMousemove1461() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMousemove1462() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMouseout1463() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"))); }

@Test
public void testMouseout1464() { assertNotNull (JQuery.idRef ("any").mouseout (new JSAnonymousFunction ())); }

@Test
public void testMouseout1465() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMouseout1466() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMouseover1467() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"))); }

@Test
public void testMouseover1468() { assertNotNull (JQuery.idRef ("any").mouseover (new JSAnonymousFunction ())); }

@Test
public void testMouseover1469() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMouseover1470() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testMouseup1471() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"))); }

@Test
public void testMouseup1472() { assertNotNull (JQuery.idRef ("any").mouseup (new JSAnonymousFunction ())); }

@Test
public void testMouseup1473() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testMouseup1474() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testNext1475() { assertNotNull (JQuery.idRef ("any").next (JSExpr.lit ("foo"))); }

@Test
public void testNext1476() { assertNotNull (JQuery.idRef ("any").next (JQuerySelector.eq (0))); }

@Test
public void testNext1477() { assertNotNull (JQuery.idRef ("any").next (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNext1478() { assertNotNull (JQuery.idRef ("any").next (EHTMLElement.DIV)); }

@Test
public void testNext1479() { assertNotNull (JQuery.idRef ("any").next (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextAll1480() { assertNotNull (JQuery.idRef ("any").nextAll (JSExpr.lit ("foo"))); }

@Test
public void testNextAll1481() { assertNotNull (JQuery.idRef ("any").nextAll (new JsonObject ().add ("foo", 5))); }

@Test
public void testNextAll1482() { assertNotNull (JQuery.idRef ("any").nextAll (new HCDiv ().addChild ("foo"))); }

@Test
public void testNextAll1483() { assertNotNull (JQuery.idRef ("any").nextAll ("foo")); }

@Test
public void testNextUntil1484() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1485() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0))); }

@Test
public void testNextUntil1486() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1487() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV)); }

@Test
public void testNextUntil1488() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1489() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1490() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1491() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1492() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1493() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1494() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testNextUntil1495() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testNextUntil1496() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testNextUntil1497() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testNextUntil1498() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testNextUntil1499() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1500() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1501() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1502() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1503() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1504() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testNextUntil1505() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testNextUntil1506() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testNextUntil1507() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testNextUntil1508() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testNextUntil1509() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1510() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1511() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1512() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1513() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1514() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo")); }

@Test
public void testNextUntil1515() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"))); }

@Test
public void testNextUntil1516() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1517() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testNextUntil1518() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testNextUntil1519() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testNextUntil1520() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1521() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testNextUntil1522() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testNextUntil1523() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testNextUntil1524() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testNextUntil1525() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void test_not1526() { assertNotNull (JQuery.idRef ("any")._not (JSExpr.lit ("foo"))); }

@Test
public void test_not1527() { assertNotNull (JQuery.idRef ("any")._not (JQuerySelector.eq (0))); }

@Test
public void test_not1528() { assertNotNull (JQuery.idRef ("any")._not (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void test_not1529() { assertNotNull (JQuery.idRef ("any")._not (EHTMLElement.DIV)); }

@Test
public void test_not1530() { assertNotNull (JQuery.idRef ("any")._not (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void test_not1531() { assertNotNull (JQuery.idRef ("any")._not ("foo")); }

@Test
public void test_not1532() { assertNotNull (JQuery.idRef ("any")._not (new JSArray ().add (1).add (2))); }

@Test
public void test_not1533() { assertNotNull (JQuery.idRef ("any")._not (new JSAnonymousFunction ())); }

@Test
public void test_not1534() { assertNotNull (JQuery.idRef ("any")._not (JQuery.idRef ("foo"))); }

@Test
public void testOff1535() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"))); }

@Test
public void testOff1536() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5))); }

@Test
public void testOff1537() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"))); }

@Test
public void testOff1538() { assertNotNull (JQuery.idRef ("any").off ("foo")); }

@Test
public void testOff1539() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1540() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOff1541() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1542() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"))); }

@Test
public void testOff1543() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOff1544() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testOff1545() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOff1546() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testOff1547() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOff1548() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testOff1549() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOff1550() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testOff1551() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo")); }

@Test
public void testOff1552() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testOff1553() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testOff1554() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo")); }

@Test
public void testOff1555() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1556() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1557() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1558() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1559() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOff1560() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOff1561() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOff1562() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOff1563() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1564() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1565() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1566() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOff1567() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOff1568() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOff1569() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOff1570() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testOff1571() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1572() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1573() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1574() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1575() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testOff1576() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testOff1577() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testOff1578() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testOff1579() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1580() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1581() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1582() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOff1583() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testOff1584() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testOff1585() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testOff1586() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testOffset1587() { assertNotNull (JQuery.idRef ("any").offset (JSExpr.lit ("foo"))); }

@Test
public void testOffset1588() { assertNotNull (JQuery.idRef ("any").offset (new JSAnonymousFunction ())); }

@Test
public void testOn1589() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1590() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOn1591() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1592() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"))); }

@Test
public void testOn1593() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOn1594() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testOn1595() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOn1596() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testOn1597() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOn1598() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testOn1599() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOn1600() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testOn1601() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo")); }

@Test
public void testOn1602() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testOn1603() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testOn1604() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo")); }

@Test
public void testOn1605() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1606() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1607() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1608() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1609() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOn1610() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOn1611() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOn1612() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOn1613() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1614() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1615() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1616() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1617() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOn1618() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOn1619() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOn1620() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testOn1621() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1622() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1623() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1624() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1625() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1626() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1627() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1628() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1629() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1630() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1631() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1632() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1633() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1634() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1635() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1636() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOn1637() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1638() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1639() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1640() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1641() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1642() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1643() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1644() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1645() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1646() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1647() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1648() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1649() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1650() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1651() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1652() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOn1653() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"))); }

@Test
public void testOne1654() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1655() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOne1656() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1657() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"))); }

@Test
public void testOne1658() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1659() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1660() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1661() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1662() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1663() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1664() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1665() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1666() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOne1667() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testOne1668() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testOne1669() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testOne1670() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOne1671() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testOne1672() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testOne1673() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testOne1674() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo")); }

@Test
public void testOne1675() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testOne1676() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testOne1677() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo")); }

@Test
public void testOne1678() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOne1679() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOne1680() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOne1681() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testOne1682() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1683() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1684() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1685() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1686() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOne1687() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOne1688() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testOne1689() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testOne1690() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1691() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1692() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1693() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1694() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1695() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1696() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1697() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1698() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1699() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1700() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1701() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1702() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1703() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1704() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1705() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testOne1706() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1707() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1708() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1709() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1710() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1711() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1712() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1713() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1714() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1715() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1716() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1717() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1718() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1719() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1720() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1721() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testOne1722() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"))); }

@Test
public void testOuterHeight1723() { assertNotNull (JQuery.idRef ("any").outerHeight (JSExpr.lit ("foo"))); }

@Test
public void testOuterHeight1724() { assertNotNull (JQuery.idRef ("any").outerHeight (true)); }

@Test
public void testOuterHeight1725() { assertNotNull (JQuery.idRef ("any").outerHeight (new JsonObject ().add ("foo", 5))); }

@Test
public void testOuterHeight1726() { assertNotNull (JQuery.idRef ("any").outerHeight (new HCDiv ().addChild ("foo"))); }

@Test
public void testOuterHeight1727() { assertNotNull (JQuery.idRef ("any").outerHeight ("foo")); }

@Test
public void testOuterHeight1728() { assertNotNull (JQuery.idRef ("any").outerHeight (3456)); }

@Test
public void testOuterHeight1729() { assertNotNull (JQuery.idRef ("any").outerHeight (87654321L)); }

@Test
public void testOuterHeight1730() { assertNotNull (JQuery.idRef ("any").outerHeight (BigInteger.valueOf (3456))); }

@Test
public void testOuterHeight1731() { assertNotNull (JQuery.idRef ("any").outerHeight (123.456)); }

@Test
public void testOuterHeight1732() { assertNotNull (JQuery.idRef ("any").outerHeight (BigDecimal.valueOf (12.3456))); }

@Test
public void testOuterHeight1733() { assertNotNull (JQuery.idRef ("any").outerHeight (new JSAnonymousFunction ())); }

@Test
public void testOuterWidth1734() { assertNotNull (JQuery.idRef ("any").outerWidth (JSExpr.lit ("foo"))); }

@Test
public void testOuterWidth1735() { assertNotNull (JQuery.idRef ("any").outerWidth (true)); }

@Test
public void testOuterWidth1736() { assertNotNull (JQuery.idRef ("any").outerWidth (new JsonObject ().add ("foo", 5))); }

@Test
public void testOuterWidth1737() { assertNotNull (JQuery.idRef ("any").outerWidth (new HCDiv ().addChild ("foo"))); }

@Test
public void testOuterWidth1738() { assertNotNull (JQuery.idRef ("any").outerWidth ("foo")); }

@Test
public void testOuterWidth1739() { assertNotNull (JQuery.idRef ("any").outerWidth (3456)); }

@Test
public void testOuterWidth1740() { assertNotNull (JQuery.idRef ("any").outerWidth (87654321L)); }

@Test
public void testOuterWidth1741() { assertNotNull (JQuery.idRef ("any").outerWidth (BigInteger.valueOf (3456))); }

@Test
public void testOuterWidth1742() { assertNotNull (JQuery.idRef ("any").outerWidth (123.456)); }

@Test
public void testOuterWidth1743() { assertNotNull (JQuery.idRef ("any").outerWidth (BigDecimal.valueOf (12.3456))); }

@Test
public void testOuterWidth1744() { assertNotNull (JQuery.idRef ("any").outerWidth (new JSAnonymousFunction ())); }

@Test
public void testParent1745() { assertNotNull (JQuery.idRef ("any").parent (JSExpr.lit ("foo"))); }

@Test
public void testParent1746() { assertNotNull (JQuery.idRef ("any").parent (JQuerySelector.eq (0))); }

@Test
public void testParent1747() { assertNotNull (JQuery.idRef ("any").parent (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParent1748() { assertNotNull (JQuery.idRef ("any").parent (EHTMLElement.DIV)); }

@Test
public void testParent1749() { assertNotNull (JQuery.idRef ("any").parent (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParents1750() { assertNotNull (JQuery.idRef ("any").parents (JSExpr.lit ("foo"))); }

@Test
public void testParents1751() { assertNotNull (JQuery.idRef ("any").parents (JQuerySelector.eq (0))); }

@Test
public void testParents1752() { assertNotNull (JQuery.idRef ("any").parents (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParents1753() { assertNotNull (JQuery.idRef ("any").parents (EHTMLElement.DIV)); }

@Test
public void testParents1754() { assertNotNull (JQuery.idRef ("any").parents (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1755() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1756() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1757() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1758() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV)); }

@Test
public void testParentsUntil1759() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1760() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1761() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1762() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1763() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1764() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1765() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1766() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1767() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1768() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1769() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1770() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1771() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1772() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1773() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1774() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1775() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testParentsUntil1776() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testParentsUntil1777() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testParentsUntil1778() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testParentsUntil1779() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testParentsUntil1780() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1781() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1782() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1783() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1784() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1785() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo")); }

@Test
public void testParentsUntil1786() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"))); }

@Test
public void testParentsUntil1787() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1788() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testParentsUntil1789() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1790() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testParentsUntil1791() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1792() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testParentsUntil1793() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testParentsUntil1794() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testParentsUntil1795() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testParentsUntil1796() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrepend1797() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"))); }

@Test
public void testPrepend1798() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1799() { assertNotNull (JQuery.idRef ("any").prepend ("foo")); }

@Test
public void testPrepend1800() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV)); }

@Test
public void testPrepend1801() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1802() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1803() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"))); }

@Test
public void testPrepend1804() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPrepend1805() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPrepend1806() { assertNotNull (JQuery.idRef ("any").prepend ("foo", JSExpr.lit ("foo"))); }

@Test
public void testPrepend1807() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testPrepend1808() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testPrepend1809() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testPrepend1810() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPrepend1811() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1812() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1813() { assertNotNull (JQuery.idRef ("any").prepend ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1814() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1815() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1816() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1817() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testPrepend1818() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), "foo")); }

@Test
public void testPrepend1819() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testPrepend1820() { assertNotNull (JQuery.idRef ("any").prepend ("foo", "foo")); }

@Test
public void testPrepend1821() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, "foo")); }

@Test
public void testPrepend1822() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testPrepend1823() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testPrepend1824() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), "foo")); }

@Test
public void testPrepend1825() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testPrepend1826() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testPrepend1827() { assertNotNull (JQuery.idRef ("any").prepend ("foo", EHTMLElement.DIV)); }

@Test
public void testPrepend1828() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testPrepend1829() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), EHTMLElement.DIV)); }

@Test
public void testPrepend1830() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testPrepend1831() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testPrepend1832() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1833() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1834() { assertNotNull (JQuery.idRef ("any").prepend ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1835() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1836() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1837() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1838() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testPrepend1839() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1840() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1841() { assertNotNull (JQuery.idRef ("any").prepend ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1842() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1843() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1844() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1845() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPrepend1846() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testPrepend1847() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testPrepend1848() { assertNotNull (JQuery.idRef ("any").prepend ("foo", JQuery.idRef ("foo"))); }

@Test
public void testPrepend1849() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testPrepend1850() { assertNotNull (JQuery.idRef ("any").prepend (new JsonObject ().add ("foo", 5), JQuery.idRef ("foo"))); }

@Test
public void testPrepend1851() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testPrepend1852() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testPrepend1853() { assertNotNull (JQuery.idRef ("any").prepend (new JSAnonymousFunction ())); }

@Test
public void testPrependTo1854() { assertNotNull (JQuery.idRef ("any").prependTo (JSExpr.lit ("foo"))); }

@Test
public void testPrependTo1855() { assertNotNull (JQuery.idRef ("any").prependTo (JQuerySelector.eq (0))); }

@Test
public void testPrependTo1856() { assertNotNull (JQuery.idRef ("any").prependTo (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrependTo1857() { assertNotNull (JQuery.idRef ("any").prependTo (EHTMLElement.DIV)); }

@Test
public void testPrependTo1858() { assertNotNull (JQuery.idRef ("any").prependTo (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrependTo1859() { assertNotNull (JQuery.idRef ("any").prependTo (new HCDiv ().addChild ("foo"))); }

@Test
public void testPrependTo1860() { assertNotNull (JQuery.idRef ("any").prependTo ("foo")); }

@Test
public void testPrependTo1861() { assertNotNull (JQuery.idRef ("any").prependTo (new JSArray ().add (1).add (2))); }

@Test
public void testPrependTo1862() { assertNotNull (JQuery.idRef ("any").prependTo (JQuery.idRef ("foo"))); }

@Test
public void testPrev1863() { assertNotNull (JQuery.idRef ("any").prev (JSExpr.lit ("foo"))); }

@Test
public void testPrev1864() { assertNotNull (JQuery.idRef ("any").prev (JQuerySelector.eq (0))); }

@Test
public void testPrev1865() { assertNotNull (JQuery.idRef ("any").prev (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrev1866() { assertNotNull (JQuery.idRef ("any").prev (EHTMLElement.DIV)); }

@Test
public void testPrev1867() { assertNotNull (JQuery.idRef ("any").prev (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevAll1868() { assertNotNull (JQuery.idRef ("any").prevAll (JSExpr.lit ("foo"))); }

@Test
public void testPrevAll1869() { assertNotNull (JQuery.idRef ("any").prevAll (JQuerySelector.eq (0))); }

@Test
public void testPrevAll1870() { assertNotNull (JQuery.idRef ("any").prevAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevAll1871() { assertNotNull (JQuery.idRef ("any").prevAll (EHTMLElement.DIV)); }

@Test
public void testPrevAll1872() { assertNotNull (JQuery.idRef ("any").prevAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1873() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1874() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1875() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1876() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV)); }

@Test
public void testPrevUntil1877() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1878() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1879() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1880() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1881() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1882() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1883() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1884() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1885() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1886() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1887() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1888() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1889() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1890() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1891() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1892() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1893() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testPrevUntil1894() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testPrevUntil1895() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testPrevUntil1896() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testPrevUntil1897() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testPrevUntil1898() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1899() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1900() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1901() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1902() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1903() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo")); }

@Test
public void testPrevUntil1904() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"))); }

@Test
public void testPrevUntil1905() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1906() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPrevUntil1907() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1908() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testPrevUntil1909() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1910() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testPrevUntil1911() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testPrevUntil1912() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testPrevUntil1913() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPrevUntil1914() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testPromise1915() { assertNotNull (JQuery.idRef ("any").promise (JSExpr.lit ("foo"))); }

@Test
public void testPromise1916() { assertNotNull (JQuery.idRef ("any").promise (new JsonObject ().add ("foo", 5))); }

@Test
public void testPromise1917() { assertNotNull (JQuery.idRef ("any").promise (new HCDiv ().addChild ("foo"))); }

@Test
public void testPromise1918() { assertNotNull (JQuery.idRef ("any").promise ("foo")); }

@Test
public void testPromise1919() { assertNotNull (JQuery.idRef ("any").promise (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPromise1920() { assertNotNull (JQuery.idRef ("any").promise (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testPromise1921() { assertNotNull (JQuery.idRef ("any").promise (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPromise1922() { assertNotNull (JQuery.idRef ("any").promise ("foo", JSExpr.lit ("foo"))); }

@Test
public void testProp1923() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"))); }

@Test
public void testProp1924() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5))); }

@Test
public void testProp1925() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"))); }

@Test
public void testProp1926() { assertNotNull (JQuery.idRef ("any").prop ("foo")); }

@Test
public void testProp1927() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testProp1928() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testProp1929() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testProp1930() { assertNotNull (JQuery.idRef ("any").prop ("foo", JSExpr.lit ("foo"))); }

@Test
public void testProp1931() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testProp1932() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testProp1933() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testProp1934() { assertNotNull (JQuery.idRef ("any").prop ("foo", new JSAnonymousFunction ())); }

@Test
public void testPushStack1935() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"))); }

@Test
public void testPushStack1936() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1937() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1938() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1939() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1940() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1941() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1942() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testPushStack1943() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testPushStack1944() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), "foo", JSExpr.lit ("foo"))); }

@Test
public void testPushStack1945() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1946() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1947() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1948() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1949() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1950() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1951() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), "foo", new JSArray ().add (1).add (2))); }

@Test
public void testPushStack1952() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), "foo", new JSArray ().add (1).add (2))); }

@Test
public void testQueue1953() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"))); }

@Test
public void testQueue1954() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5))); }

@Test
public void testQueue1955() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"))); }

@Test
public void testQueue1956() { assertNotNull (JQuery.idRef ("any").queue ("foo")); }

@Test
public void testQueue1957() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testQueue1958() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testQueue1959() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testQueue1960() { assertNotNull (JQuery.idRef ("any").queue ("foo", JSExpr.lit ("foo"))); }

@Test
public void testQueue1961() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testQueue1962() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testQueue1963() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testQueue1964() { assertNotNull (JQuery.idRef ("any").queue ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testQueue1965() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testQueue1966() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testQueue1967() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testQueue1968() { assertNotNull (JQuery.idRef ("any").queue ("foo", new JSAnonymousFunction ())); }

@Test
public void testReady1969() { assertNotNull (JQuery.idRef ("any").ready (JSExpr.lit ("foo"))); }

@Test
public void testReady1970() { assertNotNull (JQuery.idRef ("any").ready (new JSAnonymousFunction ())); }

@Test
public void testRemove1971() { assertNotNull (JQuery.idRef ("any").remove (JSExpr.lit ("foo"))); }

@Test
public void testRemove1972() { assertNotNull (JQuery.idRef ("any").remove (new JsonObject ().add ("foo", 5))); }

@Test
public void testRemove1973() { assertNotNull (JQuery.idRef ("any").remove (new HCDiv ().addChild ("foo"))); }

@Test
public void testRemove1974() { assertNotNull (JQuery.idRef ("any").remove ("foo")); }

@Test
public void testRemoveAttr1975() { assertNotNull (JQuery.idRef ("any").removeAttr (JSExpr.lit ("foo"))); }

@Test
public void testRemoveAttr1976() { assertNotNull (JQuery.idRef ("any").removeAttr (new JsonObject ().add ("foo", 5))); }

@Test
public void testRemoveAttr1977() { assertNotNull (JQuery.idRef ("any").removeAttr (new HCDiv ().addChild ("foo"))); }

@Test
public void testRemoveAttr1978() { assertNotNull (JQuery.idRef ("any").removeAttr ("foo")); }

@Test
public void testRemoveAttr1979() { assertNotNull (JQuery.idRef ("any").removeAttr (new MicroQName ("foo"))); }

@Test
public void testRemoveClass1980() { assertNotNull (JQuery.idRef ("any").removeClass (JSExpr.lit ("foo"))); }

@Test
public void testRemoveClass1981() { assertNotNull (JQuery.idRef ("any").removeClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testRemoveClass1982() { assertNotNull (JQuery.idRef ("any").removeClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testRemoveClass1983() { assertNotNull (JQuery.idRef ("any").removeClass ("foo")); }

@Test
public void testRemoveClass1984() { assertNotNull (JQuery.idRef ("any").removeClass (new JSAnonymousFunction ())); }

@Test
public void testRemoveData1985() { assertNotNull (JQuery.idRef ("any").removeData (JSExpr.lit ("foo"))); }

@Test
public void testRemoveData1986() { assertNotNull (JQuery.idRef ("any").removeData (new JsonObject ().add ("foo", 5))); }

@Test
public void testRemoveData1987() { assertNotNull (JQuery.idRef ("any").removeData (new HCDiv ().addChild ("foo"))); }

@Test
public void testRemoveData1988() { assertNotNull (JQuery.idRef ("any").removeData ("foo")); }

@Test
public void testRemoveData1989() { assertNotNull (JQuery.idRef ("any").removeData (new JSArray ().add (1).add (2))); }

@Test
public void testRemoveProp1990() { assertNotNull (JQuery.idRef ("any").removeProp (JSExpr.lit ("foo"))); }

@Test
public void testRemoveProp1991() { assertNotNull (JQuery.idRef ("any").removeProp (new JsonObject ().add ("foo", 5))); }

@Test
public void testRemoveProp1992() { assertNotNull (JQuery.idRef ("any").removeProp (new HCDiv ().addChild ("foo"))); }

@Test
public void testRemoveProp1993() { assertNotNull (JQuery.idRef ("any").removeProp ("foo")); }

@Test
public void testReplaceAll1994() { assertNotNull (JQuery.idRef ("any").replaceAll (JSExpr.lit ("foo"))); }

@Test
public void testReplaceAll1995() { assertNotNull (JQuery.idRef ("any").replaceAll (JQuerySelector.eq (0))); }

@Test
public void testReplaceAll1996() { assertNotNull (JQuery.idRef ("any").replaceAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testReplaceAll1997() { assertNotNull (JQuery.idRef ("any").replaceAll (EHTMLElement.DIV)); }

@Test
public void testReplaceAll1998() { assertNotNull (JQuery.idRef ("any").replaceAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testReplaceAll1999() { assertNotNull (JQuery.idRef ("any").replaceAll (JQuery.idRef ("foo"))); }

@Test
public void testReplaceAll2000() { assertNotNull (JQuery.idRef ("any").replaceAll (new JSArray ().add (1).add (2))); }

@Test
public void testReplaceAll2001() { assertNotNull (JQuery.idRef ("any").replaceAll ("foo")); }

@Test
public void testReplaceWith2002() { assertNotNull (JQuery.idRef ("any").replaceWith (JSExpr.lit ("foo"))); }

@Test
public void testReplaceWith2003() { assertNotNull (JQuery.idRef ("any").replaceWith (new HCDiv ().addChild ("foo"))); }

@Test
public void testReplaceWith2004() { assertNotNull (JQuery.idRef ("any").replaceWith ("foo")); }

@Test
public void testReplaceWith2005() { assertNotNull (JQuery.idRef ("any").replaceWith (EHTMLElement.DIV)); }

@Test
public void testReplaceWith2006() { assertNotNull (JQuery.idRef ("any").replaceWith (new JSArray ().add (1).add (2))); }

@Test
public void testReplaceWith2007() { assertNotNull (JQuery.idRef ("any").replaceWith (JQuery.idRef ("foo"))); }

@Test
public void testReplaceWith2008() { assertNotNull (JQuery.idRef ("any").replaceWith (new JSAnonymousFunction ())); }

@Test
public void testResize2009() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"))); }

@Test
public void testResize2010() { assertNotNull (JQuery.idRef ("any").resize (new JSAnonymousFunction ())); }

@Test
public void testResize2011() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testResize2012() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testScroll2013() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"))); }

@Test
public void testScroll2014() { assertNotNull (JQuery.idRef ("any").scroll (new JSAnonymousFunction ())); }

@Test
public void testScroll2015() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testScroll2016() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testScrollLeft2017() { assertNotNull (JQuery.idRef ("any").scrollLeft (JSExpr.lit ("foo"))); }

@Test
public void testScrollLeft2018() { assertNotNull (JQuery.idRef ("any").scrollLeft (3456)); }

@Test
public void testScrollLeft2019() { assertNotNull (JQuery.idRef ("any").scrollLeft (87654321L)); }

@Test
public void testScrollLeft2020() { assertNotNull (JQuery.idRef ("any").scrollLeft (BigInteger.valueOf (3456))); }

@Test
public void testScrollLeft2021() { assertNotNull (JQuery.idRef ("any").scrollLeft (123.456)); }

@Test
public void testScrollLeft2022() { assertNotNull (JQuery.idRef ("any").scrollLeft (BigDecimal.valueOf (12.3456))); }

@Test
public void testScrollTop2023() { assertNotNull (JQuery.idRef ("any").scrollTop (JSExpr.lit ("foo"))); }

@Test
public void testScrollTop2024() { assertNotNull (JQuery.idRef ("any").scrollTop (3456)); }

@Test
public void testScrollTop2025() { assertNotNull (JQuery.idRef ("any").scrollTop (87654321L)); }

@Test
public void testScrollTop2026() { assertNotNull (JQuery.idRef ("any").scrollTop (BigInteger.valueOf (3456))); }

@Test
public void testScrollTop2027() { assertNotNull (JQuery.idRef ("any").scrollTop (123.456)); }

@Test
public void testScrollTop2028() { assertNotNull (JQuery.idRef ("any").scrollTop (BigDecimal.valueOf (12.3456))); }

@Test
public void testSelect2029() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"))); }

@Test
public void testSelect2030() { assertNotNull (JQuery.idRef ("any").select (new JSAnonymousFunction ())); }

@Test
public void testSelect2031() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testSelect2032() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testShow2033() { assertNotNull (JQuery.idRef ("any").show (JSExpr.lit ("foo"))); }

@Test
public void testShow2034() { assertNotNull (JQuery.idRef ("any").show (3456)); }

@Test
public void testShow2035() { assertNotNull (JQuery.idRef ("any").show (87654321L)); }

@Test
public void testShow2036() { assertNotNull (JQuery.idRef ("any").show (BigInteger.valueOf (3456))); }

@Test
public void testShow2037() { assertNotNull (JQuery.idRef ("any").show (123.456)); }

@Test
public void testShow2038() { assertNotNull (JQuery.idRef ("any").show (BigDecimal.valueOf (12.3456))); }

@Test
public void testShow2039() { assertNotNull (JQuery.idRef ("any").show (new JsonObject ().add ("foo", 5))); }

@Test
public void testShow2040() { assertNotNull (JQuery.idRef ("any").show (new HCDiv ().addChild ("foo"))); }

@Test
public void testShow2041() { assertNotNull (JQuery.idRef ("any").show ("foo")); }

@Test
public void testSiblings2042() { assertNotNull (JQuery.idRef ("any").siblings (JSExpr.lit ("foo"))); }

@Test
public void testSiblings2043() { assertNotNull (JQuery.idRef ("any").siblings (JQuerySelector.eq (0))); }

@Test
public void testSiblings2044() { assertNotNull (JQuery.idRef ("any").siblings (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testSiblings2045() { assertNotNull (JQuery.idRef ("any").siblings (EHTMLElement.DIV)); }

@Test
public void testSiblings2046() { assertNotNull (JQuery.idRef ("any").siblings (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testSlice2047() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"))); }

@Test
public void testSlice2048() { assertNotNull (JQuery.idRef ("any").slice (3456)); }

@Test
public void testSlice2049() { assertNotNull (JQuery.idRef ("any").slice (87654321L)); }

@Test
public void testSlice2050() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456))); }

@Test
public void testSlice2051() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testSlice2052() { assertNotNull (JQuery.idRef ("any").slice (3456, JSExpr.lit ("foo"))); }

@Test
public void testSlice2053() { assertNotNull (JQuery.idRef ("any").slice (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testSlice2054() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testSlice2055() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), 3456)); }

@Test
public void testSlice2056() { assertNotNull (JQuery.idRef ("any").slice (3456, 3456)); }

@Test
public void testSlice2057() { assertNotNull (JQuery.idRef ("any").slice (87654321L, 3456)); }

@Test
public void testSlice2058() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), 3456)); }

@Test
public void testSlice2059() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testSlice2060() { assertNotNull (JQuery.idRef ("any").slice (3456, 87654321L)); }

@Test
public void testSlice2061() { assertNotNull (JQuery.idRef ("any").slice (87654321L, 87654321L)); }

@Test
public void testSlice2062() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), 87654321L)); }

@Test
public void testSlice2063() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testSlice2064() { assertNotNull (JQuery.idRef ("any").slice (3456, BigInteger.valueOf (3456))); }

@Test
public void testSlice2065() { assertNotNull (JQuery.idRef ("any").slice (87654321L, BigInteger.valueOf (3456))); }

@Test
public void testSlice2066() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), BigInteger.valueOf (3456))); }

@Test
public void testStop2067() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"))); }

@Test
public void testStop2068() { assertNotNull (JQuery.idRef ("any").stop (true)); }

@Test
public void testStop2069() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2070() { assertNotNull (JQuery.idRef ("any").stop (true, JSExpr.lit ("foo"))); }

@Test
public void testStop2071() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true)); }

@Test
public void testStop2072() { assertNotNull (JQuery.idRef ("any").stop (true, true)); }

@Test
public void testStop2073() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5))); }

@Test
public void testStop2074() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"))); }

@Test
public void testStop2075() { assertNotNull (JQuery.idRef ("any").stop ("foo")); }

@Test
public void testStop2076() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testStop2077() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2078() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"))); }

@Test
public void testStop2079() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true)); }

@Test
public void testStop2080() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true)); }

@Test
public void testStop2081() { assertNotNull (JQuery.idRef ("any").stop ("foo", true)); }

@Test
public void testStop2082() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2083() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2084() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2085() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testStop2086() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true, JSExpr.lit ("foo"))); }

@Test
public void testStop2087() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true, JSExpr.lit ("foo"))); }

@Test
public void testStop2088() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true, JSExpr.lit ("foo"))); }

@Test
public void testStop2089() { assertNotNull (JQuery.idRef ("any").stop ("foo", true, JSExpr.lit ("foo"))); }

@Test
public void testStop2090() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void testStop2091() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), true)); }

@Test
public void testStop2092() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void testStop2093() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"), true)); }

@Test
public void testStop2094() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true, true)); }

@Test
public void testStop2095() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true, true)); }

@Test
public void testStop2096() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true, true)); }

@Test
public void testStop2097() { assertNotNull (JQuery.idRef ("any").stop ("foo", true, true)); }

@Test
public void testSubmit2098() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"))); }

@Test
public void testSubmit2099() { assertNotNull (JQuery.idRef ("any").submit (new JSAnonymousFunction ())); }

@Test
public void testSubmit2100() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testSubmit2101() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testText2102() { assertNotNull (JQuery.idRef ("any").text (JSExpr.lit ("foo"))); }

@Test
public void testText2103() { assertNotNull (JQuery.idRef ("any").text (new JsonObject ().add ("foo", 5))); }

@Test
public void testText2104() { assertNotNull (JQuery.idRef ("any").text (new HCDiv ().addChild ("foo"))); }

@Test
public void testText2105() { assertNotNull (JQuery.idRef ("any").text ("foo")); }

@Test
public void testText2106() { assertNotNull (JQuery.idRef ("any").text (3456)); }

@Test
public void testText2107() { assertNotNull (JQuery.idRef ("any").text (87654321L)); }

@Test
public void testText2108() { assertNotNull (JQuery.idRef ("any").text (BigInteger.valueOf (3456))); }

@Test
public void testText2109() { assertNotNull (JQuery.idRef ("any").text (123.456)); }

@Test
public void testText2110() { assertNotNull (JQuery.idRef ("any").text (BigDecimal.valueOf (12.3456))); }

@Test
public void testText2111() { assertNotNull (JQuery.idRef ("any").text (true)); }

@Test
public void testText2112() { assertNotNull (JQuery.idRef ("any").text (new JSAnonymousFunction ())); }

@Test
public void testToggle2113() { assertNotNull (JQuery.idRef ("any").toggle (JSExpr.lit ("foo"))); }

@Test
public void testToggle2114() { assertNotNull (JQuery.idRef ("any").toggle (3456)); }

@Test
public void testToggle2115() { assertNotNull (JQuery.idRef ("any").toggle (87654321L)); }

@Test
public void testToggle2116() { assertNotNull (JQuery.idRef ("any").toggle (BigInteger.valueOf (3456))); }

@Test
public void testToggle2117() { assertNotNull (JQuery.idRef ("any").toggle (123.456)); }

@Test
public void testToggle2118() { assertNotNull (JQuery.idRef ("any").toggle (BigDecimal.valueOf (12.3456))); }

@Test
public void testToggle2119() { assertNotNull (JQuery.idRef ("any").toggle (new JsonObject ().add ("foo", 5))); }

@Test
public void testToggle2120() { assertNotNull (JQuery.idRef ("any").toggle (new HCDiv ().addChild ("foo"))); }

@Test
public void testToggle2121() { assertNotNull (JQuery.idRef ("any").toggle ("foo")); }

@Test
public void testToggle2122() { assertNotNull (JQuery.idRef ("any").toggle (true)); }

@Test
public void testToggleClass2123() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2124() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testToggleClass2125() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testToggleClass2126() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo")); }

@Test
public void testToggleClass2127() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2128() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2129() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2130() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo", JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2131() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"), true)); }

@Test
public void testToggleClass2132() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5), true)); }

@Test
public void testToggleClass2133() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"), true)); }

@Test
public void testToggleClass2134() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo", true)); }

@Test
public void testToggleClass2135() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction ())); }

@Test
public void testToggleClass2136() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testToggleClass2137() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction (), true)); }

@Test
public void testTrigger2138() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"))); }

@Test
public void testTrigger2139() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5))); }

@Test
public void testTrigger2140() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"))); }

@Test
public void testTrigger2141() { assertNotNull (JQuery.idRef ("any").trigger ("foo")); }

@Test
public void testTrigger2142() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testTrigger2143() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testTrigger2144() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testTrigger2145() { assertNotNull (JQuery.idRef ("any").trigger ("foo", JSExpr.lit ("foo"))); }

@Test
public void testTrigger2146() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testTrigger2147() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testTrigger2148() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testTrigger2149() { assertNotNull (JQuery.idRef ("any").trigger ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testTriggerHandler2150() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"))); }

@Test
public void testTriggerHandler2151() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5))); }

@Test
public void testTriggerHandler2152() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"))); }

@Test
public void testTriggerHandler2153() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo")); }

@Test
public void testTriggerHandler2154() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testTriggerHandler2155() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testTriggerHandler2156() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testTriggerHandler2157() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo", JSExpr.lit ("foo"))); }

@Test
public void testTriggerHandler2158() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testTriggerHandler2159() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testTriggerHandler2160() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testTriggerHandler2161() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testUnwrap2162() { assertNotNull (JQuery.idRef ("any").unwrap (JSExpr.lit ("foo"))); }

@Test
public void testUnwrap2163() { assertNotNull (JQuery.idRef ("any").unwrap (new JsonObject ().add ("foo", 5))); }

@Test
public void testUnwrap2164() { assertNotNull (JQuery.idRef ("any").unwrap (new HCDiv ().addChild ("foo"))); }

@Test
public void testUnwrap2165() { assertNotNull (JQuery.idRef ("any").unwrap ("foo")); }

@Test
public void testVal2166() { assertNotNull (JQuery.idRef ("any").val (JSExpr.lit ("foo"))); }

@Test
public void testVal2167() { assertNotNull (JQuery.idRef ("any").val (new JsonObject ().add ("foo", 5))); }

@Test
public void testVal2168() { assertNotNull (JQuery.idRef ("any").val (new HCDiv ().addChild ("foo"))); }

@Test
public void testVal2169() { assertNotNull (JQuery.idRef ("any").val ("foo")); }

@Test
public void testVal2170() { assertNotNull (JQuery.idRef ("any").val (3456)); }

@Test
public void testVal2171() { assertNotNull (JQuery.idRef ("any").val (87654321L)); }

@Test
public void testVal2172() { assertNotNull (JQuery.idRef ("any").val (BigInteger.valueOf (3456))); }

@Test
public void testVal2173() { assertNotNull (JQuery.idRef ("any").val (123.456)); }

@Test
public void testVal2174() { assertNotNull (JQuery.idRef ("any").val (BigDecimal.valueOf (12.3456))); }

@Test
public void testVal2175() { assertNotNull (JQuery.idRef ("any").val (new JSArray ().add (1).add (2))); }

@Test
public void testVal2176() { assertNotNull (JQuery.idRef ("any").val (new JSAnonymousFunction ())); }

@Test
public void testWidth2177() { assertNotNull (JQuery.idRef ("any").width (JSExpr.lit ("foo"))); }

@Test
public void testWidth2178() { assertNotNull (JQuery.idRef ("any").width (new JsonObject ().add ("foo", 5))); }

@Test
public void testWidth2179() { assertNotNull (JQuery.idRef ("any").width (new HCDiv ().addChild ("foo"))); }

@Test
public void testWidth2180() { assertNotNull (JQuery.idRef ("any").width ("foo")); }

@Test
public void testWidth2181() { assertNotNull (JQuery.idRef ("any").width (3456)); }

@Test
public void testWidth2182() { assertNotNull (JQuery.idRef ("any").width (87654321L)); }

@Test
public void testWidth2183() { assertNotNull (JQuery.idRef ("any").width (BigInteger.valueOf (3456))); }

@Test
public void testWidth2184() { assertNotNull (JQuery.idRef ("any").width (123.456)); }

@Test
public void testWidth2185() { assertNotNull (JQuery.idRef ("any").width (BigDecimal.valueOf (12.3456))); }

@Test
public void testWidth2186() { assertNotNull (JQuery.idRef ("any").width (new JSAnonymousFunction ())); }

@Test
public void testWrap2187() { assertNotNull (JQuery.idRef ("any").wrap (JSExpr.lit ("foo"))); }

@Test
public void testWrap2188() { assertNotNull (JQuery.idRef ("any").wrap (JQuerySelector.eq (0))); }

@Test
public void testWrap2189() { assertNotNull (JQuery.idRef ("any").wrap (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testWrap2190() { assertNotNull (JQuery.idRef ("any").wrap (EHTMLElement.DIV)); }

@Test
public void testWrap2191() { assertNotNull (JQuery.idRef ("any").wrap (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testWrap2192() { assertNotNull (JQuery.idRef ("any").wrap (new HCDiv ().addChild ("foo"))); }

@Test
public void testWrap2193() { assertNotNull (JQuery.idRef ("any").wrap ("foo")); }

@Test
public void testWrap2194() { assertNotNull (JQuery.idRef ("any").wrap (JQuery.idRef ("foo"))); }

@Test
public void testWrap2195() { assertNotNull (JQuery.idRef ("any").wrap (new JSAnonymousFunction ())); }

@Test
public void testWrapAll2196() { assertNotNull (JQuery.idRef ("any").wrapAll (JSExpr.lit ("foo"))); }

@Test
public void testWrapAll2197() { assertNotNull (JQuery.idRef ("any").wrapAll (JQuerySelector.eq (0))); }

@Test
public void testWrapAll2198() { assertNotNull (JQuery.idRef ("any").wrapAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testWrapAll2199() { assertNotNull (JQuery.idRef ("any").wrapAll (EHTMLElement.DIV)); }

@Test
public void testWrapAll2200() { assertNotNull (JQuery.idRef ("any").wrapAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testWrapAll2201() { assertNotNull (JQuery.idRef ("any").wrapAll (new HCDiv ().addChild ("foo"))); }

@Test
public void testWrapAll2202() { assertNotNull (JQuery.idRef ("any").wrapAll ("foo")); }

@Test
public void testWrapAll2203() { assertNotNull (JQuery.idRef ("any").wrapAll (JQuery.idRef ("foo"))); }

@Test
public void testWrapAll2204() { assertNotNull (JQuery.idRef ("any").wrapAll (new JSAnonymousFunction ())); }

@Test
public void testWrapInner2205() { assertNotNull (JQuery.idRef ("any").wrapInner (JSExpr.lit ("foo"))); }

@Test
public void testWrapInner2206() { assertNotNull (JQuery.idRef ("any").wrapInner (new HCDiv ().addChild ("foo"))); }

@Test
public void testWrapInner2207() { assertNotNull (JQuery.idRef ("any").wrapInner ("foo")); }

@Test
public void testWrapInner2208() { assertNotNull (JQuery.idRef ("any").wrapInner (JQuerySelector.eq (0))); }

@Test
public void testWrapInner2209() { assertNotNull (JQuery.idRef ("any").wrapInner (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testWrapInner2210() { assertNotNull (JQuery.idRef ("any").wrapInner (EHTMLElement.DIV)); }

@Test
public void testWrapInner2211() { assertNotNull (JQuery.idRef ("any").wrapInner (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testWrapInner2212() { assertNotNull (JQuery.idRef ("any").wrapInner (JQuery.idRef ("foo"))); }

@Test
public void testWrapInner2213() { assertNotNull (JQuery.idRef ("any").wrapInner (new JSAnonymousFunction ())); }

}
