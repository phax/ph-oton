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
package com.helger.webctrls.page.data;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.masterdata.locale.ContinentUtils;
import com.helger.masterdata.locale.EContinent;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.EWebCtrlsCSSPathProvider;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.famfam.EFamFamFlagIcon;
import com.helger.webctrls.page.AbstractWebPageExt;
import com.helger.webctrls.page.EWebPageText;

/**
 * Page with all available locales
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataCountries <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_CONTINENTS ("Kontinente", "Continents");

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
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

  public BasePageDataCountries (@Nonnull @Nonempty final String sID,
                                @Nonnull final String sName,
                                @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageDataCountries (@Nonnull @Nonempty final String sID,
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

    final IHCTable <?> aTable = getStyler ().createTable (HCCol.star (), HCCol.star (), HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_CONTINENTS.getDisplayText (aDisplayLocale));

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
      final Set <EContinent> aContinents = ContinentUtils.getContinentsOfCountry (aCountry);
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

    final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTable);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);

    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EWebCtrlsCSSPathProvider.FAMFAM_FLAGS);
  }
}
