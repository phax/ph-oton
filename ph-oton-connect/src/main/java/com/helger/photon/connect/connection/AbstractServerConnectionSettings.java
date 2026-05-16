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
package com.helger.photon.connect.connection;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.network.port.NetworkPortHelper;

/**
 * Default abstract implementation of the {@link IBaseServerConnectionSettings} interface.
 *
 * @author philip
 */
@Immutable
public abstract class AbstractServerConnectionSettings implements IBaseServerConnectionSettings
{
  private final String m_sAddress;
  private final int m_nPort;
  private final Duration m_aConnectionTimeout;
  private final String m_sUserName;

  @Deprecated (forRemoval = true, since = "10.2.3")
  public AbstractServerConnectionSettings (@NonNull @Nonempty final String sIP,
                                           @Nonnegative final int nPort,
                                           final int nConnectionTimeoutMillis,
                                           @NonNull @Nonempty final String sUserName)
  {
    this (sIP, nPort, nConnectionTimeoutMillis < 0 ? null : Duration.ofMillis (nConnectionTimeoutMillis), sUserName);
  }

  public AbstractServerConnectionSettings (@NonNull @Nonempty final String sIP,
                                           @Nonnegative final int nPort,
                                           @Nullable final Duration aConnectionTimeout,
                                           @NonNull @Nonempty final String sUserName)
  {
    ValueEnforcer.notEmpty (sIP, "Address");
    if (!NetworkPortHelper.isValidPort (nPort))
      throw new IllegalArgumentException ("port is invalid: " + nPort);
    ValueEnforcer.notEmpty (sUserName, "UserName");

    m_sAddress = sIP;
    m_nPort = nPort;
    m_aConnectionTimeout = aConnectionTimeout;
    m_sUserName = sUserName;
  }

  @NonNull
  @Nonempty
  public final String getServerAddress ()
  {
    return m_sAddress;
  }

  @Nonnegative
  public final int getServerPort ()
  {
    return m_nPort;
  }

  @Nullable
  public Duration getConnectionTimeout ()
  {
    return m_aConnectionTimeout;
  }

  @NonNull
  @Nonempty
  public final String getUserName ()
  {
    return m_sUserName;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Address", m_sAddress)
                                       .append ("Port", m_nPort)
                                       .append ("Username", m_sUserName)
                                       .appendIfNotNull ("ConnectionTimeout", m_aConnectionTimeout)
                                       .getToString ();
  }
}
