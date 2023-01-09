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
package com.helger.photon.uictrls.colorbox;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

@Translatable
public enum EColorBoxText implements IHasDisplayText
{
  CURRENT ("Bild {current} von {total}", "image {current} of {total}"),
  PREVIOUS ("Vorheriges", "previous"),
  NEXT ("Nächstes", "next"),
  CLOSE ("Schließen", "close"),
  XHR_ERROR ("Fehler beim Laden des Inhalts", "This content failed to load."),
  IMG_ERROR ("Fehler beim Laden des Bildes", "This image failed to load."),
  SLIDESHOW_START ("Slideshow starten", "start slideshow"),
  SLIDESHOW_STOP ("Slideshow anhalten", "stop slideshow");

  private final IMultilingualText m_aTP;

  EColorBoxText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
