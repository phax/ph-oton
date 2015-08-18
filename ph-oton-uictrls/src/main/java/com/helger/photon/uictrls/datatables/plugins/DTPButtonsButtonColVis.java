package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.JSAssocArray;

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
  private String m_sColumns;

  public DTPButtonsButtonColVis ()
  {
    setExtend (EDTPButtonsButtonType.COL_VIS.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColVis setColumns (@Nullable final String sColumns)
  {
    m_sColumns = sColumns;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (StringHelper.hasText (m_sColumns))
      ret.add ("columns", m_sColumns);
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COL_VIS.registerExternalResources ();
  }
}
