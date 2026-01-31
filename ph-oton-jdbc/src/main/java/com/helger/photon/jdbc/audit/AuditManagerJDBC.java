/*
 * Copyright (C) 2021-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc.audit;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.ICommonsList;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.photon.audit.IAuditItem;
import com.helger.photon.audit.IAuditManager;
import com.helger.photon.audit.IAuditor;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

/**
 * The JDBC based implementation of {@link IAuditManager}
 *
 * @author Philip Helger
 */
public class AuditManagerJDBC implements IAuditManager
{
  private final AuditorJDBC m_aAuditor;

  public AuditManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                           @NonNull final Function <String, String> aTableNameCustomizer)
  {
    this (aDBExecSupplier, aTableNameCustomizer, LoggedInUserManager.getInstance ());
  }

  public AuditManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                           @NonNull final Function <String, String> aTableNameCustomizer,
                           @NonNull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    m_aAuditor = new AuditorJDBC (aDBExecSupplier, aTableNameCustomizer, aCurrentUserIDProvider);
  }

  public boolean isInMemory ()
  {
    return false;
  }

  @Nullable
  public String getBaseDir ()
  {
    // No file system
    return null;
  }

  @NonNull
  public IAuditor getAuditor ()
  {
    return m_aAuditor;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    return m_aAuditor.getLastAuditItems (nMaxItems);
  }

  public void stop ()
  {
    // Nothing to do
  }

  @Nullable
  public LocalDate getEarliestAuditDate ()
  {
    return m_aAuditor.getEarliestAuditDate ();
  }
}
