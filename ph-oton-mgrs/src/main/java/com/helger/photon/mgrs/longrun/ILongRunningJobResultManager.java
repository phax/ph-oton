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
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsList;

/**
 * Base interface for a long running job result manager
 *
 * @author Philip Helger
 * @since 10.2.0
 */
public interface ILongRunningJobResultManager
{
  void addResult (@NonNull final LongRunningJobData aJobData);

  @NonNull
  @ReturnsMutableCopy
  ICommonsList <LongRunningJobData> getAllJobResults ();

  @Nullable
  LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID);

  /**
   * Delete the job result with the provided ID. Note: if the job result refers to an external
   * resource - like a {@link ELongRunningJobResultType#FILE} - that resource is not touched by this
   * method and must be deleted separately.
   *
   * @param sJobResultID
   *        The ID of the job result to be deleted. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the job result was deleted, {@link EChange#UNCHANGED} if no
   *         such job result exists.
   * @since 10.3.2
   */
  @NonNull
  EChange deleteResult (@Nullable String sJobResultID);
}
