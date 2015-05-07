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
package com.helger.html.js;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsImmutableObject;
import com.helger.commons.collections.CollectionHelper;
import com.helger.html.annotations.DeprecatedInHTML5;
import com.helger.html.annotations.SinceHTML5;

/**
 * This enumeration contains all known java script events.
 *
 * @author boris, philip
 */
public enum EJSEvent
{
  // For all elements
  ONBLUR ("onblur", EJSEventType.WINDOW, EJSEventType.FORM),
  ONERROR ("onerror", EJSEventType.WINDOW, EJSEventType.MEDIA),
  ONFOCUS ("onfocus", EJSEventType.WINDOW, EJSEventType.FORM),
  ONLOAD ("onload", EJSEventType.WINDOW),
  @SinceHTML5
  ONRESIZE ("onresize", EJSEventType.WINDOW),
  @SinceHTML5
  ONSCROLL ("onscroll", EJSEventType.MOUSE),

  // Window events
  @SinceHTML5
  ONAFTERPRINT ("onafterprint", EJSEventType.WINDOW),
  @SinceHTML5
  ONBEFOREPRINT ("onbeforeprint", EJSEventType.WINDOW),
  @SinceHTML5
  ONBEFOREUNLOAD ("onbeforeunload", EJSEventType.WINDOW),
  @SinceHTML5
  ONHASHCHANGE ("onhashchange", EJSEventType.WINDOW),
  @SinceHTML5
  ONMESSAGE ("onmessage", EJSEventType.WINDOW),
  @SinceHTML5
  ONOFFLINE ("onoffline", EJSEventType.WINDOW),
  @SinceHTML5
  ONONLINE ("ononline", EJSEventType.WINDOW),
  @SinceHTML5
  ONPAGEHIDE ("onpagehide", EJSEventType.WINDOW),
  @SinceHTML5
  ONPAGESHOW ("onpageshow", EJSEventType.WINDOW),
  @SinceHTML5
  ONPOPSTATE ("onpopstate", EJSEventType.WINDOW),
  @SinceHTML5
  ONSTORAGE ("onstorage", EJSEventType.WINDOW),
  ONUNLOAD ("onunload", EJSEventType.WINDOW),

  // Document events
  @SinceHTML5
  ONREADYSTATECHANGE ("onreadystatechange", EJSEventType.MEDIA),

  // Form events
  // also onblur
  ONCHANGE ("onchange", EJSEventType.FORM),
  @SinceHTML5
  ONCONTEXTMENU ("oncontextmenu", EJSEventType.FORM),
  // also onfocus
  @SinceHTML5
  ONFORMCHANGE ("onformchange", EJSEventType.FORM),
  @SinceHTML5
  ONFORMINPUT ("onforminput", EJSEventType.FORM),
  @SinceHTML5
  ONINPUT ("oninput", EJSEventType.FORM),
  @SinceHTML5
  ONINVALID ("oninvalid", EJSEventType.FORM),
  @DeprecatedInHTML5
  ONRESET ("onreset", EJSEventType.FORM),
  ONSELECT ("onselect", EJSEventType.FORM),
  ONSUBMIT ("onsubmit", EJSEventType.FORM),

  // Keyboard events
  ONKEYDOWN ("onkeydown", EJSEventType.KEYBOARD),
  ONKEYPRESS ("onkeypress", EJSEventType.KEYBOARD),
  ONKEYUP ("onkeyup", EJSEventType.KEYBOARD),

