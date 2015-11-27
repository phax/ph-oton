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
package com.helger.photon.security.token.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.security.token.object.IObjectWithAccessToken;

/**
 * Base interface for an application token.
 *
 * @author Philip Helger
 */
public interface IAppToken extends IObjectWithAccessToken
{
  /**
   * @return The name of the application that owns this token. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getOwnerName ();

  /**
   * @return The URL of the owning application. For informational purposes only.
   *         May be <code>null</code>.
   */
  @Nullable
  String getOwnerURL ();

  /**
   * @return The contact person or email of the owning application. For
   *         informational purposes only. May be <code>null</code>.
   */
  @Nullable
  String getOwnerContact ();

  /**
   * @return The contact email address of the owning application. For
   *         informational purposes only. May be <code>null</code>.
   */
  @Nullable
  String getOwnerContactEmail ();
}
