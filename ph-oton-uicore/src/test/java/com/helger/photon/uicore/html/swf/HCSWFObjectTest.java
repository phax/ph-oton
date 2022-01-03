/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.swf;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.render.HCRenderer;
import com.helger.photon.core.mock.PhotonCoreTestRule;
import com.helger.xml.microdom.IMicroNode;

/**
 * Test class for class {@link HCSWFObject}.
 *
 * @author Philip Helger
 */
public final class HCSWFObjectTest
{
  @Rule
  public final TestRule m_aRule = new PhotonCoreTestRule ();

  @Test
  public void testCodeGen ()
  {
    final HCSWFObject x = new HCSWFObject ();
    x.setSWFURL (new SimpleURL ("a.swf"));
    x.setWidth (500).setHeight (300);
    x.setRequiredSWFVersion ("9.0.0");
    x.addFlashVar ("flash1", "Wert");
    final IMicroNode aNode = HCRenderer.getAsNode (x);
    assertNotNull (aNode);
  }
}
