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
package com.helger.html.jscode.type;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSFieldRef;
import com.helger.html.jscode.JSInvocation;

/**
 * Contains the JS built-in type 'Math'
 *
 * @author Philip Helger
 */
public class JSTypeMath extends JSPrimitiveType
{
  public JSTypeMath ()
  {
    super ("Math");
  }

  @NonNull
  public JSFieldRef e ()
  {
    return global ().ref ("E");
  }

  @NonNull
  public JSFieldRef ln2 ()
  {
    return global ().ref ("LN2");
  }

  @NonNull
  public JSFieldRef ln10 ()
  {
    return global ().ref ("LN10");
  }

  @NonNull
  public JSFieldRef log2e ()
  {
    return global ().ref ("LOG2E");
  }

  @NonNull
  public JSFieldRef log10e ()
  {
    return global ().ref ("LOG10E");
  }

  @NonNull
  public JSFieldRef pi ()
  {
    return global ().ref ("PI");
  }

  @NonNull
  public JSFieldRef sqrt1_2 ()
  {
    return global ().ref ("SQRT1_2");
  }

  @NonNull
  public JSFieldRef sqrt2 ()
  {
    return global ().ref ("SQRT2");
  }

  @NonNull
  public JSInvocation abs ()
  {
    return global ().invoke ("abs");
  }

  @NonNull
  public JSInvocation abs (@NonNull final IJSExpression aExpr)
  {
    return abs ().arg (aExpr);
  }

  @NonNull
  public JSInvocation acos ()
  {
    return global ().invoke ("acos");
  }

  @NonNull
  public JSInvocation acos (@NonNull final IJSExpression aExpr)
  {
    return acos ().arg (aExpr);
  }

  @NonNull
  public JSInvocation asin ()
  {
    return global ().invoke ("asin");
  }

  @NonNull
  public JSInvocation asin (@NonNull final IJSExpression aExpr)
  {
    return asin ().arg (aExpr);
  }

  @NonNull
  public JSInvocation atan ()
  {
    return global ().invoke ("atan");
  }

  @NonNull
  public JSInvocation atan (@NonNull final IJSExpression aExpr)
  {
    return atan ().arg (aExpr);
  }

  @NonNull
  public JSInvocation atan2 ()
  {
    return global ().invoke ("atan2");
  }

  @NonNull
  public JSInvocation atan2 (@NonNull final IJSExpression aExprY, @NonNull final IJSExpression aExprX)
  {
    return atan2 ().arg (aExprY).arg (aExprX);
  }

  @NonNull
  public JSInvocation ceil ()
  {
    return global ().invoke ("ceil");
  }

  @NonNull
  public JSInvocation ceil (@NonNull final IJSExpression aExpr)
  {
    return ceil ().arg (aExpr);
  }

  @NonNull
  public JSInvocation cos ()
  {
    return global ().invoke ("cos");
  }

  @NonNull
  public JSInvocation cos (@NonNull final IJSExpression aExpr)
  {
    return cos ().arg (aExpr);
  }

  @NonNull
  public JSInvocation exp ()
  {
    return global ().invoke ("exp");
  }

  @NonNull
  public JSInvocation exp (@NonNull final IJSExpression aExpr)
  {
    return exp ().arg (aExpr);
  }

  @NonNull
  public JSInvocation floor ()
  {
    return global ().invoke ("floor");
  }

  @NonNull
  public JSInvocation floor (@NonNull final IJSExpression aExpr)
  {
    return floor ().arg (aExpr);
  }

  @NonNull
  public JSInvocation log ()
  {
    return global ().invoke ("log");
  }

  @NonNull
  public JSInvocation log (@NonNull final IJSExpression aExpr)
  {
    return log ().arg (aExpr);
  }

  @NonNull
  public JSInvocation max ()
  {
    return global ().invoke ("max");
  }

  @NonNull
  public JSInvocation max (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    return max ().args (aExprs);
  }

  @NonNull
  public JSInvocation max (@Nullable final IJSExpression... aExprs)
  {
    return max ().args (aExprs);
  }

  @NonNull
  public JSInvocation min ()
  {
    return global ().invoke ("min");
  }

  @NonNull
  public JSInvocation min (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    return min ().args (aExprs);
  }

  @NonNull
  public JSInvocation min (@Nullable final IJSExpression... aExprs)
  {
    return min ().args (aExprs);
  }

  @NonNull
  public JSInvocation pow ()
  {
    return global ().invoke ("pow");
  }

  @NonNull
  public JSInvocation pow (@NonNull final IJSExpression aExprX, @NonNull final IJSExpression aExprY)
  {
    return pow ().arg (aExprX).arg (aExprY);
  }

  @NonNull
  public JSInvocation random ()
  {
    return global ().invoke ("random");
  }

  @NonNull
  public JSInvocation round ()
  {
    return global ().invoke ("round");
  }

  @NonNull
  public JSInvocation round (@NonNull final IJSExpression aExpr)
  {
    return round ().arg (aExpr);
  }

  @NonNull
  public JSInvocation sin ()
  {
    return global ().invoke ("sin");
  }

  @NonNull
  public JSInvocation sin (@NonNull final IJSExpression aExpr)
  {
    return sin ().arg (aExpr);
  }

  @NonNull
  public JSInvocation sqrt ()
  {
    return global ().invoke ("sqrt");
  }

  @NonNull
  public JSInvocation sqrt (@NonNull final IJSExpression aExpr)
  {
    return sqrt ().arg (aExpr);
  }

  @NonNull
  public JSInvocation tan ()
  {
    return global ().invoke ("tan");
  }

  @NonNull
  public JSInvocation tan (@NonNull final IJSExpression aExpr)
  {
    return tan ().arg (aExpr);
  }
}
