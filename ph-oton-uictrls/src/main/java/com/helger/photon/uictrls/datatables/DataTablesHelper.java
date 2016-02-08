/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryInvocation;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jquery.JQuerySelectorList;
import com.helger.html.js.IHasJSCode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSBlock;
import com.helger.html.jscode.JSConditional;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSGlobal;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSOp;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSRef;
import com.helger.html.jscode.JSVar;
import com.helger.photon.uictrls.datatables.column.DataTablesColumnDef;

/**
 * Some sanity functionality for {@link DataTables} objects.
 *
 * @author Philip Helger
 */
@Immutable
public final class DataTablesHelper
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesHelper.class);

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

    aFuncPrintSum.body ()
                 ._return (JSOp.cond (aTotal.eq (aPageTotal),
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

  /**
   * Create an {@link HCScriptInline} or {@link HCScriptInlineOnDocumentReady}
   * block that handles expand and collapse. The following pre-conditions must
   * be met: The first column must be the expand/collapse column and it must
   * contain an image where the event handler is registered.
   *
   * @param aDataTables
   *        Source data tables. Never <code>null</code>.
   * @param aExpandImgURL
   *        The URL of the expand icon (closed state)
   * @param aCollapseImgURL
   *        The URL of the collapse icon (open state)
   * @param nColumnIndexWithDetails
   *        The index of the column that contains the details. Must be &ge; 0
   *        and is usually hidden.
   * @param aCellClass
   *        The CSS class to be applied to the created cell. May be
   *        <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @Deprecated
  public static IHCNode createExpandCollapseHandling (@Nonnull final DataTables aDataTables,
                                                      @Nonnull final ISimpleURL aExpandImgURL,
                                                      @Nonnull final ISimpleURL aCollapseImgURL,
                                                      @Nonnegative final int nColumnIndexWithDetails,
                                                      @Nullable final ICSSClassProvider aCellClass)
  {
    final DataTablesColumnDef aColumn = aDataTables.getOrCreateColumnOfTarget (nColumnIndexWithDetails);
    if (aColumn != null && aColumn.isVisible ())
      s_aLogger.warn ("The column with the expand text, should not be visible!");

    final JSRef jsTable = JSExpr.ref (aDataTables.getJSVariableName ());

    final JSPackage aPackage = new JSPackage ();
    final JSAnonymousFunction aOpenCloseCallback = new JSAnonymousFunction ();
    {
      final JSVar jsTR = aOpenCloseCallback.body ().var ("r",
                                                         JQuery.jQueryThis ().parents (EHTMLElement.TR).component0 ());
      final JSConditional aIf = aOpenCloseCallback.body ()._if (jsTable.invoke ("fnIsOpen").arg (jsTR));
      aIf._then ().assign (JSExpr.THIS.ref (CHTMLAttributes.SRC), aExpandImgURL.getAsStringWithEncodedParameters ());
      aIf._then ().invoke (jsTable, "fnClose").arg (jsTR);
      aIf._else ().assign (JSExpr.THIS.ref (CHTMLAttributes.SRC), aCollapseImgURL.getAsStringWithEncodedParameters ());
      aIf._else ()
         .invoke (jsTable, "fnOpen")
         .arg (jsTR)
         .arg (jsTable.invoke ("fnGetData").arg (jsTR).component (nColumnIndexWithDetails))
         .arg (aCellClass == null ? null : aCellClass.getCSSClass ());
    }
    aPackage.add (JQuery.idRef (aDataTables.getTableID ())
                        .on ()
                        .arg ("click")
                        .arg (new JQuerySelectorList (JQuerySelector.element (EHTMLElement.TBODY),
                                                      JQuerySelector.element (EHTMLElement.TD),
                                                      JQuerySelector.element (EHTMLElement.IMG)))
                        .arg (aOpenCloseCallback));
    return aDataTables.isGenerateOnDocumentReady () ? new HCScriptInlineOnDocumentReady (aPackage)
                                                    : new HCScriptInline (aPackage);
  }

  /**
   * @param aDataTables
   *        Source data tables. Never <code>null</code>.
   * @param aRowSelect
   *        E.g. <code>JQuery.jQueryThis ().parents (EHTMLElement.TR)</code>
   * @param bSwapUsingJQuery
   *        Use it only, if if no actions can be performed on the table! This is
   *        much quicker.
   * @return The created JS code
   */
  @Nonnull
  @Deprecated
  public static IHasJSCode getMoveRowUpCode (@Nonnull final DataTables aDataTables,
                                             @Nonnull final JQueryInvocation aRowSelect,
                                             final boolean bSwapUsingJQuery)
  {
    final JSRef jsTable = JSExpr.ref (aDataTables.getJSVariableName ());

    final JSPackage aPackage = new JSPackage ();
    final JSVar aRow = aPackage.var ("row", aRowSelect);
    final JSVar aPrevRow = aPackage.var ("prow", aRow.invoke ("prev"));
    final JSBlock aIfPrev = aPackage._if (aPrevRow.ref ("length").gt (0))._then ();

    if (bSwapUsingJQuery)
    {
      // This is much quicker, if sorting and searching is disabled
      aIfPrev.add (aRow.invoke ("detach"));
      aIfPrev.add (aPrevRow.invoke ("before").arg (aRow));
    }
    else
    {
      final JSVar aRow0 = aIfPrev.var ("row0", aRow.invoke ("get").arg (0));
      final JSVar aPrevRow0 = aIfPrev.var ("prow0", aPrevRow.invoke ("get").arg (0));

      final JSVar aData = aIfPrev.var ("data", jsTable.invoke ("fnGetData").arg (aRow0));
      final JSVar aPrevData = aIfPrev.var ("prevdata", jsTable.invoke ("fnGetData").arg (aPrevRow0));

      aIfPrev.invoke (jsTable, "fnUpdate").arg (aPrevData).arg (aRow0);
      aIfPrev.invoke (jsTable, "fnUpdate").arg (aData).arg (aPrevRow0);
    }
    return aPackage;
  }

  /**
   * @param aDataTables
   *        Source data tables. Never <code>null</code>.
   * @param aRowSelect
   *        E.g. <code>JQuery.jQueryThis ().parents (EHTMLElement.TR)</code>
   * @param bSwapUsingJQuery
   *        Use it only, if if no actions can be performed on the table! This is
   *        much quicker.
   * @return The created JS code
   */
  @Nonnull
  @Deprecated
  public static IHasJSCode getMoveRowDownCode (@Nonnull final DataTables aDataTables,
                                               @Nonnull final JQueryInvocation aRowSelect,
                                               final boolean bSwapUsingJQuery)
  {
    final JSRef jsTable = JSExpr.ref (aDataTables.getJSVariableName ());

    final JSPackage aPackage = new JSPackage ();
    final JSVar aRow = aPackage.var ("row", aRowSelect);
    final JSVar aNextRow = aPackage.var ("nrow", aRow.invoke ("next"));
    final JSBlock aIfNext = aPackage._if (aNextRow.ref ("length").gt (0))._then ();

    if (bSwapUsingJQuery)
    {
      // This is much quicker, if sorting and searching is disabled
      aIfNext.add (aRow.invoke ("detach"));
      aIfNext.add (aNextRow.invoke ("after").arg (aRow));
    }
    else
    {
      final JSVar aRow0 = aIfNext.var ("row0", aRow.invoke ("get").arg (0));
      final JSVar aNextRow0 = aIfNext.var ("nrow0", aNextRow.invoke ("get").arg (0));

      final JSVar aData = aIfNext.var ("data", jsTable.invoke ("fnGetData").arg (aRow0));
      final JSVar aNextData = aIfNext.var ("nextdata", jsTable.invoke ("fnGetData").arg (aNextRow0));

      aIfNext.invoke (jsTable, "fnUpdate").arg (aNextData).arg (aRow0);
      aIfNext.invoke (jsTable, "fnUpdate").arg (aData).arg (aNextRow0);
    }
    return aPackage;
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
