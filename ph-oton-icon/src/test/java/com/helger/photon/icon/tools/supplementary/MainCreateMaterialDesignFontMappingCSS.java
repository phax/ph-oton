package com.helger.photon.icon.tools.supplementary;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.photon.icon.EIconCSSPathProvider;

public class MainCreateMaterialDesignFontMappingCSS
{
  public static void main (final String [] args)
  {
    final String sCodepointsPath = FilenameHelper.getPath (EIconCSSPathProvider.MATERIAL_ICONS.getCSSItemPath (true)) +
                                   "codepoints";
    final File aFile = new ClassPathResource (sCodepointsPath).getAsFile ();
    final StringBuilder aSBCSS = new StringBuilder ();
    final StringBuilder aSBEnum = new StringBuilder ();
    SimpleFileIO.readFileLines (aFile, StandardCharsets.ISO_8859_1, x -> {
      final String [] aParts = x.trim ().split ("\\s+", 2);
      if (aParts.length == 2)
      {
        final String sCSSName = "md-" + aParts[0];
        String sEnumName = aParts[0].toUpperCase (Locale.US);
        if (Character.isDigit (sEnumName.charAt (0)))
          sEnumName = '_' + sEnumName;

        aSBCSS.append ("." + sCSSName + ":before { content: \"\\" + aParts[1] + "\"; }\n");
        aSBEnum.append (sEnumName + " (\"" + sCSSName + "\"),\n");
      }
      else
        System.err.println ("Error in " + x);
    });
    System.out.println (aSBCSS.toString ());
    System.out.println ();
    System.out.println (aSBEnum.toString ());
  }
}
