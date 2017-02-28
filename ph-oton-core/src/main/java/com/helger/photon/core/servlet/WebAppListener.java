/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.cleanup.CommonsCleanup;
import com.helger.commons.collection.ext.CommonsTreeMap;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsNavigableMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.lang.ClassPathHelper;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.system.EJVMVendor;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.system.SystemProperties;
import com.helger.commons.thirdparty.IThirdPartyModule;
import com.helger.commons.thirdparty.ThirdPartyModuleRegistry;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.url.URLHelper;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.datetime.util.PDTWebDateHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.app.io.WebIOIntIDFactory;
import com.helger.photon.basic.app.io.WebIOLongIDFactory;
import com.helger.servlet.StaticServerInfo;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.XMLWriterSettings;
import com.helger.xml.util.XMLCleanup;
import com.helger.xml.util.statistics.StatisticsExporter;

/**
 * This class is intended to handle the initial application startup and the
 * final shutdown. It is also responsible for creating the global and session
 * scopes.
 *
 * @author Philip Helger
 */
public class WebAppListener implements ServletContextListener, HttpSessionListener
{
  static
  {
    // Ensure that any AWT code runs headless (fonts etc.)
    SystemProperties.setPropertyValue ("java.awt.headless", "true");
  }

  /** Name of the initialization parameter to enable debug. */
  public static final String DEFAULT_INIT_PARAMETER_DEBUG = "debug";

  /** Name of the initialization parameter to enable production mode. */
  public static final String DEFAULT_INIT_PARAMETER_PRODUCTION = "production";

  /** Name of the initialization parameter for the storagePath. */
  public static final String INIT_PARAMETER_DATA_PATH = "dataPath";

  /**
   * Name of the initialization parameter to disable logging the startup info.
   */
  public static final String INIT_PARAMETER_NO_STARTUP_INFO = "noStartupInfo";

  /**
   * Name of the initialization parameter that contains the server URL for
   * non-production mode.
   */
  public static final String INIT_PARAMETER_SERVER_URL = "serverUrl";

  /**
   * Name of the initialization parameter that contains the server URL for
   * production mode.
   */
  public static final String INIT_PARAMETER_SERVER_URL_PRODUCTION = "serverUrlProduction";

  /**
   * Name of the initialization parameter to disable the file access check on
   * startup.
   */
  public static final String INIT_PARAMETER_NO_CHECK_FILE_ACCESS = "noCheckFileAccess";

  /**
   * The default file name where the global unique IDs are stored.
   */
  public static final String ID_FILENAME = "persistent_id.dat";

  /** The logger to use. */
  private static final Logger s_aLogger = LoggerFactory.getLogger (WebAppListener.class);

  private static final AtomicBoolean s_aInited = new AtomicBoolean (false);
  private LocalDateTime m_aInitializationStartDT;
  private LocalDateTime m_aInitializationEndDT;
  private boolean m_bHandleStatisticsOnEnd = true;

  public WebAppListener ()
  {}

  protected final void logServerInfo (@Nonnull final ServletContext aSC)
  {
    // Print Java and Server (e.g. Tomcat) info
    s_aLogger.info ("Java " +
                    SystemProperties.getJavaVersion () +
                    " running '" +
                    aSC.getServletContextName () +
                    "' on " +
                    aSC.getServerInfo () +
                    " with " +
                    (Runtime.getRuntime ().maxMemory () / CGlobal.BYTES_PER_MEGABYTE) +
                    "MB max RAM and Servlet API " +
                    aSC.getMajorVersion () +
                    "." +
                    aSC.getMinorVersion ());

    // Tell them to use the server VM if possible:
    final EJVMVendor eJVMVendor = EJVMVendor.getCurrentVendor ();
    if (eJVMVendor.isSun () && eJVMVendor != EJVMVendor.SUN_SERVER)
      s_aLogger.warn ("Consider using the Sun Server Runtime by specifiying '-server' on the commandline!");

    if (SystemProperties.getJavaVersion ().startsWith ("1.6.0_14"))
      s_aLogger.warn ("This Java version is bad for development - breakpoints don't work in the debugger!");

    if (getClass ().desiredAssertionStatus ())
      s_aLogger.warn ("Java assertions are enabled - this should be disabled in production!");
  }

