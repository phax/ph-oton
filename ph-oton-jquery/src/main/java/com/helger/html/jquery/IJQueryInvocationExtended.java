/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
 * 
 * @author com.helger.html.jquery.supplementary.main.Main_IJQueryInvocationExtended
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IJQueryInvocationExtended <IMPLTYPE extends IJQueryInvocationExtended <IMPLTYPE>> extends IJQueryInvocation <IMPLTYPE>
{
  @Nonnull
  default IMPLTYPE add (@Nonnull final IJSExpression selector)
  {
    return add ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final IJQuerySelector selector)
  {
    return add ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final JQuerySelectorList selector)
  {
    return add ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final EHTMLElement selector)
  {
    return add ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final ICSSClassProvider selector)
  {
    return add ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final String elements)
  {
    return add ().arg (elements);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final IHCNode html)
  {
    return add ().arg (html);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull final JQueryInvocation selection)
  {
    return add ().arg (selection);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJSExpression selector, @Nonnull IJSExpression context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull EHTMLElement selector, @Nonnull IJSExpression context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJSExpression selector, @Nonnull EHTMLElement context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJSExpression selector, @Nonnull String context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull IJQuerySelector selector, @Nonnull String context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull JQuerySelectorList selector, @Nonnull String context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull EHTMLElement selector, @Nonnull String context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE add (@Nonnull ICSSClassProvider selector, @Nonnull String context)
  {
    return add ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE addBack (@Nonnull final IJSExpression selector)
  {
    return addBack ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE addBack (@Nonnull final IJQuerySelector selector)
  {
    return addBack ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE addBack (@Nonnull final JQuerySelectorList selector)
  {
    return addBack ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE addBack (@Nonnull final EHTMLElement selector)
  {
    return addBack ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE addBack (@Nonnull final ICSSClassProvider selector)
  {
    return addBack ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE addClass (@Nonnull final IJSExpression className)
  {
    return addClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE addClass (@Nonnull final IJson className)
  {
    return addClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE addClass (@Nonnull final IHCNode className)
  {
    return addClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE addClass (@Nonnull final String className)
  {
    return addClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE addClass (@Nonnull final JSAnonymousFunction function)
  {
    return addClass ().arg (function);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final IJSExpression content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final IHCNode content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final String content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final EHTMLElement content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final IJson content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final JSArray content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final JQueryInvocation content)
  {
    return after ().arg (content);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull IHCNode content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull String content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull IJson content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull JSArray content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IHCNode content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull String content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull IJson content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JSArray content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1)
  {
    return after ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE after (@Nonnull final JSAnonymousFunction function)
  {
    return after ().arg (function);
  }

  @Nonnull
  default IMPLTYPE ajaxComplete (@Nonnull final IJSExpression handler)
  {
    return ajaxComplete ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxComplete (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxComplete ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxError (@Nonnull final IJSExpression handler)
  {
    return ajaxError ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxError (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxError ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxSend (@Nonnull final IJSExpression handler)
  {
    return ajaxSend ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxSend (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxSend ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxStart (@Nonnull final IJSExpression handler)
  {
    return ajaxStart ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxStart (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxStart ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxStop (@Nonnull final IJSExpression handler)
  {
    return ajaxStop ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxStop (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxStop ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxSuccess (@Nonnull final IJSExpression handler)
  {
    return ajaxSuccess ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ajaxSuccess (@Nonnull final JSAnonymousFunction handler)
  {
    return ajaxSuccess ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE animate (@Nonnull final IJSExpression properties)
  {
    return animate ().arg (properties);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final IJSExpression content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final IHCNode content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final String content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final EHTMLElement content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final IJson content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final JSArray content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final JQueryInvocation content)
  {
    return append ().arg (content);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull IHCNode content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull String content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull IJson content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull JSArray content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IHCNode content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull String content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull IJson content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JSArray content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1)
  {
    return append ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE append (@Nonnull final JSAnonymousFunction function)
  {
    return append ().arg (function);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final IJSExpression target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final IJQuerySelector target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final JQuerySelectorList target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final EHTMLElement target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final ICSSClassProvider target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final IHCNode target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final String target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final JSArray target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE appendTo (@Nonnull final JQueryInvocation target)
  {
    return appendTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull final IJSExpression attributeName)
  {
    return attr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull final IJson attributeName)
  {
    return attr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull final IHCNode attributeName)
  {
    return attr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull final String attributeName)
  {
    return attr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull final IMicroQName attributeName)
  {
    return attr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull IJSExpression value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull IJSExpression value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull IJSExpression value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull IJSExpression value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull IJSExpression value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull IJson value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull IJson value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull IJson value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull IJson value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull IJson value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull IHCNode value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull IHCNode value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull IHCNode value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull IHCNode value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull IHCNode value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull String value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull String value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull String value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull String value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull String value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, int value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, int value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, int value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, int value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, int value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, long value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, long value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, long value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, long value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, long value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull BigInteger value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull BigInteger value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull BigInteger value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull BigInteger value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull BigInteger value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, double value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, double value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, double value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, double value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, double value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull BigDecimal value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull BigDecimal value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull BigDecimal value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull BigDecimal value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull BigDecimal value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull JQueryInvocation value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull JQueryInvocation value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull JQueryInvocation value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull JQueryInvocation value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull JQueryInvocation value)
  {
    return attr ().arg (attributeName).arg (value);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJSExpression attributeName, @Nonnull JSAnonymousFunction function)
  {
    return attr ().arg (attributeName).arg (function);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IJson attributeName, @Nonnull JSAnonymousFunction function)
  {
    return attr ().arg (attributeName).arg (function);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IHCNode attributeName, @Nonnull JSAnonymousFunction function)
  {
    return attr ().arg (attributeName).arg (function);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull String attributeName, @Nonnull JSAnonymousFunction function)
  {
    return attr ().arg (attributeName).arg (function);
  }

  @Nonnull
  default IMPLTYPE attr (@Nonnull IMicroQName attributeName, @Nonnull JSAnonymousFunction function)
  {
    return attr ().arg (attributeName).arg (function);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final IJSExpression content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final IHCNode content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final String content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final EHTMLElement content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final IJson content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final JSArray content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final JQueryInvocation content)
  {
    return before ().arg (content);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull IHCNode content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull String content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull IJson content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull JSArray content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IHCNode content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull String content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull IJson content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JSArray content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1)
  {
    return before ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE before (@Nonnull final JSAnonymousFunction function)
  {
    return before ().arg (function);
  }

  @Nonnull
  default IMPLTYPE blur (@Nonnull final IJSExpression handler)
  {
    return blur ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE blur (@Nonnull final JSAnonymousFunction handler)
  {
    return blur ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE blur (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return blur ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE blur (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return blur ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE callbacks_add (@Nonnull final IJSExpression callbacks)
  {
    return callbacks_add ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE callbacks_add (@Nonnull final JSAnonymousFunction callbacks)
  {
    return callbacks_add ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE callbacks_add (@Nonnull final JSArray callbacks)
  {
    return callbacks_add ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE callbacks_fire (@Nonnull final IJSExpression arguments)
  {
    return callbacks_fire ().arg (arguments);
  }

  @Nonnull
  default IMPLTYPE callbacks_fireWith (@Nonnull final IJSExpression context)
  {
    return callbacks_fireWith ().arg (context);
  }

  @Nonnull
  default IMPLTYPE callbacks_fireWith (@Nonnull IJSExpression context, @Nonnull IJSExpression args)
  {
    return callbacks_fireWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE callbacks_has (@Nonnull final IJSExpression callback)
  {
    return callbacks_has ().arg (callback);
  }

  @Nonnull
  default IMPLTYPE callbacks_has (@Nonnull final JSAnonymousFunction callback)
  {
    return callbacks_has ().arg (callback);
  }

  @Nonnull
  default IMPLTYPE callbacks_remove (@Nonnull final IJSExpression callbacks)
  {
    return callbacks_remove ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE callbacks_remove (@Nonnull final JSAnonymousFunction callbacks)
  {
    return callbacks_remove ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE callbacks_remove (@Nonnull final JSArray callbacks)
  {
    return callbacks_remove ().arg (callbacks);
  }

  @Nonnull
  default IMPLTYPE change (@Nonnull final IJSExpression handler)
  {
    return change ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE change (@Nonnull final JSAnonymousFunction handler)
  {
    return change ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE change (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return change ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE change (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return change ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE children (@Nonnull final IJSExpression selector)
  {
    return children ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE children (@Nonnull final IJQuerySelector selector)
  {
    return children ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE children (@Nonnull final JQuerySelectorList selector)
  {
    return children ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE children (@Nonnull final EHTMLElement selector)
  {
    return children ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE children (@Nonnull final ICSSClassProvider selector)
  {
    return children ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE clearQueue (@Nonnull final IJSExpression queueName)
  {
    return clearQueue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE clearQueue (@Nonnull final IJson queueName)
  {
    return clearQueue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE clearQueue (@Nonnull final IHCNode queueName)
  {
    return clearQueue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE clearQueue (@Nonnull final String queueName)
  {
    return clearQueue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE click (@Nonnull final IJSExpression handler)
  {
    return click ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE click (@Nonnull final JSAnonymousFunction handler)
  {
    return click ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE click (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return click ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE click (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return click ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE _clone (@Nonnull final IJSExpression withDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE _clone (final boolean withDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE _clone (@Nonnull IJSExpression withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE _clone (boolean withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE _clone (@Nonnull IJSExpression withDataAndEvents, boolean deepWithDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE _clone (boolean withDataAndEvents, boolean deepWithDataAndEvents)
  {
    return _clone ().arg (withDataAndEvents).arg (deepWithDataAndEvents);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final IJSExpression selector)
  {
    return closest ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final IJQuerySelector selector)
  {
    return closest ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final JQuerySelectorList selector)
  {
    return closest ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final EHTMLElement selector)
  {
    return closest ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final ICSSClassProvider selector)
  {
    return closest ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJSExpression selector, @Nonnull IJSExpression context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull EHTMLElement selector, @Nonnull IJSExpression context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJSExpression selector, @Nonnull EHTMLElement context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJSExpression selector, @Nonnull String context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull IJQuerySelector selector, @Nonnull String context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull JQuerySelectorList selector, @Nonnull String context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull EHTMLElement selector, @Nonnull String context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull ICSSClassProvider selector, @Nonnull String context)
  {
    return closest ().arg (selector).arg (context);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final JQueryInvocation selection)
  {
    return closest ().arg (selection);
  }

  @Nonnull
  default IMPLTYPE closest (@Nonnull final String element)
  {
    return closest ().arg (element);
  }

  @Nonnull
  default IMPLTYPE contextmenu (@Nonnull final IJSExpression handler)
  {
    return contextmenu ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE contextmenu (@Nonnull final JSAnonymousFunction handler)
  {
    return contextmenu ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE contextmenu (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return contextmenu ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE contextmenu (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return contextmenu ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull final IJSExpression propertyName)
  {
    return css ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull final IJson propertyName)
  {
    return css ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull final IHCNode propertyName)
  {
    return css ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull final String propertyName)
  {
    return css ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull final JSArray propertyNames)
  {
    return css ().arg (propertyNames);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull IJSExpression value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull IJSExpression value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull IJSExpression value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull IJson value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull IJson value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull IJson value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull IJson value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull IHCNode value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull IHCNode value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull IHCNode value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull IHCNode value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull String value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull String value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull String value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull String value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, int value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, int value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, int value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, int value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, long value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, long value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, long value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, long value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull BigInteger value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull BigInteger value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull BigInteger value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull BigInteger value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, double value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, double value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, double value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, double value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull BigDecimal value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull BigDecimal value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull BigDecimal value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull BigDecimal value)
  {
    return css ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function)
  {
    return css ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function)
  {
    return css ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function)
  {
    return css ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE css (@Nonnull String propertyName, @Nonnull JSAnonymousFunction function)
  {
    return css ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull IJSExpression key, @Nonnull IJSExpression value)
  {
    return data ().arg (key).arg (value);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull IJson key, @Nonnull IJSExpression value)
  {
    return data ().arg (key).arg (value);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull IHCNode key, @Nonnull IJSExpression value)
  {
    return data ().arg (key).arg (value);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull String key, @Nonnull IJSExpression value)
  {
    return data ().arg (key).arg (value);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull final IJSExpression obj)
  {
    return data ().arg (obj);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull final IJson key)
  {
    return data ().arg (key);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull final IHCNode key)
  {
    return data ().arg (key);
  }

  @Nonnull
  default IMPLTYPE data (@Nonnull final String key)
  {
    return data ().arg (key);
  }

  @Nonnull
  default IMPLTYPE dblclick (@Nonnull final IJSExpression handler)
  {
    return dblclick ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE dblclick (@Nonnull final JSAnonymousFunction handler)
  {
    return dblclick ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE dblclick (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return dblclick ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE dblclick (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return dblclick ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull final IJSExpression alwaysCallbacks)
  {
    return deferred_always ().arg (alwaysCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull final JSAnonymousFunction alwaysCallbacks)
  {
    return deferred_always ().arg (alwaysCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull IJSExpression alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1)
  {
    return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1)
  {
    return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull IJSExpression alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1)
  {
    return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_always (@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1)
  {
    return deferred_always ().arg (alwaysCallbacks).arg (alwaysCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull final IJSExpression doneCallbacks)
  {
    return deferred_done ().arg (doneCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull final JSAnonymousFunction doneCallbacks)
  {
    return deferred_done ().arg (doneCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression doneCallbacks1)
  {
    return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression doneCallbacks1)
  {
    return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1)
  {
    return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_done (@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1)
  {
    return deferred_done ().arg (doneCallbacks).arg (doneCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull final IJSExpression failCallbacks)
  {
    return deferred_fail ().arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull final JSAnonymousFunction failCallbacks)
  {
    return deferred_fail ().arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull IJSExpression failCallbacks, @Nonnull IJSExpression failCallbacks1)
  {
    return deferred_fail ().arg (failCallbacks).arg (failCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull JSAnonymousFunction failCallbacks, @Nonnull IJSExpression failCallbacks1)
  {
    return deferred_fail ().arg (failCallbacks).arg (failCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull IJSExpression failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1)
  {
    return deferred_fail ().arg (failCallbacks).arg (failCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_fail (@Nonnull JSAnonymousFunction failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1)
  {
    return deferred_fail ().arg (failCallbacks).arg (failCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_notify (@Nonnull final IJSExpression args)
  {
    return deferred_notify ().arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_notifyWith (@Nonnull final IJSExpression context)
  {
    return deferred_notifyWith ().arg (context);
  }

  @Nonnull
  default IMPLTYPE deferred_notifyWith (@Nonnull IJSExpression context, @Nonnull IJSExpression args)
  {
    return deferred_notifyWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_notifyWith (@Nonnull IJSExpression context, @Nonnull JSArray args)
  {
    return deferred_notifyWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull final IJSExpression progressCallbacks)
  {
    return deferred_progress ().arg (progressCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull final JSAnonymousFunction progressCallbacks)
  {
    return deferred_progress ().arg (progressCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull final JSArray progressCallbacks)
  {
    return deferred_progress ().arg (progressCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull IJSExpression progressCallbacks, @Nonnull IJSExpression progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull IJSExpression progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSArray progressCallbacks, @Nonnull IJSExpression progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull IJSExpression progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSArray progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull IJSExpression progressCallbacks, @Nonnull JSArray progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSArray progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_progress (@Nonnull JSArray progressCallbacks, @Nonnull JSArray progressCallbacks1)
  {
    return deferred_progress ().arg (progressCallbacks).arg (progressCallbacks1);
  }

  @Nonnull
  default IMPLTYPE deferred_promise (@Nonnull final IJSExpression target)
  {
    return deferred_promise ().arg (target);
  }

  @Nonnull
  default IMPLTYPE deferred_reject (@Nonnull final IJSExpression args)
  {
    return deferred_reject ().arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_rejectWith (@Nonnull final IJSExpression context)
  {
    return deferred_rejectWith ().arg (context);
  }

  @Nonnull
  default IMPLTYPE deferred_rejectWith (@Nonnull IJSExpression context, @Nonnull IJSExpression args)
  {
    return deferred_rejectWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_rejectWith (@Nonnull IJSExpression context, @Nonnull JSArray args)
  {
    return deferred_rejectWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_resolve (@Nonnull final IJSExpression args)
  {
    return deferred_resolve ().arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_resolveWith (@Nonnull final IJSExpression context)
  {
    return deferred_resolveWith ().arg (context);
  }

  @Nonnull
  default IMPLTYPE deferred_resolveWith (@Nonnull IJSExpression context, @Nonnull IJSExpression args)
  {
    return deferred_resolveWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_resolveWith (@Nonnull IJSExpression context, @Nonnull JSArray args)
  {
    return deferred_resolveWith ().arg (context).arg (args);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull final IJSExpression doneFilter)
  {
    return deferred_then ().arg (doneFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull final JSAnonymousFunction doneFilter)
  {
    return deferred_then ().arg (doneFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneFilter,
                                  @Nonnull IJSExpression failFilter,
                                  @Nonnull IJSExpression progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneFilter,
                                  @Nonnull IJSExpression failFilter,
                                  @Nonnull IJSExpression progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneFilter,
                                  @Nonnull JSAnonymousFunction failFilter,
                                  @Nonnull IJSExpression progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneFilter,
                                  @Nonnull JSAnonymousFunction failFilter,
                                  @Nonnull IJSExpression progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneFilter,
                                  @Nonnull IJSExpression failFilter,
                                  @Nonnull JSAnonymousFunction progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneFilter,
                                  @Nonnull IJSExpression failFilter,
                                  @Nonnull JSAnonymousFunction progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneFilter,
                                  @Nonnull JSAnonymousFunction failFilter,
                                  @Nonnull JSAnonymousFunction progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneFilter,
                                  @Nonnull JSAnonymousFunction failFilter,
                                  @Nonnull JSAnonymousFunction progressFilter)
  {
    return deferred_then ().arg (doneFilter).arg (failFilter).arg (progressFilter);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression failCallbacks)
  {
    return deferred_then ().arg (doneCallbacks).arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression failCallbacks)
  {
    return deferred_then ().arg (doneCallbacks).arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks)
  {
    return deferred_then ().arg (doneCallbacks).arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE deferred_then (@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks)
  {
    return deferred_then ().arg (doneCallbacks).arg (failCallbacks);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull final IJSExpression duration)
  {
    return delay ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE delay (final int duration)
  {
    return delay ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE delay (final long duration)
  {
    return delay ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull final BigInteger duration)
  {
    return delay ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull IJSExpression duration, @Nonnull IJSExpression queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (int duration, @Nonnull IJSExpression queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (long duration, @Nonnull IJSExpression queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull BigInteger duration, @Nonnull IJSExpression queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull IJSExpression duration, @Nonnull IJson queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (int duration, @Nonnull IJson queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (long duration, @Nonnull IJson queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull BigInteger duration, @Nonnull IJson queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull IJSExpression duration, @Nonnull IHCNode queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (int duration, @Nonnull IHCNode queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (long duration, @Nonnull IHCNode queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull BigInteger duration, @Nonnull IHCNode queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull IJSExpression duration, @Nonnull String queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (int duration, @Nonnull String queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (long duration, @Nonnull String queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE delay (@Nonnull BigInteger duration, @Nonnull String queueName)
  {
    return delay ().arg (duration).arg (queueName);
  }

  @Nonnull
  default IMPLTYPE dequeue (@Nonnull final IJSExpression queueName)
  {
    return dequeue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE dequeue (@Nonnull final IJson queueName)
  {
    return dequeue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE dequeue (@Nonnull final IHCNode queueName)
  {
    return dequeue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE dequeue (@Nonnull final String queueName)
  {
    return dequeue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE detach (@Nonnull final IJSExpression selector)
  {
    return detach ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE detach (@Nonnull final IJQuerySelector selector)
  {
    return detach ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE detach (@Nonnull final JQuerySelectorList selector)
  {
    return detach ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE detach (@Nonnull final EHTMLElement selector)
  {
    return detach ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE detach (@Nonnull final ICSSClassProvider selector)
  {
    return detach ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE each (@Nonnull final IJSExpression function)
  {
    return each ().arg (function);
  }

  @Nonnull
  default IMPLTYPE each (@Nonnull final JSAnonymousFunction function)
  {
    return each ().arg (function);
  }

  @Nonnull
  default IMPLTYPE _eq (@Nonnull final IJSExpression index)
  {
    return _eq ().arg (index);
  }

  @Nonnull
  default IMPLTYPE _eq (final int index)
  {
    return _eq ().arg (index);
  }

  @Nonnull
  default IMPLTYPE _eq (final long index)
  {
    return _eq ().arg (index);
  }

  @Nonnull
  default IMPLTYPE _eq (@Nonnull final BigInteger index)
  {
    return _eq ().arg (index);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity)
  {
    return fadeTo ().arg (duration).arg (opacity);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull IJSExpression complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           int opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           long opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           double opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           double opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           double opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJSExpression easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IJson easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull IHCNode easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull IJSExpression opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigInteger opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJSExpression duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IJson duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull IHCNode duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull String duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigInteger duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE fadeTo (@Nonnull BigDecimal duration,
                           @Nonnull BigDecimal opacity,
                           @Nonnull String easing,
                           @Nonnull JSAnonymousFunction complete)
  {
    return fadeTo ().arg (duration).arg (opacity).arg (easing).arg (complete);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final IJSExpression selector)
  {
    return filter ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final IJQuerySelector selector)
  {
    return filter ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final JQuerySelectorList selector)
  {
    return filter ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final EHTMLElement selector)
  {
    return filter ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final ICSSClassProvider selector)
  {
    return filter ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final JSAnonymousFunction function)
  {
    return filter ().arg (function);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final String elements)
  {
    return filter ().arg (elements);
  }

  @Nonnull
  default IMPLTYPE filter (@Nonnull final JQueryInvocation selection)
  {
    return filter ().arg (selection);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final IJSExpression selector)
  {
    return find ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final IJQuerySelector selector)
  {
    return find ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final JQuerySelectorList selector)
  {
    return find ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final EHTMLElement selector)
  {
    return find ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final ICSSClassProvider selector)
  {
    return find ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final String element)
  {
    return find ().arg (element);
  }

  @Nonnull
  default IMPLTYPE find (@Nonnull final JQueryInvocation element)
  {
    return find ().arg (element);
  }

  @Nonnull
  default IMPLTYPE finish (@Nonnull final IJSExpression queue)
  {
    return finish ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE finish (@Nonnull final IJson queue)
  {
    return finish ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE finish (@Nonnull final IHCNode queue)
  {
    return finish ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE finish (@Nonnull final String queue)
  {
    return finish ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE focus (@Nonnull final IJSExpression handler)
  {
    return focus ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focus (@Nonnull final JSAnonymousFunction handler)
  {
    return focus ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focus (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return focus ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE focus (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return focus ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusin (@Nonnull final IJSExpression handler)
  {
    return focusin ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusin (@Nonnull final JSAnonymousFunction handler)
  {
    return focusin ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusin (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return focusin ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusin (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return focusin ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusout (@Nonnull final IJSExpression handler)
  {
    return focusout ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusout (@Nonnull final JSAnonymousFunction handler)
  {
    return focusout ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusout (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return focusout ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE focusout (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return focusout ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE get (@Nonnull final IJSExpression index)
  {
    return get ().arg (index);
  }

  @Nonnull
  default IMPLTYPE get (final int index)
  {
    return get ().arg (index);
  }

  @Nonnull
  default IMPLTYPE get (final long index)
  {
    return get ().arg (index);
  }

  @Nonnull
  default IMPLTYPE get (@Nonnull final BigInteger index)
  {
    return get ().arg (index);
  }

  @Nonnull
  default IMPLTYPE has (@Nonnull final IJSExpression selector)
  {
    return has ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE has (@Nonnull final IJson selector)
  {
    return has ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE has (@Nonnull final IHCNode selector)
  {
    return has ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE has (@Nonnull final String selector)
  {
    return has ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE has (@Nonnull final EHTMLElement contained)
  {
    return has ().arg (contained);
  }

  @Nonnull
  default IMPLTYPE hasClass (@Nonnull final IJSExpression className)
  {
    return hasClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE hasClass (@Nonnull final IJson className)
  {
    return hasClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE hasClass (@Nonnull final IHCNode className)
  {
    return hasClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE hasClass (@Nonnull final String className)
  {
    return hasClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final IJSExpression value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final IJson value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final IHCNode value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final String value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (final int value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (final long value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final BigInteger value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (final double value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final BigDecimal value)
  {
    return height ().arg (value);
  }

  @Nonnull
  default IMPLTYPE height (@Nonnull final JSAnonymousFunction function)
  {
    return height ().arg (function);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final IJSExpression duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (final int duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (final long duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final BigInteger duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (final double duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final BigDecimal duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final IJson duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final IHCNode duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hide (@Nonnull final String duration)
  {
    return hide ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull IJSExpression handlerIn, @Nonnull IJSExpression handlerOut)
  {
    return hover ().arg (handlerIn).arg (handlerOut);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull JSAnonymousFunction handlerIn, @Nonnull IJSExpression handlerOut)
  {
    return hover ().arg (handlerIn).arg (handlerOut);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull IJSExpression handlerIn, @Nonnull JSAnonymousFunction handlerOut)
  {
    return hover ().arg (handlerIn).arg (handlerOut);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull JSAnonymousFunction handlerIn, @Nonnull JSAnonymousFunction handlerOut)
  {
    return hover ().arg (handlerIn).arg (handlerOut);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull final IJSExpression handlerInOut)
  {
    return hover ().arg (handlerInOut);
  }

  @Nonnull
  default IMPLTYPE hover (@Nonnull final JSAnonymousFunction handlerInOut)
  {
    return hover ().arg (handlerInOut);
  }

  @Nonnull
  default IMPLTYPE html (@Nonnull final IJSExpression htmlString)
  {
    return html ().arg (htmlString);
  }

  @Nonnull
  default IMPLTYPE html (@Nonnull final IHCNode htmlString)
  {
    return html ().arg (htmlString);
  }

  @Nonnull
  default IMPLTYPE html (@Nonnull final String htmlString)
  {
    return html ().arg (htmlString);
  }

  @Nonnull
  default IMPLTYPE html (@Nonnull final JSAnonymousFunction function)
  {
    return html ().arg (function);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final IJSExpression selector)
  {
    return index ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final IJQuerySelector selector)
  {
    return index ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final JQuerySelectorList selector)
  {
    return index ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final EHTMLElement selector)
  {
    return index ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final ICSSClassProvider selector)
  {
    return index ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final String element)
  {
    return index ().arg (element);
  }

  @Nonnull
  default IMPLTYPE index (@Nonnull final JQueryInvocation element)
  {
    return index ().arg (element);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final IJSExpression value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final IJson value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final IHCNode value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final String value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (final int value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (final long value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final BigInteger value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (final double value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final BigDecimal value)
  {
    return innerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerHeight (@Nonnull final JSAnonymousFunction function)
  {
    return innerHeight ().arg (function);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final IJSExpression value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final IJson value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final IHCNode value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final String value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (final int value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (final long value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final BigInteger value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (final double value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final BigDecimal value)
  {
    return innerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE innerWidth (@Nonnull final JSAnonymousFunction function)
  {
    return innerWidth ().arg (function);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final IJSExpression target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final IJQuerySelector target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final JQuerySelectorList target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final EHTMLElement target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final ICSSClassProvider target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final IHCNode target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final String target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final JSArray target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertAfter (@Nonnull final JQueryInvocation target)
  {
    return insertAfter ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final IJSExpression target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final IJQuerySelector target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final JQuerySelectorList target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final EHTMLElement target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final ICSSClassProvider target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final IHCNode target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final String target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final JSArray target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE insertBefore (@Nonnull final JQueryInvocation target)
  {
    return insertBefore ().arg (target);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final IJSExpression selector)
  {
    return is ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final IJQuerySelector selector)
  {
    return is ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final JQuerySelectorList selector)
  {
    return is ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final EHTMLElement selector)
  {
    return is ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final ICSSClassProvider selector)
  {
    return is ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final JSAnonymousFunction function)
  {
    return is ().arg (function);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final JQueryInvocation selection)
  {
    return is ().arg (selection);
  }

  @Nonnull
  default IMPLTYPE is (@Nonnull final String elements)
  {
    return is ().arg (elements);
  }

  @Nonnull
  default IMPLTYPE keydown (@Nonnull final IJSExpression handler)
  {
    return keydown ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keydown (@Nonnull final JSAnonymousFunction handler)
  {
    return keydown ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keydown (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return keydown ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE keydown (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return keydown ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE keypress (@Nonnull final IJSExpression handler)
  {
    return keypress ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keypress (@Nonnull final JSAnonymousFunction handler)
  {
    return keypress ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keypress (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return keypress ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE keypress (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return keypress ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE keyup (@Nonnull final IJSExpression handler)
  {
    return keyup ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keyup (@Nonnull final JSAnonymousFunction handler)
  {
    return keyup ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE keyup (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return keyup ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE keyup (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return keyup ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull final IJSExpression url)
  {
    return load ().arg (url);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull final IJson url)
  {
    return load ().arg (url);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull final IHCNode url)
  {
    return load ().arg (url);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull final String url)
  {
    return load ().arg (url);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJSExpression data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJSExpression data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJSExpression data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJSExpression data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJson data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJson data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJson data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJson data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IHCNode data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IHCNode data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IHCNode data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IHCNode data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull String data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull String data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull String data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull String data)
  {
    return load ().arg (url).arg (data);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJson data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJson data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IHCNode data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull String data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull String data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull String data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull String data, @Nonnull IJSExpression complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJSExpression url, @Nonnull String data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IJson url, @Nonnull String data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull IHCNode url, @Nonnull String data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE load (@Nonnull String url, @Nonnull String data, @Nonnull JSAnonymousFunction complete)
  {
    return load ().arg (url).arg (data).arg (complete);
  }

  @Nonnull
  default IMPLTYPE map (@Nonnull final IJSExpression callback)
  {
    return map ().arg (callback);
  }

  @Nonnull
  default IMPLTYPE map (@Nonnull final JSAnonymousFunction callback)
  {
    return map ().arg (callback);
  }

  @Nonnull
  default IMPLTYPE mousedown (@Nonnull final IJSExpression handler)
  {
    return mousedown ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousedown (@Nonnull final JSAnonymousFunction handler)
  {
    return mousedown ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousedown (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mousedown ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousedown (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mousedown ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseenter (@Nonnull final IJSExpression handler)
  {
    return mouseenter ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseenter (@Nonnull final JSAnonymousFunction handler)
  {
    return mouseenter ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseenter (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mouseenter ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseenter (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mouseenter ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseleave (@Nonnull final IJSExpression handler)
  {
    return mouseleave ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseleave (@Nonnull final JSAnonymousFunction handler)
  {
    return mouseleave ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseleave (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mouseleave ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseleave (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mouseleave ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousemove (@Nonnull final IJSExpression handler)
  {
    return mousemove ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousemove (@Nonnull final JSAnonymousFunction handler)
  {
    return mousemove ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousemove (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mousemove ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mousemove (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mousemove ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseout (@Nonnull final IJSExpression handler)
  {
    return mouseout ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseout (@Nonnull final JSAnonymousFunction handler)
  {
    return mouseout ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseout (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mouseout ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseout (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mouseout ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseover (@Nonnull final IJSExpression handler)
  {
    return mouseover ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseover (@Nonnull final JSAnonymousFunction handler)
  {
    return mouseover ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseover (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mouseover ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseover (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mouseover ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseup (@Nonnull final IJSExpression handler)
  {
    return mouseup ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseup (@Nonnull final JSAnonymousFunction handler)
  {
    return mouseup ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseup (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return mouseup ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE mouseup (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return mouseup ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE next (@Nonnull final IJSExpression selector)
  {
    return next ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE next (@Nonnull final IJQuerySelector selector)
  {
    return next ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE next (@Nonnull final JQuerySelectorList selector)
  {
    return next ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE next (@Nonnull final EHTMLElement selector)
  {
    return next ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE next (@Nonnull final ICSSClassProvider selector)
  {
    return next ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextAll (@Nonnull final IJSExpression selector)
  {
    return nextAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextAll (@Nonnull final IJson selector)
  {
    return nextAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextAll (@Nonnull final IHCNode selector)
  {
    return nextAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextAll (@Nonnull final String selector)
  {
    return nextAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final IJSExpression selector)
  {
    return nextUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final IJQuerySelector selector)
  {
    return nextUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final JQuerySelectorList selector)
  {
    return nextUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final EHTMLElement selector)
  {
    return nextUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final ICSSClassProvider selector)
  {
    return nextUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJSExpression selector, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final String element)
  {
    return nextUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull final JQueryInvocation element)
  {
    return nextUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull String element, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull String element, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull String element, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull String element, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull String element, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE nextUntil (@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter)
  {
    return nextUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final IJSExpression selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final IJQuerySelector selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final JQuerySelectorList selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final EHTMLElement selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final ICSSClassProvider selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final String selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final JSArray selector)
  {
    return _not ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final JSAnonymousFunction function)
  {
    return _not ().arg (function);
  }

  @Nonnull
  default IMPLTYPE _not (@Nonnull final JQueryInvocation selection)
  {
    return _not ().arg (selection);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull final IJSExpression events)
  {
    return off ().arg (events);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull final IJson events)
  {
    return off ().arg (events);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull final IHCNode events)
  {
    return off ().arg (events);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull final String events)
  {
    return off ().arg (events);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJSExpression selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJSExpression selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJSExpression selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJSExpression selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJson selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJson selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJson selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJson selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IHCNode selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IHCNode selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IHCNode selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IHCNode selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull String selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull String selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull String selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull String selector)
  {
    return off ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IJson events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE off (@Nonnull String events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler)
  {
    return off ().arg (events).arg (selector).arg (handler);
  }

  @Nonnull
  default IMPLTYPE offset (@Nonnull final IJSExpression coordinates)
  {
    return offset ().arg (coordinates);
  }

  @Nonnull
  default IMPLTYPE offset (@Nonnull final JSAnonymousFunction function)
  {
    return offset ().arg (function);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IJSExpression selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJSExpression selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJSExpression selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJSExpression selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IJson selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJson selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJson selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJson selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IHCNode selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IHCNode selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IHCNode selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IHCNode selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull String selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull String selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull String selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull String selector)
  {
    return on ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return on ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull IHCNode selector,
                       @Nonnull IJSExpression data,
                       @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events,
                       @Nonnull IJSExpression selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull IJson selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull IHCNode selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events,
                       @Nonnull IHCNode selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJSExpression events,
                       @Nonnull String selector,
                       @Nonnull IJSExpression data,
                       @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return on ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE on (@Nonnull final IJSExpression events)
  {
    return on ().arg (events);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJson selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJson selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJson selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJson selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IHCNode selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IHCNode selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IHCNode selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IHCNode selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull String selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull String selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull String selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull String selector)
  {
    return one ().arg (events).arg (selector);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data)
  {
    return one ().arg (events).arg (selector).arg (data);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull IHCNode selector,
                        @Nonnull IJSExpression data,
                        @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull String selector,
                        @Nonnull IJSExpression data,
                        @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events,
                        @Nonnull IJSExpression selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull IJson selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull IHCNode selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events,
                        @Nonnull IHCNode selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events,
                        @Nonnull IHCNode selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJSExpression events,
                        @Nonnull String selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull IHCNode events,
                        @Nonnull String selector,
                        @Nonnull IJSExpression data,
                        @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler)
  {
    return one ().arg (events).arg (selector).arg (data).arg (handler);
  }

  @Nonnull
  default IMPLTYPE one (@Nonnull final IJSExpression events)
  {
    return one ().arg (events);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final IJSExpression includeMargin)
  {
    return outerHeight ().arg (includeMargin);
  }

  @Nonnull
  default IMPLTYPE outerHeight (final boolean includeMargin)
  {
    return outerHeight ().arg (includeMargin);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final IJson value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final IHCNode value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final String value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (final int value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (final long value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final BigInteger value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (final double value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final BigDecimal value)
  {
    return outerHeight ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerHeight (@Nonnull final JSAnonymousFunction function)
  {
    return outerHeight ().arg (function);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final IJSExpression includeMargin)
  {
    return outerWidth ().arg (includeMargin);
  }

  @Nonnull
  default IMPLTYPE outerWidth (final boolean includeMargin)
  {
    return outerWidth ().arg (includeMargin);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final IJson value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final IHCNode value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final String value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (final int value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (final long value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final BigInteger value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (final double value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final BigDecimal value)
  {
    return outerWidth ().arg (value);
  }

  @Nonnull
  default IMPLTYPE outerWidth (@Nonnull final JSAnonymousFunction function)
  {
    return outerWidth ().arg (function);
  }

  @Nonnull
  default IMPLTYPE parent (@Nonnull final IJSExpression selector)
  {
    return parent ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parent (@Nonnull final IJQuerySelector selector)
  {
    return parent ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parent (@Nonnull final JQuerySelectorList selector)
  {
    return parent ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parent (@Nonnull final EHTMLElement selector)
  {
    return parent ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parent (@Nonnull final ICSSClassProvider selector)
  {
    return parent ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parents (@Nonnull final IJSExpression selector)
  {
    return parents ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parents (@Nonnull final IJQuerySelector selector)
  {
    return parents ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parents (@Nonnull final JQuerySelectorList selector)
  {
    return parents ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parents (@Nonnull final EHTMLElement selector)
  {
    return parents ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parents (@Nonnull final ICSSClassProvider selector)
  {
    return parents ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final IJSExpression selector)
  {
    return parentsUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final IJQuerySelector selector)
  {
    return parentsUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final JQuerySelectorList selector)
  {
    return parentsUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final EHTMLElement selector)
  {
    return parentsUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final ICSSClassProvider selector)
  {
    return parentsUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJSExpression selector, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final String element)
  {
    return parentsUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull final JQueryInvocation element)
  {
    return parentsUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull String element, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull String element, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull String element, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull String element, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull String element, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE parentsUntil (@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter)
  {
    return parentsUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final IJSExpression content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final IHCNode content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final String content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final EHTMLElement content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final IJson content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final JSArray content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final JQueryInvocation content)
  {
    return prepend ().arg (content);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull IHCNode content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull String content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull IJson content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull JSArray content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IHCNode content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull String content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull IJson content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JSArray content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1)
  {
    return prepend ().arg (content).arg (content1);
  }

  @Nonnull
  default IMPLTYPE prepend (@Nonnull final JSAnonymousFunction function)
  {
    return prepend ().arg (function);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final IJSExpression target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final IJQuerySelector target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final JQuerySelectorList target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final EHTMLElement target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final ICSSClassProvider target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final IHCNode target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final String target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final JSArray target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prependTo (@Nonnull final JQueryInvocation target)
  {
    return prependTo ().arg (target);
  }

  @Nonnull
  default IMPLTYPE prev (@Nonnull final IJSExpression selector)
  {
    return prev ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prev (@Nonnull final IJQuerySelector selector)
  {
    return prev ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prev (@Nonnull final JQuerySelectorList selector)
  {
    return prev ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prev (@Nonnull final EHTMLElement selector)
  {
    return prev ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prev (@Nonnull final ICSSClassProvider selector)
  {
    return prev ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevAll (@Nonnull final IJSExpression selector)
  {
    return prevAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevAll (@Nonnull final IJQuerySelector selector)
  {
    return prevAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevAll (@Nonnull final JQuerySelectorList selector)
  {
    return prevAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevAll (@Nonnull final EHTMLElement selector)
  {
    return prevAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevAll (@Nonnull final ICSSClassProvider selector)
  {
    return prevAll ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final IJSExpression selector)
  {
    return prevUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final IJQuerySelector selector)
  {
    return prevUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final JQuerySelectorList selector)
  {
    return prevUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final EHTMLElement selector)
  {
    return prevUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final ICSSClassProvider selector)
  {
    return prevUntil ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJSExpression selector, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (selector).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final String element)
  {
    return prevUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull final JQueryInvocation element)
  {
    return prevUntil ().arg (element);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull String element, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull String element, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull String element, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull String element, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull String element, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE prevUntil (@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter)
  {
    return prevUntil ().arg (element).arg (filter);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull final IJSExpression type)
  {
    return promise ().arg (type);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull final IJson type)
  {
    return promise ().arg (type);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull final IHCNode type)
  {
    return promise ().arg (type);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull final String type)
  {
    return promise ().arg (type);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull IJSExpression type, @Nonnull IJSExpression target)
  {
    return promise ().arg (type).arg (target);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull IJson type, @Nonnull IJSExpression target)
  {
    return promise ().arg (type).arg (target);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull IHCNode type, @Nonnull IJSExpression target)
  {
    return promise ().arg (type).arg (target);
  }

  @Nonnull
  default IMPLTYPE promise (@Nonnull String type, @Nonnull IJSExpression target)
  {
    return promise ().arg (type).arg (target);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull final IJSExpression propertyName)
  {
    return prop ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull final IJson propertyName)
  {
    return prop ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull final IHCNode propertyName)
  {
    return prop ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull final String propertyName)
  {
    return prop ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value)
  {
    return prop ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IJson propertyName, @Nonnull IJSExpression value)
  {
    return prop ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IHCNode propertyName, @Nonnull IJSExpression value)
  {
    return prop ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull String propertyName, @Nonnull IJSExpression value)
  {
    return prop ().arg (propertyName).arg (value);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function)
  {
    return prop ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function)
  {
    return prop ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function)
  {
    return prop ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE prop (@Nonnull String propertyName, @Nonnull JSAnonymousFunction function)
  {
    return prop ().arg (propertyName).arg (function);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull final IJSExpression elements)
  {
    return pushStack ().arg (elements);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull final JSArray elements)
  {
    return pushStack ().arg (elements);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull String name, @Nonnull IJSExpression arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE pushStack (@Nonnull JSArray elements, @Nonnull String name, @Nonnull JSArray arguments)
  {
    return pushStack ().arg (elements).arg (name).arg (arguments);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull final IJSExpression queueName)
  {
    return queue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull final IJson queueName)
  {
    return queue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull final IHCNode queueName)
  {
    return queue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull final String queueName)
  {
    return queue ().arg (queueName);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJSExpression queueName, @Nonnull IJSExpression newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJson queueName, @Nonnull IJSExpression newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IHCNode queueName, @Nonnull IJSExpression newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull String queueName, @Nonnull IJSExpression newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJSExpression queueName, @Nonnull JSArray newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJson queueName, @Nonnull JSArray newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IHCNode queueName, @Nonnull JSArray newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull String queueName, @Nonnull JSArray newQueue)
  {
    return queue ().arg (queueName).arg (newQueue);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJSExpression queueName, @Nonnull JSAnonymousFunction callback)
  {
    return queue ().arg (queueName).arg (callback);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IJson queueName, @Nonnull JSAnonymousFunction callback)
  {
    return queue ().arg (queueName).arg (callback);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull IHCNode queueName, @Nonnull JSAnonymousFunction callback)
  {
    return queue ().arg (queueName).arg (callback);
  }

  @Nonnull
  default IMPLTYPE queue (@Nonnull String queueName, @Nonnull JSAnonymousFunction callback)
  {
    return queue ().arg (queueName).arg (callback);
  }

  @Nonnull
  default IMPLTYPE ready (@Nonnull final IJSExpression handler)
  {
    return ready ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE ready (@Nonnull final JSAnonymousFunction handler)
  {
    return ready ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE remove (@Nonnull final IJSExpression selector)
  {
    return remove ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE remove (@Nonnull final IJson selector)
  {
    return remove ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE remove (@Nonnull final IHCNode selector)
  {
    return remove ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE remove (@Nonnull final String selector)
  {
    return remove ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE removeAttr (@Nonnull final IJSExpression attributeName)
  {
    return removeAttr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE removeAttr (@Nonnull final IJson attributeName)
  {
    return removeAttr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE removeAttr (@Nonnull final IHCNode attributeName)
  {
    return removeAttr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE removeAttr (@Nonnull final String attributeName)
  {
    return removeAttr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE removeAttr (@Nonnull final IMicroQName attributeName)
  {
    return removeAttr ().arg (attributeName);
  }

  @Nonnull
  default IMPLTYPE removeClass (@Nonnull final IJSExpression className)
  {
    return removeClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE removeClass (@Nonnull final IJson className)
  {
    return removeClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE removeClass (@Nonnull final IHCNode className)
  {
    return removeClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE removeClass (@Nonnull final String className)
  {
    return removeClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE removeClass (@Nonnull final JSAnonymousFunction function)
  {
    return removeClass ().arg (function);
  }

  @Nonnull
  default IMPLTYPE removeData (@Nonnull final IJSExpression name)
  {
    return removeData ().arg (name);
  }

  @Nonnull
  default IMPLTYPE removeData (@Nonnull final IJson name)
  {
    return removeData ().arg (name);
  }

  @Nonnull
  default IMPLTYPE removeData (@Nonnull final IHCNode name)
  {
    return removeData ().arg (name);
  }

  @Nonnull
  default IMPLTYPE removeData (@Nonnull final String name)
  {
    return removeData ().arg (name);
  }

  @Nonnull
  default IMPLTYPE removeData (@Nonnull final JSArray list)
  {
    return removeData ().arg (list);
  }

  @Nonnull
  default IMPLTYPE removeProp (@Nonnull final IJSExpression propertyName)
  {
    return removeProp ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE removeProp (@Nonnull final IJson propertyName)
  {
    return removeProp ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE removeProp (@Nonnull final IHCNode propertyName)
  {
    return removeProp ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE removeProp (@Nonnull final String propertyName)
  {
    return removeProp ().arg (propertyName);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final IJSExpression target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final IJQuerySelector target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final JQuerySelectorList target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final EHTMLElement target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final ICSSClassProvider target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final JQueryInvocation target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final JSArray target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceAll (@Nonnull final String target)
  {
    return replaceAll ().arg (target);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final IJSExpression newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final IHCNode newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final String newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final EHTMLElement newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final JSArray newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final JQueryInvocation newContent)
  {
    return replaceWith ().arg (newContent);
  }

  @Nonnull
  default IMPLTYPE replaceWith (@Nonnull final JSAnonymousFunction function)
  {
    return replaceWith ().arg (function);
  }

  @Nonnull
  default IMPLTYPE resize (@Nonnull final IJSExpression handler)
  {
    return resize ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE resize (@Nonnull final JSAnonymousFunction handler)
  {
    return resize ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE resize (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return resize ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE resize (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return resize ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE scroll (@Nonnull final IJSExpression handler)
  {
    return scroll ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE scroll (@Nonnull final JSAnonymousFunction handler)
  {
    return scroll ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE scroll (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return scroll ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE scroll (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return scroll ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (@Nonnull final IJSExpression value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (final int value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (final long value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (@Nonnull final BigInteger value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (final double value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollLeft (@Nonnull final BigDecimal value)
  {
    return scrollLeft ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (@Nonnull final IJSExpression value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (final int value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (final long value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (@Nonnull final BigInteger value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (final double value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE scrollTop (@Nonnull final BigDecimal value)
  {
    return scrollTop ().arg (value);
  }

  @Nonnull
  default IMPLTYPE select (@Nonnull final IJSExpression handler)
  {
    return select ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE select (@Nonnull final JSAnonymousFunction handler)
  {
    return select ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE select (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return select ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE select (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return select ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final IJSExpression duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (final int duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (final long duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final BigInteger duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (final double duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final BigDecimal duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final IJson duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final IHCNode duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE show (@Nonnull final String duration)
  {
    return show ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE siblings (@Nonnull final IJSExpression selector)
  {
    return siblings ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE siblings (@Nonnull final IJQuerySelector selector)
  {
    return siblings ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE siblings (@Nonnull final JQuerySelectorList selector)
  {
    return siblings ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE siblings (@Nonnull final EHTMLElement selector)
  {
    return siblings ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE siblings (@Nonnull final ICSSClassProvider selector)
  {
    return siblings ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull final IJSExpression start)
  {
    return slice ().arg (start);
  }

  @Nonnull
  default IMPLTYPE slice (final int start)
  {
    return slice ().arg (start);
  }

  @Nonnull
  default IMPLTYPE slice (final long start)
  {
    return slice ().arg (start);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull final BigInteger start)
  {
    return slice ().arg (start);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull IJSExpression start, @Nonnull IJSExpression end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (int start, @Nonnull IJSExpression end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (long start, @Nonnull IJSExpression end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull BigInteger start, @Nonnull IJSExpression end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull IJSExpression start, int end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (int start, int end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (long start, int end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull BigInteger start, int end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull IJSExpression start, long end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (int start, long end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (long start, long end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull BigInteger start, long end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull IJSExpression start, @Nonnull BigInteger end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (int start, @Nonnull BigInteger end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (long start, @Nonnull BigInteger end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE slice (@Nonnull BigInteger start, @Nonnull BigInteger end)
  {
    return slice ().arg (start).arg (end);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull final IJSExpression clearQueue)
  {
    return stop ().arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (final boolean clearQueue)
  {
    return stop ().arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (boolean clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (boolean clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull final IJson queue)
  {
    return stop ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull final IHCNode queue)
  {
    return stop ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull final String queue)
  {
    return stop ().arg (queue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, @Nonnull IJSExpression clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, @Nonnull IJSExpression clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, boolean clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, boolean clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, boolean clearQueue)
  {
    return stop ().arg (queue).arg (clearQueue);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJSExpression queue, boolean clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IJson queue, boolean clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull IHCNode queue, boolean clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE stop (@Nonnull String queue, boolean clearQueue, boolean jumpToEnd)
  {
    return stop ().arg (queue).arg (clearQueue).arg (jumpToEnd);
  }

  @Nonnull
  default IMPLTYPE submit (@Nonnull final IJSExpression handler)
  {
    return submit ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE submit (@Nonnull final JSAnonymousFunction handler)
  {
    return submit ().arg (handler);
  }

  @Nonnull
  default IMPLTYPE submit (@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler)
  {
    return submit ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE submit (@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler)
  {
    return submit ().arg (eventData).arg (handler);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final IJSExpression text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final IJson text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final IHCNode text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final String text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (final int text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (final long text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final BigInteger text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (final double text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final BigDecimal text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (final boolean text)
  {
    return text ().arg (text);
  }

  @Nonnull
  default IMPLTYPE text (@Nonnull final JSAnonymousFunction function)
  {
    return text ().arg (function);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final IJSExpression duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (final int duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (final long duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final BigInteger duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (final double duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final BigDecimal duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final IJson duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final IHCNode duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (@Nonnull final String duration)
  {
    return toggle ().arg (duration);
  }

  @Nonnull
  default IMPLTYPE toggle (final boolean display)
  {
    return toggle ().arg (display);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull final IJSExpression className)
  {
    return toggleClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull final IJson className)
  {
    return toggleClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull final IHCNode className)
  {
    return toggleClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull final String className)
  {
    return toggleClass ().arg (className);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IJSExpression className, @Nonnull IJSExpression state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IJson className, @Nonnull IJSExpression state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IHCNode className, @Nonnull IJSExpression state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull String className, @Nonnull IJSExpression state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IJSExpression className, boolean state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IJson className, boolean state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull IHCNode className, boolean state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull String className, boolean state)
  {
    return toggleClass ().arg (className).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull final JSAnonymousFunction function)
  {
    return toggleClass ().arg (function);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull JSAnonymousFunction function, @Nonnull IJSExpression state)
  {
    return toggleClass ().arg (function).arg (state);
  }

  @Nonnull
  default IMPLTYPE toggleClass (@Nonnull JSAnonymousFunction function, boolean state)
  {
    return toggleClass ().arg (function).arg (state);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull final IJSExpression eventType)
  {
    return trigger ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull final IJson eventType)
  {
    return trigger ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull final IHCNode eventType)
  {
    return trigger ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull final String eventType)
  {
    return trigger ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull String eventType, @Nonnull IJSExpression extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IJson eventType, @Nonnull JSArray extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE trigger (@Nonnull String eventType, @Nonnull JSArray extraParameters)
  {
    return trigger ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull final IJSExpression eventType)
  {
    return triggerHandler ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull final IJson eventType)
  {
    return triggerHandler ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull final IHCNode eventType)
  {
    return triggerHandler ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull final String eventType)
  {
    return triggerHandler ().arg (eventType);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull String eventType, @Nonnull IJSExpression extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IJson eventType, @Nonnull JSArray extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE triggerHandler (@Nonnull String eventType, @Nonnull JSArray extraParameters)
  {
    return triggerHandler ().arg (eventType).arg (extraParameters);
  }

  @Nonnull
  default IMPLTYPE unwrap (@Nonnull final IJSExpression selector)
  {
    return unwrap ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE unwrap (@Nonnull final IJson selector)
  {
    return unwrap ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE unwrap (@Nonnull final IHCNode selector)
  {
    return unwrap ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE unwrap (@Nonnull final String selector)
  {
    return unwrap ().arg (selector);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final IJSExpression value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final IJson value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final IHCNode value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final String value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (final int value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (final long value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final BigInteger value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (final double value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final BigDecimal value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final JSArray value)
  {
    return val ().arg (value);
  }

  @Nonnull
  default IMPLTYPE val (@Nonnull final JSAnonymousFunction function)
  {
    return val ().arg (function);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final IJSExpression value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final IJson value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final IHCNode value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final String value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (final int value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (final long value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final BigInteger value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (final double value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final BigDecimal value)
  {
    return width ().arg (value);
  }

  @Nonnull
  default IMPLTYPE width (@Nonnull final JSAnonymousFunction function)
  {
    return width ().arg (function);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final IJSExpression wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final IJQuerySelector wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final JQuerySelectorList wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final EHTMLElement wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final ICSSClassProvider wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final IHCNode wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final String wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final JQueryInvocation wrappingElement)
  {
    return wrap ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrap (@Nonnull final JSAnonymousFunction function)
  {
    return wrap ().arg (function);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final IJSExpression wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final IJQuerySelector wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final JQuerySelectorList wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final EHTMLElement wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final ICSSClassProvider wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final IHCNode wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final String wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final JQueryInvocation wrappingElement)
  {
    return wrapAll ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapAll (@Nonnull final JSAnonymousFunction function)
  {
    return wrapAll ().arg (function);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final IJSExpression wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final IHCNode wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final String wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final IJQuerySelector wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final JQuerySelectorList wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final EHTMLElement wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final ICSSClassProvider wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final JQueryInvocation wrappingElement)
  {
    return wrapInner ().arg (wrappingElement);
  }

  @Nonnull
  default IMPLTYPE wrapInner (@Nonnull final JSAnonymousFunction function)
  {
    return wrapInner ().arg (function);
  }

}
