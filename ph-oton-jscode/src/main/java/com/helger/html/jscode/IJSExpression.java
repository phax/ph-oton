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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.json.IJson;

/**
 * A JS expression.
 *
 * @author Philip Helger
 */
public interface IJSExpression extends IJSGeneratable
{
  /**
   * @return "-[this]" from "[this]".
   */
  @NonNull
  IJSExpression minus ();

  /**
   * @return "([this]);" from "[this]".
   */
  @NonNull
  IJSExpression inParantheses ();

  /**
   * @return "![this]" from "[this]".
   */
  @NonNull
  IJSExpression not ();

  /**
   * @return "~[this]" from "[this]".
   */
  @NonNull
  IJSExpression complement ();

  /**
   * @return "[this]++" from "[this]".
   */
  @NonNull
  IJSExpression incrPostfix ();

  /**
   * @return "++[this]" from "[this]".
   */
  @NonNull
  IJSExpression incrPrefix ();

  /**
   * @return "[this]--" from "[this]".
   */
  @NonNull
  IJSExpression decrPostfix ();

  /**
   * @return "--[this]" from "[this]".
   */
  @NonNull
  IJSExpression decrPrefix ();

  /**
   * @return "typeof [this]" from "[this]"
   */
  @NonNull
  IJSExpression typeof ();

  /**
   * @param aType
   *        The type to compare against
   * @return "typeof [this] === typename" from "[this]"
   */
  @NonNull
  IJSExpression isTypeof (@NonNull AbstractJSType aType);

  /**
   * @param aType
   *        The type to compare against
   * @return "typeof [this] !== typename" from "[this]"
   */
  @NonNull
  IJSExpression isNotTypeof (@NonNull AbstractJSType aType);

  /**
   * @return "typeof [this] === 'undefined'" or "[this] === undefined" from
   *         "[this]"
   */
  @NonNull
  IJSExpression isUndefined ();

  /**
   * @return "typeof [this] !== 'undefined'" or "[this] !== undefined" from
   *         "[this]"
   */
  @NonNull
  IJSExpression isNotUndefined ();

