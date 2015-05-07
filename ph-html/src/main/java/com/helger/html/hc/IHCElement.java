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
package com.helger.html.hc;

import java.util.Map;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.state.ETriState;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.api.EHCTextDirection;
import com.helger.html.hc.api5.EHCContentEditable;
import com.helger.html.hc.api5.EHCDraggable;
import com.helger.html.hc.api5.EHCDropZone;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IJSCodeProvider;

/**
 * Base interface for an HC element
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        The implementation type
 */
public interface IHCElement <THISTYPE extends IHCElement <THISTYPE>> extends IHCNode, IHCHasCSSStyles <THISTYPE>, IHCHasCSSClasses <THISTYPE>
{
  /** The default value for an unset tab index, as -1 is used for "none" */
  public static final long DEFAULT_TABINDEX = -5l;

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
   * @return <code>true</code> if this element has an ID, <code>false</code> if
   *         not.
   */
  boolean hasID ();

  /**
   * Get the HTML ID of this object.<br>
   * Note: we cannot use <code>IHasID&lt;String&gt;</code> because the
   * constraint of IHasID is, that the returned ID may not be <code>null</code>
   * whereas here the HTML ID can be <code>null</code>!
   *
   * @return The HTML ID of this object.
   */
  @Nullable
  String getID ();

  /**
   * Set the HTML ID of this object.
   *
   * @param sID
   *        The ID to use. Must conform to the HTML rules for an element ID.
   * @return this
   */
  @Nonnull
  THISTYPE setID (String sID);

  /**
   * Set a unique HTML ID for this object. Equal to
   * <code>setID (GlobalIDFactory.getNewStringID ())</code>
   *
   * @return this
   */
  @Nonnull
  THISTYPE setUniqueID ();

  /**
   * Set a new ID if none is present. This is a shortcut for
   * <code>if (!hasID())setUniqueID ();</code>
   *
   * @return this
   */
  @Nonnull
  THISTYPE ensureID ();

  @Nonnull
  THISTYPE setTitle (String sTitle);

  @Nullable
  EHCTextDirection getDirection ();

  @Nonnull
  THISTYPE setDirection (@Nullable EHCTextDirection eDirection);

  @Nullable
  String getLanguage ();

  @Nonnull
  THISTYPE setLanguage (@Nullable String sLanguage);

