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
package com.helger.photon.jetty;

import java.io.File;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
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
  private static final Logger s_aLogger = LoggerFactory.getLogger (JettyStarter.class);

  private final String m_sAppName;
  private final String m_sDirBaseName;
  private int m_nPort = DEFAULT_PORT;
  private boolean m_bRunStopMonitor = true;
  private String m_sStopKey = DEFAULT_STOP_KEY;
  private int m_nStopPort = DEFAULT_STOP_PORT;
  private boolean m_bSpecialSessionMgr = true;
  private String m_sResourceBase = "target/webapp-classes";
  private String m_sContextPath = DEFAULT_CONTEXT_PATH;

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
      throw new IllegalStateException ("FolderName is empty.");

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
  public JettyStarter setPort (@Nonnegative final int nPort)
  {
    ValueEnforcer.isGT0 (nPort, "Port");
    m_nPort = nPort;
    return this;
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
  public JettyStarter setRunStopMonitor (final boolean bRunStopMonitor)
  {
    m_bRunStopMonitor = bRunStopMonitor;
    return this;
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
  public JettyStarter setStopKey (@Nonnull final String sStopKey)
  {
    ValueEnforcer.notNull (sStopKey, "StopKey");
    m_sStopKey = sStopKey;
    return this;
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
  public JettyStarter setStopPort (@Nonnegative final int nStopPort)
  {
    ValueEnforcer.isGT0 (nStopPort, "StopPort");
    m_nStopPort = nStopPort;
    return this;
  }

  /**
   * @param bSpecialSessionMgr
   *        <code>true</code> to set a session manager that allows for
   *        persistent activation and passivation of sessions.
   * @return this for chaining
   */
  @Nonnull
  public JettyStarter setSpecialSessionMgr (final boolean bSpecialSessionMgr)
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
  public JettyStarter setResourceBase (@Nonnull @Nonempty final String sResourceBase)
  {
    ValueEnforcer.notEmpty (sResourceBase, "sResourceBase");
    m_sResourceBase = sResourceBase;
    return this;
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
  public JettyStarter setContextPath (@Nonnull @Nonempty final String sContextPath)
  {
    ValueEnforcer.notEmpty (sContextPath, "sContextPath");
    m_sContextPath = sContextPath;
    return this;
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
    final Server aServer = new Server ();
    {
      // Create connector on Port
      final ServerConnector aConnector = new ServerConnector (aServer);
      aConnector.setPort (m_nPort);
      aConnector.setIdleTimeout (30000);
      customizeServerConnector (aConnector);
      aServer.setConnectors (new Connector [] { aConnector });
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormContentSize",
                            Integer.valueOf (2 * CGlobal.BYTES_PER_MEGABYTE));
      aServer.setAttribute ("org.eclipse.jetty.server.Request.maxFormKeys", Integer.valueOf (20000));
      customizeServer (aServer);
    }

    final WebAppContext aWebAppCtx = new WebAppContext ();
    {
      aWebAppCtx.setDescriptor (m_sResourceBase + "/WEB-INF/web.xml");
      aWebAppCtx.setResourceBase (m_sResourceBase);
      aWebAppCtx.setContextPath (m_sContextPath);
      aWebAppCtx.setTempDirectory (new File (sTempDir + '/' + m_sDirBaseName + ".webapp"));
      aWebAppCtx.setParentLoaderPriority (true);
      aWebAppCtx.setThrowUnavailableOnStartupException (true);

      // Important to add the AnnotationConfiguration!
      aWebAppCtx.setConfigurations (new Configuration [] { new WebInfConfiguration (),
                                                           new WebXmlConfiguration (),
                                                           new MetaInfConfiguration (),
                                                           new FragmentConfiguration (),
                                                           new JettyWebXmlConfiguration (),
                                                           new AnnotationConfiguration () });
    }

    // Set session store directory to passivate/activate sessions
    if (m_bSpecialSessionMgr)
    {
      final HashSessionManager aMgr = new HashSessionManager ();
      aMgr.setStoreDirectory (new File (sTempDir + '/' + m_sDirBaseName + ".sessions"));
      aMgr.setLazyLoad (true);
      aMgr.setDeleteUnrestorableSessions (true);
      aWebAppCtx.setSessionHandler (new SessionHandler (aMgr));
    }

    aWebAppCtx.getSessionHandler ().getSessionManager ().getSessionCookieConfig ().setName ("PHOTONSESSIONID");

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

    // Starting shutdown listener thread
    if (m_bRunStopMonitor)
      new InternalJettyStopMonitorThread (m_nStopPort, m_sStopKey).start ();

    try
    {
      // Starting the engines:
      aServer.start ();
      s_aLogger.info ("Started Jetty " + m_sAppName);
    }
    catch (final Throwable t)
    {
      // Do not throw something here, in case some exception occurs in finally
      // code
      s_aLogger.error ("Failed to start Jetty " + m_sAppName + "!", t);
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
