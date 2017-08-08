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
package com.helger.photon.xservlet;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.xservlet.requesttrack.IParallelRunningRequestCallback;
import com.helger.xservlet.requesttrack.TrackedRequest;

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
    final ICommonsList <String> aURLs = new CommonsArrayList <> ();
    for (final TrackedRequest aRequest : aRequests)
      aURLs.add (aRequest.getRequestScope ().getURL ());
    AuditHelper.onAuditExecuteSuccess ("parallel-running-requests", Integer.valueOf (nParallelRequests), aURLs);
  }

  public void onParallelRunningRequestsBelowLimit ()
  {
    AuditHelper.onAuditExecuteSuccess ("parallel-running-requests", "back-to-normal");
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
