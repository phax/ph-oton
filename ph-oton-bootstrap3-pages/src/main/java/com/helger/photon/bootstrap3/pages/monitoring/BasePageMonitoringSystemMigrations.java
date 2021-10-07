/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.sysmigration.SystemMigrationManager;
import com.helger.photon.core.sysmigration.SystemMigrationResult;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Show system migration status.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringSystemMigrations <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_DATE ("Datum", "Date"),
    MSG_SUCCESS ("Erfolg?", "Success?"),
    MSG_ERRORMESSAGE ("Fehlermeldung", "Error message");

    @Nonnull
    private final IMultilingualText m_aTP;

    EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private final SystemMigrationManager m_aSystemMigrationMgr;

  public BasePageMonitoringSystemMigrations (@Nonnull @Nonempty final String sID, @Nonnull final SystemMigrationManager aSystemMigrationMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SYSTEMMIGRATIONS.getAsMLT ());
    m_aSystemMigrationMgr = ValueEnforcer.notNull (aSystemMigrationMgr, "SystemMigrationMgr");
  }

  public BasePageMonitoringSystemMigrations (@Nonnull @Nonempty final String sID,
                                             @Nonnull final String sName,
                                             @Nonnull final SystemMigrationManager aSystemMigrationMgr)
  {
    super (sID, sName);
    m_aSystemMigrationMgr = ValueEnforcer.notNull (aSystemMigrationMgr, "SystemMigrationMgr");
  }

  public BasePageMonitoringSystemMigrations (@Nonnull @Nonempty final String sID,
                                             @Nonnull final String sName,
                                             @Nullable final String sDescription,
                                             @Nonnull final SystemMigrationManager aSystemMigrationMgr)
  {
    super (sID, sName, sDescription);
    m_aSystemMigrationMgr = ValueEnforcer.notNull (aSystemMigrationMgr, "SystemMigrationMgr");
  }

  public BasePageMonitoringSystemMigrations (@Nonnull @Nonempty final String sID,
                                             @Nonnull final IMultilingualText aName,
                                             @Nullable final IMultilingualText aDescription,
                                             @Nonnull final SystemMigrationManager aSystemMigrationMgr)
  {
    super (sID, aName, aDescription);
    m_aSystemMigrationMgr = ValueEnforcer.notNull (aSystemMigrationMgr, "SystemMigrationMgr");
  }

  @Nonnull
  protected final SystemMigrationManager getSystemMigrationMgr ()
  {
    return m_aSystemMigrationMgr;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale), aWPEC.getSelfHref (), EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                   aDisplayLocale)
                                                                                                  .setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_SUCCESS.getDisplayText (aDisplayLocale)).setDataSort (2, 1),
                                        new DTCol (EText.MSG_ERRORMESSAGE.getDisplayText (aDisplayLocale)).setDataSort (3, 2, 1))
                                                                                                                                 .setID (getID ());

    for (final SystemMigrationResult aItem : m_aSystemMigrationMgr.getAllMigrationResultsFlattened ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aItem.getID ());
      aRow.addCell (PDTToString.getAsString (aItem.getExecutionDateTime (), aDisplayLocale));
      aRow.addCell (EPhotonCoreText.getYesOrNo (aItem.isSuccess (), aDisplayLocale));
      aRow.addCell (aItem.getErrorMessage ());
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
