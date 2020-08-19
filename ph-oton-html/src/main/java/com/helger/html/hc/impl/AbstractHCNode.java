/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.hc.impl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsIterable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.EHCNodeState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.xml.microdom.IMicroNode;

/**
 * Default implementation of the {@link IHCNode} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractHCNode implements IHCNode
{
  private static final boolean DEBUG_NODE_STATE = GlobalDebug.isDebugMode ();
  private EHCNodeState m_eNodeState = EHCNodeState.INITIAL;

  @OverrideOnDemand
  public boolean hasChildren ()
  {
    return false;
  }

  @OverrideOnDemand
  @Nonnegative
  public int getChildCount ()
  {
    return 0;
  }

  @Nullable
  @OverrideOnDemand
  public ICommonsList <? extends IHCNode> getAllChildren ()
  {
    return null;
  }

  @OverrideOnDemand
  @Nullable
  public ICommonsIterable <? extends IHCNode> getChildren ()
  {
    return null;
  }

  public void forAllChildren (@Nonnull final Consumer <? super IHCNode> aConsumer)
  {
    // empty
  }

  @Nonnull
  public EContinue forAllChildrenBreakable (@Nonnull final Function <? super IHCNode, EContinue> aConsumer)
  {
    return EContinue.CONTINUE;
  }

  public void forAllChildren (@Nonnull final Predicate <? super IHCNode> aFilter, @Nonnull final Consumer <? super IHCNode> aConsumer)
  {
    // empty
  }

  public <DSTTYPE> void forAllChildrenMapped (@Nonnull final Predicate <? super IHCNode> aFilter,
                                              @Nonnull final Function <? super IHCNode, ? extends DSTTYPE> aMapper,
                                              @Nonnull final Consumer <? super DSTTYPE> aConsumer)
  {
    // empty
  }

  @Nullable
  @OverrideOnDemand
  public IHCNode getChildAtIndex (@Nonnegative final int nIndex)
  {
    return null;
  }

  @Nullable
  @OverrideOnDemand
  public IHCNode getFirstChild ()
  {
    return null;
  }

  @Override
  @Nullable
  public IHCNode findFirstChild (@Nonnull final Predicate <? super IHCNode> aFilter)
  {
    return null;
  }

  @Override
  @Nullable
  public <DSTTYPE> DSTTYPE findFirstChildMapped (@Nonnull final Predicate <? super IHCNode> aFilter,
                                                 @Nonnull final Function <? super IHCNode, ? extends DSTTYPE> aMapper)
  {
    return null;
  }

  @Nullable
  @OverrideOnDemand
  public IHCNode getLastChild ()
  {
    return null;
  }

  @Nonnull
  public EHCNodeState getNodeState ()
  {
    return m_eNodeState;
  }

  private final void _ensureNodeState (@Nonnull final EHCNodeState eNodeState)
  {
    ValueEnforcer.notNull (eNodeState, "NodeState");
    if (false)
      if (!m_eNodeState.equals (eNodeState))
        HCConsistencyChecker.consistencyError ("Expected node state " +
                                               eNodeState +
                                               " but having node state " +
                                               m_eNodeState +
                                               " in " +
                                               toString ());
  }

  /**
   * Change the node state internally. Handle with care!
   *
   * @param eNodeState
   *        The new node state. May not be <code>null</code>.
   */
  public final void internalSetNodeState (@Nonnull final EHCNodeState eNodeState)
  {
    if (DEBUG_NODE_STATE)
    {
      ValueEnforcer.notNull (eNodeState, "NodeState");
      if (m_eNodeState.isAfter (eNodeState))
        HCConsistencyChecker.consistencyError ("The new node state is invalid. Got " + eNodeState + " but having " + m_eNodeState);
    }
    m_eNodeState = eNodeState;
  }

  @OverrideOnDemand
  protected void onCustomizeNode (@Nonnull final IHCCustomizer aCustomizer,
                                  @Nonnull final EHTMLVersion eHTMLVersion,
                                  @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    aCustomizer.customizeNode (this, eHTMLVersion, aTargetNode);
  }

  public final void customizeNode (@Nullable final IHCCustomizer aCustomizer,
                                   @Nonnull final EHTMLVersion eHTMLVersion,
                                   @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    // Customize only once
    if (m_eNodeState.isBefore (EHCNodeState.CUSTOMIZED))
    {
      _ensureNodeState (EHCNodeState.INITIAL);
      if (aCustomizer != null)
        onCustomizeNode (aCustomizer, eHTMLVersion, aTargetNode);
      internalSetNodeState (EHCNodeState.CUSTOMIZED);
    }
  }

  /**
   * Finalize the node by applying any internal state that was not yet converted
   * to a HC element. This method is called at maximum once per IHCNode. It is
   * safe to still add classes, style or children to this node in this method.
   *
   * @param aConversionSettings
   *        HC conversion settings
   * @param aTargetNode
   *        The target node where additional nodes should be added
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {}

  public final void finalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                       @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    // finalize only once
    if (m_eNodeState.isBefore (EHCNodeState.FINALIZED))
    {
      _ensureNodeState (EHCNodeState.CUSTOMIZED);
      onFinalizeNodeState (aConversionSettings, aTargetNode);
      internalSetNodeState (EHCNodeState.FINALIZED);
    }
  }

  /**
   * @param aConversionSettings
   *        HC conversion settings
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {}

  public final void consistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // finalize only once
    if (m_eNodeState.isBefore (EHCNodeState.CONSISTENCY_CHECKED))
    {
      _ensureNodeState (EHCNodeState.FINALIZED);
      if (aConversionSettings.areConsistencyChecksEnabled ())
      {
        // Only if enabled
        onConsistencyCheck (aConversionSettings);
      }
      internalSetNodeState (EHCNodeState.CONSISTENCY_CHECKED);
    }
  }

  @OverrideOnDemand
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return true;
  }

  /**
   * @param aConversionSettings
   *        HC conversion settings
   * @param bForcedRegistration
   *        <code>true</code> if the registration is forced by the caller.
   */
  @OverrideOnDemand
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {}

  public final void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                               final boolean bForcedRegistration)
  {
    // register resources only once
    if (m_eNodeState.isBefore (EHCNodeState.RESOURCES_REGISTERED))
    {
      _ensureNodeState (EHCNodeState.CONSISTENCY_CHECKED);
      // Register resources only, if forced or if it can be converted to a micro
      // node
      if (bForcedRegistration || canConvertToMicroNode (aConversionSettings))
        onRegisterExternalResources (aConversionSettings, bForcedRegistration);
      internalSetNodeState (EHCNodeState.RESOURCES_REGISTERED);
    }
  }

  @Nullable
  @OverrideOnDemand
  protected abstract IMicroNode internalConvertToMicroNode (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  @Nullable
  public final IMicroNode convertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    _ensureNodeState (EHCNodeState.RESOURCES_REGISTERED);

    // Can this node be converted to a MicroNode?
    if (!canConvertToMicroNode (aConversionSettings))
      return null;

    // Main conversion
    return internalConvertToMicroNode (aConversionSettings);
  }

  @OverrideOnDemand
  @Nonnull
  public String getPlainText ()
  {
    return "";
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("NodeState", m_eNodeState).getToString ();
  }
}
