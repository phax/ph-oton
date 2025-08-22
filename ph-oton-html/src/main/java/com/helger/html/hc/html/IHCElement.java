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
package com.helger.html.hc.html;

import java.util.function.Consumer;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.state.ETriState;
import com.helger.base.string.StringHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.IHCNode;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

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
                            IHCHasCSSClasses <IMPLTYPE>,
                            IHCHasJSEventHandler <IMPLTYPE>
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
   * @return <code>true</code> if this element should be automatically focused,
   *         <code>false</code> if not.
   * @since 9.3.0
   */
  boolean isAutoFocus ();

  /**
   * Set the value of the HTML <code>autofocus</code> attribute.
   *
   * @param bAutoFocus
   *        <code>true</code> to enable, <code>false</code> to disable.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setAutoFocus (boolean bAutoFocus);

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
   * @return The value of the HTML <code>enterkeyhint</code> attribute. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getEnterKeyHint ();

  /**
   * Set the value of the HTML <code>enterkeyhint</code> attribute.
   *
   * @param sEnterKeyHint
   *        The new enter key hint. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setEnterKeyHint (@Nullable String sEnterKeyHint);

  /**
   * @return The value of the HTML <code>exportparts</code> attribute. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getExportParts ();

  /**
   * Set the value of the HTML <code>exportparts</code> attribute.
   *
   * @param sExportParts
   *        The new export parts. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setExportParts (@Nullable String sExportParts);

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

  // getID and setID would be here

  /**
   * @return <code>true</code> if this is inert
   * @since 9.3.0
   */
  boolean isInert ();

  /**
   * Set the inert state of this element
   *
   * @param bInert
   *        <code>true</code> if it is inert, <code>false</code> otherwise
   * @return this
   * @since 9.3.0
   */
  IMPLTYPE setInert (boolean bInert);

  /**
   * @return the inputmode. May be <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  EHCInputMode getInputMode ();

  /**
   * Set the inputmode state of this element
   *
   * @param eInputMode
   *        Value to set. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setInputMode (@Nullable EHCInputMode eInputMode);

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

  /**
   * @return The value of the HTML <code>nonce</code> attribute. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getNonce ();

  /**
   * @return <code>true</code> if the HTML <code>nonce</code> attribute is
   *         present, <code>false</code> if not.
   * @since 9.3.0
   */
  default boolean hasNonce ()
  {
    return StringHelper.hasText (getNonce ());
  }

  /**
   * Set the value of the HTML <code>nonce</code> attribute.
   *
   * @param sNonce
   *        The new nonce. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setNonce (@Nullable String sNonce);

  /**
   * @return The value of the HTML <code>part</code> attribute. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getPart ();

  /**
   * Set the value of the HTML <code>part</code> attribute.
   *
   * @param sPart
   *        The new part. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setPart (@Nullable String sPart);

  /**
   * @return The value of the HTML <code>slot</code> attribute. May be
   *         <code>null</code>.
   * @since 9.3.0
   */
  @Nullable
  String getSlot ();

  /**
   * Set the value of the HTML <code>slot</code> attribute.
   *
   * @param sSlot
   *        The new slot. May be <code>null</code>.
   * @return this
   * @since 9.3.0
   */
  @Nonnull
  IMPLTYPE setSlot (@Nullable String sSlot);

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

  //////////////////////////////////////////////////

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

  /**
   * A helper method that deals with custom attributes, but maintains the
   * chainable manner of this API.
   *
   * @param aConsumer
   *        The consumer that deals with the custom attributes. May not be
   *        <code>null</code>.
   * @return this for chaining
   * @since 8.4.4
   */
  @Nonnull
  default IMPLTYPE withCustomAttrs (@Nonnull final Consumer <? super IHCAttrContainer> aConsumer)
  {
    aConsumer.accept (customAttrs ());
    return thisAsT ();
  }
}
