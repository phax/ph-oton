/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.basic;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.concurrent.Immutable;

import com.helger.photon.basic.xservlet.AuditingLongRunningRequestCallback;
import com.helger.photon.basic.xservlet.AuditingParallelRunningRequestCallback;
import com.helger.xservlet.requesttrack.RequestTracker;

@Immutable
public final class PhotonBasic
{
  private static final AtomicBoolean s_aRegisteredRequestTracker = new AtomicBoolean (false);

  private PhotonBasic ()
  {}

  public static void startUp ()
  {
    PhotonAppInit.startUp ();

    // Ensure this is done only once
    if (!s_aRegisteredRequestTracker.getAndSet (true))
    {
      RequestTracker.longRunningRequestCallbacks ().add (AuditingLongRunningRequestCallback.INSTANCE);
      RequestTracker.parallelRunningRequestCallbacks ().add (AuditingParallelRunningRequestCallback.INSTANCE);
    }
  }

  public static void shutdown ()
  {
    if (s_aRegisteredRequestTracker.getAndSet (false))
    {
      RequestTracker.longRunningRequestCallbacks ().removeObject (AuditingLongRunningRequestCallback.INSTANCE);
      RequestTracker.parallelRunningRequestCallbacks ().removeObject (AuditingParallelRunningRequestCallback.INSTANCE);
    }

    PhotonAppInit.shutdown ();
  }
}
