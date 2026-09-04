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
import static org.junit.Assert.assertTrue;

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

import com.helger.base.state.EContinue;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.execcontext.LayoutExecutionContext;
import com.helger.photon.core.execcontext.SimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.photon.uicore.css.CPageParam;
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
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Test class for the ph-telemetry integration of {@link WebPageCSRFHandler}.
 *
 * @author Philip Helger
 */
public final class CSRFTelemetryTest
{
  private static final String PAGE_ID = "unit-test-csrf-page";

  /** A span that only records what was set on it. */
  private static final class CapturingSpan implements ITelemetrySpan
  {
    private final String m_sName;
    private final ETelemetrySpanKind m_eKind;
    private final ICommonsOrderedMap <String, Object> m_aAttrs = new CommonsLinkedHashMap <> ();
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

  /** A page that does nothing - it is only needed to build a web page execution context. */
  private static final class MockWebPage extends AbstractWebPage <WebPageExecutionContext>
  {
    MockWebPage ()
    {
      super (PAGE_ID, new ReadOnlyMultilingualText (Locale.ENGLISH, "Unit test page"), null);
    }

    @Override
    protected void fillContent (@NonNull final WebPageExecutionContext aWPEC)
    {}
  }

  private static final CapturingTracer TRACER = new CapturingTracer ();
  private static final CapturingMeter METER = new CapturingMeter ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before CSRFMetrics is class-loaded, because the instruments are resolved once in
    // its static initializer
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
  private static WebPageExecutionContext _createWPEC ()
  {
    final MockWebPage aPage = new MockWebPage ();
    final MenuTree aMenuTree = new MenuTree ();
    final IMenuItemPage aMenuItem = aMenuTree.createRootItem (aPage);
    final SimpleWebExecutionContext aSWEC = new SimpleWebExecutionContext (WebScopeManager.getRequestScope (),
                                                                          Locale.GERMAN,
                                                                          aMenuTree,
                                                                          null);
    return new WebPageExecutionContext (new LayoutExecutionContext (aSWEC, aMenuItem), aPage);
  }

  @Test
  public void testValidNonce ()
  {
    final WebPageExecutionContext aWPEC = _createWPEC ();
    // Provide the expected nonce
    aWPEC.params ().putIn (CPageParam.FIELD_NONCE, CSRFSessionManager.getInstance ().getNonce ());

    assertTrue (WebPageCSRFHandler.INSTANCE.checkCSRFNonce (aWPEC).isContinue ());

    final Measurement aChecks = _findMeasurement (CUICoreTelemetry.METRIC_CSRF_CHECKS);
    assertNotNull (aChecks);
    assertEquals (1, (int) aChecks.dValue ());
    assertEquals (PAGE_ID, aChecks.aAttrs ().get (CUICoreTelemetry.ATTR_CSRF_PAGE_ID));
    assertEquals (Boolean.TRUE, aChecks.aAttrs ().get (CUICoreTelemetry.ATTR_CSRF_VALID));
    // Only the bounded page ID and the validity - never the nonce itself
    assertEquals (2, aChecks.aAttrs ().size ());
  }

  @Test
  public void testInvalidNonce ()
  {
    final WebPageExecutionContext aWPEC = _createWPEC ();
    final String sWrongNonce = "definitely-not-the-expected-nonce";
    aWPEC.params ().putIn (CPageParam.FIELD_NONCE, sWrongNonce);

    assertEquals (EContinue.BREAK, WebPageCSRFHandler.INSTANCE.checkCSRFNonce (aWPEC));

    final Measurement aChecks = _findMeasurement (CUICoreTelemetry.METRIC_CSRF_CHECKS);
    assertNotNull (aChecks);
    assertEquals (1, (int) aChecks.dValue ());
    assertEquals (PAGE_ID, aChecks.aAttrs ().get (CUICoreTelemetry.ATTR_CSRF_PAGE_ID));
    assertEquals (Boolean.FALSE, aChecks.aAttrs ().get (CUICoreTelemetry.ATTR_CSRF_VALID));
    // The nonce value is a security token and must not show up anywhere
    assertFalse (aChecks.aAttrs ().containsValue (sWrongNonce));
    assertEquals (2, aChecks.aAttrs ().size ());
  }
}
