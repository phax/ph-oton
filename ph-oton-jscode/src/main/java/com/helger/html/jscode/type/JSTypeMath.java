/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

  @Nonnull
  public JSFieldRef e ()
  {
    return global ().ref ("E");
  }

  @Nonnull
  public JSFieldRef ln2 ()
  {
    return global ().ref ("LN2");
  }

  @Nonnull
  public JSFieldRef ln10 ()
  {
    return global ().ref ("LN10");
  }

  @Nonnull
  public JSFieldRef log2e ()
  {
    return global ().ref ("LOG2E");
  }

  @Nonnull
  public JSFieldRef log10e ()
  {
    return global ().ref ("LOG10E");
  }

  @Nonnull
  public JSFieldRef pi ()
  {
    return global ().ref ("PI");
  }

  @Nonnull
  public JSFieldRef sqrt1_2 ()
  {
    return global ().ref ("SQRT1_2");
  }

  @Nonnull
  public JSFieldRef sqrt2 ()
  {
    return global ().ref ("SQRT2");
  }

  @Nonnull
  public JSInvocation abs ()
  {
    return global ().invoke ("abs");
  }

  @Nonnull
  public JSInvocation abs (@Nonnull final IJSExpression aExpr)
  {
    return abs ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation acos ()
  {
    return global ().invoke ("acos");
  }

  @Nonnull
  public JSInvocation acos (@Nonnull final IJSExpression aExpr)
  {
    return acos ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation asin ()
  {
    return global ().invoke ("asin");
  }

  @Nonnull
  public JSInvocation asin (@Nonnull final IJSExpression aExpr)
  {
    return asin ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation atan ()
  {
    return global ().invoke ("atan");
  }

  @Nonnull
  public JSInvocation atan (@Nonnull final IJSExpression aExpr)
  {
    return atan ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation atan2 ()
  {
    return global ().invoke ("atan2");
  }

  @Nonnull
  public JSInvocation atan2 (@Nonnull final IJSExpression aExprY, @Nonnull final IJSExpression aExprX)
  {
    return atan2 ().arg (aExprY).arg (aExprX);
  }

  @Nonnull
  public JSInvocation ceil ()
  {
    return global ().invoke ("ceil");
  }

  @Nonnull
  public JSInvocation ceil (@Nonnull final IJSExpression aExpr)
  {
    return ceil ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation cos ()
  {
    return global ().invoke ("cos");
  }

  @Nonnull
  public JSInvocation cos (@Nonnull final IJSExpression aExpr)
  {
    return cos ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation exp ()
  {
    return global ().invoke ("exp");
  }

  @Nonnull
  public JSInvocation exp (@Nonnull final IJSExpression aExpr)
  {
    return exp ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation floor ()
  {
    return global ().invoke ("floor");
  }

  @Nonnull
  public JSInvocation floor (@Nonnull final IJSExpression aExpr)
  {
    return floor ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation log ()
  {
    return global ().invoke ("log");
  }

  @Nonnull
  public JSInvocation log (@Nonnull final IJSExpression aExpr)
  {
    return log ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation max ()
  {
    return global ().invoke ("max");
  }

  @Nonnull
  public JSInvocation max (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    return max ().args (aExprs);
  }

  @Nonnull
  public JSInvocation max (@Nullable final IJSExpression... aExprs)
  {
    return max ().args (aExprs);
  }

  @Nonnull
  public JSInvocation min ()
  {
    return global ().invoke ("min");
  }

  @Nonnull
  public JSInvocation min (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    return min ().args (aExprs);
  }

  @Nonnull
  public JSInvocation min (@Nullable final IJSExpression... aExprs)
  {
    return min ().args (aExprs);
  }

  @Nonnull
  public JSInvocation pow ()
  {
    return global ().invoke ("pow");
  }

  @Nonnull
  public JSInvocation pow (@Nonnull final IJSExpression aExprX, @Nonnull final IJSExpression aExprY)
  {
    return pow ().arg (aExprX).arg (aExprY);
  }

  @Nonnull
  public JSInvocation random ()
  {
    return global ().invoke ("random");
  }

  @Nonnull
  public JSInvocation round ()
  {
    return global ().invoke ("round");
  }

  @Nonnull
  public JSInvocation round (@Nonnull final IJSExpression aExpr)
  {
    return round ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation sin ()
  {
    return global ().invoke ("sin");
  }

  @Nonnull
  public JSInvocation sin (@Nonnull final IJSExpression aExpr)
  {
    return sin ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation sqrt ()
  {
    return global ().invoke ("sqrt");
  }

  @Nonnull
  public JSInvocation sqrt (@Nonnull final IJSExpression aExpr)
  {
    return sqrt ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation tan ()
  {
    return global ().invoke ("tan");
  }

  @Nonnull
  public JSInvocation tan (@Nonnull final IJSExpression aExpr)
  {
    return tan ().arg (aExpr);
  }
}
