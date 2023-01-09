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
package com.helger.html.js;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.collection.impl.CommonsHashSet;

/**
 * This enumeration contains all known java script events.
 *
 * @author boris, philip
 */
public enum EJSEvent
{
  // For all elements
  BLUR ("blur", EJSEventType.WINDOW, EJSEventType.FORM),
  ERROR ("error", EJSEventType.WINDOW, EJSEventType.MEDIA),
  FOCUS ("focus", EJSEventType.WINDOW, EJSEventType.FORM),
  LOAD ("load", EJSEventType.WINDOW),
  RESIZE ("resize", EJSEventType.WINDOW),
  SCROLL ("scroll", EJSEventType.MOUSE),

  // Window events
  AFTERPRINT ("afterprint", EJSEventType.WINDOW),
  BEFOREPRINT ("beforeprint", EJSEventType.WINDOW),
  BEFOREUNLOAD ("beforeunload", EJSEventType.WINDOW),
  HASHCHANGE ("hashchange", EJSEventType.WINDOW),
  MESSAGE ("message", EJSEventType.WINDOW),
  OFFLINE ("offline", EJSEventType.WINDOW),
  ONLINE ("online", EJSEventType.WINDOW),
  PAGEHIDE ("pagehide", EJSEventType.WINDOW),
  PAGESHOW ("pageshow", EJSEventType.WINDOW),
  POPSTATE ("popstate", EJSEventType.WINDOW),
  STORAGE ("storage", EJSEventType.WINDOW),
  UNLOAD ("unload", EJSEventType.WINDOW),

  // Document events
  READYSTATECHANGE ("readystatechange", EJSEventType.MEDIA),

  // Form events
  // also blur
  CHANGE ("change", EJSEventType.FORM),
  CONTEXTMENU ("contextmenu", EJSEventType.FORM),
  // also focus
  FORMCHANGE ("formchange", EJSEventType.FORM),
  FORMINPUT ("forminput", EJSEventType.FORM),
  INPUT ("input", EJSEventType.FORM),
  INVALID ("invalid", EJSEventType.FORM),
  @Deprecated
  RESET ("reset", EJSEventType.FORM),
  SELECT ("select", EJSEventType.FORM),
  SUBMIT ("submit", EJSEventType.FORM),

  // Keyboard events
  KEYDOWN ("keydown", EJSEventType.KEYBOARD),
  KEYPRESS ("keypress", EJSEventType.KEYBOARD),
  KEYUP ("keyup", EJSEventType.KEYBOARD),

  // Mouse events
  CLICK ("click", EJSEventType.MOUSE),
  DBLCLICK ("dblclick", EJSEventType.MOUSE),
  DRAG ("drag", EJSEventType.MOUSE),
  DRAGEND ("dragend", EJSEventType.MOUSE),
  DRAGENTER ("dragenter", EJSEventType.MOUSE),
  DRAGLEAVE ("dragleave", EJSEventType.MOUSE),
  DRAGOVER ("dragover", EJSEventType.MOUSE),
  DRAGSTART ("dragstart", EJSEventType.MOUSE),
  DROP ("drop", EJSEventType.MOUSE),
  MOUSEDOWN ("mousedown", EJSEventType.MOUSE),
  MOUSEMOVE ("mousemove", EJSEventType.MOUSE),
  MOUSEOUT ("mouseout", EJSEventType.MOUSE),
  MOUSEOVER ("mouseover", EJSEventType.MOUSE),
  MOUSEUP ("mouseup", EJSEventType.MOUSE),
  MOUSEWHEEL ("mousewheel", EJSEventType.MOUSE),

  // Media events
  ABORT ("abort", EJSEventType.MEDIA),
  CANPLAY ("canplay", EJSEventType.MEDIA),
  CANPLAYTHROUGH ("canplaythrough", EJSEventType.MEDIA),
  DURATIONCHANGE ("durationchange", EJSEventType.MEDIA),
  EMPTIED ("emptied", EJSEventType.MEDIA),
  ENDED ("ended", EJSEventType.MEDIA),
  // also error
  LOADEDDATA ("loadeddata", EJSEventType.MEDIA),
  LOADEDMETADATA ("loadedmetadata", EJSEventType.MEDIA),
  LOADSTART ("loadstart", EJSEventType.MEDIA),
  PAUSE ("pause", EJSEventType.MEDIA),
  PLAY ("play", EJSEventType.MEDIA),
  PLAYING ("playing", EJSEventType.MEDIA),
  PROGRESS ("progress", EJSEventType.MEDIA),
  RATECHANGE ("ratechange", EJSEventType.MEDIA),
  SEEKEND ("seekend", EJSEventType.MEDIA),
  SEEKING ("seeking", EJSEventType.MEDIA),
  STALLED ("stalled", EJSEventType.MEDIA),
  SUSPEND ("suspend", EJSEventType.MEDIA),
  TIMEUPDATE ("timeupdate", EJSEventType.MEDIA),
  VOLUMECHANGE ("volumechange", EJSEventType.MEDIA),
  WAITING ("waiting", EJSEventType.MEDIA),

  // Touch events
  TOUCHSTART ("touchstart", EJSEventType.TOUCH),
  TOUCHMOVE ("touchmove", EJSEventType.TOUCH),
  TOUCHEND ("touchend", EJSEventType.TOUCH),
  GESTURESTART ("gesturestart", EJSEventType.TOUCH),
  GESTUREMOVE ("gesturemove", EJSEventType.TOUCH),
  GESTUREEND ("gestureend", EJSEventType.TOUCH),

  // Pointer events
  POINTEROVER ("pointerover", EJSEventType.POINTER),
  POINTERENTER ("pointerenter", EJSEventType.POINTER),
  POINTERDOWN ("pointerdown", EJSEventType.POINTER),
  POINTERMOVE ("pointermove", EJSEventType.POINTER),
  POINTERUP ("pointerup", EJSEventType.POINTER),
  POINTERCANCEL ("pointercancel", EJSEventType.POINTER),
  POINTEROUT ("pointerout", EJSEventType.POINTER),
  POINTERLEAVE ("pointerleave", EJSEventType.POINTER),
  GOTPOINTERCAPTURE ("gotpointercapture", EJSEventType.POINTER),
  LOSTPOINTERCAPTURE ("lostpointercapture", EJSEventType.POINTER),
  // rest

  CANCEL ("cancel", EJSEventType.FORM),
  CUECHANGE ("cuechange", EJSEventType.FORM);

  private final String m_sEvent;
  @CodingStyleguideUnaware
  private final Set <EJSEventType> m_aJSEventTypes;

  EJSEvent (@Nonnull @Nonempty final String sEvent, @Nonnull @Nonempty final EJSEventType... aTypes)
  {
    m_sEvent = sEvent;
    m_aJSEventTypes = new CommonsHashSet <> (aTypes).getAsUnmodifiable ();
  }

  /**
   * @return The JS event name without a leading "on"
   */
  @Nonnull
  @Nonempty
  public String getJSEventName ()
  {
    return m_sEvent;
  }

  /**
   * @return The HTML attribute to be emitted for this event (starting with
   *         "on")
   */
  @Nonnull
  @Nonempty
  public String getHTMLEventName ()
  {
    return "on" + m_sEvent;
  }

  @Nonnull
  @Nonempty
  @ReturnsImmutableObject
  @CodingStyleguideUnaware
  public Set <EJSEventType> getAllTypes ()
  {
    return m_aJSEventTypes;
  }

  public boolean isForType (@Nullable final EJSEventType eJSEventType)
  {
    return m_aJSEventTypes.contains (eJSEventType);
  }
}
