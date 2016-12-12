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
package com.helger.photon.core.job;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.photon.core.app.CApplication;
import com.helger.quartz.JobDataMap;
import com.helger.web.scope.util.AbstractScopeAwareJob;
import com.helger.quartz.IJobExecutionContext;

/**
 * Base class for ph-oton jobs
 *
 * @author Philip Helger
 */
public abstract class AbstractPhotonJob extends AbstractScopeAwareJob
{
  // Default to be on the safe side
  private static String s_sAppID = CApplication.APP_ID_SECURE;

  protected static void setApplicationScopeID (@Nonnull @Nonempty final String sApplicationID)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    if (s_sAppID == null)
      s_sAppID = sApplicationID;
  }

  @Override
  protected final String getApplicationScopeID (@Nonnull final JobDataMap aJobDataMap,
                                                @Nonnull final IJobExecutionContext aContext)
  {
    return s_sAppID;
  }
}
