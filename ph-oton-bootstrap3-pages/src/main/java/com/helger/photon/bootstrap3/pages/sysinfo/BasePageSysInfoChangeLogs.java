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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.changelog.ChangeLog;
import com.helger.commons.changelog.ChangeLogEntry;
import com.helger.commons.changelog.ChangeLogSerializer;
import com.helger.commons.changelog.ComparatorChangeLogEntryDate;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTDate;

/**
 * Page with all linked third party libraries
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoChangeLogs <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_HEADER_DATE ("Datum", "Date"),
    MSG_HEADER_COMPONENT ("Komponente", "Component"),
    MSG_HEADER_CATEGORY ("Kategorie", "Category"),
    MSG_HEADER_CHANGE ("Änderung", "Change");

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

  private static List <ChangeLogEntry> s_aCache;

  public BasePageSysInfoChangeLogs (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_CHANGELOGS.getAsMLT ());
  }

  public BasePageSysInfoChangeLogs (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoChangeLogs (@Nonnull @Nonempty final String sID,
                                    @Nonnull final String sName,
                                    @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoChangeLogs (@Nonnull @Nonempty final String sID,
                                    @Nonnull final IReadonlyMultiLingualText aName,
                                    @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nullable
  private static String _getText (@Nonnull final ChangeLogEntry aEntry, @Nonnull final Locale aDisplayLocale)
  {
    String ret = aEntry.getText (aDisplayLocale);
    if (StringHelper.hasNoText (ret))
      if (aEntry.getAllTexts ().size () == 1)
        ret = CollectionHelper.getFirstElement (aEntry.getAllTexts ().getAllTexts ()).getValue ();
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
      List <ChangeLogEntry> aChangeLogEntries = new ArrayList <ChangeLogEntry> ();
      for (final ChangeLog aChangeLog : CollectionHelper.newList (ChangeLogSerializer.readAllChangeLogs ().values ()))
        aChangeLogEntries.addAll (aChangeLog.getAllEntries ());

      // Show at last the 500 newest entries
      aChangeLogEntries = CollectionHelper.getSortedInline (aChangeLogEntries,
                                                           new ComparatorChangeLogEntryDate (ESortOrder.DESCENDING));
      s_aCache = aChangeLogEntries.subList (0, Math.min (500, aChangeLogEntries.size ()));
    }

    // Create table
    final IHCTable <?> aTable = new BootstrapTable (new HCCol (COLUMN_WIDTH_DATE),
                                                          new HCCol (150),
                                                          new HCCol (100),
                                                          HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_HEADER_DATE.getDisplayText (aDisplayLocale),
                                     EText.MSG_HEADER_COMPONENT.getDisplayText (aDisplayLocale),
                                     EText.MSG_HEADER_CATEGORY.getDisplayText (aDisplayLocale),
                                     EText.MSG_HEADER_CHANGE.getDisplayText (aDisplayLocale));
    for (final ChangeLogEntry aEntry : s_aCache)
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (PDTToString.getAsString (PDTFactory.createLocalDate (aEntry.getDate ()), aDisplayLocale));
      aRow.addCell (aEntry.getChangeLog ().getComponent ());
      aRow.addCell (aEntry.getCategory ().getID () + " " + aEntry.getAction ().getID ());
      aRow.addCell (_getText (aEntry, aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (0).setComparator (new ComparatorDTDate (aDisplayLocale));
    aDataTables.setInitialSorting (0, ESortOrder.DESCENDING);
    aNodeList.addChild (aDataTables);
  }
}
