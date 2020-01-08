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
package com.helger.photon.core.userdata;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Represents a single web accessible object, that was provided by the user.
 * Think of this as a file descriptor. A {@link AbstractUserDataObject} always
 * resides on the file system and can be accessed by regular file IO.
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractUserDataObject implements IUserDataObject
{
  private final String m_sPath;
  private final boolean m_bIsTemporary;

  public AbstractUserDataObject (@Nonnull @Nonempty final String sPath, final boolean bIsTemporary)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    // Ensure only forward slashes
    m_sPath = FilenameHelper.getPathUsingUnixSeparator (FilenameHelper.ensurePathStartingWithSeparator (sPath));
    m_bIsTemporary = bIsTemporary;
  }

  @Nonnull
  @Nonempty
  public String getPath ()
  {
    return m_sPath;
  }

  public boolean isTemporary ()
  {
    return m_bIsTemporary;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractUserDataObject rhs = (AbstractUserDataObject) o;
    return m_sPath.equals (rhs.m_sPath);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sPath).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("path", m_sPath).append ("isTemporary", m_bIsTemporary).getToString ();
  }
}
