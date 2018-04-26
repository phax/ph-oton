package com.helger.photon.bootstrap4.nav;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapNavLink extends AbstractHCA <BootstrapNavLink>
{
  private static final ISimpleURL DEFAULT_HREF = new SimpleURL ("#");

  private boolean m_bActive = false;
  private boolean m_bDisabled = false;

  public BootstrapNavLink ()
  {
    this (null);
  }

  public BootstrapNavLink (@Nullable final ISimpleURL aURL)
  {
    super (aURL != null ? aURL : DEFAULT_HREF);
  }

  public boolean isActive ()
  {
    return m_bActive;
  }

  @Nonnull
  public BootstrapNavLink setActive (final boolean bActive)
  {
    m_bActive = bActive;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public BootstrapNavLink setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.NAV_LINK);
    if (m_bActive)
      addClass (CBootstrapCSS.ACTIVE);
    if (m_bDisabled)
      addClass (CBootstrapCSS.DISABLED);
  }
}
