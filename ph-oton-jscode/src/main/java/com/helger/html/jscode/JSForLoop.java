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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.js.IJSWriterSettings;

/**
 * For statement
 *
 * @author Philip Helger
 */
public class JSForLoop extends AbstractJSStatement
{
  private final ICommonsList <IJSExpression> m_aInits = new CommonsArrayList <> ();
  private IJSExpression m_aTest;
  private final ICommonsList <IJSExpression> m_aUpdates = new CommonsArrayList <> ();
  private JSBlock m_aBody;

  public JSForLoop ()
  {}

  @NonNull
  public JSForLoop simpleLoop (@NonNull @Nonempty final String sVarName, final int nStartIncl, final int nEndExcl)
  {
    final JSLet aLoopVar = init (sVarName, nStartIncl);
    if (nEndExcl >= nStartIncl)
    {
      test (aLoopVar.lt (nEndExcl));
      update (aLoopVar.incrPostfix ());
    }
    else
    {
      test (aLoopVar.gt (nEndExcl));
      update (aLoopVar.decrPostfix ());
    }
    return this;
  }

  @NonNull
  public JSLet init (@NonNull @Nonempty final String sVarName, final int nValue)
  {
    return init (sVarName, JSExpr.lit (nValue));
  }

  @NonNull
  public JSLet init (@NonNull @Nonempty final String sVarName, final long nValue)
  {
    return init (sVarName, JSExpr.lit (nValue));
  }

  @NonNull
  public JSLet init (@NonNull @Nonempty final String sVarName, @NonNull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "InitExpression");

    final JSLet aVar = new JSLet (sVarName, aExpr);
    m_aInits.add (aVar);
    return aVar;
  }

  public void init (@NonNull final JSLet aVar, @NonNull final IJSExpression aExpr)
  {
    m_aInits.add (aVar.assign (aExpr));
  }

  public void test (@NonNull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  public void update (@NonNull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "Expr");

    m_aUpdates.add (aExpr);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IJSExpression> updates ()
  {
    return m_aUpdates.getClone ();
  }

  @NonNull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ();
    return m_aBody;
  }

  public void state (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain ("for(");
    boolean bFirst = true;
    for (final IJSExpression aInit : m_aInits)
    {
      if (bFirst)
        bFirst = false;
      else
        aFormatter.plain (',');
      if (aInit instanceof AbstractJSVariable <?>)
      {
        final AbstractJSVariable <?> aVar = (AbstractJSVariable <?>) aInit;
        aFormatter.plain (aVar.getJSVarMode ().getCode ()).variable (aVar);
      }
      else
        aFormatter.generatable (aInit);
    }
    aFormatter.plain (';').generatable (m_aTest).plain (';').generatable (m_aUpdates).plain (')');
    if (m_aBody != null)
      aFormatter.generatable (m_aBody).nl ();
    else
      aFormatter.plain (';').nl ();
  }

  @Nullable
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
    final JSForLoop rhs = (JSForLoop) o;
    return m_aInits.equals (rhs.m_aInits) &&
           EqualsHelper.equals (m_aTest, rhs.m_aTest) &&
           m_aUpdates.equals (rhs.m_aUpdates) &&
           EqualsHelper.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aInits)
                                       .append (m_aTest)
                                       .append (m_aUpdates)
                                       .append (m_aBody)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("inits", m_aInits)
                                       .append ("test", m_aTest)
                                       .append ("updates", m_aUpdates)
                                       .append ("body", m_aBody)
                                       .getToString ();
  }
}
