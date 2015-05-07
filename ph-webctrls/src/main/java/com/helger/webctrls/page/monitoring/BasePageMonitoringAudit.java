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
package com.helger.webctrls.page.monitoring;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.security.audit.IAuditItem;
import com.helger.appbasics.security.audit.IAuditManager;
import com.helger.appbasics.security.util.SecurityUtils;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
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
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.EDefaultIcon;
import com.helger.webctrls.custom.toolbar.IButtonToolbar;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.datatables.comparator.ComparatorDTDateTime;
import com.helger.webctrls.page.AbstractWebPageExt;
import com.helger.webctrls.page.EWebPageText;

/**
 * Show audit items.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringAudit <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_DATE ("Datum", "Date"),
    MSG_USER ("Benutzer", "User"),
    MSG_TYPE ("Typ", "Type"),
    MSG_SUCCESS ("Erfolg?", "Success?"),
    MSG_ACTION ("Aktion", "Action");

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

  private final IAuditManager m_aAuditMgr;

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID, @Nonnull final IAuditManager aAuditMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_AUDIT.getAsMLT ());
    m_aAuditMgr = ValueEnforcer.notNull (aAuditMgr, "AuditManager");
  }

  public BasePageMonitoringAudit (@Nonnull @Nonempty final String sID,
                                  @Nonnull final String sName,
                                  @Nonnull final IAuditManager aAuditMgr)
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
                                  @Nonnull final IReadonlyMultiLingualText aName,
                                  @Nullable final IReadonlyMultiLingualText aDescription,
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
    final IButtonToolbar <?> aToolbar = getStyler ().createToolbar (aWPEC);
    aToolbar.addButton (EWebBasicsText.MSG_BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final IHCTable <?> aTable = getStyler ().createTable (new HCCol (COLUMN_WIDTH_DATETIME),
                                                          new HCCol (120),
                                                          new HCCol (60),
                                                          new HCCol (60),
                                                          HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_DATE.getDisplayText (aDisplayLocale),
                                     EText.MSG_USER.getDisplayText (aDisplayLocale),
                                     EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                     EText.MSG_SUCCESS.getDisplayText (aDisplayLocale),
                                     EText.MSG_ACTION.getDisplayText (aDisplayLocale));

    for (final IAuditItem aItem : m_aAuditMgr.getLastAuditItems (250))
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (aItem.getDateTime (), aDisplayLocale));
      aRow.addCell (SecurityUtils.getUserDisplayName (aItem.getUserID (), aDisplayLocale));
      aRow.addCell (aItem.getType ().getID ());
      aRow.addCell (EWebBasicsText.getYesOrNo (aItem.getSuccess ().isSuccess (), aDisplayLocale));
      aRow.addCell (getActionString (aItem));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (0)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (4).addClass (CSS_CLASS_ACTION_COL).setSortable (false);
    aDataTables.setInitialSorting (0, ESortOrder.DESCENDING);
    aNodeList.addChild (aDataTables);
  }
}
