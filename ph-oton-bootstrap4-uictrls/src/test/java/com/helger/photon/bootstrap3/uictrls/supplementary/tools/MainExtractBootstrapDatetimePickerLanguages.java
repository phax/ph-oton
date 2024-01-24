/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsTreeSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.string.StringHelper;

public final class MainExtractBootstrapDatetimePickerLanguages
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainExtractBootstrapDatetimePickerLanguages.class);

  public static void main (final String [] args)
  {
    final ICommonsSet <String> aAll = new CommonsTreeSet <> ();
    for (final File aFile : new FileSystemIterator ("src\\main\\resources\\bootstrap\\datetimepicker\\locales"))
      if (aFile.getName ().endsWith (".js") && !aFile.getName ().endsWith (".min.js"))
      {
        final String sLocale = StringHelper.trimStartAndEnd (aFile.getName (), "bootstrap-datetimepicker.", ".js");
        aAll.add (sLocale);
      }
    final StringBuilder aSB = new StringBuilder ();
    for (final String s : aAll)
    {
      final String sLocale = StringHelper.replaceAll (s, "-", "_");
      if (!LocaleCache.getInstance ().containsLocale (sLocale))
      {
        aSB.append ("/* Note: this is not a valid Java locale! */\n");
      }
      aSB.append (sLocale.toUpperCase (Locale.US) + " (\"" + sLocale + "\", \"" + s + "\"),\n");
    }
    LOGGER.info (aSB.toString ());
  }
}
