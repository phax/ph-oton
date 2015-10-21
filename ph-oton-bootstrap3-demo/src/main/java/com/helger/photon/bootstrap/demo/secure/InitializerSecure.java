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
package com.helger.photon.bootstrap.demo.secure;

import javax.annotation.Nonnull;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.photon.basic.app.locale.ILocaleManager;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.bootstrap.demo.app.CApp;
import com.helger.photon.bootstrap.demo.secure.ajax.CAjaxSecure;
import com.helger.photon.bootstrap.demo.secure.menu.MenuSecure;
import com.helger.photon.bootstrap3.pages.sysinfo.ConfigurationFile;
import com.helger.photon.bootstrap3.pages.sysinfo.ConfigurationFileManager;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.core.app.init.DefaultApplicationInitializer;
import com.helger.photon.core.app.layout.CLayout;
import com.helger.photon.core.app.layout.ILayoutManager;
import com.helger.photon.uictrls.prism.EPrismLanguage;

/**
 * Initialize the config application stuff
 *
 * @author Philip Helger
 */
public final class InitializerSecure extends DefaultApplicationInitializer <LayoutExecutionContext>
{
  @Override
  public void initLocales (@Nonnull final ILocaleManager aLocaleMgr)
  {
    aLocaleMgr.registerLocale (CApp.DEFAULT_LOCALE);
    aLocaleMgr.setDefaultLocale (CApp.DEFAULT_LOCALE);
  }

  @Override
  public void initLayout (@Nonnull final ILayoutManager <LayoutExecutionContext> aLayoutMgr)
  {
    aLayoutMgr.registerAreaContentProvider (CLayout.LAYOUT_AREAID_VIEWPORT, new AppRendererSecure ());
  }

  @Override
  public void initMenu (@Nonnull final IMenuTree aMenuTree)
  {
    MenuSecure.init (aMenuTree);
  }

  @Override
  public void initAjax (@Nonnull final IAjaxInvoker aAjaxInvoker)
  {
    aAjaxInvoker.registerFunction (CAjaxSecure.SAVE_FORM_STATE);
    aAjaxInvoker.registerFunction (CAjaxSecure.UPDATE_MENU_VIEW);
  }

  @Override
  public void initRest ()
  {
    final ConfigurationFileManager aCfgMgr = ConfigurationFileManager.getInstance ();
    aCfgMgr.registerConfigurationFile (new ConfigurationFile (new ClassPathResource ("log4j2.xml")).setDescription ("log4j configuration file")
                                                                                                   .setSyntaxHighlightLanguage (EPrismLanguage.MARKUP));
    aCfgMgr.registerConfigurationFile (new ConfigurationFile (new ClassPathResource ("webapp.properties")).setDescription ("Web application properties")
                                                                                                          .setSyntaxHighlightLanguage (EPrismLanguage.APACHECONF));
  }
}
