/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroQName;

/**
 * Contains constant HTML attribute names.
 *
 * @author Philip Helger
 */
@Immutable
public final class CHTMLAttributes
{
  /** The standard prefix for HTML5 custom attributes */
  public static final String HTML5_PREFIX_DATA = "data-";

  /** The standard prefix for ARIA attributes */
  public static final String PREFIX_ARIA = "aria-";

  public static final IMicroQName ABBR = new MicroQName ("abbr");
  public static final IMicroQName ACCEPT = new MicroQName ("accept");
  public static final IMicroQName ACCEPTCHARSET = new MicroQName ("accept-charset");
  public static final IMicroQName AUTOCOMPLETE = new MicroQName ("autocomplete");
  public static final IMicroQName ACCESSKEY = new MicroQName ("accesskey");
  public static final IMicroQName ACTION = new MicroQName ("action");
  public static final IMicroQName ALIGN = new MicroQName ("align");
  public static final IMicroQName ALT = new MicroQName ("alt");
  public static final IMicroQName ARCHIVE = new MicroQName ("archive");
  public static final IMicroQName ASYNC = new MicroQName ("async");
  public static final IMicroQName AUTOFOCUS = new MicroQName ("autofocus");
  public static final IMicroQName AUTOPLAY = new MicroQName ("autoplay");
  public static final IMicroQName AUTOSTART = new MicroQName ("autostart");
  public static final IMicroQName AXIS = new MicroQName ("axis");
  public static final IMicroQName BGCOLOR = new MicroQName ("bgcolor");
  public static final IMicroQName BASE = new MicroQName ("base");
  public static final IMicroQName BORDER = new MicroQName ("border");
  public static final IMicroQName CELLPADDING = new MicroQName ("cellpadding");
  public static final IMicroQName CELLSPACING = new MicroQName ("cellspacing");
  public static final IMicroQName CHALLENGE = new MicroQName ("challenge");
  public static final IMicroQName CHAR = new MicroQName ("char");
  public static final IMicroQName CHAROFF = new MicroQName ("charoff");
  public static final IMicroQName CHARSET = new MicroQName ("charset");
  public static final IMicroQName CHECKED = new MicroQName ("checked");
  public static final IMicroQName CITE = new MicroQName ("cite");
  public static final IMicroQName CLASS = new MicroQName ("class");
  public static final IMicroQName CLASSID = new MicroQName ("classid");
  public static final IMicroQName CLEAR = new MicroQName ("clear");
  public static final IMicroQName CODE = new MicroQName ("code");
  public static final IMicroQName CODEBASE = new MicroQName ("codebase");
  public static final IMicroQName CODETYPE = new MicroQName ("codetype");
  public static final IMicroQName COLOR = new MicroQName ("color");
  public static final IMicroQName COLS = new MicroQName ("cols");
  public static final IMicroQName COLSPAN = new MicroQName ("colspan");
  public static final IMicroQName COMMAND = new MicroQName ("command");
  public static final IMicroQName COMPACT = new MicroQName ("compact");
  public static final IMicroQName CONTENT = new MicroQName ("content");
  public static final IMicroQName CONTENTEDITABLE = new MicroQName ("contenteditable");
  public static final IMicroQName CONTEXTMENU = new MicroQName ("contextmenu");
  public static final IMicroQName CONTROLLER = new MicroQName ("controller");
  public static final IMicroQName CONTROLS = new MicroQName ("controls");
  public static final IMicroQName COORDS = new MicroQName ("coords");
  public static final IMicroQName CROSSORIGIN = new MicroQName ("crossorigin");
  public static final IMicroQName DATA = new MicroQName ("data");
  public static final IMicroQName DATETIME = new MicroQName ("datetime");
  public static final IMicroQName DECLARE = new MicroQName ("declare");
  public static final IMicroQName DEFAULT = new MicroQName ("default");
  public static final IMicroQName DEFER = new MicroQName ("defer");
  public static final IMicroQName DIR = new MicroQName ("dir");
  public static final IMicroQName DIRNAME = new MicroQName ("dirname");
  public static final IMicroQName DISABLED = new MicroQName ("disabled");
  public static final IMicroQName DRAGGABLE = new MicroQName ("draggable");
  public static final IMicroQName DROPZONE = new MicroQName ("dropzone");
  public static final IMicroQName ENCTYPE = new MicroQName ("enctype");
  public static final IMicroQName ENDTIME = new MicroQName ("endtime");
  public static final IMicroQName FACE = new MicroQName ("face");
  public static final IMicroQName FOR = new MicroQName ("for");
  public static final IMicroQName FORM = new MicroQName ("form");
  public static final IMicroQName FORMACTION = new MicroQName ("formaction");
  public static final IMicroQName FORMENCTYPE = new MicroQName ("formenctype");
  public static final IMicroQName FORMMETHOD = new MicroQName ("formmethod");
  public static final IMicroQName FORMNOVALIDATE = new MicroQName ("formnovalidate");
  public static final IMicroQName FORMTARGET = new MicroQName ("formtarget");
  public static final IMicroQName FRAME = new MicroQName ("frame");
  public static final IMicroQName FRAMEBORDER = new MicroQName ("frameborder");
  public static final IMicroQName HEADERS = new MicroQName ("headers");
  public static final IMicroQName HEIGHT = new MicroQName ("height");
  public static final IMicroQName HIDDEN = new MicroQName ("hidden");
  public static final IMicroQName HIGH = new MicroQName ("high");
  public static final IMicroQName HREF = new MicroQName ("href");
  public static final IMicroQName HREFLANG = new MicroQName ("hreflang");
  public static final IMicroQName HSPACE = new MicroQName ("hspace");
  public static final IMicroQName HTTP_EQUIV = new MicroQName ("http-equiv");
  public static final IMicroQName ICON = new MicroQName ("icon");
  public static final IMicroQName ID = new MicroQName ("id");
  public static final IMicroQName INPUTMODE = new MicroQName ("inputmode");
  public static final IMicroQName INTEGRITY = new MicroQName ("integrity");
  public static final IMicroQName ISMAP = new MicroQName ("ismap");
  public static final IMicroQName KEYTYPE = new MicroQName ("keytype");
  public static final IMicroQName KIND = new MicroQName ("kind");
  public static final IMicroQName LABEL = new MicroQName ("label");
  public static final IMicroQName LANG = new MicroQName ("lang");
  public static final IMicroQName LANGUAGE = new MicroQName ("language");
  public static final IMicroQName LIST = new MicroQName ("list");
  public static final IMicroQName LOOP = new MicroQName ("loop");
  public static final IMicroQName LONGDESC = new MicroQName ("longdesc");
  public static final IMicroQName LOW = new MicroQName ("low");
  public static final IMicroQName MARGINHEIGHT = new MicroQName ("marginheight");
  public static final IMicroQName MARGINWIDTH = new MicroQName ("marginwidth");
  public static final IMicroQName MASTERSOUND = new MicroQName ("mastersound");
  public static final IMicroQName MAX = new MicroQName ("max");
  public static final IMicroQName MAXLENGTH = new MicroQName ("maxlength");
  public static final IMicroQName MEDIA = new MicroQName ("media");
  public static final IMicroQName MENU = new MicroQName ("menu");
  public static final IMicroQName METHOD = new MicroQName ("method");
  public static final IMicroQName MIN = new MicroQName ("min");
  public static final IMicroQName MINLENGTH = new MicroQName ("minlength");
  public static final IMicroQName MULTIPLE = new MicroQName ("multiple");
  public static final IMicroQName MUTED = new MicroQName ("muted");
  public static final IMicroQName NAME = new MicroQName ("name");
  public static final IMicroQName NOHREF = new MicroQName ("nohref");
  public static final IMicroQName NORESIZE = new MicroQName ("noresize");
  public static final IMicroQName NOSHADE = new MicroQName ("noshade");
  public static final IMicroQName NOVALIDATE = new MicroQName ("novalidate");
  public static final IMicroQName NOWRAP = new MicroQName ("nowrap");
  public static final IMicroQName OBJECT = new MicroQName ("object");
  public static final IMicroQName OPEN = new MicroQName ("open");
  public static final IMicroQName OPTIMUM = new MicroQName ("optimum");
  public static final IMicroQName PALETTE = new MicroQName ("palette");
  public static final IMicroQName PATTERN = new MicroQName ("pattern");
  public static final IMicroQName PLACEHOLDER = new MicroQName ("placeholder");
  public static final IMicroQName PLAYCOUNT = new MicroQName ("playcount");
  public static final IMicroQName PLUGINSPAGE = new MicroQName ("pluginspage");
  public static final IMicroQName PLUGINURL = new MicroQName ("pluginurl");
  public static final IMicroQName PRELOAD = new MicroQName ("preload");
  public static final IMicroQName PROFILE = new MicroQName ("profile");
  public static final IMicroQName QUALITY = new MicroQName ("quality");
  public static final IMicroQName RADIOGROUP = new MicroQName ("radiogroup");
  public static final IMicroQName READONLY = new MicroQName ("readonly");
  public static final IMicroQName REQUIRED = new MicroQName ("required");
  public static final IMicroQName REL = new MicroQName ("rel");
  public static final IMicroQName REV = new MicroQName ("rev");
  public static final IMicroQName REVERSED = new MicroQName ("reversed");
  public static final IMicroQName ROLE = new MicroQName ("role");
  public static final IMicroQName ROWS = new MicroQName ("rows");
  public static final IMicroQName ROWSPAN = new MicroQName ("rowspan");
  public static final IMicroQName RULES = new MicroQName ("rules");
  public static final IMicroQName SANDBOX = new MicroQName ("sandbox");
  public static final IMicroQName SCALE = new MicroQName ("scale");
  public static final IMicroQName SCHEME = new MicroQName ("scheme");
  public static final IMicroQName SCOPE = new MicroQName ("scope");
  public static final IMicroQName SCROLLING = new MicroQName ("scrolling");
  public static final IMicroQName SELECTED = new MicroQName ("selected");
  public static final IMicroQName SHAPE = new MicroQName ("shape");
  public static final IMicroQName SIZE = new MicroQName ("size");
  public static final IMicroQName SIZES = new MicroQName ("sizes");
  public static final IMicroQName SPAN = new MicroQName ("span");
  public static final IMicroQName SPELLCHECK = new MicroQName ("spellcheck");
  public static final IMicroQName SRC = new MicroQName ("src");
  public static final IMicroQName SRCLANG = new MicroQName ("srclang");
  public static final IMicroQName SRCSET = new MicroQName ("srcset");
  public static final IMicroQName STANDBY = new MicroQName ("standby");
  public static final IMicroQName START = new MicroQName ("start");
  public static final IMicroQName STARTTIME = new MicroQName ("starttime");
  public static final IMicroQName STEP = new MicroQName ("step");
  public static final IMicroQName STYLE = new MicroQName ("style");
  public static final IMicroQName SUMMARY = new MicroQName ("summary");
  public static final IMicroQName SWLIVECONNECT = new MicroQName ("swliveconnect");
  public static final IMicroQName TABINDEX = new MicroQName ("tabindex");
  public static final IMicroQName TARGET = new MicroQName ("target");
  public static final IMicroQName TITLE = new MicroQName ("title");
  public static final IMicroQName TRANSLATE = new MicroQName ("translate");
  public static final IMicroQName TYPE = new MicroQName ("type");
  public static final IMicroQName UNITS = new MicroQName ("units");
  public static final IMicroQName USEMAP = new MicroQName ("usemap");
  public static final IMicroQName VSPACE = new MicroQName ("vspace");
  public static final IMicroQName VALIGN = new MicroQName ("valign");
  public static final IMicroQName VALUE = new MicroQName ("value");
  public static final IMicroQName VALUETYPE = new MicroQName ("valuetype");
  public static final IMicroQName VOLUME = new MicroQName ("volume");
  public static final IMicroQName WIDTH = new MicroQName ("width");
  public static final IMicroQName WRAP = new MicroQName ("wrap");

