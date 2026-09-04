/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.LongSupplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.state.EValidity;
import com.helger.base.state.IValidityIndicator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.execcontext.LayoutExecutionContext;
import com.helger.photon.core.execcontext.SimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.ITelemetryCounter;
import com.helger.telemetry.ITelemetryGauge;
import com.helger.telemetry.ITelemetryHistogram;
import com.helger.telemetry.ITelemetryMeterSPI;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.ITelemetryTracerSPI;
import com.helger.telemetry.ITelemetryUpDownCounter;
import com.helger.telemetry.Telemetry;
import com.helger.telemetry.TelemetryAttributes;
import com.helger.telemetry.TelemetryMetrics;
import com.helger.text.ReadOnlyMultilingualText;
import com.helger.url.SimpleURL;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;

/**
 * Test class for the ph-telemetry integration of {@link AbstractWebPage}.
 *
 * @author Philip Helger
 */
public final class WebPageTelemetryTest
{
  private static final String PAGE_ID = "unit-test-page";
  private static final Locale LOCALE = Locale.GERMAN;

  /** A span that only records what was set on it. */
  private static final class CapturingSpan implements ITelemetrySpan
  {
    private final String m_sName;
    private final ETelemetrySpanKind m_eKind;
    private final ICommonsOrderedMap <String, Object> m_aAttrs = new CommonsLinkedHashMap <> ();
    private final ICommonsList <String> m_aEvents = new CommonsArrayList <> ();
    private String m_sStatus;
    private Throwable m_aException;
    private boolean m_bClosed;

