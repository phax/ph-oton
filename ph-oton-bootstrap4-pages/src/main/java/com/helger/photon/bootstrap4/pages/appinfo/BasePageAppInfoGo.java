/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.appinfo;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.go.GoMappingItem;
import com.helger.photon.core.go.GoMappingManager;
import com.helger.photon.core.go.GoServlet;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.xservlet.servletstatus.ServletStatusManager;

/**
 * Show web site go-mappings.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoGo <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_GO_SERVLET ("GoServlet registriert: ", "GoServlet registered: "),
    MSG_KEY ("ID", "ID"),
    MSG_INTERNAL ("Intern?", "Internal?"),
    MSG_URL ("Ziel-URL", "Target URL"),
    MSG_EDITABLE ("Editierbar?", "Editable?");

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

  private final GoMappingManager m_aGoMappingMgr;

  public BasePageAppInfoGo (@Nonnull @Nonempty final String sID, @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_GO.getAsMLT ());
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageAppInfoGo (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, sName);
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageAppInfoGo (@Nonnull @Nonempty final String sID,
                            @Nonnull final String sName,
                            @Nullable final String sDescription,
                            @Nonnull final GoMappingManager aGoMappingMgr)
  {
    super (sID, sName, sDescription);
    m_aGoMappingMgr = ValueEnforcer.notNull (aGoMappingMgr, "GoMappingMgr");
  }

  public BasePageAppInfoGo (@Nonnull @Nonempty final String sID,
                            @Nonnull final IMultilingualText aName,
                            @Nullable final IMultilingualText aDescription,
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
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale), aWPEC.getSelfHref (), EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    aNodeList.addChild (div (EText.MSG_GO_SERVLET.getDisplayText (aDisplayLocale) +
                             EPhotonCoreText.getYesOrNo (ServletStatusManager.isServletRegistered (GoServlet.class), aDisplayLocale)));

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_KEY.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_INTERNAL.getDisplayText (aDisplayLocale)).setDataSort (1, 0),
                                        new DTCol (EText.MSG_URL.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_EDITABLE.getDisplayText (aDisplayLocale)).setDataSort (3, 0)).setID (getID ());

    for (final GoMappingItem aItem : m_aGoMappingMgr.getAllItems ().values ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aItem.getKey ());
      aRow.addCell (EPhotonCoreText.getYesOrNo (aItem.isInternal (), aDisplayLocale));
      aRow.addCell (HCA.createLinkedWebsite (aItem.getTargetURLAsString ()));
      aRow.addCell (EPhotonCoreText.getYesOrNo (aItem.isEditable (), aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
