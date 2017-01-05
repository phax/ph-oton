/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSFunction;
import com.helger.json.IJson;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_AbstractJQueryInvocationExtended
 * @param <THISTYPE> Implementation type
*/
public abstract class AbstractJQueryInvocationExtended <THISTYPE extends AbstractJQueryInvocationExtended <THISTYPE>> extends AbstractJQueryInvocation <THISTYPE> implements IJQueryInvocationExtended <THISTYPE>
{
  public AbstractJQueryInvocationExtended (@Nonnull final JSFunction aFunction)
  {
    super (aFunction);
  }

  public AbstractJQueryInvocationExtended (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)
  {
    super (aLhs, sMethod);
  }

@Nonnull
public THISTYPE add(@Nonnull IJSExpression selector) { return add ().arg (selector); }

@Nonnull
public THISTYPE add(@Nonnull IJQuerySelector selector) { return add ().arg (selector); }

@Nonnull
public THISTYPE add(@Nonnull JQuerySelectorList selector) { return add ().arg (selector); }

@Nonnull
public THISTYPE add(@Nonnull EHTMLElement selector) { return add ().arg (selector); }

@Nonnull
public THISTYPE add(@Nonnull ICSSClassProvider selector) { return add ().arg (selector); }

@Nonnull
public THISTYPE add(@Nonnull String elements) { return add ().arg (elements); }

@Nonnull
public THISTYPE add(@Nonnull IHCNode html) { return add ().arg (html); }

@Nonnull
public THISTYPE add(@Nonnull JQueryInvocation selection) { return add ().arg (selection); }

@Nonnull
public THISTYPE add(@Nonnull IJSExpression selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull IJSExpression selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
public THISTYPE addBack(@Nonnull IJSExpression selector) { return addBack ().arg (selector); }

@Nonnull
public THISTYPE addBack(@Nonnull IJQuerySelector selector) { return addBack ().arg (selector); }

@Nonnull
public THISTYPE addBack(@Nonnull JQuerySelectorList selector) { return addBack ().arg (selector); }

@Nonnull
public THISTYPE addBack(@Nonnull EHTMLElement selector) { return addBack ().arg (selector); }

@Nonnull
public THISTYPE addBack(@Nonnull ICSSClassProvider selector) { return addBack ().arg (selector); }

@Nonnull
public THISTYPE addClass(@Nonnull IJSExpression className) { return addClass ().arg (className); }

@Nonnull
public THISTYPE addClass(@Nonnull IJson className) { return addClass ().arg (className); }

@Nonnull
public THISTYPE addClass(@Nonnull IHCNode className) { return addClass ().arg (className); }

@Nonnull
public THISTYPE addClass(@Nonnull String className) { return addClass ().arg (className); }

@Nonnull
public THISTYPE addClass(@Nonnull JSAnonymousFunction function) { return addClass ().arg (function); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull String content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull IJson content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content) { return after ().arg (content); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull String content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
public THISTYPE after(@Nonnull JSAnonymousFunction function) { return after ().arg (function); }

@Nonnull
public THISTYPE ajaxComplete(@Nonnull IJSExpression handler) { return ajaxComplete ().arg (handler); }

@Nonnull
public THISTYPE ajaxComplete(@Nonnull JSAnonymousFunction handler) { return ajaxComplete ().arg (handler); }

@Nonnull
public THISTYPE ajaxError(@Nonnull IJSExpression handler) { return ajaxError ().arg (handler); }

@Nonnull
public THISTYPE ajaxError(@Nonnull JSAnonymousFunction handler) { return ajaxError ().arg (handler); }

@Nonnull
public THISTYPE ajaxSend(@Nonnull IJSExpression handler) { return ajaxSend ().arg (handler); }

@Nonnull
public THISTYPE ajaxSend(@Nonnull JSAnonymousFunction handler) { return ajaxSend ().arg (handler); }

@Nonnull
public THISTYPE ajaxStart(@Nonnull IJSExpression handler) { return ajaxStart ().arg (handler); }

@Nonnull
public THISTYPE ajaxStart(@Nonnull JSAnonymousFunction handler) { return ajaxStart ().arg (handler); }

@Nonnull
public THISTYPE ajaxStop(@Nonnull IJSExpression handler) { return ajaxStop ().arg (handler); }

@Nonnull
public THISTYPE ajaxStop(@Nonnull JSAnonymousFunction handler) { return ajaxStop ().arg (handler); }

@Nonnull
public THISTYPE ajaxSuccess(@Nonnull IJSExpression handler) { return ajaxSuccess ().arg (handler); }

@Nonnull
public THISTYPE ajaxSuccess(@Nonnull JSAnonymousFunction handler) { return ajaxSuccess ().arg (handler); }

@Nonnull
public THISTYPE animate(@Nonnull IJSExpression properties) { return animate ().arg (properties); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull String content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull IJson content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content) { return append ().arg (content); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull String content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
public THISTYPE append(@Nonnull JSAnonymousFunction function) { return append ().arg (function); }

@Nonnull
public THISTYPE appendTo(@Nonnull IJSExpression target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull IJQuerySelector target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull JQuerySelectorList target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull EHTMLElement target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull ICSSClassProvider target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull IHCNode target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull String target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull JSArray target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE appendTo(@Nonnull JQueryInvocation target) { return appendTo ().arg (target); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName) { return attr ().arg (attributeName); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName) { return attr ().arg (attributeName); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName) { return attr ().arg (attributeName); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName) { return attr ().arg (attributeName); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
public THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
public THISTYPE attr(@Nonnull IJson attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
public THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
public THISTYPE attr(@Nonnull String attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull String content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull IJson content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content) { return before ().arg (content); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull String content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
public THISTYPE before(@Nonnull JSAnonymousFunction function) { return before ().arg (function); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData) { return bind ().arg (eventType).arg (eventData); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData) { return bind ().arg (eventType).arg (eventData); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData) { return bind ().arg (eventType).arg (eventData); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData) { return bind ().arg (eventType).arg (eventData); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return bind ().arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJSExpression eventType) { return bind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJson eventType) { return bind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IHCNode eventType) { return bind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull String eventType) { return bind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, boolean preventBubble) { return bind ().arg (eventType).arg (eventData).arg (preventBubble); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, boolean preventBubble) { return bind ().arg (eventType).arg (eventData).arg (preventBubble); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, boolean preventBubble) { return bind ().arg (eventType).arg (eventData).arg (preventBubble); }

@Deprecated
@Nonnull
public THISTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, boolean preventBubble) { return bind ().arg (eventType).arg (eventData).arg (preventBubble); }

@Nonnull
public THISTYPE blur(@Nonnull IJSExpression handler) { return blur ().arg (handler); }

@Nonnull
public THISTYPE blur(@Nonnull JSAnonymousFunction handler) { return blur ().arg (handler); }

@Nonnull
public THISTYPE blur(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return blur ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE blur(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return blur ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE callbacks_add(@Nonnull IJSExpression callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
public THISTYPE callbacks_add(@Nonnull JSAnonymousFunction callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
public THISTYPE callbacks_add(@Nonnull JSArray callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
public THISTYPE callbacks_fire(@Nonnull IJSExpression arguments) { return callbacks_fire ().arg (arguments); }

@Nonnull
public THISTYPE callbacks_fireWith(@Nonnull IJSExpression context) { return callbacks_fireWith ().arg (context); }

@Nonnull
public THISTYPE callbacks_fireWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return callbacks_fireWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE callbacks_has(@Nonnull IJSExpression callback) { return callbacks_has ().arg (callback); }

@Nonnull
public THISTYPE callbacks_has(@Nonnull JSAnonymousFunction callback) { return callbacks_has ().arg (callback); }

@Nonnull
public THISTYPE callbacks_remove(@Nonnull IJSExpression callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
public THISTYPE callbacks_remove(@Nonnull JSAnonymousFunction callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
public THISTYPE callbacks_remove(@Nonnull JSArray callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
public THISTYPE change(@Nonnull IJSExpression handler) { return change ().arg (handler); }

@Nonnull
public THISTYPE change(@Nonnull JSAnonymousFunction handler) { return change ().arg (handler); }

@Nonnull
public THISTYPE change(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return change ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE change(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return change ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE children(@Nonnull IJSExpression selector) { return children ().arg (selector); }

@Nonnull
public THISTYPE children(@Nonnull IJQuerySelector selector) { return children ().arg (selector); }

@Nonnull
public THISTYPE children(@Nonnull JQuerySelectorList selector) { return children ().arg (selector); }

@Nonnull
public THISTYPE children(@Nonnull EHTMLElement selector) { return children ().arg (selector); }

@Nonnull
public THISTYPE children(@Nonnull ICSSClassProvider selector) { return children ().arg (selector); }

@Nonnull
public THISTYPE clearQueue(@Nonnull IJSExpression queueName) { return clearQueue ().arg (queueName); }

@Nonnull
public THISTYPE clearQueue(@Nonnull IJson queueName) { return clearQueue ().arg (queueName); }

@Nonnull
public THISTYPE clearQueue(@Nonnull IHCNode queueName) { return clearQueue ().arg (queueName); }

@Nonnull
public THISTYPE clearQueue(@Nonnull String queueName) { return clearQueue ().arg (queueName); }

@Nonnull
public THISTYPE click(@Nonnull IJSExpression handler) { return click ().arg (handler); }

@Nonnull
public THISTYPE click(@Nonnull JSAnonymousFunction handler) { return click ().arg (handler); }

@Nonnull
public THISTYPE click(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return click ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE click(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return click ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE _clone(@Nonnull IJSExpression withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@Nonnull
public THISTYPE _clone(boolean withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@Nonnull
public THISTYPE _clone(@Nonnull IJSExpression withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
public THISTYPE _clone(boolean withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
public THISTYPE _clone(@Nonnull IJSExpression withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
public THISTYPE _clone(boolean withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
public THISTYPE closest(@Nonnull IJSExpression selector) { return closest ().arg (selector); }

@Nonnull
public THISTYPE closest(@Nonnull IJQuerySelector selector) { return closest ().arg (selector); }

@Nonnull
public THISTYPE closest(@Nonnull JQuerySelectorList selector) { return closest ().arg (selector); }

@Nonnull
public THISTYPE closest(@Nonnull EHTMLElement selector) { return closest ().arg (selector); }

@Nonnull
public THISTYPE closest(@Nonnull ICSSClassProvider selector) { return closest ().arg (selector); }

@Nonnull
public THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
public THISTYPE closest(@Nonnull JQueryInvocation selection) { return closest ().arg (selection); }

@Nonnull
public THISTYPE closest(@Nonnull String element) { return closest ().arg (element); }

@Deprecated
@Nonnull
public THISTYPE closest(@Nonnull JSArray selectors) { return closest ().arg (selectors); }

@Deprecated
@Nonnull
public THISTYPE closest(@Nonnull JSArray selectors, @Nonnull IJSExpression context) { return closest ().arg (selectors).arg (context); }

@Deprecated
@Nonnull
public THISTYPE closest(@Nonnull JSArray selectors, @Nonnull EHTMLElement context) { return closest ().arg (selectors).arg (context); }

@Deprecated
@Nonnull
public THISTYPE closest(@Nonnull JSArray selectors, @Nonnull String context) { return closest ().arg (selectors).arg (context); }

@Nonnull
public THISTYPE contextmenu(@Nonnull IJSExpression handler) { return contextmenu ().arg (handler); }

@Nonnull
public THISTYPE contextmenu(@Nonnull JSAnonymousFunction handler) { return contextmenu ().arg (handler); }

@Nonnull
public THISTYPE contextmenu(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return contextmenu ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE contextmenu(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return contextmenu ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName) { return css ().arg (propertyName); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName) { return css ().arg (propertyName); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName) { return css ().arg (propertyName); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName) { return css ().arg (propertyName); }

@Nonnull
public THISTYPE css(@Nonnull JSArray propertyNames) { return css ().arg (propertyNames); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE css(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE css(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE data(@Nonnull IJSExpression key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
public THISTYPE data(@Nonnull IJson key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
public THISTYPE data(@Nonnull IHCNode key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
public THISTYPE data(@Nonnull String key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
public THISTYPE data(@Nonnull IJSExpression obj) { return data ().arg (obj); }

@Nonnull
public THISTYPE data(@Nonnull IJson key) { return data ().arg (key); }

@Nonnull
public THISTYPE data(@Nonnull IHCNode key) { return data ().arg (key); }

@Nonnull
public THISTYPE data(@Nonnull String key) { return data ().arg (key); }

@Nonnull
public THISTYPE dblclick(@Nonnull IJSExpression handler) { return dblclick ().arg (handler); }

@Nonnull
public THISTYPE dblclick(@Nonnull JSAnonymousFunction handler) { return dblclick ().arg (handler); }

@Nonnull
public THISTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return dblclick ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return dblclick ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@Nonnull
public THISTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@Nonnull
public THISTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
public THISTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
public THISTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
public THISTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
public THISTYPE deferred_done(@Nonnull IJSExpression doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@Nonnull
public THISTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@Nonnull
public THISTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
public THISTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
public THISTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
public THISTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull IJSExpression failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
public THISTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
public THISTYPE deferred_notify(@Nonnull IJSExpression args) { return deferred_notify ().arg (args); }

@Nonnull
public THISTYPE deferred_notifyWith(@Nonnull IJSExpression context) { return deferred_notifyWith ().arg (context); }

@Nonnull
public THISTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_notifyWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_notifyWith ().arg (context).arg (args); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter) { return deferred_pipe ().arg (doneFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter) { return deferred_pipe ().arg (doneFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Deprecated
@Nonnull
public THISTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_pipe ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
public THISTYPE deferred_promise(@Nonnull IJSExpression target) { return deferred_promise ().arg (target); }

@Nonnull
public THISTYPE deferred_reject(@Nonnull IJSExpression args) { return deferred_reject ().arg (args); }

@Nonnull
public THISTYPE deferred_rejectWith(@Nonnull IJSExpression context) { return deferred_rejectWith ().arg (context); }

@Nonnull
public THISTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_rejectWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_rejectWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE deferred_resolve(@Nonnull IJSExpression args) { return deferred_resolve ().arg (args); }

@Nonnull
public THISTYPE deferred_resolveWith(@Nonnull IJSExpression context) { return deferred_resolveWith ().arg (context); }

@Nonnull
public THISTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_resolveWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_resolveWith ().arg (context).arg (args); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneFilter) { return deferred_then ().arg (doneFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter) { return deferred_then ().arg (doneFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
public THISTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
public THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
public THISTYPE delay(@Nonnull IJSExpression duration) { return delay ().arg (duration); }

@Nonnull
public THISTYPE delay(int duration) { return delay ().arg (duration); }

@Nonnull
public THISTYPE delay(long duration) { return delay ().arg (duration); }

@Nonnull
public THISTYPE delay(@Nonnull BigInteger duration) { return delay ().arg (duration); }

@Nonnull
public THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(int duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(long duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(int duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(long duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(int duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(long duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(int duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(long duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
public THISTYPE delay(@Nonnull BigInteger duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return delegate ().arg (selector).arg (eventType).arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression events) { return delegate ().arg (selector).arg (events); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression events) { return delegate ().arg (selector).arg (events); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression events) { return delegate ().arg (selector).arg (events); }

@Deprecated
@Nonnull
public THISTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression events) { return delegate ().arg (selector).arg (events); }

@Nonnull
public THISTYPE dequeue(@Nonnull IJSExpression queueName) { return dequeue ().arg (queueName); }

@Nonnull
public THISTYPE dequeue(@Nonnull IJson queueName) { return dequeue ().arg (queueName); }

@Nonnull
public THISTYPE dequeue(@Nonnull IHCNode queueName) { return dequeue ().arg (queueName); }

@Nonnull
public THISTYPE dequeue(@Nonnull String queueName) { return dequeue ().arg (queueName); }

@Nonnull
public THISTYPE detach(@Nonnull IJSExpression selector) { return detach ().arg (selector); }

@Nonnull
public THISTYPE detach(@Nonnull IJQuerySelector selector) { return detach ().arg (selector); }

@Nonnull
public THISTYPE detach(@Nonnull JQuerySelectorList selector) { return detach ().arg (selector); }

@Nonnull
public THISTYPE detach(@Nonnull EHTMLElement selector) { return detach ().arg (selector); }

@Nonnull
public THISTYPE detach(@Nonnull ICSSClassProvider selector) { return detach ().arg (selector); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJSExpression eventType) { return die ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJson eventType) { return die ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IHCNode eventType) { return die ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull String eventType) { return die ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJson eventType, @Nonnull IJSExpression handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull String eventType, @Nonnull IJSExpression handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJSExpression eventType, @Nonnull IJson handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJson eventType, @Nonnull IJson handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IHCNode eventType, @Nonnull IJson handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull String eventType, @Nonnull IJson handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJSExpression eventType, @Nonnull IHCNode handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJson eventType, @Nonnull IHCNode handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IHCNode eventType, @Nonnull IHCNode handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull String eventType, @Nonnull IHCNode handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJSExpression eventType, @Nonnull String handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IJson eventType, @Nonnull String handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull IHCNode eventType, @Nonnull String handler) { return die ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE die(@Nonnull String eventType, @Nonnull String handler) { return die ().arg (eventType).arg (handler); }

@Nonnull
public THISTYPE each(@Nonnull IJSExpression function) { return each ().arg (function); }

@Nonnull
public THISTYPE each(@Nonnull JSAnonymousFunction function) { return each ().arg (function); }

@Nonnull
public THISTYPE _eq(@Nonnull IJSExpression index) { return _eq ().arg (index); }

@Nonnull
public THISTYPE _eq(int index) { return _eq ().arg (index); }

@Nonnull
public THISTYPE _eq(long index) { return _eq ().arg (index); }

@Nonnull
public THISTYPE _eq(@Nonnull BigInteger index) { return _eq ().arg (index); }

@Deprecated
@Nonnull
public THISTYPE error(@Nonnull IJSExpression handler) { return error ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE error(@Nonnull JSAnonymousFunction handler) { return error ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE error(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return error ().arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE error(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return error ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
public THISTYPE filter(@Nonnull IJSExpression selector) { return filter ().arg (selector); }

@Nonnull
public THISTYPE filter(@Nonnull IJQuerySelector selector) { return filter ().arg (selector); }

@Nonnull
public THISTYPE filter(@Nonnull JQuerySelectorList selector) { return filter ().arg (selector); }

@Nonnull
public THISTYPE filter(@Nonnull EHTMLElement selector) { return filter ().arg (selector); }

@Nonnull
public THISTYPE filter(@Nonnull ICSSClassProvider selector) { return filter ().arg (selector); }

@Nonnull
public THISTYPE filter(@Nonnull JSAnonymousFunction function) { return filter ().arg (function); }

@Nonnull
public THISTYPE filter(@Nonnull String elements) { return filter ().arg (elements); }

@Nonnull
public THISTYPE filter(@Nonnull JQueryInvocation selection) { return filter ().arg (selection); }

@Nonnull
public THISTYPE find(@Nonnull IJSExpression selector) { return find ().arg (selector); }

@Nonnull
public THISTYPE find(@Nonnull IJQuerySelector selector) { return find ().arg (selector); }

@Nonnull
public THISTYPE find(@Nonnull JQuerySelectorList selector) { return find ().arg (selector); }

@Nonnull
public THISTYPE find(@Nonnull EHTMLElement selector) { return find ().arg (selector); }

@Nonnull
public THISTYPE find(@Nonnull ICSSClassProvider selector) { return find ().arg (selector); }

@Nonnull
public THISTYPE find(@Nonnull String element) { return find ().arg (element); }

@Nonnull
public THISTYPE find(@Nonnull JQueryInvocation element) { return find ().arg (element); }

@Nonnull
public THISTYPE finish(@Nonnull IJSExpression queue) { return finish ().arg (queue); }

@Nonnull
public THISTYPE finish(@Nonnull IJson queue) { return finish ().arg (queue); }

@Nonnull
public THISTYPE finish(@Nonnull IHCNode queue) { return finish ().arg (queue); }

@Nonnull
public THISTYPE finish(@Nonnull String queue) { return finish ().arg (queue); }

@Nonnull
public THISTYPE focus(@Nonnull IJSExpression handler) { return focus ().arg (handler); }

@Nonnull
public THISTYPE focus(@Nonnull JSAnonymousFunction handler) { return focus ().arg (handler); }

@Nonnull
public THISTYPE focus(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focus ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE focus(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focus ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE focusin(@Nonnull IJSExpression handler) { return focusin ().arg (handler); }

@Nonnull
public THISTYPE focusin(@Nonnull JSAnonymousFunction handler) { return focusin ().arg (handler); }

@Nonnull
public THISTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focusin ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focusin ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE focusout(@Nonnull IJSExpression handler) { return focusout ().arg (handler); }

@Nonnull
public THISTYPE focusout(@Nonnull JSAnonymousFunction handler) { return focusout ().arg (handler); }

@Nonnull
public THISTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focusout ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focusout ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE get(@Nonnull IJSExpression index) { return get ().arg (index); }

@Nonnull
public THISTYPE get(int index) { return get ().arg (index); }

@Nonnull
public THISTYPE get(long index) { return get ().arg (index); }

@Nonnull
public THISTYPE get(@Nonnull BigInteger index) { return get ().arg (index); }

@Nonnull
public THISTYPE has(@Nonnull IJSExpression selector) { return has ().arg (selector); }

@Nonnull
public THISTYPE has(@Nonnull IJson selector) { return has ().arg (selector); }

@Nonnull
public THISTYPE has(@Nonnull IHCNode selector) { return has ().arg (selector); }

@Nonnull
public THISTYPE has(@Nonnull String selector) { return has ().arg (selector); }

@Nonnull
public THISTYPE has(@Nonnull EHTMLElement contained) { return has ().arg (contained); }

@Nonnull
public THISTYPE hasClass(@Nonnull IJSExpression className) { return hasClass ().arg (className); }

@Nonnull
public THISTYPE hasClass(@Nonnull IJson className) { return hasClass ().arg (className); }

@Nonnull
public THISTYPE hasClass(@Nonnull IHCNode className) { return hasClass ().arg (className); }

@Nonnull
public THISTYPE hasClass(@Nonnull String className) { return hasClass ().arg (className); }

@Nonnull
public THISTYPE height(@Nonnull IJSExpression value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull IJson value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull IHCNode value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull String value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(int value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(long value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull BigInteger value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(double value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull BigDecimal value) { return height ().arg (value); }

@Nonnull
public THISTYPE height(@Nonnull JSAnonymousFunction function) { return height ().arg (function); }

@Nonnull
public THISTYPE hide(@Nonnull IJSExpression duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(int duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(long duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(@Nonnull BigInteger duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(double duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(@Nonnull BigDecimal duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(@Nonnull IJson duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(@Nonnull IHCNode duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hide(@Nonnull String duration) { return hide ().arg (duration); }

@Nonnull
public THISTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
public THISTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
public THISTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
public THISTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
public THISTYPE hover(@Nonnull IJSExpression handlerInOut) { return hover ().arg (handlerInOut); }

@Nonnull
public THISTYPE hover(@Nonnull JSAnonymousFunction handlerInOut) { return hover ().arg (handlerInOut); }

@Nonnull
public THISTYPE html(@Nonnull IJSExpression htmlString) { return html ().arg (htmlString); }

@Nonnull
public THISTYPE html(@Nonnull IHCNode htmlString) { return html ().arg (htmlString); }

@Nonnull
public THISTYPE html(@Nonnull String htmlString) { return html ().arg (htmlString); }

@Nonnull
public THISTYPE html(@Nonnull JSAnonymousFunction function) { return html ().arg (function); }

@Nonnull
public THISTYPE index(@Nonnull IJSExpression selector) { return index ().arg (selector); }

@Nonnull
public THISTYPE index(@Nonnull IJQuerySelector selector) { return index ().arg (selector); }

@Nonnull
public THISTYPE index(@Nonnull JQuerySelectorList selector) { return index ().arg (selector); }

@Nonnull
public THISTYPE index(@Nonnull EHTMLElement selector) { return index ().arg (selector); }

@Nonnull
public THISTYPE index(@Nonnull ICSSClassProvider selector) { return index ().arg (selector); }

@Nonnull
public THISTYPE index(@Nonnull String element) { return index ().arg (element); }

@Nonnull
public THISTYPE index(@Nonnull JQueryInvocation element) { return index ().arg (element); }

@Nonnull
public THISTYPE innerHeight(@Nonnull IJSExpression value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull IJson value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull IHCNode value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull String value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(int value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(long value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull BigInteger value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(double value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull BigDecimal value) { return innerHeight ().arg (value); }

@Nonnull
public THISTYPE innerHeight(@Nonnull JSAnonymousFunction function) { return innerHeight ().arg (function); }

@Nonnull
public THISTYPE innerWidth(@Nonnull IJSExpression value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull IJson value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull IHCNode value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull String value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(int value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(long value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull BigInteger value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(double value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull BigDecimal value) { return innerWidth ().arg (value); }

@Nonnull
public THISTYPE innerWidth(@Nonnull JSAnonymousFunction function) { return innerWidth ().arg (function); }

@Nonnull
public THISTYPE insertAfter(@Nonnull IJSExpression target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull IJQuerySelector target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull JQuerySelectorList target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull EHTMLElement target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull ICSSClassProvider target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull IHCNode target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull String target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull JSArray target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertAfter(@Nonnull JQueryInvocation target) { return insertAfter ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull IJSExpression target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull IJQuerySelector target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull JQuerySelectorList target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull EHTMLElement target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull ICSSClassProvider target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull IHCNode target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull String target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull JSArray target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE insertBefore(@Nonnull JQueryInvocation target) { return insertBefore ().arg (target); }

@Nonnull
public THISTYPE is(@Nonnull IJSExpression selector) { return is ().arg (selector); }

@Nonnull
public THISTYPE is(@Nonnull IJQuerySelector selector) { return is ().arg (selector); }

@Nonnull
public THISTYPE is(@Nonnull JQuerySelectorList selector) { return is ().arg (selector); }

@Nonnull
public THISTYPE is(@Nonnull EHTMLElement selector) { return is ().arg (selector); }

@Nonnull
public THISTYPE is(@Nonnull ICSSClassProvider selector) { return is ().arg (selector); }

@Nonnull
public THISTYPE is(@Nonnull JSAnonymousFunction function) { return is ().arg (function); }

@Nonnull
public THISTYPE is(@Nonnull JQueryInvocation selection) { return is ().arg (selection); }

@Nonnull
public THISTYPE is(@Nonnull String elements) { return is ().arg (elements); }

@Nonnull
public THISTYPE keydown(@Nonnull IJSExpression handler) { return keydown ().arg (handler); }

@Nonnull
public THISTYPE keydown(@Nonnull JSAnonymousFunction handler) { return keydown ().arg (handler); }

@Nonnull
public THISTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keydown ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keydown ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE keypress(@Nonnull IJSExpression handler) { return keypress ().arg (handler); }

@Nonnull
public THISTYPE keypress(@Nonnull JSAnonymousFunction handler) { return keypress ().arg (handler); }

@Nonnull
public THISTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keypress ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keypress ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE keyup(@Nonnull IJSExpression handler) { return keyup ().arg (handler); }

@Nonnull
public THISTYPE keyup(@Nonnull JSAnonymousFunction handler) { return keyup ().arg (handler); }

@Nonnull
public THISTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keyup ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keyup ().arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJson events, @Nonnull IJSExpression handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull String events, @Nonnull IJSExpression handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJSExpression events, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJson events, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IHCNode events, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull String events, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return live ().arg (events).arg (data).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE live(@Nonnull IJSExpression events) { return live ().arg (events); }

@Deprecated
@Nonnull
public THISTYPE load(@Nonnull IJSExpression handler) { return load ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE load(@Nonnull JSAnonymousFunction handler) { return load ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE load(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return load ().arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE load(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return load ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE load(@Nonnull IJson url) { return load ().arg (url); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url) { return load ().arg (url); }

@Nonnull
public THISTYPE load(@Nonnull String url) { return load ().arg (url); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
public THISTYPE map(@Nonnull IJSExpression callback) { return map ().arg (callback); }

@Nonnull
public THISTYPE map(@Nonnull JSAnonymousFunction callback) { return map ().arg (callback); }

@Nonnull
public THISTYPE mousedown(@Nonnull IJSExpression handler) { return mousedown ().arg (handler); }

@Nonnull
public THISTYPE mousedown(@Nonnull JSAnonymousFunction handler) { return mousedown ().arg (handler); }

@Nonnull
public THISTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mousedown ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mousedown ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseenter(@Nonnull IJSExpression handler) { return mouseenter ().arg (handler); }

@Nonnull
public THISTYPE mouseenter(@Nonnull JSAnonymousFunction handler) { return mouseenter ().arg (handler); }

@Nonnull
public THISTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseenter ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseenter ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseleave(@Nonnull IJSExpression handler) { return mouseleave ().arg (handler); }

@Nonnull
public THISTYPE mouseleave(@Nonnull JSAnonymousFunction handler) { return mouseleave ().arg (handler); }

@Nonnull
public THISTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseleave ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseleave ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mousemove(@Nonnull IJSExpression handler) { return mousemove ().arg (handler); }

@Nonnull
public THISTYPE mousemove(@Nonnull JSAnonymousFunction handler) { return mousemove ().arg (handler); }

@Nonnull
public THISTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mousemove ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mousemove ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseout(@Nonnull IJSExpression handler) { return mouseout ().arg (handler); }

@Nonnull
public THISTYPE mouseout(@Nonnull JSAnonymousFunction handler) { return mouseout ().arg (handler); }

@Nonnull
public THISTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseout ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseout ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseover(@Nonnull IJSExpression handler) { return mouseover ().arg (handler); }

@Nonnull
public THISTYPE mouseover(@Nonnull JSAnonymousFunction handler) { return mouseover ().arg (handler); }

@Nonnull
public THISTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseover ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseover ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseup(@Nonnull IJSExpression handler) { return mouseup ().arg (handler); }

@Nonnull
public THISTYPE mouseup(@Nonnull JSAnonymousFunction handler) { return mouseup ().arg (handler); }

@Nonnull
public THISTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseup ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseup ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE next(@Nonnull IJSExpression selector) { return next ().arg (selector); }

@Nonnull
public THISTYPE next(@Nonnull IJQuerySelector selector) { return next ().arg (selector); }

@Nonnull
public THISTYPE next(@Nonnull JQuerySelectorList selector) { return next ().arg (selector); }

@Nonnull
public THISTYPE next(@Nonnull EHTMLElement selector) { return next ().arg (selector); }

@Nonnull
public THISTYPE next(@Nonnull ICSSClassProvider selector) { return next ().arg (selector); }

@Nonnull
public THISTYPE nextAll(@Nonnull IJSExpression selector) { return nextAll ().arg (selector); }

@Nonnull
public THISTYPE nextAll(@Nonnull IJson selector) { return nextAll ().arg (selector); }

@Nonnull
public THISTYPE nextAll(@Nonnull IHCNode selector) { return nextAll ().arg (selector); }

@Nonnull
public THISTYPE nextAll(@Nonnull String selector) { return nextAll ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector) { return nextUntil ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector) { return nextUntil ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector) { return nextUntil ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector) { return nextUntil ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector) { return nextUntil ().arg (selector); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element) { return nextUntil ().arg (element); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element) { return nextUntil ().arg (element); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE _not(@Nonnull IJSExpression selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull IJQuerySelector selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull JQuerySelectorList selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull EHTMLElement selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull ICSSClassProvider selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull String selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull JSArray selector) { return _not ().arg (selector); }

@Nonnull
public THISTYPE _not(@Nonnull JSAnonymousFunction function) { return _not ().arg (function); }

@Nonnull
public THISTYPE _not(@Nonnull JQueryInvocation selection) { return _not ().arg (selection); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events) { return off ().arg (events); }

@Nonnull
public THISTYPE off(@Nonnull IJson events) { return off ().arg (events); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events) { return off ().arg (events); }

@Nonnull
public THISTYPE off(@Nonnull String events) { return off ().arg (events); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
public THISTYPE offset(@Nonnull IJSExpression coordinates) { return offset ().arg (coordinates); }

@Nonnull
public THISTYPE offset(@Nonnull JSAnonymousFunction function) { return offset ().arg (function); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE on(@Nonnull IJSExpression events) { return on ().arg (events); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
public THISTYPE one(@Nonnull IJSExpression events) { return one ().arg (events); }

@Nonnull
public THISTYPE outerHeight(@Nonnull IJSExpression includeMargin) { return outerHeight ().arg (includeMargin); }

@Nonnull
public THISTYPE outerHeight(boolean includeMargin) { return outerHeight ().arg (includeMargin); }

@Nonnull
public THISTYPE outerHeight(@Nonnull IJson value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(@Nonnull IHCNode value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(@Nonnull String value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(int value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(long value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(@Nonnull BigInteger value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(double value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(@Nonnull BigDecimal value) { return outerHeight ().arg (value); }

@Nonnull
public THISTYPE outerHeight(@Nonnull JSAnonymousFunction function) { return outerHeight ().arg (function); }

@Nonnull
public THISTYPE outerWidth(@Nonnull IJSExpression includeMargin) { return outerWidth ().arg (includeMargin); }

@Nonnull
public THISTYPE outerWidth(boolean includeMargin) { return outerWidth ().arg (includeMargin); }

@Nonnull
public THISTYPE outerWidth(@Nonnull IJson value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(@Nonnull IHCNode value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(@Nonnull String value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(int value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(long value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(@Nonnull BigInteger value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(double value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(@Nonnull BigDecimal value) { return outerWidth ().arg (value); }

@Nonnull
public THISTYPE outerWidth(@Nonnull JSAnonymousFunction function) { return outerWidth ().arg (function); }

@Nonnull
public THISTYPE parent(@Nonnull IJSExpression selector) { return parent ().arg (selector); }

@Nonnull
public THISTYPE parent(@Nonnull IJQuerySelector selector) { return parent ().arg (selector); }

@Nonnull
public THISTYPE parent(@Nonnull JQuerySelectorList selector) { return parent ().arg (selector); }

@Nonnull
public THISTYPE parent(@Nonnull EHTMLElement selector) { return parent ().arg (selector); }

@Nonnull
public THISTYPE parent(@Nonnull ICSSClassProvider selector) { return parent ().arg (selector); }

@Nonnull
public THISTYPE parents(@Nonnull IJSExpression selector) { return parents ().arg (selector); }

@Nonnull
public THISTYPE parents(@Nonnull IJQuerySelector selector) { return parents ().arg (selector); }

@Nonnull
public THISTYPE parents(@Nonnull JQuerySelectorList selector) { return parents ().arg (selector); }

@Nonnull
public THISTYPE parents(@Nonnull EHTMLElement selector) { return parents ().arg (selector); }

@Nonnull
public THISTYPE parents(@Nonnull ICSSClassProvider selector) { return parents ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector) { return parentsUntil ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector) { return parentsUntil ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector) { return parentsUntil ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector) { return parentsUntil ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector) { return parentsUntil ().arg (selector); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element) { return parentsUntil ().arg (element); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element) { return parentsUntil ().arg (element); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull String content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content) { return prepend ().arg (content); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull String content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
public THISTYPE prepend(@Nonnull JSAnonymousFunction function) { return prepend ().arg (function); }

@Nonnull
public THISTYPE prependTo(@Nonnull IJSExpression target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull IJQuerySelector target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull JQuerySelectorList target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull EHTMLElement target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull ICSSClassProvider target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull IHCNode target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull String target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull JSArray target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prependTo(@Nonnull JQueryInvocation target) { return prependTo ().arg (target); }

@Nonnull
public THISTYPE prev(@Nonnull IJSExpression selector) { return prev ().arg (selector); }

@Nonnull
public THISTYPE prev(@Nonnull IJQuerySelector selector) { return prev ().arg (selector); }

@Nonnull
public THISTYPE prev(@Nonnull JQuerySelectorList selector) { return prev ().arg (selector); }

@Nonnull
public THISTYPE prev(@Nonnull EHTMLElement selector) { return prev ().arg (selector); }

@Nonnull
public THISTYPE prev(@Nonnull ICSSClassProvider selector) { return prev ().arg (selector); }

@Nonnull
public THISTYPE prevAll(@Nonnull IJSExpression selector) { return prevAll ().arg (selector); }

@Nonnull
public THISTYPE prevAll(@Nonnull IJQuerySelector selector) { return prevAll ().arg (selector); }

@Nonnull
public THISTYPE prevAll(@Nonnull JQuerySelectorList selector) { return prevAll ().arg (selector); }

@Nonnull
public THISTYPE prevAll(@Nonnull EHTMLElement selector) { return prevAll ().arg (selector); }

@Nonnull
public THISTYPE prevAll(@Nonnull ICSSClassProvider selector) { return prevAll ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector) { return prevUntil ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector) { return prevUntil ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector) { return prevUntil ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector) { return prevUntil ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector) { return prevUntil ().arg (selector); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element) { return prevUntil ().arg (element); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element) { return prevUntil ().arg (element); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
public THISTYPE promise(@Nonnull IJSExpression type) { return promise ().arg (type); }

@Nonnull
public THISTYPE promise(@Nonnull IJson type) { return promise ().arg (type); }

@Nonnull
public THISTYPE promise(@Nonnull IHCNode type) { return promise ().arg (type); }

@Nonnull
public THISTYPE promise(@Nonnull String type) { return promise ().arg (type); }

@Nonnull
public THISTYPE promise(@Nonnull IJSExpression type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
public THISTYPE promise(@Nonnull IJson type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
public THISTYPE promise(@Nonnull IHCNode type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
public THISTYPE promise(@Nonnull String type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
public THISTYPE prop(@Nonnull IJSExpression propertyName) { return prop ().arg (propertyName); }

@Nonnull
public THISTYPE prop(@Nonnull IJson propertyName) { return prop ().arg (propertyName); }

@Nonnull
public THISTYPE prop(@Nonnull IHCNode propertyName) { return prop ().arg (propertyName); }

@Nonnull
public THISTYPE prop(@Nonnull String propertyName) { return prop ().arg (propertyName); }

@Nonnull
public THISTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE prop(@Nonnull IJson propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE prop(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE prop(@Nonnull String propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
public THISTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE prop(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE prop(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE prop(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements) { return pushStack ().arg (elements); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements) { return pushStack ().arg (elements); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
public THISTYPE queue(@Nonnull IJSExpression queueName) { return queue ().arg (queueName); }

@Nonnull
public THISTYPE queue(@Nonnull IJson queueName) { return queue ().arg (queueName); }

@Nonnull
public THISTYPE queue(@Nonnull IHCNode queueName) { return queue ().arg (queueName); }

@Nonnull
public THISTYPE queue(@Nonnull String queueName) { return queue ().arg (queueName); }

@Nonnull
public THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IJson queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull String queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IJson queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull String queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
public THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
public THISTYPE queue(@Nonnull IJson queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
public THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
public THISTYPE queue(@Nonnull String queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
public THISTYPE ready(@Nonnull IJSExpression handler) { return ready ().arg (handler); }

@Nonnull
public THISTYPE ready(@Nonnull JSAnonymousFunction handler) { return ready ().arg (handler); }

@Nonnull
public THISTYPE remove(@Nonnull IJSExpression selector) { return remove ().arg (selector); }

@Nonnull
public THISTYPE remove(@Nonnull IJson selector) { return remove ().arg (selector); }

@Nonnull
public THISTYPE remove(@Nonnull IHCNode selector) { return remove ().arg (selector); }

@Nonnull
public THISTYPE remove(@Nonnull String selector) { return remove ().arg (selector); }

@Nonnull
public THISTYPE removeAttr(@Nonnull IJSExpression attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
public THISTYPE removeAttr(@Nonnull IJson attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
public THISTYPE removeAttr(@Nonnull IHCNode attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
public THISTYPE removeAttr(@Nonnull String attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
public THISTYPE removeClass(@Nonnull IJSExpression className) { return removeClass ().arg (className); }

@Nonnull
public THISTYPE removeClass(@Nonnull IJson className) { return removeClass ().arg (className); }

@Nonnull
public THISTYPE removeClass(@Nonnull IHCNode className) { return removeClass ().arg (className); }

@Nonnull
public THISTYPE removeClass(@Nonnull String className) { return removeClass ().arg (className); }

@Nonnull
public THISTYPE removeClass(@Nonnull JSAnonymousFunction function) { return removeClass ().arg (function); }

@Nonnull
public THISTYPE removeData(@Nonnull IJSExpression name) { return removeData ().arg (name); }

@Nonnull
public THISTYPE removeData(@Nonnull IJson name) { return removeData ().arg (name); }

@Nonnull
public THISTYPE removeData(@Nonnull IHCNode name) { return removeData ().arg (name); }

@Nonnull
public THISTYPE removeData(@Nonnull String name) { return removeData ().arg (name); }

@Nonnull
public THISTYPE removeData(@Nonnull JSArray list) { return removeData ().arg (list); }

@Nonnull
public THISTYPE removeProp(@Nonnull IJSExpression propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
public THISTYPE removeProp(@Nonnull IJson propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
public THISTYPE removeProp(@Nonnull IHCNode propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
public THISTYPE removeProp(@Nonnull String propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
public THISTYPE replaceAll(@Nonnull IJSExpression target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull IJQuerySelector target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull JQuerySelectorList target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull EHTMLElement target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull ICSSClassProvider target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull JQueryInvocation target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull JSArray target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceAll(@Nonnull String target) { return replaceAll ().arg (target); }

@Nonnull
public THISTYPE replaceWith(@Nonnull IJSExpression newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull IHCNode newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull String newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull EHTMLElement newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull JSArray newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull JQueryInvocation newContent) { return replaceWith ().arg (newContent); }

@Nonnull
public THISTYPE replaceWith(@Nonnull JSAnonymousFunction function) { return replaceWith ().arg (function); }

@Nonnull
public THISTYPE resize(@Nonnull IJSExpression handler) { return resize ().arg (handler); }

@Nonnull
public THISTYPE resize(@Nonnull JSAnonymousFunction handler) { return resize ().arg (handler); }

@Nonnull
public THISTYPE resize(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return resize ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE resize(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return resize ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE scroll(@Nonnull IJSExpression handler) { return scroll ().arg (handler); }

@Nonnull
public THISTYPE scroll(@Nonnull JSAnonymousFunction handler) { return scroll ().arg (handler); }

@Nonnull
public THISTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return scroll ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return scroll ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE scrollLeft(@Nonnull IJSExpression value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollLeft(int value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollLeft(long value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollLeft(@Nonnull BigInteger value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollLeft(double value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollLeft(@Nonnull BigDecimal value) { return scrollLeft ().arg (value); }

@Nonnull
public THISTYPE scrollTop(@Nonnull IJSExpression value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE scrollTop(int value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE scrollTop(long value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE scrollTop(@Nonnull BigInteger value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE scrollTop(double value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE scrollTop(@Nonnull BigDecimal value) { return scrollTop ().arg (value); }

@Nonnull
public THISTYPE select(@Nonnull IJSExpression handler) { return select ().arg (handler); }

@Nonnull
public THISTYPE select(@Nonnull JSAnonymousFunction handler) { return select ().arg (handler); }

@Nonnull
public THISTYPE select(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return select ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE select(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return select ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE show(@Nonnull IJSExpression duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(int duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(long duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(@Nonnull BigInteger duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(double duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(@Nonnull BigDecimal duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(@Nonnull IJson duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(@Nonnull IHCNode duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE show(@Nonnull String duration) { return show ().arg (duration); }

@Nonnull
public THISTYPE siblings(@Nonnull IJSExpression selector) { return siblings ().arg (selector); }

@Nonnull
public THISTYPE siblings(@Nonnull IJQuerySelector selector) { return siblings ().arg (selector); }

@Nonnull
public THISTYPE siblings(@Nonnull JQuerySelectorList selector) { return siblings ().arg (selector); }

@Nonnull
public THISTYPE siblings(@Nonnull EHTMLElement selector) { return siblings ().arg (selector); }

@Nonnull
public THISTYPE siblings(@Nonnull ICSSClassProvider selector) { return siblings ().arg (selector); }

@Nonnull
public THISTYPE slice(@Nonnull IJSExpression start) { return slice ().arg (start); }

@Nonnull
public THISTYPE slice(int start) { return slice ().arg (start); }

@Nonnull
public THISTYPE slice(long start) { return slice ().arg (start); }

@Nonnull
public THISTYPE slice(@Nonnull BigInteger start) { return slice ().arg (start); }

@Nonnull
public THISTYPE slice(@Nonnull IJSExpression start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(int start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(long start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull BigInteger start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull IJSExpression start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(int start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(long start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull BigInteger start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull IJSExpression start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(int start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(long start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull BigInteger start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull IJSExpression start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(int start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(long start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE slice(@Nonnull BigInteger start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression clearQueue) { return stop ().arg (clearQueue); }

@Nonnull
public THISTYPE stop(boolean clearQueue) { return stop ().arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(boolean clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue) { return stop ().arg (queue); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue) { return stop ().arg (queue); }

@Nonnull
public THISTYPE stop(@Nonnull String queue) { return stop ().arg (queue); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IJson queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE stop(@Nonnull String queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
public THISTYPE submit(@Nonnull IJSExpression handler) { return submit ().arg (handler); }

@Nonnull
public THISTYPE submit(@Nonnull JSAnonymousFunction handler) { return submit ().arg (handler); }

@Nonnull
public THISTYPE submit(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return submit ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE submit(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return submit ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE text(@Nonnull IJSExpression text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull IJson text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull IHCNode text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull String text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(int text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(long text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull BigInteger text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(double text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull BigDecimal text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(boolean text) { return text ().arg (text); }

@Nonnull
public THISTYPE text(@Nonnull JSAnonymousFunction function) { return text ().arg (function); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1) { return toggle ().arg (handler).arg (handler1); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1) { return toggle ().arg (handler).arg (handler1); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1) { return toggle ().arg (handler).arg (handler1); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1) { return toggle ().arg (handler).arg (handler1); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1, @Nonnull IJSExpression handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1, @Nonnull IJSExpression handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1, @Nonnull IJSExpression handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1, @Nonnull IJSExpression handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1, @Nonnull JSAnonymousFunction handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1, @Nonnull JSAnonymousFunction handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1, @Nonnull JSAnonymousFunction handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Deprecated
@Nonnull
public THISTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1, @Nonnull JSAnonymousFunction handler2) { return toggle ().arg (handler).arg (handler1).arg (handler2); }

@Nonnull
public THISTYPE toggle(@Nonnull IJSExpression duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(int duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(long duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(@Nonnull BigInteger duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(double duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(@Nonnull BigDecimal duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(@Nonnull IJson duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(@Nonnull IHCNode duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(@Nonnull String duration) { return toggle ().arg (duration); }

@Nonnull
public THISTYPE toggle(boolean display) { return toggle ().arg (display); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJSExpression className) { return toggleClass ().arg (className); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJson className) { return toggleClass ().arg (className); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IHCNode className) { return toggleClass ().arg (className); }

@Nonnull
public THISTYPE toggleClass(@Nonnull String className) { return toggleClass ().arg (className); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJSExpression className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJson className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IHCNode className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull String className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJSExpression className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IJson className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull IHCNode className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull String className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull JSAnonymousFunction function) { return toggleClass ().arg (function); }

@Nonnull
public THISTYPE toggleClass(@Nonnull JSAnonymousFunction function, @Nonnull IJSExpression state) { return toggleClass ().arg (function).arg (state); }

@Nonnull
public THISTYPE toggleClass(@Nonnull JSAnonymousFunction function, boolean state) { return toggleClass ().arg (function).arg (state); }

@Deprecated
@Nonnull
public THISTYPE toggleClass(boolean state) { return toggleClass ().arg (state); }

@Nonnull
public THISTYPE trigger(@Nonnull IJSExpression eventType) { return trigger ().arg (eventType); }

@Nonnull
public THISTYPE trigger(@Nonnull IJson eventType) { return trigger ().arg (eventType); }

@Nonnull
public THISTYPE trigger(@Nonnull IHCNode eventType) { return trigger ().arg (eventType); }

@Nonnull
public THISTYPE trigger(@Nonnull String eventType) { return trigger ().arg (eventType); }

@Nonnull
public THISTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull String eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull IJson eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE trigger(@Nonnull String eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJSExpression eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJson eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IHCNode eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull String eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull String eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
public THISTYPE triggerHandler(@Nonnull String eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJSExpression eventType) { return unbind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJson eventType) { return unbind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IHCNode eventType) { return unbind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull String eventType) { return unbind ().arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJson eventType, @Nonnull IJSExpression handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull String eventType, @Nonnull IJSExpression handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return unbind ().arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJSExpression eventType, boolean _false) { return unbind ().arg (eventType).arg (_false); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IJson eventType, boolean _false) { return unbind ().arg (eventType).arg (_false); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull IHCNode eventType, boolean _false) { return unbind ().arg (eventType).arg (_false); }

@Deprecated
@Nonnull
public THISTYPE unbind(@Nonnull String eventType, boolean _false) { return unbind ().arg (eventType).arg (_false); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull String eventType) { return undelegate ().arg (selector).arg (eventType); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler) { return undelegate ().arg (selector).arg (eventType).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJSExpression namespace) { return undelegate ().arg (namespace); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IJson namespace) { return undelegate ().arg (namespace); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull IHCNode namespace) { return undelegate ().arg (namespace); }

@Deprecated
@Nonnull
public THISTYPE undelegate(@Nonnull String namespace) { return undelegate ().arg (namespace); }

@Deprecated
@Nonnull
public THISTYPE unload(@Nonnull IJSExpression handler) { return unload ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unload(@Nonnull JSAnonymousFunction handler) { return unload ().arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unload(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return unload ().arg (eventData).arg (handler); }

@Deprecated
@Nonnull
public THISTYPE unload(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return unload ().arg (eventData).arg (handler); }

@Nonnull
public THISTYPE unwrap(@Nonnull IJSExpression selector) { return unwrap ().arg (selector); }

@Nonnull
public THISTYPE unwrap(@Nonnull IJson selector) { return unwrap ().arg (selector); }

@Nonnull
public THISTYPE unwrap(@Nonnull IHCNode selector) { return unwrap ().arg (selector); }

@Nonnull
public THISTYPE unwrap(@Nonnull String selector) { return unwrap ().arg (selector); }

@Nonnull
public THISTYPE val(@Nonnull IJSExpression value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull IJson value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull IHCNode value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull String value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(int value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(long value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull BigInteger value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(double value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull BigDecimal value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull JSArray value) { return val ().arg (value); }

@Nonnull
public THISTYPE val(@Nonnull JSAnonymousFunction function) { return val ().arg (function); }

@Nonnull
public THISTYPE width(@Nonnull IJSExpression value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull IJson value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull IHCNode value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull String value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(int value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(long value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull BigInteger value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(double value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull BigDecimal value) { return width ().arg (value); }

@Nonnull
public THISTYPE width(@Nonnull JSAnonymousFunction function) { return width ().arg (function); }

@Nonnull
public THISTYPE wrap(@Nonnull IJSExpression wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull IJQuerySelector wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull JQuerySelectorList wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull EHTMLElement wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull ICSSClassProvider wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull IHCNode wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull String wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull JQueryInvocation wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrap(@Nonnull JSAnonymousFunction function) { return wrap ().arg (function); }

@Nonnull
public THISTYPE wrapAll(@Nonnull IJSExpression wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull IJQuerySelector wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull JQuerySelectorList wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull EHTMLElement wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull ICSSClassProvider wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull IHCNode wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull String wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull JQueryInvocation wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapAll(@Nonnull JSAnonymousFunction function) { return wrapAll ().arg (function); }

@Nonnull
public THISTYPE wrapInner(@Nonnull IJSExpression wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull IHCNode wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull String wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull IJQuerySelector wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull JQuerySelectorList wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull EHTMLElement wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull ICSSClassProvider wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull JQueryInvocation wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
public THISTYPE wrapInner(@Nonnull JSAnonymousFunction function) { return wrapInner ().arg (function); }

}
