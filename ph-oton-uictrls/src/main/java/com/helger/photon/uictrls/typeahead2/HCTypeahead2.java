/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead2;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.WorkInProgress;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.html.HCScript;
import com.helger.html.js.builder.IJSExpression;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSInvocation;
import com.helger.html.js.builder.jquery.IJQuerySelector;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.typeahead.TypeaheadDataset;

@WorkInProgress
public class HCTypeahead2 implements IHCNodeBuilder
{
  public static final boolean DEFAULT_AUTOSELECT = false;
  public static final boolean DEFAULT_HIGHLIGHT = false;
  public static final boolean DEFAULT_HINT = true;
  public static final int DEFAULT_MIN_LENGTH = 1;

  public static final ICSSClassProvider TT_QUERY = DefaultCSSClassProvider.create ("tt-query");
  public static final ICSSClassProvider TT_HINT = DefaultCSSClassProvider.create ("tt-hint");
  public static final ICSSClassProvider TT_DROPDOWN_MENU = DefaultCSSClassProvider.create ("tt-dropdown-menu");
  public static final ICSSClassProvider TT_SUGGESTIONS = DefaultCSSClassProvider.create ("tt-suggestions");
  public static final ICSSClassProvider TT_SUGGESTION = DefaultCSSClassProvider.create ("tt-suggestion");
  public static final ICSSClassProvider TT_IS_UNDER_CURSOR = DefaultCSSClassProvider.create ("tt-is-under-cursor");

  private final IJSExpression m_aSelector;
  private boolean m_bAutoSelect = DEFAULT_AUTOSELECT;
  private boolean m_bHighlight = DEFAULT_HIGHLIGHT;
  private boolean m_bHint = DEFAULT_HINT;
  private int m_nMinLength = DEFAULT_MIN_LENGTH;
  private final List <TypeaheadDataset> m_aDatasets = new ArrayList <TypeaheadDataset> ();
  private JSAnonymousFunction m_aOnOpened;
  private JSAnonymousFunction m_aOnClosed;
  private JSAnonymousFunction m_aOnCursorChanged;
  private JSAnonymousFunction m_aOnSelected;
  private JSAnonymousFunction m_aOnAutoCompleted;

  public HCTypeahead2 (@Nonnull final IJQuerySelector aSelector)
  {
    this (aSelector.invoke ());
  }

  public HCTypeahead2 (@Nonnull final IJSExpression aSelector)
  {
    m_aSelector = ValueEnforcer.notNull (aSelector, "Selector");
  }

  @Nonnull
  public IJSExpression getSelector ()
  {
    return m_aSelector;
  }

  public boolean isAutoselect ()
  {
    return m_bAutoSelect;
  }

  @Nonnull
  public HCTypeahead2 setAutoselect (final boolean bAutoSelect)
  {
    m_bAutoSelect = bAutoSelect;
    return this;
  }

  public boolean isHighlight ()
  {
    return m_bHighlight;
  }

  @Nonnull
  public HCTypeahead2 setHighlight (final boolean bHighlight)
  {
    m_bHighlight = bHighlight;
    return this;
  }

  public boolean isHint ()
  {
    return m_bHint;
  }

  @Nonnull
  public HCTypeahead2 setHint (final boolean bHint)
  {
    m_bHint = bHint;
    return this;
  }

  public int getMinLength ()
  {
    return m_nMinLength;
  }

  @Nonnull
  public HCTypeahead2 setMinLength (@Nonnegative final int nMinLength)
  {
    m_nMinLength = nMinLength;
    return this;
  }

  @Nonnull
  public HCTypeahead2 addDataset (@Nonnull final TypeaheadDataset aDataset)
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

  @Nonnull
  @ReturnsMutableCopy
  public List <TypeaheadDataset> getAllDatasets ()
  {
    return CollectionHelper.newList (m_aDatasets);
  }

