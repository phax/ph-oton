/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.data;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.compare.ESortOrder;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsMap;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.famfam.EFamFamFlagIcon;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.locale.LocaleCache;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with all available locales
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataLanguages <WPECTYPE extends IWebPageExecutionContext> extends
                                   AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_LOCALES ("Locales", "Locales");

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

    final ICommonsMap <String, ICommonsList <Locale>> aMapLanguageToLocale = new CommonsHashMap <> ();
    for (final Locale aLocale : LocaleCache.getInstance ().getAllLocales ())
    {
      final String sLanguage = aLocale.getLanguage ();
      if (sLanguage.length () > 0)
        aMapLanguageToLocale.computeIfAbsent (sLanguage, k -> new CommonsArrayList <> ()).add (aLocale);
    }

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_LOCALES.getDisplayText (aDisplayLocale)).setOrderable (false)).setID (getID ());

    // For all languages
    for (final Map.Entry <String, ICommonsList <Locale>> aEntry : aMapLanguageToLocale.entrySet ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aEntry.getKey ());
      aRow.addCell (aEntry.getValue ().getFirstOrNull ().getDisplayLanguage (aDisplayLocale));

      final IHCCell <?> aCell = aRow.addCell ();
      for (final Locale aLocale : aEntry.getValue ().getSortedInline (Comparator.comparing (Locale::toString)))
      {
        final HCDiv aDiv = div ();
        final EFamFamFlagIcon eIcon = EFamFamFlagIcon.getFromIDOrNull (aLocale.getCountry ());
        if (eIcon != null)
          aDiv.addChild (eIcon.getAsNode ()).addChild (" ");
        aDiv.addChild (aLocale.toString ());
        if (aLocale.getCountry ().length () > 0)
          aDiv.addChild (" (" + aLocale.getDisplayCountry (aDisplayLocale) + ")");
        aCell.addChild (aDiv);
      }
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);

    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.FAMFAM_FLAGS);
  }
}
