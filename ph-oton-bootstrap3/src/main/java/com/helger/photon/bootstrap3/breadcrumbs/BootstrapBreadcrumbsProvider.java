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
package com.helger.photon.bootstrap3.breadcrumbs;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.core.menu.IMenuItem;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.tree.withid.DefaultTreeItemWithID;

@Immutable
public final class BootstrapBreadcrumbsProvider
{
  private BootstrapBreadcrumbsProvider ()
  {}

  @Nonnull
  public static BootstrapBreadcrumbs createBreadcrumbs (@Nonnull final ILayoutExecutionContext aLEC)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    final IMenuTree aMenuTree = aLEC.getMenuTree ();
    final BootstrapBreadcrumbs aBreadcrumb = new BootstrapBreadcrumbs ();
    final ICommonsList <IMenuItem> aItems = new CommonsArrayList <> ();
    IMenuItem aCurrent = aLEC.getSelectedMenuItem ();
    while (aCurrent != null)
    {
      aItems.add (0, aCurrent);
      final DefaultTreeItemWithID <String, IMenuObject> aTreeItem = aMenuTree.getItemWithID (aCurrent.getID ());
      aCurrent = aTreeItem.isRootItem () ? null : (IMenuItem) aTreeItem.getParent ().getData ();
    }

    final int nItems = aItems.size ();
    if (nItems > 0)
    {
      for (int i = 0; i < nItems; ++i)
      {
        final IMenuItem aItem = aItems.get (i);

        // Create link on all but the last item
        if (i < nItems - 1)
          aBreadcrumb.addLink (aLEC.getLinkToMenuItem (aItem.getID ()), aItem.getDisplayText (aDisplayLocale));
        else
          aBreadcrumb.addActive (aItem.getDisplayText (aDisplayLocale));
      }
    }
    return aBreadcrumb;
  }
}
