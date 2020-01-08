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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.file.FileSystemRecursiveIterator;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.mock.HCTestRuleOptimized;

@RunWith (value = Parameterized.class)
public final class MarkdownProcessorTest
{
  @Parameters
  public static Iterable <Object []> markdownTests ()
  {
    final ICommonsList <Object []> ret = new CommonsArrayList<> ();
    for (final File aFile : new FileSystemRecursiveIterator (new File ("src/test/resources/MarkdownTest")))
    {
      final String sFilename = aFile.getName ();
      if (sFilename.endsWith (".text"))
      {
        final String sTestName = FilenameHelper.getWithoutExtension (sFilename);
        ret.add (new Object [] { aFile.getParentFile ().getAbsolutePath (), sTestName });
      }
    }
    return ret;
  }

  @Rule
  public final HCTestRuleOptimized m_aRule = new HCTestRuleOptimized ();

  private final String m_sDir;
  private final String m_sTestName;

  public MarkdownProcessorTest (final String sDir, final String sTestName)
  {
    m_sDir = sDir;
    m_sTestName = sTestName;
  }

  @Nonnull
  private static String _slurp (final String sFilename)
  {
    // Avoid differences in newlines
    final String sFile = SimpleFileIO.getFileAsString (new File (sFilename), StandardCharsets.UTF_8);
    return StringHelper.removeAll (sFile, '\r');
  }

  @Test
  public void runTest () throws IOException
  {
    final String testText = _slurp (m_sDir + File.separator + m_sTestName + ".text");
    final String htmlText = _slurp (m_sDir + File.separator + m_sTestName + ".html");
    try
    {
      final String markdownText = new MarkdownProcessor ().process (testText).getAsHTMLString ();
      assertEquals (m_sTestName, htmlText.trim (), markdownText.trim ());
    }
    catch (final MarkdownException ex)
    {
      throw new IllegalStateException ("Error processing test file '" + m_sTestName + "'", ex);
    }
  }
}