  protected final void logClassPath ()
  {
    // List class path elements in trace mode
    if (GlobalDebug.isDebugMode ())
    {
      final ICommonsList <String> aCP = ClassPathHelper.getAllClassPathEntries ();
      s_aLogger.info ("Class path [" + aCP.size () + " elements]:");
      for (final String sCP : aCP.getSorted (Comparator.naturalOrder ()))
        s_aLogger.info ("  " + sCP);
    }
  }

  protected final void logInitParameters (@Nonnull final ServletContext aSC)
  {
    // Put them in a sorted map
    final ICommonsNavigableMap <String, String> aParams = new CommonsTreeMap <> ();
    final Enumeration <?> aEnum = aSC.getInitParameterNames ();
    while (aEnum.hasMoreElements ())
    {
      final String sName = (String) aEnum.nextElement ();
      final String sValue = aSC.getInitParameter (sName);
      aParams.put (sName, sValue);
    }

    if (aParams.isEmpty ())
      s_aLogger.info ("No servlet context init-parameters present");
    else
    {
      // Emit them
      s_aLogger.info ("Servlet context init-parameters:");
      for (final Map.Entry <String, String> aEntry : aParams.entrySet ())
        s_aLogger.info ("  " + aEntry.getKey () + "=" + aEntry.getValue ());
    }
  }

  protected final void logThirdpartyModules ()
  {
    // List all third party modules for later evaluation
    final ICommonsSet <IThirdPartyModule> aModules = ThirdPartyModuleRegistry.getInstance ()
                                                                             .getAllRegisteredThirdPartyModules ();
    if (!aModules.isEmpty ())
    {
      s_aLogger.info ("Using the following third party modules:");
      for (final IThirdPartyModule aModule : aModules.getSorted (IHasDisplayName.getComparatorCollating (SystemHelper.getSystemLocale ())))
        if (!aModule.isOptional ())
        {
          String sMsg = "  " + aModule.getDisplayName ();
          if (aModule.getVersion () != null)
            sMsg += ' ' + aModule.getVersion ().getAsString (true);
          sMsg += " licensed under " + aModule.getLicense ().getDisplayName ();
          if (aModule.getLicense ().getVersion () != null)
            sMsg += ' ' + aModule.getLicense ().getVersion ().getAsString ();
          s_aLogger.info (sMsg);
        }
    }
  }

