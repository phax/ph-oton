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
package com.helger.photon.core.api.pathdescriptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class keeps a single constraint that maybe used in a
 * {@link PathDescriptorPart} object.
 *
 * @author Philip Helger
 */
public final class PathDescriptorVariableConstraint
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PathDescriptorVariableConstraint.class);

  private final EPathDescriptorVariableConstraintType m_eConstraintType;
  private final String m_sConstraintValue;

  private PathDescriptorVariableConstraint (@Nonnull final EPathDescriptorVariableConstraintType eConstraintType,
                                            @Nullable final String sConstraintValue)
  {
    m_eConstraintType = ValueEnforcer.notNull (eConstraintType, "ConstraintType");
    m_sConstraintValue = sConstraintValue;
  }

  @Nonnull
  public EPathDescriptorVariableConstraintType getConstraintType ()
  {
    return m_eConstraintType;
  }

  @Nullable
  public String getConstraintValue ()
  {
    return m_sConstraintValue;
  }

  public boolean matches (final String sPathPart)
  {
    switch (m_eConstraintType)
    {
      case REGEX:
        return RegExHelper.stringMatchesPattern (m_sConstraintValue, sPathPart);
      default:
        throw new IllegalStateException ("Constraint type " + m_eConstraintType + " is not implemented yet!");
    }
  }

  @Nonnull
  @Nonempty
  public String getAsURLString ()
  {
    if (m_eConstraintType.isRequiresValue ())
      return m_eConstraintType.getID () + '=' + m_sConstraintValue;

    return m_eConstraintType.getID ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PathDescriptorVariableConstraint rhs = (PathDescriptorVariableConstraint) o;
    return m_eConstraintType.equals (rhs.m_eConstraintType) &&
           EqualsHelper.equals (m_sConstraintValue, rhs.m_sConstraintValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eConstraintType).append (m_sConstraintValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("ConstraintType", m_eConstraintType)
                                       .appendIfNotNull ("ConstraintValue", m_sConstraintValue)
                                       .toString ();
  }

  /**
   * Factory method. Tries to split the string of the form <code>x[=y]</code>
   * where "x" is the constraint type and "y" is the constraint value. All
   * possible constraint types are located in
   * {@link EPathDescriptorVariableConstraintType}. If the constraint type
   * requires no value the "y" part may be omitted.
   *
   * @param sConstraint
   *        Constraint to be parsed.
   * @return <code>null</code> if the passed constraint string could not be
   *         parsed.
   */
  @Nullable
  public static PathDescriptorVariableConstraint createOrNull (@Nonnull final String sConstraint)
  {
    final String sRealValue = StringHelper.trim (sConstraint);
    if (StringHelper.hasNoText (sRealValue))
    {
      s_aLogger.warn ("Empty path descriptor variable constraint is ignored!");
      return null;
    }

    // Split in type and value
    final ICommonsList <String> aParts = StringHelper.getExploded ('=', sConstraint, 2);

    // Mandatory type
    final String sConstraintType = aParts.getAtIndex (0);
    final EPathDescriptorVariableConstraintType eConstraintType = EPathDescriptorVariableConstraintType.getFromIDOrNull (sConstraintType);
    if (eConstraintType == null)
    {
      s_aLogger.error ("Unsupported variable constraint type '" + sConstraintType + "' used!");
      return null;
    }

    // Optional value
    final String sConstraintValue = aParts.getAtIndex (1);
    if (eConstraintType.isRequiresValue () && StringHelper.hasNoText (sConstraintValue))
    {
      s_aLogger.error ("Variable constraint type '" +
                       sConstraintType +
                       "' requires a value but no value provided! Separate type and value with a '=' character.");
      return null;
    }

    return new PathDescriptorVariableConstraint (eConstraintType, sConstraintValue);
  }
}
