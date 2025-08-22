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
package com.helger.photon.security.role;

import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.photon.security.object.StubObject;
import com.helger.tenancy.AbstractBusinessObject;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Default implementation of the {@link IRole} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class Role extends AbstractBusinessObject implements IRole
{
  public static final ObjectType OT = new ObjectType ("role");

  private String m_sName;
  private String m_sDescription;

  /**
   * Constructor for new object.
   *
   * @param sName
   *        Role name. May neither be <code>null</code> nor empty.
   * @param sDescription
   *        Role description. May be <code>null</code>.
   * @param aCustomAttrs
   *        Optional attributes. May be <code>null</code>.
   */
  public Role (@Nonnull @Nonempty final String sName,
               @Nullable final String sDescription,
               @Nullable final Map <String, String> aCustomAttrs)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs), sName, sDescription);
  }

  /**
   * Constructor for deserialization.
   *
   * @param aStubObject
   *        The base data. May not be <code>null</code>.
   * @param sName
   *        Role name. May neither be <code>null</code> nor empty.
   * @param sDescription
   *        Role description. May be <code>null</code>.
   */
  public Role (@Nonnull final StubObject aStubObject,
               @Nonnull @Nonempty final String sName,
               @Nullable final String sDescription)
  {
    super (aStubObject);
    setName (sName);
    setDescription (sDescription);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return Role.OT;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public EChange setName (@Nonnull @Nonempty final String sName)
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
  public EChange setDescription (@Nullable final String sDescription)
  {
    if (EqualsHelper.equals (sDescription, m_sDescription))
      return EChange.UNCHANGED;
    m_sDescription = sDescription;
    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    // New fields, no changes
    return super.equals (o);
  }

  @Override
  public int hashCode ()
  {
    // New fields, no changes
    return super.hashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Name", m_sName)
                            .appendIfNotNull ("Description", m_sDescription)
                            .getToString ();
  }
}
