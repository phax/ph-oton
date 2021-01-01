/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Try statement with Catch and/or Finally clause
 *
 * @author Philip Helger
 */
public class JSTryBlock extends AbstractJSStatement
{
  private final JSBlock m_aBody = new JSBlock ();
  private JSCatchBlock m_aCatch;
  private JSBlock m_aFinally;

  public JSTryBlock ()
  {}

  @Nonnull
  public JSBlock body ()
  {
    return m_aBody;
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCatchBlock _catch ()
  {
    if (m_aCatch == null)
      m_aCatch = new JSCatchBlock ();
    return m_aCatch;
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSCatchBlock _catch (@Nonnull @Nonempty final String sName)
  {
    if (m_aCatch == null)
      m_aCatch = new JSCatchBlock (sName);
    return m_aCatch;
  }

  public boolean hasCatch ()
  {
    return m_aCatch != null;
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSBlock _finally ()
  {
    if (m_aFinally == null)
      m_aFinally = new JSBlock ();
    return m_aFinally;
  }

  public boolean hasFinally ()
  {
    return m_aFinally != null;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aCatch == null && m_aFinally == null)
    {
      // no try necessary when there is no catch and no finally
      aFormatter.generatable (m_aBody);
    }
    else
    {
      aFormatter.plain ("try").generatable (m_aBody);
      if (m_aCatch != null)
        aFormatter.generatable (m_aCatch);
      if (m_aFinally != null)
        aFormatter.plain ("finally").generatable (m_aFinally);
      aFormatter.nl ();
    }
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
    final JSTryBlock rhs = (JSTryBlock) o;
    return EqualsHelper.equals (m_aBody, rhs.m_aBody) &&
           EqualsHelper.equals (m_aCatch, rhs.m_aCatch) &&
           EqualsHelper.equals (m_aFinally, rhs.m_aFinally);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aBody).append (m_aCatch).append (m_aFinally).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("body", m_aBody).append ("catch", m_aCatch).append ("finally", m_aFinally).getToString ();
  }
}
