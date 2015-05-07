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
package com.helger.photon.connect.connection;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IServerConnectionSettingsPassword}
 * interface.
 *
 * @author philip
 */
@Immutable
public class ServerConnectionSettingsPassword extends AbstractServerConnectionSettings implements IServerConnectionSettingsPassword
{
  private final String m_sPassword;

  public ServerConnectionSettingsPassword (@Nonnull @Nonempty final String sIP,
                                           @Nonnegative final int nPort,
                                           final int nConnectionTimeoutMillis,
                                           @Nonnull @Nonempty final String sUserName,
                                           @Nonnull final String sPassword)
  {
    super (sIP, nPort, nConnectionTimeoutMillis, sUserName);

    ValueEnforcer.notNull (sPassword, "Password");
    m_sPassword = sPassword;
  }

  @Nonnull
  public String getPassword ()
  {
    return m_sPassword;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendPassword ("password").toString ();
  }
}
