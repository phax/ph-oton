/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webbasics.servlet;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.GlobalDebug;
import com.helger.commons.SystemProperties;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.cleanup.CommonsCleanup;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.exceptions.InitializationException;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.io.file.SimpleFileIO;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.name.ComparatorHasDisplayName;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.stats.utils.StatisticsExporter;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.system.EJVMVendor;
import com.helger.commons.thirdparty.IThirdPartyModule;
import com.helger.commons.thirdparty.ThirdPartyModuleRegistry;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.url.URLUtils;
import com.helger.commons.utils.ClassPathHelper;
import com.helger.commons.vminit.VirtualMachineInitializer;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.io.PDTIOHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.basic.app.io.WebIOIntIDFactory;
import com.helger.web.datetime.PDTWebDateUtils;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.web.servlet.server.StaticServerInfo;

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
    VirtualMachineInitializer.runInitialization ();

    // Ensure that any AWT code runs headless (fonts etc.)
    SystemProperties.setPropertyValue ("java.awt.headless", "true");
  }

  /** Name of the initialization parameter to enable debug. */
  public static final String DEFAULT_INIT_PARAMETER_DEBUG = "debug";

  /** Name of the initialization parameter to enable production mode. */
  public static final String DEFAULT_INIT_PARAMETER_PRODUCTION = "production";

  /** Name of the initialization parameter for the storagePath. */
  public static final String INIT_PARAMETER_DATA_PATH = "dataPath";

  /** Name of the initialization parameter to disable logging the startup info. */
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
  }

  protected final void logClassPath ()
  {
    // List class path elements in trace mode
    if (GlobalDebug.isDebugMode ())
    {
      final List <String> aCP = ClassPathHelper.getAllClassPathEntries ();
      s_aLogger.info ("Class path [" + aCP.size () + " elements]:");
      for (final String sCP : CollectionHelper.getSortedInline (aCP))
        s_aLogger.info ("  " + sCP);
    }
  }

  protected final void logInitParameters (@Nonnull final ServletContext aSC)
  {
    s_aLogger.info ("Servlet context init-parameters:");

    // Put them in a sorted map
    final Map <String, String> aParams = new TreeMap <String, String> ();
    final Enumeration <?> aEnum = aSC.getInitParameterNames ();
    while (aEnum.hasMoreElements ())
    {
      final String sName = (String) aEnum.nextElement ();
      final String sValue = aSC.getInitParameter (sName);
      aParams.put (sName, sValue);
    }

    // Emit them
    for (final Map.Entry <String, String> aEntry : aParams.entrySet ())
      s_aLogger.info ("  " + aEntry.getKey () + "=" + aEntry.getValue ());
  }

  protected final void logThirdpartyModules ()
  {
    // List all third party modules for later evaluation
    final Set <IThirdPartyModule> aModules = ThirdPartyModuleRegistry.getInstance ()
                                                                     .getAllRegisteredThirdPartyModules ();
    if (!aModules.isEmpty ())
    {
      s_aLogger.info ("Using the following third party modules:");
      for (final IThirdPartyModule aModule : CollectionHelper.getSorted (aModules,
                                                                         new ComparatorHasDisplayName <IHasDisplayName> (null)))
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
    GlobalIDFactory.setPersistentIntIDFactory (new WebIOIntIDFactory (ID_FILENAME));
  }

  public final void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    final ServletContext aSC = aSCE.getServletContext ();

    if (s_aInited.getAndSet (true))
      throw new IllegalStateException ("WebAppListener was already instantiated!");

    final StopWatch aSW = new StopWatch (true);
    m_aInitializationStartDT = PDTFactory.getCurrentLocalDateTime ();

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
        final URL aURL = URLUtils.getAsURL (sInitParameter);
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
  @OverrideOnDemand
  protected boolean isHandleStatisticsOnEnd ()
  {
    return true;
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
           PDTIOHelper.getCurrentDateTimeForFilename () +
           ".xml";
  }

  /**
   * Handle all statistics AFTER the context was shut down. Depending on
   * {@link #isHandleStatisticsOnEnd()} this method is called or not.
   */
  @OverrideOnDemand
  protected void handleStatisticsOnEnd ()
  {
    // serialize statistics
    try
    {
      final File aDestPath = WebFileIO.getFile (getStatisticsFilename ());
      final IMicroDocument aDoc = StatisticsExporter.getAsXMLDocument ();
      aDoc.getDocumentElement ().setAttribute ("location", "shutdown");
      aDoc.getDocumentElement ().setAttribute ("datetime",
                                               PDTWebDateUtils.getAsStringXSD (PDTFactory.getCurrentDateTime ()));
      SimpleFileIO.writeFile (aDestPath, MicroWriter.getXMLString (aDoc), XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ);
    }
    catch (final Throwable t)
    {
      s_aLogger.error ("Failed to write statistics on context shutdown.", t);
    }
  }

  public final void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {
    final ServletContext aSC = aSCE.getServletContext ();

    final StopWatch aSW = new StopWatch (true);
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
