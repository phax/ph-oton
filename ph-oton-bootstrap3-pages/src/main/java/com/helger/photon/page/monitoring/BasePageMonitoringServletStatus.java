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
package com.helger.photon.page.monitoring;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.datatables.BootstrapDataTables;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.servletstatus.EServletStatus;
import com.helger.webbasics.servletstatus.ServletStatus;
import com.helger.webbasics.servletstatus.ServletStatusManager;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.datatables.comparator.ComparatorDTDateTime;
import com.helger.webctrls.datatables.comparator.ComparatorDTInteger;

/**
 * Show servlet status.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringServletStatus <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_SERVLET ("Servlet Klasse", "Servlet class"),
    MSG_STATUS ("Status", "Status"),
    MSG_INVOCATION_COUNT ("Aufrufe", "Inocations"),
    MSG_INIT_DT ("Initialisiert", "Initialzed");

    @Nonnull
    private final TextProvider m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }
  }

  public BasePageMonitoringServletStatus (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SERVLETSTATUS.getAsMLT ());
  }

  public BasePageMonitoringServletStatus (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringServletStatus (@Nonnull @Nonempty final String sID,
                                          @Nonnull final String sName,
                                          @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringServletStatus (@Nonnull @Nonempty final String sID,
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

    // Refresh button
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EWebBasicsText.MSG_BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ())
                                            .setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_SERVLET.getDisplayText (aDisplayLocale),
                                     EText.MSG_STATUS.getDisplayText (aDisplayLocale),
                                     EText.MSG_INVOCATION_COUNT.getDisplayText (aDisplayLocale),
                                     EText.MSG_INIT_DT.getDisplayText (aDisplayLocale));

    for (final Map.Entry <String, ServletStatus> aItem : ServletStatusManager.getAllStatus ().entrySet ())
    {
      final ServletStatus aServletStatus = aItem.getValue ();

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aItem.getKey ());
      aRow.addCell (aServletStatus.getCurrentStatus ().getDisplayText (aDisplayLocale));
      aRow.addCell (Integer.toString (aServletStatus.getInvocationCount ()));
      aRow.addCell (PDTToString.getAsString (aServletStatus.getDateTimeOfStatus (EServletStatus.INITED), aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (2)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTInteger (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (3)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
