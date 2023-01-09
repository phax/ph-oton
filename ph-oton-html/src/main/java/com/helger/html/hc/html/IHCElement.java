/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.state.ETriState;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.IHCNode;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.JSEventMap;

/**
 * Base interface for an HC element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementation type
 */
public interface IHCElement <IMPLTYPE extends IHCElement <IMPLTYPE>> extends
                            IHCNode,
                            IHCHasID <IMPLTYPE>,
                            IHCHasCSSStyles <IMPLTYPE>,
                            IHCHasCSSClasses <IMPLTYPE>
{
  /** The default value for an unset tab index, as -1 is used for "none" */
  long DEFAULT_TABINDEX = -5l;

  /**
   * @return The contained HTML element. Never <code>null</code>.
   */
  @Nonnull
  EHTMLElement getElement ();

  /**
   * @return The tag name of this element (without namespace)
   */
  @Nonnull
  @Nonempty
  String getTagName ();

  /**
   * @return The value of the HTML <code>title</code> attribute. May be
   *         <code>null</code>.
   */
  @Nullable
  String getTitle ();

  /**
   * Set the value of the HTML <code>title</code> attribute.
   *
   * @param sTitle
   *        The new title. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setTitle (String sTitle);

  /**
   * @return The value of the HTML <code>dir</code> attribute. May be
   *         <code>null</code>.
   */
  @Nullable
  EHCTextDirection getDirection ();

  /**
   * Set the value of the HTML <code>dir</code> attribute.
   *
   * @param eDirection
   *        The new direction. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setDirection (@Nullable EHCTextDirection eDirection);

  /**
   * @return The value of the HTML <code>lang</code> attribute. May be
   *         <code>null</code>.
   */
  @Nullable
  String getLanguage ();

  /**
   * Set the value of the HTML <code>lang</code> attribute.
   *
   * @param sLanguage
   *        The new language. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setLanguage (@Nullable String sLanguage);

  @Nullable
  @ReturnsMutableObject ("design")
  JSEventMap getEventMap ();

  /**
   * Get the event handler of the specified event.
   *
   * @param eJSEvent
   *        The event to query. May be <code>null</code>.
   * @return <code>null</code> if no such event handler is registered.
   */
  @Nullable
  IHasJSCode getEventHandler (@Nullable EJSEvent eJSEvent);

  /**
   * Check if any event handler is registered for the specified event.
   *
   * @param eJSEvent
   *        The event to be queried. May be <code>null</code>.
   * @return <code>true</code> of a non-<code>null</code> event is specified,
   *         and if a handler is present.
   */
  boolean containsEventHandler (@Nullable EJSEvent eJSEvent);

  /**
   * Add a JS event handler at the end.
   *
   * @param eJSEvent
   *        The event to use. May not be <code>null</code>.
   * @param aJSHandler
   *        The JSCode to be executed on the specified event. May be
   *        <code>null</code> in which case nothing happens.
   * @return this.
   */
  @Nonnull
  IMPLTYPE addEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

  /**
   * Add a JS event handler at the front.
   *
   * @param eJSEvent
   *        The event to use. May not be <code>null</code>.
   * @param aJSHandler
   *        The JSCode to be executed on the specified event. May be
   *        <code>null</code> in which case nothing happens.
   * @return this.
   */
  @Nonnull
  IMPLTYPE prependEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

  /**
   * Set a JS event handler. All eventually present event handlers are
   * overwritten.
   *
   * @param eJSEvent
   *        The event to set. May not be <code>null</code>.
   * @param aJSHandler
   *        The JSCode to be executed on the specified event. May be
   *        <code>null</code> in which case no event handler will be present
   *        after the call.
   * @return this.
   */
  @Nonnull
  IMPLTYPE setEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

  /**
   * Remove all event handler for the specified JS event.
   *
   * @param eJSEvent
   *        The JS event to remove the handler. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE removeAllEventHandler (@Nullable EJSEvent eJSEvent);

  /**
   * @return <code>true</code> if this element cannot be focused.
   */
  boolean isUnfocusable ();

  /**
   * Set the unfocusable state of this element.
   *
   * @param bUnfocusable
   *        <code>true</code> to make it unfocusable, <code>false</code>
   *        otherwise.
   * @return this
   */
  @Nonnull
  IMPLTYPE setUnfocusable (boolean bUnfocusable);

  /**
   * @return <code>true</code> if this is hidden
   */
  boolean isHidden ();

  /**
   * Set the hidden state of this element
   *
   * @param bHidden
   *        <code>true</code> if it is hidden, <code>false</code> otherwise
   * @return this
   */
  IMPLTYPE setHidden (boolean bHidden);

  /**
   * @return The tab index of this object. The semantics of negative values
   *         depends on the browser! In HTML5 negative values are allowed but
   *         won't set a focus when tabbing. The default value is indicated by
   *         {@link #DEFAULT_TABINDEX}
   */
  @CheckForSigned
  long getTabIndex ();

  /**
   * Set the tab index of this object. This is a common element in HTML5 only.
   * The default value is {@link #DEFAULT_TABINDEX}
   *
   * @param nTabIndex
   *        The tab-index of this object. The semantics of negative values
   *        depends on the browser! In HTML5 negative values are allowed but
   *        won't set a focus when tabbing.
   * @return this
   */
  @Nonnull
  IMPLTYPE setTabIndex (long nTabIndex);

  /**
   * @return The value of the HTML <code>accesskey</code> attribute. May be
   *         <code>null</code>.
   */
  @Nullable
  String getAccessKey ();

  /**
   * Set the value of the HTML <code>accesskey</code> attribute.
   *
   * @param sAccessKey
   *        The new accesskey. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setAccessKey (@Nullable String sAccessKey);

  /**
   * @return the draggable state. May be <code>null</code>.
   */
  @Nullable
  EHCDraggable getDraggable ();

  /**
   * Set the draggable state of this element
   *
   * @param eDraggable
   *        Value to set. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setDraggable (@Nullable EHCDraggable eDraggable);

  /**
   * @return <code>true</code> if HTML <code>translate</code> is
   *         <code>true</code>.
   */
  boolean isTranslateOn ();

  /**
   * @return <code>true</code> if HTML <code>translate</code> is
   *         <code>false</code>.
   */
  boolean isTranslateOff ();

  /**
   * @return <code>true</code> if HTML <code>translate</code> is not set.
   */
  boolean isTranslateUndefined ();

  /**
   * @return The value of the HTML <code>translate</code> attribute. Never
   *         <code>null</code>.
   */
  @Nonnull
  ETriState getTranslate ();

  /**
   * Set the value of the HTML <code>translate</code> attribute.
   *
   * @param bTranslate
   *        <code>true</code> to translate, <code>false</code> otherwise.
   * @return this
   */
  @Nonnull
  default IMPLTYPE setTranslate (final boolean bTranslate)
  {
    return setTranslate (ETriState.valueOf (bTranslate));
  }

  /**
   * Set the value of the HTML <code>translate</code> attribute.
   *
   * @param eTranslate
   *        The new translate state. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setTranslate (@Nonnull ETriState eTranslate);

  /**
   * @return The current state of content editable
   */
  @Nullable
  EHCContentEditable getContentEditable ();

  /**
   * Change the content editable state
   *
   * @param eContentEditable
   *        New value. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setContentEditable (@Nullable EHCContentEditable eContentEditable);

  /**
   * @return <code>true</code> if spell check is enabled, <code>false</code>
   *         otherwise.
   */
  boolean isSpellCheck ();

  /**
   * Set the value of the HTML <code>spellcheck</code> attribute.
   *
   * @param bSpellCheck
   *        <code>true</code> to enabled, <code>false</code> otherwise.
   * @return this
   */
  @Nonnull
  IMPLTYPE setSpellCheck (boolean bSpellCheck);

  /**
   * @return The role of this element. May be <code>null</code>. By default an
   *         element has no role.
   */
  @Nullable
  EHTMLRole getRole ();

  /**
   * Set the role attribute of this element.
   *
   * @param eRole
   *        The role to set. May be <code>null</code>. According to the specs,
   *        abstract roles should not be used!
   * @return this
   */
  @Nonnull
  IMPLTYPE setRole (@Nullable EHTMLRole eRole);

  /**
   * @return A non-<code>null</code> set of "custom" attributes for which no
   *         predefined method binding is available. Used e.g. for "aria-*" and
   *         "data-*" attributes.
   */
  @Nonnull
  @ReturnsMutableObject
  IHCAttrContainer customAttrs ();
}
