/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.util.Map;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.menu.IMenuItemExternal;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.requestparam.RequestParameterManager;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * Default servlet that performs URL redirects based on {@link GoMappingManager}
 * .
 *
 * @author Philip Helger
 */
public class GoXServletHandler implements IXServletSimpleHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (GoXServletHandler.class);
  private static final IMutableStatisticsHandlerKeyedCounter STATS_ERROR = StatisticsManager.getKeyedCounterHandler (GoXServletHandler.class.getName () +
                                                                                                                     "$error");
  private static final IMutableStatisticsHandlerKeyedCounter STATS_OK = StatisticsManager.getKeyedCounterHandler (GoXServletHandler.class.getName () +
                                                                                                                  "$ok");
  private final Function <? super IRequestWebScopeWithoutResponse, ? extends IMenuTree> m_aMenuTreeSupplier;

  public GoXServletHandler (@Nonnull final Function <? super IRequestWebScopeWithoutResponse, ? extends IMenuTree> aMenuTreeSupplier)
  {
    ValueEnforcer.notNull (aMenuTreeSupplier, "MenuTreeSupplier");
    m_aMenuTreeSupplier = aMenuTreeSupplier;
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

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
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
      LOGGER.warn ("No such go-mapping item '" + sKey + "'");
      // Goto start page
      aTargetURL = getURLForNonExistingItem (aRequestScope, sKey);
      STATS_ERROR.increment (sKey);
    }
    else
    {
      // Base URL
      if (aGoItem.isInternal ())
      {
        final IMenuTree aMenuTree = m_aMenuTreeSupplier.apply (aRequestScope);
        if (aMenuTree != null)
        {
          // If it is an internal menu item, check if this internal item is an
          // "external menu item" and if so, directly use the URL of the
          // external menu item
          final String sTargetMenuItemID = RequestParameterManager.getInstance ()
                                                                  .getMenuItemFromURL (aGoItem.getTargetURL (),
                                                                                       aMenuTree);

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

      STATS_OK.increment (sKey);
    }
    // Append all request parameters of this request
    // FIXME crash with multipart request?
    for (final Map.Entry <String, Object> aEntry : aRequestScope.params ().entrySet ())
    {
      final String sParamName = aEntry.getKey ();
      final Object aParamValue = aEntry.getValue ();
      if (aParamValue instanceof String)
        aTargetURL.add (sParamName, (String) aParamValue);
      else
        if (aParamValue instanceof String [])
          for (final String sParamValue : (String []) aParamValue)
            aTargetURL.add (sParamName, sParamValue);
    }
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Following go-mapping item '" + sKey + "' to " + aTargetURL.getAsStringWithEncodedParameters ());
    else
      if (GlobalDebug.isDebugMode ())
        LOGGER.info ("Following go-mapping item '" + sKey + "' to " + aTargetURL.getAsStringWithEncodedParameters ());

    // Main redirect :)
    aUnifiedResponse.setRedirect (aTargetURL);
  }
}
