/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.autonumeric;

import java.math.BigDecimal;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSGlobal;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.request.IHCRequestField;

/**
 * jQuery autoNumeric plugin from
 *
 * <pre>
 * http://www.decorplanit.com/plugin/
 * </pre>
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HCAutoNumeric extends AbstractHCAutoNumeric <HCAutoNumeric>
{
  public HCAutoNumeric (@Nullable final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    super (aRF, aDisplayLocale);
  }

  @Nonnull
  public static JSInvocation autoNumericInit (@Nonnull final IJSExpression aAutoNumeric)
  {
    return invoke (aAutoNumeric).arg ("init");
  }

  @Nonnull
  public static JSInvocation autoNumericInit (@Nonnull final IJSExpression aAutoNumeric, @Nonnull final JSAssocArray aOptions)
  {
    return autoNumericInit (aAutoNumeric).arg (aOptions);
  }

  @Nonnull
  public static IJSExpression autoNumericGet (@Nonnull final IJSExpression aAutoNumeric)
  {
    // Remember: the result is a String!!
    return invoke (aAutoNumeric).arg ("get");
  }

  @Nonnull
  public static IJSExpression autoNumericGetAsFloat (@Nonnull final IJSExpression aAutoNumeric)
  {
    return JSGlobal.parseFloat (autoNumericGet (aAutoNumeric));
  }

  @Nonnull
  public static JSInvocation autoNumericSet (@Nonnull final IJSExpression aAutoNumeric, @Nonnull final IJSExpression aValueToSet)
  {
    return invoke (aAutoNumeric).arg ("set").arg (aValueToSet);
  }

  @Nonnull
  public static JSInvocation autoNumericSet (@Nonnull final IJSExpression aAutoNumeric, final int nValueToSet)
  {
    return autoNumericSet (aAutoNumeric, JSExpr.lit (nValueToSet));
  }

  @Nonnull
  public static JSInvocation autoNumericSet (@Nonnull final IJSExpression aAutoNumeric, @Nonnull final BigDecimal aValueToSet)
  {
    return autoNumericSet (aAutoNumeric, JSExpr.lit (aValueToSet));
  }
}
