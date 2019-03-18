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
package com.helger.photon.core;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.lang.ClassHelper;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link WebSiteResourceBundleManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonAppManager extends AbstractGlobalSingleton
{
  public static final String WEBSITE_RESOURCE_BUNDLES_XML = "resource-bundles.xml";

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonAppManager.class);

  private WebSiteResourceBundleManager m_aWebSiteResourceBundleMgr;

  @Deprecated
  @UsedViaReflection
  public PhotonAppManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aWebSiteResourceBundleMgr = new WebSiteResourceBundleManager (WEBSITE_RESOURCE_BUNDLES_XML);

      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Throwable t)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), t);
    }
  }

  @Nonnull
  public static PhotonAppManager getInstance ()
  {
    return getGlobalSingleton (PhotonAppManager.class);
  }

  @Nonnull
  public static WebSiteResourceBundleManager getWebSiteResourceBundleMgr ()
  {
    return getInstance ().m_aWebSiteResourceBundleMgr;
  }
}
