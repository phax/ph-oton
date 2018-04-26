package com.helger.photon.bootstrap4.nav;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.grouping.AbstractHCLI;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapNavItem extends AbstractHCLI <BootstrapNavItem>
{
  public BootstrapNavItem ()
  {
    addClass (CBootstrapCSS.NAV_ITEM);
  }

  @Nonnull
  public BootstrapNavLink addNavLink ()
  {
    return addAndReturnChild (new BootstrapNavLink ());
  }
}
