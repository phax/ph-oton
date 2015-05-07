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
package com.helger.appbasics.app.io;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Standard implementation of {@link IHasFilename} returning a constant but
 * potentially <code>null</code> filename.
 *
 * @author Philip Helger
 */
@Immutable
public class ConstantHasFilename implements IHasFilename
{
  private final String m_sFilename;

  public ConstantHasFilename (@Nullable final String sFilename)
  {
    m_sFilename = StringHelper.trim (sFilename);
  }

  @Nullable
  public String getFilename ()
  {
    return m_sFilename;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ConstantHasFilename rhs = (ConstantHasFilename) o;
    return EqualsUtils.equals (m_sFilename, rhs.m_sFilename);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sFilename).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("filename", m_sFilename).toString ();
  }
}
