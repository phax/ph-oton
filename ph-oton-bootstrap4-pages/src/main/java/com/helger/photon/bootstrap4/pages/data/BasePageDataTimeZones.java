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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.time.zone.ZoneRules;
import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.compare.ESortOrder;
import com.helger.datetime.format.PDTToString;
import com.helger.datetime.helper.PDTFactory;
import com.helger.datetime.zone.PDTConfig;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with all time zones
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageDataTimeZones <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    MSG_CURRENT_TIMEZONE ("Eingestellte Zeitzone: ", "Time zone set: "),
    MSG_CURRENT_LOCAL_TIME ("Aktuelle lokale Uhrzeit: {0}", "Current local time: {0}"),
    MSG_ID ("ID", "ID"),
    MSG_NAME ("Name", "Name"),
    MSG_SHORTNAME ("Kurzer Name", "Short name"),
    MSG_OFFSET ("Aktuelle Abweichung", "Current offset"),
    MSG_FIXED ("Konstant?", "Fixed?");

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

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_DATA_TIMEZONES.getAsMLT ());
  }

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageDataTimeZones (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
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

    final LocalDateTime aNow = PDTFactory.getCurrentLocalDateTime ();

    // Get default time zone
    final ZoneId aCurrentDTZ = PDTConfig.getDefaultZoneId ();

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.MSG_CURRENT_TIMEZONE.getDisplayText (aDisplayLocale) +
                                                            aCurrentDTZ.getId () +
                                                            " - " +
                                                            aCurrentDTZ.getDisplayName (TextStyle.FULL, aDisplayLocale)));

    // Show current local date time
    aNodeList.addChild (div (EText.MSG_CURRENT_LOCAL_TIME.getDisplayTextWithArgs (aDisplayLocale,
                                                                                  PDTToString.getAsString (PDTFactory.getCurrentLocalDateTime (),
                                                                                                           aDisplayLocale))));

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_SHORTNAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_OFFSET.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DURATION,
                                                                                                                     aDisplayLocale),
                                        new DTCol (EText.MSG_FIXED.getDisplayText (aDisplayLocale))).setID (getID ());
    for (final String sID : ZoneId.getAvailableZoneIds ())
    {
      final ZoneId aDTZ = ZoneId.of (sID);
      final ZoneRules aZR = aDTZ.getRules ();
      final ZoneOffset aZO = aZR.getOffset (aNow);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (sID);
      aRow.addCell (aDTZ.getDisplayName (TextStyle.FULL, aDisplayLocale));
      aRow.addCell (aDTZ.getDisplayName (TextStyle.SHORT, aDisplayLocale));
      aRow.addCell (Duration.ofSeconds (aZO.getTotalSeconds ()).toString ());
      aRow.addCell (EPhotonCoreText.getYesOrNo (aZR.isFixedOffset (), aDisplayLocale));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
