/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Case statement
 *
 * @author Philip Helger
 */
public class JSCase extends AbstractJSStatement
{
  public static final boolean DEFAULT_DEFAULT_CASE = false;

  /**
   * label part of the case statement
   */
  private final IJSExpression m_aLabel;

  /**
   * Block of statements which makes up body of this While statement
   */
  private JSBlock m_aBody;

  /**
   * is this a regular case statement or a default case statement?
   */
  private boolean m_bIsDefaultCase = DEFAULT_DEFAULT_CASE;

  /**
   * Construct a case statement
   *
   * @param aLabel
   *        May not be <code>null</code>.
   */
  public JSCase (@NonNull final IJSExpression aLabel)
  {
    this (aLabel, false);
  }

  /**
   * Construct a case statement. If isDefaultCase is true, then label should be
   * null since default cases don't have a label.
   *
   * @param aLabel
   *        May be <code>null</code> if this is the default case
   * @param bIsDefaultCase
   *        <code>true</code> if this is the default case!
   */
  public JSCase (@Nullable final IJSExpression aLabel, final boolean bIsDefaultCase)
  {
    if (!bIsDefaultCase && aLabel == null)
      throw new IllegalArgumentException ("Only the default case may not have a label!");
    m_aLabel = aLabel;
    m_bIsDefaultCase = bIsDefaultCase;
  }

  public boolean isDefaultCase ()
  {
    return m_bIsDefaultCase;
  }

  @Nullable
  public IJSExpression label ()
  {
    return m_aLabel;
  }

  @NonNull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock (false, true);
    return m_aBody;
  }

  public void state (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.indent ();
    if (!m_bIsDefaultCase)
      aFormatter.plain ("case ").generatable (m_aLabel).plain (':').nl ();
    else
      aFormatter.plain ("default:").nl ();
    if (m_aBody != null)
      aFormatter.stmt (m_aBody);
    aFormatter.outdent ();
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
    final JSCase rhs = (JSCase) o;
    return EqualsHelper.equals (m_aLabel, rhs.m_aLabel) &&
           EqualsHelper.equals (m_aBody, rhs.m_aBody) &&
           m_bIsDefaultCase == rhs.m_bIsDefaultCase;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aLabel).append (m_aBody).append (m_bIsDefaultCase).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("label", m_aLabel)
                                       .append ("body", m_aBody)
                                       .append ("isDefaultCase", m_bIsDefaultCase)
                                       .getToString ();
  }
}
