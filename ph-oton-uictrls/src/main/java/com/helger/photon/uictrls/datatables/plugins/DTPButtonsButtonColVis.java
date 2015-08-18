package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * A button collection that provides column visibility control.
 *
 * @author Philip Helger
 */
public class DTPButtonsButtonColVis extends DTPButtonsButton
{
  /**
   * Columns selector that defines the columns to include in the column
   * visibility button set. By default this is undefined which results in all
   * columns being selected, but any of the column-selector options can be used
   * to define a custom button set.
   */
  private IJSExpression m_aColumns;

  public DTPButtonsButtonColVis ()
  {
    setExtend (EDTPButtonsButtonType.COL_VIS.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColVis setColumns (@Nullable final String sColumns)
  {
    return setColumns (sColumns == null ? null : JSExpr.lit (sColumns));
  }

  @Nonnull
  public DTPButtonsButtonColVis setColumns (@Nullable final IJSExpression aColumns)
  {
    m_aColumns = aColumns;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (m_aColumns != null)
      ret.add ("columns", m_aColumns);
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COL_VIS.registerExternalResources ();
  }
}
