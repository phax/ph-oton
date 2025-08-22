/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.tools.supplementary;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.string.StringHelper;
import com.helger.base.string.StringReplace;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.helper.CollectionSort;
import com.helger.http.url.ISimpleURL;
import com.helger.http.url.SimpleURL;
import com.helger.io.file.SimpleFileIO;

public final class MainExtractPrismLanguage
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainExtractPrismLanguage.class);

  public static void main (final String [] args)
  {
    final StringBuilder aSB = new StringBuilder ();
    final File f = new File ("src/main/resources/prismjs/prism.css");
    String sLine = SimpleFileIO.getAllFileLines (f, StandardCharsets.UTF_8).get (0);
    sLine = StringHelper.trimStartAndEnd (sLine, "/*", "*/").trim ();
    final ISimpleURL aURL = new SimpleURL (sLine);

    // Languages
    {
      final String sLanguages = aURL.params ().getFirstParamValue ("languages");
      final List <String> aLanguages = CollectionSort.getSortedInline (StringHelper.getExploded ('+', sLanguages));
      // Special "none" language
      aLanguages.add (0, "none");
      for (final String sLanguage : aLanguages)
        aSB.append (sLanguage.toUpperCase (Locale.US)).append (" (\"language-").append (sLanguage).append ("\"),\n");
    }

    aSB.append ('\n');

    // Plugins
    {
      final String sPlugins = aURL.params ().getFirstParamValue ("plugins");
      final ICommonsList <String> aPlugins = CollectionSort.getSorted (StringHelper.getExploded ('+', sPlugins));
      for (final String sPlugin : aPlugins)
        aSB.append (StringReplace.replaceAll (sPlugin, '-', '_').toUpperCase (Locale.US))
           .append (" (\"")
           .append (sPlugin)
           .append ("\"),\n");
    }
    LOGGER.info (aSB.toString ());
  }
}
