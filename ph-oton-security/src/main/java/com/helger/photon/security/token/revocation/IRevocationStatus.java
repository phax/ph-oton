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
package com.helger.photon.security.token.revocation;

import java.time.LocalDateTime;

import javax.annotation.Nullable;

/**
 * Read-only interface for the revocation status. This is e.g. used to determine
 * if app tokens or user tokens are revoked or not.
 *
 * @author Philip Helger
 */
public interface IRevocationStatus
{
  /**
   * @return <code>true</code> if this object is revoked, <code>false</code>
   *         otherwise.
   */
  boolean isRevoked ();

  /**
   * @return The ID of the user who revoked the owning object. Must be non-
   *         <code>null</code> and non-empty if this object is revoked.
   */
  @Nullable
  String getRevocationUserID ();

  /**
   * @return The date time when the owning object was revoked. Must be non-
   *         <code>null</code> if this object is revoked.
   */
  @Nullable
  LocalDateTime getRevocationDateTime ();

  /**
   * @return The reason why the owning object was revoked. Must be non-
   *         <code>null</code> and non-empty if this object is revoked.
   */
  @Nullable
  String getRevocationReason ();
}
