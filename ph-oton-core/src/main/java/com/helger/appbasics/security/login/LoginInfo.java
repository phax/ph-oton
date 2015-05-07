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
package com.helger.appbasics.security.login;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.joda.time.DateTime;

import com.helger.appbasics.security.user.IUser;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.attrs.MapBasedAttributeContainer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.id.IHasID;
import com.helger.commons.scopes.domain.ISessionScope;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;

/**
 * Represents the information of a single logged in user.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public final class LoginInfo extends MapBasedAttributeContainer implements IHasID <String>
{
  private final IUser m_aUser;
  private final ISessionScope m_aSessionScope;
  private final DateTime m_aLoginDT;
  private DateTime m_aLastAccessDT;
  private DateTime m_aLogoutDT;

  LoginInfo (@Nonnull final IUser aUser, @Nonnull final ISessionScope aSessionScope)
  {
    m_aUser = ValueEnforcer.notNull (aUser, "User");
    m_aSessionScope = ValueEnforcer.notNull (aSessionScope, "SessionScope");
    m_aLoginDT = PDTFactory.getCurrentDateTime ();
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
  public DateTime getLoginDT ()
  {
    return m_aLoginDT;
  }

  /**
   * @return The last access data and time. Never <code>null</code>.
   */
  @Nonnull
  public DateTime getLastAccessDT ()
  {
    return m_aLastAccessDT;
  }

  /**
   * Update the last access date time to the current date and time.
   */
  public void setLastAccessDTNow ()
  {
    m_aLastAccessDT = PDTFactory.getCurrentDateTime ();
  }

  /**
   * @return The date and time when the user logged out. Is <code>null</code>
   *         when the user is still logged in :)
   */
  @Nullable
  public DateTime getLogoutDT ()
  {
    return m_aLogoutDT;
  }

  /**
   * Update the logout date time to the current date and time.
   */
  void setLogoutDTNow ()
  {
    m_aLogoutDT = PDTFactory.getCurrentDateTime ();
  }

  /**
   * @return <code>true</code> if this LoginInfo refers to an already logged out
   *         user.
   */
  public boolean isLogout ()
  {
    return m_aLogoutDT != null;
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
           EqualsUtils.equals (m_aLogoutDT, rhs.m_aLogoutDT);
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
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("user", m_aUser)
                            .append ("sessionScopeID", m_aSessionScope.getID ())
                            .append ("loginDT", m_aLoginDT)
                            .append ("lastAccessDT", m_aLastAccessDT)
                            .appendIfNotNull ("logoutDT", m_aLogoutDT)
                            .toString ();
  }
}
