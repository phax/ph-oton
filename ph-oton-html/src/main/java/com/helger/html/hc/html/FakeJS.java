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
package com.helger.html.hc.html;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.JSMarshaller;
import com.helger.html.js.PhotonInternalUnparsedJS;
import com.helger.url.ISimpleURL;

/**
 * This class only exists, so that ph-oton-html can be used without requiring ph-oton-jscode module
 *
 * @author Philip Helger
 */
@Immutable
public final class FakeJS
{
  public static final IHasJSCode JS_BLUR = new PhotonInternalUnparsedJS ("blur();");
  public static final IHasJSCode RETURN_FALSE = new PhotonInternalUnparsedJS ("return false;");

  private FakeJS ()
  {}

  @NonNull
  public static IHasJSCode focus (@NonNull final IHCHasID <?> aElement)
  {
    return new PhotonInternalUnparsedJS ("document.getElementById('" +
                                         JSMarshaller.javaScriptEscape (aElement.ensureID ().getID ()) +
                                         "').focus();");
  }

  @NonNull
  public static IHasJSCode windowLocationHref (@NonNull final ISimpleURL aURL)
  {
    return new PhotonInternalUnparsedJS ("window.location.href='" +
                                         JSMarshaller.javaScriptEscape (aURL.getWithCharset (HCSettings.getHTMLCharset ())
                                                                            .getAsString ()) +
                                         "';");
  }
}
