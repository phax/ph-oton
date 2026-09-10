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

import org.jspecify.annotations.NonNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

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
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;
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

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before PageRequestMetrics and HTMLResponseMetrics are class-loaded, because the
    // instruments are resolved once in their static initializers
    TELEMETRY.install ();
  }

  @AfterClass
  public static void uninstallTelemetry ()
  {
    CapturingTelemetry.uninstall ();
  }

  @Before
  public void clearRecordings ()
  {
    TELEMETRY.reset ();
  }

  @NonNull
  private static UnifiedResponse _createResponse ()
  {
    // The mock request has no protocol, so createSimple (...) cannot be used
    return new UnifiedResponse (EHttpVersion.HTTP_11,
                                EHttpMethod.GET,
                                WebScopeManager.getRequestScope ().getRequest ());
  }

  @Test
  public void testSuccessfulPageRequest () throws Exception
  {
    new MockHandler (EMockAction.SUCCESS).handleRequest (WebScopeManager.getRequestScope (), _createResponse ());

    // The outer page request span and the nested HTML response span
    assertEquals (2, TELEMETRY.getSpanCount ());

    final CapturedSpan aPageSpan = TELEMETRY.getSpans ().getAtIndex (0);
    assertNotNull (aPageSpan);
    assertEquals (CCoreTelemetry.SPAN_PAGE_REQUEST, aPageSpan.getName ());
    assertEquals (ETelemetrySpanKind.SERVER, aPageSpan.getKind ());
    assertTrue (aPageSpan.isClosed ());
    assertTrue (aPageSpan.isStatusOk ());
    assertEquals (Boolean.TRUE, aPageSpan.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    final CapturedSpan aHTMLSpan = TELEMETRY.getSpans ().getAtIndex (1);
    assertNotNull (aHTMLSpan);
    assertEquals (CAppTelemetry.SPAN_HTML_RESPONSE, aHTMLSpan.getName ());
    assertEquals (ETelemetrySpanKind.INTERNAL, aHTMLSpan.getKind ());
    assertTrue (aHTMLSpan.isClosed ());
    assertTrue (aHTMLSpan.isStatusOk ());
    assertEquals (Boolean.TRUE, aHTMLSpan.getAttribute (CAppTelemetry.ATTR_HTML_SUCCESS));
    assertNotNull (aHTMLSpan.getAttribute (CAppTelemetry.ATTR_HTML_MIME_TYPE));

    final CapturedMeasurement aRequests = TELEMETRY.getFirstMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (1, (int) aRequests.getValue ());
    assertEquals (Boolean.TRUE, aRequests.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));
    assertEquals (1, aRequests.getAttributes ().size ());

    assertNotNull (TELEMETRY.getFirstMeasurement (CCoreTelemetry.METRIC_PAGE_DURATION));
    assertNotNull (TELEMETRY.getFirstMeasurement (CAppTelemetry.METRIC_HTML_DURATION));
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
    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aPageSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aPageSpan);
    assertTrue (aPageSpan.isClosed ());
    assertTrue (aPageSpan.isStatusError ());
    assertNotNull (aPageSpan.getRecordedException ());
    assertEquals (Boolean.FALSE, aPageSpan.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    // The counter counts failed requests as well
    final CapturedMeasurement aRequests = TELEMETRY.getFirstMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (Boolean.FALSE, aRequests.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));

    // And the duration is recorded for failures as well
    final CapturedMeasurement aDuration = TELEMETRY.getFirstMeasurement (CCoreTelemetry.METRIC_PAGE_DURATION);
    assertNotNull (aDuration);
    assertEquals (Boolean.FALSE, aDuration.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));
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

    final CapturedSpan aPageSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aPageSpan);
    assertTrue (aPageSpan.isClosed ());
    // A Post-Redirect-Get is a regular control flow and must not be marked as an error
    assertTrue (aPageSpan.isStatusOk ());
    assertNull (aPageSpan.getRecordedException ());
    assertEquals (Boolean.TRUE, aPageSpan.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));
    assertNotNull (aPageSpan.getFirstEvent (CCoreTelemetry.EVENT_FORCED_REDIRECT));

    final CapturedMeasurement aRequests = TELEMETRY.getFirstMeasurement (CCoreTelemetry.METRIC_PAGE_REQUESTS);
    assertNotNull (aRequests);
    assertEquals (Boolean.TRUE, aRequests.getAttribute (CCoreTelemetry.ATTR_PAGE_SUCCESS));
  }
}