  protected final void logJMX ()
  {
    if (SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote") != null)
    {
      final String sPort = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.port");
      final String sSSL = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.ssl");
      final String sAuthenticate = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.authenticate");
      final String sPasswordFile = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.password.file");
      final String sAccessFile = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.access.file");
      s_aLogger.info ("Remote JMX is enabled!");
      if (sPort != null)
        s_aLogger.info ("  Port=" + sPort);
      if (sSSL != null)
        s_aLogger.info ("  SSL enabled=" + sSSL);
      if (sAuthenticate != null)
        s_aLogger.info ("  Authenticate=" + sAuthenticate);
      if (sPasswordFile != null)
        s_aLogger.info ("  Password file=" + sPasswordFile);
      if (sAccessFile != null)
        s_aLogger.info ("  Access file=" + sAccessFile);
    }
  }

  @OverrideOnDemand
  protected void logStartupInfo (@Nonnull final ServletContext aSC)
  {
    logServerInfo (aSC);
    logClassPath ();
    logInitParameters (aSC);
    logThirdpartyModules ();
    logJMX ();
  }

  /**
   * Callback before anything else happens. Handle with care!
   *
   * @param aSC
   *        ServletContext
   */
  @OverrideOnDemand
  protected void onTheVeryBeginning (@Nonnull final ServletContext aSC)
  {}

  /**
   * Callback before init. By default some relevant debug information is emitted
   *
   * @param aSC
   *        ServletContext
   */
  @OverrideOnDemand
  protected void beforeContextInitialized (@Nonnull final ServletContext aSC)
  {}

  /**
   * Callback after init
   *
   * @param aSC
   *        ServletContext
   */
  @OverrideOnDemand
  protected void afterContextInitialized (@Nonnull final ServletContext aSC)
  {}

  /**
   * Get the value of the servlet context init-parameter that represents the
   * <b>debug</b> flag. This value is than converted to a boolean internally.
   *
   * @param aSC
   *        The servlet context under investigation. Never <code>null</code>.
   * @return The string value of the <b>debug</b> init-parameter. May be
   *         <code>null</code> if no such init-parameter is present.
   */
  @Nullable
  @OverrideOnDemand
  protected String getInitParameterDebug (@Nonnull final ServletContext aSC)
  {
    return aSC.getInitParameter (DEFAULT_INIT_PARAMETER_DEBUG);
  }

  /**
   * Get the value of the servlet context init-parameter that represents the
   * <b>production</b> flag. This value is than converted to a boolean
   * internally.
   *
   * @param aSC
   *        The servlet context under investigation. Never <code>null</code>.
   * @return The string value of the <b>production</b> init-parameter. May be
   *         <code>null</code> if no such init-parameter is present.
   */
  @Nullable
  @OverrideOnDemand
  protected String getInitParameterProduction (@Nonnull final ServletContext aSC)
  {
    return aSC.getInitParameter (DEFAULT_INIT_PARAMETER_PRODUCTION);
  }

  /**
   * Get the value of the servlet context init-parameter that represents the
   * <b>no-startup-info</b> flag. This value is than converted to a boolean
   * internally.
   *
   * @param aSC
   *        The servlet context under investigation. Never <code>null</code>.
   * @return The string value of the <b>no-startup-info</b> init-parameter. May
   *         be <code>null</code> if no such init-parameter is present.
   */
  @Nullable
  @OverrideOnDemand
  protected String getInitParameterNoStartupInfo (@Nonnull final ServletContext aSC)
  {
    return aSC.getInitParameter (INIT_PARAMETER_NO_STARTUP_INFO);
  }

  /**
   * Get the value of the servlet context init-parameter that represents the
   * <b>no-startup-info</b> flag. This value is than converted to a boolean
   * internally.
   *
   * @param aSC
   *        The servlet context under investigation. Never <code>null</code>.
   * @param bProductionMode
   *        <code>true</code> if we're in production mode, <code>false</code> if
   *        not.
   * @return The string value of the <b>no-startup-info</b> init-parameter. May
   *         be <code>null</code> if no such init-parameter is present.
   */
  @Nullable
  @OverrideOnDemand
  protected String getInitParameterServerURL (@Nonnull final ServletContext aSC, final boolean bProductionMode)
  {
    final String sParameterName = bProductionMode ? INIT_PARAMETER_SERVER_URL_PRODUCTION : INIT_PARAMETER_SERVER_URL;
    return aSC.getInitParameter (sParameterName);
  }

  @Nonnull
  protected String getServletContextPath (@Nonnull final ServletContext aSC)
  {
    String sPath = aSC.getRealPath (".");
    if (sPath == null)
    {
      // Fallback for Undertow
      sPath = aSC.getRealPath ("");
    }
    if (StringHelper.hasNoText (sPath))
      throw new IllegalStateException ("Failed to determine real path of ServletContext " + aSC);
    return sPath;
  }

  /**
   * Get the data path to be used for this application. By default the servlet
   * context init-parameter {@link #INIT_PARAMETER_DATA_PATH} is evaluated. If
   * non is present, the servlet context path is used.
   *
   * @param aSC
   *        The servlet context. Never <code>null</code>.
   * @return The data path to use. May neither be <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @OverrideOnDemand
  protected String getDataPath (@Nonnull final ServletContext aSC)
  {
    String sDataPath = aSC.getInitParameter (INIT_PARAMETER_DATA_PATH);
    if (StringHelper.hasNoText (sDataPath))
    {
      // Use legacy parameter name
      sDataPath = aSC.getInitParameter ("storagePath");
      if (StringHelper.hasText (sDataPath))
      {
        s_aLogger.error ("You are using the old 'storagePath' parameter. Please use '" +
                         INIT_PARAMETER_DATA_PATH +
                         "' instead!");
      }
    }
    if (StringHelper.hasNoText (sDataPath))
    {
      // No storage path provided in web.xml
      sDataPath = getServletContextPath (aSC);
      if (GlobalDebug.isDebugMode () && s_aLogger.isInfoEnabled ())
        s_aLogger.info ("No servlet context init-parameter '" +
                        INIT_PARAMETER_DATA_PATH +
                        "' found! Defaulting to servlet context path '" +
                        sDataPath +
                        "'");
    }
    return sDataPath;
  }

  /**
   * Determine if the file access should be checked upon startup. By default
   * this is done by evaluating the servlet context init-parameter
   * {@link #INIT_PARAMETER_NO_CHECK_FILE_ACCESS}.
   *
   * @param aSC
   *        The servlet context. Never <code>null</code>.
   * @return <code>true</code> if file access should be checked,
   *         <code>false</code> otherwise.
   */
  @OverrideOnDemand
  protected boolean shouldCheckFileAccess (@Nonnull final ServletContext aSC)
  {
    return !StringParser.parseBool (aSC.getInitParameter (INIT_PARAMETER_NO_CHECK_FILE_ACCESS));
  }

  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void initPaths (@Nonnull final ServletContext aSC)
  {
    // Get the ServletContext base path
    final String sServletContextPath = getServletContextPath (aSC);
    // Get the data path
    final String sDataPath = getDataPath (aSC);
    if (StringHelper.hasNoText (sDataPath))
      throw new InitializationException ("No data path was provided!");
    final File aDataPath = new File (sDataPath).getAbsoluteFile ();
    // Should the file access check be performed?
    final boolean bFileAccessCheck = shouldCheckFileAccess (aSC);
    // Init the IO layer
    WebFileIO.initPaths (aDataPath, new File (sServletContextPath), bFileAccessCheck);
  }

  /**
   * This method is called to initialize the global ID factory. By default a
   * file-based {@link WebIOIntIDFactory} with the filename {@link #ID_FILENAME}
   * is created.
   */
  @OverrideOnDemand
  protected void initGlobalIDFactory ()
  {
    // Set persistent ID provider: file based
    GlobalIDFactory.setPersistentLongIDFactory (new WebIOLongIDFactory (ID_FILENAME));
    GlobalIDFactory.setPersistentIntIDFactory ( () -> (int) GlobalIDFactory.getNewPersistentLongID ());
  }

  public final void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    final ServletContext aSC = aSCE.getServletContext ();

    if (s_aInited.getAndSet (true))
      throw new IllegalStateException ("WebAppListener was already instantiated!");

    final StopWatch aSW = StopWatch.createdStarted ();
    m_aInitializationStartDT = PDTFactory.getCurrentLocalDateTime ();

    // Call callback
    onTheVeryBeginning (aSC);

    // set global debug/trace mode
    final boolean bDebugMode = StringParser.parseBool (getInitParameterDebug (aSC));
    final boolean bProductionMode = StringParser.parseBool (getInitParameterProduction (aSC));
    GlobalDebug.setDebugModeDirect (bDebugMode);
    GlobalDebug.setProductionModeDirect (bProductionMode);

    final boolean bNoStartupInfo = StringParser.parseBool (getInitParameterNoStartupInfo (aSC));
    if (!bNoStartupInfo)
    {
      // Requires the global debug things to be present
      logStartupInfo (aSC);
    }

    // StaticServerInfo
    {
      final String sInitParameter = getInitParameterServerURL (aSC, bProductionMode);
      if (StringHelper.hasText (sInitParameter))
      {
        final URL aURL = URLHelper.getAsURL (sInitParameter);
        if (aURL != null)
        {
          StaticServerInfo.init (aURL.getProtocol (), aURL.getHost (), aURL.getPort (), aSC.getContextPath ());
        }
        else
          s_aLogger.error ("The init-parameter for the server URL" +
                           (bProductionMode ? " (production mode)" : " (non-production mode)") +
                           "contains the non-URL value '" +
                           sInitParameter +
                           "'");
      }
    }

    // Call callback
    beforeContextInitialized (aSC);

    // begin global context
    WebScopeManager.onGlobalBegin (aSC);

    // Init IO
    initPaths (aSC);

    // Set persistent ID provider - must be done after IO is setup
    initGlobalIDFactory ();

    // Callback
    afterContextInitialized (aSC);

    // Remember end time
    m_aInitializationEndDT = PDTFactory.getCurrentLocalDateTime ();

    // Finally
    if (s_aLogger.isInfoEnabled ())
      s_aLogger.info ("Servlet context '" +
                      aSC.getServletContextName () +
                      "' was initialized in " +
                      aSW.stopAndGetMillis () +
                      " milli seconds");
  }

