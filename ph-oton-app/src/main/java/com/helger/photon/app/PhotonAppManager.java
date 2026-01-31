/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.app;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.exception.InitializationException;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.photon.app.resource.WebSiteResourceBundleManager;
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

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonAppManager ()
  {}

  @Override
  protected void onAfterInstantiation (@NonNull final IScope aScope)
  {
    try
    {
      m_aWebSiteResourceBundleMgr = new WebSiteResourceBundleManager (WEBSITE_RESOURCE_BUNDLES_XML);

      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Exception ex)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), ex);
    }
  }

  @NonNull
  public static PhotonAppManager getInstance ()
  {
    return getGlobalSingleton (PhotonAppManager.class);
  }

  @NonNull
  public static WebSiteResourceBundleManager getWebSiteResourceBundleMgr ()
  {
    return getInstance ().m_aWebSiteResourceBundleMgr;
  }
}
