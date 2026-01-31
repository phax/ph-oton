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
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.CheckReturnValue;
import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.base.array.ArrayHelper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.cache.regex.RegExHelper;
import com.helger.collection.CollectionFind;
import com.helger.collection.CollectionHelper;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSStringLiteral;
import com.helger.xml.microdom.IMicroQName;

@Immutable
@CodingStyleguideUnaware
public final class JQuerySelector implements IJQuerySelector
{
  public static final IJQuerySelector all = new JQuerySelector ("*");
  // @since jQuery 1.2
  public static final IJQuerySelector animated = new JQuerySelector (":animated");
  public static final IJQuerySelector button = new JQuerySelector (":button");
  public static final IJQuerySelector checkbox = new JQuerySelector (":checkbox");
  public static final IJQuerySelector checked = new JQuerySelector (":checked");
  public static final IJQuerySelector disabled = new JQuerySelector (":disabled");
  public static final IJQuerySelector empty = new JQuerySelector (":empty");
  public static final IJQuerySelector enabled = new JQuerySelector (":enabled");
  public static final IJQuerySelector even = new JQuerySelector (":even");
  public static final IJQuerySelector file = new JQuerySelector (":file");
  public static final IJQuerySelector first = new JQuerySelector (":first");
  // @since jQuery 1.1.4
  public static final IJQuerySelector first_child = new JQuerySelector (":first-child");
  // @since jQuery 1.9
  public static final IJQuerySelector first_of_type = new JQuerySelector (":first-of-type");
  // @since jQuery 1.6
  public static final IJQuerySelector focus = new JQuerySelector (":focus");
  // @since jQuery 1.2
  public static final IJQuerySelector header = new JQuerySelector (":header");
  public static final IJQuerySelector hidden = new JQuerySelector (":hidden");
  public static final IJQuerySelector image = new JQuerySelector (":image");
  public static final IJQuerySelector input = new JQuerySelector (":input");
  public static final IJQuerySelector last = new JQuerySelector (":last");
  // @since jQuery 1.1.4
  public static final IJQuerySelector last_child = new JQuerySelector (":last-child");
  // @since jQuery 1.9
  public static final IJQuerySelector last_of_type = new JQuerySelector (":last-of-type");
  public static final IJQuerySelector odd = new JQuerySelector (":odd");
  // @since jQuery 1.1.4
  public static final IJQuerySelector only_child = new JQuerySelector (":only-child");
  // @since jQuery 1.9
  public static final IJQuerySelector only_of_type = new JQuerySelector (":only-of-type");
  public static final IJQuerySelector parent = new JQuerySelector (":parent");
  public static final IJQuerySelector password = new JQuerySelector (":password");
  public static final IJQuerySelector radio = new JQuerySelector (":radio");
  public static final IJQuerySelector reset = new JQuerySelector (":reset");
  // @since jQuery 1.9
  public static final IJQuerySelector root = new JQuerySelector (":root");
  public static final IJQuerySelector selected = new JQuerySelector (":selected");
  public static final IJQuerySelector submit = new JQuerySelector (":submit");
  // @since jQuery 1.9
  public static final IJQuerySelector target = new JQuerySelector (":target");
  public static final IJQuerySelector text = new JQuerySelector (":text");
  public static final IJQuerySelector visible = new JQuerySelector (":visible");

  private static final char [] ILLEGAL_JQUERY_ID_CHARS = { ':', '.' };

  private final IJSExpression m_aExpr;

  private JQuerySelector (@NonNull @Nonempty final String sSelectorName, @NonNull final IJSExpression aSelectorExpr)
  {
    // Is used as a literal!!
    this (sSelectorName +
          '(' +
          aSelectorExpr.getJSCode (HCSettings.getConversionSettings ().getJSWriterSettings ()) +
          ')');
    ValueEnforcer.notEmpty (sSelectorName, "SelectorName");
  }

  private JQuerySelector (@NonNull @Nonempty final String sSelectorName)
  {
    // Treat as literal!!!
    this (JSExpr.lit (sSelectorName));
    ValueEnforcer.notEmpty (sSelectorName, "SelectorName");
  }

  public JQuerySelector (@NonNull final IJSExpression aExpr)
  {
    m_aExpr = ValueEnforcer.notNull (aExpr, "Expr");
  }

  @NonNull
  public IJSExpression getExpression ()
  {
    return m_aExpr;
  }

