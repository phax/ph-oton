package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.JSAssocArray;

public class DTPResponsiveBreakpoint
{
  private final String m_sName;
  private final String m_sWidth;

  public DTPResponsiveBreakpoint (@Nonnull @Nonempty final String sName, @Nonnull @Nonempty final String sWidth)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
    m_sWidth = ValueEnforcer.notEmpty (sWidth, "Width");
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public String getWidth ()
  {
    return m_sWidth;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    return new JSAssocArray ().add ("name", m_sName).add ("width", m_sWidth);
  }
}
