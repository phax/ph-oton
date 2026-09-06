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

import org.jspecify.annotations.NonNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.state.EContinue;
import com.helger.photon.app.csrf.CSRFSessionManager;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.execcontext.LayoutExecutionContext;
import com.helger.photon.core.execcontext.SimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
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

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before CSRFMetrics is class-loaded, because the instruments are resolved once in
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

    final CapturedMeasurement aChecks = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_CSRF_CHECKS);
    assertNotNull (aChecks);
    assertEquals (1, (int) aChecks.getValue ());
    assertEquals (PAGE_ID, aChecks.getAttribute (CUICoreTelemetry.ATTR_CSRF_PAGE_ID));
    assertEquals (Boolean.TRUE, aChecks.getAttribute (CUICoreTelemetry.ATTR_CSRF_VALID));
    // Only the bounded page ID and the validity - never the nonce itself
    assertEquals (2, aChecks.getAttributes ().size ());
  }

  @Test
  public void testInvalidNonce ()
  {
    final WebPageExecutionContext aWPEC = _createWPEC ();
    final String sWrongNonce = "definitely-not-the-expected-nonce";
    aWPEC.params ().putIn (CPageParam.FIELD_NONCE, sWrongNonce);

    assertEquals (EContinue.BREAK, WebPageCSRFHandler.INSTANCE.checkCSRFNonce (aWPEC));

    final CapturedMeasurement aChecks = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_CSRF_CHECKS);
    assertNotNull (aChecks);
    assertEquals (1, (int) aChecks.getValue ());
    assertEquals (PAGE_ID, aChecks.getAttribute (CUICoreTelemetry.ATTR_CSRF_PAGE_ID));
    assertEquals (Boolean.FALSE, aChecks.getAttribute (CUICoreTelemetry.ATTR_CSRF_VALID));
    // The nonce value is a security token and must not show up anywhere
    assertFalse (aChecks.getAttributes ().containsValue (sWrongNonce));
    assertEquals (2, aChecks.getAttributes ().size ());
  }
}
