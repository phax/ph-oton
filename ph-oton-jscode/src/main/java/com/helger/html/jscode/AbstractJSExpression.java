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
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;
import com.helger.json.IJson;

/**
 * Provides default implementations for {@link IJSExpression}.
 *
 * @author Philip Helger
 */
public abstract class AbstractJSExpression implements IJSExpression
{
  @Nonnull
  public final AbstractJSExpression minus ()
  {
    return JSOp.minus (this);
  }

  @Nonnull
  public final AbstractJSExpression inParantheses ()
  {
    return JSOp.inParantheses (this);
  }

  @Nonnull
  public final AbstractJSExpression not ()
  {
    return JSOp.not (this);
  }

  @Nonnull
  public final AbstractJSExpression complement ()
  {
    return JSOp.complement (this);
  }

  @Nonnull
  public final AbstractJSExpression incrPostfix ()
  {
    return JSOp.incrPostfix (this);
  }

  @Nonnull
  public final AbstractJSExpression incrPrefix ()
  {
    return JSOp.incrPrefix (this);
  }

  @Nonnull
  public final AbstractJSExpression decrPostfix ()
  {
    return JSOp.decrPostfix (this);
  }

  @Nonnull
  public final AbstractJSExpression decrPrefix ()
  {
    return JSOp.decrPrefix (this);
  }

