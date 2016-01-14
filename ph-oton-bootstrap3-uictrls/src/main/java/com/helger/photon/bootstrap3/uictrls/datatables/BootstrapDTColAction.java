/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls.datatables;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uictrls.datatables.column.DTCol;

/**
 * Special action column. Has a special CSS class and is never sortable nor
 * searchable.
 *
 * @author Philip Helger
 */
public class BootstrapDTColAction extends DTCol
{
  private void _init ()
  {
    addClass (CUICoreCSS.CSS_CLASS_ACTION_COL);
    addClass (CUICoreCSS.CSS_CLASS_RIGHT);
    addClass (CBootstrapCSS.HIDDEN_PRINT);
    setOrderable (false);
    setSearchable (false);
  }

  public BootstrapDTColAction ()
  {
    super ();
    _init ();
  }

  public BootstrapDTColAction (@Nullable final String sHeaderText)
  {
    super (sHeaderText);
    _init ();
  }

  public BootstrapDTColAction (@Nullable final IHCNode aHeaderNode)
  {
    super (aHeaderNode);
    _init ();
  }

  /**
   * Special constructor using the predefined header text "Actions" in the
   * selected locale.
   *
   * @param aDisplayLocale
   *        Display locale to use. May not be <code>null</code>.
   */
  public BootstrapDTColAction (@Nonnull final Locale aDisplayLocale)
  {
    this (EPhotonCoreText.ACTIONS.getDisplayText (aDisplayLocale));
  }
}
