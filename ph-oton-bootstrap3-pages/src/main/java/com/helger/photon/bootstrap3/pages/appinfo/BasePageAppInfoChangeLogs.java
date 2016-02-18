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
package com.helger.photon.bootstrap3.pages.appinfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.changelog.ChangeLog;
import com.helger.commons.changelog.ChangeLogEntry;
import com.helger.commons.changelog.ChangeLogSerializer;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Page with all linked third party libraries
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoChangeLogs <WPECTYPE extends IWebPageExecutionContext>
                                       extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_HEADER_DATE ("Datum", "Date"),
    MSG_HEADER_COMPONENT ("Komponente", "Component"),
    MSG_HEADER_CATEGORY ("Kategorie", "Category"),
    MSG_HEADER_CHANGE ("Änderung", "Change");

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

  private static List <ChangeLogEntry> s_aCache;

  public BasePageAppInfoChangeLogs (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_CHANGELOGS.getAsMLT ());
  }

  public BasePageAppInfoChangeLogs (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoChangeLogs (@Nonnull @Nonempty final String sID,
                                    @Nonnull final String sName,
                                    @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoChangeLogs (@Nonnull @Nonempty final String sID,
                                    @Nonnull final IMultilingualText aName,
                                    @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nullable
  private static String _getText (@Nonnull final ChangeLogEntry aEntry, @Nonnull final Locale aDisplayLocale)
  {
    String ret = aEntry.getText (aDisplayLocale);
    if (StringHelper.hasNoText (ret))
      if (aEntry.getAllTexts ().getSize () == 1)
        ret = aEntry.getAllTexts ().getAllTexts ().getFirstValue ();
    return ret;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (s_aCache == null)
    {
      // Get all change logs
      List <ChangeLogEntry> aChangeLogEntries = new ArrayList <> ();
      for (final ChangeLog aChangeLog : CollectionHelper.newList (ChangeLogSerializer.readAllChangeLogs ().values ()))
        aChangeLogEntries.addAll (aChangeLog.getAllEntries ());

      // Show at last the 500 newest entries
      aChangeLogEntries = CollectionHelper.getSortedInline (aChangeLogEntries,
                                                            Comparator.comparing (ChangeLogEntry::getDate)
                                                                      .thenComparing (Comparator.comparing (ChangeLogEntry::getChangeLogComponent))
                                                                      .reversed ());
      s_aCache = aChangeLogEntries.subList (0, Math.min (500, aChangeLogEntries.size ()));
    }

    // Create table
    final HCTable aTable = new HCTable (new DTCol (EText.MSG_HEADER_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATE,
                                                                                                                          aDisplayLocale)
                                                                                                         .setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_HEADER_COMPONENT.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_HEADER_CATEGORY.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_HEADER_CHANGE.getDisplayText (aDisplayLocale))).setID (getID ());
    for (final ChangeLogEntry aEntry : s_aCache)
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (aEntry.getDate (), aDisplayLocale));
      aRow.addCell (aEntry.getChangeLog ().getComponent ());
      aRow.addCell (aEntry.getCategory ().getID () + " " + aEntry.getAction ().getID ());
      aRow.addCell (_getText (aEntry, aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
