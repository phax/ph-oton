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
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;

/**
 * Single line comments
 *
 * @author Philip Helger
 */
public class JSCommentSingleLine extends AbstractJSStatement
{
  private final String m_sComment;

  public JSCommentSingleLine (@Nonnull final String sComment)
  {
    m_sComment = ValueEnforcer.notNull (sComment, "Comment");
  }

  @Nonnull
  public String getComment ()
  {
    return m_sComment;
  }

  @Override
  public void state (@Nonnull final JSFormatter aFormatter)
  {
    if (aFormatter.getSettings ().isGenerateComments ())
    {
      final boolean bIndentAndAlign = aFormatter.getSettings ().isIndentAndAlign ();
      for (final String sLine : RegExHelper.getSplitToArray (m_sComment, "(\\r\\n|\\r|\\n)"))
        if (bIndentAndAlign)
          aFormatter.plain ("// ").plain (sLine).nl ();
        else
          aFormatter.plain ("/*").plain (sLine).plain ("*/");
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
    final JSCommentSingleLine rhs = (JSCommentSingleLine) o;
    return m_sComment.equals (rhs.m_sComment);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sComment).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("comment", m_sComment).getToString ();
  }
}
