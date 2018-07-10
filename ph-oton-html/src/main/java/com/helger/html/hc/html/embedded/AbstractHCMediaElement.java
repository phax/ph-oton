/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.embedded;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;
import com.helger.html.hc.html.IHCMediaElementChild;
import com.helger.xml.microdom.IMicroElement;

@SinceHTML5
public abstract class AbstractHCMediaElement <IMPLTYPE extends AbstractHCMediaElement <IMPLTYPE>> extends
                                             AbstractHCElementWithInternalChildren <IMPLTYPE, IHCMediaElementChild <?>>
                                             implements
                                             IHCMediaElement <IMPLTYPE>
{
  /** By default auto play is disabled */
  public static final boolean DEFAULT_AUTOPLAY = false;
  /** By default controls are disabled */
  public static final boolean DEFAULT_CONTROLS = false;
  /** By default loop is disabled */
  public static final boolean DEFAULT_LOOP = false;
  /** By default muted is disabled */
  public static final boolean DEFAULT_MUTED = false;

  private boolean m_bAutoPlay = DEFAULT_AUTOPLAY;
  private EHCPreload m_ePreload;
  private boolean m_bControls = DEFAULT_CONTROLS;
  private boolean m_bLoop = DEFAULT_LOOP;
  private boolean m_bMuted = DEFAULT_MUTED;
  private ISimpleURL m_aSrc;
  private EHCCORSSettings m_eCrossOrigin;

  public AbstractHCMediaElement (@Nonnull final EHTMLElement eElement)
  {
    super (eElement);
  }

  public final boolean isAutoPlay ()
  {
    return m_bAutoPlay;
  }

  @Nonnull
  public final IMPLTYPE setAutoPlay (final boolean bAutoPlay)
  {
    m_bAutoPlay = bAutoPlay;
    return thisAsT ();
  }

  @Nullable
  public final EHCPreload getPreload ()
  {
    return m_ePreload;
  }

  @Nonnull
  public final IMPLTYPE setPreload (@Nullable final EHCPreload ePreload)
  {
    m_ePreload = ePreload;
    return thisAsT ();
  }

  public final boolean isControls ()
  {
    return m_bControls;
  }

  @Nonnull
  public final IMPLTYPE setControls (final boolean bControls)
  {
    m_bControls = bControls;
    return thisAsT ();
  }

  public final boolean isLoop ()
  {
    return m_bLoop;
  }

  @Nonnull
  public final IMPLTYPE setLoop (final boolean bLoop)
  {
    m_bLoop = bLoop;
    return thisAsT ();
  }

  public final boolean isMuted ()
  {
    return m_bMuted;
  }

  @Nonnull
  public final IMPLTYPE setMuted (final boolean bMuted)
  {
    m_bMuted = bMuted;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final IMPLTYPE setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return thisAsT ();
  }

  @Nullable
  public final EHCCORSSettings getCrossOrigin ()
  {
    return m_eCrossOrigin;
  }

  @Nonnull
  public final IMPLTYPE setCrossOrigin (@Nullable final EHCCORSSettings eCrossOrigin)
  {
    m_eCrossOrigin = eCrossOrigin;
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addSource (@Nullable final HCSource aSource)
  {
    if (aSource != null)
      addChild (aSource);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addTrack (@Nullable final HCTrack aTrack)
  {
    if (aTrack != null)
      addChild (aTrack);
    return thisAsT ();
  }

  @Override
  @Nonnull
  @Nonempty
  protected ICommonsList <IHCMediaElementChild <?>> getChildrenFormEmitting (@Nonnull @Nonempty final ICommonsList <IHCMediaElementChild <?>> aChildren)
  {
    final ICommonsList <IHCMediaElementChild <?>> ret = new CommonsArrayList <> (aChildren.size ());
    // <source> must be first
    aChildren.findAll (c -> c.getElement ().equals (EHTMLElement.SOURCE), ret::add);
    // Add <track> and <img>
    aChildren.findAll (c -> !c.getElement ().equals (EHTMLElement.SOURCE), ret::add);
    return ret;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_bAutoPlay)
      aElement.setAttribute (CHTMLAttributes.AUTOPLAY, CHTMLAttributeValues.AUTOPLAY);
    if (m_ePreload != null)
      aElement.setAttribute (CHTMLAttributes.PRELOAD, m_ePreload);
    if (m_bControls)
      aElement.setAttribute (CHTMLAttributes.CONTROLS, CHTMLAttributeValues.CONTROLS);
    if (m_bLoop)
      aElement.setAttribute (CHTMLAttributes.LOOP, CHTMLAttributeValues.LOOP);
    if (m_bMuted)
      aElement.setAttribute (CHTMLAttributes.MUTED, CHTMLAttributeValues.MUTED);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC,
                             m_aSrc.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (m_eCrossOrigin != null)
      aElement.setAttribute (CHTMLAttributes.CROSSORIGIN, m_eCrossOrigin);
  }
}
