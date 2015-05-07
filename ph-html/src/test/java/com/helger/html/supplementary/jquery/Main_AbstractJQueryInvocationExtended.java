/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.supplementary.jquery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.helger.commons.charset.CCharset;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;

public class Main_AbstractJQueryInvocationExtended extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args) throws Exception
  {
    // Read all data
    final List <Entry> aAllEntries = readAllEntries ();
    final List <String> aLines = new ArrayList <String> ();

    // IJQueryInvocationExtended
    final Set <String> aUsedJavaSignatures = new HashSet <String> ();
    for (final Entry aEntry : aAllEntries)
      if (aEntry.getAPIType () == EAPIType.METHOD)
      {
        if (aEntry.isStaticMethod ())
          continue;

        final String sUsedSignaturePrefix = aEntry.getName () + ":";
        for (final Signature aSignature : aEntry.getAllSignatures ())
        {
          final int nArgCount = aSignature.getArgumentCount ();
          if (nArgCount == 0)
          {
            // No need to implement it as it is already in the base class!
            continue;
          }

          String sRealPrefix = "@Nonnull\npublic IMPLTYPE " + aEntry.getIdentifier ();
          if (aEntry.isDeprecated ())
            sRealPrefix = "@Deprecated\n" + sRealPrefix;

          if (nArgCount == 1)
          {
            // Only one argument
            final Argument aArg = aSignature.getArgumentAtIndex (0);
            for (final String sJavaType : aArg.getAllJavaTypes ())
              if (aUsedJavaSignatures.add (sUsedSignaturePrefix + sJavaType))
              {
                aLines.add (sRealPrefix +
                            "(" +
                            _getAnnotation (sJavaType) +
                            sJavaType +
                            " " +
                            aArg.getIdentifier () +
                            ") { return " +
                            aEntry.getIdentifier () +
                            " ().arg (" +
                            aArg.getIdentifier () +
                            "); }");
              }
          }
          else
          {
            // More than one argument
            final int nMultiJavaTypeArgs = aSignature.getArgumentsWithMultipleJavaTypesCount ();
            if (nMultiJavaTypeArgs == 0)
            {
              String sParams = "";
              final List <String> aJavaTypeKey = new ArrayList <String> ();
              for (final Argument aArg : aSignature.getAllArguments ())
              {
                if (sParams.length () > 0)
                  sParams += ", ";

                final String sJavaType = aArg.getFirstJavaType ();
                sParams += _getAnnotation (sJavaType) + sJavaType + " " + aArg.getIdentifier ();
                aJavaTypeKey.add (sJavaType);
              }
              if (aUsedJavaSignatures.add (sUsedSignaturePrefix + StringHelper.getImploded (',', aJavaTypeKey)))
              {
                String sLine = sRealPrefix + "(" + sParams + ") { return " + aEntry.getIdentifier () + " ()";
                for (final Argument aArg : aSignature.getAllArguments ())
                  sLine += ".arg (" + aArg.getIdentifier () + ")";
                aLines.add (sLine + "; }");
              }
            }
            else
            {
              // At least one multi java-type argument
              final Argument [] aMultiJavaTypeArgs = new Argument [nArgCount];

              // Build template
              String sTemplate = "";
              final List <String> aJavaTypeKey = new ArrayList <String> ();
              int nArgIndex = 0;
              for (final Argument aArg : aSignature.getAllArguments ())
              {
                if (sTemplate.length () > 0)
                  sTemplate += ", ";

                if (aArg.getJavaTypeCount () > 1)
                {
                  final String sJavaType = "{" + nArgIndex + "}";
                  aMultiJavaTypeArgs[nArgIndex] = aArg;
                  sTemplate += sJavaType + " " + aArg.getIdentifier ();
                  aJavaTypeKey.add (sJavaType);
                }
                else
                {
                  final String sJavaType = aArg.getFirstJavaType ();
                  sTemplate += _getAnnotation (sJavaType) + sJavaType + " " + aArg.getIdentifier ();
                  aJavaTypeKey.add (sJavaType);
                }
                ++nArgIndex;
              }

              List <String> aAllParams = CollectionHelper.newList (sTemplate);
              List <String> aAllJavaKeys = CollectionHelper.newList (StringHelper.getImploded (',', aJavaTypeKey));

              for (int i = 0; i < nArgCount; ++i)
                if (aMultiJavaTypeArgs[i] != null)
                {
                  final List <String> aNewParams = new ArrayList <String> ();
                  final List <String> aNewJavaKeys = new ArrayList <String> ();
                  final String sSearch = "{" + i + "}";
                  for (final String sJavaType : aMultiJavaTypeArgs[i].getAllJavaTypes ())
                  {
                    for (final String sParam : aAllParams)
                      aNewParams.add (sParam.replace (sSearch, _getAnnotation (sJavaType) + sJavaType));
                    for (final String sJavaKey : aAllJavaKeys)
                      aNewJavaKeys.add (sJavaKey.replace (sSearch, sJavaType));
                  }
                  aAllParams = aNewParams;
                  aAllJavaKeys = aNewJavaKeys;
                }

              for (int i = 0; i < aAllParams.size (); ++i)
                if (aUsedJavaSignatures.add (sUsedSignaturePrefix + aAllJavaKeys.get (i)))
                {
                  String sLine = sRealPrefix +
                                 "(" +
                                 aAllParams.get (i) +
                                 ") { return " +
                                 aEntry.getIdentifier () +
                                 " ()";
                  for (final Argument aArg : aSignature.getAllArguments ())
                    sLine += ".arg (" + aArg.getIdentifier () + ")";
                  aLines.add (sLine + "; }");
                }
            }
          }
        }
      }

    final StringBuilder aFull = new StringBuilder ("package com.helger.html.js.builder.jquery;\n" +
                                                   "\n" +
                                                   "import java.math.BigDecimal;\n" +
                                                   "import java.math.BigInteger;\n" +
                                                   "\n" +
                                                   "import javax.annotation.Nonnull;\n" +
                                                   "import javax.annotation.Nullable;\n" +
                                                   "\n" +
                                                   "import com.helger.commons.annotations.Nonempty;\n" +
                                                   "import com.helger.html.EHTMLElement;\n" +
                                                   "import com.helger.html.css.ICSSClassProvider;\n" +
                                                   "import com.helger.html.hc.IHCNode;\n" +
                                                   "import com.helger.html.js.builder.IJSExpression;\n" +
                                                   "import com.helger.html.js.builder.JSAnonymousFunction;\n" +
                                                   "import com.helger.html.js.builder.JSArray;\n" +
                                                   "import com.helger.html.js.builder.JSFunction;\n" +
                                                   "import com.helger.json.IJson;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_AbstractJQueryInvocationExtended.class.getName () +
                                                   "\n" +
                                                   " * @param <IMPLTYPE> Implementation type\n" +
                                                   "*/\n" +
                                                   "public abstract class AbstractJQueryInvocationExtended <IMPLTYPE extends AbstractJQueryInvocationExtended <IMPLTYPE>> extends AbstractJQueryInvocation <IMPLTYPE> implements IJQueryInvocationExtended <IMPLTYPE>\n" +
                                                   "{\n" +
                                                   "  public AbstractJQueryInvocationExtended (@Nonnull final JSFunction aFunction)\n" +
                                                   "  {\n" +
                                                   "    super (aFunction);\n" +
                                                   "  }\n" +
                                                   "\n" +
                                                   "  public AbstractJQueryInvocationExtended (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)\n" +
                                                   "  {\n" +
                                                   "    super (aLhs, sMethod);\n" +
                                                   "  }\n" +
                                                   "\n");
    for (final String sEntry : aLines)
      aFull.append (sEntry).append ("\n\n");
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/main/java/com/helger/html/js/builder/jquery/AbstractJQueryInvocationExtended.java"),
                            aFull.toString (),
                            CCharset.CHARSET_UTF_8_OBJ);
    System.out.println ("Done");
  }
}
