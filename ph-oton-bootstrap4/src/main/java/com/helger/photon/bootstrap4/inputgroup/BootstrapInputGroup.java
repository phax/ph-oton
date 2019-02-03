/**
 * Copyright (C) 2018-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.inputgroup;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap input group. Children must be added in the correct order. Use
 * {@link #addChildPrefix(String)} and {@link #addChildSuffix(String)} for the
 * prepends and appends.
 *
 * @author Philip Helger
 */
public class BootstrapInputGroup extends AbstractHCDiv <BootstrapInputGroup>
{
  private EBootstrapInputGroupSize m_eSize;

  public BootstrapInputGroup ()
  {
    this (EBootstrapInputGroupSize.DEFAULT);
  }

  public BootstrapInputGroup (@Nonnull final EBootstrapInputGroupSize eSize)
  {
    setSize (eSize);
  }

  @Nonnull
  public final EBootstrapInputGroupSize getSize ()
  {
    return m_eSize;
  }

  @Nonnull
  public final BootstrapInputGroup setSize (@Nonnull final EBootstrapInputGroupSize eSize)
  {
    ValueEnforcer.notNull (eSize, "Size");
    m_eSize = eSize;
    return this;
  }

  @Nonnull
  @OverrideOnDemand
  protected HCDiv createGroupPrepend ()
  {
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_PREPEND);
  }

  @Nonnull
  @OverrideOnDemand
  protected HCDiv createGroupAppend ()
  {
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_APPEND);
  }

  @Nonnull
  private HCDiv _getOrCreatePrepend ()
  {
    final HCDiv aDiv = findFirstChildMapped (x -> x instanceof HCDiv &&
                                                  ((HCDiv) x).containsClass (CBootstrapCSS.INPUT_GROUP_PREPEND),
                                             x -> (HCDiv) x);
    return aDiv != null ? aDiv : addAndReturnChild (createGroupPrepend ());
  }

  @Nonnull
  private HCDiv _getOrCreateAppend ()
  {
    final HCDiv aDiv = findFirstChildMapped (x -> x instanceof HCDiv &&
                                                  ((HCDiv) x).containsClass (CBootstrapCSS.INPUT_GROUP_APPEND),
                                             x -> (HCDiv) x);
    return aDiv != null ? aDiv : addAndReturnChild (createGroupAppend ());
  }

  @Nonnull
  private static HCSpan _getWrapped (@Nonnull final String sText)
  {
    return new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_TEXT).addChild (sText);
  }

  @Nonnull
  private static IHCNode _getWrapped (@Nonnull final IHCNode aNode)
  {
    if (aNode instanceof AbstractHCButton <?>)
      return aNode;
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_TEXT).addChild (aNode);
  }

  @Nonnull
  public final BootstrapInputGroup addChildPrefix (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      _getOrCreatePrepend ().addChild (_getWrapped (sText));
    return this;
  }

  @Nonnull
  public final BootstrapInputGroup addChildPrefix (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
      _getOrCreatePrepend ().addChild (_getWrapped (aNode));
    return this;
  }

  @Nonnull
  public final BootstrapInputGroup addChildSuffix (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      _getOrCreateAppend ().addChild (_getWrapped (sText));
    return this;
  }

  @Nonnull
  public final BootstrapInputGroup addChildSuffix (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
      _getOrCreateAppend ().addChild (_getWrapped (aNode));
    return this;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.INPUT_GROUP, m_eSize);
  }
}
