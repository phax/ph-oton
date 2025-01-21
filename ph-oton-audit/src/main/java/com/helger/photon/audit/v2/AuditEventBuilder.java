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
package com.helger.photon.audit.v2;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.photon.audit.EAuditActionType;
import com.helger.photon.audit.v2.config.AuditSettings;
import com.helger.photon.audit.v2.config.IAuditSettings;
import com.helger.photon.audit.v2.domain.AuditEvent;
import com.helger.photon.audit.v2.domain.AuditField;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * Builder for {@link AuditEvent} objects.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AuditEventBuilder
{
  // Avoid constructing settings every time
  private IAuditSettings m_aSettings = AuditSettings.DEFAULT_INSTANCE;
  private ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private String m_sActor;
  private String m_sOrigin;
  private EAuditActionType m_eAction;
  private ESuccess m_eSuccess;
  private final ICommonsList <AuditField> m_aFields = new CommonsArrayList <> ();

  public AuditEventBuilder ()
  {}

  @Nonnull
  public AuditEventBuilder setSettings (@Nonnull final IAuditSettings aSettings)
  {
    ValueEnforcer.notNull (aSettings, "Settings");
    m_aSettings = aSettings;
    return this;
  }

  @Nonnull
  public AuditEventBuilder setCurrentUserIDProvider (@Nullable final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    m_aCurrentUserIDProvider = aCurrentUserIDProvider;
    return this;
  }

  @Nonnull
  public AuditEventBuilder setActor (@Nullable final String sActor)
  {
    m_sActor = sActor;
    return this;
  }

  @Nonnull
  public AuditEventBuilder setOrigin (@Nullable final String sOrigin)
  {
    m_sOrigin = sOrigin;
    return this;
  }

  @Nonnull
  public AuditEventBuilder setAction (@Nullable final EAuditActionType eAction)
  {
    m_eAction = eAction;
    return this;
  }

  @Nonnull
  public AuditEventBuilder setSucces (@Nullable final ESuccess eSuccess)
  {
    m_eSuccess = eSuccess;
    return this;
  }

  @Nonnull
  public AuditEventBuilder addField (@Nullable final AuditField aField)
  {
    if (aField != null)
      m_aFields.add (aField);
    return this;
  }

  @Nonnull
  public AuditEventBuilder addField (@Nullable final String sFieldName, @Nullable final String sFieldValue)
  {
    // Avoid adding an empty field
    if (sFieldName != null || sFieldValue != null)
      return addField (new AuditField (sFieldName, sFieldValue));
    return this;
  }

  @Nonnull
  public AuditEventBuilder addFieldHiddenValue (@Nullable final String sFieldName)
  {
    return addField (AuditField.createWithHiddenValue (sFieldName));
  }

  @Nullable
  public String getRealActorID ()
  {
    // Explicitly provided has precedence
    if (StringHelper.hasText (m_sActor))
      return m_sActor;

    // Provider for user ID in current context
    if (m_aCurrentUserIDProvider != null)
      return m_aCurrentUserIDProvider.getCurrentUserID ();

    // We don't know
    return null;
  }

  /**
   * Build a new {@link AuditEvent} based on the provided parameters. Each
   * invocation creates a new instance. The ID and the date time are retrieved
   * from the providers registered in the {@link IAuditSettings}.
   *
   * @return The created {@link AuditEvent} and never <code>null</code>.
   */
  @Nonnull
  public AuditEvent build ()
  {
    final long nID = m_aSettings.getAuditEventIDProvider ().getAsLong ();
    final LocalDateTime aLDT = m_aSettings.getAuditEventDateTimeProvider ().get ();
    final String sActor = getRealActorID ();
    return new AuditEvent (nID, aLDT, sActor, m_sOrigin, m_eAction, m_eSuccess, m_aFields);
  }
}
