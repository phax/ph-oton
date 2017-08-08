package com.helger.photon.basic.config;

import javax.annotation.concurrent.Immutable;

import com.helger.photon.xservlet.AuditingLongRunningRequestCallback;
import com.helger.photon.xservlet.AuditingParallelRunningRequestCallback;
import com.helger.xservlet.requesttrack.RequestTracker;

@Immutable
public final class PhotonBasic
{
  private PhotonBasic ()
  {}

  public static void startUp ()
  {
    RequestTracker.longRunningRequestCallbacks ().add (new AuditingLongRunningRequestCallback ());
    RequestTracker.parallelRunningRequestCallbacks ().add (new AuditingParallelRunningRequestCallback ());
  }

  public static void shutDown ()
  {
    // empty
  }
}
