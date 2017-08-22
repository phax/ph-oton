/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.job;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.quartz.IJobExecutionContext;
import com.helger.quartz.JobDataMap;
import com.helger.scope.mgr.ScopeManager;
import com.helger.web.scope.util.AbstractScopeAwareJob;

/**
 * Base class for ph-oton jobs
 *
 * @author Philip Helger
 */
public abstract class AbstractPhotonJob extends AbstractScopeAwareJob
{
  /**
   * This key of the job-data map must be present and must contain the
   * application ID if no fixed Application ID is provided in the constructor.
   *
   * @since 7.0.4
   */
  public static final String JOB_DATA_ATTR_APPLICATION_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL +
                                                            "photon.job.appid";

  private final String m_sFixedAppID;

  protected AbstractPhotonJob ()
  {
    this ((String) null);
  }

  protected AbstractPhotonJob (@Nullable final String sFixedAppID)
  {
    m_sFixedAppID = sFixedAppID;
  }

  @Override
  @Nonnull
  @Nonempty
  protected final String getApplicationScopeID (@Nonnull final JobDataMap aJobDataMap,
                                                @Nonnull final IJobExecutionContext aContext)
  {
    if (StringHelper.hasText (m_sFixedAppID))
      return m_sFixedAppID;

    final String sAppID = aJobDataMap.getString (JOB_DATA_ATTR_APPLICATION_ID);
    if (StringHelper.hasNoText (sAppID))
      throw new IllegalStateException ("JobDataMap is missing entry for '" +
                                       JOB_DATA_ATTR_APPLICATION_ID +
                                       "' - see " +
                                       AbstractPhotonJob.class.getName ());
    return sAppID;
  }
}
