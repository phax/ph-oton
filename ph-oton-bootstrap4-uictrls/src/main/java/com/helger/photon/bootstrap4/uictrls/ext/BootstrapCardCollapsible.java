/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.SimpleURL;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;
import com.helger.photon.bootstrap4.card.BootstrapCardBody;
import com.helger.photon.bootstrap4.card.BootstrapCardHeader;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.icon.fontawesome.EFontAwesome4Icon;

/**
 * Bootstrap 4 collapsible card. It contains a fixed header that is clickable.
 *
 * @author Philip Helger
 * @since 8.1.2
 */
public class BootstrapCardCollapsible extends AbstractBootstrapDiv <BootstrapCardCollapsible>
{
  public static final ICSSClassProvider CSS_CLASS_CARD_COLLAPSIBLE = DefaultCSSClassProvider.create ("card-collapsible");
  public static final ICSSClassProvider CSS_CLASS_COLLAPSED = DefaultCSSClassProvider.create ("collapsed");

  private final BootstrapCardHeader m_aHeader;
  private final BootstrapCardBody m_aBody;

  public BootstrapCardCollapsible (@Nullable final IHCNode aHeaderText)
  {
    this (aHeaderText, true);
  }

  public BootstrapCardCollapsible (@Nullable final IHCNode aHeaderText, final boolean bIsOpen)
  {
    m_aHeader = addAndReturnChild (new BootstrapCardHeader ());

    // Collapsible div
    final HCDiv aCollapseDiv = addAndReturnChild (new HCDiv ().ensureID ().addClass (CBootstrapCSS.COLLAPSE));
    if (bIsOpen)
      aCollapseDiv.addClass (CBootstrapCSS.SHOW);
    aCollapseDiv.customAttrs ().setAriaLabeledBy (m_aHeader);
    m_aBody = aCollapseDiv.addAndReturnChild (new BootstrapCardBody ());

    // Toggle in header
    final HCA aToggle = m_aHeader.addAndReturnChild (new HCA ().setHref (new SimpleURL ("#" + aCollapseDiv.getID ()))
                                                               .addClass (CBootstrapCSS.D_BLOCK));
    aToggle.customAttrs ().setDataAttr ("toggle", "collapse");
    aToggle.customAttrs ().setAriaExpanded (bIsOpen);
    aToggle.customAttrs ().setAriaControls (aCollapseDiv.getID ());
    aToggle.addChild (aHeaderText);
    aToggle.addChild (EFontAwesome4Icon.CHEVRON_DOWN.getAsNode ().addClass (CBootstrapCSS.FLOAT_RIGHT));
    if (!bIsOpen)
      aToggle.addClass (CSS_CLASS_COLLAPSED);
  }

  @Nonnull
  public BootstrapCardHeader getHeader ()
  {
    return m_aHeader;
  }

  @Nonnull
  public BootstrapCardBody getBody ()
  {
    return m_aBody;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.CARD);
    addClass (CSS_CLASS_CARD_COLLAPSIBLE);
  }

  @Override
  protected void onRegisterExternalResources (final IHCConversionSettingsToNode aConversionSettings, final boolean bForcedRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForcedRegistration);
    EFontAwesome4Icon.registerResourcesForThisRequest ();
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.BOOTSTRAP_EXT);
  }
}
