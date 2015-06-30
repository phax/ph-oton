/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.migration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.id.IHasID;
import com.helger.commons.state.ISuccessIndicator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;

/**
 * Represents the result of a single system migration.
 *
 * @author Philip Helger
 */
@Immutable
public class SystemMigrationResult implements IHasID <String>, ISuccessIndicator
{
  private final String m_sMigrationID;
  private final LocalDateTime m_aExecutionDT;
  private final boolean m_bSuccess;
  private final String m_sErrorMessage;

  protected SystemMigrationResult (@Nonnull @Nonempty final String sMigrationID,
                                   @Nonnull final LocalDateTime aExecutionDT,
                                   final boolean bSuccess,
                                   @Nullable final String sErrorMessage)
  {
    m_sMigrationID = ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    m_aExecutionDT = ValueEnforcer.notNull (aExecutionDT, "ExecutionDT");
    m_bSuccess = bSuccess;
    m_sErrorMessage = sErrorMessage;
  }

  @Nonnull
  public String getID ()
  {
    return m_sMigrationID;
  }

  @Nonnull
  public LocalDateTime getExecutionDateTime ()
  {
    return m_aExecutionDT;
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public boolean isFailure ()
  {
    return !m_bSuccess;
  }

  @Nullable
  public String getErrorMessage ()
  {
    return m_sErrorMessage;
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
    return new ToStringGenerator (this).append ("ID", m_sMigrationID)
                                       .append ("executionDT", m_aExecutionDT)
                                       .append ("success", m_bSuccess)
                                       .append ("errorMsg", m_sErrorMessage)
                                       .toString ();
  }

  @Nonnull
  public static SystemMigrationResult createSuccess (@Nonnull @Nonempty final String sMigrationID)
  {
    return new SystemMigrationResult (sMigrationID, PDTFactory.getCurrentLocalDateTime (), true, null);
  }

  @Nonnull
  public static SystemMigrationResult createFailure (@Nonnull @Nonempty final String sMigrationID,
                                                     @Nonnull final String sErrorMsg)
  {
    return new SystemMigrationResult (sMigrationID, PDTFactory.getCurrentLocalDateTime (), false, sErrorMsg);
  }
}
