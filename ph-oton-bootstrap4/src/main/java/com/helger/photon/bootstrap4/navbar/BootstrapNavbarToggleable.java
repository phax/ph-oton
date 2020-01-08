/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.navbar;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap 4 Navbar toggleable
 *
 * @author Philip Helger
 */
public class BootstrapNavbarToggleable extends AbstractHCDiv <BootstrapNavbarToggleable>
{
  public BootstrapNavbarToggleable ()
  {}

  @Nonnull
  public BootstrapNavbarText addAndReturnText ()
  {
    return addAndReturnChild (new BootstrapNavbarText ());
  }

  @Nonnull
  public BootstrapNavbarNav addAndReturnNav ()
  {
    return addAndReturnChild (new BootstrapNavbarNav ());
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.COLLAPSE);
    addClass (CBootstrapCSS.NAVBAR_COLLAPSE);
  }
}
