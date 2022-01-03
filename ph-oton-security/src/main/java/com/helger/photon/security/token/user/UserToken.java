/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.object.AbstractObjectWithAccessToken;
import com.helger.photon.security.user.IUser;

/**
 * A single token for granting a machine user access to this application.
 *
 * @author Philip Helger
 */
public class UserToken extends AbstractObjectWithAccessToken implements IUserToken
{
  public static final ObjectType OT = new ObjectType ("usertoken");

  private final IUser m_aUser;

  public UserToken (@Nullable final String sTokenString, @Nullable final Map <String, String> aCustomAttrs, @Nonnull final IUser aUser)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs),
          new CommonsArrayList <> (AccessToken.createAccessTokenValidFromNow (sTokenString)),
          aUser);
  }

  UserToken (@Nonnull final StubObject aStubObject, @Nonnull @Nonempty final List <AccessToken> aAccessTokens, @Nonnull final IUser aUser)
  {
    super (aStubObject, aAccessTokens);
    m_aUser = ValueEnforcer.notNull (aUser, "User");
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_aUser.getDisplayName ();
  }

  @Nonnull
  public IUser getUser ()
  {
    return m_aUser;
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
    return ToStringGenerator.getDerived (super.toString ()).append ("User", m_aUser).getToString ();
  }
}
