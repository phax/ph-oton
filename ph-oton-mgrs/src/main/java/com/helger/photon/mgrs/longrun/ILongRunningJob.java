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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.text.IMultilingualText;

/**
 * This is a marker interface that identifies long running background jobs.
 *
 * @author Philip Helger
 */
public interface ILongRunningJob
{
  /**
   * The maximum string length of the job type.
   *
   * @since 10.4.0
   */
  int JOB_TYPE_MAX_LENGTH = 100;

  /**
   * @return The type of this long running job. This is meant to be a coarse grained category, that
   *         groups multiple jobs together - like <code>import</code> or <code>export</code> - and
   *         that can be used to filter the job results of an {@link ILongRunningJobResultManager}.
   *         May not be longer than {@link #JOB_TYPE_MAX_LENGTH} characters. Every single job
   *         execution is identified by the globally unique ID that
   *         {@link LongRunningJobManager#onStartJob(ILongRunningJob, String)} creates and that is
   *         available as {@link LongRunningJobData#getID()}.
   * @since 10.4.0
   */
  @NonNull
  @Nonempty
  String getJobType ();

  /**
   * @return A description of this long running job. May not be <code>null</code>.
   */
  @NonNull
  IMultilingualText getJobDescription ();

  /**
   * @return The results of this job for asynchronous retrieval by the user. Never
   *         <code>null</code>.
   */
  @NonNull
  LongRunningJobResult createLongRunningJobResult ();
}
