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
package com.helger.photon.uictrls.pdfobject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSRef;

@Immutable
public final class PDFObjectJS
{
  private PDFObjectJS ()
  {}

  @Nonnull
  public static JSRef pdfObject ()
  {
    return JSExpr.ref ("PDFObject");
  }

  @Nonnull
  public static JSFieldRef supportsPDFs ()
  {
    return pdfObject ().ref ("supportsPDFs");
  }

  /**
   * @return The version used
   */
  @Nonnull
  public static JSFieldRef pdfobjectversion ()
  {
    return pdfObject ().ref ("pdfobjectversion");
  }

  @Nonnull
  public static JSInvocation embed (@Nonnull final ISimpleURL aURL,
                                    @Nonnull final IJSExpression aTarget,
                                    @Nullable final PDFObjectOptions aOptions)
  {
    ValueEnforcer.notNull (aURL, "URL");
    ValueEnforcer.notNull (aTarget, "Target");

    final JSInvocation ret = pdfObject ().invoke ("embed").arg (aURL.getAsStringWithEncodedParameters ()).arg (aTarget);
    if (aOptions != null)
      ret.arg (aOptions.getAsJSObject ());
    return ret;
  }
}
