/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.colorbox;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.jquery.IJQuerySelector;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * jQuery colorbox plugin from
 *
 * <pre>
 * http://www.jacklmoore.com/colorbox
 * </pre>
 *
 * @author Philip Helger
 */
@Immutable
public final class ColorBoxJS
{
  private ColorBoxJS ()
  {}

  @Nonnull
  public static JSInvocation invokeColorBox (@Nonnull final IJQuerySelector aSelector)
  {
    ValueEnforcer.notNull (aSelector, "Selector");
    registerExternalResources ();

    return aSelector.invoke ().invoke ("colorbox");
  }

  @Nonnull
  public static JSInvocation invokeColorBox (@Nonnull final IJQuerySelector aSelector,
                                             @Nonnull final ColorBoxOptions aOptions)
  {
    ValueEnforcer.notNull (aOptions, "Options");
    return invokeColorBox (aSelector).arg (aOptions.getJSOptions ());
  }

  public static void registerExternalResources ()
  {
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.COLORBOX);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.COLORBOX);
  }
}
