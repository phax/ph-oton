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

import com.helger.annotation.Nonempty;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Catch block for a try/catch/finally statement
 *
 * @author Philip Helger
 */
public class JSCatchBlock implements IJSGeneratable
{
  public static final String DEFAULT_VAR_NAME = "e";

  private JSParam m_aVar;
  private final JSBlock m_aBody = new JSBlock ();

  public JSCatchBlock ()
  {}

  public JSCatchBlock (@Nonnull @Nonempty final String sName)
  {
    m_aVar = new JSParam (sName);
  }

  @Nullable
  public JSParam param ()
  {
    return m_aVar;
  }

  @Nonnull
  public JSParam param (@Nonnull @Nonempty final String sName)
  {
    if (m_aVar != null)
      throw new IllegalStateException ("Catch block already has a variable");
    m_aVar = new JSParam (sName);
    return m_aVar;
  }

  @Nonnull
  public JSBlock body ()
  {
    return m_aBody;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aVar == null)
      m_aVar = new JSParam (DEFAULT_VAR_NAME);
    // Only the variable name is used
    aFormatter.plain ("catch (").variable (m_aVar).plain (')').generatable (m_aBody);
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
    final JSCatchBlock rhs = (JSCatchBlock) o;
    return m_aVar.equals (rhs.m_aVar) && m_aBody.equals (rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aVar).append (m_aBody).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Variable", m_aVar).append ("Body", m_aBody).getToString ();
  }
}
