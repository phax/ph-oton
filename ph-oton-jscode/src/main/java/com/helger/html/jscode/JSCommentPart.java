/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A part is a part of a JSDoc comment, and it is a list of values.
 *
 * @author Philip Helger
 */
public class JSCommentPart extends CommonsArrayList <Object>
{
  /**
   * Appends a new value. If the value is {@link AbstractJSType} it will be
   * printed as a @link tag. Otherwise it will be converted to String via
   * {@link Object#toString()} .
   *
   * @param aValue
   *        Value to append
   * @return this
   */
  public JSCommentPart append (@Nullable final Object aValue)
  {
    add (aValue);
    return this;
  }

  @Override
  public boolean add (@Nullable final Object aValue)
  {
    _flattenAppend (aValue);
    return true;
  }

  private void _flattenAppend (@Nullable final Object aValue)
  {
    if (aValue == null)
      return;
    if (aValue instanceof Object [])
    {
      for (final Object aChild : (Object []) aValue)
        _flattenAppend (aChild);
    }
    else
      if (aValue instanceof Iterable <?>)
      {
        for (final Object aChild : (Iterable <?>) aValue)
          _flattenAppend (aChild);
      }
      else
        super.add (aValue);
  }

  /**
   * Writes this part into the formatter by using the specified indentation.
   * 
   * @param aFormatter
   *        Formatter to use. May not be <code>null</code>.
   * @param sIndent
   *        Indentation string to use. May not be <code>null</code>.
   */
  protected void format (@Nonnull final JSFormatter aFormatter, @Nonnull final String sIndent)
  {
    if (!isEmpty ())
      aFormatter.plain (sIndent);

    final Iterator <Object> aIter = iterator ();
    while (aIter.hasNext ())
    {
      final Object aValue = aIter.next ();

      if (aValue instanceof String)
      {
        int nIdx;
        String sValue = (String) aValue;
        while ((nIdx = sValue.indexOf ('\n')) != -1)
        {
          final String sLine = sValue.substring (0, nIdx);
          if (sLine.length () > 0)
            aFormatter.plain (_escape (sLine));
          sValue = sValue.substring (nIdx + 1);
          aFormatter.nlFix ().plain (sIndent);
        }
        if (sValue.length () != 0)
          aFormatter.plain (_escape (sValue));
      }
      else
        if (aValue instanceof AbstractJSClass)
        {
          ((AbstractJSClass) aValue).printLink (aFormatter);
        }
        else
          if (aValue instanceof AbstractJSType)
          {
            aFormatter.generatable ((AbstractJSType) aValue);
          }
          else
            throw new IllegalStateException ("Unsupported value: " + aValue);
    }

    if (!isEmpty ())
      aFormatter.nlFix ();
  }

  /**
   * Escapes the appearance of the comment terminator.
   */
  @Nonnull
  private static String _escape (@Nonnull final String sStr)
  {
    String ret = sStr;
    while (true)
    {
      final int idx = ret.indexOf ("*/");
      if (idx < 0)
        return ret;

      ret = ret.substring (0, idx + 1) + "<!-- -->" + ret.substring (idx + 1);
    }
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    return super.equals (o);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", super.toString ()).getToString ();
  }
}
