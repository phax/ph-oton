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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * For in Statement
 *
 * @author Philip Helger
 */
public class JSForIn extends AbstractJSStatement
{
  private final JSVar m_aLoopVar;
  private final IJSExpression m_aCollection;
  private JSBlock m_aBody;

  public JSForIn (@Nonnull @Nonempty final String sVarName, @Nonnull final IJSExpression aCollection)
  {
    this (new JSVar (sVarName), aCollection);
  }

  public JSForIn (@Nonnull final JSVar aLoopVar, @Nonnull final IJSExpression aCollection)
  {
    m_aLoopVar = ValueEnforcer.notNull (aLoopVar, "LoopVar");
    m_aCollection = ValueEnforcer.notNull (aCollection, "Collection");
  }

  /**
   * @return a reference to the loop variable.
   */
  @Nonnull
  public JSVar var ()
  {
    return m_aLoopVar;
  }

  @Nonnull
  public IJSExpression collection ()
  {
    return m_aCollection;
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
    aFormatter.plain ("for(var ").var (m_aLoopVar).plain (" in ").generatable (m_aCollection).plain (')');
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
    final JSForIn rhs = (JSForIn) o;
    return m_aLoopVar.equals (rhs.m_aLoopVar) && m_aCollection.equals (rhs.m_aCollection) && EqualsHelper.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aLoopVar).append (m_aCollection).append (m_aBody).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("loopVar", m_aLoopVar)
                                       .append ("collection", m_aCollection)
                                       .append ("body", m_aBody)
                                       .getToString ();
  }
}
