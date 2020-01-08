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
package com.helger.html.js;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.html.annotation.DeprecatedInHTML5;
import com.helger.html.annotation.SinceHTML5;

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
  @SinceHTML5 RESIZE ("resize", EJSEventType.WINDOW),
  @SinceHTML5 SCROLL ("scroll", EJSEventType.MOUSE),

  // Window events
  @SinceHTML5 AFTERPRINT ("afterprint", EJSEventType.WINDOW),
  @SinceHTML5 BEFOREPRINT ("beforeprint", EJSEventType.WINDOW),
  @SinceHTML5 BEFOREUNLOAD ("beforeunload", EJSEventType.WINDOW),
  @SinceHTML5 HASHCHANGE ("hashchange", EJSEventType.WINDOW),
  @SinceHTML5 MESSAGE ("message", EJSEventType.WINDOW),
  @SinceHTML5 OFFLINE ("offline", EJSEventType.WINDOW),
  @SinceHTML5 ONLINE ("online", EJSEventType.WINDOW),
  @SinceHTML5 PAGEHIDE ("pagehide", EJSEventType.WINDOW),
  @SinceHTML5 PAGESHOW ("pageshow", EJSEventType.WINDOW),
  @SinceHTML5 POPSTATE ("popstate", EJSEventType.WINDOW),
  @SinceHTML5 STORAGE ("storage", EJSEventType.WINDOW),
  UNLOAD ("unload", EJSEventType.WINDOW),

  // Document events
  @SinceHTML5 READYSTATECHANGE ("readystatechange", EJSEventType.MEDIA),

  // Form events
  // also blur
  CHANGE ("change", EJSEventType.FORM),
  @SinceHTML5 CONTEXTMENU ("contextmenu", EJSEventType.FORM),
  // also focus
  @SinceHTML5 FORMCHANGE ("formchange", EJSEventType.FORM),
  @SinceHTML5 FORMINPUT ("forminput", EJSEventType.FORM),
  @SinceHTML5 INPUT ("input", EJSEventType.FORM),
  @SinceHTML5 INVALID ("invalid", EJSEventType.FORM),
  @DeprecatedInHTML5 RESET ("reset", EJSEventType.FORM),
  SELECT ("select", EJSEventType.FORM),
  SUBMIT ("submit", EJSEventType.FORM),

  // Keyboard events
  KEYDOWN ("keydown", EJSEventType.KEYBOARD),
  KEYPRESS ("keypress", EJSEventType.KEYBOARD),
  KEYUP ("keyup", EJSEventType.KEYBOARD),

  // Mouse events
  CLICK ("click", EJSEventType.MOUSE),
  DBLCLICK ("dblclick", EJSEventType.MOUSE),
  @SinceHTML5 DRAG ("drag", EJSEventType.MOUSE),
  @SinceHTML5 DRAGEND ("dragend", EJSEventType.MOUSE),
  @SinceHTML5 DRAGENTER ("dragenter", EJSEventType.MOUSE),
  @SinceHTML5 DRAGLEAVE ("dragleave", EJSEventType.MOUSE),
  @SinceHTML5 DRAGOVER ("dragover", EJSEventType.MOUSE),
  @SinceHTML5 DRAGSTART ("dragstart", EJSEventType.MOUSE),
  @SinceHTML5 DROP ("drop", EJSEventType.MOUSE),
  MOUSEDOWN ("mousedown", EJSEventType.MOUSE),
  MOUSEMOVE ("mousemove", EJSEventType.MOUSE),
  MOUSEOUT ("mouseout", EJSEventType.MOUSE),
  MOUSEOVER ("mouseover", EJSEventType.MOUSE),
  MOUSEUP ("mouseup", EJSEventType.MOUSE),
  @SinceHTML5 MOUSEWHEEL ("mousewheel", EJSEventType.MOUSE),

  // Media events
  ABORT ("abort", EJSEventType.MEDIA),
  @SinceHTML5 CANPLAY ("canplay", EJSEventType.MEDIA),
  @SinceHTML5 CANPLAYTHROUGH ("canplaythrough", EJSEventType.MEDIA),
  @SinceHTML5 DURATIONCHANGE ("durationchange", EJSEventType.MEDIA),
  @SinceHTML5 EMPTIED ("emptied", EJSEventType.MEDIA),
  @SinceHTML5 ENDED ("ended", EJSEventType.MEDIA),
  // also error
  @SinceHTML5 LOADEDDATA ("loadeddata", EJSEventType.MEDIA),
  @SinceHTML5 LOADEDMETADATA ("loadedmetadata", EJSEventType.MEDIA),
  @SinceHTML5 LOADSTART ("loadstart", EJSEventType.MEDIA),
  @SinceHTML5 PAUSE ("pause", EJSEventType.MEDIA),
  @SinceHTML5 PLAY ("play", EJSEventType.MEDIA),
  @SinceHTML5 PLAYING ("playing", EJSEventType.MEDIA),
  @SinceHTML5 PROGRESS ("progress", EJSEventType.MEDIA),
  @SinceHTML5 RATECHANGE ("ratechange", EJSEventType.MEDIA),
  @SinceHTML5 SEEKEND ("seekend", EJSEventType.MEDIA),
  @SinceHTML5 SEEKING ("seeking", EJSEventType.MEDIA),
  @SinceHTML5 STALLED ("stalled", EJSEventType.MEDIA),
  @SinceHTML5 SUSPEND ("suspend", EJSEventType.MEDIA),
  @SinceHTML5 TIMEUPDATE ("timeupdate", EJSEventType.MEDIA),
  @SinceHTML5 VOLUMECHANGE ("volumechange", EJSEventType.MEDIA),
  @SinceHTML5 WAITING ("waiting", EJSEventType.MEDIA),

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

  @SinceHTML5 CANCEL ("cancel", EJSEventType.FORM),
  @SinceHTML5 CUECHANGE ("cuechange", EJSEventType.FORM);

  private final String m_sEvent;
  @CodingStyleguideUnaware
  private final Set <EJSEventType> m_aJSEventTypes;

  private EJSEvent (@Nonnull @Nonempty final String sEvent, @Nonnull @Nonempty final EJSEventType... aTypes)
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
