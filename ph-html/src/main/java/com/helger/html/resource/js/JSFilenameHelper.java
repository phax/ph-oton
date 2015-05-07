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
package com.helger.html.resource.js;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;
import com.helger.html.js.CJS;

/**
 * Utility class for handling JavaScript file names.
 * 
 * @author Philip Helger
 */
@Immutable
public final class JSFilenameHelper
{
  private JSFilenameHelper ()
  {}

  public static boolean isJSFilename (@Nullable final String sFilename)
  {
    return StringHelper.endsWith (sFilename, CJS.FILE_EXTENSION_JS);
  }

  public static boolean isMinifiedJSFilename (@Nullable final String sFilename)
  {
    return StringHelper.endsWith (sFilename, CJS.FILE_EXTENSION_MIN_JS);
  }

  public static boolean isRegularJSFilename (@Nullable final String sFilename)
  {
    return isJSFilename (sFilename) && !isMinifiedJSFilename (sFilename);
  }

  @Nonnull
  public static String getMinifiedJSPath (@Nonnull final String sJSFilename)
  {
    if (!isJSFilename (sJSFilename))
      throw new IllegalArgumentException ("Passed file name '" + sJSFilename + "' is not a JS file name!");
    if (isMinifiedJSFilename (sJSFilename))
      return sJSFilename;
    return StringHelper.trimEnd (sJSFilename, CJS.FILE_EXTENSION_JS) + CJS.FILE_EXTENSION_MIN_JS;
  }
}
