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

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.w3c.dom.Node;

import com.helger.collection.pair.IPair;
import com.helger.collection.pair.Pair;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;
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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Wrapper around jQuery to allow for easy function calls
 *
 * @author Philip Helger
 */
@Immutable
@SuppressFBWarnings ("NM_METHOD_NAMING_CONVENTION")
public final class JQuery
{
  public static final AtomicBoolean s_aUseDollar = new AtomicBoolean (true);

  @PresentForCodeCoverage
  private static final JQuery s_aInstance = new JQuery ();

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
    s_aUseDollar.set (bUseDollar);
  }

  /**
   * @return <code>true</code> if "$" is used, <code>false</code> if "jQuery" is
   *         used for the global jQuery function
   */
  public static boolean isUseDollarForJQuery ()
  {
    return s_aUseDollar.get ();
  }

  /**
   * @return <code>$</code> or <code>jQuery</code> as a function
   */
  @Nonnull
  public static JSFunction jQueryFunction ()
  {
    return new JSFunction (isUseDollarForJQuery () ? "$" : "jQuery");
  }

  /**
   * @param aExpr
   *        Expression to use
   * @return a {@link JQueryInvocation} with an arbitrary expression.
   *         <code>$(<i>expr</i>)</code>
   */
  @Nonnull
  public static JQueryInvocation jQuery (@Nonnull final IJSExpression aExpr)
  {
    return new JQueryInvocation (jQueryFunction ()).arg (aExpr);
  }

  /**
   * @param sHTML
   *        HTML code to use
   * @return a {@link JQueryInvocation} with an HTML String.
   *         <code>$(<i>html</i>)</code>
   */
  @Nonnull
  public static JQueryInvocation jQuery (@Nonnull final String sHTML)
  {
    return jQuery (JSExpr.lit (sHTML));
  }

  /**
   * @param aHCNode
   *        HTML code to use
   * @return a {@link JQueryInvocation} with an HTML String.
   *         <code>$(<i>html</i>)</code>
   */
  @Nonnull
  public static JQueryInvocation jQuery (@Nonnull final IHCNode aHCNode)
  {
    return jQuery (HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  /**
   * @return a {@link JQueryInvocation} with an HTML document element.
   *         <code>$(document)</code>
   */
  @Nonnull
  public static JQueryInvocation jQueryDocument ()
  {
    return jQuery (JSHtml.document ());
  }

  /**
   * @return a {@link JQueryInvocation} with an HTML document element.
   *         <code>$(window)</code>
   */
  @Nonnull
  public static JQueryInvocation jQueryWindow ()
  {
    return jQuery (JSHtml.window ());
  }

  /**
   * @return a {@link JQueryInvocation} with <code>this</code>:
   *         <code>$(this)</code>
   */
  @Nonnull
  public static JQueryInvocation jQueryThis ()
  {
    return jQuery (JSExpr.THIS);
  }

  // Special cases

  /**
   * @param aArgs
   *        The array with the AJAX arguments
   * @return <code>$.ajax</code>
   */
  @Nonnull
  public static JQueryInvocation ajax (@Nonnull final JSAssocArray aArgs)
  {
    return ajax ().arg (aArgs);
  }

  /**
   * @param sEval
   *        JS code to be evaluated
   * @return <code>$.globalEval</code>
   */
  @Nonnull
  public static JQueryInvocation globalEval (@Nonnull final String sEval)
  {
    return globalEval ().arg (sEval);
  }

  /**
   * @param aEval
   *        JS code to be evaluated
   * @return <code>$.globalEval</code>
   */
  @Nonnull
  public static JQueryInvocation globalEval (@Nonnull final IJSExpression aEval)
  {
    return globalEval ().arg (aEval);
  }

  @Nonnull
  public static JQueryInvocation parseHTML (@Nonnull final Node aHTML)
  {
    return parseHTML (XMLWriter.getNodeAsString (aHTML));
  }

  @Nonnull
  public static JQueryInvocation parseHTML (@Nonnull final IMicroNode aHTML)
  {
    return parseHTML (MicroWriter.getNodeAsString (aHTML));
  }

  @Nonnull
  public static JQueryInvocation parseHTML (@Nonnull final IHCNode aHTML)
  {
    return parseHTML (HCRenderer.getAsHTMLStringWithoutNamespaces (aHTML));
  }

  @Nonnull
  public static JQueryInvocation parseHTML (@Nonnull final String sHTML)
  {
    return parseHTML (JSExpr.lit (sHTML));
  }

  @Nonnull
  public static JQueryInvocation parseHTML (@Nonnull final IJSExpression aExpr)
  {
    return parseHTML ().arg (aExpr);
  }

  @Nonnull
  public static JQueryInvocation parseJSON (@Nonnull final IJson aJson)
  {
    return parseJSON (aJson.getAsJsonString ());
  }

  @Nonnull
  public static JQueryInvocation parseJSON (@Nonnull final String sJSON)
  {
    return parseJSON (JSExpr.lit (sJSON));
  }

  @Nonnull
  public static JQueryInvocation parseJSON (@Nonnull final IJSExpression aExpr)
  {
    return parseJSON ().arg (aExpr);
  }

  @Nonnull
  public static JQueryInvocation parseXML (@Nonnull final Node aXML)
  {
    return parseXML (XMLWriter.getNodeAsString (aXML));
  }

  @Nonnull
  public static JQueryInvocation parseXML (@Nonnull final IMicroNode aXML)
  {
    return parseXML (MicroWriter.getNodeAsString (aXML));
  }

  @Nonnull
  public static JQueryInvocation parseXML (@Nonnull final String sXML)
  {
    return parseXML (JSExpr.lit (sXML));
  }

  @Nonnull
  public static JQueryInvocation parseXML (@Nonnull final IJSExpression aExpr)
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
  @Nonnull
  public static JQueryInvocation idRef (@Nonnull @Nonempty final String sID)
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
  @Nonnull
  public static JQueryInvocation idRef (@Nonnull final IJSExpression aID)
  {
    return JQuerySelector.id (aID).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDProvider
   *        The provider that has the ID to be selected. May not be
   *        <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#id')</code>
   */
  @Nonnull
  public static JQueryInvocation idRef (@Nonnull @Nonempty final IHasID <String> aIDProvider)
  {
    return JQuerySelector.id (aIDProvider).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aElement
   *        The element that has the ID to be selected. May not be
   *        <code>null</code>.
   * @return A jQuery invocation with the passed ID: <code>$('#id')</code>
   */
  @Nonnull
  public static JQueryInvocation idRef (@Nonnull @Nonempty final IHCHasID <?> aElement)
  {
    return JQuerySelector.id (aElement).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aIDs
   *        The IDs to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed IDs:
   *         <code>$('#id1,#id2,#id3')</code>
   */
  @Nonnull
  public static JQueryInvocation idRefMultiple (@Nonnull @Nonempty final String... aIDs)
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
   * @return A jQuery invocation with the passed IDs:
   *         <code>$('#id1,#id2,#id3')</code>
   */
  @Nonnull
  @SafeVarargs
  public static JQueryInvocation idRefMultiple (@Nonnull @Nonempty final IHasID <String>... aIDs)
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
   * @return A jQuery invocation with the passed IDs:
   *         <code>$('#id1,#id2,#id3')</code>
   */
  @Nonnull
  public static JQueryInvocation idRefMultiple (@Nonnull @Nonempty final IJSExpression... aIDs)
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
   * @return A jQuery invocation with the passed IDs:
   *         <code>$('#id1,#id2,#id3')</code>
   */
  @Nonnull
  public static JQueryInvocation idRefMultiple (@Nonnull @Nonempty final IHCHasID <?>... aIDs)
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
   * @return A jQuery invocation with the passed IDs:
   *         <code>$('#id1,#id2,#id3')</code>
   */
  @Nonnull
  public static JQueryInvocation idRefMultiple (@Nonnull @Nonempty final Iterable <String> aIDs)
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
  @Nonnull
  public static JQueryInvocation classRef (@Nonnull final ICSSClassProvider aCSSClass)
  {
    return JQuerySelector.clazz (aCSSClass).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param aCSSClasses
   *        The classes to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed classes:
   *         <code>$('.class1,.class2,.class3')</code>
   */
  @Nonnull
  public static JQueryInvocation classRefMultiple (@Nonnull @Nonempty final ICSSClassProvider... aCSSClasses)
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
   * @return A jQuery invocation with the passed classes:
   *         <code>$('.class1,.class2,.class3')</code>
   */
  @Nonnull
  public static JQueryInvocation classRefMultiple (@Nonnull @Nonempty final Iterable <? extends ICSSClassProvider> aCSSClasses)
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
   * @return A jQuery invocation with the passed element:
   *         <code>$('element')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameRef (@Nonnull final EHTMLElement eElement)
  {
    return JQuerySelector.element (eElement).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element:
   *         <code>$('element')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameRef (@Nonnull @Nonempty final String sElementName)
  {
    return JQuerySelector.element (sElementName).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aSelector
   *        The additional selector to be appended to the element. E.g an ID or
   *        a class selector. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and selector:
   *         <code>$('element#id')</code> or <code>$('element.class')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameRef (@Nonnull final EHTMLElement eElement,
                                                 @Nonnull final IJQuerySelector aSelector)
  {
    return JQuerySelector.element (eElement).chain (aSelector).invoke ();
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aSelector
   *        The additional selector to be appended to the element. E.g an ID or
   *        a class selector. May not be <code>null</code>.
   * @return A jQuery invocation with the passed element and selector:
   *         <code>$('element#id')</code> or <code>$('element.class')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameRef (@Nonnull @Nonempty final String sElementName,
                                                 @Nonnull final IJQuerySelector aSelector)
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
   * @return A jQuery invocation with the passed element and ID:
   *         <code>$('element#id')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameWithIDRef (@Nonnull final EHTMLElement eElement,
                                                       @Nonnull @Nonempty final String sID)
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
   * @return A jQuery invocation with the passed element and ID:
   *         <code>$('element#id')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameWithIDRef (@Nonnull @Nonempty final String sElementName,
                                                       @Nonnull @Nonempty final String sID)
  {
    return elementNameRef (sElementName, JQuerySelector.id (sID));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param eElement
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aCSSClass
   *        The class to be appended to the element. May not be
   *        <code>null</code>.
   * @return A jQuery invocation with the passed element and class:
   *         <code>$('element.class')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameWithClassRef (@Nonnull final EHTMLElement eElement,
                                                          @Nonnull final ICSSClassProvider aCSSClass)
  {
    return elementNameRef (eElement, JQuerySelector.clazz (aCSSClass));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sElementName
   *        The HTML element to be selected. May not be <code>null</code>.
   * @param aCSSClass
   *        The class to be appended to the element. May not be
   *        <code>null</code>.
   * @return A jQuery invocation with the passed element and class:
   *         <code>$('element.class')</code>
   */
  @Nonnull
  public static JQueryInvocation elementNameWithClassRef (@Nonnull @Nonempty final String sElementName,
                                                          @Nonnull final ICSSClassProvider aCSSClass)
  {
    return elementNameRef (sElementName, JQuerySelector.clazz (aCSSClass));
  }

  /**
   * Get the result of a jQuery selection
   *
   * @param sNameAttrValue
   *        The name of the HTML elements to be selected. May not be
   *        <code>null</code>.
   * @return A jQuery invocation with the passed element:
   *         <code>$('[name=value]')</code>
   */
  @Nonnull
  public static JQueryInvocation nameAttrRef (@Nonnull @Nonempty final String sNameAttrValue)
  {
    ValueEnforcer.notEmpty (sNameAttrValue, "NameAttrValue");
    return JQuerySelector.nameAttr (sNameAttrValue).invoke ();
  }

  /**
   * @return A pair consisting of the invocation and the anonymous function that
   *         can be filled with code to be executed.
   */
  @Nonnull
  public static IPair <JQueryInvocation, JSAnonymousFunction> onDocumentReady ()
  {
    final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
    final JQueryInvocation aInvocation = jQueryDocument ().ready (aAnonFunction);
    return Pair.create (aInvocation, aAnonFunction);
  }

  /**
   * Add onDocumentReady call with a single statement
   *
   * @param aJSCodeProvider
   *        The statement to be executed on document ready
   * @return The invocation object
   */
  @Nonnull
  public static JQueryInvocation onDocumentReady (@Nonnull final IHasJSCode aJSCodeProvider)
  {
    final JSAnonymousFunction aAnonFunction = new JSAnonymousFunction ();
    aAnonFunction.body ().add (aJSCodeProvider);
    return jQueryDocument ().ready (aAnonFunction);
  }

  // Everything starting from here is automatically generated:
  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.Callbacks()</code> with return type Callbacks
   * @since jQuery 1.7
   */
  @Nonnull
  public static JQueryInvocation Callbacks ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "Callbacks");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.Deferred()</code> with return type Deferred
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation Deferred ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "Deferred");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.ajax()</code> with return type jqXHR
   */
  @Nonnull
  public static JQueryInvocation ajax ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajax");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.ajaxPrefilter()</code> with return type undefined
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation ajaxPrefilter ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxPrefilter");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.ajaxSetup()</code> with return type
   * @since jQuery 1.1
   */
  @Nonnull
  public static JQueryInvocation ajaxSetup ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxSetup");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.ajaxTransport()</code> with return type undefined
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation ajaxTransport ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "ajaxTransport");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.contains()</code> with return type Boolean
   * @since jQuery 1.4
   */
  @Nonnull
  public static JQueryInvocation contains ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "contains");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.data()</code> with return type Object
   */
  @Nonnull
  public static JQueryInvocation data ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "data");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.dequeue()</code> with return type undefined
   * @since jQuery 1.3
   */
  @Nonnull
  public static JQueryInvocation dequeue ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "dequeue");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.each()</code> with return type Object
   */
  @Nonnull
  public static JQueryInvocation each ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "each");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.error()</code> with return type
   * @since jQuery 1.4.1
   */
  @Nonnull
  public static JQueryInvocation error ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "error");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.extend()</code> with return type Object
   */
  @Nonnull
  public static JQueryInvocation extend ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "extend");
  }

  /**
   * @return The reference of the static jQuery field <code>jQuery.fn</code>
   */
  @Nonnull
  public static JSFieldRef fn ()
  {
    return JQueryProperty.jQueryField ().ref ("fn");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.fn.extend()</code> with return type Object
   */
  @Nonnull
  public static JQueryInvocation fn_extend ()
  {
    return new JQueryInvocation (fn (), "extend");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.get()</code> with return type jqXHR
   */
  @Nonnull
  public static JQueryInvocation get ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "get");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.getJSON()</code> with return type jqXHR
   */
  @Nonnull
  public static JQueryInvocation getJSON ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "getJSON");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.getScript()</code> with return type jqXHR
   */
  @Nonnull
  public static JQueryInvocation getScript ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "getScript");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.globalEval()</code> with return type
   * @since jQuery 1.0.4
   */
  @Nonnull
  public static JQueryInvocation globalEval ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "globalEval");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.grep()</code> with return type Array
   */
  @Nonnull
  public static JQueryInvocation grep ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "grep");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.hasData()</code> with return type Boolean
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation hasData ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "hasData");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.holdReady()</code> with return type undefined
   * @since jQuery 1.6
   */
  @Nonnull
  public static JQueryInvocation holdReady ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "holdReady");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.inArray()</code> with return type Number
   * @since jQuery 1.2
   */
  @Nonnull
  public static JQueryInvocation inArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "inArray");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isArray()</code> with return type boolean
   * @since jQuery 1.3
   */
  @Nonnull
  public static JQueryInvocation isArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isArray");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isEmptyObject()</code> with return type Boolean
   * @since jQuery 1.4
   */
  @Nonnull
  public static JQueryInvocation isEmptyObject ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isEmptyObject");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isFunction()</code> with return type boolean
   * @since jQuery 1.2
   */
  @Nonnull
  public static JQueryInvocation isFunction ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isFunction");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isNumeric()</code> with return type Boolean
   * @since jQuery 1.7
   */
  @Nonnull
  public static JQueryInvocation isNumeric ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isNumeric");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isPlainObject()</code> with return type Boolean
   * @since jQuery 1.4
   */
  @Nonnull
  public static JQueryInvocation isPlainObject ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isPlainObject");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isWindow()</code> with return type boolean
   * @since jQuery 1.4.3
   */
  @Nonnull
  public static JQueryInvocation isWindow ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isWindow");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.isXMLDoc()</code> with return type Boolean
   * @since jQuery 1.1.4
   */
  @Nonnull
  public static JQueryInvocation isXMLDoc ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "isXMLDoc");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.makeArray()</code> with return type Array
   * @since jQuery 1.2
   */
  @Nonnull
  public static JQueryInvocation makeArray ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "makeArray");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.map()</code> with return type Array
   */
  @Nonnull
  public static JQueryInvocation map ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "map");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.merge()</code> with return type Array
   */
  @Nonnull
  public static JQueryInvocation merge ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "merge");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.noConflict()</code> with return type Object
   */
  @Nonnull
  public static JQueryInvocation noConflict ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "noConflict");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.noop()</code> with return type undefined
   * @since jQuery 1.4
   */
  @Nonnull
  public static JQueryInvocation noop ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "noop");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.now()</code> with return type Number
   * @since jQuery 1.4.3
   */
  @Nonnull
  public static JQueryInvocation now ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "now");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.param()</code> with return type String
   */
  @Nonnull
  public static JQueryInvocation param ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "param");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.parseHTML()</code> with return type Array
   * @since jQuery 1.8
   */
  @Nonnull
  public static JQueryInvocation parseHTML ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseHTML");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.parseJSON()</code> with return type Object
   * @since jQuery 1.4.1
   */
  @Nonnull
  public static JQueryInvocation parseJSON ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseJSON");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.parseXML()</code> with return type XMLDocument
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation parseXML ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "parseXML");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.post()</code> with return type jqXHR
   */
  @Nonnull
  public static JQueryInvocation post ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "post");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.proxy()</code> with return type Function
   */
  @Nonnull
  public static JQueryInvocation proxy ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "proxy");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.queue()</code> with return type Array or jQuery
   */
  @Nonnull
  public static JQueryInvocation queue ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "queue");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.removeData()</code> with return type jQuery
   * @since jQuery 1.2.3
   */
  @Nonnull
  public static JQueryInvocation removeData ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "removeData");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.trim()</code> with return type String
   */
  @Nonnull
  public static JQueryInvocation trim ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "trim");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.type()</code> with return type String
   * @since jQuery 1.4.3
   */
  @Nonnull
  public static JQueryInvocation type ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "type");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.unique()</code> with return type Array
   * @since jQuery 1.1.3
   */
  @Nonnull
  public static JQueryInvocation unique ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "unique");
  }

  /**
   * @return The invocation of the static jQuery function
   *         <code>jQuery.when()</code> with return type Promise
   * @since jQuery 1.5
   */
  @Nonnull
  public static JQueryInvocation when ()
  {
    return new JQueryInvocation (JQueryProperty.jQueryField (), "when");
  }
}
