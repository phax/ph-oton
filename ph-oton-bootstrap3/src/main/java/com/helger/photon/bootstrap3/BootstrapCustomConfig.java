/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * Customize the global Bootstrap JS and CSS to be used. This is helpful when
 * using a custom Bootstrap build.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class BootstrapCustomConfig
{
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  private static ICommonsList <ICSSPathProvider> s_aCSS = new CommonsArrayList <> ();
  private static ICommonsList <IJSPathProvider> s_aJS = new CommonsArrayList <> ();

  static
  {
    // Set default values
    s_aCSS.add (EBootstrapCSSPathProvider.BOOTSTRAP);
    s_aCSS.add (EBootstrapCSSPathProvider.BOOTSTRAP_THEME);
    s_aCSS.add (EBootstrapCSSPathProvider.BOOTSTRAP_PH);
    s_aCSS.add (EBootstrapCSSPathProvider.BOOTSTRAP_IE9);
    s_aJS.add (EBootstrapJSPathProvider.BOOTSTRAP);
    s_aJS.add (EBootstrapJSPathProvider.BOOTSTRAP_PH);
  }

  private BootstrapCustomConfig ()
  {}

  public static void setBootstrapCSS (@Nonnull @Nonempty final ICSSPathProvider... aCSSPathProvider)
  {
    ValueEnforcer.notEmptyNoNullValue (aCSSPathProvider, "CSSPathProvider");

    s_aRWLock.writeLocked ( () -> {
      s_aCSS.clear ();
      for (final ICSSPathProvider aPP : aCSSPathProvider)
        s_aCSS.add (aPP);
    });
  }

  @Nonnull
  @Nonempty
  public static ICommonsList <ICSSPathProvider> getAllBootstrapCSS ()
  {
    return s_aRWLock.readLocked ( () -> s_aCSS.getClone ());
  }

  public static void setBootstrapJS (@Nonnull @Nonempty final IJSPathProvider... aJSPathProvider)
  {
    ValueEnforcer.notEmptyNoNullValue (aJSPathProvider, "JSPathProvider");

    s_aRWLock.writeLocked ( () -> {
      s_aJS.clear ();
      for (final IJSPathProvider aPP : aJSPathProvider)
        s_aJS.add (aPP);
    });
  }

  @Nonnull
  @Nonempty
  public static ICommonsList <IJSPathProvider> getAllBootstrapJS ()
  {
    return s_aRWLock.readLocked ( () -> s_aJS.getClone ());
  }
}
