/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.AbstractJSInvocation;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFunction;

/**
 * Special invocation semantics for jQuery
 *
 * @author Philip Helger
 */
public class JQueryInvocation extends AbstractJSInvocation <JQueryInvocation> implements IJQueryInvocationExtended <JQueryInvocation>
{
  public JQueryInvocation (@Nonnull final JSFunction aFunction)
  {
    super (aFunction);
  }

  public JQueryInvocation (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)
  {
    super (aLhs, sMethod);
  }

  /**
   * Invoke an arbitrary function on this jQuery object.
   *
   * @param sMethod
   *        The method to be invoked. May neither be <code>null</code> nor
   *        empty.
   * @return A new jQuery invocation object. Never <code>null</code>.
   */
  @Override
  @Nonnull
  public JQueryInvocation jqinvoke (@Nonnull @Nonempty final String sMethod)
  {
    return new JQueryInvocation (this, sMethod);
  }

  // Special value invocations

  /**
   * @since jQuery 1.6
   * @return The invocation of the jQuery function <code>prop('checked')</code>
   */
  @Nonnull
  public JQueryInvocation propChecked ()
  {
    return prop ().arg ("checked");
  }

  // Custom provided methods from jquery-utils.js in ph-webctrls

  /**
   * @return The invocation of the custom jQuery function <code>enable()</code>
   */
  @Nonnull
  public JQueryInvocation enable ()
  {
    return jqinvoke ("enable");
  }

  /**
   * @return The invocation of the custom jQuery function <code>disable()</code>
   */
  @Nonnull
  public JQueryInvocation disable ()
  {
    return jqinvoke ("disable");
  }

  /**
   * @return The invocation of the custom jQuery function
   *         <code>setDisabled()</code>
   */
  @Nonnull
  public JQueryInvocation setDisabled ()
  {
    return jqinvoke ("setDisabled");
  }

  /**
   * @param bDisabled
   *        <code>true</code> to set disabled
   * @return The invocation of the custom jQuery function
   *         <code>setDisabled()</code>
   */
  @Nonnull
  public JQueryInvocation setDisabled (final boolean bDisabled)
  {
    return setDisabled (JSExpr.lit (bDisabled));
  }

  /**
   * @param aExpr
   *        Expression to determine disabled state
   * @return The invocation of the custom jQuery function
   *         <code>setDisabled()</code>
   */
  @Nonnull
  public JQueryInvocation setDisabled (@Nonnull final IJSExpression aExpr)
  {
    return setDisabled ().arg (aExpr);
  }

  /**
   * @return The invocation of the custom jQuery function
   *         <code>setReadOnly()</code>
   * @since 8.3.1
   */
  @Nonnull
  public JQueryInvocation setReadOnly ()
  {
    return jqinvoke ("setReadOnly");
  }

  /**
   * @param bReadOnly
   *        <code>true</code> to set read-only
   * @return The invocation of the custom jQuery function
   *         <code>setReadOnly()</code>
   * @since 8.3.1
   */
  @Nonnull
  public JQueryInvocation setReadOnly (final boolean bReadOnly)
  {
    return setReadOnly (JSExpr.lit (bReadOnly));
  }

  /**
   * @param aExpr
   *        Expression to determine read-only state
   * @return The invocation of the custom jQuery function
   *         <code>setReadOnly()</code>
   * @since 8.3.1
   */
  @Nonnull
  public JQueryInvocation setReadOnly (@Nonnull final IJSExpression aExpr)
  {
    return setReadOnly ().arg (aExpr);
  }

  /**
   * @return The invocation of the custom jQuery function <code>check()</code>
   */
  @Nonnull
  public JQueryInvocation check ()
  {
    return jqinvoke ("check");
  }

  /**
   * @return The invocation of the custom jQuery function <code>uncheck()</code>
   */
  @Nonnull
  public JQueryInvocation uncheck ()
  {
    return jqinvoke ("uncheck");
  }

  /**
   * @return The invocation of the custom jQuery function
   *         <code>setChecked()</code>
   */
  @Nonnull
  public JQueryInvocation setChecked ()
  {
    return jqinvoke ("setChecked");
  }

  /**
   * @param bChecked
   *        <code>true</code> to check it
   * @return The invocation of the custom jQuery function
   *         <code>setChecked()</code>
   */
  @Nonnull
  public JQueryInvocation setChecked (final boolean bChecked)
  {
    return setChecked (JSExpr.lit (bChecked));
  }

  /**
   * @param aExpr
   *        Expression to determine checked state
   * @return The invocation of the custom jQuery function
   *         <code>setChecked()</code>
   */
  @Nonnull
  public JQueryInvocation setChecked (@Nonnull final IJSExpression aExpr)
  {
    return setChecked ().arg (aExpr);
  }

  /**
   * @return <code>is (':disabled')</code>
   */
  @Nonnull
  public JQueryInvocation isDisabled ()
  {
    return is (JQuerySelector.disabled);
  }

  /**
   * @return <code>is (':enabled')</code>
   */
  @Nonnull
  public JQueryInvocation isEnabled ()
  {
    return is (JQuerySelector.enabled);
  }

  /**
   * @return <code>is (':first-child')</code>
   */
  @Nonnull
  public JQueryInvocation isFirstChild ()
  {
    return is (JQuerySelector.first_child);
  }

  /**
   * @return <code>is (':last-child')</code>
   */
  @Nonnull
  public JQueryInvocation isLastChild ()
  {
    return is (JQuerySelector.last_child);
  }

  /**
   * @return <code>is (':visible')</code>
   */
  @Nonnull
  public JQueryInvocation isVisible ()
  {
    return is (JQuerySelector.visible);
  }

  /**
   * @return <code>is (':hidden')</code>
   */
  @Nonnull
  public JQueryInvocation isHidden ()
  {
    return is (JQuerySelector.hidden);
  }
}
