package com.helger.photon.basic;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.cleanup.CommonsCleanup;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.xservlet.AuditingLongRunningRequestCallback;
import com.helger.photon.xservlet.AuditingParallelRunningRequestCallback;
import com.helger.scope.ScopeCleanup;
import com.helger.xml.util.XMLCleanup;
import com.helger.xservlet.requesttrack.RequestTracker;

@Immutable
public final class PhotonBasic
{
  private static final AtomicBoolean s_aRegisteredRequestTracker = new AtomicBoolean (false);

  private PhotonBasic ()
  {}

  public static void startUp ()
  {
    // Ensure this is done only once
    if (!s_aRegisteredRequestTracker.getAndSet (true))
    {
      RequestTracker.longRunningRequestCallbacks ().add (new AuditingLongRunningRequestCallback ());
      RequestTracker.parallelRunningRequestCallbacks ().add (new AuditingParallelRunningRequestCallback ());
    }
  }

  public static void shutDown ()
  {
    // Init the base path once
    WebFileIO.resetPaths ();

    // Clear commons cache also manually - but at the end because it
    // is used in equals and hashCode implementations
    ScopeCleanup.cleanup ();
    XMLCleanup.cleanup ();
    CommonsCleanup.cleanup ();
  }
}
