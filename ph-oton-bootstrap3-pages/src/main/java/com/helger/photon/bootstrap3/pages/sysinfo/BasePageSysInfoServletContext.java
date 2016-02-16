/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Show servlet context information.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageSysInfoServletContext <WPECTYPE extends IWebPageExecutionContext>
                                           extends AbstractBootstrapWebPage <WPECTYPE>
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
    MSG_URL_MAPPINGS ("URL Mappings", "URL mappings"),
    MSG_ROOT_MAPPING ("ROOT Mapping", "ROOT mapping");

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

  public BasePageSysInfoServletContext (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_SERVLETCONTEXT.getAsMLT ());
  }

  public BasePageSysInfoServletContext (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoServletContext (@Nonnull @Nonempty final String sID,
                                        @Nonnull final String sName,
                                        @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoServletContext (@Nonnull @Nonempty final String sID,
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

    final ServletContext aSC = WebScopeManager.getGlobalScope ().getServletContext ();

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    {
      // Add servlets
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_CLASS_NAME.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_INIT_PARAMS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_MAPPINGS.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                 "servlets");
      for (final ServletRegistration aRegistration : aSC.getServletRegistrations ().values ())
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aRegistration.getName ());
        aRow.addCell (aRegistration.getClassName ());

        final List <String> aInitParams = new ArrayList <String> ();
        for (final Map.Entry <String, String> aEntry : aRegistration.getInitParameters ().entrySet ())
          aInitParams.add (aEntry.getKey () + "=" + aEntry.getValue ());
        aRow.addCell (HCExtHelper.list2divList (aInitParams));

        final HCNodeList aURLPatterns = new HCNodeList ();
        for (final String sText : aRegistration.getMappings ())
          if (sText.length () == 0)
            aURLPatterns.addChild (new HCDiv ().addChild (new HCEM ().addChild (EText.MSG_ROOT_MAPPING.getDisplayText (aDisplayLocale))));
          else
            aURLPatterns.addChild (new HCDiv ().addChild (sText));
        aRow.addCell (aURLPatterns);
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);

      aTabBox.addTab ("servlets",
                      EText.MSG_SERVLETS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    {
      // Add filters
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_CLASS_NAME.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_INIT_PARAMS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_SERVLET_MAPPINGS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_URL_MAPPINGS.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                     "filters");
      for (final FilterRegistration aRegistration : aSC.getFilterRegistrations ().values ())
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aRegistration.getName ());
        aRow.addCell (aRegistration.getClassName ());

        final List <String> aInitParams = new ArrayList <String> ();
        for (final Map.Entry <String, String> aEntry : aRegistration.getInitParameters ().entrySet ())
          aInitParams.add (aEntry.getKey () + "=" + aEntry.getValue ());
        aRow.addCell (HCExtHelper.list2divList (aInitParams));

        aRow.addCell (HCExtHelper.list2divList (aRegistration.getServletNameMappings ()));

        final HCNodeList aURLPatterns = new HCNodeList ();
        for (final String sText : aRegistration.getUrlPatternMappings ())
          if (sText.length () == 0)
            aURLPatterns.addChild (new HCDiv ().addChild (new HCEM ().addChild (EText.MSG_ROOT_MAPPING.getDisplayText (aDisplayLocale))));
          else
            aURLPatterns.addChild (new HCDiv ().addChild (sText));

        aRow.addCell (aURLPatterns);
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);

      aTabBox.addTab ("filters",
                      EText.MSG_FILTERS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    aNodeList.addChild (aTabBox);
  }
}
