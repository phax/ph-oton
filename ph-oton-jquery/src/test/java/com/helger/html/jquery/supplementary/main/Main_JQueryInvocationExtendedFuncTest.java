/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;

public class Main_JQueryInvocationExtendedFuncTest extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args)
  {
    // Read all data
    final ICommonsList <Entry> aAllEntries = readAllEntries ();
    final ICommonsList <String> aLines = new CommonsArrayList <> ();

    // IJQueryInvocationExtended
    final ICommonsSet <String> aUsedJavaSignatures = new CommonsHashSet <> ();
    int nUniqueIndex = 0;
    for (final Entry aEntry : aAllEntries)
      if (aEntry.getAPIType () == EAPIType.METHOD)
      {
        if (aEntry.isStaticMethod ())
          continue;

        if (aEntry.isDeprecated ())
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

          final String sRealPrefix = "@Test\npublic void test" + aEntry.getIdentifier ();

          if (nArgCount == 1)
          {
            // Only one argument
            final Argument aArg = aSignature.getArgumentAtIndex (0);
            for (final String sJavaType : aArg.getAllJavaTypes ())
              if (aUsedJavaSignatures.add (sUsedSignaturePrefix + sJavaType))
              {
                aLines.add (sRealPrefix +
                            (nUniqueIndex++) +
                            "() { assertNotNull (JQuery.idRef (\"any\")." +
                            aEntry.getIdentifier () +
                            " (" +
                            _getTestValue (sJavaType) +
                            ")); }");
              }
          }
          else
          {
            // More than one argument
            final int nMultiJavaTypeArgs = aSignature.getArgumentsWithMultipleJavaTypesCount ();
            if (nMultiJavaTypeArgs == 0)
            {
              final StringBuilder aParams = new StringBuilder ();
              final ICommonsList <String> aJavaTypeKey = new CommonsArrayList <> ();
              for (final Argument aArg : aSignature.getAllArguments ())
              {
                if (aParams.length () > 0)
                  aParams.append (", ");

                final String sJavaType = aArg.getFirstJavaType ();
                aParams.append (_getAnnotation (sJavaType))
                       .append (sJavaType)
                       .append (" ")
                       .append (aArg.getIdentifier ());
                aJavaTypeKey.add (sJavaType);
              }
              if (aUsedJavaSignatures.add (sUsedSignaturePrefix + StringHelper.getImploded (',', aJavaTypeKey)))
              {
                final StringBuilder aLine = new StringBuilder ();
                aLine.append (sRealPrefix)
                     .append (nUniqueIndex++)
                     .append ("() { assertNotNull (JQuery.idRef (\"any\").")
                     .append (aEntry.getIdentifier ())
                     .append (" (");
                int i = 0;
                for (final Argument aArg : aSignature.getAllArguments ())
                {
                  if (i++ > 0)
                    aLine.append (", ");
                  final String sJavaType = aArg.getFirstJavaType ();
                  aLine.append (_getTestValue (sJavaType));
                }
                aLines.add (aLine.append (")); }").toString ());
              }
            }
            else
            {
              // At least one multi java-type argument
              final Argument [] aMultiJavaTypeArgs = new Argument [nArgCount];

              // Build template
              final StringBuilder aTemplate = new StringBuilder ();
              final ICommonsList <String> aJavaTypeKey = new CommonsArrayList <> ();
              int nArgIndex = 0;
              for (final Argument aArg : aSignature.getAllArguments ())
              {
                if (aTemplate.length () > 0)
                  aTemplate.append (", ");

                if (aArg.getJavaTypeCount () > 1)
                {
                  final String sJavaType = "{" + nArgIndex + "}";
                  aMultiJavaTypeArgs[nArgIndex] = aArg;
                  aTemplate.append (sJavaType);
                  aJavaTypeKey.add (sJavaType);
                }
                else
                {
                  final String sJavaType = aArg.getFirstJavaType ();
                  aTemplate.append (_getTestValue (sJavaType));
                  aJavaTypeKey.add (sJavaType);
                }
                ++nArgIndex;
              }

              ICommonsList <String> aAllParams = new CommonsArrayList <> (aTemplate.toString ());
              ICommonsList <String> aAllJavaKeys = new CommonsArrayList <> (StringHelper.getImploded (',',
                                                                                                      aJavaTypeKey));

              for (int i = 0; i < nArgCount; ++i)
                if (aMultiJavaTypeArgs[i] != null)
                {
                  final ICommonsList <String> aNewParams = new CommonsArrayList <> ();
                  final ICommonsList <String> aNewJavaKeys = new CommonsArrayList <> ();
                  final String sSearch = "{" + i + "}";
                  for (final String sJavaType : aMultiJavaTypeArgs[i].getAllJavaTypes ())
                  {
                    for (final String sParam : aAllParams)
                      aNewParams.add (StringHelper.replaceAll (sParam, sSearch, _getTestValue (sJavaType)));
                    for (final String sJavaKey : aAllJavaKeys)
                      aNewJavaKeys.add (StringHelper.replaceAll (sJavaKey, sSearch, sJavaType));
                  }
                  aAllParams = aNewParams;
                  aAllJavaKeys = aNewJavaKeys;
                }

              for (int i = 0; i < aAllParams.size (); ++i)
              {
                if (aUsedJavaSignatures.add (sUsedSignaturePrefix + aAllJavaKeys.get (i)))
                {
                  final String sParams = aAllParams.get (i);
                  final String sLine = sRealPrefix +
                                       (nUniqueIndex++) +
                                       "() { assertNotNull (JQuery.idRef (\"any\")." +
                                       aEntry.getIdentifier () +
                                       " (" +
                                       sParams;
                  aLines.add (sLine + ")); }");
                }
              }
            }
          }
        }
      }

    final StringBuilder aFull = new StringBuilder ("package com.helger.html.jquery;\n" +
                                                   "\n" +
                                                   "import static org.junit.Assert.assertNotNull;\n" +
                                                   "\n" +
                                                   "import org.junit.Test;\n" +
                                                   "\n" +
                                                   "import java.math.BigDecimal;\n" +
                                                   "import java.math.BigInteger;\n" +
                                                   "\n" +
                                                   "\n" +
                                                   "import com.helger.html.EHTMLElement;\n" +
                                                   "import com.helger.html.css.DefaultCSSClassProvider;\n" +
                                                   "import com.helger.html.hc.html.grouping.HCDiv;\n" +
                                                   "import com.helger.html.jscode.JSAnonymousFunction;\n" +
                                                   "import com.helger.html.jscode.JSArray;\n" +
                                                   "import com.helger.html.jscode.JSExpr;\n" +
                                                   "import com.helger.json.JsonObject;\n" +
                                                   "import com.helger.xml.microdom.MicroQName;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * Unit test class for class @{link IJQueryInvocationExtended}\n" +
                                                   " *\n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_JQueryInvocationExtendedFuncTest.class.getName () +
                                                   "\n" +
                                                   "*/\n" +
                                                   "public final class JQueryInvocationExtendedFuncTest\n" +
                                                   "{\n");
    for (final String sEntry : aLines)
      aFull.append (sEntry).append ("\n\n");
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/test/java/com/helger/html/jquery/JQueryInvocationExtendedFuncTest.java"),
                            aFull.toString (),
                            StandardCharsets.UTF_8);
    LOGGER.info ("Done");
  }
}