  @Nonnull
  public final AbstractJSExpression plus (final char cValue)
  {
    return plus (JSExpr.lit (cValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (final double dValue)
  {
    return plus (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (final float fValue)
  {
    return plus (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (final int nValue)
  {
    return plus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (final long nValue)
  {
    return plus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (@Nonnull final BigInteger aValue)
  {
    return plus (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (@Nonnull final BigDecimal aValue)
  {
    return plus (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (@Nonnull final String sValue)
  {
    return plus (JSExpr.lit (sValue));
  }

  @Nonnull
  public final AbstractJSExpression plus (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.plus (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression minus (final double dValue)
  {
    return minus (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (final float fValue)
  {
    return minus (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (final int nValue)
  {
    return minus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (final long nValue)
  {
    return minus (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (@Nonnull final BigInteger aValue)
  {
    return minus (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (@Nonnull final BigDecimal aValue)
  {
    return minus (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression minus (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.minus (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression mul (final double dValue)
  {
    return mul (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (final float fValue)
  {
    return mul (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (final int nValue)
  {
    return mul (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (final long nValue)
  {
    return mul (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (@Nonnull final BigInteger aValue)
  {
    return mul (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (@Nonnull final BigDecimal aValue)
  {
    return mul (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression mul (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.mul (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression div (final double dValue)
  {
    return div (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression div (final float fValue)
  {
    return div (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression div (final int nValue)
  {
    return div (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression div (final long nValue)
  {
    return div (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression div (@Nonnull final BigInteger aValue)
  {
    return div (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression div (@Nonnull final BigDecimal aValue)
  {
    return div (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression div (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.div (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression mod (final int nValue)
  {
    return mod (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression mod (final long nValue)
  {
    return mod (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression mod (@Nonnull final BigInteger aValue)
  {
    return mod (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression mod (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.mod (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression shl (final int nValue)
  {
    return shl (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shl (final long nValue)
  {
    return shl (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shl (@Nonnull final BigInteger aValue)
  {
    return shl (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression shl (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.shl (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression shr (final int nValue)
  {
    return shr (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shr (final long nValue)
  {
    return shr (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shr (@Nonnull final BigInteger aValue)
  {
    return shr (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression shr (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.shr (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression shrz (final int nValue)
  {
    return shrz (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shrz (final long nValue)
  {
    return shrz (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression shrz (@Nonnull final BigInteger aValue)
  {
    return shrz (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression shrz (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.shrz (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression band (final int nValue)
  {
    return band (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression band (final long nValue)
  {
    return band (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression band (@Nonnull final BigInteger aValue)
  {
    return band (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression band (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.band (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression bor (final int nValue)
  {
    return bor (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression bor (final long nValue)
  {
    return bor (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression bor (@Nonnull final BigInteger aValue)
  {
    return bor (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression bor (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.bor (this, aExpr);
  }

  @Nonnull
  public final IJSExpression cand (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.cand (this, aExpr);
  }

  @Nonnull
  public final IJSExpression cor (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.cor (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression xor (final int nValue)
  {
    return xor (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression xor (final long nValue)
  {
    return xor (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression xor (@Nonnull final BigInteger aValue)
  {
    return xor (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression xor (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.xor (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression lt (final double dValue)
  {
    return lt (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (final float fValue)
  {
    return lt (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (final int nValue)
  {
    return lt (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (final long nValue)
  {
    return lt (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (@Nonnull final BigInteger aValue)
  {
    return lt (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (@Nonnull final BigDecimal aValue)
  {
    return lt (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression lt (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.lt (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression lte (final double dValue)
  {
    return lte (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (final float fValue)
  {
    return lte (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (final int nValue)
  {
    return lte (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (final long nValue)
  {
    return lte (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (@Nonnull final BigInteger aValue)
  {
    return lte (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (@Nonnull final BigDecimal aValue)
  {
    return lte (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression lte (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.lte (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression gt (final double dValue)
  {
    return gt (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (final float fValue)
  {
    return gt (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (final int nValue)
  {
    return gt (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (final long nValue)
  {
    return gt (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (@Nonnull final BigInteger aValue)
  {
    return gt (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (@Nonnull final BigDecimal aValue)
  {
    return gt (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression gt (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.gt (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression gte (final double dValue)
  {
    return gte (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (final float fValue)
  {
    return gte (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (final int nValue)
  {
    return gte (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (final long nValue)
  {
    return gte (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (@Nonnull final BigInteger aValue)
  {
    return gte (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (@Nonnull final BigDecimal aValue)
  {
    return gte (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression gte (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.gte (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression eq (final boolean bValue)
  {
    return eq (JSExpr.lit (bValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (final char cValue)
  {
    return eq (JSExpr.lit (cValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (final float fValue)
  {
    return eq (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (final double dValue)
  {
    return eq (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (final int nValue)
  {
    return eq (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (final long nValue)
  {
    return eq (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (@Nonnull final BigInteger aValue)
  {
    return eq (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (@Nonnull final BigDecimal aValue)
  {
    return eq (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (@Nonnull final String sValue)
  {
    return eq (JSExpr.lit (sValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (@Nonnull final IJson aValue)
  {
    return eq (JSExpr.json (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eq (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.eq (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression eeq (final boolean bValue)
  {
    return eeq (JSExpr.lit (bValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (final char cValue)
  {
    return eeq (JSExpr.lit (cValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (final float fValue)
  {
    return eeq (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (final double dValue)
  {
    return eeq (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (final int nValue)
  {
    return eeq (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (final long nValue)
  {
    return eeq (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (@Nonnull final BigInteger aValue)
  {
    return eeq (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (@Nonnull final BigDecimal aValue)
  {
    return eeq (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (@Nonnull final String sValue)
  {
    return eeq (JSExpr.lit (sValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (@Nonnull final IJson aValue)
  {
    return eeq (JSExpr.json (aValue));
  }

  @Nonnull
  public final AbstractJSExpression eeq (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.eeq (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression ne (final boolean bValue)
  {
    return ne (JSExpr.lit (bValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (final char cValue)
  {
    return ne (JSExpr.lit (cValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (final float fValue)
  {
    return ne (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (final double dValue)
  {
    return ne (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (final int nValue)
  {
    return ne (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (final long nValue)
  {
    return ne (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (@Nonnull final BigInteger aValue)
  {
    return ne (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (@Nonnull final BigDecimal aValue)
  {
    return ne (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (@Nonnull final String sValue)
  {
    return ne (JSExpr.lit (sValue));

  }

  @Nonnull
  public final AbstractJSExpression ne (@Nonnull final IJson aValue)
  {
    return ne (JSExpr.json (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ne (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.ne (this, aExpr);
  }

  @Nonnull
  public final AbstractJSExpression ene (final boolean bValue)
  {
    return ene (JSExpr.lit (bValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (final char cValue)
  {
    return ene (JSExpr.lit (cValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (final double dValue)
  {
    return ene (JSExpr.lit (dValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (final float fValue)
  {
    return ene (JSExpr.lit (fValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (final int nValue)
  {
    return ene (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (final long nValue)
  {
    return ene (JSExpr.lit (nValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (@Nonnull final BigInteger aValue)
  {
    return ene (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (@Nonnull final BigDecimal aValue)
  {
    return ene (JSExpr.lit (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (@Nonnull final String sValue)
  {
    return ene (JSExpr.lit (sValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (@Nonnull final IJson aValue)
  {
    return ene (JSExpr.json (aValue));
  }

  @Nonnull
  public final AbstractJSExpression ene (@Nonnull final IJSExpression aExpr)
  {
    return JSOp.ene (this, aExpr);
  }

  @Nonnull
  @CodingStyleguideUnaware
  public final AbstractJSExpression _instanceof (@Nonnull final AbstractJSType aType)
  {
    return JSOp._instanceof (this, aType);
  }

  @Nonnull
  public final AbstractJSExpression typeof ()
  {
    return JSOp.typeof (this);
  }

  @Nonnull
  public final AbstractJSExpression isTypeof (@Nonnull final AbstractJSType aType)
  {
    return typeof ().eeq (aType.typeName ());
  }

  @Nonnull
  public final AbstractJSExpression isNotTypeof (@Nonnull final AbstractJSType aType)
  {
    return typeof ().ene (aType.typeName ());
  }

  @Nonnull
  public final AbstractJSExpression isUndefined ()
  {
    if (this instanceof JSArrayCompRef)
      return eeq (JSExpr.UNDEFINED);

    // typeof requires a String
    return typeof ().eeq (JSExpr.UNDEFINED_STR);
  }

  @Nonnull
  public final AbstractJSExpression isNotUndefined ()
  {
    if (this instanceof JSArrayCompRef)
      return ene (JSExpr.UNDEFINED);

    // typeof requires a String
    return typeof ().ene (JSExpr.UNDEFINED_STR);
  }

  @Nonnull
  public final JSInvocation invoke (@Nonnull final JSMethod aMethod)
  {
    return JSExpr.invoke (this, aMethod);
  }

  @Nonnull
  public final JSInvocation invoke (@Nonnull @Nonempty final String sMethod)
  {
    return JSExpr.invoke (this, sMethod);
  }

  @Nonnull
  public final JSFieldRef ref (@Nonnull final JSVar aField)
  {
    return JSExpr.ref (this, aField);
  }

  @Nonnull
  public final JSFieldRef ref (@Nonnull final String sField)
  {
    return JSExpr.ref (this, sField);
  }

  @Nonnull
  public final JSArrayCompRef component (final char cIndex)
  {
    return component (JSExpr.lit (cIndex));
  }

  @Nonnull
  public final JSArrayCompRef component (final int nIndex)
  {
    return component (JSExpr.lit (nIndex));
  }

  @Nonnull
  public final JSArrayCompRef component (final long nIndex)
  {
    return component (JSExpr.lit (nIndex));
  }

  @Nonnull
  public final AbstractJSExpression component (@Nonnull final BigInteger aValue)
  {
    return component (JSExpr.lit (aValue));
  }

  @Nonnull
  public final JSArrayCompRef component (@Nonnull final String sIndex)
  {
    return component (JSExpr.lit (sIndex));
  }

  @Nonnull
  public final JSArrayCompRef component (@Nonnull final IJSExpression aExpr)
  {
    return JSExpr.component (this, aExpr);
  }

  @Nonnull
  public final JSArrayCompRef component0 ()
  {
    return component (JSExpr.lit (0));
  }

  @Nonnull
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
