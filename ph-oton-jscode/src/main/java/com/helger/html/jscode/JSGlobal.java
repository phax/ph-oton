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

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.annotation.style.PresentForCodeCoverage;

/**
 * This contains global JS function wrappers.<br>
 * Source: http://www.w3schools.com/jsref/jsref_obj_global.asp
 *
 * @author Philip Helger
 */
@Immutable
@CodingStyleguideUnaware
public final class JSGlobal
{
  @PresentForCodeCoverage
  private static final JSGlobal INSTANCE = new JSGlobal ();

  private JSGlobal ()
  {}

  /**
   * @return Global field <code>Infinity</code>
   */
  @NonNull
  public static JSRef Infinity ()
  {
    return JSExpr.ref ("Infinity");
  }

  /**
   * @return Global field <code>NaN</code>
   */
  @NonNull
  public static JSRef NaN ()
  {
    return JSExpr.ref ("NaN");
  }

  /**
   * @return Global field <code>undefined</code>
   */
  @NonNull
  public static JSRef undefined ()
  {
    return JSExpr.ref ("undefined");
  }

  /**
   * @return Global function <code>decodeURI(uri)</code>
   */
  @NonNull
  public static JSInvocation decodeURI ()
  {
    return JSExpr.invoke ("decodeURI");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>decodeURI(uri)</code>
   */
  @NonNull
  public static JSInvocation decodeURI (@NonNull final IJSExpression aURI)
  {
    return decodeURI ().arg (aURI);
  }

  /**
   * @return Global function <code>decodeURIComponent(uri)</code>
   */
  @NonNull
  public static JSInvocation decodeURIComponent ()
  {
    return JSExpr.invoke ("decodeURIComponent");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>decodeURIComponent(uri)</code>
   */
  @NonNull
  public static JSInvocation decodeURIComponent (@NonNull final IJSExpression aURI)
  {
    return decodeURIComponent ().arg (aURI);
  }

  /**
   * @return Global function <code>encodeURI(uri)</code>
   */
  @NonNull
  public static JSInvocation encodeURI ()
  {
    return JSExpr.invoke ("encodeURI");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>encodeURI(uri)</code>
   */
  @NonNull
  public static JSInvocation encodeURI (@NonNull final IJSExpression aURI)
  {
    return encodeURI ().arg (aURI);
  }

  /**
   * @return Global function <code>encodeURIComponent(uri)</code>
   */
  @NonNull
  public static JSInvocation encodeURIComponent ()
  {
    return JSExpr.invoke ("encodeURIComponent");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>encodeURIComponent(uri)</code>
   */
  @NonNull
  public static JSInvocation encodeURIComponent (@NonNull final IJSExpression aURI)
  {
    return encodeURIComponent ().arg (aURI);
  }

  /**
   * @return Global function <code>escape(string)</code>
   */
  @NonNull
  public static JSInvocation escape ()
  {
    return JSExpr.invoke ("escape");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>escape(string)</code>
   */
  @NonNull
  public static JSInvocation escape (@NonNull final IJSExpression aString)
  {
    return escape ().arg (aString);
  }

  /**
   * @return Global function <code>eval(string)</code>
   */
  @NonNull
  public static JSInvocation eval ()
  {
    return JSExpr.invoke ("eval");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>eval(string)</code>
   */
  @NonNull
  public static JSInvocation eval (@NonNull final IJSExpression aString)
  {
    return eval ().arg (aString);
  }

  /**
   * @return Global function <code>isFinite(value)</code>
   */
  @NonNull
  public static JSInvocation isFinite ()
  {
    return JSExpr.invoke ("isFinite");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>isFinite(value)</code>
   */
  @NonNull
  public static JSInvocation isFinite (@NonNull final IJSExpression aValue)
  {
    return isFinite ().arg (aValue);
  }

  /**
   * @return Global function <code>isNaN(value)</code>
   */
  @NonNull
  public static JSInvocation isNaN ()
  {
    return JSExpr.invoke ("isNaN");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>isNaN(value)</code>
   */
  @NonNull
  public static JSInvocation isNaN (@NonNull final IJSExpression aValue)
  {
    return isNaN ().arg (aValue);
  }

  /**
   * @return Global function <code>Number(value)</code>
   */
  @NonNull
  public static JSInvocation Number ()
  {
    return JSExpr.invoke ("Number");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>Number(aValue)</code>
   */
  @NonNull
  public static JSInvocation Number (@NonNull final IJSExpression aValue)
  {
    return Number ().arg (aValue);
  }

  /**
   * @return Global function <code>parseFloat(string)</code>
   */
  @NonNull
  public static JSInvocation parseFloat ()
  {
    return JSExpr.invoke ("parseFloat");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>parseFloat(string)</code>
   */
  @NonNull
  public static JSInvocation parseFloat (@NonNull final IJSExpression aString)
  {
    return parseFloat ().arg (aString);
  }

  /**
   * @return Global function <code>parseInt(string,radix)</code>
   */
  @NonNull
  public static JSInvocation parseInt ()
  {
    return JSExpr.invoke ("parseInt");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>parseInt(string, radix)</code>
   */
  @NonNull
  public static JSInvocation parseInt (@NonNull final IJSExpression aString)
  {
    return parseInt ().arg (aString);
  }

  /**
   * @param aString
   *        parameter
   * @param aRadix
   *        parameter
   * @return Global function <code>parseInt(string, radix)</code>
   */
  @NonNull
  public static JSInvocation parseInt (@NonNull final IJSExpression aString, @NonNull final IJSExpression aRadix)
  {
    return parseInt ().arg (aString).arg (aRadix);
  }

  /**
   * @param aString
   *        parameter
   * @param nRadix
   *        parameter
   * @return Global function <code>parseInt(string, radix)</code>
   */
  @NonNull
  public static JSInvocation parseInt (@NonNull final IJSExpression aString, final int nRadix)
  {
    return parseInt (aString, JSExpr.lit (nRadix));
  }

  /**
   * @return Global function <code>String(object)</code>
   */
  @NonNull
  public static JSInvocation String ()
  {
    return JSExpr.invoke ("String");
  }

  /**
   * @param aObject
   *        parameter
   * @return Global function <code>String(string)</code>
   */
  @NonNull
  public static JSInvocation String (@NonNull final IJSExpression aObject)
  {
    return String ().arg (aObject);
  }

  /**
   * @return Global function <code>unescape(string)</code>
   */
  @NonNull
  public static JSInvocation unescape ()
  {
    return JSExpr.invoke ("unescape");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>unescape(string)</code>
   */
  @NonNull
  public static JSInvocation unescape (@NonNull final IJSExpression aString)
  {
    return unescape ().arg (aString);
  }

  @NonNull
  public static JSRef json ()
  {
    return JSExpr.ref ("JSON");
  }

  @NonNull
  public static JSInvocation jsonParse ()
  {
    return json ().invoke ("parse");
  }

  @NonNull
  public static JSInvocation jsonParse (@NonNull final IJSExpression aText)
  {
    return jsonParse ().arg (aText);
  }

  @NonNull
  public static JSInvocation jsonStringify ()
  {
    return json ().invoke ("stringify");
  }

  @NonNull
  public static JSInvocation jsonStringify (@NonNull final IJSExpression aExpr)
  {
    return jsonStringify ().arg (aExpr);
  }
}
