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
package com.helger.photon.uictrls.fineupload5;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

@Translatable
public enum EFineUploader5CoreText implements IHasDisplayText
{
  // message
  EMPTY_ERROR ("{file} ist leer. Wählen Sie Dateien ohne diese Datei aus.",
               "{file} is empty, please select files again without it."),
  MAX_HEIGHT_IMAGE_ERROR ("Das Bild ist zu hoch.", "Image is too tall."),
  MAX_WIDTH_IMAGE_ERROR ("Das Bild ist zu breit.", "Image is too wide."),
  MIN_HEIGHT_IMAGE_ERROR ("Das Bild ist nicht hoch genug.", "Image is not tall enough."),
  MIN_WIDTH_IMAGE_ERROR ("Das Bild ist zu schmal.", "Image is not wide enough."),
  MIN_SIZE_ERROR ("{file} ist zu klein. Die minimale Größe beträgt {minSizeLimit}.",
                  "{file} is too small, minimum file size is {minSizeLimit}."),
  NO_FILES_ERROR ("Es wurde keine Datei ausgewählt.", "No files to upload."),
  ON_LEAVE ("Derzeit werden Dateien hochgeladen. Wenn Sie diese seiten nun verlassen, wird das Hochladen abgebrochen!",
            "The files are being uploaded, if you leave now the upload will be cancelled."),
  RETRY_FAIL_TOO_MANY_ITEMS_ERROR ("Wiederholung ist fehlgeschlagen - das Limit an Uploads wurde erreicht.",
                                   "Retry failed - you have reached your file limit."),
  SIZE_ERROR ("{file} ist zu groß. Die maximale Größe beträgt {sizeLimit}.",
              "{file} is too large, maximum file size is {sizeLimit}."),
  TOO_MANY_ITEMS_ERROR ("Es würden zuviele Dateien ({netItems}) hochgeladen werden. Das Limit beträgt {itemLimit} Dateien.",
                        "Too many items ({netItems}) would be uploaded. Item limit is {itemLimit}."),
  TYPE_ERROR ("{file} hat eine ungültige Dateierweiterung. Gültige Erweiterungen sind: {extensions}.",
              "{file} has an invalid extension. Valid extension(s): {extensions}."),
  UNSUPPORTED_BROWSER_IOS8_SAFARI ("Fehler - dieser Browser unterstützt auf Grund von internen Fehlern keinen Datei-Upload. Stattdessen kann iOS8 Chrome verwendet werden.",
                                   "Unrecoverable error - this browser does not permit file uploading of any kind due to serious bugs in iOS8 Safari. Please use iOS8 Chrome until Apple fixes these issues."),
  // text
  DEFAULT_RESPONSE_ERROR ("Beim Hochladen ist ein unspezifizierter Fehler aufgetreten",
                          "Upload failure - reason unknown"),
  FILE_INPUT_TITLE ("Dateien hochladen", "file input");

  private final IMultilingualText m_aTP;

  private EFineUploader5CoreText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
