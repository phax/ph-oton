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
package com.helger.photon.connect.connection;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.array.ArrayHelper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;

/**
 * Default implementation of the {@link IServerConnectionSettingsKeyPair} interface.
 *
 * @author philip
 */
@Immutable
public class ServerConnectionSettingsKeyPair extends AbstractServerConnectionSettings implements
                                             IServerConnectionSettingsKeyPair
{
  private final byte [] m_aPrivateKeyBytes;
  private final byte [] m_aPublicKeyBytes;
  private final byte [] m_aKeyPairPassphrase;

  public ServerConnectionSettingsKeyPair (@NonNull @Nonempty final String sIP,
                                          @Nonnegative final int nPort,
                                          final int nConnectionTimeoutMillis,
                                          @NonNull @Nonempty final String sUserName,
                                          final byte @NonNull @Nonempty [] aPrivateKeyBytes,
                                          final byte @NonNull @Nonempty [] aPublicKeyBytes,
                                          final byte @NonNull [] aKeyPairPassphrase)
  {
    super (sIP, nPort, nConnectionTimeoutMillis, sUserName);

    m_aPrivateKeyBytes = ArrayHelper.getCopy (ValueEnforcer.notEmpty (aPrivateKeyBytes, "PrivateKeyBytes"));
    m_aPublicKeyBytes = ArrayHelper.getCopy (ValueEnforcer.notEmpty (aPublicKeyBytes, "PublicKeyBytes"));
    m_aKeyPairPassphrase = ArrayHelper.getCopy (ValueEnforcer.notNull (aKeyPairPassphrase, "KeyPairPassphrase"));
  }

  @ReturnsMutableCopy
  public byte @NonNull @Nonempty [] getPrivateKey ()
  {
    return ArrayHelper.getCopy (m_aPrivateKeyBytes);
  }

  @ReturnsMutableCopy
  public byte @NonNull @Nonempty [] getPublicKey ()
  {
    return ArrayHelper.getCopy (m_aPublicKeyBytes);
  }

  @ReturnsMutableCopy
  public byte @NonNull [] getKeyPairPassphrase ()
  {
    return ArrayHelper.getCopy (m_aKeyPairPassphrase);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("PrivateKeyBytes#", m_aPrivateKeyBytes.length)
                            .append ("PublicKeyBytes#", m_aPublicKeyBytes.length)
                            .appendPassword ("KeyPairPassphrase")
                            .getToString ();
  }
}
