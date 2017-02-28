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

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsHashSet;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;

public class Main_IJQueryInvocationExtended extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args) throws Exception
  {
    // Read all data
    final ICommonsList <Entry> aAllEntries = readAllEntries ();
    final ICommonsList <String> aLines = new CommonsArrayList<> ();

    // IJQueryInvocationExtended
    final ICommonsSet <String> aUsedJavaSignatures = new CommonsHashSet<> ();
    for (final Entry aEntry : aAllEntries)
      if (aEntry.getAPIType () == EAPIType.METHOD)
      {
        if (aEntry.isStaticMethod ())
          continue;

        final String sUsedSignaturePrefix = aEntry.getName () + ":";
        for (final Signature aSignature : aEntry.getAllSignatures ())
        {
          final int nArgCount = aSignature.getArgumentCount ();

          String sRealPrefix = "@Nonnull THISTYPE " + aEntry.getIdentifier ();

          // Build comment
          String sComment = "";
          for (final Argument aArg : aSignature.getAllArguments ())
          {
            String sDesc = aArg.getDescription ();
            if (StringHelper.hasNoText (sDesc))
              sDesc = "parameter value";
            sComment += "* @param " + aArg.getIdentifier () + " " + sDesc + "\n";
          }
          if (aSignature.getArgumentCount () > 0)
            sComment += '\n';
          sComment += "* @return this\n";
          if (aEntry.isDeprecated ())
          {
            sComment += "* @deprecated Deprecated since jQuery " + aEntry.getDeprecated ().getAsString (false) + "\n";
            sRealPrefix = "@Deprecated\n" + sRealPrefix;
          }
          sComment += "* @since jQuery " + aSignature.getAdded ().getAsString (false) + "\n";
          if (sComment.length () > 0)
            sComment = "/**\n" + sComment + "*/\n";

          if (nArgCount == 0)
          {
            // Only one argument - overwrite comment from parent class, as the
            // @since may be different
            if (aUsedJavaSignatures.add (sUsedSignaturePrefix))
              aLines.add (sComment + sRealPrefix + "();");
          }
          else
            if (nArgCount == 1)
            {
              // Only one argument
              final Argument aArg = aSignature.getArgumentAtIndex (0);
              for (final String sJavaType : aArg.getAllJavaTypes ())
                if (aUsedJavaSignatures.add (sUsedSignaturePrefix + sJavaType))
                  aLines.add (sComment +
                              sRealPrefix +
                              "(" +
                              _getAnnotation (sJavaType) +
                              sJavaType +
                              " " +
                              aArg.getIdentifier () +
                              ");");
            }
            else
            {
              // More than one argument
              final int nMultiJavaTypeArgs = aSignature.getArgumentsWithMultipleJavaTypesCount ();
              if (nMultiJavaTypeArgs == 0)
              {
                String sParams = "";
                final ICommonsList <String> aJavaTypeKey = new CommonsArrayList<> ();
                for (final Argument aArg : aSignature.getAllArguments ())
                {
                  if (sParams.length () > 0)
                    sParams += ", ";

                  final String sJavaType = aArg.getFirstJavaType ();
                  sParams += _getAnnotation (sJavaType) + sJavaType + " " + aArg.getIdentifier ();
                  aJavaTypeKey.add (sJavaType);
                }
                if (aUsedJavaSignatures.add (sUsedSignaturePrefix + StringHelper.getImploded (',', aJavaTypeKey)))
                  aLines.add (sComment + sRealPrefix + "(" + sParams + ");");
              }
              else
              {
                // At least one multi java-type argument
                final Argument [] aMultiJavaTypeArgs = new Argument [nArgCount];

                // Build template
                String sTemplate = "";
                final ICommonsList <String> aJavaTypeKey = new CommonsArrayList<> ();
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

                ICommonsList <String> aAllParams = new CommonsArrayList<> (sTemplate);
                ICommonsList <String> aAllJavaKeys = new CommonsArrayList<> (StringHelper.getImploded (',',
                                                                                                       aJavaTypeKey));

                for (int i = 0; i < nArgCount; ++i)
                  if (aMultiJavaTypeArgs[i] != null)
                  {
                    final ICommonsList <String> aNewParams = new CommonsArrayList<> ();
                    final ICommonsList <String> aNewJavaKeys = new CommonsArrayList<> ();
                    final String sSearch = "{" + i + "}";
                    for (final String sJavaType : aMultiJavaTypeArgs[i].getAllJavaTypes ())
                    {
                      for (final String sParam : aAllParams)
                        aNewParams.add (StringHelper.replaceAll (sParam,
                                                                 sSearch,
                                                                 _getAnnotation (sJavaType) + sJavaType));
                      for (final String sJavaKey : aAllJavaKeys)
                        aNewJavaKeys.add (StringHelper.replaceAll (sJavaKey, sSearch, sJavaType));
                    }
                    aAllParams = aNewParams;
                    aAllJavaKeys = aNewJavaKeys;
                  }

                for (int i = 0; i < aAllParams.size (); ++i)
                  if (aUsedJavaSignatures.add (sUsedSignaturePrefix + aAllJavaKeys.get (i)))
                    aLines.add (sComment + sRealPrefix + "(" + aAllParams.get (i) + ");");
              }
            }
        }
      }

    final StringBuilder aFull = new StringBuilder ("package com.helger.html.jquery;\n" +
                                                   "\n" +
                                                   "import java.math.BigDecimal;\n" +
                                                   "import java.math.BigInteger;\n" +
                                                   "\n" +
                                                   "import javax.annotation.Nonnull;\n" +
                                                   "\n" +
                                                   "import com.helger.html.EHTMLElement;\n" +
                                                   "import com.helger.html.css.ICSSClassProvider;\n" +
                                                   "import com.helger.html.hc.IHCNode;\n" +
                                                   "import com.helger.html.jscode.IJSExpression;\n" +
                                                   "import com.helger.html.jscode.JSAnonymousFunction;\n" +
                                                   "import com.helger.html.jscode.JSArray;\n" +
                                                   "import com.helger.json.IJson;\n" +
                                                   "\n" +
                                                   "/**\n" +
                                                   " * This file is generated - do NOT edit!\n" +
                                                   " * @author " +
                                                   Main_IJQueryInvocationExtended.class.getName () +
                                                   "\n" +
                                                   " * @param <THISTYPE> Implementation type\n" +
                                                   "*/\n" +
                                                   "public interface IJQueryInvocationExtended <THISTYPE extends IJQueryInvocationExtended <THISTYPE>> extends IJQueryInvocation <THISTYPE>\n" +
                                                   "{\n");
    for (final String sEntry : aLines)
      aFull.append (sEntry).append ("\n\n");
    aFull.append ("}\n");
    SimpleFileIO.writeFile (new File ("src/main/java/com/helger/html/jquery/IJQueryInvocationExtended.java"),
                            aFull.toString (),
                            StandardCharsets.UTF_8);
    s_aLogger.info ("Done");
  }
}
