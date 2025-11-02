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
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.CodingStyleguideUnaware;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;
import com.helger.json.IJson;

/**
 * Provides default implementations for {@link IJSExpression}.
 *
 * @author Philip Helger
 */
public abstract class AbstractJSExpression implements IJSExpression
{
  @NonNull
  public final AbstractJSExpression minus ()
  {
    return JSOp.minus (this);
  }

  @NonNull
  public final AbstractJSExpression inParantheses ()
  {
    return JSOp.inParantheses (this);
  }

  @NonNull
  public final AbstractJSExpression not ()
  {
    return JSOp.not (this);
  }

  @NonNull
  public final AbstractJSExpression complement ()
  {
    return JSOp.complement (this);
  }

  @NonNull
  public final AbstractJSExpression incrPostfix ()
  {
    return JSOp.incrPostfix (this);
  }

  @NonNull
  public final AbstractJSExpression incrPrefix ()
  {
    return JSOp.incrPrefix (this);
  }

  @NonNull
  public final AbstractJSExpression decrPostfix ()
  {
    return JSOp.decrPostfix (this);
  }

  @NonNull
  public final AbstractJSExpression decrPrefix ()
  {
    return JSOp.decrPrefix (this);
  }

  @NonNull
  public final AbstractJSExpression plus (final char cValue)
  {
    return plus (JSExpr.lit (cValue));
  }

