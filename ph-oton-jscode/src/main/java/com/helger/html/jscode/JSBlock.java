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

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;

/**
 * A block of JS code, which may contain statements and local declarations.
 * <p>
 * {@link JSBlock} contains a large number of factory methods that creates new
 * statements/declarations. Those newly created statements/declarations are
 * inserted into the {@link #pos() "current position"}. The position advances
 * one every time you add a new instruction.
 *
 * @author Philip Helger
 */
public class JSBlock extends AbstractJSBlock <JSBlock> implements IJSGeneratable, IJSStatement
{
  public static final boolean DEFAULT_BRACES_REQUIRED = true;
  public static final boolean DEFAULT_INDENT_REQUIRED = true;
  public static final boolean DEFAULT_NEWLINE_AT_END = true;

  /**
   * Whether or not this block must be braced and indented
   */
  private boolean m_bBracesRequired;
  private boolean m_bIndentRequired;
  private boolean m_bNewLineAtEnd = DEFAULT_NEWLINE_AT_END;

  public JSBlock ()
  {
    this (DEFAULT_BRACES_REQUIRED, DEFAULT_INDENT_REQUIRED);
  }

  public JSBlock (final boolean bBracesRequired, final boolean bIndentRequired)
  {
    m_bBracesRequired = bBracesRequired;
    m_bIndentRequired = bIndentRequired;
  }

  public boolean bracesRequired ()
  {
    return m_bBracesRequired;
  }

  public boolean indentRequired ()
  {
    return m_bIndentRequired;
  }

  public boolean newlineAtEnd ()
  {
    return m_bNewLineAtEnd;
  }

  /**
   * Determine whether a newline should be printed at the end of the block. This
   * is only set to false for anonymous functions
   *
   * @param bNewLineAtEnd
   *        <code>true</code> to enable newline at the end
   * @return this
   */
  @Nonnull
  public JSBlock newlineAtEnd (final boolean bNewLineAtEnd)
  {
    m_bNewLineAtEnd = bNewLineAtEnd;
    return this;
  }

  @Override
  protected void onAddDeclaration (@Nonnull final IJSDeclaration aDeclaration)
  {
    if (aDeclaration instanceof JSVar)
    {
      m_bBracesRequired = true;
      m_bIndentRequired = true;
    }
  }

  /**
   * Create a break statement and add it to this block
   *
   * @return Created break block
   */
  @Nonnull
  @CodingStyleguideUnaware
  public JSBlock _break ()
  {
    return _break (null);
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSBlock _break (@Nullable final JSLabel aLabel)
  {
    addStatement (new JSBreak (aLabel));
    return this;
  }

  @Nonnull
  @CodingStyleguideUnaware
  public JSBlock _continue ()
  {
    return _continue (null);
  }

  /**
   * Create a continue statement and add it to this block
   *
   * @param aLabel
   *        optional label to be used
   * @return Created continue block
   */
  @Nonnull
  @CodingStyleguideUnaware
  public JSBlock _continue (@Nullable final JSLabel aLabel)
  {
    addStatement (new JSContinue (aLabel));
    return this;
  }

  public void generate (@Nonnull final JSFormatter f)
  {
    if (m_bBracesRequired)
      f.plain ('{').nl ();
    if (m_bIndentRequired)
      f.indent ();
    generateBody (f);
    if (m_bIndentRequired)
      f.outdent ();
    if (m_bBracesRequired)
      f.plain ('}');
  }

  void generateBody (@Nonnull final JSFormatter aFormatter)
  {
    for (final IHasJSCode aJSCode : directMembers ())
    {
      if (aJSCode instanceof IJSDeclaration)
        aFormatter.decl ((IJSDeclaration) aJSCode);
      else
        if (aJSCode instanceof IJSStatement)
          aFormatter.stmt ((IJSStatement) aJSCode);
        else
          if (aJSCode instanceof IHasJSCodeWithSettings)
            aFormatter.plain (((IHasJSCodeWithSettings) aJSCode).getJSCode (aFormatter.getSettings ()));
          else
            aFormatter.plain (aJSCode.getJSCode ());
    }
  }

  public void state (@Nonnull final JSFormatter aFormatter)
  {
    aFormatter.generatable (this);
    if (m_bBracesRequired && m_bNewLineAtEnd)
      aFormatter.nl ();
  }

  @Nullable
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, (IJSGeneratable) this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final JSBlock rhs = (JSBlock) o;
    return m_bBracesRequired == rhs.m_bBracesRequired &&
           m_bIndentRequired == rhs.m_bIndentRequired &&
           m_bNewLineAtEnd == rhs.m_bNewLineAtEnd;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_bBracesRequired)
                            .append (m_bIndentRequired)
                            .append (m_bNewLineAtEnd)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("bracesRequired", m_bBracesRequired)
                            .append ("identRequired", m_bIndentRequired)
                            .append ("newLineAtEnd", m_bNewLineAtEnd)
                            .getToString ();
  }
}