  /**
   * @return The date time when the initialization started. May be
   *         <code>null</code> if the context was never initialized.
   */
  @Nullable
  public LocalDateTime getInitializationStartDT ()
  {
    return m_aInitializationStartDT;
  }

  /**
   * @return The date time when the initialization ended. May be
   *         <code>null</code> if the context was never initialized or is just
   *         in the middle of initialization.
   */
  @Nullable
  public LocalDateTime getInitializationEndDT ()
  {
    return m_aInitializationEndDT;
  }

  /**
   * before destroy
   *
   * @param aSC
   *        the servlet context in destruction
   */
  @OverrideOnDemand
  protected void beforeContextDestroyed (@Nonnull final ServletContext aSC)
  {}

  /**
   * after destroy
   *
   * @param aSC
   *        the servlet context in destruction
   */
  @OverrideOnDemand
  protected void afterContextDestroyed (@Nonnull final ServletContext aSC)
  {}

  /**
   * @return <code>true</code> to write statistics on context shutdown,
   *         <code>false</code> to not do so
   */
  public final boolean isHandleStatisticsOnEnd ()
  {
    return m_bHandleStatisticsOnEnd;
  }

  @Nonnull
  public final WebAppListener setHandleStatisticsOnEnd (final boolean bHandleStatisticsOnEnd)
  {
    m_bHandleStatisticsOnEnd = bHandleStatisticsOnEnd;
    return this;
  }

