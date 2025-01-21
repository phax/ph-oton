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
package com.helger.photon.audit.v2.config;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.helger.commons.datetime.PDTFactory;

/**
 * Audit default settings.
 *
 * @author Philip Helger
 */
public final class AuditDefaultSettings
{
  private static final ILongSupplier ADUIT_EVENT_ID_PROVIDER = UUID.randomUUID ()::getMostSignificantBits;
  private static final Supplier <LocalDateTime> AUDIT_EVENT_DT_PROVIDER = PDTFactory::getCurrentLocalDateTime;

  private AuditDefaultSettings ()
  {}

  /**
   * @return The default ID provider that uses the most significant bits of a
   *         random UUID.
   */
  @Nonnull
  public static ILongSupplier getDefaultAuditEventIDProvider ()
  {
    return ADUIT_EVENT_ID_PROVIDER;
  }

  /**
   * @return The default date time provider using
   *         {@link PDTFactory#getCurrentLocalDateTime()}.
   */
  @Nonnull
  public static Supplier <LocalDateTime> getDefaultAuditEventDateTimeProvider ()
  {
    return AUDIT_EVENT_DT_PROVIDER;
  }
}
