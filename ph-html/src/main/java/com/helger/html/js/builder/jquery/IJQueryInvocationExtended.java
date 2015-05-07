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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSArray;
import com.helger.json.IJson;

/**
 * This file is generated - do NOT edit!
 * @author com.helger.html.supplementary.jquery.Main_IJQueryInvocationExtended
 * @param <IMPLTYPE> Implementation type
*/
public interface IJQueryInvocationExtended <IMPLTYPE extends IJQueryInvocationExtended <IMPLTYPE>> extends IJQueryInvocation <IMPLTYPE>
{
/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull IJSExpression selector);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull IJQuerySelector selector);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull EHTMLElement selector);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull ICSSClassProvider selector);

/**
* @param elements One or more elements to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull String elements);

/**
* @param html An HTML fragment to add to the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE add(@Nonnull IHCNode html);

/**
* @param selection An existing jQuery object to add to the set of matched elements.

* @return this
* @since jQuery 1.3.2
*/
@Nonnull IMPLTYPE add(@Nonnull JQueryInvocation selection);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJSExpression selector, @Nonnull IJSExpression context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJSExpression selector, @Nonnull String context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull IJQuerySelector selector, @Nonnull String context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull JQuerySelectorList selector, @Nonnull String context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull EHTMLElement selector, @Nonnull String context);

/**
* @param selector A string representing a selector expression to find additional elements to add to the set of matched elements.
* @param context The point in the document at which the selector should begin matching; similar to the context argument of the $(selector, context) method.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE add(@Nonnull ICSSClassProvider selector, @Nonnull String context);

@Nonnull IMPLTYPE addBack();

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE addBack(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE addBack(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE addBack(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE addBack(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE addBack(@Nonnull ICSSClassProvider selector);

/**
* @param className One or more space-separated classes to be added to the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE addClass(@Nonnull IJSExpression className);

/**
* @param className One or more space-separated classes to be added to the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE addClass(@Nonnull IJson className);

/**
* @param className One or more space-separated classes to be added to the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE addClass(@Nonnull IHCNode className);

/**
* @param className One or more space-separated classes to be added to the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE addClass(@Nonnull String className);

/**
* @param function A function returning one or more space-separated class names to be added to the existing class name(s). Receives the index position of the element in the set and the existing class name(s) as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE addClass(@Nonnull JSAnonymousFunction function);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull String content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JSArray content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert after each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert after each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE after(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1);

/**
* @param function A function that returns an HTML string, DOM element(s), or jQuery object to insert after each element in the set of matched elements. Receives the index position of the element in the set as an argument. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE after(@Nonnull JSAnonymousFunction function);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxComplete(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxComplete(@Nonnull JSAnonymousFunction handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxError(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxError(@Nonnull JSAnonymousFunction handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxSend(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxSend(@Nonnull JSAnonymousFunction handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxStart(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxStart(@Nonnull JSAnonymousFunction handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxStop(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxStop(@Nonnull JSAnonymousFunction handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxSuccess(@Nonnull IJSExpression handler);

/**
* @param handler The function to be invoked.

* @return this
*/
@Nonnull IMPLTYPE ajaxSuccess(@Nonnull JSAnonymousFunction handler);

@Deprecated
@Nonnull IMPLTYPE andSelf();

/**
* @param properties An object of CSS properties and values that the animation will move toward.

* @return this
*/
@Nonnull IMPLTYPE animate(@Nonnull IJSExpression properties);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull String content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JSArray content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the end of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the end of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE append(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1);

/**
* @param function A function that returns an HTML string, DOM element(s), or jQuery object to insert at the end of each element in the set of matched elements. Receives the index position of the element in the set and the old HTML value of the element as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE append(@Nonnull JSAnonymousFunction function);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull IJSExpression target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull IJQuerySelector target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull JQuerySelectorList target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull EHTMLElement target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull ICSSClassProvider target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull IHCNode target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull String target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull JSArray target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the end of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE appendTo(@Nonnull JQueryInvocation target);

/**
* @param attributeName The name of the attribute to get.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName);

/**
* @param attributeName The name of the attribute to get.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName);

/**
* @param attributeName The name of the attribute to get.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName);

/**
* @param attributeName The name of the attribute to get.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJSExpression value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull IJSExpression value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJSExpression value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull IJSExpression value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IJson value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull IJson value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IJson value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull IJson value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull IHCNode value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull IHCNode value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull IHCNode value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull IHCNode value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull String value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull String value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull String value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull String value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, int value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, int value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, int value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, int value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, long value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, long value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, long value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, long value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigInteger value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull BigInteger value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigInteger value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull BigInteger value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, double value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, double value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, double value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, double value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull BigDecimal value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull BigDecimal value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull BigDecimal value);

/**
* @param attributeName The name of the attribute to set.
* @param value A value to set for the attribute.

* @return this
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull BigDecimal value);

/**
* @param attributeName The name of the attribute to set.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old attribute value as arguments.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE attr(@Nonnull IJSExpression attributeName, @Nonnull JSAnonymousFunction function);

/**
* @param attributeName The name of the attribute to set.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old attribute value as arguments.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE attr(@Nonnull IJson attributeName, @Nonnull JSAnonymousFunction function);

/**
* @param attributeName The name of the attribute to set.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old attribute value as arguments.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE attr(@Nonnull IHCNode attributeName, @Nonnull JSAnonymousFunction function);

/**
* @param attributeName The name of the attribute to set.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old attribute value as arguments.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE attr(@Nonnull String attributeName, @Nonnull JSAnonymousFunction function);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull String content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull JSArray content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull String content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JSArray content, @Nonnull JQueryInvocation content1);

/**
* @param content HTML string, DOM element, array of elements, or jQuery object to insert before each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert before each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE before(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1);

/**
* @param function A function that returns an HTML string, DOM element(s), or jQuery object to insert before each element in the set of matched elements. Receives the index position of the element in the set as an argument. Within the function, this refers to the current element in the set.
      

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE before(@Nonnull JSAnonymousFunction function);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IJSExpression eventType);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IJson eventType);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IHCNode eventType);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull String eventType);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param preventBubble Setting the third argument to false will attach a function that prevents the default action from occurring and stops the event from bubbling. The default is true.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, boolean preventBubble);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param preventBubble Setting the third argument to false will attach a function that prevents the default action from occurring and stops the event from bubbling. The default is true.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IJson eventType, @Nonnull IJSExpression eventData, boolean preventBubble);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param preventBubble Setting the third argument to false will attach a function that prevents the default action from occurring and stops the event from bubbling. The default is true.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, boolean preventBubble);

/**
* @param eventType A string containing one or more DOM event types, such as "click" or "submit," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param preventBubble Setting the third argument to false will attach a function that prevents the default action from occurring and stops the event from bubbling. The default is true.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE bind(@Nonnull String eventType, @Nonnull IJSExpression eventData, boolean preventBubble);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE blur(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE blur(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE blur(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE blur(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE blur();

/**
* @param callbacks A function, or array of functions, that are to be added to the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_add(@Nonnull IJSExpression callbacks);

/**
* @param callbacks A function, or array of functions, that are to be added to the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_add(@Nonnull JSAnonymousFunction callbacks);

/**
* @param callbacks A function, or array of functions, that are to be added to the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_add(@Nonnull JSArray callbacks);

@Nonnull IMPLTYPE callbacks_disable();

@Nonnull IMPLTYPE callbacks_disabled();

@Nonnull IMPLTYPE callbacks_empty();

/**
* @param arguments The argument or list of arguments to pass back to the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_fire(@Nonnull IJSExpression arguments);

@Nonnull IMPLTYPE callbacks_fireWith();

/**
* @param context A reference to the context in which the callbacks in the list should be fired.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_fireWith(@Nonnull IJSExpression context);

/**
* @param context A reference to the context in which the callbacks in the list should be fired.
* @param args An argument, or array of arguments, to pass to the callbacks in the list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_fireWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args);

@Nonnull IMPLTYPE callbacks_fired();

@Nonnull IMPLTYPE callbacks_has();

/**
* @param callback The callback to search for.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_has(@Nonnull IJSExpression callback);

/**
* @param callback The callback to search for.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_has(@Nonnull JSAnonymousFunction callback);

@Nonnull IMPLTYPE callbacks_lock();

@Nonnull IMPLTYPE callbacks_locked();

/**
* @param callbacks A function, or array of functions, that are to be removed from the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_remove(@Nonnull IJSExpression callbacks);

/**
* @param callbacks A function, or array of functions, that are to be removed from the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_remove(@Nonnull JSAnonymousFunction callbacks);

/**
* @param callbacks A function, or array of functions, that are to be removed from the callback list.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE callbacks_remove(@Nonnull JSArray callbacks);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE change(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE change(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE change(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE change(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE change();

@Nonnull IMPLTYPE children();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE children(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE children(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE children(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE children(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE children(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE clearQueue();

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE clearQueue(@Nonnull IJSExpression queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE clearQueue(@Nonnull IJson queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE clearQueue(@Nonnull IHCNode queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE clearQueue(@Nonnull String queueName);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE click(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE click(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE click(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE click(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE click();

@Nonnull IMPLTYPE _clone();

/**
* @param withDataAndEvents A Boolean indicating whether event handlers should be copied along with the elements. As of jQuery 1.4, element data will be copied as well.

* @return this
*/
@Nonnull IMPLTYPE _clone(@Nonnull IJSExpression withDataAndEvents);

