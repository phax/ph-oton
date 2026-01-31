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
package com.helger.photon.uictrls.pdfobject;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;
import com.helger.url.ISimpleURL;

@Immutable
public final class PDFObjectJS
{
  private PDFObjectJS ()
  {}

  @NonNull
  public static JSRef pdfObject ()
  {
    return JSExpr.ref ("PDFObject");
  }

  @NonNull
  public static JSFieldRef supportsPDFs ()
  {
    return pdfObject ().ref ("supportsPDFs");
  }

  /**
   * @return The version used
   */
  @NonNull
  public static JSFieldRef pdfobjectversion ()
  {
    return pdfObject ().ref ("pdfobjectversion");
  }

  @NonNull
  public static JSInvocation embed (@NonNull final ISimpleURL aURL,
                                    @NonNull final IJSExpression aTarget,
                                    @Nullable final PDFObjectOptions aOptions)
  {
    ValueEnforcer.notNull (aURL, "URL");
    ValueEnforcer.notNull (aTarget, "Target");

    final JSInvocation ret = pdfObject ().invoke ("embed").arg (aURL.getAsString ()).arg (aTarget);
    if (aOptions != null)
      ret.arg (aOptions.getAsJSObject ());
    return ret;
  }
}
