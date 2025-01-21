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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.helger.html.jscode.type.JSPrimitiveTypes;

/**
 * Test class for class {@link JSOp}.
 *
 * @author Philip Helger
 */
public final class JSOpTest
{

  private void _test (final String sJS, final IJSExpression aExpr)
  {
    assertEquals (sJS, aExpr.getJSCode ());
  }

  @Test
  public void testBasic ()
  {
    final JSAtomInt n5 = JSExpr.lit (5);
    final JSAtomBigDecimal d5 = JSExpr.lit (BigDecimal.valueOf (5));
    final JSRef a = JSExpr.ref ("a");
    final JSRef b = JSExpr.ref ("b");
    _test ("-5", JSOp.minus (n5));
    _test ("-5", JSOp.minus (d5));
    _test ("(5)", JSOp.inParantheses (n5));
    _test ("(5)", JSOp.inParantheses (d5));
    _test ("false", JSOp.not (JSExpr.TRUE));
    _test ("true", JSOp.not (JSExpr.FALSE));
    _test ("(!a)", JSOp.not (a));
    _test ("(~5)", JSOp.complement (n5));
    _test ("(~5)", JSOp.complement (d5));
    _test ("a++", JSOp.incrPostfix (a));
    _test ("6", JSOp.incrPostfix (n5));
    _test ("6", JSOp.incrPostfix (d5));
    _test ("a--", JSOp.decrPostfix (a));
    _test ("4", JSOp.decrPostfix (n5));
    _test ("4", JSOp.decrPostfix (d5));
    _test ("typeof a", JSOp.typeof (a));
    _test ("(a+b)", JSOp.plus (a, b));
    _test ("10", JSOp.plus (n5, n5));
    _test ("10", JSOp.plus (d5, d5));
    _test ("(a-b)", JSOp.minus (a, b));
    _test ("0", JSOp.minus (n5, n5));
    _test ("0", JSOp.minus (d5, d5));
    _test ("(a*b)", JSOp.mul (a, b));
    _test ("25", JSOp.mul (n5, n5));
    _test ("25", JSOp.mul (d5, d5));
    _test ("(a/b)", JSOp.div (a, b));
    _test ("1.0", JSOp.div (n5, n5));
    _test ("1", JSOp.div (d5, d5));
    _test ("(a%b)", JSOp.mod (a, b));
    _test ("0", JSOp.mod (n5, n5));
    _test ("0", JSOp.mod (d5, d5));
    _test ("(a<<b)", JSOp.shl (a, b));
    _test ("(a>>b)", JSOp.shr (a, b));
    _test ("(a>>>b)", JSOp.shrz (a, b));
    _test ("(a&b)", JSOp.band (a, b));
    _test ("(a|b)", JSOp.bor (a, b));
    _test ("(a&&b)", JSOp.cand (a, b));
    _test ("a", JSOp.cand (a, JSExpr.TRUE));
    _test ("false", JSOp.cand (a, JSExpr.FALSE));
    _test ("b", JSOp.cand (JSExpr.TRUE, b));
    _test ("false", JSOp.cand (JSExpr.FALSE, b));
    _test ("(a||b)", JSOp.cor (a, b));
    _test ("true", JSOp.cor (a, JSExpr.TRUE));
    _test ("a", JSOp.cor (a, JSExpr.FALSE));
    _test ("true", JSOp.cor (JSExpr.TRUE, b));
    _test ("b", JSOp.cor (JSExpr.FALSE, b));
    _test ("(a^b)", JSOp.xor (a, b));
    _test ("(a<b)", JSOp.lt (a, b));
    _test ("(a<=b)", JSOp.lte (a, b));
    _test ("(a>b)", JSOp.gt (a, b));
    _test ("(a>=b)", JSOp.gte (a, b));
    _test ("(a==b)", JSOp.eq (a, b));
    _test ("(a===b)", JSOp.eeq (a, b));
    _test ("(a!=b)", JSOp.ne (a, b));
    _test ("(a!==b)", JSOp.ene (a, b));
    _test ("(a instanceof Number)", JSOp._instanceof (a, JSPrimitiveTypes.NUMBER));
    _test ("(a?b:5)", JSOp.cond (a, b, n5));
    _test ("(a?b:5)", JSOp.cond (a, b, d5));

    assertFalse (JSOp.hasOperator (null));
    assertFalse (JSOp.hasOperator (n5));
    assertFalse (JSOp.hasOperator (d5));
    assertTrue (JSOp.hasOperator (JSOp.minus (a)));
    assertTrue (JSOp.hasOperator (JSOp.not (a)));
    assertTrue (JSOp.hasOperator (JSOp.xor (a, b)));
    assertTrue (JSOp.hasOperator (JSOp.cond (a, b, n5)));
  }
}
