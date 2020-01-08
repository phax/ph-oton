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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.JSMarshaller;
import com.helger.html.js.UnparsedJSCodeProvider;

@Immutable
public final class FakeJS
{
  public static final IHasJSCode JS_BLUR = new UnparsedJSCodeProvider ("blur();");
  public static final IHasJSCode RETURN_FALSE = new UnparsedJSCodeProvider ("return false;");

  private FakeJS ()
  {}

  @Nonnull
  public static IHasJSCode focus (@Nonnull final IHCHasID <?> aElement)
  {
    return new UnparsedJSCodeProvider ("document.getElementById('" +
                                       JSMarshaller.javaScriptEscape (aElement.ensureID ().getID ()) +
                                       "').focus();");
  }

  @Nonnull
  public static IHasJSCode windowLocationHref (@Nonnull final ISimpleURL aURL)
  {
    return new UnparsedJSCodeProvider ("window.location.href='" +
                                       JSMarshaller.javaScriptEscape (aURL.getAsStringWithEncodedParameters (HCSettings.getHTMLCharset ())) +
                                       "';");
  }
}
