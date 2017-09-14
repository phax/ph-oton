package com.helger.photon.basic.app.appid;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;

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

  public void beforeRequest (@Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // Set all fields from session
    final PhotonSessionState aSession = PhotonSessionState.getInstance ();
    final String sAppID = aSession.getLastApplicationID ();
    final PhotonState aSessionState = aSession.state (sAppID);
    RequestSettings.set (aRequestScope, sAppID, aSessionState);
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // empty
  }
}
