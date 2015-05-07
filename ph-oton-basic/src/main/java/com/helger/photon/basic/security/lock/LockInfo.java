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
package com.helger.photon.basic.security.lock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;

/**
 * Default implementation of the {@link ILockInfo} interface.
 *
 * @author Philip Helger
 */
@Immutable
public final class LockInfo implements ILockInfo
{
  private final String m_sUserID;
  private final DateTime m_aLockTime;

  public LockInfo (@Nonnull @Nonempty final String sUserID)
  {
    m_sUserID = ValueEnforcer.notEmpty (sUserID, "UserID");
    m_aLockTime = PDTFactory.getCurrentDateTime ();
  }

  @Nonnull
  @Nonempty
  public String getLockUserID ()
  {
    return m_sUserID;
  }

  @Nonnull
  public DateTime getLockDateTime ()
  {
    return m_aLockTime;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final LockInfo rhs = (LockInfo) o;
    return m_sUserID.equals (rhs.m_sUserID) && m_aLockTime.equals (rhs.m_aLockTime);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sUserID).append (m_aLockTime).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("userID", m_sUserID).append ("lockTime", m_aLockTime).toString ();
  }
}
