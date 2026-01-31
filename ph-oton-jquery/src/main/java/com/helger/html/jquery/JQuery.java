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

import java.util.concurrent.atomic.AtomicBoolean;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.w3c.dom.Node;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSFunction;
import com.helger.html.jscode.html.JSHtml;
import com.helger.json.IJson;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.XMLWriter;

/**
 * Wrapper around jQuery to allow for easy function calls
 *
 * @author Philip Helger
 */
@Immutable
public final class JQuery
{
  private static final AtomicBoolean USE_DOLLAR = new AtomicBoolean (true);

  @PresentForCodeCoverage
  private static final JQuery INSTANCE = new JQuery ();

  private JQuery ()
  {}

  /**
   * Globally decide, whether to use "$" or "jQuery" to access jQuery function
   *
   * @param bUseDollar
   *        <code>true</code> to use "$", <code>false</code> to use "jQuery"
   */
  public static void setUseDollarForJQuery (final boolean bUseDollar)
  {
    USE_DOLLAR.set (bUseDollar);
  }

  /**
   * @return <code>true</code> if "$" is used, <code>false</code> if "jQuery" is used for the global
   *         jQuery function
   */
  public static boolean isUseDollarForJQuery ()
  {
    return USE_DOLLAR.get ();
  }

  /**
   * @return <code>$</code> or <code>jQuery</code> as a function
   */
  @NonNull
  public static JSFunction jQueryFunction ()
  {
    return new JSFunction (isUseDollarForJQuery () ? "$" : "jQuery");
  }

  /**
   * @param aExpr
   *        Expression to use
   * @return a {@link JQueryInvocation} with an arbitrary expression. <code>$(<i>expr</i>)</code>
   */
  @NonNull
  public static JQueryInvocation jQuery (@NonNull final IJSExpression aExpr)
  {
    return new JQueryInvocation (jQueryFunction ()).arg (aExpr);
  }

  /**
   * @param sHTML
   *        HTML code to use
   * @return a {@link JQueryInvocation} with an HTML String. <code>$(<i>html</i>)</code>
   */
  @NonNull
  public static JQueryInvocation jQuery (@NonNull final String sHTML)
  {
    return jQuery (JSExpr.lit (sHTML));
  }