/**
* @param withDataAndEvents A Boolean indicating whether event handlers should be copied along with the elements. As of jQuery 1.4, element data will be copied as well.

* @return this
*/
@Nonnull IMPLTYPE _clone(boolean withDataAndEvents);

/**
* @param withDataAndEvents A Boolean indicating whether event handlers and data should be copied along with the elements. The default value is false. *In jQuery 1.5.0 the default value was incorrectly true; it was changed back to false in 1.5.1 and up.
* @param deepWithDataAndEvents A Boolean indicating whether event handlers and data for all children of the cloned element should be copied. By default its value matches the first argument's value (which defaults to false).

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE _clone(@Nonnull IJSExpression withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents);

/**
* @param withDataAndEvents A Boolean indicating whether event handlers and data should be copied along with the elements. The default value is false. *In jQuery 1.5.0 the default value was incorrectly true; it was changed back to false in 1.5.1 and up.
* @param deepWithDataAndEvents A Boolean indicating whether event handlers and data for all children of the cloned element should be copied. By default its value matches the first argument's value (which defaults to false).

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE _clone(boolean withDataAndEvents, @Nonnull IJSExpression deepWithDataAndEvents);

/**
* @param withDataAndEvents A Boolean indicating whether event handlers and data should be copied along with the elements. The default value is false. *In jQuery 1.5.0 the default value was incorrectly true; it was changed back to false in 1.5.1 and up.
* @param deepWithDataAndEvents A Boolean indicating whether event handlers and data for all children of the cloned element should be copied. By default its value matches the first argument's value (which defaults to false).

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE _clone(@Nonnull IJSExpression withDataAndEvents, boolean deepWithDataAndEvents);

/**
* @param withDataAndEvents A Boolean indicating whether event handlers and data should be copied along with the elements. The default value is false. *In jQuery 1.5.0 the default value was incorrectly true; it was changed back to false in 1.5.1 and up.
* @param deepWithDataAndEvents A Boolean indicating whether event handlers and data for all children of the cloned element should be copied. By default its value matches the first argument's value (which defaults to false).

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE _clone(boolean withDataAndEvents, boolean deepWithDataAndEvents);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE closest(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE closest(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE closest(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE closest(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE closest(@Nonnull ICSSClassProvider selector);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJSExpression selector, @Nonnull IJSExpression context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull EHTMLElement selector, @Nonnull IJSExpression context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJSExpression selector, @Nonnull EHTMLElement context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJSExpression selector, @Nonnull String context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull IJQuerySelector selector, @Nonnull String context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull JQuerySelectorList selector, @Nonnull String context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull EHTMLElement selector, @Nonnull String context);

/**
* @param selector A string containing a selector expression to match elements against.
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE closest(@Nonnull ICSSClassProvider selector, @Nonnull String context);

/**
* @param selection A jQuery object to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE closest(@Nonnull JQueryInvocation selection);

/**
* @param element An element to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE closest(@Nonnull String element);

/**
* @param selectors An array or string containing a selector expression to match elements against (can also be a jQuery object).

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE closest(@Nonnull JSArray selectors);

/**
* @param selectors An array or string containing a selector expression to match elements against (can also be a jQuery object).
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE closest(@Nonnull JSArray selectors, @Nonnull IJSExpression context);

/**
* @param selectors An array or string containing a selector expression to match elements against (can also be a jQuery object).
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE closest(@Nonnull JSArray selectors, @Nonnull EHTMLElement context);

/**
* @param selectors An array or string containing a selector expression to match elements against (can also be a jQuery object).
* @param context A DOM element within which a matching element may be found. If no context is passed in then the context of the jQuery set will be used instead.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE closest(@Nonnull JSArray selectors, @Nonnull String context);

@Nonnull IMPLTYPE contents();

/**
* @param propertyName A CSS property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName);

/**
* @param propertyName A CSS property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName);

/**
* @param propertyName A CSS property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName);

/**
* @param propertyName A CSS property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName);

/**
* @param propertyNames An array of one or more CSS properties.

* @return this
* @since jQuery 1.9
*/
@Nonnull IMPLTYPE css(@Nonnull JSArray propertyNames);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IJson value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull IJson value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull IJson value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull IJson value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull IHCNode value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull IHCNode value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull IHCNode value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull IHCNode value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull String value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull String value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull String value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull String value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, int value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, int value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, int value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, int value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, long value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, long value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, long value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, long value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigInteger value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull BigInteger value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigInteger value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull BigInteger value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, double value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, double value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, double value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, double value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull BigDecimal value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull BigDecimal value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull BigDecimal value);

/**
* @param propertyName A CSS property name.
* @param value A value to set for the property.

* @return this
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull BigDecimal value);

/**
* @param propertyName A CSS property name.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE css(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName A CSS property name.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE css(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName A CSS property name.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE css(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName A CSS property name.
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE css(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param key A string naming the piece of data to set.
* @param value The new data value; this can be any Javascript type except undefined.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull IJSExpression key, @Nonnull IJSExpression value);

/**
* @param key A string naming the piece of data to set.
* @param value The new data value; this can be any Javascript type except undefined.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull IJson key, @Nonnull IJSExpression value);

/**
* @param key A string naming the piece of data to set.
* @param value The new data value; this can be any Javascript type except undefined.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull IHCNode key, @Nonnull IJSExpression value);

/**
* @param key A string naming the piece of data to set.
* @param value The new data value; this can be any Javascript type except undefined.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull String key, @Nonnull IJSExpression value);

/**
* @param obj An object of key-value pairs of data to update.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE data(@Nonnull IJSExpression obj);

/**
* @param key Name of the data stored.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull IJson key);

/**
* @param key Name of the data stored.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull IHCNode key);

/**
* @param key Name of the data stored.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE data(@Nonnull String key);

@Nonnull IMPLTYPE data();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE dblclick(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE dblclick(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE dblclick(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE dblclick();

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks);

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks);

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      
* @param alwaysCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1);

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      
* @param alwaysCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull IJSExpression alwaysCallbacks1);

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      
* @param alwaysCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull IJSExpression alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1);

/**
* @param alwaysCallbacks 
        A function, or array of functions, that is called when the Deferred is resolved or rejected.
      
* @param alwaysCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved or rejected.
      

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE deferred_always(@Nonnull JSAnonymousFunction alwaysCallbacks, @Nonnull JSAnonymousFunction alwaysCallbacks1);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull IJSExpression doneCallbacks);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      
* @param doneCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression doneCallbacks1);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      
* @param doneCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression doneCallbacks1);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      
* @param doneCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1);

/**
* @param doneCallbacks 
        A function, or array of functions, that are called when the Deferred is resolved.
      
* @param doneCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_done(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction doneCallbacks1);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull IJSExpression failCallbacks);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      
* @param failCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull IJSExpression failCallbacks1);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      
* @param failCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull IJSExpression failCallbacks1);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      
* @param failCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull IJSExpression failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1);

/**
* @param failCallbacks 
        A function, or array of functions, that are called when the Deferred is rejected.
      
* @param failCallbacks1 
        Optional additional functions, or arrays of functions, that are called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_fail(@Nonnull JSAnonymousFunction failCallbacks, @Nonnull JSAnonymousFunction failCallbacks1);

@Deprecated
@Nonnull IMPLTYPE deferred_isRejected();

@Deprecated
@Nonnull IMPLTYPE deferred_isResolved();

/**
* @param args 
        Optional arguments that are passed to the progressCallbacks.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_notify(@Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the progressCallbacks as the this object.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_notifyWith(@Nonnull IJSExpression context);

/**
* @param context 
        Context passed to the progressCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the progressCallbacks.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the progressCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the progressCallbacks.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_notifyWith(@Nonnull IJSExpression context, @Nonnull JSArray args);

@Deprecated
@Nonnull IMPLTYPE deferred_pipe();

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.6
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        An optional function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.7
*/
@Deprecated
@Nonnull IMPLTYPE deferred_pipe(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull IJSExpression progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull IJSExpression progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull IJSExpression progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSAnonymousFunction progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull IJSExpression progressCallbacks, @Nonnull JSArray progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSAnonymousFunction progressCallbacks, @Nonnull JSArray progressCallbacks1);

