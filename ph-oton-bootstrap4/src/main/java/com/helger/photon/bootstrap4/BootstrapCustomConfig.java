/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.uicore.EUICoreJSPathProvider;

/**
 * Customize the global Bootstrap JS and CSS to be used. This is helpful when
 * using a custom Bootstrap build.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class BootstrapCustomConfig
{
  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  private static final ICommonsList <ICSSPathProvider> CSS = new CommonsArrayList <> ();
  private static final ICommonsList <IJSPathProvider> JS = new CommonsArrayList <> ();

  static
  {
    // Set default values: the default plain CSS and JS
    CSS.add (EBootstrapCSSPathProvider.BOOTSTRAP);
    CSS.add (EBootstrapCSSPathProvider.BOOTSTRAP_PH);

    JS.add (EUICoreJSPathProvider.POPPER);
    JS.add (EBootstrapJSPathProvider.BOOTSTRAP);
    JS.add (EBootstrapJSPathProvider.BOOTSTRAP_PH);
  }

  private BootstrapCustomConfig ()
  {}

  public static void setBootstrapCSS (@Nonnull @Nonempty final ICSSPathProvider... aCSSPathProvider)
  {
    ValueEnforcer.notEmptyNoNullValue (aCSSPathProvider, "CSSPathProvider");

    RW_LOCK.writeLocked ( () -> CSS.setAll (aCSSPathProvider));
  }

  @Nonnull
  @Nonempty
  public static ICommonsList <ICSSPathProvider> getAllBootstrapCSS ()
  {
    return RW_LOCK.readLockedGet (CSS::getClone);
  }

  public static void setBootstrapJS (@Nonnull @Nonempty final IJSPathProvider... aJSPathProvider)
  {
    ValueEnforcer.notEmptyNoNullValue (aJSPathProvider, "JSPathProvider");

    RW_LOCK.writeLocked ( () -> JS.setAll (aJSPathProvider));
  }

  @Nonnull
  @Nonempty
  public static ICommonsList <IJSPathProvider> getAllBootstrapJS ()
  {
    return RW_LOCK.readLockedGet (JS::getClone);
  }
}
