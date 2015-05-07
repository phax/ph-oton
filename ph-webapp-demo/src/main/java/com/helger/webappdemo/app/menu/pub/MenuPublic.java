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
package com.helger.webappdemo.app.menu.pub;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.app.menu.IMenuTree;
import com.helger.appbasics.app.menu.filter.MenuItemFilterNotLoggedIn;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.webappdemo.page.pub.PagePublicLogin;
import com.helger.webbasics.app.page.WebPageExecutionContext;
import com.helger.webctrls.page.PageViewExternal;

@Immutable
public final class MenuPublic
{
  private MenuPublic ()
  {}

  public static void init (@Nonnull final IMenuTree aMenuTree)
  {
    // Not logged in
    aMenuTree.createRootItem (new PagePublicLogin (CMenuPublic.MENU_LOGIN))
             .setDisplayFilter (MenuItemFilterNotLoggedIn.getInstance ());
    aMenuTree.createRootSeparator ().setDisplayFilter (MenuItemFilterNotLoggedIn.getInstance ());

    // Common stuff
    aMenuTree.createRootItem (new PageViewExternal <WebPageExecutionContext> (CMenuPublic.MENU_SITENOTICE,
                                                                              "Site notice",
                                                                              new ClassPathResource ("viewpages/en/site-notice.xml")));

    aMenuTree.createRootItem (new PageViewExternal <WebPageExecutionContext> (CMenuPublic.MENU_GTC,
                                                                              "GTC",
                                                                              new ClassPathResource ("viewpages/en/gtc.xml")))
             .setAttribute (CMenuPublic.FLAG_FOOTER, Boolean.TRUE);

    // Set default
    aMenuTree.setDefaultMenuItemID (CMenuPublic.MENU_SITENOTICE);
  }
}
