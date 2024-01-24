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

import com.helger.commons.collection.CollectionHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.jquery.JQuery.OnDocumentReadyInvocation;
import com.helger.html.js.JSWriterSettings;
import com.helger.html.jscode.JSExpr;

/**
 * Test class for class {@link JQuery}.
 *
 * @author Philip Helger
 */
public final class JQueryTest
{
  @Test
  public void testBasic ()
  {
    final JSWriterSettings aSettings = new JSWriterSettings ().setIndentAndAlign (false);

    JQueryInvocation aJQI = JQuery.idRef ("id4711").focus ();
    assertEquals ("$('#id4711').focus();", aJQI.getJSCode (aSettings));

    final OnDocumentReadyInvocation aPair = JQuery.onDocumentReady ();
    aPair.getAnonFunction ().body ()._return (0);
    assertEquals ("$(document).ready(function(){return 0;});", aPair.getInvocation ().getJSCode (aSettings));

    aJQI = JQuery.onDocumentReady (JSExpr.ref ("i").assign (5));
    assertEquals ("$(document).ready(function(){i=5;});", aJQI.getJSCode (aSettings));
  }

  @Test
  public void testMethods ()
  {
    assertEquals ("$.ajax(5);", JQuery.ajax ().arg (5).getJSCode ());
    assertEquals ("$.ajaxPrefilter(5);", JQuery.ajaxPrefilter ().arg (5).getJSCode ());
    assertEquals ("$.ajaxSetup(5);", JQuery.ajaxSetup ().arg (5).getJSCode ());
    assertEquals ("$.Callbacks(5);", JQuery.Callbacks ().arg (5).getJSCode ());
    assertEquals ("$.contains(5);", JQuery.contains ().arg (5).getJSCode ());
    assertEquals ("$.data(5);", JQuery.data ().arg (5).getJSCode ());
    assertEquals ("$.dequeue(5);", JQuery.dequeue ().arg (5).getJSCode ());
    assertEquals ("$.each(5);", JQuery.each ().arg (5).getJSCode ());
    assertEquals ("$.extend(5);", JQuery.extend ().arg (5).getJSCode ());
    assertEquals ("$.get(5);", JQuery.get ().arg (5).getJSCode ());
    assertEquals ("$.getJSON(5);", JQuery.getJSON ().arg (5).getJSCode ());
    assertEquals ("$.getScript(5);", JQuery.getScript ().arg (5).getJSCode ());
    assertEquals ("$.globalEval(5);", JQuery.globalEval ().arg (5).getJSCode ());
    assertEquals ("$.grep(5);", JQuery.grep ().arg (5).getJSCode ());
    assertEquals ("$.hasData(5);", JQuery.hasData ().arg (5).getJSCode ());
    assertEquals ("$.holdReady(5);", JQuery.holdReady ().arg (5).getJSCode ());
    assertEquals ("$.inArray(5);", JQuery.inArray ().arg (5).getJSCode ());
    assertEquals ("$.isArray(5);", JQuery.isArray ().arg (5).getJSCode ());
    assertEquals ("$.isEmptyObject(5);", JQuery.isEmptyObject ().arg (5).getJSCode ());
    assertEquals ("$.isFunction(5);", JQuery.isFunction ().arg (5).getJSCode ());
    assertEquals ("$.isNumeric(5);", JQuery.isNumeric ().arg (5).getJSCode ());
    assertEquals ("$.isPlainObject(5);", JQuery.isPlainObject ().arg (5).getJSCode ());
    assertEquals ("$.isWindow(5);", JQuery.isWindow ().arg (5).getJSCode ());
    assertEquals ("$.isXMLDoc(5);", JQuery.isXMLDoc ().arg (5).getJSCode ());
    assertEquals ("$.makeArray(5);", JQuery.makeArray ().arg (5).getJSCode ());
    assertEquals ("$.map(5);", JQuery.map ().arg (5).getJSCode ());
    assertEquals ("$.merge(5);", JQuery.merge ().arg (5).getJSCode ());
    assertEquals ("$.noConflict(5);", JQuery.noConflict ().arg (5).getJSCode ());
    assertEquals ("$.noop(5);", JQuery.noop ().arg (5).getJSCode ());
    assertEquals ("$.now(5);", JQuery.now ().arg (5).getJSCode ());
    assertEquals ("$.param(5);", JQuery.param ().arg (5).getJSCode ());
    assertEquals ("$.parseXML(5);", JQuery.parseXML ().arg (5).getJSCode ());
    assertEquals ("$.post(5);", JQuery.post ().arg (5).getJSCode ());
    assertEquals ("$.proxy(5);", JQuery.proxy ().arg (5).getJSCode ());
    assertEquals ("$.queue(5);", JQuery.queue ().arg (5).getJSCode ());
    assertEquals ("$.removeData(5);", JQuery.removeData ().arg (5).getJSCode ());
    assertEquals ("$.trim(5);", JQuery.trim ().arg (5).getJSCode ());
    assertEquals ("$.type(5);", JQuery.type ().arg (5).getJSCode ());
    assertEquals ("$.unique(5);", JQuery.unique ().arg (5).getJSCode ());
    assertEquals ("$.when(5);", JQuery.when ().arg (5).getJSCode ());
    assertEquals ("$(a);", JQuery.jQuery (JSExpr.ref ("a")).getJSCode ());
    assertEquals ("$(this);", JQuery.jQueryThis ().getJSCode ());
    assertEquals ("$(document);", JQuery.jQueryDocument ().getJSCode ());
  }

  @Test
  public void testSelectors ()
  {
    assertEquals ("$('#abc');", JQuery.idRef ("abc").getJSCode ());
    assertEquals ("$('#abc');", JQuery.idRefMultiple ("abc").getJSCode ());
    assertEquals ("$('#a,#b,#c');", JQuery.idRefMultiple ("a", "b", "c").getJSCode ());
    assertEquals ("$('#a,#b,#c');", JQuery.idRefMultiple (CollectionHelper.newList ("a", "b", "c")).getJSCode ());
    final ICSSClassProvider aClass = DefaultCSSClassProvider.create ("any");
    final ICSSClassProvider aClass2 = DefaultCSSClassProvider.create ("other");
    assertEquals ("$('.any');", JQuery.classRef (aClass).getJSCode ());
    assertEquals ("$('.any');", JQuery.classRefMultiple (aClass).getJSCode ());
    assertEquals ("$('.any,.other');", JQuery.classRefMultiple (aClass, aClass2).getJSCode ());
    assertEquals ("$('.any,.other');",
                  JQuery.classRefMultiple (CollectionHelper.newList (aClass, aClass2)).getJSCode ());
    assertEquals ("$('div');", JQuery.elementNameRef (EHTMLElement.DIV).getJSCode ());
    assertEquals ("$('bla');", JQuery.elementNameRef ("bla").getJSCode ());
    assertEquals ("$('div#foo');", JQuery.elementNameWithIDRef (EHTMLElement.DIV, "foo").getJSCode ());
    assertEquals ("$('bla#foo');", JQuery.elementNameWithIDRef ("bla", "foo").getJSCode ());
    assertEquals ("$('div.any');", JQuery.elementNameWithClassRef (EHTMLElement.DIV, aClass).getJSCode ());
    assertEquals ("$('bla.any');", JQuery.elementNameWithClassRef ("bla", aClass).getJSCode ());
  }
}
