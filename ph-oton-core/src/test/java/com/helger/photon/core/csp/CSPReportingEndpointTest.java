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
package com.helger.photon.core.csp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;

/**
 * Test class for class {@link CSPReportingEndpoint}.
 *
 * @author Philip Helger
 */
public final class CSPReportingEndpointTest
{
  @Test
  public void testHeaderAndDirectiveUseTheSameName ()
  {
    final CSPReportingEndpoint aEP = new CSPReportingEndpoint ("my-endpoint", "/cspreporting?page=x");
    assertEquals ("my-endpoint", aEP.getName ());
    assertEquals ("/cspreporting?page=x", aEP.getURI ());

    // The header names the endpoint and the URI ...
    assertEquals ("my-endpoint=\"/cspreporting?page=x\"", aEP.getReportingEndpointsHeaderValue ());
    // ... and "report-to" refers to exactly that name, so the two cannot drift apart
    assertEquals ("report-to my-endpoint", aEP.getAsReportToDirective ().getAsString ());
    // The legacy directive carries the URI itself
    assertEquals ("report-uri /cspreporting?page=x", aEP.getAsReportURIDirective ().getAsString ());
  }

  @Test
  public void testDefaultName ()
  {
    final CSPReportingEndpoint aEP = new CSPReportingEndpoint ("/cspreporting");
    assertEquals (CSPReportingEndpoint.DEFAULT_ENDPOINT_NAME, aEP.getName ());
  }

  @Test
  public void testCreateURI ()
  {
    // No parameters - the base URI is returned unchanged
    assertEquals ("/cspreporting", CSPReportingEndpoint.createURI ("/cspreporting", null));
    assertEquals ("/cspreporting", CSPReportingEndpoint.createURI ("/cspreporting", new CommonsLinkedHashMap <> ()));

    final ICommonsOrderedMap <String, String> aParams = new CommonsLinkedHashMap <> ();
    aParams.put ("page", "menuitem-service_groups_export_data");
    aParams.put ("build", "8.2.1");
    final String sURI = CSPReportingEndpoint.createURI ("/cspreporting", aParams);
    assertTrue (sURI, sURI.startsWith ("/cspreporting?"));
    assertTrue (sURI, sURI.contains ("page=menuitem-service_groups_export_data"));
    assertTrue (sURI, sURI.contains ("build=8.2.1"));
  }

  @Test
  public void testNoParameterProviderMeansNoParameters ()
  {
    // A consumer that does not supply an implementation must see no change at all
    final ICommonsOrderedMap <String, String> aTarget = new CommonsLinkedHashMap <> ();
    ICSPReportingParameterProvider.NONE.addReportingParameters (null, aTarget);
    assertTrue (aTarget.isEmpty ());
    assertEquals ("/cspreporting", CSPReportingEndpoint.createURI ("/cspreporting", aTarget));
  }
}
