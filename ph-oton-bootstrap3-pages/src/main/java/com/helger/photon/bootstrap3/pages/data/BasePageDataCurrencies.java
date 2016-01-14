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
package com.helger.photon.bootstrap3.pages.data;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.type.EBaseType;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.masterdata.currency.CurrencyHelper;
import com.helger.masterdata.currency.ECurrency;
import com.helger.masterdata.locale.ContinentHelper;
import com.helger.masterdata.locale.EContinent;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.famfam.EFamFamFlagIcon;

/**
 * Page with all currencies
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataCurrencies <WPECTYPE extends IWebPageExecutionContext>
                                    extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CODE ("Code", "Code"),
    MSG_NAME ("Name", "Name"),
    MSG_SYMBOL ("Symbol", "Symbol"),
    MSG_DEFAULT_FRACTION_DIGITS ("Nachkommastellen", "Fraction digits"),
    MSG_EXAMPLE ("Beispiel", "Example"),
    MSG_CONTINENTS ("Kontinente", "Continents"),
    MSG_LOCALE ("Locale", "Locale");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageDataCurrencies (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_DATA_CURRENCIES.getAsMLT ());
  }

  public BasePageDataCurrencies (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageDataCurrencies (@Nonnull @Nonempty final String sID,
                                 @Nonnull final String sName,
                                 @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageDataCurrencies (@Nonnull @Nonempty final String sID,
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

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_CODE.getDisplayText (aDisplayLocale)).setDataSort (0, 6)
                                                                                                  .setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setDataSort (1, 6),
                                        new DTCol (EText.MSG_SYMBOL.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_DEFAULT_FRACTION_DIGITS.getDisplayText (aDisplayLocale)).setDisplayType (EBaseType.INT,
                                                                                                                                      aDisplayLocale),
                                        new DTCol (EText.MSG_EXAMPLE.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_CONTINENTS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_LOCALE.getDisplayText (aDisplayLocale))).setID (getID ());
    for (final Map.Entry <Locale, Currency> aEntry : CurrencyHelper.getLocaleToCurrencyMap ().entrySet ())
    {
      final Locale aLocale = aEntry.getKey ();
      final Currency aCurrency = aEntry.getValue ();
      final ECurrency eCurrency = ECurrency.getFromIDOrNull (aCurrency.getCurrencyCode ());

      final HCRow aRow = aTable.addBodyRow ();

      aRow.addCell (aCurrency.getCurrencyCode ());
      aRow.addCell (eCurrency == null ? null : eCurrency.getDisplayText (aDisplayLocale));
      aRow.addCell (aCurrency.getSymbol (aDisplayLocale));
      aRow.addCell (Integer.toString (aCurrency.getDefaultFractionDigits ()));
      aRow.addCell (NumberFormat.getCurrencyInstance (aLocale).format (12.3456));

      // Continents
      final Set <EContinent> aContinents = ContinentHelper.getContinentsOfCountry (aLocale);
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

      // Locale name and flag
      final HCDiv aDiv = new HCDiv ();
      final EFamFamFlagIcon eIcon = EFamFamFlagIcon.getFromIDOrNull (aLocale.getCountry ());
      if (eIcon != null)
      {
        aDiv.addChild (eIcon.getAsNode ());
        aDiv.addChild (" ");
      }
      aDiv.addChild (aLocale.getDisplayName (aDisplayLocale) + " [" + aLocale.toString () + "]");
      aRow.addCell (aDiv);
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);

    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
  }
}
