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
package com.helger.photon.api.pathdescriptor;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class keeps a single path part. This can be either a static string or a
 * variable argument with optional constraints.
 *
 * @author Philip Helger
 */
@Immutable
public final class PathDescriptorPart implements Serializable
{
  public static final String VARIABLE_START = "{";
  public static final String VARIABLE_END = "}";
  public static final char CONSTRAINT_SEPARATOR = ':';
  private static final int MIN_VARIABLE_LENGTH = VARIABLE_START.length () + VARIABLE_END.length ();

  private final boolean m_bIsVariable;
  private final String m_sName;
  private final ICommonsList <PathDescriptorVariableConstraint> m_aVariableConstraints;

  private PathDescriptorPart (final boolean bIsVariable,
                              @Nonnull @Nonempty final String sName,
                              @Nullable final ICommonsList <PathDescriptorVariableConstraint> aVariableConstraints)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    m_bIsVariable = bIsVariable;
    m_sName = sName;
    m_aVariableConstraints = aVariableConstraints;
  }

  /**
   * @return <code>true</code> if this is variable path part, <code>false</code>
   *         if it is static
   */
  public boolean isVariable ()
  {
    return m_bIsVariable;
  }

  /**
   * @return Either the static string or the name of the variable. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  /**
   * @return The optional variable constraints. Only relevant if this is a
   *         variable path part. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <PathDescriptorVariableConstraint> getAllVariableConstraints ()
  {
    return new CommonsArrayList <> (m_aVariableConstraints);
  }

  /**
   * Check if the passed path part matches the requirements of this object. If
   * this is a static path part, it's a simple string equals. For variable path
   * parts, all the constraints (if any) must be fulfilled.
   *
   * @param sPathPart
   *        The string to be checked.
   * @return <code>true</code> if the passed string matches, <code>false</code>
   *         otherwise.
   */
  public boolean matches (@Nonnull final String sPathPart)
  {
    if (m_bIsVariable)
    {
      // Check additional constraints
      if (m_aVariableConstraints != null)
        for (final PathDescriptorVariableConstraint aVariableConstraint : m_aVariableConstraints)
          if (!aVariableConstraint.matches (sPathPart))
            return false;

      // Variables match if all constraints match
      return true;
    }

    // Static path part - string equals
    return m_sName.equals (sPathPart);
  }

  @Nonnull
  @Nonempty
  public String getAsURLString ()
  {
    if (m_bIsVariable)
    {
      final StringBuilder aSB = new StringBuilder ();
      aSB.append (VARIABLE_START).append (m_sName);
      if (m_aVariableConstraints != null)
        for (final PathDescriptorVariableConstraint aVariableConstraint : m_aVariableConstraints)
          aSB.append (CONSTRAINT_SEPARATOR).append (aVariableConstraint.getAsURLString ());
      aSB.append (VARIABLE_END);
      return aSB.toString ();
    }

    // Static :)
    return m_sName;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PathDescriptorPart rhs = (PathDescriptorPart) o;
    return m_bIsVariable == rhs.m_bIsVariable &&
           m_sName.equals (rhs.m_sName) &&
           EqualsHelper.equals (m_aVariableConstraints, rhs.m_aVariableConstraints);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_bIsVariable)
                                       .append (m_sName)
                                       .append (m_aVariableConstraints)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("IsVariable", m_bIsVariable)
                                       .append ("Name", m_sName)
                                       .appendIf ("VariableConstraints",
                                                  m_aVariableConstraints,
                                                  CollectionHelper::isNotEmpty)
                                       .getToString ();
  }

  @Nonnull
  @Nonempty
  public static String getVariableName (@Nonnull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.isFalse ( () -> sName.startsWith (VARIABLE_START),
                            () -> "Name is already a variable: '" + sName + "'");
    return VARIABLE_START + sName + VARIABLE_END;
  }

  @Nonnull
  public static PathDescriptorPart create (@Nonnull @Nonempty final String sPathPart)
  {
    ValueEnforcer.notEmpty (sPathPart, "PathPart");
    if (sPathPart.length () > MIN_VARIABLE_LENGTH &&
        sPathPart.startsWith (VARIABLE_START) &&
        sPathPart.endsWith (VARIABLE_END))
    {
      // It's a variable

      // Remove "{" and "}"
      final String sVariablePart = sPathPart.substring (VARIABLE_START.length (),
                                                        sPathPart.length () - VARIABLE_END.length ());

      // Check for additional variable constraints
      // Example: {path:regex=^[0-9]+$}
      final ICommonsList <String> aVarPartParts = StringHelper.getExploded (CONSTRAINT_SEPARATOR, sVariablePart);

      // Variable name is always first
      final String sVariableName = aVarPartParts.get (0);

      // Constraints come afterwars
      ICommonsList <PathDescriptorVariableConstraint> aVariableConstraints = null;
      final int nVarPartPartCount = aVarPartParts.size ();
      if (nVarPartPartCount > 1)
      {
        // Skip var name
        for (int i = 1; i < nVarPartPartCount; ++i)
        {
          final String sVariableConstraint = aVarPartParts.get (i);
          final PathDescriptorVariableConstraint aConstraint = PathDescriptorVariableConstraint.createOrNull (sVariableConstraint);
          if (aConstraint != null)
          {
            if (aVariableConstraints == null)
              aVariableConstraints = new CommonsArrayList <> ();
            aVariableConstraints.add (aConstraint);
          }
        }
      }

      // It's a variable argument - cut prefix
      return new PathDescriptorPart (true, sVariableName, aVariableConstraints);
    }
    return new PathDescriptorPart (false, sPathPart, null);
  }
}
