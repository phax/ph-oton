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

  public boolean canBeApplied (@Nonnull final DataTables aDT)
  {
    return true;
  }

  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    // empty
  }

  public void addInitJS (@Nonnull final DataTables aDT, @Nonnull final JSPackage aJSCode, @Nonnull final JSVar aJSTable)
  {
    // empty
  }

  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // empty
  }
}
