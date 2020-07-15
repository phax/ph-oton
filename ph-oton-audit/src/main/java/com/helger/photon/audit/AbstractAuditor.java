/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.state.ESuccess;
import com.helger.commons.type.ObjectType;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * Abstract base class for interface {@link IAuditor}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractAuditor implements IAuditor
{
  private ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private IAuditActionStringProvider m_aActionStringProvider;

  public AbstractAuditor (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    this (aCurrentUserIDProvider, IAuditActionStringProvider.JSON);
  }

  protected AbstractAuditor (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider,
                             @Nonnull final IAuditActionStringProvider aActionStringProvider)
  {
    setCurrentUserIDProvider (aCurrentUserIDProvider);
    setActionStringProvider (aActionStringProvider);
  }

  @Nonnull
  public final ICurrentUserIDProvider getCurrentUserIDProvider ()
  {
    return m_aCurrentUserIDProvider;
  }

  public final void setCurrentUserIDProvider (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    m_aCurrentUserIDProvider = ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
  }

  @Nonnull
  public final IAuditActionStringProvider getActionStringProvider ()
  {
    return m_aActionStringProvider;
  }

  public final void setActionStringProvider (@Nonnull final IAuditActionStringProvider aActionStringProvider)
  {
    m_aActionStringProvider = ValueEnforcer.notNull (aActionStringProvider, "ActionStringProvider");
  }

  /**
   * Implement this method to handle the created audit items.
   *
   * @param aAuditItem
   *        The audit item to handle. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void handleAuditItem (@Nonnull IAuditItem aAuditItem);

  public void createAuditItem (@Nonnull final EAuditActionType eActionType,
                               @Nonnull final ESuccess eSuccess,
                               @Nullable final ObjectType aActionObjectType,
                               @Nullable final String sAction,
                               @Nullable final Object... aArgs)
  {
    final String sFullAction = m_aActionStringProvider.apply (aActionObjectType != null ? aActionObjectType.getName () : sAction, aArgs);
    final AuditItem aAuditItem = new AuditItem (m_aCurrentUserIDProvider.getCurrentUserID (), eActionType, eSuccess, sFullAction);
    handleAuditItem (aAuditItem);
  }
}
