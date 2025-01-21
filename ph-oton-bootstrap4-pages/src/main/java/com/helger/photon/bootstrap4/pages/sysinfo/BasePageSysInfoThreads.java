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
package com.helger.photon.bootstrap4.pages.sysinfo;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenu;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Page with all threads
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoThreads <WPECTYPE extends IWebPageExecutionContext> extends
                                    AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    MSG_HEADER ("Anzahl={0}; Prios (min/norm/max): " +
                Thread.MIN_PRIORITY +
                "/" +
                Thread.NORM_PRIORITY +
                "/" +
                Thread.MAX_PRIORITY +
                "; Zeitpunkt: {1}",
                "Total count={0}; Prios (min/norm/max): " +
                                    Thread.MIN_PRIORITY +
                                    "/" +
                                    Thread.NORM_PRIORITY +
                                    "/" +
                                    Thread.MAX_PRIORITY +
                                    "; Datetime: {1}"),
    MSG_ID ("ID", "ID"),
    MSG_GROUP ("Gruppe", "Group"),
    MSG_NAME ("Name", "Name"),
    MSG_PRIORITY ("Prio", "Prio"),
    MSG_STATE ("Status", "State"),
    MSG_STACKTRACE ("Stacktrace", "Stacktrace");

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

  public BasePageSysInfoThreads (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_THREADS.getAsMLT ());
  }

  public BasePageSysInfoThreads (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoThreads (@Nonnull @Nonempty final String sID,
                                 @Nonnull final String sName,
                                 @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoThreads (@Nonnull @Nonempty final String sID,
                                 @Nonnull final IMultilingualText aName,
                                 @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nonnull
  private static String _getThreadGroupName (@Nullable final ThreadGroup aParamTG)
  {
    ThreadGroup aThreadGroup = aParamTG;
    final StringBuilder ret = new StringBuilder ();
    while (aThreadGroup != null)
    {
      if (ret.length () > 0)
        ret.insert (0, '/');
      ret.insert (0, aThreadGroup.getName ());

      // Descend
      aThreadGroup = aThreadGroup.getParent ();
    }
    return ret.toString ();
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // get all threads and sort them by thread ID
    final ICommonsMap <Thread, StackTraceElement []> aThreads = CollectionHelper.getSortedByKey (Thread.getAllStackTraces (),
                                                                                                 Comparator.comparing (Thread::getId));

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.MSG_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     Integer.valueOf (aThreads.size ()),
                                                                                                     PDTToString.getAsString (PDTFactory.getCurrentLocalDateTime (),
                                                                                                                              aDisplayLocale))));
    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                 aDisplayLocale)
                                                                                                .setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_GROUP.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_PRIORITY.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                       aDisplayLocale),
                                        new DTCol (EText.MSG_STATE.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_STACKTRACE.getDisplayText (aDisplayLocale)).setOrderable (false)).setID (getID ());
    // For all system properties
    for (final Map.Entry <Thread, StackTraceElement []> aEntry : aThreads.entrySet ())
    {
      final HCRow aRow = aTable.addBodyRow ();

      // Thread ID (long)
      final Thread aThread = aEntry.getKey ();
      aRow.addCell (Long.toString (aThread.getId ()));

      // Thread group
      final ThreadGroup aThreadGroup = aThread.getThreadGroup ();
      aRow.addCell (_getThreadGroupName (aThreadGroup));

      // Thread name
      aRow.addCell (aThread.getName ());

      // Priority (int)
      aRow.addCell (Integer.toString (aThread.getPriority ()));

      // State
      aRow.addCell (String.valueOf (aThread.getState ()));

      // Stack trace
      aRow.addCell (HCExtHelper.nl2brList (StackTraceHelper.getStackAsString (aEntry.getValue ())));
    }
    aNodeList.addChild (aTable);

    // Use a client side DataTables since this page is mostly called in
    // situations where the machine is very busy
    final DataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDT.setLengthMenu (DataTablesLengthMenu.INSTANCE_ALL);
    aDT.setAjaxBuilder (null);
    aDT.setNoTextLoadingURL ();
    aNodeList.addChild (aDT);
  }
}
