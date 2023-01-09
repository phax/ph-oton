/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.impl.CommonsTreeMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsNavigableMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTWebDateHelper;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.lang.ClassPathHelper;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.locale.language.LanguageCache;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.pool.ObjectPool;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.system.EJVMVendor;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.system.SystemProperties;
import com.helger.commons.thirdparty.IThirdPartyModule;
import com.helger.commons.thirdparty.ThirdPartyModuleRegistry;
import com.helger.commons.timing.StopWatch;
import com.helger.commons.typeconvert.TypeConverter;
import com.helger.commons.url.URLHelper;
import com.helger.css.propertyvalue.CSSValue;
import com.helger.dao.AbstractDAO;
import com.helger.datetime.util.PDTIOHelper;
import com.helger.html.hc.config.HCSettings;
import com.helger.photon.ajax.GlobalAjaxInvoker;
import com.helger.photon.ajax.IAjaxRegistry;
import com.helger.photon.api.GlobalAPIInvoker;
import com.helger.photon.api.IAPIRegistry;
import com.helger.photon.app.io.WebFileIO;
import com.helger.photon.app.io.WebIOLongIDFactory;
import com.helger.photon.app.resource.WebSiteResourceCache;
import com.helger.photon.core.CPhotonVersion;
import com.helger.photon.core.PhotonCoreInit;
import com.helger.photon.core.locale.GlobalLocaleManager;
import com.helger.photon.core.locale.ILocaleManager;
import com.helger.photon.core.smtp.AuditingEmailDataTransportListener;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.password.constraint.PasswordConstraintList;
import com.helger.photon.security.password.constraint.PasswordConstraintMinLength;
import com.helger.servlet.ServletContextPathHolder;
import com.helger.servlet.ServletHelper;
import com.helger.servlet.StaticServerInfo;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.smtp.EmailGlobalSettings;
import com.helger.smtp.transport.listener.LoggingConnectionListener;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.util.statistics.StatisticsExporter;
import com.helger.xservlet.filter.XServletFilterConsistency;

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

  public static final int DEFAULT_PASSWORD_MIN_LENGTH = 8;

  public static final boolean DEFAULT_HANDLE_STATISTICS_ON_END = true;

  /** The logger to use. */
  private static final Logger LOGGER = LoggerFactory.getLogger (WebAppListener.class);

  private static final AtomicBoolean ONLY_ONE_INSTANCE_ALLOWED = new AtomicBoolean (true);
  private static final AtomicBoolean INITED = new AtomicBoolean (false);

  private LocalDateTime m_aInitializationStartDT;
  private LocalDateTime m_aInitializationEndDT;
  private boolean m_bHandleStatisticsOnEnd = DEFAULT_HANDLE_STATISTICS_ON_END;

  public WebAppListener ()
  {}

  public static void setOnlyOneInstanceAllowed (final boolean bCheck)
  {
    ONLY_ONE_INSTANCE_ALLOWED.set (bCheck);
  }

  public static boolean isOnlyOneInstanceAllowed ()
  {
    return ONLY_ONE_INSTANCE_ALLOWED.get ();
  }

  protected static final void logLogo ()
  {
    LOGGER.info ("       _                 _              ");
    LOGGER.info (" _ __ | |__         ___ | |_ ___  _ __  ");
    LOGGER.info ("| '_ \\| '_ \\ _____ / _ \\| __/ _ \\| '_ \\ ");
    LOGGER.info ("| |_) | | | |_____| (_) | || (_) | | | |");
    LOGGER.info ("| .__/|_| |_|      \\___/ \\__\\___/|_| |_|");
    // Ensure version is always right aligned
    // Use 20 chars to also cater for "SNAPSHOT" versions
    final String sSpaces = StringHelper.getRepeated (' ', Math.max (20 - CPhotonVersion.BUILD_VERSION.length (), 0));
    LOGGER.info ("|_|                " + sSpaces + "v" + CPhotonVersion.BUILD_VERSION);
  }

  protected final void logServerInfo (@Nonnull final ServletContext aSC)
  {
    // Print Java and Server (e.g. Tomcat) info
    LOGGER.info ("Java " +
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
      LOGGER.warn ("Consider using the Sun Server Runtime by specifiying '-server' on the commandline!");

    if (getClass ().desiredAssertionStatus ())
      LOGGER.warn ("Java assertions are enabled - this should be disabled in production!");
  }

  protected static final void logClassPath ()
  {
    // List class path elements in trace mode
    if (GlobalDebug.isDebugMode ())
    {
      final ICommonsList <String> aCP = ClassPathHelper.getAllClassPathEntries ();
      LOGGER.info ("Class path [" + aCP.size () + " elements]:");
      for (final String sCP : aCP.getSorted (Comparator.naturalOrder ()))
        LOGGER.info ("  " + sCP);
    }
  }

  protected static final void logInitParameters (@Nonnull final ServletContext aSC)
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
      LOGGER.info ("No servlet context init-parameters present");
    else
    {
      // Emit them
      LOGGER.info ("Servlet context init-parameters:");
      for (final Map.Entry <String, String> aEntry : aParams.entrySet ())
        LOGGER.info ("  " + aEntry.getKey () + "=" + aEntry.getValue ());
    }
  }

  protected static final void logThirdpartyModules ()
  {
    // List all third party modules for later evaluation
    final ICommonsSet <IThirdPartyModule> aModules = ThirdPartyModuleRegistry.getInstance ().getAllRegisteredThirdPartyModules ();
    if (!aModules.isEmpty ())
    {
      LOGGER.info ("Using the following third party modules:");
      for (final IThirdPartyModule aModule : aModules.getSorted (IHasDisplayName.getComparatorCollating (SystemHelper.getSystemLocale ())))
        if (!aModule.isOptional ())
        {
          String sMsg = "  " + aModule.getDisplayName ();
          if (aModule.getVersion () != null)
            sMsg += ' ' + aModule.getVersion ().getAsString (true);
          sMsg += " licensed under " + aModule.getLicense ().getDisplayName ();
          if (aModule.getLicense ().getVersion () != null)
            sMsg += ' ' + aModule.getLicense ().getVersion ().getAsString ();
          LOGGER.info (sMsg);
        }
    }
  }

  protected static final void logJMX ()
  {
    if (SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote") != null)
    {
      final String sPort = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.port");
      final String sSSL = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.ssl");
      final String sAuthenticate = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.authenticate");
      final String sPasswordFile = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.password.file");
      final String sAccessFile = SystemProperties.getPropertyValueOrNull ("com.sun.management.jmxremote.access.file");
      LOGGER.info ("Remote JMX is enabled!");
      if (sPort != null)
        LOGGER.info ("  Port=" + sPort);
      if (sSSL != null)
        LOGGER.info ("  SSL enabled=" + sSSL);
      if (sAuthenticate != null)
        LOGGER.info ("  Authenticate=" + sAuthenticate);
      if (sPasswordFile != null)
        LOGGER.info ("  Password file=" + sPasswordFile);
      if (sAccessFile != null)
        LOGGER.info ("  Access file=" + sAccessFile);
    }
  }

  @OverrideOnDemand
  protected void logStartupInfo (@Nonnull final ServletContext aSC)
  {
    logLogo ();
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
   * <b>{@value #DEFAULT_INIT_PARAMETER_DEBUG}</b> flag. This value is than
   * converted to a boolean internally.
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
   * <b>{@value #DEFAULT_INIT_PARAMETER_PRODUCTION}</b> flag. This value is than
   * converted to a boolean internally.
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
   * <b>{@value #INIT_PARAMETER_NO_STARTUP_INFO}</b> flag. This value is than
   * converted to a boolean internally.
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
   * <b>{@value #INIT_PARAMETER_SERVER_URL_PRODUCTION}</b> or
   * <b>{@value #INIT_PARAMETER_SERVER_URL}</b> flag.
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
  @Nonempty
  protected String getServletContextPath (@Nonnull final ServletContext aSC) throws IllegalStateException
  {
    return ServletHelper.getServletContextBasePath (aSC);
  }

  /**
   * Get the data path to be used for this application. By default the servlet
   * context init-parameter <b>{@link #INIT_PARAMETER_DATA_PATH}</b> is
   * evaluated. If non is present, the servlet context path is used.
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
        LOGGER.error ("You are using the old 'storagePath' parameter. Please use '" + INIT_PARAMETER_DATA_PATH + "' instead!");
      }
    }
    if (StringHelper.hasNoText (sDataPath))
    {
      // No storage path provided in web.xml
      sDataPath = getServletContextPath (aSC);
      if (GlobalDebug.isDebugMode () && LOGGER.isInfoEnabled ())
        LOGGER.info ("No servlet context init-parameter '" +
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

  /**
   * This method is supposed to call
   * {@link WebFileIO#initPaths(File, String, boolean)} with parameters from
   * {@link #getServletContextPath(ServletContext)},
   * {@link #getDataPath(ServletContext)} and
   * {@link #shouldCheckFileAccess(ServletContext)}.
   *
   * @param aSC
   *        The servlet context to be initialized. Never <code>null</code>.
   */
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
    WebFileIO.initPaths (aDataPath, sServletContextPath, bFileAccessCheck);
  }

  /**
   * This method is called to initialize the global ID factory. By default a
   * file-based {@link WebIOLongIDFactory} with the filename
   * {@link #ID_FILENAME} is created. This is called init of the paths.
   */
  @OverrideOnDemand
  protected void initGlobalIDFactory ()
  {
    // Set persistent ID provider: file based
    GlobalIDFactory.setPersistentLongIDFactory (new WebIOLongIDFactory (ID_FILENAME));
    GlobalIDFactory.setPersistentIntIDFactory ( () -> (int) GlobalIDFactory.getNewPersistentLongID ());
  }

  /**
   * Init the default global settings. This is called after init of the global
   * ID factory.
   */
  protected static final void initDefaultGlobalSettings ()
  {
    // Enable when ready
    WebScopeManager.setSessionPassivationAllowed (false);

    // Define the password constrains
    GlobalPasswordSettings.setPasswordConstraintList (new PasswordConstraintList (new PasswordConstraintMinLength (DEFAULT_PASSWORD_MIN_LENGTH)));

    // Email global settings
    EmailGlobalSettings.addEmailDataTransportListener (new AuditingEmailDataTransportListener ());
    if (GlobalDebug.isDebugMode ())
    {
      EmailGlobalSettings.addConnectionListener (new LoggingConnectionListener ());
    }
    else
    {
      // HTML output settings
      HCSettings.getMutableConversionSettings ().setToOptimized ();

      // Disable CSS Value consistency checks
      CSSValue.setConsistencyChecksEnabled (false);
    }
  }

  /**
   * Set global system properties, after the content was initialized but before
   * the application specific init is started
   */
  @OverrideOnDemand
  protected void initGlobalSettings ()
  {}

  /**
   * Init all available locales. This is called after init of global settings.
   *
   * @param aLocaleMgr
   *        Locale manager
   */
  @OverrideOnDemand
  protected void initLocales (@Nonnull final ILocaleManager aLocaleMgr)
  {}

  /**
   * Init menu. If you have more than one menu tree, you need to init them all
   * in here. This is called after init locales.
   */
  @OverrideOnDemand
  protected void initMenu ()
  {}

  /**
   * Init all Ajax functions. This is called after init of menu.
   *
   * @param aAjaxRegistry
   *        Ajax registry
   */
  @OverrideOnDemand
  protected void initAjax (@Nonnull final IAjaxRegistry aAjaxRegistry)
  {}

  /**
   * Init all APIs. This is called after init AJAX.
   *
   * @param aAPIRegistry
   *        API registry
   */
  @OverrideOnDemand
  protected void initAPI (@Nonnull final IAPIRegistry aAPIRegistry)
  {}

  /**
   * Init security stuff. This is called after init API.
   */
  @OverrideOnDemand
  protected void initSecurity ()
  {}

  /**
   * Init UI stuff. This is called after init security.
   */
  @OverrideOnDemand
  protected void initUI ()
  {}

  /**
   * Init managers. This is called after init UI.
   */
  @OverrideOnDemand
  protected void initManagers ()
  {}

  /**
   * Init jobs. This is called after init managers.
   *
   * @since 8.1.2
   */
  @OverrideOnDemand
  protected void initJobs ()
  {}

  /**
   * @return <code>true</code> if Runtime exceptions on startup and shutdown
   *         should be logged or not. Enabled by default.
   * @since 8.3.1
   */
  @OverrideOnDemand
  protected boolean isLogExceptions ()
  {
    return true;
  }

  public final void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Start contextInitialized");

    final ServletContext aSC = aSCE.getServletContext ();

    if (isOnlyOneInstanceAllowed ())
      if (INITED.getAndSet (true))
      {
        LOGGER.error ("WebAppListener was already instantiated!");
        throw new IllegalStateException ("WebAppListener was already instantiated!");
      }

    try
    {
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
            LOGGER.error ("The init-parameter for the server URL" +
                          (bProductionMode ? " (production mode)" : " (non-production mode)") +
                          "contains the non-URL value '" +
                          sInitParameter +
                          "'");
        }
      }

      // Call callback
      beforeContextInitialized (aSC);

      // begin global context
      if (isOnlyOneInstanceAllowed () || !WebScopeManager.isGlobalScopePresent ())
      {
        // Create scopes
        WebScopeManager.onGlobalBegin (aSC);

        // right order?
        PhotonCoreInit.startUp ();

        // Init IO
        initPaths (aSC);

        // Set persistent ID provider - must be done after IO is setup
        initGlobalIDFactory ();

        // Init default settings
        initDefaultGlobalSettings ();

        // Global properties
        initGlobalSettings ();

        // Register application locales
        initLocales (GlobalLocaleManager.getInstance ());

        // Init menu (per app)
        initMenu ();

        // Register all Ajax functions here
        initAjax (GlobalAjaxInvoker.getInstance ().getRegistry ());

        // Register all API functions here
        initAPI (GlobalAPIInvoker.getInstance ().getRegistry ());

        // Set all security related stuff
        initSecurity ();

        // UI stuff
        initUI ();

        // Init all managers
        initManagers ();

        // Init all jobs, AFTER managers
        initJobs ();
      }

      // Callback
      afterContextInitialized (aSC);

      // Remember end time
      m_aInitializationEndDT = PDTFactory.getCurrentLocalDateTime ();

      // Finally
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Servlet context '" +
                     aSC.getServletContextName () +
                     "' was initialized in " +
                     aSW.stopAndGetMillis () +
                     " milli seconds");
    }
    catch (final RuntimeException ex)
    {
      if (isLogExceptions ())
      {
        // Ensure it is logged on startup - otherwise (especially for Docker
        // images) it is hard to trace
        LOGGER.error ("Context Initialization Error", ex);
      }
      throw ex;
    }
    finally
    {
      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("End contextInitialized");
    }
  }

  /**
   * @return The date time when the initialization started. May be
   *         <code>null</code> if the context was never initialized.
   */
  @Nullable
  public final LocalDateTime getInitializationStartDT ()
  {
    return m_aInitializationStartDT;
  }

  /**
   * @return The date time when the initialization ended. May be
   *         <code>null</code> if the context was never initialized or is just
   *         in the middle of initialization.
   */
  @Nullable
  public final LocalDateTime getInitializationEndDT ()
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
    return "statistics/" + PDTFactory.getCurrentYear () + "/statistics_" + PDTIOHelper.getCurrentLocalDateTimeForFilename () + ".xml";
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
        final IMicroDocument aDoc = StatisticsExporter.getAsXMLDocument ();
        aDoc.getDocumentElement ().setAttribute ("location", "shutdown");
        aDoc.getDocumentElement ().setAttribute ("datetime", PDTWebDateHelper.getAsStringXSD (PDTFactory.getCurrentLocalDateTime ()));

        final File aDestPath = WebFileIO.getDataIO ().getFile (getStatisticsFilename ());
        MicroWriter.writeToFile (aDoc, aDestPath);
      }
      catch (final Exception ex)
      {
        LOGGER.error ("Failed to write statistics on context shutdown", ex);
      }
    }
    else
      LOGGER.error ("Not writing statistics because WebFileIO was not initialized!");
  }

  public final void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Start contextDestroyed");

    try
    {
      final ServletContext aSC = aSCE.getServletContext ();

      final StopWatch aSW = StopWatch.createdStarted ();
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Servlet context '" + aSC.getServletContextName () + "' is being destroyed");

      // Callback before global scope end
      beforeContextDestroyed (aSC);

      // Shutdown global scope and destroy all singletons
      WebScopeManager.onGlobalEnd ();

      // Callback after global scope end
      afterContextDestroyed (aSC);

      // Handle statistics
      if (isHandleStatisticsOnEnd ())
        handleStatisticsOnEnd ();

      // Clean commons stuff etc
      PhotonCoreInit.shutdown ();

      if (isOnlyOneInstanceAllowed ())
      {
        // De-init
        INITED.set (false);
      }

      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Servlet context '" +
                     aSC.getServletContextName () +
                     "' was destroyed in " +
                     aSW.stopAndGetMillis () +
                     " milli seconds");
    }
    catch (final RuntimeException ex)
    {
      if (isLogExceptions ())
      {
        // Ensure it is logged on startup - otherwise (especially for Docker
        // images) it is hard to trace
        LOGGER.error ("Context Initialization Error", ex);
      }
      throw ex;
    }
    finally
    {
      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("End contextDestroyed");
    }
  }

  /**
   * Notification that a session was created.
   *
   * @param aSessionEvent
   *        The notification event. Never <code>null</code>.
   */
  public final void sessionCreated (@Nonnull final HttpSessionEvent aSessionEvent)
  {
    final HttpSession aHttpSession = aSessionEvent.getSession ();

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("A new session was created: " + aHttpSession);

    // Create the SessionScope
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
    final HttpSession aHttpSession = aSessionEvent.getSession ();

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Destroying session: " + aHttpSession);

    // Destroy the SessionScope
    WebScopeManager.onSessionEnd (aHttpSession);
  }

  public static void setSilentMode (final boolean bSilentMode)
  {
    // ph-commons
    LocaleCache.setSilentMode (bSilentMode);
    CountryCache.setSilentMode (bSilentMode);
    LanguageCache.setSilentMode (bSilentMode);
    ObjectPool.setSilentMode (bSilentMode);
    TypeConverter.setSilentMode (bSilentMode);
    AbstractDAO.setSilentMode (bSilentMode);
    // JAXBContextCache.setSilentMode (bSilentMode);

    // ph-web
    ServletContextPathHolder.setSilentMode (bSilentMode);
    XServletFilterConsistency.setSilentMode (bSilentMode);
    UnifiedResponse.setSilentMode (bSilentMode);

    // ph-oton
    HCSettings.setSilentMode (bSilentMode);
    WebSiteResourceCache.setSilentMode (bSilentMode);
    // DefaultLockManager instance
  }
}
