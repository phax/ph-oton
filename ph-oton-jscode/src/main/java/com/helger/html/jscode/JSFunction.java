/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSMarshaller;

/**
 * JS function declaration
 *
 * @author Philip Helger
 */
public class JSFunction implements IJSDocCommentable, IJSDeclaration
{
  /**
   * JS doc comments for this function
   */
  private JSCommentMultiLine m_aJSDoc;

  /**
   * Name of this function
   */
  private String m_sName;

  /**
   * List of parameters for this function's declaration
   */
  private final ICommonsList <JSVar> m_aParams = new CommonsArrayList <> ();

  /**
   * Block of statements that makes up the body this function
   */
  private JSBlock m_aBody;

  /**
   * function constructor
   *
   * @param sName
   *        Name of this function
   */
  public JSFunction (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
  }

  @Nonnull
  public String name ()
  {
    return m_sName;
  }

  /**
   * Changes the name of the function.
   *
   * @param sName
   *        new function name
   * @return this
   */
  @Nonnull
  public JSFunction name (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
    return this;
  }

  /**
   * Returns the list of variable of this function.
   *
   * @return List of parameters of this function. This list is not modifiable.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <JSVar> params ()
  {
    return m_aParams.getClone ();
  }

  /**
   * Add the specified variable to the list of parameters for this function
   * signature.
   *
   * @param sName
   *        Name of the parameter being added
   * @return New parameter variable
   */
  @Nonnull
  public JSVar param (@Nonnull @Nonempty final String sName)
  {
    final JSVar aVar = new JSVar (sName, null);
    m_aParams.add (aVar);
    return aVar;
  }

  @Nonnegative
  public int getParamCount ()
  {
    return m_aParams.size ();
  }

  @Nullable
  public JSVar getParamAtIndex (final int nIndex)
  {
    return m_aParams.getAtIndex (nIndex);
  }

  @Nonnull
  public JSFunction removeAllParams ()
  {
    m_aParams.clear ();
    return this;
  }

  /**
   * Get the block that makes up body of this function
   *
   * @return Body of function
   */
  @Nonnull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ();
    return m_aBody;
  }

  /**
   * Creates, if necessary, and returns the class JSDocs for this function
   *
   * @return {@link JSCommentMultiLine} containing JSDocs for this class
   */
  @Nonnull
  public JSCommentMultiLine jsDoc ()
  {
    if (m_aJSDoc == null)
      m_aJSDoc = new JSCommentMultiLine ();
    return m_aJSDoc;
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    return new JSInvocation (this);
  }

  @Nonnull
  public JSAnonymousFunction getAsAnonymousFunction ()
  {
    // No name required for anonymous function
    return new JSAnonymousFunction (params (), body ());
  }

  @Override
  public void declare (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aJSDoc != null)
      aFormatter.generatable (m_aJSDoc);

    aFormatter.plain ("function ").plain (m_sName).plain ('(');
    boolean bFirst = true;
    for (final JSVar aParam : m_aParams)
    {
      if (bFirst)
        bFirst = false;
      else
        aFormatter.plain (',');
      aFormatter.var (aParam);
    }
    aFormatter.plain (')').stmt (body ());
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
    final JSFunction rhs = (JSFunction) o;
    return EqualsHelper.equals (m_aJSDoc, rhs.m_aJSDoc) &&
           m_sName.equals (rhs.m_sName) &&
           m_aParams.equals (rhs.m_aParams) &&
           EqualsHelper.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aJSDoc)
                                       .append (m_sName)
                                       .append (m_aParams)
                                       .append (m_aBody)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("jsDoc", m_aJSDoc)
                                       .append ("name", m_sName)
                                       .appendIf ("params", m_aParams, CollectionHelper::isNotEmpty)
                                       .appendIfNotNull ("body", m_aBody)
                                       .getToString ();
  }
}
