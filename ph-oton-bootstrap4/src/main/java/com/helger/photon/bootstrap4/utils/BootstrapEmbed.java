package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

/**
 * Bootstrap responsive embed. See
 * https://getbootstrap.com/docs/4.1/utilities/embed/
 * 
 * @author Philip Helger
 */
public class BootstrapEmbed extends AbstractBootstrapDiv <BootstrapEmbed>
{
  private EEmbedAspectRatio m_eAspectRatio;

  public BootstrapEmbed (@Nonnull final EEmbedAspectRatio eAspectRatio)
  {
    setAspectRatio (eAspectRatio);
  }

  @Nullable
  public EEmbedAspectRatio getAspectRatio ()
  {
    return m_eAspectRatio;
  }

  @Nonnull
  public final BootstrapEmbed setAspectRatio (@Nonnull final EEmbedAspectRatio eAspectRatio)
  {
    ValueEnforcer.notNull (eAspectRatio, "AspectRatio");
    m_eAspectRatio = eAspectRatio;
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.EMBED_RESPONSIVE, m_eAspectRatio);
  }
}
