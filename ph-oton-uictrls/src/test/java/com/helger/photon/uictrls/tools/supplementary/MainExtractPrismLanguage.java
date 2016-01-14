/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import java.util.List;
import java.util.Locale;

import com.helger.commons.charset.CCharset;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;

public class MainExtractPrismLanguage
{
  public static void main (final String [] args)
  {
    final File f = new File ("src/main/resources/prismjs/prism.css");
    String sLine = SimpleFileIO.getAllFileLines (f, CCharset.CHARSET_UTF_8_OBJ).get (0);
    sLine = StringHelper.trimStartAndEnd (sLine, "/*", "*/").trim ();
    final ISimpleURL aURL = new SimpleURL (sLine);
    if (false)
      System.out.println (aURL.getAllParams ());

    // Languages
    {
      final String sLanguages = aURL.getParam ("languages");
      final List <String> aLanguages = CollectionHelper.getSorted (StringHelper.getExploded ('+', sLanguages));
      // Special "none" language
      aLanguages.add (0, "none");
      for (final String sLanguage : aLanguages)
        System.out.println (sLanguage.toUpperCase (Locale.US) + " (\"language-" + sLanguage + "\"),");
    }

    System.out.println ();

    // Plugins
    {
      final String sPlugins = aURL.getParam ("plugins");
      final List <String> aPlugins = CollectionHelper.getSorted (StringHelper.getExploded ('+', sPlugins));
      for (final String sPlugin : aPlugins)
        System.out.println (sPlugin.replace ('-', '_').toUpperCase (Locale.US) + " (\"" + sPlugin + "\"),");
    }
  }
}
