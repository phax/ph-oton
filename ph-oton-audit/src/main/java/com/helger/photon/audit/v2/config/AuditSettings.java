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
import java.util.function.Supplier;

import com.helger.base.enforce.ValueEnforcer;

import jakarta.annotation.Nonnull;

/**
 * Settings for auditing
 *
 * @author Philip Helger
 */
public class AuditSettings implements IAuditSettings
{
  /** Read-only default instance */
  public static final IAuditSettings DEFAULT_INSTANCE = new AuditSettings ();

  private ILongSupplier m_aAuditEventIDProvider = AuditDefaultSettings.getDefaultAuditEventIDProvider ();
  private Supplier <LocalDateTime> m_aAuditEventDateTimeProvider = AuditDefaultSettings.getDefaultAuditEventDateTimeProvider ();

  public AuditSettings ()
  {}

  @Nonnull
  public ILongSupplier getAuditEventIDProvider ()
  {
    return m_aAuditEventIDProvider;
  }

  @Nonnull
  public AuditSettings setAuditEventIDProvider (@Nonnull final ILongSupplier aIDProvider)
  {
    ValueEnforcer.notNull (aIDProvider, "IDProvider");
    m_aAuditEventIDProvider = aIDProvider;
    return this;
  }

  @Nonnull
  public Supplier <LocalDateTime> getAuditEventDateTimeProvider ()
  {
    return m_aAuditEventDateTimeProvider;
  }

  @Nonnull
  public AuditSettings setAuditEventDateTimeProvider (@Nonnull final Supplier <LocalDateTime> aDateTimeProvider)
  {
    ValueEnforcer.notNull (aDateTimeProvider, "DateTimeProvider");
    m_aAuditEventDateTimeProvider = aDateTimeProvider;
    return this;
  }
}
