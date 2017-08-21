package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.spacing.BootstrapSpacingBuilder;

public class BootstrapCol extends AbstractHCDiv <BootstrapCol>
{
  private EBootstrapColOrder m_eOrder;
  private BootstrapSpacingBuilder m_aMargin;

  public BootstrapCol ()
  {}

  @Nonnull
  public BootstrapCol setOrder (@Nullable final EBootstrapColOrder eOrder)
  {
    m_eOrder = eOrder;
    return this;
  }

  @Nullable
  public EBootstrapColOrder getOrder ()
  {
    return m_eOrder;
  }

  @Nonnull
  public BootstrapCol setMargin (@Nullable final BootstrapSpacingBuilder aMargin)
  {
    m_aMargin = aMargin;
    return this;
  }

  @Nullable
  public BootstrapSpacingBuilder getMargin ()
  {
    return m_aMargin;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_eOrder);
    addClass (m_aMargin);
  }
}
