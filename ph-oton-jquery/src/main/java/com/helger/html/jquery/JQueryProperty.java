/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.jquery;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSRef;

/**
 * Wrapper around jQuery to allow for easy function calls
 *
 * @author Philip Helger
 */
@Immutable
public final class JQueryProperty
{
  public static final AtomicBoolean s_aUseDollar = new AtomicBoolean (true);

  @PresentForCodeCoverage
  private static final JQueryProperty s_aInstance = new JQueryProperty ();

  private JQueryProperty ()
  {}

  /**
   * Globally decide, whether to use "$" or "jQuery" to access jQuery field
   *
   * @param bUseDollar
   *        <code>true</code> to use "$", <code>false</code> to use "jQuery"
   */
  public static void setUseDollarForJQuery (final boolean bUseDollar)
  {
    s_aUseDollar.set (bUseDollar);
  }

  /**
   * @return <code>true</code> if "$" is used, <code>false</code> if "jQuery" is
   *         used for the global jQuery field
   */
  public static boolean isUseDollarForJQuery ()
  {
    return s_aUseDollar.get ();
  }

  // Properties of the Global jQuery Object

  /**
   * @return <code>$</code> or <code>jQuery</code> as a field
   */
  @Nonnull
  public static JSRef jQueryField ()
  {
    return JSExpr.ref (isUseDollarForJQuery () ? "$" : "jQuery");
  }

  /**
   * @return <code>$.fx</code>
   */
  @Nonnull
  public static JSFieldRef fx ()
  {
    return jQueryField ().ref ("fx");
  }

  /**
   * @return <code>$.fx.interval</code>
   * @since jQuery 1.4.3
   */
  @Nonnull
  public static JSFieldRef fxInterval ()
  {
    return fx ().ref ("interval");
  }

  /**
   * @return <code>$.fx.off</code>
   * @since jQuery 1.3
   */
  @Nonnull
  public static JSFieldRef fxOff ()
  {
    return fx ().ref ("off");
  }

  /**
   * @return <code>$.support</code>
   * @since jQuery 1.3
   */
  @Nonnull
  public static JSFieldRef support ()
  {
    return jQueryField ().ref ("support");
  }

  /**
   * @return <code>$.cssHooks</code>
   * @since jQuery 1.4.3
   */
  @Nonnull
  public static JSFieldRef cssHooks ()
  {
    return jQueryField ().ref ("cssHooks");
  }

  // Internals

  /**
   * @return <code>$.fn</code>
   */
  @Nonnull
  public static JSFieldRef fn ()
  {
    return jQueryField ().ref ("fn");
  }

  /**
   * @return <code>$.error</code>
   */
  @Nonnull
  public static JSFieldRef error ()
  {
    return jQueryField ().ref ("error");
  }
}
