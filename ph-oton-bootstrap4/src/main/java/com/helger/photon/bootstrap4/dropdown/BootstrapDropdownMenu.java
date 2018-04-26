package com.helger.photon.bootstrap4.dropdown;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

/**
 * Bootstrap dropdown menu, without integration into surrounding objects.
 * 
 * @author Philip Helger
 */
public class BootstrapDropdownMenu extends AbstractBootstrapDiv <BootstrapDropdownMenu>
{
  public BootstrapDropdownMenu ()
  {}

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.DROPDOWN);
  }
}
