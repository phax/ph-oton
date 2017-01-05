/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
 * Marker interface for code components that can be placed to the left of '=' in
 * an assignment. A left hand value can always be a right hand value, so this
 * interface derives from {@link IJSExpression}.
 *
 * @author Philip Helger
 */
public interface IJSAssignmentTarget extends IJSExpression
{
  @Nonnull
  JSAssignment assign (boolean bValue);

  @Nonnull
  JSAssignment assign (char cValue);

  @Nonnull
  JSAssignment assign (double dValue);

  @Nonnull
  JSAssignment assign (float fValue);

  @Nonnull
  JSAssignment assign (int nValue);

  @Nonnull
  JSAssignment assign (long nValue);

  @Nonnull
  JSAssignment assign (@Nonnull String sValue);

  @Nonnull
  JSAssignment assign (@Nonnull IJSExpression aExpr);

  @Nonnull
  JSAssignment assignPlus (char cValue);

  @Nonnull
  JSAssignment assignPlus (double dValue);

  @Nonnull
  JSAssignment assignPlus (float fValue);

  @Nonnull
  JSAssignment assignPlus (int nValue);

  @Nonnull
  JSAssignment assignPlus (long nValue);

  @Nonnull
  JSAssignment assignPlus (@Nonnull String sValue);

  @Nonnull
  JSAssignment assignPlus (@Nonnull IJSExpression aRhs);

  @Nonnull
  JSAssignment assignMinus (double dValue);

  @Nonnull
  JSAssignment assignMinus (float fValue);

  @Nonnull
  JSAssignment assignMinus (int nValue);

  @Nonnull
  JSAssignment assignMinus (long nValue);

  @Nonnull
  JSAssignment assignMinus (@Nonnull IJSExpression aExpr);

  @Nonnull
  JSAssignment assignMultiply (double dValue);

  @Nonnull
  JSAssignment assignMultiply (float fValue);

  @Nonnull
  JSAssignment assignMultiply (int nValue);

  @Nonnull
  JSAssignment assignMultiply (long nValue);

  @Nonnull
  JSAssignment assignMultiply (@Nonnull IJSExpression aRhs);

  @Nonnull
  JSAssignment assignDivide (double dValue);

  @Nonnull
  JSAssignment assignDivide (float fValue);

  @Nonnull
  JSAssignment assignDivide (int nValue);

  @Nonnull
  JSAssignment assignDivide (long nValue);

  @Nonnull
  JSAssignment assignDivide (@Nonnull IJSExpression aRhs);

  @Nonnull
  JSAssignment assignModulo (int nValue);

  @Nonnull
  JSAssignment assignModulo (long nValue);

  @Nonnull
  JSAssignment assignModulo (@Nonnull IJSExpression aRhs);
}
