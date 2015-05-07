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

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;


import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSExpr;
import com.helger.json.impl.JsonObject;

/**
 * Unit test class for class @{link AbstractJQueryInvocation}
 *
 * This file is generated - do NOT edit!
 * @author com.helger.html.supplementary.jquery.Main_AbstractJQueryInvocationExtendedTest
*/
@SuppressWarnings ("deprecation")
public final class AbstractJQueryInvocationExtendedTest
{
@Test
public void testadd0() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"))); }

@Test
public void testadd1() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0))); }

@Test
public void testadd2() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testadd3() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV)); }

@Test
public void testadd4() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testadd5() { assertNotNull (JQuery.idRef ("any").add ("foo")); }

@Test
public void testadd6() { assertNotNull (JQuery.idRef ("any").add (new HCDiv ().addChild ("foo"))); }

@Test
public void testadd7() { assertNotNull (JQuery.idRef ("any").add (JQuery.idRef ("foo"))); }

@Test
public void testadd8() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testadd9() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testadd10() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testadd11() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testadd12() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testadd13() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testadd14() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testadd15() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testadd16() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testadd17() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testadd18() { assertNotNull (JQuery.idRef ("any").add (JSExpr.lit ("foo"), "foo")); }

@Test
public void testadd19() { assertNotNull (JQuery.idRef ("any").add (JQuerySelector.eq (0), "foo")); }

@Test
public void testadd20() { assertNotNull (JQuery.idRef ("any").add (new JQuerySelectorList (JQuerySelector.lt (3)), "foo")); }

@Test
public void testadd21() { assertNotNull (JQuery.idRef ("any").add (EHTMLElement.DIV, "foo")); }

@Test
public void testadd22() { assertNotNull (JQuery.idRef ("any").add (DefaultCSSClassProvider.create ("cssclass"), "foo")); }

@Test
public void testaddBack23() { assertNotNull (JQuery.idRef ("any").addBack (JSExpr.lit ("foo"))); }

@Test
public void testaddBack24() { assertNotNull (JQuery.idRef ("any").addBack (JQuerySelector.eq (0))); }

@Test
public void testaddBack25() { assertNotNull (JQuery.idRef ("any").addBack (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testaddBack26() { assertNotNull (JQuery.idRef ("any").addBack (EHTMLElement.DIV)); }

@Test
public void testaddBack27() { assertNotNull (JQuery.idRef ("any").addBack (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testaddClass28() { assertNotNull (JQuery.idRef ("any").addClass (JSExpr.lit ("foo"))); }

@Test
public void testaddClass29() { assertNotNull (JQuery.idRef ("any").addClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testaddClass30() { assertNotNull (JQuery.idRef ("any").addClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testaddClass31() { assertNotNull (JQuery.idRef ("any").addClass ("foo")); }

@Test
public void testaddClass32() { assertNotNull (JQuery.idRef ("any").addClass (new JSAnonymousFunction ())); }

@Test
public void testafter33() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"))); }

@Test
public void testafter34() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"))); }

@Test
public void testafter35() { assertNotNull (JQuery.idRef ("any").after ("foo")); }

@Test
public void testafter36() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV)); }

@Test
public void testafter37() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2))); }

@Test
public void testafter38() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"))); }

@Test
public void testafter39() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testafter40() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testafter41() { assertNotNull (JQuery.idRef ("any").after ("foo", JSExpr.lit ("foo"))); }

@Test
public void testafter42() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testafter43() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testafter44() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testafter45() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testafter46() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testafter47() { assertNotNull (JQuery.idRef ("any").after ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testafter48() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testafter49() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testafter50() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testafter51() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), "foo")); }

@Test
public void testafter52() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testafter53() { assertNotNull (JQuery.idRef ("any").after ("foo", "foo")); }

@Test
public void testafter54() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, "foo")); }

@Test
public void testafter55() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testafter56() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), "foo")); }

@Test
public void testafter57() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testafter58() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testafter59() { assertNotNull (JQuery.idRef ("any").after ("foo", EHTMLElement.DIV)); }

