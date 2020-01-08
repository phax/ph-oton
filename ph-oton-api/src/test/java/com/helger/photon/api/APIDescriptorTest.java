/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.helger.commons.http.CHttpHeader;
import com.helger.photon.api.APIDescriptor;
import com.helger.photon.api.APIDescriptorList;
import com.helger.photon.api.APIPath;
import com.helger.photon.api.IAPIExecutor;

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
    final IAPIExecutor aExec = (a, b, c, d, e) -> {};
    aList.addDescriptor (new APIDescriptor (APIPath.get ("/r2o/v1/accessToken"), aExec).addRequiredParams ("token",
                                                                                                           "systemid",
                                                                                                           "client"));
    aList.addDescriptor (new APIDescriptor (APIPath.delete ("/service/{participantID}/get"), aExec));
    aList.addDescriptor (new APIDescriptor (APIPath.put ("/r2o/v1/invoice"),
                                            aExec).addRequiredHeaders (CHttpHeader.AUTHORIZATION));
    aList.addDescriptor (new APIDescriptor (APIPath.put ("/r2o/{version:regex=v[0-9]+}/invoice"), aExec));

    assertNotNull (aList.getMatching (APIPath.get ("/r2o/v1/accessToken")));
    assertNull (aList.getMatching (APIPath.post ("/r2o/v1/accessToken")));
    assertNotNull (aList.getMatching (APIPath.delete ("/service/12345/get")));
    assertNull (aList.getMatching (APIPath.get ("/service/12345/list")));

    // Does not match regex
    assertNull (aList.getMatching (APIPath.put ("/r2o/dummy/invoice")));
    // matches static and regex
    assertNull (aList.getMatching (APIPath.put ("/r2o/v1/invoice")));
    // Matches only the regex
    assertNotNull (aList.getMatching (APIPath.put ("/r2o/v24/invoice")));
    // Matches the regex but invalid HTTP method
    assertNull (aList.getMatching (APIPath.get ("/r2o/v24/invoice")));
  }

  @Test
  public void testBasic2 ()
  {
    final APIDescriptorList aList = new APIDescriptorList ();
    // Dummy executor
    final IAPIExecutor aExec = (a, b, c, d, e) -> {};
    aList.addDescriptor (new APIDescriptor (APIPath.get ("/{a}/b/{c}/d"), aExec));

    assertNotNull (aList.getMatching (APIPath.get ("/a/b/c/d")));
    assertNotNull (aList.getMatching (APIPath.get ("/hakjhyhxkj/b/hakjhyhxkj/d")));
    assertNotNull (aList.getMatching (APIPath.get ("/hakjh%3Ayhxkj/b/hakjh%2Fyhxkj/d")));
    assertNull (aList.getMatching (APIPath.get ("/")));
    assertNull (aList.getMatching (APIPath.get ("/a")));
    assertNull (aList.getMatching (APIPath.get ("/a/b")));
    assertNull (aList.getMatching (APIPath.get ("/a/b/c")));
    assertNull (aList.getMatching (APIPath.get ("/a/c/c/d")));
    assertNull (aList.getMatching (APIPath.get ("/a/b2/c/d")));
    assertNull (aList.getMatching (APIPath.get ("/a/b/c/e")));
    assertNull (aList.getMatching (APIPath.get ("/a/b/c/d2")));
  }
}
