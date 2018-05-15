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
package com.helger.photon.jetty;

import java.io.File;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.system.SystemProperties;

/**
 * Run a standalone web application in Jetty on port 8080.<br>
 * http://localhost:8080/
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class JettyStarter
{
  public static final int DEFAULT_PORT = 8080;
  public static final String DEFAULT_STOP_KEY = InternalJettyStopMonitorThread.STOP_KEY;
  public static final int DEFAULT_STOP_PORT = InternalJettyStopMonitorThread.STOP_PORT;
  public static final String DEFAULT_CONTEXT_PATH = "/";
  public static final String DEFAULT_CONTAINER_JAR_PATTERN = ".*\\.jar$|.*/classes/.*";
  private static final Logger s_aLogger = LoggerFactory.getLogger (JettyStarter.class);

  private final String m_sAppName;
  private final String m_sDirBaseName;
  private int m_nPort = DEFAULT_PORT;
  private boolean m_bRunStopMonitor = true;
  private String m_sStopKey = DEFAULT_STOP_KEY;
  private int m_nStopPort = DEFAULT_STOP_PORT;
  private boolean m_bSpecialSessionMgr = true;
  private Resource m_aResourceBase = _asRes ("target/webapp-classes");
  private String m_sWebXmlResource;
  private String m_sContextPath = DEFAULT_CONTEXT_PATH;
  private String m_sContainerJarPattern = DEFAULT_CONTAINER_JAR_PATTERN;
  private ThreadPool m_aThreadPool;
  private boolean m_bAllowAnnotationBasedConfig = true;

  @Nonnull
  private static Resource _asRes (@Nonnull final String sPath)
  {
    try
    {
      return Resource.newResource (sPath);
    }
    catch (final Exception ex)
    {
      throw new IllegalArgumentException ("Invalid resource path '" + sPath + "'");
    }
  }

  public JettyStarter (@Nonnull final Class <?> aAppClass)
  {
    this (ClassHelper.getClassLocalName (aAppClass));
  }

  public JettyStarter (@Nonnull @Nonempty final String sAppName)
  {
    ValueEnforcer.notEmpty (sAppName, "AppName");
    m_sAppName = sAppName;
    m_sDirBaseName = FilenameHelper.getAsSecureValidFilename (sAppName);
    if (StringHelper.hasNoText (m_sDirBaseName))
      throw new IllegalStateException ("FolderName for application name '" + sAppName + "' is empty.");

    // Must be directly called on System to have an effect!
    System.setProperty ("log4j2.disable.jmx", "true");
  }

  /**
   * Set the port to be used to run the application. Defaults to
   * {@value #DEFAULT_PORT}
   *
   * @param nPort
   *        The port to be used. Must be &gt; 0.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setPort (@Nonnegative final int nPort)
  {
    ValueEnforcer.isGT0 (nPort, "Port");
    m_nPort = nPort;
    return this;
  }

  /**
   * @return Port to run on.
   */
  @Nonnegative
  public int getPort ()
  {
    return m_nPort;
  }

  /**
   * Enable or disable the "stop monitor" that listens for the graceful
   * shutdown. By default this is enabled.
   *
   * @param bRunStopMonitor
   *        <code>true</code> to enable it, <code>false</code> to disable it.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setRunStopMonitor (final boolean bRunStopMonitor)
  {
    m_bRunStopMonitor = bRunStopMonitor;
    return this;
  }

  public boolean isRunStopMonitor ()
  {
    return m_bRunStopMonitor;
  }

  /**
   * Set the hidden "stop key" that must be submitted to stop the server.
   * Defaults to {@link #DEFAULT_STOP_KEY}. If set here, it must also be set in
   * {@link JettyStopper}.
   *
   * @param sStopKey
   *        The stop key to be used. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setStopKey (@Nonnull final String sStopKey)
  {
    ValueEnforcer.notNull (sStopKey, "StopKey");
    m_sStopKey = sStopKey;
    return this;
  }

  @Nonnull
  public String getStopKey ()
  {
    return m_sStopKey;
  }

  /**
   * Set the port on which the "stop monitor" should be running. Defaults to
   * {@link #DEFAULT_STOP_PORT}. When running multiple Jettys at once, each
   * instance must use it's own stop port. If this is set here, it must also be
   * set in {@link JettyStopper}.
   *
   * @param nStopPort
   *        The stop port to be used. Must be &gt; 0.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setStopPort (@Nonnegative final int nStopPort)
  {
    ValueEnforcer.isGT0 (nStopPort, "StopPort");
    m_nStopPort = nStopPort;
    return this;
  }

  public int getStopPort ()
  {
    return m_nStopPort;
  }

  /**
   * @param bSpecialSessionMgr
   *        <code>true</code> to set a session manager that allows for
   *        persistent activation and passivation of sessions.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setSpecialSessionMgr (final boolean bSpecialSessionMgr)
  {
    m_bSpecialSessionMgr = bSpecialSessionMgr;
    return this;
  }

  /**
   * Set the common resource base (directory) from which all web application
   * resources will be loaded (servlet context root).
   *
   * @param sResourceBase
   *        The path. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setResourceBase (@Nonnull @Nonempty final String sResourceBase)
  {
    ValueEnforcer.notEmpty (sResourceBase, "ResourceBase");
    return setResourceBase (_asRes (sResourceBase));
  }

  /**
   * Set the common resource base (directory) from which all web application
   * resources will be loaded (servlet context root).
   *
   * @param aResourceBase
   *        The resource. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setResourceBase (@Nonnull final Resource aResourceBase)
  {
    ValueEnforcer.notNull (aResourceBase, "ResourceBase");
    m_aResourceBase = aResourceBase;
    return this;
  }

  @Nonnull
  public Resource getResourceBase ()
  {
    return m_aResourceBase;
  }

  /**
   * Set the path to WEB-INF/web.xml. If unspecified, the default relative to
   * the resource base is used.
   *
   * @param sWebXmlResource
   *        web.xml resource. May be <code>null</code>.
   * @return this for chaining.
   */
  @Nonnull
  public final JettyStarter setWebXmlResource (@Nullable final String sWebXmlResource)
  {
    m_sWebXmlResource = sWebXmlResource;
    return this;
  }

  @Nullable
  public String getWebXmlResource ()
  {
    return m_sWebXmlResource;
  }

  /**
   * Set the context path in which the web application should run. By default
   * this {@link #DEFAULT_CONTEXT_PATH}
   *
   * @param sContextPath
   *        The new context path. May neither be <code>null</code> nor empty and
   *        must start with a slash.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setContextPath (@Nonnull @Nonempty final String sContextPath)
  {
    ValueEnforcer.notEmpty (sContextPath, "sContextPath");
    m_sContextPath = sContextPath;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getContextPath ()
  {
    return m_sContextPath;
  }

  /**
   * Set the container JAR pattern to be scanned for annotations. By default
   * this {@link #DEFAULT_CONTAINER_JAR_PATTERN}
   *
   * @param sContainerJarPattern
   *        The new container JAR pattern. May neither be <code>null</code> nor
   *        empty.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setContainerJarPattern (@Nonnull @Nonempty final String sContainerJarPattern)
  {
    ValueEnforcer.notEmpty (sContainerJarPattern, "ContainerJarPattern");
    m_sContainerJarPattern = sContainerJarPattern;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getContainerJarPattern ()
  {
    return m_sContainerJarPattern;
  }

  /**
   * Set the thread pool to use.
   *
   * @param aThreadPool
   *        Thread pool. May be <code>null</code> to use the default thread
   *        pool.
   * @return this
   * @since 7.0.6
   */
  @Nonnull
  public final JettyStarter setThreadPool (@Nullable final ThreadPool aThreadPool)
  {
    m_aThreadPool = aThreadPool;
    return this;
  }

  @Nullable
  public ThreadPool getThreadPool ()
  {
    return m_aThreadPool;
  }

  /**
   * Enable or disable annotation based scanning. By default it is enabled.
   * Disable it for better performance.
   *
   * @param bAllowAnnotationBasedConfig
   *        <code>false</code> to disable it.
   * @return this
   * @since 8.0.0
   */
  @Nonnull
  public final JettyStarter setAllowAnnotationBasedConfig (final boolean bAllowAnnotationBasedConfig)
  {
    m_bAllowAnnotationBasedConfig = bAllowAnnotationBasedConfig;
    return this;
  }

  public boolean isAllowAnnotationBasedConfig ()
  {
    return m_bAllowAnnotationBasedConfig;
  }

  /**
   * Customize
   *
   * @param aServerConnector
   *        Server connector
   */
  @OverrideOnDemand
  protected void customizeServerConnector (@Nonnull final ServerConnector aServerConnector)
  {}

  /**
   * Customize
   *
   * @param aServer
   *        Server
   */
  @OverrideOnDemand
  protected void customizeServer (@Nonnull final Server aServer)
  {}

  /**
   * Customize
   *
   * @param aWebAppCtx
   *        Web application context
   */
  @OverrideOnDemand
  protected void customizeWebAppCtx (@Nonnull final WebAppContext aWebAppCtx)
  {}

  /**
   * Callback to be invoked when server successfully finished startup.
   *
   * @param aServer
   *        The server that was started. Never <code>null</code>.
   * @since 7.0.2
   */
  @OverrideOnDemand
  protected void onServerStarted (@Nonnull final Server aServer)
  {}

  /**
   * Callback to be invoked when server failed startup.
   *
   * @param aServer
   *        The server that was started. Never <code>null</code>.
   * @param t
   *        The exception that occurred
   * @since 7.0.2
   */
  @OverrideOnDemand
  protected void onServerStartFailure (@Nonnull final Server aServer, @Nonnull final Throwable t)
  {}

  /**
   * Run Jetty with the provided settings.
   *
   * @throws Exception
   *         In case something goes wrong
   */
  public void run () throws Exception
  {
    if (System.getSecurityManager () != null)
      throw new IllegalStateException ("Security Manager is set but not supported - aborting!");

    final String sTempDir = SystemProperties.getTmpDir ();

    // Create main server
    final Server aServer = new Server (m_aThreadPool);
    {
      // Create connector on Port
      final ServerConnector aConnector = new ServerConnector (aServer);
      aConnector.setPort (m_nPort);
      aConnector.setIdleTimeout (30_000);
      customizeServerConnector (aConnector);
      aServer.setConnectors (new Connector [] { aConnector });
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormContentSize",
                            Integer.valueOf (2 * CGlobal.BYTES_PER_MEGABYTE));
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormKeys", Integer.valueOf (20000));
    }

    customizeServer (aServer);

    final WebAppContext aWebAppCtx = new WebAppContext ();
    {
      aWebAppCtx.setBaseResource (m_aResourceBase);
      aWebAppCtx.setDescriptor (m_sWebXmlResource != null ? m_sWebXmlResource
                                                          : m_aResourceBase.addPath ("/WEB-INF/web.xml").getName ());
      aWebAppCtx.setContextPath (m_sContextPath);
      aWebAppCtx.setTempDirectory (new File (sTempDir + '/' + m_sDirBaseName + ".webapp"));
      aWebAppCtx.setParentLoaderPriority (true);
      aWebAppCtx.setThrowUnavailableOnStartupException (true);
      // http://www.eclipse.org/jetty/documentation/9.4.x/configuring-webapps.html#container-include-jar-pattern
      // https://github.com/eclipse/jetty.project/issues/680
      aWebAppCtx.setAttribute (WebInfConfiguration.CONTAINER_JAR_PATTERN, m_sContainerJarPattern);

      if (m_bAllowAnnotationBasedConfig)
      {
        // Important to add the AnnotationConfiguration!
        aWebAppCtx.setConfigurations (new Configuration [] { new WebInfConfiguration (),
                                                             new WebXmlConfiguration (),
                                                             new MetaInfConfiguration (),
                                                             new FragmentConfiguration (),
                                                             new AnnotationConfiguration (),
                                                             new JettyWebXmlConfiguration () });
      }
      // else leave default
    }

    // Set session store directory to passivate/activate sessions
    if (m_bSpecialSessionMgr)
    {
      final SessionHandler aHdl = new SessionHandler ();
      final FileSessionDataStore aDataStore = new FileSessionDataStore ();
      aDataStore.setStoreDir (new File (sTempDir + '/' + m_sDirBaseName + ".sessions"));
      aDataStore.setDeleteUnrestorableFiles (true);
      final DefaultSessionCache aCache = new DefaultSessionCache (aHdl);
      aCache.setSessionDataStore (aDataStore);
      aCache.setRemoveUnloadableSessions (true);
      aHdl.setSessionCache (aCache);
      aWebAppCtx.setSessionHandler (aHdl);
    }

    aWebAppCtx.getSessionHandler ().setSessionCookie ("PHOTONSESSIONID");

    customizeWebAppCtx (aWebAppCtx);

    aServer.setHandler (aWebAppCtx);
    final ServletContextHandler aCtx = aWebAppCtx;

    // Setting final properties
    // Stops the server when ctrl+c is pressed (registers to
    // Runtime.addShutdownHook)
    aServer.setStopAtShutdown (true);

    if (false)
    {
      // Debug output
      aServer.setDumpBeforeStop (true);
    }

    try
    {
      // Starting shutdown listener thread
      // May fail if port is in use
      if (m_bRunStopMonitor)
        new InternalJettyStopMonitorThread (m_nStopPort, m_sStopKey, () -> aServer.stop ()).start ();

      // Starting the engines:
      aServer.start ();
      s_aLogger.info ("Started Jetty" + ":" + m_nPort + ":" + m_nStopPort + " " + m_sAppName);

      // Callback
      onServerStarted (aServer);

      Runtime.getRuntime ().addShutdownHook (new Thread ( () -> {
        try
        {
          aServer.stop ();
          aServer.join ();
        }
        catch (final Exception ex)
        {
          s_aLogger.error ("Exception in ShutdownHook of JettyStarter!", ex);
        }
      }));
    }
    catch (final Throwable t)
    {
      // Do not throw something here, in case some exception occurs in finally
      // code
      s_aLogger.error ("Failed to start Jetty " + m_sAppName + "!", t);

      // Callback
      onServerStartFailure (aServer, t);
    }
    finally
    {
      if (aCtx.isFailed ())
      {
        s_aLogger.error ("Failed to start Jetty " + m_sAppName + " - stopping server!");
        try
        {
          // Throws an Exception e.g. in log4j2 2.5
          aServer.stop ();
          s_aLogger.error ("Failed to start Jetty " + m_sAppName + " - stopped server!");
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error stopping Jetty " + m_sAppName + " after startup errors!", t);
        }
      }
      else
        if (!aServer.isFailed ())
        {
          // Running the server!
          aServer.join ();
        }
    }
  }
}
