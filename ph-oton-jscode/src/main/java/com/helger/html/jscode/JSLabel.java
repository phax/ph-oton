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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Label that can be used for continue and break.
 *
 * @author Philip Helger
 */
public class JSLabel extends AbstractJSStatement
{
  private final String m_sLabel;

  /**
   * constructor
   *
   * @param sLabel
   *        break label or null.
   */
  public JSLabel (@Nonnull @Nonempty final String sLabel)
  {
    m_sLabel = ValueEnforcer.notEmpty (sLabel, "Label");
  }

  @Nonnull
  @Nonempty
  public String label ()
  {
    return m_sLabel;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sLabel).plain (':').nl ();
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
    final JSLabel rhs = (JSLabel) o;
    return m_sLabel.equals (rhs.m_sLabel);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sLabel).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("label", m_sLabel).getToString ();
  }
}
