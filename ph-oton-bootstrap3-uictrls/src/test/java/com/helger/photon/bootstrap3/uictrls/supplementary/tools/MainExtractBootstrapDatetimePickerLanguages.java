/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls.supplementary.tools;

import java.io.File;
import java.util.Locale;

import com.helger.commons.collection.ext.CommonsTreeSet;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;

public final class MainExtractBootstrapDatetimePickerLanguages
{
  public static void main (final String [] args)
  {
    final ICommonsSet <String> aAll = new CommonsTreeSet <> ();
    for (final File aFile : new FileSystemIterator ("src\\main\\resources\\bootstrap\\datetimepicker\\locales"))
      if (aFile.getName ().endsWith (".js") && !aFile.getName ().endsWith (".min.js"))
      {
        final String sLocale = StringHelper.trimStartAndEnd (aFile.getName (), "bootstrap-datetimepicker.", ".js");
        aAll.add (sLocale);
      }
    for (final String s : aAll)
    {
      final String sLocale = StringHelper.replaceAll (s, "-", "_");
      if (!LocaleCache.getInstance ().containsLocale (sLocale))
      {
        System.out.println ("/* Note: this is not a valid Java locale! */");
      }
      System.out.println (sLocale.toUpperCase (Locale.US) + " (\"" + sLocale + "\", \"" + s + "\"),");
    }
  }
}
