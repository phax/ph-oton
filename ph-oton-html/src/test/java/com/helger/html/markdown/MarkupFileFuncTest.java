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
/*
 Copyright (c) 2005, Pete Bevin.
 <http://markdownj.petebevin.com>

 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are
 met:

 * Redistributions of source code must retain the above copyright notice,
 this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.

 * Neither the name "Markdown" nor the names of its contributors may
 be used to endorse or promote products derived from this software
 without specific prior written permission.

 This software is provided by the copyright holders and contributors "as
 is" and any express or implied warranties, including, but not limited
 to, the implied warranties of merchantability and fitness for a
 particular purpose are disclaimed. In no event shall the copyright owner
 or contributors be liable for any direct, indirect, incidental, special,
 exemplary, or consequential damages (including, but not limited to,
 procurement of substitute goods or services; loss of use, data, or
 profits; or business interruption) however caused and on any theory of
 liability, whether in contract, strict liability, or tort (including
 negligence or otherwise) arising in any way out of the use of this
 software, even if advised of the possibility of such damage.

 */

package com.helger.html.markdown;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.stream.NonBlockingBufferedReader;
import com.helger.commons.regex.RegExCache;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.mock.HCTestRuleOptimized;
import com.helger.html.markdown.MarkdownConfiguration.Builder;

@RunWith (Parameterized.class)
public final class MarkupFileFuncTest
{
  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  private final static String [] TEST_FILENAMES = new String [] { "dingus.txt",
                                                                  "paragraphs.txt",
                                                                  "snippets.txt",
                                                                  "lists.txt" };

  private static void _addTestResultPair (final List <String []> list,
                                          @Nonnull final String sFilename,
                                          final StringBuilder testbuf,
                                          final StringBuilder resultbuf,
                                          final String testNumber,
                                          final String testName)
  {
    if (testbuf != null && resultbuf != null)
    {
      final String test = StringHelper.trimTrailingWhitespaces (testbuf.toString ());
      final String result = StringHelper.trimTrailingWhitespaces (resultbuf.toString ());

      list.add (new String [] { "[" + sFilename + "]" + testNumber + "(" + testName + ")", result, test });
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  private static ICommonsList <String []> _getTestResultPairList (final String sFilename) throws IOException
  {
    final ICommonsList <String []> ret = new CommonsArrayList <> ();
    try (final NonBlockingBufferedReader in = new NonBlockingBufferedReader (new InputStreamReader (ClassPathResource.getInputStream (sFilename),
                                                                                                    CCharset.CHARSET_ISO_8859_1_OBJ)))
    {
      StringBuilder aTest = null;
      StringBuilder aResult = null;

      final Pattern aPTest = RegExCache.getPattern ("# Test (\\w+) \\((.*)\\)");
      final Pattern aPResult = RegExCache.getPattern ("# Result (\\w+)");
      String sLine;
      int nLineNumber = 0;

      String sTestNumber = null;
      String sTestName = null;
      StringBuilder aCurbuf = null;
      while ((sLine = in.readLine ()) != null)
      {
        nLineNumber++;
        final Matcher mTest = aPTest.matcher (sLine);
        final Matcher mResult = aPResult.matcher (sLine);

        if (mTest.matches ())
        {
          // Last match
          _addTestResultPair (ret, sFilename, aTest, aResult, sTestNumber, sTestName);

          // # Test
          sTestNumber = mTest.group (1);
          sTestName = mTest.group (2);
          aTest = new StringBuilder ();
          aResult = new StringBuilder ();
          aCurbuf = aTest;
        }
        else
          if (mResult.matches ())
          {
            // # Result
            if (sTestNumber == null)
              throw new IllegalStateException ("Test file has result without a test (line " + nLineNumber + ")");
            final String resultNumber = mResult.group (1);
            if (!sTestNumber.equals (resultNumber))
            {
              throw new IllegalStateException ("Result " +
                                               resultNumber +
                                               " test " +
                                               sTestNumber +
                                               " (line " +
                                               nLineNumber +
                                               ")");
            }

            aCurbuf = aResult;
          }
          else
          {
            if (aCurbuf == null)
              throw new IllegalStateException ();
            aCurbuf.append (sLine).append ('\n');
          }
      }

      // The last one
      _addTestResultPair (ret, sFilename, aTest, aResult, sTestNumber, sTestName);

      return ret;
    }
  }

  @Parameters
  public static Iterable <Object []> testResultPairs () throws IOException
  {
    final ICommonsCollection <Object []> testResultPairs = new CommonsArrayList <> ();
    for (final String filename : TEST_FILENAMES)
      for (final String [] aTest : _getTestResultPairList ("MarkupFiles/" + filename))
        testResultPairs.add (new Object [] { aTest[0], aTest[1], aTest[2] });
    return testResultPairs;
  }

  @Parameter (0)
  public String m_sTestName;

  @Parameter (1)
  public String m_sExpectedResult;

  @Parameter (2)
  public String m_sTestString;

  @Test
  public void runTest () throws IOException
  {
    final Builder aBuilder = MarkdownConfiguration.builder ();
    if (m_sTestName.startsWith ("[MarkupFiles/dingus.txt]1("))
      aBuilder.setExtendedProfile (true);
    assertEquals (m_sTestName,
                  m_sExpectedResult.trim (),
                  new MarkdownProcessor (aBuilder.build ()).process (m_sTestString).getAsHTMLString ());
  }
}
