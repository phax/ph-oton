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
package com.helger.photon.bootstrap4.tooltip;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;

/**
 * Bootstrap Tooltip
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class BootstrapTooltip extends HCScriptInlineOnDocumentReady
{
  /**
   * This event fires immediately when the show instance method is called.
   */
  public static final String JS_EVENT_SHOW = "show.bs.tooltip";
  /**
   * This event is fired when the tooltip has been made visible to the user (will
   * wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.tooltip";
  /**
   * This event is fired immediately when the hide instance method has been
   * called.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.tooltip";
  /**
   * This event is fired when the tooltip has finished being hidden from the user
   * (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.tooltip";
  /**
   * This event is fired after the show.bs.tooltip event when the tooltip template
   * has been added to the DOM.
   */
  public static final String JS_EVENT_INSERTED = "inserted.bs.tooltip";

  public static final boolean DEFAULT_ANIMATION = true;
  public static final boolean DEFAULT_HTML = false;
  public static final EBootstrapTooltipPosition DEFAULT_PLACEMENT = EBootstrapTooltipPosition.TOP;
  @CodingStyleguideUnaware
  public static final Set <EBootstrapTooltipTrigger> DEFAULT_TRIGGER = new CommonsLinkedHashSet <> (EBootstrapTooltipTrigger.HOVER,
                                                                                                    EBootstrapTooltipTrigger.FOCUS).getAsUnmodifiable ();
  public static final EBootstrapTooltipFallbackPlacement DEFAULT_FALLBACK_PLACEMENT = EBootstrapTooltipFallbackPlacement.FLIP;
  public static final EBootstrapTooltipBoundary DEFAULT_BOUNDARY = EBootstrapTooltipBoundary.SCROLL_PARENT;

  private final IJQuerySelector m_aSelector;
  private boolean m_bAnimation = DEFAULT_ANIMATION;
  private IJQuerySelector m_aContainer;
  private int m_nDelayShow = 0;
  private int m_nDelayHide = 0;
  private boolean m_bHTML = DEFAULT_HTML;
  private EBootstrapTooltipPosition m_ePlacement = DEFAULT_PLACEMENT;
  private JSAnonymousFunction m_aPlacementFunc;
  private String m_sSelector;
  private String m_sTooltipTitle;
  private JSAnonymousFunction m_aTooltipTitleFunc;
  @CodingStyleguideUnaware
  private Set <EBootstrapTooltipTrigger> m_aTrigger = DEFAULT_TRIGGER;
  private String m_sOffset;
  private EBootstrapTooltipFallbackPlacement m_eFallbackPlacement = DEFAULT_FALLBACK_PLACEMENT;
  private EBootstrapTooltipBoundary m_eBoundary = DEFAULT_BOUNDARY;

  public BootstrapTooltip (@Nonnull final IHCElement <?> aElement)
  {
    this (JQuerySelector.id (aElement));
  }

  public BootstrapTooltip (@Nonnull final IJQuerySelector aSelector)
  {
    ValueEnforcer.notNull (aSelector, "Selector");
    m_aSelector = aSelector;
  }

  public boolean isAnimation ()
  {
    return m_bAnimation;
  }

  @Nonnull
  public BootstrapTooltip setAnimation (final boolean bAnimation)
  {
    m_bAnimation = bAnimation;
    return this;
  }

  public boolean isHTML ()
  {
    return m_bHTML;
  }

  @Nonnull
  public BootstrapTooltip setHTML (final boolean bHTML)
  {
    m_bHTML = bHTML;
    return this;
  }

  @Nullable
  public EBootstrapTooltipPosition getPlacementPosition ()
  {
    return m_ePlacement;
  }

  @Nullable
  public JSAnonymousFunction getPlacementFunction ()
  {
    return m_aPlacementFunc;
  }

  @Nonnull
  public BootstrapTooltip setPlacement (@Nonnull final EBootstrapTooltipPosition ePosition)
  {
    ValueEnforcer.notNull (ePosition, "Position");
    m_ePlacement = ePosition;
    m_aPlacementFunc = null;
    return this;
  }

  /**
   * @param aFunction
   *        Callback function that is called with the tooltip DOM node as its
   *        first argument and the triggering element DOM node as its second. The
   *        this context is set to the tooltip instance.
   * @return this
   */
  @Nonnull
  public BootstrapTooltip setPlacement (@Nonnull final JSAnonymousFunction aFunction)
  {
    ValueEnforcer.notNull (aFunction, "Function");
    m_ePlacement = null;
    m_aPlacementFunc = aFunction;
    return this;
  }

  @Nullable
  public String getSelector ()
  {
    return m_sSelector;
  }

  @Nonnull
  public BootstrapTooltip setSelector (@Nullable final String sSelector)
  {
    m_sSelector = sSelector;
    return this;
  }

  @Nullable
  public String getTooltipTitleString ()
  {
    return m_sTooltipTitle;
  }

  @Nullable
  public JSAnonymousFunction getTooltipTitleFunction ()
  {
    return m_aTooltipTitleFunc;
  }

  @Nonnull
  public BootstrapTooltip setTooltipTitle (@Nullable final String sTitle)
  {
    m_sTooltipTitle = sTitle;
    m_aTooltipTitleFunc = null;
    return this;
  }

  @Nonnull
  public BootstrapTooltip setTooltipTitle (@Nullable final IHCNode aTooltipTitle)
  {
    setHTML (true);
    m_sTooltipTitle = aTooltipTitle == null ? null : HCRenderer.getAsHTMLStringWithoutNamespaces (aTooltipTitle);
    m_aTooltipTitleFunc = null;
    return this;
  }

  /**
   * @param aFunction
   *        Callback function with 1 parameter: <code>(this.$element[0])</code>
   * @return this
   */
  @Nonnull
  public BootstrapTooltip setTooltipTitle (@Nullable final JSAnonymousFunction aFunction)
  {
    m_sTooltipTitle = null;
    m_aTooltipTitleFunc = aFunction;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public ICommonsList <EBootstrapTooltipTrigger> getTrigger ()
  {
    return CollectionHelper.newList (m_aTrigger);
  }

  @Nonnull
  public BootstrapTooltip setTrigger (@Nullable final EBootstrapTooltipTrigger... aTrigger)
  {
    // Avoid duplicates!
    m_aTrigger = CollectionHelper.newSortedSet (aTrigger);
    return this;
  }

  @Nonnull
  public BootstrapTooltip setTrigger (@Nullable final Collection <EBootstrapTooltipTrigger> aTrigger)
  {
    // Avoid duplicates!
    m_aTrigger = CollectionHelper.newSortedSet (aTrigger);
    return this;
  }

  @Nonnegative
  public int getDelayShow ()
  {
    return m_nDelayShow;
  }

  @Nonnegative
  public int getDelayHide ()
  {
    return m_nDelayHide;
  }

  @Nonnull
  public BootstrapTooltip setDelay (@Nonnegative final int nDelay)
  {
    return setDelay (nDelay, nDelay);
  }

  @Nonnull
  public BootstrapTooltip setDelay (@Nonnegative final int nShowDelay, @Nonnegative final int nHideDelay)
  {
    ValueEnforcer.isGE0 (nShowDelay, "ShowDelay");
    ValueEnforcer.isGE0 (nHideDelay, "HideDelay");

    m_nDelayShow = nShowDelay;
    m_nDelayHide = nHideDelay;
    return this;
  }

  @Nullable
  public IJQuerySelector getContainer ()
  {
    return m_aContainer;
  }

  @Nonnull
  public BootstrapTooltip setContainer (@Nonnull final EHTMLElement eContainer)
  {
    ValueEnforcer.notNull (eContainer, "Container");

    return setContainer (JQuerySelector.element (eContainer));
  }

  @Nonnull
  public BootstrapTooltip setContainer (@Nullable final IJQuerySelector aContainer)
  {
    m_aContainer = aContainer;
    return this;
  }

  @Nullable
  public String getOffset ()
  {
    return m_sOffset;
  }

  @Nonnull
  public BootstrapTooltip setOffset (@Nullable final String sOffset)
  {
    m_sOffset = sOffset;
    return this;
  }

  @Nonnull
  public BootstrapTooltip setOffset (final int nOffset)
  {
    return setOffset (Integer.toString (nOffset));
  }

  @Nullable
  public EBootstrapTooltipFallbackPlacement getFallbackPlacement ()
  {
    return m_eFallbackPlacement;
  }

  @Nonnull
  public BootstrapTooltip setFallbackPlacement (@Nullable final EBootstrapTooltipFallbackPlacement eFallbackPlacement)
  {
    m_eFallbackPlacement = eFallbackPlacement;
    return this;
  }

  @Nullable
  public EBootstrapTooltipBoundary getBoundary ()
  {
    return m_eBoundary;
  }

  @Nonnull
  public BootstrapTooltip setBoundary (@Nullable final EBootstrapTooltipBoundary eBoundary)
  {
    m_eBoundary = eBoundary;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();
    if (m_bAnimation != DEFAULT_ANIMATION)
      aOptions.add ("animation", m_bAnimation);
    if (m_aContainer != null)
      aOptions.add ("container", m_aContainer.getExpression ());
    if (m_nDelayShow > 0 || m_nDelayHide > 0)
    {
      if (m_nDelayShow == m_nDelayHide)
        aOptions.add ("delay", m_nDelayShow);
      else
        aOptions.add ("delay", new JSAssocArray ().add ("show", m_nDelayShow).add ("hide", m_nDelayHide));
    }
    if (m_bHTML != DEFAULT_HTML)
      aOptions.add ("html", m_bHTML);
    if (m_ePlacement != null)
      aOptions.add ("placement", m_ePlacement.getValue ());
    else
      aOptions.add ("placement", m_aPlacementFunc);
    if (StringHelper.hasText (m_sSelector))
      aOptions.add ("selector", m_sSelector);
    if (StringHelper.hasText (m_sTooltipTitle))
      aOptions.add ("title", m_sTooltipTitle);
    else
      if (m_aTooltipTitleFunc != null)
        aOptions.add ("title", m_aTooltipTitleFunc);
    if (CollectionHelper.isNotEmpty (m_aTrigger) && !DEFAULT_TRIGGER.equals (m_aTrigger))
      aOptions.add ("trigger", StringHelper.getImplodedMapped (' ', m_aTrigger, EBootstrapTooltipTrigger::getValue));
    if (StringHelper.hasText (m_sOffset))
      aOptions.add ("offset", m_sOffset);
    if (m_eBoundary != null)
      aOptions.add ("boundary", m_eBoundary.getValue ());
    return aOptions;
  }

  @Nonnull
  public JSInvocation jsInvoke ()
  {
    return m_aSelector.invoke ().invoke ("tooltip");
  }

  @Nonnull
  public JSInvocation jsAttach ()
  {
    return jsInvoke ().arg (getJSOptions ());
  }

  @Nonnull
  public JSInvocation jsShow ()
  {
    return jsInvoke ().arg ("show");
  }

  @Nonnull
  public JSInvocation jsHide ()
  {
    return jsInvoke ().arg ("hide");
  }

  @Nonnull
  public JSInvocation jsToggle ()
  {
    return jsInvoke ().arg ("toggle");
  }

  @Nonnull
  public JSInvocation jsDispose ()
  {
    return jsInvoke ().arg ("dispose");
  }

  @Nonnull
  public JSInvocation jsEnable ()
  {
    return jsInvoke ().arg ("enable");
  }

  @Nonnull
  public JSInvocation jsDisable ()
  {
    return jsInvoke ().arg ("disable");
  }

  @Nonnull
  public JSInvocation jsToggleEnabled ()
  {
    return jsInvoke ().arg ("toggleEnabled");
  }

  @Nonnull
  public JSInvocation jsUpdate ()
  {
    return jsInvoke ().arg ("update");
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    setOnDocumentReadyCode (jsAttach ());
  }
}
