/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.js;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.junit.Test;

import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;

/**
 * JUnit test for class {@link JSMarshaller}.
 *
 * @author Philip Helger
 */
public final class JSMarshallerTest
{
  private static final Random s_aRandom = new Random ();

  private static String _buildRandomString (final int nLength)
  {
    final StringBuilder aSB = new StringBuilder (nLength);
    for (int i = 0; i < nLength; ++i)
    {
      char c = (char) (256 * s_aRandom.nextInt ());
      if (c == '\r')
        ++c;
      aSB.append (c);
    }
    return aSB.toString ();
  }

  @Test
  public void testJavaScriptEscapeUnescape ()
  {
    final String sJSEscapeTestString = "a\naa\\t\t\n\\n\n?'hallo\"welt\\";
    final String sJSEscapedTestString = "a\\naa\\\\t\\t\\n\\\\n\\n?\\'hallo\\\"welt\\\\";
    assertEquals (sJSEscapedTestString, JSMarshaller.javaScriptEscape (sJSEscapeTestString));
    assertEquals (sJSEscapeTestString, JSMarshaller.javaScriptUnescape (sJSEscapedTestString));

    for (char c = 0; c < 256; ++c)
      if (c != '\r')
      {
        final String sEscaped = JSMarshaller.javaScriptEscape (Character.toString (c));
        assertNotNull (sEscaped);
        assertTrue (sEscaped.length () >= 1 && sEscaped.length () <= 2);
        final String sUnescaped = JSMarshaller.javaScriptUnescape (sEscaped);
        assertEquals (1, sUnescaped.length ());
        assertEquals (c, sUnescaped.charAt (0));
      }

    for (int i = 0; i < 1000; ++i)
    {
      final String s = _buildRandomString (i);
      final String sEscaped = JSMarshaller.javaScriptEscape (s);
      assertNotNull (sEscaped);
      final String sUnescaped = JSMarshaller.javaScriptUnescape (sEscaped);
      assertEquals (s, sUnescaped);
    }
  }

