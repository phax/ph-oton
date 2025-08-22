/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.io.resource.ClassPathResource;
import com.helger.io.resource.IReadableResource;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsCSSPathProvider;

/**
 * Test class for class {@link EBootstrapUICtrlsCSSPathProvider}.
 *
 * @author Philip Helger
 */
public final class EBootstrapUICtrlsCSSPathProviderTest
{
  @Test
  public void testBasic ()
  {
    for (final EBootstrapUICtrlsCSSPathProvider e : EBootstrapUICtrlsCSSPathProvider.values ())
    {
      IReadableResource aRes = new ClassPathResource (e.getCSSItemPath (true));
      assertTrue (aRes.getPath (), aRes.exists ());
      aRes = new ClassPathResource (e.getCSSItemPath (false));
      assertTrue (aRes.getPath (), aRes.exists ());
    }
  }
}
