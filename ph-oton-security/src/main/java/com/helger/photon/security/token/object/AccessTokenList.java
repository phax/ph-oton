/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.object;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * List of {@link AccessToken} objects.
 *
 * @author Philip Helger
 * @since 8.4.5
 */
@Nonnull
public class AccessTokenList implements IAccessTokenList
{
  private final ICommonsList <AccessToken> m_aAccessTokens;

  // Status vars
  private AccessToken m_aActiveAccessToken;

  @Nullable
  protected static AccessToken _getIfNotRevoked (@Nullable final AccessToken aAccessToken)
  {
    return aAccessToken != null && !aAccessToken.isRevoked () ? aAccessToken : null;
  }

  public AccessTokenList (@Nonnull @Nonempty final List <AccessToken> aAccessTokens)
  {
    ValueEnforcer.notEmptyNoNullValue (aAccessTokens, "AccessTokens");
    m_aAccessTokens = new CommonsArrayList <> (aAccessTokens);
    m_aActiveAccessToken = _getIfNotRevoked (CollectionHelper.getLastElement (aAccessTokens));
  }

  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public ICommonsList <AccessToken> getAllAccessTokens ()
  {
    return m_aAccessTokens.getClone ();
  }

  @Nullable
  public IAccessToken findFirstAccessToken (@Nullable final Predicate <? super IAccessToken> aFilter)
  {
    return m_aAccessTokens.findFirst (aFilter);
  }

  @Nullable
  public IAccessToken getActiveAccessToken ()
  {
    return m_aActiveAccessToken;
  }

  @Nonnull
  public EChange revokeActiveAccessToken (@Nonnull @Nonempty final String sRevocationUserID,
                                          @Nonnull final LocalDateTime aRevocationDT,
                                          @Nonnull @Nonempty final String sRevocationReason)
  {
    if (m_aActiveAccessToken == null)
    {
      // No active token present
      return EChange.UNCHANGED;
    }
    m_aActiveAccessToken.markRevoked (sRevocationUserID, aRevocationDT, sRevocationReason);
    m_aActiveAccessToken.setNotAfter (aRevocationDT);
    m_aActiveAccessToken = null;
    return EChange.CHANGED;
  }

  @Nonnull
  public AccessToken createNewAccessToken (@Nullable final String sTokenString)
  {
    if (m_aActiveAccessToken != null)
      throw new IllegalStateException ("You need to revoke the previous access token before creating a new one!");

    final AccessToken aNewToken = AccessToken.createAccessTokenValidFromNow (sTokenString);
    m_aAccessTokens.add (aNewToken);
    m_aActiveAccessToken = aNewToken;
    return aNewToken;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("AccessTokens", m_aAccessTokens)
                                       .append ("ActiveAccessToken", m_aActiveAccessToken)
                                       .getToString ();
  }
}
