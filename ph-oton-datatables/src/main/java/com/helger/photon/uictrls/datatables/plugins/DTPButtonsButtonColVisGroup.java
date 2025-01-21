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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Show and hide multiple columns. Please note that unlike most other buttons,
 * the button text ({@link #setText(String)}) option is not predefined as there
 * is no sensible default that could be applied. You must set a text property
 * for this button.
 *
 * @author Philip Helger
 */
public class DTPButtonsButtonColVisGroup extends DTPButtonsButton
{
  /** Column to make visible */
  private IJSExpression m_aShow;
  /** Column to remove from the visible display */
  private IJSExpression m_aHide;

  public DTPButtonsButtonColVisGroup ()
  {
    setExtend (EDTPButtonsButtonType.COL_VIS_GROUP.getName ());
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setShow (@Nullable final String sShow)
  {
    return setShow (sShow == null ? null : JSExpr.lit (sShow));
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setShow (@Nullable final IJSExpression aShow)
  {
    m_aShow = aShow;
    return this;
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setHide (@Nullable final String sHide)
  {
    return setHide (sHide == null ? null : JSExpr.lit (sHide));
  }

  @Nonnull
  public DTPButtonsButtonColVisGroup setHide (@Nullable final IJSExpression aHide)
  {
    m_aHide = aHide;
    return this;
  }

  @Override
  protected void onGetAsJS (@Nonnull final JSAssocArray ret)
  {
    if (m_aShow != null)
      ret.add ("show", m_aShow);
    if (m_aHide != null)
      ret.add ("hide", m_aHide);
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    EDTPButtonsButtonType.COL_VIS_GROUP.registerExternalResources ();
  }
}
