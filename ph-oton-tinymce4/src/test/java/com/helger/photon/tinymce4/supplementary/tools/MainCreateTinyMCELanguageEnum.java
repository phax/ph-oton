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
package com.helger.photon.tinymce4.supplementary.tools;

import java.io.File;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.IFileFilter;
import com.helger.commons.locale.LocaleCache;

public final class MainCreateTinyMCELanguageEnum
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateTinyMCELanguageEnum.class);

  public static void main (final String [] args)
  {
    final StringBuilder aSB = new StringBuilder ();
    // Last update: 2013-11-22
    for (final File aFile : new FileSystemIterator ("src/main/resources/tinymce-dev/langs").withFilter (IFileFilter.filenameEndsWith (".js")))
    {
      final String sID = FilenameHelper.getBaseName (aFile);
      if (!LocaleCache.getInstance ().containsLocale (sID))
      {
        aSB.append ("/* Note: this is not a valid Java locale! */\n");
      }
      aSB.append (sID.toUpperCase (Locale.US)).append (" (\"").append (sID).append ("\"),\n");
    }
    LOGGER.info (aSB.toString ());
  }
}
