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
package com.helger.photon.core.servlet;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.debug.GlobalDebug;
import com.helger.html.hc.config.HCSettings;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.core.ajax.ApplicationAjaxManager;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.init.IApplicationInitializer;
import com.helger.photon.core.app.layout.ApplicationLayoutManager;
import com.helger.photon.core.smtp.AuditingEmailDataTransportListener;
import com.helger.photon.core.userdata.UserDataManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.password.constraint.PasswordConstraintList;
import com.helger.photon.security.password.constraint.PasswordConstraintMinLength;
import com.helger.smtp.EmailGlobalSettings;
import com.helger.smtp.transport.listener.LoggingConnectionListener;
import com.helger.web.mock.MockHttpServletResponse;
import com.helger.web.mock.OfflineHttpServletRequest;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Callbacks for the application server
 *
 * @author Philip Helger
 * @param <LECTYPE>
 *        Layout execution context type
 */
public abstract class AbstractWebAppListenerMultiApp <LECTYPE extends ILayoutExecutionContext> extends WebAppListener
{
  public static final int DEFAULT_PASSWORD_MIN_LENGTH = 6;

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractWebAppListenerMultiApp.class);

  @Nonnull
  @Nonempty
  protected abstract Map <String, IApplicationInitializer <LECTYPE>> getAllInitializers ();

  /**
   * Set global system properties, after the content was initialized but before
   * the application specific init is started
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void initGlobals ()
  {
    // Enable when ready
    WebScopeManager.setSessionPassivationAllowed (false);

    // UDO to data directory
    UserDataManager.setServletContextIO (false);

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
    }
  }

  @Override
  protected final void afterContextInitialized (@Nonnull final ServletContext aSC)
  {
    // Global properties
    initGlobals ();

    // Determine all initializers
    final Map <String, IApplicationInitializer <LECTYPE>> aIniter = getAllInitializers ();
    if (CollectionHelper.isEmpty (aIniter))
      throw new IllegalStateException ("No application initializers provided!");

    // Invoke all initializers
    for (final Map.Entry <String, IApplicationInitializer <LECTYPE>> aEntry : aIniter.entrySet ())
    {
      final String sAppID = aEntry.getKey ();
      WebScopeManager.onRequestBegin (sAppID,
                                      new OfflineHttpServletRequest (aSC, false),
                                      new MockHttpServletResponse ());
      try
      {
        final IApplicationInitializer <LECTYPE> aInitializer = aEntry.getValue ();

        // Set per-application settings
        aInitializer.initApplicationSettings ();

        // Register application locales
        aInitializer.initLocales (ApplicationLocaleManager.getLocaleMgr ());

        // Create all menu items
        aInitializer.initMenu (ApplicationMenuTree.getTree ());

        // Create the application layouts - after the menus!
        aInitializer.initLayout (ApplicationLayoutManager.<LECTYPE> getInstance ());

        // Register all Ajax functions here
        aInitializer.initAjax (ApplicationAjaxManager.getInstance ());

        // All other things come last
        aInitializer.initRest ();
      }
      catch (final RuntimeException ex)
      {
        // Log so that the failed application can easily be determined.
        s_aLogger.error ("Failed to init application '" + sAppID + "'", ex);

        // re-throw
        throw ex;
      }
      finally
      {
        WebScopeManager.onRequestEnd ();
      }
    }
  }
}