  /**
   * @param aHCNode
   *        HTML code to use
   * @return a {@link JQueryInvocation} with an HTML String. <code>$(<i>html</i>)</code>
   */
  @NonNull
  public static JQueryInvocation jQuery (@NonNull final IHCNode aHCNode)
  {
    // Eventually the nonce might make sense here
    return jQuery (HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  /**
   * @return a {@link JQueryInvocation} with an HTML document element. <code>$(document)</code>
   */
  @NonNull
  public static JQueryInvocation jQueryDocument ()
  {
    return jQuery (JSHtml.document ());
  }

  /**
   * @return a {@link JQueryInvocation} with an HTML document element. <code>$(window)</code>
   */
  @NonNull
  public static JQueryInvocation jQueryWindow ()
  {
    return jQuery (JSHtml.window ());
  }

  /**
   * @return a {@link JQueryInvocation} with <code>this</code>: <code>$(this)</code>
   */
  @NonNull
  public static JQueryInvocation jQueryThis ()
  {
    return jQuery (JSExpr.THIS);
  }

  // Special cases

  /**
   * @param aURL
   *        The AJAX target URL. May not be <code>null</code>.
   * @param aArgs
   *        The array with the AJAX arguments
   * @return <code>$.ajax</code>
   * @since 9.3.1
   */
  @NonNull
  public static JQueryInvocation ajax (@Nullable final IJSExpression aURL, @Nullable final JSAssocArray aArgs)
  {
    final JQueryInvocation ret = ajax ().arg (aURL != null ? aURL : JSExpr.NULL);
    if (aArgs != null)
      ret.arg (aArgs);
    return ret;
  }

  /**
   * @param sEval
   *        JS code to be evaluated
   * @return <code>$.globalEval</code>
   */
  @NonNull
  public static JQueryInvocation globalEval (@NonNull final String sEval)
  {
    return globalEval ().arg (sEval);
  }

  /**
   * @param aEval
   *        JS code to be evaluated
   * @return <code>$.globalEval</code>
   */
  @NonNull
  public static JQueryInvocation globalEval (@NonNull final IJSExpression aEval)
  {
    return globalEval ().arg (aEval);
  }

  @NonNull
  public static JQueryInvocation parseHTML (@NonNull final Node aHTML)
  {
    return parseHTML (XMLWriter.getNodeAsString (aHTML));
  }

  @NonNull
  public static JQueryInvocation parseHTML (@NonNull final IMicroNode aHTML)
  {
    return parseHTML (MicroWriter.getNodeAsString (aHTML));
  }

  @NonNull
  public static JQueryInvocation parseHTML (@NonNull final IHCNode aHTML)
  {
    return parseHTML (HCRenderer.getAsHTMLStringWithoutNamespaces (aHTML));
  }

  @NonNull
  public static JQueryInvocation parseHTML (@NonNull final String sHTML)
  {
    return parseHTML (JSExpr.lit (sHTML));
  }

  @NonNull
  public static JQueryInvocation parseHTML (@NonNull final IJSExpression aExpr)
  {
    return parseHTML ().arg (aExpr);
  }

  @NonNull
  public static JQueryInvocation parseJSON (@NonNull final IJson aJson)
  {
    return parseJSON (aJson.getAsJsonString ());
  }

  @NonNull
  public static JQueryInvocation parseJSON (@NonNull final String sJSON)
  {
    return parseJSON (JSExpr.lit (sJSON));
  }

  @NonNull
  public static JQueryInvocation parseJSON (@NonNull final IJSExpression aExpr)
  {
    return parseJSON ().arg (aExpr);
  }

  @NonNull
  public static JQueryInvocation parseXML (@NonNull final Node aXML)
  {
    return parseXML (XMLWriter.getNodeAsString (aXML));
  }

  @NonNull
  public static JQueryInvocation parseXML (@NonNull final IMicroNode aXML)
  {
    return parseXML (MicroWriter.getNodeAsString (aXML));
  }

  @NonNull
  public static JQueryInvocation parseXML (@NonNull final String sXML)
  {
    return parseXML (JSExpr.lit (sXML));
  }

  @NonNull
  public static JQueryInvocation parseXML (@NonNull final IJSExpression aExpr)
  {
    return parseXML ().arg (aExpr);
  }

  // selectors start here

  /**
   * Get the result of a jQuery selection
   *
   * @param sID
   *        The ID to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#id')</code>
   */
  @NonNull
  public static JQueryInvocation idRef (@NonNull @Nonempty final String sID)
  {
    return JQuerySelector.id (sID).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aID
   *        The ID to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#'+id)</code>
   */
  @NonNull
  public static JQueryInvocation idRef (@NonNull final IJSExpression aID)
  {
    return JQuerySelector.id (aID).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDProvider
   *        The provider that has the ID to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#id')</code>
   */
  @NonNull
  public static JQueryInvocation idRef (@NonNull @Nonempty final IHasID <String> aIDProvider)
  {
    return JQuerySelector.id (aIDProvider).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aElement
   *        The element that has the ID to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#id')</code>
   */
  @NonNull
  public static JQueryInvocation idRef (@NonNull @Nonempty final IHCHasID <?> aElement)
  {
    return JQuerySelector.id (aElement).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The IDs to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs: <code>$('#id1,#id2,#id3')</code>
   */
  @NonNull
  public static JQueryInvocation idRefMultiple (@NonNull @Nonempty final String... aIDs)
  {
    ValueEnforcer.notEmpty (aIDs, "IDs");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final String sID : aIDs)
      aSelectors.add (JQuerySelector.id (sID));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The IDs to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs: <code>$('#id1,#id2,#id3')</code>
   */
  @NonNull
  @SafeVarargs
  public static JQueryInvocation idRefMultiple (@NonNull @Nonempty final IHasID <String>... aIDs)
  {
    ValueEnforcer.notEmptyNoNullValue (aIDs, "IDs");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final IHasID <String> aID : aIDs)
      aSelectors.add (JQuerySelector.id (aID));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The IDs to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs: <code>$('#id1,#id2,#id3')</code>
   */
  @NonNull
  public static JQueryInvocation idRefMultiple (@NonNull @Nonempty final IJSExpression... aIDs)
  {
    ValueEnforcer.notEmptyNoNullValue (aIDs, "IDs");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final IJSExpression aID : aIDs)
      aSelectors.add (JQuerySelector.id (aID));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The elements to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs: <code>$('#id1,#id2,#id3')</code>
   */
  @NonNull
  public static JQueryInvocation idRefMultiple (@NonNull @Nonempty final IHCHasID <?>... aIDs)
  {
    ValueEnforcer.notEmptyNoNullValue (aIDs, "IDs");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final IHCHasID <?> aID : aIDs)
      aSelectors.add (JQuerySelector.id (aID));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The IDs to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs: <code>$('#id1,#id2,#id3')</code>
   */
  @NonNull
  public static JQueryInvocation idRefMultiple (@NonNull @Nonempty final Iterable <String> aIDs)
  {
    ValueEnforcer.notEmpty (aIDs, "IDs");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final String sID : aIDs)
      aSelectors.add (JQuerySelector.id (sID));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aCSSClass
   *        The class to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed class: <code>$('.class')</code>
   */
  @NonNull
  public static JQueryInvocation classRef (@NonNull final ICSSClassProvider aCSSClass)
  {
    return JQuerySelector.clazz (aCSSClass).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aCSSClasses
   *        The classes to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed classes: <code>$('.class1,.class2,.class3')</code>
   */
  @NonNull
  public static JQueryInvocation classRefMultiple (@NonNull @Nonempty final ICSSClassProvider... aCSSClasses)
  {
    ValueEnforcer.notEmpty (aCSSClasses, "CSSClasses");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final ICSSClassProvider aCSSClass : aCSSClasses)
      aSelectors.add (JQuerySelector.clazz (aCSSClass));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aCSSClasses
   *        The classes to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed classes: <code>$('.class1,.class2,.class3')</code>
   */
  @NonNull
  public static JQueryInvocation classRefMultiple (@NonNull @Nonempty final Iterable <? extends ICSSClassProvider> aCSSClasses)
  {
    ValueEnforcer.notEmpty (aCSSClasses, "CSSClasses");

    final ICommonsList <IJQuerySelector> aSelectors = new CommonsArrayList <> ();
    for (final ICSSClassProvider aCSSClass : aCSSClasses)
      aSelectors.add (JQuerySelector.clazz (aCSSClass));
    return JQuerySelector.multiple (aSelectors).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element: <code>$('element')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameRef (@NonNull final EHTMLElement eElement)
  {
    return JQuerySelector.element (eElement).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element: <code>$('element')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameRef (@NonNull @Nonempty final String sElementName)
  {
    return JQuerySelector.element (sElementName).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aSelector
   *        The additional selector to be appended to the element. E.g an ID or a class selector.
   *        May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and selector: <code>$('element#id')</code>
   *         or <code>$('element.class')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameRef (@NonNull final EHTMLElement eElement,
                                                 @NonNull final IJQuerySelector aSelector)
  {
    return JQuerySelector.element (eElement).chain (aSelector).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aSelector
   *        The additional selector to be appended to the element. E.g an ID or a class selector.
   *        May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and selector: <code>$('element#id')</code>
   *         or <code>$('element.class')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameRef (@NonNull @Nonempty final String sElementName,
                                                 @NonNull final IJQuerySelector aSelector)
  {
    return JQuerySelector.element (sElementName).chain (aSelector).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param sID
   *        The ID to be appended to the element. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and ID: <code>$('element#id')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameWithIDRef (@NonNull final EHTMLElement eElement,
                                                       @NonNull @Nonempty final String sID)
  {
    return elementNameRef (eElement, JQuerySelector.id (sID));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param sID
   *        The ID to be appended to the element. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and ID: <code>$('element#id')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameWithIDRef (@NonNull @Nonempty final String sElementName,
                                                       @NonNull @Nonempty final String sID)
  {
    return elementNameRef (sElementName, JQuerySelector.id (sID));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aCSSClass
   *        The class to be appended to the element. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and class: <code>$('element.class')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameWithClassRef (@NonNull final EHTMLElement eElement,
                                                          @NonNull final ICSSClassProvider aCSSClass)
  {
    return elementNameRef (eElement, JQuerySelector.clazz (aCSSClass));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aCSSClass
   *        The class to be appended to the element. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and class: <code>$('element.class')</code>
   */
  @NonNull
  public static JQueryInvocation elementNameWithClassRef (@NonNull @Nonempty final String sElementName,
                                                          @NonNull final ICSSClassProvider aCSSClass)
  {
    return elementNameRef (sElementName, JQuerySelector.clazz (aCSSClass));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sNameAttrValue
   *        The name of the HTML elements to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element: <code>$('[name=value]')</code>
   */
  @NonNull
  public static JQueryInvocation nameAttrRef (@NonNull @Nonempty final String sNameAttrValue)
  {
    ValueEnforcer.notEmpty (sNameAttrValue, "NameAttrValue");
    return JQuerySelector.nameAttr (sNameAttrValue).invoke ();
  }

  public static class OnDocumentReadyInvocation
  {
    private final JQueryInvocation m_aInvocation;
    private final JSAnonymousFunction m_aAnonFunction;

    public OnDocumentReadyInvocation (@NonNull final JQueryInvocation aInvocation,
                                      @NonNull final JSAnonymousFunction aAnonFunction)
    {
      m_aInvocation = aInvocation;
      m_aAnonFunction = aAnonFunction;
    }

    public JQueryInvocation getInvocation ()
    {
      return m_aInvocation;
    }

    public JSAnonymousFunction getAnonFunction ()
    {
      return m_aAnonFunction;
    }
  }

  /**
   * @return A pair consisting of the invocation and the anonymous function that can be filled with
   *         code to be executed.
   */
  @NonNull
  public static OnDocumentReadyInvocation onDocumentReady ()
  {
    final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
    final JQueryInvocation aInvocation = jQueryDocument ().ready (aAnonFunction);
    return new OnDocumentReadyInvocation (aInvocation, aAnonFunction);
  }

  /**
   * Add onDocumentReady call with a single statement
   *
   * @param aJSCodeProvider
   *        The statement to be executed on document ready
   * @return The invocation object
   */
  @NonNull
  public static JQueryInvocation onDocumentReady (@NonNull final IHasJSCode aJSCodeProvider)
  {
    final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
    aAnonFunction.body ().add (aJSCodeProvider);
    return jQueryDocument ().ready (aAnonFunction);
  }

  // Everything starting from here is automatically generated:
  /**
   * @return The invocation of the static jQuery function <code>jQuery.Callbacks()</code> with
   *         return type Callbacks
   * @since jQuery 1.7
   */
  @NonNull
  public static JQueryInvocation Callbacks ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "Callbacks");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.Deferred()</code> with return
   *         type Deferred
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation Deferred ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "Deferred");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.ajax()</code> with return
   *         type jqXHR
   */
  @NonNull
  public static JQueryInvocation ajax ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajax");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.ajaxPrefilter()</code> with
   *         return type undefined
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation ajaxPrefilter ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxPrefilter");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.ajaxSetup()</code> with
   *         return type
   * @since jQuery 1.1
   */
  @NonNull
  public static JQueryInvocation ajaxSetup ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxSetup");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.ajaxTransport()</code> with
   *         return type undefined
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation ajaxTransport ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxTransport");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.contains()</code> with return
   *         type Boolean
   * @since jQuery 1.4
   */
  @NonNull
  public static JQueryInvocation contains ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "contains");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.data()</code> with return
   *         type Object
   */
  @NonNull
  public static JQueryInvocation data ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "data");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.dequeue()</code> with return
   *         type undefined
   * @since jQuery 1.3
   */
  @NonNull
  public static JQueryInvocation dequeue ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "dequeue");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.each()</code> with return
   *         type Object
   */
  @NonNull
  public static JQueryInvocation each ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "each");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.error()</code> with return
   *         type
   * @since jQuery 1.4.1
   */
  @NonNull
  public static JQueryInvocation error ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "error");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.extend()</code> with return
   *         type Object
   */
  @NonNull
  public static JQueryInvocation extend ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "extend");
  }

  /**
   * @return The reference of the static jQuery field <code>jQuery.fn</code>
   */
  @NonNull
  public static JSFieldRef fn ()
  {
    return JQueryProperty.jQueryField ().ref ("fn");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.fn.extend()</code> with
   *         return type Object
   */
  @NonNull
  public static JQueryInvocation fn_extend ()
  {
    return new JQueryInvocation (fn (), "extend");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.get()</code> with return type
   *         jqXHR
   */
  @NonNull
  public static JQueryInvocation get ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "get");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.getJSON()</code> with return
   *         type jqXHR
   */
  @NonNull
  public static JQueryInvocation getJSON ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "getJSON");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.getScript()</code> with
   *         return type jqXHR
   */
  @NonNull
  public static JQueryInvocation getScript ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "getScript");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.globalEval()</code> with
   *         return type
   * @since jQuery 1.0.4
   */
  @NonNull
  public static JQueryInvocation globalEval ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "globalEval");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.grep()</code> with return
   *         type Array
   */
  @NonNull
  public static JQueryInvocation grep ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "grep");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.hasData()</code> with return
   *         type Boolean
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation hasData ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "hasData");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.holdReady()</code> with
   *         return type undefined
   * @since jQuery 1.6
   */
  @NonNull
  public static JQueryInvocation holdReady ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "holdReady");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.inArray()</code> with return
   *         type Number
   * @since jQuery 1.2
   */
  @NonNull
  public static JQueryInvocation inArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "inArray");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isArray()</code> with return
   *         type boolean
   * @since jQuery 1.3
   */
  @NonNull
  public static JQueryInvocation isArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isArray");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isEmptyObject()</code> with
   *         return type Boolean
   * @since jQuery 1.4
   */
  @NonNull
  public static JQueryInvocation isEmptyObject ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isEmptyObject");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isFunction()</code> with
   *         return type boolean
   * @since jQuery 1.2
   */
  @NonNull
  public static JQueryInvocation isFunction ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isFunction");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isNumeric()</code> with
   *         return type Boolean
   * @since jQuery 1.7
   */
  @NonNull
  public static JQueryInvocation isNumeric ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isNumeric");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isPlainObject()</code> with
   *         return type Boolean
   * @since jQuery 1.4
   */
  @NonNull
  public static JQueryInvocation isPlainObject ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isPlainObject");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isWindow()</code> with return
   *         type boolean
   * @since jQuery 1.4.3
   */
  @NonNull
  public static JQueryInvocation isWindow ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isWindow");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.isXMLDoc()</code> with return
   *         type Boolean
   * @since jQuery 1.1.4
   */
  @NonNull
  public static JQueryInvocation isXMLDoc ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isXMLDoc");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.makeArray()</code> with
   *         return type Array
   * @since jQuery 1.2
   */
  @NonNull
  public static JQueryInvocation makeArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "makeArray");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.map()</code> with return type
   *         Array
   */
  @NonNull
  public static JQueryInvocation map ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "map");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.merge()</code> with return
   *         type Array
   */
  @NonNull
  public static JQueryInvocation merge ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "merge");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.noConflict()</code> with
   *         return type Object
   */
  @NonNull
  public static JQueryInvocation noConflict ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "noConflict");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.noop()</code> with return
   *         type undefined
   * @since jQuery 1.4
   */
  @NonNull
  public static JQueryInvocation noop ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "noop");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.now()</code> with return type
   *         Number
   * @since jQuery 1.4.3
   */
  @NonNull
  public static JQueryInvocation now ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "now");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.param()</code> with return
   *         type String
   */
  @NonNull
  public static JQueryInvocation param ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "param");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.parseHTML()</code> with
   *         return type Array
   * @since jQuery 1.8
   */
  @NonNull
  public static JQueryInvocation parseHTML ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseHTML");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.parseJSON()</code> with
   *         return type Object
   * @since jQuery 1.4.1
   */
  @NonNull
  public static JQueryInvocation parseJSON ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseJSON");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.parseXML()</code> with return
   *         type XMLDocument
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation parseXML ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseXML");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.post()</code> with return
   *         type jqXHR
   */
  @NonNull
  public static JQueryInvocation post ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "post");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.proxy()</code> with return
   *         type Function
   */
  @NonNull
  public static JQueryInvocation proxy ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "proxy");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.queue()</code> with return
   *         type Array or jQuery
   */
  @NonNull
  public static JQueryInvocation queue ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "queue");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.removeData()</code> with
   *         return type jQuery
   * @since jQuery 1.2.3
   */
  @NonNull
  public static JQueryInvocation removeData ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "removeData");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.trim()</code> with return
   *         type String
   */
  @NonNull
  public static JQueryInvocation trim ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "trim");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.type()</code> with return
   *         type String
   * @since jQuery 1.4.3
   */
  @NonNull
  public static JQueryInvocation type ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "type");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.unique()</code> with return
   *         type Array
   * @since jQuery 1.1.3
   */
  @NonNull
  public static JQueryInvocation unique ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "unique");
  }

  /**
   * @return The invocation of the static jQuery function <code>jQuery.when()</code> with return
   *         type Promise
   * @since jQuery 1.5
   */
  @NonNull
  public static JQueryInvocation when ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "when");
  }
}
