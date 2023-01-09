/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.audit.v2.config;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * Read-only interface for audit settings.
 *
 * @author Philip Helger
 */
public interface IAuditSettings extends Serializable
{
  /**
   * @return The ID provide for AuditEvent objects. Never <code>null</code>.
   */
  @Nonnull
  ILongSupplier getAuditEventIDProvider ();

  /**
   * @return The date and time provider to be used. Never <code>null</code>. The
   *         returned supplier may not return <code>null</code>.
   */
  @Nonnull
  Supplier <LocalDateTime> getAuditEventDateTimeProvider ();
}
