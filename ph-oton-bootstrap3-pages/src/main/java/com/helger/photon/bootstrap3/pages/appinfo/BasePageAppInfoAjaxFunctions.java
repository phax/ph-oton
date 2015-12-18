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
package com.helger.photon.bootstrap3.pages.appinfo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.scope.IApplicationScope;
import com.helger.commons.scope.singleton.AbstractSingleton;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.ajax.ApplicationAjaxManager;
import com.helger.photon.core.ajax.IAjaxAfterExecutionCallback;
import com.helger.photon.core.ajax.IAjaxBeforeExecutionCallback;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxFunctionDeclaration;
import com.helger.photon.core.ajax.IAjaxLongRunningExecutionCallback;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Show all registered AJAX functions.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoAjaxFunctions <WPECTYPE extends IWebPageExecutionContext>
                                          extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_KEY ("ID", "ID"),
    MSG_FACTORY ("Factory", "Factory"),
    MSG_URL ("Ziel-URL", "Target URL"),
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

  public BasePageAppInfoAjaxFunctions (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_AJAX_FUNCTIONS.getAsMLT ());
  }

  public BasePageAppInfoAjaxFunctions (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageAppInfoAjaxFunctions (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageAppInfoAjaxFunctions (@Nonnull @Nonempty final String sID,
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
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    for (final IApplicationScope aAppScope : WebScopeManager.getGlobalScope ().getAllApplicationScopes ().values ())
    {
      final String sAppScopeID = aAppScope.getID ();
      final ApplicationAjaxManager aMgr = AbstractSingleton.getSingletonIfInstantiated (aAppScope,
                                                                                        ApplicationAjaxManager.class);
      if (aMgr != null)
      {
        final HCNodeList aTab = new HCNodeList ();

        // Show all registered AJAX functions
        {
          final HCTable aTable = new HCTable (new DTCol (EText.MSG_KEY.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                              new DTCol (EText.MSG_FACTORY.getDisplayText (aDisplayLocale)),
                                              new DTCol (EText.MSG_URL.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                sAppScopeID +
                                                                                                                "-ajax");
          for (final Map.Entry <String, IAjaxFunctionDeclaration> aEntry : aMgr.getAllRegisteredFunctions ()
                                                                               .entrySet ())
          {
            aTable.addBodyRow ().addCells (aEntry.getKey (),
                                           aEntry.getValue ().getExecutorFactory ().toString (),
                                           aEntry.getValue ().getInvocationURI (aRequestScope));
          }
          aTab.addChild (aTable);

          final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
          aTab.addChild (aDataTables);
        }

        // Show all callbacks
        {
          aTab.addChild (createDataGroupHeader (EText.MSG_CALLBACKS.getDisplayText (aDisplayLocale)));

          final HCTable aTable = new HCTable (new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                      1)
                                                                                                        .setInitialSorting (ESortOrder.ASCENDING),
                                              new DTCol (EText.MSG_CALLBACK.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                     sAppScopeID +
                                                                                                                     "-ajax-cb");
          for (final IAjaxExceptionCallback aCB : aMgr.getExceptionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("Exception", aCB.toString ());
          for (final IAjaxBeforeExecutionCallback aCB : aMgr.getBeforeExecutionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("BeforeExecution", aCB.toString ());
          for (final IAjaxAfterExecutionCallback aCB : aMgr.getAfterExecutionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("AfterExecution", aCB.toString ());
          for (final IAjaxLongRunningExecutionCallback aCB : aMgr.getLongRunningExecutionCallbacks ()
                                                                 .getAllCallbacks ())
            aTable.addBodyRow ().addCells ("LongRunningExecution", aCB.toString ());
          aTab.addChild (aTable);

          final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
          aTab.addChild (aDataTables);
        }

        // Add to tab box
        aTabBox.addTab (sAppScopeID, aTab);
      }
    }

    if (aTabBox.getTabCount () > 0)
      aNodeList.addChild (aTabBox);
    else
      aNodeList.addChild (new BootstrapInfoBox ().addChild (EText.MSG_NONE_FOUND.getDisplayText (aDisplayLocale)));
  }
}
