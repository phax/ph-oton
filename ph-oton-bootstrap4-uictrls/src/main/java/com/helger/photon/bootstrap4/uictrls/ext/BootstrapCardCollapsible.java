/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.OverrideOnDemand;
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

  private final IHCNode m_aHeaderText;
  private final boolean m_bIsOpen;
  private final BootstrapCardHeader m_aHeader;
  private final HCDiv m_aCollapseDiv;
  private final BootstrapCardBody m_aBody;

  public BootstrapCardCollapsible (@Nullable final IHCNode aHeaderText)
  {
    this (aHeaderText, true);
  }

  public BootstrapCardCollapsible (@Nullable final IHCNode aHeaderText, final boolean bIsOpen)
  {
    m_aHeaderText = aHeaderText;
    m_bIsOpen = bIsOpen;
    m_aHeader = addAndReturnChild (new BootstrapCardHeader ());

    // Collapsible div
    m_aCollapseDiv = addAndReturnChild (new HCDiv ().ensureID ().addClass (CBootstrapCSS.COLLAPSE));
    if (bIsOpen)
      m_aCollapseDiv.addClass (CBootstrapCSS.SHOW);
    m_aCollapseDiv.customAttrs ().setAriaLabeledBy (m_aHeader);
    m_aBody = m_aCollapseDiv.addAndReturnChild (new BootstrapCardBody ());
  }

  @Nonnull
  public final IHCNode getHeaderText ()
  {
    return m_aHeaderText;
  }

  public final boolean isOpen ()
  {
    return m_bIsOpen;
  }

  @Nonnull
  public final BootstrapCardHeader getHeader ()
  {
    return m_aHeader;
  }

  @Nonnull
  public final HCDiv getCollapseDiv ()
  {
    return m_aCollapseDiv;
  }

  @Nonnull
  public final BootstrapCardBody getBody ()
  {
    return m_aBody;
  }

  @Nonnull
  @OverrideOnDemand
  protected IHCNode createCloseIcon ()
  {
    return EFontAwesome4Icon.CHEVRON_DOWN.getAsNode ();
  }

  @Nonnull
  @OverrideOnDemand
  protected HCA createToggle ()
  {
    final HCA aToggle = new HCA ().setHref (new SimpleURL ("#" + m_aCollapseDiv.getID ()))
                                  .addClass (CBootstrapCSS.D_FLEX)
                                  .addClass (CBootstrapCSS.JUSTIFY_CONTENT_BETWEEN);
    aToggle.customAttrs ().setDataAttr ("toggle", "collapse");
    aToggle.customAttrs ().setAriaExpanded (m_bIsOpen);
    aToggle.customAttrs ().setAriaControls (m_aCollapseDiv.getID ());
    aToggle.addChild (m_aHeaderText);
    aToggle.addChild (createCloseIcon ());
    if (!m_bIsOpen)
      aToggle.addClass (CSS_CLASS_COLLAPSED);
    return aToggle;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.CARD);
    addClass (CSS_CLASS_CARD_COLLAPSIBLE);

    // Toggle in header
    final HCA aToggle = createToggle ();
    m_aHeader.addChild (aToggle);
  }

  @Override
  protected void onRegisterExternalResources (final IHCConversionSettingsToNode aConversionSettings, final boolean bForcedRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForcedRegistration);
    EFontAwesome4Icon.registerResourcesForThisRequest ();
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.BOOTSTRAP_EXT);
  }
}
