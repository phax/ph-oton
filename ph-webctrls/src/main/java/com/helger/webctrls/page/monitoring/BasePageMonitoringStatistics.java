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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.stats.IStatisticsHandlerCache;
import com.helger.commons.stats.IStatisticsHandlerCounter;
import com.helger.commons.stats.IStatisticsHandlerKeyedCounter;
import com.helger.commons.stats.IStatisticsHandlerKeyedSize;
import com.helger.commons.stats.IStatisticsHandlerKeyedTimer;
import com.helger.commons.stats.IStatisticsHandlerSize;
import com.helger.commons.stats.IStatisticsHandlerTimer;
import com.helger.commons.stats.visit.IStatisticsVisitor;
import com.helger.commons.stats.visit.StatisticsWalker;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webctrls.custom.EDefaultIcon;
import com.helger.webctrls.custom.tabbox.ITabBox;
import com.helger.webctrls.custom.toolbar.IButtonToolbar;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.datatables.comparator.ComparatorDTInteger;
import com.helger.webctrls.page.AbstractWebPageExt;
import com.helger.webctrls.page.EWebPageText;

/**
 * Page with all currently available in memory statistics.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringStatistics <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_TAB_TIMER ("Zeiten", "Timer"),
    MSG_TAB_SIZE ("Größe", "Size"),
    MSG_TAB_COUNTER ("Zähler", "Counter"),
    MSG_TAB_CACHE ("Cache", "Cache"),
    MSG_NAME ("Name", "Name"),
    MSG_KEY ("Schlüssel", "Key"),
    MSG_INVOCATION ("Aufrufe", "Invocations"),
    MSG_TIMER_MIN ("Minimum (ms)", "Minimum (ms)"),
    MSG_TIMER_MAX ("Maximum (ms)", "Maximum (ms)"),
    MSG_TIMER_AVG ("Durchschnitt (ms)", "Average (ms)"),
    MSG_TIMER_SUM ("Summe (ms)", "Sum (ms)"),
    MSG_MIN ("Minimum", "Minimum"),
    MSG_MAX ("Maximum", "Maximum"),
    MSG_AVG ("Durchschnitt", "Average"),
    MSG_SUM ("Summe", "Sum"),
    MSG_COUNT ("Anzahl", "Count"),
    MSG_CACHE_HIT ("Cache hit", "Cache hit"),
    MSG_CACHE_MISS ("Cache miss", "Cache miss");

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

  public BasePageMonitoringStatistics (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_STATISTICS.getAsMLT ());
  }

  public BasePageMonitoringStatistics (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageMonitoringStatistics (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageMonitoringStatistics (@Nonnull @Nonempty final String sID,
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

    // Refresh button
    final IButtonToolbar <?> aToolbar = getStyler ().createToolbar (aWPEC);
    aToolbar.addButton (EWebBasicsText.MSG_BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    // Table for timer
    final HCTable aTableTimer = new HCTable (HCCol.star (),
                                             HCCol.star (),
                                             HCCol.star (),
                                             HCCol.star (),
                                             HCCol.star (),
                                             HCCol.star (),
                                             HCCol.star ()).setID (getID () + "timer");
    aTableTimer.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                          EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                          EText.MSG_INVOCATION.getDisplayText (aDisplayLocale),
                                          EText.MSG_TIMER_MIN.getDisplayText (aDisplayLocale),
                                          EText.MSG_TIMER_MAX.getDisplayText (aDisplayLocale),
                                          EText.MSG_TIMER_AVG.getDisplayText (aDisplayLocale),
                                          EText.MSG_TIMER_SUM.getDisplayText (aDisplayLocale));

    // Table for size
    final HCTable aTableSize = new HCTable (HCCol.star (),
                                            HCCol.star (),
                                            HCCol.star (),
                                            HCCol.star (),
                                            HCCol.star (),
                                            HCCol.star (),
                                            HCCol.star ()).setID (getID () + "size");
    aTableSize.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                         EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                         EText.MSG_INVOCATION.getDisplayText (aDisplayLocale),
                                         EText.MSG_MIN.getDisplayText (aDisplayLocale),
                                         EText.MSG_MAX.getDisplayText (aDisplayLocale),
                                         EText.MSG_AVG.getDisplayText (aDisplayLocale),
                                         EText.MSG_SUM.getDisplayText (aDisplayLocale));

    // Table for counter
    final HCTable aTableCounter = new HCTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ()).setID (getID () +
                                                                                                                  "counter");
    aTableCounter.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                            EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                            EText.MSG_INVOCATION.getDisplayText (aDisplayLocale),
                                            EText.MSG_COUNT.getDisplayText (aDisplayLocale));

    // Table for cache
    final HCTable aTableCache = new HCTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ()).setID (getID () +
                                                                                                                "cache");
    aTableCache.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                          EText.MSG_INVOCATION.getDisplayText (aDisplayLocale),
                                          EText.MSG_CACHE_HIT.getDisplayText (aDisplayLocale),
                                          EText.MSG_CACHE_MISS.getDisplayText (aDisplayLocale));

    // Third party modules
    StatisticsWalker.walkStatistics (new IStatisticsVisitor ()
    {
      public void onTimer (@Nonnull final String sName, @Nonnull final IStatisticsHandlerTimer aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableTimer.addBodyRow ().addCells (sName,
                                              "",
                                              Integer.toString (aHandler.getInvocationCount ()),
                                              Long.toString (aHandler.getMin ()),
                                              Long.toString (aHandler.getMax ()),
                                              Long.toString (aHandler.getAverage ()),
                                              aHandler.getSum ().toString ());
      }

      public void onSize (@Nonnull final String sName, @Nonnull final IStatisticsHandlerSize aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableSize.addBodyRow ().addCells (sName,
                                             "",
                                             Integer.toString (aHandler.getInvocationCount ()),
                                             Long.toString (aHandler.getMin ()),
                                             Long.toString (aHandler.getMax ()),
                                             Long.toString (aHandler.getAverage ()),
                                             aHandler.getSum ().toString ());
      }

      public void onCounter (@Nonnull final String sName, @Nonnull final IStatisticsHandlerCounter aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableCounter.addBodyRow ().addCells (sName,
                                                "",
                                                Integer.toString (aHandler.getInvocationCount ()),
                                                Long.toString (aHandler.getCount ()));
      }

      public void onCache (@Nonnull final String sName, @Nonnull final IStatisticsHandlerCache aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableCache.addBodyRow ().addCells (sName,
                                              Integer.toString (aHandler.getInvocationCount ()),
                                              Integer.toString (aHandler.getHits ()),
                                              Integer.toString (aHandler.getMisses ()));
      }

      public void onKeyedTimer (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedTimer aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableTimer.addBodyRow ().addCells (sName,
                                                sKey,
                                                Integer.toString (nInvocationCount),
                                                Long.toString (aHandler.getMin (sKey)),
                                                Long.toString (aHandler.getMax (sKey)),
                                                Long.toString (aHandler.getAverage (sKey)),
                                                aHandler.getSum (sKey).toString ());
        }
      }

      public void onKeyedSize (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedSize aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableSize.addBodyRow ().addCells (sName,
                                               sKey,
                                               Integer.toString (nInvocationCount),
                                               Long.toString (aHandler.getMin (sKey)),
                                               Long.toString (aHandler.getMax (sKey)),
                                               Long.toString (aHandler.getAverage (sKey)),
                                               aHandler.getSum (sKey).toString ());
        }
      }

      public void onKeyedCounter (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedCounter aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableCounter.addBodyRow ().addCells (sName,
                                                  sKey,
                                                  Integer.toString (nInvocationCount),
                                                  Long.toString (aHandler.getCount (sKey)));
        }
      }
    });

    // Build the final UI as a tabbox
    final ITabBox <?> aTabBox = getStyler ().createTabBox (aWPEC);
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableTimer);

      final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableTimer);
      aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1);
      aDataTables.getOrCreateColumnOfTarget (2)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (3)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (4)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (5)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (6)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.setInitialSorting (6, ESortOrder.DESCENDING);
      aNL.addChild (aDataTables);

      aTabBox.addTab (EText.MSG_TAB_TIMER.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableSize);

      final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableSize);
      aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1);
      aDataTables.getOrCreateColumnOfTarget (2)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (3)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (4)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (5)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (6)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.setInitialSorting (2, ESortOrder.ASCENDING);
      aNL.addChild (aDataTables);

      aTabBox.addTab (EText.MSG_TAB_SIZE.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableCounter);

      final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableCounter);
      aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1);
      aDataTables.getOrCreateColumnOfTarget (2)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (3)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.setInitialSorting (2, ESortOrder.ASCENDING);
      aNL.addChild (aDataTables);

      aTabBox.addTab (EText.MSG_TAB_COUNTER.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableCache);

      final DataTables aDataTables = getStyler ().createDefaultDataTables (aWPEC, aTableCache);
      aDataTables.getOrCreateColumnOfTarget (1)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (2)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.getOrCreateColumnOfTarget (3)
                 .addClass (CSS_CLASS_RIGHT)
                 .setComparator (new ComparatorDTInteger (aDisplayLocale));
      aDataTables.setInitialSorting (3, ESortOrder.DESCENDING);
      aNL.addChild (aDataTables);

      aTabBox.addTab (EText.MSG_TAB_CACHE.getDisplayText (aDisplayLocale), aNL);
    }

    aNodeList.addChild (aTabBox);
  }
}
