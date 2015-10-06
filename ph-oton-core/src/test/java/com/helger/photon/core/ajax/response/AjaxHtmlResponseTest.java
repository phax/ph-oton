package com.helger.photon.core.ajax.response;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.js.UnparsedJSCodeProvider;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.web.mock.MockHttpServletResponse;
import com.helger.web.mock.OfflineHttpServletRequest;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Test class for class {@link AjaxHtmlResponse}.
 *
 * @author Philip Helger
 */
public final class AjaxHtmlResponseTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testFullHtml ()
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.onRequestBegin ("dummy",
                                                                                          new OfflineHttpServletRequest (),
                                                                                          new MockHttpServletResponse ());
    try
    {
      final HCHtml aHtml = new HCHtml ();
      aHtml.getHead ().addCSS (new HCStyle ("*{font-family:Helvetica;}"));
      aHtml.getHead ().addJS (new HCScriptInline (new UnparsedJSCodeProvider ("var x = 1;")));
      aHtml.getHead ().addCSS (HCLink.createCSSLink (new SimpleURL ("res/animate.css")));
      aHtml.getHead ().addJS (new HCScriptFile ().setSrc ("res/stacktrace.js"));
      aHtml.getBody ().addChild (new HCH1 ().addChild ("Test H1"));
      aHtml.getBody ().addChild (new HCStyle ("h1{color:red;}"));
      aHtml.getBody ().addChild (new HCScriptInline (new UnparsedJSCodeProvider ("var y = x;")));
      final AjaxHtmlResponse aResponse = AjaxHtmlResponse.createSuccess (aRequestScope, aHtml);
      assertNotNull (aResponse);
      assertNotNull (aResponse.getSuccessValue ());
      assertNotNull (aResponse.getSuccessValue ().getValue (AjaxHtmlResponse.PROPERTY_HTML));
      System.out.println (aResponse.getSuccessValue ().getValue (AjaxHtmlResponse.PROPERTY_HTML).getAsStringValue ());
    }
    finally
    {
      WebScopeManager.onRequestEnd ();
    }
  }
}
