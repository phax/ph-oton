/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.context;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.ApplicationRequestManager;
import com.helger.photon.basic.app.request.IRequestParameterManager;
import com.helger.photon.core.app.redirect.ForcedRedirectException;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This object is instantiated per page view and contains the current request
 * scope, the display locale, the selected menu item and a set of custom
 * attributes. In addition to the base class {@link SimpleWebExecutionContext}
 * the selected menu item is added.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class LayoutExecutionContext extends SimpleWebExecutionContext implements ILayoutExecutionContext
{
  private final IMenuItemPage m_aSelectedMenuItem;

  public LayoutExecutionContext (@Nonnull final ILayoutExecutionContext aLEC)
  {
    this (aLEC, aLEC.getSelectedMenuItem ());
  }

  public LayoutExecutionContext (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                 @Nonnull final IMenuItemPage aSelectedMenuItem)
  {
    super (aSWEC);
    m_aSelectedMenuItem = ValueEnforcer.notNull (aSelectedMenuItem, "SelectedMenuItem");
  }

  @Nonnull
  public IMenuItemPage getSelectedMenuItem ()
  {
    return m_aSelectedMenuItem;
  }

  public void postRedirectGet (@Nonnull final ISimpleURL aTargetURL,
                               @Nullable final IHCNode aContent) throws ForcedRedirectException
  {
    // Add the "PRG active" parameter
    throw new ForcedRedirectException (m_aSelectedMenuItem.getID (), aTargetURL, aContent);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("selectedMenuItem", m_aSelectedMenuItem)
                            .toString ();
  }

  @Nonnull
  public static LayoutExecutionContext createForAjaxOrAction (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IRequestParameterManager aRequestMgr = ApplicationRequestManager.getRequestMgr ();
    // Get the locale from the session
    final Locale aDisplayLocale = aRequestMgr.getRequestDisplayLocale ();
    final IMenuTree aMenuTree = ApplicationMenuTree.getTree ();
    // Since no menu item is selected, use the default menu item
    return new LayoutExecutionContext (new SimpleWebExecutionContext (aRequestScope, aDisplayLocale, aMenuTree),
                                       aMenuTree.getDefaultMenuItem ());
  }
}
