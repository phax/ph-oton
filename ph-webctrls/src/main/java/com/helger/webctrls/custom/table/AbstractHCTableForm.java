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
package com.helger.webctrls.custom.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCHasFocus;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCTable;
import com.helger.html.hc.html.HCCol;

public abstract class AbstractHCTableForm <IMPLTYPE extends AbstractHCTableForm <IMPLTYPE>> extends AbstractHCTable <IMPLTYPE> implements IHCTableForm <IMPLTYPE>
{
  private boolean m_bFocusHandlingEnabled = true;
  private boolean m_bSetAutoFocus = false;
  private IHCHasFocus <?> m_aFirstFocusable;

  public AbstractHCTableForm ()
  {
    super ();
  }

  public AbstractHCTableForm (@Nullable final HCCol aCol)
  {
    super (aCol);
  }

  public AbstractHCTableForm (@Nullable final HCCol... aCols)
  {
    super (aCols);
  }

  public AbstractHCTableForm (@Nullable final Iterable <? extends HCCol> aCols)
  {
    super (aCols);
  }

  @Nonnull
  public IMPLTYPE setFocusHandlingEnabled (final boolean bFocusHandlingEnabled)
  {
    m_bFocusHandlingEnabled = bFocusHandlingEnabled;
    return thisAsT ();
  }

  public boolean isFocusHandlingEnabled ()
  {
    return m_bFocusHandlingEnabled;
  }

  @OverrideOnDemand
  protected void focusNode (@Nonnull final IHCHasFocus <?> aCtrl)
  {
    aCtrl.setFocused (true);
    if (aCtrl instanceof IHCControl <?>)
    {
      // Ensure that an ID is present
      ((IHCControl <?>) aCtrl).ensureID ();
    }
  }

  private void _handleFocus (@Nonnull final Iterable <? extends IHCNode> aCtrls, final boolean bHasErrors)
  {
    if (isFocusHandlingEnabled ())
    {
      // Set focus on first element with error
      if (bHasErrors && !m_bSetAutoFocus)
        for (final IHCNode aCtrl : aCtrls)
          if (aCtrl instanceof IHCHasFocus <?>)
          {
            focusNode ((IHCHasFocus <?>) aCtrl);
            m_bSetAutoFocus = true;
            break;
          }

      // Find first focusable control
      if (m_aFirstFocusable == null)
        for (final IHCNode aCtrl : aCtrls)
          if (aCtrl instanceof IHCHasFocus <?>)
          {
            m_aFirstFocusable = (IHCHasFocus <?>) aCtrl;
            break;
          }
    }
  }

  @Nonnull
  public HCTableFormItemRow createItemRow ()
  {
    final HCTableFormItemRow ret = new HCTableFormItemRow (false, getColumnCount () > 2)
    {
      @Override
      protected void modifyControls (@Nonnull final Iterable <? extends IHCNode> aCtrls, final boolean bHasErrors)
      {
        _handleFocus (aCtrls, bHasErrors);
      }
    };
    addBodyRow (ret);
    return ret;
  }
}
