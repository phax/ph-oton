/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.appid;

import javax.annotation.Nonnull;

import com.helger.commons.string.StringHelper;
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
    final PhotonSessionState aSessionState = PhotonSessionState.getInstanceIfInstantiated ();

    // Get App ID
    String sAppID = null;
    if (aSessionState != null)
      sAppID = aSessionState.getLastApplicationID ();
    if (StringHelper.hasNoText (sAppID))
      sAppID = PhotonGlobalState.getInstance ().getDefaultApplicationID ();

    if (StringHelper.hasText (sAppID))
    {
      PhotonRequestState aRequestState = null;
      if (aSessionState != null)
      {
        final PhotonSessionStatePerApp aSessionStatePerApp = aSessionState.state (sAppID);
        // Is e.g. empty if a new session state was created!
        if (aSessionStatePerApp.isNotEmpty ())
          aRequestState = new PhotonRequestState (aSessionStatePerApp);
      }

      // Global state as last resort
      if (aRequestState == null)
        aRequestState = new PhotonRequestState (PhotonGlobalState.state (sAppID));

      // Remember in request
      RequestSettings.setRequestState (aRequestScope, sAppID, aRequestState);
    }
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
