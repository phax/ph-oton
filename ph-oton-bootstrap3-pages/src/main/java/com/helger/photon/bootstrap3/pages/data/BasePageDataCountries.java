/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.data;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.masterdata.locale.ContinentHelper;
import com.helger.masterdata.locale.EContinent;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.famfam.EFamFamFlagIcon;

/**
 * Page with all available locales
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataCountries <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_CONTINENTS ("Kontinente", "Continents");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageDataCountries (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_DATA_COUNTRIES.getAsMLT ());
  }

  public BasePageDataCountries (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageDataCountries (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageDataCountries (@Nonnull @Nonempty final String sID,
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

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_CONTINENTS.getDisplayText (aDisplayLocale))).setID (getID ());

    // For all countries
    for (final Locale aCountry : CountryCache.getInstance ().getAllCountryLocales ())
    {
      final HCRow aRow = aTable.addBodyRow ();

      // ID
      aRow.addCell (aCountry.getCountry ());

      // Flag and name
      final HCDiv aDiv = new HCDiv ();
      final EFamFamFlagIcon eIcon = EFamFamFlagIcon.getFromIDOrNull (aCountry.getCountry ());
      if (eIcon != null)
      {
        aDiv.addChild (eIcon.getAsNode ());
        aDiv.addChild (" ");
      }
      aDiv.addChild (aCountry.getDisplayCountry (aDisplayLocale));
      aRow.addCell (aDiv);

      // Continents
      final Set <EContinent> aContinents = ContinentHelper.getContinentsOfCountry (aCountry);
      final StringBuilder aSB = new StringBuilder ();
      if (aContinents != null)
        for (final EContinent eContinent : aContinents)
          if (eContinent != null)
          {
            if (aSB.length () > 0)
              aSB.append (", ");
            aSB.append (eContinent.getDisplayText (aDisplayLocale));
          }
      aRow.addCell (aSB.toString ());
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);

    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
  }
}
