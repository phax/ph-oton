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

import com.helger.html.jscode.JSAssocArray;

public class FineUploader extends FineUploaderBasic
{
  public FineUploader (@Nonnull final Locale aDisplayLocale)
  {
    super (aDisplayLocale);
  }

  @Override
  protected void extendJSONMessages (@Nonnull final JSAssocArray aMessages, @Nonnull final Locale aDisplayLocale)
  {
    aMessages.add ("tooManyFilesError", EFineUploaderText.TOO_MANY_FILE_ERROR.getDisplayText (aDisplayLocale));
  }

  @Override
  protected void extendJSON (@Nonnull final JSAssocArray aRoot, @Nullable final Locale aDisplayLocale)
  {
    if (aDisplayLocale != null)
    {
      final JSAssocArray aText = new JSAssocArray ();
      aText.add ("uploadButton", EFineUploaderText.UPLOAD_BUTTON.getDisplayText (aDisplayLocale));
      aText.add ("cancelButton", EFineUploaderText.CANCEL_BUTTON.getDisplayText (aDisplayLocale));
      aText.add ("retryButton", EFineUploaderText.RETRY_BUTTON.getDisplayText (aDisplayLocale));
      aText.add ("failUpload", EFineUploaderText.FAIL_UPLOAD.getDisplayText (aDisplayLocale));
      aText.add ("dragZone", EFineUploaderText.DRAG_ZONE.getDisplayText (aDisplayLocale));
      aText.add ("dropProcessing", EFineUploaderText.DROP_PROCESSING.getDisplayText (aDisplayLocale));
      aText.add ("formatProgress", EFineUploaderText.FORMAT_PROGRESS.getDisplayText (aDisplayLocale));
      aText.add ("waitingForResponse", EFineUploaderText.WAITING_FOR_RESPONSE.getDisplayText (aDisplayLocale));
      aRoot.add ("text", aText);
    }
  }
}
