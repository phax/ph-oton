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
package com.helger.photon.bootstrap3.breadcrumbs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCOL;
import com.helger.html.hc.htmlext.HCA_JS;
import com.helger.html.js.IHasJSCode;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Breadcrumbs
 * 
 * @author Philip Helger
 */
public class BootstrapBreadcrumbs extends HCOL
{
  public BootstrapBreadcrumbs ()
  {
    addClass (CBootstrapCSS.BREADCRUMB);
  }

  @Nonnull
  public BootstrapBreadcrumbs addLink (@Nonnull final ISimpleURL aURL, @Nonnull final String sText)
  {
    addItem (new HCA (aURL).addChild (sText));
    return this;
  }

  @Nonnull
  public BootstrapBreadcrumbs addLink (@Nonnull final IHasJSCode aJSCodeProvider, @Nonnull final String sText)
  {
    addItem (new HCA_JS (aJSCodeProvider).addChild (sText));
    return this;
  }

  @Nonnull
  public BootstrapBreadcrumbs addActive (@Nullable final String sText)
  {
    addAndReturnItem (sText).addClass (CBootstrapCSS.ACTIVE);
    return this;
  }
}
