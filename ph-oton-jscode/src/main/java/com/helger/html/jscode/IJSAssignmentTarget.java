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
package com.helger.html.jscode;

import org.jspecify.annotations.NonNull;

/**
 * Marker interface for code components that can be placed to the left of '=' in
 * an assignment. A left hand value can always be a right hand value, so this
 * interface derives from {@link IJSExpression}.
 *
 * @author Philip Helger
 */
public interface IJSAssignmentTarget extends IJSExpression
{
  @NonNull
  JSAssignment assign (boolean bValue);

  @NonNull
  JSAssignment assign (char cValue);

  @NonNull
  JSAssignment assign (double dValue);

  @NonNull
  JSAssignment assign (float fValue);

  @NonNull
  JSAssignment assign (int nValue);

  @NonNull
  JSAssignment assign (long nValue);

  @NonNull
  JSAssignment assign (@NonNull String sValue);

  @NonNull
  JSAssignment assign (@NonNull IJSExpression aExpr);

  @NonNull
  JSAssignment assignPlus (char cValue);

  @NonNull
  JSAssignment assignPlus (double dValue);

  @NonNull
  JSAssignment assignPlus (float fValue);

  @NonNull
  JSAssignment assignPlus (int nValue);

  @NonNull
  JSAssignment assignPlus (long nValue);

  @NonNull
  JSAssignment assignPlus (@NonNull String sValue);

  @NonNull
  JSAssignment assignPlus (@NonNull IJSExpression aRhs);

  @NonNull
  JSAssignment assignMinus (double dValue);

  @NonNull
  JSAssignment assignMinus (float fValue);

  @NonNull
  JSAssignment assignMinus (int nValue);

  @NonNull
  JSAssignment assignMinus (long nValue);

  @NonNull
  JSAssignment assignMinus (@NonNull IJSExpression aExpr);

  @NonNull
  JSAssignment assignMultiply (double dValue);

  @NonNull
  JSAssignment assignMultiply (float fValue);

  @NonNull
  JSAssignment assignMultiply (int nValue);

  @NonNull
  JSAssignment assignMultiply (long nValue);

  @NonNull
  JSAssignment assignMultiply (@NonNull IJSExpression aRhs);

  @NonNull
  JSAssignment assignDivide (double dValue);

  @NonNull
  JSAssignment assignDivide (float fValue);

  @NonNull
  JSAssignment assignDivide (int nValue);

  @NonNull
  JSAssignment assignDivide (long nValue);

  @NonNull
  JSAssignment assignDivide (@NonNull IJSExpression aRhs);

  @NonNull
  JSAssignment assignModulo (int nValue);

  @NonNull
  JSAssignment assignModulo (long nValue);

  @NonNull
  JSAssignment assignModulo (@NonNull IJSExpression aRhs);
}
