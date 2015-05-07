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
package com.helger.appbasics.security.role;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.appbasics.security.CSecurity;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.attrs.MapBasedAttributeContainer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;

/**
 * Default implementation of the {@link IRole} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class Role extends MapBasedAttributeContainer implements IRole
{
  private final String m_sID;
  private String m_sName;
  private String m_sDescription;

  public Role (@Nonnull @Nonempty final String sName,
               @Nullable final String sDescription,
               @Nullable final Map <String, ?> aCustomAttrs)
  {
    this (GlobalIDFactory.getNewPersistentStringID (), sName, sDescription, aCustomAttrs);
  }

  Role (@Nonnull @Nonempty final String sID,
        @Nonnull @Nonempty final String sName,
        @Nullable final String sDescription,
        @Nullable final Map <String, ?> aCustomAttrs)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    setName (sName);
    setDescription (sDescription);
    setAttributes (aCustomAttrs);
  }

  @Nonnull
  public ObjectType getTypeID ()
  {
    return CSecurity.TYPE_ROLE;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  EChange setName (@Nonnull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");

    if (sName.equals (m_sName))
      return EChange.UNCHANGED;
    m_sName = sName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getDescription ()
  {
    return m_sDescription;
  }

  @Nonnull
  EChange setDescription (@Nullable final String sDescription)
  {
    if (EqualsUtils.equals (sDescription, m_sDescription))
      return EChange.UNCHANGED;
    m_sDescription = sDescription;
    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final Role rhs = (Role) o;
    return m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("ID", m_sID)
                            .append ("name", m_sName)
                            .appendIfNotNull ("description", m_sDescription)
                            .toString ();
  }
}
