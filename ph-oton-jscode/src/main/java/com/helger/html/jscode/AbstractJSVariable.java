/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.trait.IGenericImplTrait;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSMarshaller;

/**
 * Base class for "var", "let" and "const".
 *
 * @param <IMPLTYPE>
 *        Implementation type
 * @author Philip Helger
 * @since 9.3.0
 */
public abstract class AbstractJSVariable <IMPLTYPE extends AbstractJSVariable <IMPLTYPE>> extends
                                         AbstractJSAssignmentTarget implements
                                         IJSDeclaration,
                                         IGenericImplTrait <IMPLTYPE>
{
  public enum EJSVarMode
  {
    VAR ("var "),
    LET ("let "),
    CONST ("const "),
    PARAM ("");

    private final String m_sCode;

    EJSVarMode (@NonNull final String sCode)
    {
      m_sCode = sCode;
    }

    /**
     * @return The code for usage with "formatter.plain", including a trailing
     *         whitespace if it is non-empty.
     */
    @NonNull
    public String getCode ()
    {
      return m_sCode;
    }
  }

  private final EJSVarMode m_eMode;

  /**
   * Name of the variable
   */
  private String m_sName;

  /**
   * Initialization of the variable in its declaration
   */
  private IJSExpression m_aInit;

  /**
   * Constructor
   *
   * @param eMode
   *        Declaration mode. May not be <code>null</code>.
   * @param sName
   *        Name of this variable
   * @param aInit
   *        Value to initialize this variable to
   */
  protected AbstractJSVariable (@NonNull final EJSVarMode eMode,
                                @NonNull @Nonempty final String sName,
                                @Nullable final IJSExpression aInit)
  {
    ValueEnforcer.notNull (eMode, "Mode");
    ValueEnforcer.isTrue ( () -> JSMarshaller.isJSIdentifier (sName),
                           () -> "The name '" + sName + "' is not a legal JS identifier!");
    m_eMode = eMode;
    m_sName = sName;
    m_aInit = aInit;
  }

  /**
   * @return The JS variable mode. Never <code>null</code>.
   */
  @NonNull
  public final EJSVarMode getJSVarMode ()
  {
    return m_eMode;
  }

  /**
   * Get the name of this variable
   *
   * @return Name of the variable
   */
  @NonNull
  @Nonempty
  public final String name ()
  {
    return m_sName;
  }

  /**
   * Changes the name of this variable.
   *
   * @param sName
   *        New variable name
   * @return this
   */
  @NonNull
  public IMPLTYPE name (@NonNull @Nonempty final String sName)
  {
    if (!JSMarshaller.isJSIdentifier (sName))
      throw new IllegalArgumentException ("The name '" + sName + "' is not a legal JS identifier!");
    m_sName = sName;
    return thisAsT ();
  }

  /**
   * @return <code>true</code> if an init expression is present,
   *         <code>false</code> otherwise
   */
  public final boolean hasInit ()
  {
    return m_aInit != null;
  }

  /**
   * @return The init expression currently assigned
   */
  @Nullable
  public final IJSExpression init ()
  {
    return m_aInit;
  }

  /**
   * Initialize this variable
   *
   * @param aNewInit
   *        Expression to be used to initialize this field. May be
   *        <code>null</code>.
   * @return this
   */
  @NonNull
  public IMPLTYPE init (@Nullable final IJSExpression aNewInit)
  {
    m_aInit = aNewInit;
    return thisAsT ();
  }

  public void bind (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sName);
    if (m_aInit != null)
      aFormatter.plain ('=').generatable (m_aInit);
  }

  public void declare (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_eMode.getCode ());
    bind (aFormatter);
    aFormatter.plain (';').nl ();
  }

  public void generate (@NonNull final JSFormatter aFormatter)
  {
    aFormatter.plain (m_sName);
  }

  @Override
  @NonNull
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
    final AbstractJSVariable <?> rhs = (AbstractJSVariable <?>) o;
    return m_eMode.equals (rhs.m_eMode) &&
           EqualsHelper.equals (m_sName, rhs.m_sName) &&
           EqualsHelper.equals (m_aInit, rhs.m_aInit);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_eMode)
                            .append (m_sName)
                            .append (m_aInit)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Mode", m_eMode)
                            .append ("Name", m_sName)
                            .appendIfNotNull ("Init", m_aInit)
                            .getToString ();
  }
}
