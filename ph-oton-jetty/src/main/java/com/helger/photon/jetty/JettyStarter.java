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
package com.helger.photon.jetty;

import java.io.File;
import java.util.function.IntUnaryOperator;

import org.eclipse.jetty.ee10.annotations.AnnotationConfiguration;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.SessionHandler;
import org.eclipse.jetty.ee10.webapp.Configuration;
import org.eclipse.jetty.ee10.webapp.FragmentConfiguration;
import org.eclipse.jetty.ee10.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.ee10.webapp.JspConfiguration;
import org.eclipse.jetty.ee10.webapp.MetaInfConfiguration;
import org.eclipse.jetty.ee10.webapp.WebAppConfiguration;
import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.ee10.webapp.WebInfConfiguration;
import org.eclipse.jetty.ee10.webapp.WebXmlConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.session.DefaultSessionCache;
import org.eclipse.jetty.session.FileSessionDataStore;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.CGlobal;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.base.string.StringHelper;
import com.helger.base.system.SystemProperties;
import com.helger.io.file.FilenameHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Run a standalone web application in Jetty on port 8080.<br>
 * http://localhost:8080/
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class JettyStarter
{
  public static final String CONTAINER_INCLUDE_JAR_PATTERN_JAR = ".*\\.jar$*";
  public static final String CONTAINER_INCLUDE_JAR_PATTERN_CLASSES = ".*/classes/.*";
  public static final String CONTAINER_INCLUDE_JAR_PATTERN_ALL = CONTAINER_INCLUDE_JAR_PATTERN_JAR +
                                                                 "|" +
                                                                 CONTAINER_INCLUDE_JAR_PATTERN_CLASSES;

  public static final int DEFAULT_PORT = 8080;
  public static final String DEFAULT_STOP_KEY = InternalJettyStopMonitorThread.STOP_KEY;
  public static final int DEFAULT_STOP_PORT = InternalJettyStopMonitorThread.STOP_PORT;
  public static final String DEFAULT_CONTEXT_PATH = "/";
  public static final String DEFAULT_CONTAINER_INCLUDE_JAR_PATTERN = null;
  public static final String DEFAULT_WEB_INF_INCLUDE_JAR_PATTERN = null;
  public static final String DEFAULT_SESSION_COOKIE_NAME = "PHOTONSESSIONID";
  public static final boolean DEFAULT_ALLOW_ANNOTATION_BASED_CONFIG = true;
  public static final boolean DEFAULT_ALLOW_DIRECTORY_LISTING = false;

  private static final Logger LOGGER = LoggerFactory.getLogger (JettyStarter.class);
  private static final String CONTAINER_JAR_PATTERN = "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern";
  private static final String WEBINF_JAR_PATTERN = "org.eclipse.jetty.server.webapp.WebInfIncludeJarPattern";

  private final PhotonFileSystemCache m_aFSCache = new PhotonFileSystemCache ();
  private ResourceFactory m_aRF = new PhotonResourceFactory (m_aFSCache);
  private final String m_sAppName;
  private final String m_sDirBaseName;
  private int m_nPort = DEFAULT_PORT;
  private boolean m_bRunStopMonitor = true;
  private String m_sStopKey = DEFAULT_STOP_KEY;
  private IntUnaryOperator m_aStopPort = x -> DEFAULT_STOP_PORT;
  private boolean m_bSpecialSessionMgr = true;
  private Resource m_aResourceBase = m_aRF.newResource ("target/webapp-classes");
  private String m_sWebXmlResource;
  private String m_sContextPath = DEFAULT_CONTEXT_PATH;
  private String m_sContainerIncludeJarPattern = DEFAULT_CONTAINER_INCLUDE_JAR_PATTERN;
  private String m_sWebInfIncludeJarPattern = DEFAULT_WEB_INF_INCLUDE_JAR_PATTERN;
  private ThreadPool m_aThreadPool;
  private boolean m_bAllowAnnotationBasedConfig = DEFAULT_ALLOW_ANNOTATION_BASED_CONFIG;
  private boolean m_bAllowDirectoryListing = DEFAULT_ALLOW_DIRECTORY_LISTING;
  private String m_sSessionCookieName = DEFAULT_SESSION_COOKIE_NAME;

  public JettyStarter (@Nonnull final Class <?> aAppClass)
  {
    this (ClassHelper.getClassLocalName (aAppClass));
  }

  public JettyStarter (@Nonnull @Nonempty final String sAppName)
  {
    ValueEnforcer.notEmpty (sAppName, "AppName");
    m_sAppName = sAppName;
    m_sDirBaseName = FilenameHelper.getAsSecureValidFilename (sAppName);
    if (StringHelper.isEmpty (m_sDirBaseName))
      throw new IllegalStateException ("FolderName for application name '" + sAppName + "' is empty.");

    // Use recommended thread pool
    m_aThreadPool = new QueuedThreadPool ();
    ((QueuedThreadPool) m_aThreadPool).setName ("jetty-server");

    // Must be directly called on System to have an effect!
    System.setProperty ("log4j2.disable.jmx", "true");
  }

  @Nonnull
  public ResourceFactory getResourceFactory ()
  {
    return m_aRF;
  }

  @Nonnull
  public final JettyStarter setResourceFactory (@Nonnull final ResourceFactory a)
  {
    ValueEnforcer.notNull (a, "ResourceFactory");
    m_aRF = a;
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
   * Set the port to be used to run the application. Defaults to {@value #DEFAULT_PORT}
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

  public boolean isRunStopMonitor ()
  {
    return m_bRunStopMonitor;
  }

  /**
   * Enable or disable the "stop monitor" that listens for the graceful shutdown. By default this is
   * enabled.
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

  @Nonnull
  public String getStopKey ()
  {
    return m_sStopKey;
  }

  /**
   * Set the hidden "stop key" that must be submitted to stop the server. Defaults to
   * {@link #DEFAULT_STOP_KEY}. If set here, it must also be set in {@link JettyStopper}.
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

  public int getStopPort ()
  {
    return m_aStopPort.applyAsInt (m_nPort);
  }

  /**
   * Set the port on which the "stop monitor" should be running. Defaults to
   * {@link #DEFAULT_STOP_PORT}. When running multiple Jettys at once, each instance must use it's
   * own stop port. If this is set here, it must also be set in {@link JettyStopper}.
   *
   * @param nStopPort
   *        The stop port to be used. Must be &gt; 0.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setStopPort (@Nonnegative final int nStopPort)
  {
    ValueEnforcer.isGT0 (nStopPort, "StopPort");
    // Set a constant value
    return setStopPort (x -> nStopPort);
  }

  /**
   * Set the port on which the "stop monitor" should be running. Defaults to
   * {@link #DEFAULT_STOP_PORT}. When running multiple Jettys at once, each instance must use it's
   * own stop port. If this is set here, it must also be set in {@link JettyStopper}.<br>
   * This overload lets you set a function that takes as input the default port and you can
   * calculate the stop port from it.
   *
   * @param aStopPort
   *        The stop port to be used. May not be <code>null</code>.
   * @return this for chaining
   * @since 8.3.2
   */
  @Nonnull
  public final JettyStarter setStopPort (@Nonnull final IntUnaryOperator aStopPort)
  {
    ValueEnforcer.notNull (aStopPort, "StopPort");
    m_aStopPort = aStopPort;
    return this;
  }

  public boolean isSpecialSessionMgr ()
  {
    return m_bSpecialSessionMgr;
  }

  /**
   * @param bSpecialSessionMgr
   *        <code>true</code> to set a session manager that allows for persistent activation and
   *        passivation of sessions.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setSpecialSessionMgr (final boolean bSpecialSessionMgr)
  {
    m_bSpecialSessionMgr = bSpecialSessionMgr;
    return this;
  }

  @Nonnull
  public Resource getResourceBase ()
  {
    return m_aResourceBase;
  }

  /**
   * Set the common resource base (directory) from which all web application resources will be
   * loaded (servlet context root).
   *
   * @param sResourceBase
   *        The path. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setResourceBase (@Nonnull @Nonempty final String sResourceBase)
  {
    ValueEnforcer.notEmpty (sResourceBase, "ResourceBase");
    return setResourceBase (m_aRF.newResource (sResourceBase));
  }

  /**
   * Set the common resource base (directory) from which all web application resources will be
   * loaded (servlet context root).
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

  @Nullable
  public String getWebXmlResource ()
  {
    return m_sWebXmlResource;
  }

  /**
   * Set the path to WEB-INF/web.xml. If unspecified, the default relative to the resource base is
   * used.
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

  @Nonnull
  @Nonempty
  public String getContextPath ()
  {
    return m_sContextPath;
  }

  /**
   * Set the context path in which the web application should run. By default this
   * {@link #DEFAULT_CONTEXT_PATH}
   *
   * @param sContextPath
   *        The new context path. May neither be <code>null</code> nor empty and must start with a
   *        slash.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setContextPath (@Nonnull @Nonempty final String sContextPath)
  {
    ValueEnforcer.notEmpty (sContextPath, "sContextPath");
    m_sContextPath = sContextPath;
    return this;
  }

  @Nullable
  public String getContainerIncludeJarPattern ()
  {
    return m_sContainerIncludeJarPattern;
  }

  /**
   * Set the container JAR pattern to be scanned for annotations. By default this
   * {@link #DEFAULT_CONTAINER_INCLUDE_JAR_PATTERN}
   *
   * @param sContainerIncludeJarPattern
   *        The new container JAR pattern. May be <code>null</code> to use the default.
   * @return this for chaining
   */
  @Nonnull
  public final JettyStarter setContainerIncludeJarPattern (@Nullable final String sContainerIncludeJarPattern)
  {
    m_sContainerIncludeJarPattern = sContainerIncludeJarPattern;
    return this;
  }

  @Nullable
  public String getWebInfIncludeJarPattern ()
  {
    return m_sWebInfIncludeJarPattern;
  }

  @Nonnull
  public final JettyStarter setWebInfIncludeJarPattern (@Nullable final String sWebInfIncludeJarPattern)
  {
    m_sWebInfIncludeJarPattern = sWebInfIncludeJarPattern;
    return this;
  }

  @Nullable
  public ThreadPool getThreadPool ()
  {
    return m_aThreadPool;
  }

  /**
   * Set the thread pool to use.
   *
   * @param aThreadPool
   *        Thread pool. May be <code>null</code> to use the default thread pool.
   * @return this
   * @since 7.0.6
   */
  @Nonnull
  public final JettyStarter setThreadPool (@Nullable final ThreadPool aThreadPool)
  {
    m_aThreadPool = aThreadPool;
    return this;
  }

  public boolean isAllowAnnotationBasedConfig ()
  {
    return m_bAllowAnnotationBasedConfig;
  }

  /**
   * Enable or disable annotation based scanning. By default it is enabled. Disable it for better
   * performance.
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

  public boolean isAllowDirectoryListing ()
  {
    return m_bAllowDirectoryListing;
  }

  /**
   * Enable or disable the listing of Directories. Turned off by default for security reasons.
   *
   * @param bAllowDirectoryListing
   *        <code>true</code> to enable it.
   * @return this
   * @since 8.3.7
   */
  @Nonnull
  public final JettyStarter setAllowDirectoryListing (final boolean bAllowDirectoryListing)
  {
    m_bAllowDirectoryListing = bAllowDirectoryListing;
    return this;
  }

  /**
   * @return The name of the session cookie or null to use Jetty default. The default values is
   *         {@link #DEFAULT_SESSION_COOKIE_NAME}.
   */
  @Nullable
  public String getSessionCookieName ()
  {
    return m_sSessionCookieName;
  }

  /**
   * Set the session cookie name. Default is {@value #DEFAULT_SESSION_COOKIE_NAME}. When running
   * different applications ensure to use different names to ensure you can test them in the same
   * browser in the same session.
   *
   * @param sSessionCookieName
   *        New name or <code>null</code> to use Jetty default.
   * @return this for chaining
   * @since 8.1.0
   */
  @Nonnull
  public final JettyStarter setSessionCookieName (@Nullable final String sSessionCookieName)
  {
    m_sSessionCookieName = sSessionCookieName;
    return this;
  }

  /**
   * Customize
   *
   * @param aHttpConfiguration
   *        HTTP configuration
   * @throws Exception
   *         in case of error
   * @since 8.4.0
   */
  @OverrideOnDemand
  protected void customizeHttpConfiguration (@Nonnull final HttpConfiguration aHttpConfiguration) throws Exception
  {}

  /**
   * Customize
   *
   * @param aHttpConnectionFactory
   *        HTTP connection factory
   * @throws Exception
   *         in case of error
   * @since 8.4.0
   */
  @OverrideOnDemand
  protected void customizeHttpConnectionFactory (@Nonnull final HttpConnectionFactory aHttpConnectionFactory) throws Exception
  {}

  /**
   * Customize
   *
   * @param aServerConnector
   *        Server connector
   * @throws Exception
   *         in case of error
   */
  @OverrideOnDemand
  protected void customizeServerConnector (@Nonnull final ServerConnector aServerConnector) throws Exception
  {}

  /**
   * Customize
   *
   * @param aServer
   *        Server
   * @throws Exception
   *         in case of error
   */
  @OverrideOnDemand
  protected void customizeServer (@Nonnull final Server aServer) throws Exception
  {}

  /**
   * Customize
   *
   * @param aWebAppCtx
   *        Web application context
   * @throws Exception
   *         in case of error
   */
  @OverrideOnDemand
  protected void customizeWebAppCtx (@Nonnull final WebAppContext aWebAppCtx) throws Exception
  {}

  /**
   * Create a new {@link WebAppContext} based on the settings of this class.
   *
   * @param sContextPath
   *        The context path to be used. May neither be <code>null</code> nor empty.
   * @return The created object. Never <code>null</code>.
   * @throws Exception
   *         In case of error
   */
  @Nonnull
  public WebAppContext createWebAppContext (@Nonnull @Nonempty final String sContextPath) throws Exception
  {
    ValueEnforcer.notEmpty (sContextPath, "ContextPath");
    // getTmpDir is e.g. "/tmp"
    // Context path (if present) starts with a slash
    final String sTempDir = SystemProperties.getTmpDir () + FilenameHelper.getAsSecureValidASCIIFilename (sContextPath);

    final WebAppContext aWebAppCtx = new WebAppContext ();
    {
      aWebAppCtx.setBaseResource (m_aResourceBase);
      aWebAppCtx.setDescriptor (m_sWebXmlResource != null ? m_sWebXmlResource : m_aRF.newResource (m_aResourceBase
                                                                                                                  .getName () +
                                                                                                   "/WEB-INF/web.xml")
                                                                                     .getName ());
      aWebAppCtx.setContextPath (sContextPath);
      aWebAppCtx.setTempDirectory (new File (sTempDir, m_sDirBaseName + ".webapp"));
      /*
       * This line can make a difference between Jetty and Tomcat: True if the classloader should
       * delegate first to the parentclassloader (standard java behaviour) or false if the
       * classloader should first try to load from WEB-INF/lib or WEB-INF/classes (servletspec
       * recommendation). Default is false or can be set by the systemproperty
       * org.eclipse.jetty.server.webapp.parentLoaderPriority
       */
      aWebAppCtx.setParentLoaderPriority (true);
      aWebAppCtx.setThrowUnavailableOnStartupException (true);
      if (m_sContainerIncludeJarPattern != null)
      {
        // https://www.eclipse.org/jetty/documentation/jetty-9/index.html#configuring-webapps
        // https://github.com/eclipse/jetty.project/issues/680
        aWebAppCtx.setAttribute (CONTAINER_JAR_PATTERN, m_sContainerIncludeJarPattern);
      }
      if (m_sWebInfIncludeJarPattern != null)
      {
        aWebAppCtx.setAttribute (WEBINF_JAR_PATTERN, m_sWebInfIncludeJarPattern);
      }

      if (m_bAllowAnnotationBasedConfig)
      {
        // Important to add the AnnotationConfiguration!
        aWebAppCtx.setConfigurations (new Configuration [] { new WebInfConfiguration (),
                                                             new WebXmlConfiguration (),
                                                             new WebAppConfiguration (),
                                                             new MetaInfConfiguration (),
                                                             new FragmentConfiguration (),
                                                             new AnnotationConfiguration (),
                                                             new JettyWebXmlConfiguration (),
                                                             new JspConfiguration () });
      }
      // else leave default

      aWebAppCtx.setInitParameter ("org.eclipse.jetty.servlet.Default.dirAllowed",
                                   Boolean.toString (m_bAllowDirectoryListing));
    }

    // Set session store directory to passivate/activate sessions
    if (m_bSpecialSessionMgr)
    {
      final SessionHandler aHdl = new SessionHandler ();
      final FileSessionDataStore aDataStore = new FileSessionDataStore ();
      aDataStore.setStoreDir (new File (sTempDir, m_sDirBaseName + ".sessions"));
      aDataStore.setDeleteUnrestorableFiles (true);
      final DefaultSessionCache aCache = new DefaultSessionCache (aHdl);
      aCache.setSessionDataStore (aDataStore);
      aCache.setRemoveUnloadableSessions (true);
      aHdl.setSessionCache (aCache);
      aWebAppCtx.setSessionHandler (aHdl);
    }

    // Hack to circumvent API limits - ensure SameSite for Session cookie
    aWebAppCtx.getSessionHandler ().getSessionCookieConfig ().setHttpOnly (true);
    if (StringHelper.isNotEmpty (m_sSessionCookieName))
      aWebAppCtx.getSessionHandler ().setSessionCookie (m_sSessionCookieName);

    // Customize call
    customizeWebAppCtx (aWebAppCtx);
    return aWebAppCtx;
  }

  /**
   * Customize the {@link Handler.Sequence}
   *
   * @param aHandlerList
   *        The {@link Handler.Sequence}. Never <code>null</code>.
   * @throws Exception
   *         in case of error
   */
  protected void customizeHandlerList (@Nonnull final Handler.Sequence aHandlerList) throws Exception
  {}

  /**
   * Callback to be invoked when server successfully finished startup.
   *
   * @param aServer
   *        The server that was started. Never <code>null</code>.
   * @throws Exception
   *         in case of error
   * @since 7.0.2
   */
  @OverrideOnDemand
  protected void onServerStarted (@Nonnull final Server aServer) throws Exception
  {}

  /**
   * Callback to be invoked when server failed startup.
   *
   * @param aServer
   *        The server that was started. Never <code>null</code>.
   * @param t
   *        The exception that occurred
   * @throws Exception
   *         in case of error
   * @since 7.0.2
   */
  @OverrideOnDemand
  protected void onServerStartFailure (@Nonnull final Server aServer, @Nonnull final Throwable t) throws Exception
  {}

  /**
   * Run Jetty with the provided settings.
   *
   * @throws Exception
   *         In case something goes wrong
   */
  public void run () throws Exception
  {
    // Create main server
    final Server aServer = new Server (m_aThreadPool);
    {
      final HttpConfiguration aHC = new HttpConfiguration ();
      aHC.setSendServerVersion (false);
      aHC.setSendXPoweredBy (false);
      customizeHttpConfiguration (aHC);

      final HttpConnectionFactory aHCF = new HttpConnectionFactory (aHC);
      customizeHttpConnectionFactory (aHCF);

      // Create connector on Port
      final ServerConnector aConnector = new ServerConnector (aServer, aHCF);
      aConnector.setPort (m_nPort);
      aConnector.setIdleTimeout (30_000);
      customizeServerConnector (aConnector);

      aServer.setConnectors (new Connector [] { aConnector });
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormContentSize",
                            Integer.valueOf (2 * CGlobal.BYTES_PER_MEGABYTE));
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormKeys", Integer.valueOf (20000));
    }

    // Customize call
    customizeServer (aServer);

    final WebAppContext aWebAppCtx = createWebAppContext (m_sContextPath);

    final Handler.Sequence aHandlerList = new Handler.Sequence ();
    aHandlerList.addHandler (aWebAppCtx);
    // Allow for additional web app contexts ;-)
    customizeHandlerList (aHandlerList);
    aServer.setHandler (aHandlerList);

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
      final int nStopPort = m_aStopPort.applyAsInt (m_nPort);

      // May fail if port is in use
      if (m_bRunStopMonitor)
        new InternalJettyStopMonitorThread (nStopPort, m_sStopKey, aServer::stop).start ();

      // Starting the engines:
      aServer.start ();
      LOGGER.info ("Started Jetty" + ":" + m_nPort + ":" + nStopPort + " " + m_sAppName);

      // Callback
      onServerStarted (aServer);

      Runtime.getRuntime ().addShutdownHook (new Thread ( () -> {
        try
        {
          aServer.stop ();
          aServer.join ();
        }
        catch (final InterruptedException ex)
        {
          Thread.currentThread ().interrupt ();
          LOGGER.error ("ShutdownHook of JettyStarter has been interrupted!");
        }
        catch (final Exception ex)
        {
          LOGGER.error ("Exception in ShutdownHook of JettyStarter!", ex);
        }
      }));
    }
    catch (final Exception ex)
    {
      // Do not throw something here, in case some exception occurs in finally
      // code
      LOGGER.error ("Failed to start Jetty " + m_sAppName + "!", ex);

      // Callback
      onServerStartFailure (aServer, ex);
    }
    finally
    {
      if (aCtx.isFailed ())
      {
        LOGGER.error ("Failed to start Jetty " + m_sAppName + " - stopping server!");
        try
        {
          // Throws an Exception e.g. in log4j2 2.5
          aServer.stop ();
          LOGGER.error ("Failed to start Jetty " + m_sAppName + " - stopped server!");
        }
        catch (final Exception ex)
        {
          LOGGER.error ("Error stopping Jetty " + m_sAppName + " after startup errors!", ex);
        }
      }
      else
        if (!aServer.isFailed ())
        {
          // Running the server!
          aServer.join ();
        }

      // close all loaded FileSystems
      m_aFSCache.close ();
    }
  }
}
