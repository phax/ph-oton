/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.mock.PhotonCoreTestRule;

/**
 * Test class for class {@link WebSiteResourceBundleManager}.
 *
 * @author Philip Helger
 */
public final class WebSiteResourceBundleManagerTest
{
  @Rule
  public final TestRule m_aRule = new PhotonCoreTestRule ();

  @Test
  public void testBasicJS ()
  {
    final ICommonsList <WebSiteResourceWithCondition> aList = new CommonsArrayList <> ();
    aList.add (WebSiteResourceWithCondition.createForJS (ConstantJSPathProvider.create ("/res/serverlog.js"), true));
    aList.add (WebSiteResourceWithCondition.createForJS (ConstantJSPathProvider.create ("/res/stacktrace.js"), true));
    final ICommonsList <WebSiteResourceBundleSerialized> aBundles = PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                                     .getResourceBundles (aList, true);
    assertNotNull (aBundles);
    assertEquals (1, aBundles.size ());
  }

  @Test
  public void testBasicCSS ()
  {
    final ICommonsList <WebSiteResourceWithCondition> aList = new CommonsArrayList <> ();
    aList.add (WebSiteResourceWithCondition.createForCSS (ConstantCSSPathProvider.create ("/res/animate.css"), true));
    aList.add (WebSiteResourceWithCondition.createForCSS (ConstantCSSPathProvider.create ("/res/famfam.css"), true));
    final ICommonsList <WebSiteResourceBundleSerialized> aBundles = PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                                     .getResourceBundles (aList, true);
    assertNotNull (aBundles);
    assertEquals (1, aBundles.size ());
  }
}