  /**
   * @return The filename where the statistics should be written to. May neither
   *         be <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @OverrideOnDemand
  protected String getStatisticsFilename ()
  {
    return "statistics/" +
           PDTFactory.getCurrentYear () +
           "/statistics_" +
           PDTIOHelper.getCurrentLocalDateTimeForFilename () +
           ".xml";
  }

  /**
   * Handle all statistics AFTER the context was shut down. Depending on
   * {@link #isHandleStatisticsOnEnd()} this method is called or not.
   */
  @OverrideOnDemand
  protected void handleStatisticsOnEnd ()
  {
    // Avoid error if startup failed
    if (WebFileIO.isInited ())
    {
      // serialize statistics
      try
      {
        final File aDestPath = WebFileIO.getDataIO ().getFile (getStatisticsFilename ());
        final IMicroDocument aDoc = StatisticsExporter.getAsXMLDocument ();
        aDoc.getDocumentElement ().setAttribute ("location", "shutdown");
        aDoc.getDocumentElement ()
            .setAttribute ("datetime", PDTWebDateHelper.getAsStringXSD (PDTFactory.getCurrentLocalDateTime ()));
        SimpleFileIO.writeFile (aDestPath, MicroWriter.getXMLString (aDoc), XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to write statistics on context shutdown.", t);
      }
    }
    else
      s_aLogger.error ("Not writing statistics because WebFileIO was not initialized!");
  }

  public final void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {
    final ServletContext aSC = aSCE.getServletContext ();

    final StopWatch aSW = StopWatch.createdStarted ();
    if (s_aLogger.isInfoEnabled ())
      s_aLogger.info ("Servlet context '" + aSC.getServletContextName () + "' is being destroyed");

    // Callback before global scope end
    beforeContextDestroyed (aSC);

    // Shutdown global scope and destroy all singletons
    WebScopeManager.onGlobalEnd ();

    // Callback after global scope end
    afterContextDestroyed (aSC);

    // Handle statistics
    if (isHandleStatisticsOnEnd ())
      handleStatisticsOnEnd ();

    // Reset base path - mainly for testing
    WebFileIO.resetPaths ();

    // Clear commons cache also manually - but after destroy because it
    // is used in equals and hashCode implementations
    XMLCleanup.cleanup ();
    CommonsCleanup.cleanup ();

    // De-init
    s_aInited.set (false);

    if (s_aLogger.isInfoEnabled ())
      s_aLogger.info ("Servlet context '" +
                      aSC.getServletContextName () +
                      "' was destroyed in " +
                      aSW.stopAndGetMillis () +
                      " milli seconds");
  }

  /**
   * Notification that a session was created.
   *
   * @param aSessionEvent
   *        The notification event. Never <code>null</code>.
   */
  public final void sessionCreated (@Nonnull final HttpSessionEvent aSessionEvent)
  {
    // Create the SessionScope
    final HttpSession aHttpSession = aSessionEvent.getSession ();
    WebScopeManager.onSessionBegin (aHttpSession);
  }

  /**
   * Notification that a session is about to be invalidated.
   *
   * @param aSessionEvent
   *        The notification event. Never <code>null</code>.
   */
  public final void sessionDestroyed (@Nonnull final HttpSessionEvent aSessionEvent)
  {
    // Destroy the SessionScope
    final HttpSession aHttpSession = aSessionEvent.getSession ();
    WebScopeManager.onSessionEnd (aHttpSession);
  }
}
