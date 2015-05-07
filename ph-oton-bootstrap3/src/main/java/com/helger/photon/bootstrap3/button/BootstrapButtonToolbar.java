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
package com.helger.photon.bootstrap3.button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.SimpleURL;
import com.helger.html.EHTMLRole;
import com.helger.html.js.IJSCodeProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.core.app.layout.ILayoutExecutionContext;
import com.helger.photon.uicore.html.toolbar.AbstractButtonToolbar;
import com.helger.photon.uicore.icon.IIcon;

/**
 * Bootstrap3 button toolbar. Should only be used to group button groups and not
 * simple buttons.
 *
 * @author Philip Helger
 */
public class BootstrapButtonToolbar extends AbstractButtonToolbar <BootstrapButtonToolbar>
{
  public BootstrapButtonToolbar (@Nonnull final ILayoutExecutionContext aLEC)
  {
    this (aLEC.getSelfHref ());
  }

  public BootstrapButtonToolbar (@Nonnull final SimpleURL aSelfHref)
  {
    super (aSelfHref);
    addClass (CBootstrapCSS.BTN_TOOLBAR);
    setRole (EHTMLRole.TOOLBAR);
  }

  @Nonnull
  public final BootstrapButton addAndReturnButton (@Nullable final String sCaption,
                                                   @Nullable final IJSCodeProvider aJSCode,
                                                   @Nullable final IIcon aIcon)
  {
    return addAndReturnChild (new BootstrapButton ().setIcon (aIcon).addChild (sCaption).setOnClick (aJSCode));
  }

  @Nonnull
  public final BootstrapButton addAndReturnSubmitButton (@Nullable final String sCaption,
                                                         @Nullable final IJSCodeProvider aOnClick,
                                                         @Nullable final IIcon aIcon)
  {
    return addAndReturnChild (new BootstrapSubmitButton ().setIcon (aIcon).setOnClick (aOnClick).addChild (sCaption));
  }
}
