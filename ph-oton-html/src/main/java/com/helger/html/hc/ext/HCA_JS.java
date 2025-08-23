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
package com.helger.html.hc.ext;

import com.helger.html.hc.html.FakeJS;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.html.hc.html.textlevel.IHCA;
import com.helger.html.js.CJS;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.js.IHasJSCode;
import com.helger.url.ISimpleURL;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class HCA_JS extends AbstractHCA <HCA_JS>
{
  public static final ISimpleURL JS_URL = new SimpleURL (CJS.JS_VOID);

  public HCA_JS ()
  {
    /**
     * JS links must always set a JS void in the href. This ensures that if the
     * href gets called (due to double click etc.) it has no effect.
     **/
    super (JS_URL);
  }

  public HCA_JS (@Nullable final IHasJSCode aJSOnClick)
  {
    this ();
    setOnClickReturnFalse (aJSOnClick);
  }

  @Nonnull
  public final HCA_JS addOnClickReturnFalse (@Nullable final IHasJSCode aJSOnClick)
  {
    addOnClickReturnFalse (this, aJSOnClick);
    return this;
  }

  @Nonnull
  public final HCA_JS setOnClickReturnFalse (@Nullable final IHasJSCode aJSOnClick)
  {
    setOnClickReturnFalse (this, aJSOnClick);
    return this;
  }

  public static void addOnClickReturnFalse (@Nonnull final IHCA <?> aLink, @Nullable final IHasJSCode aJSOnClick)
  {
    aLink.addOnClick (new CollectingJSCodeProvider (aJSOnClick, FakeJS.RETURN_FALSE));
  }

  public static void setOnClickReturnFalse (@Nonnull final IHCA <?> aLink, @Nullable final IHasJSCode aJSOnClick)
  {
    aLink.setOnClick (new CollectingJSCodeProvider (aJSOnClick, FakeJS.RETURN_FALSE));
  }
}
