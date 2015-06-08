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

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.format.impl.StringSkipPrefixAndSuffixFormatter;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.config.PDTConfig;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTInteger;

/**
 * Page with all time zones
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataTimeZones <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
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

    final long nNow = PDTFactory.getCurrentMillis ();

    // Get default time zone
    final DateTimeZone aCurrentDTZ = PDTConfig.getDefaultDateTimeZone ();

    aNodeList.addChild (createActionHeader (EText.MSG_CURRENT_TIMEZONE.getDisplayText (aDisplayLocale) +
                                            aCurrentDTZ.getID () +
                                            " - " +
                                            aCurrentDTZ.getName (nNow)));
    final IHCTable <?> aTable = new BootstrapTable (new HCCol (100),
                                                    HCCol.star (),
                                                    new HCCol (100),
                                                    new HCCol (70),
                                                    new HCCol (70),
                                                    new HCCol (70)).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_SHORTNAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_OFFSET.getDisplayText (aDisplayLocale),
                                     EText.MSG_STANDARD_OFFSET.getDisplayText (aDisplayLocale),
                                     EText.MSG_FIXED.getDisplayText (aDisplayLocale));
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
    aDataTables.getOrCreateColumnOfTarget (3)
               .setComparator (new ComparatorDTInteger (new StringSkipPrefixAndSuffixFormatter ("PT", "S"),
                                                        aDisplayLocale));
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
