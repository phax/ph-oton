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
package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSAtom;
import com.helger.html.jscode.JSExpr;

public class DTPButtonsButton
{
  public static final boolean DEFAULT_ENABLED = true;

  /** function action( e, dt, node, config ) */
  private JSAnonymousFunction m_aAction;
  /** function available( dt, config ) */
  private JSAnonymousFunction m_aAvailable;
  /** Set the class name for the button. */
  private final HCHasCSSClasses m_aClassNames = new HCHasCSSClasses ();
  /** function destroy( dt, node, config ) */
  private JSAnonymousFunction m_aDestroy;
  /** Set a button's initial enabled state. */
  private ETriState m_eEnabled = ETriState.UNDEFINED;
  /** Define which button type the button should be based on. */
  private String m_sExtend;
  /** function init( dt, node, config ) */
  private JSAnonymousFunction m_aInit;
  /** Define an activation key for a button. */
  private DTPButtonsButtonKey m_aKey;
  /** Set a name for each selection. */
  private String m_sName;
  /** Unique namespace for every button. */
  private String m_sNamespace;
  /**
   * The text to show in the button. Text, HTML or function text( dt, node,
   * config )
   */
  private IJSExpression m_aText;

  @Nonnull
  public DTPButtonsButton setAction (@Nullable final JSAnonymousFunction aAction)
  {
    m_aAction = aAction;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setAvailable (@Nullable final JSAnonymousFunction aAvailable)
  {
    m_aAvailable = aAvailable;
    return this;
  }

  @Nonnull
  public DTPButtonsButton addClassName (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClass (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DTPButtonsButton addClassName (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DTPButtonsButton addClassName (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DTPButtonsButton setDestroy (@Nullable final JSAnonymousFunction aDestroy)
  {
    m_aDestroy = aDestroy;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setEnabled (final boolean bEnabled)
  {
    return setEnabled (ETriState.valueOf (bEnabled));
  }

  @Nonnull
  public DTPButtonsButton setEnabled (@Nonnull final ETriState eEnabled)
  {
    ValueEnforcer.notNull (eEnabled, "Enabled");
    m_eEnabled = eEnabled;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setExtend (@Nullable final String sExtend)
  {
    m_sExtend = sExtend;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setInit (@Nullable final JSAnonymousFunction aInit)
  {
    m_aInit = aInit;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setKey (@Nullable final DTPButtonsButtonKey aKey)
  {
    m_aKey = aKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setNamespace (@Nullable final String sNamespace)
  {
    m_sNamespace = sNamespace;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setText (@Nullable final String sText)
  {
    return setText (sText == null ? null : JSExpr.lit (sText));
  }

  @Nonnull
  public DTPButtonsButton setText (@Nullable final IJSExpression aText)
  {
    m_aText = aText;
    return this;
  }

  /**
   * @param ret
   *        Associative array to be modified. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {}

  @Nonnull
  public IJSExpression getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aAction != null)
      ret.add ("action", m_aAction);
    if (m_aAvailable != null)
      ret.add ("available", m_aAvailable);
    final String sClasses = m_aClassNames.getAllClassesAsString ();
    if (StringHelper.hasText (sClasses))
      ret.add ("className", sClasses);
    if (m_aDestroy != null)
      ret.add ("destroy", m_aDestroy);
    if (m_eEnabled.isDefined ())
      ret.add ("enabled", m_eEnabled.getAsBooleanValue (DEFAULT_ENABLED));
    if (StringHelper.hasText (m_sExtend))
      ret.add ("extend", m_sExtend);
    if (m_aInit != null)
      ret.add ("init", m_aInit);
    if (m_aKey != null)
      ret.add ("key", m_aKey.getAsJS ());
    if (StringHelper.hasText (m_sName))
      ret.add ("name", m_sName);
    if (StringHelper.hasText (m_sNamespace))
      ret.add ("namespace", m_sNamespace);
    if (m_aText != null)
      ret.add ("text", m_aText);
    onGetAsJS (ret);

    if (ret.size () == 1)
    {
      final IJSExpression aValue = ret.get (new JSAtom ("extend"));
      if (aValue != null)
        return aValue;
    }

    return ret;
  }

  /**
   * Register additional resources for this button.
   *
   * @param aConversionSettings
   *        Current conversion settings. Never <code>null</code>.
   */
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {}
}
