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
package com.helger.webbasics.ssh.generic;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.auth.credentials.IAuthCredentials;
import com.helger.commons.state.EChange;

/**
 * Abstract connection destination.
 * 
 * @author philip
 * @param <HANDLETYPE>
 *        The handle type to be used.
 */
public interface IConnectionDestination <HANDLETYPE>
{
  /**
   * Connect to the destination.
   * 
   * @param aCredentials
   *        The credentials to use.
   * @return <code>null</code> if connection could not be established, the
   *         handle otherwise.
   */
  @Nullable
  HANDLETYPE openConnection (IAuthCredentials aCredentials);

  /**
   * Close the connection.
   * 
   * @param aHandle
   *        The handle returned from {@link #openConnection(IAuthCredentials)} .
   * @return {@link EChange}
   */
  @Nonnull
  EChange closeConnection (HANDLETYPE aHandle);
}
