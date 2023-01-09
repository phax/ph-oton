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
package com.helger.photon.uictrls.datatables;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.html.jscode.JSAnonymousFunction;

/**
 * Define how to sum up data tables columns.
 * 
 * @author Philip Helger
 */
@Immutable
public class FooterCallbackSumColumn implements Serializable
{
  private final JSAnonymousFunction m_aFuncIntVal;
  private final JSAnonymousFunction m_aFuncPrintSum;
  private final int m_nCalcColumn;
  private final int m_nPrintColumn;

  /**
   * Parameter for a dynamic callback for calculated datatables footer values
   * (like sums etc.)
   *
   * @param aFuncIntVal
   *        JS function to be invoked on all cells to convert a String into an
   *        int. Use it to e.g. cut prefixes and/or suffixes.
   * @param aFuncPrintSum
   *        JS function to be invoked to format the sum. The parameter is a
   *        floating point value (the sum).
   * @param nColumn
   *        The 0-based column index for which the sum should be calculated and
   *        where the sum should be printed.
   */
  public FooterCallbackSumColumn (@Nonnull final JSAnonymousFunction aFuncIntVal,
                                  @Nonnull final JSAnonymousFunction aFuncPrintSum,
                                  @Nonnegative final int nColumn)
  {
    this (aFuncIntVal, aFuncPrintSum, nColumn, nColumn);
  }

  /**
   * Parameter for a dynamic callback for calculated datatables footer values
   * (like sums etc.)
   *
   * @param aFuncIntVal
   *        JS function to be invoked on all cells to convert a String into an
   *        int. Use it to e.g. cut prefixes and/or suffixes.
   * @param aFuncPrintSum
   *        JS function to be invoked to format the sum. The first parameter is
   *        the total sum as float, and the second parameter is the page sum as
   *        float.
   * @param nCalcColumn
   *        The 0-based column index for which the sum should be calculated.
   *        This should be a hidden column that contains the numerical value
   *        without any formatting.
   * @param nPrintColumn
   *        The 0-based column index where the sum should be printed. This
   *        should be a visible column.
   */
  public FooterCallbackSumColumn (@Nonnull final JSAnonymousFunction aFuncIntVal,
                                  @Nonnull final JSAnonymousFunction aFuncPrintSum,
                                  @Nonnegative final int nCalcColumn,
                                  @Nonnegative final int nPrintColumn)
  {
    m_aFuncIntVal = aFuncIntVal;
    m_aFuncPrintSum = aFuncPrintSum;
    m_nCalcColumn = nCalcColumn;
    m_nPrintColumn = nPrintColumn;
  }

  @Nonnull
  public JSAnonymousFunction getJSFuncIntVal ()
  {
    return m_aFuncIntVal;
  }

  @Nonnull
  public JSAnonymousFunction getJSFuncPrintSum ()
  {
    return m_aFuncPrintSum;
  }

  @Nonnegative
  public int getCalcColumn ()
  {
    return m_nCalcColumn;
  }

  @Nonnegative
  public int getPrintColumn ()
  {
    return m_nPrintColumn;
  }
}
