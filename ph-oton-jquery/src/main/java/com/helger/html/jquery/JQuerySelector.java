/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.id.IHasID;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
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

  private static final char [] ILLEGAL_JQUERY_ID_CHARS = new char [] { ':', '.' };

  private final IJSExpression m_aExpr;

  private JQuerySelector (@Nonnull @Nonempty final String sSelectorName, @Nonnull final IJSExpression aSelectorExpr)
  {
    // Is used as a literal!!
    this (sSelectorName + '(' + aSelectorExpr.getJSCode (HCSettings.getConversionSettings ().getJSWriterSettings ()) + ')');
    ValueEnforcer.notEmpty (sSelectorName, "SelectorName");
  }

  private JQuerySelector (@Nonnull @Nonempty final String sSelectorName)
  {
    // Treat as literal!!!
    this (JSExpr.lit (sSelectorName));
    ValueEnforcer.notEmpty (sSelectorName, "SelectorName");
  }

  public JQuerySelector (@Nonnull final IJSExpression aExpr)
  {
    m_aExpr = ValueEnforcer.notNull (aExpr, "Expr");
  }

  @Nonnull
  public IJSExpression getExpression ()
  {
    return m_aExpr;
  }

  @Nonnull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return m_aExpr.getJSCode (aSettings);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector chain (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return chain (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector multiple (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return multiple (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector child (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return child (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector descendant (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return descendant (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector nextAdjacent (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return nextAdjacent (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector nextSiblings (@Nonnull final IJQuerySelector aRhsSelector)
  {
    return nextSiblings (this, aRhsSelector);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector animated ()
  {
    return chain (this, animated);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector button ()
  {
    return chain (this, button);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector checkbox ()
  {
    return chain (this, checkbox);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector checked ()
  {
    return chain (this, checked);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector disabled ()
  {
    return chain (this, disabled);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector empty ()
  {
    return chain (this, empty);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector enabled ()
  {
    return chain (this, enabled);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector even ()
  {
    return chain (this, even);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector file ()
  {
    return chain (this, file);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector first ()
  {
    return chain (this, first);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector first_child ()
  {
    return chain (this, first_child);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector first_of_type ()
  {
    return chain (this, first_of_type);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector focus ()
  {
    return chain (this, focus);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector header ()
  {
    return chain (this, header);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector hidden ()
  {
    return chain (this, hidden);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector image ()
  {
    return chain (this, image);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector input ()
  {
    return chain (this, input);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector last ()
  {
    return chain (this, last);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector last_child ()
  {
    return chain (this, last_child);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector last_of_type ()
  {
    return chain (this, last_of_type);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector odd ()
  {
    return chain (this, odd);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector only_child ()
  {
    return chain (this, only_child);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector only_of_type ()
  {
    return chain (this, only_of_type);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector parent ()
  {
    return chain (this, parent);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector password ()
  {
    return chain (this, password);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector radio ()
  {
    return chain (this, radio);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector reset ()
  {
    return chain (this, reset);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector root ()
  {
    return chain (this, root);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector selected ()
  {
    return chain (this, selected);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector submit ()
  {
    return chain (this, submit);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector target ()
  {
    return chain (this, target);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector text ()
  {
    return chain (this, text);
  }

  @Nonnull
  @CheckReturnValue
  public IJQuerySelector visible ()
  {
    return chain (this, visible);
  }

  @Nonnull
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
  @Nonnull
  public static IJQuerySelector attributeContains (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeContains (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeContainsPrefix (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeContainsPrefix (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeContainsWord (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeContainsWord (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeEndsWith (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeEndsWith (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeEquals (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeEquals (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeHas (@Nonnull final IMicroQName aAttrName)
  {
    ValueEnforcer.notNull (aAttrName, "AttrName");
    return attributeHas (aAttrName.getName ());
  }

  /**
   * @param sAttrName
   *        Attribute name
   * @return <code>[attrName]</code>
   */
  @Nonnull
  public static IJQuerySelector attributeHas (@Nonnull @Nonempty final String sAttrName)
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
  @Nonnull
  public static IJQuerySelector attributeNotEqual (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeNotEqual (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeStartsWith (@Nonnull final IMicroQName aAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector attributeStartsWith (@Nonnull @Nonempty final String sAttrName, @Nonnull final String sAttrValue)
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
  @Nonnull
  public static IJQuerySelector child (@Nonnull final IJQuerySelector aParentSelector, @Nonnull final IJQuerySelector aChildSelector)
  {
    ValueEnforcer.notNull (aParentSelector, "ParentSelector");
    ValueEnforcer.notNull (aChildSelector, "ChildSelector");
    return new JQuerySelector (aParentSelector.getExpression ().plus (JSExpr.lit (" > ")).plus (aChildSelector.getExpression ()));
  }

  /**
   * jQuery class selection
   *
   * @param aCSSClass
   *        The CSS class to select
   * @return <code>.class</code>
   */
  @Nonnull
  public static IJQuerySelector clazz (@Nonnull final ICSSClassProvider aCSSClass)
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
  @Nonnull
  public static IJQuerySelector contains (@Nonnull final String sText)
  {
    return contains (JSExpr.lit (sText));
  }

  /**
   * @param aExpr
   *        text to check
   * @return <code>:contains(text)</code>
   * @since jQuery 1.1.4
   */
  @Nonnull
  public static IJQuerySelector contains (@Nonnull final IJSExpression aExpr)
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
  @Nonnull
  public static IJQuerySelector descendant (@Nonnull final IJQuerySelector aAncestorSelector,
                                            @Nonnull final IJQuerySelector aDescendantSelector)
  {
    ValueEnforcer.notNull (aAncestorSelector, "AncestorSelector");
    ValueEnforcer.notNull (aDescendantSelector, "DescendantSelector");
    return new JQuerySelector (aAncestorSelector.getExpression ().plus (" ").plus (aDescendantSelector.getExpression ()));
  }

  /**
   * jQuery element name selection
   *
   * @param eHTMLElement
   *        The HTML element to select
   * @return <code>element</code>
   */
  @Nonnull
  public static IJQuerySelector element (@Nonnull final EHTMLElement eHTMLElement)
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
  @Nonnull
  public static IJQuerySelector element (@Nonnull @Nonempty final String sElementName)
  {
    ValueEnforcer.notEmpty (sElementName, "ElementName");
    return new JQuerySelector (sElementName);
  }

  @Nonnull
  public static IJQuerySelector eq (final int v)
  {
    return eq (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector eq (final long v)
  {
    return eq (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector eq (@Nonnull final BigInteger v)
  {
    return eq (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector eq (final double v)
  {
    return eq (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector eq (@Nonnull final BigDecimal v)
  {
    return eq (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector eq (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":eq", aExpr);
  }

  @Nonnull
  public static IJQuerySelector gt (final int v)
  {
    return gt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector gt (final long v)
  {
    return gt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector gt (@Nonnull final BigInteger v)
  {
    return gt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector gt (final double v)
  {
    return gt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector gt (@Nonnull final BigDecimal v)
  {
    return gt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector gt (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":gt", aExpr);
  }

  /**
   * Selects elements which contain at least one element that matches the
   * specified selector.
   *
   * @param aSelector
   *        Selector to use
   * @return <code>:has(selector)</code>
   * @since jQuery 1.1.4
   */
  @Nonnull
  public static IJQuerySelector has (@Nonnull final IJQuerySelector aSelector)
  {
    return new JQuerySelector (":has", aSelector.getExpression ());
  }

  /**
   * Selects elements which contain at least one element that matches the
   * specified selector.
   *
   * @param aExpr
   *        Selector to use
   * @return <code>:has(expr)</code>
   * @since jQuery 1.1.4
   */
  @Nonnull
  public static IJQuerySelector has (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":has", aExpr);
  }

  @Nonnull
  @Nonempty
  public static String getValidJQueryID (@Nonnull @Nonempty final String sID)
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
  @Nonnull
  public static IJQuerySelector id (@Nonnull final IHasID <String> aIDProvider)
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
  @Nonnull
  public static IJQuerySelector id (@Nonnull final IHCHasID <?> aElement)
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
  @Nonnull
  public static IJQuerySelector id (@Nonnull @Nonempty final String sID)
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
  @Nonnull
  public static IJQuerySelector id (@Nonnull final IJSExpression aID)
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
  @Nonnull
  public static IJQuerySelector lang (@Nonnull @Nonempty final String sLanguage)
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
  @Nonnull
  public static IJQuerySelector lang (@Nonnull @Nonempty final IJSExpression aExpr)
  {
    return new JQuerySelector (":lang", aExpr);
  }

  @Nonnull
  public static IJQuerySelector lt (final int v)
  {
    return lt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector lt (final long v)
  {
    return lt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector lt (@Nonnull final BigInteger v)
  {
    return lt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector lt (final double v)
  {
    return lt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector lt (@Nonnull final BigDecimal v)
  {
    return lt (JSExpr.lit (v));
  }

  @Nonnull
  public static IJQuerySelector lt (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":lt", aExpr);
  }

  /**
   * @param aSelectors
   *        the selectors to chaing via ","
   * @return <code>sel, sel, sel, ...</code>
   */
  @Nonnull
  public static IJQuerySelector multiple (@Nonnull @Nonempty final IJQuerySelector... aSelectors)
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
  @Nonnull
  public static IJQuerySelector multiple (@Nonnull @Nonempty final List <IJQuerySelector> aSelectors)
  {
    final int nSize = CollectionHelper.getSize (aSelectors);
    if (nSize == 0)
      throw new IllegalArgumentException ("empty selectors");
    if (CollectionHelper.containsAnyNullElement (aSelectors))
      throw new IllegalArgumentException ("selectors collection contains null element");

    if (nSize == 1)
      return CollectionHelper.getFirstElement (aSelectors);

    // Concatenate with ','
    IJSExpression ret = aSelectors.get (0).getExpression ();
    for (int i = 1; i < nSize; ++i)
      ret = ret.plus (',').plus (aSelectors.get (i).getExpression ());
    return new JQuerySelector (ret);
  }

  /**
   * Shortcut to select elements with a certain HTML <code>name</code>
   * attribute.
   *
   * @param sNameAttrValue
   *        The value of the name attribute to compare
   * @return <code>[name=<i>nameAttrValue</i>]</code>
   * @see #attributeEquals(String, String)
   */
  @Nonnull
  public static IJQuerySelector nameAttr (@Nonnull @Nonempty final String sNameAttrValue)
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
  @Nonnull
  public static IJQuerySelector nextAdjacent (@Nonnull final IJQuerySelector aPrevSelector, @Nonnull final IJQuerySelector aNextSelector)
  {
    ValueEnforcer.notNull (aPrevSelector, "PrevSelector");
    ValueEnforcer.notNull (aNextSelector, "NextSelector");
    return new JQuerySelector (aPrevSelector.getExpression ().plus (JSExpr.lit (" + ")).plus (aNextSelector.getExpression ()));
  }

  /**
   * @param aPrevSelector
   *        Previous selector
   * @param aSiblingsSelector
   *        Siblings selector
   * @return <code>prev ~ siblings</code>
   */
  @Nonnull
  public static IJQuerySelector nextSiblings (@Nonnull final IJQuerySelector aPrevSelector,
                                              @Nonnull final IJQuerySelector aSiblingsSelector)
  {
    ValueEnforcer.notNull (aPrevSelector, "PrevSelector");
    ValueEnforcer.notNull (aSiblingsSelector, "SiblingsSelector");
    return new JQuerySelector (aPrevSelector.getExpression ().plus (JSExpr.lit (" ~ ")).plus (aSiblingsSelector.getExpression ()));
  }

  @Nonnull
  public static IJQuerySelector not (@Nonnull final IJQuerySelector aSelector)
  {
    return not (aSelector.getExpression ());
  }

  @Nonnull
  public static IJQuerySelector not (@Nonnull final IJSExpression aExpr)
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
  @Nonnull
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
  @Nonnull
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
  @Nonnull
  public static IJQuerySelector nthChild (@Nonnull final BigInteger v)
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
  @Nonnull
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
  @Nonnull
  public static IJQuerySelector nthChild (@Nonnull final BigDecimal v)
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
  @Nonnull
  public static IJQuerySelector nthChild (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-child", aExpr);
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (final int v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (final long v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (@Nonnull final BigInteger v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (final double v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        value
   * @return <code>:nth-last-child(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (@Nonnull final BigDecimal v)
  {
    return nthLastChild (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-last-child(aExpr)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastChild (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-last-child", aExpr);
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (final int v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (final long v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (@Nonnull final BigInteger v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (final double v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param v
   *        index
   * @return <code>:nth-last-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (@Nonnull final BigDecimal v)
  {
    return nthLastOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth-child of their parent, counting from
   * the last element to the first.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-last-of-type(aExpr)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthLastOfType (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-last-of-type", aExpr);
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (final int v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (final long v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (@Nonnull final BigInteger v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (final double v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param v
   *        index
   * @return <code>:nth-of-type(value)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (@Nonnull final BigDecimal v)
  {
    return nthOfType (JSExpr.lit (v));
  }

  /**
   * Selects all elements that are the nth child of their parent in relation to
   * siblings with the same element name.
   *
   * @param aExpr
   *        index
   * @return <code>:nth-of-type(aExpr)</code>
   * @since jQuery 1.9
   */
  @Nonnull
  public static IJQuerySelector nthOfType (@Nonnull final IJSExpression aExpr)
  {
    return new JQuerySelector (":nth-of-type", aExpr);
  }

  /**
   * Chain them directly together to build stuff like "div#id" or
   * ".class1.class" or "span.foo"
   *
   * @param aFirstSelector
   *        The first selector. May not be <code>null</code>.
   * @param aSecondSelector
   *        The second selector. May not be <code>null</code>.
   * @return <code>first<i>second</i></code>
   */
  @Nonnull
  public static IJQuerySelector chain (@Nonnull final IJQuerySelector aFirstSelector, @Nonnull final IJQuerySelector aSecondSelector)
  {
    ValueEnforcer.notNull (aFirstSelector, "FirstSelector");
    ValueEnforcer.notNull (aSecondSelector, "SecondSelector");

    // expr + expr -> for Strings this chains the String!
    return new JQuerySelector (aFirstSelector.getExpression ().plus (aSecondSelector.getExpression ()));
  }
}
