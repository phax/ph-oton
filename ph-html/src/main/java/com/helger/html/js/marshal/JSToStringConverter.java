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
package com.helger.html.js.marshal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IJSToStringConverter} interface, using
 * {@link JSMarshaller} to convert an object to a string representation.
 * 
 * @author Philip Helger
 */
@Immutable
public final class JSToStringConverter implements IJSToStringConverter
{
  /** By default a surrounding variable is created */
  public static final boolean DEFAULT_WITH_SURROUNDING_VAR = true;

  private final boolean m_bWithSurroundingVar;

  public JSToStringConverter ()
  {
    this (DEFAULT_WITH_SURROUNDING_VAR);
  }

  public JSToStringConverter (final boolean bWithSurroundingVar)
  {
    m_bWithSurroundingVar = bWithSurroundingVar;
  }

  public boolean isWithSurroundingVar ()
  {
    return m_bWithSurroundingVar;
  }

  @Nullable
  public String objectToJSString (@Nullable final Object aObject, @Nonnull final JSType aType)
  {
    return aObject == null ? null : JSMarshaller.objectToJSString (aObject, aType, m_bWithSurroundingVar);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof JSToStringConverter))
      return false;
    final JSToStringConverter rhs = (JSToStringConverter) o;
    return m_bWithSurroundingVar == rhs.m_bWithSurroundingVar;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_bWithSurroundingVar).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("withVar", m_bWithSurroundingVar).toString ();
  }
}
