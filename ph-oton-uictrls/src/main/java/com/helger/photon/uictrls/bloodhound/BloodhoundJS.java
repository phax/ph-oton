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
package com.helger.photon.uictrls.bloodhound;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;

import jakarta.annotation.Nonnull;

@Immutable
public final class BloodhoundJS
{
  private BloodhoundJS ()
  {}

  @Nonnull
  public static JSRef bloodhound ()
  {
    return JSExpr.ref ("Bloodhound");
  }

  @Nonnull
  public static JSFieldRef bloodhoundTokenizers ()
  {
    return bloodhound ().ref ("tokenizers");
  }

  /**
   * @return The Bloodhound built-in "whitespace" tokenizer.
   */
  @Nonnull
  public static JSFieldRef bloodhoundTokenizersWhitespace ()
  {
    return bloodhoundTokenizers ().ref ("whitespace");
  }

  /**
   * @return The Bloodhound built-in "nonword" tokenizer.
   */
  @Nonnull
  public static JSFieldRef bloodhoundTokenizersNonword ()
  {
    return bloodhoundTokenizers ().ref ("nonword");
  }

  @Nonnull
  public static JSInvocation bloodhoundNoConflict ()
  {
    return bloodhound ().invoke ("noConflict");
  }

  @Nonnull
  public static BloodhoundJSInvocation newBloodhound (@Nonnull final BloodhoundOptions aOptions)
  {
    ValueEnforcer.notNull (aOptions, "Options");

    return new BloodhoundJSInvocation (bloodhound ()).arg (aOptions.getAsJSObject ());
  }
}
