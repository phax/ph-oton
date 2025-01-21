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
package com.helger.photon.audit.v2.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.id.IHasLongID;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.domain.IHasCreationDateTime;
import com.helger.photon.audit.EAuditActionType;

/**
 * A single audit event.
 *
 * @author Philip Helger
 */
@Immutable
public class AuditEvent implements Serializable, IHasLongID, IHasCreationDateTime
{
  private final long m_nID;
  private final LocalDateTime m_aCreationDT;
  private final String m_sActor;
  private final String m_sOrigin;
  private final EAuditActionType m_eAction;
  private final ESuccess m_eSuccess;
  private final ICommonsList <AuditField> m_aFields = new CommonsArrayList <> ();

  /**
   * Constructor.
   *
   * @param nID
   *        internal ID
   * @param aCreationDT
   *        Creation date and time. May not be <code>null</code>.
   * @param sActor
   *        Event actor. May be <code>null</code>.
   * @param sOrigin
   *        Event origin. May be <code>null</code>.
   * @param eAction
   *        Event action. May be <code>null</code>.
   * @param eSuccess
   *        Event success. May be <code>null</code>.
   * @param aFields
   *        List of event fields. May be <code>null</code>.
   */
  public AuditEvent (final long nID,
                     @Nonnull final LocalDateTime aCreationDT,
                     @Nullable final String sActor,
                     @Nullable final String sOrigin,
                     @Nullable final EAuditActionType eAction,
                     @Nullable final ESuccess eSuccess,
                     @Nullable final ICommonsList <AuditField> aFields)
  {
    ValueEnforcer.notNull (aCreationDT, "CreationDT");
    m_nID = nID;
    m_aCreationDT = aCreationDT;
    m_sActor = sActor;
    m_sOrigin = sOrigin;
    m_eAction = eAction;
    m_eSuccess = eSuccess;
    if (aFields != null)
      m_aFields.addAll (aFields);
  }

  public final long getID ()
  {
    return m_nID;
  }

  @Nonnull
  public final LocalDateTime getCreationDateTime ()
  {
    return m_aCreationDT;
  }

  @Nullable
  public String getActor ()
  {
    return m_sActor;
  }

  public boolean hasActor ()
  {
    return StringHelper.hasText (m_sActor);
  }

  @Nullable
  public String getOrigin ()
  {
    return m_sOrigin;
  }

  public boolean hasOrigin ()
  {
    return StringHelper.hasText (m_sOrigin);
  }

  @Nullable
  public EAuditActionType getAction ()
  {
    return m_eAction;
  }

  @Nullable
  public String getActionID ()
  {
    return m_eAction == null ? null : m_eAction.getID ();
  }

  public boolean hasAction ()
  {
    return m_eAction != null;
  }

  @Nullable
  public ESuccess getSuccess ()
  {
    return m_eSuccess;
  }

  public boolean hasSuccess ()
  {
    return m_eSuccess != null;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <AuditField> getAllFields ()
  {
    return m_aFields.getClone ();
  }

  @Nonnull
  @ReturnsMutableObject
  public ICommonsList <AuditField> fields ()
  {
    return m_aFields;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AuditEvent rhs = (AuditEvent) o;
    return m_nID == rhs.m_nID &&
           m_aCreationDT.equals (rhs.m_aCreationDT) &&
           EqualsHelper.equals (m_sActor, rhs.m_sActor) &&
           EqualsHelper.equals (m_sOrigin, rhs.m_sOrigin) &&
           EqualsHelper.equals (m_eAction, rhs.m_eAction) &&
           EqualsHelper.equals (m_eSuccess, rhs.m_eSuccess) &&
           m_aFields.equals (rhs.m_aFields);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nID)
                                       .append (m_aCreationDT)
                                       .append (m_sActor)
                                       .append (m_sOrigin)
                                       .append (m_eAction)
                                       .append (m_eSuccess)
                                       .append (m_aFields)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_nID)
                                       .append ("CreationDT", m_aCreationDT)
                                       .appendIfNotNull ("Actor", m_sActor)
                                       .appendIfNotNull ("Origin", m_sOrigin)
                                       .appendIfNotNull ("Action", m_eAction)
                                       .appendIfNotNull ("Success", m_eSuccess)
                                       .append ("Fields", m_aFields)
                                       .getToString ();
  }
}
