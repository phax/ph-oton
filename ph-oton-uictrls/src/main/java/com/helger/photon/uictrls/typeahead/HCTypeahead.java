/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

@OutOfBandNode
public class HCTypeahead extends AbstractHCScriptInline <HCTypeahead>
{
  public static final ICSSClassProvider TT_QUERY = DefaultCSSClassProvider.create ("tt-query");
  public static final ICSSClassProvider TT_HINT = DefaultCSSClassProvider.create ("tt-hint");
  public static final ICSSClassProvider TT_DROPDOWN_MENU = DefaultCSSClassProvider.create ("tt-dropdown-menu");
  public static final ICSSClassProvider TT_SUGGESTIONS = DefaultCSSClassProvider.create ("tt-suggestions");
  public static final ICSSClassProvider TT_SUGGESTION = DefaultCSSClassProvider.create ("tt-suggestion");
  public static final ICSSClassProvider TT_IS_UNDER_CURSOR = DefaultCSSClassProvider.create ("tt-is-under-cursor");

  private final IJSExpression m_aSelector;
  private final ICommonsList <TypeaheadDataset> m_aDatasets = new CommonsArrayList <> ();
  private JSAnonymousFunction m_aOnInitialized;
  private JSAnonymousFunction m_aOnOpened;
  private JSAnonymousFunction m_aOnClosed;
  private JSAnonymousFunction m_aOnSelected;
  private JSAnonymousFunction m_aOnAutoCompleted;

  public HCTypeahead (@NonNull final IJQuerySelector aSelector)
  {
    this (aSelector.invoke ());
  }

  public HCTypeahead (@NonNull final IJSExpression aSelector)
  {
    m_aSelector = ValueEnforcer.notNull (aSelector, "Selector");
  }

  @NonNull
  public IJSExpression getSelector ()
  {
    return m_aSelector;
  }

  @NonNull
  public HCTypeahead addDataset (@NonNull final TypeaheadDataset aDataset)
  {
    ValueEnforcer.notNull (aDataset, "Dataset");

    m_aDatasets.add (aDataset);
    return this;
  }

  @Nonnegative
  public int getDatasetCount ()
  {
    return m_aDatasets.size ();
  }

  @Nullable
  public TypeaheadDataset getFirstDataset ()
  {
    return m_aDatasets.getFirstOrNull ();
  }

