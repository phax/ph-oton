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
package com.helger.photon.bootstrap3.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.microdom.IMicroElement;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCHasFocus;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.HCCol;
import com.helger.photon.uicore.html.table.IHCTableForm;
import com.helger.web.scopes.mgr.WebScopeManager;

@Deprecated
public class BootstrapTableForm extends AbstractBootstrapTable <BootstrapTableForm> implements IHCTableForm <BootstrapTableForm>
{
  private static final String REQUEST_ATTR_FIRST_FOCUSABLE = "ph$Bootstrap3TableForm$FirstFocusable";

  private boolean m_bFocusHandlingEnabled = true;
  private boolean m_bSetAutoFocus = false;
  private IHCHasFocus <?> m_aFirstFocusable;

  public BootstrapTableForm (@Nullable final HCCol... aWidths)
  {
    super (aWidths);
    setCondensed (true);
    setStriped (true);
  }

  @Nonnull
  public BootstrapTableForm setFocusHandlingEnabled (final boolean bFocusHandlingEnabled)
  {
    m_bFocusHandlingEnabled = bFocusHandlingEnabled;
    return this;
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
  public BootstrapTableFormItemRow createItemRow ()
  {
    final BootstrapTableFormItemRow ret = new BootstrapTableFormItemRow (false, getColumnCount () > 2)
    {
      @Override
      protected void modifyControls (@Nonnull final Iterable <? extends IHCNode> aCtrls, final boolean bHasErrors)
      {
        _handleFocus (aCtrls, bHasErrors);
        super.modifyControls (aCtrls, bHasErrors);
      }
    };
    addBodyRow (ret);
    return ret;
  }

  @Override
  protected void applyProperties (final IMicroElement aDivElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    if (isFocusHandlingEnabled () && !m_bSetAutoFocus && m_aFirstFocusable != null)
    {
      // No focus has yet be set
      // Try to focus the first control (if available), but do it only once per
      // request because the cursor can only be on one control at a time :)
      if (!WebScopeManager.getRequestScope ().getAndSetAttributeFlag (REQUEST_ATTR_FIRST_FOCUSABLE))
        focusNode (m_aFirstFocusable);
    }
    super.applyProperties (aDivElement, aConversionSettings);
  }
}