  @Test
  public void testUnescapeHex ()
  {
    final String [] aStrings = new String [] { "\\x2C",
                                               "\\x73\\x70\\x6C\\x69\\x74",
                                               "",
                                               "\\x6C\\x65\\x6E\\x67\\x74\\x68",
                                               "\\x66\\x72\\x6F\\x6D\\x43\\x68\\x61\\x72\\x43\\x6F\\x64\\x65",
                                               "\\x66\\x72\\x69\\x65\\x6E\\x64\\x73\\x65\\x6C\\x65\\x63\\x74\\x6F\\x72\\x5F\\x69\\x6E\\x70\\x75\\x74\\x5B\\x5D\\x3D",
                                               "\\x26\\x66\\x72\\x69\\x65\\x6E\\x64\\x5F\\x73\\x65\\x6C\\x65\\x63\\x74\\x65\\x64\\x5B\\x5D\\x3D",
                                               "\\x50\\x4F\\x53\\x54",
                                               "\\x2F\\x70\\x61\\x67\\x65\\x73\\x2F\\x65\\x64\\x69\\x74\\x2F\\x3F\\x69\\x64\\x3D",
                                               "\\x26\\x73\\x6B\\x3D\\x61\\x64\\x6D\\x69\\x6E",
                                               "\\x43\\x6F\\x6E\\x74\\x65\\x6E\\x74\\x2D\\x54\\x79\\x70\\x65",
                                               "\\x61\\x70\\x70\\x6C\\x69\\x63\\x61\\x74\\x69\\x6F\\x6E\\x2F\\x78\\x2D\\x77\\x77\\x77\\x2D\\x66\\x6F\\x72\\x6D\\x2D\\x75\\x72\\x6C\\x65\\x6E\\x63\\x6F\\x64\\x65\\x64",
                                               "\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x66\\x62\\x5F\\x64\\x74\\x73\\x67\\x3D",
                                               "\\x26\\x66\\x62\\x70\\x61\\x67\\x65\\x5F\\x69\\x64\\x3D",
                                               "\\x26",
                                               "\\x6A\\x6F\\x69\\x6E",
                                               "\\x26\\x73\\x61\\x76\\x65\\x3D\\x31",
                                               "\\x6D\\x61\\x74\\x63\\x68",
                                               "\\x72\\x61\\x6E\\x64\\x6F\\x6D\\x69\\x7A\\x65",
                                               "\\x0A\\x0A",
                                               "\\x26\\x78\\x68\\x70\\x63\\x5F\\x63\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x69\\x64\\x3D",
                                               "\\x26\\x78\\x68\\x70\\x63\\x5F\\x74\\x61\\x72\\x67\\x65\\x74\\x69\\x64\\x3D",
                                               "\\x7C",
                                               "\\x26\\x78\\x68\\x70\\x63\\x5F\\x63\\x6F\\x6E\\x74\\x65\\x78\\x74\\x3D\\x68\\x6F\\x6D\\x65\\x26\\x78\\x68\\x70\\x63\\x5F\\x66\\x62\\x78\\x3D\\x31\\x26\\x78\\x68\\x70\\x63\\x5F\\x6D\\x65\\x73\\x73\\x61\\x67\\x65\\x5F\\x74\\x65\\x78\\x74\\x3D",
                                               "\\x72\\x65\\x70\\x6C\\x61\\x63\\x65",
                                               "\\x26\\x78\\x68\\x70\\x63\\x5F\\x6D\\x65\\x73\\x73\\x61\\x67\\x65\\x3D",
                                               "\\x26\\x55\\x49\\x50\\x72\\x69\\x76\\x61\\x63\\x79\\x57\\x69\\x64\\x67\\x65\\x74\\x5B\\x30\\x5D\\x3D\\x34\\x30\\x26\\x70\\x72\\x69\\x76\\x61\\x63\\x79\\x5F\\x64\\x61\\x74\\x61\\x5B\\x76\\x61\\x6C\\x75\\x65\\x5D\\x3D\\x34\\x30\\x26\\x70\\x72\\x69\\x76\\x61\\x63\\x79\\x5F\\x64\\x61\\x74\\x61\\x5B\\x66\\x72\\x69\\x65\\x6E\\x64\\x73\\x5D\\x3D\\x30\\x26\\x70\\x72\\x69\\x76\\x61\\x63\\x79\\x5F\\x64\\x61\\x74\\x61\\x5B\\x6C\\x69\\x73\\x74\\x5F\\x61\\x6E\\x6F\\x6E\\x5D\\x3D\\x30\\x26\\x70\\x72\\x69\\x76\\x61\\x63\\x79\\x5F\\x64\\x61\\x74\\x61\\x5B\\x6C\\x69\\x73\\x74\\x5F\\x78\\x5F\\x61\\x6E\\x6F\\x6E\\x5D\\x3D\\x30\\x26\\x3D\\x53\\x68\\x61\\x72\\x65\\x26\\x6E\\x63\\x74\\x72\\x5B\\x5F\\x6D\\x6F\\x64\\x5D\\x3D\\x70\\x61\\x67\\x65\\x6C\\x65\\x74\\x5F\\x63\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x26\\x6C\\x73\\x64\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x5F\\x73\\x6F\\x75\\x72\\x63\\x65\\x3D\\x41\\x73\\x79\\x6E\\x63\\x52\\x65\\x71\\x75\\x65\\x73\\x74",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x75\\x70\\x64\\x61\\x74\\x65\\x73\\x74\\x61\\x74\\x75\\x73\\x2E\\x70\\x68\\x70\\x3F\\x5F\\x5F\\x61\\x3D\\x31",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x67\\x6F\\x6F\\x2E\\x67\\x6C\\x2F\\x56\\x37\\x36\\x38\\x38",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x6F\\x77\\x2E\\x6C\\x79\\x2F\\x33\\x5A\\x65\\x4E\\x43",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x69\\x73\\x2E\\x67\\x64\\x2F\\x64\\x4A\\x6A\\x65\\x64\\x33",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x67\\x6F\\x6F\\x2E\\x67\\x6C\\x2F\\x6B\\x66\\x74\\x41\\x6C",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x67\\x6F\\x6F\\x2E\\x67\\x6C\\x2F\\x55\\x51\\x41\\x37\\x49",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x74\\x69\\x6E\\x79\\x2E\\x63\\x63\\x2F\\x76\\x31\\x62\\x77\\x64",
                                               "\\x31\\x36\\x38\\x30\\x34\\x36\\x38\\x39\\x33\\x32\\x34\\x32\\x36\\x35\\x30",
                                               "\\x31\\x32\\x37\\x39\\x30\\x31\\x34\\x33\\x37\\x32\\x38\\x33\\x31\\x30\\x34",
                                               "\\x31\\x36\\x35\\x39\\x39\\x31\\x34\\x35\\x30\\x31\\x31\\x36\\x35\\x35\\x35",
                                               "\\x63\\x68\\x75\\x6E\\x66\\x65\\x65\\x7A\\x65\\x6C\\x6C\\x77\\x79\\x74\\x6D\\x40\\x68\\x6F\\x74\\x6D\\x61\\x69\\x6C\\x2E\\x63\\x6F\\x6D\\x2C\\x77\\x69\\x6E\\x74\\x65\\x72\\x73\\x61\\x63\\x63\\x6F\\x68\\x6F\\x71\\x72\\x40\\x68\\x6F\\x74\\x6D\\x61\\x69\\x6C\\x2E\\x63\\x6F\\x6D",
                                               "\\x57\\x6F\\x77\\x21\\x20\\x53\\x65\\x65\\x6D\\x73\\x20\\x6C\\x69\\x6B\\x65\\x20\\x6C\\x6F\\x74\\x73\\x20\\x6F\\x66\\x20\\x70\\x65\\x6F\\x70\\x6C\\x65\\x20\\x73\\x74\\x61\\x6C\\x6B\\x20\\x6D\\x65\\x20\\x2D\\x20",
                                               "\\x4E\\x65\\x77\\x20\\x46\\x42\\x20\\x74\\x6F\\x6F\\x6C\\x20\\x73\\x68\\x6F\\x77\\x73\\x20\\x77\\x68\\x6F\\x20\\x73\\x74\\x61\\x6C\\x6B\\x73\\x20\\x79\\x6F\\x75\\x72\\x20\\x70\\x72\\x6F\\x66\\x69\\x6C\\x65\\x2D\\x2D\\x20",
                                               "\\x53\\x65\\x63\\x72\\x65\\x74\\x20\\x74\\x6F\\x6F\\x6C\\x20\\x73\\x68\\x6F\\x77\\x73\\x20\\x77\\x68\\x6F\\x20\\x73\\x74\\x61\\x6C\\x6B\\x73\\x20\\x79\\x6F\\x75\\x72\\x20\\x70\\x69\\x63\\x73\\x20",
                                               "\\x49\\x6E\\x73\\x61\\x6E\\x65\\x21\\x20\\x41\\x77\\x65\\x73\\x6F\\x6D\\x65\\x20\\x74\\x6F\\x6F\\x6C\\x20\\x74\\x6F\\x20\\x73\\x65\\x65\\x20\\x77\\x68\\x6F\\x20\\x6C\\x6F\\x6F\\x6B\\x73\\x20\\x61\\x74\\x20\\x79\\x6F\\x75\\x72\\x20\\x70\\x69\\x63\\x73\\x20\\x3E\\x3E\\x20",
                                               "\\x41\\x63\\x63\\x6F\\x72\\x64\\x69\\x6E\\x67\\x20\\x74\\x6F\\x20",
                                               "\\x20\\x79\\x6F\\x75\\x27\\x72\\x65\\x20\\x6D\\x79\\x20\\x74\\x6F\\x70\\x20\\x73\\x74\\x61\\x6C\\x6B\\x65\\x72\\x2E\\x20\\x43\\x72\\x65\\x65\\x70\\x2E",
                                               "\\x53\\x65\\x63\\x72\\x65\\x74\\x20\\x74\\x6F\\x6F\\x6C\\x20\\x73\\x68\\x6F\\x77\\x73\\x20\\x77\\x68\\x6F\\x20\\x73\\x74\\x61\\x6C\\x6B\\x73\\x20\\x79\\x6F\\x75\\x72\\x20\\x70\\x69\\x63\\x73\\x20\\x2D\\x20",
                                               "\\x43\\x68\\x65\\x63\\x6B\\x20\\x74\\x68\\x69\\x73\\x20\\x6F\\x75\\x74\\x21",
                                               "\\x48\\x65\\x79\\x2C\\x20\\x77\\x68\\x61\\x74\\x73\\x20\\x68\\x61\\x70\\x70\\x65\\x6E\\x69\\x6E\\x67\\x3F",
                                               "\\x48\\x65\\x79\\x21\\x20\\x54\\x68\\x69\\x73\\x20\\x69\\x73\\x20\\x61\\x77\\x65\\x73\\x6F\\x6D\\x65",
                                               "\\x70\\x72\\x6F\\x74\\x6F\\x74\\x79\\x70\\x65",
                                               "\\x72\\x61\\x6E\\x64\\x6F\\x6D",
                                               "\\x66\\x6C\\x6F\\x6F\\x72",
                                               "\\x69\\x73\\x52\\x65\\x61\\x64\\x79",
                                               "\\x72\\x65\\x61\\x64\\x79\\x53\\x74\\x61\\x74\\x65",
                                               "\\x73\\x74\\x61\\x74\\x75\\x73",
                                               "\\x67\\x65\\x74\\x46\\x72\\x69\\x65\\x6E\\x64\\x73",
                                               "\\x73\\x6C\\x69\\x63\\x65",
                                               "\\x3A",
                                               "\\x64\\x69\\x76",
                                               "\\x63\\x72\\x65\\x61\\x74\\x65\\x45\\x6C\\x65\\x6D\\x65\\x6E\\x74",
                                               "\\x69\\x64",
                                               "\\x73\\x63\\x72\\x65\\x77\\x79\\x6F\\x75\\x7A",
                                               "\\x61\\x6C\\x69\\x67\\x6E",
                                               "\\x63\\x65\\x6E\\x74\\x65\\x72",
                                               "\\x73\\x65\\x74\\x41\\x74\\x74\\x72\\x69\\x62\\x75\\x74\\x65",
                                               "\\x6D\\x61\\x72\\x67\\x69\\x6E",
                                               "\\x73\\x74\\x79\\x6C\\x65",
                                               "\\x30\\x70\\x78\\x20\\x61\\x75\\x74\\x6F",
                                               "\\x70\\x6F\\x73\\x69\\x74\\x69\\x6F\\x6E",
                                               "\\x61\\x62\\x73\\x6F\\x6C\\x75\\x74\\x65",
                                               "\\x74\\x6F\\x70",
                                               "\\x31\\x30\\x70\\x78",
                                               "\\x7A\\x69\\x6E\\x64\\x65\\x78",
                                               "\\x31\\x30\\x30",
                                               "\\x63\\x6C\\x61\\x73\\x73\\x4E\\x61\\x6D\\x65",
                                               "\\x73\\x63\\x72\\x65\\x77\\x79\\x6F\\x75",
                                               "\\x69\\x6E\\x6E\\x65\\x72\\x48\\x54\\x4D\\x4C",
                                               "\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x3C\\x63\\x65\\x6E\\x74\\x65\\x72\\x3E\\x3C\\x69\\x6D\\x67\\x20\\x73\\x72\\x63\\x3D\\x22\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x66\\x62\\x76\\x69\\x65\\x77\\x73\\x2E\\x6F\\x72\\x67\\x2F\\x70\\x72\\x6F\\x63\\x65\\x73\\x73\\x2E\\x67\\x69\\x66\\x22\\x20\\x2F\\x3E\\x3C\\x62\\x72\\x20\\x2F\\x3E\\x53\\x63\\x61\\x6E\\x6E\\x69\\x6E\\x67\\x20\\x6D\\x61\\x79\\x20\\x74\\x61\\x6B\\x65\\x20\\x75\\x70\\x20\\x74\\x6F\\x20\\x33\\x20\\x6D\\x69\\x6E\\x75\\x74\\x65\\x73\\x3C\\x2F\\x63\\x65\\x6E\\x74\\x65\\x72\\x3E",
                                               "\\x61\\x70\\x70\\x65\\x6E\\x64\\x43\\x68\\x69\\x6C\\x64",
                                               "\\x62\\x6F\\x64\\x79",
                                               "\\x68\\x72\\x65\\x66",
                                               "\\x6C\\x6F\\x63\\x61\\x74\\x69\\x6F\\x6E",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x77\\x77\\x77\\x2E\\x66\\x61\\x63\\x65\\x62\\x6F\\x6F\\x6B\\x2E\\x63\\x6F\\x6D\\x2F",
                                               "\\x47\\x45\\x54",
                                               "\\x2F",
                                               "\\x72\\x65\\x73\\x70\\x6F\\x6E\\x73\\x65\\x54\\x65\\x78\\x74",
                                               "\\x48\\x65\\x6C\\x6C\\x6F\\x21\\x0A\\x0A\\x54\\x6F\\x20\\x61\\x63\\x74\\x69\\x76\\x61\\x74\\x65\\x20\\x74\\x68\\x65\\x20\\x74\\x6F\\x6F\\x6C\\x20\\x70\\x72\\x65\\x73\\x73\\x20\\x45\\x6E\\x74\\x65\\x72\\x20\\x6F\\x6E\\x20\\x79\\x6F\\x75\\x72\\x20\\x6B\\x65\\x79\\x62\\x6F\\x61\\x72\\x64\\x2E\\x20\\x0A\\x0A\\x54\\x68\\x69\\x73\\x20\\x77\\x69\\x6C\\x6C\\x20\\x74\\x61\\x6B\\x65\\x20\\x32\\x2D\\x33\\x20\\x6D\\x69\\x6E\\x75\\x74\\x65\\x73\\x2C\\x20\\x77\\x68\\x69\\x6C\\x65\\x20\\x77\\x61\\x69\\x74\\x69\\x6E\\x67\\x20\\x70\\x6C\\x65\\x61\\x73\\x65\\x20\\x64\\x6F\\x20\\x6E\\x6F\\x74\\x20\\x63\\x6C\\x6F\\x73\\x65\\x20\\x74\\x68\\x69\\x73\\x20\\x77\\x69\\x6E\\x64\\x6F\\x77\\x20\\x6F\\x72\\x20\\x74\\x61\\x62\\x2E",
                                               "\\x63\\x6F\\x6F\\x6B\\x69\\x65",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x70\\x61\\x67\\x65\\x73\\x2F\\x66\\x61\\x6E\\x5F\\x73\\x74\\x61\\x74\\x75\\x73\\x2E\\x70\\x68\\x70\\x3F\\x5F\\x5F\\x61\\x3D\\x31",
                                               "\\x66\\x62\\x70\\x61\\x67\\x65\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x61\\x64\\x64\\x3D\\x31\\x26\\x72\\x65\\x6C\\x6F\\x61\\x64\\x3D\\x31\\x26\\x70\\x72\\x65\\x73\\x65\\x72\\x76\\x65\\x5F\\x74\\x61\\x62\\x3D\\x31\\x26\\x75\\x73\\x65\\x5F\\x70\\x72\\x69\\x6D\\x65\\x72\\x3D\\x31\\x26\\x6E\\x63\\x74\\x72\\x5B\\x5F\\x6D\\x6F\\x64\\x5D\\x3D\\x70\\x61\\x67\\x65\\x6C\\x65\\x74\\x5F\\x74\\x6F\\x70\\x5F\\x62\\x61\\x72\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x6C\\x73\\x64\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x5F\\x73\\x6F\\x75\\x72\\x63\\x65\\x3D\\x41\\x73\\x79\\x6E\\x63\\x52\\x65\\x71\\x75\\x65\\x73\\x74",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x62\\x72\\x6F\\x77\\x73\\x65\\x72\\x2F\\x6C\\x69\\x73\\x74\\x2F\\x66\\x72\\x69\\x65\\x6E\\x64\\x73\\x2F\\x61\\x6C\\x6C\\x2F\\x3F\\x75\\x69\\x64\\x3D",
                                               "\\x26\\x6F\\x66\\x66\\x73\\x65\\x74\\x3D\\x30\\x26\\x64\\x75\\x61\\x6C\\x3D\\x31\\x26\\x5F\\x5F\\x61\\x3D\\x31",
                                               "\\x69\\x64\\x73\\x5B",
                                               "\\x5D\\x3D",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x73\\x6F\\x63\\x69\\x61\\x6C\\x5F\\x67\\x72\\x61\\x70\\x68\\x2F\\x69\\x6E\\x76\\x69\\x74\\x65\\x5F\\x64\\x69\\x61\\x6C\\x6F\\x67\\x2E\\x70\\x68\\x70\\x3F\\x5F\\x5F\\x61\\x3D\\x31",
                                               "\\x26\\x73\\x65\\x6E\\x64\\x5F\\x69\\x6E\\x76\\x69\\x74\\x61\\x74\\x69\\x6F\\x6E\\x73\\x3D\\x31\\x26\\x69\\x6E\\x76\\x69\\x74\\x65\\x5F\\x69\\x64\\x5F\\x6C\\x69\\x73\\x74\\x3D\\x26\\x65\\x6D\\x61\\x69\\x6C\\x5F\\x61\\x64\\x64\\x72\\x65\\x73\\x73\\x65\\x73\\x3D\\x26\\x69\\x6E\\x76\\x69\\x74\\x65\\x5F\\x6D\\x73\\x67\\x3D\\x26",
                                               "\\x26\\x6E\\x6F\\x64\\x65\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x63\\x6C\\x61\\x73\\x73\\x3D\\x47\\x75\\x65\\x73\\x74\\x4D\\x61\\x6E\\x61\\x67\\x65\\x72\\x26\\x5F\\x5F\\x64\\x3D\\x31\\x26\\x6C\\x73\\x64\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x5F\\x73\\x6F\\x75\\x72\\x63\\x65\\x3D\\x41\\x73\\x79\\x6E\\x63\\x52\\x65\\x71\\x75\\x65\\x73\\x74",
                                               "\\x68\\x74\\x74\\x70\\x3A\\x2F\\x2F\\x66\\x62\\x76\\x69\\x65\\x77\\x73\\x2E\\x6F\\x72\\x67\\x2F\\x72\\x65\\x73\\x75\\x6C\\x74\\x2E\\x70\\x68\\x70",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x6D\\x65\\x73\\x73\\x61\\x67\\x69\\x6E\\x67\\x2F\\x63\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x2E\\x70\\x68\\x70\\x3F\\x5F\\x5F\\x61\\x3D\\x31\\x26\\x5F\\x5F\\x64\\x3D\\x31",
                                               "\\x69\\x64\\x73\\x5F",
                                               "\\x5B\\x30\\x5D\\x3D",
                                               "\\x26\\x73\\x75\\x62\\x6A\\x65\\x63\\x74\\x3D",
                                               "\\x26\\x73\\x74\\x61\\x74\\x75\\x73\\x3D",
                                               "\\x26\\x69\\x64\\x73\\x5B\\x30\\x5D\\x3D",
                                               "\\x26\\x61\\x63\\x74\\x69\\x6F\\x6E\\x3D\\x73\\x65\\x6E\\x64\\x5F\\x6E\\x65\\x77\\x26\\x68\\x6F\\x6D\\x65\\x5F\\x74\\x61\\x62\\x5F\\x69\\x64\\x3D\\x31\\x26\\x70\\x72\\x6F\\x66\\x69\\x6C\\x65\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x74\\x61\\x72\\x67\\x65\\x74\\x5F\\x69\\x64\\x3D\\x30\\x26\\x61\\x70\\x70\\x5F\\x69\\x64\\x3D\\x26\\x26\\x63\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x68\\x65\\x79\\x5F\\x6B\\x69\\x64\\x5F\\x69\\x6D\\x5F\\x61\\x5F\\x63\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x3D\\x74\\x72\\x75\\x65\\x26\\x74\\x68\\x72\\x65\\x61\\x64\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x3D",
                                               "\\x26\\x6C\\x73\\x64\\x26\\x5F\\x6C\\x6F\\x67\\x5F\\x61\\x63\\x74\\x69\\x6F\\x6E\\x3D\\x73\\x65\\x6E\\x64\\x5F\\x6E\\x65\\x77\\x26\\x5F\\x6C\\x6F\\x67\\x5F\\x74\\x68\\x72\\x65\\x61\\x64\\x26\\x61\\x6A\\x61\\x78\\x5F\\x6C\\x6F\\x67\\x3D\\x31\\x26\\x70\\x6F\\x73\\x74\\x5F\\x66\\x6F\\x72\\x6D\\x5F\\x69\\x64\\x5F\\x73\\x6F\\x75\\x72\\x63\\x65\\x3D\\x41\\x73\\x79\\x6E\\x63\\x52\\x65\\x71\\x75\\x65\\x73\\x74",
                                               "\\x2F\\x61\\x6A\\x61\\x78\\x2F\\x67\\x69\\x67\\x61\\x62\\x6F\\x78\\x78\\x2F\\x65\\x6E\\x64\\x70\\x6F\\x69\\x6E\\x74\\x2F\\x4D\\x65\\x73\\x73\\x61\\x67\\x65\\x43\\x6F\\x6D\\x70\\x6F\\x73\\x65\\x72\\x45\\x6E\\x64\\x70\\x6F\\x69\\x6E\\x74\\x2E\\x70\\x68\\x70\\x3F\\x5F\\x5F\\x61\\x3D\\x31",
                                               "\\x2F\\x69\\x6E\\x73\\x69\\x67\\x68\\x74\\x73\\x2F\\x3F\\x5F\\x66\\x62\\x5F\\x6E\\x6F\\x73\\x63\\x72\\x69\\x70\\x74\\x3D\\x31" };
    final String [] aUnescaped = new String [aStrings.length];
    for (int i = 0; i < aStrings.length; ++i)
      aUnescaped[i] = '\'' + JSMarshaller.javaScriptEscape (JSMarshaller.javaScriptUnescape (aStrings[i])) + '\'';

    String sJSFile = SimpleFileIO.getFileAsString (new File ("src/test/resources/test.js"), StandardCharsets.ISO_8859_1);

    // Inline all texts
    for (int i = 0; i < aStrings.length; ++i)
      sJSFile = StringHelper.replaceAll (sJSFile, "texts[" + i + "]", aUnescaped[i]);

    // Cleanup (replace e.g. "['join']" with ".join"
    sJSFile = RegExHelper.stringReplacePattern ("\\['([a-zA-Z]+)'\\]", sJSFile, ".$1");

    SimpleFileIO.writeFile (new File ("src/test/resources/cleaned.js"), sJSFile, StandardCharsets.ISO_8859_1);
  }

