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
package com.helger.photon.connect.generic.sftp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.helger.security.authentication.credentials.usernamepw.UserNamePasswordCredentials;

/**
 * Unit test for class {@link SftpConnector}.
 *
 * @author Philip Helger
 */
public final class SftpConnectorTest
{
  @Test
  @Ignore ("An FTP server must be running")
  public void testValidConnectDisconnect ()
  {
    // Fake credentials
    final String sHost = "orf.at";
    final int nPort = 1234;
    final String sUsername = "foo";
    final String sPassword = "foo";

    final SftpConnector aSC = new SftpConnector (sHost, nPort);
    assertFalse (aSC.isConnectionOpen ());
    // initial connect
    assertTrue (aSC.openConnection (new UserNamePasswordCredentials (sUsername, sPassword)).isChanged ());
    assertTrue (aSC.isConnectionOpen ());
    // already connected
    assertFalse (aSC.openConnection (new UserNamePasswordCredentials (sUsername, sPassword)).isChanged ());
    assertTrue (aSC.isConnectionOpen ());
    // close connection
    assertTrue (aSC.closeConnection ().isChanged ());
    assertFalse (aSC.isConnectionOpen ());
    // already closed
    assertFalse (aSC.closeConnection ().isChanged ());
    assertFalse (aSC.isConnectionOpen ());
  }

  @Test
  public void testInvalidConnectDisconnect ()
  {
    final SftpConnector aSC = new SftpConnector ("10.255.255.244");
    assertFalse (aSC.isConnectionOpen ());
    // initial connect
    assertFalse (aSC.openConnection (new UserNamePasswordCredentials ("wont", "work")).isChanged ());
    assertFalse (aSC.isConnectionOpen ());
  }
}
