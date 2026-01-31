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
package com.helger.html.jscode;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;

public class JSOpUnaryWithParanthesis extends JSOpUnary
{
  public JSOpUnaryWithParanthesis (@NonNull @Nonempty final String sOp, @NonNull final IJSExpression aExpr)
  {
    super (sOp, aExpr);
  }

  public JSOpUnaryWithParanthesis (@NonNull final IJSExpression aExpr, @NonNull @Nonempty final String sOp)
  {
    super (aExpr, sOp);
  }

  @Override
  public void generate (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain ('(');
    super.generate (aFormatter);
    aFormatter.plain (')');
  }
}
