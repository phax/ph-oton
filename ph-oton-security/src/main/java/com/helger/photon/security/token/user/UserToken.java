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
package com.helger.photon.security.token.user;

import java.util.List;
import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.object.AbstractObjectWithAccessToken;
import com.helger.photon.security.user.IUser;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A single token for granting a machine user access to this application.
 *
 * @author Philip Helger
 */
public class UserToken extends AbstractObjectWithAccessToken implements IUserToken
{
  public static final ObjectType OT = new ObjectType ("usertoken");

  private final IUser m_aUser;
  private String m_sDescription;

  /**
   * Constructor for new object.
   *
   * @param sTokenString
   *        The token string to be used. May be <code>null</code> in which case a new one is
   *        created.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param aUser
   *        Related user for which this token can be used. May not be <code>null</code>.
   * @param sDescription
   *        Optional description of the User Token. May be <code>null</code>.
   */
  public UserToken (@Nullable final String sTokenString,
                    @Nullable final Map <String, String> aCustomAttrs,
                    @Nonnull final IUser aUser,
                    @Nullable final String sDescription)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs),
          new CommonsArrayList <> (AccessToken.createAccessTokenValidFromNow (sTokenString)),
          aUser,
          sDescription);
  }

  /**
   * Constructor for deserialization.
   *
   * @param aStubObject
   *        The base data. May not be <code>null</code>.
   * @param aAccessTokens
   *        Access tokens. May neither be <code>null</code> nor empty.
   * @param aUser
   *        Related user for which this token can be used. May not be <code>null</code>.
   * @param sDescription
   *        Optional description of the User Token. May be <code>null</code>.
   */
  public UserToken (@Nonnull final StubObject aStubObject,
                    @Nonnull @Nonempty final List <AccessToken> aAccessTokens,
                    @Nonnull final IUser aUser,
                    @Nullable final String sDescription)
  {
    super (aStubObject, aAccessTokens);
    m_aUser = ValueEnforcer.notNull (aUser, "User");
    m_sDescription = sDescription;
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
                            .append ("User", m_aUser)
                            .appendIfNotNull ("Description", m_sDescription)
                            .getToString ();
  }
}
