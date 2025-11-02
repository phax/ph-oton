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
package com.helger.photon.core.mgr;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.exception.InitializationException;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.photon.core.favorites.FavoriteManager;
import com.helger.photon.core.longrun.LongRunningJobManager;
import com.helger.photon.core.longrun.LongRunningJobResultManager;
import com.helger.photon.core.sysmigration.SystemMigrationManager;
import com.helger.photon.core.systemmsg.SystemMessageManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link FavoriteManager}</li>
 * <li>{@link LongRunningJobManager}</li>
 * <li>{@link LongRunningJobResultManager}</li>
 * <li>{@link SystemMessageManager}</li>
 * <li>{@link SystemMigrationManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonBasicManager extends AbstractGlobalSingleton
{
  public static final String DIRECTORY_HTML = "html/";
  public static final String FAVORITES_XML = "favorites.xml";
  public static final String LONG_RUNNING_JOB_RESULTS_XML = "long-running-job-results.xml";
  public static final String SYSTEM_MIGRATIONS_XML = "systemmigrations.xml";
  public static final String SYSTEM_MESSAGE_XML = "systemmessage.xml";

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonBasicManager.class);

  private FavoriteManager m_aFavoriteManager;
  private LongRunningJobManager m_aLongRunningJobMgr;
  private LongRunningJobResultManager m_aLongRunningJobResultMgr;
  private SystemMessageManager m_aSystemMessageMgr;
  private SystemMigrationManager m_aSystemMigrationMgr;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonBasicManager ()
  {}

  @Override
  protected void onAfterInstantiation (@NonNull final IScope aScope)
  {
    try
    {
      m_aFavoriteManager = new FavoriteManager (FAVORITES_XML);

      m_aSystemMigrationMgr = new SystemMigrationManager (SYSTEM_MIGRATIONS_XML);

      m_aSystemMessageMgr = new SystemMessageManager (SYSTEM_MESSAGE_XML);

      m_aLongRunningJobResultMgr = new LongRunningJobResultManager (LONG_RUNNING_JOB_RESULTS_XML);
      m_aLongRunningJobMgr = new LongRunningJobManager (m_aLongRunningJobResultMgr);

      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Exception ex)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), ex);
    }
  }

  @NonNull
  public static PhotonBasicManager getInstance ()
  {
    return getGlobalSingleton (PhotonBasicManager.class);
  }

  @NonNull
  public static FavoriteManager getFavoriteManager ()
  {
    return getInstance ().m_aFavoriteManager;
  }

  @NonNull
  public static SystemMigrationManager getSystemMigrationMgr ()
  {
    return getInstance ().m_aSystemMigrationMgr;
  }

  @NonNull
  public static SystemMessageManager getSystemMessageMgr ()
  {
    return getInstance ().m_aSystemMessageMgr;
  }

  @NonNull
  public static LongRunningJobResultManager getLongRunningJobResultMgr ()
  {
    return getInstance ().m_aLongRunningJobResultMgr;
  }

  @NonNull
  public static LongRunningJobManager getLongRunningJobMgr ()
  {
    return getInstance ().m_aLongRunningJobMgr;
  }
}
