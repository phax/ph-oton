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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSMarshaller;
import com.helger.json.IJson;

/**
 * Object invocation
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractJSInvocation <IMPLTYPE extends AbstractJSInvocation <IMPLTYPE>>
                                           extends AbstractJSExpression implements IJSStatement
{
  /**
   * Object expression upon which this method will be invoked, or null if this
   * is a constructor invocation
   */
  private final IJSGeneratable m_aObject;

  /**
   * If this field keeps the type to be created.
   */
  private final IJSGeneratable m_aCtorType;

  /**
   * Name of the method to be invoked. Either this field is set, or
   * {@link #m_aCallee}, or {@link #m_aCtorType} (in which case it's a
   * constructor invocation.) This allows {@link JSMethod#name(String) the name
   * of the method to be changed later}.
   */
  private final String m_sName;

  private final Object m_aCallee;

  /**
   * List of argument expressions for this method invocation
   */
  private final ICommonsList <IJSExpression> m_aArgs = new CommonsArrayList<> ();

  /**
   * Invoke a function
   *
   * @param aFunction
   */
  protected AbstractJSInvocation (@Nonnull final JSFunction aFunction)
  {
    m_aObject = null;
    m_sName = null;
    m_aCallee = ValueEnforcer.notNull (aFunction, "Function");
    m_aCtorType = null;
  }

  /**
   * Invoke a function
   *
   * @param sFunctionName
   *        function name
   */
  public AbstractJSInvocation (@Nonnull final String sFunctionName)
  {
    m_aObject = null;
    m_sName = ValueEnforcer.notNull (sFunctionName, "FunctionName");
    m_aCallee = null;
    m_aCtorType = null;
  }

  /**
   * Invoke an anonymous function
   *
   * @param aAnonymousFunction
   *        The function to be invoked
   */
  protected AbstractJSInvocation (@Nonnull final JSAnonymousFunction aAnonymousFunction)
  {
    m_aObject = null;
    m_sName = null;
    m_aCallee = ValueEnforcer.notNull (aAnonymousFunction, "AnonymousFunction");
    m_aCtorType = null;
  }

  /**
   * Invokes a method on an object.
   *
   * @param aLhs
   *        Expression for the object upon which the named method will be
   *        invoked, or null if none
   * @param sMethod
   *        Name of method to invoke
   */
  protected AbstractJSInvocation (@Nullable final IJSExpression aLhs, @Nonnull @Nonempty final String sMethod)
  {
    this ((IJSGeneratable) aLhs, sMethod);
  }

  protected AbstractJSInvocation (@Nullable final IJSExpression aLhs, @Nonnull final JSMethod aMethod)
  {
    this ((IJSGeneratable) aLhs, aMethod);
  }

  /**
   * Invokes a static method on a class.
   */
  protected AbstractJSInvocation (@Nullable final AbstractJSClass aType, @Nonnull @Nonempty final String sMethod)
  {
    this ((IJSGeneratable) aType, sMethod);
  }

  protected AbstractJSInvocation (@Nullable final AbstractJSClass aType, @Nonnull final JSMethod aMethod)
  {
    this ((IJSGeneratable) aType, aMethod);
  }

  private AbstractJSInvocation (@Nullable final IJSGeneratable aLhs, @Nonnull @Nonempty final String sMethod)
  {
    if (!JSMarshaller.isJSIdentifier (sMethod))
      throw new IllegalArgumentException ("The name '" + sMethod + "' is not a legal JS identifier!");
    m_aObject = aLhs;
    m_sName = sMethod;
    m_aCallee = null;
    m_aCtorType = null;
  }

  private AbstractJSInvocation (@Nullable final IJSGeneratable aLhs, @Nonnull final JSMethod aMethod)
  {
    m_aObject = aLhs;
    m_sName = null;
    m_aCallee = ValueEnforcer.notNull (aMethod, "Method");
    m_aCtorType = null;
  }

  /**
   * Invokes a constructor of an object (i.e., creates a new object.)
   *
   * @param aType
   *        Type of the object to be created. May not be <code>null</code>.
   */
  protected AbstractJSInvocation (@Nonnull final IJSGeneratable aType)
  {
    m_aObject = null;
    m_sName = null;
    m_aCallee = null;
    m_aCtorType = ValueEnforcer.notNull (aType, "Type");
  }

  @SuppressWarnings ("unchecked")
  @Nonnull
  private IMPLTYPE _thisAsT ()
  {
    return (IMPLTYPE) this;
  }

  /**
   * Add an expression to this invocation's argument list
   *
   * @param aExpr
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnull final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (aExpr, "Argument");
    m_aArgs.add (aExpr);
    return _thisAsT ();
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(bArgument))}
   *
   * @param bValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final boolean bValue)
  {
    return arg (JSExpr.lit (bValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(cArgument))}
   *
   * @param cValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final char cValue)
  {
    return arg (JSExpr.lit (cValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(dArgument))}
   *
   * @param dValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final double dValue)
  {
    return arg (JSExpr.lit (dValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(fArgument))}
   *
   * @param fValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final float fValue)
  {
    return arg (JSExpr.lit (fValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(nArgument))}
   *
   * @param nValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final int nValue)
  {
    return arg (JSExpr.lit (nValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(JSExpr.lit(nArgument))}
   *
   * @param nValue
   *        value to be added as an argument
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (final long nValue)
  {
    return arg (JSExpr.lit (nValue));
  }

  /**
   * Add an expression to this invocation's argument list or "null" if it is
   * <code>null</code>
   *
   * @param aExpr
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE argOrNull (@Nullable final IJSExpression aExpr)
  {
    return aExpr == null ? argNull () : arg (aExpr);
  }

  @Nonnull
  public IMPLTYPE argOrNull (@Nullable final Integer aValue)
  {
    return aValue == null ? argNull () : arg (aValue.intValue ());
  }

  @Nonnull
  public IMPLTYPE argOrNull (@Nullable final Long aValue)
  {
    return aValue == null ? argNull () : arg (aValue.longValue ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final String sValue)
  {
    return sValue == null ? argNull () : arg (JSExpr.lit (sValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final IJson aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.json (aValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final BigDecimal aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.lit (aValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final BigInteger aValue)
  {
    return aValue == null ? argNull () : arg (JSExpr.lit (aValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final EHTMLElement... aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final EHTMLElement eElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (eElement.getElementName ());
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final Iterable <EHTMLElement> aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final EHTMLElement eElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (eElement.getElementName ());
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final String... aElements)
  {
    if (aElements == null)
      return argNull ();

    final StringBuilder aSB = new StringBuilder ();
    for (final String sElement : aElements)
    {
      if (aSB.length () > 0)
        aSB.append (' ');
      aSB.append (sElement);
    }
    return arg (aSB.toString ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nullable final IHCNode aHCNode)
  {
    return aHCNode == null ? argNull () : arg (HCRenderer.getAsHTMLStringWithoutNamespaces (aHCNode));
  }

  /**
   * Adds a null argument. Short for {@code arg(JSExpr.NULL)}
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE argNull ()
  {
    return arg (JSExpr.NULL);
  }

  /**
   * Adds a null argument. Short for {@code arg(JSExpr.THIS)}
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE argThis ()
  {
    return arg (JSExpr.THIS);
  }

  /**
   * Add an expression to this invocation's argument list
   *
   * @param nIndex
   *        Index to insert
   * @param aArgument
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE argOrNull (@Nonnegative final int nIndex, @Nullable final IJSExpression aArgument)
  {
    return aArgument == null ? argNull (nIndex) : arg (nIndex, aArgument);
  }

  /**
   * Add an expression to this invocation's argument list
   *
   * @param nIndex
   *        Index to insert
   * @param aArgument
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, @Nonnull final IJSExpression aArgument)
  {
    ValueEnforcer.notNull (aArgument, "Argument");
    m_aArgs.add (nIndex, aArgument);
    return _thisAsT ();
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param bValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final boolean bValue)
  {
    return arg (nIndex, JSExpr.lit (bValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param cValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final char cValue)
  {
    return arg (nIndex, JSExpr.lit (cValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param dValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final double dValue)
  {
    return arg (nIndex, JSExpr.lit (dValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param fValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final float fValue)
  {
    return arg (nIndex, JSExpr.lit (fValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param nValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final int nValue)
  {
    return arg (nIndex, JSExpr.lit (nValue));
  }

  /**
   * Adds a literal argument. Short for {@code arg(nIndex, JSExpr.lit(v))}
   *
   * @param nIndex
   *        Index to insert
   * @param nValue
   *        argument value
   * @return this
   */
  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, final long nValue)
  {
    return arg (nIndex, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final Integer aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, aValue.intValue ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final Long aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, aValue.longValue ());
  }

  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final String sValue)
  {
    return sValue == null ? argNull (nIndex) : arg (nIndex, JSExpr.lit (sValue));
  }

  @Nonnull
  public IMPLTYPE arg (@Nonnegative final int nIndex, @Nullable final IJson aValue)
  {
    return aValue == null ? argNull (nIndex) : arg (nIndex, JSExpr.json (aValue));
  }

  /**
   * Adds a null argument. Short for {@code arg(nIndex, JSExpr.NULL)}
   *
   * @param nIndex
   *        Index to insert
   * @return this
   */
  @Nonnull
  public IMPLTYPE argNull (@Nonnegative final int nIndex)
  {
    return arg (nIndex, JSExpr.NULL);
  }

  /**
   * Adds a null argument. Short for {@code arg(nIndex, JSExpr.THIS)}
   *
   * @param nIndex
   *        Index to insert
   * @return this
   */
  @Nonnull
  public IMPLTYPE argThis (@Nonnegative final int nIndex)
  {
    return arg (nIndex, JSExpr.THIS);
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE args (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        arg (aExpr);
    return _thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE args (@Nullable final IJSExpression... aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        arg (aExpr);
    return _thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE argsOrNull (@Nullable final Iterable <? extends IJSExpression> aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        argOrNull (aExpr);
    return _thisAsT ();
  }

  /**
   * Add 0-n expressions to this invocation's argument list
   *
   * @param aExprs
   *        Argument to add to argument list
   * @return this
   */
  @Nonnull
  public IMPLTYPE argssOrNull (@Nullable final IJSExpression... aExprs)
  {
    if (aExprs != null)
      for (final IJSExpression aExpr : aExprs)
        argOrNull (aExpr);
    return _thisAsT ();
  }

  /**
   * Returns all arguments of the invocation.
   *
   * @return If there's no arguments, an empty array will be returned.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IJSExpression> args ()
  {
    return m_aArgs.getClone ();
  }

  /**
   * @return Number of currently contained arguments. Always &ge; 0.
   */
  @Nonnegative
  public int getArgCount ()
  {
    return m_aArgs.size ();
  }

  /**
   * @return <code>true</code> if at least 1 arg is present, <code>false</code>
   *         otherwise.
   */
  public boolean hasArgs ()
  {
    return m_aArgs.isNotEmpty ();
  }

  @Nullable
  public IJSExpression getArgAtIndex (final int nIndex)
  {
    return m_aArgs.getAtIndex (nIndex);
  }

  /**
   * Remove all arguments
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE removeAllArgs ()
  {
    m_aArgs.clear ();
    return _thisAsT ();
  }

  public void generate (@Nonnull final JSFormatter f)
  {
    if (m_aCallee instanceof JSAnonymousFunction)
    {
      // It's an anonymous function
      f.generatable (((JSAnonymousFunction) m_aCallee).inParantheses ()).plain ('(');
    }
    else
      if (m_aCtorType != null)
      {
        // It's a constructor call
        f.plain ("new ").generatable (m_aCtorType).plain ('(');
      }
      else
      {
        // Find name
        String sName = m_sName;
        if (sName == null && m_aCallee instanceof IJSDeclaration)
          sName = ((IJSDeclaration) m_aCallee).name ();

        if (m_aObject != null)
        {
          // Regular object method invocation
          if (sName == null)
            throw new IllegalStateException ("Name is required if an object is present");
          f.generatable (m_aObject).plain ('.').plain (sName).plain ('(');
        }
        else
          if (sName != null)
          {
            // E.g. global function
            f.plain (sName).plain ('(');
          }
      }

    // Add the arguments
    f.generatable (m_aArgs).plain (')');
  }

  public void state (@Nonnull final JSFormatter f)
  {
    f.generatable (this).plain (';').nl ();
  }

  @Override
  @Nullable
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, (IJSStatement) this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AbstractJSInvocation <?> rhs = (AbstractJSInvocation <?>) o;
    return EqualsHelper.equals (m_aObject, rhs.m_aObject) &&
           EqualsHelper.equals (m_aCtorType, rhs.m_aCtorType) &&
           EqualsHelper.equals (m_aCallee, rhs.m_aCallee) &&
           EqualsHelper.equals (m_sName, rhs.m_sName) &&
           m_aArgs.equals (rhs.m_aArgs);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aObject)
                            .append (m_aCtorType)
                            .append (m_aCallee)
                            .append (m_sName)
                            .append (m_aArgs)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("object", m_aObject)
                            .appendIfNotNull ("ctorType", m_aCtorType)
                            .appendIfNotNull ("callee", m_aCallee)
                            .appendIfNotNull ("name", m_sName)
                            .append ("args", m_aArgs)
                            .getToString ();
  }
}
