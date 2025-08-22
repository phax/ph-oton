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
package com.helger.photon.connect.sftp;

import java.nio.charset.StandardCharsets;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.io.stream.StreamHelper;
import com.helger.base.string.StringHelper;
import com.helger.io.resource.ClassPathResource;
import com.helger.photon.connect.connection.ServerConnectionSettingsKeyPair;
import com.helger.photon.connect.connection.ServerConnectionSettingsPassword;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import jakarta.annotation.Nonnull;

/**
 * Special JSch session provider. It differentiates between username/password
 * auth and public key auth. Based on {@link ISftpSettingsHost}.
 *
 * @author Philip Helger
 * @since 9.2.9
 */
public class JschSessionProvider implements IJSchSessionProvider
{
  private final ISftpSettingsHost m_aSFTPSettings;

  public JschSessionProvider (@Nonnull final ISftpSettingsHost aSFTPSettings)
  {
    ValueEnforcer.notNull (aSFTPSettings, "SFTPSettings");
    m_aSFTPSettings = aSFTPSettings;
  }

  @Nonnull
  public Session createSession () throws JSchException
  {
    if (m_aSFTPSettings.hasServerPassword ())
    {
      // Password based
      return JSchSessionFactory.createSession (new ServerConnectionSettingsPassword (m_aSFTPSettings.getServerHost (),
                                                                                     m_aSFTPSettings.getServerPort (),
                                                                                     m_aSFTPSettings.getConnectionTimeoutMillis (),
                                                                                     m_aSFTPSettings.getServerUserName (),
                                                                                     m_aSFTPSettings.getServerPassword ()));
    }

    // key pair
    final String sKeyPairPrivateKeyFile = m_aSFTPSettings.getKeyPairPrivateKeyFile ();
    if (StringHelper.isEmpty (sKeyPairPrivateKeyFile))
      throw new JSchException ("Private key file path is not contained in settings!");
    final byte [] aPrivateKey = StreamHelper.getAllBytes (new ClassPathResource (sKeyPairPrivateKeyFile));
    if (aPrivateKey == null)
      throw new JSchException ("Failed to read private key from '" + sKeyPairPrivateKeyFile + "'");

    final String sKeyPairPublicKeyFile = m_aSFTPSettings.getKeyPairPublicKeyFile ();
    if (StringHelper.isEmpty (sKeyPairPublicKeyFile))
      throw new JSchException ("Public key file path is not contained in settings!");
    final byte [] aPublicKey = StreamHelper.getAllBytes (new ClassPathResource (sKeyPairPublicKeyFile));
    if (aPublicKey == null)
      throw new JSchException ("Failed to read public key from '" + sKeyPairPublicKeyFile + "'");

    return JSchSessionFactory.createSession (new ServerConnectionSettingsKeyPair (m_aSFTPSettings.getServerHost (),
                                                                                  m_aSFTPSettings.getServerPort (),
                                                                                  m_aSFTPSettings.getConnectionTimeoutMillis (),
                                                                                  m_aSFTPSettings.getServerUserName (),
                                                                                  aPrivateKey,
                                                                                  aPublicKey,
                                                                                  m_aSFTPSettings.getKeyPairPassphrase ()
                                                                                                 .getBytes (StandardCharsets.ISO_8859_1)));
  }
}
