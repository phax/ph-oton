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
import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.security.lock.ILockInfo;
import com.helger.photon.security.lock.ILockManager;
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
 * Show all locked objects.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringLockedObjects <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_DATE ("Datum", "Date"),
    MSG_USER ("Benutzer", "User"),
    MSG_OBJECTID ("ID", "ID");

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

  private final ILockManager <String> m_aLockMgr;

  public BasePageMonitoringLockedObjects (@Nonnull @Nonempty final String sID, @Nonnull final ILockManager <String> aLockMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_LOCKED_OBJECTS.getAsMLT ());
    m_aLockMgr = ValueEnforcer.notNull (aLockMgr, "LockManager");
  }

  public BasePageMonitoringLockedObjects (@Nonnull @Nonempty final String sID,
                                          @Nonnull final String sName,
                                          @Nonnull final ILockManager <String> aLockMgr)
  {
    super (sID, sName);
    m_aLockMgr = ValueEnforcer.notNull (aLockMgr, "LockManager");
  }

  public BasePageMonitoringLockedObjects (@Nonnull @Nonempty final String sID,
                                          @Nonnull final String sName,
                                          @Nullable final String sDescription,
                                          @Nonnull final ILockManager <String> aLockMgr)
  {
    super (sID, sName, sDescription);
    m_aLockMgr = ValueEnforcer.notNull (aLockMgr, "LockManager");
  }

  public BasePageMonitoringLockedObjects (@Nonnull @Nonempty final String sID,
                                          @Nonnull final IMultilingualText aName,
                                          @Nullable final IMultilingualText aDescription,
                                          @Nonnull final ILockManager <String> aLockMgr)
  {
    super (sID, aName, aDescription);
    m_aLockMgr = ValueEnforcer.notNull (aLockMgr, "LockManager");
  }

  @Nonnull
  protected final ILockManager <String> getLockMgr ()
  {
    return m_aLockMgr;
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

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                   aDisplayLocale)
                                                                                                  .setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_USER.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_OBJECTID.getDisplayText (aDisplayLocale))).setID (getID ());

    for (final Map.Entry <String, ILockInfo> aEntry : m_aLockMgr.getAllLockInfos ().entrySet ())
    {
      final String sObjectID = aEntry.getKey ();
      final ILockInfo aInfo = aEntry.getValue ();

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (aInfo.getLockDateTime (), aDisplayLocale));
      aRow.addCell (SecurityHelper.getUserDisplayName (aInfo.getLockUserID (), aDisplayLocale));
      aRow.addCell (sObjectID);
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
