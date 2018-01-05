/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.pub;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.bootstrap.demo.pub.page.PagePublicLogin;
import com.helger.photon.bootstrap.demo.pub.page.icon.PagePublicIconFontAwesome;
import com.helger.photon.bootstrap.demo.pub.page.icon.PagePublicIconMaterialDesign;
import com.helger.photon.security.menu.MenuObjectFilterNoUserLoggedIn;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uicore.page.external.BasePageViewExternal;
import com.helger.photon.uicore.page.system.BasePageShowChildren;

@Immutable
public final class MenuPublic
{
  private MenuPublic ()
  {}

  public static void init (@Nonnull final IMenuTree aMenuTree)
  {
    // Not logged in
    aMenuTree.createRootItem (new PagePublicLogin (CMenuPublic.MENU_LOGIN))
             .setDisplayFilter (new MenuObjectFilterNoUserLoggedIn ());
    aMenuTree.createRootSeparator ().setDisplayFilter (new MenuObjectFilterNoUserLoggedIn ());

    // Icons stuff
    {
      final IMenuItemPage aIcons = aMenuTree.createRootItem (new BasePageShowChildren <> ("icon",
                                                                                          "Icon sets",
                                                                                          aMenuTree));
      aMenuTree.createItem (aIcons, new PagePublicIconFontAwesome ("icon-fa"));
      aMenuTree.createItem (aIcons, new PagePublicIconMaterialDesign ("icon-md"));
    }

    // Common stuff
    aMenuTree.createRootItem (new BasePageViewExternal <WebPageExecutionContext> (CMenuPublic.MENU_SITENOTICE,
                                                                                  "Site notice",
                                                                                  new ClassPathResource ("viewpages/en/site-notice.xml"),
                                                                                  null));

    aMenuTree.createRootItem (new BasePageViewExternal <WebPageExecutionContext> (CMenuPublic.MENU_GTC,
                                                                                  "GTC",
                                                                                  new ClassPathResource ("viewpages/en/gtc.xml"),
                                                                                  null))
             .attrs ()
             .putIn (CMenuPublic.FLAG_FOOTER, Boolean.TRUE);

    // Set default
    aMenuTree.setDefaultMenuItemID (CMenuPublic.MENU_SITENOTICE);
  }
}