  /**
   * Triggered when the dropdown menu of a typeahead is opened.
   *
   * @param aOnOpened
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTypeahead2 setOnOpened (@Nullable final JSAnonymousFunction aOnOpened)
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
  @Nonnull
  public HCTypeahead2 setOnClosed (@Nullable final JSAnonymousFunction aOnClosed)
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
   * Triggered when the dropdown menu cursor is moved to a different suggestion.
   * The event handler will be invoked with 3 arguments: the jQuery event
   * object, the suggestion object, and the name of the dataset the suggestion
   * belongs to.
   *
   * @param aOnCursorChanged
   *        Function to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTypeahead2 setOnCursorChanged (@Nullable final JSAnonymousFunction aOnCursorChanged)
  {
    m_aOnCursorChanged = aOnCursorChanged;
    return this;
  }

  /**
   * @return Triggered when the dropdown menu cursor is moved to a different
   *         suggestion. The event handler will be invoked with 3 arguments: the
   *         jQuery event object, the suggestion object, and the name of the
   *         dataset the suggestion belongs to.
   */
  @Nullable
  public JSAnonymousFunction getOnCursorChanged ()
  {
    return m_aOnCursorChanged;
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
  @Nonnull
  public HCTypeahead2 setOnSelected (@Nullable final JSAnonymousFunction aOnSelected)
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
  @Nonnull
  public HCTypeahead2 setOnAutoCompleted (@Nullable final JSAnonymousFunction aOnAutoCompleted)
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

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getOptions ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_bAutoSelect != DEFAULT_AUTOSELECT)
      ret.add ("autoselect", m_bAutoSelect);
    if (m_bHighlight != DEFAULT_HIGHLIGHT)
      ret.add ("highlight", m_bHighlight);
    if (m_bHint != DEFAULT_HINT)
      ret.add ("hint", m_bHint);
    if (m_nMinLength != 1)
      ret.add ("minLength", m_nMinLength);
    return ret;
  }

  @Nullable
  public JSInvocation getAsJSObject ()
  {
    if (m_aDatasets.isEmpty ())
      throw new IllegalStateException ("At least one dataset must be provided!");

    JSInvocation ret = invoke (m_aSelector).arg (getOptions ());
    if (m_aDatasets.size () == 1)
    {
      // Exactly one dataset
      ret.arg (CollectionHelper.getFirstElement (m_aDatasets).getAsJSObject ());
    }
    else
    {
      // More than one dataset
      final JSArray aJSDatasets = new JSArray ();
      for (final TypeaheadDataset aDataset : m_aDatasets)
        aJSDatasets.add (aDataset.getAsJSObject ());
      ret.arg (aJSDatasets);
    }
    if (m_aOnOpened != null)
      ret = ret.invoke ("on").arg ("typeahead:opened").arg (m_aOnOpened);
    if (m_aOnClosed != null)
      ret = ret.invoke ("on").arg ("typeahead:closed").arg (m_aOnClosed);
    if (m_aOnCursorChanged != null)
      ret = ret.invoke ("on").arg ("typeahead:cursorchanged").arg (m_aOnCursorChanged);
    if (m_aOnSelected != null)
      ret = ret.invoke ("on").arg ("typeahead:selected").arg (m_aOnSelected);
    if (m_aOnAutoCompleted != null)
      ret = ret.invoke ("on").arg ("typeahead:autocompleted").arg (m_aOnAutoCompleted);
    return ret;
  }

  @Nullable
  public IHCNode build ()
  {
    return new HCScript (getAsJSObject ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("selector", m_aSelector)
                                       .append ("datasets", m_aDatasets)
                                       .appendIfNotNull ("onOpened", m_aOnOpened)
                                       .appendIfNotNull ("onClosed", m_aOnClosed)
                                       .appendIfNotNull ("onCursorChanged", m_aOnCursorChanged)
                                       .appendIfNotNull ("onSelected", m_aOnSelected)
                                       .appendIfNotNull ("onAutoCompleted", m_aOnAutoCompleted)
                                       .toString ();
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final IJSExpression aExpr)
  {
    return aExpr.invoke ("typeahead");
  }

  @Nonnull
  public static JSInvocation typeaheadDestroy (@Nonnull final IJSExpression aTypeahead)
  {
    return invoke (aTypeahead).arg ("destroy");
  }

  @Nonnull
  public static JSInvocation typeaheadOpen (@Nonnull final IJSExpression aTypeahead)
  {
    return invoke (aTypeahead).arg ("open");
  }

  @Nonnull
  public static JSInvocation typeaheadClose (@Nonnull final IJSExpression aTypeahead)
  {
    return invoke (aTypeahead).arg ("open");
  }

  @Nonnull
  public static JSInvocation typeaheadVal (@Nonnull final IJSExpression aTypeahead)
  {
    return invoke (aTypeahead).arg ("val");
  }

  @Nonnull
  public static JSInvocation typeaheadVal (@Nonnull final IJSExpression aTypeahead, @Nonnull final IJSExpression aValue)
  {
    return typeaheadVal (aTypeahead).arg (aValue);
  }

  @Nonnull
  public static JSInvocation typeaheadSetQuery (@Nonnull final IJSExpression aTypeahead, @Nonnull final String sQuery)
  {
    return invoke (aTypeahead).arg ("setQuery").arg (sQuery);
  }

  public static void registerExternalResources ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.TYPEAHEAD_0_11);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.TYPEAHEAD_PH);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.TYPEAHEAD_BOOTSTRAP);
  }
}
