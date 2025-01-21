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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.PresentForCodeCoverage;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * This contains global JS function wrappers.<br>
 * Source: http://www.w3schools.com/jsref/jsref_obj_global.asp
 *
 * @author Philip Helger
 */
@Immutable
@CodingStyleguideUnaware
@SuppressFBWarnings ("NM_METHOD_NAMING_CONVENTION")
public final class JSGlobal
{
  @PresentForCodeCoverage
  private static final JSGlobal INSTANCE = new JSGlobal ();

  private JSGlobal ()
  {}

  /**
   * @return Global field <code>Infinity</code>
   */
  @Nonnull
  public static JSRef Infinity ()
  {
    return JSExpr.ref ("Infinity");
  }

  /**
   * @return Global field <code>NaN</code>
   */
  @Nonnull
  public static JSRef NaN ()
  {
    return JSExpr.ref ("NaN");
  }

  /**
   * @return Global field <code>undefined</code>
   */
  @Nonnull
  public static JSRef undefined ()
  {
    return JSExpr.ref ("undefined");
  }

  /**
   * @return Global function <code>decodeURI(uri)</code>
   */
  @Nonnull
  public static JSInvocation decodeURI ()
  {
    return JSExpr.invoke ("decodeURI");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>decodeURI(uri)</code>
   */
  @Nonnull
  public static JSInvocation decodeURI (@Nonnull final IJSExpression aURI)
  {
    return decodeURI ().arg (aURI);
  }

  /**
   * @return Global function <code>decodeURIComponent(uri)</code>
   */
  @Nonnull
  public static JSInvocation decodeURIComponent ()
  {
    return JSExpr.invoke ("decodeURIComponent");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>decodeURIComponent(uri)</code>
   */
  @Nonnull
  public static JSInvocation decodeURIComponent (@Nonnull final IJSExpression aURI)
  {
    return decodeURIComponent ().arg (aURI);
  }

  /**
   * @return Global function <code>encodeURI(uri)</code>
   */
  @Nonnull
  public static JSInvocation encodeURI ()
  {
    return JSExpr.invoke ("encodeURI");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>encodeURI(uri)</code>
   */
  @Nonnull
  public static JSInvocation encodeURI (@Nonnull final IJSExpression aURI)
  {
    return encodeURI ().arg (aURI);
  }

  /**
   * @return Global function <code>encodeURIComponent(uri)</code>
   */
  @Nonnull
  public static JSInvocation encodeURIComponent ()
  {
    return JSExpr.invoke ("encodeURIComponent");
  }

  /**
   * @param aURI
   *        parameter
   * @return Global function <code>encodeURIComponent(uri)</code>
   */
  @Nonnull
  public static JSInvocation encodeURIComponent (@Nonnull final IJSExpression aURI)
  {
    return encodeURIComponent ().arg (aURI);
  }

  /**
   * @return Global function <code>escape(string)</code>
   */
  @Nonnull
  public static JSInvocation escape ()
  {
    return JSExpr.invoke ("escape");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>escape(string)</code>
   */
  @Nonnull
  public static JSInvocation escape (@Nonnull final IJSExpression aString)
  {
    return escape ().arg (aString);
  }

  /**
   * @return Global function <code>eval(string)</code>
   */
  @Nonnull
  public static JSInvocation eval ()
  {
    return JSExpr.invoke ("eval");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>eval(string)</code>
   */
  @Nonnull
  public static JSInvocation eval (@Nonnull final IJSExpression aString)
  {
    return eval ().arg (aString);
  }

  /**
   * @return Global function <code>isFinite(value)</code>
   */
  @Nonnull
  public static JSInvocation isFinite ()
  {
    return JSExpr.invoke ("isFinite");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>isFinite(value)</code>
   */
  @Nonnull
  public static JSInvocation isFinite (@Nonnull final IJSExpression aValue)
  {
    return isFinite ().arg (aValue);
  }

  /**
   * @return Global function <code>isNaN(value)</code>
   */
  @Nonnull
  public static JSInvocation isNaN ()
  {
    return JSExpr.invoke ("isNaN");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>isNaN(value)</code>
   */
  @Nonnull
  public static JSInvocation isNaN (@Nonnull final IJSExpression aValue)
  {
    return isNaN ().arg (aValue);
  }

  /**
   * @return Global function <code>Number(value)</code>
   */
  @Nonnull
  public static JSInvocation Number ()
  {
    return JSExpr.invoke ("Number");
  }

  /**
   * @param aValue
   *        parameter
   * @return Global function <code>Number(aValue)</code>
   */
  @Nonnull
  public static JSInvocation Number (@Nonnull final IJSExpression aValue)
  {
    return Number ().arg (aValue);
  }

  /**
   * @return Global function <code>parseFloat(string)</code>
   */
  @Nonnull
  public static JSInvocation parseFloat ()
  {
    return JSExpr.invoke ("parseFloat");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>parseFloat(string)</code>
   */
  @Nonnull
  public static JSInvocation parseFloat (@Nonnull final IJSExpression aString)
  {
    return parseFloat ().arg (aString);
  }

  /**
   * @return Global function <code>parseInt(string,radix)</code>
   */
  @Nonnull
  public static JSInvocation parseInt ()
  {
    return JSExpr.invoke ("parseInt");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>parseInt(string, radix)</code>
   */
  @Nonnull
  public static JSInvocation parseInt (@Nonnull final IJSExpression aString)
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
  @Nonnull
  public static JSInvocation parseInt (@Nonnull final IJSExpression aString, @Nonnull final IJSExpression aRadix)
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
  @Nonnull
  public static JSInvocation parseInt (@Nonnull final IJSExpression aString, final int nRadix)
  {
    return parseInt (aString, JSExpr.lit (nRadix));
  }

  /**
   * @return Global function <code>String(object)</code>
   */
  @Nonnull
  public static JSInvocation String ()
  {
    return JSExpr.invoke ("String");
  }

  /**
   * @param aObject
   *        parameter
   * @return Global function <code>String(string)</code>
   */
  @Nonnull
  public static JSInvocation String (@Nonnull final IJSExpression aObject)
  {
    return String ().arg (aObject);
  }

  /**
   * @return Global function <code>unescape(string)</code>
   */
  @Nonnull
  public static JSInvocation unescape ()
  {
    return JSExpr.invoke ("unescape");
  }

  /**
   * @param aString
   *        parameter
   * @return Global function <code>unescape(string)</code>
   */
  @Nonnull
  public static JSInvocation unescape (@Nonnull final IJSExpression aString)
  {
    return unescape ().arg (aString);
  }

  @Nonnull
  public static JSRef json ()
  {
    return JSExpr.ref ("JSON");
  }

  @Nonnull
  public static JSInvocation jsonParse ()
  {
    return json ().invoke ("parse");
  }

  @Nonnull
  public static JSInvocation jsonParse (@Nonnull final IJSExpression aText)
  {
    return jsonParse ().arg (aText);
  }

  @Nonnull
  public static JSInvocation jsonStringify ()
  {
    return json ().invoke ("stringify");
  }

  @Nonnull
  public static JSInvocation jsonStringify (@Nonnull final IJSExpression aExpr)
  {
    return jsonStringify ().arg (aExpr);
  }
}
