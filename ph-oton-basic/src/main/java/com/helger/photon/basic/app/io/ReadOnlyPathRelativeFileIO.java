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
package com.helger.photon.basic.app.io;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of {@link IPathRelativeIO}.
 *
 * @author Philip Helger
 */
@Immutable
public class ReadOnlyPathRelativeFileIO implements IPathRelativeIO
{
  private final File m_aBasePath;

  public ReadOnlyPathRelativeFileIO (@Nonnull final File aBasePath, final boolean bCheckAccessRights)
  {
    ValueEnforcer.notNull (aBasePath, "BasePath");
    if (!aBasePath.isAbsolute ())
      throw new IllegalArgumentException ("Please provide an absolute path: " + aBasePath);

    // Must be an existing directory (and not e.g. a file)
    if (!aBasePath.isDirectory ())
      throw new InitializationException ("The passed base path " + aBasePath + " exists but is not a directory!");
    m_aBasePath = aBasePath;

    if (bCheckAccessRights)
      PathRelativeFileIO.internalCheckAccessRights (m_aBasePath);
  }

  @Nonnull
  public File getBasePathFile ()
  {
    return m_aBasePath;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ReadOnlyPathRelativeFileIO rhs = (ReadOnlyPathRelativeFileIO) o;
    return m_aBasePath.equals (rhs.m_aBasePath);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aBasePath).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("BasePath", m_aBasePath).getToString ();
  }
}
