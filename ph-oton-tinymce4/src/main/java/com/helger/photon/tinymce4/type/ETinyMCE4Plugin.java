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
package com.helger.photon.tinymce4.type;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;

/**
 * All TinyMCE4 supported plugins.<br>
 * Partly generated by MainCreateTinyMCEPluginEnum
 *
 * @author Philip Helger
 */
public enum ETinyMCE4Plugin
{
  ADVLIST ("advlist"),
  ANCHOR ("anchor"),
  AUTOLINK ("autolink"),
  AUTORESIZE ("autoresize"),
  AUTOSAVE ("autosave"),
  BBCODE ("bbcode"),
  CHARMAP ("charmap"),
  CODE ("code"),
  CODESAMPLE ("codesample"),
  COLORPICKER ("colorpicker"),
  COMPAT3X ("compat3x"),
  CONTEXTMENU ("contextmenu"),
  DIRECTIONALITY ("directionality"),
  EMOTICONS ("emoticons"),
  FULLPAGE ("fullpage"),
  FULLSCREEN ("fullscreen"),
  HELP ("help"),
  HR ("hr"),
  IMAGE ("image"),
  IMAGETOOLS ("imagetools"),
  IMPORTCSS ("importcss"),
  INSERTDATETIME ("insertdatetime"),
  LEGACYOUTPUT ("legacyoutput"),
  LINK ("link"),
  LISTS ("lists"),
  MEDIA ("media"),
  NONBREAKING ("nonbreaking"),
  NONEDITABLE ("noneditable"),
  PAGEBREAK ("pagebreak"),
  PASTE ("paste"),
  PREVIEW ("preview"),
  PRINT ("print"),
  SAVE ("save"),
  SEARCHREPLACE ("searchreplace"),
  SPELLCHECKER ("spellchecker"),
  TABFOCUS ("tabfocus"),
  TABLE ("table"),
  TEMPLATE ("template"),
  TEXTCOLOR ("textcolor"),
  TEXTPATTERN ("textpattern"),
  TOC ("toc"),
  VISUALBLOCKS ("visualblocks"),
  VISUALCHARS ("visualchars"),
  WORDCOUNT ("wordcount");

  private final String m_sValue;

  ETinyMCE4Plugin (@Nonnull @Nonempty final String sValue)
  {
    m_sValue = sValue;
  }

  @Nonnull
  @Nonempty
  public String getValue ()
  {
    return m_sValue;
  }

  @Nullable
  public static ETinyMCE4Plugin getFromValueOrNull (@Nullable final String sValue)
  {
    return getFromValueOrDefault (sValue, null);
  }

  @Nullable
  public static ETinyMCE4Plugin getFromValueOrDefault (@Nullable final String sValue, @Nullable final ETinyMCE4Plugin eDefault)
  {
    if (StringHelper.hasText (sValue))
      for (final ETinyMCE4Plugin e : values ())
        if (sValue.equals (e.m_sValue))
          return e;
    return eDefault;
  }
}
