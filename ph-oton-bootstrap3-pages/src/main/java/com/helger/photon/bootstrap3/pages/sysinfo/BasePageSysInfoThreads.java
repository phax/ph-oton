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

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.concurrent.ComparatorThreadID;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTInteger;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTLong;

/**
 * Page with all threads
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoThreads <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_ID ("ID", "ID"),
    MSG_GROUP ("Gruppe", "Group"),
    MSG_NAME ("Name", "Name"),
    MSG_PRIORITY ("Prio", "Prio"),
    MSG_STATE ("Status", "State"),
    MSG_STACKTRACE ("Stacktrace", "Stacktrace");

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
                                 @Nonnull final IReadonlyMultiLingualText aName,
                                 @Nullable final IReadonlyMultiLingualText aDescription)
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
    final Map <Thread, StackTraceElement []> aThreads = CollectionHelper.getSortedByKey (Thread.getAllStackTraces (),
                                                                                         new ComparatorThreadID ());

    aNodeList.addChild (createInPageHeader ("Total count=" +
                                            aThreads.size () +
                                            "; Prios (min/norm/max): " +
                                            Thread.MIN_PRIORITY +
                                            "/" +
                                            Thread.NORM_PRIORITY +
                                            "/" +
                                            Thread.MAX_PRIORITY +
                                            "; Zeitpunkt: " +
                                            PDTToString.getAsString (PDTFactory.getCurrentDateTime (), aDisplayLocale)));
    final IHCTable <?> aTable = new BootstrapTable (new HCCol (50),
                                                    new HCCol (100),
                                                    new HCCol (150),
                                                    new HCCol (55),
                                                    new HCCol (100),
                                                    HCCol.star ()).setID (getID ());

    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_GROUP.getDisplayText (aDisplayLocale),
                                     EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                     EText.MSG_PRIORITY.getDisplayText (aDisplayLocale),
                                     EText.MSG_STATE.getDisplayText (aDisplayLocale),
                                     EText.MSG_STACKTRACE.getDisplayText (aDisplayLocale));

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
      aRow.addCell (HCUtils.nl2brList (StackTraceHelper.getStackAsString (aEntry.getValue ())));
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (0)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTLong (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (3)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTInteger (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (5).setSortable (false);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
