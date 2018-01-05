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
package com.helger.photon.security.role;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.object.StubObject;
import com.helger.tenancy.AbstractBusinessObject;

/**
 * Default implementation of the {@link IRole} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class Role extends AbstractBusinessObject implements IRole
{
  public static final ObjectType OT = new ObjectType ("role");

  private String m_sName;
  private String m_sDescription;

  public Role (@Nonnull @Nonempty final String sName,
               @Nullable final String sDescription,
               @Nullable final Map <String, String> aCustomAttrs)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs), sName, sDescription);
  }

  protected Role (@Nonnull final StubObject aStubObject,
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
  protected EChange setName (@Nonnull @Nonempty final String sName)
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
  protected EChange setDescription (@Nullable final String sDescription)
  {
    if (EqualsHelper.equals (sDescription, m_sDescription))
      return EChange.UNCHANGED;
    m_sDescription = sDescription;
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("name", m_sName)
                            .appendIfNotNull ("description", m_sDescription)
                            .getToString ();
  }
}
