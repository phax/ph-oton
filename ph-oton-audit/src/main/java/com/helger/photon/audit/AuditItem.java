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
package com.helger.photon.audit;

import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.ESuccess;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.datetime.helper.PDTFactory;
import com.helger.security.authentication.subject.user.CUserID;

/**
 * Represents a single change item
 *
 * @author Philip Helger
 */
@Immutable
public final class AuditItem implements IAuditItem
{
  private final LocalDateTime m_aDateTime;
  private final String m_sUserID;
  private final EAuditActionType m_eType;
  private final ESuccess m_eSuccess;
  private final String m_sAction;

  public AuditItem (@Nullable final String sUserID,
                    @NonNull final EAuditActionType eType,
                    @NonNull final ESuccess eSuccess,
                    @NonNull final String sAction)
  {
    this (PDTFactory.getCurrentLocalDateTime (),
          StringHelper.isNotEmpty (sUserID) ? sUserID : CUserID.USER_ID_GUEST,
          eType,
          eSuccess,
          sAction);
  }

  public AuditItem (@NonNull final LocalDateTime aDateTime,
                    @NonNull final String sUserID,
                    @NonNull final EAuditActionType eType,
                    @NonNull final ESuccess eSuccess,
                    @NonNull final String sAction)
  {
    m_aDateTime = ValueEnforcer.notNull (aDateTime, "LocalDateTime");
    m_sUserID = ValueEnforcer.notEmpty (sUserID, "UserID");
    m_eType = ValueEnforcer.notNull (eType, "Type");
    m_eSuccess = ValueEnforcer.notNull (eSuccess, "Success");
    m_sAction = ValueEnforcer.notNull (sAction, "Action");
  }

  @NonNull
  public LocalDateTime getDateTime ()
  {
    return m_aDateTime;
  }

  @NonNull
  public String getUserID ()
  {
    return m_sUserID;
  }

  @NonNull
  public EAuditActionType getType ()
  {
    return m_eType;
  }

  @NonNull
  public ESuccess getSuccess ()
  {
    return m_eSuccess;
  }

  @NonNull
  public String getAction ()
  {
    return m_sAction;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AuditItem rhs = (AuditItem) o;
    return m_aDateTime.equals (rhs.m_aDateTime) &&
           m_sUserID.equals (rhs.m_sUserID) &&
           m_eType.equals (rhs.m_eType) &&
           m_eSuccess.equals (rhs.m_eSuccess) &&
           m_sAction.equals (rhs.m_sAction);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aDateTime)
                                       .append (m_sUserID)
                                       .append (m_eType)
                                       .append (m_eSuccess)
                                       .append (m_sAction)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("dateTime", m_aDateTime)
                                       .append ("userID", m_sUserID)
                                       .append ("type", m_eType)
                                       .append ("success", m_eSuccess)
                                       .append ("action", m_sAction)
                                       .getToString ();
  }
}