  public static final IMicroQName ARIA_ACTIVEDESCENDANT = new MicroQName (PREFIX_ARIA + "activedescendant");
  public static final IMicroQName ARIA_ATOMIC = new MicroQName (PREFIX_ARIA + "atomic");
  public static final IMicroQName ARIA_AUTOCOMPLETE = new MicroQName (PREFIX_ARIA + "autocomplete");
  public static final IMicroQName ARIA_BUSY = new MicroQName (PREFIX_ARIA + "busy");
  public static final IMicroQName ARIA_CHECKED = new MicroQName (PREFIX_ARIA + "checked");
  public static final IMicroQName ARIA_CONTROLS = new MicroQName (PREFIX_ARIA + "controls");
  public static final IMicroQName ARIA_DESCRIBEDBY = new MicroQName (PREFIX_ARIA + "describedby");
  public static final IMicroQName ARIA_DISABLED = new MicroQName (PREFIX_ARIA + "disabled");
  public static final IMicroQName ARIA_DROPEFFECT = new MicroQName (PREFIX_ARIA + "dropeffect");
  public static final IMicroQName ARIA_EXPANDED = new MicroQName (PREFIX_ARIA + "expanded");
  public static final IMicroQName ARIA_FLOWTO = new MicroQName (PREFIX_ARIA + "flowto");
  public static final IMicroQName ARIA_GRABBED = new MicroQName (PREFIX_ARIA + "grabbed");
  public static final IMicroQName ARIA_HASPOPUP = new MicroQName (PREFIX_ARIA + "haspopup");
  public static final IMicroQName ARIA_HIDDEN = new MicroQName (PREFIX_ARIA + "hidden");
  public static final IMicroQName ARIA_INVALID = new MicroQName (PREFIX_ARIA + "invalid");
  public static final IMicroQName ARIA_LABEL = new MicroQName (PREFIX_ARIA + "label");
  public static final IMicroQName ARIA_LABELLEDBY = new MicroQName (PREFIX_ARIA + "labelledby");
  public static final IMicroQName ARIA_LEVEL = new MicroQName (PREFIX_ARIA + "level");
  public static final IMicroQName ARIA_LIVE = new MicroQName (PREFIX_ARIA + "live");
  public static final IMicroQName ARIA_MULTILINE = new MicroQName (PREFIX_ARIA + "multiline");
  public static final IMicroQName ARIA_MULTISELECTABLE = new MicroQName (PREFIX_ARIA + "multiselectable");
  public static final IMicroQName ARIA_ORIENTATION = new MicroQName (PREFIX_ARIA + "orientation");
  public static final IMicroQName ARIA_OWNS = new MicroQName (PREFIX_ARIA + "owns");
  public static final IMicroQName ARIA_POSINSET = new MicroQName (PREFIX_ARIA + "posinset");
  public static final IMicroQName ARIA_PRESSED = new MicroQName (PREFIX_ARIA + "pressed");
  public static final IMicroQName ARIA_READONLY = new MicroQName (PREFIX_ARIA + "readonly");
  public static final IMicroQName ARIA_RELEVANT = new MicroQName (PREFIX_ARIA + "relevant");
  public static final IMicroQName ARIA_REQUIRED = new MicroQName (PREFIX_ARIA + "required");
  public static final IMicroQName ARIA_SELECTED = new MicroQName (PREFIX_ARIA + "selected");
  public static final IMicroQName ARIA_SETSIZE = new MicroQName (PREFIX_ARIA + "setsize");
  public static final IMicroQName ARIA_SORT = new MicroQName (PREFIX_ARIA + "sort");
  public static final IMicroQName ARIA_VALUEMAX = new MicroQName (PREFIX_ARIA + "valuemax");
  public static final IMicroQName ARIA_VALUEMIN = new MicroQName (PREFIX_ARIA + "valuemin");
  public static final IMicroQName ARIA_VALUENOW = new MicroQName (PREFIX_ARIA + "valuenow");
  public static final IMicroQName ARIA_VALUETEXT = new MicroQName (PREFIX_ARIA + "valuetext");

  // http://msdn.microsoft.com/en-us/library/ie/dn265018%28v=vs.85%29.aspx
  public static final IMicroQName X_MS_FORMAT_DETECTION = new MicroQName ("x-ms-format-detection");

  private CHTMLAttributes ()
  {}

  public static boolean isDataAttrName (@Nullable final IMicroQName aName)
  {
    return aName != null && isDataAttrName (aName.getName ());
  }

  public static boolean isDataAttrName (@Nullable final String sName)
  {
    return StringHelper.startsWith (sName, HTML5_PREFIX_DATA);
  }

  @Nullable
  public static IMicroQName makeDataAttrName (@Nullable final String sName)
  {
    return StringHelper.hasNoText (sName) ? null : new MicroQName (HTML5_PREFIX_DATA + sName);
  }
}
