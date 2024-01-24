/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.security.login;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.attr.IStringMap;
import com.helger.commons.collection.attr.StringMap;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.id.IHasID;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.security.user.IUser;
import com.helger.scope.ISessionScope;

/**
 * Represents the information of a single logged in user.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class LoginInfo implements IHasID <String>
{
  private final IUser m_aUser;
  private final ISessionScope m_aSessionScope;
  private final LocalDateTime m_aLoginDT;
  private LocalDateTime m_aLastAccessDT;
  private LocalDateTime m_aLogoutDT;
  private final StringMap m_aAttrs = new StringMap ();

  public LoginInfo (@Nonnull final IUser aUser, @Nonnull final ISessionScope aSessionScope)
  {
    m_aUser = ValueEnforcer.notNull (aUser, "User");
    m_aSessionScope = ValueEnforcer.notNull (aSessionScope, "SessionScope");
    m_aLoginDT = PDTFactory.getCurrentLocalDateTime ();
    m_aLastAccessDT = m_aLoginDT;
  }

  /**
   * @return The user to which this login info belongs. Never <code>null</code>.
   */
  @Nonnull
  public IUser getUser ()
  {
    return m_aUser;
  }

  /**
   * @return The session scope to which this login info belongs. Never
   *         <code>null</code>.
   */
  @Nonnull
  public ISessionScope getSessionScope ()
  {
    return m_aSessionScope;
  }

  /**
   * @return The ID of the user to which this login info belongs. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getUserID ()
  {
    return m_aUser.getID ();
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return getUserID ();
  }

  /**
   * @return The login data and time. Never <code>null</code>.
   */
  @Nonnull
  public LocalDateTime getLoginDT ()
  {
    return m_aLoginDT;
  }

  /**
   * @return The last access data and time. Never <code>null</code>.
   */
  @Nonnull
  public LocalDateTime getLastAccessDT ()
  {
    return m_aLastAccessDT;
  }

  /**
   * Update the last access date time to the current date and time.
   */
  public void setLastAccessDTNow ()
  {
    m_aLastAccessDT = PDTFactory.getCurrentLocalDateTime ();
  }

  /**
   * @return The date and time when the user logged out. Is <code>null</code>
   *         when the user is still logged in :)
   */
  @Nullable
  public LocalDateTime getLogoutDT ()
  {
    return m_aLogoutDT;
  }

  /**
   * Update the logout date time to the current date and time.
   */
  public void setLogoutDTNow ()
  {
    m_aLogoutDT = PDTFactory.getCurrentLocalDateTime ();
  }

  /**
   * @return <code>true</code> if this LoginInfo refers to an already logged out
   *         user.
   */
  public boolean isLogout ()
  {
    return m_aLogoutDT != null;
  }

  @Nonnull
  @ReturnsMutableObject
  public IStringMap attrs ()
  {
    return m_aAttrs;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final LoginInfo rhs = (LoginInfo) o;
    return m_aUser.equals (rhs.m_aUser) &&
           m_aSessionScope.getID ().equals (rhs.m_aSessionScope.getID ()) &&
           m_aLoginDT.equals (rhs.m_aLoginDT) &&
           m_aLastAccessDT.equals (rhs.m_aLastAccessDT) &&
           EqualsHelper.equals (m_aLogoutDT, rhs.m_aLogoutDT) &&
           m_aAttrs.equals (rhs.m_aAttrs);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aUser)
                            .append (m_aSessionScope.getID ())
                            .append (m_aLoginDT)
                            .append (m_aLastAccessDT)
                            .append (m_aLogoutDT)
                            .append (m_aAttrs)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("User", m_aUser)
                            .append ("SessionScopeID", m_aSessionScope.getID ())
                            .append ("LoginDT", m_aLoginDT)
                            .append ("LastAccessDT", m_aLastAccessDT)
                            .appendIfNotNull ("LogoutDT", m_aLogoutDT)
                            .append ("Attrs", m_aAttrs)
                            .getToString ();
  }
}
