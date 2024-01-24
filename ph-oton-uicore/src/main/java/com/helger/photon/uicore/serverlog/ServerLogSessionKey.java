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
package com.helger.photon.uicore.serverlog;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * Session singleton to create unique keys for server logging. Each generated
 * String has a length of 16.
 *
 * @author Philip Helger
 */
public final class ServerLogSessionKey extends AbstractSessionWebSingleton
{
  private final String m_sGeneratedKey;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public ServerLogSessionKey ()
  {
    final byte [] aKey = new byte [8];
    new Random ().nextBytes (aKey);
    m_sGeneratedKey = StringHelper.getHexEncoded (aKey);
  }

  @Nonnull
  public static ServerLogSessionKey getInstance ()
  {
    return getSessionSingleton (ServerLogSessionKey.class);
  }

  /**
   * @return The generated session key and never <code>null</code>.
   */
  @Nonnull
  public String getGeneratedKey ()
  {
    return m_sGeneratedKey;
  }

  /**
   * @return The generated session key or <code>null</code> if no
   *         {@link ServerLogSessionKey} was created yet.
   */
  @Nullable
  public static String getGeneratedSessionKey ()
  {
    final ServerLogSessionKey aObj = getSessionSingletonIfInstantiated (ServerLogSessionKey.class);
    return aObj == null ? null : aObj.m_sGeneratedKey;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ServerLogSessionKey rhs = (ServerLogSessionKey) o;
    return m_sGeneratedKey.equals (rhs.m_sGeneratedKey);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sGeneratedKey).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("generatedKey", m_sGeneratedKey).getToString ();
  }
}
