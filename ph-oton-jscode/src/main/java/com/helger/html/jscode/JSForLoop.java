/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
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

  @Nonnull
  public JSForLoop simpleLoop (@Nonnull @Nonempty final String sVarName, final int nStartIncl, final int nEndExcl)
  {
    final JSVar aLoopVar = init (sVarName, nStartIncl);
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

  @Nonnull
  public JSVar init (@Nonnull @Nonempty final String sVarName, final int nValue)
  {
    return init (sVarName, JSExpr.lit (nValue));
  }

  @Nonnull
  public JSVar init (@Nonnull @Nonempty final String sVarName, final long nValue)
  {
    return init (sVarName, JSExpr.lit (nValue));
  }

  @Nonnull
  public JSVar init (@Nonnull @Nonempty final String sVarName, @Nonnull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "InitExpression");

    final JSVar aVar = new JSVar (sVarName, aExpr);
    m_aInits.add (aVar);
    return aVar;
  }

  public void init (@Nonnull final JSVar aVar, @Nonnull final IJSExpression aExpr)
  {
    m_aInits.add (aVar.assign (aExpr));
  }

  public void test (@Nonnull final IJSExpression aTest)
  {
    m_aTest = ValueEnforcer.notNull (aTest, "Test");
  }

  public void update (@Nonnull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "Expr");

    m_aUpdates.add (aExpr);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IJSExpression> updates ()
  {
    return m_aUpdates.getClone ();
  }

  @Nonnull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ();
    return m_aBody;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain ("for(");
    boolean bFirst = true;
    for (final IJSExpression aInit : m_aInits)
    {
      if (bFirst)
        bFirst = false;
      else
        aFormatter.plain (',');
      if (aInit instanceof JSVar)
        aFormatter.plain ("var ").variable ((JSVar) aInit);
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
