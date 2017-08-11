package com.helger.html.jquery;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;

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
 * @param <THISTYPE> Implementation type
*/
public interface IJQueryInvocationExtended <THISTYPE extends IJQueryInvocationExtended <THISTYPE>> extends IJQueryInvocation <THISTYPE>
{
@Nonnull
default THISTYPE add(@Nonnull final IJSExpression selector) { return add ().arg (selector); }

@Nonnull
default THISTYPE add(@Nonnull final IJQuerySelector selector) { return add ().arg (selector); }

@Nonnull
default THISTYPE add(@Nonnull final JQuerySelectorList selector) { return add ().arg (selector); }

@Nonnull
default THISTYPE add(@Nonnull final EHTMLElement selector) { return add ().arg (selector); }

@Nonnull
default THISTYPE add(@Nonnull final ICSSClassProvider selector) { return add ().arg (selector); }

@Nonnull
default THISTYPE add(@Nonnull final String elements) { return add ().arg (elements); }

@Nonnull
default THISTYPE add(@Nonnull final IHCNode html) { return add ().arg (html); }

@Nonnull
default THISTYPE add(@Nonnull final JQueryInvocation selection) { return add ().arg (selection); }

@Nonnull
default THISTYPE add(@Nonnull IJSExpression selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull IJSExpression selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull IJQuerySelector selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull EHTMLElement selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull String context) { return add ().arg (selector).arg (context); }

@Nonnull
default THISTYPE addBack(@Nonnull final IJSExpression selector) { return addBack ().arg (selector); }

@Nonnull
default THISTYPE addBack(@Nonnull final IJQuerySelector selector) { return addBack ().arg (selector); }

@Nonnull
default THISTYPE addBack(@Nonnull final JQuerySelectorList selector) { return addBack ().arg (selector); }

@Nonnull
default THISTYPE addBack(@Nonnull final EHTMLElement selector) { return addBack ().arg (selector); }

@Nonnull
default THISTYPE addBack(@Nonnull final ICSSClassProvider selector) { return addBack ().arg (selector); }

@Nonnull
default THISTYPE addClass(@Nonnull final IJSExpression className) { return addClass ().arg (className); }

@Nonnull
default THISTYPE addClass(@Nonnull final IJson className) { return addClass ().arg (className); }

@Nonnull
default THISTYPE addClass(@Nonnull final IHCNode className) { return addClass ().arg (className); }

@Nonnull
default THISTYPE addClass(@Nonnull final String className) { return addClass ().arg (className); }

@Nonnull
default THISTYPE addClass(@Nonnull final JSAnonymousFunction function) { return addClass ().arg (function); }

@Nonnull
default THISTYPE after(@Nonnull final IJSExpression content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final IHCNode content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final String content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final EHTMLElement content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final IJson content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final JSArray content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull final JQueryInvocation content) { return after ().arg (content); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull String content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull String content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return after ().arg (content).arg (content1); }

@Nonnull
default THISTYPE after(@Nonnull final JSAnonymousFunction function) { return after ().arg (function); }

@Nonnull
default THISTYPE ajaxComplete(@Nonnull final IJSExpression handler) { return ajaxComplete ().arg (handler); }

@Nonnull
default THISTYPE ajaxComplete(@Nonnull final JSAnonymousFunction handler) { return ajaxComplete ().arg (handler); }

@Nonnull
default THISTYPE ajaxError(@Nonnull final IJSExpression handler) { return ajaxError ().arg (handler); }

@Nonnull
default THISTYPE ajaxError(@Nonnull final JSAnonymousFunction handler) { return ajaxError ().arg (handler); }

@Nonnull
default THISTYPE ajaxSend(@Nonnull final IJSExpression handler) { return ajaxSend ().arg (handler); }

@Nonnull
default THISTYPE ajaxSend(@Nonnull final JSAnonymousFunction handler) { return ajaxSend ().arg (handler); }

@Nonnull
default THISTYPE ajaxStart(@Nonnull final IJSExpression handler) { return ajaxStart ().arg (handler); }

@Nonnull
default THISTYPE ajaxStart(@Nonnull final JSAnonymousFunction handler) { return ajaxStart ().arg (handler); }

@Nonnull
default THISTYPE ajaxStop(@Nonnull final IJSExpression handler) { return ajaxStop ().arg (handler); }

@Nonnull
default THISTYPE ajaxStop(@Nonnull final JSAnonymousFunction handler) { return ajaxStop ().arg (handler); }

@Nonnull
default THISTYPE ajaxSuccess(@Nonnull final IJSExpression handler) { return ajaxSuccess ().arg (handler); }

@Nonnull
default THISTYPE ajaxSuccess(@Nonnull final JSAnonymousFunction handler) { return ajaxSuccess ().arg (handler); }

@Nonnull
default THISTYPE animate(@Nonnull final IJSExpression properties) { return animate ().arg (properties); }

@Nonnull
default THISTYPE append(@Nonnull final IJSExpression content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final IHCNode content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final String content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final EHTMLElement content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final IJson content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final JSArray content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull final JQueryInvocation content) { return append ().arg (content); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull String content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull String content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return append ().arg (content).arg (content1); }

@Nonnull
default THISTYPE append(@Nonnull final JSAnonymousFunction function) { return append ().arg (function); }

@Nonnull
default THISTYPE appendTo(@Nonnull final IJSExpression target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final IJQuerySelector target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final JQuerySelectorList target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final EHTMLElement target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final ICSSClassProvider target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final IHCNode target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final String target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final JSArray target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE appendTo(@Nonnull final JQueryInvocation target) { return appendTo ().arg (target); }

@Nonnull
default THISTYPE attr(@Nonnull final IJSExpression attributeName) { return attr ().arg (attributeName); }

@Nonnull
default THISTYPE attr(@Nonnull final IJson attributeName) { return attr ().arg (attributeName); }

@Nonnull
default THISTYPE attr(@Nonnull final IHCNode attributeName) { return attr ().arg (attributeName); }

@Nonnull
default THISTYPE attr(@Nonnull final String attributeName) { return attr ().arg (attributeName); }

@Nonnull
default THISTYPE attr(@Nonnull final IMicroQName attributeName) { return attr ().arg (attributeName); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull IJSExpression value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull IJson value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull IHCNode value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull String value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, int value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, long value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull BigInteger value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, double value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull BigDecimal value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull JQueryInvocation value) { return attr ().arg (attributeName).arg (value); }

@Nonnull
default THISTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
default THISTYPE attr(@Nonnull IJson attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
default THISTYPE attr(@Nonnull IHCNode attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
default THISTYPE attr(@Nonnull String attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
default THISTYPE attr(@Nonnull IMicroQName attributeName, @Nonnull JSAnonymousFunction function) { return attr ().arg (attributeName).arg (function); }

@Nonnull
default THISTYPE before(@Nonnull final IJSExpression content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final IHCNode content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final String content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final EHTMLElement content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final IJson content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final JSArray content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull final JQueryInvocation content) { return before ().arg (content); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull String content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull String content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return before ().arg (content).arg (content1); }

@Nonnull
default THISTYPE before(@Nonnull final JSAnonymousFunction function) { return before ().arg (function); }

@Nonnull
default THISTYPE blur(@Nonnull final IJSExpression handler) { return blur ().arg (handler); }

@Nonnull
default THISTYPE blur(@Nonnull final JSAnonymousFunction handler) { return blur ().arg (handler); }

@Nonnull
default THISTYPE blur(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return blur ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE blur(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return blur ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE callbacks_add(@Nonnull final IJSExpression callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
default THISTYPE callbacks_add(@Nonnull final JSAnonymousFunction callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
default THISTYPE callbacks_add(@Nonnull final JSArray callbacks) { return callbacks_add ().arg (callbacks); }

@Nonnull
default THISTYPE callbacks_fire(@Nonnull final IJSExpression arguments) { return callbacks_fire ().arg (arguments); }

@Nonnull
default THISTYPE callbacks_fireWith(@Nonnull final IJSExpression context) { return callbacks_fireWith ().arg (context); }

@Nonnull
default THISTYPE callbacks_fireWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return callbacks_fireWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE callbacks_has(@Nonnull final IJSExpression callback) { return callbacks_has ().arg (callback); }

@Nonnull
default THISTYPE callbacks_has(@Nonnull final JSAnonymousFunction callback) { return callbacks_has ().arg (callback); }

@Nonnull
default THISTYPE callbacks_remove(@Nonnull final IJSExpression callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
default THISTYPE callbacks_remove(@Nonnull final JSAnonymousFunction callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
default THISTYPE callbacks_remove(@Nonnull final JSArray callbacks) { return callbacks_remove ().arg (callbacks); }

@Nonnull
default THISTYPE change(@Nonnull final IJSExpression handler) { return change ().arg (handler); }

@Nonnull
default THISTYPE change(@Nonnull final JSAnonymousFunction handler) { return change ().arg (handler); }

@Nonnull
default THISTYPE change(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return change ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE change(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return change ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE children(@Nonnull final IJSExpression selector) { return children ().arg (selector); }

@Nonnull
default THISTYPE children(@Nonnull final IJQuerySelector selector) { return children ().arg (selector); }

@Nonnull
default THISTYPE children(@Nonnull final JQuerySelectorList selector) { return children ().arg (selector); }

@Nonnull
default THISTYPE children(@Nonnull final EHTMLElement selector) { return children ().arg (selector); }

@Nonnull
default THISTYPE children(@Nonnull final ICSSClassProvider selector) { return children ().arg (selector); }

@Nonnull
default THISTYPE clearQueue(@Nonnull final IJSExpression queueName) { return clearQueue ().arg (queueName); }

@Nonnull
default THISTYPE clearQueue(@Nonnull final IJson queueName) { return clearQueue ().arg (queueName); }

@Nonnull
default THISTYPE clearQueue(@Nonnull final IHCNode queueName) { return clearQueue ().arg (queueName); }

@Nonnull
default THISTYPE clearQueue(@Nonnull final String queueName) { return clearQueue ().arg (queueName); }

@Nonnull
default THISTYPE click(@Nonnull final IJSExpression handler) { return click ().arg (handler); }

@Nonnull
default THISTYPE click(@Nonnull final JSAnonymousFunction handler) { return click ().arg (handler); }

@Nonnull
default THISTYPE click(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return click ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE click(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return click ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE _clone(@Nonnull final IJSExpression withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@Nonnull
default THISTYPE _clone(final boolean withDataAndEvents) { return _clone ().arg (withDataAndEvents); }

@Nonnull
default THISTYPE _clone(@Nonnull IJSExpression withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
default THISTYPE _clone(boolean withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
default THISTYPE _clone(@Nonnull IJSExpression withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
default THISTYPE _clone(boolean withDataAndEvents, boolean deepWithDataAndEvents) { return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents); }

@Nonnull
default THISTYPE closest(@Nonnull final IJSExpression selector) { return closest ().arg (selector); }

@Nonnull
default THISTYPE closest(@Nonnull final IJQuerySelector selector) { return closest ().arg (selector); }

@Nonnull
default THISTYPE closest(@Nonnull final JQuerySelectorList selector) { return closest ().arg (selector); }

@Nonnull
default THISTYPE closest(@Nonnull final EHTMLElement selector) { return closest ().arg (selector); }

@Nonnull
default THISTYPE closest(@Nonnull final ICSSClassProvider selector) { return closest ().arg (selector); }

@Nonnull
default THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull IJSExpression selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull EHTMLElement selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull String context) { return closest ().arg (selector).arg (context); }

@Nonnull
default THISTYPE closest(@Nonnull final JQueryInvocation selection) { return closest ().arg (selection); }

@Nonnull
default THISTYPE closest(@Nonnull final String element) { return closest ().arg (element); }

@Nonnull
default THISTYPE contextmenu(@Nonnull final IJSExpression handler) { return contextmenu ().arg (handler); }

@Nonnull
default THISTYPE contextmenu(@Nonnull final JSAnonymousFunction handler) { return contextmenu ().arg (handler); }

@Nonnull
default THISTYPE contextmenu(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return contextmenu ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE contextmenu(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return contextmenu ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE css(@Nonnull final IJSExpression propertyName) { return css ().arg (propertyName); }

@Nonnull
default THISTYPE css(@Nonnull final IJson propertyName) { return css ().arg (propertyName); }

@Nonnull
default THISTYPE css(@Nonnull final IHCNode propertyName) { return css ().arg (propertyName); }

@Nonnull
default THISTYPE css(@Nonnull final String propertyName) { return css ().arg (propertyName); }

@Nonnull
default THISTYPE css(@Nonnull final JSArray propertyNames) { return css ().arg (propertyNames); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull IJSExpression value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull IJson value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull IHCNode value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull String value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, int value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, long value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull BigInteger value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, double value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull BigDecimal value) { return css ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE css(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE css(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE css(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE css(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function) { return css ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE data(@Nonnull IJSExpression key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
default THISTYPE data(@Nonnull IJson key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
default THISTYPE data(@Nonnull IHCNode key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
default THISTYPE data(@Nonnull String key, @Nonnull IJSExpression value) { return data ().arg (key).arg (value); }

@Nonnull
default THISTYPE data(@Nonnull final IJSExpression obj) { return data ().arg (obj); }

@Nonnull
default THISTYPE data(@Nonnull final IJson key) { return data ().arg (key); }

@Nonnull
default THISTYPE data(@Nonnull final IHCNode key) { return data ().arg (key); }

@Nonnull
default THISTYPE data(@Nonnull final String key) { return data ().arg (key); }

@Nonnull
default THISTYPE dblclick(@Nonnull final IJSExpression handler) { return dblclick ().arg (handler); }

@Nonnull
default THISTYPE dblclick(@Nonnull final JSAnonymousFunction handler) { return dblclick ().arg (handler); }

@Nonnull
default THISTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return dblclick ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return dblclick ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE deferred_always(@Nonnull final IJSExpression alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@Nonnull
default THISTYPE deferred_always(@Nonnull final JSAnonymousFunction alwaysCallbacks) { return deferred_always ().arg (alwaysCallbacks); }

@Nonnull
default THISTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
default THISTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
default THISTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
default THISTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1) { return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1); }

@Nonnull
default THISTYPE deferred_done(@Nonnull final IJSExpression doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@Nonnull
default THISTYPE deferred_done(@Nonnull final JSAnonymousFunction doneCallbacks) { return deferred_done ().arg (doneCallbacks); }

@Nonnull
default THISTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
default THISTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
default THISTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
default THISTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1) { return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull final IJSExpression failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull final JSAnonymousFunction failCallbacks) { return deferred_fail ().arg (failCallbacks); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull IJSExpression failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
default THISTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1) { return deferred_fail ().arg (failCallbacks).arg (failCallbacks1); }

@Nonnull
default THISTYPE deferred_notify(@Nonnull final IJSExpression args) { return deferred_notify ().arg (args); }

@Nonnull
default THISTYPE deferred_notifyWith(@Nonnull final IJSExpression context) { return deferred_notifyWith ().arg (context); }

@Nonnull
default THISTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_notifyWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_notifyWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull final IJSExpression progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull final JSAnonymousFunction progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull final JSArray progressCallbacks) { return deferred_progress ().arg (progressCallbacks); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull IJSExpression progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSArray progressCallbacks1) { return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1); }

@Nonnull
default THISTYPE deferred_promise(@Nonnull final IJSExpression target) { return deferred_promise ().arg (target); }

@Nonnull
default THISTYPE deferred_reject(@Nonnull final IJSExpression args) { return deferred_reject ().arg (args); }

@Nonnull
default THISTYPE deferred_rejectWith(@Nonnull final IJSExpression context) { return deferred_rejectWith ().arg (context); }

@Nonnull
default THISTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_rejectWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_rejectWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_resolve(@Nonnull final IJSExpression args) { return deferred_resolve ().arg (args); }

@Nonnull
default THISTYPE deferred_resolveWith(@Nonnull final IJSExpression context) { return deferred_resolveWith ().arg (context); }

@Nonnull
default THISTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args) { return deferred_resolveWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull JSArray args) { return deferred_resolveWith ().arg (context).arg (args); }

@Nonnull
default THISTYPE deferred_then(@Nonnull final IJSExpression doneFilter) { return deferred_then ().arg (doneFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull final JSAnonymousFunction doneFilter) { return deferred_then ().arg (doneFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter) { return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
default THISTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
default THISTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks) { return deferred_then ().arg (doneCallbacks).arg (failCallbacks); }

@Nonnull
default THISTYPE delay(@Nonnull final IJSExpression duration) { return delay ().arg (duration); }

@Nonnull
default THISTYPE delay(final int duration) { return delay ().arg (duration); }

@Nonnull
default THISTYPE delay(final long duration) { return delay ().arg (duration); }

@Nonnull
default THISTYPE delay(@Nonnull final BigInteger duration) { return delay ().arg (duration); }

@Nonnull
default THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(int duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(long duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IJSExpression queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(int duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(long duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IJson queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(int duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(long duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull BigInteger duration, @Nonnull IHCNode queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull IJSExpression duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(int duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(long duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE delay(@Nonnull BigInteger duration, @Nonnull String queueName) { return delay ().arg (duration).arg (queueName); }

@Nonnull
default THISTYPE dequeue(@Nonnull final IJSExpression queueName) { return dequeue ().arg (queueName); }

@Nonnull
default THISTYPE dequeue(@Nonnull final IJson queueName) { return dequeue ().arg (queueName); }

@Nonnull
default THISTYPE dequeue(@Nonnull final IHCNode queueName) { return dequeue ().arg (queueName); }

@Nonnull
default THISTYPE dequeue(@Nonnull final String queueName) { return dequeue ().arg (queueName); }

@Nonnull
default THISTYPE detach(@Nonnull final IJSExpression selector) { return detach ().arg (selector); }

@Nonnull
default THISTYPE detach(@Nonnull final IJQuerySelector selector) { return detach ().arg (selector); }

@Nonnull
default THISTYPE detach(@Nonnull final JQuerySelectorList selector) { return detach ().arg (selector); }

@Nonnull
default THISTYPE detach(@Nonnull final EHTMLElement selector) { return detach ().arg (selector); }

@Nonnull
default THISTYPE detach(@Nonnull final ICSSClassProvider selector) { return detach ().arg (selector); }

@Nonnull
default THISTYPE each(@Nonnull final IJSExpression function) { return each ().arg (function); }

@Nonnull
default THISTYPE each(@Nonnull final JSAnonymousFunction function) { return each ().arg (function); }

@Nonnull
default THISTYPE _eq(@Nonnull final IJSExpression index) { return _eq ().arg (index); }

@Nonnull
default THISTYPE _eq(final int index) { return _eq ().arg (index); }

@Nonnull
default THISTYPE _eq(final long index) { return _eq ().arg (index); }

@Nonnull
default THISTYPE _eq(@Nonnull final BigInteger index) { return _eq ().arg (index); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity) { return fadeTo ().arg (duration).arg (opacity); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing) { return fadeTo ().arg (duration).arg (opacity).arg (easing); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete) { return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete); }

@Nonnull
default THISTYPE filter(@Nonnull final IJSExpression selector) { return filter ().arg (selector); }

@Nonnull
default THISTYPE filter(@Nonnull final IJQuerySelector selector) { return filter ().arg (selector); }

@Nonnull
default THISTYPE filter(@Nonnull final JQuerySelectorList selector) { return filter ().arg (selector); }

@Nonnull
default THISTYPE filter(@Nonnull final EHTMLElement selector) { return filter ().arg (selector); }

@Nonnull
default THISTYPE filter(@Nonnull final ICSSClassProvider selector) { return filter ().arg (selector); }

@Nonnull
default THISTYPE filter(@Nonnull final JSAnonymousFunction function) { return filter ().arg (function); }

@Nonnull
default THISTYPE filter(@Nonnull final String elements) { return filter ().arg (elements); }

@Nonnull
default THISTYPE filter(@Nonnull final JQueryInvocation selection) { return filter ().arg (selection); }

@Nonnull
default THISTYPE find(@Nonnull final IJSExpression selector) { return find ().arg (selector); }

@Nonnull
default THISTYPE find(@Nonnull final IJQuerySelector selector) { return find ().arg (selector); }

@Nonnull
default THISTYPE find(@Nonnull final JQuerySelectorList selector) { return find ().arg (selector); }

@Nonnull
default THISTYPE find(@Nonnull final EHTMLElement selector) { return find ().arg (selector); }

@Nonnull
default THISTYPE find(@Nonnull final ICSSClassProvider selector) { return find ().arg (selector); }

@Nonnull
default THISTYPE find(@Nonnull final String element) { return find ().arg (element); }

@Nonnull
default THISTYPE find(@Nonnull final JQueryInvocation element) { return find ().arg (element); }

@Nonnull
default THISTYPE finish(@Nonnull final IJSExpression queue) { return finish ().arg (queue); }

@Nonnull
default THISTYPE finish(@Nonnull final IJson queue) { return finish ().arg (queue); }

@Nonnull
default THISTYPE finish(@Nonnull final IHCNode queue) { return finish ().arg (queue); }

@Nonnull
default THISTYPE finish(@Nonnull final String queue) { return finish ().arg (queue); }

@Nonnull
default THISTYPE focus(@Nonnull final IJSExpression handler) { return focus ().arg (handler); }

@Nonnull
default THISTYPE focus(@Nonnull final JSAnonymousFunction handler) { return focus ().arg (handler); }

@Nonnull
default THISTYPE focus(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focus ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE focus(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focus ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE focusin(@Nonnull final IJSExpression handler) { return focusin ().arg (handler); }

@Nonnull
default THISTYPE focusin(@Nonnull final JSAnonymousFunction handler) { return focusin ().arg (handler); }

@Nonnull
default THISTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focusin ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focusin ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE focusout(@Nonnull final IJSExpression handler) { return focusout ().arg (handler); }

@Nonnull
default THISTYPE focusout(@Nonnull final JSAnonymousFunction handler) { return focusout ().arg (handler); }

@Nonnull
default THISTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return focusout ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return focusout ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE get(@Nonnull final IJSExpression index) { return get ().arg (index); }

@Nonnull
default THISTYPE get(final int index) { return get ().arg (index); }

@Nonnull
default THISTYPE get(final long index) { return get ().arg (index); }

@Nonnull
default THISTYPE get(@Nonnull final BigInteger index) { return get ().arg (index); }

@Nonnull
default THISTYPE has(@Nonnull final IJSExpression selector) { return has ().arg (selector); }

@Nonnull
default THISTYPE has(@Nonnull final IJson selector) { return has ().arg (selector); }

@Nonnull
default THISTYPE has(@Nonnull final IHCNode selector) { return has ().arg (selector); }

@Nonnull
default THISTYPE has(@Nonnull final String selector) { return has ().arg (selector); }

@Nonnull
default THISTYPE has(@Nonnull final EHTMLElement contained) { return has ().arg (contained); }

@Nonnull
default THISTYPE hasClass(@Nonnull final IJSExpression className) { return hasClass ().arg (className); }

@Nonnull
default THISTYPE hasClass(@Nonnull final IJson className) { return hasClass ().arg (className); }

@Nonnull
default THISTYPE hasClass(@Nonnull final IHCNode className) { return hasClass ().arg (className); }

@Nonnull
default THISTYPE hasClass(@Nonnull final String className) { return hasClass ().arg (className); }

@Nonnull
default THISTYPE height(@Nonnull final IJSExpression value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final IJson value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final IHCNode value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final String value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(final int value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(final long value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final BigInteger value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(final double value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final BigDecimal value) { return height ().arg (value); }

@Nonnull
default THISTYPE height(@Nonnull final JSAnonymousFunction function) { return height ().arg (function); }

@Nonnull
default THISTYPE hide(@Nonnull final IJSExpression duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(final int duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(final long duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(@Nonnull final BigInteger duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(final double duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(@Nonnull final BigDecimal duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(@Nonnull final IJson duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(@Nonnull final IHCNode duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hide(@Nonnull final String duration) { return hide ().arg (duration); }

@Nonnull
default THISTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
default THISTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull IJSExpression handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
default THISTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
default THISTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull JSAnonymousFunction handlerOut) { return hover ().arg (handlerIn).arg (handlerOut); }

@Nonnull
default THISTYPE hover(@Nonnull final IJSExpression handlerInOut) { return hover ().arg (handlerInOut); }

@Nonnull
default THISTYPE hover(@Nonnull final JSAnonymousFunction handlerInOut) { return hover ().arg (handlerInOut); }

@Nonnull
default THISTYPE html(@Nonnull final IJSExpression htmlString) { return html ().arg (htmlString); }

@Nonnull
default THISTYPE html(@Nonnull final IHCNode htmlString) { return html ().arg (htmlString); }

@Nonnull
default THISTYPE html(@Nonnull final String htmlString) { return html ().arg (htmlString); }

@Nonnull
default THISTYPE html(@Nonnull final JSAnonymousFunction function) { return html ().arg (function); }

@Nonnull
default THISTYPE index(@Nonnull final IJSExpression selector) { return index ().arg (selector); }

@Nonnull
default THISTYPE index(@Nonnull final IJQuerySelector selector) { return index ().arg (selector); }

@Nonnull
default THISTYPE index(@Nonnull final JQuerySelectorList selector) { return index ().arg (selector); }

@Nonnull
default THISTYPE index(@Nonnull final EHTMLElement selector) { return index ().arg (selector); }

@Nonnull
default THISTYPE index(@Nonnull final ICSSClassProvider selector) { return index ().arg (selector); }

@Nonnull
default THISTYPE index(@Nonnull final String element) { return index ().arg (element); }

@Nonnull
default THISTYPE index(@Nonnull final JQueryInvocation element) { return index ().arg (element); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final IJSExpression value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final IJson value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final IHCNode value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final String value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(final int value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(final long value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final BigInteger value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(final double value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final BigDecimal value) { return innerHeight ().arg (value); }

@Nonnull
default THISTYPE innerHeight(@Nonnull final JSAnonymousFunction function) { return innerHeight ().arg (function); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final IJSExpression value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final IJson value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final IHCNode value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final String value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(final int value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(final long value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final BigInteger value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(final double value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final BigDecimal value) { return innerWidth ().arg (value); }

@Nonnull
default THISTYPE innerWidth(@Nonnull final JSAnonymousFunction function) { return innerWidth ().arg (function); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final IJSExpression target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final IJQuerySelector target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final JQuerySelectorList target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final EHTMLElement target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final ICSSClassProvider target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final IHCNode target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final String target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final JSArray target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertAfter(@Nonnull final JQueryInvocation target) { return insertAfter ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final IJSExpression target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final IJQuerySelector target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final JQuerySelectorList target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final EHTMLElement target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final ICSSClassProvider target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final IHCNode target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final String target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final JSArray target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE insertBefore(@Nonnull final JQueryInvocation target) { return insertBefore ().arg (target); }

@Nonnull
default THISTYPE is(@Nonnull final IJSExpression selector) { return is ().arg (selector); }

@Nonnull
default THISTYPE is(@Nonnull final IJQuerySelector selector) { return is ().arg (selector); }

@Nonnull
default THISTYPE is(@Nonnull final JQuerySelectorList selector) { return is ().arg (selector); }

@Nonnull
default THISTYPE is(@Nonnull final EHTMLElement selector) { return is ().arg (selector); }

@Nonnull
default THISTYPE is(@Nonnull final ICSSClassProvider selector) { return is ().arg (selector); }

@Nonnull
default THISTYPE is(@Nonnull final JSAnonymousFunction function) { return is ().arg (function); }

@Nonnull
default THISTYPE is(@Nonnull final JQueryInvocation selection) { return is ().arg (selection); }

@Nonnull
default THISTYPE is(@Nonnull final String elements) { return is ().arg (elements); }

@Nonnull
default THISTYPE keydown(@Nonnull final IJSExpression handler) { return keydown ().arg (handler); }

@Nonnull
default THISTYPE keydown(@Nonnull final JSAnonymousFunction handler) { return keydown ().arg (handler); }

@Nonnull
default THISTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keydown ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keydown ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE keypress(@Nonnull final IJSExpression handler) { return keypress ().arg (handler); }

@Nonnull
default THISTYPE keypress(@Nonnull final JSAnonymousFunction handler) { return keypress ().arg (handler); }

@Nonnull
default THISTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keypress ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keypress ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE keyup(@Nonnull final IJSExpression handler) { return keyup ().arg (handler); }

@Nonnull
default THISTYPE keyup(@Nonnull final JSAnonymousFunction handler) { return keyup ().arg (handler); }

@Nonnull
default THISTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return keyup ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return keyup ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE load(@Nonnull final IJSExpression url) { return load ().arg (url); }

@Nonnull
default THISTYPE load(@Nonnull final IJson url) { return load ().arg (url); }

@Nonnull
default THISTYPE load(@Nonnull final IHCNode url) { return load ().arg (url); }

@Nonnull
default THISTYPE load(@Nonnull final String url) { return load ().arg (url); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJson data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IHCNode data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull String data) { return load ().arg (url).arg (data); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull IJSExpression complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull JSAnonymousFunction complete) { return load ().arg (url).arg (data).arg (complete); }

@Nonnull
default THISTYPE map(@Nonnull final IJSExpression callback) { return map ().arg (callback); }

@Nonnull
default THISTYPE map(@Nonnull final JSAnonymousFunction callback) { return map ().arg (callback); }

@Nonnull
default THISTYPE mousedown(@Nonnull final IJSExpression handler) { return mousedown ().arg (handler); }

@Nonnull
default THISTYPE mousedown(@Nonnull final JSAnonymousFunction handler) { return mousedown ().arg (handler); }

@Nonnull
default THISTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mousedown ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mousedown ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseenter(@Nonnull final IJSExpression handler) { return mouseenter ().arg (handler); }

@Nonnull
default THISTYPE mouseenter(@Nonnull final JSAnonymousFunction handler) { return mouseenter ().arg (handler); }

@Nonnull
default THISTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseenter ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseenter ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseleave(@Nonnull final IJSExpression handler) { return mouseleave ().arg (handler); }

@Nonnull
default THISTYPE mouseleave(@Nonnull final JSAnonymousFunction handler) { return mouseleave ().arg (handler); }

@Nonnull
default THISTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseleave ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseleave ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mousemove(@Nonnull final IJSExpression handler) { return mousemove ().arg (handler); }

@Nonnull
default THISTYPE mousemove(@Nonnull final JSAnonymousFunction handler) { return mousemove ().arg (handler); }

@Nonnull
default THISTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mousemove ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mousemove ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseout(@Nonnull final IJSExpression handler) { return mouseout ().arg (handler); }

@Nonnull
default THISTYPE mouseout(@Nonnull final JSAnonymousFunction handler) { return mouseout ().arg (handler); }

@Nonnull
default THISTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseout ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseout ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseover(@Nonnull final IJSExpression handler) { return mouseover ().arg (handler); }

@Nonnull
default THISTYPE mouseover(@Nonnull final JSAnonymousFunction handler) { return mouseover ().arg (handler); }

@Nonnull
default THISTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseover ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseover ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseup(@Nonnull final IJSExpression handler) { return mouseup ().arg (handler); }

@Nonnull
default THISTYPE mouseup(@Nonnull final JSAnonymousFunction handler) { return mouseup ().arg (handler); }

@Nonnull
default THISTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return mouseup ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return mouseup ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE next(@Nonnull final IJSExpression selector) { return next ().arg (selector); }

@Nonnull
default THISTYPE next(@Nonnull final IJQuerySelector selector) { return next ().arg (selector); }

@Nonnull
default THISTYPE next(@Nonnull final JQuerySelectorList selector) { return next ().arg (selector); }

@Nonnull
default THISTYPE next(@Nonnull final EHTMLElement selector) { return next ().arg (selector); }

@Nonnull
default THISTYPE next(@Nonnull final ICSSClassProvider selector) { return next ().arg (selector); }

@Nonnull
default THISTYPE nextAll(@Nonnull final IJSExpression selector) { return nextAll ().arg (selector); }

@Nonnull
default THISTYPE nextAll(@Nonnull final IJson selector) { return nextAll ().arg (selector); }

@Nonnull
default THISTYPE nextAll(@Nonnull final IHCNode selector) { return nextAll ().arg (selector); }

@Nonnull
default THISTYPE nextAll(@Nonnull final String selector) { return nextAll ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final IJSExpression selector) { return nextUntil ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final IJQuerySelector selector) { return nextUntil ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final JQuerySelectorList selector) { return nextUntil ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final EHTMLElement selector) { return nextUntil ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final ICSSClassProvider selector) { return nextUntil ().arg (selector); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final String element) { return nextUntil ().arg (element); }

@Nonnull
default THISTYPE nextUntil(@Nonnull final JQueryInvocation element) { return nextUntil ().arg (element); }

@Nonnull
default THISTYPE nextUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return nextUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE _not(@Nonnull final IJSExpression selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final IJQuerySelector selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final JQuerySelectorList selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final EHTMLElement selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final ICSSClassProvider selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final String selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final JSArray selector) { return _not ().arg (selector); }

@Nonnull
default THISTYPE _not(@Nonnull final JSAnonymousFunction function) { return _not ().arg (function); }

@Nonnull
default THISTYPE _not(@Nonnull final JQueryInvocation selection) { return _not ().arg (selection); }

@Nonnull
default THISTYPE off(@Nonnull final IJSExpression events) { return off ().arg (events); }

@Nonnull
default THISTYPE off(@Nonnull final IJson events) { return off ().arg (events); }

@Nonnull
default THISTYPE off(@Nonnull final IHCNode events) { return off ().arg (events); }

@Nonnull
default THISTYPE off(@Nonnull final String events) { return off ().arg (events); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJson selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull String selector) { return off ().arg (events).arg (selector); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler) { return off ().arg (events).arg (selector).arg (handler); }

@Nonnull
default THISTYPE offset(@Nonnull final IJSExpression coordinates) { return offset ().arg (coordinates); }

@Nonnull
default THISTYPE offset(@Nonnull final JSAnonymousFunction function) { return offset ().arg (function); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJson selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull String selector) { return on ().arg (events).arg (selector); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data) { return on ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return on ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE on(@Nonnull final IJSExpression events) { return on ().arg (events); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data) { return one ().arg (events).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJson selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull String selector) { return one ().arg (events).arg (selector); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data) { return one ().arg (events).arg (selector).arg (data); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler) { return one ().arg (events).arg (selector).arg (data).arg (handler); }

@Nonnull
default THISTYPE one(@Nonnull final IJSExpression events) { return one ().arg (events); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final IJSExpression includeMargin) { return outerHeight ().arg (includeMargin); }

@Nonnull
default THISTYPE outerHeight(final boolean includeMargin) { return outerHeight ().arg (includeMargin); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final IJson value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final IHCNode value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final String value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(final int value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(final long value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final BigInteger value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(final double value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final BigDecimal value) { return outerHeight ().arg (value); }

@Nonnull
default THISTYPE outerHeight(@Nonnull final JSAnonymousFunction function) { return outerHeight ().arg (function); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final IJSExpression includeMargin) { return outerWidth ().arg (includeMargin); }

@Nonnull
default THISTYPE outerWidth(final boolean includeMargin) { return outerWidth ().arg (includeMargin); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final IJson value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final IHCNode value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final String value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(final int value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(final long value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final BigInteger value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(final double value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final BigDecimal value) { return outerWidth ().arg (value); }

@Nonnull
default THISTYPE outerWidth(@Nonnull final JSAnonymousFunction function) { return outerWidth ().arg (function); }

@Nonnull
default THISTYPE parent(@Nonnull final IJSExpression selector) { return parent ().arg (selector); }

@Nonnull
default THISTYPE parent(@Nonnull final IJQuerySelector selector) { return parent ().arg (selector); }

@Nonnull
default THISTYPE parent(@Nonnull final JQuerySelectorList selector) { return parent ().arg (selector); }

@Nonnull
default THISTYPE parent(@Nonnull final EHTMLElement selector) { return parent ().arg (selector); }

@Nonnull
default THISTYPE parent(@Nonnull final ICSSClassProvider selector) { return parent ().arg (selector); }

@Nonnull
default THISTYPE parents(@Nonnull final IJSExpression selector) { return parents ().arg (selector); }

@Nonnull
default THISTYPE parents(@Nonnull final IJQuerySelector selector) { return parents ().arg (selector); }

@Nonnull
default THISTYPE parents(@Nonnull final JQuerySelectorList selector) { return parents ().arg (selector); }

@Nonnull
default THISTYPE parents(@Nonnull final EHTMLElement selector) { return parents ().arg (selector); }

@Nonnull
default THISTYPE parents(@Nonnull final ICSSClassProvider selector) { return parents ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final IJSExpression selector) { return parentsUntil ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final IJQuerySelector selector) { return parentsUntil ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final JQuerySelectorList selector) { return parentsUntil ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final EHTMLElement selector) { return parentsUntil ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final ICSSClassProvider selector) { return parentsUntil ().arg (selector); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final String element) { return parentsUntil ().arg (element); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull final JQueryInvocation element) { return parentsUntil ().arg (element); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return parentsUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prepend(@Nonnull final IJSExpression content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final IHCNode content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final String content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final EHTMLElement content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final IJson content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final JSArray content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull final JQueryInvocation content) { return prepend ().arg (content); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull String content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IJson content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JSArray content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull String content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull IJson content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JSArray content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1) { return prepend ().arg (content).arg (content1); }

@Nonnull
default THISTYPE prepend(@Nonnull final JSAnonymousFunction function) { return prepend ().arg (function); }

@Nonnull
default THISTYPE prependTo(@Nonnull final IJSExpression target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final IJQuerySelector target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final JQuerySelectorList target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final EHTMLElement target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final ICSSClassProvider target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final IHCNode target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final String target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final JSArray target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prependTo(@Nonnull final JQueryInvocation target) { return prependTo ().arg (target); }

@Nonnull
default THISTYPE prev(@Nonnull final IJSExpression selector) { return prev ().arg (selector); }

@Nonnull
default THISTYPE prev(@Nonnull final IJQuerySelector selector) { return prev ().arg (selector); }

@Nonnull
default THISTYPE prev(@Nonnull final JQuerySelectorList selector) { return prev ().arg (selector); }

@Nonnull
default THISTYPE prev(@Nonnull final EHTMLElement selector) { return prev ().arg (selector); }

@Nonnull
default THISTYPE prev(@Nonnull final ICSSClassProvider selector) { return prev ().arg (selector); }

@Nonnull
default THISTYPE prevAll(@Nonnull final IJSExpression selector) { return prevAll ().arg (selector); }

@Nonnull
default THISTYPE prevAll(@Nonnull final IJQuerySelector selector) { return prevAll ().arg (selector); }

@Nonnull
default THISTYPE prevAll(@Nonnull final JQuerySelectorList selector) { return prevAll ().arg (selector); }

@Nonnull
default THISTYPE prevAll(@Nonnull final EHTMLElement selector) { return prevAll ().arg (selector); }

@Nonnull
default THISTYPE prevAll(@Nonnull final ICSSClassProvider selector) { return prevAll ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final IJSExpression selector) { return prevUntil ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final IJQuerySelector selector) { return prevUntil ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final JQuerySelectorList selector) { return prevUntil ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final EHTMLElement selector) { return prevUntil ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final ICSSClassProvider selector) { return prevUntil ().arg (selector); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (selector).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final String element) { return prevUntil ().arg (element); }

@Nonnull
default THISTYPE prevUntil(@Nonnull final JQueryInvocation element) { return prevUntil ().arg (element); }

@Nonnull
default THISTYPE prevUntil(@Nonnull String element, @Nonnull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull String element, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull String element, @Nonnull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter) { return prevUntil ().arg (element).arg (filter); }

@Nonnull
default THISTYPE promise(@Nonnull final IJSExpression type) { return promise ().arg (type); }

@Nonnull
default THISTYPE promise(@Nonnull final IJson type) { return promise ().arg (type); }

@Nonnull
default THISTYPE promise(@Nonnull final IHCNode type) { return promise ().arg (type); }

@Nonnull
default THISTYPE promise(@Nonnull final String type) { return promise ().arg (type); }

@Nonnull
default THISTYPE promise(@Nonnull IJSExpression type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
default THISTYPE promise(@Nonnull IJson type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
default THISTYPE promise(@Nonnull IHCNode type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
default THISTYPE promise(@Nonnull String type, @Nonnull IJSExpression target) { return promise ().arg (type).arg (target); }

@Nonnull
default THISTYPE prop(@Nonnull final IJSExpression propertyName) { return prop ().arg (propertyName); }

@Nonnull
default THISTYPE prop(@Nonnull final IJson propertyName) { return prop ().arg (propertyName); }

@Nonnull
default THISTYPE prop(@Nonnull final IHCNode propertyName) { return prop ().arg (propertyName); }

@Nonnull
default THISTYPE prop(@Nonnull final String propertyName) { return prop ().arg (propertyName); }

@Nonnull
default THISTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE prop(@Nonnull IJson propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE prop(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE prop(@Nonnull String propertyName, @Nonnull IJSExpression value) { return prop ().arg (propertyName).arg (value); }

@Nonnull
default THISTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE prop(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE prop(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE prop(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function) { return prop ().arg (propertyName).arg (function); }

@Nonnull
default THISTYPE pushStack(@Nonnull final IJSExpression elements) { return pushStack ().arg (elements); }

@Nonnull
default THISTYPE pushStack(@Nonnull final JSArray elements) { return pushStack ().arg (elements); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull IJSExpression arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull JSArray arguments) { return pushStack ().arg (elements).arg (name).arg (arguments); }

@Nonnull
default THISTYPE queue(@Nonnull final IJSExpression queueName) { return queue ().arg (queueName); }

@Nonnull
default THISTYPE queue(@Nonnull final IJson queueName) { return queue ().arg (queueName); }

@Nonnull
default THISTYPE queue(@Nonnull final IHCNode queueName) { return queue ().arg (queueName); }

@Nonnull
default THISTYPE queue(@Nonnull final String queueName) { return queue ().arg (queueName); }

@Nonnull
default THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IJson queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull String queueName, @Nonnull IJSExpression newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IJson queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull String queueName, @Nonnull JSArray newQueue) { return queue ().arg (queueName).arg (newQueue); }

@Nonnull
default THISTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
default THISTYPE queue(@Nonnull IJson queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
default THISTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
default THISTYPE queue(@Nonnull String queueName, @Nonnull JSAnonymousFunction callback) { return queue ().arg (queueName).arg (callback); }

@Nonnull
default THISTYPE ready(@Nonnull final IJSExpression handler) { return ready ().arg (handler); }

@Nonnull
default THISTYPE ready(@Nonnull final JSAnonymousFunction handler) { return ready ().arg (handler); }

@Nonnull
default THISTYPE remove(@Nonnull final IJSExpression selector) { return remove ().arg (selector); }

@Nonnull
default THISTYPE remove(@Nonnull final IJson selector) { return remove ().arg (selector); }

@Nonnull
default THISTYPE remove(@Nonnull final IHCNode selector) { return remove ().arg (selector); }

@Nonnull
default THISTYPE remove(@Nonnull final String selector) { return remove ().arg (selector); }

@Nonnull
default THISTYPE removeAttr(@Nonnull final IJSExpression attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
default THISTYPE removeAttr(@Nonnull final IJson attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
default THISTYPE removeAttr(@Nonnull final IHCNode attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
default THISTYPE removeAttr(@Nonnull final String attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
default THISTYPE removeAttr(@Nonnull final IMicroQName attributeName) { return removeAttr ().arg (attributeName); }

@Nonnull
default THISTYPE removeClass(@Nonnull final IJSExpression className) { return removeClass ().arg (className); }

@Nonnull
default THISTYPE removeClass(@Nonnull final IJson className) { return removeClass ().arg (className); }

@Nonnull
default THISTYPE removeClass(@Nonnull final IHCNode className) { return removeClass ().arg (className); }

@Nonnull
default THISTYPE removeClass(@Nonnull final String className) { return removeClass ().arg (className); }

@Nonnull
default THISTYPE removeClass(@Nonnull final JSAnonymousFunction function) { return removeClass ().arg (function); }

@Nonnull
default THISTYPE removeData(@Nonnull final IJSExpression name) { return removeData ().arg (name); }

@Nonnull
default THISTYPE removeData(@Nonnull final IJson name) { return removeData ().arg (name); }

@Nonnull
default THISTYPE removeData(@Nonnull final IHCNode name) { return removeData ().arg (name); }

@Nonnull
default THISTYPE removeData(@Nonnull final String name) { return removeData ().arg (name); }

@Nonnull
default THISTYPE removeData(@Nonnull final JSArray list) { return removeData ().arg (list); }

@Nonnull
default THISTYPE removeProp(@Nonnull final IJSExpression propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
default THISTYPE removeProp(@Nonnull final IJson propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
default THISTYPE removeProp(@Nonnull final IHCNode propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
default THISTYPE removeProp(@Nonnull final String propertyName) { return removeProp ().arg (propertyName); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final IJSExpression target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final IJQuerySelector target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final JQuerySelectorList target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final EHTMLElement target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final ICSSClassProvider target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final JQueryInvocation target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final JSArray target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceAll(@Nonnull final String target) { return replaceAll ().arg (target); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final IJSExpression newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final IHCNode newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final String newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final EHTMLElement newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final JSArray newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final JQueryInvocation newContent) { return replaceWith ().arg (newContent); }

@Nonnull
default THISTYPE replaceWith(@Nonnull final JSAnonymousFunction function) { return replaceWith ().arg (function); }

@Nonnull
default THISTYPE resize(@Nonnull final IJSExpression handler) { return resize ().arg (handler); }

@Nonnull
default THISTYPE resize(@Nonnull final JSAnonymousFunction handler) { return resize ().arg (handler); }

@Nonnull
default THISTYPE resize(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return resize ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE resize(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return resize ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE scroll(@Nonnull final IJSExpression handler) { return scroll ().arg (handler); }

@Nonnull
default THISTYPE scroll(@Nonnull final JSAnonymousFunction handler) { return scroll ().arg (handler); }

@Nonnull
default THISTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return scroll ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return scroll ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE scrollLeft(@Nonnull final IJSExpression value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollLeft(final int value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollLeft(final long value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollLeft(@Nonnull final BigInteger value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollLeft(final double value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollLeft(@Nonnull final BigDecimal value) { return scrollLeft ().arg (value); }

@Nonnull
default THISTYPE scrollTop(@Nonnull final IJSExpression value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE scrollTop(final int value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE scrollTop(final long value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE scrollTop(@Nonnull final BigInteger value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE scrollTop(final double value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE scrollTop(@Nonnull final BigDecimal value) { return scrollTop ().arg (value); }

@Nonnull
default THISTYPE select(@Nonnull final IJSExpression handler) { return select ().arg (handler); }

@Nonnull
default THISTYPE select(@Nonnull final JSAnonymousFunction handler) { return select ().arg (handler); }

@Nonnull
default THISTYPE select(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return select ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE select(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return select ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE show(@Nonnull final IJSExpression duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(final int duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(final long duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(@Nonnull final BigInteger duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(final double duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(@Nonnull final BigDecimal duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(@Nonnull final IJson duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(@Nonnull final IHCNode duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE show(@Nonnull final String duration) { return show ().arg (duration); }

@Nonnull
default THISTYPE siblings(@Nonnull final IJSExpression selector) { return siblings ().arg (selector); }

@Nonnull
default THISTYPE siblings(@Nonnull final IJQuerySelector selector) { return siblings ().arg (selector); }

@Nonnull
default THISTYPE siblings(@Nonnull final JQuerySelectorList selector) { return siblings ().arg (selector); }

@Nonnull
default THISTYPE siblings(@Nonnull final EHTMLElement selector) { return siblings ().arg (selector); }

@Nonnull
default THISTYPE siblings(@Nonnull final ICSSClassProvider selector) { return siblings ().arg (selector); }

@Nonnull
default THISTYPE slice(@Nonnull final IJSExpression start) { return slice ().arg (start); }

@Nonnull
default THISTYPE slice(final int start) { return slice ().arg (start); }

@Nonnull
default THISTYPE slice(final long start) { return slice ().arg (start); }

@Nonnull
default THISTYPE slice(@Nonnull final BigInteger start) { return slice ().arg (start); }

@Nonnull
default THISTYPE slice(@Nonnull IJSExpression start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(int start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(long start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull BigInteger start, @Nonnull IJSExpression end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull IJSExpression start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(int start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(long start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull BigInteger start, int end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull IJSExpression start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(int start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(long start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull BigInteger start, long end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull IJSExpression start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(int start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(long start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE slice(@Nonnull BigInteger start, @Nonnull BigInteger end) { return slice ().arg (start).arg (end); }

@Nonnull
default THISTYPE stop(@Nonnull final IJSExpression clearQueue) { return stop ().arg (clearQueue); }

@Nonnull
default THISTYPE stop(final boolean clearQueue) { return stop ().arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(boolean clearQueue, boolean jumpToEnd) { return stop ().arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull final IJson queue) { return stop ().arg (queue); }

@Nonnull
default THISTYPE stop(@Nonnull final IHCNode queue) { return stop ().arg (queue); }

@Nonnull
default THISTYPE stop(@Nonnull final String queue) { return stop ().arg (queue); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, boolean clearQueue) { return stop ().arg (queue).arg (clearQueue); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IJson queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE stop(@Nonnull String queue, boolean clearQueue, boolean jumpToEnd) { return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd); }

@Nonnull
default THISTYPE submit(@Nonnull final IJSExpression handler) { return submit ().arg (handler); }

@Nonnull
default THISTYPE submit(@Nonnull final JSAnonymousFunction handler) { return submit ().arg (handler); }

@Nonnull
default THISTYPE submit(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler) { return submit ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE submit(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler) { return submit ().arg (eventData).arg (handler); }

@Nonnull
default THISTYPE text(@Nonnull final IJSExpression text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final IJson text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final IHCNode text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final String text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(final int text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(final long text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final BigInteger text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(final double text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final BigDecimal text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(final boolean text) { return text ().arg (text); }

@Nonnull
default THISTYPE text(@Nonnull final JSAnonymousFunction function) { return text ().arg (function); }

@Nonnull
default THISTYPE toggle(@Nonnull final IJSExpression duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(final int duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(final long duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(@Nonnull final BigInteger duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(final double duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(@Nonnull final BigDecimal duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(@Nonnull final IJson duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(@Nonnull final IHCNode duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(@Nonnull final String duration) { return toggle ().arg (duration); }

@Nonnull
default THISTYPE toggle(final boolean display) { return toggle ().arg (display); }

@Nonnull
default THISTYPE toggleClass(@Nonnull final IJSExpression className) { return toggleClass ().arg (className); }

@Nonnull
default THISTYPE toggleClass(@Nonnull final IJson className) { return toggleClass ().arg (className); }

@Nonnull
default THISTYPE toggleClass(@Nonnull final IHCNode className) { return toggleClass ().arg (className); }

@Nonnull
default THISTYPE toggleClass(@Nonnull final String className) { return toggleClass ().arg (className); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IJSExpression className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IJson className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IHCNode className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull String className, @Nonnull IJSExpression state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IJSExpression className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IJson className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull IHCNode className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull String className, boolean state) { return toggleClass ().arg (className).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull final JSAnonymousFunction function) { return toggleClass ().arg (function); }

@Nonnull
default THISTYPE toggleClass(@Nonnull JSAnonymousFunction function, @Nonnull IJSExpression state) { return toggleClass ().arg (function).arg (state); }

@Nonnull
default THISTYPE toggleClass(@Nonnull JSAnonymousFunction function, boolean state) { return toggleClass ().arg (function).arg (state); }

@Nonnull
default THISTYPE trigger(@Nonnull final IJSExpression eventType) { return trigger ().arg (eventType); }

@Nonnull
default THISTYPE trigger(@Nonnull final IJson eventType) { return trigger ().arg (eventType); }

@Nonnull
default THISTYPE trigger(@Nonnull final IHCNode eventType) { return trigger ().arg (eventType); }

@Nonnull
default THISTYPE trigger(@Nonnull final String eventType) { return trigger ().arg (eventType); }

@Nonnull
default THISTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull String eventType, @Nonnull IJSExpression extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull IJson eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE trigger(@Nonnull String eventType, @Nonnull JSArray extraParameters) { return trigger ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull final IJSExpression eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull final IJson eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull final IHCNode eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull final String eventType) { return triggerHandler ().arg (eventType); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull String eventType, @Nonnull IJSExpression extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE triggerHandler(@Nonnull String eventType, @Nonnull JSArray extraParameters) { return triggerHandler ().arg (eventType).arg (extraParameters); }

@Nonnull
default THISTYPE unwrap(@Nonnull final IJSExpression selector) { return unwrap ().arg (selector); }

@Nonnull
default THISTYPE unwrap(@Nonnull final IJson selector) { return unwrap ().arg (selector); }

@Nonnull
default THISTYPE unwrap(@Nonnull final IHCNode selector) { return unwrap ().arg (selector); }

@Nonnull
default THISTYPE unwrap(@Nonnull final String selector) { return unwrap ().arg (selector); }

@Nonnull
default THISTYPE val(@Nonnull final IJSExpression value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final IJson value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final IHCNode value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final String value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(final int value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(final long value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final BigInteger value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(final double value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final BigDecimal value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final JSArray value) { return val ().arg (value); }

@Nonnull
default THISTYPE val(@Nonnull final JSAnonymousFunction function) { return val ().arg (function); }

@Nonnull
default THISTYPE width(@Nonnull final IJSExpression value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final IJson value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final IHCNode value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final String value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(final int value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(final long value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final BigInteger value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(final double value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final BigDecimal value) { return width ().arg (value); }

@Nonnull
default THISTYPE width(@Nonnull final JSAnonymousFunction function) { return width ().arg (function); }

@Nonnull
default THISTYPE wrap(@Nonnull final IJSExpression wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final IJQuerySelector wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final JQuerySelectorList wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final EHTMLElement wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final ICSSClassProvider wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final IHCNode wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final String wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final JQueryInvocation wrappingElement) { return wrap ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrap(@Nonnull final JSAnonymousFunction function) { return wrap ().arg (function); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final IJSExpression wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final IJQuerySelector wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final JQuerySelectorList wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final EHTMLElement wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final ICSSClassProvider wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final IHCNode wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final String wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final JQueryInvocation wrappingElement) { return wrapAll ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapAll(@Nonnull final JSAnonymousFunction function) { return wrapAll ().arg (function); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final IJSExpression wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final IHCNode wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final String wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final IJQuerySelector wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final JQuerySelectorList wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final EHTMLElement wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final ICSSClassProvider wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final JQueryInvocation wrappingElement) { return wrapInner ().arg (wrappingElement); }

@Nonnull
default THISTYPE wrapInner(@Nonnull final JSAnonymousFunction function) { return wrapInner ().arg (function); }

}
