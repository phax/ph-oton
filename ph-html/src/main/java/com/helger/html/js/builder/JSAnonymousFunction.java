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

/**
 * An anonymous function (a function without a name)
 *
 * @author Philip Helger
 */
public class JSAnonymousFunction extends AbstractJSExpression
{
  /**
   * Return type for this function
   */
  private AbstractJSType m_aType;

  /**
   * List of parameters for this function's declaration
   */
  private final List <JSVar> m_aParams = new ArrayList <JSVar> ();

  /**
   * JBlock of statements that makes up the body this function
   */
  private JSBlock m_aBody;

  /**
   * Default constructor
   */
  public JSAnonymousFunction ()
  {}

  /**
   * constructor
   *
   * @param aType
   *        Type to which the expression is cast
   */
  public JSAnonymousFunction (@Nullable final AbstractJSType aType)
  {
    m_aType = aType;
  }

  /**
   * Constructor for simple functions
   *
   * @param aBody
   *        The body statement. May be <code>null</code>.
   */
  public JSAnonymousFunction (@Nullable final IJSStatement aBody)
  {
    if (aBody != null)
      body ().add (aBody);
  }

  public JSAnonymousFunction (@Nullable final JSVar aParam, @Nullable final IJSStatement aBody)
  {
    if (aParam != null)
      m_aParams.add (aParam);
    if (aBody != null)
      body ().add (aBody);
  }

  public JSAnonymousFunction (@Nullable final List <JSVar> aParams, @Nullable final IJSStatement aBody)
  {
    if (aParams != null)
      m_aParams.addAll (aParams);
    if (aBody != null)
      body ().add (aBody);
  }

  public JSAnonymousFunction (@Nullable final AbstractJSType aType,
                              @Nullable final List <JSVar> aParams,
                              @Nullable final IJSStatement aBody)
  {
    this (aType);
    if (aParams != null)
      m_aParams.addAll (aParams);
    if (aBody != null)
      body ().add (aBody);
  }

  public JSAnonymousFunction (@Nullable final JSVar aParam, @Nullable final JSBlock aBody)
  {
    if (aParam != null)
      m_aParams.add (aParam);
    m_aBody = aBody;
  }

  public JSAnonymousFunction (@Nullable final List <JSVar> aParams, @Nullable final JSBlock aBody)
  {
    if (aParams != null)
      m_aParams.addAll (aParams);
    m_aBody = aBody;
  }

  public JSAnonymousFunction (@Nullable final AbstractJSType aType,
                              @Nullable final List <JSVar> aParams,
                              @Nullable final JSBlock aBody)
  {
    this (aType);
    if (aParams != null)
      m_aParams.addAll (aParams);
    m_aBody = aBody;
  }

  /**
   * @return The return type. May be <code>null</code>.
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
   *        the new return type.
   */
  public void type (@Nullable final AbstractJSType aType)
  {
    m_aType = aType;
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
   *        type of the parameter being added
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

  /**
   * Get the block that makes up body of this function
   *
   * @return Body of function
   */
  @Nonnull
  public JSBlock body ()
  {
    if (m_aBody == null)
      m_aBody = new JSBlock ().newlineAtEnd (false);
    return m_aBody;
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    return new JSInvocation (this);
  }

  public void generate (final JSFormatter aFormatter)
  {
    aFormatter.plain ("function").typename (m_aType).plain ('(');
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

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSAnonymousFunction rhs = (JSAnonymousFunction) o;
    return EqualsUtils.equals (m_aType, rhs.m_aType) &&
           m_aParams.equals (rhs.m_aParams) &&
           EqualsUtils.equals (m_aBody, rhs.m_aBody);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aType)
                            .append (m_aParams)
                            .append (m_aBody)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("type", m_aType)
                            .appendIfNotEmpty ("params", m_aParams)
                            .append ("body", m_aBody)
                            .toString ();
  }
}