/**
* @param progressCallbacks 
        A function, or array of functions, to be called when the Deferred generates progress notifications.
      
* @param progressCallbacks1 
        Optional additional function, or array of functions, to be called when the Deferred generates progress notifications.
      

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE deferred_progress(@Nonnull JSArray progressCallbacks, @Nonnull JSArray progressCallbacks1);

@Nonnull IMPLTYPE deferred_promise();

/**
* @param target Object onto which the promise methods have to be attached

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_promise(@Nonnull IJSExpression target);

@Nonnull IMPLTYPE deferred_reject();

/**
* @param args 
        Optional arguments that are passed to the failCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_reject(@Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the failCallbacks as the this object.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_rejectWith(@Nonnull IJSExpression context);

/**
* @param context 
        Context passed to the failCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the failCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the failCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the failCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_rejectWith(@Nonnull IJSExpression context, @Nonnull JSArray args);

@Nonnull IMPLTYPE deferred_resolve();

/**
* @param args 
        Optional arguments that are passed to the doneCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_resolve(@Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the doneCallbacks as the this object.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_resolveWith(@Nonnull IJSExpression context);

/**
* @param context 
        Context passed to the doneCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the doneCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull IJSExpression args);

/**
* @param context 
        Context passed to the doneCallbacks as the this object.
      
* @param args 
        An optional array of arguments that are passed to the doneCallbacks.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_resolveWith(@Nonnull IJSExpression context, @Nonnull JSArray args);

@Nonnull IMPLTYPE deferred_state();

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull IJSExpression progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull IJSExpression failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneFilter 
        A function that is called when the Deferred is resolved.
      
* @param failFilter 
        An optional function that is called when the Deferred is rejected.
      
* @param progressFilter 
        An optional function that is called when progress notifications are sent to the Deferred.
      

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneFilter, @Nonnull JSAnonymousFunction failFilter, @Nonnull JSAnonymousFunction progressFilter);

/**
* @param doneCallbacks 
        A function, or array of functions, called when the Deferred is resolved.
      
* @param failCallbacks 
        A function, or array of functions, called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull IJSExpression failCallbacks);

/**
* @param doneCallbacks 
        A function, or array of functions, called when the Deferred is resolved.
      
* @param failCallbacks 
        A function, or array of functions, called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull IJSExpression failCallbacks);

/**
* @param doneCallbacks 
        A function, or array of functions, called when the Deferred is resolved.
      
* @param failCallbacks 
        A function, or array of functions, called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull IJSExpression doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks);

/**
* @param doneCallbacks 
        A function, or array of functions, called when the Deferred is resolved.
      
* @param failCallbacks 
        A function, or array of functions, called when the Deferred is rejected.
      

* @return this
* @since jQuery 1.5
*/
@Nonnull IMPLTYPE deferred_then(@Nonnull JSAnonymousFunction doneCallbacks, @Nonnull JSAnonymousFunction failCallbacks);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull IJSExpression duration);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(int duration);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(long duration);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull BigInteger duration);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJSExpression queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(int duration, @Nonnull IJSExpression queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(long duration, @Nonnull IJSExpression queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull BigInteger duration, @Nonnull IJSExpression queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull IJSExpression duration, @Nonnull IJson queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(int duration, @Nonnull IJson queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(long duration, @Nonnull IJson queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull BigInteger duration, @Nonnull IJson queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull IJSExpression duration, @Nonnull IHCNode queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(int duration, @Nonnull IHCNode queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(long duration, @Nonnull IHCNode queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull BigInteger duration, @Nonnull IHCNode queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull IJSExpression duration, @Nonnull String queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(int duration, @Nonnull String queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(long duration, @Nonnull String queueName);

/**
* @param duration An integer indicating the number of milliseconds to delay execution of the next item in the queue.
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE delay(@Nonnull BigInteger duration, @Nonnull String queueName);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param eventType A string containing one or more space-separated JavaScript event types, such as "click" or "keydown," or custom event names.
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param events A plain object of one or more event types and functions to execute for them.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression events);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param events A plain object of one or more event types and functions to execute for them.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE delegate(@Nonnull IJson selector, @Nonnull IJSExpression events);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param events A plain object of one or more event types and functions to execute for them.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE delegate(@Nonnull IHCNode selector, @Nonnull IJSExpression events);

/**
* @param selector A selector to filter the elements that trigger the event.
* @param events A plain object of one or more event types and functions to execute for them.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE delegate(@Nonnull String selector, @Nonnull IJSExpression events);

@Nonnull IMPLTYPE dequeue();

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE dequeue(@Nonnull IJSExpression queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE dequeue(@Nonnull IJson queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE dequeue(@Nonnull IHCNode queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE dequeue(@Nonnull String queueName);

@Nonnull IMPLTYPE detach();

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE detach(@Nonnull IJSExpression selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE detach(@Nonnull IJQuerySelector selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE detach(@Nonnull JQuerySelectorList selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE detach(@Nonnull EHTMLElement selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE detach(@Nonnull ICSSClassProvider selector);

@Deprecated
@Nonnull IMPLTYPE die();

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJSExpression eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJson eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IHCNode eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull String eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJSExpression eventType, @Nonnull IJson handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJson eventType, @Nonnull IJson handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IHCNode eventType, @Nonnull IJson handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull String eventType, @Nonnull IJson handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJSExpression eventType, @Nonnull IHCNode handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJson eventType, @Nonnull IHCNode handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IHCNode eventType, @Nonnull IHCNode handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull String eventType, @Nonnull IHCNode handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJSExpression eventType, @Nonnull String handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IJson eventType, @Nonnull String handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull IHCNode eventType, @Nonnull String handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or keydown.
* @param handler The function that is no longer to be executed.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE die(@Nonnull String eventType, @Nonnull String handler);

/**
* @param function A function to execute for each matched element.

* @return this
*/
@Nonnull IMPLTYPE each(@Nonnull IJSExpression function);

/**
* @param function A function to execute for each matched element.

* @return this
*/
@Nonnull IMPLTYPE each(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE empty();

@Nonnull IMPLTYPE end();

/**
* @param index An integer indicating the 0-based position of the element. 

* @return this
* @since jQuery 1.1.2
*/
@Nonnull IMPLTYPE _eq(@Nonnull IJSExpression index);

/**
* @param index An integer indicating the 0-based position of the element. 

* @return this
* @since jQuery 1.1.2
*/
@Nonnull IMPLTYPE _eq(int index);

/**
* @param index An integer indicating the 0-based position of the element. 

* @return this
* @since jQuery 1.1.2
*/
@Nonnull IMPLTYPE _eq(long index);

/**
* @param index An integer indicating the 0-based position of the element. 

* @return this
* @since jQuery 1.1.2
*/
@Nonnull IMPLTYPE _eq(@Nonnull BigInteger index);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE error(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE error(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE error(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE error(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE event_isDefaultPrevented();

@Nonnull IMPLTYPE event_isImmediatePropagationStopped();

@Nonnull IMPLTYPE event_isPropagationStopped();

@Nonnull IMPLTYPE event_preventDefault();

@Nonnull IMPLTYPE event_stopImmediatePropagation();

@Nonnull IMPLTYPE event_stopPropagation();

@Nonnull IMPLTYPE fadeIn();

@Nonnull IMPLTYPE fadeOut();

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param complete A function to call once the animation is complete.

* @return this
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull IJSExpression complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJSExpression easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IJson easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull IHCNode easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull IJSExpression opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, int opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, long opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigInteger opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, double opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJSExpression duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IJson duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull IHCNode duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull String duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(int duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(long duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigInteger duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(double duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

/**
* @param duration A string or number determining how long the animation will run.
* @param opacity A number between 0 and 1 denoting the target opacity.
* @param easing A string indicating which easing function to use for the transition.
* @param complete A function to call once the animation is complete.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE fadeTo(@Nonnull BigDecimal duration, @Nonnull BigDecimal opacity, @Nonnull String easing, @Nonnull JSAnonymousFunction complete);

@Nonnull IMPLTYPE fadeToggle();

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match the current set of elements against.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull ICSSClassProvider selector);

/**
* @param function A function used as a test for each element in the set. this is the current DOM element.

* @return this
*/
@Nonnull IMPLTYPE filter(@Nonnull JSAnonymousFunction function);

/**
* @param elements One or more DOM elements to match the current set of elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE filter(@Nonnull String elements);

/**
* @param selection An existing jQuery object to match the current set of elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE filter(@Nonnull JQueryInvocation selection);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE find(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE find(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE find(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE find(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE find(@Nonnull ICSSClassProvider selector);

/**
* @param element An element or a jQuery object to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE find(@Nonnull String element);

/**
* @param element An element or a jQuery object to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE find(@Nonnull JQueryInvocation element);

@Nonnull IMPLTYPE finish();

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.9
*/
@Nonnull IMPLTYPE finish(@Nonnull IJSExpression queue);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.9
*/
@Nonnull IMPLTYPE finish(@Nonnull IJson queue);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.9
*/
@Nonnull IMPLTYPE finish(@Nonnull IHCNode queue);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.9
*/
@Nonnull IMPLTYPE finish(@Nonnull String queue);

@Nonnull IMPLTYPE first();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE focus(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE focus(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focus(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focus(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE focus();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE focusin(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE focusin(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focusin(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE focusout(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE focusout(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE focusout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param index A zero-based integer indicating which element to retrieve.

* @return this
*/
@Nonnull IMPLTYPE get(@Nonnull IJSExpression index);

/**
* @param index A zero-based integer indicating which element to retrieve.

* @return this
*/
@Nonnull IMPLTYPE get(int index);

/**
* @param index A zero-based integer indicating which element to retrieve.

* @return this
*/
@Nonnull IMPLTYPE get(long index);

/**
* @param index A zero-based integer indicating which element to retrieve.

* @return this
*/
@Nonnull IMPLTYPE get(@Nonnull BigInteger index);

@Nonnull IMPLTYPE get();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE has(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE has(@Nonnull IJson selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE has(@Nonnull IHCNode selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE has(@Nonnull String selector);

/**
* @param contained A DOM element to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE has(@Nonnull EHTMLElement contained);

/**
* @param className The class name to search for.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE hasClass(@Nonnull IJSExpression className);

/**
* @param className The class name to search for.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE hasClass(@Nonnull IJson className);

/**
* @param className The class name to search for.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE hasClass(@Nonnull IHCNode className);

/**
* @param className The class name to search for.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE hasClass(@Nonnull String className);

@Nonnull IMPLTYPE height();

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull IJSExpression value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull IJson value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull IHCNode value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull String value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(int value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(long value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull BigInteger value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(double value);

/**
* @param value An integer representing the number of pixels, or an integer with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE height(@Nonnull BigDecimal value);

/**
* @param function A function returning the height to set. Receives the index position of the element in the set and the old height as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4.1
*/
@Nonnull IMPLTYPE height(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE hide();

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull IJSExpression duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(int duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(long duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull BigInteger duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(double duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull BigDecimal duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull IJson duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull IHCNode duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE hide(@Nonnull String duration);

/**
* @param handlerIn A function to execute when the mouse pointer enters the element.
* @param handlerOut A function to execute when the mouse pointer leaves the element.

* @return this
*/
@Nonnull IMPLTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull IJSExpression handlerOut);

/**
* @param handlerIn A function to execute when the mouse pointer enters the element.
* @param handlerOut A function to execute when the mouse pointer leaves the element.

* @return this
*/
@Nonnull IMPLTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull IJSExpression handlerOut);

/**
* @param handlerIn A function to execute when the mouse pointer enters the element.
* @param handlerOut A function to execute when the mouse pointer leaves the element.

* @return this
*/
@Nonnull IMPLTYPE hover(@Nonnull IJSExpression handlerIn, @Nonnull JSAnonymousFunction handlerOut);

/**
* @param handlerIn A function to execute when the mouse pointer enters the element.
* @param handlerOut A function to execute when the mouse pointer leaves the element.

* @return this
*/
@Nonnull IMPLTYPE hover(@Nonnull JSAnonymousFunction handlerIn, @Nonnull JSAnonymousFunction handlerOut);

/**
* @param handlerInOut A function to execute when the mouse pointer enters or leaves the element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE hover(@Nonnull IJSExpression handlerInOut);

/**
* @param handlerInOut A function to execute when the mouse pointer enters or leaves the element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE hover(@Nonnull JSAnonymousFunction handlerInOut);

@Nonnull IMPLTYPE html();

/**
* @param htmlString A string of HTML to set as the content of each matched element.

* @return this
*/
@Nonnull IMPLTYPE html(@Nonnull IJSExpression htmlString);

/**
* @param htmlString A string of HTML to set as the content of each matched element.

* @return this
*/
@Nonnull IMPLTYPE html(@Nonnull IHCNode htmlString);

/**
* @param htmlString A string of HTML to set as the content of each matched element.

* @return this
*/
@Nonnull IMPLTYPE html(@Nonnull String htmlString);

/**
* @param function A function returning the HTML content to set. Receives the
          index position of the element in the set and the old HTML value as arguments.
          jQuery empties the element before calling the function;
          use the oldhtml argument to reference the previous content.
          Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE html(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE index();

/**
* @param selector A selector representing a jQuery collection in which to look for an element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE index(@Nonnull IJSExpression selector);

/**
* @param selector A selector representing a jQuery collection in which to look for an element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE index(@Nonnull IJQuerySelector selector);

/**
* @param selector A selector representing a jQuery collection in which to look for an element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE index(@Nonnull JQuerySelectorList selector);

/**
* @param selector A selector representing a jQuery collection in which to look for an element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE index(@Nonnull EHTMLElement selector);

/**
* @param selector A selector representing a jQuery collection in which to look for an element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE index(@Nonnull ICSSClassProvider selector);

/**
* @param element The DOM element or first element within the jQuery object to look for.

* @return this
*/
@Nonnull IMPLTYPE index(@Nonnull String element);

/**
* @param element The DOM element or first element within the jQuery object to look for.

* @return this
*/
@Nonnull IMPLTYPE index(@Nonnull JQueryInvocation element);

@Nonnull IMPLTYPE innerHeight();

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull IJSExpression value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull IJson value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull IHCNode value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull String value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(int value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(long value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull BigInteger value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(double value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull BigDecimal value);

/**
* @param function A function returning the inner height (including padding but not border) to set. Receives the index position of the element in the set and the old inner height as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerHeight(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE innerWidth();

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull IJSExpression value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull IJson value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull IHCNode value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull String value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(int value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(long value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull BigInteger value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(double value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull BigDecimal value);

/**
* @param function A function returning the inner width (including padding but not border) to set. Receives the index position of the element in the set and the old inner width as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE innerWidth(@Nonnull JSAnonymousFunction function);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull IJSExpression target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull IJQuerySelector target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull JQuerySelectorList target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull EHTMLElement target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull ICSSClassProvider target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull IHCNode target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull String target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull JSArray target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted after the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertAfter(@Nonnull JQueryInvocation target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull IJSExpression target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull IJQuerySelector target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull JQuerySelectorList target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull EHTMLElement target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull ICSSClassProvider target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull IHCNode target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull String target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull JSArray target);

/**
* @param target A selector, element, array of elements, HTML string, or jQuery object; the matched set of elements will be inserted before the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE insertBefore(@Nonnull JQueryInvocation target);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE is(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE is(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE is(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE is(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE is(@Nonnull ICSSClassProvider selector);

/**
* @param function A function used as a test for every element in the set. It accepts two arguments, index, which is the element's index in the jQuery collection, and element, which is the DOM element. Within the function, this refers to the current DOM element.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE is(@Nonnull JSAnonymousFunction function);

/**
* @param selection An existing jQuery object to match the current set of elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE is(@Nonnull JQueryInvocation selection);

/**
* @param elements One or more elements to match the current set of elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE is(@Nonnull String elements);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keydown(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keydown(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keydown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE keydown();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keypress(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keypress(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keypress(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE keypress();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keyup(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE keyup(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE keyup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE keyup();

@Nonnull IMPLTYPE last();

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJson events, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull String events, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJSExpression events, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJson events, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IHCNode events, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull String events, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing a JavaScript event type, such as "click" or "keydown." As of jQuery 1.4 the string can contain multiple, space-separated event types or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A plain object of one or more JavaScript event types and functions to execute for them.

* @return this
* @deprecated Deprecated since jQuery 1.7
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE live(@Nonnull IJSExpression events);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE load(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE load(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE load(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE load(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

/**
* @param url A string containing the URL to which the request is sent.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url);

/**
* @param url A string containing the URL to which the request is sent.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url);

/**
* @param url A string containing the URL to which the request is sent.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJSExpression data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJson data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJson data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJson data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IHCNode data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IHCNode data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull String data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull String data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull String data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull String data);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull IJSExpression complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IJson data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull IHCNode data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJSExpression url, @Nonnull String data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IJson url, @Nonnull String data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull IHCNode url, @Nonnull String data, @Nonnull JSAnonymousFunction complete);

/**
* @param url A string containing the URL to which the request is sent.
* @param data A plain object or string that is sent to the server with the request.
* @param complete A callback function that is executed when the request completes.

* @return this
*/
@Nonnull IMPLTYPE load(@Nonnull String url, @Nonnull String data, @Nonnull JSAnonymousFunction complete);

/**
* @param callback A function object that will be invoked for each element in the current set.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE map(@Nonnull IJSExpression callback);

/**
* @param callback A function object that will be invoked for each element in the current set.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE map(@Nonnull JSAnonymousFunction callback);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mousedown(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mousedown(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mousedown(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mousedown();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseenter(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseenter(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseenter(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mouseenter();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseleave(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseleave(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseleave(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mouseleave();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mousemove(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mousemove(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mousemove(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mousemove();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseout(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseout(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseout(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mouseout();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseover(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseover(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseover(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mouseover();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseup(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE mouseup(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE mouseup(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE mouseup();

@Nonnull IMPLTYPE next();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE next(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE next(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE next(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE next(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE next(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE nextAll();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE nextAll(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE nextAll(@Nonnull IJson selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE nextAll(@Nonnull IHCNode selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE nextAll(@Nonnull String selector);

@Nonnull IMPLTYPE nextUntil();

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching following sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE nextUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull ICSSClassProvider selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull String selector);

/**
* @param selector A string containing a selector expression, a DOM element, or an array of elements to match against the set.

* @return this
*/
@Nonnull IMPLTYPE _not(@Nonnull JSArray selector);

/**
* @param function A function used as a test for each element in the set. It accepts two arguments, index, which is the element's index in the jQuery collection, and element, which is the DOM element. Within the function, this refers to the current DOM element.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE _not(@Nonnull JSAnonymousFunction function);

/**
* @param selection An existing jQuery object to match the current set of elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE _not(@Nonnull JQueryInvocation selection);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IJson selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IJson events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, or just namespaces, such as "click", "keydown.myPlugin", or ".myPlugin".
* @param selector A selector which should match the one originally passed to .on() when attaching event handlers.
* @param handler A handler function previously attached for the event(s), or the special value false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE off(@Nonnull String events, @Nonnull String selector, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE off();

@Nonnull IMPLTYPE offset();

/**
* @param coordinates An object containing the properties top and left, which are numbers indicating the new top and left coordinates for the elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE offset(@Nonnull IJSExpression coordinates);

/**
* @param function A function to return the coordinates to set. Receives the index of the element in the collection as the first argument and the current coordinates as the second argument. The function should return an object with the new top and left properties.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE offset(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE offsetParent();

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJSExpression selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events An object in which the string keys represent one or more space-separated event types and optional namespaces, and the values represent a handler function to be called for the event(s).

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE on(@Nonnull IJSExpression events);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJSExpression data);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events A string containing one or more JavaScript event types, such as "click" or "submit," or custom event names.
* @param data An object containing data that will be passed to the event handler.
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.1
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJson selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IHCNode selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull String selector);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull IJSExpression handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJSExpression selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IJson selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull IHCNode selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJson events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IHCNode events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events One or more space-separated event types and optional namespaces, such as "click" or "keydown.myPlugin".
* @param selector A selector string to filter the descendants of the selected elements that trigger the event. If the selector is null or omitted, the event is always triggered when it reaches the selected element.
* @param data Data to be passed to the handler in event.data when an event is triggered.
* @param handler A function to execute when the event is triggered. The value false is also allowed as a shorthand for a function that simply does return false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull String events, @Nonnull String selector, @Nonnull IJSExpression data, @Nonnull JSAnonymousFunction handler);

/**
* @param events An object in which the string keys represent one or more space-separated event types and optional namespaces, and the values represent a handler function to be called for the event(s).

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE one(@Nonnull IJSExpression events);

@Nonnull IMPLTYPE outerHeight();

/**
* @param includeMargin A Boolean indicating whether to include the element's margin in the calculation.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull IJSExpression includeMargin);

/**
* @param includeMargin A Boolean indicating whether to include the element's margin in the calculation.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE outerHeight(boolean includeMargin);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull IJson value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull IHCNode value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull String value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(int value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(long value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull BigInteger value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(double value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull BigDecimal value);

/**
* @param function A function returning the outer height to set. Receives the index position of the element in the set and the old outer height as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerHeight(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE outerWidth();

/**
* @param includeMargin A Boolean indicating whether to include the element's margin in the calculation.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull IJSExpression includeMargin);

/**
* @param includeMargin A Boolean indicating whether to include the element's margin in the calculation.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE outerWidth(boolean includeMargin);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull IJson value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull IHCNode value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull String value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(int value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(long value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull BigInteger value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(double value);

/**
* @param value A number representing the number of pixels, or a number along with an optional unit of measure appended (as a string).

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull BigDecimal value);

/**
* @param function A function returning the outer width to set. Receives the index position of the element in the set and the old outer width as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.8
*/
@Nonnull IMPLTYPE outerWidth(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE parent();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parent(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parent(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parent(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parent(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parent(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE parents();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parents(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parents(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parents(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parents(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE parents(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE parentsUntil();

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching ancestor elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE parentsUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter);

@Nonnull IMPLTYPE position();

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IJSExpression content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull IHCNode content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull String content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull EHTMLElement content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JSArray content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IJSExpression content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull IHCNode content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull String content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull EHTMLElement content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSArray content, @Nonnull JQueryInvocation content1);

/**
* @param content DOM element, array of elements, HTML string, or jQuery object to insert at the beginning of each element in the set of matched elements.
* @param content1 One or more additional DOM elements, arrays of elements, HTML strings, or jQuery objects to insert at the beginning of each element in the set of matched elements.

* @return this
*/
@Nonnull IMPLTYPE prepend(@Nonnull JQueryInvocation content, @Nonnull JQueryInvocation content1);

/**
* @param function A function that returns an HTML string, DOM element(s), or jQuery object to insert at the beginning of each element in the set of matched elements. Receives the index position of the element in the set and the old HTML value of the element as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prepend(@Nonnull JSAnonymousFunction function);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull IJSExpression target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull IJQuerySelector target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull JQuerySelectorList target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull EHTMLElement target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull ICSSClassProvider target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull IHCNode target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull String target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull JSArray target);

/**
* @param target A selector, element, HTML string, array of elements, or jQuery object; the matched set of elements will be inserted at the beginning of the element(s) specified by this parameter.

* @return this
*/
@Nonnull IMPLTYPE prependTo(@Nonnull JQueryInvocation target);

@Nonnull IMPLTYPE prev();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE prev(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE prev(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE prev(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE prev(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE prev(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE prevAll();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE prevAll(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE prevAll(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE prevAll(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE prevAll(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE prevAll(@Nonnull ICSSClassProvider selector);

@Nonnull IMPLTYPE prevUntil();

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJSExpression filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull IJQuerySelector filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull JQuerySelectorList filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull EHTMLElement filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJSExpression selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull IJQuerySelector selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQuerySelectorList selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull EHTMLElement selector, @Nonnull ICSSClassProvider filter);

/**
* @param selector A string containing a selector expression to indicate where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull ICSSClassProvider selector, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJSExpression filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull IJQuerySelector filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull JQuerySelectorList filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull EHTMLElement filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull String element, @Nonnull ICSSClassProvider filter);

/**
* @param element A DOM node or jQuery object indicating where to stop matching preceding sibling elements.
* @param filter A string containing a selector expression to match elements against.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prevUntil(@Nonnull JQueryInvocation element, @Nonnull ICSSClassProvider filter);

@Nonnull IMPLTYPE promise();

/**
* @param type  The type of queue that needs to be observed. 

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IJSExpression type);

/**
* @param type  The type of queue that needs to be observed. 

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IJson type);

/**
* @param type  The type of queue that needs to be observed. 

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IHCNode type);

/**
* @param type  The type of queue that needs to be observed. 

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull String type);

/**
* @param type  The type of queue that needs to be observed. 
* @param target Object onto which the promise methods have to be attached

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IJSExpression type, @Nonnull IJSExpression target);

/**
* @param type  The type of queue that needs to be observed. 
* @param target Object onto which the promise methods have to be attached

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IJson type, @Nonnull IJSExpression target);

/**
* @param type  The type of queue that needs to be observed. 
* @param target Object onto which the promise methods have to be attached

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull IHCNode type, @Nonnull IJSExpression target);

/**
* @param type  The type of queue that needs to be observed. 
* @param target Object onto which the promise methods have to be attached

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE promise(@Nonnull String type, @Nonnull IJSExpression target);

/**
* @param propertyName The name of the property to get.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJSExpression propertyName);

/**
* @param propertyName The name of the property to get.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJson propertyName);

/**
* @param propertyName The name of the property to get.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IHCNode propertyName);

/**
* @param propertyName The name of the property to get.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull String propertyName);

/**
* @param propertyName The name of the property to set.
* @param value A value to set for the property.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName The name of the property to set.
* @param value A value to set for the property.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJson propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName The name of the property to set.
* @param value A value to set for the property.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IHCNode propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName The name of the property to set.
* @param value A value to set for the property.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull String propertyName, @Nonnull IJSExpression value);

/**
* @param propertyName The name of the property to set.
* @param function A function returning the value to set. Receives the index position of the element in the set and the old property value as arguments. Within the function, the keyword this refers to the current element.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJSExpression propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName The name of the property to set.
* @param function A function returning the value to set. Receives the index position of the element in the set and the old property value as arguments. Within the function, the keyword this refers to the current element.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IJson propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName The name of the property to set.
* @param function A function returning the value to set. Receives the index position of the element in the set and the old property value as arguments. Within the function, the keyword this refers to the current element.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull IHCNode propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param propertyName The name of the property to set.
* @param function A function returning the value to set. Receives the index position of the element in the set and the old property value as arguments. Within the function, the keyword this refers to the current element.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE prop(@Nonnull String propertyName, @Nonnull JSAnonymousFunction function);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.

* @return this
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.

* @return this
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull IJSExpression arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJSExpression name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IJson name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IJson name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull IHCNode name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull IHCNode name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull IJSExpression elements, @Nonnull String name, @Nonnull JSArray arguments);

/**
* @param elements An array of elements to push onto the stack and make into a new jQuery object.
* @param name The name of a jQuery method that generated the array of elements.
* @param arguments The arguments that were passed in to the jQuery method (for serialization).

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE pushStack(@Nonnull JSArray elements, @Nonnull String name, @Nonnull JSArray arguments);

@Nonnull IMPLTYPE queue();

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJSExpression queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJson queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IHCNode queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull String queueName);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJSExpression queueName, @Nonnull IJSExpression newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJson queueName, @Nonnull IJSExpression newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IHCNode queueName, @Nonnull IJSExpression newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull String queueName, @Nonnull IJSExpression newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSArray newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJson queueName, @Nonnull JSArray newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSArray newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param newQueue An array of functions to replace the current queue contents.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull String queueName, @Nonnull JSArray newQueue);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param callback The new function to add to the queue, with a function to call that will dequeue the next item.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJSExpression queueName, @Nonnull JSAnonymousFunction callback);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param callback The new function to add to the queue, with a function to call that will dequeue the next item.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IJson queueName, @Nonnull JSAnonymousFunction callback);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param callback The new function to add to the queue, with a function to call that will dequeue the next item.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull IHCNode queueName, @Nonnull JSAnonymousFunction callback);

/**
* @param queueName A string containing the name of the queue. Defaults to fx, the standard effects queue.
* @param callback The new function to add to the queue, with a function to call that will dequeue the next item.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE queue(@Nonnull String queueName, @Nonnull JSAnonymousFunction callback);

/**
* @param handler A function to execute after the DOM is ready.

* @return this
*/
@Nonnull IMPLTYPE ready(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute after the DOM is ready.

* @return this
*/
@Nonnull IMPLTYPE ready(@Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE remove();

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
*/
@Nonnull IMPLTYPE remove(@Nonnull IJSExpression selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
*/
@Nonnull IMPLTYPE remove(@Nonnull IJson selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
*/
@Nonnull IMPLTYPE remove(@Nonnull IHCNode selector);

/**
* @param selector A selector expression that filters the set of matched elements to be removed.

* @return this
*/
@Nonnull IMPLTYPE remove(@Nonnull String selector);

/**
* @param attributeName An attribute to remove; as of version 1.7, it can be a space-separated list of attributes.

* @return this
*/
@Nonnull IMPLTYPE removeAttr(@Nonnull IJSExpression attributeName);

/**
* @param attributeName An attribute to remove; as of version 1.7, it can be a space-separated list of attributes.

* @return this
*/
@Nonnull IMPLTYPE removeAttr(@Nonnull IJson attributeName);

/**
* @param attributeName An attribute to remove; as of version 1.7, it can be a space-separated list of attributes.

* @return this
*/
@Nonnull IMPLTYPE removeAttr(@Nonnull IHCNode attributeName);

/**
* @param attributeName An attribute to remove; as of version 1.7, it can be a space-separated list of attributes.

* @return this
*/
@Nonnull IMPLTYPE removeAttr(@Nonnull String attributeName);

@Nonnull IMPLTYPE removeClass();

/**
* @param className One or more space-separated classes to be removed from the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE removeClass(@Nonnull IJSExpression className);

/**
* @param className One or more space-separated classes to be removed from the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE removeClass(@Nonnull IJson className);

/**
* @param className One or more space-separated classes to be removed from the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE removeClass(@Nonnull IHCNode className);

/**
* @param className One or more space-separated classes to be removed from the class attribute of each matched element.

* @return this
*/
@Nonnull IMPLTYPE removeClass(@Nonnull String className);

/**
* @param function A function returning one or more space-separated class names to be removed. Receives the index position of the element in the set and the old class value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE removeClass(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE removeData();

/**
* @param name A string naming the piece of data to delete.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE removeData(@Nonnull IJSExpression name);

/**
* @param name A string naming the piece of data to delete.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE removeData(@Nonnull IJson name);

/**
* @param name A string naming the piece of data to delete.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE removeData(@Nonnull IHCNode name);

/**
* @param name A string naming the piece of data to delete.

* @return this
* @since jQuery 1.2.3
*/
@Nonnull IMPLTYPE removeData(@Nonnull String name);

/**
* @param list An array or space-separated string naming the pieces of data to delete.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE removeData(@Nonnull JSArray list);

/**
* @param propertyName The name of the property to remove.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE removeProp(@Nonnull IJSExpression propertyName);

/**
* @param propertyName The name of the property to remove.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE removeProp(@Nonnull IJson propertyName);

/**
* @param propertyName The name of the property to remove.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE removeProp(@Nonnull IHCNode propertyName);

/**
* @param propertyName The name of the property to remove.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE removeProp(@Nonnull String propertyName);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull IJSExpression target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull IJQuerySelector target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull JQuerySelectorList target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull EHTMLElement target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull ICSSClassProvider target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull JQueryInvocation target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull JSArray target);

/**
* @param target A selector string, jQuery object, DOM element, or array of elements indicating which element(s) to replace.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceAll(@Nonnull String target);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull IJSExpression newContent);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull IHCNode newContent);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull String newContent);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull EHTMLElement newContent);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull JSArray newContent);

/**
* @param newContent The content to insert. May be an HTML string, DOM element, array of DOM elements, or jQuery object.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull JQueryInvocation newContent);

/**
* @param function A function that returns content with which to replace the set of matched elements.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE replaceWith(@Nonnull JSAnonymousFunction function);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE resize(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE resize(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE resize(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE resize(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE resize();

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE scroll(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE scroll(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE scroll(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE scroll();

@Nonnull IMPLTYPE scrollLeft();

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(@Nonnull IJSExpression value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(int value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(long value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(@Nonnull BigInteger value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(double value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollLeft(@Nonnull BigDecimal value);

@Nonnull IMPLTYPE scrollTop();

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(@Nonnull IJSExpression value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(int value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(long value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(@Nonnull BigInteger value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(double value);

/**
* @param value An integer indicating the new position to set the scroll bar to.

* @return this
* @since jQuery 1.2.6
*/
@Nonnull IMPLTYPE scrollTop(@Nonnull BigDecimal value);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE select(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE select(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE select(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE select(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE select();

@Nonnull IMPLTYPE serialize();

@Nonnull IMPLTYPE serializeArray();

@Nonnull IMPLTYPE show();

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull IJSExpression duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(int duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(long duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull BigInteger duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(double duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull BigDecimal duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull IJson duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull IHCNode duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE show(@Nonnull String duration);

@Nonnull IMPLTYPE siblings();

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE siblings(@Nonnull IJSExpression selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE siblings(@Nonnull IJQuerySelector selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE siblings(@Nonnull JQuerySelectorList selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE siblings(@Nonnull EHTMLElement selector);

/**
* @param selector A string containing a selector expression to match elements against.

* @return this
*/
@Nonnull IMPLTYPE siblings(@Nonnull ICSSClassProvider selector);

@Deprecated
@Nonnull IMPLTYPE size();

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull IJSExpression start);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(int start);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(long start);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull BigInteger start);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull IJSExpression start, @Nonnull IJSExpression end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(int start, @Nonnull IJSExpression end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(long start, @Nonnull IJSExpression end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull BigInteger start, @Nonnull IJSExpression end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull IJSExpression start, int end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(int start, int end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(long start, int end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull BigInteger start, int end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull IJSExpression start, long end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(int start, long end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(long start, long end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull BigInteger start, long end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull IJSExpression start, @Nonnull BigInteger end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(int start, @Nonnull BigInteger end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(long start, @Nonnull BigInteger end);

/**
* @param start An integer indicating the 0-based position at which the elements begin to be selected. If negative, it indicates an offset from the end of the set.
* @param end An integer indicating the 0-based position at which the elements stop being selected. If negative, it indicates an offset from the end of the set. If omitted, the range continues until the end of the set.

* @return this
* @since jQuery 1.1.4
*/
@Nonnull IMPLTYPE slice(@Nonnull BigInteger start, @Nonnull BigInteger end);

@Nonnull IMPLTYPE slideDown();

@Nonnull IMPLTYPE slideToggle();

@Nonnull IMPLTYPE slideUp();

@Nonnull IMPLTYPE stop();

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression clearQueue);

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(boolean clearQueue);

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(boolean clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression clearQueue, boolean jumpToEnd);

/**
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE stop(boolean clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue);

/**
* @param queue The name of the queue in which to stop animations.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, boolean clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, boolean clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, boolean clearQueue);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, boolean clearQueue, @Nonnull IJSExpression jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, @Nonnull IJSExpression clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJSExpression queue, boolean clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IJson queue, boolean clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull IHCNode queue, boolean clearQueue, boolean jumpToEnd);

/**
* @param queue The name of the queue in which to stop animations.
* @param clearQueue A Boolean indicating whether to remove queued animation as well. Defaults to false.
* @param jumpToEnd A Boolean indicating whether to complete the current animation immediately. Defaults to false.

* @return this
* @since jQuery 1.7
*/
@Nonnull IMPLTYPE stop(@Nonnull String queue, boolean clearQueue, boolean jumpToEnd);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE submit(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute each time the event is triggered.

* @return this
*/
@Nonnull IMPLTYPE submit(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE submit(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData An object containing data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE submit(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE submit();

@Nonnull IMPLTYPE text();

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull IJSExpression text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull IJson text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull IHCNode text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull String text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(int text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(long text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull BigInteger text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(double text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(@Nonnull BigDecimal text);

/**
* @param text The text to set as the content of each matched element. When Number or Boolean is supplied, it will be converted to a String representation.

* @return this
*/
@Nonnull IMPLTYPE text(boolean text);

/**
* @param function A function returning the text content to set. Receives the index position of the element in the set and the old text value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE text(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE toArray();

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1, @Nonnull IJSExpression handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1, @Nonnull IJSExpression handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1, @Nonnull IJSExpression handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1, @Nonnull IJSExpression handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull IJSExpression handler1, @Nonnull JSAnonymousFunction handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull IJSExpression handler1, @Nonnull JSAnonymousFunction handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression handler, @Nonnull JSAnonymousFunction handler1, @Nonnull JSAnonymousFunction handler2);

/**
* @param handler A function to execute every even time the element is clicked.
* @param handler1 A function to execute every odd time the element is clicked.
* @param handler2 Additional handlers to cycle through after clicks.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE toggle(@Nonnull JSAnonymousFunction handler, @Nonnull JSAnonymousFunction handler1, @Nonnull JSAnonymousFunction handler2);

@Nonnull IMPLTYPE toggle();

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull IJSExpression duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(int duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(long duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull BigInteger duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(double duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull BigDecimal duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull IJson duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull IHCNode duration);

/**
* @param duration A string or number determining how long the animation will run.

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE toggle(@Nonnull String duration);

/**
* @param display Use true to show the element or false to hide it.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggle(boolean display);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.

* @return this
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJSExpression className);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.

* @return this
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJson className);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.

* @return this
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IHCNode className);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.

* @return this
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull String className);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJSExpression className, @Nonnull IJSExpression state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJson className, @Nonnull IJSExpression state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IHCNode className, @Nonnull IJSExpression state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull String className, @Nonnull IJSExpression state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJSExpression className, boolean state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IJson className, boolean state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull IHCNode className, boolean state);

/**
* @param className One or more class names (separated by spaces) to be toggled for each element in the matched set.
* @param state A Boolean (not just truthy/falsy) value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.3
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull String className, boolean state);

@Nonnull IMPLTYPE toggleClass();

/**
* @param state A boolean value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE toggleClass(boolean state);

/**
* @param function A function that returns class names to be toggled in the class attribute of each element in the matched set. Receives the index position of the element in the set, the old class value, and the state as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull JSAnonymousFunction function);

/**
* @param function A function that returns class names to be toggled in the class attribute of each element in the matched set. Receives the index position of the element in the set, the old class value, and the state as arguments.
* @param state A boolean value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull JSAnonymousFunction function, @Nonnull IJSExpression state);

/**
* @param function A function that returns class names to be toggled in the class attribute of each element in the matched set. Receives the index position of the element in the set, the old class value, and the state as arguments.
* @param state A boolean value to determine whether the class should be added or removed.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE toggleClass(@Nonnull JSAnonymousFunction function, boolean state);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJSExpression eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJson eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IHCNode eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull String eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull String eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IJson eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
*/
@Nonnull IMPLTYPE trigger(@Nonnull String eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJSExpression eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJson eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IHCNode eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull String eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull String eventType, @Nonnull IJSExpression extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJSExpression eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IJson eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull IHCNode eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param extraParameters Additional parameters to pass along to the event handler.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE triggerHandler(@Nonnull String eventType, @Nonnull JSArray extraParameters);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJSExpression eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJson eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IHCNode eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull String eventType);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param handler The function that is to be no longer executed.

* @return this
*/
@Nonnull IMPLTYPE unbind(@Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param _false Unbinds the corresponding 'return false' function that was bound using .bind( eventType, false ).

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJSExpression eventType, boolean _false);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param _false Unbinds the corresponding 'return false' function that was bound using .bind( eventType, false ).

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE unbind(@Nonnull IJson eventType, boolean _false);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param _false Unbinds the corresponding 'return false' function that was bound using .bind( eventType, false ).

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE unbind(@Nonnull IHCNode eventType, boolean _false);

/**
* @param eventType A string containing a JavaScript event type, such as click or submit.
* @param _false Unbinds the corresponding 'return false' function that was bound using .bind( eventType, false ).

* @return this
* @since jQuery 1.4.3
*/
@Nonnull IMPLTYPE unbind(@Nonnull String eventType, boolean _false);

@Nonnull IMPLTYPE unbind();

@Nonnull IMPLTYPE undelegate();

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull String eventType);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull IJSExpression handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJSExpression eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IJson eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull IHCNode eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param selector A selector which will be used to filter the event results.
* @param eventType A string containing a JavaScript event type, such as "click" or "keydown"
* @param handler A function to execute at the time the event is triggered.

* @return this
* @since jQuery 1.4.2
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String selector, @Nonnull String eventType, @Nonnull JSAnonymousFunction handler);

/**
* @param namespace A string containing a namespace to unbind all events from.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJSExpression namespace);

/**
* @param namespace A string containing a namespace to unbind all events from.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IJson namespace);

/**
* @param namespace A string containing a namespace to unbind all events from.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE undelegate(@Nonnull IHCNode namespace);

/**
* @param namespace A string containing a namespace to unbind all events from.

* @return this
* @since jQuery 1.6
*/
@Nonnull IMPLTYPE undelegate(@Nonnull String namespace);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE unload(@Nonnull IJSExpression handler);

/**
* @param handler A function to execute when the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
*/
@Deprecated
@Nonnull IMPLTYPE unload(@Nonnull JSAnonymousFunction handler);

/**
* @param eventData A plain object of data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE unload(@Nonnull IJSExpression eventData, @Nonnull IJSExpression handler);

/**
* @param eventData A plain object of data that will be passed to the event handler.
* @param handler A function to execute each time the event is triggered.

* @return this
* @deprecated Deprecated since jQuery 1.8
* @since jQuery 1.4.3
*/
@Deprecated
@Nonnull IMPLTYPE unload(@Nonnull IJSExpression eventData, @Nonnull JSAnonymousFunction handler);

@Nonnull IMPLTYPE unwrap();

@Nonnull IMPLTYPE val();

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull IJSExpression value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull IJson value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull IHCNode value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull String value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(int value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(long value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull BigInteger value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(double value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull BigDecimal value);

/**
* @param value A string of text, a number, or an array of strings corresponding to the value of each matched element to set as selected/checked.

* @return this
*/
@Nonnull IMPLTYPE val(@Nonnull JSArray value);

/**
* @param function A function returning the value to set. this is the current element. Receives the index position of the element in the set and the old value as arguments.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE val(@Nonnull JSAnonymousFunction function);

@Nonnull IMPLTYPE width();

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull IJSExpression value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull IJson value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull IHCNode value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull String value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(int value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(long value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull BigInteger value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(double value);

/**
* @param value An integer representing the number of pixels, or an integer along with an optional unit of measure appended (as a string).

* @return this
*/
@Nonnull IMPLTYPE width(@Nonnull BigDecimal value);

/**
* @param function A function returning the width to set. Receives the index position of the element in the set and the old width as arguments. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4.1
*/
@Nonnull IMPLTYPE width(@Nonnull JSAnonymousFunction function);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull IJSExpression wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull IJQuerySelector wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull JQuerySelectorList wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull EHTMLElement wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull ICSSClassProvider wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull IHCNode wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull String wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
*/
@Nonnull IMPLTYPE wrap(@Nonnull JQueryInvocation wrappingElement);

/**
* @param function A callback function returning the HTML content or jQuery object to wrap around the matched elements. Receives the index position of the element in the set as an argument. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE wrap(@Nonnull JSAnonymousFunction function);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull IJSExpression wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull IJQuerySelector wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull JQuerySelectorList wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull EHTMLElement wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull ICSSClassProvider wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull IHCNode wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull String wrappingElement);

/**
* @param wrappingElement A selector, element, HTML string, or jQuery object specifying the structure to wrap around the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull JQueryInvocation wrappingElement);

/**
* @param function A function that returns a structure to wrap around the matched elements. Receives the index position of the element in the set as an argument. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE wrapAll(@Nonnull JSAnonymousFunction function);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull IJSExpression wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull IHCNode wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull String wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull IJQuerySelector wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull JQuerySelectorList wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull EHTMLElement wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull ICSSClassProvider wrappingElement);

/**
* @param wrappingElement An HTML snippet, selector expression, jQuery object, or DOM element specifying the structure to wrap around the content of the matched elements.

* @return this
* @since jQuery 1.2
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull JQueryInvocation wrappingElement);

/**
* @param function A callback function which generates a structure to wrap around the content of the matched elements. Receives the index position of the element in the set as an argument. Within the function, this refers to the current element in the set.

* @return this
* @since jQuery 1.4
*/
@Nonnull IMPLTYPE wrapInner(@Nonnull JSAnonymousFunction function);

}
