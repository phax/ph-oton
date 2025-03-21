/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.longrun;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.text.IMultilingualText;

/**
 * This is a marker interface that identifies long running background jobs.
 *
 * @author Philip Helger
 */
public interface ILongRunningJob
{
  /**
   * @return The internal ID of the long running job type. This should not be an
   *         instance ID.
   */
  @Nonnull
  @Nonempty
  String getJobID ();

  /**
   * @return A description of this long running job. May not be
   *         <code>null</code>.
   */
  @Nonnull
  IMultilingualText getJobDescription ();

  /**
   * @return The results of this job for asynchronous retrieval by the user.
   *         Never <code>null</code>.
   */
  @Nonnull
  LongRunningJobResult createLongRunningJobResult ();
}
