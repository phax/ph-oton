/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.sysinfo;

import java.net.NetworkInterface;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.hierarchy.IParentProvider;
import com.helger.collection.iterator.IteratorHelper;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.tree.withid.unique.DefaultTreeWithGlobalUniqueID;

import jakarta.annotation.Nonnull;

/**
 * Some utility methods for {@link NetworkInterface}
 *
 * @author Philip Helger
 */
@Immutable
public final class NetworkInterfaceHelper
{
  @PresentForCodeCoverage
  private static final NetworkInterfaceHelper INSTANCE = new NetworkInterfaceHelper ();

  private NetworkInterfaceHelper ()
  {}

  /**
   * Create a hierarchical tree of the network interfaces.
   *
   * @return The created tree and never <code>null</code>.
   * @throws IllegalStateException
   *         In case an internal error occurred.
   */
  @Nonnull
  public static DefaultTreeWithGlobalUniqueID <String, NetworkInterface> createNetworkInterfaceTree ()
  {
    final DefaultTreeWithGlobalUniqueID <String, NetworkInterface> ret = new DefaultTreeWithGlobalUniqueID <> ();

    // Build basic level - all IFs without a parent
    final ICommonsList <NetworkInterface> aNonRootNIs = new CommonsArrayList <> ();
    try
    {
      for (final NetworkInterface aNI : IteratorHelper.getIterator (NetworkInterface.getNetworkInterfaces ()))
        if (aNI.getParent () == null)
          ret.getRootItem ().createChildItem (aNI.getName (), aNI);
        else
          aNonRootNIs.add (aNI);
    }
    catch (final Exception ex)
    {
      throw new IllegalStateException ("Failed to get all network interfaces", ex);
    }

    int nNotFound = 0;
    while (aNonRootNIs.isNotEmpty ())
    {
      final NetworkInterface aNI = aNonRootNIs.removeFirstOrNull ();
      final DefaultTreeItemWithID <String, NetworkInterface> aParentItem = ret.getItemWithID (aNI.getParent ()
                                                                                                 .getName ());
      if (aParentItem != null)
      {
        // We found the parent
        aParentItem.createChildItem (aNI.getName (), aNI);

        // Reset counter
        nNotFound = 0;
      }
      else
      {
        // Add again at the end
        aNonRootNIs.add (aNI);

        // Parent not found
        nNotFound++;

        // We tried too many times without success - we iterated the whole
        // remaining list and found no parent tree item
        if (nNotFound > aNonRootNIs.size ())
          throw new IllegalStateException ("Seems like we have a data structure inconsistency! Remaining are: " +
                                           aNonRootNIs);
      }
    }
    return ret;
  }

  @Nonnull
  public static IParentProvider <NetworkInterface> parentProvider ()
  {
    return NetworkInterface::getParent;
  }
}
