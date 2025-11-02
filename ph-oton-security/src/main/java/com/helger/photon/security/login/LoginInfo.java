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
package com.helger.photon.security.login;

import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.id.IHasID;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.datetime.helper.PDTFactory;
import com.helger.photon.security.user.IUser;
import com.helger.scope.ISessionScope;
import com.helger.typeconvert.collection.IStringMap;
import com.helger.typeconvert.collection.StringMap;

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

  public LoginInfo (@NonNull final IUser aUser, @NonNull final ISessionScope aSessionScope)
  {
    m_aUser = ValueEnforcer.notNull (aUser, "User");
    m_aSessionScope = ValueEnforcer.notNull (aSessionScope, "SessionScope");
    m_aLoginDT = PDTFactory.getCurrentLocalDateTime ();
    m_aLastAccessDT = m_aLoginDT;
  }

  /**
   * @return The user to which this login info belongs. Never <code>null</code>.
   */
  @NonNull
  public IUser getUser ()
  {
    return m_aUser;
  }

  /**
   * @return The session scope to which this login info belongs. Never
   *         <code>null</code>.
   */
  @NonNull
  public ISessionScope getSessionScope ()
  {
    return m_aSessionScope;
  }

  /**
   * @return The ID of the user to which this login info belongs. Neither
   *         <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public String getUserID ()
  {
    return m_aUser.getID ();
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return getUserID ();
  }

  /**
   * @return The login data and time. Never <code>null</code>.
   */
  @NonNull
  public LocalDateTime getLoginDT ()
  {
    return m_aLoginDT;
  }

  /**
   * @return The last access data and time. Never <code>null</code>.
   */
  @NonNull
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

  @NonNull
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
