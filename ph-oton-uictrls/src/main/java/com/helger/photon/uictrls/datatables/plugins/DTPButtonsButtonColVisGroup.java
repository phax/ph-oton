package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.JSAssocArray;

/**
 * Show and hide multiple columns. Please note that unlike most other buttons,
 * the button text ({@link #setText(String)}) option is not predefined as there
 * is no sensible default that could be applied. You must set a text property
 * for this button.
 *
 * @author Philip Helger
 */
public class DTPButtonsButtonColVisGroup extends DTPButtonsButton
{
  /** Column to make visible */
  private String m_sShow;
  /** Column to remove from the visible display */
  private String m_sHide;

  public DTPButtonsButtonColVisGroup ()
  {
    setExtend (EDTPButtonsButtonType.COL_VIS_GROUP.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setShow (@Nullable final String sShow)
  {
    m_sShow = sShow;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setHide (@Nullable final String sHide)
  {
    m_sHide = sHide;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (StringHelper.hasText (m_sShow))
      ret.add ("show", m_sShow);
    if (StringHelper.hasText (m_sHide))
      ret.add ("hide", m_sHide);
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COL_VIS_GROUP.registerExternalResources ();
  }
}
