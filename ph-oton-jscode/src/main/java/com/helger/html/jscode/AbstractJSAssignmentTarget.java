/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

/**
 * Abstract assignment target implementation
 *
 * @author Philip Helger
 */
public abstract class AbstractJSAssignmentTarget extends AbstractJSExpression implements IJSAssignmentTarget
{
  @Nonnull
  public final JSAssignment assign (final boolean bValue)
  {
    return assign (JSExpr.lit (bValue));
  }

  @Nonnull
  public final JSAssignment assign (final char cValue)
  {
    return assign (JSExpr.lit (cValue));
  }

  @Nonnull
  public final JSAssignment assign (final double dValue)
  {
    return assign (JSExpr.lit (dValue));
  }

  @Nonnull
  public final JSAssignment assign (final float fValue)
  {
    return assign (JSExpr.lit (fValue));
  }

  @Nonnull
  public final JSAssignment assign (final int nValue)
  {
    return assign (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assign (final long nValue)
  {
    return assign (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assign (@Nonnull final String sValue)
  {
    return assign (JSExpr.lit (sValue));
  }

  @Nonnull
  public final JSAssignment assign (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assign (this, aExpr);
  }

  @Nonnull
  public final JSAssignment assignPlus (final char cValue)
  {
    return assignPlus (JSExpr.lit (cValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (final double dValue)
  {
    return assignPlus (JSExpr.lit (dValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (final float fValue)
  {
    return assignPlus (JSExpr.lit (fValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (final int nValue)
  {
    return assignPlus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (final long nValue)
  {
    return assignPlus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (@Nonnull final String sValue)
  {
    return assignPlus (JSExpr.lit (sValue));
  }

  @Nonnull
  public final JSAssignment assignPlus (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assignPlus (this, aExpr);
  }

  @Nonnull
  public final JSAssignment assignMinus (final double dValue)
  {
    return assignMinus (JSExpr.lit (dValue));
  }

  @Nonnull
  public final JSAssignment assignMinus (final float fValue)
  {
    return assignMinus (JSExpr.lit (fValue));
  }

  @Nonnull
  public final JSAssignment assignMinus (final int nValue)
  {
    return assignMinus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignMinus (final long nValue)
  {
    return assignMinus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignMinus (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assignMinus (this, aExpr);
  }

  @Nonnull
  public final JSAssignment assignMultiply (final double dValue)
  {
    return assignMultiply (JSExpr.lit (dValue));
  }

  @Nonnull
  public final JSAssignment assignMultiply (final float fValue)
  {
    return assignMultiply (JSExpr.lit (fValue));
  }

  @Nonnull
  public final JSAssignment assignMultiply (final int nValue)
  {
    return assignMultiply (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignMultiply (final long nValue)
  {
    return assignMultiply (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignMultiply (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assignMultiply (this, aExpr);
  }

  @Nonnull
  public final JSAssignment assignDivide (final double dValue)
  {
    return assignDivide (JSExpr.lit (dValue));
  }

  @Nonnull
  public final JSAssignment assignDivide (final float fValue)
  {
    return assignDivide (JSExpr.lit (fValue));
  }

  @Nonnull
  public final JSAssignment assignDivide (final int nValue)
  {
    return assignDivide (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignDivide (final long nValue)
  {
    return assignDivide (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignDivide (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assignDivide (this, aExpr);
  }

  @Nonnull
  public final JSAssignment assignModulo (final int nValue)
  {
    return assignModulo (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignModulo (final long nValue)
  {
    return assignModulo (JSExpr.lit (nValue));
  }

  @Nonnull
  public final JSAssignment assignModulo (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.assignModulo (this, aExpr);
  }
}
