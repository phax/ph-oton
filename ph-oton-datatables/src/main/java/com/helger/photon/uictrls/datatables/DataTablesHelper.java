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
package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.html.jquery.JQuery;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSBlock;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSGlobal;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSLet;
import com.helger.html.jscode.JSOp;
import com.helger.html.jscode.JSParam;

/**
 * Some sanity functionality for {@link DataTables} objects.
 *
 * @author Philip Helger
 */
@Immutable
public final class DataTablesHelper
{
  private DataTablesHelper ()
  {}

  /**
   * Create the JS conversion routine from object to number.
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static JSAnonymousFunction createFunctionIntVal ()
  {
    return createFunctionIntVal ((JSAnonymousFunction) null);
  }

  @Nonnull
  public static JSAnonymousFunction createFunctionIntVal (@Nullable final JSAnonymousFunction aValueCleanupFunc)
  {
    final JSAnonymousFunction aFuncIntVal = new JSAnonymousFunction ();
    final JSParam aVal = aFuncIntVal.param ("v");

    // If string
    final JSBlock aIfString = aFuncIntVal.body ()._if (aVal.typeof ().eeq ("string"))._then ();
    if (aValueCleanupFunc != null)
      aIfString.assign (aVal, aValueCleanupFunc.invoke ().arg (aVal));
    aIfString._return (JSGlobal.parseFloat (aVal));

    // If number
    aFuncIntVal.body ()._if (aVal.typeof ().eeq ("number"))._then ()._return (aVal);

    // Assume 0
    aFuncIntVal.body ()._return (0);
    return aFuncIntVal;
  }

  /**
   * Create the JS function to print the sum in the footer
   *
   * @param sPrefix
   *        The prefix to be prepended. May be <code>null</code> or empty.
   * @param sSuffix
   *        The string suffix to be appended. May be <code>null</code> or empty.
   * @param sBothPrefix
   *        The prefix to be printed if page total and overall total are
   *        displayed. May be <code>null</code>.
   * @param sBothSep
   *        The separator to be printed if page total and overall total are
   *        displayed. May be <code>null</code>.
   * @param sBothSuffix
   *        The suffix to be printed if page total and overall total are
   *        displayed. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static JSAnonymousFunction createFunctionPrintSum (@Nullable final String sPrefix,
                                                            @Nullable final String sSuffix,
                                                            @Nullable final String sBothPrefix,
                                                            @Nullable final String sBothSep,
                                                            @Nullable final String sBothSuffix)
  {
    final JSAnonymousFunction aFuncPrintSum = new JSAnonymousFunction ();

    IJSExpression aTotal = aFuncPrintSum.param ("t");
    IJSExpression aPageTotal = aFuncPrintSum.param ("pt");
    if (StringHelper.hasText (sPrefix))
    {
      aTotal = JSExpr.lit (sPrefix).plus (aTotal);
      aPageTotal = JSExpr.lit (sPrefix).plus (aPageTotal);
    }
    if (StringHelper.hasText (sSuffix))
    {
      aTotal = aTotal.plus (sSuffix);
      aPageTotal = aPageTotal.plus (sSuffix);
    }

    IJSExpression aBoth;
    if (StringHelper.hasText (sBothPrefix))
      aBoth = JSExpr.lit (sBothPrefix).plus (aPageTotal);
    else
      aBoth = aPageTotal;
    if (StringHelper.hasText (sBothSep))
      aBoth = aBoth.plus (sBothSep);
    aBoth = aBoth.plus (aTotal);
    if (StringHelper.hasText (sBothSuffix))
      aBoth = aBoth.plus (sBothSuffix);

    aFuncPrintSum.body ()._return (JSOp.cond (aTotal.eq (aPageTotal), aTotal, aBoth));
    return aFuncPrintSum;
  }

  /**
   * Create a dynamic callback for calculated footer values (like sums etc.)
   *
   * @param aColumns
   *        The columns to be added. May neither be <code>null</code> nor empty.
   * @return A JS function to be used as the dataTables footer callback.
   */
  @Nonnull
  public static JSAnonymousFunction createFooterCallbackColumnSum (@Nonnull final FooterCallbackSumColumn... aColumns)
  {
    ValueEnforcer.notEmpty (aColumns, "Columns");

    final JSAnonymousFunction ret = new JSAnonymousFunction ();
    ret.param ("tfoot");
    ret.param ("data");
    ret.param ("start");
    ret.param ("end");
    ret.param ("display");
    final JSLet aAPI = ret.body ().let ("api", JSExpr.THIS.invoke ("api"));

    for (final FooterCallbackSumColumn aColumn : aColumns)
    {
      final String sSuffix = Integer.toString (aColumn.getPrintColumn ());
      final JSLet aIntVal = ret.body ().let ("funcIntVal" + sSuffix, aColumn.getJSFuncIntVal ());
      final JSLet aPrintSum = ret.body ().let ("funcPrintSum" + sSuffix, aColumn.getJSFuncPrintSum ());

      // The reduce function: plus
      final JSAnonymousFunction aFuncReduce = new JSAnonymousFunction ();
      {
        final JSParam aParam1 = aFuncReduce.param ("a");
        final JSParam aParam2 = aFuncReduce.param ("b");
        aFuncReduce.body ()
                   ._return (JSExpr.invoke (aIntVal).arg (aParam1).plus (JSExpr.invoke (aIntVal).arg (aParam2)));
      }
      final JSLet aReduce = ret.body ().let ("funcReduce" + sSuffix, aFuncReduce);

      // Calc overall total
      final JSLet aTotal = ret.body ()
                              .let ("total" + sSuffix,
                                    aAPI.invoke ("column")
                                        .arg (aColumn.getCalcColumn ())
                                        .invoke ("data")
                                        .invoke ("reduce")
                                        .arg (aReduce)
                                        .arg (0));
      // Calc visible total
      final JSLet aPageTotal = ret.body ()
                                  .let ("pagetotal" + sSuffix,
                                        aAPI.invoke ("column")
                                            .arg (aColumn.getCalcColumn ())
                                            .arg (new JSAssocArray ().add ("page", "current"))
                                            .invoke ("data")
                                            .invoke ("reduce")
                                            .arg (aReduce)
                                            .arg (0));
      // Update the respective footer
      ret.body ()
         .add (JQuery.jQuery (aAPI.invoke ("column").arg (aColumn.getPrintColumn ()).invoke ("footer"))
                     .html (JSExpr.invoke (aPrintSum).arg (aTotal).arg (aPageTotal)));
    }

    return ret;
  }

  /**
   * Remove all filters and redraw the data table
   *
   * @param aDTSelect
   *        JS expression that selects 1-n datatables
   * @return The invocation to clear the filter. Never <code>null</code>.
   */
  @Nonnull
  public static JSInvocation createClearFilterCode (@Nonnull final IJSExpression aDTSelect)
  {
    return aDTSelect.invoke ("DataTable")
                    .invoke ("search")
                    .arg ("")
                    .invoke ("columns")
                    .invoke ("search")
                    .arg ("")
                    .invoke ("draw");
  }
}
