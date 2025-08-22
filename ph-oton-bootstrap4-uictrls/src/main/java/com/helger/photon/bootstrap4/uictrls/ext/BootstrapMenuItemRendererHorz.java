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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuSeparator;

import jakarta.annotation.Nonnull;

/**
 * A special menu item renderer for the footer area, where the items are
 * displayed horizontally
 *
 * @author Philip Helger
 */
public class BootstrapMenuItemRendererHorz extends BootstrapMenuItemRenderer
{
  public BootstrapMenuItemRendererHorz (@Nonnull final Locale aContentLocale)
  {
    super (aContentLocale);
  }

  @Override
  @Nonnull
  public IHCNode renderSeparator (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final IMenuSeparator aSeparator)
  {
    return new HCLI ().addChild ("·");
  }
}
