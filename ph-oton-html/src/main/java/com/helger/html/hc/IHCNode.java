/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.html.hc;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.hierarchy.IHasChildrenRecursive;
import com.helger.commons.hierarchy.IHasChildrenSorted;
import com.helger.html.EHTMLVersion;
import com.helger.xml.microdom.IMicroNode;

/**
 * Base interface for a main HC node.<br>
 * TODO get rid of Serializable
 *
 * @author Philip Helger
 */
public interface IHCNode extends IHasChildrenSorted <IHCNode>, IHasChildrenRecursive <IHCNode>, Serializable
{
  /**
   * @return The current node state and never <code>null</code>.
   */
  @Nonnull
  EHCNodeState getNodeState ();

  /**
   * Customize the current node with the respective customizer.<br>
   * This method is called at last once per {@link IHCNode}.
   *
   * @param aCustomizer
   *        The customizer to use. May be <code>null</code>.
   * @param eHTMLVersion
   *        The HTML version to be used. May not be <code>null</code>.
   * @param aTargetNode
   *        The target node where additional nodes should be added. May not be
   *        <code>null</code>.
   */
  void customizeNode (@Nullable IHCCustomizer aCustomizer,
                      @Nonnull EHTMLVersion eHTMLVersion,
                      @Nonnull IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode);

  /**
   * Finalize the node by applying any internal state that was not yet converted
   * to a HC element.<br>
   * This method is called at last once per {@link IHCNode}.
   *
   * @param aConversionSettings
   *        The current conversion settings to be used. May not be
   *        <code>null</code>.
   * @param aTargetNode
   *        The target node where additional nodes should be added. May not be
   *        <code>null</code>.
   */
  void finalizeNodeState (@Nonnull IHCConversionSettingsToNode aConversionSettings,
                          @Nonnull IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode);

  /**
   * Perform consistency checks on this node.<br>
   * This method is called at last once per {@link IHCNode}.
   *
   * @param aConversionSettings
   *        The current conversion settings to be used. May not be
   *        <code>null</code>.
   */
  void consistencyCheck (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  /**
   * This method checks whether the node is suitable for conversion to an
   * {@link IMicroNode}. If this node cannot be converted, no child node will be
   * converted as well!
   *
   * @param aConversionSettings
   *        The conversion settings to be used
   * @return <code>true</code> if the node can be converted to a node,
   *         <code>false</code> otherwise.
   */
  boolean canConvertToMicroNode (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  /**
   * Register external JS and CSS resources required for this node, but only if
   * this HC node can be converted to a micro node as determined by
   * {@link #canConvertToMicroNode(IHCConversionSettingsToNode)}. Using the
   * bForceRegistration parameter, this can be forced.<br>
   * This method is called at last once per {@link IHCNode}.
   *
   * @param aConversionSettings
   *        Conversion settings to be used. Never <code>null</code>.
   * @param bForceRegistration
   *        <code>true</code> to force registration, <code>false</code> to
   *        register resources only if the node can be converted to a micro
   *        node.
   */
  void registerExternalResources (@Nonnull IHCConversionSettingsToNode aConversionSettings, boolean bForceRegistration);

  /**
   * The main conversion to a micro node.<br>
   * Note: must be an IMicroNode since e.g. the text node returns an IMicroText.
   *
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  IMicroNode convertToMicroNode (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  /**
   * @return The plain text representation of this text. May not be
   *         <code>null</code>.
   */
  @Nonnull
  String getPlainText ();
}
