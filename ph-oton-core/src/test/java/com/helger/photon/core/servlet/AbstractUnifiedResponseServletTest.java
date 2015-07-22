/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import org.junit.Rule;
import org.junit.Test;

import com.helger.commons.charset.CCharset;
import com.helger.web.mock.MockHttpServletResponse;
import com.helger.web.scope.mock.WebScopeAwareTestSetup;
import com.helger.web.scope.mock.WebScopeTestRule;

/**
 * Test class for class {@link AbstractUnifiedResponseServlet}.
 *
 * @author Philip Helger
 */
public final class AbstractUnifiedResponseServletTest
{
  @Rule
  public WebScopeTestRule m_aRule = new WebScopeTestRule ()
  {
    @Override
    public void before ()
    {
      super.before ();
      getServletPool ().registerServlet (MockUnifiedResponseServlet.class, "/mock/*", "MockServlet", null);
    }
  };

  @Test
  public void testBasic ()
  {
    m_aRule.getRequest ().setAllPaths ("http://localhost:1234" +
                                       WebScopeAwareTestSetup.MOCK_CONTEXT_PATH +
                                       "/mock/testrequest;JSESSIONID=1234?name=value&name2=value2");
    final MockHttpServletResponse aResponse = m_aRule.getServletContext ().invoke (m_aRule.getRequest ());
    assertNotNull (aResponse);
    final String sResponseContent = aResponse.getContentAsString (CCharset.CHARSET_UTF_8_OBJ);
    assertNotNull (sResponseContent);
    assertEquals (MockUnifiedResponseServlet.RESPONSE_TEXT, sResponseContent);
  }
}
