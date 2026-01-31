/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;

import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.json.IJson;
import com.helger.xml.microdom.IMicroQName;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.jquery.supplementary.main.Main_IJQueryInvocationExtended
 * @param <IMPLTYPE> Implementation type
*/
public interface IJQueryInvocationExtended <IMPLTYPE extends IJQueryInvocationExtended <IMPLTYPE>> extends IJQueryInvocation <IMPLTYPE>
{
@NonNull
default IMPLTYPE add(@NonNull final IJSExpression selector) { return add ().arg (selector); }

@NonNull
default IMPLTYPE add(@NonNull final IJQuerySelector selector) { return add ().arg (selector); }

@NonNull
default IMPLTYPE add(@NonNull final JQuerySelectorList selector) { return add ().arg (selector); }

@NonNull
default IMPLTYPE add(@NonNull final EHTMLElement selector) { return add ().arg (selector); }

@NonNull
default IMPLTYPE add(@NonNull final ICSSClassProvider selector) { return add ().arg (selector); }

@NonNull
default IMPLTYPE add(@NonNull final String elements) { return add ().arg (elements); }

@NonNull
default IMPLTYPE add(@NonNull final IHCNode html) { return add ().arg (html); }

@NonNull
default IMPLTYPE add(@NonNull final JQueryInvocation selection) { return add ().arg (selection); }

@NonNull
default IMPLTYPE add(@NonNull IJSExpression selector, @NonNull IJSExpression context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull IJQuerySelector selector, @NonNull IJSExpression context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull JQuerySelectorList selector, @NonNull IJSExpression context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull EHTMLElement selector, @NonNull IJSExpression context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull ICSSClassProvider selector, @NonNull IJSExpression context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull IJSExpression selector, @NonNull EHTMLElement context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull IJQuerySelector selector, @NonNull EHTMLElement context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull JQuerySelectorList selector, @NonNull EHTMLElement context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull EHTMLElement selector, @NonNull EHTMLElement context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull ICSSClassProvider selector, @NonNull EHTMLElement context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull IJSExpression selector, @NonNull String context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull IJQuerySelector selector, @NonNull String context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull JQuerySelectorList selector, @NonNull String context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull EHTMLElement selector, @NonNull String context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE add(@NonNull ICSSClassProvider selector, @NonNull String context) { return add ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE addBack(@NonNull final IJSExpression selector) { return addBack ().arg (selector); }

@NonNull
default IMPLTYPE addBack(@NonNull final IJQuerySelector selector) { return addBack ().arg (selector); }

@NonNull
default IMPLTYPE addBack(@NonNull final JQuerySelectorList selector) { return addBack ().arg (selector); }

@NonNull
default IMPLTYPE addBack(@NonNull final EHTMLElement selector) { return addBack ().arg (selector); }

@NonNull
default IMPLTYPE addBack(@NonNull final ICSSClassProvider selector) { return addBack ().arg (selector); }

@NonNull
default IMPLTYPE addClass(@NonNull final IJSExpression className) { return addClass ().arg (className); }

@NonNull
default IMPLTYPE addClass(@NonNull final IJson className) { return addClass ().arg (className); }

@NonNull
default IMPLTYPE addClass(@NonNull final IHCNode className) { return addClass ().arg (className); }

@NonNull
default IMPLTYPE addClass(@NonNull final String className) { return addClass ().arg (className); }

@NonNull
default IMPLTYPE addClass(@NonNull final JSAnonymousFunction function) { return addClass ().arg (function); }

@NonNull
default IMPLTYPE after(@NonNull final IJSExpression content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final IHCNode content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final String content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final EHTMLElement content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final IJson content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final JSArray content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull final JQueryInvocation content) { return after ().arg (content); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull IJSExpression content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull IHCNode content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull String content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull IJson content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull JSArray content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJSExpression content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IHCNode content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull String content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull EHTMLElement content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull IJson content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JSArray content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull JQueryInvocation content, @NonNull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE after(@NonNull final JSAnonymousFunction function) { return after ().arg (function); }

@NonNull
default IMPLTYPE ajaxComplete(@NonNull final IJSExpression handler) { return ajaxComplete ().arg (handler); }

@NonNull
default IMPLTYPE ajaxComplete(@NonNull final JSAnonymousFunction handler) { return ajaxComplete ().arg (handler); }

@NonNull
default IMPLTYPE ajaxError(@NonNull final IJSExpression handler) { return ajaxError ().arg (handler); }

@NonNull
default IMPLTYPE ajaxError(@NonNull final JSAnonymousFunction handler) { return ajaxError ().arg (handler); }

@NonNull
default IMPLTYPE ajaxSend(@NonNull final IJSExpression handler) { return ajaxSend ().arg (handler); }

@NonNull
default IMPLTYPE ajaxSend(@NonNull final JSAnonymousFunction handler) { return ajaxSend ().arg (handler); }

@NonNull
default IMPLTYPE ajaxStart(@NonNull final IJSExpression handler) { return ajaxStart ().arg (handler); }

@NonNull
default IMPLTYPE ajaxStart(@NonNull final JSAnonymousFunction handler) { return ajaxStart ().arg (handler); }

@NonNull
default IMPLTYPE ajaxStop(@NonNull final IJSExpression handler) { return ajaxStop ().arg (handler); }

@NonNull
default IMPLTYPE ajaxStop(@NonNull final JSAnonymousFunction handler) { return ajaxStop ().arg (handler); }

@NonNull
default IMPLTYPE ajaxSuccess(@NonNull final IJSExpression handler) { return ajaxSuccess ().arg (handler); }

@NonNull
default IMPLTYPE ajaxSuccess(@NonNull final JSAnonymousFunction handler) { return ajaxSuccess ().arg (handler); }

@NonNull
default IMPLTYPE animate(@NonNull final IJSExpression properties) { return animate ().arg (properties); }

@NonNull
default IMPLTYPE append(@NonNull final IJSExpression content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final IHCNode content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final String content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final EHTMLElement content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final IJson content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final JSArray content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull final JQueryInvocation content) { return append ().arg (content); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull IJSExpression content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull IHCNode content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull String content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull IJson content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull JSArray content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJSExpression content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IHCNode content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull String content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull EHTMLElement content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull IJson content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JSArray content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull JQueryInvocation content, @NonNull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE append(@NonNull final JSAnonymousFunction function) { return append ().arg (function); }

@NonNull
default IMPLTYPE appendTo(@NonNull final IJSExpression target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final IJQuerySelector target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final JQuerySelectorList target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final EHTMLElement target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final ICSSClassProvider target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final IHCNode target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final String target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final JSArray target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE appendTo(@NonNull final JQueryInvocation target) { return appendTo ().arg (target); }

@NonNull
default IMPLTYPE attr(@NonNull final IJSExpression attributeName) { return attr ().arg (attributeName); }

@NonNull
default IMPLTYPE attr(@NonNull final IJson attributeName) { return attr ().arg (attributeName); }

@NonNull
default IMPLTYPE attr(@NonNull final IHCNode attributeName) { return attr ().arg (attributeName); }

@NonNull
default IMPLTYPE attr(@NonNull final String attributeName) { return attr ().arg (attributeName); }

@NonNull
default IMPLTYPE attr(@NonNull final IMicroQName attributeName) { return attr ().arg (attributeName); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull IJson value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull IJson value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull IJson value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull IJson value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull IJson value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull String value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull String value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull String value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull String value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull String value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@NonNull
default IMPLTYPE attr(@NonNull IJSExpression attributeName, @NonNull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@NonNull
default IMPLTYPE attr(@NonNull IJson attributeName, @NonNull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@NonNull
default IMPLTYPE attr(@NonNull IHCNode attributeName, @NonNull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@NonNull
default IMPLTYPE attr(@NonNull String attributeName, @NonNull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@NonNull
default IMPLTYPE attr(@NonNull IMicroQName attributeName, @NonNull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@NonNull
default IMPLTYPE before(@NonNull final IJSExpression content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final IHCNode content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final String content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final EHTMLElement content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final IJson content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final JSArray content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull final JQueryInvocation content) { return before ().arg (content); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull IJSExpression content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull IHCNode content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull String content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull IJson content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull JSArray content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJSExpression content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IHCNode content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull String content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull EHTMLElement content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull IJson content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JSArray content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull JQueryInvocation content, @NonNull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE before(@NonNull final JSAnonymousFunction function) { return before ().arg (function); }

@NonNull
default IMPLTYPE blur(@NonNull final IJSExpression handler) { return blur ().arg (handler); }

@NonNull
default IMPLTYPE blur(@NonNull final JSAnonymousFunction handler) { return blur ().arg (handler); }

@NonNull
default IMPLTYPE blur(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return blur ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE blur(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return blur ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE callbacks_add(@NonNull final IJSExpression callbacks) { return callbacks_add ().arg (callbacks); }

@NonNull
default IMPLTYPE callbacks_add(@NonNull final JSAnonymousFunction callbacks) { return callbacks_add ().arg (callbacks); }

@NonNull
default IMPLTYPE callbacks_add(@NonNull final JSArray callbacks) { return callbacks_add ().arg (callbacks); }

@NonNull
default IMPLTYPE callbacks_fire(@NonNull final IJSExpression arguments) { return callbacks_fire ().arg (arguments); }

@NonNull
default IMPLTYPE callbacks_fireWith(@NonNull final IJSExpression context) { return callbacks_fireWith ().arg (context); }

@NonNull
default IMPLTYPE callbacks_fireWith(@NonNull IJSExpression context, @NonNull IJSExpression args) { return callbacks_fireWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE callbacks_has(@NonNull final IJSExpression callback) { return callbacks_has ().arg (callback); }

@NonNull
default IMPLTYPE callbacks_has(@NonNull final JSAnonymousFunction callback) { return callbacks_has ().arg (callback); }

@NonNull
default IMPLTYPE callbacks_remove(@NonNull final IJSExpression callbacks) { return callbacks_remove ().arg (callbacks); }

@NonNull
default IMPLTYPE callbacks_remove(@NonNull final JSAnonymousFunction callbacks) { return callbacks_remove ().arg (callbacks); }

@NonNull
default IMPLTYPE callbacks_remove(@NonNull final JSArray callbacks) { return callbacks_remove ().arg (callbacks); }

@NonNull
default IMPLTYPE change(@NonNull final IJSExpression handler) { return change ().arg (handler); }

@NonNull
default IMPLTYPE change(@NonNull final JSAnonymousFunction handler) { return change ().arg (handler); }

@NonNull
default IMPLTYPE change(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return change ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE change(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return change ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE children(@NonNull final IJSExpression selector) { return children ().arg (selector); }

@NonNull
default IMPLTYPE children(@NonNull final IJQuerySelector selector) { return children ().arg (selector); }

@NonNull
default IMPLTYPE children(@NonNull final JQuerySelectorList selector) { return children ().arg (selector); }

@NonNull
default IMPLTYPE children(@NonNull final EHTMLElement selector) { return children ().arg (selector); }

@NonNull
default IMPLTYPE children(@NonNull final ICSSClassProvider selector) { return children ().arg (selector); }

@NonNull
default IMPLTYPE clearQueue(@NonNull final IJSExpression queueName) { return clearQueue ().arg (queueName); }

@NonNull
default IMPLTYPE clearQueue(@NonNull final IJson queueName) { return clearQueue ().arg (queueName); }

@NonNull
default IMPLTYPE clearQueue(@NonNull final IHCNode queueName) { return clearQueue ().arg (queueName); }

@NonNull
default IMPLTYPE clearQueue(@NonNull final String queueName) { return clearQueue ().arg (queueName); }

@NonNull
default IMPLTYPE click(@NonNull final IJSExpression handler) { return click ().arg (handler); }

@NonNull
default IMPLTYPE click(@NonNull final JSAnonymousFunction handler) { return click ().arg (handler); }

@NonNull
default IMPLTYPE click(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return click ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE click(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return click ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE _clone(@NonNull final IJSExpression withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@NonNull
default IMPLTYPE _clone(final boolean withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@NonNull
default IMPLTYPE _clone(@NonNull IJSExpression withDataAndEvents, @NonNull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@NonNull
default IMPLTYPE _clone(boolean withDataAndEvents, @NonNull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@NonNull
default IMPLTYPE _clone(@NonNull IJSExpression withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@NonNull
default IMPLTYPE _clone(boolean withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@NonNull
default IMPLTYPE closest(@NonNull final IJSExpression selector) { return closest ().arg (selector); }

@NonNull
default IMPLTYPE closest(@NonNull final IJQuerySelector selector) { return closest ().arg (selector); }

@NonNull
default IMPLTYPE closest(@NonNull final JQuerySelectorList selector) { return closest ().arg (selector); }

@NonNull
default IMPLTYPE closest(@NonNull final EHTMLElement selector) { return closest ().arg (selector); }

@NonNull
default IMPLTYPE closest(@NonNull final ICSSClassProvider selector) { return closest ().arg (selector); }

@NonNull
default IMPLTYPE closest(@NonNull IJSExpression selector, @NonNull IJSExpression context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull IJQuerySelector selector, @NonNull IJSExpression context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull JQuerySelectorList selector, @NonNull IJSExpression context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull EHTMLElement selector, @NonNull IJSExpression context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull ICSSClassProvider selector, @NonNull IJSExpression context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull IJSExpression selector, @NonNull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull IJQuerySelector selector, @NonNull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull JQuerySelectorList selector, @NonNull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull EHTMLElement selector, @NonNull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull ICSSClassProvider selector, @NonNull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull IJSExpression selector, @NonNull String context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull IJQuerySelector selector, @NonNull String context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull JQuerySelectorList selector, @NonNull String context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull EHTMLElement selector, @NonNull String context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull ICSSClassProvider selector, @NonNull String context) { return closest ().arg (selector).arg (context); }

@NonNull
default IMPLTYPE closest(@NonNull final JQueryInvocation selection) { return closest ().arg (selection); }

@NonNull
default IMPLTYPE closest(@NonNull final String element) { return closest ().arg (element); }

@NonNull
default IMPLTYPE contextmenu(@NonNull final IJSExpression handler) { return contextmenu ().arg (handler); }

@NonNull
default IMPLTYPE contextmenu(@NonNull final JSAnonymousFunction handler) { return contextmenu ().arg (handler); }

@NonNull
default IMPLTYPE contextmenu(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return contextmenu ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE contextmenu(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return contextmenu ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE css(@NonNull final IJSExpression propertyName) { return css ().arg (propertyName); }

@NonNull
default IMPLTYPE css(@NonNull final IJson propertyName) { return css ().arg (propertyName); }

@NonNull
default IMPLTYPE css(@NonNull final IHCNode propertyName) { return css ().arg (propertyName); }

@NonNull
default IMPLTYPE css(@NonNull final String propertyName) { return css ().arg (propertyName); }

@NonNull
default IMPLTYPE css(@NonNull final JSArray propertyNames) { return css ().arg (propertyNames); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull IJson value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull IJson value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull IJson value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull IJson value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull IHCNode value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull IHCNode value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull IHCNode value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull IHCNode value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull String value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull String value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull String value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull String value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, int value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, int value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, int value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, int value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, long value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, long value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, long value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, long value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull BigInteger value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull BigInteger value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull BigInteger value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull BigInteger value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, double value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, double value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, double value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, double value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE css(@NonNull IJSExpression propertyName, @NonNull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE css(@NonNull IJson propertyName, @NonNull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE css(@NonNull IHCNode propertyName, @NonNull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE css(@NonNull String propertyName, @NonNull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE data(@NonNull IJSExpression key, @NonNull IJSExpression value) { return data ().arg (key).arg (value); }

@NonNull
default IMPLTYPE data(@NonNull IJson key, @NonNull IJSExpression value) { return data ().arg (key).arg (value); }

@NonNull
default IMPLTYPE data(@NonNull IHCNode key, @NonNull IJSExpression value) { return data ().arg (key).arg (value); }

@NonNull
default IMPLTYPE data(@NonNull String key, @NonNull IJSExpression value) { return data ().arg (key).arg (value); }

@NonNull
default IMPLTYPE data(@NonNull final IJSExpression obj) { return data ().arg (obj); }

@NonNull
default IMPLTYPE data(@NonNull final IJson key) { return data ().arg (key); }

@NonNull
default IMPLTYPE data(@NonNull final IHCNode key) { return data ().arg (key); }

@NonNull
default IMPLTYPE data(@NonNull final String key) { return data ().arg (key); }

@NonNull
default IMPLTYPE dblclick(@NonNull final IJSExpression handler) { return dblclick ().arg (handler); }

@NonNull
default IMPLTYPE dblclick(@NonNull final JSAnonymousFunction handler) { return dblclick ().arg (handler); }

@NonNull
default IMPLTYPE dblclick(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return dblclick ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE dblclick(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return dblclick ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE deferred_always(@NonNull final IJSExpression alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@NonNull
default IMPLTYPE deferred_always(@NonNull final JSAnonymousFunction alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@NonNull
default IMPLTYPE deferred_always(@NonNull IJSExpression alwaysCallbacks, @NonNull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@NonNull
default IMPLTYPE deferred_always(@NonNull JSAnonymousFunction alwaysCallbacks, @NonNull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@NonNull
default IMPLTYPE deferred_always(@NonNull IJSExpression alwaysCallbacks, @NonNull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@NonNull
default IMPLTYPE deferred_always(@NonNull JSAnonymousFunction alwaysCallbacks, @NonNull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@NonNull
default IMPLTYPE deferred_done(@NonNull final IJSExpression doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@NonNull
default IMPLTYPE deferred_done(@NonNull final JSAnonymousFunction doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@NonNull
default IMPLTYPE deferred_done(@NonNull IJSExpression doneCallbacks, @NonNull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@NonNull
default IMPLTYPE deferred_done(@NonNull JSAnonymousFunction doneCallbacks, @NonNull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@NonNull
default IMPLTYPE deferred_done(@NonNull IJSExpression doneCallbacks, @NonNull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@NonNull
default IMPLTYPE deferred_done(@NonNull JSAnonymousFunction doneCallbacks, @NonNull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull final IJSExpression failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull final JSAnonymousFunction failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull IJSExpression failCallbacks, @NonNull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull JSAnonymousFunction failCallbacks, @NonNull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull IJSExpression failCallbacks, @NonNull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@NonNull
default IMPLTYPE deferred_fail(@NonNull JSAnonymousFunction failCallbacks, @NonNull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@NonNull
default IMPLTYPE deferred_notify(@NonNull final IJSExpression args) { return deferred_notify ().arg (args); }

@NonNull
default IMPLTYPE deferred_notifyWith(@NonNull final IJSExpression context) { return deferred_notifyWith ().arg (context); }

@NonNull
default IMPLTYPE deferred_notifyWith(@NonNull IJSExpression context, @NonNull IJSExpression args) { return deferred_notifyWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_notifyWith(@NonNull IJSExpression context, @NonNull JSArray args) { return deferred_notifyWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull final IJSExpression progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull final JSAnonymousFunction progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull final JSArray progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull IJSExpression progressCallbacks, @NonNull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSAnonymousFunction progressCallbacks, @NonNull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSArray progressCallbacks, @NonNull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull IJSExpression progressCallbacks, @NonNull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSAnonymousFunction progressCallbacks, @NonNull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSArray progressCallbacks, @NonNull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull IJSExpression progressCallbacks, @NonNull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSAnonymousFunction progressCallbacks, @NonNull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_progress(@NonNull JSArray progressCallbacks, @NonNull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@NonNull
default IMPLTYPE deferred_promise(@NonNull final IJSExpression target) { return deferred_promise ().arg (target); }

@NonNull
default IMPLTYPE deferred_reject(@NonNull final IJSExpression args) { return deferred_reject ().arg (args); }

@NonNull
default IMPLTYPE deferred_rejectWith(@NonNull final IJSExpression context) { return deferred_rejectWith ().arg (context); }

@NonNull
default IMPLTYPE deferred_rejectWith(@NonNull IJSExpression context, @NonNull IJSExpression args) { return deferred_rejectWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_rejectWith(@NonNull IJSExpression context, @NonNull JSArray args) { return deferred_rejectWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_resolve(@NonNull final IJSExpression args) { return deferred_resolve ().arg (args); }

@NonNull
default IMPLTYPE deferred_resolveWith(@NonNull final IJSExpression context) { return deferred_resolveWith ().arg (context); }

@NonNull
default IMPLTYPE deferred_resolveWith(@NonNull IJSExpression context, @NonNull IJSExpression args) { return deferred_resolveWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_resolveWith(@NonNull IJSExpression context, @NonNull JSArray args) { return deferred_resolveWith ().arg (context).arg (args); }

@NonNull
default IMPLTYPE deferred_then(@NonNull final IJSExpression doneFilter) { return deferred_then ().arg (doneFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull final JSAnonymousFunction doneFilter) { return deferred_then ().arg (doneFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneFilter, @NonNull IJSExpression failFilter, @NonNull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneFilter, @NonNull IJSExpression failFilter, @NonNull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneFilter, @NonNull JSAnonymousFunction failFilter, @NonNull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneFilter, @NonNull JSAnonymousFunction failFilter, @NonNull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneFilter, @NonNull IJSExpression failFilter, @NonNull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneFilter, @NonNull IJSExpression failFilter, @NonNull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneFilter, @NonNull JSAnonymousFunction failFilter, @NonNull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneFilter, @NonNull JSAnonymousFunction failFilter, @NonNull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneCallbacks, @NonNull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneCallbacks, @NonNull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@NonNull
default IMPLTYPE deferred_then(@NonNull IJSExpression doneCallbacks, @NonNull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@NonNull
default IMPLTYPE deferred_then(@NonNull JSAnonymousFunction doneCallbacks, @NonNull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@NonNull
default IMPLTYPE delay(@NonNull final IJSExpression duration) { return delay ().arg (duration); }

@NonNull
default IMPLTYPE delay(final int duration) { return delay ().arg (duration); }

@NonNull
default IMPLTYPE delay(final long duration) { return delay ().arg (duration); }

@NonNull
default IMPLTYPE delay(@NonNull final BigInteger duration) { return delay ().arg (duration); }

@NonNull
default IMPLTYPE delay(@NonNull IJSExpression duration, @NonNull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(int duration, @NonNull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(long duration, @NonNull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull BigInteger duration, @NonNull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull IJSExpression duration, @NonNull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(int duration, @NonNull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(long duration, @NonNull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull BigInteger duration, @NonNull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull IJSExpression duration, @NonNull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(int duration, @NonNull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(long duration, @NonNull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull BigInteger duration, @NonNull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull IJSExpression duration, @NonNull String queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(int duration, @NonNull String queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(long duration, @NonNull String queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE delay(@NonNull BigInteger duration, @NonNull String queueName) { return delay ().arg (duration).arg (queueName); }

@NonNull
default IMPLTYPE dequeue(@NonNull final IJSExpression queueName) { return dequeue ().arg (queueName); }

@NonNull
default IMPLTYPE dequeue(@NonNull final IJson queueName) { return dequeue ().arg (queueName); }

@NonNull
default IMPLTYPE dequeue(@NonNull final IHCNode queueName) { return dequeue ().arg (queueName); }

@NonNull
default IMPLTYPE dequeue(@NonNull final String queueName) { return dequeue ().arg (queueName); }

@NonNull
default IMPLTYPE detach(@NonNull final IJSExpression selector) { return detach ().arg (selector); }

@NonNull
default IMPLTYPE detach(@NonNull final IJQuerySelector selector) { return detach ().arg (selector); }

@NonNull
default IMPLTYPE detach(@NonNull final JQuerySelectorList selector) { return detach ().arg (selector); }

@NonNull
default IMPLTYPE detach(@NonNull final EHTMLElement selector) { return detach ().arg (selector); }

@NonNull
default IMPLTYPE detach(@NonNull final ICSSClassProvider selector) { return detach ().arg (selector); }

@NonNull
default IMPLTYPE each(@NonNull final IJSExpression function) { return each ().arg (function); }

@NonNull
default IMPLTYPE each(@NonNull final JSAnonymousFunction function) { return each ().arg (function); }

@NonNull
default IMPLTYPE _eq(@NonNull final IJSExpression index) { return _eq ().arg (index); }

@NonNull
default IMPLTYPE _eq(final int index) { return _eq ().arg (index); }

@NonNull
default IMPLTYPE _eq(final long index) { return _eq ().arg (index); }

@NonNull
default IMPLTYPE _eq(@NonNull final BigInteger index) { return _eq ().arg (index); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJSExpression easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IJson easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull IHCNode easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull IJSExpression opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, int opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, long opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigInteger opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, double opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJSExpression duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IJson duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull IHCNode duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull String duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(int duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(long duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigInteger duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(double duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE fadeTo(@NonNull BigDecimal duration, @NonNull BigDecimal opacity, @NonNull String easing, @NonNull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@NonNull
default IMPLTYPE filter(@NonNull final IJSExpression selector) { return filter ().arg (selector); }

@NonNull
default IMPLTYPE filter(@NonNull final IJQuerySelector selector) { return filter ().arg (selector); }

@NonNull
default IMPLTYPE filter(@NonNull final JQuerySelectorList selector) { return filter ().arg (selector); }

@NonNull
default IMPLTYPE filter(@NonNull final EHTMLElement selector) { return filter ().arg (selector); }

@NonNull
default IMPLTYPE filter(@NonNull final ICSSClassProvider selector) { return filter ().arg (selector); }

@NonNull
default IMPLTYPE filter(@NonNull final JSAnonymousFunction function) { return filter ().arg (function); }

@NonNull
default IMPLTYPE filter(@NonNull final String elements) { return filter ().arg (elements); }

@NonNull
default IMPLTYPE filter(@NonNull final JQueryInvocation selection) { return filter ().arg (selection); }

@NonNull
default IMPLTYPE find(@NonNull final IJSExpression selector) { return find ().arg (selector); }

@NonNull
default IMPLTYPE find(@NonNull final IJQuerySelector selector) { return find ().arg (selector); }

@NonNull
default IMPLTYPE find(@NonNull final JQuerySelectorList selector) { return find ().arg (selector); }

@NonNull
default IMPLTYPE find(@NonNull final EHTMLElement selector) { return find ().arg (selector); }

@NonNull
default IMPLTYPE find(@NonNull final ICSSClassProvider selector) { return find ().arg (selector); }

@NonNull
default IMPLTYPE find(@NonNull final String element) { return find ().arg (element); }

@NonNull
default IMPLTYPE find(@NonNull final JQueryInvocation element) { return find ().arg (element); }

@NonNull
default IMPLTYPE finish(@NonNull final IJSExpression queue) { return finish ().arg (queue); }

@NonNull
default IMPLTYPE finish(@NonNull final IJson queue) { return finish ().arg (queue); }

@NonNull
default IMPLTYPE finish(@NonNull final IHCNode queue) { return finish ().arg (queue); }

@NonNull
default IMPLTYPE finish(@NonNull final String queue) { return finish ().arg (queue); }

@NonNull
default IMPLTYPE focus(@NonNull final IJSExpression handler) { return focus ().arg (handler); }

@NonNull
default IMPLTYPE focus(@NonNull final JSAnonymousFunction handler) { return focus ().arg (handler); }

@NonNull
default IMPLTYPE focus(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return focus ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE focus(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return focus ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE focusin(@NonNull final IJSExpression handler) { return focusin ().arg (handler); }

@NonNull
default IMPLTYPE focusin(@NonNull final JSAnonymousFunction handler) { return focusin ().arg (handler); }

@NonNull
default IMPLTYPE focusin(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return focusin ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE focusin(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return focusin ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE focusout(@NonNull final IJSExpression handler) { return focusout ().arg (handler); }

@NonNull
default IMPLTYPE focusout(@NonNull final JSAnonymousFunction handler) { return focusout ().arg (handler); }

@NonNull
default IMPLTYPE focusout(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return focusout ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE focusout(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return focusout ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE get(@NonNull final IJSExpression index) { return get ().arg (index); }

@NonNull
default IMPLTYPE get(final int index) { return get ().arg (index); }

@NonNull
default IMPLTYPE get(final long index) { return get ().arg (index); }

@NonNull
default IMPLTYPE get(@NonNull final BigInteger index) { return get ().arg (index); }

@NonNull
default IMPLTYPE has(@NonNull final IJSExpression selector) { return has ().arg (selector); }

@NonNull
default IMPLTYPE has(@NonNull final IJson selector) { return has ().arg (selector); }

@NonNull
default IMPLTYPE has(@NonNull final IHCNode selector) { return has ().arg (selector); }

@NonNull
default IMPLTYPE has(@NonNull final String selector) { return has ().arg (selector); }

@NonNull
default IMPLTYPE has(@NonNull final EHTMLElement contained) { return has ().arg (contained); }

@NonNull
default IMPLTYPE hasClass(@NonNull final IJSExpression className) { return hasClass ().arg (className); }

@NonNull
default IMPLTYPE hasClass(@NonNull final IJson className) { return hasClass ().arg (className); }

@NonNull
default IMPLTYPE hasClass(@NonNull final IHCNode className) { return hasClass ().arg (className); }

@NonNull
default IMPLTYPE hasClass(@NonNull final String className) { return hasClass ().arg (className); }

@NonNull
default IMPLTYPE height(@NonNull final IJSExpression value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final IJson value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final IHCNode value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final String value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(final int value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(final long value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final BigInteger value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(final double value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final BigDecimal value) { return height ().arg (value); }

@NonNull
default IMPLTYPE height(@NonNull final JSAnonymousFunction function) { return height ().arg (function); }

@NonNull
default IMPLTYPE hide(@NonNull final IJSExpression duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(final int duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(final long duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(@NonNull final BigInteger duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(final double duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(@NonNull final BigDecimal duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(@NonNull final IJson duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(@NonNull final IHCNode duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hide(@NonNull final String duration) { return hide ().arg (duration); }

@NonNull
default IMPLTYPE hover(@NonNull IJSExpression handlerIn, @NonNull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@NonNull
default IMPLTYPE hover(@NonNull JSAnonymousFunction handlerIn, @NonNull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@NonNull
default IMPLTYPE hover(@NonNull IJSExpression handlerIn, @NonNull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@NonNull
default IMPLTYPE hover(@NonNull JSAnonymousFunction handlerIn, @NonNull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@NonNull
default IMPLTYPE hover(@NonNull final IJSExpression handlerInOut) { return hover ().arg (handlerInOut); }

@NonNull
default IMPLTYPE hover(@NonNull final JSAnonymousFunction handlerInOut) { return hover ().arg (handlerInOut); }

@NonNull
default IMPLTYPE html(@NonNull final IJSExpression htmlString) { return html ().arg (htmlString); }

@NonNull
default IMPLTYPE html(@NonNull final IHCNode htmlString) { return html ().arg (htmlString); }

@NonNull
default IMPLTYPE html(@NonNull final String htmlString) { return html ().arg (htmlString); }

@NonNull
default IMPLTYPE html(@NonNull final JSAnonymousFunction function) { return html ().arg (function); }

@NonNull
default IMPLTYPE index(@NonNull final IJSExpression selector) { return index ().arg (selector); }

@NonNull
default IMPLTYPE index(@NonNull final IJQuerySelector selector) { return index ().arg (selector); }

@NonNull
default IMPLTYPE index(@NonNull final JQuerySelectorList selector) { return index ().arg (selector); }

@NonNull
default IMPLTYPE index(@NonNull final EHTMLElement selector) { return index ().arg (selector); }

@NonNull
default IMPLTYPE index(@NonNull final ICSSClassProvider selector) { return index ().arg (selector); }

@NonNull
default IMPLTYPE index(@NonNull final String element) { return index ().arg (element); }

@NonNull
default IMPLTYPE index(@NonNull final JQueryInvocation element) { return index ().arg (element); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final IJSExpression value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final IJson value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final IHCNode value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final String value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(final int value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(final long value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final BigInteger value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(final double value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final BigDecimal value) { return innerHeight ().arg (value); }

@NonNull
default IMPLTYPE innerHeight(@NonNull final JSAnonymousFunction function) { return innerHeight ().arg (function); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final IJSExpression value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final IJson value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final IHCNode value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final String value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(final int value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(final long value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final BigInteger value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(final double value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final BigDecimal value) { return innerWidth ().arg (value); }

@NonNull
default IMPLTYPE innerWidth(@NonNull final JSAnonymousFunction function) { return innerWidth ().arg (function); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final IJSExpression target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final IJQuerySelector target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final JQuerySelectorList target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final EHTMLElement target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final ICSSClassProvider target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final IHCNode target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final String target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final JSArray target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertAfter(@NonNull final JQueryInvocation target) { return insertAfter ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final IJSExpression target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final IJQuerySelector target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final JQuerySelectorList target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final EHTMLElement target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final ICSSClassProvider target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final IHCNode target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final String target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final JSArray target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE insertBefore(@NonNull final JQueryInvocation target) { return insertBefore ().arg (target); }

@NonNull
default IMPLTYPE is(@NonNull final IJSExpression selector) { return is ().arg (selector); }

@NonNull
default IMPLTYPE is(@NonNull final IJQuerySelector selector) { return is ().arg (selector); }

@NonNull
default IMPLTYPE is(@NonNull final JQuerySelectorList selector) { return is ().arg (selector); }

@NonNull
default IMPLTYPE is(@NonNull final EHTMLElement selector) { return is ().arg (selector); }

@NonNull
default IMPLTYPE is(@NonNull final ICSSClassProvider selector) { return is ().arg (selector); }

@NonNull
default IMPLTYPE is(@NonNull final JSAnonymousFunction function) { return is ().arg (function); }

@NonNull
default IMPLTYPE is(@NonNull final JQueryInvocation selection) { return is ().arg (selection); }

@NonNull
default IMPLTYPE is(@NonNull final String elements) { return is ().arg (elements); }

@NonNull
default IMPLTYPE keydown(@NonNull final IJSExpression handler) { return keydown ().arg (handler); }

@NonNull
default IMPLTYPE keydown(@NonNull final JSAnonymousFunction handler) { return keydown ().arg (handler); }

@NonNull
default IMPLTYPE keydown(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return keydown ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE keydown(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return keydown ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE keypress(@NonNull final IJSExpression handler) { return keypress ().arg (handler); }

@NonNull
default IMPLTYPE keypress(@NonNull final JSAnonymousFunction handler) { return keypress ().arg (handler); }

@NonNull
default IMPLTYPE keypress(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return keypress ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE keypress(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return keypress ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE keyup(@NonNull final IJSExpression handler) { return keyup ().arg (handler); }

@NonNull
default IMPLTYPE keyup(@NonNull final JSAnonymousFunction handler) { return keyup ().arg (handler); }

@NonNull
default IMPLTYPE keyup(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return keyup ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE keyup(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return keyup ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE load(@NonNull final IJSExpression url) { return load ().arg (url); }

@NonNull
default IMPLTYPE load(@NonNull final IJson url) { return load ().arg (url); }

@NonNull
default IMPLTYPE load(@NonNull final IHCNode url) { return load ().arg (url); }

@NonNull
default IMPLTYPE load(@NonNull final String url) { return load ().arg (url); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJSExpression data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJSExpression data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJSExpression data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJSExpression data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJson data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJson data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJson data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJson data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IHCNode data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IHCNode data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IHCNode data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IHCNode data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull String data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull String data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull String data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull String data) { return load ().arg (url).arg (data); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJSExpression data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJSExpression data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJSExpression data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJSExpression data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJson data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJson data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJson data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJson data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IHCNode data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IHCNode data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IHCNode data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IHCNode data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull String data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull String data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull String data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull String data, @NonNull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJSExpression data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJSExpression data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJSExpression data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJSExpression data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IJson data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IJson data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IJson data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IJson data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull IHCNode data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull IHCNode data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull IHCNode data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull IHCNode data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJSExpression url, @NonNull String data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IJson url, @NonNull String data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull IHCNode url, @NonNull String data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE load(@NonNull String url, @NonNull String data, @NonNull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@NonNull
default IMPLTYPE map(@NonNull final IJSExpression callback) { return map ().arg (callback); }

@NonNull
default IMPLTYPE map(@NonNull final JSAnonymousFunction callback) { return map ().arg (callback); }

@NonNull
default IMPLTYPE mousedown(@NonNull final IJSExpression handler) { return mousedown ().arg (handler); }

@NonNull
default IMPLTYPE mousedown(@NonNull final JSAnonymousFunction handler) { return mousedown ().arg (handler); }

@NonNull
default IMPLTYPE mousedown(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mousedown ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mousedown(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mousedown ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseenter(@NonNull final IJSExpression handler) { return mouseenter ().arg (handler); }

@NonNull
default IMPLTYPE mouseenter(@NonNull final JSAnonymousFunction handler) { return mouseenter ().arg (handler); }

@NonNull
default IMPLTYPE mouseenter(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mouseenter ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseenter(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mouseenter ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseleave(@NonNull final IJSExpression handler) { return mouseleave ().arg (handler); }

@NonNull
default IMPLTYPE mouseleave(@NonNull final JSAnonymousFunction handler) { return mouseleave ().arg (handler); }

@NonNull
default IMPLTYPE mouseleave(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mouseleave ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseleave(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mouseleave ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mousemove(@NonNull final IJSExpression handler) { return mousemove ().arg (handler); }

@NonNull
default IMPLTYPE mousemove(@NonNull final JSAnonymousFunction handler) { return mousemove ().arg (handler); }

@NonNull
default IMPLTYPE mousemove(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mousemove ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mousemove(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mousemove ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseout(@NonNull final IJSExpression handler) { return mouseout ().arg (handler); }

@NonNull
default IMPLTYPE mouseout(@NonNull final JSAnonymousFunction handler) { return mouseout ().arg (handler); }

@NonNull
default IMPLTYPE mouseout(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mouseout ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseout(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mouseout ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseover(@NonNull final IJSExpression handler) { return mouseover ().arg (handler); }

@NonNull
default IMPLTYPE mouseover(@NonNull final JSAnonymousFunction handler) { return mouseover ().arg (handler); }

@NonNull
default IMPLTYPE mouseover(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mouseover ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseover(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mouseover ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseup(@NonNull final IJSExpression handler) { return mouseup ().arg (handler); }

@NonNull
default IMPLTYPE mouseup(@NonNull final JSAnonymousFunction handler) { return mouseup ().arg (handler); }

@NonNull
default IMPLTYPE mouseup(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return mouseup ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE mouseup(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return mouseup ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE next(@NonNull final IJSExpression selector) { return next ().arg (selector); }

@NonNull
default IMPLTYPE next(@NonNull final IJQuerySelector selector) { return next ().arg (selector); }

@NonNull
default IMPLTYPE next(@NonNull final JQuerySelectorList selector) { return next ().arg (selector); }

@NonNull
default IMPLTYPE next(@NonNull final EHTMLElement selector) { return next ().arg (selector); }

@NonNull
default IMPLTYPE next(@NonNull final ICSSClassProvider selector) { return next ().arg (selector); }

@NonNull
default IMPLTYPE nextAll(@NonNull final IJSExpression selector) { return nextAll ().arg (selector); }

@NonNull
default IMPLTYPE nextAll(@NonNull final IJson selector) { return nextAll ().arg (selector); }

@NonNull
default IMPLTYPE nextAll(@NonNull final IHCNode selector) { return nextAll ().arg (selector); }

@NonNull
default IMPLTYPE nextAll(@NonNull final String selector) { return nextAll ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final IJSExpression selector) { return nextUntil ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final IJQuerySelector selector) { return nextUntil ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final JQuerySelectorList selector) { return nextUntil ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final EHTMLElement selector) { return nextUntil ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final ICSSClassProvider selector) { return nextUntil ().arg (selector); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJSExpression selector, @NonNull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJQuerySelector selector, @NonNull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQuerySelectorList selector, @NonNull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull EHTMLElement selector, @NonNull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull ICSSClassProvider selector, @NonNull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJSExpression selector, @NonNull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJQuerySelector selector, @NonNull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQuerySelectorList selector, @NonNull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull EHTMLElement selector, @NonNull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull ICSSClassProvider selector, @NonNull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJSExpression selector, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJQuerySelector selector, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQuerySelectorList selector, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull EHTMLElement selector, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull ICSSClassProvider selector, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJSExpression selector, @NonNull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJQuerySelector selector, @NonNull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQuerySelectorList selector, @NonNull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull EHTMLElement selector, @NonNull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull ICSSClassProvider selector, @NonNull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJSExpression selector, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull IJQuerySelector selector, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQuerySelectorList selector, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull EHTMLElement selector, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull ICSSClassProvider selector, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final String element) { return nextUntil ().arg (element); }

@NonNull
default IMPLTYPE nextUntil(@NonNull final JQueryInvocation element) { return nextUntil ().arg (element); }

@NonNull
default IMPLTYPE nextUntil(@NonNull String element, @NonNull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQueryInvocation element, @NonNull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull String element, @NonNull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQueryInvocation element, @NonNull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull String element, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQueryInvocation element, @NonNull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull String element, @NonNull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQueryInvocation element, @NonNull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull String element, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE nextUntil(@NonNull JQueryInvocation element, @NonNull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE _not(@NonNull final IJSExpression selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final IJQuerySelector selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final JQuerySelectorList selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final EHTMLElement selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final ICSSClassProvider selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final String selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final JSArray selector) { return _not ().arg (selector); }

@NonNull
default IMPLTYPE _not(@NonNull final JSAnonymousFunction function) { return _not ().arg (function); }

@NonNull
default IMPLTYPE _not(@NonNull final JQueryInvocation selection) { return _not ().arg (selection); }

@NonNull
default IMPLTYPE off(@NonNull final IJSExpression events) { return off ().arg (events); }

@NonNull
default IMPLTYPE off(@NonNull final IJson events) { return off ().arg (events); }

@NonNull
default IMPLTYPE off(@NonNull final IHCNode events) { return off ().arg (events); }

@NonNull
default IMPLTYPE off(@NonNull final String events) { return off ().arg (events); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJSExpression selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJSExpression selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJSExpression selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJSExpression selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJson selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJson selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJson selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJson selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IHCNode selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IHCNode selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IHCNode selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IHCNode selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull String selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull String selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull String selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull String selector) { return off ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull String selector, @NonNull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJSExpression selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IJson selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IJson selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IJson selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull IHCNode selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull IHCNode selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJSExpression events, @NonNull String selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IJson events, @NonNull String selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull IHCNode events, @NonNull String selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE off(@NonNull String events, @NonNull String selector, @NonNull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@NonNull
default IMPLTYPE offset(@NonNull final IJSExpression coordinates) { return offset ().arg (coordinates); }

@NonNull
default IMPLTYPE offset(@NonNull final JSAnonymousFunction function) { return offset ().arg (function); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJSExpression selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJSExpression selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJSExpression selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJSExpression selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJson selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJson selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJson selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJson selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IHCNode selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IHCNode selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IHCNode selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IHCNode selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull String selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull String selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull String selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull String selector) { return on ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE on(@NonNull final IJSExpression events) { return on ().arg (events); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJSExpression data) { return one ().arg (events).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJSExpression data) { return one ().arg (events).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJSExpression data) { return one ().arg (events).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJSExpression data) { return one ().arg (events).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJson selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJson selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJson selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJson selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IHCNode selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IHCNode selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IHCNode selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IHCNode selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull String selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull String selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull String selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull String selector) { return one ().arg (events).arg (selector); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data, @NonNull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJSExpression selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IJson selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull IHCNode selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJSExpression events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IJson events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull IHCNode events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull String events, @NonNull String selector, @NonNull IJSExpression data, @NonNull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@NonNull
default IMPLTYPE one(@NonNull final IJSExpression events) { return one ().arg (events); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final IJSExpression includeMargin) { return outerHeight ().arg (includeMargin); }

@NonNull
default IMPLTYPE outerHeight(final boolean includeMargin) { return outerHeight ().arg (includeMargin); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final IJson value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final IHCNode value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final String value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(final int value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(final long value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final BigInteger value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(final double value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final BigDecimal value) { return outerHeight ().arg (value); }

@NonNull
default IMPLTYPE outerHeight(@NonNull final JSAnonymousFunction function) { return outerHeight ().arg (function); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final IJSExpression includeMargin) { return outerWidth ().arg (includeMargin); }

@NonNull
default IMPLTYPE outerWidth(final boolean includeMargin) { return outerWidth ().arg (includeMargin); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final IJson value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final IHCNode value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final String value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(final int value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(final long value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final BigInteger value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(final double value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final BigDecimal value) { return outerWidth ().arg (value); }

@NonNull
default IMPLTYPE outerWidth(@NonNull final JSAnonymousFunction function) { return outerWidth ().arg (function); }

@NonNull
default IMPLTYPE parent(@NonNull final IJSExpression selector) { return parent ().arg (selector); }

@NonNull
default IMPLTYPE parent(@NonNull final IJQuerySelector selector) { return parent ().arg (selector); }

@NonNull
default IMPLTYPE parent(@NonNull final JQuerySelectorList selector) { return parent ().arg (selector); }

@NonNull
default IMPLTYPE parent(@NonNull final EHTMLElement selector) { return parent ().arg (selector); }

@NonNull
default IMPLTYPE parent(@NonNull final ICSSClassProvider selector) { return parent ().arg (selector); }

@NonNull
default IMPLTYPE parents(@NonNull final IJSExpression selector) { return parents ().arg (selector); }

@NonNull
default IMPLTYPE parents(@NonNull final IJQuerySelector selector) { return parents ().arg (selector); }

@NonNull
default IMPLTYPE parents(@NonNull final JQuerySelectorList selector) { return parents ().arg (selector); }

@NonNull
default IMPLTYPE parents(@NonNull final EHTMLElement selector) { return parents ().arg (selector); }

@NonNull
default IMPLTYPE parents(@NonNull final ICSSClassProvider selector) { return parents ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final IJSExpression selector) { return parentsUntil ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final IJQuerySelector selector) { return parentsUntil ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final JQuerySelectorList selector) { return parentsUntil ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final EHTMLElement selector) { return parentsUntil ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final ICSSClassProvider selector) { return parentsUntil ().arg (selector); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJSExpression selector, @NonNull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJQuerySelector selector, @NonNull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQuerySelectorList selector, @NonNull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull EHTMLElement selector, @NonNull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull ICSSClassProvider selector, @NonNull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJSExpression selector, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJQuerySelector selector, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQuerySelectorList selector, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull EHTMLElement selector, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull ICSSClassProvider selector, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJSExpression selector, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJQuerySelector selector, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQuerySelectorList selector, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull EHTMLElement selector, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull ICSSClassProvider selector, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJSExpression selector, @NonNull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJQuerySelector selector, @NonNull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQuerySelectorList selector, @NonNull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull EHTMLElement selector, @NonNull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull ICSSClassProvider selector, @NonNull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJSExpression selector, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull IJQuerySelector selector, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQuerySelectorList selector, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull EHTMLElement selector, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull ICSSClassProvider selector, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final String element) { return parentsUntil ().arg (element); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull final JQueryInvocation element) { return parentsUntil ().arg (element); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull String element, @NonNull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQueryInvocation element, @NonNull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull String element, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQueryInvocation element, @NonNull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull String element, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQueryInvocation element, @NonNull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull String element, @NonNull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQueryInvocation element, @NonNull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull String element, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE parentsUntil(@NonNull JQueryInvocation element, @NonNull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prepend(@NonNull final IJSExpression content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final IHCNode content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final String content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final EHTMLElement content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final IJson content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final JSArray content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull final JQueryInvocation content) { return prepend ().arg (content); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull String content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull IJson content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull JSArray content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJSExpression content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IHCNode content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull String content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull EHTMLElement content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull IJson content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JSArray content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull JQueryInvocation content, @NonNull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@NonNull
default IMPLTYPE prepend(@NonNull final JSAnonymousFunction function) { return prepend ().arg (function); }

@NonNull
default IMPLTYPE prependTo(@NonNull final IJSExpression target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final IJQuerySelector target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final JQuerySelectorList target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final EHTMLElement target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final ICSSClassProvider target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final IHCNode target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final String target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final JSArray target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prependTo(@NonNull final JQueryInvocation target) { return prependTo ().arg (target); }

@NonNull
default IMPLTYPE prev(@NonNull final IJSExpression selector) { return prev ().arg (selector); }

@NonNull
default IMPLTYPE prev(@NonNull final IJQuerySelector selector) { return prev ().arg (selector); }

@NonNull
default IMPLTYPE prev(@NonNull final JQuerySelectorList selector) { return prev ().arg (selector); }

@NonNull
default IMPLTYPE prev(@NonNull final EHTMLElement selector) { return prev ().arg (selector); }

@NonNull
default IMPLTYPE prev(@NonNull final ICSSClassProvider selector) { return prev ().arg (selector); }

@NonNull
default IMPLTYPE prevAll(@NonNull final IJSExpression selector) { return prevAll ().arg (selector); }

@NonNull
default IMPLTYPE prevAll(@NonNull final IJQuerySelector selector) { return prevAll ().arg (selector); }

@NonNull
default IMPLTYPE prevAll(@NonNull final JQuerySelectorList selector) { return prevAll ().arg (selector); }

@NonNull
default IMPLTYPE prevAll(@NonNull final EHTMLElement selector) { return prevAll ().arg (selector); }

@NonNull
default IMPLTYPE prevAll(@NonNull final ICSSClassProvider selector) { return prevAll ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final IJSExpression selector) { return prevUntil ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final IJQuerySelector selector) { return prevUntil ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final JQuerySelectorList selector) { return prevUntil ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final EHTMLElement selector) { return prevUntil ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final ICSSClassProvider selector) { return prevUntil ().arg (selector); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJSExpression selector, @NonNull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJQuerySelector selector, @NonNull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQuerySelectorList selector, @NonNull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull EHTMLElement selector, @NonNull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull ICSSClassProvider selector, @NonNull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJSExpression selector, @NonNull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJQuerySelector selector, @NonNull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQuerySelectorList selector, @NonNull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull EHTMLElement selector, @NonNull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull ICSSClassProvider selector, @NonNull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJSExpression selector, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJQuerySelector selector, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQuerySelectorList selector, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull EHTMLElement selector, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull ICSSClassProvider selector, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJSExpression selector, @NonNull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJQuerySelector selector, @NonNull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQuerySelectorList selector, @NonNull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull EHTMLElement selector, @NonNull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull ICSSClassProvider selector, @NonNull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJSExpression selector, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull IJQuerySelector selector, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQuerySelectorList selector, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull EHTMLElement selector, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull ICSSClassProvider selector, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final String element) { return prevUntil ().arg (element); }

@NonNull
default IMPLTYPE prevUntil(@NonNull final JQueryInvocation element) { return prevUntil ().arg (element); }

@NonNull
default IMPLTYPE prevUntil(@NonNull String element, @NonNull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQueryInvocation element, @NonNull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull String element, @NonNull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQueryInvocation element, @NonNull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull String element, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQueryInvocation element, @NonNull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull String element, @NonNull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQueryInvocation element, @NonNull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull String element, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE prevUntil(@NonNull JQueryInvocation element, @NonNull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@NonNull
default IMPLTYPE promise(@NonNull final IJSExpression type) { return promise ().arg (type); }

@NonNull
default IMPLTYPE promise(@NonNull final IJson type) { return promise ().arg (type); }

@NonNull
default IMPLTYPE promise(@NonNull final IHCNode type) { return promise ().arg (type); }

@NonNull
default IMPLTYPE promise(@NonNull final String type) { return promise ().arg (type); }

@NonNull
default IMPLTYPE promise(@NonNull IJSExpression type, @NonNull IJSExpression target) { return promise ().arg (type).arg (target); }

@NonNull
default IMPLTYPE promise(@NonNull IJson type, @NonNull IJSExpression target) { return promise ().arg (type).arg (target); }

@NonNull
default IMPLTYPE promise(@NonNull IHCNode type, @NonNull IJSExpression target) { return promise ().arg (type).arg (target); }

@NonNull
default IMPLTYPE promise(@NonNull String type, @NonNull IJSExpression target) { return promise ().arg (type).arg (target); }

@NonNull
default IMPLTYPE prop(@NonNull final IJSExpression propertyName) { return prop ().arg (propertyName); }

@NonNull
default IMPLTYPE prop(@NonNull final IJson propertyName) { return prop ().arg (propertyName); }

@NonNull
default IMPLTYPE prop(@NonNull final IHCNode propertyName) { return prop ().arg (propertyName); }

@NonNull
default IMPLTYPE prop(@NonNull final String propertyName) { return prop ().arg (propertyName); }

@NonNull
default IMPLTYPE prop(@NonNull IJSExpression propertyName, @NonNull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE prop(@NonNull IJson propertyName, @NonNull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE prop(@NonNull IHCNode propertyName, @NonNull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE prop(@NonNull String propertyName, @NonNull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@NonNull
default IMPLTYPE prop(@NonNull IJSExpression propertyName, @NonNull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE prop(@NonNull IJson propertyName, @NonNull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE prop(@NonNull IHCNode propertyName, @NonNull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE prop(@NonNull String propertyName, @NonNull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@NonNull
default IMPLTYPE pushStack(@NonNull final IJSExpression elements) { return pushStack ().arg (elements); }

@NonNull
default IMPLTYPE pushStack(@NonNull final JSArray elements) { return pushStack ().arg (elements); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IJSExpression name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IJSExpression name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IJson name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IJson name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IHCNode name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IHCNode name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull String name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull String name, @NonNull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IJSExpression name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IJSExpression name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IJson name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IJson name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull IHCNode name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull IHCNode name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull IJSExpression elements, @NonNull String name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE pushStack(@NonNull JSArray elements, @NonNull String name, @NonNull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@NonNull
default IMPLTYPE queue(@NonNull final IJSExpression queueName) { return queue ().arg (queueName); }

@NonNull
default IMPLTYPE queue(@NonNull final IJson queueName) { return queue ().arg (queueName); }

@NonNull
default IMPLTYPE queue(@NonNull final IHCNode queueName) { return queue ().arg (queueName); }

@NonNull
default IMPLTYPE queue(@NonNull final String queueName) { return queue ().arg (queueName); }

@NonNull
default IMPLTYPE queue(@NonNull IJSExpression queueName, @NonNull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IJson queueName, @NonNull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IHCNode queueName, @NonNull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull String queueName, @NonNull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IJSExpression queueName, @NonNull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IJson queueName, @NonNull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IHCNode queueName, @NonNull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull String queueName, @NonNull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@NonNull
default IMPLTYPE queue(@NonNull IJSExpression queueName, @NonNull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@NonNull
default IMPLTYPE queue(@NonNull IJson queueName, @NonNull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@NonNull
default IMPLTYPE queue(@NonNull IHCNode queueName, @NonNull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@NonNull
default IMPLTYPE queue(@NonNull String queueName, @NonNull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@NonNull
default IMPLTYPE ready(@NonNull final IJSExpression handler) { return ready ().arg (handler); }

@NonNull
default IMPLTYPE ready(@NonNull final JSAnonymousFunction handler) { return ready ().arg (handler); }

@NonNull
default IMPLTYPE remove(@NonNull final IJSExpression selector) { return remove ().arg (selector); }

@NonNull
default IMPLTYPE remove(@NonNull final IJson selector) { return remove ().arg (selector); }

@NonNull
default IMPLTYPE remove(@NonNull final IHCNode selector) { return remove ().arg (selector); }

@NonNull
default IMPLTYPE remove(@NonNull final String selector) { return remove ().arg (selector); }

@NonNull
default IMPLTYPE removeAttr(@NonNull final IJSExpression attributeName) { return removeAttr ().arg (attributeName); }

@NonNull
default IMPLTYPE removeAttr(@NonNull final IJson attributeName) { return removeAttr ().arg (attributeName); }

@NonNull
default IMPLTYPE removeAttr(@NonNull final IHCNode attributeName) { return removeAttr ().arg (attributeName); }

@NonNull
default IMPLTYPE removeAttr(@NonNull final String attributeName) { return removeAttr ().arg (attributeName); }

@NonNull
default IMPLTYPE removeAttr(@NonNull final IMicroQName attributeName) { return removeAttr ().arg (attributeName); }

@NonNull
default IMPLTYPE removeClass(@NonNull final IJSExpression className) { return removeClass ().arg (className); }

@NonNull
default IMPLTYPE removeClass(@NonNull final IJson className) { return removeClass ().arg (className); }

@NonNull
default IMPLTYPE removeClass(@NonNull final IHCNode className) { return removeClass ().arg (className); }

@NonNull
default IMPLTYPE removeClass(@NonNull final String className) { return removeClass ().arg (className); }

@NonNull
default IMPLTYPE removeClass(@NonNull final JSAnonymousFunction function) { return removeClass ().arg (function); }

@NonNull
default IMPLTYPE removeData(@NonNull final IJSExpression name) { return removeData ().arg (name); }

@NonNull
default IMPLTYPE removeData(@NonNull final IJson name) { return removeData ().arg (name); }

@NonNull
default IMPLTYPE removeData(@NonNull final IHCNode name) { return removeData ().arg (name); }

@NonNull
default IMPLTYPE removeData(@NonNull final String name) { return removeData ().arg (name); }

@NonNull
default IMPLTYPE removeData(@NonNull final JSArray list) { return removeData ().arg (list); }

@NonNull
default IMPLTYPE removeProp(@NonNull final IJSExpression propertyName) { return removeProp ().arg (propertyName); }

@NonNull
default IMPLTYPE removeProp(@NonNull final IJson propertyName) { return removeProp ().arg (propertyName); }

@NonNull
default IMPLTYPE removeProp(@NonNull final IHCNode propertyName) { return removeProp ().arg (propertyName); }

@NonNull
default IMPLTYPE removeProp(@NonNull final String propertyName) { return removeProp ().arg (propertyName); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final IJSExpression target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final IJQuerySelector target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final JQuerySelectorList target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final EHTMLElement target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final ICSSClassProvider target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final JQueryInvocation target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final JSArray target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceAll(@NonNull final String target) { return replaceAll ().arg (target); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final IJSExpression newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final IHCNode newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final String newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final EHTMLElement newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final JSArray newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final JQueryInvocation newContent) { return replaceWith ().arg (newContent); }

@NonNull
default IMPLTYPE replaceWith(@NonNull final JSAnonymousFunction function) { return replaceWith ().arg (function); }

@NonNull
default IMPLTYPE resize(@NonNull final IJSExpression handler) { return resize ().arg (handler); }

@NonNull
default IMPLTYPE resize(@NonNull final JSAnonymousFunction handler) { return resize ().arg (handler); }

@NonNull
default IMPLTYPE resize(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return resize ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE resize(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return resize ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE scroll(@NonNull final IJSExpression handler) { return scroll ().arg (handler); }

@NonNull
default IMPLTYPE scroll(@NonNull final JSAnonymousFunction handler) { return scroll ().arg (handler); }

@NonNull
default IMPLTYPE scroll(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return scroll ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE scroll(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return scroll ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE scrollLeft(@NonNull final IJSExpression value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollLeft(final int value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollLeft(final long value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollLeft(@NonNull final BigInteger value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollLeft(final double value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollLeft(@NonNull final BigDecimal value) { return scrollLeft ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(@NonNull final IJSExpression value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(final int value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(final long value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(@NonNull final BigInteger value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(final double value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE scrollTop(@NonNull final BigDecimal value) { return scrollTop ().arg (value); }

@NonNull
default IMPLTYPE select(@NonNull final IJSExpression handler) { return select ().arg (handler); }

@NonNull
default IMPLTYPE select(@NonNull final JSAnonymousFunction handler) { return select ().arg (handler); }

@NonNull
default IMPLTYPE select(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return select ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE select(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return select ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE show(@NonNull final IJSExpression duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(final int duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(final long duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(@NonNull final BigInteger duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(final double duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(@NonNull final BigDecimal duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(@NonNull final IJson duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(@NonNull final IHCNode duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE show(@NonNull final String duration) { return show ().arg (duration); }

@NonNull
default IMPLTYPE siblings(@NonNull final IJSExpression selector) { return siblings ().arg (selector); }

@NonNull
default IMPLTYPE siblings(@NonNull final IJQuerySelector selector) { return siblings ().arg (selector); }

@NonNull
default IMPLTYPE siblings(@NonNull final JQuerySelectorList selector) { return siblings ().arg (selector); }

@NonNull
default IMPLTYPE siblings(@NonNull final EHTMLElement selector) { return siblings ().arg (selector); }

@NonNull
default IMPLTYPE siblings(@NonNull final ICSSClassProvider selector) { return siblings ().arg (selector); }

@NonNull
default IMPLTYPE slice(@NonNull final IJSExpression start) { return slice ().arg (start); }

@NonNull
default IMPLTYPE slice(final int start) { return slice ().arg (start); }

@NonNull
default IMPLTYPE slice(final long start) { return slice ().arg (start); }

@NonNull
default IMPLTYPE slice(@NonNull final BigInteger start) { return slice ().arg (start); }

@NonNull
default IMPLTYPE slice(@NonNull IJSExpression start, @NonNull IJSExpression end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(int start, @NonNull IJSExpression end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(long start, @NonNull IJSExpression end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull BigInteger start, @NonNull IJSExpression end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull IJSExpression start, int end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(int start, int end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(long start, int end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull BigInteger start, int end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull IJSExpression start, long end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(int start, long end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(long start, long end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull BigInteger start, long end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull IJSExpression start, @NonNull BigInteger end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(int start, @NonNull BigInteger end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(long start, @NonNull BigInteger end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE slice(@NonNull BigInteger start, @NonNull BigInteger end) { return slice ().arg (start).arg (end); }

@NonNull
default IMPLTYPE stop(@NonNull final IJSExpression clearQueue) { return stop ().arg (clearQueue); }

@NonNull
default IMPLTYPE stop(final boolean clearQueue) { return stop ().arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(boolean clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(boolean clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull final IJson queue) { return stop ().arg (queue); }

@NonNull
default IMPLTYPE stop(@NonNull final IHCNode queue) { return stop ().arg (queue); }

@NonNull
default IMPLTYPE stop(@NonNull final String queue) { return stop ().arg (queue); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, @NonNull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, @NonNull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, @NonNull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression queue, @NonNull IJSExpression clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, @NonNull IJSExpression clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, @NonNull IJSExpression clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, @NonNull IJSExpression clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression queue, boolean clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, boolean clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, boolean clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, boolean clearQueue, @NonNull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression queue, @NonNull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, @NonNull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, @NonNull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, @NonNull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJSExpression queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IJson queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull IHCNode queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE stop(@NonNull String queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@NonNull
default IMPLTYPE submit(@NonNull final IJSExpression handler) { return submit ().arg (handler); }

@NonNull
default IMPLTYPE submit(@NonNull final JSAnonymousFunction handler) { return submit ().arg (handler); }

@NonNull
default IMPLTYPE submit(@NonNull IJSExpression eventData, @NonNull IJSExpression handler) { return submit ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE submit(@NonNull IJSExpression eventData, @NonNull JSAnonymousFunction handler) { return submit ().arg (eventData).arg (handler); }

@NonNull
default IMPLTYPE text(@NonNull final IJSExpression text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final IJson text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final IHCNode text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final String text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(final int text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(final long text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final BigInteger text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(final double text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final BigDecimal text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(final boolean text) { return text ().arg (text); }

@NonNull
default IMPLTYPE text(@NonNull final JSAnonymousFunction function) { return text ().arg (function); }

@NonNull
default IMPLTYPE toggle(@NonNull final IJSExpression duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(final int duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(final long duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(@NonNull final BigInteger duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(final double duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(@NonNull final BigDecimal duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(@NonNull final IJson duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(@NonNull final IHCNode duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(@NonNull final String duration) { return toggle ().arg (duration); }

@NonNull
default IMPLTYPE toggle(final boolean display) { return toggle ().arg (display); }

@NonNull
default IMPLTYPE toggleClass(@NonNull final IJSExpression className) { return toggleClass ().arg (className); }

@NonNull
default IMPLTYPE toggleClass(@NonNull final IJson className) { return toggleClass ().arg (className); }

@NonNull
default IMPLTYPE toggleClass(@NonNull final IHCNode className) { return toggleClass ().arg (className); }

@NonNull
default IMPLTYPE toggleClass(@NonNull final String className) { return toggleClass ().arg (className); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IJSExpression className, @NonNull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IJson className, @NonNull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IHCNode className, @NonNull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull String className, @NonNull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IJSExpression className, boolean state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IJson className, boolean state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull IHCNode className, boolean state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull String className, boolean state) { return toggleClass ().arg (className).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull final JSAnonymousFunction function) { return toggleClass ().arg (function); }

@NonNull
default IMPLTYPE toggleClass(@NonNull JSAnonymousFunction function, @NonNull IJSExpression state) { return toggleClass ().arg (function).arg (state); }

@NonNull
default IMPLTYPE toggleClass(@NonNull JSAnonymousFunction function, boolean state) { return toggleClass ().arg (function).arg (state); }

@NonNull
default IMPLTYPE trigger(@NonNull final IJSExpression eventType) { return trigger ().arg (eventType); }

@NonNull
default IMPLTYPE trigger(@NonNull final IJson eventType) { return trigger ().arg (eventType); }

@NonNull
default IMPLTYPE trigger(@NonNull final IHCNode eventType) { return trigger ().arg (eventType); }

@NonNull
default IMPLTYPE trigger(@NonNull final String eventType) { return trigger ().arg (eventType); }

@NonNull
default IMPLTYPE trigger(@NonNull IJSExpression eventType, @NonNull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull IJson eventType, @NonNull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull IHCNode eventType, @NonNull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull String eventType, @NonNull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull IJSExpression eventType, @NonNull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull IJson eventType, @NonNull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull IHCNode eventType, @NonNull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE trigger(@NonNull String eventType, @NonNull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull final IJSExpression eventType) { return triggerHandler ().arg (eventType); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull final IJson eventType) { return triggerHandler ().arg (eventType); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull final IHCNode eventType) { return triggerHandler ().arg (eventType); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull final String eventType) { return triggerHandler ().arg (eventType); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IJSExpression eventType, @NonNull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IJson eventType, @NonNull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IHCNode eventType, @NonNull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull String eventType, @NonNull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IJSExpression eventType, @NonNull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IJson eventType, @NonNull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull IHCNode eventType, @NonNull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE triggerHandler(@NonNull String eventType, @NonNull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@NonNull
default IMPLTYPE unwrap(@NonNull final IJSExpression selector) { return unwrap ().arg (selector); }

@NonNull
default IMPLTYPE unwrap(@NonNull final IJson selector) { return unwrap ().arg (selector); }

@NonNull
default IMPLTYPE unwrap(@NonNull final IHCNode selector) { return unwrap ().arg (selector); }

@NonNull
default IMPLTYPE unwrap(@NonNull final String selector) { return unwrap ().arg (selector); }

@NonNull
default IMPLTYPE val(@NonNull final IJSExpression value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final IJson value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final IHCNode value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final String value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(final int value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(final long value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final BigInteger value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(final double value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final BigDecimal value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final JSArray value) { return val ().arg (value); }

@NonNull
default IMPLTYPE val(@NonNull final JSAnonymousFunction function) { return val ().arg (function); }

@NonNull
default IMPLTYPE width(@NonNull final IJSExpression value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final IJson value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final IHCNode value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final String value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(final int value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(final long value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final BigInteger value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(final double value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final BigDecimal value) { return width ().arg (value); }

@NonNull
default IMPLTYPE width(@NonNull final JSAnonymousFunction function) { return width ().arg (function); }

@NonNull
default IMPLTYPE wrap(@NonNull final IJSExpression wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final IJQuerySelector wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final JQuerySelectorList wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final EHTMLElement wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final ICSSClassProvider wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final IHCNode wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final String wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final JQueryInvocation wrappingElement) { return wrap ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrap(@NonNull final JSAnonymousFunction function) { return wrap ().arg (function); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final IJSExpression wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final IJQuerySelector wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final JQuerySelectorList wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final EHTMLElement wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final ICSSClassProvider wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final IHCNode wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final String wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final JQueryInvocation wrappingElement) { return wrapAll ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapAll(@NonNull final JSAnonymousFunction function) { return wrapAll ().arg (function); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final IJSExpression wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final IHCNode wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final String wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final IJQuerySelector wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final JQuerySelectorList wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final EHTMLElement wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final ICSSClassProvider wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final JQueryInvocation wrappingElement) { return wrapInner ().arg (wrappingElement); }

@NonNull
default IMPLTYPE wrapInner(@NonNull final JSAnonymousFunction function) { return wrapInner ().arg (function); }

}
