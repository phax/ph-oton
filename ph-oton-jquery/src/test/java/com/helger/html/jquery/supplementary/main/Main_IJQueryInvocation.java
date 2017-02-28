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
import java.util.Comparator;

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsLinkedHashSet;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.collection.multimap.IMultiMapListBased;
import com.helger.commons.collection.multimap.MultiTreeMapArrayListBased;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;
import com.helger.commons.version.Version;

public class Main_IJQueryInvocation extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args) throws Exception
  {
    // Read all data
    final ICommonsList <Entry> aAllEntries = readAllEntries ();
    final ICommonsList <String> aLines = new CommonsArrayList<> ();

    // Collect all methods with the same name
    final IMultiMapListBased <String, Entry> aUsed = new MultiTreeMapArrayListBased<> ();
    for (final Entry aEntry : aAllEntries)
      if (aEntry.getAPIType () == EAPIType.METHOD)
        aUsed.putSingle (aEntry.getName (), aEntry);

    // non static methods for IJQueryInvocation
    for (final ICommonsList <Entry> aEntries : aUsed.values ())
    {
      boolean bIsDeprecated = true;
      boolean bIsPartiallyDeprecated = false;
      final ICommonsOrderedSet <String> aReturnTypes = new CommonsLinkedHashSet<> ();
      final ICommonsOrderedSet <String> aDeprecatedVersions = new CommonsLinkedHashSet<> ();
      for (final Entry aEntry : aEntries)
      {
        aReturnTypes.add (aEntry.getReturnOrVoid ());
        if (aEntry.isDeprecated ())
        {
          aDeprecatedVersions.add (aEntry.getDeprecated ().getAsString (false));
          bIsPartiallyDeprecated = true;
        }
        else
          bIsDeprecated = false;
      }

      final Entry aEntry = aEntries.getFirst ();

      // Static methods are handled in class jQuery
      if (!aEntry.isStaticMethod ())
      {
        // Remove implicit prefixes for non-static names
        String sPrefix = "";
        String sRealName = aEntry.getName ();
        final int i = sRealName.indexOf ('.');
        if (i > 0)
        {
          sPrefix = sRealName.substring (0, i) + " ";
          sRealName = sRealName.substring (i + 1);
        }

        final Version aSmallestVersion = aEntry.getAllSignatures ()
                                               .getSortedInline (Comparator.comparing (Signature::getAdded))
                                               .getFirst ()
                                               .getAdded ();
        final String sSince = aSmallestVersion.getAsString (false);

        aLines.add ("/**");
        if (!bIsDeprecated && bIsPartiallyDeprecated)
          aLines.add ("* Certain versions of this method are deprecated since jQuery " +
                      StringHelper.getImploded (" or ", aDeprecatedVersions));
        aLines.add (" * @return The invocation of the jQuery " +
                    sPrefix +
                    "function <code>" +
                    sRealName +
                    "()</code> with return type " +
                    StringHelper.getImploded (" or ", aReturnTypes));
        if (bIsDeprecated)
          aLines.add (" * @deprecated Deprecated since jQuery " +
                      StringHelper.getImploded (" or ", aDeprecatedVersions));
        if (sSince != null)
          aLines.add (" * @since jQuery " + sSince);
        aLines.add (" */");
        if (bIsDeprecated)
          aLines.add ("@Deprecated");
        aLines.add ("@Nonnull");
        aLines.add ("THISTYPE " + aEntry.getIdentifier () + " ();");
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
                                                   "import com.helger.html.jscode.JSFieldRef;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_IJQueryInvocation.class.getName () +
                                                   "\n" +
                                                   " * @param <THISTYPE> Implementation type\n" +
                                                   "*/\n" +
                                                   "public interface IJQueryInvocation <THISTYPE extends IJQueryInvocation <THISTYPE>>\n" +
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
                                                   "  THISTYPE arg (@Nullable ICSSClassProvider aArgument);\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * Adds a JQuery selector as a string argument.\n" +
                                                   "   * \n" +
                                                   "   * @param aArgument\n" +
                                                   "   *        value to be added as an argument\n" +
                                                   "   * @return this\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  THISTYPE arg (@Nullable IJQuerySelector aArgument);\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * Adds a JQuery selector list as a string argument.\n" +
                                                   "   * \n" +
                                                   "   * @param aArgument\n" +
                                                   "   *        value to be added as an argument\n" +
                                                   "   * @return this\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  THISTYPE arg (@Nullable JQuerySelectorList aArgument);\n" +
                                                   "\n" +
                                                   "  // Properties of jQuery Object Instances\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * @return The invocation of the jQuery function <code>context()</code>\n" +
                                                   "   * @since jQuery 1.3\n" +
                                                   "   * @deprecated Deprecated since jQuery 1.10\n" +
                                                   "   */\n" +
                                                   "  @Deprecated\n" +
                                                   "  @Nonnull\n" +
                                                   "  JSFieldRef context ();\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * @return The invocation of the jQuery field <code>jquery</code>\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  JSFieldRef jquery ();\n" +
                                                   "\n" +
                                                   "  /**\n" +
                                                   "   * @return The invocation of the jQuery field <code>length()</code>\n" +
                                                   "   */\n" +
                                                   "  @Nonnull\n" +
                                                   "  JSFieldRef length ();\n");

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
