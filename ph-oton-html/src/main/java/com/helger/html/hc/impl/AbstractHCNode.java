/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EContinue;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.ICommonsIterable;
import com.helger.collection.commons.ICommonsList;
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
  @Override
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

  @Override
  public void forAllChildren (@NonNull final Consumer <? super IHCNode> aConsumer)
  {
    // empty
  }

  @NonNull
  @Override
  public EContinue forAllChildrenBreakable (@NonNull final Function <? super IHCNode, EContinue> aConsumer)
  {
    return EContinue.CONTINUE;
  }

  @Override
  public void forAllChildren (@NonNull final Predicate <? super IHCNode> aFilter,
                              @NonNull final Consumer <? super IHCNode> aConsumer)
  {
    // empty
  }

  @Override
  public <DSTTYPE> void forAllChildrenMapped (@NonNull final Predicate <? super IHCNode> aFilter,
                                              @NonNull final Function <? super IHCNode, ? extends DSTTYPE> aMapper,
                                              @NonNull final Consumer <? super DSTTYPE> aConsumer)
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
  @Override
  public IHCNode getFirstChild ()
  {
    return null;
  }

  @Override
  @Nullable
  public IHCNode findFirstChild (@NonNull final Predicate <? super IHCNode> aFilter)
  {
    return null;
  }

  @Override
  @Nullable
  public <DSTTYPE> DSTTYPE findFirstChildMapped (@NonNull final Predicate <? super IHCNode> aFilter,
                                                 @NonNull final Function <? super IHCNode, ? extends DSTTYPE> aMapper)
  {
    return null;
  }

  @Nullable
  @OverrideOnDemand
  @Override
  public IHCNode getLastChild ()
  {
    return null;
  }

  @NonNull
  public EHCNodeState getNodeState ()
  {
    return m_eNodeState;
  }

  private final void _ensureNodeState (@NonNull final EHCNodeState eNodeState)
  {
    ValueEnforcer.notNull (eNodeState, "NodeState");
    if (false)
    {
      final EHCNodeState eMyNodeState = getNodeState ();
      if (!eMyNodeState.equals (eNodeState))
        HCConsistencyChecker.consistencyError ("Expected node state " +
                                               eNodeState +
                                               " but having node state " +
                                               eMyNodeState +
                                               " in " +
                                               toString ());
    }
  }

  /**
   * Change the node state internally. Handle with care!
   *
   * @param eNodeState
   *        The new node state. May not be <code>null</code>.
   */
  public final void internalSetNodeState (@NonNull final EHCNodeState eNodeState)
  {
    if (DEBUG_NODE_STATE)
    {
      ValueEnforcer.notNull (eNodeState, "NodeState");
      final EHCNodeState eMyNodeState = getNodeState ();
      if (eMyNodeState.isAfter (eNodeState))
        HCConsistencyChecker.consistencyError ("The new node state is invalid. Got " +
                                               eNodeState +
                                               " but having " +
                                               eMyNodeState);
    }
    m_eNodeState = eNodeState;
  }

  @OverrideOnDemand
  protected void onCustomizeNode (@NonNull final IHCCustomizer aCustomizer,
                                  @NonNull final EHTMLVersion eHTMLVersion,
                                  @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    aCustomizer.customizeNode (this, eHTMLVersion, aTargetNode);
  }

  public final void customizeNode (@Nullable final IHCCustomizer aCustomizer,
                                   @NonNull final EHTMLVersion eHTMLVersion,
                                   @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
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
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    // empty
  }

  public final void finalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                       @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
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
  protected void onConsistencyCheck (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    // empty
  }

  public final void consistencyCheck (@NonNull final IHCConversionSettingsToNode aConversionSettings)
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
  public boolean canConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
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
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {}

  public final void registerExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
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
  protected abstract IMicroNode internalConvertToMicroNode (@NonNull IHCConversionSettingsToNode aConversionSettings);

  @Nullable
  public final IMicroNode convertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    _ensureNodeState (EHCNodeState.RESOURCES_REGISTERED);

    // Can this node be converted to a MicroNode?
    if (!canConvertToMicroNode (aConversionSettings))
      return null;

    // Main conversion
    return internalConvertToMicroNode (aConversionSettings);
  }

  @OverrideOnDemand
  @NonNull
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
