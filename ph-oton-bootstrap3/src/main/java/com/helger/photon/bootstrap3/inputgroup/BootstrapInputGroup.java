/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.inputgroup;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.button.BootstrapButton;

public class BootstrapInputGroup extends AbstractHCDiv <BootstrapInputGroup>
{
  private final EBootstrapInputGroupSize m_eSize;
  private final ICommonsList <IHCNode> m_aPrefixes = new CommonsArrayList <> ();
  private final IHCNode m_aInput;
  private final ICommonsList <IHCNode> m_aSuffixes = new CommonsArrayList <> ();

  public BootstrapInputGroup (@Nonnull final IHCNode aInput)
  {
    this (EBootstrapInputGroupSize.DEFAULT, aInput);
  }

  public BootstrapInputGroup (@Nonnull final EBootstrapInputGroupSize eSize, @Nonnull final IHCNode aInput)
  {
    m_eSize = ValueEnforcer.notNull (eSize, "Size");
    m_aInput = ValueEnforcer.notNull (aInput, "Input");
  }

  @Nonnull
  public EBootstrapInputGroupSize getSize ()
  {
    return m_eSize;
  }

  @Nonnull
  public BootstrapInputGroup setPrefix (@Nullable final String sPrefix)
  {
    return setPrefix (HCTextNode.createOnDemand (sPrefix));
  }

  @Nonnull
  public BootstrapInputGroup setPrefix (@Nullable final IHCNode aPrefix)
  {
    m_aPrefixes.clear ();
    return addPrefix (aPrefix);
  }

  @Nonnull
  public BootstrapInputGroup addPrefix (@Nullable final String sPrefix)
  {
    return addPrefix (HCTextNode.createOnDemand (sPrefix));
  }

  @Nonnull
  public BootstrapInputGroup addPrefix (@Nullable final IHCNode aPrefix)
  {
    if (aPrefix != null)
      m_aPrefixes.add (aPrefix);
    return this;
  }

  @Nonnull
  public BootstrapInputGroup addPrefix (@Nonnegative final int nIndex, @Nullable final String sPrefix)
  {
    return addPrefix (nIndex, HCTextNode.createOnDemand (sPrefix));
  }

  @Nonnull
  public BootstrapInputGroup addPrefix (@Nonnegative final int nIndex, @Nullable final IHCNode aPrefix)
  {
    if (nIndex < 0)
      throw new IllegalArgumentException ("Index too small: " + nIndex);

    if (aPrefix != null)
      if (nIndex >= getPrefixCount ())
        m_aPrefixes.add (aPrefix);
      else
        m_aPrefixes.add (nIndex, aPrefix);
    return this;
  }

  @Nonnull
  public EChange removePrefixAtIndex (@Nonnegative final int nIndex)
  {
    return m_aPrefixes.removeAtIndex (nIndex);
  }

  @Nonnull
  public BootstrapInputGroup removeAllPrefixes ()
  {
    m_aPrefixes.clear ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IHCNode> getAllPrefixes ()
  {
    return m_aPrefixes.getClone ();
  }

  public boolean hasPrefixes ()
  {
    return m_aPrefixes.isNotEmpty ();
  }

  @Nonnegative
  public int getPrefixCount ()
  {
    return m_aPrefixes.size ();
  }

  @Nonnull
  public IHCNode getInput ()
  {
    return m_aInput;
  }

  @Nonnull
  public BootstrapInputGroup setSuffix (@Nullable final String sSuffix)
  {
    return setSuffix (HCTextNode.createOnDemand (sSuffix));
  }

  @Nonnull
  public BootstrapInputGroup setSuffix (@Nullable final IHCNode aSuffix)
  {
    m_aSuffixes.clear ();
    return addSuffix (aSuffix);
  }

  @Nonnull
  public BootstrapInputGroup addSuffix (@Nullable final String sSuffix)
  {
    return addSuffix (HCTextNode.createOnDemand (sSuffix));
  }

  @Nonnull
  public BootstrapInputGroup addSuffix (@Nullable final IHCNode aSuffix)
  {
    if (aSuffix != null)
      m_aSuffixes.add (aSuffix);
    return this;
  }

  @Nonnull
  public BootstrapInputGroup addSuffix (@Nonnegative final int nIndex, @Nullable final String sSuffix)
  {
    return addSuffix (nIndex, HCTextNode.createOnDemand (sSuffix));
  }

  @Nonnull
  public BootstrapInputGroup addSuffix (@Nonnegative final int nIndex, @Nullable final IHCNode aSuffix)
  {
    if (nIndex < 0)
      throw new IllegalArgumentException ("Index too small: " + nIndex);

    if (aSuffix != null)
      if (nIndex >= getSuffixCount ())
        m_aSuffixes.add (aSuffix);
      else
        m_aSuffixes.add (nIndex, aSuffix);
    return this;
  }

  @Nonnull
  public EChange removeSuffixAtIndex (@Nonnegative final int nIndex)
  {
    return m_aSuffixes.removeAtIndex (nIndex);
  }

  @Nonnull
  public BootstrapInputGroup removeAllSuffixes ()
  {
    m_aSuffixes.clear ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IHCNode> getAllSuffixes ()
  {
    return m_aSuffixes.getClone ();
  }

  public boolean hasSuffixes ()
  {
    return m_aSuffixes.isNotEmpty ();
  }

  @Nonnegative
  public int getSuffixCount ()
  {
    return m_aSuffixes.size ();
  }

  public boolean hasNeitherPrefixNoSuffix ()
  {
    return m_aPrefixes.isEmpty () && m_aSuffixes.isEmpty ();
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.INPUT_GROUP, m_eSize);

    for (final IHCNode aPrefix : m_aPrefixes)
    {
      if (aPrefix instanceof BootstrapButton)
        addChild (new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_BTN).addChild (aPrefix));
      else
        addChild (new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_ADDON).addChild (aPrefix));
    }

    addChild (m_aInput);

    for (final IHCNode aSuffix : m_aSuffixes)
    {
      if (aSuffix instanceof BootstrapButton)
        addChild (new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_BTN).addChild (aSuffix));
      else
        addChild (new HCSpan ().addClass (CBootstrapCSS.INPUT_GROUP_ADDON).addChild (aSuffix));
    }
  }
}
