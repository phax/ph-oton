/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.icon;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.photon.icon.EIconCSSPathProvider;

/**
 * Test class for class {@link EIconCSSPathProvider}.
 *
 * @author Philip Helger
 */
public final class EIconCSSPathProviderTest
{
  @Test
  public void testBasic ()
  {
    for (final EIconCSSPathProvider e : EIconCSSPathProvider.values ())
    {
      IReadableResource aRes = new ClassPathResource (e.getCSSItemPath (true));
      assertTrue (aRes.getPath (), aRes.exists ());
      aRes = new ClassPathResource (e.getCSSItemPath (false));
      assertTrue (aRes.getPath (), aRes.exists ());
    }
  }
}