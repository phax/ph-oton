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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSMarshaller;

/**
 * A generated JS class.
 *
 * @author Philip Helger
 */
public class JSDefinedClass extends AbstractJSClass implements IJSDeclaration, IJSDocCommentable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JSDefinedClass.class);

  /** class JSDoc */
  private JSCommentMultiLine m_aJSDoc;

  /** Name of this class. */
  private final String m_sName;

  /** Name of the super class of this class. */
  private AbstractJSClass m_aSuperClass;

  /** Fields keyed by their names. */
  private final ICommonsOrderedMap <String, JSFieldVar> m_aFields = new CommonsLinkedHashMap <> ();

  /** Constructors for this class */
  private JSConstructor m_aConstructor;

  /** Set of methods that are members of this class */
  private final ICommonsList <JSMethod> m_aMethods = new CommonsArrayList <> ();

  /**
   * constructor
   *
   * @param sName
   *        Name of this class
   */
  public JSDefinedClass (@Nonnull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    if (!Character.isUpperCase (sName.charAt (0)))
      LOGGER.warn ("Class names should always start with an upper-case character: " + sName);
    m_sName = sName;
  }

  /**
   * This class extends the specified class.
   *
   * @param aSuperClass
   *        Superclass for this class
   * @return This class
   */
  @Nonnull
  @CodingStyleguideUnaware
  public JSDefinedClass _extends (@Nonnull final AbstractJSClass aSuperClass)
  {
    m_aSuperClass = ValueEnforcer.notNull (aSuperClass, "SuperClass");
    return this;
  }

  /**
   * Returns the class extended by this class.
   */
  @Override
  @Nullable
  @CodingStyleguideUnaware
  public AbstractJSClass _extends ()
  {
    return m_aSuperClass;
  }

  /**
   * Class name accessor. For example, for <code>java.util.List</code>, this
   * method returns <code>"List"</code>
   *
   * @return Name of this class
   */
  @Override
  @Nonnull
  @Nonempty
  public String name ()
  {
    return m_sName;
  }

  /**
   * Adds a field to the list of field members of this defined class.
   *
   * @param sName
   *        Name of this field
   * @return Newly generated field
   */
  @Nonnull
  public JSFieldVar field (@Nonnull @Nonempty final String sName)
  {
    return field (sName, null);
  }

  /**
   * Adds a field to the list of field members of this defined class.
   *
   * @param sName
   *        Name of this field.
   * @param aInit
   *        Initial value of this field.
   * @return Newly generated field
   */
  @Nonnull
  public JSFieldVar field (@Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInit)
  {
    final JSFieldVar aField = new JSFieldVar (this, sName, aInit);
    return addField (aField);
  }

  @Nullable
  public JSFieldVar getFieldOfName (@Nullable final String sName)
  {
    return m_aFields.get (sName);
  }

  /**
   * Add a new field
   *
   * @param aField
   *        The field to add. May not be <code>null</code>.
   * @return The parameter value
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  public JSFieldVar addField (@Nonnull final JSFieldVar aField)
  {
    ValueEnforcer.notNull (aField, "Field");

    final String sName = aField.name ();
    final JSFieldVar aOldField = getFieldOfName (sName);
    if (aOldField != null)
      throw new JSNameAlreadyExistsException (aOldField);

    m_aFields.put (sName, aField);
    return aField;
  }

  /**
   * @return A copy of all declared fields in this class
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, JSFieldVar> fields ()
  {
    return m_aFields.getClone ();
  }

  public boolean containsField (@Nullable final String sName)
  {
    return m_aFields.containsKey (sName);
  }

  /**
   * Removes a {@link JSFieldVar} from this class.
   *
   * @param aField
   *        Field to be removed
   * @return this
   * @throws IllegalArgumentException
   *         if the given field is not a field on this class.
   */
  @Nonnull
  public JSDefinedClass removeField (@Nonnull final JSFieldVar aField)
  {
    ValueEnforcer.notNull (aField, "Field");

    if (m_aFields.remove (aField.name ()) != aField)
      throw new IllegalArgumentException ("Failed to remove field '" + aField.name () + "' from " + m_aFields.keySet ());
    return this;
  }

  @Nonnull
  public JSDefinedClass removeAllFields ()
  {
    m_aFields.clear ();
    return this;
  }

  /**
   * Adds a constructor to this class.
   *
   * @return The constructor object to use. Never <code>null</code>.
   */
  @Nonnull
  public JSConstructor constructor ()
  {
    if (m_aConstructor == null)
      m_aConstructor = new JSConstructor (this);
    return m_aConstructor;
  }

  /**
   * Add a method to the list of method members of this JS class instance.
   *
   * @param sName
   *        Name of the method
   * @return Newly generated method
   */
  @Nonnull
  public JSMethod method (@Nonnull @Nonempty final String sName)
  {
    final JSMethod aMethod = new JSMethod (this, sName);
    m_aMethods.add (aMethod);
    return aMethod;
  }

  /**
   * @return the set of methods defined in this class.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <JSMethod> methods ()
  {
    return m_aMethods.getClone ();
  }

  /**
   * Creates, if necessary, and returns the class JSDoc for this defined class
   *
   * @return {@link JSCommentMultiLine} containing JSDoc for this class
   */
  @Nonnull
  public JSCommentMultiLine jsDoc ()
  {
    if (m_aJSDoc == null)
      m_aJSDoc = new JSCommentMultiLine ();
    return m_aJSDoc;
  }

  @Nonnull
  public JSFieldRef prototype ()
  {
    return staticRef ("prototype");
  }

  public void declare (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aJSDoc != null)
      aFormatter.nl ().generatable (m_aJSDoc);

    // Emit the constructor first (a function)
    aFormatter.decl (constructor ());

    final JSAssocArray aPrototypefields = new JSAssocArray ();

    // Add all fields
    for (final JSFieldVar aField : m_aFields.values ())
      aPrototypefields.add (aField.name (), aField.hasInit () ? aField.init () : JSExpr.NULL);

    // Add all methods
    for (final JSMethod aMethod : m_aMethods)
      aPrototypefields.add (aMethod.name (), aMethod.getAsAnonymousFunction ());

    // Start with the prototype methods
    JSExpr.assign (prototype (), aPrototypefields).generate (aFormatter);
  }

  @Nullable
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, (IJSDeclaration) this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSDefinedClass rhs = (JSDefinedClass) o;
    return EqualsHelper.equals (m_aJSDoc, rhs.m_aJSDoc) &&
           m_sName.equals (rhs.m_sName) &&
           EqualsHelper.equals (m_aSuperClass, rhs.m_aSuperClass) &&
           m_aFields.equals (rhs.m_aFields) &&
           EqualsHelper.equals (m_aConstructor, rhs.m_aConstructor) &&
           m_aMethods.equals (rhs.m_aMethods);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_aJSDoc)
                            .append (m_sName)
                            .append (m_aSuperClass)
                            .append (m_aFields)
                            .append (m_aConstructor)
                            .append (m_aMethods)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("jsDoc", m_aJSDoc)
                            .append ("name", m_sName)
                            .appendIfNotNull ("superClass", m_aSuperClass)
                            .append ("fields", m_aFields)
                            .appendIfNotNull ("constructor", m_aConstructor)
                            .append ("methods", m_aMethods)
                            .getToString ();
  }
}
