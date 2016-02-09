/**
 * Copyright (C) 2012-2016 winenet GmbH - www.winenet.at
 * All Rights Reserved
 *
 * This file is part of the winenet-Kellerbuch software.
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is
 * strictly prohibited.
 */
package com.helger.photon.jetty;

import java.io.File;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
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
@Immutable
public final class JettyStarter
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JettyStarter.class);

  private final String m_sAppName;
  private final String m_sDirBaseName;
  private int m_nPort = 8080;
  private boolean m_bRunStopMonitor = true;
  private String m_sStopKey = InternalJettyStopMonitorThread.STOP_KEY;
  private int m_nStopPort = InternalJettyStopMonitorThread.STOP_PORT;
  private boolean m_bSpecialSessionMgr = true;
  private String m_sResourceBase = "target/webapp-classes";
  private String m_sContextPath = "/";

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
  }

  @Nonnull
  public JettyStarter setPort (@Nonnegative final int nPort)
  {
    ValueEnforcer.isGT0 (nPort, "Port");
    m_nPort = nPort;
    return this;
  }

  @Nonnull
  public JettyStarter setRunStopMonitor (final boolean bRunStopMonitor)
  {
    m_bRunStopMonitor = bRunStopMonitor;
    return this;
  }

  @Nonnull
  public JettyStarter setStopKey (@Nonnull final String sStopKey)
  {
    ValueEnforcer.notNull (sStopKey, "StopKey");
    m_sStopKey = sStopKey;
    return this;
  }

  @Nonnull
  public JettyStarter setStopPort (@Nonnegative final int nStopPort)
  {
    ValueEnforcer.isGT0 (nStopPort, "StopPort");
    m_nStopPort = nStopPort;
    return this;
  }

  @Nonnull
  public JettyStarter setSpecialSessionMgr (final boolean bSpecialSessionMgr)
  {
    m_bSpecialSessionMgr = bSpecialSessionMgr;
    return this;
  }

  @Nonnull
  public JettyStarter setResourceBase (@Nonnull @Nonempty final String sResourceBase)
  {
    ValueEnforcer.notEmpty (sResourceBase, "sResourceBase");
    m_sResourceBase = sResourceBase;
    return this;
  }

  @Nonnull
  public JettyStarter setContextPath (@Nonnull @Nonempty final String sContextPath)
  {
    ValueEnforcer.notEmpty (sContextPath, "sContextPath");
    m_sContextPath = sContextPath;
    return this;
  }

  public void run () throws Exception
  {
    if (System.getSecurityManager () != null)
      throw new IllegalStateException ("Security Manager is set but not supported - aborting!");

    final String sTempDir = SystemProperties.getTmpDir ();

    // Create main server
    final Server aServer = new Server ();
    // Create connector on Port
    final ServerConnector aConnector = new ServerConnector (aServer);
    aConnector.setPort (m_nPort);
    aConnector.setIdleTimeout (30000);
    aServer.setConnectors (new Connector [] { aConnector });

    final WebAppContext aWebAppCtx = new WebAppContext ();
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
        aServer.stop ();
        s_aLogger.error ("Failed to start Jetty " + m_sAppName + " - stopped server!");
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
