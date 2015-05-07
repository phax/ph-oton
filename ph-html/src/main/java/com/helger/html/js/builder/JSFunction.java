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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.marshal.JSMarshaller;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * JS function
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
   * Return type for this function
   */
  private AbstractJSType m_aType;

  /**
   * Name of this function
   */
  private String m_sName;

  /**
   * List of parameters for this function's declaration
   */
  private final List <JSVar> m_aParams = new ArrayList <JSVar> ();

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
    this (null, sName);
  }

  /**
   * function constructor
   *
   * @param aType
   *        Type to use
   * @param sName
   *        Name of this function
   */
  public JSFunction (@Nullable final AbstractJSType aType, @Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_aType = aType;
    m_sName = sName;
  }

  /**
   * @return the return type.
   */
  @Nullable
  public AbstractJSType type ()
  {
    return m_aType;
  }

  /**
   * Overrides the return type.
   *
   * @param aType
   *        new return type
   * @return this
   */
  @Nonnull
  public JSFunction type (@Nullable final AbstractJSType aType)
  {
    m_aType = aType;
    return this;
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
  public List <JSVar> params ()
  {
    return CollectionHelper.newList (m_aParams);
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
    return param (null, sName);
  }

  /**
   * Add the specified variable to the list of parameters for this function
   * signature.
   *
   * @param aType
   *        Type of the parameter being added
   * @param sName
   *        Name of the parameter being added
   * @return New parameter variable
   */
  @Nonnull
  public JSVar param (@Nullable final AbstractJSType aType, @Nonnull @Nonempty final String sName)
  {
    final JSVar aVar = new JSVar (aType, sName, null);
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
    return CollectionHelper.getSafe (m_aParams, nIndex);
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
    return new JSAnonymousFunction (type (), params (), body ());
  }

  @Override
  public void declare (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aJSDoc != null)
      aFormatter.generatable (m_aJSDoc);

    aFormatter.plain ("function ").typename (m_aType).plain (m_sName).plain ('(');
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

  @Nonnull
  public final String getJSCode ()
  {
    return getJSCode ((IJSWriterSettings) null);
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
    return EqualsUtils.equals (m_aJSDoc, rhs.m_aJSDoc) &&
           EqualsUtils.equals (m_aType, rhs.m_aType) &&
           m_sName.equals (rhs.m_sName) &&
           m_aParams.equals (rhs.m_aParams) &&
           EqualsUtils.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aJSDoc)
                                       .append (m_aType)
                                       .append (m_sName)
                                       .append (m_aParams)
                                       .append (m_aBody)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("jsDoc", m_aJSDoc)
                                       .appendIfNotNull ("type", m_aType)
                                       .append ("name", m_sName)
                                       .appendIfNotEmpty ("params", m_aParams)
                                       .appendIfNotNull ("body", m_aBody)
                                       .toString ();
  }
}
