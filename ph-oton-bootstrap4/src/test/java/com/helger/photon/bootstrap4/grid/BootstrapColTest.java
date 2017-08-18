package com.helger.photon.bootstrap4.grid;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.grid.BootstrapCol.MarginBuilder;

public final class BootstrapColTest
{
  @Test
  public void testMargin () throws Exception
  {
    final ICommonsSet <String> aNames = new CommonsLinkedHashSet <> ();
    final MarginBuilder m = new MarginBuilder ();
    for (final EBootstrapEdgeType eEdge : EBootstrapEdgeType.values ())
    {
      m.edgeType (eEdge);
      for (final EBootstrapGridType eGrid : EBootstrapGridType.values ())
      {
        m.gridType (eGrid);
        for (final int nCount : new int [] { 0, 1, 2, 3, 4, 5, -1 })
        {
          m.count (nCount);
          aNames.add (m.getCSSClass ());
        }
      }
    }

    // Get all CBootrapCSS class names via reflection :)
    final ICommonsSet <String> aBootstrap = new CommonsLinkedHashSet <> ();
    for (final Field f : CBootstrapCSS.class.getDeclaredFields ())
      if (Modifier.isStatic (f.getModifiers ()))
        aBootstrap.add (((ICSSClassProvider) f.get (null)).getCSSClass ());

    if (false)
      System.out.println (aBootstrap.toString ());
    if (false)
      System.out.println (aNames.size () + " - " + aNames.toString ());
    for (final String sName : aNames)
      assertTrue (sName, aBootstrap.contains (sName));
  }
}
