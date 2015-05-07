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
package com.helger.photon.basic.longrun;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;

@ThreadSafe
public class LongRunningJobResultManager extends AbstractSimpleDAO
{
  private static final String ELEMENT_ROOT = "root";
  private static final String ELEMENT_JOBDATA = "jobdata";

  private final Map <String, LongRunningJobData> m_aMap = new LinkedHashMap <String, LongRunningJobData> ();

  public LongRunningJobResultManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    if (aDoc != null)
      for (final IMicroElement eJobData : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_JOBDATA))
        _internalAdd (MicroTypeConverter.convertToNative (eJobData, LongRunningJobData.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final LongRunningJobData aJobData : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aJobData, ELEMENT_JOBDATA));
    return aDoc;
  }

  private void _internalAdd (@Nonnull final LongRunningJobData aJobData)
  {
    if (aJobData == null)
      throw new NullPointerException ("JobData");

    m_aMap.put (aJobData.getID (), aJobData);
  }

  public void addResult (@Nonnull final LongRunningJobData aJobData)
  {
    if (aJobData == null)
      throw new NullPointerException ("jobData");
    if (!aJobData.isEnded ())
      throw new IllegalArgumentException ("Passed jobData is not yet finished");

    m_aRWLock.writeLock ().lock ();
    try
    {
      _internalAdd (aJobData);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <LongRunningJobData> getAllJobResults ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aMap.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sJobResultID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }
}
