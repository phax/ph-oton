/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.tinymce4;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;

import jakarta.annotation.Nonnull;

/**
 * JS wrapped for TinyMCE4
 *
 * @author Philip Helger
 */
@Immutable
public final class JSTinyMCE4
{
  @PresentForCodeCoverage
  private static final JSTinyMCE4 INSTANCE = new JSTinyMCE4 ();

  private JSTinyMCE4 ()
  {}

  @Nonnull
  public static JSRef tinymce ()
  {
    return JSExpr.ref ("tinymce");
  }

  @Nonnull
  public static JSInvocation init (@Nonnull final JSAssocArray aOptions)
  {
    return tinymce ().invoke ("init").arg (aOptions);
  }
}
