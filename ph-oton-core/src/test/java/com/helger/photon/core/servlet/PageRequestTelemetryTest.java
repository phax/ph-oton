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
package com.helger.photon.core.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.photon.app.CAppTelemetry;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.CCoreTelemetry;
import com.helger.servlet.response.UnifiedResponse;
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
import com.helger.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;

/**
 * Test class for the ph-telemetry integration of {@link AbstractApplicationXServletHandler} and
 * {@link com.helger.photon.app.html.PhotonHTMLHelper}.
 *
 * @author Philip Helger
 */
public final class PageRequestTelemetryTest
{
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

  /** What the mock handler should do. */
  private enum EMockAction
  {
    SUCCESS,
    FAIL,
    REDIRECT
  }

  /** A handler that renders a trivial page. */
  private static final class MockHandler extends AbstractApplicationXServletHandler
  {
    private final EMockAction m_eAction;

    MockHandler (@NonNull final EMockAction eAction)
    {
      m_eAction = eAction;
    }

    @Override
    @NonNull
    protected IHTMLProvider createHTMLProvider (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
    {
      if (m_eAction == EMockAction.FAIL)
        throw new IllegalStateException ("oops");

      if (m_eAction == EMockAction.REDIRECT)
        throw new ForcedRedirectException ("source", new SimpleURL ("/target"), null);

      return x -> {
        final HCHtml aHtml = new HCHtml ();
        aHtml.body ().addChild (new HCH1 ().addChild ("Test H1"));
        return aHtml;
      };
    }
  }

  private static final CapturingTracer TRACER = new CapturingTracer ();
  private static final CapturingMeter METER = new CapturingMeter ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before PageRequestMetrics and HTMLResponseMetrics are class-loaded, because the
    // instruments are resolved once in their static initializers
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
  private static UnifiedResponse _createResponse ()
  {
    // The mock request has no protocol, so createSimple (...) cannot be used
    return new UnifiedResponse (EHttpVersion.HTTP_11, EHttpMethod.GET, WebScopeManager.getRequestScope ().getRequest ());
  }

  @Test
  public void testSuccessfulPageRequest () throws Exception
  {
    new MockHandler (EMockAction.SUCCESS).handleRequest (WebScopeManager.getRequestScope (), _createResponse ());

    // The outer page request span and the nested HTML response span
    assertEquals (2, TRACER.m_aSpans.size ());

    final CapturingSpan aPageSpan = TRACER.m_aSpans.getAtIndex (0);
    assertNotNull (aPageSpan);
    assertEquals (CCoreTelemetry.SPAN_PAGE_REQUEST, aPageSpan.m_sName);
    assertEquals (ETelemetrySpanKind.SERVER, aPageSpan.m_eKind);
    assertTrue (aPageSpan.m_bClosed);
    assertEquals ("ok", aPageSpan.m_sStatus);
    assertEquals (Boolean.TRUE, aPageSpan.m_aAttrs.get (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    final CapturingSpan aHTMLSpan = TRACER.m_aSpans.getAtIndex (1);
    assertNotNull (aHTMLSpan);
    assertEquals (CAppTelemetry.SPAN_HTML_RESPONSE, aHTMLSpan.m_sName);
    assertEquals (ETelemetrySpanKind.INTERNAL, aHTMLSpan.m_eKind);
    assertTrue (aHTMLSpan.m_bClosed);
    assertEquals ("ok", aHTMLSpan.m_sStatus);
    assertEquals (Boolean.TRUE, aHTMLSpan.m_aAttrs.get (CAppTelemetry.ATTR_HTML_SUCCESS));
    assertNotNull (aHTMLSpan.m_aAttrs.get (CAppTelemetry.ATTR_HTML_MIME_TYPE));

    final Measurement aRequests = _findMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (1, (int) aRequests.dValue ());
    assertEquals (Boolean.TRUE, aRequests.aAttrs ().get (CCoreTelemetry.ATTR_PAGE_SUCCESS));
    assertEquals (1, aRequests.aAttrs ().size ());

    assertNotNull (_findMeasurement (CCoreTelemetry.METRIC_PAGE_DURATION));
    assertNotNull (_findMeasurement (CAppTelemetry.METRIC_HTML_DURATION));
  }

  @Test
  public void testFailedPageRequest ()
  {
    try
    {
      new MockHandler (EMockAction.FAIL).handleRequest (WebScopeManager.getRequestScope (), _createResponse ());
      fail ();
    }
    catch (final Exception ex)
    {
      // Expected
      assertEquals ("oops", ex.getMessage ());
    }

    // Only the outer span was started
    assertEquals (1, TRACER.m_aSpans.size ());
    final CapturingSpan aPageSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aPageSpan);
    assertTrue (aPageSpan.m_bClosed);
    assertEquals ("error", aPageSpan.m_sStatus);
    assertNotNull (aPageSpan.m_aException);
    assertEquals (Boolean.FALSE, aPageSpan.m_aAttrs.get (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    // The counter counts failed requests as well
    final Measurement aRequests = _findMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (Boolean.FALSE, aRequests.aAttrs ().get (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    // And the duration is recorded for failures as well
    final Measurement aDuration = _findMeasurement (CCoreTelemetry.METRIC_PAGE_DURATION);
    assertNotNull (aDuration);
    assertEquals (Boolean.FALSE, aDuration.aAttrs ().get (CCoreTelemetry.ATTR_PAGE_SUCCESS));
  }

  @Test
  public void testForcedRedirect ()
  {
    try
    {
      new MockHandler (EMockAction.REDIRECT).handleRequest (WebScopeManager.getRequestScope (), _createResponse ());
      fail ();
    }
    catch (final ForcedRedirectException ex)
    {
      // Expected - Post-Redirect-Get
    }
    catch (final Exception ex)
    {
      fail ();
    }

    final CapturingSpan aPageSpan = TRACER.m_aSpans.getFirstOrNull ();
    assertNotNull (aPageSpan);
    assertTrue (aPageSpan.m_bClosed);
    // A Post-Redirect-Get is a regular control flow and must not be marked as an error
    assertEquals ("ok", aPageSpan.m_sStatus);
    assertNull (aPageSpan.m_aException);
    assertEquals (Boolean.TRUE, aPageSpan.m_aAttrs.get (CCoreTelemetry.ATTR_PAGE_SUCCESS));
    assertTrue (aPageSpan.m_aEvents.contains (CCoreTelemetry.EVENT_FORCED_REDIRECT));

    final Measurement aRequests = _findMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (Boolean.TRUE, aRequests.aAttrs ().get (CCoreTelemetry.ATTR_PAGE_SUCCESS));
  }
}
