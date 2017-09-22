package com.helger.photon.basic.app.appid;

import javax.annotation.Nonnull;

import com.helger.web.scope.IRequestWebScope;
import com.helger.xservlet.filter.IXServletHighLevelFilter;

/**
 * XServlet filter responsible for remembering the application ID in the current
 * request based on the existing session data
 *
 * @author Philip Helger
 */
public final class XServletFilterAppIDFromSessionID implements IXServletHighLevelFilter
{
  public XServletFilterAppIDFromSessionID ()
  {}

  public void beforeRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // Set all fields from session
    final PhotonSessionState aSession = PhotonSessionState.getInstanceIfInstantiated ();
    if (aSession != null)
    {
      final String sAppID = aSession.getLastApplicationID ();
      if (sAppID != null)
      {
        PhotonState aState = aSession.state (sAppID);
        if (aState.isEmpty ())
          aState = PhotonGlobalState.getInstance ().state (sAppID);
        RequestSettings.setRequestState (aRequestScope, sAppID, aState);
      }
    }
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
