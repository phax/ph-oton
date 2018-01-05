/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.appinfo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.xservlet.servletstatus.EServletStatus;
import com.helger.xservlet.servletstatus.ServletStatus;
import com.helger.xservlet.servletstatus.ServletStatusManager;

/**
 * Show servlet status.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageAppInfoServletStatus <WPECTYPE extends IWebPageExecutionContext> extends
                                          AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_SERVLET ("Servlet Klasse", "Servlet class"),
    MSG_STATUS ("Status", "Status"),
    MSG_INVOCATION_COUNT ("Aufrufe", "Invocations"),
    MSG_INIT_DT ("Initialisiert", "Initialized");

    @Nonnull
    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageAppInfoServletStatus (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_SERVLETSTATUS.getAsMLT ());
  }

  public BasePageAppInfoServletStatus (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoServletStatus (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoServletStatus (@Nonnull @Nonempty final String sID,
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

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_SERVLET.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_STATUS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_INVOCATION_COUNT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                               aDisplayLocale),
                                        new DTCol (EText.MSG_INIT_DT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                      aDisplayLocale)).setID (getID ());

    for (final Map.Entry <String, ServletStatus> aItem : ServletStatusManager.getInstance ()
                                                                             .getAllStatus ()
                                                                             .entrySet ())
    {
      final ServletStatus aServletStatus = aItem.getValue ();

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aItem.getKey ());
      aRow.addCell (aServletStatus.getCurrentStatus ().getDisplayText (aDisplayLocale));
      aRow.addCell (Integer.toString (aServletStatus.getInvocationCount ()));
      aRow.addCell (PDTToString.getAsString (aServletStatus.getDateTimeOfStatus (EServletStatus.INITED),
                                             aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
