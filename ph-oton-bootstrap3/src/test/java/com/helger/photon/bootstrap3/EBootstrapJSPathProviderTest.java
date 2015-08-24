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
package com.helger.photon.bootstrap3;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;

/**
 * Test class for class {@link EBootstrapJSPathProvider}.
 *
 * @author Philip Helger
 */
public final class EBootstrapJSPathProviderTest
{
  @Test
  public void testBasic ()
  {
    for (final EBootstrapJSPathProvider e : EBootstrapJSPathProvider.values ())
      if (e.isBundlable ())
      {
        IReadableResource aRes = new ClassPathResource (e.getJSItemPath (true));
        assertTrue (aRes.getPath (), aRes.exists ());
        aRes = new ClassPathResource (e.getJSItemPath (false));
        assertTrue (aRes.getPath (), aRes.exists ());
      }
  }
}
