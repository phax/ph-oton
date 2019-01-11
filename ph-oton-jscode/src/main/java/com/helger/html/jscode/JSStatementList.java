/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * A list of JS statements that is itself a statement
 *
 * @author Philip Helger
 */
public final class JSStatementList extends AbstractJSStatement
{
  private final ICommonsList <IJSStatement> m_aStatements = new CommonsArrayList<> ();

  public JSStatementList ()
  {}

  public JSStatementList (@Nullable final IJSStatement... aStatements)
  {
    if (aStatements != null)
      for (final IJSStatement aStatement : aStatements)
        add (aStatement);
  }

  public JSStatementList (@Nullable final Iterable <IJSStatement> aStatements)
  {
    if (aStatements != null)
      for (final IJSStatement aStatement : aStatements)
        add (aStatement);
  }

  @Nonnull
  public JSStatementList add (@Nonnull final IJSStatement aStatement)
  {
    ValueEnforcer.notNull (aStatement, "Statement");
    m_aStatements.add (aStatement);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IJSStatement> getAllStatements ()
  {
    return m_aStatements.getClone ();
  }

  @Nonnegative
  public int size ()
  {
    return m_aStatements.size ();
  }

  public boolean isEmpty ()
  {
    return m_aStatements.isEmpty ();
  }

  @Override
  public void state (@Nonnull final JSFormatter aFormatter)
  {
    for (final IJSStatement aStatement : m_aStatements)
      aFormatter.stmt (aStatement);
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
    final JSStatementList rhs = (JSStatementList) o;
    return m_aStatements.equals (rhs.m_aStatements);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStatements).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("statements", m_aStatements).getToString ();
  }
}
