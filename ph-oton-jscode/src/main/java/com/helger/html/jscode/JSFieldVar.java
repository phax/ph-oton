/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A field of a class
 *
 * @author Philip Helger
 */
public class JSFieldVar extends JSVar implements IJSDocCommentable
{
  private final JSDefinedClass m_aOwnerClass;

  /**
   * JSdoc comments for this field
   */
  private JSCommentMultiLine m_aJSDoc;

  /**
   * Field constructor
   *
   * @param aOwnerClass
   *        Owner class
   * @param sName
   *        Name of this variable
   * @param aInit
   *        Value to initialize this variable to
   */
  public JSFieldVar (@Nonnull final JSDefinedClass aOwnerClass, @Nonnull @Nonempty final String sName, @Nullable final IJSExpression aInit)
  {
    super (sName, aInit);
    m_aOwnerClass = ValueEnforcer.notNull (aOwnerClass, "OwnerClass");
  }

  @Nonnull
  public JSDefinedClass parentClass ()
  {
    return m_aOwnerClass;
  }

  /**
   * Change the name of this field
   *
   * @param sNewName
   *        The new name. May neither be <code>null</code> nor empty.
   * @return this for chaining
   * @throws JSNameAlreadyExistsException
   *         if the name is not unique
   */
  @Nonnull
  @Override
  public JSFieldVar name (@Nonnull @Nonempty final String sNewName)
  {
    final String sOldName = name ();
    if (!sOldName.equals (sNewName))
    {
      // make sure that the new name is available
      final JSFieldVar aExistingField = m_aOwnerClass.getFieldOfName (sNewName);
      if (aExistingField != null)
        throw new JSNameAlreadyExistsException (aExistingField);
      m_aOwnerClass.removeField (this);
      super.name (sNewName);
      m_aOwnerClass.addField (this);
    }
    return this;
  }

  /**
   * Creates, if necessary, and returns the class JSDoc for this field
   *
   * @return {@link JSCommentMultiLine} containing JSDoc for this field
   */
  @Nonnull
  public JSCommentMultiLine jsDoc ()
  {
    if (m_aJSDoc == null)
      m_aJSDoc = new JSCommentMultiLine ();
    return m_aJSDoc;
  }

  @Override
  public void declare (@Nonnull final JSFormatter aFormatter)
  {
    if (m_aJSDoc != null)
      aFormatter.generatable (m_aJSDoc);
    super.declare (aFormatter);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSFieldVar rhs = (JSFieldVar) o;
    return m_aOwnerClass.name ().equals (rhs.m_aOwnerClass.name ()) && EqualsHelper.equals (m_aJSDoc, rhs.m_aJSDoc);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aOwnerClass.name ()).append (m_aJSDoc).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("ownerClass", m_aOwnerClass)
                            .appendIfNotNull ("jsDoc", m_aJSDoc)
                            .getToString ();
  }
}
