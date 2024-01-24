/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
 * All TinyMCE4 top-level menu items.<br>
 * Source: http://www.tinymce.com/wiki.php/Configuration:menu
 *
 * @author Philip Helger
 */
public enum ETinyMCE4MenuItem
{
  FILE ("file"),
  EDIT ("edit"),
  INSERT ("insert"),
  VIEW ("view"),
  FORMAT ("format"),
  TABLE ("table"),
  TOOLS ("tools");

  private final String m_sValue;

  ETinyMCE4MenuItem (@Nonnull @Nonempty final String sValue)
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
  public static ETinyMCE4MenuItem getFromValueOrNull (@Nullable final String sValue)
  {
    return getFromValueOrDefault (sValue, null);
  }

  @Nullable
  public static ETinyMCE4MenuItem getFromValueOrDefault (@Nullable final String sValue, @Nullable final ETinyMCE4MenuItem eDefault)
  {
    if (StringHelper.hasText (sValue))
      for (final ETinyMCE4MenuItem e : values ())
        if (sValue.equals (e.m_sValue))
          return e;
    return eDefault;
  }
}
