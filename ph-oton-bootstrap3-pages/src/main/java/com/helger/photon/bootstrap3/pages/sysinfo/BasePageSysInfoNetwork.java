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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.GlobalDebug;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.hierarchy.DefaultHierarchyWalkerCallback;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.tree.utils.sort.TreeWithIDSorter;
import com.helger.commons.tree.utils.walk.TreeWalker;
import com.helger.commons.tree.withid.DefaultTreeItemWithID;
import com.helger.commons.tree.withid.unique.DefaultTreeWithGlobalUniqueID;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.table.BootstrapTableFormView;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.html.table.IHCTableFormView;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenuList;
import com.helger.web.networkinterface.ComparatorNetworkInterfaceName;
import com.helger.web.networkinterface.NetworkInterfaceUtils;
import com.helger.webbasics.EWebBasicsText;

/**
 * Page with information on the current network settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoNetwork <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
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

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
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
                                 @Nonnull final IReadonlyMultiLingualText aName,
                                 @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final ITabBox <?> aTabBox = new BootstrapTabBox();

    // ntwork interfaces
    {
      final IHCTableFormView <?> aTable = new BootstrapTableFormView (new HCCol (80),
                                                                            HCCol.star (),
                                                                            HCCol.star (),
                                                                            new HCCol (40),
                                                                            new HCCol (40),
                                                                            new HCCol (40),
                                                                            new HCCol (40),
                                                                            new HCCol (40),
                                                                            new HCCol (40));
      aTable.setID (getID () + "-ni");
      aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                       EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_MAC.getDisplayText (aDisplayLocale),
                                       EText.MSG_IS_UP.getDisplayText (aDisplayLocale),
                                       EText.MSG_IS_LOOPBACK.getDisplayText (aDisplayLocale),
                                       EText.MSG_IS_POINT_TO_POINT.getDisplayText (aDisplayLocale),
                                       EText.MSG_IS_MULTICAST.getDisplayText (aDisplayLocale),
                                       EText.MSG_MTU.getDisplayText (aDisplayLocale),
                                       EText.MSG_IS_VIRTUAL.getDisplayText (aDisplayLocale));
      try
      {
        final DefaultTreeWithGlobalUniqueID <String, NetworkInterface> aNITree = NetworkInterfaceUtils.createNetworkInterfaceTree ();
        // Sort on each level
        TreeWithIDSorter.sortByValue (aNITree, new ComparatorNetworkInterfaceName ());
        TreeWalker.walkTree (aNITree,
                             new DefaultHierarchyWalkerCallback <DefaultTreeItemWithID <String, NetworkInterface>> ()
                             {
                               @Override
                               public void onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, NetworkInterface> aItem)
                               {
                                 final NetworkInterface aNI = aItem.getData ();
                                 final int nDepth = getLevel ();
                                 final HCRow aRow = aTable.addBodyRow ();
                                 aRow.addCell (aNI.getName ());
                                 final IHCCell <?> aCell = aRow.addAndReturnCell (aNI.getDisplayName ());
                                 if (nDepth > 0)
                                   aCell.addStyle (CCSSProperties.PADDING_LEFT.newValue (ECSSUnit.em (nDepth)));

                                 // hardware address (usually MAC) of the
                                 // interface if it has one and if it can be
                                 // accessed given the current privileges.
                                 try
                                 {
                                   final byte [] aMAC = aNI.getHardwareAddress ();
                                   aRow.addCell (aMAC == null ? "" : StringHelper.getHexEncoded (aMAC));
                                 }
                                 catch (final SocketException ex)
                                 {
                                   aRow.addCell (HCEM.create (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                 }

                                 // network interface is up and running.
                                 try
                                 {
                                   aRow.addCell (EWebBasicsText.getYesOrNo (aNI.isUp (), aDisplayLocale));
                                 }
                                 catch (final SocketException ex)
                                 {
                                   aRow.addCell (HCEM.create (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                 }

                                 // network interface is a loopback interface.
                                 try
                                 {
                                   aRow.addCell (EWebBasicsText.getYesOrNo (aNI.isLoopback (), aDisplayLocale));
                                 }
                                 catch (final SocketException ex)
                                 {
                                   aRow.addCell (HCEM.create (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                 }

                                 // network interface is a point to point
                                 // interface. A typical point to point
                                 // interface would be a PPP connection through
                                 // a modem.
                                 try
                                 {
                                   aRow.addCell (EWebBasicsText.getYesOrNo (aNI.isPointToPoint (), aDisplayLocale));
                                 }
                                 catch (final SocketException ex)
                                 {
                                   aRow.addCell (HCEM.create (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                 }

                                 // network interface supports multicasting or
                                 // not.
                                 try
                                 {
                                   aRow.addCell (EWebBasicsText.getYesOrNo (aNI.supportsMulticast (), aDisplayLocale));
                                 }
                                 catch (final SocketException ex)
                                 {
                                   aRow.addCell (HCEM.create (EText.MSG_ERROR.getDisplayText (aDisplayLocale)));
                                 }

                                 // Maximum Transmission Unit (MTU) of this
                                 // interface.
                                 int nMTU = -1;
                                 try
                                 {
                                   nMTU = aNI.getMTU ();
                                 }
                                 catch (final SocketException ex)
                                 {}
                                 if (nMTU > 0)
                                   aRow.addCell (Integer.toString (nMTU));
                                 else
                                   aRow.addCell ();

                                 // this interface is a virtual interface (also
                                 // called subinterface). Virtual interfaces
                                 // are, on some systems, interfaces created as
                                 // a child of a physical interface and given
                                 // different settings (like address or MTU).
                                 // Usually the name of the interface will the
                                 // name of the parent followed by a colon (:)
                                 // and a number identifying the child since
                                 // there can be several virtual interfaces
                                 // attached to a single physical interface
                                 aRow.addCell (EWebBasicsText.getYesOrNo (aNI.isVirtual (), aDisplayLocale));
                               }
                             });
      }
      catch (final Throwable t)
      {
        aTable.addSpanningBodyContent (EText.MSG_ERROR_FINDING.getDisplayText (aDisplayLocale) +
                                       (GlobalDebug.isDebugMode () ? ": " + t.getMessage () : ""));
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.MSG_NETWORK_INTERFACES.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }
    aNodeList.addChild (aTabBox);
  }
}