  @NonNull
  public final AbstractJSExpression plus (final double dValue)
  {
    return plus (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression plus (final float fValue)
  {
    return plus (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression plus (final int nValue)
  {
    return plus (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression plus (final long nValue)
  {
    return plus (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression plus (@NonNull final BigInteger aValue)
  {
    return plus (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression plus (@NonNull final BigDecimal aValue)
  {
    return plus (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression plus (@NonNull final String sValue)
  {
    return plus (JSExpr.lit (sValue));
  }

  @NonNull
  public final AbstractJSExpression plus (@NonNull final IJSExpression aExpr)
  {
    return JSOp.plus (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression minus (final double dValue)
  {
    return minus (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression minus (final float fValue)
  {
    return minus (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression minus (final int nValue)
  {
    return minus (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression minus (final long nValue)
  {
    return minus (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression minus (@NonNull final BigInteger aValue)
  {
    return minus (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression minus (@NonNull final BigDecimal aValue)
  {
    return minus (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression minus (@NonNull final IJSExpression aExpr)
  {
    return JSOp.minus (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression mul (final double dValue)
  {
    return mul (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression mul (final float fValue)
  {
    return mul (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression mul (final int nValue)
  {
    return mul (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression mul (final long nValue)
  {
    return mul (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression mul (@NonNull final BigInteger aValue)
  {
    return mul (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression mul (@NonNull final BigDecimal aValue)
  {
    return mul (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression mul (@NonNull final IJSExpression aExpr)
  {
    return JSOp.mul (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression div (final double dValue)
  {
    return div (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression div (final float fValue)
  {
    return div (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression div (final int nValue)
  {
    return div (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression div (final long nValue)
  {
    return div (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression div (@NonNull final BigInteger aValue)
  {
    return div (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression div (@NonNull final BigDecimal aValue)
  {
    return div (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression div (@NonNull final IJSExpression aExpr)
  {
    return JSOp.div (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression mod (final int nValue)
  {
    return mod (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression mod (final long nValue)
  {
    return mod (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression mod (@NonNull final BigInteger aValue)
  {
    return mod (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression mod (@NonNull final IJSExpression aExpr)
  {
    return JSOp.mod (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression shl (final int nValue)
  {
    return shl (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shl (final long nValue)
  {
    return shl (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shl (@NonNull final BigInteger aValue)
  {
    return shl (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression shl (@NonNull final IJSExpression aExpr)
  {
    return JSOp.shl (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression shr (final int nValue)
  {
    return shr (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shr (final long nValue)
  {
    return shr (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shr (@NonNull final BigInteger aValue)
  {
    return shr (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression shr (@NonNull final IJSExpression aExpr)
  {
    return JSOp.shr (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression shrz (final int nValue)
  {
    return shrz (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shrz (final long nValue)
  {
    return shrz (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression shrz (@NonNull final BigInteger aValue)
  {
    return shrz (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression shrz (@NonNull final IJSExpression aExpr)
  {
    return JSOp.shrz (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression band (final int nValue)
  {
    return band (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression band (final long nValue)
  {
    return band (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression band (@NonNull final BigInteger aValue)
  {
    return band (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression band (@NonNull final IJSExpression aExpr)
  {
    return JSOp.band (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression bor (final int nValue)
  {
    return bor (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression bor (final long nValue)
  {
    return bor (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression bor (@NonNull final BigInteger aValue)
  {
    return bor (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression bor (@NonNull final IJSExpression aExpr)
  {
    return JSOp.bor (this, aExpr);
  }

  @NonNull
  public final IJSExpression cand (@NonNull final IJSExpression aExpr)
  {
    return JSOp.cand (this, aExpr);
  }

  @NonNull
  public final IJSExpression cor (@NonNull final IJSExpression aExpr)
  {
    return JSOp.cor (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression xor (final int nValue)
  {
    return xor (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression xor (final long nValue)
  {
    return xor (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression xor (@NonNull final BigInteger aValue)
  {
    return xor (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression xor (@NonNull final IJSExpression aExpr)
  {
    return JSOp.xor (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression lt (final double dValue)
  {
    return lt (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression lt (final float fValue)
  {
    return lt (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression lt (final int nValue)
  {
    return lt (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression lt (final long nValue)
  {
    return lt (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression lt (@NonNull final BigInteger aValue)
  {
    return lt (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression lt (@NonNull final BigDecimal aValue)
  {
    return lt (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression lt (@NonNull final IJSExpression aExpr)
  {
    return JSOp.lt (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression lte (final double dValue)
  {
    return lte (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression lte (final float fValue)
  {
    return lte (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression lte (final int nValue)
  {
    return lte (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression lte (final long nValue)
  {
    return lte (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression lte (@NonNull final BigInteger aValue)
  {
    return lte (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression lte (@NonNull final BigDecimal aValue)
  {
    return lte (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression lte (@NonNull final IJSExpression aExpr)
  {
    return JSOp.lte (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression gt (final double dValue)
  {
    return gt (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression gt (final float fValue)
  {
    return gt (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression gt (final int nValue)
  {
    return gt (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression gt (final long nValue)
  {
    return gt (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression gt (@NonNull final BigInteger aValue)
  {
    return gt (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression gt (@NonNull final BigDecimal aValue)
  {
    return gt (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression gt (@NonNull final IJSExpression aExpr)
  {
    return JSOp.gt (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression gte (final double dValue)
  {
    return gte (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression gte (final float fValue)
  {
    return gte (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression gte (final int nValue)
  {
    return gte (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression gte (final long nValue)
  {
    return gte (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression gte (@NonNull final BigInteger aValue)
  {
    return gte (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression gte (@NonNull final BigDecimal aValue)
  {
    return gte (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression gte (@NonNull final IJSExpression aExpr)
  {
    return JSOp.gte (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression eq (final boolean bValue)
  {
    return eq (JSExpr.lit (bValue));
  }

  @NonNull
  public final AbstractJSExpression eq (final char cValue)
  {
    return eq (JSExpr.lit (cValue));
  }

  @NonNull
  public final AbstractJSExpression eq (final float fValue)
  {
    return eq (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression eq (final double dValue)
  {
    return eq (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression eq (final int nValue)
  {
    return eq (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression eq (final long nValue)
  {
    return eq (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression eq (@NonNull final BigInteger aValue)
  {
    return eq (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression eq (@NonNull final BigDecimal aValue)
  {
    return eq (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression eq (@NonNull final String sValue)
  {
    return eq (JSExpr.lit (sValue));
  }

  @NonNull
  public final AbstractJSExpression eq (@NonNull final IJson aValue)
  {
    return eq (JSExpr.json (aValue));
  }

  @NonNull
  public final AbstractJSExpression eq (@NonNull final IJSExpression aExpr)
  {
    return JSOp.eq (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression eeq (final boolean bValue)
  {
    return eeq (JSExpr.lit (bValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (final char cValue)
  {
    return eeq (JSExpr.lit (cValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (final float fValue)
  {
    return eeq (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (final double dValue)
  {
    return eeq (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (final int nValue)
  {
    return eeq (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (final long nValue)
  {
    return eeq (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (@NonNull final BigInteger aValue)
  {
    return eeq (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (@NonNull final BigDecimal aValue)
  {
    return eeq (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (@NonNull final String sValue)
  {
    return eeq (JSExpr.lit (sValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (@NonNull final IJson aValue)
  {
    return eeq (JSExpr.json (aValue));
  }

  @NonNull
  public final AbstractJSExpression eeq (@NonNull final IJSExpression aExpr)
  {
    return JSOp.eeq (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression ne (final boolean bValue)
  {
    return ne (JSExpr.lit (bValue));
  }

  @NonNull
  public final AbstractJSExpression ne (final char cValue)
  {
    return ne (JSExpr.lit (cValue));
  }

  @NonNull
  public final AbstractJSExpression ne (final float fValue)
  {
    return ne (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression ne (final double dValue)
  {
    return ne (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression ne (final int nValue)
  {
    return ne (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression ne (final long nValue)
  {
    return ne (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression ne (@NonNull final BigInteger aValue)
  {
    return ne (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression ne (@NonNull final BigDecimal aValue)
  {
    return ne (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression ne (@NonNull final String sValue)
  {
    return ne (JSExpr.lit (sValue));
  }

  @NonNull
  public final AbstractJSExpression ne (@NonNull final IJson aValue)
  {
    return ne (JSExpr.json (aValue));
  }

  @NonNull
  public final AbstractJSExpression ne (@NonNull final IJSExpression aExpr)
  {
    return JSOp.ne (this, aExpr);
  }

  @NonNull
  public final AbstractJSExpression ene (final boolean bValue)
  {
    return ene (JSExpr.lit (bValue));
  }

  @NonNull
  public final AbstractJSExpression ene (final char cValue)
  {
    return ene (JSExpr.lit (cValue));
  }

  @NonNull
  public final AbstractJSExpression ene (final double dValue)
  {
    return ene (JSExpr.lit (dValue));
  }

  @NonNull
  public final AbstractJSExpression ene (final float fValue)
  {
    return ene (JSExpr.lit (fValue));
  }

  @NonNull
  public final AbstractJSExpression ene (final int nValue)
  {
    return ene (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression ene (final long nValue)
  {
    return ene (JSExpr.lit (nValue));
  }

  @NonNull
  public final AbstractJSExpression ene (@NonNull final BigInteger aValue)
  {
    return ene (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression ene (@NonNull final BigDecimal aValue)
  {
    return ene (JSExpr.lit (aValue));
  }

  @NonNull
  public final AbstractJSExpression ene (@NonNull final String sValue)
  {
    return ene (JSExpr.lit (sValue));
  }

  @NonNull
  public final AbstractJSExpression ene (@NonNull final IJson aValue)
  {
    return ene (JSExpr.json (aValue));
  }

  @NonNull
  public final AbstractJSExpression ene (@NonNull final IJSExpression aExpr)
  {
    return JSOp.ene (this, aExpr);
  }

  @NonNull
  @CodingStyleguideUnaware
  public final AbstractJSExpression _instanceof (@NonNull final AbstractJSType aType)
  {
    return JSOp._instanceof (this, aType);
  }

  @NonNull
  public final AbstractJSExpression typeof ()
  {
    return JSOp.typeof (this);
  }

  @NonNull
  public final AbstractJSExpression isTypeof (@NonNull final AbstractJSType aType)
  {
    return typeof ().eeq (aType.typeName ());
  }

  @NonNull
  public final AbstractJSExpression isNotTypeof (@NonNull final AbstractJSType aType)
  {
    return typeof ().ene (aType.typeName ());
  }

  @NonNull
  public final AbstractJSExpression isUndefined ()
  {
    if (this instanceof JSArrayCompRef)
      return eeq (JSExpr.UNDEFINED);

    // typeof requires a String
    return typeof ().eeq (JSExpr.UNDEFINED_STR);
  }

  @NonNull
  public final AbstractJSExpression isNotUndefined ()
  {
    if (this instanceof JSArrayCompRef)
      return ene (JSExpr.UNDEFINED);

    // typeof requires a String
    return typeof ().ene (JSExpr.UNDEFINED_STR);
  }

  @NonNull
  public final JSInvocation invoke (@NonNull final JSMethod aMethod)
  {
    return JSExpr.invoke (this, aMethod);
  }

  @NonNull
  public final JSInvocation invoke (@NonNull @Nonempty final String sMethod)
  {
    return JSExpr.invoke (this, sMethod);
  }

  @NonNull
  public final JSFieldRef ref (@NonNull final AbstractJSVariable <?> aField)
  {
    return JSExpr.ref (this, aField);
  }

  @NonNull
  public final JSFieldRef ref (@NonNull final String sField)
  {
    return JSExpr.ref (this, sField);
  }

  @NonNull
  public final JSArrayCompRef component (final char cIndex)
  {
    return component (JSExpr.lit (cIndex));
  }

  @NonNull
  public final JSArrayCompRef component (final int nIndex)
  {
    return component (JSExpr.lit (nIndex));
  }

  @NonNull
  public final JSArrayCompRef component (final long nIndex)
  {
    return component (JSExpr.lit (nIndex));
  }

  @NonNull
  public final AbstractJSExpression component (@NonNull final BigInteger aValue)
  {
    return component (JSExpr.lit (aValue));
  }

  @NonNull
  public final JSArrayCompRef component (@NonNull final String sIndex)
  {
    return component (JSExpr.lit (sIndex));
  }

  @NonNull
  public final JSArrayCompRef component (@NonNull final IJSExpression aExpr)
  {
    return JSExpr.component (this, aExpr);
  }

  @NonNull
  public final JSArrayCompRef component0 ()
  {
    return component (JSExpr.lit (0));
  }

  @NonNull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    return true;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
