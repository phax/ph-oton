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
package com.helger.photon.bootstrap3.page.monitoring;

import java.util.Locale;

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
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.go.GoMappingItem;
import com.helger.webbasics.go.GoMappingManager;
import com.helger.webbasics.go.GoServlet;

/**
 * Show web site resource bundles.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringGo <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_GO_SERVLET ("GoServlet registriert: ", "GoServlet registered: "),
    MSG_KEY ("ID", "ID"),
    MSG_INTERNAL ("Intern?", "Internal?"),
    MSG_URL ("Ziel-URL", "Target URL"),
    MSG_EDITABLE ("Editierbar?", "Editable?");

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

  private final GoMappingManager m_aGoMappingMgr;

  public BasePageMonitoringGo (@Nonnull @Nonempty final String sID, @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_GO.getAsMLT ());
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageMonitoringGo (@Nonnull @Nonempty final String sID,
                            @Nonnull final String sName,
                            @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, sName);
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageMonitoringGo (@Nonnull @Nonempty final String sID,
                            @Nonnull final String sName,
                            @Nullable final String sDescription,
                            @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, sName, sDescription);
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageMonitoringGo (@Nonnull @Nonempty final String sID,
                            @Nonnull final IReadonlyMultiLingualText aName,
                            @Nullable final IReadonlyMultiLingualText aDescription,
                            @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, aName, aDescription);
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  @Nonnull
  protected final GoMappingManager getGoMappingMgr ()
  {
    return m_aGoMappingMgr;
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

    aNodeList.addChild (new HCDiv ().addChild (EText.MSG_GO_SERVLET.getDisplayText (aDisplayLocale) +
                                               EWebBasicsText.getYesOrNo (GoServlet.isServletRegisteredInServletContext (),
                                                                          aDisplayLocale)));

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ())
                                            .setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                     EText.MSG_INTERNAL.getDisplayText (aDisplayLocale),
                                     EText.MSG_URL.getDisplayText (aDisplayLocale),
                                     EText.MSG_EDITABLE.getDisplayText (aDisplayLocale));

    for (final GoMappingItem aItem : m_aGoMappingMgr.getAllItems ().values ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aItem.getKey ());
      aRow.addCell (EWebBasicsText.getYesOrNo (aItem.isInternal (), aDisplayLocale));
      aRow.addCell (HCA.createLinkedWebsite (aItem.getTargetURLAsString ()));
      aRow.addCell (EWebBasicsText.getYesOrNo (aItem.isEditable (), aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (1).setDataSort (1, 0);
    aDataTables.getOrCreateColumnOfTarget (3).setDataSort (3, 0);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
