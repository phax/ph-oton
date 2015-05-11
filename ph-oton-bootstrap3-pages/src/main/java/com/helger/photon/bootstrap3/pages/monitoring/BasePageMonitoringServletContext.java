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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.web.scopes.mgr.WebScopeManager;

/**
 * Show servlet context information.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringServletContext <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_SERVLETS ("Servlets", "Servlets"),
    MSG_LISTENERS ("Listener", "Listeners"),
    MSG_FILTERS ("Filter", "Filters"),
    MSG_NAME ("Name", "Name"),
    MSG_CLASS_NAME ("Klasse", "Class name"),
    MSG_INIT_PARAMS ("Init Params", "Init params"),
    MSG_MAPPINGS ("Mappings", "Mappings"),
    MSG_SERVLET_MAPPINGS ("Servlet Mappings", "Servlet mappings"),
    MSG_URL_MAPPINGS ("URL Mappings", "URL mappings");

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

  public BasePageMonitoringServletContext (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_SERVLETCONTEXT.getAsMLT ());
  }

  public BasePageMonitoringServletContext (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringServletContext (@Nonnull @Nonempty final String sID,
                                           @Nonnull final String sName,
                                           @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringServletContext (@Nonnull @Nonempty final String sID,
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

    final ServletContext aSC = WebScopeManager.getGlobalScope ().getServletContext ();

    final ITabBox <?> aTabBox = new BootstrapTabBox ();

    {
      // Add servlets
      final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ()).setID (getID () +
                                                                                                                         "servlets");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_CLASS_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_INIT_PARAMS.getDisplayText (aDisplayLocale),
                                       EText.MSG_MAPPINGS.getDisplayText (aDisplayLocale));

      for (final ServletRegistration aRegistration : aSC.getServletRegistrations ().values ())
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aRegistration.getName ());
        aRow.addCell (aRegistration.getClassName ());

        final List <String> aInitParams = new ArrayList <String> ();
        for (final Map.Entry <String, String> aEntry : aRegistration.getInitParameters ().entrySet ())
          aInitParams.add (aEntry.getKey () + "=" + aEntry.getValue ());
        aRow.addCell (HCUtils.list2divList (aInitParams));

        aRow.addCell (HCUtils.list2divList (aRegistration.getMappings ()));
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.MSG_SERVLETS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    {
      // Add filters
      final IHCTable <?> aTable = new BootstrapTable (HCCol.star (),
                                                      HCCol.star (),
                                                      HCCol.star (),
                                                      HCCol.star (),
                                                      HCCol.star ()).setID (getID () + "filters");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_CLASS_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_INIT_PARAMS.getDisplayText (aDisplayLocale),
                                       EText.MSG_SERVLET_MAPPINGS.getDisplayText (aDisplayLocale),
                                       EText.MSG_URL_MAPPINGS.getDisplayText (aDisplayLocale));

      for (final FilterRegistration aRegistration : aSC.getFilterRegistrations ().values ())
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aRegistration.getName ());
        aRow.addCell (aRegistration.getClassName ());

        final List <String> aInitParams = new ArrayList <String> ();
        for (final Map.Entry <String, String> aEntry : aRegistration.getInitParameters ().entrySet ())
          aInitParams.add (aEntry.getKey () + "=" + aEntry.getValue ());
        aRow.addCell (HCUtils.list2divList (aInitParams));

        aRow.addCell (HCUtils.list2divList (aRegistration.getServletNameMappings ()));
        aRow.addCell (HCUtils.list2divList (aRegistration.getUrlPatternMappings ()));
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.MSG_FILTERS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    aNodeList.addChild (aTabBox);
  }
}
