/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.appinfo;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.api.APISettings;
import com.helger.photon.api.GlobalAPIInvoker;
import com.helger.photon.api.IAPIAfterExecutionCallback;
import com.helger.photon.api.IAPIBeforeExecutionCallback;
import com.helger.photon.api.IAPIDescriptor;
import com.helger.photon.api.IAPIExceptionCallback;
import com.helger.photon.api.IAPILongRunningExecutionCallback;
import com.helger.photon.api.IAPIRegistry;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;

/**
 * Show all registered APIs.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoAPI <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_HTTP_METHOD ("Verb", "Verb"),
    MSG_URL ("Ziel-URL", "Target URL"),
    MSG_FACTORY ("Factory", "Factory"),
    MSG_REQUIRED_HEADERS ("Header", "Headers"),
    MSG_REQUIRED_PARAMS ("Parameter", "Parameters"),
    MSG_ALLOWED_MIME_TYPES ("MIME Typen", "MIME types"),
    MSG_HAS_EXECUTION_FILTER ("Filter?", "Filter?"),
    MSG_HAS_EXCEPTION_MAPPER ("Exception?", "Exception?"),
    MSG_CALLBACKS ("Callbacks", "Callbacks"),
    MSG_TYPE ("Typ", "Type"),
    MSG_CALLBACK ("Callback", "Callback"),
    MSG_NONE_FOUND ("Keine Daten gefunden.", "No data found.");

    @Nonnull
    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageAppInfoAPI (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_API.getAsMLT ());
  }

  public BasePageAppInfoAPI (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoAPI (@Nonnull @Nonempty final String sID,
                             @Nonnull final String sName,
                             @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoAPI (@Nonnull @Nonempty final String sID,
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

    final IAPIRegistry aRegistry = GlobalAPIInvoker.getInstance ().getRegistry ();
    final HCNodeList aTab = new HCNodeList ();

    // Show all registered AJAX functions
    {
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_HTTP_METHOD.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_URL.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_FACTORY.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_REQUIRED_HEADERS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_REQUIRED_PARAMS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_ALLOWED_MIME_TYPES.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_HAS_EXECUTION_FILTER.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_HAS_EXCEPTION_MAPPER.getDisplayText (aDisplayLocale))).setID (getID () + "-api");
      for (final IAPIDescriptor aDescriptor : aRegistry.getAllAPIDescriptors ())
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCells (aDescriptor.getHTTPMethod ().getName (),
                       aDescriptor.getPathDescriptor ().getAsURLString (),
                       aDescriptor.getExecutorFactory ().toString ());
        aRow.addCell (HCExtHelper.list2divList (aDescriptor.requiredHeaders ()));
        aRow.addCell (HCExtHelper.list2divList (aDescriptor.requiredParams ()));
        aRow.addCell (HCExtHelper.list2divList (aDescriptor.allowedMimeTypes ()));
        aRow.addCell (EPhotonCoreText.getYesOrNo (aDescriptor.hasExecutionFilter (), aDisplayLocale));
        aRow.addCell (EPhotonCoreText.getYesOrNo (aDescriptor.hasExceptionMapper (), aDisplayLocale));
      }
      aTab.addChild (aTable);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTab.addChild (aDataTables);
    }

    // Show all callbacks
    {
      aTab.addChild (getUIHandler ().createDataGroupHeader (EText.MSG_CALLBACKS.getDisplayText (aDisplayLocale)));

      final HCTable aTable = new HCTable (new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)).setDataSort (0, 1)
                                                                                                    .setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_CALLBACK.getDisplayText (aDisplayLocale))).setID (getID () + "-api-cb");
      for (final IAPIExceptionCallback aCB : APISettings.exceptionCallbacks ().getAllCallbacks ())
        aTable.addBodyRow ().addCells ("Exception", aCB.toString ());
      for (final IAPIBeforeExecutionCallback aCB : APISettings.beforeExecutionCallbacks ().getAllCallbacks ())
        aTable.addBodyRow ().addCells ("BeforeExecution", aCB.toString ());
      for (final IAPIAfterExecutionCallback aCB : APISettings.afterExecutionCallbacks ().getAllCallbacks ())
        aTable.addBodyRow ().addCells ("AfterExecution", aCB.toString ());
      for (final IAPILongRunningExecutionCallback aCB : APISettings.longRunningExecutionCallbacks ().getAllCallbacks ())
        aTable.addBodyRow ().addCells ("LongRunningExecution", aCB.toString ());
      aTab.addChild (aTable);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTab.addChild (aDataTables);
    }

    aNodeList.addChild (aTab);
  }
}
