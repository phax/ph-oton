/*
 * Copyright (C) 2018-2023 Philip Helger (www.helger.com)
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
import com.helger.photon.bootstrap4.dropdown.BootstrapDropdownMenu;

/**
 * Bootstrap input group. Children must be added in the correct order. Use
 * {@link #addChildPrefix(String)}, {@link #addChildPrefix(IHCNode)},
 * {@link #addChildSuffix(String)} and {@link #addChildSuffix(IHCNode)} for the
 * prepends and appends. The API was reworked in 8.1.3 for correct parent/child
 * relationship management.
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

  /**
   * @return The DIV with class "input-group-prepend". Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  public HCDiv createGroupPrepend ()
  {
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_PREPEND);
  }

  /**
   * @return The DIV with class "input-group-append". Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  public HCDiv createGroupAppend ()
  {
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_APPEND);
  }

  /**
   * If an existing DIV with class "input-group-prepend" is present, reuse it.
   * Else create a new one and append it. Elements in here are prepended to the
   * date edit.
   *
   * @return Never <code>null</code>.
   * @see #createGroupPrepend()
   */
  @Nonnull
  public HCDiv getOrCreateGroupPrepend ()
  {
    // Existing "prepend" present?
    final HCDiv aDiv = (HCDiv) findFirstChild (x -> x instanceof HCDiv && ((HCDiv) x).containsClass (CBootstrapCSS.INPUT_GROUP_PREPEND));
    // Prepend group MUST always be the first child, so before any control
    return aDiv != null ? aDiv : addAndReturnChildAt (0, createGroupPrepend ());
  }

  /**
   * If an existing DIV with class "input-group-append" is present, reuse it.
   * Else create a new one and append it. Elements in here are appended to the
   * date edit.
   *
   * @return Never <code>null</code>.
   * @see #createGroupAppend()
   */
  @Nonnull
  public HCDiv getOrCreateGroupAppend ()
  {
    // Existing "append" present?
    final HCDiv aDiv = (HCDiv) findFirstChild (x -> x instanceof HCDiv && ((HCDiv) x).containsClass (CBootstrapCSS.INPUT_GROUP_APPEND));
    return aDiv != null ? aDiv : addAndReturnChild (createGroupAppend ());
  }

  @Nonnull
  public static HCSpan getWrapped (@Nonnull final String sText)
  {
    return new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_TEXT).addChild (sText);
  }

  @Nonnull
  public static IHCNode getWrapped (@Nonnull final IHCNode aNode)
  {
    // Buttons and dropdowns don't need a surrounding div
    if (aNode instanceof AbstractHCButton <?> || aNode instanceof BootstrapDropdownMenu)
      return aNode;
    return new HCDiv ().addClass (CBootstrapCSS.INPUT_GROUP_TEXT).addChild (aNode);
  }

  /**
   * Add a new text element to the prepend group.
   *
   * @param sText
   *        The text to be added. May be <code>null</code>.
   * @return this for chaining
   * @see #getOrCreateGroupPrepend()
   * @see #getWrapped(String)
   */
  @Nonnull
  public final BootstrapInputGroup addChildPrefix (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      getOrCreateGroupPrepend ().addChild (getWrapped (sText));
    return this;
  }

  /**
   * Add a new node to the prepend group.
   *
   * @param aNode
   *        The node to be added. May be <code>null</code>.
   * @return this for chaining
   * @see #getOrCreateGroupPrepend()
   * @see #getWrapped(IHCNode)
   */
  @Nonnull
  public final BootstrapInputGroup addChildPrefix (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
      getOrCreateGroupPrepend ().addChild (getWrapped (aNode));
    return this;
  }

  /**
   * Add a new text element to the append group.
   *
   * @param sText
   *        The text to be added. May be <code>null</code>.
   * @return this for chaining
   * @see #getOrCreateGroupAppend()
   * @see #getWrapped(String)
   */
  @Nonnull
  public final BootstrapInputGroup addChildSuffix (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      getOrCreateGroupAppend ().addChild (getWrapped (sText));
    return this;
  }

  /**
   * Add a new node to the append group.
   *
   * @param aNode
   *        The node to be added. May be <code>null</code>.
   * @return this for chaining
   * @see #getOrCreateGroupAppend()
   * @see #getWrapped(IHCNode)
   */
  @Nonnull
  public final BootstrapInputGroup addChildSuffix (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
      getOrCreateGroupAppend ().addChild (getWrapped (aNode));
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
