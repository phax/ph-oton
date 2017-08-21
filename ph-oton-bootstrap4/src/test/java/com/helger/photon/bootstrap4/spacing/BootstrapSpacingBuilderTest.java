package com.helger.photon.bootstrap4.spacing;

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
import com.helger.photon.bootstrap4.utils.ESpacingPropertyType;
import com.helger.photon.bootstrap4.utils.ESpacingSideType;

public final class BootstrapSpacingBuilderTest
{
  @Test
  public void testMargin () throws Exception
  {
    // Default value
    assertEquals ("m-auto", new BootstrapSpacingBuilder ().getCSSClass ());

    final ICommonsSet <String> aNames = new CommonsLinkedHashSet <> ();
    final BootstrapSpacingBuilder aBuilder = new BootstrapSpacingBuilder ();
    for (final ESpacingPropertyType eProp : ESpacingPropertyType.values ())
    {
      aBuilder.property (eProp);
      for (final ESpacingSideType eSide : ESpacingSideType.values ())
      {
        aBuilder.side (eSide);
        for (final EBootstrapGridType eGrid : EBootstrapGridType.values ())
        {
          aBuilder.grid (eGrid);
          for (final int nSize : new int [] { 0, 1, 2, 3, 4, 5, -1 })
          {
            if (nSize == -1)
            {
              if (eProp == ESpacingPropertyType.PADDING)
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
