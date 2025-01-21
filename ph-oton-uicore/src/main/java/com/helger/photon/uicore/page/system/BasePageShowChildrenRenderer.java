/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.system;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.photon.core.menu.IMenuItemExternal;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuSeparator;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * The default renderer used for {@link BasePageShowChildren}.
 *
 * @author Philip Helger
 */
public class BasePageShowChildrenRenderer implements Serializable
{
  public BasePageShowChildrenRenderer ()
  {}

  /**
   * Called before a menu item is rendered.
   *
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @param aMenuObj
   *        The menu object about to be rendered. Never <code>null</code>.
   * @param aPreviousLI
   *        The previous element from the last call. May be <code>null</code>.
   */
  @OverrideOnDemand
  public void beforeAddRenderedMenuItem (@Nonnull final IWebPageExecutionContext aWPEC,
                                         @Nonnull final IMenuObject aMenuObj,
                                         @Nullable final IHCElement <?> aPreviousLI)
  {
    if (aMenuObj.getMenuObjectType ().isSeparator () && aPreviousLI != null)
      aPreviousLI.addStyle (CCSSProperties.MARGIN_BOTTOM.newValue ("1em"));
  }

  /**
   * Render a menu separator
   *
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @param aMenuSeparator
   *        The menu separator. Never <code>null</code>.
   * @return The rendered representation or <code>null</code>
   */
  @Nullable
  @OverrideOnDemand
  public IHCNode renderMenuSeparator (@Nonnull final IWebPageExecutionContext aWPEC, @Nonnull final IMenuSeparator aMenuSeparator)
  {
    return null;
  }

  /**
   * Render a menu item to an internal page
   *
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @param aMenuItemPage
   *        The menu item. Never <code>null</code>.
   * @return The rendered representation or <code>null</code>
   */
  @Nullable
  @OverrideOnDemand
  public IHCNode renderMenuItemPage (@Nonnull final IWebPageExecutionContext aWPEC, @Nonnull final IMenuItemPage aMenuItemPage)
  {
    if (!aMenuItemPage.matchesDisplayFilter ())
      return null;

    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCA ret = new HCA (aWPEC.getLinkToMenuItem (aMenuItemPage.getID ()));
    // Set window target (if defined)
    if (aMenuItemPage.hasTarget ())
      ret.setTarget (new HC_Target (aMenuItemPage.getTarget ()));
    ret.addChild (aMenuItemPage.getDisplayText (aDisplayLocale));
    return ret;
  }

  /**
   * Render a menu item to an external page
   *
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @param aMenuItemExternal
   *        The menu item. Never <code>null</code>.
   * @return The rendered representation or <code>null</code>
   */
  @Nullable
  @OverrideOnDemand
  public IHCNode renderMenuItemExternal (@Nonnull final IWebPageExecutionContext aWPEC, @Nonnull final IMenuItemExternal aMenuItemExternal)
  {
    if (!aMenuItemExternal.matchesDisplayFilter ())
      return null;

    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCA ret = new HCA (aMenuItemExternal.getURL ());
    // Set window target (if defined)
    if (aMenuItemExternal.hasTarget ())
      ret.setTarget (new HC_Target (aMenuItemExternal.getTarget ()));
    ret.addChild (aMenuItemExternal.getDisplayText (aDisplayLocale));
    return ret;
  }

  /**
   * @param aWPEC
   *        Web page execution context. May not be <code>null</code>.
   * @param aMenuObj
   *        The menu object just rendered before. Never <code>null</code>.
   * @param aLI
   *        The created element for the passed object. May be <code>null</code>.
   */
  @OverrideOnDemand
  public void afterAddRenderedMenuItem (@Nonnull final IWebPageExecutionContext aWPEC,
                                        @Nonnull final IMenuObject aMenuObj,
                                        @Nullable final IHCElement <?> aLI)
  {}
}
