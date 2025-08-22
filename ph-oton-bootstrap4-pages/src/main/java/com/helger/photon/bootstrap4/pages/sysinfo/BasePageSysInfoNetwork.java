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
import java.net.SocketException;
import java.util.Comparator;
import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.compare.ESortOrder;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.string.StringHex;
import com.helger.collection.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.collection.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.nav.BootstrapTabBox;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;
import com.helger.tree.sort.TreeWithIDSorter;
import com.helger.tree.util.TreeVisitor;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.tree.withid.unique.DefaultTreeWithGlobalUniqueID;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with information on the current network settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoNetwork <WPECTYPE extends IWebPageExecutionContext> extends
                                    AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_NETWORK_INTERFACES ("Netzwerkkarten", "Network interfaces"),
    MSG_ERROR_FINDING ("Fehler beim Ermitteln der Netzwerkkarten", "Error determining network interfaces"),
    MSG_ID ("ID", "id"),
    MSG_NAME ("Name", "Name"),
    MSG_MAC ("MAC Adresse", "MAC address"),
    MSG_IS_UP ("Up?", "Up?"),
    MSG_IS_LOOPBACK ("Lb?", "Lb?"),
    MSG_IS_POINT_TO_POINT ("P2P?", "P2P?"),
    MSG_IS_MULTICAST ("MC?", "MC?"),
    MSG_MTU ("MTU", "MTU"),
    MSG_IS_VIRTUAL ("Virt?", "Virt?"),
    MSG_ERROR ("Fehler!", "Error!");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageSysInfoNetwork (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_NETWORK.getAsMLT ());
  }

  public BasePageSysInfoNetwork (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoNetwork (@Nonnull @Nonempty final String sID,
                                 @Nonnull final String sName,
                                 @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoNetwork (@Nonnull @Nonempty final String sID,
                                 @Nonnull final IMultilingualText aName,
                                 @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    // network interfaces
    {
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_MAC.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_IS_UP.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_IS_LOOPBACK.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_IS_POINT_TO_POINT.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_IS_MULTICAST.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_MTU.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                    aDisplayLocale),
                                          new DTCol (EText.MSG_IS_VIRTUAL.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                   "-ni");
      try
      {
        final DefaultTreeWithGlobalUniqueID <String, NetworkInterface> aNITree = NetworkInterfaceHelper.createNetworkInterfaceTree ();
        // Sort on each level
        TreeWithIDSorter.sortByValue (aNITree, Comparator.comparing (NetworkInterface::getDisplayName));
        TreeVisitor.visitTree (aNITree,
                               new DefaultHierarchyVisitorCallback <DefaultTreeItemWithID <String, NetworkInterface>> ()
                               {
                                 @Override
                                 public EHierarchyVisitorReturn onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, NetworkInterface> aItem)
                                 {
                                   final NetworkInterface aNI = aItem.getData ();
                                   final int nDepth = getLevel ();
                                   final HCRow aRow = aTable.addBodyRow ();
                                   aRow.addCell (aNI.getName ());
                                   final IHCCell <?> aCell = aRow.addAndReturnCell (aNI.getDisplayName ());
                                   if (nDepth > 0)
                                     aCell.addStyle (CCSSProperties.PADDING_LEFT.newValue (ECSSUnit.em (nDepth)));

                                   /*
                                    * hardware address (usually MAC) of the interface if it has one
                                    * and if it can be accessed given the current privileges.
                                    */
                                   try
                                   {
                                     final byte [] aMAC = aNI.getHardwareAddress ();
                                     aRow.addCell (aMAC == null ? "" : StringHex.getHexEncoded (aMAC));
                                   }
                                   catch (final SocketException ex)
                                   {
                                     aRow.addCell (em (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                   }

                                   /* network interface is up and running. */
                                   try
                                   {
                                     aRow.addCell (EPhotonCoreText.getYesOrNo (aNI.isUp (), aDisplayLocale));
                                   }
                                   catch (final SocketException ex)
                                   {
                                     aRow.addCell (em (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                   }

                                   /*
                                    * network interface is a loopback interface.
                                    */
                                   try
                                   {
                                     aRow.addCell (EPhotonCoreText.getYesOrNo (aNI.isLoopback (), aDisplayLocale));
                                   }
                                   catch (final SocketException ex)
                                   {
                                     aRow.addCell (em (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                   }

                                   /*
                                    * network interface is a point to point interface. A typical
                                    * point to point interface would be a PPP connection through a
                                    * modem.
                                    */
                                   try
                                   {
                                     aRow.addCell (EPhotonCoreText.getYesOrNo (aNI.isPointToPoint (), aDisplayLocale));
                                   }
                                   catch (final SocketException ex)
                                   {
                                     aRow.addCell (em (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                   }

                                   /*
                                    * network interface supports multicasting or not.
                                    */
                                   try
                                   {
                                     aRow.addCell (EPhotonCoreText.getYesOrNo (aNI.supportsMulticast (),
                                                                               aDisplayLocale));
                                   }
                                   catch (final SocketException ex)
                                   {
                                     aRow.addCell (em (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                   }

                                   /*
                                    * Maximum Transmission Unit (MTU) of this interface.
                                    */
                                   int nMTU = -1;
                                   try
                                   {
                                     nMTU = aNI.getMTU ();
                                   }
                                   catch (final SocketException ex)
                                   {
                                     // Ignore
                                   }
                                   if (nMTU > 0)
                                     aRow.addCell (Integer.toString (nMTU));
                                   else
                                     aRow.addCell ();

                                   /*
                                    * this interface is a virtual interface (also called
                                    * subinterface). Virtual interfaces are, on some systems,
                                    * interfaces created as a child of a physical interface and
                                    * given different settings (like address or MTU). Usually the
                                    * name of the interface will the name of the parent followed by
                                    * a colon (:) and a number identifying the child since there can
                                    * be several virtual interfaces attached to a single physical
                                    * interface
                                    */
                                   aRow.addCell (EPhotonCoreText.getYesOrNo (aNI.isVirtual (), aDisplayLocale));
                                   return EHierarchyVisitorReturn.CONTINUE;
                                 }
                               });
      }
      catch (final Exception ex)
      {
        aTable.addSpanningBodyContent (EText.MSG_ERROR_FINDING.getDisplayText (aDisplayLocale) +
                                       (GlobalDebug.isDebugMode () ? ": " + ex.getMessage () : ""));
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setPageLengthAll ();

      aTabBox.addTab ("network",
                      EText.MSG_NETWORK_INTERFACES.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }
    aNodeList.addChild (aTabBox);
  }
}
