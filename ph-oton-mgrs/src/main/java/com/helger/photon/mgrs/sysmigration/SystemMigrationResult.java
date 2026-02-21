/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.mgrs.sysmigration;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.id.IHasID;
import com.helger.base.state.ISuccessIndicator;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.datetime.helper.PDTFactory;

/**
 * Represents the result of a single system migration.
 *
 * @author Philip Helger
 */
@Immutable
public class SystemMigrationResult implements IHasID <String>, ISuccessIndicator, Serializable
{
  public static final ObjectType OT = new ObjectType ("systemmigrationresult");

  private final String m_sMigrationID;
  private final LocalDateTime m_aExecutionDT;
  private final boolean m_bSuccess;
  private final String m_sErrorMessage;

  protected SystemMigrationResult (@NonNull @Nonempty final String sMigrationID,
                                   @NonNull final LocalDateTime aExecutionDT,
                                   final boolean bSuccess,
                                   @Nullable final String sErrorMessage)
  {
    m_sMigrationID = ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    m_aExecutionDT = ValueEnforcer.notNull (aExecutionDT, "ExecutionDT");
    m_bSuccess = bSuccess;
    m_sErrorMessage = sErrorMessage;
  }

  @NonNull
  public String getID ()
  {
    return m_sMigrationID;
  }

  @NonNull
  public LocalDateTime getExecutionDateTime ()
  {
    return m_aExecutionDT;
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  @Override
  public boolean isFailure ()
  {
    return !m_bSuccess;
  }

  @Nullable
  public String getErrorMessage ()
  {
    return m_sErrorMessage;
  }

  public boolean hasErrorMessage ()
  {
    return StringHelper.isNotEmpty (m_sErrorMessage);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final SystemMigrationResult rhs = (SystemMigrationResult) o;
    return m_sMigrationID.equals (rhs.m_sMigrationID) &&
           m_aExecutionDT.equals (rhs.m_aExecutionDT) &&
           m_bSuccess == rhs.m_bSuccess &&
           EqualsHelper.equals (m_sErrorMessage, rhs.m_sErrorMessage);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sMigrationID)
                                       .append (m_aExecutionDT)
                                       .append (m_bSuccess)
                                       .append (m_sErrorMessage)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("MigrationID", m_sMigrationID)
                                       .append ("ExecutionDT", m_aExecutionDT)
                                       .append ("Success", m_bSuccess)
                                       .append ("ErrorMsg", m_sErrorMessage)
                                       .getToString ();
  }

  @NonNull
  public static SystemMigrationResult createSuccess (@NonNull @Nonempty final String sMigrationID)
  {
    return new SystemMigrationResult (sMigrationID, PDTFactory.getCurrentLocalDateTime (), true, null);
  }

  @NonNull
  public static SystemMigrationResult createFailure (@NonNull @Nonempty final String sMigrationID,
                                                     @NonNull final String sErrorMsg)
  {
    return new SystemMigrationResult (sMigrationID, PDTFactory.getCurrentLocalDateTime (), false, sErrorMsg);
  }

  @NonNull
  public static SystemMigrationResult ofAll (@NonNull @Nonempty final String sMigrationID,
                                             @NonNull final LocalDateTime aExecutionDT,
                                             final boolean bSuccess,
                                             @Nullable final String sErrorMessage)
  {
    return new SystemMigrationResult (sMigrationID, aExecutionDT, bSuccess, sErrorMessage);
  }
}
