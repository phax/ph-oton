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
package com.helger.photon.ajax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;
import com.helger.web.scope.mgr.WebScoped;

/**
 * Test class for the ph-telemetry integration of {@link AjaxInvoker}.
 *
 * @author Philip Helger
 */
public final class AjaxTelemetryTest
{
  private static final String FUNCTION_NAME = "unit-test-function";

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before AjaxMetrics is class-loaded, because the instruments are resolved once in
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

  @Test
  public void testSuccessfulInvocation () throws Exception
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final IAjaxExecutor aExecutor = (aRequestScope, aResponse) -> {};
      new AjaxInvoker ().invokeFunction (FUNCTION_NAME,
                                         aExecutor,
                                         aWebScoped.getRequestScope (),
                                         PhotonUnifiedResponse.createSimple (aWebScoped.getRequestScope ()));
    }

    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CAjaxTelemetry.SPAN_INVOKE, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.SERVER, aSpan.getKind ());
    assertTrue (aSpan.isClosed ());
    assertTrue (aSpan.isStatusOk ());
    assertEquals (FUNCTION_NAME, aSpan.getAttribute (CAjaxTelemetry.ATTR_AJAX_FUNCTION));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS));

    final CapturedMeasurement aInvocations = TELEMETRY.getFirstMeasurement (CAjaxTelemetry.METRIC_INVOCATIONS);
    assertNotNull (aInvocations);
    assertEquals (1, (int) aInvocations.getValue ());
    assertEquals (FUNCTION_NAME, aInvocations.getAttribute (CAjaxTelemetry.ATTR_AJAX_FUNCTION));
    assertEquals (Boolean.TRUE, aInvocations.getAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS));
    assertEquals (2, aInvocations.getAttributes ().size ());

    assertNotNull (TELEMETRY.getFirstMeasurement (CAjaxTelemetry.METRIC_DURATION));
  }

  @Test
  public void testFailedInvocation ()
  {
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      final IAjaxExecutor aExecutor = (aRequestScope, aResponse) -> { throw new IllegalStateException ("oops"); };
      new AjaxInvoker ().invokeFunction (FUNCTION_NAME,
                                         aExecutor,
                                         aWebScoped.getRequestScope (),
                                         PhotonUnifiedResponse.createSimple (aWebScoped.getRequestScope ()));
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
    assertNotNull (aSpan.getRecordedException ());
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS));

    // The counter counts failed invocations as well
    final CapturedMeasurement aInvocations = TELEMETRY.getFirstMeasurement (CAjaxTelemetry.METRIC_INVOCATIONS);
    assertNotNull (aInvocations);
    assertEquals (1, (int) aInvocations.getValue ());
    assertEquals (Boolean.FALSE, aInvocations.getAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS));

    // The duration is now recorded for failing invocations as well
    final CapturedMeasurement aDuration = TELEMETRY.getFirstMeasurement (CAjaxTelemetry.METRIC_DURATION);
    assertNotNull (aDuration);
    assertEquals (Boolean.FALSE, aDuration.getAttribute (CAjaxTelemetry.ATTR_AJAX_SUCCESS));
  }
}
