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
package com.helger.photon.page.data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.datatables.BootstrapDataTables;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.collections.multimap.IMultiMapListBased;
import com.helger.commons.collections.multimap.MultiHashMapArrayListBased;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.locale.ComparatorLocale;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webctrls.EUICtrlsCSSPathProvider;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.famfam.EFamFamFlagIcon;

/**
 * Page with all available locales
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataLanguages <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_LOCALES ("Locales", "Locales");

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

  public BasePageDataLanguages (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_DATA_LANGUAGES.getAsMLT ());
  }

  public BasePageDataLanguages (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageDataLanguages (@Nonnull @Nonempty final String sID,
                                @Nonnull final String sName,
                                @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageDataLanguages (@Nonnull @Nonempty final String sID,
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

    final IMultiMapListBased <String, Locale> aMapLanguageToLocale = new MultiHashMapArrayListBased <String, Locale> ();
    for (final Locale aLocale : LocaleCache.getAllLocales ())
    {
      final String sLanguage = aLocale.getLanguage ();
      if (sLanguage.length () > 0)
        aMapLanguageToLocale.putSingle (sLanguage, aLocale);
    }

    final IHCTable <?> aTable = new BootstrapTable (new HCCol (100), new HCCol (200), HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_LOCALES.getDisplayText (aDisplayLocale));

    // For all languages
    for (final Map.Entry <String, List <Locale>> aEntry : aMapLanguageToLocale.entrySet ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aEntry.getKey ());
      aRow.addCell (CollectionHelper.getFirstElement (aEntry.getValue ()).getDisplayLanguage (aDisplayLocale));

      final IHCCell <?> aCell = aRow.addCell ();
      for (final Locale aLocale : CollectionHelper.getSorted (aEntry.getValue (), new ComparatorLocale ()))
      {
        final HCDiv aDiv = new HCDiv ();
        final EFamFamFlagIcon eIcon = EFamFamFlagIcon.getFromIDOrNull (aLocale.getCountry ());
        if (eIcon != null)
        {
          aDiv.addChild (eIcon.getAsNode ());
          aDiv.addChild (" ");
        }
        aDiv.addChild (aLocale.toString ());
        if (aLocale.getCountry ().length () > 0)
          aDiv.addChild (" (" + aLocale.getDisplayCountry (aDisplayLocale) + ")");
        aCell.addChild (aDiv);
      }
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (2).setSortable (false);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);

    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
  }
}
