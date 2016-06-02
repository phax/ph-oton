/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.go;

import java.util.Enumeration;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemExternal;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.basic.app.request.IRequestManager;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Default servlet that performs URL redirects based on {@link GoMappingManager}
 * .
 *
 * @author Philip Helger
 */
public class GoServlet extends AbstractUnifiedResponseServlet
{
  public static final String SERVLET_DEFAULT_NAME = "go";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  private static final Logger s_aLogger = LoggerFactory.getLogger (GoServlet.class);
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsError = StatisticsManager.getKeyedCounterHandler (GoServlet.class.getName () +
                                                                                                                       "$error");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsOK = StatisticsManager.getKeyedCounterHandler (GoServlet.class.getName () +
                                                                                                                    "$ok");
  private static final boolean s_bIsRegistered = ServletStatusManager.isServletRegistered (GoServlet.class);

  public GoServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return s_bIsRegistered;
  }

  @Override
  @Nonnull
  @Nonempty
  protected String getApplicationID ()
  {
    return CApplication.APP_ID_PUBLIC;
  }

  @Override
  @Nonnull
  @CodingStyleguideUnaware
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return ALLOWED_METHDOS_GET;
  }

  /**
   * Resolve the passed go-mapping key to an {@link GoMappingItem}.
   *
   * @param sKey
   *        The key to be resolved. Never <code>null</code> but may be empty.
   * @return <code>null</code> if no such go-mapping item exists.
   */
  @Nullable
  @OverrideOnDemand
  protected GoMappingItem getResolvedGoMappingItem (@Nonnull final String sKey)
  {
    return PhotonCoreManager.getGoMappingMgr ().getItemOfKey (sKey);
  }

  /**
   * Get the URL to redirect in case an invalid go mapping key was provided.
   *
   * @param aRequestScope
   *        The current request scope.
   * @param sKey
   *        The key that was searched.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected SimpleURL getURLForNonExistingItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                @Nonnull final String sKey)
  {
    return new SimpleURL (aRequestScope.getFullContextPath ());
  }

  /**
   * @return The menu tree to be used for item resolving.
   */
  @Nullable
  @OverrideOnDemand
  protected IMenuTree getMenuTree ()
  {
    return ApplicationMenuTree.getTree ();
  }

  /**
   * Callback method that allows for customizing the result URL.
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param sKey
   *        The go mapping key that was resolved. Never <code>null</code>.
   * @param aTargetURL
   *        The resolved target URL to be manipulated. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void modifyResultURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                  @Nonnull final String sKey,
                                  @Nonnull final SimpleURL aTargetURL)
  {}

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // cut the leading "/" and strip of any ";jsessionid="!
    String sKey = aRequestScope.getPathWithinServlet ();
    if (sKey.length () > 0)
      sKey = sKey.substring (1);

    SimpleURL aTargetURL = null;
    final GoMappingItem aGoItem = getResolvedGoMappingItem (sKey);
    if (aGoItem == null)
    {
      s_aLogger.warn ("No such go-mapping item '" + sKey + "'");
      // Goto start page
      aTargetURL = getURLForNonExistingItem (aRequestScope, sKey);
      s_aStatsError.increment (sKey);
    }
    else
    {
      // Base URL
      if (aGoItem.isInternal ())
      {
        final IMenuTree aMenuTree = getMenuTree ();
        if (aMenuTree != null)
        {
          // If it is an internal menu item, check if this internal item is an
          // "external menu item" and if so, directly use the URL of the
          // external menu item
          final IRequestManager aARM = ApplicationRequestManager.getRequestMgr ();
          final String sTargetMenuItemID = aARM.getMenuItemFromURL (aGoItem.getTargetURL ());

          final IMenuObject aMenuObj = aMenuTree.getItemDataWithID (sTargetMenuItemID);
          if (aMenuObj instanceof IMenuItemExternal)
          {
            aTargetURL = new SimpleURL (((IMenuItemExternal) aMenuObj).getURL ());
          }
        }
      }
      if (aTargetURL == null)
      {
        // Default case - use target link from go-mapping
        aTargetURL = aGoItem.getTargetURL ();
      }

      // Callback
      modifyResultURL (aRequestScope, sKey, aTargetURL);

      s_aStatsOK.increment (sKey);
    }

    // Append all request parameters of this request
    // Don't use the request attributes, as there might be more of them
    final Enumeration <?> aEnum = aRequestScope.getRequest ().getParameterNames ();
    while (aEnum.hasMoreElements ())
    {
      final String sParamName = (String) aEnum.nextElement ();
      final String [] aParamValues = aRequestScope.getRequest ().getParameterValues (sParamName);
      if (aParamValues != null)
        for (final String sParamValue : aParamValues)
          aTargetURL.add (sParamName, sParamValue);
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Following go-mapping item '" + sKey + "' to " + aTargetURL.getAsStringWithEncodedParameters ());
    else
      if (GlobalDebug.isDebugMode ())
        s_aLogger.info ("Following go-mapping item '" +
                        sKey +
                        "' to " +
                        aTargetURL.getAsStringWithEncodedParameters ());

    // Main redirect :)
    aUnifiedResponse.setRedirect (aTargetURL);
  }
}
