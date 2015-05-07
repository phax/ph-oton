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
package com.helger.photon.uicore.html.swf;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.photon.uicore.html.swf.HCSWFObject;

/**
 * Test class for class {@link HCSWFObject}.
 *
 * @author Philip Helger
 */
public final class HCSWFObjectTest
{
  @Test
  public void testCodeGen ()
  {
    final HCSWFObject x = new HCSWFObject ();
    x.setID ("div1");
    x.setSWFURL (new SimpleURL ("a.swf"));
    x.setWidth (500).setHeight (300);
    x.setRequiredSWFVersion ("9.0.0");
    x.addFlashVar ("flash1", "Wert");
    final IMicroNode aNode = HCSettings.getAsNode (x);
    assertNotNull (aNode);
    if (false)
      System.out.println (MicroWriter.getXMLString (aNode));
  }
}
