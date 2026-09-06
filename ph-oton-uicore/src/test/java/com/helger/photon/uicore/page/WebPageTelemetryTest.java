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

import org.jspecify.annotations.NonNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.state.EValidity;
import com.helger.base.state.IValidityIndicator;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.photon.core.execcontext.LayoutExecutionContext;
import com.helger.photon.core.execcontext.SimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.uicore.CUICoreTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.mock.CapturingTelemetry;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedMeasurement;
import com.helger.telemetry.mock.CapturingTelemetry.CapturedSpan;
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

  private static final CapturingTelemetry TELEMETRY = new CapturingTelemetry ();

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @BeforeClass
  public static void installTelemetry ()
  {
    // Must happen before WebPageMetrics is class-loaded, because the instruments are resolved once
    // in its static initializer
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

    assertEquals (1, TELEMETRY.getSpanCount ());
    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertEquals (CUICoreTelemetry.SPAN_PAGE_CONTENT, aSpan.getName ());
    assertEquals (ETelemetrySpanKind.INTERNAL, aSpan.getKind ());
    assertTrue (aSpan.isClosed ());
    assertEquals (PAGE_ID, aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (LOCALE.toString (), aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_LOCALE));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    final CapturedMeasurement aRendered = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (1, (int) aRendered.getValue ());
    assertEquals (PAGE_ID, aRendered.getAttribute (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (Boolean.TRUE, aRendered.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    // The locale is a span attribute only
    assertNull (aRendered.getAttribute (CUICoreTelemetry.ATTR_PAGE_LOCALE));
    assertEquals (2, aRendered.getAttributes ().size ());

    final CapturedMeasurement aDuration = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION);
    assertNotNull (aDuration);
    assertEquals (PAGE_ID, aDuration.getAttribute (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (1, aDuration.getAttributes ().size ());
  }

  @Test
  public void testNotDisplayedPage ()
  {
    final MockWebPage aPage = new MockWebPage (false, false);
    aPage.getContent (_createWPEC (aPage));
    assertFalse (aPage.m_bFilled);
    assertTrue (aPage.m_bInvalidCalled);

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isClosed ());
    assertEquals (PAGE_ID, aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_ID));
    assertEquals (Boolean.FALSE, aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    // A page that is never displayed is counted as well
    final CapturedMeasurement aRendered = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (1, (int) aRendered.getValue ());
    assertEquals (Boolean.FALSE, aRendered.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    assertNotNull (TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION));
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

    final CapturedSpan aSpan = TELEMETRY.getSpans ().getFirstOrNull ();
    assertNotNull (aSpan);
    assertTrue (aSpan.isClosed ());
    // A Post-Redirect-Get is a regular control flow and must not be marked as an error
    assertTrue (aSpan.isStatusOk ());
    assertNull (aSpan.getRecordedException ());
    assertNotNull (aSpan.getFirstEvent (CUICoreTelemetry.EVENT_FORCED_REDIRECT));
    assertEquals (Boolean.TRUE, aSpan.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));

    // The page is counted as displayed, because the content creation started
    final CapturedMeasurement aRendered = TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT);
    assertNotNull (aRendered);
    assertEquals (Boolean.TRUE, aRendered.getAttribute (CUICoreTelemetry.ATTR_PAGE_DISPLAYED));
    assertNotNull (TELEMETRY.getFirstMeasurement (CUICoreTelemetry.METRIC_PAGE_CONTENT_DURATION));
  }
}
