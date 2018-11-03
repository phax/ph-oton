/**
 * Copyright (C) 2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.grid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for class {@link EBootstrapGridType}.
 *
 * @author Philip Helger
 */
public final class EBootstrapGridTypeTest
{
  @Test
  public void testWidth ()
  {
    assertNull (EBootstrapGridType.getForWidth (-1));
    assertEquals (EBootstrapGridType.XS, EBootstrapGridType.getForWidth (0));
    assertEquals (EBootstrapGridType.XS, EBootstrapGridType.getForWidth (1));
    assertEquals (EBootstrapGridType.XS, EBootstrapGridType.getForWidth (575));
    assertEquals (EBootstrapGridType.SM, EBootstrapGridType.getForWidth (576));
    assertEquals (EBootstrapGridType.SM, EBootstrapGridType.getForWidth (767));
    assertEquals (EBootstrapGridType.MD, EBootstrapGridType.getForWidth (768));
    assertEquals (EBootstrapGridType.MD, EBootstrapGridType.getForWidth (991));
    assertEquals (EBootstrapGridType.LG, EBootstrapGridType.getForWidth (992));
    assertEquals (EBootstrapGridType.LG, EBootstrapGridType.getForWidth (1199));
    assertEquals (EBootstrapGridType.XL, EBootstrapGridType.getForWidth (1200));
    assertEquals (EBootstrapGridType.XL, EBootstrapGridType.getForWidth (Integer.MAX_VALUE));
  }
}
