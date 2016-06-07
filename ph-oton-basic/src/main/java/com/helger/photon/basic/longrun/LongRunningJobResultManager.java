/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsLinkedHashMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsOrderedMap;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

@ThreadSafe
public class LongRunningJobResultManager extends AbstractSimpleDAO
{
  private static final String ELEMENT_ROOT = "root";
  private static final String ELEMENT_JOBDATA = "jobdata";

  private final ICommonsOrderedMap <String, LongRunningJobData> m_aMap = new CommonsLinkedHashMap <> ();

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
    for (final LongRunningJobData aJobData : m_aMap.getSortedByKey (Comparator.naturalOrder ()).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aJobData, ELEMENT_JOBDATA));
    return aDoc;
  }

  private void _internalAdd (@Nonnull final LongRunningJobData aJobData)
  {
    ValueEnforcer.notNull (aJobData, "JobData");

    m_aMap.put (aJobData.getID (), aJobData);
  }

  public void addResult (@Nonnull final LongRunningJobData aJobData)
  {
    ValueEnforcer.notNull (aJobData, "JobData");
    if (!aJobData.isEnded ())
      throw new IllegalArgumentException ("Passed jobData is not yet finished");

    m_aRWLock.writeLocked ( () -> {
      _internalAdd (aJobData);
      markAsChanged ();
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <LongRunningJobData> getAllJobResults ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfValues ());
  }

  @Nullable
  public LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID)
  {
    return m_aRWLock.readLocked ( () -> m_aMap.get (sJobResultID));
  }
}
