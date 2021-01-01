/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.tinymce4.type;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;

/**
 * All TinyMCE4 toolbar controls.<br>
 * Partly generated by MainCreateTinyMCEToolbarControls
 *
 * @author Philip Helger
 */
public enum ETinyMCE4ToolbarControl
{
  NEWDOCUMENT ("newdocument", null),
  BOLD ("bold", null),
  ITALIC ("italic", null),
  UNDERLINE ("underline", null),
  STRIKETHROUGH ("strikethrough", null),
  ALIGNLEFT ("alignleft", null),
  ALIGNCENTER ("aligncenter", null),
  ALIGNRIGHT ("alignright", null),
  ALIGNJUSTIFY ("alignjustify", null),
  STYLESELECT ("styleselect", null),
  FORMATSELECT ("formatselect", null),
  FONTSELECT ("fontselect", null),
  FONTSIZESELECT ("fontsizeselect", null),
  CUT ("cut", null),
  COPY ("copy", null),
  PASTE ("paste", null),
  BULLIST ("bullist", null),
  NUMLIST ("numlist", null),
  OUTDENT ("outdent", null),
  INDENT ("indent", null),
  BLOCKQUOTE ("blockquote", null),
  UNDO ("undo", null),
  REDO ("redo", null),
  REMOVEFORMAT ("removeformat", null),
  SUBSCRIPT ("subscript", null),
  SUPERSCRIPT ("superscript", null),
  HR ("hr", ETinyMCE4Plugin.HR),
  LINK ("link", ETinyMCE4Plugin.LINK),
  UNLINK ("unlink", ETinyMCE4Plugin.LINK),
  IMAGE ("image", ETinyMCE4Plugin.IMAGE),
  CHARMAP ("charmap", ETinyMCE4Plugin.CHARMAP),
  PASTETEXT ("pastetext", ETinyMCE4Plugin.PASTE),
  PRINT ("print", ETinyMCE4Plugin.PRINT),
  PREVIEW ("preview", ETinyMCE4Plugin.PREVIEW),
  ANCHOR ("anchor", ETinyMCE4Plugin.ANCHOR),
  PAGEBREAK ("pagebreak", ETinyMCE4Plugin.PAGEBREAK),
  SPELLCHECKER ("spellchecker", ETinyMCE4Plugin.SPELLCHECKER),
  SEARCHREPLACE ("searchreplace", ETinyMCE4Plugin.SEARCHREPLACE),
  VISUALBLOCKS ("visualblocks", ETinyMCE4Plugin.VISUALBLOCKS),
  VISUALCHARS ("visualchars", ETinyMCE4Plugin.VISUALCHARS),
  CODE ("code", ETinyMCE4Plugin.CODE),
  FULLSCREEN ("fullscreen", ETinyMCE4Plugin.FULLSCREEN),
  INSERTTIME ("inserttime", ETinyMCE4Plugin.INSERTDATETIME),
  MEDIA ("media", ETinyMCE4Plugin.MEDIA),
  NONBREAKING ("nonbreaking", ETinyMCE4Plugin.NONBREAKING),
  SAVE ("save", ETinyMCE4Plugin.SAVE),
  CANCEL ("cancel", ETinyMCE4Plugin.SAVE),
  TABLE ("table", ETinyMCE4Plugin.TABLE),
  LTR ("ltr", ETinyMCE4Plugin.DIRECTIONALITY),
  RTL ("rtl", ETinyMCE4Plugin.DIRECTIONALITY),
  EMOTICONS ("emoticons", ETinyMCE4Plugin.EMOTICONS),
  TEMPLATE ("template", ETinyMCE4Plugin.TEMPLATE),
  FORECOLOR ("forecolor", ETinyMCE4Plugin.TEXTCOLOR),
  BACKCOLOR ("backcolor", ETinyMCE4Plugin.TEXTCOLOR);

  private final String m_sValue;
  private final ETinyMCE4Plugin m_eRequiredPlugin;

  ETinyMCE4ToolbarControl (@Nonnull @Nonempty final String sValue, @Nullable final ETinyMCE4Plugin eRequiredPlugin)
  {
    m_sValue = sValue;
    m_eRequiredPlugin = eRequiredPlugin;
  }

  @Nonnull
  @Nonempty
  public String getValue ()
  {
    return m_sValue;
  }

  public boolean isSpecialPluginRequired ()
  {
    return m_eRequiredPlugin != null;
  }

  @Nullable
  public ETinyMCE4Plugin getRequiredPlugin ()
  {
    return m_eRequiredPlugin;
  }

  @Nullable
  public static ETinyMCE4ToolbarControl getFromValueOrNull (@Nullable final String sValue)
  {
    return getFromValueOrDefault (sValue, null);
  }

  @Nullable
  public static ETinyMCE4ToolbarControl getFromValueOrDefault (@Nullable final String sValue,
                                                               @Nullable final ETinyMCE4ToolbarControl eDefault)
  {
    if (StringHelper.hasText (sValue))
      for (final ETinyMCE4ToolbarControl e : values ())
        if (sValue.equals (e.m_sValue))
          return e;
    return eDefault;
  }
}
