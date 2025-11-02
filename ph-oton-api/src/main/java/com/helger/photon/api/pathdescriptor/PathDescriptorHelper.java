/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.api.pathdescriptor;

import java.util.List;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.string.StringHelper;
import com.helger.io.file.FilenameHelper;

/**
 * Utility class for API path handling.
 *
 * @author Philip Helger
 */
@Immutable
public final class PathDescriptorHelper
{
  private PathDescriptorHelper ()
  {}

  /**
   * Clean the provided path and split it into parts, separated by slashes.
   *
   * @param sPath
   *        Source path. May not be <code>null</code>.
   * @return The list with all path parts. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  public static List <String> getCleanPathParts (@NonNull final String sPath)
  {
    // Remove leading and trailing whitespaces and slashes
    String sRealPath = StringHelper.trimStartAndEnd (sPath.trim (), "/", "/");

    // Remove obscure path parts
    sRealPath = FilenameHelper.getCleanPath (sRealPath);

    // Split into pieces
    return StringHelper.getExploded ('/', sRealPath);
  }
}