  @NonNull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return m_aExpr.getJSCode (aSettings);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector chain (@NonNull final IJQuerySelector aRhsSelector)
  {
    return chain (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector multiple (@NonNull final IJQuerySelector aRhsSelector)
  {
    return multiple (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector child (@NonNull final IJQuerySelector aRhsSelector)
  {
    return child (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector descendant (@NonNull final IJQuerySelector aRhsSelector)
  {
    return descendant (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector nextAdjacent (@NonNull final IJQuerySelector aRhsSelector)
  {
    return nextAdjacent (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector nextSiblings (@NonNull final IJQuerySelector aRhsSelector)
  {
    return nextSiblings (this, aRhsSelector);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector animated ()
  {
    return chain (this, animated);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector button ()
  {
    return chain (this, button);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector checkbox ()
  {
    return chain (this, checkbox);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector checked ()
  {
    return chain (this, checked);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector disabled ()
  {
    return chain (this, disabled);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector empty ()
  {
    return chain (this, empty);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector enabled ()
  {
    return chain (this, enabled);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector even ()
  {
    return chain (this, even);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector file ()
  {
    return chain (this, file);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector first ()
  {
    return chain (this, first);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector first_child ()
  {
    return chain (this, first_child);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector first_of_type ()
  {
    return chain (this, first_of_type);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector focus ()
  {
    return chain (this, focus);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector header ()
  {
    return chain (this, header);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector hidden ()
  {
    return chain (this, hidden);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector image ()
  {
    return chain (this, image);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector input ()
  {
    return chain (this, input);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector last ()
  {
    return chain (this, last);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector last_child ()
  {
    return chain (this, last_child);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector last_of_type ()
  {
    return chain (this, last_of_type);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector odd ()
  {
    return chain (this, odd);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector only_child ()
  {
    return chain (this, only_child);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector only_of_type ()
  {
    return chain (this, only_of_type);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector parent ()
  {
    return chain (this, parent);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector password ()
  {
    return chain (this, password);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector radio ()
  {
    return chain (this, radio);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector reset ()
  {
    return chain (this, reset);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector root ()
  {
    return chain (this, root);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector selected ()
  {
    return chain (this, selected);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector submit ()
  {
    return chain (this, submit);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector target ()
  {
    return chain (this, target);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector text ()
  {
    return chain (this, text);
  }

  @NonNull
  @CheckReturnValue
  public IJQuerySelector visible ()
  {
    return chain (this, visible);
  }

  @NonNull
  public JQueryInvocation invoke ()
  {
    return JQuery.jQuery (getExpression ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expr", m_aExpr).getToString ();
  }

  // static methods

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName*='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContains (@NonNull final IMicroQName aAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeContains (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName*='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContains (@NonNull @Nonempty final String sAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "*=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName|='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContainsPrefix (@NonNull final IMicroQName aAttrName,
                                                         @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeContainsPrefix (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName|='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContainsPrefix (@NonNull @Nonempty final String sAttrName,
                                                         @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "|=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName~='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContainsWord (@NonNull final IMicroQName aAttrName,
                                                       @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeContainsWord (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName~='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeContainsWord (@NonNull @Nonempty final String sAttrName,
                                                       @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "~=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName$='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeEndsWith (@NonNull final IMicroQName aAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeEndsWith (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName$='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeEndsWith (@NonNull @Nonempty final String sAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "$=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeEquals (@NonNull final IMicroQName aAttrName, @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeEquals (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeEquals (@NonNull @Nonempty final String sAttrName,
                                                 @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @return <code>[attrName]</code>
   */
  @NonNull
  public static IJQuerySelector attributeHas (@NonNull final IMicroQName aAttrName)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeHas (aAttrName.getName ());
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @return <code>[attrName]</code>
   */
  @NonNull
  public static IJQuerySelector attributeHas (@NonNull @Nonempty final String sAttrName)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    return new JQuerySelector ("[" + sAttrName + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName!='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeNotEqual (@NonNull final IMicroQName aAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeNotEqual (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName!='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeNotEqual (@NonNull @Nonempty final String sAttrName,
                                                   @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "!=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName^='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeStartsWith (@NonNull final IMicroQName aAttrName,
                                                     @NonNull final String sAttrValue)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeStartsWith (aAttrName.getName (), sAttrValue);
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @param sAttrValue
   *        Attribute value
   * @return <code>[attrName^='attrValue']</code>
   */
  @NonNull
  public static IJQuerySelector attributeStartsWith (@NonNull @Nonempty final String sAttrName,
                                                     @NonNull final String sAttrValue)
  {
    ValueEnforcer.notEmpty (sAttrName, "AttrName");
    ValueEnforcer.notNull (sAttrValue, "AttrValue");
    return new JQuerySelector ("[" + sAttrName + "^=" + JSStringLiteral.getAsString (sAttrValue) + "]");
  }

  /**
   * @param aParentSelector
   *        Parent selector
   * @param aChildSelector
   *        Child selector
   * @return <code>parent &gt; child</code>
   */
  @NonNull
  public static IJQuerySelector child (@NonNull final IJQuerySelector aParentSelector,
                                       @NonNull final IJQuerySelector aChildSelector)
  {
    ValueEnforcer.notNull (aParentSelector, "ParentSelector");
    ValueEnforcer.notNull (aChildSelector, "ChildSelector");
    return new JQuerySelector (aParentSelector.getExpression ()
                                              .plus (JSExpr.lit (" > "))
                                              .plus (aChildSelector.getExpression ()));
  }

  /**
   * jQuery class selection
   *
   * @param aCSSClass
   *        The CSS class to select
   * @return <code>.class</code>
   */
  @NonNull
  public static IJQuerySelector clazz (@NonNull final ICSSClassProvider aCSSClass)
  {
    ValueEnforcer.notNull (aCSSClass, "CSSClass");
    return new JQuerySelector ('.' + aCSSClass.getCSSClass ());
  }

  /**
   * @param sText
   *        text to check
   * @return <code>:contains(text)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector contains (@NonNull final String sText)
  {
    return contains (JSExpr.lit (sText));
  }

  /**
   * @param aExpr
   *        text to check
   * @return <code>:contains(text)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector contains (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":contains", aExpr);
  }

  /**
   * @param aAncestorSelector
   *        Ancestor selector
   * @param aDescendantSelector
   *        Descendant selector
   * @return <code>ancestor descendant</code>
   */
  @NonNull
  public static IJQuerySelector descendant (@NonNull final IJQuerySelector aAncestorSelector,
                                            @NonNull final IJQuerySelector aDescendantSelector)
  {
    ValueEnforcer.notNull (aAncestorSelector, "AncestorSelector");
    ValueEnforcer.notNull (aDescendantSelector, "DescendantSelector");
    return new JQuerySelector (aAncestorSelector.getExpression ()
                                                .plus (" ")
                                                .plus (aDescendantSelector.getExpression ()));
  }

  /**
   * jQuery element name selection
   *
   * @param eHTMLElement
   *        The HTML element to select
   * @return <code>element</code>
   */
  @NonNull
  public static IJQuerySelector element (@NonNull final EHTMLElement eHTMLElement)
  {
    ValueEnforcer.notNull (eHTMLElement, "HTMLElement");
    return element (eHTMLElement.getElementName ());
  }

  /**
   * jQuery element name selection
   *
   * @param sElementName
   *        The HTML element to select
   * @return <code>element</code>
   */
  @NonNull
  public static IJQuerySelector element (@NonNull @Nonempty final String sElementName)
  {
    ValueEnforcer.notEmpty (sElementName, "ElementName");
    return new JQuerySelector (sElementName);
  }

  @NonNull
  public static IJQuerySelector eq (final int v)
  {
    return eq (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector eq (final long v)
  {
    return eq (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector eq (@NonNull final BigInteger v)
  {
    return eq (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector eq (final double v)
  {
    return eq (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector eq (@NonNull final BigDecimal v)
  {
    return eq (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector eq (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":eq", aExpr);
  }

  @NonNull
  public static IJQuerySelector gt (final int v)
  {
    return gt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector gt (final long v)
  {
    return gt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector gt (@NonNull final BigInteger v)
  {
    return gt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector gt (final double v)
  {
    return gt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector gt (@NonNull final BigDecimal v)
  {
    return gt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector gt (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":gt", aExpr);
  }

  /**
   * Selects elements which contain at least one element that matches the specified selector.
   *
   * @param aSelector
   *        Selector to use
   * @return <code>:has(selector)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector has (@NonNull final IJQuerySelector aSelector)
  {
    return new JQuerySelector (":has", aSelector.getExpression ());
  }

  /**
   * Selects elements which contain at least one element that matches the specified selector.
   *
   * @param aExpr
   *        Selector to use
   * @return <code>:has(expr)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector has (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":has", aExpr);
  }

  @NonNull
  @Nonempty
  public static String getValidJQueryID (@NonNull @Nonempty final String sID)
  {
    ValueEnforcer.notEmpty (sID, "ID");

    // Pre-check before doing reg ex
    if (!StringHelper.containsAny (sID, ILLEGAL_JQUERY_ID_CHARS))
      return sID;

    // Replace all illegal characters in IDs: ":" and "." with "\:" and "\."
    // http://docs.jquery.com/Frequently_Asked_Questions#How_do_I_select_an_element_by_an_ID_that_has_characters_used_in_CSS_notation.3F
    return RegExHelper.stringReplacePattern ("(:|\\.)", sID, "\\\\$1");
  }

  /**
   * jQuery ID selection
   *
   * @param aIDProvider
   *        The object with an ID to select
   * @return <code>#id</code>
   */
  @NonNull
  public static IJQuerySelector id (@NonNull final IHasID <String> aIDProvider)
  {
    return id (aIDProvider.getID ());
  }

  /**
   * jQuery ID selection
   *
   * @param aElement
   *        The object with an ID to select
   * @return <code>#id</code>
   */
  @NonNull
  public static IJQuerySelector id (@NonNull final IHCHasID <?> aElement)
  {
    // Ensure element has an ID
    return id (aElement.ensureID ().getID ());
  }

  /**
   * jQuery ID selection
   *
   * @param sID
   *        The ID to select
   * @return <code>#id</code>
   */
  @NonNull
  public static IJQuerySelector id (@NonNull @Nonempty final String sID)
  {
    final String sMaskedID = getValidJQueryID (sID);
    return new JQuerySelector ('#' + sMaskedID);
  }

  /**
   * jQuery ID selection
   *
   * @param aID
   *        The ID to select
   * @return <code>'#'+id</code>
   */
  @NonNull
  public static IJQuerySelector id (@NonNull final IJSExpression aID)
  {
    return new JQuerySelector (JSExpr.lit ('#').plus (aID));
  }

  /**
   * jQuery language selection
   *
   * @param sLanguage
   *        The language to select
   * @return <code>:lang(language)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector lang (@NonNull @Nonempty final String sLanguage)
  {
    return lang (JSExpr.lit (sLanguage));
  }

  /**
   * jQuery language selection
   *
   * @param aExpr
   *        The language to select
   * @return <code>:lang(expr)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector lang (@NonNull @Nonempty final IJSExpression aExpr)
  {
    return new JQuerySelector (":lang", aExpr);
  }

  @NonNull
  public static IJQuerySelector lt (final int v)
  {
    return lt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector lt (final long v)
  {
    return lt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector lt (@NonNull final BigInteger v)
  {
    return lt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector lt (final double v)
  {
    return lt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector lt (@NonNull final BigDecimal v)
  {
    return lt (JSExpr.lit (v));
  }

  @NonNull
  public static IJQuerySelector lt (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":lt", aExpr);
  }

  /**
   * @param aSelectors
   *        the selectors to chaing via ","
   * @return <code>sel, sel, sel, ...</code>
   */
  @NonNull
  public static IJQuerySelector multiple (@NonNull @Nonempty final IJQuerySelector... aSelectors)
  {
    final int nSize = ArrayHelper.getSize (aSelectors);
    if (nSize == 0)
      throw new IllegalArgumentException ("empty selectors");
    if (ArrayHelper.containsAnyNullElement (aSelectors))
      throw new IllegalArgumentException ("selectors array contains null element");

    if (nSize == 1)
      return aSelectors[0];

    // Concatenate with ','
    IJSExpression ret = aSelectors[0].getExpression ();
    for (int i = 1; i < nSize; ++i)
      ret = ret.plus (',').plus (aSelectors[i].getExpression ());
    return new JQuerySelector (ret);
  }

  /**
   * @param aSelectors
   *        the selectors to chaing via ","
   * @return <code>sel, sel, sel, ...</code>
   */
  @NonNull
  public static IJQuerySelector multiple (@NonNull @Nonempty final List <IJQuerySelector> aSelectors)
  {
    final int nSize = CollectionHelper.getSize (aSelectors);
    if (nSize == 0)
      throw new IllegalArgumentException ("empty selectors");
    if (CollectionFind.containsAnyNullElement (aSelectors))
      throw new IllegalArgumentException ("selectors collection contains null element");

    if (nSize == 1)
      return CollectionFind.getFirstElement (aSelectors);

    // Concatenate with ','
    IJSExpression ret = aSelectors.get (0).getExpression ();
    for (int i = 1; i < nSize; ++i)
      ret = ret.plus (',').plus (aSelectors.get (i).getExpression ());
    return new JQuerySelector (ret);
  }

  /**
   * Shortcut to select elements with a certain HTML <code>name</code> attribute.
   *
   * @param sNameAttrValue
   *        The value of the name attribute to compare
   * @return <code>[name=<i>nameAttrValue</i>]</code>
   * @see #attributeEquals(String, String)
   */
  @NonNull
  public static IJQuerySelector nameAttr (@NonNull @Nonempty final String sNameAttrValue)
  {
    ValueEnforcer.notEmpty (sNameAttrValue, "NameAttrValue");
    return attributeEquals (CHTMLAttributes.NAME, sNameAttrValue);
  }

  /**
   * @param aPrevSelector
   *        Previous selector
   * @param aNextSelector
   *        Next selector
   * @return <code>prev + next</code>
   */
  @NonNull
  public static IJQuerySelector nextAdjacent (@NonNull final IJQuerySelector aPrevSelector,
                                              @NonNull final IJQuerySelector aNextSelector)
  {
    ValueEnforcer.notNull (aPrevSelector, "PrevSelector");
    ValueEnforcer.notNull (aNextSelector, "NextSelector");
    return new JQuerySelector (aPrevSelector.getExpression ()
                                            .plus (JSExpr.lit (" + "))
                                            .plus (aNextSelector.getExpression ()));
  }

  /**
   * @param aPrevSelector
   *        Previous selector
   * @param aSiblingsSelector
   *        Siblings selector
   * @return <code>prev ~ siblings</code>
   */
  @NonNull
  public static IJQuerySelector nextSiblings (@NonNull final IJQuerySelector aPrevSelector,
                                              @NonNull final IJQuerySelector aSiblingsSelector)
  {
    ValueEnforcer.notNull (aPrevSelector, "PrevSelector");
    ValueEnforcer.notNull (aSiblingsSelector, "SiblingsSelector");
    return new JQuerySelector (aPrevSelector.getExpression ()
                                            .plus (JSExpr.lit (" ~ "))
                                            .plus (aSiblingsSelector.getExpression ()));
  }

  @NonNull
  public static IJQuerySelector not (@NonNull final IJQuerySelector aSelector)
  {
    return not (aSelector.getExpression ());
  }

  @NonNull
  public static IJQuerySelector not (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":not", aExpr);
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param v
   *        index
   * @return <code>:nth-child(value)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (final int v)
  {
    return nthChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param v
   *        index
   * @return <code>:nth-child(value)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (final long v)
  {
    return nthChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param v
   *        index
   * @return <code>:nth-child(value)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (@NonNull final BigInteger v)
  {
    return nthChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param v
   *        index
   * @return <code>:nth-child(value)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (final double v)
  {
    return nthChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param v
   *        index
   * @return <code>:nth-child(value)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (@NonNull final BigDecimal v)
  {
    return nthChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-child(aExpr)</code>
   * @since jQuery 1.1.4
   */
  @NonNull
  public static IJQuerySelector nthChild (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-child", aExpr);
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (final int v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (final long v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (@NonNull final BigInteger v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (final double v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (@NonNull final BigDecimal v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-last-child(aExpr)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastChild (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-last-child", aExpr);
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (final int v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (final long v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (@NonNull final BigInteger v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (final double v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (@NonNull final BigDecimal v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from the last element to
   * the first.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-last-of-type(aExpr)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthLastOfType (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-last-of-type", aExpr);
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (final int v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (final long v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (@NonNull final BigInteger v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (final double v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (@NonNull final BigDecimal v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to siblings with the
   * same element name.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-of-type(aExpr)</code>
   * @since jQuery 1.9
   */
  @NonNull
  public static IJQuerySelector nthOfType (@NonNull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-of-type", aExpr);
  }

  /**
   * Chain them directly together to build stuff like "div#id" or ".class1.class" or "span.foo"
   *
   * @param aFirstSelector
   *        The first selector. May not be <code>null</code>.
   * @param aSecondSelector
   *        The second selector. May not be <code>null</code>.
   * @return <code>first<i>second</i></code>
   */
  @NonNull
  public static IJQuerySelector chain (@NonNull final IJQuerySelector aFirstSelector,
                                       @NonNull final IJQuerySelector aSecondSelector)
  {
    ValueEnforcer.notNull (aFirstSelector, "FirstSelector");
    ValueEnforcer.notNull (aSecondSelector, "SecondSelector");

    // expr + expr -> for Strings this chains the String!
    return new JQuerySelector (aFirstSelector.getExpression ().plus (aSecondSelector.getExpression ()));
  }
}
