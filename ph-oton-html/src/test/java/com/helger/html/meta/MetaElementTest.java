/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nullable;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.config.HCSettings;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * Test class for class {@link MetaElement}.
 *
 * @author Philip Helger
 */
public final class MetaElementTest
{

  @Test
  public void testBasic ()
  {
    final MetaElement e1 = MetaElement.createMeta ("nam", "con");
    assertEquals ("nam", e1.getName ());
    assertFalse (e1.isHttpEquiv ());
    assertTrue (e1.isLanguageIndependent ());
    assertEquals ("con", e1.getContentLanguageIndependent ());
    assertEquals ("<meta name=\"nam\" content=\"con\" />", _getAsString (e1));

    final MetaElement e2 = MetaElement.createMetaHttpEquiv ("x", "y");
    assertEquals ("<meta http-equiv=\"x\" content=\"y\" />", _getAsString (e2));

    final MetaElement e3 = MetaElement.createMetaCharset (StandardCharsets.ISO_8859_1);
    assertEquals ("<meta charset=\"ISO-8859-1\" />", _getAsString (e3));

    CommonsTestHelper.testDefaultSerialization (e1);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (e1, MetaElement.createMeta ("nam", "con"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1, MetaElement.createMeta ("nam2", "con"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1, MetaElement.createMeta ("nam", "con2"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1,
                                                                           MetaElement.createMetaHttpEquiv ("nam",
                                                                                                            "con"));
  }

  @Nullable
  private static String _getAsString (final MetaElement aElement)
  {
    final IMicroNode aNode = aElement.convertToNode (HCSettings.getConversionSettings ());
    assertNotNull (aNode);
    return StringHelper.trim (MicroWriter.getNodeAsString (aNode));
  }
}
