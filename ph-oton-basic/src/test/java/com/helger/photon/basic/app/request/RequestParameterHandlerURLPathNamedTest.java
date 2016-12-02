package com.helger.photon.basic.app.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Nonnull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.photon.basic.mock.PhotonBasicWebTestRule;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Test class for class {@link RequestParameterHandlerURLPathNamed}.
 *
 * @author Philip Helger
 */
public final class RequestParameterHandlerURLPathNamedTest
{
  @Rule
  public final TestRule m_aRule = new PhotonBasicWebTestRule ();

  private void _test (@Nonnull final String sBasePath)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = WebScopeManager.getRequestScope ();
    final RequestParameterHandlerURLPathNamed h = new RequestParameterHandlerURLPathNamed ();
    final IRequestParameterManager aRequestParamMgr = ApplicationRequestManager.getRequestMgr ();
    final String sParamLocale = aRequestParamMgr.getRequestParamNameLocale ();
    final String sParamMenuItem = aRequestParamMgr.getRequestParamNameMenuItem ();

    // No params
    SimpleURL aURL = h.buildURL (aRequestScope, sBasePath, null);
    assertEquals (sBasePath, aURL.getAsStringWithEncodedParameters ());
    URLParameterList aParams = h.getParametersFromURL (aURL);
    assertTrue (aParams.isEmpty ());

    // Locale only
    aURL = h.buildURL (aRequestScope, sBasePath, new CommonsArrayList<> (new URLParameter (sParamLocale, "de_AT")));
    assertEquals (sBasePath + "/" + sParamLocale + "-de_AT", aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertEquals (1, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertNull (aParams.getFirstParamValue (sParamMenuItem));

    // Menu item only
    aURL = h.buildURL (aRequestScope, sBasePath, new CommonsArrayList<> (new URLParameter (sParamMenuItem, "test")));
    assertEquals (sBasePath + "/" + sParamMenuItem + "-test", aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertEquals (1, aParams.size ());
    assertNull (aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Locale and menu item
    aURL = h.buildURL (aRequestScope,
                       sBasePath,
                       new CommonsArrayList<> (new URLParameter (sParamLocale, "de_AT"),
                                               new URLParameter (sParamMenuItem, "test")));
    assertEquals (sBasePath +
                  "/" +
                  sParamLocale +
                  "-de_AT/" +
                  sParamMenuItem +
                  "-test",
                  aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertEquals (2, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));

    // Locale and menu item - reverse order
    aURL = h.buildURL (aRequestScope,
                       sBasePath,
                       new CommonsArrayList<> (new URLParameter (sParamMenuItem, "test"),
                                               new URLParameter (sParamLocale, "de_AT")));
    assertEquals (sBasePath +
                  "/" +
                  sParamMenuItem +
                  "-test/" +
                  sParamLocale +
                  "-de_AT",
                  aURL.getAsStringWithEncodedParameters ());
    aParams = h.getParametersFromURL (aURL);
    assertEquals (2, aParams.size ());
    assertEquals ("de_AT", aParams.getFirstParamValue (sParamLocale));
    assertEquals ("test", aParams.getFirstParamValue (sParamMenuItem));
  }

  @Test
  public void testBasic ()
  {
    _test ("");
    _test ("/ctx");
    _test ("/servlet");
    _test ("/ctx/servlet");
  }
}
