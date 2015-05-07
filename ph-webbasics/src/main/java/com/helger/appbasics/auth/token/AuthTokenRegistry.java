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
package com.helger.appbasics.auth.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.appbasics.auth.identify.IAuthIdentification;
import com.helger.appbasics.auth.subject.IAuthSubject;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ESuccess;

/**
 * This class manages all the currently available authentications tokens.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AuthTokenRegistry
{
  /** The value indicating, that a token never expires */
  public static final int NEVER_EXPIRES = 0;

  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  private static final Map <String, AuthToken> s_aRegistry = new HashMap <String, AuthToken> ();

  private AuthTokenRegistry ()
  {}

  @Nonnull
  public static IAuthToken createToken (@Nonnull final IAuthIdentification aIdentification,
                                        @Nonnegative final int nExpirationSeconds)
  {
    final AuthToken aToken = new AuthToken (aIdentification, nExpirationSeconds);
    final String sTokenID = aToken.getID ();

    s_aRWLock.writeLock ().lock ();
    try
    {
      if (s_aRegistry.containsKey (sTokenID))
        throw new IllegalArgumentException ("Token '" + sTokenID + "' already contained");
      s_aRegistry.put (sTokenID, aToken);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }

    return aToken;
  }

  @Nonnull
  public static ESuccess removeToken (@Nonnull final String sTokenID)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      final AuthToken aToken = s_aRegistry.remove (sTokenID);
      if (aToken == null)
        return ESuccess.FAILURE;

      // manually set token as expired to avoid further usage in case somebody
      // has a reference to the token
      aToken.setExpired ();
      return ESuccess.SUCCESS;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nullable
  private static AuthToken _getValidNotExpiredToken (@Nullable final String sTokenID)
  {
    if (sTokenID == null)
      return null;

    s_aRWLock.readLock ().lock ();
    try
    {
      final AuthToken aToken = s_aRegistry.get (sTokenID);
      return aToken != null && !aToken.isExpired () ? aToken : null;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public static IAuthToken getValidToken (@Nullable final String sTokenID)
  {
    return _getValidNotExpiredToken (sTokenID);
  }

  @Nullable
  public static IAuthToken validateTokenAndUpdateLastAccess (@Nullable final String sTokenID)
  {
    final AuthToken aToken = _getValidNotExpiredToken (sTokenID);
    if (aToken == null)
      return null;

    s_aRWLock.writeLock ().lock ();
    try
    {
      aToken.updateLastAccess ();
      return aToken;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Get all tokens of the specified auth subject. All tokens are returned, no
   * matter whether they are expired or not.
   *
   * @param aSubject
   *        The subject to query. May not be <code>null</code>.
   * @return The list and never <code>null</code>.
   */
  @Nonnull
  public static List <IAuthToken> getAllTokensOfSubject (@Nonnull final IAuthSubject aSubject)
  {
    ValueEnforcer.notNull (aSubject, "Subject");

    final List <IAuthToken> ret = new ArrayList <IAuthToken> ();
    s_aRWLock.readLock ().lock ();
    try
    {
      for (final AuthToken aToken : s_aRegistry.values ())
        if (aToken.getIdentification ().getSubject ().equals (aSubject))
          ret.add (aToken);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
    return ret;
  }

  /**
   * Remove all tokens of the given subject
   *
   * @param aSubject
   *        The subject for which the tokens should be removed.
   * @return The number of removed tokens. Always &ge; 0.
   */
  @Nonnegative
  public static int removeAllTokensOfSubject (@Nonnull final IAuthSubject aSubject)
  {
    ValueEnforcer.notNull (aSubject, "Subject");

    // get all token IDs matching a given subject
    // Note: required IAuthSubject to implement equals!
    final List <String> aDelTokenIDs = new ArrayList <String> ();
    s_aRWLock.readLock ().lock ();
    try
    {
      for (final Map.Entry <String, AuthToken> aEntry : s_aRegistry.entrySet ())
        if (aEntry.getValue ().getIdentification ().getSubject ().equals (aSubject))
          aDelTokenIDs.add (aEntry.getKey ());
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }

    for (final String sDelTokenID : aDelTokenIDs)
      removeToken (sDelTokenID);

    return aDelTokenIDs.size ();
  }
}
