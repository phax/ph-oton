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
package com.helger.photon.uicore.facebook.xfbml;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for class {@link FBLike}.
 *
 * @author Philip Helger
 */
public final class FBLikeTest
{
  private static final String VALID = "abcdefghijabcdefghijabcdefghijabcdefghij123456789";
  private static final String VALID_PUNCT = "abcdefgh+/=-.:_ijabcdefghijabcdefghijabcdefghij12";
  private static final String INVALID = "a+#()/{}\\`´$\".;,:-_";

  @Test
  public void testCreateRefText ()
  {
    Assert.assertEquals (VALID, FBLike.createRefText (VALID));
    Assert.assertEquals (VALID, FBLike.createRefText (VALID + VALID));
    Assert.assertEquals (VALID_PUNCT, FBLike.createRefText (VALID_PUNCT));
    Assert.assertEquals ("a+/.:-_", FBLike.createRefText (INVALID));
  }
}