  @Nullable
  public TypeaheadDataset getDatasetAtIndex (@Nonnegative final int nIndex)
  {
    return m_aDatasets.getAtIndex (nIndex);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <TypeaheadDataset> getAllDatasets ()
  {
    return m_aDatasets.getClone ();
  }

  /**
   * Triggered after initialization. If data needs to be prefetched, this event
   * will not be triggered until after the prefetched data is processed.
   *
   * @param aOnInitialized
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @NonNull
  public HCTypeahead setOnInitialized (@Nullable final JSAnonymousFunction aOnInitialized)
  {
    m_aOnInitialized = aOnInitialized;
    return this;
  }

  /**
   * @return Triggered after initialization. If data needs to be prefetched,
   *         this event will not be triggered until after the prefetched data is
   *         processed.
   */
  @Nullable
  public JSAnonymousFunction getOnInitialized ()
  {
    return m_aOnInitialized;
  }

  /**
   * Triggered when the dropdown menu of a typeahead is opened.
   *
   * @param aOnOpened
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @NonNull
  public HCTypeahead setOnOpened (@Nullable final JSAnonymousFunction aOnOpened)
  {
    m_aOnOpened = aOnOpened;
    return this;
  }

  /**
   * @return Triggered when the dropdown menu of a typeahead is opened.
   */
  @Nullable
  public JSAnonymousFunction getOnOpened ()
  {
    return m_aOnOpened;
  }

  /**
   * Triggered when the dropdown menu of a typeahead is closed.
   *
   * @param aOnClosed
   *        Function to call. May be <code>null</code>.
   * @return this
   */
  @NonNull
  public HCTypeahead setOnClosed (@Nullable final JSAnonymousFunction aOnClosed)
  {
    m_aOnClosed = aOnClosed;
    return this;
  }

  /**
   * @return Triggered when the dropdown menu of a typeahead is closed.
   */
  @Nullable
  public JSAnonymousFunction getOnClosed ()
  {
    return m_aOnClosed;
  }

  /**
   * Triggered when a suggestion from the dropdown menu is explicitly selected.
   * The datum for the selected suggestion is passed to the event handler as an
   * argument in addition to the name of the dataset it originated from.
   *
   * @param aOnSelected
   *        Function to call. May be <code>null</code>.
   * @return this
   */
  @NonNull
  public HCTypeahead setOnSelected (@Nullable final JSAnonymousFunction aOnSelected)
  {
    m_aOnSelected = aOnSelected;
    return this;
  }

  /**
   * @return Triggered when a suggestion from the dropdown menu is explicitly
   *         selected. The datum for the selected suggestion is passed to the
   *         event handler as an argument in addition to the name of the dataset
   *         it originated from.
   */
  @Nullable
  public JSAnonymousFunction getOnSelected ()
  {
    return m_aOnSelected;
  }

  /**
   * Triggered when the query is autocompleted. The datum used for
   * autocompletion is passed to the event handler as an argument in addition to
   * the name of the dataset it originated from.
   *
   * @param aOnAutoCompleted
   *        Function to call. May be <code>null</code>.
   * @return this
   */
  @NonNull
  public HCTypeahead setOnAutoCompleted (@Nullable final JSAnonymousFunction aOnAutoCompleted)
  {
    m_aOnAutoCompleted = aOnAutoCompleted;
    return this;
  }

  /**
   * @return Triggered when the query is autocompleted. The datum used for
   *         autocompletion is passed to the event handler as an argument in
   *         addition to the name of the dataset it originated from.
   */
  @Nullable
  public JSAnonymousFunction getOnAutoCompleted ()
  {
    return m_aOnAutoCompleted;
  }

  @Nullable
  public JSInvocation getAsJSObject ()
  {
    if (m_aDatasets.isEmpty ())
      throw new IllegalStateException ("At least one dataset must be provided!");

    JSInvocation ret = invoke (m_aSelector);
    if (m_aDatasets.size () == 1)
    {
      // Exactly one dataset
      ret.arg (m_aDatasets.getFirstOrNull ().getAsJSObject ());
    }
    else
    {
      // More than one dataset
      final JSArray aJSDatasets = new JSArray ();
      for (final TypeaheadDataset aDataset : m_aDatasets)
        aJSDatasets.add (aDataset.getAsJSObject ());
      ret.arg (aJSDatasets);
    }
    if (m_aOnInitialized != null)
      ret = ret.invoke ("on").arg ("typeahead:initialized").arg (m_aOnInitialized);
    if (m_aOnOpened != null)
      ret = ret.invoke ("on").arg ("typeahead:opened").arg (m_aOnOpened);
    if (m_aOnClosed != null)
      ret = ret.invoke ("on").arg ("typeahead:closed").arg (m_aOnClosed);
    if (m_aOnSelected != null)
      ret = ret.invoke ("on").arg ("typeahead:selected").arg (m_aOnSelected);
    if (m_aOnAutoCompleted != null)
      ret = ret.invoke ("on").arg ("typeahead:autocompleted").arg (m_aOnAutoCompleted);
    return ret;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    setJSCodeProvider (getAsJSObject ());
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onRegisterExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);

    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.TYPEAHEAD_0_9);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.TYPEAHEAD_PH);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("selector", m_aSelector)
                                       .append ("datasets", m_aDatasets)
                                       .appendIfNotNull ("onInitialized", m_aOnInitialized)
                                       .appendIfNotNull ("onOpened", m_aOnOpened)
                                       .appendIfNotNull ("onClosed", m_aOnClosed)
                                       .appendIfNotNull ("onSelected", m_aOnSelected)
                                       .appendIfNotNull ("onAutoCompleted", m_aOnAutoCompleted)
                                       .getToString ();
  }

  @NonNull
  public static JSInvocation invoke (@NonNull final IJSExpression aExpr)
  {
    return aExpr.invoke ("typeahead");
  }

  @NonNull
  public static JSInvocation typeaheadDestroy (@NonNull final IJSExpression aTypeahead)
  {
    return invoke (aTypeahead).arg ("destroy");
  }

  @NonNull
  public static JSInvocation typeaheadSetQuery (@NonNull final IJSExpression aTypeahead, @NonNull final String sQuery)
  {
    return invoke (aTypeahead).arg ("setQuery").arg (sQuery);
  }
}
