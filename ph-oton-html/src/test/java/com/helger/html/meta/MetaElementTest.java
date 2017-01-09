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
package com.helger.html.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;

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
    final MetaElement e1 = new MetaElement ("nam", "con");
    assertEquals ("nam", e1.getName ());
    assertFalse (e1.isHttpEquiv ());
    assertTrue (e1.isLanguageIndependent ());
    assertEquals ("con", e1.getContentLanguageIndependent ());

    CommonsTestHelper.testDefaultSerialization (e1);
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (e1, new MetaElement ("nam", "con"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1, new MetaElement ("nam2", "con"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1, new MetaElement ("nam", "con2"));
    CommonsTestHelper.testDefaultImplementationWithDifferentContentObject (e1,
                                                                           new MetaElement ("nam",
                                                                                            "con").setHttpEquiv (true));
  }
}