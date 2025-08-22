/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.bootstrap4.uictrls.ext;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.tooltip.BootstrapTooltip;
import com.helger.photon.icon.fontawesome.EFontAwesome4Icon;

import jakarta.annotation.Nonnull;

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
