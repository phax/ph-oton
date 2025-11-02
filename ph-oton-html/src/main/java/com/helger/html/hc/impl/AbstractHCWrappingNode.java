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
import com.helger.collection.commons.ICommonsIterable;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.EHCNodeState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCWrappingNode;
import com.helger.xml.microdom.IMicroNode;

/**
 * Abstract implementation of {@link IHCWrappingNode}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractHCWrappingNode extends AbstractHCNode implements IHCWrappingNode
{
  @Override
  @NonNull
  public EHCNodeState getNodeState ()
  {
    return getWrappedNode ().getNodeState ();
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onCustomizeNode (@NonNull final IHCCustomizer aCustomizer,
                                  @NonNull final EHTMLVersion eHTMLVersion,
                                  @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    getWrappedNode ().customizeNode (aCustomizer, eHTMLVersion, aTargetNode);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    getWrappedNode ().finalizeNodeState (aConversionSettings, aTargetNode);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onConsistencyCheck (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    getWrappedNode ().consistencyCheck (aConversionSettings);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    getWrappedNode ().registerExternalResources (aConversionSettings, bForceRegistration);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    return getWrappedNode ().canConvertToMicroNode (aConversionSettings);
  }

  @Override
  @Nullable
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected IMicroNode internalConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    return getWrappedNode ().convertToMicroNode (aConversionSettings);
  }

  @Override
  @NonNull
  public String getPlainText ()
  {
    return getWrappedNode ().getPlainText ();
  }

  @Override
  @Nullable
  public ICommonsList <? extends IHCNode> getAllChildren ()
  {
    return getWrappedNode ().getAllChildren ();
  }

  @Override
  @Nullable
  public ICommonsIterable <? extends IHCNode> getChildren ()
  {
    return getWrappedNode ().getChildren ();
  }

  @Override
  public final void forAllChildren (@NonNull final Consumer <? super IHCNode> aConsumer)
  {
    getWrappedNode ().forAllChildren (aConsumer);
  }

  @Override
  public final void forAllChildren (@NonNull final Predicate <? super IHCNode> aFilter, @NonNull final Consumer <? super IHCNode> aConsumer)
  {
    getWrappedNode ().forAllChildren (aFilter, aConsumer);
  }

  @Override
  public final <DSTTYPE> void forAllChildrenMapped (@NonNull final Predicate <? super IHCNode> aFilter,
                                                    @NonNull final Function <? super IHCNode, ? extends DSTTYPE> aMapper,
                                                    @NonNull final Consumer <? super DSTTYPE> aConsumer)
  {
    getWrappedNode ().forAllChildrenMapped (aFilter, aMapper, aConsumer);
  }

  @Override
  @Nullable
  public IHCNode getChildAtIndex (final int nIndex)
  {
    return getWrappedNode ().getChildAtIndex (nIndex);
  }

  @Override
  @Nullable
  public IHCNode getFirstChild ()
  {
    return getWrappedNode ().getFirstChild ();
  }

  @Override
  @Nullable
  public IHCNode getLastChild ()
  {
    return getWrappedNode ().getLastChild ();
  }

  @Override
  public boolean hasChildren ()
  {
    return getWrappedNode ().hasChildren ();
  }

  @Override
  @Nonnegative
  public int getChildCount ()
  {
    return getWrappedNode ().getChildCount ();
  }
}
