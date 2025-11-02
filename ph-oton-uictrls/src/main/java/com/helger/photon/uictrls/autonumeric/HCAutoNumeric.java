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
package com.helger.photon.uictrls.autonumeric;

import java.math.BigDecimal;
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
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
  public HCAutoNumeric (@Nullable final IHCRequestField aRF, @NonNull final Locale aDisplayLocale)
  {
    super (aRF, aDisplayLocale);
  }

  @NonNull
  public static JSInvocation autoNumericInit (@NonNull final IJSExpression aAutoNumeric)
  {
    return invoke (aAutoNumeric).arg ("init");
  }

  @NonNull
  public static JSInvocation autoNumericInit (@NonNull final IJSExpression aAutoNumeric, @NonNull final JSAssocArray aOptions)
  {
    return autoNumericInit (aAutoNumeric).arg (aOptions);
  }

  @NonNull
  public static IJSExpression autoNumericGet (@NonNull final IJSExpression aAutoNumeric)
  {
    // Remember: the result is a String!!
    return invoke (aAutoNumeric).arg ("get");
  }

  @NonNull
  public static IJSExpression autoNumericGetAsFloat (@NonNull final IJSExpression aAutoNumeric)
  {
    return JSGlobal.parseFloat (autoNumericGet (aAutoNumeric));
  }

  @NonNull
  public static JSInvocation autoNumericSet (@NonNull final IJSExpression aAutoNumeric, @NonNull final IJSExpression aValueToSet)
  {
    return invoke (aAutoNumeric).arg ("set").arg (aValueToSet);
  }

  @NonNull
  public static JSInvocation autoNumericSet (@NonNull final IJSExpression aAutoNumeric, final int nValueToSet)
  {
    return autoNumericSet (aAutoNumeric, JSExpr.lit (nValueToSet));
  }

  @NonNull
  public static JSInvocation autoNumericSet (@NonNull final IJSExpression aAutoNumeric, @NonNull final BigDecimal aValueToSet)
  {
    return autoNumericSet (aAutoNumeric, JSExpr.lit (aValueToSet));
  }
}
