package com.helger.photon.bootstrap4.listgroup;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.grouping.AbstractHCUL;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapListGroup extends AbstractHCUL <BootstrapListGroup>
{
  public BootstrapListGroup ()
  {
    addClass (CBootstrapCSS.LIST_GROUP);
  }

  @Override
  @Nonnull
  protected HCLI createEmptyItem ()
  {
    return new HCLI ().addClass (CBootstrapCSS.LIST_GROUP_ITEM);
  }
}
