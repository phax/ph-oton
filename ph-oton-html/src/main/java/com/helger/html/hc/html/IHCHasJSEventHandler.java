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
package com.helger.html.hc.html;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.trait.IGenericImplTrait;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.JSEventMap;

/**
 * Base interface for objects having JS event handlers
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 * @since 9.3.0 as an extra interface
 */
public interface IHCHasJSEventHandler <IMPLTYPE extends IHCHasJSEventHandler <IMPLTYPE>> extends
                                      IGenericImplTrait <IMPLTYPE>
{
  /**
   * @return The internal event map. May be <code>null</code>.
   */
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
  @NonNull
  IMPLTYPE addEventHandler (@NonNull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

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
  @NonNull
  IMPLTYPE prependEventHandler (@NonNull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

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
  @NonNull
  IMPLTYPE setEventHandler (@NonNull EJSEvent eJSEvent, @Nullable IHasJSCode aJSHandler);

  /**
   * Remove all event handler for the specified JS event.
   *
   * @param eJSEvent
   *        The JS event to remove the handler. May be <code>null</code>.
   * @return this
   */
  @NonNull
  IMPLTYPE removeAllEventHandler (@Nullable EJSEvent eJSEvent);
}
