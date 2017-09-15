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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.debug.GlobalDebug;
import com.helger.css.propertyvalue.CSSValue;
import com.helger.html.hc.config.HCSettings;
import com.helger.photon.basic.app.appid.PhotonGlobalState;
import com.helger.photon.basic.app.locale.GlobalLocaleManager;
import com.helger.photon.basic.app.locale.ILocaleManager;
import com.helger.photon.basic.app.menu.MenuTree;
import com.helger.photon.core.ajax.GlobalAjaxInvoker;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.api.GlobalAPIInvoker;
import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.init.IApplicationInitializer;
import com.helger.photon.core.app.layout.ILayoutManager;
import com.helger.photon.core.app.layout.LayoutManagerProxy;
import com.helger.photon.core.smtp.AuditingEmailDataTransportListener;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.password.constraint.PasswordConstraintList;
import com.helger.photon.security.password.constraint.PasswordConstraintMinLength;
import com.helger.smtp.EmailGlobalSettings;
import com.helger.smtp.transport.listener.LoggingConnectionListener;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.web.scope.mgr.WebScoped;

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
  protected abstract ICommonsMap <String, IApplicationInitializer <LECTYPE>> getAllInitializers ();

  /**
   * Init all locales
   *
   * @param aLocaleMgr
   *        Locale manager to use
   */
  protected abstract void initLocales (@Nonnull final ILocaleManager aLocaleMgr);

  /**
   * Register all ajax functions
   *
   * @param aAjaxInvoker
   *        The ajax invoker to use
   */
  public void initAjax (@Nonnull final IAjaxInvoker aAjaxInvoker)
  {}

  /**
   * Register all API functions
   *
   * @param aAPIInvoker
   *        The API invoker to use
   */
  public void initAPI (@Nonnull final IAPIInvoker aAPIInvoker)
  {}

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
   * This method is called after globals and all initializers are done.
   *
   * @param aSC
   *        {@link ServletContext} that is initialized.
   * @since 7.0.2
   */
  @OverrideOnDemand
  protected void afterContextAndInitializersDone (@Nonnull final ServletContext aSC)
  {}

  @Override
  protected final void afterContextInitialized (@Nonnull final ServletContext aSC)
  {
    // Global properties
    initGlobals ();

    // Determine all initializers
    final ICommonsMap <String, IApplicationInitializer <LECTYPE>> aIniter = getAllInitializers ();
    if (aIniter.isEmpty ())
      throw new IllegalStateException ("No application initializers provided!");

    // Invoke all initializers
    for (final Map.Entry <String, IApplicationInitializer <LECTYPE>> aEntry : aIniter.entrySet ())
    {
      final String sAppID = aEntry.getKey ();
      try (final WebScoped aWebScoped = new WebScoped ())
      {
        final IApplicationInitializer <LECTYPE> aInitializer = aEntry.getValue ();

        // Create all menu items
        final MenuTree aMenuTree = new MenuTree ();
        aInitializer.initMenu (aMenuTree);
        PhotonGlobalState.getInstance ().state (sAppID).setMenuTree (aMenuTree);

        final ILayoutManager <LECTYPE> aLayoutMgr = new LayoutManagerProxy <> ();
        aInitializer.initLayout (aLayoutMgr);
        PhotonGlobalState.getInstance ().state (sAppID).setCustom ("lm", aLayoutMgr);

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
    }

    // Register application locales
    initLocales (GlobalLocaleManager.getInstance ());

    // Register all Ajax functions here
    initAjax (GlobalAjaxInvoker.getInstance ());

    // Register all API functions here
    initAPI (GlobalAPIInvoker.getInstance ());

    // Call final callback
    afterContextAndInitializersDone (aSC);
  }
}
