/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.filter.FileFilterDirectoryPublic;
import com.helger.commons.io.file.iterate.FileSystemIterator;

public class MainCreateTinyMCEThemeEnum
{
  public static void main (final String [] args)
  {
    // Last update: 2013-11-22
    for (final File aFile : FileSystemIterator.create ("src/main/resources/tinymce-dev/themes",
                                                       FileFilterDirectoryPublic.getInstance ()))
    {
      final String sID = FilenameHelper.getBaseName (aFile);
      System.out.println (sID.toUpperCase (Locale.US) + " (\"" + sID + "\"),");
    }
  }
}
