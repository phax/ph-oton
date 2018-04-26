/**
 * Copyright (C) 2015-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.system.EOperatingSystem;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.EBootstrapGridType;
import com.helger.photon.bootstrap4.utils.BootstrapSpacingBuilder;
import com.helger.photon.bootstrap4.utils.EBootstrapSpacingPropertyType;
import com.helger.photon.bootstrap4.utils.EBootstrapSpacingSideType;

/**
 * Test class for class {@link BootstrapSpacingBuilder}.
 * 
 * @author Philip Helger
 */
public final class BootstrapSpacingBuilderTest
{
  @Test
  public void testMargin () throws Exception
  {
    // Default value
    assertEquals ("m-auto", new BootstrapSpacingBuilder ().getCSSClass ());

    final ICommonsSet <String> aNames = new CommonsLinkedHashSet <> ();
    final BootstrapSpacingBuilder aBuilder = new BootstrapSpacingBuilder ();
    for (final EBootstrapSpacingPropertyType eProp : EBootstrapSpacingPropertyType.values ())
    {
      aBuilder.property (eProp);
      for (final EBootstrapSpacingSideType eSide : EBootstrapSpacingSideType.values ())
      {
        aBuilder.side (eSide);
        for (final EBootstrapGridType eGrid : EBootstrapGridType.values ())
        {
          aBuilder.grid (eGrid);
          for (final int nSize : new int [] { 0, 1, 2, 3, 4, 5, -1 })
          {
            if (nSize == -1)
            {
              if (eProp == EBootstrapSpacingPropertyType.PADDING)
                continue;
              aBuilder.auto ();
            }
            else
              aBuilder.size (nSize);
            aNames.add (aBuilder.getCSSClass ());
          }
        }
      }
    }

    // Get all CBootrapCSS class names via reflection :)
    if (EOperatingSystem.WINDOWS.isCurrentOS ())
    {
      final ICommonsSet <String> aBootstrap = new CommonsLinkedHashSet <> ();
      for (final Field f : CBootstrapCSS.class.getDeclaredFields ())
        if (Modifier.isStatic (f.getModifiers ()))
          aBootstrap.add (((ICSSClassProvider) f.get (null)).getCSSClass ());
      for (final String sName : aNames)
        assertTrue (sName, aBootstrap.contains (sName));

      if (false)
        System.out.println (aBootstrap.toString ());
    }

    if (false)
      System.out.println (aNames.size () + " - " + aNames.toString ());
  }
}
