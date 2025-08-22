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
package com.helger.photon.uictrls.datatables.plugins;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A button collection that provides column visibility control.
 *
 * @author Philip Helger
 */
public class DTPButtonsButtonColVis extends DTPButtonsButton
{
  /**
   * Columns selector that defines the columns to include in the column
   * visibility button set. By default this is undefined which results in all
   * columns being selected, but any of the column-selector options can be used
   * to define a custom button set.
   */
  private IJSExpression m_aColumns;

  public DTPButtonsButtonColVis ()
  {
    setExtend (EDTPButtonsButtonType.COL_VIS.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColVis setColumns (@Nullable final String sColumns)
  {
    return setColumns (sColumns == null ? null : JSExpr.lit (sColumns));
  }

  @Nonnull
  public DTPButtonsButtonColVis setColumns (@Nullable final IJSExpression aColumns)
  {
    m_aColumns = aColumns;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (m_aColumns != null)
      ret.add ("columns", m_aColumns);
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COL_VIS.registerExternalResources ();
  }
}
