package com.helger.photon.uictrls.tools.supplementary;

import java.io.File;
import java.util.List;
import java.util.Locale;

import com.helger.commons.charset.CCharset;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;

public class MainExtractPrismLanguage
{
  public static void main (final String [] args)
  {
    final File f = new File ("src/main/resources/prismjs/prism.css");
    String sLine = SimpleFileIO.readFileLines (f, CCharset.CHARSET_UTF_8_OBJ).get (0);
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
