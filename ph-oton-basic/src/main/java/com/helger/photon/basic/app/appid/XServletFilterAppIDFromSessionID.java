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
        final PhotonSessionStatePerApp aSessionStatePerApp = aSession.state (sAppID);
        PhotonRequestState aRequestState;
        if (aSessionStatePerApp.isEmpty ())
          aRequestState = new PhotonRequestState (PhotonGlobalState.state (sAppID));
        else
          aRequestState = new PhotonRequestState (aSessionStatePerApp);

        RequestSettings.setRequestState (aRequestScope, sAppID, aRequestState);
      }
    }
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
