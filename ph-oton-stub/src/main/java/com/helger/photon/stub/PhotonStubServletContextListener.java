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
package com.helger.photon.stub;

import javax.annotation.Nonnull;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.helger.html.meta.MetaElement;
import com.helger.photon.core.app.html.HTMLConfigManager;
import com.helger.photon.core.mgr.PhotonCoreManager;

public final class PhotonStubServletContextListener implements ServletContextListener
{
  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    final HTMLConfigManager aConfigMgr = PhotonCoreManager.getHTMLConfigMgr ();
    aConfigMgr.addMetaElement (new MetaElement ("generator", "ph-oton stack - https://github.com/phax/ph-oton"));
    aConfigMgr.addMetaElement (new MetaElement ("X-UA-Compatible", true, "IE=Edge,chrome=1"));
  }

  public void contextDestroyed (final ServletContextEvent sce)
  {}
}
