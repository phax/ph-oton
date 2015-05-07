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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.GlobalDebug;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.CodingStyleguideUnaware;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.math.MathHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.provider.CollectingJSCodeProvider;
import com.helger.html.js.provider.IJSCodeProviderWithSettings;
import com.helger.html.js.writer.IJSWriterSettings;
import com.helger.json.IJson;

/**
 * A JS block. It contains a list of statements and declarations.
 *
 * @author Philip Helger
 */
@CodingStyleguideUnaware
public abstract class AbstractJSBlock implements IJSFunctionContainer
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractJSBlock.class);

  /**
   * List of the content of this block
   */
  private final List <IJSCodeProvider> m_aObjs = new ArrayList <IJSCodeProvider> ();

  /**
   * Named map of all declarations
   */
  private final Map <String, IJSDeclaration> m_aDecls = new HashMap <String, IJSDeclaration> ();

  /**
   * Current position. Must be &ge; 0.
   */
  private int m_nPos = 0;

  /**
   * Constructor
   */
  public AbstractJSBlock ()
  {}

  /**
   * Removes a declaration from this package.
   *
   * @param sName
   *        Name to remove
   * @return Never <code>null</code>.
   */
  @Nonnull
  public EChange removeByName (final String sName)
  {
    final IJSDeclaration aDecl = m_aDecls.remove (sName);
    if (aDecl == null)
      return EChange.UNCHANGED;
    m_aObjs.remove (aDecl);
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IJSDeclaration> declarations ()
  {
    return CollectionHelper.newList (m_aDecls.values ());
  }

  /**
   * Gets a reference to the already created {@link JSDefinedClass}.
   *
   * @param sName
   *        Name to search
   * @return <code>null</code> If the object is not yet created.
   */
  @Nullable
  public IJSDeclaration getDeclaration (@Nullable final String sName)
  {
    return m_aDecls.get (sName);
  }

  /**
   * Checks if a given name is already defined as a class/interface
   *
   * @param sName
   *        Name to search
   * @return <code>true</code> if the passed variable is contained
   */
  public boolean isDeclared (@Nullable final String sName)
  {
    return m_aDecls.containsKey (sName);
  }

  /**
   * @return <code>true</code> if this block is empty and does not contain any
   *         statement.
   */
  public boolean isEmpty ()
  {
    return m_aObjs.isEmpty ();
  }

  @Nonnegative
  public int memberCount ()
  {
    return m_aObjs.size ();
  }

  @Nonnull
  @ReturnsMutableObject (reason = "speed")
  List <IJSCodeProvider> directMembers ()
  {
    return m_aObjs;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IJSCodeProvider> members ()
  {
    return CollectionHelper.newList (m_aObjs);
  }

  /**
   * Remove all contents of the block. Sets the position to 0.
   *
   * @return this
   */
  @Nonnull
  public AbstractJSBlock clear ()
  {
    m_aObjs.clear ();
    m_aDecls.clear ();
    m_nPos = 0;
    return this;
  }

  /**
   * Called when a declaration is added
   *
   * @param aDeclaration
   *        The added declaration. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onAddDeclaration (@Nonnull final IJSDeclaration aDeclaration)
  {}

  @Nonnull
  public final <T extends IJSDeclaration> T addDeclaration (@Nonnull final T aDeclaration) throws JSNameAlreadyExistsException
  {
    ValueEnforcer.notNull (aDeclaration, "Declaration");

    final String sName = aDeclaration.name ();
    final IJSDeclaration aOldDecl = m_aDecls.get (sName);
    if (aOldDecl != null)
      throw new JSNameAlreadyExistsException (aOldDecl);
    m_aObjs.add (m_nPos, aDeclaration);
    m_aDecls.put (sName, aDeclaration);
    m_nPos++;
    onAddDeclaration (aDeclaration);
    return aDeclaration;
  }

  @Nonnull
  public final <T extends IJSStatement> T addStatement (@Nonnull final T aStatement)
  {
    ValueEnforcer.notNull (aStatement, "Statement");

    m_aObjs.add (m_nPos, aStatement);
    m_nPos++;
    return aStatement;
  }

  /**
   * Gets the current position to which new statements will be inserted. For
   * example if the value is 0, newly created instructions will be inserted at
   * the very beginning of the block.
   *
   * @return The current position
   * @see #pos(int)
   */
  @Nonnegative
  public int pos ()
  {
    return m_nPos;
  }

  /**
   * Sets the current position.
   *
   * @param nNewPos
   *        New position to use
   * @return the old value of the current position.
   * @throws IllegalArgumentException
   *         if the new position value is illegal.
   * @see #pos()
   */
  @Nonnegative
  public int pos (@Nonnegative final int nNewPos)
  {
    ValueEnforcer.isBetweenInclusive (nNewPos, "NewPos", 0, m_aObjs.size ());
    final int nOldPos = m_nPos;
    m_nPos = nNewPos;
    return nOldPos;
  }

  /**
   * Sets the current position to the end of the block.
   *
   * @return the old value of the current position.
   * @see #pos()
   */
  @Nonnegative
  public int posEnd ()
  {
    return pos (m_aObjs.size ());
  }

  /**
   * Add a class to this package.
   *
   * @param sName
   *        Name of class to be added to this package
   * @return Newly generated class
   * @exception JSNameAlreadyExistsException
   *            When the specified class/interface was already created.
   */
  @Nonnull
  public JSDefinedClass _class (@Nonnull @Nonempty final String sName) throws JSNameAlreadyExistsException
  {
    final JSDefinedClass aDefinedClass = new JSDefinedClass (sName);
    return addDeclaration (aDefinedClass);
  }

  /**
   * @param aType
   *        The type to be instantiated
   * @return A "new type" invocation object
   */
  @Nonnull
  public JSInvocation _new (@Nonnull final AbstractJSType aType)
  {
    ValueEnforcer.notNull (aType, "Type");

    return aType._new ();
  }

  @Nonnull
  public JSFunction function (@Nonnull final String sName) throws JSNameAlreadyExistsException
  {
    return function (null, sName);
  }

  /**
   * Add a function to this package.
   *
   * @param aType
   *        Optional return type
   * @param sName
   *        Name of function to be added to this package
   * @return Newly generated function
   * @exception JSNameAlreadyExistsException
   *            When the specified function was already created.
   */
  @Nonnull
  public JSFunction function (@Nullable final AbstractJSType aType, @Nonnull @Nonempty final String sName) throws JSNameAlreadyExistsException
  {
    final JSFunction aFunction = new JSFunction (aType, sName);
    return addDeclaration (aFunction);
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName) throws JSNameAlreadyExistsException
  {
    return var (null, sName, null);
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param bInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final boolean bInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (bInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param cInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final char cInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (cInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param dInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final double dInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (dInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param fInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final float fInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (fInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param nInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final int nInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (nInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param nInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final long nInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, JSExpr.lit (nInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param sInitValue
   *        Initialization value for this variable.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, @Nullable final String sInitValue) throws JSNameAlreadyExistsException
  {
    return var (null, sName, sInitValue == null ? JSExpr.NULL : JSExpr.lit (sInitValue));
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param sName
   *        Name of the variable
   * @param aInitExpression
   *        Initialization expression for this variable. May be null.
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInitExpression) throws JSNameAlreadyExistsException
  {
    return var (null, sName, aInitExpression);
  }

  /**
   * Adds a local variable declaration to this block
   *
   * @param aType
   *        Type of the variable
   * @param sName
   *        Name of the variable
   * @return Newly generated {@link JSVar}
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSVar var (@Nullable final AbstractJSType aType, @Nonnull @Nonempty final String sName) throws JSNameAlreadyExistsException
  {
    return var (aType, sName, null);
  }

  /**
   * Add a var to this block.
   *
   * @param aType
   *        optional type to use
   * @param sName
   *        Name of variable to be added to this package
   * @param aInitExpression
   *        the initial expression. May be <code>null</code>
   * @return Newly generated function
   * @exception JSNameAlreadyExistsException
   *            When the specified var was already created.
   */
  @Nonnull
  public JSVar var (@Nullable final AbstractJSType aType,
                    @Nonnull @Nonempty final String sName,
                    @Nullable final IJSExpression aInitExpression) throws JSNameAlreadyExistsException
  {
    final JSVar aVar = new JSVar (aType, sName, aInitExpression);
    return addDeclaration (aVar);
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull final JSAnonymousFunction aAnonFunction)
  {
    ValueEnforcer.notNull (aAnonFunction, "AnonFunction");

    return addStatement (aAnonFunction.invoke ());
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull final JSFunction aFunction)
  {
    ValueEnforcer.notNull (aFunction, "Function");

    return addStatement (aFunction.invoke ());
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull @Nonempty final String sFunctionName)
  {
    final JSInvocation aInvocation = new JSInvocation (sFunctionName);
    return addStatement (aInvocation);
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull @Nonempty final String sField, @Nonnull @Nonempty final String sMethodName)
  {
    final JSInvocation aInvocation = JSExpr.ref (sField).invoke (sMethodName);
    return addStatement (aInvocation);
  }

  @Nonnull
  public JSInvocation invoke (@Nonnull @Nonempty final String sField, @Nonnull final JSMethod aMethod)
  {
    final JSInvocation aInvocation = JSExpr.ref (sField).invoke (aMethod);
    return addStatement (aInvocation);
  }

  /**
   * Creates an invocation statement and adds it to this block.
   *
   * @param aExpr
   *        JExpression evaluating to the class or object upon which the named
   *        method will be invoked
   * @param sMethod
   *        Name of method to invoke
   * @return Newly generated {@link JSInvocation}
   */
  @Nonnull
  public JSInvocation invoke (@Nullable final IJSExpression aExpr, @Nonnull @Nonempty final String sMethod)
  {
    final JSInvocation aInvocation = new JSInvocation (aExpr, sMethod);
    return addStatement (aInvocation);
  }

  /**
   * Creates an invocation statement and adds it to this block.
   *
   * @param aExpr
   *        JExpression evaluating to the class or object upon which the method
   *        will be invoked
   * @param aMethod
   *        JMethod to invoke
   * @return Newly generated {@link JSInvocation}
   */
  @Nonnull
  public JSInvocation invoke (@Nullable final IJSExpression aExpr, @Nonnull final JSMethod aMethod)
  {
    final JSInvocation aInvocation = new JSInvocation (aExpr, aMethod);
    return addStatement (aInvocation);
  }

  /**
   * Creates a static invocation statement.
   *
   * @param aType
   *        Type to use
   * @param sMethod
   *        Method name to invoke
   * @return Never <code>null</code>.
   */
  @Nonnull
  public JSInvocation staticInvoke (@Nullable final AbstractJSClass aType, @Nonnull final String sMethod)
  {
    final JSInvocation aInvocation = new JSInvocation (aType, sMethod);
    return addStatement (aInvocation);
  }

  /**
   * Creates a static invocation statement.
   *
   * @param aType
   *        Type to use
   * @param aMethod
   *        Method to invoke
   * @return Never <code>null</code>.
   */
  @Nonnull
  public JSInvocation staticInvoke (@Nullable final AbstractJSClass aType, @Nonnull final JSMethod aMethod)
  {
    final JSInvocation aInvocation = new JSInvocation (aType, aMethod);
    return addStatement (aInvocation);
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final boolean bValue)
  {
    return assign (aLhs, JSExpr.lit (bValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final char cValue)
  {
    return assign (aLhs, JSExpr.lit (cValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    return assign (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    return assign (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    return assign (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    return assign (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final String sValue)
  {
    return assign (aLhs, sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, @Nullable final IJson aValue)
  {
    return assign (aLhs, aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  @Nonnull
  public AbstractJSBlock assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (aLhs.assign (aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final char cValue)
  {
    return assignPlus (aLhs, JSExpr.lit (cValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No add with 0
    if (EqualsUtils.equals (dValue, 0))
      return this;
    if (dValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (dValue)));
    return assignPlus (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No add with 0
    if (EqualsUtils.equals (fValue, 0))
      return this;
    if (fValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (fValue)));
    return assignPlus (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No add with 0
    if (EqualsUtils.equals (nValue, 0))
      return this;
    if (nValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (nValue)));
    return assignPlus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No add with 0
    if (EqualsUtils.equals (nValue, 0))
      return this;
    if (nValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (nValue)));
    return assignPlus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final String sValue)
  {
    return assignPlus (aLhs, JSExpr.lit (sValue));
  }

  @Nonnull
  public AbstractJSBlock assignPlus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignPlus (aLhs, aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No subtract with 0
    if (EqualsUtils.equals (dValue, 0))
      return this;
    return assignMinus (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No subtract with 0
    if (EqualsUtils.equals (fValue, 0))
      return this;
    return assignMinus (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No subtract with 0
    if (EqualsUtils.equals (nValue, 0))
      return this;
    return assignMinus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No subtract with 0
    if (EqualsUtils.equals (nValue, 0))
      return this;
    return assignMinus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignMinus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignMinus (aLhs, aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No multiply with 1
    if (EqualsUtils.equals (dValue, 1))
      return this;
    return assignMultiply (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No multiply with 1
    if (EqualsUtils.equals (fValue, 1))
      return this;
    return assignMultiply (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No multiply with 1
    if (EqualsUtils.equals (nValue, 1))
      return this;
    return assignMultiply (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No multiply with 1
    if (EqualsUtils.equals (nValue, 1))
      return this;
    return assignMultiply (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignMultiply (aLhs, aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No divide by 1
    if (EqualsUtils.equals (dValue, 1))
      return this;
    return assignDivide (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No divide by 1
    if (EqualsUtils.equals (fValue, 1))
      return this;
    return assignDivide (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No divide by 1
    if (EqualsUtils.equals (nValue, 1))
      return this;
    return assignDivide (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No divide by 1
    if (EqualsUtils.equals (nValue, 1))
      return this;
    return assignDivide (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignDivide (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignDivide (aLhs, aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock assignModulo (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    return assignModulo (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignModulo (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    return assignModulo (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock assignModulo (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignModulo (aLhs, aExpr));
    return this;
  }

  @Nonnull
  public AbstractJSBlock incrPostfix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSIncrPostfix (aLhs));
    return this;
  }

  @Nonnull
  public AbstractJSBlock incrPrefix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSIncrPrefix (aLhs));
    return this;
  }

  @Nonnull
  public AbstractJSBlock decrPostfix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSDecrPostfix (aLhs));
    return this;
  }

  @Nonnull
  public AbstractJSBlock decrPrefix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSDecrPrefix (aLhs));
    return this;
  }

  /**
   * Create a For statement and add it to this block
   *
   * @return Newly generated For statement
   */
  @Nonnull
  public JSForLoop _for ()
  {
    return addStatement (new JSForLoop ());
  }

  @Nonnull
  public JSForIn forIn (@Nonnull final JSVar aVar, @Nonnull final IJSExpression aCollection)
  {
    return addStatement (new JSForIn (aVar, aCollection));
  }

  @Nonnull
  public JSForIn forIn (@Nonnull @Nonempty final String sVarName, @Nonnull final IJSExpression aCollection)
  {
    return forIn (null, sVarName, aCollection);
  }

  @Nonnull
  public JSForIn forIn (@Nullable final AbstractJSType aVarType,
                        @Nonnull @Nonempty final String sVarName,
                        @Nonnull final IJSExpression aCollection)
  {
    return addStatement (new JSForIn (aVarType, sVarName, aCollection));
  }

  /**
   * Create a While statement and add it to this block
   *
   * @param aTest
   *        The while condition to use
   * @return Newly generated While statement
   */
  @Nonnull
  public JSWhileLoop _while (@Nonnull final IJSExpression aTest)
  {
    return addStatement (new JSWhileLoop (aTest));
  }

  /**
   * Create a Do statement and add it to this block
   *
   * @param aTest
   *        Do test
   * @return Newly generated Do statement
   */
  @Nonnull
  public JSDoLoop _do (@Nonnull final IJSExpression aTest)
  {
    return addStatement (new JSDoLoop (aTest));
  }

  /**
   * Create a switch/case statement and add it to this block
   *
   * @param aTest
   *        test expression
   * @return Created switch statement
   */
  @Nonnull
  public JSSwitch _switch (@Nonnull final IJSExpression aTest)
  {
    return addStatement (new JSSwitch (aTest));
  }

  /**
   * Create a Try statement and add it to this block
   *
   * @return Newly generated Try statement
   */
  @Nonnull
  public JSTryBlock _try ()
  {
    return addStatement (new JSTryBlock ());
  }

  /**
   * Insert a <code>delete aExpr;</code> statement
   *
   * @param aExpr
   *        the expression to be deleted. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public AbstractJSBlock delete (@Nonnull final IJSExpression aExpr)
  {
    addStatement (new JSDelete (aExpr));
    return this;
  }

  /**
   * Create a throw statement and add it to this block
   *
   * @param aExpr
   *        Throwing expression
   * @return this
   */
  @Nonnull
  public AbstractJSBlock _throw (@Nonnull final IJSExpression aExpr)
  {
    addStatement (new JSThrow (aExpr));
    return this;
  }

  /**
   * @return this
   */
  @Nonnull
  public AbstractJSBlock debugger ()
  {
    addStatement (new JSDebugger ());
    return this;
  }

  /**
   * Create a label, which can be referenced from <code>continue</code> and
   * <code>break</code> statements.
   *
   * @param sName
   *        name
   * @return this
   */
  @Nonnull
  public JSLabel label (@Nonnull @Nonempty final String sName)
  {
    return addStatement (new JSLabel (sName));
  }

  /**
   * Create an If statement and add it to this block
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   * @return Newly generated conditional statement
   */
  @Nonnull
  public JSConditional _if (@Nonnull final IJSExpression aTest)
  {
    return addStatement (new JSConditional (aTest));
  }

  /**
   * Create a return statement and add it to this block
   *
   * @return this
   */
  @Nonnull
  public AbstractJSBlock _return ()
  {
    return _return ((IJSExpression) null);
  }

  @Nonnull
  public AbstractJSBlock _return (final boolean bValue)
  {
    return _return (JSExpr.lit (bValue));
  }

  @Nonnull
  public AbstractJSBlock _return (final char cValue)
  {
    return _return (JSExpr.lit (cValue));
  }

  @Nonnull
  public AbstractJSBlock _return (final double dValue)
  {
    return _return (JSExpr.lit (dValue));
  }

  @Nonnull
  public AbstractJSBlock _return (final float fValue)
  {
    return _return (JSExpr.lit (fValue));
  }

  @Nonnull
  public AbstractJSBlock _return (final int nValue)
  {
    return _return (JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock _return (final long nValue)
  {
    return _return (JSExpr.lit (nValue));
  }

  @Nonnull
  public AbstractJSBlock _return (@Nullable final String sValue)
  {
    return _return (sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @Nonnull
  public AbstractJSBlock _return (@Nullable final IJson aValue)
  {
    return _return (aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  /**
   * Create a return statement and add it to this block
   *
   * @param aExpr
   *        Expression to return
   * @return this
   */
  @Nonnull
  public AbstractJSBlock _return (@Nullable final IJSExpression aExpr)
  {
    addStatement (new JSReturn (aExpr));
    return this;
  }

  /**
   * Create a sub-block and add it to this block
   *
   * @return The newly created block
   */
  @Nonnull
  public JSBlock block ()
  {
    return addStatement (new JSBlock (false, false));
  }

  @Nonnull
  public AbstractJSBlock comment (@Nonnull final String sComment)
  {
    addStatement (new JSCommentSingleLine (sComment));
    return this;
  }

  @Nonnull
  public AbstractJSBlock add (@Nonnull final IJSCodeProvider aJSCode)
  {
    if (aJSCode instanceof JSPackage)
    {
      // Avoid nested JSPackage
      for (final IJSCodeProvider aNestedJSCode : ((JSPackage) aJSCode).members ())
        add (aNestedJSCode);
    }
    else
      if (aJSCode instanceof CollectingJSCodeProvider)
      {
        // Flatten CollectingJSCodeProvider
        for (final IJSCodeProvider aNestedJSCode : ((CollectingJSCodeProvider) aJSCode).getAll ())
          add (aNestedJSCode);
      }
      else
      {
        if (GlobalDebug.isDebugMode ())
          if (!(aJSCode instanceof IJSCodeProviderWithSettings))
            s_aLogger.warn ("Adding untyped IJSCodeProvider of class " +
                            aJSCode.getClass ().getName () +
                            " to " +
                            CGStringHelper.getClassLocalName (this));

        m_aObjs.add (m_nPos, aJSCode);
        m_nPos++;
      }
    return this;
  }

  @Nonnull
  public AbstractJSBlock add (@Nonnull final IJSStatement aStatement)
  {
    addStatement (aStatement);
    return this;
  }

  @Nonnull
  public final String getJSCode ()
  {
    return getJSCode ((IJSWriterSettings) null);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractJSBlock rhs = (AbstractJSBlock) o;
    return m_aObjs.equals (rhs.m_aObjs) && m_aDecls.equals (rhs.m_aDecls) && m_nPos == rhs.m_nPos;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aObjs).append (m_aDecls).append (m_nPos).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotEmpty ("objs", m_aObjs)
                                       .appendIfNotEmpty ("decls", m_aDecls)
                                       .append ("pos", m_nPos)
                                       .toString ();
  }
}
