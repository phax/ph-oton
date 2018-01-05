/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.core.api.pathdescriptor;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.string.StringHelper;

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
   * Clean the provided path and split it into parts.
   *
   * @param sPath
   *        Source path
   * @return The list with all path parts. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <String> getCleanPathParts (@Nonnull final String sPath)
  {
    // Remove leading and trailing whitespaces and slashes
    String sRealPath = StringHelper.trimStartAndEnd (sPath.trim (), "/", "/");

    // Remove obscure path parts
    sRealPath = FilenameHelper.getCleanPath (sRealPath);

    // Split into pieces
    final ICommonsList <String> aPathParts = StringHelper.getExploded ('/', sRealPath);
    return aPathParts;
  }
}