  /**
   * Get the event handler of the specified event.
   *
   * @param eJSEvent
   *        The event to query. May be <code>null</code>.
   * @return <code>null</code> if no such event handler is registered.
   */
  @Nullable
  IJSCodeProvider getEventHandler (@Nullable EJSEvent eJSEvent);

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
  THISTYPE addEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IJSCodeProvider aJSHandler);

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
  THISTYPE prependEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IJSCodeProvider aJSHandler);

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
  THISTYPE setEventHandler (@Nonnull EJSEvent eJSEvent, @Nullable IJSCodeProvider aJSHandler);

  /**
   * Remove all event handler for the specified JS event.
   *
   * @param eJSEvent
   *        The JS event to remove the handler. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE removeAllEventHandler (@Nullable EJSEvent eJSEvent);

  boolean isUnfocusable ();

  @Nonnull
  THISTYPE setUnfocusable (boolean bUnfocusable);

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
  THISTYPE setHidden (boolean bHidden);

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
  THISTYPE setTabIndex (long nTabIndex);

  @Nullable
  String getAccessKey ();

  @Nonnull
  THISTYPE setAccessKey (@Nullable String sAccessKey);

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
  THISTYPE setDraggable (@Nullable EHCDraggable eDraggable);

  /**
   * @return The drop zone value. May be <code>null</code>.
   */
  @Nullable
  EHCDropZone getDropZone ();

  /**
   * Set the drop zone value of this element.
   *
   * @param eDropZone
   *        Value to set. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE setDropZone (@Nullable EHCDropZone eDropZone);

  boolean isTranslateOn ();

  boolean isTranslateOff ();

  boolean isTranslateUndefined ();

  @Nonnull
  THISTYPE setTranslate (boolean bTranslate);

  @Nonnull
  THISTYPE setTranslate (@Nonnull ETriState eTranslate);

  /**
   * @return The current state of content editable
   */
  @Nullable
  EHCContentEditable getContentEditable ();

  /**
   * Change the content editable state
   *
   * @param eContentEditable
   *        New value
   * @return this
   */
  @Nonnull
  THISTYPE setContentEditable (@Nullable EHCContentEditable eContentEditable);

  /**
   * @return The ID of the &lt;menu&gt; element that should be used as the
   *         context menu. May be <code>null</code>.
   */
  @Nullable
  String getContextMenu ();

  /**
   * Set the ID of the &lt;menu&gt; element that should add as a context menu
   *
   * @param sContextMenu
   *        The ID of the &lt;menu&gt; element
   * @return this
   */
  @Nonnull
  THISTYPE setContextMenu (@Nullable String sContextMenu);

  boolean isSpellCheck ();

  @Nonnull
  THISTYPE setSpellCheck (boolean bSpellCheck);

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
  THISTYPE setRole (@Nullable EHTMLRole eRole);

  /**
   * @return <code>true</code> if at least one custom attribute is contained
   */
  boolean hasCustomAttrs ();

  /**
   * @return The number of contained custom attributes. Always &ge; 0.
   */
  @Nonnegative
  int getCustomAttrCount ();

  /**
   * Check if a certain custom attribute is contained
   *
   * @param sName
   *        The name of the custom attribute to check
   * @return <code>true</code> if such a custom attribute is contained.
   */
  boolean containsCustomAttr (@Nullable String sName);

  /**
   * Get the value of a certain custom attribute
   *
   * @param sName
   *        The name of the custom attribute to retrieve the value from
   * @return <code>null</code> if no such custom attribute is contained.
   */
  @Nullable
  String getCustomAttrValue (@Nullable String sName);

  /**
   * @return All custom attributes contained. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <String, String> getAllCustomAttrs ();

  /**
   * Set a custom attribute that is serialized as is.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return this
   */
  @Nonnull
  THISTYPE setCustomAttr (@Nullable String sName, int nValue);

  /**
   * Set a custom attribute that is serialized as is.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return this
   */
  @Nonnull
  THISTYPE setCustomAttr (@Nullable String sName, long nValue);

  /**
   * Set a custom attribute that is serialized as is.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param sValue
   *        The value of the attribute. If it is <code>null</code> nothing
   *        happens
   * @return this
   */
  @Nonnull
  THISTYPE setCustomAttr (@Nullable String sName, @Nullable String sValue);

  /**
   * Remove the custom attribute with the specified name
   *
   * @param sName
   *        The name of the custom attribute to be removed
   * @return this
   */
  @Nonnull
  THISTYPE removeCustomAttr (@Nullable String sName);

  /**
   * @return <code>true</code> if at least one data attribute is contained
   */
  boolean hasDataAttrs ();

  /**
   * Check if a certain data attribute is contained. Shortcut for
   * <code>containsCustomAttr ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to check
   * @return <code>true</code> if such a data attribute is contained.
   */
  boolean containsDataAttr (@Nullable String sName);

  /**
   * Get the value of a certain data attribute. Shortcut for
   * <code>getCustomAttrValue ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to retrieve the value from
   * @return <code>null</code> if no such data attribute is contained.
   */
  @Nullable
  String getDataAttrValue (@Nullable String sName);

  /**
   * @return All data attributes contained. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <String, String> getAllDataAttrs ();

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, nValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return this
   */
  @Nonnull
  THISTYPE setDataAttr (@Nullable String sName, int nValue);

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, nValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param nValue
   *        The value of the attribute that is converted to a String.
   * @return this
   */
  @Nonnull
  THISTYPE setDataAttr (@Nullable String sName, long nValue);

  /**
   * Set a data attribute that is serialized as is. Shortcut for
   * <code>setCustomAttr ("data-"+sName, sValue)</code>.
   *
   * @param sName
   *        The name of the attribute. If it is <code>null</code> nothing
   *        happens
   * @param sValue
   *        The value of the attribute. If it is <code>null</code> nothing
   *        happens
   * @return this
   */
  @Nonnull
  THISTYPE setDataAttr (@Nullable String sName, @Nullable String sValue);

  /**
   * Remove the data attribute with the specified name. Shortcut for
   * <code>removeCustomAttr ("data-"+sName)</code>.
   *
   * @param sName
   *        The name of the data attribute to be removed
   * @return this
   */
  @Nonnull
  THISTYPE removeDataAttr (@Nullable String sName);
}
