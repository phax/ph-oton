package com.helger.photon.basic.app.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.locale.LocaleCache;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.photon.basic.app.locale.ApplicationLocaleManager;
import com.helger.photon.basic.app.menu.ApplicationMenuTree;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.page.AbstractPage;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.web.mock.MockHttpServletRequest;
import com.helger.web.mock.MockHttpServletResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.impl.RequestWebScope;

/**
 * Test class for class {@link RequestParameterHandlerURLPathOrdered}.
 *
 * @author Philip Helger
 */
public final class RequestParameterHandlerURLPathOrderedTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  @Test
  public void testBasic ()
  {
    final MockHttpServletRequest aRequest = new MockHttpServletRequest ();
    final IRequestWebScopeWithoutResponse aRequestScope = new RequestWebScope (aRequest,
                                                                               new MockHttpServletResponse ());
    final RequestParameterHandlerURLPathOrdered h = new RequestParameterHandlerURLPathOrdered ();
    final IRequestParameterManager aRequestParamMgr = ApplicationRequestManager.getRequestMgr ();

    final Locale aLocale = LocaleCache.getInstance ().getLocale ("de", "AT");
    ApplicationLocaleManager.getLocaleMgr ().registerLocale (aLocale);
    final IMenuItemPage aMenuItem = ApplicationMenuTree.getTree ().createRootItem (new AbstractPage ("test")
    {});

    // No params
    SimpleURL aURL = h.buildURL (aRequestScope, "", null);
    assertEquals ("", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    URLParameterList aParams = h.getParametersFromRequest (aRequestScope);
    assertTrue (aParams.isEmpty ());

    // Locale only
    aURL = h.buildURL (aRequestScope, "", new CommonsArrayList<> (new URLParameter (sParamLocale, "de_AT")));
    assertEquals ("/de_AT", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (1, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertNull (aParams.getFirstParamValue (sParamMenuItem));

    // Locale only but not existing
    aURL = h.buildURL (aRequestScope, "", new CommonsArrayList<> (new URLParameter (sParamLocale, "en_US")));
    assertEquals ("/en_US", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertTrue (aParams.isEmpty ());

    // Menu item only
    aURL = h.buildURL (aRequestScope, "", new CommonsArrayList<> (new URLParameter (sParamMenuItem, "test")));
    assertEquals ("/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (1, aParams.size ());
    assertNull (aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Menu item only but not existing
    aURL = h.buildURL (aRequestScope, "", new CommonsArrayList<> (new URLParameter (sParamMenuItem, "other")));
    assertEquals ("/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertTrue (aParams.isEmpty ());

    // Locale and menu item
    aURL = h.buildURL (aRequestScope,
                       "",
                       new CommonsArrayList<> (new URLParameter (sParamLocale, "de_AT"),
                                               new URLParameter (sParamMenuItem, "test")));
    assertEquals ("/de_AT/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (2, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Locale and menu item different input order
    aURL = h.buildURL (aRequestScope,
                       "",
                       new CommonsArrayList<> (new URLParameter (sParamMenuItem, "test"),
                                               new URLParameter (sParamLocale, "de_AT")));
    assertEquals ("/de_AT/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (2, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Locale and menu item - locale invalid
    aURL = h.buildURL (aRequestScope,
                       "",
                       new CommonsArrayList<> (new URLParameter (sParamLocale, "en_US"),
                                               new URLParameter (sParamMenuItem, "test")));
    assertEquals ("/en_US/test", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (1, aParams.size ());
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Locale and menu item - menu item invalid
    aURL = h.buildURL (aRequestScope,
                       "",
                       new CommonsArrayList<> (new URLParameter (sParamLocale, "de_AT"),
                                               new URLParameter (sParamMenuItem, "other")));
    assertEquals ("/de_AT/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertEquals (1, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));

    // Locale and menu item - both invalid
    aURL = h.buildURL (aRequestScope,
                       "",
                       new CommonsArrayList<> (new URLParameter (sParamLocale, "en_US"),
                                               new URLParameter (sParamMenuItem, "other")));
    assertEquals ("/en_US/other", aURL.getAsStringWithEncodedParameters ());
    aRequest.setPathInfo (aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromRequest (aRequestScope);
    assertTrue (aParams.isEmpty ());
  }
}