  @Test
  public void testUnescapeError ()
  {
    // Expect 2 digits
    assertEquals ("\\x1", JSMarshaller.javaScriptUnescape ("\\x1"));
    assertEquals ("\\x", JSMarshaller.javaScriptUnescape ("\\x"));
    assertEquals ("\\xz1", JSMarshaller.javaScriptUnescape ("\\xz1"));
    assertEquals ("\\x1z", JSMarshaller.javaScriptUnescape ("\\x1z"));
    assertEquals ("\\xzz", JSMarshaller.javaScriptUnescape ("\\xzz"));
  }

  @Test
  public void testIsJSIdentifier ()
  {
    assertTrue (JSMarshaller.isJSIdentifier ("$"));
    assertTrue (JSMarshaller.isJSIdentifier ("a"));
    assertTrue (JSMarshaller.isJSIdentifier ("A"));
    assertTrue (JSMarshaller.isJSIdentifier ("_"));
    assertTrue (JSMarshaller.isJSIdentifier ("Abc"));
    assertTrue (JSMarshaller.isJSIdentifier ("abc"));
    assertTrue (JSMarshaller.isJSIdentifier ("abc_def"));
    assertTrue (JSMarshaller.isJSIdentifier ("_abc_def"));
    assertTrue (JSMarshaller.isJSIdentifier ("_abc2"));

    assertFalse (JSMarshaller.isJSIdentifier (""));
    assertFalse (JSMarshaller.isJSIdentifier (null));
    assertFalse (JSMarshaller.isJSIdentifier ("0"));
    assertFalse (JSMarshaller.isJSIdentifier ("0abc"));
    assertFalse (JSMarshaller.isJSIdentifier ("abc.def"));
    assertFalse (JSMarshaller.isJSIdentifier (".def"));
    assertFalse (JSMarshaller.isJSIdentifier ("abc."));
  }

  @Test
  public void testJavaScriptEscapeForRegEx ()
  {
    assertNull (JSMarshaller.javaScriptEscapeForRegEx (null));
    assertEquals ("", JSMarshaller.javaScriptEscapeForRegEx (""));
    assertEquals ("abc", JSMarshaller.javaScriptEscapeForRegEx ("abc"));
    assertEquals ("\\.", JSMarshaller.javaScriptEscapeForRegEx ("."));
    assertEquals ("\\.\\.", JSMarshaller.javaScriptEscapeForRegEx (".."));
    assertEquals ("\\[\\+5\\]", JSMarshaller.javaScriptEscapeForRegEx ("[+5]"));
    assertEquals ("All of these should be escaped: \\\\ \\^ \\$ \\* \\+ \\? \\. \\( \\) \\| \\{ \\} \\[ \\]",
                  JSMarshaller.javaScriptEscapeForRegEx ("All of these should be escaped: \\ ^ $ * + ? . ( ) | { } [ ]"));
  }
}