@Test
public void testafter60() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testafter61() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testafter62() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testafter63() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testafter64() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testafter65() { assertNotNull (JQuery.idRef ("any").after ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testafter66() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testafter67() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testafter68() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testafter69() { assertNotNull (JQuery.idRef ("any").after (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testafter70() { assertNotNull (JQuery.idRef ("any").after (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testafter71() { assertNotNull (JQuery.idRef ("any").after ("foo", JQuery.idRef ("foo"))); }

@Test
public void testafter72() { assertNotNull (JQuery.idRef ("any").after (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testafter73() { assertNotNull (JQuery.idRef ("any").after (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testafter74() { assertNotNull (JQuery.idRef ("any").after (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testafter75() { assertNotNull (JQuery.idRef ("any").after (new JSAnonymousFunction ())); }

@Test
public void testajaxComplete76() { assertNotNull (JQuery.idRef ("any").ajaxComplete (JSExpr.lit ("foo"))); }

@Test
public void testajaxComplete77() { assertNotNull (JQuery.idRef ("any").ajaxComplete (new JSAnonymousFunction ())); }

@Test
public void testajaxError78() { assertNotNull (JQuery.idRef ("any").ajaxError (JSExpr.lit ("foo"))); }

@Test
public void testajaxError79() { assertNotNull (JQuery.idRef ("any").ajaxError (new JSAnonymousFunction ())); }

@Test
public void testajaxSend80() { assertNotNull (JQuery.idRef ("any").ajaxSend (JSExpr.lit ("foo"))); }

@Test
public void testajaxSend81() { assertNotNull (JQuery.idRef ("any").ajaxSend (new JSAnonymousFunction ())); }

@Test
public void testajaxStart82() { assertNotNull (JQuery.idRef ("any").ajaxStart (JSExpr.lit ("foo"))); }

@Test
public void testajaxStart83() { assertNotNull (JQuery.idRef ("any").ajaxStart (new JSAnonymousFunction ())); }

@Test
public void testajaxStop84() { assertNotNull (JQuery.idRef ("any").ajaxStop (JSExpr.lit ("foo"))); }

@Test
public void testajaxStop85() { assertNotNull (JQuery.idRef ("any").ajaxStop (new JSAnonymousFunction ())); }

@Test
public void testajaxSuccess86() { assertNotNull (JQuery.idRef ("any").ajaxSuccess (JSExpr.lit ("foo"))); }

@Test
public void testajaxSuccess87() { assertNotNull (JQuery.idRef ("any").ajaxSuccess (new JSAnonymousFunction ())); }

@Test
public void testanimate88() { assertNotNull (JQuery.idRef ("any").animate (JSExpr.lit ("foo"))); }

@Test
public void testappend89() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"))); }

@Test
public void testappend90() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"))); }

@Test
public void testappend91() { assertNotNull (JQuery.idRef ("any").append ("foo")); }

@Test
public void testappend92() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV)); }

@Test
public void testappend93() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2))); }

@Test
public void testappend94() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"))); }

@Test
public void testappend95() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testappend96() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testappend97() { assertNotNull (JQuery.idRef ("any").append ("foo", JSExpr.lit ("foo"))); }

@Test
public void testappend98() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testappend99() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testappend100() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testappend101() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testappend102() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testappend103() { assertNotNull (JQuery.idRef ("any").append ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testappend104() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testappend105() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testappend106() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testappend107() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), "foo")); }

@Test
public void testappend108() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testappend109() { assertNotNull (JQuery.idRef ("any").append ("foo", "foo")); }

@Test
public void testappend110() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, "foo")); }

@Test
public void testappend111() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testappend112() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), "foo")); }

@Test
public void testappend113() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testappend114() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testappend115() { assertNotNull (JQuery.idRef ("any").append ("foo", EHTMLElement.DIV)); }

@Test
public void testappend116() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testappend117() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testappend118() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testappend119() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testappend120() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testappend121() { assertNotNull (JQuery.idRef ("any").append ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testappend122() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testappend123() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testappend124() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testappend125() { assertNotNull (JQuery.idRef ("any").append (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testappend126() { assertNotNull (JQuery.idRef ("any").append (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testappend127() { assertNotNull (JQuery.idRef ("any").append ("foo", JQuery.idRef ("foo"))); }

@Test
public void testappend128() { assertNotNull (JQuery.idRef ("any").append (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testappend129() { assertNotNull (JQuery.idRef ("any").append (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testappend130() { assertNotNull (JQuery.idRef ("any").append (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testappend131() { assertNotNull (JQuery.idRef ("any").append (new JSAnonymousFunction ())); }

@Test
public void testappendTo132() { assertNotNull (JQuery.idRef ("any").appendTo (JSExpr.lit ("foo"))); }

@Test
public void testappendTo133() { assertNotNull (JQuery.idRef ("any").appendTo (JQuerySelector.eq (0))); }

@Test
public void testappendTo134() { assertNotNull (JQuery.idRef ("any").appendTo (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testappendTo135() { assertNotNull (JQuery.idRef ("any").appendTo (EHTMLElement.DIV)); }

@Test
public void testappendTo136() { assertNotNull (JQuery.idRef ("any").appendTo (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testappendTo137() { assertNotNull (JQuery.idRef ("any").appendTo (new HCDiv ().addChild ("foo"))); }

@Test
public void testappendTo138() { assertNotNull (JQuery.idRef ("any").appendTo ("foo")); }

@Test
public void testappendTo139() { assertNotNull (JQuery.idRef ("any").appendTo (new JSArray ().add (1).add (2))); }

@Test
public void testappendTo140() { assertNotNull (JQuery.idRef ("any").appendTo (JQuery.idRef ("foo"))); }

@Test
public void testattr141() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"))); }

@Test
public void testattr142() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5))); }

@Test
public void testattr143() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"))); }

@Test
public void testattr144() { assertNotNull (JQuery.idRef ("any").attr ("foo")); }

@Test
public void testattr145() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testattr146() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testattr147() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testattr148() { assertNotNull (JQuery.idRef ("any").attr ("foo", JSExpr.lit ("foo"))); }

@Test
public void testattr149() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testattr150() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testattr151() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testattr152() { assertNotNull (JQuery.idRef ("any").attr ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testattr153() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testattr154() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testattr155() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testattr156() { assertNotNull (JQuery.idRef ("any").attr ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testattr157() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), "foo")); }

@Test
public void testattr158() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testattr159() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testattr160() { assertNotNull (JQuery.idRef ("any").attr ("foo", "foo")); }

@Test
public void testattr161() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 3456)); }

@Test
public void testattr162() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testattr163() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testattr164() { assertNotNull (JQuery.idRef ("any").attr ("foo", 3456)); }

@Test
public void testattr165() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testattr166() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testattr167() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testattr168() { assertNotNull (JQuery.idRef ("any").attr ("foo", 87654321L)); }

@Test
public void testattr169() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testattr170() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testattr171() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testattr172() { assertNotNull (JQuery.idRef ("any").attr ("foo", BigInteger.valueOf (3456))); }

@Test
public void testattr173() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testattr174() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testattr175() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testattr176() { assertNotNull (JQuery.idRef ("any").attr ("foo", 123.456)); }

@Test
public void testattr177() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testattr178() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testattr179() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testattr180() { assertNotNull (JQuery.idRef ("any").attr ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testattr181() { assertNotNull (JQuery.idRef ("any").attr (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testattr182() { assertNotNull (JQuery.idRef ("any").attr (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testattr183() { assertNotNull (JQuery.idRef ("any").attr (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testattr184() { assertNotNull (JQuery.idRef ("any").attr ("foo", new JSAnonymousFunction ())); }

@Test
public void testbefore185() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"))); }

@Test
public void testbefore186() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore187() { assertNotNull (JQuery.idRef ("any").before ("foo")); }

@Test
public void testbefore188() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV)); }

@Test
public void testbefore189() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2))); }

@Test
public void testbefore190() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"))); }

@Test
public void testbefore191() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbefore192() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbefore193() { assertNotNull (JQuery.idRef ("any").before ("foo", JSExpr.lit ("foo"))); }

@Test
public void testbefore194() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testbefore195() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testbefore196() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbefore197() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore198() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore199() { assertNotNull (JQuery.idRef ("any").before ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore200() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore201() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore202() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testbefore203() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), "foo")); }

@Test
public void testbefore204() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testbefore205() { assertNotNull (JQuery.idRef ("any").before ("foo", "foo")); }

@Test
public void testbefore206() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, "foo")); }

@Test
public void testbefore207() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testbefore208() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), "foo")); }

@Test
public void testbefore209() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testbefore210() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testbefore211() { assertNotNull (JQuery.idRef ("any").before ("foo", EHTMLElement.DIV)); }

@Test
public void testbefore212() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testbefore213() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testbefore214() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testbefore215() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testbefore216() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testbefore217() { assertNotNull (JQuery.idRef ("any").before ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testbefore218() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testbefore219() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testbefore220() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testbefore221() { assertNotNull (JQuery.idRef ("any").before (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testbefore222() { assertNotNull (JQuery.idRef ("any").before (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testbefore223() { assertNotNull (JQuery.idRef ("any").before ("foo", JQuery.idRef ("foo"))); }

@Test
public void testbefore224() { assertNotNull (JQuery.idRef ("any").before (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testbefore225() { assertNotNull (JQuery.idRef ("any").before (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testbefore226() { assertNotNull (JQuery.idRef ("any").before (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testbefore227() { assertNotNull (JQuery.idRef ("any").before (new JSAnonymousFunction ())); }

@Test
public void testbind228() { assertNotNull (JQuery.idRef ("any").bind (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind229() { assertNotNull (JQuery.idRef ("any").bind (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testbind230() { assertNotNull (JQuery.idRef ("any").bind (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind231() { assertNotNull (JQuery.idRef ("any").bind ("foo", JSExpr.lit ("foo"))); }

@Test
public void testbind232() { assertNotNull (JQuery.idRef ("any").bind (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind233() { assertNotNull (JQuery.idRef ("any").bind (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind234() { assertNotNull (JQuery.idRef ("any").bind (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind235() { assertNotNull (JQuery.idRef ("any").bind ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testbind236() { assertNotNull (JQuery.idRef ("any").bind (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testbind237() { assertNotNull (JQuery.idRef ("any").bind (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testbind238() { assertNotNull (JQuery.idRef ("any").bind (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testbind239() { assertNotNull (JQuery.idRef ("any").bind ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testbind240() { assertNotNull (JQuery.idRef ("any").bind (JSExpr.lit ("foo"))); }

@Test
public void testbind241() { assertNotNull (JQuery.idRef ("any").bind (new JsonObject ().add ("foo", 5))); }

@Test
public void testbind242() { assertNotNull (JQuery.idRef ("any").bind (new HCDiv ().addChild ("foo"))); }

@Test
public void testbind243() { assertNotNull (JQuery.idRef ("any").bind ("foo")); }

@Test
public void testbind244() { assertNotNull (JQuery.idRef ("any").bind (JSExpr.lit ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void testbind245() { assertNotNull (JQuery.idRef ("any").bind (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), true)); }

@Test
public void testbind246() { assertNotNull (JQuery.idRef ("any").bind (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void testbind247() { assertNotNull (JQuery.idRef ("any").bind ("foo", JSExpr.lit ("foo"), true)); }

@Test
public void testblur248() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"))); }

@Test
public void testblur249() { assertNotNull (JQuery.idRef ("any").blur (new JSAnonymousFunction ())); }

@Test
public void testblur250() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testblur251() { assertNotNull (JQuery.idRef ("any").blur (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testcallbacks_add252() { assertNotNull (JQuery.idRef ("any").callbacks_add (JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_add253() { assertNotNull (JQuery.idRef ("any").callbacks_add (new JSAnonymousFunction ())); }

@Test
public void testcallbacks_add254() { assertNotNull (JQuery.idRef ("any").callbacks_add (new JSArray ().add (1).add (2))); }

@Test
public void testcallbacks_fire255() { assertNotNull (JQuery.idRef ("any").callbacks_fire (JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_fireWith256() { assertNotNull (JQuery.idRef ("any").callbacks_fireWith (JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_fireWith257() { assertNotNull (JQuery.idRef ("any").callbacks_fireWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_has258() { assertNotNull (JQuery.idRef ("any").callbacks_has (JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_has259() { assertNotNull (JQuery.idRef ("any").callbacks_has (new JSAnonymousFunction ())); }

@Test
public void testcallbacks_remove260() { assertNotNull (JQuery.idRef ("any").callbacks_remove (JSExpr.lit ("foo"))); }

@Test
public void testcallbacks_remove261() { assertNotNull (JQuery.idRef ("any").callbacks_remove (new JSAnonymousFunction ())); }

@Test
public void testcallbacks_remove262() { assertNotNull (JQuery.idRef ("any").callbacks_remove (new JSArray ().add (1).add (2))); }

@Test
public void testchange263() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"))); }

@Test
public void testchange264() { assertNotNull (JQuery.idRef ("any").change (new JSAnonymousFunction ())); }

@Test
public void testchange265() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testchange266() { assertNotNull (JQuery.idRef ("any").change (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testchildren267() { assertNotNull (JQuery.idRef ("any").children (JSExpr.lit ("foo"))); }

@Test
public void testchildren268() { assertNotNull (JQuery.idRef ("any").children (JQuerySelector.eq (0))); }

@Test
public void testchildren269() { assertNotNull (JQuery.idRef ("any").children (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testchildren270() { assertNotNull (JQuery.idRef ("any").children (EHTMLElement.DIV)); }

@Test
public void testchildren271() { assertNotNull (JQuery.idRef ("any").children (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testclearQueue272() { assertNotNull (JQuery.idRef ("any").clearQueue (JSExpr.lit ("foo"))); }

@Test
public void testclearQueue273() { assertNotNull (JQuery.idRef ("any").clearQueue (new JsonObject ().add ("foo", 5))); }

@Test
public void testclearQueue274() { assertNotNull (JQuery.idRef ("any").clearQueue (new HCDiv ().addChild ("foo"))); }

@Test
public void testclearQueue275() { assertNotNull (JQuery.idRef ("any").clearQueue ("foo")); }

@Test
public void testclick276() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"))); }

@Test
public void testclick277() { assertNotNull (JQuery.idRef ("any").click (new JSAnonymousFunction ())); }

@Test
public void testclick278() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testclick279() { assertNotNull (JQuery.idRef ("any").click (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void test_clone280() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"))); }

@Test
public void test_clone281() { assertNotNull (JQuery.idRef ("any")._clone (true)); }

@Test
public void test_clone282() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void test_clone283() { assertNotNull (JQuery.idRef ("any")._clone (true, JSExpr.lit ("foo"))); }

@Test
public void test_clone284() { assertNotNull (JQuery.idRef ("any")._clone (JSExpr.lit ("foo"), true)); }

@Test
public void test_clone285() { assertNotNull (JQuery.idRef ("any")._clone (true, true)); }

@Test
public void testclosest286() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"))); }

@Test
public void testclosest287() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0))); }

@Test
public void testclosest288() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testclosest289() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV)); }

@Test
public void testclosest290() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testclosest291() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testclosest292() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testclosest293() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testclosest294() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testclosest295() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testclosest296() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testclosest297() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testclosest298() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testclosest299() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testclosest300() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testclosest301() { assertNotNull (JQuery.idRef ("any").closest (JSExpr.lit ("foo"), "foo")); }

@Test
public void testclosest302() { assertNotNull (JQuery.idRef ("any").closest (JQuerySelector.eq (0), "foo")); }

@Test
public void testclosest303() { assertNotNull (JQuery.idRef ("any").closest (new JQuerySelectorList (JQuerySelector.lt (3)), "foo")); }

@Test
public void testclosest304() { assertNotNull (JQuery.idRef ("any").closest (EHTMLElement.DIV, "foo")); }

@Test
public void testclosest305() { assertNotNull (JQuery.idRef ("any").closest (DefaultCSSClassProvider.create ("cssclass"), "foo")); }

@Test
public void testclosest306() { assertNotNull (JQuery.idRef ("any").closest (JQuery.idRef ("foo"))); }

@Test
public void testclosest307() { assertNotNull (JQuery.idRef ("any").closest ("foo")); }

@Test
public void testcss308() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"))); }

@Test
public void testcss309() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5))); }

@Test
public void testcss310() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"))); }

@Test
public void testcss311() { assertNotNull (JQuery.idRef ("any").css ("foo")); }

@Test
public void testcss312() { assertNotNull (JQuery.idRef ("any").css (new JSArray ().add (1).add (2))); }

@Test
public void testcss313() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testcss314() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testcss315() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testcss316() { assertNotNull (JQuery.idRef ("any").css ("foo", JSExpr.lit ("foo"))); }

@Test
public void testcss317() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testcss318() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testcss319() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testcss320() { assertNotNull (JQuery.idRef ("any").css ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testcss321() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testcss322() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testcss323() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testcss324() { assertNotNull (JQuery.idRef ("any").css ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testcss325() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), "foo")); }

@Test
public void testcss326() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testcss327() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testcss328() { assertNotNull (JQuery.idRef ("any").css ("foo", "foo")); }

@Test
public void testcss329() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 3456)); }

@Test
public void testcss330() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testcss331() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testcss332() { assertNotNull (JQuery.idRef ("any").css ("foo", 3456)); }

@Test
public void testcss333() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testcss334() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testcss335() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testcss336() { assertNotNull (JQuery.idRef ("any").css ("foo", 87654321L)); }

@Test
public void testcss337() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testcss338() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testcss339() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testcss340() { assertNotNull (JQuery.idRef ("any").css ("foo", BigInteger.valueOf (3456))); }

@Test
public void testcss341() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testcss342() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testcss343() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testcss344() { assertNotNull (JQuery.idRef ("any").css ("foo", 123.456)); }

@Test
public void testcss345() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testcss346() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testcss347() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testcss348() { assertNotNull (JQuery.idRef ("any").css ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testcss349() { assertNotNull (JQuery.idRef ("any").css (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testcss350() { assertNotNull (JQuery.idRef ("any").css (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testcss351() { assertNotNull (JQuery.idRef ("any").css (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testcss352() { assertNotNull (JQuery.idRef ("any").css ("foo", new JSAnonymousFunction ())); }

@Test
public void testdata353() { assertNotNull (JQuery.idRef ("any").data (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdata354() { assertNotNull (JQuery.idRef ("any").data (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdata355() { assertNotNull (JQuery.idRef ("any").data (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdata356() { assertNotNull (JQuery.idRef ("any").data ("foo", JSExpr.lit ("foo"))); }

@Test
public void testdata357() { assertNotNull (JQuery.idRef ("any").data (JSExpr.lit ("foo"))); }

@Test
public void testdata358() { assertNotNull (JQuery.idRef ("any").data (new JsonObject ().add ("foo", 5))); }

@Test
public void testdata359() { assertNotNull (JQuery.idRef ("any").data (new HCDiv ().addChild ("foo"))); }

@Test
public void testdata360() { assertNotNull (JQuery.idRef ("any").data ("foo")); }

@Test
public void testdblclick361() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"))); }

@Test
public void testdblclick362() { assertNotNull (JQuery.idRef ("any").dblclick (new JSAnonymousFunction ())); }

@Test
public void testdblclick363() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdblclick364() { assertNotNull (JQuery.idRef ("any").dblclick (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_always365() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_always366() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction ())); }

@Test
public void testdeferred_always367() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_always368() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_always369() { assertNotNull (JQuery.idRef ("any").deferred_always (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_always370() { assertNotNull (JQuery.idRef ("any").deferred_always (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_done371() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_done372() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction ())); }

@Test
public void testdeferred_done373() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_done374() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_done375() { assertNotNull (JQuery.idRef ("any").deferred_done (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_done376() { assertNotNull (JQuery.idRef ("any").deferred_done (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_fail377() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_fail378() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction ())); }

@Test
public void testdeferred_fail379() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_fail380() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_fail381() { assertNotNull (JQuery.idRef ("any").deferred_fail (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_fail382() { assertNotNull (JQuery.idRef ("any").deferred_fail (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_notify383() { assertNotNull (JQuery.idRef ("any").deferred_notify (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_notifyWith384() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_notifyWith385() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_notifyWith386() { assertNotNull (JQuery.idRef ("any").deferred_notifyWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_progress387() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_progress388() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_progress389() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_progress390() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_progress391() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_progress392() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), new JSAnonymousFunction ())); }

@Test
public void testdeferred_progress393() { assertNotNull (JQuery.idRef ("any").deferred_progress (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_progress394() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSAnonymousFunction (), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_progress395() { assertNotNull (JQuery.idRef ("any").deferred_progress (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_promise396() { assertNotNull (JQuery.idRef ("any").deferred_promise (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_reject397() { assertNotNull (JQuery.idRef ("any").deferred_reject (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_rejectWith398() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_rejectWith399() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_rejectWith400() { assertNotNull (JQuery.idRef ("any").deferred_rejectWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_resolve401() { assertNotNull (JQuery.idRef ("any").deferred_resolve (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_resolveWith402() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_resolveWith403() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_resolveWith404() { assertNotNull (JQuery.idRef ("any").deferred_resolveWith (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testdeferred_then405() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then406() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction ())); }

@Test
public void testdeferred_then407() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then408() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then409() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then410() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then411() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_then412() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_then413() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_then414() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdeferred_then415() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then416() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testdeferred_then417() { assertNotNull (JQuery.idRef ("any").deferred_then (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdeferred_then418() { assertNotNull (JQuery.idRef ("any").deferred_then (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testdelay419() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"))); }

@Test
public void testdelay420() { assertNotNull (JQuery.idRef ("any").delay (3456)); }

@Test
public void testdelay421() { assertNotNull (JQuery.idRef ("any").delay (87654321L)); }

@Test
public void testdelay422() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456))); }

@Test
public void testdelay423() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelay424() { assertNotNull (JQuery.idRef ("any").delay (3456, JSExpr.lit ("foo"))); }

@Test
public void testdelay425() { assertNotNull (JQuery.idRef ("any").delay (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testdelay426() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testdelay427() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testdelay428() { assertNotNull (JQuery.idRef ("any").delay (3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testdelay429() { assertNotNull (JQuery.idRef ("any").delay (87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testdelay430() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testdelay431() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testdelay432() { assertNotNull (JQuery.idRef ("any").delay (3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testdelay433() { assertNotNull (JQuery.idRef ("any").delay (87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testdelay434() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testdelay435() { assertNotNull (JQuery.idRef ("any").delay (JSExpr.lit ("foo"), "foo")); }

@Test
public void testdelay436() { assertNotNull (JQuery.idRef ("any").delay (3456, "foo")); }

@Test
public void testdelay437() { assertNotNull (JQuery.idRef ("any").delay (87654321L, "foo")); }

@Test
public void testdelay438() { assertNotNull (JQuery.idRef ("any").delay (BigInteger.valueOf (3456), "foo")); }

@Test
public void testdelegate439() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate440() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate441() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate442() { assertNotNull (JQuery.idRef ("any").delegate ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate443() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdelegate444() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdelegate445() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdelegate446() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdelegate447() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate448() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate449() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate450() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate451() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testdelegate452() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testdelegate453() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testdelegate454() { assertNotNull (JQuery.idRef ("any").delegate ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testdelegate455() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate456() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate457() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate458() { assertNotNull (JQuery.idRef ("any").delegate ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate459() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testdelegate460() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testdelegate461() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testdelegate462() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testdelegate463() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate464() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate465() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate466() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate467() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testdelegate468() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testdelegate469() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testdelegate470() { assertNotNull (JQuery.idRef ("any").delegate ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testdelegate471() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate472() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate473() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate474() { assertNotNull (JQuery.idRef ("any").delegate ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate475() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate476() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate477() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate478() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate479() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate480() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate481() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate482() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate483() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate484() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate485() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate486() { assertNotNull (JQuery.idRef ("any").delegate ("foo", "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate487() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate488() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate489() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate490() { assertNotNull (JQuery.idRef ("any").delegate ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate491() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate492() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate493() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate494() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate495() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate496() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate497() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate498() { assertNotNull (JQuery.idRef ("any").delegate ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate499() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate500() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate501() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate502() { assertNotNull (JQuery.idRef ("any").delegate ("foo", "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testdelegate503() { assertNotNull (JQuery.idRef ("any").delegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate504() { assertNotNull (JQuery.idRef ("any").delegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testdelegate505() { assertNotNull (JQuery.idRef ("any").delegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testdelegate506() { assertNotNull (JQuery.idRef ("any").delegate ("foo", JSExpr.lit ("foo"))); }

@Test
public void testdequeue507() { assertNotNull (JQuery.idRef ("any").dequeue (JSExpr.lit ("foo"))); }

@Test
public void testdequeue508() { assertNotNull (JQuery.idRef ("any").dequeue (new JsonObject ().add ("foo", 5))); }

@Test
public void testdequeue509() { assertNotNull (JQuery.idRef ("any").dequeue (new HCDiv ().addChild ("foo"))); }

@Test
public void testdequeue510() { assertNotNull (JQuery.idRef ("any").dequeue ("foo")); }

@Test
public void testdetach511() { assertNotNull (JQuery.idRef ("any").detach (JSExpr.lit ("foo"))); }

@Test
public void testdetach512() { assertNotNull (JQuery.idRef ("any").detach (JQuerySelector.eq (0))); }

@Test
public void testdetach513() { assertNotNull (JQuery.idRef ("any").detach (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testdetach514() { assertNotNull (JQuery.idRef ("any").detach (EHTMLElement.DIV)); }

@Test
public void testdetach515() { assertNotNull (JQuery.idRef ("any").detach (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testeach516() { assertNotNull (JQuery.idRef ("any").each (JSExpr.lit ("foo"))); }

@Test
public void testeach517() { assertNotNull (JQuery.idRef ("any").each (new JSAnonymousFunction ())); }

@Test
public void test_eq518() { assertNotNull (JQuery.idRef ("any")._eq (JSExpr.lit ("foo"))); }

@Test
public void test_eq519() { assertNotNull (JQuery.idRef ("any")._eq (3456)); }

@Test
public void test_eq520() { assertNotNull (JQuery.idRef ("any")._eq (87654321L)); }

@Test
public void test_eq521() { assertNotNull (JQuery.idRef ("any")._eq (BigInteger.valueOf (3456))); }

@Test
public void testfadeTo522() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo523() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo524() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo525() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo526() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo527() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo528() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo529() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo530() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo531() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456)); }

@Test
public void testfadeTo532() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456)); }

@Test
public void testfadeTo533() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456)); }

@Test
public void testfadeTo534() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456)); }

@Test
public void testfadeTo535() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456)); }

@Test
public void testfadeTo536() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456)); }

@Test
public void testfadeTo537() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456)); }

@Test
public void testfadeTo538() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456)); }

@Test
public void testfadeTo539() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456)); }

@Test
public void testfadeTo540() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testfadeTo541() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L)); }

@Test
public void testfadeTo542() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L)); }

@Test
public void testfadeTo543() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L)); }

@Test
public void testfadeTo544() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L)); }

@Test
public void testfadeTo545() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L)); }

@Test
public void testfadeTo546() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L)); }

@Test
public void testfadeTo547() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L)); }

@Test
public void testfadeTo548() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L)); }

@Test
public void testfadeTo549() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testfadeTo550() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456))); }

@Test
public void testfadeTo551() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testfadeTo552() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456))); }

@Test
public void testfadeTo553() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456))); }

@Test
public void testfadeTo554() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456))); }

@Test
public void testfadeTo555() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456))); }

@Test
public void testfadeTo556() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456))); }

@Test
public void testfadeTo557() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456))); }

@Test
public void testfadeTo558() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456)); }

@Test
public void testfadeTo559() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456)); }

@Test
public void testfadeTo560() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456)); }

@Test
public void testfadeTo561() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456)); }

@Test
public void testfadeTo562() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456)); }

@Test
public void testfadeTo563() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456)); }

@Test
public void testfadeTo564() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456)); }

@Test
public void testfadeTo565() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456)); }

@Test
public void testfadeTo566() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456)); }

@Test
public void testfadeTo567() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo568() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo569() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo570() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo571() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo572() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo573() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo574() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo575() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456))); }

@Test
public void testfadeTo576() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo577() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo578() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo579() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo580() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo581() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo582() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo583() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo584() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo585() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo586() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo587() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo588() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo589() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo590() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo591() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo592() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo593() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo594() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo595() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo596() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo597() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo598() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo599() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo600() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo601() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo602() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo603() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo604() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo605() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo606() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo607() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo608() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo609() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo610() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo611() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo612() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo613() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo614() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo615() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo616() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo617() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo618() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo619() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo620() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"))); }

@Test
public void testfadeTo621() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo622() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo623() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo624() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo625() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo626() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo627() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo628() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo629() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo630() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo631() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo632() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo633() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo634() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo635() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo636() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo637() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo638() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo639() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo640() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo641() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo642() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo643() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo644() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo645() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo646() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo647() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo648() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo649() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo650() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo651() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo652() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo653() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo654() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo655() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo656() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JSAnonymousFunction ())); }

@Test
public void testfadeTo657() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo658() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo659() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo660() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo661() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo662() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo663() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo664() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo665() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo666() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo667() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo668() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo669() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo670() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo671() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo672() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo673() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo674() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JSAnonymousFunction ())); }

@Test
public void testfadeTo675() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo676() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo677() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo678() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo679() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo680() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo681() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo682() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo683() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JSAnonymousFunction ())); }

@Test
public void testfadeTo684() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo685() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo686() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo687() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo688() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo689() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo690() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo691() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo692() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo693() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo694() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo695() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo696() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo697() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo698() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo699() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo700() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo701() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo702() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo703() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo704() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo705() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo706() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo707() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo708() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo709() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo710() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo711() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo712() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo713() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo714() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo715() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo716() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo717() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo718() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo719() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo720() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo721() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo722() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo723() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo724() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo725() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo726() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo727() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo728() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo729() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo730() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo731() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo732() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo733() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo734() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo735() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo736() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo737() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5))); }

@Test
public void testfadeTo738() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo739() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo740() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo741() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo742() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo743() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo744() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo745() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo746() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo747() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo748() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo749() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo750() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo751() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo752() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo753() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo754() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo755() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo756() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo757() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo758() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo759() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo760() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo761() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo762() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo763() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo764() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo765() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo766() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo767() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo768() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo769() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo770() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo771() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo772() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo773() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo774() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo775() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo776() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo777() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo778() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo779() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo780() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo781() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo782() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo783() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo784() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo785() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo786() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo787() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo788() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo789() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo790() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo791() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"))); }

@Test
public void testfadeTo792() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo793() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo794() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo795() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo796() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo797() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo798() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo799() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo800() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo")); }

@Test
public void testfadeTo801() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo")); }

@Test
public void testfadeTo802() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo")); }

@Test
public void testfadeTo803() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo")); }

@Test
public void testfadeTo804() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo")); }

@Test
public void testfadeTo805() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo")); }

@Test
public void testfadeTo806() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo")); }

@Test
public void testfadeTo807() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo")); }

@Test
public void testfadeTo808() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo")); }

@Test
public void testfadeTo809() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo")); }

@Test
public void testfadeTo810() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo")); }

@Test
public void testfadeTo811() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo")); }

@Test
public void testfadeTo812() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo")); }

@Test
public void testfadeTo813() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo")); }

@Test
public void testfadeTo814() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo")); }

@Test
public void testfadeTo815() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo")); }

@Test
public void testfadeTo816() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo")); }

@Test
public void testfadeTo817() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo")); }

@Test
public void testfadeTo818() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo")); }

@Test
public void testfadeTo819() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo820() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo821() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo822() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo823() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo824() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo825() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo826() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo827() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo")); }

@Test
public void testfadeTo828() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo")); }

@Test
public void testfadeTo829() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo")); }

@Test
public void testfadeTo830() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo")); }

@Test
public void testfadeTo831() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo")); }

@Test
public void testfadeTo832() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo")); }

@Test
public void testfadeTo833() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo")); }

@Test
public void testfadeTo834() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo")); }

@Test
public void testfadeTo835() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo")); }

@Test
public void testfadeTo836() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo")); }

@Test
public void testfadeTo837() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo838() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo839() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo840() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo841() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo842() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo843() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo844() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo845() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo")); }

@Test
public void testfadeTo846() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo847() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo848() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo849() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo850() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo851() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo852() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo853() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo854() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo855() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo856() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo857() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo858() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo859() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo860() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo861() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo862() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo863() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo864() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo865() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo866() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo867() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo868() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo869() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo870() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo871() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo872() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo873() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo874() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo875() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo876() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo877() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo878() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo879() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo880() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo881() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo882() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo883() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo884() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo885() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo886() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo887() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo888() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo889() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo890() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo891() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo892() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo893() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo894() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo895() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo896() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo897() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo898() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo899() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo900() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo901() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo902() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo903() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo904() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo905() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo906() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo907() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo908() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo909() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo910() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo911() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo912() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo913() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo914() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo915() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo916() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo917() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo918() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo919() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo920() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo921() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo922() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo923() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo924() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo925() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo926() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo927() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo928() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo929() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo930() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo931() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo932() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo933() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo934() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo935() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo936() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo937() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo938() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo939() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo940() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo941() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo942() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo943() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo944() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo945() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo946() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo947() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo948() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo949() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo950() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo951() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo952() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo953() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo954() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo955() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo956() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo957() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo958() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo959() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo960() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo961() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo962() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo963() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo964() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo965() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo966() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo967() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo968() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo969() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo970() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo971() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo972() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo973() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo974() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo975() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo976() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo977() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo978() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo979() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo980() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo981() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo982() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo983() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo984() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo985() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo986() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo987() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo988() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo989() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo990() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo991() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo992() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo993() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo994() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo995() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo996() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo997() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo998() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo999() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1000() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1001() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1002() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1003() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1004() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1005() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1006() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1007() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1008() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1009() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1010() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1011() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1012() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1013() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1014() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1015() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1016() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1017() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1018() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1019() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1020() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1021() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1022() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1023() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1024() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1025() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1026() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1027() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1028() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1029() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1030() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1031() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1032() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1033() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1034() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1035() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1036() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1037() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1038() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1039() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1040() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1041() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1042() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1043() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1044() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1045() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1046() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1047() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1048() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1049() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1050() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1051() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1052() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1053() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1054() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1055() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1056() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1057() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1058() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1059() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1060() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1061() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo", JSExpr.lit ("foo"))); }

@Test
public void testfadeTo1062() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1063() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1064() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1065() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1066() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1067() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1068() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1069() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1070() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1071() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1072() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1073() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1074() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1075() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1076() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1077() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1078() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1079() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1080() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1081() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1082() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1083() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1084() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1085() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1086() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1087() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1088() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1089() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1090() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1091() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1092() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1093() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1094() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1095() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1096() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1097() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1098() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1099() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1100() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1101() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1102() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1103() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1104() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1105() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1106() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1107() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1108() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1109() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1110() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1111() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1112() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1113() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1114() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1115() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1116() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1117() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1118() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1119() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1120() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1121() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1122() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1123() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1124() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1125() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1126() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1127() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1128() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1129() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1130() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1131() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1132() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1133() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1134() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1135() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1136() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1137() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1138() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1139() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1140() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1141() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1142() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1143() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1144() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1145() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1146() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1147() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1148() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1149() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1150() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1151() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1152() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1153() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1154() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1155() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1156() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1157() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1158() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1159() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1160() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1161() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1162() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1163() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1164() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1165() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1166() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1167() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1168() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1169() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1170() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1171() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1172() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1173() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1174() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1175() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1176() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1177() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1178() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1179() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1180() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1181() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1182() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1183() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1184() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1185() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1186() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1187() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1188() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1189() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1190() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1191() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1192() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1193() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1194() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1195() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1196() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1197() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1198() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1199() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1200() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1201() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1202() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1203() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1204() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1205() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1206() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1207() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1208() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1209() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1210() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1211() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1212() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1213() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1214() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1215() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1216() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1217() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1218() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1219() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1220() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1221() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1222() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1223() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfadeTo1224() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1225() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1226() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1227() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1228() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1229() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1230() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1231() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1232() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1233() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1234() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1235() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1236() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1237() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1238() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1239() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1240() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1241() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 3456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1242() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1243() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1244() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1245() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1246() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1247() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1248() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1249() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1250() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 87654321L, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1251() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1252() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1253() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1254() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1255() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1256() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1257() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1258() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1259() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigInteger.valueOf (3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1260() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1261() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1262() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1263() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1264() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1265() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1266() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1267() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1268() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), 123.456, "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1269() { assertNotNull (JQuery.idRef ("any").fadeTo (JSExpr.lit ("foo"), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1270() { assertNotNull (JQuery.idRef ("any").fadeTo (new JsonObject ().add ("foo", 5), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1271() { assertNotNull (JQuery.idRef ("any").fadeTo (new HCDiv ().addChild ("foo"), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1272() { assertNotNull (JQuery.idRef ("any").fadeTo ("foo", BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1273() { assertNotNull (JQuery.idRef ("any").fadeTo (3456, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1274() { assertNotNull (JQuery.idRef ("any").fadeTo (87654321L, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1275() { assertNotNull (JQuery.idRef ("any").fadeTo (BigInteger.valueOf (3456), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1276() { assertNotNull (JQuery.idRef ("any").fadeTo (123.456, BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfadeTo1277() { assertNotNull (JQuery.idRef ("any").fadeTo (BigDecimal.valueOf (12.3456), BigDecimal.valueOf (12.3456), "foo", new JSAnonymousFunction ())); }

@Test
public void testfilter1278() { assertNotNull (JQuery.idRef ("any").filter (JSExpr.lit ("foo"))); }

@Test
public void testfilter1279() { assertNotNull (JQuery.idRef ("any").filter (JQuerySelector.eq (0))); }

@Test
public void testfilter1280() { assertNotNull (JQuery.idRef ("any").filter (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testfilter1281() { assertNotNull (JQuery.idRef ("any").filter (EHTMLElement.DIV)); }

@Test
public void testfilter1282() { assertNotNull (JQuery.idRef ("any").filter (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testfilter1283() { assertNotNull (JQuery.idRef ("any").filter (new JSAnonymousFunction ())); }

@Test
public void testfilter1284() { assertNotNull (JQuery.idRef ("any").filter ("foo")); }

@Test
public void testfilter1285() { assertNotNull (JQuery.idRef ("any").filter (JQuery.idRef ("foo"))); }

@Test
public void testfind1286() { assertNotNull (JQuery.idRef ("any").find (JSExpr.lit ("foo"))); }

@Test
public void testfind1287() { assertNotNull (JQuery.idRef ("any").find (JQuerySelector.eq (0))); }

@Test
public void testfind1288() { assertNotNull (JQuery.idRef ("any").find (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testfind1289() { assertNotNull (JQuery.idRef ("any").find (EHTMLElement.DIV)); }

@Test
public void testfind1290() { assertNotNull (JQuery.idRef ("any").find (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testfind1291() { assertNotNull (JQuery.idRef ("any").find ("foo")); }

@Test
public void testfind1292() { assertNotNull (JQuery.idRef ("any").find (JQuery.idRef ("foo"))); }

@Test
public void testfinish1293() { assertNotNull (JQuery.idRef ("any").finish (JSExpr.lit ("foo"))); }

@Test
public void testfinish1294() { assertNotNull (JQuery.idRef ("any").finish (new JsonObject ().add ("foo", 5))); }

@Test
public void testfinish1295() { assertNotNull (JQuery.idRef ("any").finish (new HCDiv ().addChild ("foo"))); }

@Test
public void testfinish1296() { assertNotNull (JQuery.idRef ("any").finish ("foo")); }

@Test
public void testfocus1297() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"))); }

@Test
public void testfocus1298() { assertNotNull (JQuery.idRef ("any").focus (new JSAnonymousFunction ())); }

@Test
public void testfocus1299() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfocus1300() { assertNotNull (JQuery.idRef ("any").focus (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfocusin1301() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"))); }

@Test
public void testfocusin1302() { assertNotNull (JQuery.idRef ("any").focusin (new JSAnonymousFunction ())); }

@Test
public void testfocusin1303() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfocusin1304() { assertNotNull (JQuery.idRef ("any").focusin (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testfocusout1305() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"))); }

@Test
public void testfocusout1306() { assertNotNull (JQuery.idRef ("any").focusout (new JSAnonymousFunction ())); }

@Test
public void testfocusout1307() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testfocusout1308() { assertNotNull (JQuery.idRef ("any").focusout (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testget1309() { assertNotNull (JQuery.idRef ("any").get (JSExpr.lit ("foo"))); }

@Test
public void testget1310() { assertNotNull (JQuery.idRef ("any").get (3456)); }

@Test
public void testget1311() { assertNotNull (JQuery.idRef ("any").get (87654321L)); }

@Test
public void testget1312() { assertNotNull (JQuery.idRef ("any").get (BigInteger.valueOf (3456))); }

@Test
public void testhas1313() { assertNotNull (JQuery.idRef ("any").has (JSExpr.lit ("foo"))); }

@Test
public void testhas1314() { assertNotNull (JQuery.idRef ("any").has (new JsonObject ().add ("foo", 5))); }

@Test
public void testhas1315() { assertNotNull (JQuery.idRef ("any").has (new HCDiv ().addChild ("foo"))); }

@Test
public void testhas1316() { assertNotNull (JQuery.idRef ("any").has ("foo")); }

@Test
public void testhas1317() { assertNotNull (JQuery.idRef ("any").has (EHTMLElement.DIV)); }

@Test
public void testhasClass1318() { assertNotNull (JQuery.idRef ("any").hasClass (JSExpr.lit ("foo"))); }

@Test
public void testhasClass1319() { assertNotNull (JQuery.idRef ("any").hasClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testhasClass1320() { assertNotNull (JQuery.idRef ("any").hasClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testhasClass1321() { assertNotNull (JQuery.idRef ("any").hasClass ("foo")); }

@Test
public void testheight1322() { assertNotNull (JQuery.idRef ("any").height (JSExpr.lit ("foo"))); }

@Test
public void testheight1323() { assertNotNull (JQuery.idRef ("any").height (new JsonObject ().add ("foo", 5))); }

@Test
public void testheight1324() { assertNotNull (JQuery.idRef ("any").height (new HCDiv ().addChild ("foo"))); }

@Test
public void testheight1325() { assertNotNull (JQuery.idRef ("any").height ("foo")); }

@Test
public void testheight1326() { assertNotNull (JQuery.idRef ("any").height (3456)); }

@Test
public void testheight1327() { assertNotNull (JQuery.idRef ("any").height (87654321L)); }

@Test
public void testheight1328() { assertNotNull (JQuery.idRef ("any").height (BigInteger.valueOf (3456))); }

@Test
public void testheight1329() { assertNotNull (JQuery.idRef ("any").height (123.456)); }

@Test
public void testheight1330() { assertNotNull (JQuery.idRef ("any").height (BigDecimal.valueOf (12.3456))); }

@Test
public void testheight1331() { assertNotNull (JQuery.idRef ("any").height (new JSAnonymousFunction ())); }

@Test
public void testhide1332() { assertNotNull (JQuery.idRef ("any").hide (JSExpr.lit ("foo"))); }

@Test
public void testhide1333() { assertNotNull (JQuery.idRef ("any").hide (3456)); }

@Test
public void testhide1334() { assertNotNull (JQuery.idRef ("any").hide (87654321L)); }

@Test
public void testhide1335() { assertNotNull (JQuery.idRef ("any").hide (BigInteger.valueOf (3456))); }

@Test
public void testhide1336() { assertNotNull (JQuery.idRef ("any").hide (123.456)); }

@Test
public void testhide1337() { assertNotNull (JQuery.idRef ("any").hide (BigDecimal.valueOf (12.3456))); }

@Test
public void testhide1338() { assertNotNull (JQuery.idRef ("any").hide (new JsonObject ().add ("foo", 5))); }

@Test
public void testhide1339() { assertNotNull (JQuery.idRef ("any").hide (new HCDiv ().addChild ("foo"))); }

@Test
public void testhide1340() { assertNotNull (JQuery.idRef ("any").hide ("foo")); }

@Test
public void testhover1341() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testhover1342() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testhover1343() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testhover1344() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction (), new JSAnonymousFunction ())); }

@Test
public void testhover1345() { assertNotNull (JQuery.idRef ("any").hover (JSExpr.lit ("foo"))); }

@Test
public void testhover1346() { assertNotNull (JQuery.idRef ("any").hover (new JSAnonymousFunction ())); }

@Test
public void testhtml1347() { assertNotNull (JQuery.idRef ("any").html (JSExpr.lit ("foo"))); }

@Test
public void testhtml1348() { assertNotNull (JQuery.idRef ("any").html (new HCDiv ().addChild ("foo"))); }

@Test
public void testhtml1349() { assertNotNull (JQuery.idRef ("any").html ("foo")); }

@Test
public void testhtml1350() { assertNotNull (JQuery.idRef ("any").html (new JSAnonymousFunction ())); }

@Test
public void testindex1351() { assertNotNull (JQuery.idRef ("any").index (JSExpr.lit ("foo"))); }

@Test
public void testindex1352() { assertNotNull (JQuery.idRef ("any").index (JQuerySelector.eq (0))); }

@Test
public void testindex1353() { assertNotNull (JQuery.idRef ("any").index (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testindex1354() { assertNotNull (JQuery.idRef ("any").index (EHTMLElement.DIV)); }

@Test
public void testindex1355() { assertNotNull (JQuery.idRef ("any").index (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testindex1356() { assertNotNull (JQuery.idRef ("any").index ("foo")); }

@Test
public void testindex1357() { assertNotNull (JQuery.idRef ("any").index (JQuery.idRef ("foo"))); }

@Test
public void testinnerHeight1358() { assertNotNull (JQuery.idRef ("any").innerHeight (JSExpr.lit ("foo"))); }

@Test
public void testinnerHeight1359() { assertNotNull (JQuery.idRef ("any").innerHeight (new JsonObject ().add ("foo", 5))); }

@Test
public void testinnerHeight1360() { assertNotNull (JQuery.idRef ("any").innerHeight (new HCDiv ().addChild ("foo"))); }

@Test
public void testinnerHeight1361() { assertNotNull (JQuery.idRef ("any").innerHeight ("foo")); }

@Test
public void testinnerHeight1362() { assertNotNull (JQuery.idRef ("any").innerHeight (3456)); }

@Test
public void testinnerHeight1363() { assertNotNull (JQuery.idRef ("any").innerHeight (87654321L)); }

@Test
public void testinnerHeight1364() { assertNotNull (JQuery.idRef ("any").innerHeight (BigInteger.valueOf (3456))); }

@Test
public void testinnerHeight1365() { assertNotNull (JQuery.idRef ("any").innerHeight (123.456)); }

@Test
public void testinnerHeight1366() { assertNotNull (JQuery.idRef ("any").innerHeight (BigDecimal.valueOf (12.3456))); }

@Test
public void testinnerHeight1367() { assertNotNull (JQuery.idRef ("any").innerHeight (new JSAnonymousFunction ())); }

@Test
public void testinnerWidth1368() { assertNotNull (JQuery.idRef ("any").innerWidth (JSExpr.lit ("foo"))); }

@Test
public void testinnerWidth1369() { assertNotNull (JQuery.idRef ("any").innerWidth (new JsonObject ().add ("foo", 5))); }

@Test
public void testinnerWidth1370() { assertNotNull (JQuery.idRef ("any").innerWidth (new HCDiv ().addChild ("foo"))); }

@Test
public void testinnerWidth1371() { assertNotNull (JQuery.idRef ("any").innerWidth ("foo")); }

@Test
public void testinnerWidth1372() { assertNotNull (JQuery.idRef ("any").innerWidth (3456)); }

@Test
public void testinnerWidth1373() { assertNotNull (JQuery.idRef ("any").innerWidth (87654321L)); }

@Test
public void testinnerWidth1374() { assertNotNull (JQuery.idRef ("any").innerWidth (BigInteger.valueOf (3456))); }

@Test
public void testinnerWidth1375() { assertNotNull (JQuery.idRef ("any").innerWidth (123.456)); }

@Test
public void testinnerWidth1376() { assertNotNull (JQuery.idRef ("any").innerWidth (BigDecimal.valueOf (12.3456))); }

@Test
public void testinnerWidth1377() { assertNotNull (JQuery.idRef ("any").innerWidth (new JSAnonymousFunction ())); }

@Test
public void testinsertAfter1378() { assertNotNull (JQuery.idRef ("any").insertAfter (JSExpr.lit ("foo"))); }

@Test
public void testinsertAfter1379() { assertNotNull (JQuery.idRef ("any").insertAfter (JQuerySelector.eq (0))); }

@Test
public void testinsertAfter1380() { assertNotNull (JQuery.idRef ("any").insertAfter (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testinsertAfter1381() { assertNotNull (JQuery.idRef ("any").insertAfter (EHTMLElement.DIV)); }

@Test
public void testinsertAfter1382() { assertNotNull (JQuery.idRef ("any").insertAfter (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testinsertAfter1383() { assertNotNull (JQuery.idRef ("any").insertAfter (new HCDiv ().addChild ("foo"))); }

@Test
public void testinsertAfter1384() { assertNotNull (JQuery.idRef ("any").insertAfter ("foo")); }

@Test
public void testinsertAfter1385() { assertNotNull (JQuery.idRef ("any").insertAfter (new JSArray ().add (1).add (2))); }

@Test
public void testinsertAfter1386() { assertNotNull (JQuery.idRef ("any").insertAfter (JQuery.idRef ("foo"))); }

@Test
public void testinsertBefore1387() { assertNotNull (JQuery.idRef ("any").insertBefore (JSExpr.lit ("foo"))); }

@Test
public void testinsertBefore1388() { assertNotNull (JQuery.idRef ("any").insertBefore (JQuerySelector.eq (0))); }

@Test
public void testinsertBefore1389() { assertNotNull (JQuery.idRef ("any").insertBefore (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testinsertBefore1390() { assertNotNull (JQuery.idRef ("any").insertBefore (EHTMLElement.DIV)); }

@Test
public void testinsertBefore1391() { assertNotNull (JQuery.idRef ("any").insertBefore (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testinsertBefore1392() { assertNotNull (JQuery.idRef ("any").insertBefore (new HCDiv ().addChild ("foo"))); }

@Test
public void testinsertBefore1393() { assertNotNull (JQuery.idRef ("any").insertBefore ("foo")); }

@Test
public void testinsertBefore1394() { assertNotNull (JQuery.idRef ("any").insertBefore (new JSArray ().add (1).add (2))); }

@Test
public void testinsertBefore1395() { assertNotNull (JQuery.idRef ("any").insertBefore (JQuery.idRef ("foo"))); }

@Test
public void testis1396() { assertNotNull (JQuery.idRef ("any").is (JSExpr.lit ("foo"))); }

@Test
public void testis1397() { assertNotNull (JQuery.idRef ("any").is (JQuerySelector.eq (0))); }

@Test
public void testis1398() { assertNotNull (JQuery.idRef ("any").is (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testis1399() { assertNotNull (JQuery.idRef ("any").is (EHTMLElement.DIV)); }

@Test
public void testis1400() { assertNotNull (JQuery.idRef ("any").is (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testis1401() { assertNotNull (JQuery.idRef ("any").is (new JSAnonymousFunction ())); }

@Test
public void testis1402() { assertNotNull (JQuery.idRef ("any").is (JQuery.idRef ("foo"))); }

@Test
public void testis1403() { assertNotNull (JQuery.idRef ("any").is ("foo")); }

@Test
public void testkeydown1404() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"))); }

@Test
public void testkeydown1405() { assertNotNull (JQuery.idRef ("any").keydown (new JSAnonymousFunction ())); }

@Test
public void testkeydown1406() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testkeydown1407() { assertNotNull (JQuery.idRef ("any").keydown (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testkeypress1408() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"))); }

@Test
public void testkeypress1409() { assertNotNull (JQuery.idRef ("any").keypress (new JSAnonymousFunction ())); }

@Test
public void testkeypress1410() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testkeypress1411() { assertNotNull (JQuery.idRef ("any").keypress (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testkeyup1412() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"))); }

@Test
public void testkeyup1413() { assertNotNull (JQuery.idRef ("any").keyup (new JSAnonymousFunction ())); }

@Test
public void testkeyup1414() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testkeyup1415() { assertNotNull (JQuery.idRef ("any").keyup (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1416() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"))); }

@Test
public void testload1417() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5))); }

@Test
public void testload1418() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"))); }

@Test
public void testload1419() { assertNotNull (JQuery.idRef ("any").load ("foo")); }

@Test
public void testload1420() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1421() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testload1422() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1423() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"))); }

@Test
public void testload1424() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testload1425() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testload1426() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testload1427() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testload1428() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testload1429() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testload1430() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testload1431() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testload1432() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo")); }

@Test
public void testload1433() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testload1434() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testload1435() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo")); }

@Test
public void testload1436() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1437() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1438() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1439() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1440() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testload1441() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testload1442() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testload1443() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testload1444() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1445() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1446() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1447() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testload1448() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testload1449() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testload1450() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testload1451() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testload1452() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1453() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1454() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1455() { assertNotNull (JQuery.idRef ("any").load ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1456() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testload1457() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testload1458() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testload1459() { assertNotNull (JQuery.idRef ("any").load ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testload1460() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1461() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1462() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1463() { assertNotNull (JQuery.idRef ("any").load ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testload1464() { assertNotNull (JQuery.idRef ("any").load (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testload1465() { assertNotNull (JQuery.idRef ("any").load (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testload1466() { assertNotNull (JQuery.idRef ("any").load (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testload1467() { assertNotNull (JQuery.idRef ("any").load ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testmap1468() { assertNotNull (JQuery.idRef ("any").map (JSExpr.lit ("foo"))); }

@Test
public void testmap1469() { assertNotNull (JQuery.idRef ("any").map (new JSAnonymousFunction ())); }

@Test
public void testmousedown1470() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"))); }

@Test
public void testmousedown1471() { assertNotNull (JQuery.idRef ("any").mousedown (new JSAnonymousFunction ())); }

@Test
public void testmousedown1472() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmousedown1473() { assertNotNull (JQuery.idRef ("any").mousedown (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmouseenter1474() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"))); }

@Test
public void testmouseenter1475() { assertNotNull (JQuery.idRef ("any").mouseenter (new JSAnonymousFunction ())); }

@Test
public void testmouseenter1476() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmouseenter1477() { assertNotNull (JQuery.idRef ("any").mouseenter (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmouseleave1478() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"))); }

@Test
public void testmouseleave1479() { assertNotNull (JQuery.idRef ("any").mouseleave (new JSAnonymousFunction ())); }

@Test
public void testmouseleave1480() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmouseleave1481() { assertNotNull (JQuery.idRef ("any").mouseleave (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmousemove1482() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"))); }

@Test
public void testmousemove1483() { assertNotNull (JQuery.idRef ("any").mousemove (new JSAnonymousFunction ())); }

@Test
public void testmousemove1484() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmousemove1485() { assertNotNull (JQuery.idRef ("any").mousemove (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmouseout1486() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"))); }

@Test
public void testmouseout1487() { assertNotNull (JQuery.idRef ("any").mouseout (new JSAnonymousFunction ())); }

@Test
public void testmouseout1488() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmouseout1489() { assertNotNull (JQuery.idRef ("any").mouseout (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmouseover1490() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"))); }

@Test
public void testmouseover1491() { assertNotNull (JQuery.idRef ("any").mouseover (new JSAnonymousFunction ())); }

@Test
public void testmouseover1492() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmouseover1493() { assertNotNull (JQuery.idRef ("any").mouseover (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testmouseup1494() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"))); }

@Test
public void testmouseup1495() { assertNotNull (JQuery.idRef ("any").mouseup (new JSAnonymousFunction ())); }

@Test
public void testmouseup1496() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testmouseup1497() { assertNotNull (JQuery.idRef ("any").mouseup (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testnext1498() { assertNotNull (JQuery.idRef ("any").next (JSExpr.lit ("foo"))); }

@Test
public void testnext1499() { assertNotNull (JQuery.idRef ("any").next (JQuerySelector.eq (0))); }

@Test
public void testnext1500() { assertNotNull (JQuery.idRef ("any").next (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnext1501() { assertNotNull (JQuery.idRef ("any").next (EHTMLElement.DIV)); }

@Test
public void testnext1502() { assertNotNull (JQuery.idRef ("any").next (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextAll1503() { assertNotNull (JQuery.idRef ("any").nextAll (JSExpr.lit ("foo"))); }

@Test
public void testnextAll1504() { assertNotNull (JQuery.idRef ("any").nextAll (new JsonObject ().add ("foo", 5))); }

@Test
public void testnextAll1505() { assertNotNull (JQuery.idRef ("any").nextAll (new HCDiv ().addChild ("foo"))); }

@Test
public void testnextAll1506() { assertNotNull (JQuery.idRef ("any").nextAll ("foo")); }

@Test
public void testnextUntil1507() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1508() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0))); }

@Test
public void testnextUntil1509() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1510() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV)); }

@Test
public void testnextUntil1511() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1512() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1513() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1514() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1515() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1516() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1517() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testnextUntil1518() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testnextUntil1519() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testnextUntil1520() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testnextUntil1521() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testnextUntil1522() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1523() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1524() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1525() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1526() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1527() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testnextUntil1528() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testnextUntil1529() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testnextUntil1530() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testnextUntil1531() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testnextUntil1532() { assertNotNull (JQuery.idRef ("any").nextUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1533() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1534() { assertNotNull (JQuery.idRef ("any").nextUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1535() { assertNotNull (JQuery.idRef ("any").nextUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1536() { assertNotNull (JQuery.idRef ("any").nextUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1537() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo")); }

@Test
public void testnextUntil1538() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"))); }

@Test
public void testnextUntil1539() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1540() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testnextUntil1541() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testnextUntil1542() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testnextUntil1543() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1544() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testnextUntil1545() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testnextUntil1546() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testnextUntil1547() { assertNotNull (JQuery.idRef ("any").nextUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testnextUntil1548() { assertNotNull (JQuery.idRef ("any").nextUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void test_not1549() { assertNotNull (JQuery.idRef ("any")._not (JSExpr.lit ("foo"))); }

@Test
public void test_not1550() { assertNotNull (JQuery.idRef ("any")._not (JQuerySelector.eq (0))); }

@Test
public void test_not1551() { assertNotNull (JQuery.idRef ("any")._not (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void test_not1552() { assertNotNull (JQuery.idRef ("any")._not (EHTMLElement.DIV)); }

@Test
public void test_not1553() { assertNotNull (JQuery.idRef ("any")._not (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void test_not1554() { assertNotNull (JQuery.idRef ("any")._not ("foo")); }

@Test
public void test_not1555() { assertNotNull (JQuery.idRef ("any")._not (new JSArray ().add (1).add (2))); }

@Test
public void test_not1556() { assertNotNull (JQuery.idRef ("any")._not (new JSAnonymousFunction ())); }

@Test
public void test_not1557() { assertNotNull (JQuery.idRef ("any")._not (JQuery.idRef ("foo"))); }

@Test
public void testoff1558() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"))); }

@Test
public void testoff1559() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5))); }

@Test
public void testoff1560() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"))); }

@Test
public void testoff1561() { assertNotNull (JQuery.idRef ("any").off ("foo")); }

@Test
public void testoff1562() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1563() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testoff1564() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1565() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"))); }

@Test
public void testoff1566() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testoff1567() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testoff1568() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testoff1569() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testoff1570() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testoff1571() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testoff1572() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testoff1573() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testoff1574() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo")); }

@Test
public void testoff1575() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testoff1576() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testoff1577() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo")); }

@Test
public void testoff1578() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1579() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1580() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1581() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1582() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testoff1583() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testoff1584() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testoff1585() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testoff1586() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1587() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1588() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1589() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testoff1590() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testoff1591() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testoff1592() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testoff1593() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testoff1594() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1595() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1596() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1597() { assertNotNull (JQuery.idRef ("any").off ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1598() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testoff1599() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testoff1600() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testoff1601() { assertNotNull (JQuery.idRef ("any").off ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testoff1602() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1603() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1604() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1605() { assertNotNull (JQuery.idRef ("any").off ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testoff1606() { assertNotNull (JQuery.idRef ("any").off (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testoff1607() { assertNotNull (JQuery.idRef ("any").off (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testoff1608() { assertNotNull (JQuery.idRef ("any").off (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testoff1609() { assertNotNull (JQuery.idRef ("any").off ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testoffset1610() { assertNotNull (JQuery.idRef ("any").offset (JSExpr.lit ("foo"))); }

@Test
public void testoffset1611() { assertNotNull (JQuery.idRef ("any").offset (new JSAnonymousFunction ())); }

@Test
public void teston1612() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1613() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teston1614() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1615() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"))); }

@Test
public void teston1616() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void teston1617() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void teston1618() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void teston1619() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void teston1620() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void teston1621() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void teston1622() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void teston1623() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void teston1624() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo")); }

@Test
public void teston1625() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void teston1626() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void teston1627() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo")); }

@Test
public void teston1628() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1629() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1630() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1631() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1632() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teston1633() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teston1634() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teston1635() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teston1636() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1637() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1638() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1639() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1640() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void teston1641() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void teston1642() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void teston1643() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void teston1644() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1645() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1646() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1647() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1648() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1649() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1650() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1651() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1652() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1653() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1654() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1655() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1656() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1657() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1658() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1659() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teston1660() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1661() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1662() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1663() { assertNotNull (JQuery.idRef ("any").on ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1664() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1665() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1666() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1667() { assertNotNull (JQuery.idRef ("any").on ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1668() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1669() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1670() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1671() { assertNotNull (JQuery.idRef ("any").on ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1672() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1673() { assertNotNull (JQuery.idRef ("any").on (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1674() { assertNotNull (JQuery.idRef ("any").on (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1675() { assertNotNull (JQuery.idRef ("any").on ("foo", "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void teston1676() { assertNotNull (JQuery.idRef ("any").on (JSExpr.lit ("foo"))); }

@Test
public void testone1677() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1678() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testone1679() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1680() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"))); }

@Test
public void testone1681() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1682() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1683() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1684() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1685() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1686() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1687() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1688() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1689() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testone1690() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testone1691() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testone1692() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testone1693() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testone1694() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testone1695() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testone1696() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testone1697() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo")); }

@Test
public void testone1698() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testone1699() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testone1700() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo")); }

@Test
public void testone1701() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testone1702() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testone1703() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testone1704() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testone1705() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1706() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1707() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1708() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1709() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testone1710() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testone1711() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testone1712() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testone1713() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1714() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1715() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1716() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1717() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1718() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1719() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1720() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1721() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1722() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1723() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1724() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1725() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1726() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1727() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1728() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testone1729() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1730() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1731() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1732() { assertNotNull (JQuery.idRef ("any").one ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1733() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1734() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1735() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1736() { assertNotNull (JQuery.idRef ("any").one ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1737() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1738() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1739() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1740() { assertNotNull (JQuery.idRef ("any").one ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1741() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1742() { assertNotNull (JQuery.idRef ("any").one (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1743() { assertNotNull (JQuery.idRef ("any").one (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1744() { assertNotNull (JQuery.idRef ("any").one ("foo", "foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testone1745() { assertNotNull (JQuery.idRef ("any").one (JSExpr.lit ("foo"))); }

@Test
public void testouterHeight1746() { assertNotNull (JQuery.idRef ("any").outerHeight (JSExpr.lit ("foo"))); }

@Test
public void testouterHeight1747() { assertNotNull (JQuery.idRef ("any").outerHeight (true)); }

@Test
public void testouterHeight1748() { assertNotNull (JQuery.idRef ("any").outerHeight (new JsonObject ().add ("foo", 5))); }

@Test
public void testouterHeight1749() { assertNotNull (JQuery.idRef ("any").outerHeight (new HCDiv ().addChild ("foo"))); }

@Test
public void testouterHeight1750() { assertNotNull (JQuery.idRef ("any").outerHeight ("foo")); }

@Test
public void testouterHeight1751() { assertNotNull (JQuery.idRef ("any").outerHeight (3456)); }

@Test
public void testouterHeight1752() { assertNotNull (JQuery.idRef ("any").outerHeight (87654321L)); }

@Test
public void testouterHeight1753() { assertNotNull (JQuery.idRef ("any").outerHeight (BigInteger.valueOf (3456))); }

@Test
public void testouterHeight1754() { assertNotNull (JQuery.idRef ("any").outerHeight (123.456)); }

@Test
public void testouterHeight1755() { assertNotNull (JQuery.idRef ("any").outerHeight (BigDecimal.valueOf (12.3456))); }

@Test
public void testouterHeight1756() { assertNotNull (JQuery.idRef ("any").outerHeight (new JSAnonymousFunction ())); }

@Test
public void testouterWidth1757() { assertNotNull (JQuery.idRef ("any").outerWidth (JSExpr.lit ("foo"))); }

@Test
public void testouterWidth1758() { assertNotNull (JQuery.idRef ("any").outerWidth (true)); }

@Test
public void testouterWidth1759() { assertNotNull (JQuery.idRef ("any").outerWidth (new JsonObject ().add ("foo", 5))); }

@Test
public void testouterWidth1760() { assertNotNull (JQuery.idRef ("any").outerWidth (new HCDiv ().addChild ("foo"))); }

@Test
public void testouterWidth1761() { assertNotNull (JQuery.idRef ("any").outerWidth ("foo")); }

@Test
public void testouterWidth1762() { assertNotNull (JQuery.idRef ("any").outerWidth (3456)); }

@Test
public void testouterWidth1763() { assertNotNull (JQuery.idRef ("any").outerWidth (87654321L)); }

@Test
public void testouterWidth1764() { assertNotNull (JQuery.idRef ("any").outerWidth (BigInteger.valueOf (3456))); }

@Test
public void testouterWidth1765() { assertNotNull (JQuery.idRef ("any").outerWidth (123.456)); }

@Test
public void testouterWidth1766() { assertNotNull (JQuery.idRef ("any").outerWidth (BigDecimal.valueOf (12.3456))); }

@Test
public void testouterWidth1767() { assertNotNull (JQuery.idRef ("any").outerWidth (new JSAnonymousFunction ())); }

@Test
public void testparent1768() { assertNotNull (JQuery.idRef ("any").parent (JSExpr.lit ("foo"))); }

@Test
public void testparent1769() { assertNotNull (JQuery.idRef ("any").parent (JQuerySelector.eq (0))); }

@Test
public void testparent1770() { assertNotNull (JQuery.idRef ("any").parent (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparent1771() { assertNotNull (JQuery.idRef ("any").parent (EHTMLElement.DIV)); }

@Test
public void testparent1772() { assertNotNull (JQuery.idRef ("any").parent (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparents1773() { assertNotNull (JQuery.idRef ("any").parents (JSExpr.lit ("foo"))); }

@Test
public void testparents1774() { assertNotNull (JQuery.idRef ("any").parents (JQuerySelector.eq (0))); }

@Test
public void testparents1775() { assertNotNull (JQuery.idRef ("any").parents (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparents1776() { assertNotNull (JQuery.idRef ("any").parents (EHTMLElement.DIV)); }

@Test
public void testparents1777() { assertNotNull (JQuery.idRef ("any").parents (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1778() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1779() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1780() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1781() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV)); }

@Test
public void testparentsUntil1782() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1783() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1784() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1785() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1786() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1787() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1788() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1789() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1790() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1791() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1792() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1793() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1794() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1795() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1796() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1797() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1798() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testparentsUntil1799() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testparentsUntil1800() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testparentsUntil1801() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testparentsUntil1802() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testparentsUntil1803() { assertNotNull (JQuery.idRef ("any").parentsUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1804() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1805() { assertNotNull (JQuery.idRef ("any").parentsUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1806() { assertNotNull (JQuery.idRef ("any").parentsUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1807() { assertNotNull (JQuery.idRef ("any").parentsUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1808() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo")); }

@Test
public void testparentsUntil1809() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"))); }

@Test
public void testparentsUntil1810() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1811() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testparentsUntil1812() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1813() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testparentsUntil1814() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1815() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testparentsUntil1816() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testparentsUntil1817() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testparentsUntil1818() { assertNotNull (JQuery.idRef ("any").parentsUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testparentsUntil1819() { assertNotNull (JQuery.idRef ("any").parentsUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprepend1820() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"))); }

@Test
public void testprepend1821() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1822() { assertNotNull (JQuery.idRef ("any").prepend ("foo")); }

@Test
public void testprepend1823() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV)); }

@Test
public void testprepend1824() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2))); }

@Test
public void testprepend1825() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"))); }

@Test
public void testprepend1826() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprepend1827() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprepend1828() { assertNotNull (JQuery.idRef ("any").prepend ("foo", JSExpr.lit ("foo"))); }

@Test
public void testprepend1829() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testprepend1830() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), JSExpr.lit ("foo"))); }

@Test
public void testprepend1831() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprepend1832() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1833() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1834() { assertNotNull (JQuery.idRef ("any").prepend ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1835() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1836() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1837() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testprepend1838() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), "foo")); }

@Test
public void testprepend1839() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testprepend1840() { assertNotNull (JQuery.idRef ("any").prepend ("foo", "foo")); }

@Test
public void testprepend1841() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, "foo")); }

@Test
public void testprepend1842() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), "foo")); }

@Test
public void testprepend1843() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), "foo")); }

@Test
public void testprepend1844() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testprepend1845() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), EHTMLElement.DIV)); }

@Test
public void testprepend1846() { assertNotNull (JQuery.idRef ("any").prepend ("foo", EHTMLElement.DIV)); }

@Test
public void testprepend1847() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testprepend1848() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), EHTMLElement.DIV)); }

@Test
public void testprepend1849() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testprepend1850() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testprepend1851() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testprepend1852() { assertNotNull (JQuery.idRef ("any").prepend ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testprepend1853() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, new JSArray ().add (1).add (2))); }

@Test
public void testprepend1854() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), new JSArray ().add (1).add (2))); }

@Test
public void testprepend1855() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testprepend1856() { assertNotNull (JQuery.idRef ("any").prepend (JSExpr.lit ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testprepend1857() { assertNotNull (JQuery.idRef ("any").prepend (new HCDiv ().addChild ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testprepend1858() { assertNotNull (JQuery.idRef ("any").prepend ("foo", JQuery.idRef ("foo"))); }

@Test
public void testprepend1859() { assertNotNull (JQuery.idRef ("any").prepend (EHTMLElement.DIV, JQuery.idRef ("foo"))); }

@Test
public void testprepend1860() { assertNotNull (JQuery.idRef ("any").prepend (new JSArray ().add (1).add (2), JQuery.idRef ("foo"))); }

@Test
public void testprepend1861() { assertNotNull (JQuery.idRef ("any").prepend (JQuery.idRef ("foo"), JQuery.idRef ("foo"))); }

@Test
public void testprepend1862() { assertNotNull (JQuery.idRef ("any").prepend (new JSAnonymousFunction ())); }

@Test
public void testprependTo1863() { assertNotNull (JQuery.idRef ("any").prependTo (JSExpr.lit ("foo"))); }

@Test
public void testprependTo1864() { assertNotNull (JQuery.idRef ("any").prependTo (JQuerySelector.eq (0))); }

@Test
public void testprependTo1865() { assertNotNull (JQuery.idRef ("any").prependTo (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprependTo1866() { assertNotNull (JQuery.idRef ("any").prependTo (EHTMLElement.DIV)); }

@Test
public void testprependTo1867() { assertNotNull (JQuery.idRef ("any").prependTo (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprependTo1868() { assertNotNull (JQuery.idRef ("any").prependTo (new HCDiv ().addChild ("foo"))); }

@Test
public void testprependTo1869() { assertNotNull (JQuery.idRef ("any").prependTo ("foo")); }

@Test
public void testprependTo1870() { assertNotNull (JQuery.idRef ("any").prependTo (new JSArray ().add (1).add (2))); }

@Test
public void testprependTo1871() { assertNotNull (JQuery.idRef ("any").prependTo (JQuery.idRef ("foo"))); }

@Test
public void testprev1872() { assertNotNull (JQuery.idRef ("any").prev (JSExpr.lit ("foo"))); }

@Test
public void testprev1873() { assertNotNull (JQuery.idRef ("any").prev (JQuerySelector.eq (0))); }

@Test
public void testprev1874() { assertNotNull (JQuery.idRef ("any").prev (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprev1875() { assertNotNull (JQuery.idRef ("any").prev (EHTMLElement.DIV)); }

@Test
public void testprev1876() { assertNotNull (JQuery.idRef ("any").prev (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevAll1877() { assertNotNull (JQuery.idRef ("any").prevAll (JSExpr.lit ("foo"))); }

@Test
public void testprevAll1878() { assertNotNull (JQuery.idRef ("any").prevAll (JQuerySelector.eq (0))); }

@Test
public void testprevAll1879() { assertNotNull (JQuery.idRef ("any").prevAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevAll1880() { assertNotNull (JQuery.idRef ("any").prevAll (EHTMLElement.DIV)); }

@Test
public void testprevAll1881() { assertNotNull (JQuery.idRef ("any").prevAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1882() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1883() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0))); }

@Test
public void testprevUntil1884() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1885() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV)); }

@Test
public void testprevUntil1886() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1887() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1888() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1889() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1890() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1891() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1892() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), JQuerySelector.eq (0))); }

@Test
public void testprevUntil1893() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), JQuerySelector.eq (0))); }

@Test
public void testprevUntil1894() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), JQuerySelector.eq (0))); }

@Test
public void testprevUntil1895() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, JQuerySelector.eq (0))); }

@Test
public void testprevUntil1896() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), JQuerySelector.eq (0))); }

@Test
public void testprevUntil1897() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1898() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1899() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1900() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1901() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1902() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), EHTMLElement.DIV)); }

@Test
public void testprevUntil1903() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), EHTMLElement.DIV)); }

@Test
public void testprevUntil1904() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), EHTMLElement.DIV)); }

@Test
public void testprevUntil1905() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, EHTMLElement.DIV)); }

@Test
public void testprevUntil1906() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), EHTMLElement.DIV)); }

@Test
public void testprevUntil1907() { assertNotNull (JQuery.idRef ("any").prevUntil (JSExpr.lit ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1908() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuerySelector.eq (0), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1909() { assertNotNull (JQuery.idRef ("any").prevUntil (new JQuerySelectorList (JQuerySelector.lt (3)), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1910() { assertNotNull (JQuery.idRef ("any").prevUntil (EHTMLElement.DIV, DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1911() { assertNotNull (JQuery.idRef ("any").prevUntil (DefaultCSSClassProvider.create ("cssclass"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1912() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo")); }

@Test
public void testprevUntil1913() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"))); }

@Test
public void testprevUntil1914() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1915() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprevUntil1916() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", JQuerySelector.eq (0))); }

@Test
public void testprevUntil1917() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), JQuerySelector.eq (0))); }

@Test
public void testprevUntil1918() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1919() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testprevUntil1920() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", EHTMLElement.DIV)); }

@Test
public void testprevUntil1921() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), EHTMLElement.DIV)); }

@Test
public void testprevUntil1922() { assertNotNull (JQuery.idRef ("any").prevUntil ("foo", DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testprevUntil1923() { assertNotNull (JQuery.idRef ("any").prevUntil (JQuery.idRef ("foo"), DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testpromise1924() { assertNotNull (JQuery.idRef ("any").promise (JSExpr.lit ("foo"))); }

@Test
public void testpromise1925() { assertNotNull (JQuery.idRef ("any").promise (new JsonObject ().add ("foo", 5))); }

@Test
public void testpromise1926() { assertNotNull (JQuery.idRef ("any").promise (new HCDiv ().addChild ("foo"))); }

@Test
public void testpromise1927() { assertNotNull (JQuery.idRef ("any").promise ("foo")); }

@Test
public void testpromise1928() { assertNotNull (JQuery.idRef ("any").promise (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpromise1929() { assertNotNull (JQuery.idRef ("any").promise (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testpromise1930() { assertNotNull (JQuery.idRef ("any").promise (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpromise1931() { assertNotNull (JQuery.idRef ("any").promise ("foo", JSExpr.lit ("foo"))); }

@Test
public void testprop1932() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"))); }

@Test
public void testprop1933() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5))); }

@Test
public void testprop1934() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"))); }

@Test
public void testprop1935() { assertNotNull (JQuery.idRef ("any").prop ("foo")); }

@Test
public void testprop1936() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprop1937() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testprop1938() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testprop1939() { assertNotNull (JQuery.idRef ("any").prop ("foo", JSExpr.lit ("foo"))); }

@Test
public void testprop1940() { assertNotNull (JQuery.idRef ("any").prop (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testprop1941() { assertNotNull (JQuery.idRef ("any").prop (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testprop1942() { assertNotNull (JQuery.idRef ("any").prop (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testprop1943() { assertNotNull (JQuery.idRef ("any").prop ("foo", new JSAnonymousFunction ())); }

@Test
public void testpushStack1944() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"))); }

@Test
public void testpushStack1945() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1946() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1947() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1948() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1949() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1950() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1951() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testpushStack1952() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testpushStack1953() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), "foo", JSExpr.lit ("foo"))); }

@Test
public void testpushStack1954() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1955() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1956() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1957() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1958() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1959() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1960() { assertNotNull (JQuery.idRef ("any").pushStack (JSExpr.lit ("foo"), "foo", new JSArray ().add (1).add (2))); }

@Test
public void testpushStack1961() { assertNotNull (JQuery.idRef ("any").pushStack (new JSArray ().add (1).add (2), "foo", new JSArray ().add (1).add (2))); }

@Test
public void testqueue1962() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"))); }

@Test
public void testqueue1963() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5))); }

@Test
public void testqueue1964() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"))); }

@Test
public void testqueue1965() { assertNotNull (JQuery.idRef ("any").queue ("foo")); }

@Test
public void testqueue1966() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testqueue1967() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testqueue1968() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testqueue1969() { assertNotNull (JQuery.idRef ("any").queue ("foo", JSExpr.lit ("foo"))); }

@Test
public void testqueue1970() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testqueue1971() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testqueue1972() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testqueue1973() { assertNotNull (JQuery.idRef ("any").queue ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testqueue1974() { assertNotNull (JQuery.idRef ("any").queue (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testqueue1975() { assertNotNull (JQuery.idRef ("any").queue (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testqueue1976() { assertNotNull (JQuery.idRef ("any").queue (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testqueue1977() { assertNotNull (JQuery.idRef ("any").queue ("foo", new JSAnonymousFunction ())); }

@Test
public void testready1978() { assertNotNull (JQuery.idRef ("any").ready (JSExpr.lit ("foo"))); }

@Test
public void testready1979() { assertNotNull (JQuery.idRef ("any").ready (new JSAnonymousFunction ())); }

@Test
public void testremove1980() { assertNotNull (JQuery.idRef ("any").remove (JSExpr.lit ("foo"))); }

@Test
public void testremove1981() { assertNotNull (JQuery.idRef ("any").remove (new JsonObject ().add ("foo", 5))); }

@Test
public void testremove1982() { assertNotNull (JQuery.idRef ("any").remove (new HCDiv ().addChild ("foo"))); }

@Test
public void testremove1983() { assertNotNull (JQuery.idRef ("any").remove ("foo")); }

@Test
public void testremoveAttr1984() { assertNotNull (JQuery.idRef ("any").removeAttr (JSExpr.lit ("foo"))); }

@Test
public void testremoveAttr1985() { assertNotNull (JQuery.idRef ("any").removeAttr (new JsonObject ().add ("foo", 5))); }

@Test
public void testremoveAttr1986() { assertNotNull (JQuery.idRef ("any").removeAttr (new HCDiv ().addChild ("foo"))); }

@Test
public void testremoveAttr1987() { assertNotNull (JQuery.idRef ("any").removeAttr ("foo")); }

@Test
public void testremoveClass1988() { assertNotNull (JQuery.idRef ("any").removeClass (JSExpr.lit ("foo"))); }

@Test
public void testremoveClass1989() { assertNotNull (JQuery.idRef ("any").removeClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testremoveClass1990() { assertNotNull (JQuery.idRef ("any").removeClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testremoveClass1991() { assertNotNull (JQuery.idRef ("any").removeClass ("foo")); }

@Test
public void testremoveClass1992() { assertNotNull (JQuery.idRef ("any").removeClass (new JSAnonymousFunction ())); }

@Test
public void testremoveData1993() { assertNotNull (JQuery.idRef ("any").removeData (JSExpr.lit ("foo"))); }

@Test
public void testremoveData1994() { assertNotNull (JQuery.idRef ("any").removeData (new JsonObject ().add ("foo", 5))); }

@Test
public void testremoveData1995() { assertNotNull (JQuery.idRef ("any").removeData (new HCDiv ().addChild ("foo"))); }

@Test
public void testremoveData1996() { assertNotNull (JQuery.idRef ("any").removeData ("foo")); }

@Test
public void testremoveData1997() { assertNotNull (JQuery.idRef ("any").removeData (new JSArray ().add (1).add (2))); }

@Test
public void testremoveProp1998() { assertNotNull (JQuery.idRef ("any").removeProp (JSExpr.lit ("foo"))); }

@Test
public void testremoveProp1999() { assertNotNull (JQuery.idRef ("any").removeProp (new JsonObject ().add ("foo", 5))); }

@Test
public void testremoveProp2000() { assertNotNull (JQuery.idRef ("any").removeProp (new HCDiv ().addChild ("foo"))); }

@Test
public void testremoveProp2001() { assertNotNull (JQuery.idRef ("any").removeProp ("foo")); }

@Test
public void testreplaceAll2002() { assertNotNull (JQuery.idRef ("any").replaceAll (JSExpr.lit ("foo"))); }

@Test
public void testreplaceAll2003() { assertNotNull (JQuery.idRef ("any").replaceAll (JQuerySelector.eq (0))); }

@Test
public void testreplaceAll2004() { assertNotNull (JQuery.idRef ("any").replaceAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testreplaceAll2005() { assertNotNull (JQuery.idRef ("any").replaceAll (EHTMLElement.DIV)); }

@Test
public void testreplaceAll2006() { assertNotNull (JQuery.idRef ("any").replaceAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testreplaceAll2007() { assertNotNull (JQuery.idRef ("any").replaceAll (JQuery.idRef ("foo"))); }

@Test
public void testreplaceAll2008() { assertNotNull (JQuery.idRef ("any").replaceAll (new JSArray ().add (1).add (2))); }

@Test
public void testreplaceAll2009() { assertNotNull (JQuery.idRef ("any").replaceAll ("foo")); }

@Test
public void testreplaceWith2010() { assertNotNull (JQuery.idRef ("any").replaceWith (JSExpr.lit ("foo"))); }

@Test
public void testreplaceWith2011() { assertNotNull (JQuery.idRef ("any").replaceWith (new HCDiv ().addChild ("foo"))); }

@Test
public void testreplaceWith2012() { assertNotNull (JQuery.idRef ("any").replaceWith ("foo")); }

@Test
public void testreplaceWith2013() { assertNotNull (JQuery.idRef ("any").replaceWith (EHTMLElement.DIV)); }

@Test
public void testreplaceWith2014() { assertNotNull (JQuery.idRef ("any").replaceWith (new JSArray ().add (1).add (2))); }

@Test
public void testreplaceWith2015() { assertNotNull (JQuery.idRef ("any").replaceWith (JQuery.idRef ("foo"))); }

@Test
public void testreplaceWith2016() { assertNotNull (JQuery.idRef ("any").replaceWith (new JSAnonymousFunction ())); }

@Test
public void testresize2017() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"))); }

@Test
public void testresize2018() { assertNotNull (JQuery.idRef ("any").resize (new JSAnonymousFunction ())); }

@Test
public void testresize2019() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testresize2020() { assertNotNull (JQuery.idRef ("any").resize (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testscroll2021() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"))); }

@Test
public void testscroll2022() { assertNotNull (JQuery.idRef ("any").scroll (new JSAnonymousFunction ())); }

@Test
public void testscroll2023() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testscroll2024() { assertNotNull (JQuery.idRef ("any").scroll (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testscrollLeft2025() { assertNotNull (JQuery.idRef ("any").scrollLeft (JSExpr.lit ("foo"))); }

@Test
public void testscrollLeft2026() { assertNotNull (JQuery.idRef ("any").scrollLeft (3456)); }

@Test
public void testscrollLeft2027() { assertNotNull (JQuery.idRef ("any").scrollLeft (87654321L)); }

@Test
public void testscrollLeft2028() { assertNotNull (JQuery.idRef ("any").scrollLeft (BigInteger.valueOf (3456))); }

@Test
public void testscrollLeft2029() { assertNotNull (JQuery.idRef ("any").scrollLeft (123.456)); }

@Test
public void testscrollLeft2030() { assertNotNull (JQuery.idRef ("any").scrollLeft (BigDecimal.valueOf (12.3456))); }

@Test
public void testscrollTop2031() { assertNotNull (JQuery.idRef ("any").scrollTop (JSExpr.lit ("foo"))); }

@Test
public void testscrollTop2032() { assertNotNull (JQuery.idRef ("any").scrollTop (3456)); }

@Test
public void testscrollTop2033() { assertNotNull (JQuery.idRef ("any").scrollTop (87654321L)); }

@Test
public void testscrollTop2034() { assertNotNull (JQuery.idRef ("any").scrollTop (BigInteger.valueOf (3456))); }

@Test
public void testscrollTop2035() { assertNotNull (JQuery.idRef ("any").scrollTop (123.456)); }

@Test
public void testscrollTop2036() { assertNotNull (JQuery.idRef ("any").scrollTop (BigDecimal.valueOf (12.3456))); }

@Test
public void testselect2037() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"))); }

@Test
public void testselect2038() { assertNotNull (JQuery.idRef ("any").select (new JSAnonymousFunction ())); }

@Test
public void testselect2039() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testselect2040() { assertNotNull (JQuery.idRef ("any").select (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testshow2041() { assertNotNull (JQuery.idRef ("any").show (JSExpr.lit ("foo"))); }

@Test
public void testshow2042() { assertNotNull (JQuery.idRef ("any").show (3456)); }

@Test
public void testshow2043() { assertNotNull (JQuery.idRef ("any").show (87654321L)); }

@Test
public void testshow2044() { assertNotNull (JQuery.idRef ("any").show (BigInteger.valueOf (3456))); }

@Test
public void testshow2045() { assertNotNull (JQuery.idRef ("any").show (123.456)); }

@Test
public void testshow2046() { assertNotNull (JQuery.idRef ("any").show (BigDecimal.valueOf (12.3456))); }

@Test
public void testshow2047() { assertNotNull (JQuery.idRef ("any").show (new JsonObject ().add ("foo", 5))); }

@Test
public void testshow2048() { assertNotNull (JQuery.idRef ("any").show (new HCDiv ().addChild ("foo"))); }

@Test
public void testshow2049() { assertNotNull (JQuery.idRef ("any").show ("foo")); }

@Test
public void testsiblings2050() { assertNotNull (JQuery.idRef ("any").siblings (JSExpr.lit ("foo"))); }

@Test
public void testsiblings2051() { assertNotNull (JQuery.idRef ("any").siblings (JQuerySelector.eq (0))); }

@Test
public void testsiblings2052() { assertNotNull (JQuery.idRef ("any").siblings (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testsiblings2053() { assertNotNull (JQuery.idRef ("any").siblings (EHTMLElement.DIV)); }

@Test
public void testsiblings2054() { assertNotNull (JQuery.idRef ("any").siblings (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testslice2055() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"))); }

@Test
public void testslice2056() { assertNotNull (JQuery.idRef ("any").slice (3456)); }

@Test
public void testslice2057() { assertNotNull (JQuery.idRef ("any").slice (87654321L)); }

@Test
public void testslice2058() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456))); }

@Test
public void testslice2059() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testslice2060() { assertNotNull (JQuery.idRef ("any").slice (3456, JSExpr.lit ("foo"))); }

@Test
public void testslice2061() { assertNotNull (JQuery.idRef ("any").slice (87654321L, JSExpr.lit ("foo"))); }

@Test
public void testslice2062() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), JSExpr.lit ("foo"))); }

@Test
public void testslice2063() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), 3456)); }

@Test
public void testslice2064() { assertNotNull (JQuery.idRef ("any").slice (3456, 3456)); }

@Test
public void testslice2065() { assertNotNull (JQuery.idRef ("any").slice (87654321L, 3456)); }

@Test
public void testslice2066() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), 3456)); }

@Test
public void testslice2067() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), 87654321L)); }

@Test
public void testslice2068() { assertNotNull (JQuery.idRef ("any").slice (3456, 87654321L)); }

@Test
public void testslice2069() { assertNotNull (JQuery.idRef ("any").slice (87654321L, 87654321L)); }

@Test
public void testslice2070() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), 87654321L)); }

@Test
public void testslice2071() { assertNotNull (JQuery.idRef ("any").slice (JSExpr.lit ("foo"), BigInteger.valueOf (3456))); }

@Test
public void testslice2072() { assertNotNull (JQuery.idRef ("any").slice (3456, BigInteger.valueOf (3456))); }

@Test
public void testslice2073() { assertNotNull (JQuery.idRef ("any").slice (87654321L, BigInteger.valueOf (3456))); }

@Test
public void testslice2074() { assertNotNull (JQuery.idRef ("any").slice (BigInteger.valueOf (3456), BigInteger.valueOf (3456))); }

@Test
public void teststop2075() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"))); }

@Test
public void teststop2076() { assertNotNull (JQuery.idRef ("any").stop (true)); }

@Test
public void teststop2077() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2078() { assertNotNull (JQuery.idRef ("any").stop (true, JSExpr.lit ("foo"))); }

@Test
public void teststop2079() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true)); }

@Test
public void teststop2080() { assertNotNull (JQuery.idRef ("any").stop (true, true)); }

@Test
public void teststop2081() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5))); }

@Test
public void teststop2082() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"))); }

@Test
public void teststop2083() { assertNotNull (JQuery.idRef ("any").stop ("foo")); }

@Test
public void teststop2084() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void teststop2085() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2086() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"))); }

@Test
public void teststop2087() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true)); }

@Test
public void teststop2088() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true)); }

@Test
public void teststop2089() { assertNotNull (JQuery.idRef ("any").stop ("foo", true)); }

@Test
public void teststop2090() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2091() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2092() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2093() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void teststop2094() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true, JSExpr.lit ("foo"))); }

@Test
public void teststop2095() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true, JSExpr.lit ("foo"))); }

@Test
public void teststop2096() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true, JSExpr.lit ("foo"))); }

@Test
public void teststop2097() { assertNotNull (JQuery.idRef ("any").stop ("foo", true, JSExpr.lit ("foo"))); }

@Test
public void teststop2098() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void teststop2099() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), true)); }

@Test
public void teststop2100() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), true)); }

@Test
public void teststop2101() { assertNotNull (JQuery.idRef ("any").stop ("foo", JSExpr.lit ("foo"), true)); }

@Test
public void teststop2102() { assertNotNull (JQuery.idRef ("any").stop (JSExpr.lit ("foo"), true, true)); }

@Test
public void teststop2103() { assertNotNull (JQuery.idRef ("any").stop (new JsonObject ().add ("foo", 5), true, true)); }

@Test
public void teststop2104() { assertNotNull (JQuery.idRef ("any").stop (new HCDiv ().addChild ("foo"), true, true)); }

@Test
public void teststop2105() { assertNotNull (JQuery.idRef ("any").stop ("foo", true, true)); }

@Test
public void testsubmit2106() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"))); }

@Test
public void testsubmit2107() { assertNotNull (JQuery.idRef ("any").submit (new JSAnonymousFunction ())); }

@Test
public void testsubmit2108() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testsubmit2109() { assertNotNull (JQuery.idRef ("any").submit (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testtext2110() { assertNotNull (JQuery.idRef ("any").text (JSExpr.lit ("foo"))); }

@Test
public void testtext2111() { assertNotNull (JQuery.idRef ("any").text (new JsonObject ().add ("foo", 5))); }

@Test
public void testtext2112() { assertNotNull (JQuery.idRef ("any").text (new HCDiv ().addChild ("foo"))); }

@Test
public void testtext2113() { assertNotNull (JQuery.idRef ("any").text ("foo")); }

@Test
public void testtext2114() { assertNotNull (JQuery.idRef ("any").text (3456)); }

@Test
public void testtext2115() { assertNotNull (JQuery.idRef ("any").text (87654321L)); }

@Test
public void testtext2116() { assertNotNull (JQuery.idRef ("any").text (BigInteger.valueOf (3456))); }

@Test
public void testtext2117() { assertNotNull (JQuery.idRef ("any").text (123.456)); }

@Test
public void testtext2118() { assertNotNull (JQuery.idRef ("any").text (BigDecimal.valueOf (12.3456))); }

@Test
public void testtext2119() { assertNotNull (JQuery.idRef ("any").text (true)); }

@Test
public void testtext2120() { assertNotNull (JQuery.idRef ("any").text (new JSAnonymousFunction ())); }

@Test
public void testtoggle2121() { assertNotNull (JQuery.idRef ("any").toggle (JSExpr.lit ("foo"))); }

@Test
public void testtoggle2122() { assertNotNull (JQuery.idRef ("any").toggle (3456)); }

@Test
public void testtoggle2123() { assertNotNull (JQuery.idRef ("any").toggle (87654321L)); }

@Test
public void testtoggle2124() { assertNotNull (JQuery.idRef ("any").toggle (BigInteger.valueOf (3456))); }

@Test
public void testtoggle2125() { assertNotNull (JQuery.idRef ("any").toggle (123.456)); }

@Test
public void testtoggle2126() { assertNotNull (JQuery.idRef ("any").toggle (BigDecimal.valueOf (12.3456))); }

@Test
public void testtoggle2127() { assertNotNull (JQuery.idRef ("any").toggle (new JsonObject ().add ("foo", 5))); }

@Test
public void testtoggle2128() { assertNotNull (JQuery.idRef ("any").toggle (new HCDiv ().addChild ("foo"))); }

@Test
public void testtoggle2129() { assertNotNull (JQuery.idRef ("any").toggle ("foo")); }

@Test
public void testtoggle2130() { assertNotNull (JQuery.idRef ("any").toggle (true)); }

@Test
public void testtoggleClass2131() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2132() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5))); }

@Test
public void testtoggleClass2133() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"))); }

@Test
public void testtoggleClass2134() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo")); }

@Test
public void testtoggleClass2135() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2136() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2137() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2138() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo", JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2139() { assertNotNull (JQuery.idRef ("any").toggleClass (JSExpr.lit ("foo"), true)); }

@Test
public void testtoggleClass2140() { assertNotNull (JQuery.idRef ("any").toggleClass (new JsonObject ().add ("foo", 5), true)); }

@Test
public void testtoggleClass2141() { assertNotNull (JQuery.idRef ("any").toggleClass (new HCDiv ().addChild ("foo"), true)); }

@Test
public void testtoggleClass2142() { assertNotNull (JQuery.idRef ("any").toggleClass ("foo", true)); }

@Test
public void testtoggleClass2143() { assertNotNull (JQuery.idRef ("any").toggleClass (true)); }

@Test
public void testtoggleClass2144() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction ())); }

@Test
public void testtoggleClass2145() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction (), JSExpr.lit ("foo"))); }

@Test
public void testtoggleClass2146() { assertNotNull (JQuery.idRef ("any").toggleClass (new JSAnonymousFunction (), true)); }

@Test
public void testtrigger2147() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"))); }

@Test
public void testtrigger2148() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5))); }

@Test
public void testtrigger2149() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"))); }

@Test
public void testtrigger2150() { assertNotNull (JQuery.idRef ("any").trigger ("foo")); }

@Test
public void testtrigger2151() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtrigger2152() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testtrigger2153() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtrigger2154() { assertNotNull (JQuery.idRef ("any").trigger ("foo", JSExpr.lit ("foo"))); }

@Test
public void testtrigger2155() { assertNotNull (JQuery.idRef ("any").trigger (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testtrigger2156() { assertNotNull (JQuery.idRef ("any").trigger (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testtrigger2157() { assertNotNull (JQuery.idRef ("any").trigger (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testtrigger2158() { assertNotNull (JQuery.idRef ("any").trigger ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testtriggerHandler2159() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"))); }

@Test
public void testtriggerHandler2160() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5))); }

@Test
public void testtriggerHandler2161() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"))); }

@Test
public void testtriggerHandler2162() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo")); }

@Test
public void testtriggerHandler2163() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtriggerHandler2164() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testtriggerHandler2165() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testtriggerHandler2166() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo", JSExpr.lit ("foo"))); }

@Test
public void testtriggerHandler2167() { assertNotNull (JQuery.idRef ("any").triggerHandler (JSExpr.lit ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testtriggerHandler2168() { assertNotNull (JQuery.idRef ("any").triggerHandler (new JsonObject ().add ("foo", 5), new JSArray ().add (1).add (2))); }

@Test
public void testtriggerHandler2169() { assertNotNull (JQuery.idRef ("any").triggerHandler (new HCDiv ().addChild ("foo"), new JSArray ().add (1).add (2))); }

@Test
public void testtriggerHandler2170() { assertNotNull (JQuery.idRef ("any").triggerHandler ("foo", new JSArray ().add (1).add (2))); }

@Test
public void testunbind2171() { assertNotNull (JQuery.idRef ("any").unbind (JSExpr.lit ("foo"))); }

@Test
public void testunbind2172() { assertNotNull (JQuery.idRef ("any").unbind (new JsonObject ().add ("foo", 5))); }

@Test
public void testunbind2173() { assertNotNull (JQuery.idRef ("any").unbind (new HCDiv ().addChild ("foo"))); }

@Test
public void testunbind2174() { assertNotNull (JQuery.idRef ("any").unbind ("foo")); }

@Test
public void testunbind2175() { assertNotNull (JQuery.idRef ("any").unbind (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testunbind2176() { assertNotNull (JQuery.idRef ("any").unbind (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testunbind2177() { assertNotNull (JQuery.idRef ("any").unbind (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testunbind2178() { assertNotNull (JQuery.idRef ("any").unbind ("foo", JSExpr.lit ("foo"))); }

@Test
public void testunbind2179() { assertNotNull (JQuery.idRef ("any").unbind (JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testunbind2180() { assertNotNull (JQuery.idRef ("any").unbind (new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testunbind2181() { assertNotNull (JQuery.idRef ("any").unbind (new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testunbind2182() { assertNotNull (JQuery.idRef ("any").unbind ("foo", new JSAnonymousFunction ())); }

@Test
public void testunbind2183() { assertNotNull (JQuery.idRef ("any").unbind (JSExpr.lit ("foo"), true)); }

@Test
public void testunbind2184() { assertNotNull (JQuery.idRef ("any").unbind (new JsonObject ().add ("foo", 5), true)); }

@Test
public void testunbind2185() { assertNotNull (JQuery.idRef ("any").unbind (new HCDiv ().addChild ("foo"), true)); }

@Test
public void testunbind2186() { assertNotNull (JQuery.idRef ("any").unbind ("foo", true)); }

@Test
public void testundelegate2187() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2188() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2189() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2190() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", JSExpr.lit ("foo"))); }

@Test
public void testundelegate2191() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testundelegate2192() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5))); }

@Test
public void testundelegate2193() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5))); }

@Test
public void testundelegate2194() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new JsonObject ().add ("foo", 5))); }

@Test
public void testundelegate2195() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testundelegate2196() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"))); }

@Test
public void testundelegate2197() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"))); }

@Test
public void testundelegate2198() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new HCDiv ().addChild ("foo"))); }

@Test
public void testundelegate2199() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), "foo")); }

@Test
public void testundelegate2200() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), "foo")); }

@Test
public void testundelegate2201() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), "foo")); }

@Test
public void testundelegate2202() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", "foo")); }

@Test
public void testundelegate2203() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2204() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2205() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2206() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", JSExpr.lit ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2207() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2208() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2209() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2210() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2211() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2212() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2213() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2214() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"))); }

@Test
public void testundelegate2215() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testundelegate2216() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), "foo", JSExpr.lit ("foo"))); }

@Test
public void testundelegate2217() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), "foo", JSExpr.lit ("foo"))); }

@Test
public void testundelegate2218() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", "foo", JSExpr.lit ("foo"))); }

@Test
public void testundelegate2219() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2220() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2221() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2222() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", JSExpr.lit ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2223() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testundelegate2224() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testundelegate2225() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testundelegate2226() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new JsonObject ().add ("foo", 5), new JSAnonymousFunction ())); }

@Test
public void testundelegate2227() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2228() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2229() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2230() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", new HCDiv ().addChild ("foo"), new JSAnonymousFunction ())); }

@Test
public void testundelegate2231() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testundelegate2232() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5), "foo", new JSAnonymousFunction ())); }

@Test
public void testundelegate2233() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"), "foo", new JSAnonymousFunction ())); }

@Test
public void testundelegate2234() { assertNotNull (JQuery.idRef ("any").undelegate ("foo", "foo", new JSAnonymousFunction ())); }

@Test
public void testundelegate2235() { assertNotNull (JQuery.idRef ("any").undelegate (JSExpr.lit ("foo"))); }

@Test
public void testundelegate2236() { assertNotNull (JQuery.idRef ("any").undelegate (new JsonObject ().add ("foo", 5))); }

@Test
public void testundelegate2237() { assertNotNull (JQuery.idRef ("any").undelegate (new HCDiv ().addChild ("foo"))); }

@Test
public void testundelegate2238() { assertNotNull (JQuery.idRef ("any").undelegate ("foo")); }

@Test
public void testval2239() { assertNotNull (JQuery.idRef ("any").val (JSExpr.lit ("foo"))); }

@Test
public void testval2240() { assertNotNull (JQuery.idRef ("any").val (new JsonObject ().add ("foo", 5))); }

@Test
public void testval2241() { assertNotNull (JQuery.idRef ("any").val (new HCDiv ().addChild ("foo"))); }

@Test
public void testval2242() { assertNotNull (JQuery.idRef ("any").val ("foo")); }

@Test
public void testval2243() { assertNotNull (JQuery.idRef ("any").val (3456)); }

@Test
public void testval2244() { assertNotNull (JQuery.idRef ("any").val (87654321L)); }

@Test
public void testval2245() { assertNotNull (JQuery.idRef ("any").val (BigInteger.valueOf (3456))); }

@Test
public void testval2246() { assertNotNull (JQuery.idRef ("any").val (123.456)); }

@Test
public void testval2247() { assertNotNull (JQuery.idRef ("any").val (BigDecimal.valueOf (12.3456))); }

@Test
public void testval2248() { assertNotNull (JQuery.idRef ("any").val (new JSArray ().add (1).add (2))); }

@Test
public void testval2249() { assertNotNull (JQuery.idRef ("any").val (new JSAnonymousFunction ())); }

@Test
public void testwidth2250() { assertNotNull (JQuery.idRef ("any").width (JSExpr.lit ("foo"))); }

@Test
public void testwidth2251() { assertNotNull (JQuery.idRef ("any").width (new JsonObject ().add ("foo", 5))); }

@Test
public void testwidth2252() { assertNotNull (JQuery.idRef ("any").width (new HCDiv ().addChild ("foo"))); }

@Test
public void testwidth2253() { assertNotNull (JQuery.idRef ("any").width ("foo")); }

@Test
public void testwidth2254() { assertNotNull (JQuery.idRef ("any").width (3456)); }

@Test
public void testwidth2255() { assertNotNull (JQuery.idRef ("any").width (87654321L)); }

@Test
public void testwidth2256() { assertNotNull (JQuery.idRef ("any").width (BigInteger.valueOf (3456))); }

@Test
public void testwidth2257() { assertNotNull (JQuery.idRef ("any").width (123.456)); }

@Test
public void testwidth2258() { assertNotNull (JQuery.idRef ("any").width (BigDecimal.valueOf (12.3456))); }

@Test
public void testwidth2259() { assertNotNull (JQuery.idRef ("any").width (new JSAnonymousFunction ())); }

@Test
public void testwrap2260() { assertNotNull (JQuery.idRef ("any").wrap (JSExpr.lit ("foo"))); }

@Test
public void testwrap2261() { assertNotNull (JQuery.idRef ("any").wrap (JQuerySelector.eq (0))); }

@Test
public void testwrap2262() { assertNotNull (JQuery.idRef ("any").wrap (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testwrap2263() { assertNotNull (JQuery.idRef ("any").wrap (EHTMLElement.DIV)); }

@Test
public void testwrap2264() { assertNotNull (JQuery.idRef ("any").wrap (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testwrap2265() { assertNotNull (JQuery.idRef ("any").wrap (new HCDiv ().addChild ("foo"))); }

@Test
public void testwrap2266() { assertNotNull (JQuery.idRef ("any").wrap ("foo")); }

@Test
public void testwrap2267() { assertNotNull (JQuery.idRef ("any").wrap (JQuery.idRef ("foo"))); }

@Test
public void testwrap2268() { assertNotNull (JQuery.idRef ("any").wrap (new JSAnonymousFunction ())); }

@Test
public void testwrapAll2269() { assertNotNull (JQuery.idRef ("any").wrapAll (JSExpr.lit ("foo"))); }

@Test
public void testwrapAll2270() { assertNotNull (JQuery.idRef ("any").wrapAll (JQuerySelector.eq (0))); }

@Test
public void testwrapAll2271() { assertNotNull (JQuery.idRef ("any").wrapAll (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testwrapAll2272() { assertNotNull (JQuery.idRef ("any").wrapAll (EHTMLElement.DIV)); }

@Test
public void testwrapAll2273() { assertNotNull (JQuery.idRef ("any").wrapAll (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testwrapAll2274() { assertNotNull (JQuery.idRef ("any").wrapAll (new HCDiv ().addChild ("foo"))); }

@Test
public void testwrapAll2275() { assertNotNull (JQuery.idRef ("any").wrapAll ("foo")); }

@Test
public void testwrapAll2276() { assertNotNull (JQuery.idRef ("any").wrapAll (JQuery.idRef ("foo"))); }

@Test
public void testwrapAll2277() { assertNotNull (JQuery.idRef ("any").wrapAll (new JSAnonymousFunction ())); }

@Test
public void testwrapInner2278() { assertNotNull (JQuery.idRef ("any").wrapInner (JSExpr.lit ("foo"))); }

@Test
public void testwrapInner2279() { assertNotNull (JQuery.idRef ("any").wrapInner (new HCDiv ().addChild ("foo"))); }

@Test
public void testwrapInner2280() { assertNotNull (JQuery.idRef ("any").wrapInner ("foo")); }

@Test
public void testwrapInner2281() { assertNotNull (JQuery.idRef ("any").wrapInner (JQuerySelector.eq (0))); }

@Test
public void testwrapInner2282() { assertNotNull (JQuery.idRef ("any").wrapInner (new JQuerySelectorList (JQuerySelector.lt (3)))); }

@Test
public void testwrapInner2283() { assertNotNull (JQuery.idRef ("any").wrapInner (EHTMLElement.DIV)); }

@Test
public void testwrapInner2284() { assertNotNull (JQuery.idRef ("any").wrapInner (DefaultCSSClassProvider.create ("cssclass"))); }

@Test
public void testwrapInner2285() { assertNotNull (JQuery.idRef ("any").wrapInner (JQuery.idRef ("foo"))); }

@Test
public void testwrapInner2286() { assertNotNull (JQuery.idRef ("any").wrapInner (new JSAnonymousFunction ())); }

}