  // Mouse events
  ONCLICK ("onclick", EJSEventType.MOUSE),
  ONDBLCLICK ("ondblclick", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAG ("ondrag", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAGEND ("ondragend", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAGENTER ("ondragenter", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAGLEAVE ("ondragleave", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAGOVER ("ondragover", EJSEventType.MOUSE),
  @SinceHTML5
  ONDRAGSTART ("ondragstart", EJSEventType.MOUSE),
  @SinceHTML5
  ONDROP ("ondrop", EJSEventType.MOUSE),
  ONMOUSEDOWN ("onmousedown", EJSEventType.MOUSE),
  ONMOUSEMOVE ("onmousemove", EJSEventType.MOUSE),
  ONMOUSEOUT ("onmouseout", EJSEventType.MOUSE),
  ONMOUSEOVER ("onmouseover", EJSEventType.MOUSE),
  ONMOUSEUP ("onmouseup", EJSEventType.MOUSE),
  @SinceHTML5
  ONMOUSEWHEEL ("onmousewheel", EJSEventType.MOUSE),

  // Media events
  ONABORT ("onabort", EJSEventType.MEDIA),
  @SinceHTML5
  ONCANPLAY ("oncanplay", EJSEventType.MEDIA),
  @SinceHTML5
  ONCANPLAYTHROUGH ("oncanplaythrough", EJSEventType.MEDIA),
  @SinceHTML5
  ONDURATIONCHANGE ("ondurationchange", EJSEventType.MEDIA),
  @SinceHTML5
  ONEMPTIED ("onemptied", EJSEventType.MEDIA),
  @SinceHTML5
  ONENDED ("onended", EJSEventType.MEDIA),
  // also onerror
  @SinceHTML5
  ONLOADEDDATA ("onloadeddata", EJSEventType.MEDIA),
  @SinceHTML5
  ONLOADEDMETADATA ("onloadedmetadata", EJSEventType.MEDIA),
  @SinceHTML5
  ONLOADSTART ("onloadstart", EJSEventType.MEDIA),
  @SinceHTML5
  ONPAUSE ("onpause", EJSEventType.MEDIA),
  @SinceHTML5
  ONPLAY ("onplay", EJSEventType.MEDIA),
  @SinceHTML5
  ONPLAYING ("onplaying", EJSEventType.MEDIA),
  @SinceHTML5
  ONPROGRESS ("onprogress", EJSEventType.MEDIA),
  @SinceHTML5
  ONRATECHANGE ("onratechange", EJSEventType.MEDIA),
  @SinceHTML5
  ONSEEKEND ("onseekend", EJSEventType.MEDIA),
  @SinceHTML5
  ONSEEKING ("onseeking", EJSEventType.MEDIA),
  @SinceHTML5
  ONSTALLED ("onstalled", EJSEventType.MEDIA),
  @SinceHTML5
  ONSUSPEND ("onsuspend", EJSEventType.MEDIA),
  @SinceHTML5
  ONTIMEUPDATE ("ontimeupdate", EJSEventType.MEDIA),
  @SinceHTML5
  ONVOLUMECHANGE ("onvolumechange", EJSEventType.MEDIA),
  @SinceHTML5
  ONWAITING ("onwaiting", EJSEventType.MEDIA),

  // Touch events
  TOUCHSTART ("touchstart", EJSEventType.TOUCH),
  TOUCHMOVE ("touchmove", EJSEventType.TOUCH),
  TOUCHEND ("touchend", EJSEventType.TOUCH),
  GESTURESTART ("gesturestart", EJSEventType.TOUCH),
  GESTUREMOVE ("gesturemove", EJSEventType.TOUCH),
  GESTUREEND ("gestureend", EJSEventType.TOUCH),

  // rest

  @SinceHTML5
  ONCANCEL ("oncancel", EJSEventType.FORM),
  @SinceHTML5
  ONCUECHANGE ("oncuechange", EJSEventType.FORM), ;

  private final String m_sEvent;
  private final Set <EJSEventType> m_aJSEventTypes;

  private EJSEvent (@Nonnull @Nonempty final String sEvent, @Nonnull @Nonempty final EJSEventType... aTypes)
  {
    m_sEvent = sEvent;
    m_aJSEventTypes = CollectionHelper.newUnmodifiableSet (aTypes);
  }

  /**
   * @return The HTML attribute to be emitted for this event.
   */
  @Nonnull
  @Nonempty
  public String getEvent ()
  {
    return m_sEvent;
  }

  @Nonnull
  @Nonempty
  @ReturnsImmutableObject
  public Set <EJSEventType> getAllTypes ()
  {
    return m_aJSEventTypes;
  }

  public boolean isForType (@Nullable final EJSEventType eJSEventType)
  {
    return m_aJSEventTypes.contains (eJSEventType);
  }
}
