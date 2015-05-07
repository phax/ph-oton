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
package com.helger.photon.basic.app.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.io.streams.StreamUtils;
import com.helger.photon.basic.app.io.IPathRelativeIO;
import com.helger.photon.basic.app.io.PathRelativeFileIO;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.mock.AppBasicTestRule;

/**
 * Test class for class {@link PathRelativeFileIO}
 * 
 * @author Philip Helger
 */
public final class PathRelativeIOTest
{
  @Rule
  public final TestRule m_aRule = new AppBasicTestRule ();

  @Test
  public void testBasePath () throws IOException
  {
    final IPathRelativeIO aIO = WebFileIO.getDataIO ();
    final String sTestFile = "testfile";
    final String sTestContent = "Das ist der Inhalt der TestDatei";
    final String sTestFile2 = "testfile2";

    // may not exist
    assertFalse (aIO.existsFile (sTestFile));

    try
    {
      // write file
      final OutputStream aOS = aIO.getOutputStream (sTestFile);
      assertNotNull (aOS);
      aOS.write (sTestContent.getBytes ());
      assertTrue (StreamUtils.close (aOS).isSuccess ());

      // rename a to b
      assertTrue (aIO.existsFile (sTestFile));
      assertTrue (aIO.renameFile (sTestFile, sTestFile2).isSuccess ());

      // ensure only b is present
      assertFalse (aIO.existsFile (sTestFile));
      assertTrue (aIO.existsFile (sTestFile2));

      // rename back from b to a
      assertTrue (aIO.renameFile (sTestFile2, sTestFile).isSuccess ());

      // ensure only a is present
      assertTrue (aIO.existsFile (sTestFile));
      assertFalse (aIO.existsFile (sTestFile2));

      // read file
      final InputStream aIS = aIO.getResource (sTestFile).getInputStream ();
      assertNotNull (aIS);
      StreamUtils.close (aIS);
    }
    finally
    {
      // ensure all files are gone :)
      if (aIO.existsFile (sTestFile))
        assertTrue (aIO.deleteFile (sTestFile).isSuccess ());
      if (aIO.existsFile (sTestFile2))
        assertTrue (aIO.deleteFile (sTestFile2).isSuccess ());
      assertFalse (aIO.existsFile (sTestFile));
    }
  }
}
