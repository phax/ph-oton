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

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.jscode.IJSExpression;

/**
 * A single jQuery selector
 *
 * @author Philip Helger
 */
public interface IJQuerySelector extends IHasJSCodeWithSettings
{
  /**
   * @return The contained expression that was used as the parameter to this
   *         selector. Never <code>null</code>.
   */
  @Nonnull
  IJSExpression getExpression ();

  /**
   * Get chained with the passed selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this<i>rhs</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector chain (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Make a multiple selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this, rhs</code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector multiple (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Make a child selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this &gt; rhs</code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector child (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Make a descendant selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this rhs</code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector descendant (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Make a next adjacent selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this + rhs</code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector nextAdjacent (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Make a next siblings selector
   *
   * @param aRhsSelector
   *        Other selector
   * @return <code>this ~ rhs</code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector nextSiblings (@Nonnull IJQuerySelector aRhsSelector);

  /**
   * Chain a animated selector
   *
   * @return <code>this<i>:animated</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector animated ();

  /**
   * Chain a button selector
   *
   * @return <code>this<i>:button</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector button ();

  /**
   * Chain a checkbox selector
   *
   * @return <code>this<i>:checkbox</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector checkbox ();

  /**
   * Chain a checked selector
   *
   * @return <code>this<i>:checked</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector checked ();

  /**
   * Chain a disabled selector
   *
   * @return <code>this<i>:disabled</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector disabled ();

  /**
   * Chain a empty selector
   *
   * @return <code>this<i>:empty</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector empty ();

  /**
   * Chain a enabled selector
   *
   * @return <code>this<i>:enabled</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector enabled ();

  /**
   * Chain a even selector
   *
   * @return <code>this<i>:even</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector even ();

  /**
   * Chain a file selector
   *
   * @return <code>this<i>:file</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector file ();

  /**
   * Chain a first selector
   *
   * @return <code>this<i>:first</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector first ();

  /**
   * Chain a first-child selector
   *
   * @return <code>this<i>:first-child</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector first_child ();

  /**
   * Chain a first-of-type selector
   *
   * @return <code>this<i>:first-of-type</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector first_of_type ();

  /**
   * Chain a focus selector
   *
   * @return <code>this<i>:focus</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector focus ();

  /**
   * Chain a header selector
   *
   * @return <code>this<i>:header</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector header ();

  /**
   * Chain a hidden selector
   *
   * @return <code>this<i>:hidden</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector hidden ();

  /**
   * Chain a image selector
   *
   * @return <code>this<i>:image</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector image ();

  /**
   * Chain a input selector
   *
   * @return <code>this<i>:input</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector input ();

  /**
   * Chain a last selector
   *
   * @return <code>this<i>:last</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector last ();

  /**
   * Chain a last-child selector
   *
   * @return <code>this<i>:last-child</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector last_child ();

  /**
   * Chain a last-of-type selector
   *
   * @return <code>this<i>:last-of-type</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector last_of_type ();

  /**
   * Chain a odd selector
   *
   * @return <code>this<i>:odd</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector odd ();

  /**
   * Chain a only-child selector
   *
   * @return <code>this<i>:only-child</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector only_child ();

  /**
   * Chain a only-of-type selector
   *
   * @return <code>this<i>:only-of-type</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector only_of_type ();

  /**
   * Chain a parent selector
   *
   * @return <code>this<i>:parent</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector parent ();

  /**
   * Chain a password selector
   *
   * @return <code>this<i>:password</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector password ();

  /**
   * Chain a radio selector
   *
   * @return <code>this<i>:radio</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector radio ();

  /**
   * Chain a reset selector
   *
   * @return <code>this<i>:reset</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector reset ();

  /**
   * Chain a root selector
   *
   * @return <code>this<i>:root</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector root ();

  /**
   * Chain a selected selector
   *
   * @return <code>this<i>:selected</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector selected ();

  /**
   * Chain a submit selector
   *
   * @return <code>this<i>:submit</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector submit ();

  /**
   * Chain a target selector
   *
   * @return <code>this<i>:target</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector target ();

  /**
   * Chain a text selector
   *
   * @return <code>this<i>:text</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector text ();

  /**
   * Chain a visible selector
   *
   * @return <code>this<i>:visible</i></code>
   */
  @Nonnull
  @CheckReturnValue
  IJQuerySelector visible ();

  /**
   * Create an invocation of this selector
   *
   * @return <code>$(selectorString)</code>
   */
  @Nonnull
  JQueryInvocation invoke ();
}
