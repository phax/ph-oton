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

public class Main_IJQueryInvocation extends AbstractCreateJQueryAPIList
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
        aLines.add ("default THISTYPE " + aEntry.getIdentifier () + " ()");
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
                                                   "import com.helger.html.jscode.IJSInvocation;\n" +
                                                   "import com.helger.html.jscode.JSFieldRef;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_IJQueryInvocation.class.getName () +
                                                   "\n" +
                                                   " * @param <THISTYPE> Implementation type\n" +
                                                   "*/\n" +
                                                   "public interface IJQueryInvocation <THISTYPE extends IJQueryInvocation <THISTYPE>> extends IJSInvocation <THISTYPE>\n" +
                                                   "{\n" +
                                                   "  /**\n" +
                                                   "   * Invoke an arbitrary function on this jQuery object.\n" +
                                                   "   * \n" +
                                                   "   * @param sMethod\n" +
                                                   "   *        The method to be invoked. May neither be <code>null</code> nor\n" +
                                                   "   *        empty.\n" +
                                                   "   * @return A new jQuery invocation object. Never <code>null</code>.\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  THISTYPE jqinvoke (@Nonnull @Nonempty String sMethod);\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * Adds a CSSClassProvider selector as a string argument.\n" +
                                                   "   * \n" +
                                                   "   * @param aArgument\n" +
                                                   "   *        value to be added as an argument\n" +
                                                   "   * @return this\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  default THISTYPE arg (@Nullable ICSSClassProvider aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getCSSClass ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * Adds a JQuery selector as a string argument.\n" +
                                                   "   * \n" +
                                                   "   * @param aArgument\n" +
                                                   "   *        value to be added as an argument\n" +
                                                   "   * @return this\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  default THISTYPE arg (@Nullable IJQuerySelector aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getExpression ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * Adds a JQuery selector list as a string argument.\n" +
                                                   "   * \n" +
                                                   "   * @param aArgument\n" +
                                                   "   *        value to be added as an argument\n" +
                                                   "   * @return this\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  default THISTYPE arg (@Nullable JQuerySelectorList aArgument)\n" +
                                                   "  {\n" +
                                                   "    return aArgument == null ? argNull () : arg (aArgument.getAsExpression ());\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  // Properties of jQuery Object Instances\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * @return The invocation of the jQuery field <code>jquery</code>\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  default JSFieldRef jquery ()\n" +
                                                   "  {\n" +
                                                   "    return ref (\"jquery\");\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * @return The invocation of the jQuery field <code>length()</code>\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  default JSFieldRef length ()\n" +
                                                   "  {\n" +
                                                   "    return ref (\"length\");\n" +
                                                   "  }\n");

    for (final String sLine : aLines)
      if (sLine.length () > 0)
        aFull.append ("  ").append (sLine).append ('\n');
      else
        aFull.append ('\n');
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/main/java/com/helger/html/jquery/IJQueryInvocation.java"),
                            aFull.toString (),
                            StandardCharsets.UTF_8);
    s_aLogger.info ("Done");
  }
}
