/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.builder.JSAnonymousFunction;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSBlock;
import com.helger.html.js.builder.JSExpr;
import com.helger.html.js.builder.JSGlobal;
import com.helger.html.js.builder.JSOp;
import com.helger.html.js.builder.JSVar;

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
    final JSVar aVal = aFuncIntVal.param ("v");
    final JSBlock aIfString = aFuncIntVal.body ()._if (aVal.typeof ().eeq ("string"))._then ();
    if (aValueCleanupFunc != null)
      aIfString.assign (aVal, aValueCleanupFunc.invoke ().arg (aVal));
    aIfString._return (JSGlobal.parseFloat (aVal));
    aFuncIntVal.body ()._if (aVal.typeof ().eeq ("number"))._then ()._return (aVal);
    aFuncIntVal.body ()._return (0);
    return aFuncIntVal;
  }

  /**
   * Create the JS function to print the sum in the footer
   *
   * @param sSuffix
   *        The string suffix to be appended. May not be <code>null</code> but
   *        maybe empty.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static JSAnonymousFunction createFunctionPrintSum (@Nonnull final String sSuffix)
  {
    final JSAnonymousFunction aFuncPrintSum = new JSAnonymousFunction ();
    final JSVar aTotal = aFuncPrintSum.param ("t");
    final JSVar aPageTotal = aFuncPrintSum.param ("pt");

    aFuncPrintSum.body ()._return (JSOp.cond (aTotal.eq (aPageTotal),
                                              aTotal.plus (sSuffix),
                                              aPageTotal.plus (sSuffix + " / ").plus (aTotal).plus (sSuffix)));
    return aFuncPrintSum;
  }

  @Nonnull
  public static JSAnonymousFunction createFooterCallbackColumnSum (@Nonnull @Nonempty final int... aColumns)
  {
    return createFooterCallbackColumnSum (createFunctionIntVal (), createFunctionPrintSum (""), aColumns);
  }

  @Nonnull
  public static JSAnonymousFunction createFooterCallbackColumnSum (@Nonnull final JSAnonymousFunction aFuncIntVal,
                                                                   @Nonnull final JSAnonymousFunction aFuncPrintSum,
                                                                   @Nonnull @Nonempty final int... aColumns)
  {
    ValueEnforcer.notEmpty (aColumns, "Columns");

    final JSAnonymousFunction ret = new JSAnonymousFunction ();
    ret.param ("tfoot");
    ret.param ("data");
    ret.param ("start");
    ret.param ("end");
    ret.param ("display");
    final JSVar aAPI = ret.body ().var ("api", JSExpr.THIS.invoke ("api"));
    final JSVar aIntVal = ret.body ().var ("funcIntVal", aFuncIntVal);
    final JSVar aPrintSum = ret.body ().var ("funcPrintSum", aFuncPrintSum);
    final JSVar aTotal = ret.body ().var ("total");
    final JSVar aPageTotal = ret.body ().var ("pagetotal");
    // The reduce function: plus
    JSVar aReduce;
    {
      final JSAnonymousFunction aFuncReduce = new JSAnonymousFunction ();
      final JSVar aParam1 = aFuncReduce.param ("a");
      final JSVar aParam2 = aFuncReduce.param ("b");
      aFuncReduce.body ()._return (JSExpr.invoke (aIntVal).arg (aParam1).plus (JSExpr.invoke (aIntVal).arg (aParam2)));
      aReduce = ret.body ().var ("funcReduce", aFuncReduce);
    }

    for (final int nTarget : aColumns)
    {
      // Calc overall total
      ret.body ().assign (aTotal,
                          aAPI.invoke ("column").arg (nTarget).invoke ("data").invoke ("reduce").arg (aReduce).arg (0));
      // Calc visible total
      ret.body ().assign (aPageTotal,
                          aAPI.invoke ("column")
                              .arg (nTarget)
                              .arg (new JSAssocArray ().add ("page", "current"))
                              .invoke ("data")
                              .invoke ("reduce")
                              .arg (aReduce)
                              .arg (0));
      // Update the respective footer
      ret.body ().add (JQuery.jQuery (aAPI.invoke ("column").arg (nTarget).invoke ("footer"))
                             .html (JSExpr.invoke (aPrintSum).arg (aTotal).arg (aPageTotal)));
    }

    return ret;
  }
}
