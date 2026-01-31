/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.user.credentials;

import java.util.Collection;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsCollection;
import com.helger.collection.commons.ICommonsList;
import com.helger.photon.security.user.IUser;

/**
 * Default implementation of the {@link IUserPasswordCredentials} interface.
 *
 * @author Philip Helger
 */
@Immutable
public class UserPasswordCredentials implements IUserPasswordCredentials
{
  private final IUser m_aUser;
  private final String m_sPassword;
  private final ICommonsList <String> m_aRequiredRoles;

  public UserPasswordCredentials (@Nullable final IUser aUser,
                                  @Nullable final String sPassword,
                                  @Nullable final Collection <String> aRequiredRoles)
  {
    m_aUser = aUser;
    m_sPassword = sPassword;
    m_aRequiredRoles = new CommonsArrayList <> (aRequiredRoles);
  }

  @Nullable
  public final IUser getUser ()
  {
    return m_aUser;
  }

  @Nullable
  public final String getPassword ()
  {
    return m_sPassword;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsCollection <String> getAllRequiredRoles ()
  {
    return m_aRequiredRoles.getClone ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final UserPasswordCredentials rhs = (UserPasswordCredentials) o;
    return EqualsHelper.equals (m_aUser, rhs.m_aUser) &&
           EqualsHelper.equals (m_sPassword, rhs.m_sPassword) &&
           m_aRequiredRoles.equals (rhs.m_aRequiredRoles);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aUser).append (m_sPassword).append (m_aRequiredRoles).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("User", m_aUser)
                                       .appendPassword ("Password")
                                       .append ("RequiredRoles", m_aRequiredRoles)
                                       .getToString ();
  }
}
