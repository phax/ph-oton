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
package com.helger.photon.core.userdata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.fileupload.IFileItem;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * A special upload servlet to be used with FineUploader.
 *
 * @author Philip Helger
 */
public class UserUploadXServletHandler implements IXServletSimpleHandler
{
  public static final String PARAM_DIRECTORY = "dir";
  public static final String PARAM_ID = "id";
  public static final String PARAM_FILE = "file";

  private static final Logger s_aLogger = LoggerFactory.getLogger (UserUploadXServletHandler.class);

  public UserUploadXServletHandler ()
  {}

  @Nonnull
  protected IJsonObject createSuccess ()
  {
    return new JsonObject ().add ("success", true);
  }

  @Nonnull
  protected IJsonObject createError (@Nonnull final String sErrorMsg)
  {
    s_aLogger.error ("User upload error: " + sErrorMsg);
    return new JsonObject ().add ("success", false).add ("error", sErrorMsg).add ("preventRetry", true);
  }

  private void _post (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                      @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    IJsonObject ret;

    final Object aFile = aRequestScope.params ().getValue (PARAM_FILE);
    if (!(aFile instanceof IFileItem))
    {
      ret = createError ("No file passed. Maybe the request is not multipart, put 'multipart/form-data' enctype for your form.");
    }
    else
    {
      final IFileItem aFileItem = (IFileItem) aFile;
      final String sDirectory = aRequestScope.params ().getAsString (PARAM_DIRECTORY);
      final boolean bDirectoryPresent = StringHelper.hasText (sDirectory);
      if (bDirectoryPresent && !FilenameHelper.isValidFilenameWithPaths (sDirectory))
        ret = createError ("The passed directory name '" + sDirectory + "' is invalid!");
      else
      {
        final String sID = aRequestScope.params ().getAsString (PARAM_ID);
        if (StringHelper.hasNoText (sID))
          ret = createError ("No file ID passed!");
        else
        {
          s_aLogger.info ("Uploading " + aFileItem + " as " + sID + " to " + sDirectory);
          // Directory
          String sPath = bDirectoryPresent ? FilenameHelper.ensurePathEndingWithSeparator (sDirectory) : "/";

          // Add basename
          sPath += GlobalIDFactory.getNewPersistentStringID ();

          // Add extension (if present)
          final String sExt = FilenameHelper.getExtension (aFileItem.getNameSecure ());
          if (StringHelper.hasText (sExt))
            sPath += "." + sExt;

          // Lower case complete name - for later handling relevant
          sPath = sPath.toLowerCase (Locale.US);

          final TemporaryUserDataObject aUDO = new TemporaryUserDataObject (sPath);
          try
          {
            if (aFileItem.write (aUDO.getAsFile ()).isFailure ())
            {
              ret = createError ("Failed to store uploaded file " + aFileItem + " to " + aUDO);
            }
            else
            {
              // Add to manager
              UserUploadManager.getInstance ().addUploadedFile (sID, aUDO);
              ret = createSuccess ();
            }
          }
          catch (final Exception ex)
          {
            s_aLogger.error ("Writing " + aFileItem + " to " + aUDO + " failed", ex);
            ret = createError ("Failed to store uploaded file " + aFileItem + " to " + aUDO);
          }
        }
      }
    }
    aUnifiedResponse.setMimeType (CMimeType.APPLICATION_JSON).setContentAndCharset (ret.getAsJsonString (),
                                                                                    StandardCharsets.UTF_8);
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    aUnifiedResponse.disableCaching ();
    _post (aRequestScope, aUnifiedResponse);
  }
}
