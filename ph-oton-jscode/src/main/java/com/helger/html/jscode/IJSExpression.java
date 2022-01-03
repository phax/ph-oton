/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.CodingStyleguideUnaware;
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
  @Nonnull
  IJSExpression minus ();

  /**
   * @return "([this]);" from "[this]".
   */
  @Nonnull
  IJSExpression inParantheses ();

  /**
   * @return "![this]" from "[this]".
   */
  @Nonnull
  IJSExpression not ();

  /**
   * @return "~[this]" from "[this]".
   */
  @Nonnull
  IJSExpression complement ();

  /**
   * @return "[this]++" from "[this]".
   */
  @Nonnull
  IJSExpression incrPostfix ();

  /**
   * @return "++[this]" from "[this]".
   */
  @Nonnull
  IJSExpression incrPrefix ();

  /**
   * @return "[this]--" from "[this]".
   */
  @Nonnull
  IJSExpression decrPostfix ();

  /**
   * @return "--[this]" from "[this]".
   */
  @Nonnull
  IJSExpression decrPrefix ();

  /**
   * @return "typeof [this]" from "[this]"
   */
  @Nonnull
  IJSExpression typeof ();

  /**
   * @param aType
   *        The type to compare against
   * @return "typeof [this] === typename" from "[this]"
   */
  @Nonnull
  IJSExpression isTypeof (@Nonnull AbstractJSType aType);

  /**
   * @param aType
   *        The type to compare against
   * @return "typeof [this] !== typename" from "[this]"
   */
  @Nonnull
  IJSExpression isNotTypeof (@Nonnull AbstractJSType aType);

  /**
   * @return "typeof [this] === 'undefined'" or "[this] === undefined" from
   *         "[this]"
   */
  @Nonnull
  IJSExpression isUndefined ();

  /**
   * @return "typeof [this] !== 'undefined'" or "[this] !== undefined" from
   *         "[this]"
   */
  @Nonnull
  IJSExpression isNotUndefined ();

  /**
   * @param cValue
   *        constant char to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (char cValue);

  /**
   * @param dValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (double dValue);

  /**
   * @param fValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (float fValue);

  /**
   * @param nValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (int nValue);

  /**
   * @param nValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (long nValue);

  /**
   * @param aValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (@Nonnull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (@Nonnull BigDecimal aValue);

  /**
   * @param sValue
   *        constant String value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (@Nonnull String sValue);

  /**
   * @param aExpr
   *        value to add
   * @return "[this]+[right]"
   */
  @Nonnull
  IJSExpression plus (@Nonnull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (double dValue);

  /**
   * @param fValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (float fValue);

  /**
   * @param nValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (int nValue);

  /**
   * @param nValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (long nValue);

  /**
   * @param aValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (@Nonnull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (@Nonnull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to subtract
   * @return "[this]-[right]"
   */
  @Nonnull
  IJSExpression minus (@Nonnull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (double dValue);

  /**
   * @param fValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (float fValue);

  /**
   * @param nValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (int nValue);

  /**
   * @param nValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (long nValue);

  /**
   * @param aValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (@Nonnull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (@Nonnull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to multiply with
   * @return "[this]*[right]"
   */
  @Nonnull
  IJSExpression mul (@Nonnull IJSExpression aExpr);

  /**
   * @param dValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (double dValue);

  /**
   * @param fValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (float fValue);

  /**
   * @param nValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (int nValue);

  /**
   * @param nValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (long nValue);

  /**
   * @param aValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (@Nonnull BigInteger aValue);

  /**
   * @param aValue
   *        constant value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (@Nonnull BigDecimal aValue);

  /**
   * @param aExpr
   *        value to divide through
   * @return "[this]/[right]"
   */
  @Nonnull
  IJSExpression div (@Nonnull IJSExpression aExpr);

  /**
   * @param nValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @Nonnull
  IJSExpression mod (int nValue);

  /**
   * @param nValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @Nonnull
  IJSExpression mod (long nValue);

  /**
   * @param aValue
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @Nonnull
  IJSExpression mod (@Nonnull BigInteger aValue);

  /**
   * @param aExpr
   *        value to modulo against
   * @return "[this]%[right]"
   */
  @Nonnull
  IJSExpression mod (@Nonnull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @Nonnull
  IJSExpression shl (int nValue);

  /**
   * @param nValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @Nonnull
  IJSExpression shl (long nValue);

  /**
   * @param aValue
   *        constant value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @Nonnull
  IJSExpression shl (@Nonnull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift left
   * @return "[this]&lt;&lt;[right]"
   */
  @Nonnull
  IJSExpression shl (@Nonnull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shr (int nValue);

  /**
   * @param nValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shr (long nValue);

  /**
   * @param aValue
   *        constant value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shr (@Nonnull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift right
   * @return "[this]&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shr (@Nonnull IJSExpression aExpr);

  /**
   * @param nValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shrz (int nValue);

  /**
   * @param nValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shrz (long nValue);

  /**
   * @param aValue
   *        constant value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shrz (@Nonnull BigInteger aValue);

  /**
   * @param aExpr
   *        value to shift right with zero padding
   * @return "[this]&gt;&gt;&gt;[right]"
   */
  @Nonnull
  IJSExpression shrz (@Nonnull IJSExpression aExpr);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param nValue
   *        value
   * @return [this] &amp; value
   */
  @Nonnull
  IJSExpression band (int nValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param nValue
   *        value
   * @return [this] &amp; value
   */
  @Nonnull
  IJSExpression band (long nValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param aValue
   *        value
   * @return [this] &amp; value
   */
  @Nonnull
  IJSExpression band (@Nonnull BigInteger aValue);

  /**
   * Bit-wise AND '&amp;'.
   *
   * @param aExpr
   *        value
   * @return [this] &amp; value
   */
  @Nonnull
  IJSExpression band (@Nonnull IJSExpression aExpr);

  /**
   * Bit-wise OR '|'.
   *
   * @param nValue
   *        value
   * @return [this] | value
   */
  @Nonnull
  IJSExpression bor (int nValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param nValue
   *        value
   * @return [this] | value
   */
  @Nonnull
  IJSExpression bor (long nValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param aValue
   *        value
   * @return [this] | value
   */
  @Nonnull
  IJSExpression bor (@Nonnull BigInteger aValue);

  /**
   * Bit-wise OR '|'.
   *
   * @param aExpr
   *        value
   * @return [this] | value
   */
  @Nonnull
  IJSExpression bor (@Nonnull IJSExpression aExpr);

  /**
   * Logical AND '&amp;&amp;'.
   *
   * @param aExpr
   *        value
   * @return [this] &amp;&amp; value
   */
  @Nonnull
  IJSExpression cand (@Nonnull IJSExpression aExpr);

  /**
   * Logical OR '||'.
   *
   * @param aExpr
   *        value
   * @return [this] || value
   */
  @Nonnull
  IJSExpression cor (@Nonnull IJSExpression aExpr);

  /**
   * XOR '^'.
   *
   * @param nValue
   *        value
   * @return [this] ^ value
   */
  @Nonnull
  IJSExpression xor (int nValue);

  /**
   * XOR '^'.
   *
   * @param nValue
   *        value
   * @return [this] ^ value
   */
  @Nonnull
  IJSExpression xor (long nValue);

  /**
   * XOR '^'.
   *
   * @param aValue
   *        value
   * @return [this] ^ value
   */
  @Nonnull
  IJSExpression xor (@Nonnull BigInteger aValue);

  /**
   * XOR '^'.
   *
   * @param aExpr
   *        value
   * @return [this] ^ value
   */
  @Nonnull
  IJSExpression xor (@Nonnull IJSExpression aExpr);

  /**
   * Lower than '&lt;'.
   *
   * @param dValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (double dValue);

  /**
   * Lower than '&lt;'.
   *
   * @param fValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (float fValue);

  /**
   * Lower than '&lt;'.
   *
   * @param nValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (int nValue);

  /**
   * Lower than '&lt;'.
   *
   * @param nValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (long nValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (@Nonnull BigInteger aValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aValue
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (@Nonnull BigDecimal aValue);

  /**
   * Lower than '&lt;'.
   *
   * @param aExpr
   *        value
   * @return [this] &lt; value
   */
  @Nonnull
  IJSExpression lt (@Nonnull IJSExpression aExpr);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param dValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (double dValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param fValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (float fValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param nValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (int nValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param nValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (long nValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (@Nonnull BigInteger aValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aValue
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (@Nonnull BigDecimal aValue);

  /**
   * Lower than or equal '&lt;='.
   *
   * @param aExpr
   *        value
   * @return [this] &lt;= value
   */
  @Nonnull
  IJSExpression lte (@Nonnull IJSExpression aExpr);

  /**
   * Greater than '&gt;'.
   *
   * @param dValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (double dValue);

  /**
   * Greater than '&gt;'.
   *
   * @param fValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (float fValue);

  /**
   * Greater than '&gt;'.
   *
   * @param nValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (int nValue);

  /**
   * Greater than '&gt;'.
   *
   * @param nValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (long nValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (@Nonnull BigInteger aValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aValue
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (@Nonnull BigDecimal aValue);

  /**
   * Greater than '&gt;'.
   *
   * @param aExpr
   *        value
   * @return [this] &gt; value
   */
  @Nonnull
  IJSExpression gt (@Nonnull IJSExpression aExpr);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param dValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (double dValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param fValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (float fValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param nValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (int nValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param nValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (long nValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (@Nonnull BigInteger aValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aValue
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (@Nonnull BigDecimal aValue);

  /**
   * Greater than or equal '&gt;='.
   *
   * @param aExpr
   *        value
   * @return [this] &gt;= value
   */
  @Nonnull
  IJSExpression gte (@Nonnull IJSExpression aExpr);

  /**
   * Equals '=='.
   *
   * @param bValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (boolean bValue);

  /**
   * Equals '=='.
   *
   * @param cValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (char cValue);

  /**
   * Equals '=='.
   *
   * @param fValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (float fValue);

  /**
   * Equals '=='.
   *
   * @param dValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (double dValue);

  /**
   * Equals '=='.
   *
   * @param nValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (int nValue);

  /**
   * Equals '=='.
   *
   * @param nValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (long nValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (@Nonnull BigInteger aValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (@Nonnull BigDecimal aValue);

  /**
   * Equals '=='.
   *
   * @param sValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (@Nonnull String sValue);

  /**
   * Equals '=='.
   *
   * @param aValue
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (@Nonnull IJson aValue);

  /**
   * Equals '=='.
   *
   * @param aExpr
   *        value
   * @return [this] == value
   */
  @Nonnull
  IJSExpression eq (@Nonnull IJSExpression aExpr);

  /**
   * Exactly equals '==='.
   *
   * @param bValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (boolean bValue);

  /**
   * Exactly equals '==='.
   *
   * @param cValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (char cValue);

  /**
   * Exactly equals '==='.
   *
   * @param fValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (float fValue);

  /**
   * Exactly equals '==='.
   *
   * @param dValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (double dValue);

  /**
   * Exactly equals '==='.
   *
   * @param nValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (int nValue);

  /**
   * Exactly equals '==='.
   *
   * @param nValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (long nValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (@Nonnull BigInteger aValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (@Nonnull BigDecimal aValue);

  /**
   * Exactly equals '==='.
   *
   * @param sValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (@Nonnull String sValue);

  /**
   * Exactly equals '==='.
   *
   * @param aValue
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (@Nonnull IJson aValue);

  /**
   * Exactly equals '==='.
   *
   * @param aExpr
   *        value
   * @return [this] === value
   */
  @Nonnull
  IJSExpression eeq (@Nonnull IJSExpression aExpr);

  /**
   * Not equals '!='.
   *
   * @param bValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (boolean bValue);

  /**
   * Not equals '!='.
   *
   * @param cValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (char cValue);

  /**
   * Not equals '!='.
   *
   * @param fValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (float fValue);

  /**
   * Not equals '!='.
   *
   * @param dValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (double dValue);

  /**
   * Not equals '!='.
   *
   * @param nValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (int nValue);

  /**
   * Not equals '!='.
   *
   * @param nValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (long nValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (@Nonnull BigInteger aValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (@Nonnull BigDecimal aValue);

  /**
   * Not equals '!='.
   *
   * @param sValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (@Nonnull String sValue);

  /**
   * Not equals '!='.
   *
   * @param aValue
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (@Nonnull IJson aValue);

  /**
   * Not equals '!='.
   *
   * @param aExpr
   *        value
   * @return [this] != value
   */
  @Nonnull
  IJSExpression ne (@Nonnull IJSExpression aExpr);

  /**
   * Exactly not equals '!=='.
   *
   * @param bValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (boolean bValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param cValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (char cValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param fValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (float fValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param dValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (double dValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param nValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (int nValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param nValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (long nValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (@Nonnull BigInteger aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (@Nonnull BigDecimal aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param sValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (@Nonnull String sValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aValue
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (@Nonnull IJson aValue);

  /**
   * Exactly not equals '!=='.
   *
   * @param aExpr
   *        value
   * @return [this] !== value
   */
  @Nonnull
  IJSExpression ene (@Nonnull IJSExpression aExpr);

  /**
   * instanceof
   *
   * @param aType
   *        Type to check
   * @return [this] instanceof type
   */
  @Nonnull
  @CodingStyleguideUnaware
  IJSExpression _instanceof (@Nonnull AbstractJSType aType);

  /**
   * @param aMethod
   *        Method to invoke
   * @return "[this].[method]". Arguments shall be added to the returned
   *         {@link JSInvocation} object.
   */
  @Nonnull
  JSInvocation invoke (@Nonnull JSMethod aMethod);

  /**
   * @param sMethod
   *        Method to invoke
   * @return "[this].[method]". Arguments shall be added to the returned
   *         {@link JSInvocation} object.
   */
  @Nonnull
  JSInvocation invoke (@Nonnull String sMethod);

  /**
   * @param aField
   *        Field to reference
   * @return "[this].[field]"
   */
  @Nonnull
  JSFieldRef ref (@Nonnull JSVar aField);

  /**
   * @param sField
   *        Field to reference
   * @return "[this].[field]"
   */
  @Nonnull
  JSFieldRef ref (@Nonnull String sField);

  /**
   * Get the array component at the specified index.
   *
   * @param cIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component (char cIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param nIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component (int nIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param nIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component (long nIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param sIndex
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component (@Nonnull String sIndex);

  /**
   * Get the array component at the specified index.
   *
   * @param aExpr
   *        Index expression
   * @return [this]<b>[</b>value<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component (@Nonnull IJSExpression aExpr);

  /**
   * Get the array component at index 0.
   *
   * @return [this]<b>[</b>0<b>]</b>
   */
  @Nonnull
  JSArrayCompRef component0 ();
}
