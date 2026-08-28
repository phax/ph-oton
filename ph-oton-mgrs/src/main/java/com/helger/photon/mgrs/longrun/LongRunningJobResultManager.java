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
package com.helger.photon.mgrs.longrun;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsList;
import com.helger.dao.DAOException;
import com.helger.photon.io.dao.AbstractPhotonMapBasedWALDAO;

@ThreadSafe
public class LongRunningJobResultManager extends AbstractPhotonMapBasedWALDAO <LongRunningJobData, LongRunningJobData>
                                         implements
                                         ILongRunningJobResultManager
{
  public LongRunningJobResultManager (@NonNull @Nonempty final String sFilename) throws DAOException
  {
    super (LongRunningJobData.class, sFilename, new InitSettings <LongRunningJobData> ().setOrderedMapSupplier ());
  }

  public void addResult (@NonNull final LongRunningJobData aJobData)
  {
    ValueEnforcer.notNull (aJobData, "JobData");
    if (!aJobData.isEnded ())
      throw new IllegalArgumentException ("Passed jobData is not yet finished");

    m_aRWLock.writeLocked (() -> internalCreateItem (aJobData));
  }

  @Nullable
  private static Predicate <LongRunningJobData> _getJobTypeFilter (@Nullable final String sJobType)
  {
    if (sJobType == null)
      return null;
    return x -> sJobType.equals (x.getJobType ());
  }

  public void forEachJobResult (@Nullable final String sJobType,
                                @NonNull final Consumer <? super LongRunningJobData> aConsumer)
  {
    findAll (_getJobTypeFilter (sJobType), aConsumer);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <LongRunningJobData> getAllJobResults (@Nullable final String sJobType)
  {
    return getAll (_getJobTypeFilter (sJobType));
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <LongRunningJobData> getAllJobResults ()
  {
    return getAll ();
  }

  @Nullable
  public LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID)
  {
    return getOfID (sJobResultID);
  }

  @NonNull
  public EChange deleteResult (@Nullable final String sJobResultID)
  {
    if (sJobResultID == null)
      return EChange.UNCHANGED;

    return m_aRWLock.writeLockedGet (() -> EChange.valueOf (internalDeleteItem (sJobResultID) != null));
  }
}
