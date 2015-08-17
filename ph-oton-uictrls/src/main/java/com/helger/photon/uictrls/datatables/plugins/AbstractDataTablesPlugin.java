package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.IDataTablesPlugin;

public abstract class AbstractDataTablesPlugin implements IDataTablesPlugin
{
  private final String m_sName;

  public AbstractDataTablesPlugin (@Nonnull @Nonempty final String sName)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  public void finalizeDataTablesSettings (final DataTables aDT)
  {
    // empty
  }

  public void addInitJS (final DataTables aDT, final JSPackage aJSCode, final JSVar aJSTable)
  {
    // empty
  }

  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    // empty
  }
}
