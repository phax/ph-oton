/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import com.helger.commons.state.ETriState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * A button collection that provides column visibility control.
 *
 * @author Philip Helger
 */
public class DTPButtonsButtonColumnsToggle extends DTPButtonsButton
{
  /**
   * Columns selector that defines the columns to include in the column
   * visibility button set. By default this is undefined which results in all
   * columns being selected, but any of the column-selector options can be used
   * to define a custom button set.
   */
  private IJSExpression m_aColumns;

  /**
   * The visibility value to set for the selected column(s). true will display
   * the column, false will hide it and undefined will toggle its current state.
   */
  private ETriState m_eVisibility = ETriState.UNDEFINED;

  public DTPButtonsButtonColumnsToggle ()
  {
    setExtend (EDTPButtonsButtonType.COLUMNS_TOGGLE.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColumnsToggle setColumns (@Nullable final String sColumns)
  {
    return setColumns (sColumns == null ? null : JSExpr.lit (sColumns));
  }

  @Nonnull
  public DTPButtonsButtonColumnsToggle setColumns (@Nullable final IJSExpression aColumns)
  {
    m_aColumns = aColumns;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonColumnsToggle setVisibility (final boolean bVisibility)
  {
    return setVisibility (ETriState.valueOf (bVisibility));
  }

  @Nonnull
  public DTPButtonsButtonColumnsToggle setVisibility (@Nonnull final ETriState eVisibility)
  {
    ValueEnforcer.notNull (eVisibility, "Visibility");
    m_eVisibility = eVisibility;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (m_aColumns != null)
      ret.add ("columns", m_aColumns);
    if (m_eVisibility.isDefined ())
      ret.add ("visibility", m_eVisibility.getAsBooleanValue (true));
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COLUMNS_TOGGLE.registerExternalResources ();
  }
}
