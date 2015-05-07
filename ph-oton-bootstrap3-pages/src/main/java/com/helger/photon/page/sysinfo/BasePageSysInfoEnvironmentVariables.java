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
package com.helger.photon.page.sysinfo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.datatables.BootstrapDataTables;
import com.helger.bootstrap3.table.BootstrapTable;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.webctrls.datatables.DataTables;

/**
 * Page with all environment variables
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoEnvironmentVariables <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_NAME ("Name", "Name"),
    MSG_VALUE ("Wert", "Value");

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

  public BasePageSysInfoEnvironmentVariables (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_ENV_VARS.getAsMLT ());
  }

  public BasePageSysInfoEnvironmentVariables (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoEnvironmentVariables (@Nonnull @Nonempty final String sID,
                                              @Nonnull final String sName,
                                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoEnvironmentVariables (@Nonnull @Nonempty final String sID,
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

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_VALUE.getDisplayText (aDisplayLocale));

    // For all environment variables
    for (final Map.Entry <String, String> aEntry : System.getenv ().entrySet ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aEntry.getKey ());
      aRow.addCell (aEntry.getValue ());
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
