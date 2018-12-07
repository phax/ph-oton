package com.helger.photon.bootstrap4.uictrls.ext;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.tooltip.BootstrapTooltip;
import com.helger.photon.icon.fontawesome.EFontAwesome4Icon;

public class BootstrapSimpleTooltip
{
  private BootstrapSimpleTooltip ()
  {}

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final String sTitle)
  {
    final IHCElement <?> aIcon = EFontAwesome4Icon.QUESTION_CIRCLE.getAsNode ();
    final BootstrapTooltip aTooltip = new BootstrapTooltip (aIcon).setTooltipTitle (sTitle);
    return new HCNodeList ().addChild (aIcon).addChild (aTooltip);
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final IHCNode aTitle)
  {
    final IHCElement <?> aIcon = EFontAwesome4Icon.QUESTION_CIRCLE.getAsNode ();
    final BootstrapTooltip aTooltip = new BootstrapTooltip (aIcon).setTooltipTitle (aTitle);
    return new HCNodeList ().addChild (aIcon).addChild (aTooltip);
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final Iterable <? extends IHCNode> aTitle)
  {
    return createSimpleTooltip (new HCNodeList ().addChildren (aTitle));
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final IHCNode... aTitle)
  {
    return createSimpleTooltip (new HCNodeList ().addChildren (aTitle));
  }
}
