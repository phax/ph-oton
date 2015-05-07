/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.js.builder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * Continue statement
 *
 * @author Philip Helger
 */
public class JSContinue extends AbstractJSStatement
{
  private final JSLabel m_aLabel;

  /**
   * constructor.
   *
   * @param aLabel
   *        a valid label or null.
   */
  public JSContinue (@Nullable final JSLabel aLabel)
  {
    m_aLabel = aLabel;
  }

  @Nullable
  public JSLabel label ()
  {
    return m_aLabel;
  }

  public boolean hasLabel ()
  {
    return m_aLabel != null;
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aLabel == null)
      aFormatter.plain ("continue;").nl ();
    else
      aFormatter.plain ("continue ").plain (m_aLabel.label ()).plain (';').nl ();
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
    final JSContinue rhs = (JSContinue) o;
    return EqualsUtils.equals (m_aLabel, rhs.m_aLabel);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aLabel).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("label", m_aLabel).toString ();
  }
}
