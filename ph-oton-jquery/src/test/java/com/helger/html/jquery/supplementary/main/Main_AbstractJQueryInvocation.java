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

import com.helger.commons.charset.CCharset;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.multimap.IMultiMapListBased;
import com.helger.commons.collection.multimap.MultiTreeMapArrayListBased;
import com.helger.commons.io.file.SimpleFileIO;

public class Main_AbstractJQueryInvocation extends AbstractCreateJQueryAPIList
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

      final Entry aEntry = aEntries.get (0);

      // Static methods are handled in class jQuery
      if (!aEntry.isStaticMethod ())
      {
        // Remove implicit prefixes for non-static names
        String sRealName = aEntry.getName ();
        final int i = sRealName.indexOf ('.');
        if (i > 0)
          sRealName = sRealName.substring (i + 1);

        if (bIsDeprecated)
          aLines.add ("@Deprecated");
        aLines.add ("@Nonnull");
        aLines.add ("public final THISTYPE " + aEntry.getIdentifier () + " ()");
        aLines.add ("{");
        aLines.add ("  return jqinvoke (\"" + sRealName + "\");");
        aLines.add ("}");
        aLines.add ("");
      }
    }

    final StringBuilder aFull = new StringBuilder ("package com.helger.html.jquery;\n" +
                                                   "\n" +
                                                   "import javax.annotation.Nonnull;\n" +
                                                   "import javax.annotation.Nullable;\n" +
                                                   "\n" +
                                                   "import com.helger.commons.annotation.Nonempty;\n" +
                                                   "import com.helger.html.css.ICSSClassProvider;\n" +
                                                   "import com.helger.html.jscode.AbstractJSInvocation;\n" +
                                                   "import com.helger.html.jscode.IJSExpression;\n" +
                                                   "import com.helger.html.jscode.JSFieldRef;\n" +
                                                   "import com.helger.html.jscode.JSFunction;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * Special invocation semantics for jQuery\n" +
                                                   " * \n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_AbstractJQueryInvocation.class.getName () +
                                                   "\n" +
                                                   " * @param <THISTYPE> Implementation type\n" +
                                                   " */\n" +
                                                   "public abstract class AbstractJQueryInvocation <THISTYPE extends AbstractJQueryInvocation <THISTYPE>> extends AbstractJSInvocation <THISTYPE> implements IJQueryInvocation <THISTYPE>\n" +
                                                   "{\n" +
                                                   "  public AbstractJQueryInvocation (@Nonnull final JSFunction aFunction)\n" +
                                                   "  {\n" +
                                                   "    super (aFunction);\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  public AbstractJQueryInvocation (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)\n" +
                                                   "  {\n" +
                                                   "    super (aLhs, sMethod);\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  @Nonnull\n" +
                                                   "  public THISTYPE arg (@Nullable final ICSSClassProvider aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getCSSClass ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  @Nonnull\n" +
                                                   "  public THISTYPE arg (@Nullable final IJQuerySelector aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getExpression ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  @Nonnull\n" +
                                                   "  public THISTYPE arg (@Nullable final JQuerySelectorList aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getAsExpression ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  // Properties of jQuery Object Instances\n" +
                                                   "\n" +
                                                   "  @Deprecated\n" +
                                                   "  @Nonnull\n" +
                                                   "  public JSFieldRef context ()\n" +
                                                   "  {\n" +
                                                   "    return ref (\"context\");\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  @Nonnull\n" +
                                                   "  public JSFieldRef jquery ()\n" +
                                                   "  {\n" +
                                                   "    return ref (\"jquery\");\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  @Nonnull\n" +
                                                   "  public JSFieldRef length ()\n" +
                                                   "  {\n" +
                                                   "    return ref (\"length\");\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  // Methods start here\n" +
                                                   "\n");

    for (final String sLine : aLines)
      if (sLine.length () > 0)
        aFull.append ("  ").append (sLine).append ('\n');
      else
        aFull.append ('\n');
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/main/java/com/helger/html/jquery/AbstractJQueryInvocation.java"),
                            aFull.toString (),
                            CCharset.CHARSET_UTF_8_OBJ);
    System.out.println ("Done");
  }
}
