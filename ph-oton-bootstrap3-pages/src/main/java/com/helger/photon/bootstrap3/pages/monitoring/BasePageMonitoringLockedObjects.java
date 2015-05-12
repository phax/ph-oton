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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
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
import com.helger.photon.basic.security.lock.ILockInfo;
import com.helger.photon.basic.security.lock.ILockManager;
import com.helger.photon.basic.security.util.SecurityUtils;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTDateTime;

/**
 * Show all locked objects.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringLockedObjects <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_DATE ("Datum", "Date"),
    MSG_USER ("Benutzer", "User"),
    MSG_OBJECTID ("ID", "ID");

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

  private final ILockManager <String> m_aLockMgr;

  public BasePageMonitoringLockedObjects (@Nonnull @Nonempty final String sID,
                                          @Nonnull final ILockManager <String> aLockMgr)
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
                                          @Nonnull final IReadonlyMultiLingualText aName,
                                          @Nullable final IReadonlyMultiLingualText aDescription,
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
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final IHCTable <?> aTable = new BootstrapTable (new HCCol (COLUMN_WIDTH_DATETIME), new HCCol (120), HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_DATE.getDisplayText (aDisplayLocale),
                                     EText.MSG_USER.getDisplayText (aDisplayLocale),
                                     EText.MSG_OBJECTID.getDisplayText (aDisplayLocale));

    for (final Map.Entry <String, ILockInfo> aEntry : m_aLockMgr.getAllLockInfos ().entrySet ())
    {
      final String sObjectID = aEntry.getKey ();
      final ILockInfo aInfo = aEntry.getValue ();

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (aInfo.getLockDateTime (), aDisplayLocale));
      aRow.addCell (SecurityUtils.getUserDisplayName (aInfo.getLockUserID (), aDisplayLocale));
      aRow.addCell (sObjectID);
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (0)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.setInitialSorting (0, ESortOrder.DESCENDING);
    aNodeList.addChild (aDataTables);
  }
}