    CapturingSpan (@NonNull final String sName, @NonNull final ETelemetrySpanKind eKind)
    {
      m_sName = sName;
      m_eKind = eKind;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, @Nullable final String sValue)
    {
      if (sValue != null)
        m_aAttrs.put (sKey, sValue);
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final boolean bValue)
    {
      m_aAttrs.put (sKey, Boolean.valueOf (bValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final long nValue)
    {
      m_aAttrs.put (sKey, Long.valueOf (nValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan setAttribute (@NonNull final String sKey, final double dValue)
    {
      m_aAttrs.put (sKey, Double.valueOf (dValue));
      return this;
    }

    @NonNull
    public ITelemetrySpan recordException (@NonNull final Throwable aException)
    {
      m_aException = aException;
      return this;
    }

    @NonNull
    public ITelemetrySpan addEvent (@NonNull final String sName, @NonNull final TelemetryAttributes aAttributes)
    {
      m_aEvents.add (sName);
      return this;
    }

    @NonNull
    public ITelemetrySpan setStatusOk ()
    {
      m_sStatus = "ok";
      return this;
    }

    @NonNull
    public ITelemetrySpan setStatusError (@Nullable final String sMessage)
    {
      m_sStatus = "error";
      return this;
    }

    public void close ()
    {
      m_bClosed = true;
    }
  }

  /** A tracer that remembers all started spans. */
  private static final class CapturingTracer implements ITelemetryTracerSPI
  {
    private final ICommonsList <CapturingSpan> m_aSpans = new CommonsArrayList <> ();

    @NonNull
    public ITelemetrySpan startSpan (@NonNull final String sName, @NonNull final ETelemetrySpanKind eKind)
    {
      final CapturingSpan ret = new CapturingSpan (sName, eKind);
      m_aSpans.add (ret);
      return ret;
    }
  }

  /** A single recorded measurement of an instrument. */
  private record Measurement (String sInstrument, double dValue, ICommonsOrderedMap <String, Object> aAttrs)
  {}

  /** A meter that remembers all recorded measurements. */
  private static final class CapturingMeter implements ITelemetryMeterSPI
  {
    private final ICommonsList <Measurement> m_aMeasurements = new CommonsArrayList <> (new CopyOnWriteArrayList <> ());

    @NonNull
    private static ICommonsOrderedMap <String, Object> _toMap (@NonNull final TelemetryAttributes aAttrs)
    {
      final ICommonsOrderedMap <String, Object> ret = new CommonsLinkedHashMap <> ();
      aAttrs.forEach (new TelemetryAttributes.IVisitor ()
      {
        public void onString (@NonNull final String sKey, @NonNull final String sValue)
        {
          ret.put (sKey, sValue);
        }

        public void onLong (@NonNull final String sKey, final long nValue)
        {
          ret.put (sKey, Long.valueOf (nValue));
        }

        public void onDouble (@NonNull final String sKey, final double dValue)
        {
          ret.put (sKey, Double.valueOf (dValue));
        }

        public void onBoolean (@NonNull final String sKey, final boolean bValue)
        {
          ret.put (sKey, Boolean.valueOf (bValue));
        }
      });
      return ret;
    }

    private void _record (@NonNull final String sName, final double dValue, @NonNull final TelemetryAttributes aAttrs)
    {
      m_aMeasurements.add (new Measurement (sName, dValue, _toMap (aAttrs)));
    }

    @NonNull
    public ITelemetryCounter createCounter (@NonNull final String sName,
                                           @Nullable final String sDescription,
                                           @Nullable final String sUnit)
    {
      return (nValue, aAttrs) -> _record (sName, nValue, aAttrs);
    }

    @NonNull
    public ITelemetryUpDownCounter createUpDownCounter (@NonNull final String sName,
                                                        @Nullable final String sDescription,
                                                        @Nullable final String sUnit)
    {
      return (nValue, aAttrs) -> _record (sName, nValue, aAttrs);
    }

    @NonNull
    public ITelemetryHistogram createHistogram (@NonNull final String sName,
                                                @Nullable final String sDescription,
                                                @Nullable final String sUnit)
    {
      return (dValue, aAttrs) -> _record (sName, dValue, aAttrs);
    }

    @NonNull
    public ITelemetryGauge createGauge (@NonNull final String sName,
                                        @Nullable final String sDescription,
                                        @Nullable final String sUnit,
                                        @NonNull final LongSupplier aSupplier)
    {
      return () -> {};
    }
  }

  /** A page that only remembers which branch of getContent was taken. */
  private static final class MockWebPage extends AbstractWebPage <WebPageExecutionContext>
  {
    private final boolean m_bValid;
    private final boolean m_bRedirect;
    private boolean m_bFilled;
    private boolean m_bInvalidCalled;

    MockWebPage (final boolean bValid, final boolean bRedirect)
    {
      super (PAGE_ID, new ReadOnlyMultilingualText (Locale.ENGLISH, "Unit test page"), null);
      m_bValid = bValid;
      m_bRedirect = bRedirect;
    }

    @Override
    @NonNull
    protected IValidityIndicator isValidToDisplayPage (@NonNull final WebPageExecutionContext aWPEC)
    {
      return EValidity.valueOf (m_bValid);
    }

    @Override
    protected void fillContent (@NonNull final WebPageExecutionContext aWPEC)
    {
      m_bFilled = true;
      if (m_bRedirect)
        throw new ForcedRedirectException (PAGE_ID, new SimpleURL ("/target"), null);
    }

    @Override
    protected void onInvalidToDisplayPage (@NonNull final WebPageExecutionContext aWPEC)
    {
      m_bInvalidCalled = true;
    }
  }

  private static final CapturingTracer TRACER = new CapturingTracer ();
  private static final CapturingMeter METER = new CapturingMeter ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before WebPageMetrics is class-loaded, because the instruments are resolved once
    // in its static initializer
    Telemetry.install (TRACER);
    TelemetryMetrics.install (METER);
  }

  @AfterClass
  public static void uninstallTelemetry ()
  {
    Telemetry.install (null);
    TelemetryMetrics.install (null);
  }

  @Before
  public void clearRecordings ()
  {
    TRACER.m_aSpans.clear ();
    METER.m_aMeasurements.clear ();
  }

  @Nullable
  private static Measurement _findMeasurement (@NonNull final String sInstrument)
  {
    return METER.m_aMeasurements.findFirst (x -> x.sInstrument ().equals (sInstrument));
  }

  @NonNull
  private static WebPageExecutionContext _createWPEC (@NonNull final MockWebPage aPage)
  {
    final MenuTree aMenuTree = new MenuTree ();
    final IMenuItemPage aMenuItem = aMenuTree.createRootItem (aPage);
    final SimpleWebExecutionContext aSWEC = new SimpleWebExecutionContext (WebScopeManager.getRequestScope (),
                                                                          LOCALE,
                                                                          aMenuTree,
                                                                          null);
    return new WebPageExecutionContext (new LayoutExecutionContext (aSWEC, aMenuItem), aPage);
  }

  @Test
  public void testDisplayedPage ()
  {
    final MockWebPage aPage = new MockWebPage (true, false);
    aPage.getContent (_createWPEC (aPage));
    assertTrue (aPage.m_bFilled);
    assertFalse (aPage.m_bInvalidCalled);

    assertEquals (1, TRACER.m_aSpans.size ());
    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CUICoreTelemetry.SPAN_PAGE_CONTENT, aSpan.m_sName);
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.m_eKind);
    assertTrue (aSpan.m_bClosed);
    assertEquals (PAGE_ID, aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (LOCALE.toString (), aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_LOCALE));
    assertEquals (Boolean.TRUE, aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    final Measurement aRendered = _findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (1, (int) aRendered.dValue ());
    assertEquals (PAGE_ID, aRendered.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (Boolean.TRUE, aRendered.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    // The locale is a span attribute only
    assertNull (aRendered.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_LOCALE));
    assertEquals (2, aRendered.aAttrs ().size ());

    final Measurement aDuration = _findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION);
    assertNotNull (aDuration);
    assertEquals (PAGE_ID, aDuration.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (1, aDuration.aAttrs ().size ());
  }

  @Test
  public void testNotDisplayedPage ()
  {
    final MockWebPage aPage = new MockWebPage (false, false);
    aPage.getContent (_createWPEC (aPage));
    assertFalse (aPage.m_bFilled);
    assertTrue (aPage.m_bInvalidCalled);

    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.m_bClosed);
    assertEquals (PAGE_ID, aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (Boolean.FALSE, aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    // A page that is never displayed is counted as well
    final Measurement aRendered = _findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (1, (int) aRendered.dValue ());
    assertEquals (Boolean.FALSE, aRendered.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    assertNotNull (_findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION));
  }

  @Test
  public void testForcedRedirect ()
  {
    final MockWebPage aPage = new MockWebPage (true, true);
    try
    {
      aPage.getContent (_createWPEC (aPage));
      fail ();
    }
    catch (final ForcedRedirectException ex)
    {
      // Expected - Post-Redirect-Get
    }

    final CapturingSpan aSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.m_bClosed);
    // A Post-Redirect-Get is a regular control flow and must not be marked as an error
    assertEquals ("ok", aSpan.m_sStatus);
    assertNull (aSpan.m_aException);
    assertTrue (aSpan.m_aEvents.contains (CUICoreTelemetry.EVENT_FORCED_REDIRECT));
    assertEquals (Boolean.TRUE, aSpan.m_aAttrs.get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    // The page is counted as displayed, because the content creation started
    final Measurement aRendered = _findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (Boolean.TRUE, aRendered.aAttrs ().get (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    assertNotNull (_findMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION));
  }
}
