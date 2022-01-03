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
package com.helger.html.jscode.type;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSMarshaller;
import com.helger.html.jscode.AbstractJSType;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSFormatter;
import com.helger.html.jscode.JSPrinter;
import com.helger.html.jscode.JSRef;

/**
 * Contains the JS built-in primitive types
 *
 * @author Philip Helger
 */
@CodingStyleguideUnaware
public abstract class JSPrimitiveType extends AbstractJSType
{
  private final String m_sName;
  private final JSRef m_aGlobal;

  protected JSPrimitiveType (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
    m_aGlobal = JSExpr.ref (sName);
  }

  @Override
  @Nonnull
  @Nonempty
  public final String name ()
  {
    return m_sName;
  }

  @Nonnull
  public final JSRef global ()
  {
    return m_aGlobal;
  }

  public final void generate (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sName);
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
    if (!super.equals (o))
      return false;
    final JSPrimitiveType rhs = (JSPrimitiveType) o;
    return m_sName.equals (rhs.m_sName);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sName).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("name", m_sName).getToString ();
  }
}
