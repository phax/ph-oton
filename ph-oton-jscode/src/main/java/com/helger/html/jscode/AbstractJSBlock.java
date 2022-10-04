/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.math.MathHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.json.IJson;

/**
 * A JS block. It contains a list of statements and declarations.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Real implementation type
 */
@CodingStyleguideUnaware
public abstract class AbstractJSBlock <IMPLTYPE extends AbstractJSBlock <IMPLTYPE>> implements
                                      IJSFunctionContainer,
                                      IGenericImplTrait <IMPLTYPE>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractJSBlock.class);

  /**
   * List of the content of this block
   */
  private final ICommonsList <IHasJSCode> m_aObjs = new CommonsArrayList <> ();

  /**
   * Named map of all declarations
   */
  private final ICommonsMap <String, IJSDeclaration> m_aDecls = new CommonsHashMap <> ();

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
  @ReturnsMutableObject
  public ICommonsMap <String, IJSDeclaration> directDeclarations ()
  {
    return m_aDecls;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IJSDeclaration> getAllDeclarations ()
  {
    return m_aDecls.copyOfValues ();
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
  @ReturnsMutableObject ("speed")
  final ICommonsList <IHasJSCode> directMembers ()
  {
    return m_aObjs;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IHasJSCode> getAllMembers ()
  {
    return m_aObjs.getClone ();
  }

  /**
   * Remove all contents of the block. Sets the position to 0.
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE clear ()
  {
    m_aObjs.clear ();
    m_aDecls.clear ();
    m_nPos = 0;
    return thisAsT ();
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

  /**
   * Add a new declaration
   *
   * @param <T>
   *        The type of the declaration
   * @param aDeclaration
   *        The declaration to add
   * @return The parameter value
   * @throws JSNameAlreadyExistsException
   *         If another declaration with the same name is already present
   */
  @Nonnull
  public final <T extends IJSDeclaration> T addDeclaration (@Nonnull final T aDeclaration)
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
  public JSDefinedClass _class (@Nonnull @Nonempty final String sName)
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

  /**
   * Add a function to this package.
   *
   * @param sName
   *        Name of function to be added to this package
   * @return Newly generated function
   * @exception JSNameAlreadyExistsException
   *            When the specified class/interface was already created.
   */
  @Nonnull
  public JSFunction function (@Nonnull final String sName)
  {
    final JSFunction aFunction = new JSFunction (sName);
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
   * @deprecated Since 8.4.3; Use {@link #variable(String)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName)
  {
    return variable (sName);
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
  public JSVar variable (@Nonnull @Nonempty final String sName)
  {
    return variable (sName, (IJSExpression) null);
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,boolean)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final boolean bInitValue)
  {
    return variable (sName, bInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final boolean bInitValue)
  {
    return variable (sName, JSExpr.lit (bInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,char)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final char cInitValue)
  {
    return variable (sName, cInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final char cInitValue)
  {
    return variable (sName, JSExpr.lit (cInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,double)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final double dInitValue)
  {
    return variable (sName, dInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final double dInitValue)
  {
    return variable (sName, JSExpr.lit (dInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,float)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final float fInitValue)
  {
    return variable (sName, fInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final float fInitValue)
  {
    return variable (sName, JSExpr.lit (fInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,int)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final int nInitValue)
  {
    return variable (sName, nInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final int nInitValue)
  {
    return variable (sName, JSExpr.lit (nInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,long)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, final long nInitValue)
  {
    return variable (sName, nInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, final long nInitValue)
  {
    return variable (sName, JSExpr.lit (nInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,String)} instead
   */
  @Deprecated
  @Nonnull
  public JSVar var (@Nonnull @Nonempty final String sName, @Nullable final String sInitValue)
  {
    return variable (sName, sInitValue);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, @Nullable final String sInitValue)
  {
    return variable (sName, sInitValue == null ? JSExpr.NULL : JSExpr.lit (sInitValue));
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
   * @deprecated Since 8.4.3; Use {@link #variable(String,IJSExpression)}
   *             instead
   */
  @Nonnull
  @Deprecated
  public JSVar var (@Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInitExpression)
  {
    return variable (sName, aInitExpression);
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
  public JSVar variable (@Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInitExpression)
  {
    final JSVar aVar = new JSVar (sName, aInitExpression);
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
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final boolean bValue)
  {
    return assign (aLhs, JSExpr.lit (bValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final char cValue)
  {
    return assign (aLhs, JSExpr.lit (cValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    return assign (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    return assign (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    return assign (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    return assign (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final String sValue)
  {
    return assign (aLhs, sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, @Nullable final IJson aValue)
  {
    return assign (aLhs, aValue == null ? JSExpr.NULL : JSExpr.json (aValue));
  }

  @Nonnull
  public IMPLTYPE assign (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (aLhs.assign (aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final char cValue)
  {
    return assignPlus (aLhs, JSExpr.lit (cValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No add with 0
    if (EqualsHelper.equals (dValue, 0))
      return thisAsT ();
    if (dValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (dValue)));
    return assignPlus (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No add with 0
    if (EqualsHelper.equals (fValue, 0))
      return thisAsT ();
    if (fValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (fValue)));
    return assignPlus (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No add with 0
    if (EqualsHelper.equals (nValue, 0))
      return thisAsT ();
    if (nValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (nValue)));
    return assignPlus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No add with 0
    if (EqualsHelper.equals (nValue, 0))
      return thisAsT ();
    if (nValue < 0)
      return assignMinus (aLhs, JSExpr.lit (MathHelper.abs (nValue)));
    return assignPlus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final String sValue)
  {
    return assignPlus (aLhs, JSExpr.lit (sValue));
  }

  @Nonnull
  public IMPLTYPE assignPlus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignPlus (aLhs, aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No subtract with 0
    if (EqualsHelper.equals (dValue, 0))
      return thisAsT ();
    return assignMinus (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No subtract with 0
    if (EqualsHelper.equals (fValue, 0))
      return thisAsT ();
    return assignMinus (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No subtract with 0
    if (EqualsHelper.equals (nValue, 0))
      return thisAsT ();
    return assignMinus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignMinus (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No subtract with 0
    if (EqualsHelper.equals (nValue, 0))
      return thisAsT ();
    return assignMinus (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignMinus (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignMinus (aLhs, aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No multiply with 1
    if (EqualsHelper.equals (dValue, 1))
      return thisAsT ();
    return assignMultiply (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No multiply with 1
    if (EqualsHelper.equals (fValue, 1))
      return thisAsT ();
    return assignMultiply (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No multiply with 1
    if (EqualsHelper.equals (nValue, 1))
      return thisAsT ();
    return assignMultiply (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No multiply with 1
    if (EqualsHelper.equals (nValue, 1))
      return thisAsT ();
    return assignMultiply (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignMultiply (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignMultiply (aLhs, aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final double dValue)
  {
    // No divide by 1
    if (EqualsHelper.equals (dValue, 1))
      return thisAsT ();
    return assignDivide (aLhs, JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final float fValue)
  {
    // No divide by 1
    if (EqualsHelper.equals (fValue, 1))
      return thisAsT ();
    return assignDivide (aLhs, JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    // No divide by 1
    if (EqualsHelper.equals (nValue, 1))
      return thisAsT ();
    return assignDivide (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignDivide (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    // No divide by 1
    if (EqualsHelper.equals (nValue, 1))
      return thisAsT ();
    return assignDivide (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignDivide (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignDivide (aLhs, aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE assignModulo (@Nonnull final IJSAssignmentTarget aLhs, final int nValue)
  {
    return assignModulo (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignModulo (@Nonnull final IJSAssignmentTarget aLhs, final long nValue)
  {
    return assignModulo (aLhs, JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE assignModulo (@Nonnull final IJSAssignmentTarget aLhs, @Nonnull final IJSExpression aExpr)
  {
    addStatement (JSExpr.assignModulo (aLhs, aExpr));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE incrPostfix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSIncrPostfix (aLhs));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE incrPrefix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSIncrPrefix (aLhs));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE decrPostfix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSDecrPostfix (aLhs));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE decrPrefix (@Nonnull final IJSAssignmentTarget aLhs)
  {
    addStatement (new JSDecrPrefix (aLhs));
    return thisAsT ();
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
    return addStatement (new JSForIn (sVarName, aCollection));
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
  public IMPLTYPE delete (@Nonnull final IJSExpression aExpr)
  {
    addStatement (new JSDelete (aExpr));
    return thisAsT ();
  }

  /**
   * Create a throw statement and add it to this block
   *
   * @param aExpr
   *        Throwing expression
   * @return this
   */
  @Nonnull
  public IMPLTYPE _throw (@Nonnull final IJSExpression aExpr)
  {
    addStatement (new JSThrow (aExpr));
    return thisAsT ();
  }

  /**
   * @return this
   */
  @Nonnull
  public IMPLTYPE debugger ()
  {
    addStatement (new JSDebugger ());
    return thisAsT ();
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
   * Create an If statement and add it to this block
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   * @param aThen
   *        "then" block content. May be <code>null</code>.
   * @return Newly generated conditional statement
   */
  @Nonnull
  public JSConditional _if (@Nonnull final IJSExpression aTest, @Nullable final IHasJSCode aThen)
  {
    return addStatement (new JSConditional (aTest, aThen));
  }

  /**
   * Create an If statement and add it to this block
   *
   * @param aTest
   *        {@link IJSExpression} to be tested to determine branching
   * @param aThen
   *        "then" block content. May be <code>null</code>.
   * @param aElse
   *        "else" block content. May be <code>null</code>.
   * @return Newly generated conditional statement
   */
  @Nonnull
  public JSConditional _if (@Nonnull final IJSExpression aTest,
                            @Nullable final IHasJSCode aThen,
                            @Nullable final IHasJSCode aElse)
  {
    return addStatement (new JSConditional (aTest, aThen, aElse));
  }

  /**
   * Create a return statement and add it to this block
   *
   * @return this
   */
  @Nonnull
  public IMPLTYPE _return ()
  {
    return _return ((IJSExpression) null);
  }

  @Nonnull
  public IMPLTYPE _return (final boolean bValue)
  {
    return _return (JSExpr.lit (bValue));
  }

  @Nonnull
  public IMPLTYPE _return (final char cValue)
  {
    return _return (JSExpr.lit (cValue));
  }

  @Nonnull
  public IMPLTYPE _return (final double dValue)
  {
    return _return (JSExpr.lit (dValue));
  }

  @Nonnull
  public IMPLTYPE _return (final float fValue)
  {
    return _return (JSExpr.lit (fValue));
  }

  @Nonnull
  public IMPLTYPE _return (final int nValue)
  {
    return _return (JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE _return (final long nValue)
  {
    return _return (JSExpr.lit (nValue));
  }

  @Nonnull
  public IMPLTYPE _return (@Nullable final String sValue)
  {
    return _return (sValue == null ? JSExpr.NULL : JSExpr.lit (sValue));
  }

  @Nonnull
  public IMPLTYPE _return (@Nullable final IJson aValue)
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
  public IMPLTYPE _return (@Nullable final IJSExpression aExpr)
  {
    addStatement (new JSReturn (aExpr));
    return thisAsT ();
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
  public IMPLTYPE comment (@Nonnull final String sComment)
  {
    addStatement (new JSCommentSingleLine (sComment));
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE add (@Nonnull final IHasJSCode aJSCode)
  {
    if (aJSCode instanceof JSPackage)
    {
      // Avoid nested JSPackage
      for (final IHasJSCode aNestedJSCode : ((JSPackage) aJSCode).directMembers ())
        add (aNestedJSCode);
    }
    else
      if (aJSCode instanceof CollectingJSCodeProvider)
      {
        // Flatten CollectingJSCodeProvider
        for (final IHasJSCode aNestedJSCode : ((CollectingJSCodeProvider) aJSCode).directAll ())
          add (aNestedJSCode);
      }
      else
      {
        if (GlobalDebug.isDebugMode ())
          if (!(aJSCode instanceof IHasJSCodeWithSettings))
            LOGGER.warn ("Adding unspecified IHasJSCode of type " +
                         aJSCode.getClass ().getName () +
                         " to " +
                         ClassHelper.getClassLocalName (this));

        m_aObjs.add (m_nPos, aJSCode);
        m_nPos++;
      }
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE add (@Nonnull final IJSStatement aStatement)
  {
    addStatement (aStatement);
    return thisAsT ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractJSBlock <?> rhs = (AbstractJSBlock <?>) o;
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
    return new ToStringGenerator (this).appendIf ("objs", m_aObjs, CollectionHelper::isNotEmpty)
                                       .appendIf ("decls", m_aDecls, CollectionHelper::isNotEmpty)
                                       .append ("pos", m_nPos)
                                       .getToString ();
  }
}
