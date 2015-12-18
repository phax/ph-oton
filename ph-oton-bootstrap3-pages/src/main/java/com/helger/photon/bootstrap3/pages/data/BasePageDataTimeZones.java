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
package com.helger.photon.bootstrap3.pages.data;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.format.FormatterStringSkipPrefixAndSuffix;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.config.PDTConfig;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTInteger;

/**
 * Page with all time zones
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataTimeZones <WPECTYPE extends IWebPageExecutionContext>
                                   extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CURRENT_TIMEZONE ("Eingestellte Zeitzone: ", "Time zone set: "),
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_SHORTNAME ("Kurzer Name", "Short name"),
    MSG_OFFSET ("Abweichung", "Offset"),
    MSG_STANDARD_OFFSET ("Ist Std.?", "Is std?"),
    MSG_FIXED ("Konstant?", "Fixed?");

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

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_DATA_TIMEZONES.getAsMLT ());
  }

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID,
                                @Nonnull final String sName,
                                @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID,
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

    final long nNow = PDTFactory.getCurrentMillis ();

    // Get default time zone
    final DateTimeZone aCurrentDTZ = PDTConfig.getDefaultDateTimeZone ();

    aNodeList.addChild (createActionHeader (EText.MSG_CURRENT_TIMEZONE.getDisplayText (aDisplayLocale) +
                                            aCurrentDTZ.getID () +
                                            " - " +
                                            aCurrentDTZ.getName (nNow)));
    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_SHORTNAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_OFFSET.getDisplayText (aDisplayLocale)).setComparator (new ComparatorDTInteger (new FormatterStringSkipPrefixAndSuffix ("PT",
                                                                                                                                                                                     "S"),
                                                                                                                                             aDisplayLocale)),
                                        new DTCol (EText.MSG_STANDARD_OFFSET.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_FIXED.getDisplayText (aDisplayLocale))).setID (getID ());
    for (final String sID : DateTimeZone.getAvailableIDs ())
    {
      final DateTimeZone aDTZ = DateTimeZone.forID (sID);
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (sID);
      aRow.addCell (aDTZ.getName (nNow, aDisplayLocale));
      aRow.addCell (aDTZ.getShortName (nNow, aDisplayLocale));
      aRow.addCell (new Duration (aDTZ.getOffset (nNow)).toString ());
      aRow.addCell (EPhotonCoreText.getYesOrNo (aDTZ.isStandardOffset (nNow), aDisplayLocale));
      aRow.addCell (EPhotonCoreText.getYesOrNo (aDTZ.isFixed (), aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
