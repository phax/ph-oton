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
package com.helger.photon.bootstrap4.pages.monitoring;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.CGlobal;
import com.helger.base.compare.ESortOrder;
import com.helger.base.numeric.BigHelper;
import com.helger.datetime.helper.PDTFactory;
import com.helger.datetime.web.PDTWebDateHelper;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.ajax.decl.AjaxFunctionDeclaration;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.nav.BootstrapTabBox;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.statistics.api.IStatisticsHandlerCache;
import com.helger.statistics.api.IStatisticsHandlerCounter;
import com.helger.statistics.api.IStatisticsHandlerKeyedCounter;
import com.helger.statistics.api.IStatisticsHandlerKeyedSize;
import com.helger.statistics.api.IStatisticsHandlerKeyedTimer;
import com.helger.statistics.api.IStatisticsHandlerSize;
import com.helger.statistics.api.IStatisticsHandlerTimer;
import com.helger.statistics.visit.IStatisticsVisitorCallback;
import com.helger.statistics.visit.StatisticsVisitor;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.util.statistics.StatisticsExporter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with all currently available in memory statistics.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringStatistics <WPECTYPE extends IWebPageExecutionContext> extends
                                          AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
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
    MSG_CACHE_HIT_PERC ("Cache hit %", "Cache hit %"),
    MSG_CACHE_MISS ("Cache miss", "Cache miss"),
    MSG_CACHE_MISS_PERC ("Cache miss %", "Cache miss %");

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

  private static final Logger LOGGER = LoggerFactory.getLogger (BasePageMonitoringStatistics.class);

  private static final AjaxFunctionDeclaration AJAX_SAVE_STATS = addAjax ( (aRequestScope, aAjaxResponse) -> {
    LOGGER.info ("Downloading ph-oton statistics");
    final IMicroDocument aDoc = StatisticsExporter.getAsXMLDocument ();
    aDoc.getDocumentElement ().setAttribute ("location", "user-interface");
    aDoc.getDocumentElement ()
        .setAttribute ("datetime", PDTWebDateHelper.getAsStringXSD (PDTFactory.getCurrentLocalDateTime ()));
    aAjaxResponse.xml (aDoc).setContentDispositionFilename ("ph-oton-statistics.xml").disableCaching ();
  });

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
                                       @Nonnull final IMultilingualText aName,
                                       @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aToolbar.addButton (EPhotonCoreText.BUTTON_DOWNLOAD.getDisplayText (aDisplayLocale),
                        AJAX_SAVE_STATS.getInvocationURL (aRequestScope),
                        EDefaultIcon.SAVE);
    aNodeList.addChild (aToolbar);

    // Table for timer
    final HCTable aTableTimer = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                     1),
                                             new DTCol (EText.MSG_KEY.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_INVOCATION.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                              aDisplayLocale),
                                             new DTCol (EText.MSG_TIMER_MIN.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale),
                                             new DTCol (EText.MSG_TIMER_MAX.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale),
                                             new DTCol (EText.MSG_TIMER_AVG.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale),
                                             new DTCol (EText.MSG_TIMER_SUM.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale)
                                                                                                            .setInitialSorting (ESortOrder.DESCENDING)).setID (getID () +
                                                                                                                                                               "timer");

    // Table for size
    final HCTable aTableSize = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                    1),
                                            new DTCol (EText.MSG_KEY.getDisplayText (aDisplayLocale)),
                                            new DTCol (EText.MSG_INVOCATION.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale)
                                                                                                            .setInitialSorting (ESortOrder.ASCENDING),
                                            new DTCol (EText.MSG_MIN.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                      aDisplayLocale),
                                            new DTCol (EText.MSG_MAX.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                      aDisplayLocale),
                                            new DTCol (EText.MSG_AVG.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                      aDisplayLocale),
                                            new DTCol (EText.MSG_SUM.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                      aDisplayLocale)).setID (getID () +
                                                                                                                                              "size");

    // Table for counter
    final HCTable aTableCounter = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                       1),
                                               new DTCol (EText.MSG_KEY.getDisplayText (aDisplayLocale)),
                                               new DTCol (EText.MSG_INVOCATION.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                                aDisplayLocale)
                                                                                                               .setInitialSorting (ESortOrder.ASCENDING),
                                               new DTCol (EText.MSG_COUNT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                           aDisplayLocale)).setID (getID () +
                                                                                                                                                   "counter");

    // Table for cache
    final HCTable aTableCache = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)),
                                             new DTCol (EText.MSG_INVOCATION.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                              aDisplayLocale),
                                             new DTCol (EText.MSG_CACHE_HIT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                             aDisplayLocale),
                                             new DTCol (EText.MSG_CACHE_HIT_PERC.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DOUBLE,
                                                                                                                                  aDisplayLocale),
                                             new DTCol (EText.MSG_CACHE_MISS.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.INT,
                                                                                                                              aDisplayLocale)
                                                                                                             .setInitialSorting (ESortOrder.DESCENDING),
                                             new DTCol (EText.MSG_CACHE_MISS_PERC.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DOUBLE,
                                                                                                                                   aDisplayLocale)).setID (getID () +
                                                                                                                                                           "cache");

    // Third party modules
    StatisticsVisitor.visitStatistics (new IStatisticsVisitorCallback ()
    {
      @Override
      public void onTimer (@Nonnull final String sName, @Nonnull final IStatisticsHandlerTimer aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableTimer.addBodyRow ()
                     .addCells (sName,
                                "",
                                Integer.toString (aHandler.getInvocationCount ()),
                                Long.toString (aHandler.getMin ()),
                                Long.toString (aHandler.getMax ()),
                                Long.toString (aHandler.getAverage ()),
                                aHandler.getSum ().toString ());
      }

      @Override
      public void onSize (@Nonnull final String sName, @Nonnull final IStatisticsHandlerSize aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableSize.addBodyRow ()
                    .addCells (sName,
                               "",
                               Integer.toString (aHandler.getInvocationCount ()),
                               Long.toString (aHandler.getMin ()),
                               Long.toString (aHandler.getMax ()),
                               Long.toString (aHandler.getAverage ()),
                               aHandler.getSum ().toString ());
      }

      @Override
      public void onCounter (@Nonnull final String sName, @Nonnull final IStatisticsHandlerCounter aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
          aTableCounter.addBodyRow ()
                       .addCells (sName,
                                  "",
                                  Integer.toString (aHandler.getInvocationCount ()),
                                  Long.toString (aHandler.getCount ()));
      }

      @Override
      public void onCache (@Nonnull final String sName, @Nonnull final IStatisticsHandlerCache aHandler)
      {
        if (aHandler.getInvocationCount () > 0)
        {
          final int nTotal = aHandler.getInvocationCount ();
          final int nHits = aHandler.getHits ();
          final int nMisses = aHandler.getMisses ();
          final int nScale = 4;
          final BigDecimal aHitsPerc = BigHelper.getDividedBigDecimal (nHits, nTotal, nScale, RoundingMode.HALF_UP)
                                                .multiply (CGlobal.BIGDEC_100)
                                                .setScale (nScale - 2);
          final BigDecimal aMissPerc = CGlobal.BIGDEC_100.subtract (aHitsPerc);
          aTableCache.addBodyRow ()
                     .addCells (sName,
                                Integer.toString (nTotal),
                                Integer.toString (nHits),
                                aHitsPerc.toPlainString () + "%",
                                Integer.toString (nMisses),
                                aMissPerc.toPlainString () + "%");
        }
      }

      @Override
      public void onKeyedTimer (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedTimer aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableTimer.addBodyRow ()
                       .addCells (sName,
                                  sKey,
                                  Integer.toString (nInvocationCount),
                                  Long.toString (aHandler.getMin (sKey)),
                                  Long.toString (aHandler.getMax (sKey)),
                                  Long.toString (aHandler.getAverage (sKey)),
                                  aHandler.getSum (sKey).toString ());
        }
      }

      @Override
      public void onKeyedSize (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedSize aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableSize.addBodyRow ()
                      .addCells (sName,
                                 sKey,
                                 Integer.toString (nInvocationCount),
                                 Long.toString (aHandler.getMin (sKey)),
                                 Long.toString (aHandler.getMax (sKey)),
                                 Long.toString (aHandler.getAverage (sKey)),
                                 aHandler.getSum (sKey).toString ());
        }
      }

      @Override
      public void onKeyedCounter (@Nonnull final String sName, @Nonnull final IStatisticsHandlerKeyedCounter aHandler)
      {
        for (final String sKey : aHandler.getAllKeys ())
        {
          final int nInvocationCount = aHandler.getInvocationCount (sKey);
          if (nInvocationCount > 0)
            aTableCounter.addBodyRow ()
                         .addCells (sName,
                                    sKey,
                                    Integer.toString (nInvocationCount),
                                    Long.toString (aHandler.getCount (sKey)));
        }
      }
    });

    // Build the final UI as a tabbox
    final BootstrapTabBox aTabBox = new BootstrapTabBox ();
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableTimer);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableTimer);
      aNL.addChild (aDataTables);

      aTabBox.addTab ("timer", EText.MSG_TAB_TIMER.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableSize);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableSize);
      aNL.addChild (aDataTables);

      aTabBox.addTab ("size", EText.MSG_TAB_SIZE.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableCounter);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableCounter);
      aNL.addChild (aDataTables);

      aTabBox.addTab ("counter", EText.MSG_TAB_COUNTER.getDisplayText (aDisplayLocale), aNL);
    }
    {
      final HCNodeList aNL = new HCNodeList ();
      aNL.addChild (aTableCache);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTableCache);
      aNL.addChild (aDataTables);

      aTabBox.addTab ("cache", EText.MSG_TAB_CACHE.getDisplayText (aDisplayLocale), aNL);
    }

    aNodeList.addChild (aTabBox);
  }
}