  /**
   * @param cValue
   *        constant char to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (char cValue);

  /**
   * @param dValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (double dValue);

  /**
   * @param fValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (float fValue);

  /**
   * @param nValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (int nValue);

  /**
   * @param nValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (long nValue);

  /**
   * @param aValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (@NonNull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (@NonNull BigDecimal aValue);

  /**
   * @param sValue
   *        constant String value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (@NonNull String sValue);

  /**
   * @param aExpr
   *        value to add
   * @return "[this]+[right]"
   */
  @NonNull
  IJSExpression plus (@NonNull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (double dValue);

  /**
   * @param fValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (float fValue);

  /**
   * @param nValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (int nValue);

  /**
   * @param nValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (long nValue);

  /**
   * @param aValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (@NonNull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (@NonNull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to subtract
   * @return "[this]-[right]"
   */
  @NonNull
  IJSExpression minus (@NonNull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (double dValue);

  /**
   * @param fValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (float fValue);

  /**
   * @param nValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (int nValue);

  /**
   * @param nValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (long nValue);

  /**
   * @param aValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (@NonNull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (@NonNull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to multiply with
   * @return "[this]*[right]"
   */
  @NonNull
  IJSExpression mul (@NonNull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (double dValue);

  /**
   * @param fValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (float fValue);

  /**
   * @param nValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (int nValue);

  /**
   * @param nValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (long nValue);

  /**
   * @param aValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (@NonNull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (@NonNull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to divide through
   * @return "[this]/[right]"
   */
  @NonNull
  IJSExpression div (@NonNull IJSExpression aExpr);

  /**
   * @param nValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @NonNull
  IJSExpression mod (int nValue);

  /**
   * @param nValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @NonNull
  IJSExpression mod (long nValue);

  /**
   * @param aValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @NonNull
  IJSExpression mod (@NonNull BigInteger aValue);

  /**
   * @param aExpr
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @NonNull
  IJSExpression mod (@NonNull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @NonNull
  IJSExpression shl (int nValue);

  /**
   * @param nValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @NonNull
  IJSExpression shl (long nValue);

  /**
   * @param aValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @NonNull
  IJSExpression shl (@NonNull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @NonNull
  IJSExpression shl (@NonNull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shr (int nValue);

  /**
   * @param nValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shr (long nValue);

  /**
   * @param aValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shr (@NonNull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shr (@NonNull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shrz (int nValue);

  /**
   * @param nValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shrz (long nValue);

  /**
   * @param aValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shrz (@NonNull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @NonNull
  IJSExpression shrz (@NonNull IJSExpression aExpr);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param nValue
   *        value
   * @return [this] &amp; value
   */
  @NonNull
  IJSExpression band (int nValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param nValue
   *        value
   * @return [this] &amp; value
   */
  @NonNull
  IJSExpression band (long nValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param aValue
   *        value
   * @return [this] &amp; value
   */
  @NonNull
  IJSExpression band (@NonNull BigInteger aValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param aExpr
   *        value
   * @return [this] &amp; value
   */
  @NonNull
  IJSExpression band (@NonNull IJSExpression aExpr);

  /**
   * Bit-wise OR '|'.
   *
   * @param nValue
   *        value
   * @return [this] | value
   */
  @NonNull
  IJSExpression bor (int nValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param nValue
   *        value
   * @return [this] | value
   */
  @NonNull
  IJSExpression bor (long nValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param aValue
   *        value
   * @return [this] | value
   */
  @NonNull
  IJSExpression bor (@NonNull BigInteger aValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param aExpr
   *        value
   * @return [this] | value
   */
  @NonNull
  IJSExpression bor (@NonNull IJSExpression aExpr);

  /**
   * Logical AND '&amp;&amp;'.
   *
   * @param aExpr
   *        value
   * @return [this] &amp;&amp; value
   */
  @NonNull
  IJSExpression cand (@NonNull IJSExpression aExpr);

  /**
   * Logical OR '||'.
   *
   * @param aExpr
   *        value
   * @return [this] || value
   */
  @NonNull
  IJSExpression cor (@NonNull IJSExpression aExpr);

  /**
   * XOR '^'.
   *
   * @param nValue
   *        value
   * @return [this] ^ value
   */
  @NonNull
  IJSExpression xor (int nValue);

  /**
   * XOR '^'.
   *
   * @param nValue
   *        value
   * @return [this] ^ value
   */
  @NonNull
  IJSExpression xor (long nValue);

  /**
   * XOR '^'.
   *
   * @param aValue
   *        value
   * @return [this] ^ value
   */
  @NonNull
  IJSExpression xor (@NonNull BigInteger aValue);

  /**
   * XOR '^'.
   *
   * @param aExpr
   *        value
   * @return [this] ^ value
   */
  @NonNull
  IJSExpression xor (@NonNull IJSExpression aExpr);

  /**
   * Lower than '&lt;'.
   *
   * @param dValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (double dValue);

  /**
   * Lower than '&lt;'.
   *
   * @param fValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (float fValue);

  /**
   * Lower than '&lt;'.
   *
   * @param nValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (int nValue);

  /**
   * Lower than '&lt;'.
   *
   * @param nValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (long nValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (@NonNull BigInteger aValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aValue
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (@NonNull BigDecimal aValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aExpr
   *        value
   * @return [this] &lt; value
   */
  @NonNull
  IJSExpression lt (@NonNull IJSExpression aExpr);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param dValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (double dValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param fValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (float fValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param nValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (int nValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param nValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (long nValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (@NonNull BigInteger aValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aValue
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (@NonNull BigDecimal aValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aExpr
   *        value
   * @return [this] &lt;= value
   */
  @NonNull
  IJSExpression lte (@NonNull IJSExpression aExpr);

  /**
   * Greater than '&gt;'.
   *
   * @param dValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (double dValue);

  /**
   * Greater than '&gt;'.
   *
   * @param fValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (float fValue);

  /**
   * Greater than '&gt;'.
   *
   * @param nValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (int nValue);

  /**
   * Greater than '&gt;'.
   *
   * @param nValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (long nValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (@NonNull BigInteger aValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aValue
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (@NonNull BigDecimal aValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aExpr
   *        value
   * @return [this] &gt; value
   */
  @NonNull
  IJSExpression gt (@NonNull IJSExpression aExpr);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param dValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (double dValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param fValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (float fValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param nValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (int nValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param nValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (long nValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (@NonNull BigInteger aValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aValue
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (@NonNull BigDecimal aValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aExpr
   *        value
   * @return [this] &gt;= value
   */
  @NonNull
  IJSExpression gte (@NonNull IJSExpression aExpr);

  /**
   * Equals '=='.
   *
   * @param bValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (boolean bValue);

  /**
   * Equals '=='.
   *
   * @param cValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (char cValue);

  /**
   * Equals '=='.
   *
   * @param fValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (float fValue);

  /**
   * Equals '=='.
   *
   * @param dValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (double dValue);

  /**
   * Equals '=='.
   *
   * @param nValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (int nValue);

  /**
   * Equals '=='.
   *
   * @param nValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (long nValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (@NonNull BigInteger aValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (@NonNull BigDecimal aValue);

  /**
   * Equals '=='.
   *
   * @param sValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (@NonNull String sValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (@NonNull IJson aValue);

  /**
   * Equals '=='.
   *
   * @param aExpr
   *        value
   * @return [this] == value
   */
  @NonNull
  IJSExpression eq (@NonNull IJSExpression aExpr);

  /**
   * Exactly equals '==='.
   *
   * @param bValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (boolean bValue);

  /**
   * Exactly equals '==='.
   *
   * @param cValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (char cValue);

  /**
   * Exactly equals '==='.
   *
   * @param fValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (float fValue);

  /**
   * Exactly equals '==='.
   *
   * @param dValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (double dValue);

  /**
   * Exactly equals '==='.
   *
   * @param nValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (int nValue);

  /**
   * Exactly equals '==='.
   *
   * @param nValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (long nValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (@NonNull BigInteger aValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (@NonNull BigDecimal aValue);

  /**
   * Exactly equals '==='.
   *
   * @param sValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (@NonNull String sValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (@NonNull IJson aValue);

  /**
   * Exactly equals '==='.
   *
   * @param aExpr
   *        value
   * @return [this] === value
   */
  @NonNull
  IJSExpression eeq (@NonNull IJSExpression aExpr);

  /**
   * Not equals '!='.
   *
   * @param bValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (boolean bValue);

  /**
   * Not equals '!='.
   *
   * @param cValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (char cValue);

  /**
   * Not equals '!='.
   *
   * @param fValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (float fValue);

  /**
   * Not equals '!='.
   *
   * @param dValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (double dValue);

  /**
   * Not equals '!='.
   *
   * @param nValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (int nValue);

  /**
   * Not equals '!='.
   *
   * @param nValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (long nValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (@NonNull BigInteger aValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (@NonNull BigDecimal aValue);

  /**
   * Not equals '!='.
   *
   * @param sValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (@NonNull String sValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (@NonNull IJson aValue);

  /**
   * Not equals '!='.
   *
   * @param aExpr
   *        value
   * @return [this] != value
   */
  @NonNull
  IJSExpression ne (@NonNull IJSExpression aExpr);

  /**
   * Exactly not equals '!=='.
   *
   * @param bValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (boolean bValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param cValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (char cValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param fValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (float fValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param dValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (double dValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param nValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (int nValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param nValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (long nValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (@NonNull BigInteger aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (@NonNull BigDecimal aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param sValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (@NonNull String sValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (@NonNull IJson aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aExpr
   *        value
   * @return [this] !== value
   */
  @NonNull
  IJSExpression ene (@NonNull IJSExpression aExpr);

  /**
   * instanceof
   *
   * @param aType
   *        Type to check
   * @return [this] instanceof type
   */
  @NonNull
  @CodingStyleguideUnaware
  IJSExpression _instanceof (@NonNull AbstractJSType aType);

  /**
   * @param aMethod
   *        Method to invoke
   * @return "[this].[method]". Arguments shall be added to the returned
   *         {@link JSInvocation} object.
   */
  @NonNull
  JSInvocation invoke (@NonNull JSMethod aMethod);

  /**
   * @param sMethod
   *        Method to invoke
   * @return "[this].[method]". Arguments shall be added to the returned
   *         {@link JSInvocation} object.
   */
  @NonNull
  JSInvocation invoke (@NonNull String sMethod);

  /**
   * @param aField
   *        Field to reference
   * @return "[this].[field]"
   */
  @NonNull
  JSFieldRef ref (@NonNull AbstractJSVariable <?> aField);

  /**
   * @param sField
   *        Field to reference
   * @return "[this].[field]"
   */
  @NonNull
  JSFieldRef ref (@NonNull String sField);

  /**
   * Get the array component at the specified index.
   *
   * @param cIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @NonNull
  JSArrayCompRef component (char cIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param nIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @NonNull
  JSArrayCompRef component (int nIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param nIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @NonNull
  JSArrayCompRef component (long nIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param sIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @NonNull
  JSArrayCompRef component (@NonNull String sIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param aExpr
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @NonNull
  JSArrayCompRef component (@NonNull IJSExpression aExpr);

  /**
   * Get the array component at index 0.
   *
   * @return [this]<b>[</b>0<b>]</b>
   */
  @NonNull
  JSArrayCompRef component0 ();
}
