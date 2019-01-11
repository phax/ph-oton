/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class keeps a list of {@link PathDescriptorPart} objects that are
 * created when initially parsing an API path. It consists of plain string parts
 * as well as of dynamic (variable) parts.
 *
 * @author Philip Helger
 */
@Immutable
public final class PathDescriptor
{
  private final ICommonsList <PathDescriptorPart> m_aPathParts = new CommonsArrayList<> ();

  private PathDescriptor (@Nonnull @Nonempty final List <String> aPathParts)
  {
    ValueEnforcer.notEmpty (aPathParts, "PathParts");
    for (final String sPathPart : aPathParts)
      m_aPathParts.add (PathDescriptorPart.create (sPathPart));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <PathDescriptorPart> getAllParts ()
  {
    return m_aPathParts.getClone ();
  }

  /**
   * Check if this path descriptor matches the provided path parts. This
   * requires that this path descriptor and the provided collection have the
   * same number of elements and that all static and variable parts match.
   *
   * @param aPathParts
   *        The parts to
   * @return A non-<code>null</code> {@link PathMatchingResult} object with all
   *         matching variable names.
   */
  @Nonnull
  public PathMatchingResult matchesParts (@Nonnull final List <String> aPathParts)
  {
    ValueEnforcer.notNull (aPathParts, "PathParts");
    final int nPartCount = m_aPathParts.size ();
    if (aPathParts.size () != nPartCount)
    {
      // Size must match
      return PathMatchingResult.NO_MATCH;
    }

    final ICommonsOrderedMap <String, String> aVariableValues = new CommonsLinkedHashMap<> ();
    for (int i = 0; i < nPartCount; ++i)
    {
      final PathDescriptorPart aPart = m_aPathParts.get (i);
      final String sPathPart = aPathParts.get (i);
      if (!aPart.matches (sPathPart))
      {
        // Current part does not match - full error
        return PathMatchingResult.NO_MATCH;
      }

      // Matching variable part?
      if (aPart.isVariable ())
        aVariableValues.put (aPart.getName (), sPathPart);
    }

    // We've got it!
    return PathMatchingResult.createSuccess (aVariableValues);
  }

  @Nonnull
  @Nonempty
  public String getAsURLString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final PathDescriptorPart aPart : m_aPathParts)
      aSB.append ('/').append (aPart.getAsURLString ());
    return aSB.toString ();
  }

  public boolean containsVariables ()
  {
    return m_aPathParts.containsAny (aPart -> aPart.isVariable ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PathDescriptor rhs = (PathDescriptor) o;
    return m_aPathParts.equals (rhs.m_aPathParts);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aPathParts).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("PathParts", m_aPathParts).getToString ();
  }

  @Nonnull
  public static PathDescriptor create (@Nonnull @Nonempty final String sPath)
  {
    ValueEnforcer.notEmpty (sPath, "Path");

    // Split into pieces
    final ICommonsList <String> aPathParts = PathDescriptorHelper.getCleanPathParts (sPath);
    return new PathDescriptor (aPathParts);
  }
}
