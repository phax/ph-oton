/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.app.io;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.id.factory.IIntIDFactory;
import com.helger.photon.app.mock.PhotonAppTestRule;

/**
 * Test class for class {@link WebIOIntIDFactory}.
 *
 * @author Philip Helger
 */
public final class WebIOIntIDFactoryTest
{
  @Rule
  public final TestRule m_aRule = new PhotonAppTestRule ();

  @Test
  public void testFileBasedIDProvider ()
  {
    final IIntIDFactory aIDProvider = new WebIOIntIDFactory ("junit.ids.dat");
    final ICommonsSet <Integer> aIDs = new CommonsHashSet <> ();

    for (int i = 0; i < 1000; ++i)
    {
      final int nID = aIDProvider.getNewID ();
      assertTrue ("Failed to add ID " + nID, aIDs.add (Integer.valueOf (nID)));
    }
  }
}
