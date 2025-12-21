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
import com.helger.annotation.style.ReturnsMutableCopy;

/**
 * Interface having all required fields required for connecting to a server via private and public
 * key.
 *
 * @author philip
 */
public interface IServerConnectionSettingsKeyPair extends IBaseServerConnectionSettings
{
  /**
   * @return Private key bytes. Neither <code>null</code> nor empty.
   */
  @Nonempty
  @ReturnsMutableCopy
  byte @NonNull [] getPrivateKey ();

  /**
   * @return Public key bytes. Neither <code>null</code> nor empty.
   */
  @Nonempty
  @ReturnsMutableCopy
  byte @NonNull [] getPublicKey ();

  /**
   * @return The pass phrase to be used to access the key pair.
   */
  @ReturnsMutableCopy
  byte @NonNull [] getKeyPairPassphrase ();
}
