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

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ESuccess;
import com.helger.photon.mgrs.PhotonBasicManager;
import com.helger.text.IMultilingualText;

/**
 * Abstract implementation of {@link ILongRunningJob}
 *
 * @author Philip Helger
 * @since 8.2.6
 */
public abstract class AbstractLongRunningJobRunnable implements Runnable, ILongRunningJob
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractLongRunningJobRunnable.class);

  private final String m_sJobID;
  private final IMultilingualText m_aDesc;
  private final Supplier <String> m_aCurrentUserIDProvider;

  public AbstractLongRunningJobRunnable (@NonNull @Nonempty final String sJobID,
                                         @NonNull final IMultilingualText aJobDesc,
                                         @NonNull final Supplier <String> aCurrentUserIDProvider)
  {
    ValueEnforcer.notEmpty (sJobID, "JobID");
    ValueEnforcer.notNull (aJobDesc, "JobDesc");
    ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
    m_sJobID = sJobID;
    m_aDesc = aJobDesc;
    m_aCurrentUserIDProvider = aCurrentUserIDProvider;
  }

  @NonNull
  @Nonempty
  public String getJobID ()
  {
    return m_sJobID;
  }

  @NonNull
  public final IMultilingualText getJobDescription ()
  {
    return m_aDesc;
  }

  /**
   * @return The {@link LongRunningJobManager} to be used. May not return <code>null</code>.
   */
  @NonNull
  protected static final LongRunningJobManager getLongRunningJobManager ()
  {
    return PhotonBasicManager.getLongRunningJobMgr ();
  }

  public void run ()
  {
    final String sUserID = m_aCurrentUserIDProvider.get ();

    // Remember that a long running job is starting
    final String sLongRunningJobID = getLongRunningJobManager ().onStartJob (this, sUserID);

    LOGGER.info ("Starting long running job '" +
                 m_sJobID +
                 "' as user '" +
                 sUserID +
                 "' with unique ID '" +
                 sLongRunningJobID +
                 "'");
    try
    {
      // Create the main result
      final LongRunningJobResult aJobResult = createLongRunningJobResult ();

      // Mark the long running job as finished
      getLongRunningJobManager ().onEndJob (sLongRunningJobID, ESuccess.SUCCESS, aJobResult);

      LOGGER.info ("Finished long running job '" +
                   sLongRunningJobID +
                   "' with success and result of type " +
                   aJobResult.getType ());
    }
    catch (final Exception ex)
    {
      // Mark the long running job as finished
      getLongRunningJobManager ().onEndJob (sLongRunningJobID,
                                            ESuccess.FAILURE,
                                            LongRunningJobResult.createExceptionText (ex));

      LOGGER.info ("Error executing long running job '" + sLongRunningJobID + "'", ex);
    }
  }
}
