/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.tooltip;

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
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.bootstrap3.EBootstrapIcon;

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
   * This event is fired when the tooltip has been made visible to the user
   * (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.tooltip";
  /**
   * This event is fired immediately when the hide instance method has been
   * called.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.tooltip";
  /**
   * This event is fired when the tooltip has finished being hidden from the
   * user (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.tooltip";
  /**
   * This event is fired after the show.bs.tooltip event when the tooltip
   * template has been added to the DOM.
   */
  public static final String JS_EVENT_INSERTED = "inserted.bs.tooltip";

  public static final boolean DEFAULT_ANIMATION = true;
  public static final boolean DEFAULT_HTML = false;
  public static final EBootstrapTooltipPosition DEFAULT_PLACEMENT = EBootstrapTooltipPosition.TOP;
  public static final boolean DEFAULT_PLACEMENT_AUTO = false;
  @CodingStyleguideUnaware
  public static final Set <EBootstrapTooltipTrigger> DEFAULT_TRIGGER = new CommonsLinkedHashSet <> (EBootstrapTooltipTrigger.HOVER,
                                                                                                    EBootstrapTooltipTrigger.FOCUS).getAsUnmodifiable ();

  private final IJQuerySelector m_aSelector;
  private boolean m_bAnimation = DEFAULT_ANIMATION;
  private boolean m_bHTML = DEFAULT_HTML;
  private EBootstrapTooltipPosition m_ePlacement = DEFAULT_PLACEMENT;
  private boolean m_bPlacementAuto = DEFAULT_PLACEMENT_AUTO;
  private JSAnonymousFunction m_aPlacementFunc;
  private String m_sSelector;
  private String m_sTooltipTitle;
  private JSAnonymousFunction m_aTooltipTitleFunc;
  @CodingStyleguideUnaware
  private Set <EBootstrapTooltipTrigger> m_aTrigger = DEFAULT_TRIGGER;
  private int m_nShowDelay = 0;
  private int m_nHideDelay = 0;
  private IJQuerySelector m_aContainer;

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

  public boolean isPlacementAuto ()
  {
    return m_bPlacementAuto;
  }

  @Nullable
  public JSAnonymousFunction getPlacementFunction ()
  {
    return m_aPlacementFunc;
  }

  @Nonnull
  public BootstrapTooltip setPlacement (@Nonnull final EBootstrapTooltipPosition ePosition, final boolean bAutoAlign)
  {
    ValueEnforcer.notNull (ePosition, "Position");
    m_ePlacement = ePosition;
    m_bPlacementAuto = bAutoAlign;
    m_aPlacementFunc = null;
    return this;
  }

  /**
   * @param aFunction
   *        Callback function with 3 parameters:
   *        <code>(this, $tip[0], this.$element[0])</code>
   * @return this
   */
  @Nonnull
  public BootstrapTooltip setPlacement (@Nonnull final JSAnonymousFunction aFunction)
  {
    ValueEnforcer.notNull (aFunction, "Function");
    m_ePlacement = null;
    m_bPlacementAuto = DEFAULT_PLACEMENT_AUTO;
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
  public int getShowDelay ()
  {
    return m_nShowDelay;
  }

  @Nonnegative
  public int getHideDelay ()
  {
    return m_nHideDelay;
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

    m_nShowDelay = nShowDelay;
    m_nHideDelay = nHideDelay;
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

  @Nonnull
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();
    if (m_bAnimation != DEFAULT_ANIMATION)
      aOptions.add ("animation", m_bAnimation);
    if (m_bHTML != DEFAULT_HTML)
      aOptions.add ("html", m_bHTML);
    if (m_ePlacement != null)
    {
      if (!m_ePlacement.equals (DEFAULT_PLACEMENT) || m_bPlacementAuto != DEFAULT_PLACEMENT_AUTO)
        aOptions.add ("placement", m_ePlacement.getValue () + (m_bPlacementAuto ? " auto" : ""));
    }
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
    {
      final StringBuilder aSB = new StringBuilder ();
      for (final EBootstrapTooltipTrigger eTrigger : m_aTrigger)
      {
        if (aSB.length () > 0)
          aSB.append (' ');
        aSB.append (eTrigger.getValue ());
      }
      aOptions.add ("trigger", aSB.toString ());
    }
    if (m_nShowDelay > 0 || m_nHideDelay > 0)
    {
      if (m_nShowDelay == m_nHideDelay)
        aOptions.add ("delay", m_nShowDelay);
      else
        aOptions.add ("delay", new JSAssocArray ().add ("show", m_nShowDelay).add ("hide", m_nHideDelay));
    }
    if (m_aContainer != null)
      aOptions.add ("container", m_aContainer.getExpression ());
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
  public JSInvocation jsDestroy ()
  {
    return jsInvoke ().arg ("destroy");
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    setOnDocumentReadyCode (jsAttach ());
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final String sTitle)
  {
    final IHCElement <?> aIcon = EBootstrapIcon.QUESTION_SIGN.getAsNode ();
    final BootstrapTooltip aTooltip = new BootstrapTooltip (aIcon).setTooltipTitle (sTitle);
    return new HCNodeList ().addChild (aIcon).addChild (aTooltip);
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final IHCNode aTitle)
  {
    final IHCElement <?> aIcon = EBootstrapIcon.QUESTION_SIGN.getAsNode ();
    final BootstrapTooltip aTooltip = new BootstrapTooltip (aIcon).setTooltipTitle (aTitle);
    return new HCNodeList ().addChild (aIcon).addChild (aTooltip);
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final Iterable <? extends IHCNode> aTitle)
  {
    return createSimpleTooltip (new HCNodeList ().addChildren (aTitle));
  }

  @Nonnull
  public static IHCNode createSimpleTooltip (@Nonnull final IHCNode... aTitle)
  {
    return createSimpleTooltip (new HCNodeList ().addChildren (aTitle));
  }
}
