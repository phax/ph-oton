/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.functional.IFunction;
import com.helger.commons.state.ESuccess;
import com.helger.commons.type.ObjectType;
import com.helger.photon.audit.EAuditActionType;
import com.helger.photon.audit.IAuditor;
import com.helger.photon.audit.v2.domain.AuditEvent;
import com.helger.photon.audit.v2.pipeline.IAuditEventConsumer;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * Implementation of V1 {@link IAuditor} in terms of V2 {@link AuditEvent} etc.
 * This class is mainly meant for easy transition without adopting all APIs.
 *
 * @author Philip Helger
 */
public class Audit2Bridge implements IAuditor
{
  public static final String AUDIT_FIELD_OBJECT_TYPE = "ObjectType";
  public static final String AUDIT_FIELD_USER_ACTION = "UserAction";

  private final ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private final IAuditEventConsumer m_aAuditEventConsumer;
  private final IFunction <Object, String> m_aToStringConverter;

  public Audit2Bridge (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider,
                       @Nonnull final IAuditEventConsumer aAuditEventConsumer,
                       @Nonnull final IFunction <Object, String> aToStringConverter)
  {
    ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
    ValueEnforcer.notNull (aAuditEventConsumer, "AuditEventConsumer");
    ValueEnforcer.notNull (aToStringConverter, "ToStringConverter");
    m_aCurrentUserIDProvider = aCurrentUserIDProvider;
    m_aAuditEventConsumer = aAuditEventConsumer;
    m_aToStringConverter = aToStringConverter;
  }

  @Nonnull
  public final ICurrentUserIDProvider getCurrentUserIDProvider ()
  {
    return m_aCurrentUserIDProvider;
  }

  @Nonnull
  public final IAuditEventConsumer getAuditEventConsumer ()
  {
    return m_aAuditEventConsumer;
  }

  @Override
  public void createAuditItem (@Nonnull final EAuditActionType eActionType,
                               @Nonnull final ESuccess eSuccess,
                               @Nullable final ObjectType aActionObjectType,
                               @Nullable final String sUserAction,
                               @Nullable final Object... aArgs)
  {
    // No need to change settings
    // No origin present
    final AuditEventBuilder aBuilder = new AuditEventBuilder ().setCurrentUserIDProvider (m_aCurrentUserIDProvider)
                                                               .setAction (eActionType)
                                                               .setSucces (eSuccess);
    if (aActionObjectType != null)
      aBuilder.addField (AUDIT_FIELD_OBJECT_TYPE, aActionObjectType.getName ());
    if (sUserAction != null)
      aBuilder.addField (AUDIT_FIELD_USER_ACTION, sUserAction);
    if (aArgs != null)
      for (final Object aArg : aArgs)
      {
        // Use custom toString converter
        final String sValue = m_aToStringConverter.apply (aArg);
        // Add field without name
        aBuilder.addField (null, sValue);
      }
    final AuditEvent aAuditEvent = aBuilder.build ();
    m_aAuditEventConsumer.consumeAuditEvent (aAuditEvent);
  }
}
