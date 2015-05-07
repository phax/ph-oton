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
package com.helger.webbasics.requesttrack;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.appbasics.security.audit.AuditUtils;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * A simple implementation of {@link IParallelRunningRequestCallback} simply
 * auditing such events.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
public class AuditingParallelRunningRequestCallback implements IParallelRunningRequestCallback
{
  public AuditingParallelRunningRequestCallback ()
  {}

  public void onParallelRunningRequests (@Nonnegative final int nParallelRequests,
                                         @Nonnull @Nonempty final List <TrackedRequest> aRequests)
  {
    final List <String> aURLs = new ArrayList <String> ();
    for (final TrackedRequest aRequest : aRequests)
      aURLs.add (aRequest.getRequestScope ().getURL ());
    AuditUtils.onAuditExecuteSuccess ("parallel-running-requests", Integer.valueOf (nParallelRequests), aURLs);
  }

  public void onParallelRunningRequestsBelowLimit ()
  {
    AuditUtils.onAuditExecuteSuccess ("parallel-running-requests", "back-to-normal");
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
