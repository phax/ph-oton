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
package com.helger.photon.bootstrap4.pages.monitoring;

import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.audit.IAuditItem;
import com.helger.photon.audit.IAuditManager;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Show audit items.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringAudit <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_EARLIEST_DATA ("Älteste verfügbare Daten: ", "Earliest available data: "),
    MSG_DATE ("Datum", "Date"),
    MSG_USER ("Benutzer", "User"),
    MSG_TYPE ("Typ", "Type"),
    MSG_SUCCESS ("Erfolg?", "Success?"),
    MSG_ACTION ("Aktion", "Action");

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

  public static final int DEFAULT_MAX_ITEMS = 250;
  public static final String PARAM_MAX_ITEMS = "maxitems";

  private final IAuditManager m_aAuditMgr;

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID, @Nonnull final IAuditManager aAuditMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_AUDIT.getAsMLT ());
    m_aAuditMgr = ValueEnforcer.notNull (aAuditMgr, "AuditManager");
  }

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nonnull final IAuditManager aAuditMgr)
  {
    super (sID, sName);
    m_aAuditMgr = ValueEnforcer.notNull (aAuditMgr, "AuditManager");
  }

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID,
                                  @Nonnull final String sName,
                                  @Nullable final String sDescription,
                                  @Nonnull final IAuditManager aAuditMgr)
  {
    super (sID, sName, sDescription);
    m_aAuditMgr = ValueEnforcer.notNull (aAuditMgr, "AuditManager");
  }

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID,
                                  @Nonnull final IMultilingualText aName,
                                  @Nullable final IMultilingualText aDescription,
                                  @Nonnull final IAuditManager aAuditMgr)
  {
    super (sID, aName, aDescription);
    m_aAuditMgr = ValueEnforcer.notNull (aAuditMgr, "AuditManager");
  }

  @Nonnull
  protected final IAuditManager getAuditMgr ()
  {
    return m_aAuditMgr;
  }

  @Nonnull
  @OverrideOnDemand
  protected String getActionString (@Nonnull final IAuditItem aItem)
  {
    return aItem.getAction ();
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

    // Info
    aNodeList.addChild (div (EText.MSG_EARLIEST_DATA.getDisplayText (aDisplayLocale)).addChild (PDTToString.getAsString (m_aAuditMgr.getEarliestAuditDate (),
                                                                                                                         aDisplayLocale)));

    // Check max items parameter
    int nMaxItems = aWPEC.params ().getAsInt (PARAM_MAX_ITEMS);
    if (nMaxItems <= 0)
      nMaxItems = DEFAULT_MAX_ITEMS;

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                   aDisplayLocale)
                                                                                                  .setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_USER.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_SUCCESS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_ACTION.getDisplayText (aDisplayLocale)).setDataSort (4, 0)).setID (getID ());
    for (final IAuditItem aItem : m_aAuditMgr.getLastAuditItems (nMaxItems))
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (aItem.getDateTime (), aDisplayLocale));
      aRow.addCell (SecurityHelper.getUserDisplayName (aItem.getUserID (), aDisplayLocale));
      aRow.addCell (aItem.getType ().getID ());
      aRow.addCell (EPhotonCoreText.getYesOrNo (aItem.getSuccess ().isSuccess (), aDisplayLocale));
      aRow.addCell (getActionString (aItem));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
