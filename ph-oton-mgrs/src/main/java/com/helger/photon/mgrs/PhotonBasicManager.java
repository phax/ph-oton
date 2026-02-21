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
package com.helger.photon.mgrs;

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
import com.helger.photon.mgrs.longrun.ILongRunningJobResultManager;
import com.helger.photon.mgrs.longrun.LongRunningJobManager;
import com.helger.photon.mgrs.longrun.LongRunningJobResultManager;
import com.helger.photon.mgrs.sysmigration.ISystemMigrationManager;
import com.helger.photon.mgrs.sysmigration.SystemMigrationManager;
import com.helger.photon.mgrs.sysmsg.ISystemMessageManager;
import com.helger.photon.mgrs.sysmsg.SystemMessageManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * The meta system manager encapsulates all managers that are located in this project. Currently the
 * contained managers are:
 * <ul>
 * <li>{@link ISystemMigrationManager}</li>
 * <li>{@link ISystemMessageManager}</li>
 * <li>{@link ILongRunningJobResultManager}</li>
 * <li>{@link LongRunningJobManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonBasicManager extends AbstractGlobalSingleton
{
  /**
   * Manager factory interface.
   *
   * @author Philip Helger
   * @since 11.2.0
   */
  public interface IFactory
  {
    /**
     * @return A new instance of {@link ISystemMigrationManager}.
     * @throws Exception
     *         In case of error
     */
    @NonNull
    ISystemMigrationManager createSystemMigrationMgr () throws Exception;

    /**
     * @return A new instance of {@link ISystemMessageManager}.
     * @throws Exception
     *         In case of error
     */
    @NonNull
    ISystemMessageManager createSystemMessageMgr () throws Exception;

    /**
     * @return A new instance of {@link ILongRunningJobResultManager}.
     * @throws Exception
     *         In case of error
     */
    @NonNull
    ILongRunningJobResultManager createLongRunningJobResultMgr () throws Exception;
  }

  /**
   * Default {@link IFactory} implementation using XML backend.
   *
   * @author Philip Helger
   * @since 10.2.0
   */
  @Immutable
  public static class FactoryXML implements IFactory
  {
    public static final String SYSTEM_MIGRATIONS_XML = "systemmigrations.xml";
    public static final String SYSTEM_MESSAGE_XML = "systemmessage.xml";
    public static final String LONG_RUNNING_JOB_RESULTS_XML = "long-running-job-results.xml";

    public @NonNull ISystemMigrationManager createSystemMigrationMgr () throws DAOException
    {
      return new SystemMigrationManager (SYSTEM_MIGRATIONS_XML);
    }

    public @NonNull ISystemMessageManager createSystemMessageMgr () throws DAOException
    {
      return new SystemMessageManager (SYSTEM_MESSAGE_XML);
    }

    public @NonNull ILongRunningJobResultManager createLongRunningJobResultMgr () throws DAOException
    {
      return new LongRunningJobResultManager (LONG_RUNNING_JOB_RESULTS_XML);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonBasicManager.class);
  private static final AtomicBoolean INITED = new AtomicBoolean (false);

  /** The global factory to be used. */
  private static IFactory s_aFactory = new FactoryXML ();

  /**
   * @return <code>true</code> if the {@link PhotonBasicManager} was already initialized,
   *         <code>false</code> if not.
   * @since 10.2.0
   */
  public static boolean isAlreadyInitialized ()
  {
    return INITED.get ();
  }

  /**
   * @return The currently installed factory for security managers. By default an instance of
   *         {@link FactoryXML} is returned.
   * @see #setFactory(IFactory)
   */
  @NonNull
  public static IFactory getFactory ()
  {
    return s_aFactory;
  }

  /**
   * Set a new global factory for security managers.
   *
   * @param aFactory
   *        The new factory to be set. May not be <code>null</code>.
   */
  public static void setFactory (@NonNull final IFactory aFactory)
  {
    ValueEnforcer.notNull (aFactory, "Factory");
    if (isAlreadyInitialized ())
    {
      LOGGER.error ("Setting the PhotonBasicManager factory after initialization has no effect and is ignored!");
    }
    else
    {
      s_aFactory = aFactory;
      LOGGER.info ("Setting the PhotonBasicManager to " + aFactory.toString ());
    }
  }

  private ISystemMigrationManager m_aSystemMigrationMgr;
  private ISystemMessageManager m_aSystemMessageMgr;
  private ILongRunningJobResultManager m_aLongRunningJobResultMgr;
  private LongRunningJobManager m_aLongRunningJobMgr;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonBasicManager ()
  {}

  @Override
  protected void onAfterInstantiation (@NonNull final IScope aScope)
  {
    try
    {
      m_aSystemMigrationMgr = s_aFactory.createSystemMigrationMgr ();
      m_aSystemMessageMgr = s_aFactory.createSystemMessageMgr ();

      m_aLongRunningJobResultMgr = s_aFactory.createLongRunningJobResultMgr ();
      m_aLongRunningJobMgr = new LongRunningJobManager (m_aLongRunningJobResultMgr);

      INITED.set (true);
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
  public static ISystemMigrationManager getSystemMigrationMgr ()
  {
    return getInstance ().m_aSystemMigrationMgr;
  }

  @NonNull
  public static ISystemMessageManager getSystemMessageMgr ()
  {
    return getInstance ().m_aSystemMessageMgr;
  }

  @NonNull
  public static ILongRunningJobResultManager getLongRunningJobResultMgr ()
  {
    return getInstance ().m_aLongRunningJobResultMgr;
  }

  @NonNull
  public static LongRunningJobManager getLongRunningJobMgr ()
  {
    return getInstance ().m_aLongRunningJobMgr;
  }
}
