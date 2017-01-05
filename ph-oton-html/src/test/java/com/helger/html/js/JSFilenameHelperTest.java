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
package com.helger.html.js;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for class {@link JSFilenameHelper}.
 *
 * @author Philip Helger
 */
public final class JSFilenameHelperTest
{
  @Test
  public void testAll ()
  {
    final String s1 = "dir/a.js";
    final String s2 = "dir/a.min.js";

    assertTrue (JSFilenameHelper.isJSFilename (s1));
    assertTrue (JSFilenameHelper.isJSFilename (s2));
    assertFalse (JSFilenameHelper.isJSFilename ("anydir/otherfile.txt"));

    assertFalse (JSFilenameHelper.isMinifiedJSFilename (s1));
    assertTrue (JSFilenameHelper.isMinifiedJSFilename (s2));
    assertFalse (JSFilenameHelper.isMinifiedJSFilename ("anydir/otherfile.txt"));

    assertTrue (JSFilenameHelper.isRegularJSFilename (s1));
    assertFalse (JSFilenameHelper.isRegularJSFilename (s2));
    assertFalse (JSFilenameHelper.isRegularJSFilename ("anydir/otherfile.txt"));

    assertEquals (s2, JSFilenameHelper.getMinifiedJSFilename (s1));
    assertEquals (s2, JSFilenameHelper.getMinifiedJSFilename (s2));
    try
    {
      JSFilenameHelper.getMinifiedJSFilename ("anydir/otherfile.txt");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }
}
