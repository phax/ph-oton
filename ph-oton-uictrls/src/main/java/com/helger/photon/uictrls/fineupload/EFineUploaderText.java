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
package com.helger.photon.uictrls.fineupload;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

@Translatable
public enum EFineUploaderText implements IHasDisplayText
{
  // messages
  TOO_MANY_FILE_ERROR ("Sie können immer nur eine Datei hierher ziehen.", "You may only drop one file."),
  // text
  UPLOAD_BUTTON ("Datei auswählen", "Upload a file"),
  CANCEL_BUTTON ("Löschen", "Delete"),
  RETRY_BUTTON ("Wiederholen", "Retry"),
  FAIL_UPLOAD ("Hochladen fehlgeschlagen", "Upload failed"),
  DRAG_ZONE ("Dateien hier loslassen, um sie hochzuladen", "Drop files here to upload"),
  DROP_PROCESSING ("Verarbeite Dateien...", "Processing dropped files..."),
  FORMAT_PROGRESS ("{percent}% von {total_size}", "{percent}% of {total_size}"),
  WAITING_FOR_RESPONSE ("Verarbeitung...", "Processing...");

  private final IMultilingualText m_aTP;

  private EFineUploaderText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
