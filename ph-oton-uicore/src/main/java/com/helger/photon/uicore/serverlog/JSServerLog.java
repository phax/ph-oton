/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.serverlog;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.url.ISimpleURL;

@Immutable
public final class JSServerLog
{
  private JSServerLog ()
  {}

  public static void registerExternalResources ()
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.SERVERLOG);
  }

  @NonNull
  public static JSInvocation serverLogInit (@NonNull final ISimpleURL aURL, @NonNull final String sKey, final boolean bDebugMode)
  {
    return new JSInvocation ("serverLogInit").arg (aURL.getAsString ()).arg (sKey).arg (bDebugMode);
  }

  @NonNull
  public static JSInvocation serverLog (@NonNull final String sSeverity, @NonNull final String sMessage)
  {
    return new JSInvocation ("serverLog").arg (sSeverity).arg (sMessage);
  }

  @NonNull
  public static JSInvocation setupServerLogForWindow ()
  {
    return new JSInvocation ("setupServerLogForWindow");
  }

  @NonNull
  public static JSInvocation addExceptionHandlers (@NonNull final IJSExpression aExpr)
  {
    return new JSInvocation ("addExceptionHandlers").arg (aExpr);
  }
}
