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
package com.helger.photon.security.token.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.object.StubObjectWithCustomAttrs;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.object.AbstractObjectWithAccessToken;

/**
 * A single token for granting a machine user access to this application.
 *
 * @author Philip Helger
 */
public class UserToken extends AbstractObjectWithAccessToken implements IUserToken
{
  public static final ObjectType OT = new ObjectType ("usertoken");

  private final IAppToken m_aAppToken;
  private String m_sUserName;

  public UserToken (@Nullable final String sTokenString,
                    @Nullable final Map <String, String> aCustomAttrs,
                    @Nonnull final IAppToken aAppToken,
                    @Nonnull @Nonempty final String sUserName)
  {
    this (StubObjectWithCustomAttrs.createForCurrentUser (aCustomAttrs),
          CollectionHelper.newList (AccessToken.createAccessTokenValidFromNow (sTokenString)),
          aAppToken,
          sUserName);
  }

  UserToken (@Nonnull final StubObjectWithCustomAttrs aStubObject,
             @Nonnull @Nonempty final List <AccessToken> aAccessTokens,
             @Nonnull final IAppToken aAppToken,
             @Nonnull @Nonempty final String sUserName)
  {
    super (aStubObject, aAccessTokens);
    m_aAppToken = ValueEnforcer.notNull (aAppToken, "AppToken");
    setUserName (sUserName);
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
    return m_sUserName;
  }

  @Nonnull
  public IAppToken getAppToken ()
  {
    return m_aAppToken;
  }

  @Nonnull
  @Nonempty
  public String getUserName ()
  {
    return m_sUserName;
  }

  @Nonnull
  public EChange setUserName (@Nonnull @Nonempty final String sUserName)
  {
    ValueEnforcer.notEmpty (sUserName, "UserName");
    if (sUserName.equals (m_sUserName))
      return EChange.UNCHANGED;
    m_sUserName = sUserName;
    return EChange.CHANGED;
  }

  // equals and hashCode are derived

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("AppToken", m_aAppToken)
                            .append ("UserName", m_sUserName)
                            .toString ();
  }
}
