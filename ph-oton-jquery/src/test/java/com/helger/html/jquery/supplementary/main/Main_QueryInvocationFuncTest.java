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
package com.helger.html.jquery.supplementary.main;

import java.io.File;
import java.nio.charset.StandardCharsets;

import com.helger.collection.multimap.IMultiMapListBased;
import com.helger.collection.multimap.MultiTreeMapArrayListBased;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.file.SimpleFileIO;

public class Main_QueryInvocationFuncTest extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args) throws Exception
  {
    // Read all data
    final ICommonsList <Entry> aAllEntries = readAllEntries ();
    final ICommonsList <String> aLines = new CommonsArrayList <> ();

    // Collect all methods with the same name
    final IMultiMapListBased <String, Entry> aUsed = new MultiTreeMapArrayListBased <> ();
    for (final Entry aEntry : aAllEntries)
      if (aEntry.getAPIType () == EAPIType.METHOD)
        aUsed.putSingle (aEntry.getName (), aEntry);

    // non static methods for AbstractJQueryInvocation
    for (final ICommonsList <Entry> aEntries : aUsed.values ())
    {
      boolean bIsDeprecated = true;
      for (final Entry aEntry : aEntries)
        if (!aEntry.isDeprecated ())
        {
          bIsDeprecated = false;
          break;
        }

      final Entry aEntry = aEntries.getFirst ();

      // Static methods are handled in class jQuery
      if (!aEntry.isStaticMethod ())
      {
        // Remove implicit prefixes for non-static names
        String sRealName = aEntry.getName ();
        final int i = sRealName.indexOf ('.');
        if (i > 0)
          sRealName = sRealName.substring (i + 1);

        if (!bIsDeprecated)
        {
          aLines.add ("@Test");
          aLines.add ("public void test" + aEntry.getIdentifier () + " ()");
          aLines.add ("{");
          aLines.add ("  assertNotNull (JQuery.idRef (\"any\")." + aEntry.getIdentifier () + " ());");
          aLines.add ("}");
          aLines.add ("");
        }
      }
    }

    final StringBuilder aFull = new StringBuilder ("package com.helger.html.jquery;\n" +
                                                   "\n" +
                                                   "import static org.junit.Assert.assertNotNull;\n" +
                                                   "\n" +
                                                   "import org.junit.Test;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * Unit test class for interface @{link IJQueryInvocation}\n" +
                                                   " * \n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_QueryInvocationFuncTest.class.getName () +
                                                   "\n" +
                                                   " */\n" +
                                                   "public final class JQueryInvocationFuncTest\n" +
                                                   "{\n" +
                                                   "  // Methods start here\n" +
                                                   "\n");

    for (final String sLine : aLines)
      if (sLine.length () > 0)
        aFull.append ("  ").append (sLine).append ('\n');
      else
        aFull.append ('\n');
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/test/java/com/helger/html/jquery/JQueryInvocationFuncTest.java"),
                            aFull.toString (),
                            StandardCharsets.UTF_8);
    s_aLogger.info ("Done");
  }
}
