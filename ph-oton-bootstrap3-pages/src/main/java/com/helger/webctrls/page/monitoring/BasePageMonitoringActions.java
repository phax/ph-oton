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
package com.helger.webctrls.page.monitoring;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.alert.BootstrapInfoBox;
import com.helger.bootstrap3.datatables.BootstrapDataTables;
import com.helger.bootstrap3.nav.BootstrapTabBox;
import com.helger.bootstrap3.table.BootstrapTable;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.scopes.AbstractSingleton;
import com.helger.commons.scopes.domain.IApplicationScope;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCH3;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.scopes.mgr.WebScopeManager;
import com.helger.webbasics.action.ApplicationActionManager;
import com.helger.webbasics.action.IActionAfterExecutionCallback;
import com.helger.webbasics.action.IActionBeforeExecutionCallback;
import com.helger.webbasics.action.IActionDeclaration;
import com.helger.webbasics.action.IActionExceptionCallback;
import com.helger.webbasics.action.IActionLongRunningExecutionCallback;
import com.helger.webctrls.datatables.DataTables;

/**
 * Show all registered actions.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringActions <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
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
    private final TextProvider m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }
  }

  public BasePageMonitoringActions (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_ACTIONS.getAsMLT ());
  }

  public BasePageMonitoringActions (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringActions (@Nonnull @Nonempty final String sID,
                                    @Nonnull final String sName,
                                    @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringActions (@Nonnull @Nonempty final String sID,
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
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();

    final ITabBox <?> aTabBox = new BootstrapTabBox ();

    for (final IApplicationScope aAppScope : WebScopeManager.getGlobalScope ().getAllApplicationScopes ().values ())
    {
      final String sAppScopeID = aAppScope.getID ();
      final ApplicationActionManager aMgr = AbstractSingleton.getSingletonIfInstantiated (aAppScope,
                                                                                          ApplicationActionManager.class);
      if (aMgr != null)
      {
        final HCNodeList aTab = new HCNodeList ();

        // Show all registered AJAX functions
        {
          final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star (), HCCol.star ()).setID (getID () +
                                                                                                              sAppScopeID +
                                                                                                              "-func");
          aTable.addHeaderRow ().addCells (EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                           EText.MSG_FACTORY.getDisplayText (aDisplayLocale),
                                           EText.MSG_URL.getDisplayText (aDisplayLocale));

          for (final Map.Entry <String, IActionDeclaration> aEntry : CollectionHelper.getSortedByKey (aMgr.getAllRegisteredActions ())
                                                                                     .entrySet ())
          {
            aTable.addBodyRow ().addCells (aEntry.getKey (),
                                           aEntry.getValue ().getExecutorFactory ().toString (),
                                           aEntry.getValue ().getInvocationURI (aRequestScope));
          }
          aTab.addChild (aTable);

          final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
          aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
          aTab.addChild (aDataTables);
        }

        // Show all callbacks
        {
          aTab.addChild (new HCH3 ().addChild (EText.MSG_CALLBACKS.getDisplayText (aDisplayLocale)));

          final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), HCCol.star ()).setID (getID () +
                                                                                               sAppScopeID +
                                                                                               "-cb");
          aTable.addHeaderRow ().addCells (EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                           EText.MSG_CALLBACK.getDisplayText (aDisplayLocale));
          for (final IActionExceptionCallback aCB : aMgr.getExceptionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("Exception", aCB.toString ());
          for (final IActionBeforeExecutionCallback aCB : aMgr.getBeforeExecutionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("BeforeExecution", aCB.toString ());
          for (final IActionAfterExecutionCallback aCB : aMgr.getAfterExecutionCallbacks ().getAllCallbacks ())
            aTable.addBodyRow ().addCells ("AfterExecution", aCB.toString ());
          for (final IActionLongRunningExecutionCallback aCB : aMgr.getLongRunningExecutionCallbacks ()
                                                                   .getAllCallbacks ())
            aTable.addBodyRow ().addCells ("LongRunningExecution", aCB.toString ());
          aTab.addChild (aTable);

          final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
          aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
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
