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
package com.helger.photon.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.state.EHandled;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;
import com.helger.web.scope.mgr.WebScoped;

/**
 * Test class for the ph-telemetry integration of {@link APIInvoker}.
 *
 * @author Philip Helger
 */
public final class APITelemetryTest
{
  private static final String ROUTE = "/user/{id}";
  private static final String PATH = "/user/4711";

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before APIMetrics is class-loaded, because the instruments are resolved once in
    // its static initializer
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
  private static UnifiedResponse _createResponse (@NonNull final WebScoped aWebScoped)
  {
    // The mock request has no protocol, so createSimple (...) cannot be used
    return new UnifiedResponse (EHttpVersion.HTTP_11, EHttpMethod.GET, aWebScoped.getRequestScope ().getRequest ());
  }

  @NonNull
  private static InvokableAPIDescriptor _createInvokable (@NonNull final IAPIExecutor aExecutor,
                                                          @Nullable final IAPIExceptionMapper aExceptionMapper)
  {
    final APIDescriptor aDescriptor = new APIDescriptor (APIPath.get (ROUTE), aExecutor);
    aDescriptor.setExceptionMapper (aExceptionMapper);
    return new InvokableAPIDescriptor (aDescriptor, PATH, new CommonsLinkedHashMap <> ());
  }

  @Test
  public void testSuccessfulInvocation () throws Exception
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final InvokableAPIDescriptor aInvokable = _createInvokable ( (a, b, c, d, e) -> {}, null);
      new APIInvoker ().invoke (aInvokable,
                                aWebScoped.getRequestScope (),
                                _createResponse (aWebScoped));
    }

    // The span was started, filled and closed
    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CAPITelemetry.SPAN_INVOKE, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.SERVER, aSpan.getKind ());
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusOk ());
    assertEquals (ROUTE, aSpan.getAttribute (CAPITelemetry.ATTR_API_ROUTE));
    // The concrete path is a span attribute only
    assertEquals (PATH, aSpan.getAttribute (CAPITelemetry.ATTR_API_PATH));
    assertEquals ("GET", aSpan.getAttribute (CAPITelemetry.ATTR_API_METHOD));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));

    final CapturedMeasurement aInvocations = TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_INVOCATIONS);
    assertNotNull (aInvocations);
    assertEquals (1, (int) aInvocations.getValue ());
    assertEquals (ROUTE, aInvocations.getAttribute (CAPITelemetry.ATTR_API_ROUTE));
    assertEquals ("GET", aInvocations.getAttribute (CAPITelemetry.ATTR_API_METHOD));
    assertEquals (Boolean.TRUE, aInvocations.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));
    // The unbounded concrete path must never be a metric attribute
    assertNull (aInvocations.getAttribute (CAPITelemetry.ATTR_API_PATH));
    assertEquals (3, aInvocations.getAttributes ().size ());

    final CapturedMeasurement aDuration = TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_DURATION);
    assertNotNull (aDuration);
    assertNull (aDuration.getAttribute (CAPITelemetry.ATTR_API_PATH));
  }

  @Test
  public void testMappedException () throws Exception
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final InvokableAPIDescriptor aInvokable = _createInvokable ( (a, b, c, d, e) -> {
        throw new IllegalStateException ("oops");
      }, (a, b, c, d) -> EHandled.HANDLED);
      // The exception mapper handled it - so no exception is propagated
      new APIInvoker ().invoke (aInvokable,
                                aWebScoped.getRequestScope (),
                                _createResponse (aWebScoped));
    }

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isClosed ());
    // Even though invoke() returned normally, the span is marked as failed
    assertTrue (aSpan.isStatusError ());
    assertNotNull (aSpan.getRecordedException ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CAPITelemetry.ATTR_API_EXCEPTION_HANDLED));

    final CapturedMeasurement aInvocations = TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_INVOCATIONS);
    assertNotNull (aInvocations);
    assertEquals (Boolean.FALSE, aInvocations.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));
    assertNotNull (TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_DURATION));
  }

  @Test
  public void testPropagatedException ()
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final InvokableAPIDescriptor aInvokable = _createInvokable ( (a, b, c, d, e) -> {
        throw new IllegalStateException ("oops");
      }, null);
      new APIInvoker ().invoke (aInvokable,
                                aWebScoped.getRequestScope (),
                                _createResponse (aWebScoped));
      fail ();
    }
    catch (final Exception ex)
    {
      // Expected
      assertEquals ("oops", ex.getMessage ());
    }

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusError ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CAPITelemetry.ATTR_API_EXCEPTION_HANDLED));

    // The counter counts failed invocations as well
    final CapturedMeasurement aInvocations = TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_INVOCATIONS);
    assertNotNull (aInvocations);
    assertEquals (1, (int) aInvocations.getValue ());
    assertEquals (Boolean.FALSE, aInvocations.getAttribute (CAPITelemetry.ATTR_API_SUCCESS));
    // And the duration is recorded for failures as well
    assertNotNull (TELEMETRY.getFirstMeasurement (CAPITelemetry.METRIC_DURATION));
    assertFalse (TELEMETRY.getSpans ().isEmpty ());
  }
}
