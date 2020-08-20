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
package com.helger.photon.security.usergroup;

import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.object.StubObject;
import com.helger.tenancy.AbstractBusinessObject;

/**
 * Default implementation of the {@link IUserGroup} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class UserGroup extends AbstractBusinessObject implements IUserGroup
{
  public static final ObjectType OT = new ObjectType ("usergroup");

  private String m_sName;
  private String m_sDescription;
  private final ICommonsSet <String> m_aUserIDs = new CommonsHashSet <> ();
  private final ICommonsSet <String> m_aRoleIDs = new CommonsHashSet <> ();

  public UserGroup (@Nonnull @Nonempty final String sName,
                    @Nullable final String sDescription,
                    @Nullable final Map <String, String> aCustomAttrs)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs), sName, sDescription);
  }

  protected UserGroup (@Nonnull final StubObject aStubObject, @Nonnull @Nonempty final String sName, @Nullable final String sDescription)
  {
    super (aStubObject);
    setName (sName);
    setDescription (sDescription);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return UserGroup.OT;
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

  public boolean hasContainedUsers ()
  {
    return m_aUserIDs.isNotEmpty ();
  }

  @Nonnegative
  public int getContainedUserCount ()
  {
    return m_aUserIDs.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllContainedUserIDs ()
  {
    return m_aUserIDs.getClone ();
  }

  public boolean containsUserID (final String sUserID)
  {
    return m_aUserIDs.contains (sUserID);
  }

  @Nonnull
  protected EChange assignUser (@Nonnull @Nonempty final String sUserID)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");

    return EChange.valueOf (m_aUserIDs.add (sUserID));
  }

  @Nonnull
  protected EChange unassignUser (@Nonnull final String sUserID)
  {
    return EChange.valueOf (m_aUserIDs.remove (sUserID));
  }

  public boolean hasContainedRoles ()
  {
    return m_aRoleIDs.isNotEmpty ();
  }

  @Nonnegative
  public int getContainedRoleCount ()
  {
    return m_aRoleIDs.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllContainedRoleIDs ()
  {
    return m_aRoleIDs.getClone ();
  }

  public boolean containsRoleID (final String sRoleID)
  {
    return m_aRoleIDs.contains (sRoleID);
  }

  @Nonnull
  protected EChange assignRole (@Nonnull @Nonempty final String sRoleID)
  {
    ValueEnforcer.notEmpty (sRoleID, "RoleID");

    return EChange.valueOf (m_aRoleIDs.add (sRoleID));
  }

  @Nonnull
  protected EChange unassignRole (@Nonnull final String sRoleID)
  {
    return EChange.valueOf (m_aRoleIDs.remove (sRoleID));
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("name", m_sName)
                            .appendIfNotNull ("description", m_sDescription)
                            .append ("assignedUsers", m_aUserIDs)
                            .append ("assignedRoles", m_aRoleIDs)
                            .getToString ();
  }
}
