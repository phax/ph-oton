package com.helger.photon.audit.v2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ESuccess;
import com.helger.commons.type.ObjectType;
import com.helger.photon.audit.EAuditActionType;
import com.helger.photon.audit.IAuditor;
import com.helger.photon.audit.v2.domain.AuditEvent;
import com.helger.photon.audit.v2.pipeline.IAuditEventConsumer;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * Implementation of V1 {@link IAuditor} in terms of V2 {@link AuditEvent} etc
 *
 * @author Philip Helger
 */
public class Audit2Bridge implements IAuditor
{
  private final ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private final IAuditEventConsumer m_aAuditEventConsumer;

  public Audit2Bridge (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider,
                       @Nonnull final IAuditEventConsumer aAuditEventConsumer)
  {
    ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
    ValueEnforcer.notNull (aAuditEventConsumer, "AuditEventConsumer");
    m_aCurrentUserIDProvider = aCurrentUserIDProvider;
    m_aAuditEventConsumer = aAuditEventConsumer;
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
                               @Nullable final String sAction,
                               @Nullable final Object... aArgs)
  {
    // No need to change settings
    // No origin present
    final AuditEventBuilder aBuilder = new AuditEventBuilder ().setCurrentUserIDProvider (m_aCurrentUserIDProvider)
                                                               .setAction (eActionType)
                                                               .setSucces (eSuccess);
    if (aActionObjectType != null)
      aBuilder.addField ("ObjectType", aActionObjectType.getName ());
    if (sAction != null)
      aBuilder.addField ("Action", sAction);
    final AuditEvent aAuditEvent = aBuilder.build ();
    m_aAuditEventConsumer.handleAuditEvent (aAuditEvent);
  }
}
