/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.utils;

import com.helger.html.hc.html.IHCElement;
import com.helger.photon.bootstrap4.CBootstrapCSS;

import jakarta.annotation.Nonnull;

/**
 * Helper for the collapse functionality
 *
 * @author Philip Helger
 * @since 8.2.1
 */
public final class BootstrapCollapseHelper
{
  private BootstrapCollapseHelper ()
  {}

  public static void makeCollapsible (@Nonnull final IHCElement <?> aToggle, @Nonnull final IHCElement <?> aCollapsible)
  {
    aCollapsible.ensureID ().addClass (CBootstrapCSS.COLLAPSE);
    aToggle.customAttrs ().setDataAttr ("toggle", "collapse");
    aToggle.customAttrs ().setDataAttr ("target", "#" + aCollapsible.getID ());
    aToggle.customAttrs ().setAriaExpanded (false);
    aToggle.customAttrs ().setAriaControls (aCollapsible.getID ());
  }
}
