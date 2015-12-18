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
package com.helger.photon.core.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.helger.web.http.CHTTPHeader;
import com.helger.web.http.EHTTPMethod;

/**
 * Test class for class {@link APIDescriptor}.
 * 
 * @author Philip Helger
 */
public final class APIDescriptorTest
{
  @Test
  public void testBasic ()
  {
    final APIDescriptorList aList = new APIDescriptorList ();
    // Dummy executor
    final IAPIExecutor aExec = (a, b, c, d) -> {};
    aList.addDescriptor (APIDescriptor.get ("/r2o/v1/accessToken", aExec).addRequiredParams ("token",
                                                                                             "systemid",
                                                                                             "client"));
    aList.addDescriptor (APIDescriptor.delete ("/service/{participantID}/get", aExec));
    aList.addDescriptor (APIDescriptor.put ("/r2o/v1/invoice", aExec).addRequiredHeaders (CHTTPHeader.AUTHORIZATION));
    aList.addDescriptor (APIDescriptor.put ("/r2o/{version:regex=v[0-9]+}/invoice", aExec));

    assertNotNull (aList.getMatching ("/r2o/v1/accessToken", EHTTPMethod.GET));
    assertNull (aList.getMatching ("/r2o/v1/accessToken", EHTTPMethod.POST));
    assertNotNull (aList.getMatching ("/service/12345/get", EHTTPMethod.DELETE));
    assertNull (aList.getMatching ("/service/12345/list", EHTTPMethod.GET));

    // Does not match regex
    assertNull (aList.getMatching ("/r2o/dummy/invoice", EHTTPMethod.PUT));
    // matches static and regex
    assertNull (aList.getMatching ("/r2o/v1/invoice", EHTTPMethod.PUT));
    // Matches only the regex
    assertNotNull (aList.getMatching ("/r2o/v24/invoice", EHTTPMethod.PUT));
    // Matches the regex but invalid HTTP method
    assertNull (aList.getMatching ("/r2o/v24/invoice", EHTTPMethod.GET));
  }
}
