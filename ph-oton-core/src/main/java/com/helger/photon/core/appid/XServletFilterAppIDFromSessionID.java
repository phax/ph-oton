/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.appid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.string.StringHelper;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.filter.IXServletHighLevelFilter;

import jakarta.annotation.Nonnull;

/**
 * XServlet filter responsible for remembering the application ID in the current
 * request based on the existing session data
 *
 * @author Philip Helger
 */
public final class XServletFilterAppIDFromSessionID implements IXServletHighLevelFilter
{
  private static final Logger LOGGER = LoggerFactory.getLogger (XServletFilterAppIDFromSessionID.class);

  public XServletFilterAppIDFromSessionID ()
  {}

  public static void setStatePerApp (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
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
    else
    {
      LOGGER.warn ("No application ID is present - please check if you really need this filter");
    }
  }

  public void beforeRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    setStatePerApp (aRequestScope);
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
