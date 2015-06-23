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

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.type.EBaseType;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.servletstatus.EServletStatus;
import com.helger.photon.core.servletstatus.ServletStatus;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DTCol;
import com.helger.photon.uictrls.datatables.DataTables;

/**
 * Show servlet status.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringServletStatus <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
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
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_SERVLET.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_STATUS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_INVOCATION_COUNT.getDisplayText (aDisplayLocale)).setDisplayType (EBaseType.INT,
                                                                                                                               aDisplayLocale),
                                        new DTCol (EText.MSG_INIT_DT.getDisplayText (aDisplayLocale)).setDisplayType (EBaseType.DATETIME,
                                                                                                                      aDisplayLocale)).setID (getID ());

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
    aNodeList.addChild (aDataTables);
  }
}
