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
package com.helger.html.js;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;

/**
 * Utility class for handling JavaScript file names.
 *
 * @author Philip Helger
 */
@Immutable
public final class JSFilenameHelper
{
  private static final String JS_IN_URL = CJS.FILE_EXTENSION_JS + "?";
  private static final String JS_IN_URL2 = CJS.FILE_EXTENSION_JS + "#";
  private static final String MIN_JS_IN_URL = CJS.FILE_EXTENSION_MIN_JS + "?";
  private static final String MIN_JS_IN_URL2 = CJS.FILE_EXTENSION_MIN_JS + "#";

  private JSFilenameHelper ()
  {}

  /**
   * Check if the passed filename is a JS filename. Either it ends with ".js" or
   * it contains ".js?" or ".js#" (both for URLs). Please note that every
   * minified JS filename is also a "valid" filename in the sense of this
   * method. That's why there is a {@link #isRegularJSFilename(String)} method
   * to check for non-minified JS filenames explicitly.
   *
   * @param sFilename
   *        Filename to check. May be <code>null</code>.
   * @return <code>true</code> if it is a valid JS filename.
   */
  public static boolean isJSFilename (@Nullable final String sFilename)
  {
    if (StringHelper.hasNoText (sFilename))
      return false;

    // Second check for URLs with parameters
    return sFilename.endsWith (CJS.FILE_EXTENSION_JS) || sFilename.contains (JS_IN_URL) || sFilename.contains (JS_IN_URL2);
  }

  /**
   * Check if the passed filename is a minified JS filename. Either it ends with
   * ".min.js" or it contains ".min.js?" or ".min.js#" (both for URLs). Please
   * note that every minified JS filename is also a "valid" filename in the
   * sense of {@link #isJSFilename(String)} but not vice versa!.
   *
   * @param sFilename
   *        Filename to check. May be <code>null</code>.
   * @return <code>true</code> if it is a valid minified JS filename.
   */
  public static boolean isMinifiedJSFilename (@Nullable final String sFilename)
  {
    if (StringHelper.hasNoText (sFilename))
      return false;

    // Second check for URLs with parameters
    return sFilename.endsWith (CJS.FILE_EXTENSION_MIN_JS) || sFilename.contains (MIN_JS_IN_URL) || sFilename.contains (MIN_JS_IN_URL2);
  }

  /**
   * <code>true</code> if it the passed name is a non-minified JS filename.
   *
   * @param sFilename
   *        filename to check
   * @return <code>true</code> if {@link #isJSFilename(String)} is
   *         <code>true</code> and {@link #isMinifiedJSFilename(String)} is
   *         <code>false</code>.
   * @see #isJSFilename(String)
   * @see #isMinifiedJSFilename(String)
   */
  public static boolean isRegularJSFilename (@Nullable final String sFilename)
  {
    return isJSFilename (sFilename) && !isMinifiedJSFilename (sFilename);
  }

  @Nonnull
  public static String getMinifiedJSFilename (@Nonnull final String sJSFilename)
  {
    if (!isJSFilename (sJSFilename))
      throw new IllegalArgumentException ("Passed file name '" + sJSFilename + "' is not a JS file name!");
    if (isMinifiedJSFilename (sJSFilename))
      return sJSFilename;

    // Replace ".js" with ".min.js"
    // -> works static and in URLs!
    return sJSFilename.replace (CJS.FILE_EXTENSION_JS, CJS.FILE_EXTENSION_MIN_JS);
  }
}
