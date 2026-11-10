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

import java.util.concurrent.atomic.AtomicBoolean;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.exception.InitializationException;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.dao.DAOException;
import com.helger.photon.app.resource.IWebSiteResourceBundleManager;
import com.helger.photon.app.resource.WebSiteResourceBundleManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * The meta system manager encapsulates all managers that are located in this project. Currently the
 * contained managers are:
 * <ul>
 * <li>{@link IWebSiteResourceBundleManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonAppManager extends AbstractGlobalSingleton
{
  /**
   * Manager factory interface.
   *
   * @author Philip Helger
   * @since 10.8.0
   */
  public interface IFactory
  {
    /**
     * @return A new instance of {@link IWebSiteResourceBundleManager}.
     * @throws Exception
     *         In case of error
     */
    @NonNull
    IWebSiteResourceBundleManager createWebSiteResourceBundleMgr () throws Exception;
  }

  /**
   * Default {@link IFactory} implementation using XML backend.
   *
   * @author Philip Helger
   * @since 10.8.0
   */
  @Immutable
  public static class FactoryXML implements IFactory
  {
    @SuppressWarnings ("hiding")
    public static final String WEBSITE_RESOURCE_BUNDLES_XML = "resource-bundles.xml";

    public @NonNull IWebSiteResourceBundleManager createWebSiteResourceBundleMgr () throws DAOException
    {
      return new WebSiteResourceBundleManager (WEBSITE_RESOURCE_BUNDLES_XML);
    }
  }

  /**
   * @deprecated Use {@link FactoryXML#WEBSITE_RESOURCE_BUNDLES_XML} instead
   */
  @Deprecated (forRemoval = true, since = "10.8.0")
  public static final String WEBSITE_RESOURCE_BUNDLES_XML = FactoryXML.WEBSITE_RESOURCE_BUNDLES_XML;

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonAppManager.class);
  private static final AtomicBoolean INITED = new AtomicBoolean (false);

  /** The global factory to be used. */
  private static IFactory s_aFactory = new FactoryXML ();

  /**
   * @return <code>true</code> if the {@link PhotonAppManager} was already initialized,
   *         <code>false</code> if not.
   * @since 10.8.0
   */
  public static boolean isAlreadyInitialized ()
  {
    return INITED.get ();
  }

  /**
   * @return The currently installed factory for app managers. By default an instance of
   *         {@link FactoryXML} is returned.
   * @see #setFactory(IFactory)
   * @since 10.8.0
   */
  @NonNull
  public static IFactory getFactory ()
  {
    return s_aFactory;
  }

  /**
   * Set a new global factory for app managers. This must be called before the
   * {@link PhotonAppManager} is initialized.
   *
   * @param aFactory
   *        The new factory to be set. May not be <code>null</code>.
   * @since 10.8.0
   */
  public static void setFactory (@NonNull final IFactory aFactory)
  {
    ValueEnforcer.notNull (aFactory, "Factory");
    if (isAlreadyInitialized ())
    {
      LOGGER.error ("Setting the PhotonAppManager factory after initialization has no effect and is ignored!");
    }
    else
    {
      s_aFactory = aFactory;
      LOGGER.info ("Setting the PhotonAppManager to " + aFactory.toString ());
    }
  }

  private IWebSiteResourceBundleManager m_aWebSiteResourceBundleMgr;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonAppManager ()
  {}

  @Override
  protected void onAfterInstantiation (@NonNull final IScope aScope)
  {
    try
    {
      m_aWebSiteResourceBundleMgr = s_aFactory.createWebSiteResourceBundleMgr ();

      INITED.set (true);
      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Exception ex)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), ex);
    }
  }

  @Override
  protected void onDestroy (@NonNull final IScope aScopeInDestruction)
  {
    INITED.set (false);
  }

  @NonNull
  public static PhotonAppManager getInstance ()
  {
    return getGlobalSingleton (PhotonAppManager.class);
  }

  @NonNull
  public static IWebSiteResourceBundleManager getWebSiteResourceBundleMgr ()
  {
    return getInstance ().m_aWebSiteResourceBundleMgr;
  }
}
