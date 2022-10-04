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

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillCloseWhenClosed;

import com.helger.commons.ValueEnforcer;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSWriterSettings;

/**
 * This is a utility class for managing indentation and other basic formatting
 * for {@link PrintWriter}.
 *
 * @author Philip Helger
 */
public class JSFormatter implements Closeable
{
  /**
   * Stream associated with this JFormatter
   */
  private final PrintWriter m_aPW;
  private final IJSWriterSettings m_aSettings;

  /**
   * Current number of indentation strings to print
   */
  private int m_nIndentLevel;
  private String m_sIndentCache = "";
  private boolean m_bAtBeginningOfLine = true;

  /**
   * Creates a formatter with default settings.
   *
   * @param aWriter
   *        Writer to use
   */
  public JSFormatter (@Nonnull @WillCloseWhenClosed final Writer aWriter)
  {
    this (aWriter, (IJSWriterSettings) null);
  }

  /**
   * Creates a formatter with default settings.
   *
   * @param aPrintWriter
   *        {@link PrintWriter} to be used
   */
  public JSFormatter (@Nonnull @WillCloseWhenClosed final PrintWriter aPrintWriter)
  {
    this (aPrintWriter, (IJSWriterSettings) null);
  }

  /**
   * Creates a formatter with default settings.
   *
   * @param aWriter
   *        Writer to use
   * @param aSettings
   *        The settings to be used.
   */
  public JSFormatter (@Nonnull @WillCloseWhenClosed final Writer aWriter, @Nullable final IJSWriterSettings aSettings)
  {
    this (aWriter instanceof PrintWriter ? (PrintWriter) aWriter : new PrintWriter (aWriter), aSettings);
  }

  /**
   * Creates a JFormatter.
   *
   * @param aPrintWriter
   *        PrintWriter to use.
   * @param aSettings
   *        The settings to be used.
   */
  public JSFormatter (@Nonnull @WillCloseWhenClosed final PrintWriter aPrintWriter,
                      @Nullable final IJSWriterSettings aSettings)
  {
    m_aPW = ValueEnforcer.notNull (aPrintWriter, "PrintWriter");
    m_aSettings = JSWriterSettings.createCloneOnDemand (aSettings);
  }

  @Nonnull
  public IJSWriterSettings getSettings ()
  {
    return m_aSettings;
  }

  /**
   * Closes this formatter.
   */
  public void close () throws IOException
  {
    m_aPW.close ();
  }

  /**
   * Decrement the indentation level if indent and align is active
   *
   * @return this
   */
  @Nonnull
  public JSFormatter outdent ()
  {
    if (m_aSettings.isIndentAndAlign ())
      outdentAlways ();
    return this;
  }

  /**
   * Decrement the indentation level.
   *
   * @return this
   */
  @Nonnull
  public JSFormatter outdentAlways ()
  {
    if (m_nIndentLevel == 0)
      throw new IllegalStateException ("Nothing left to outdent!");
    m_nIndentLevel--;
    m_sIndentCache = m_sIndentCache.substring (0, m_nIndentLevel * m_aSettings.getIndent ().length ());
    return this;
  }

  /**
   * Increment the indentation level if indent and align is active
   *
   * @return this
   */
  @Nonnull
  public JSFormatter indent ()
  {
    if (m_aSettings.isIndentAndAlign ())
      indentAlways ();
    return this;
  }

  /**
   * Increment the indentation level.
   *
   * @return this
   */
  @Nonnull
  public JSFormatter indentAlways ()
  {
    m_nIndentLevel++;
    m_sIndentCache += m_aSettings.getIndent ();
    return this;
  }

  private void _spaceIfNeeded ()
  {
    if (m_bAtBeginningOfLine)
    {
      if (m_nIndentLevel > 0)
        m_aPW.print (m_sIndentCache);
      m_bAtBeginningOfLine = false;
    }
  }

  /**
   * Print a char into the stream
   *
   * @param cChar
   *        the char
   * @return this
   */
  @Nonnull
  public JSFormatter plain (final char cChar)
  {
    _spaceIfNeeded ();
    m_aPW.print (cChar);
    return this;
  }

  /**
   * Print a {@link String} into the stream
   *
   * @param sText
   *        the {@link String}
   * @return this
   */
  @Nonnull
  public JSFormatter plain (@Nonnull final String sText)
  {
    _spaceIfNeeded ();
    m_aPW.print (sText);
    return this;
  }

  /**
   * Print a type name.
   *
   * @param aType
   *        The type whose name is to be printed
   * @return this
   */
  @Nonnull
  public JSFormatter type (@Nonnull final AbstractJSType aType)
  {
    plain (aType.name ());
    return this;
  }

  /**
   * Print a new line into the stream if indent and align is active
   *
   * @return this
   */
  @Nonnull
  public JSFormatter nl ()
  {
    if (m_aSettings.isIndentAndAlign ())
      nlFix ();
    return this;
  }

  /**
   * Print a new line into the stream
   *
   * @return this
   */
  @Nonnull
  public JSFormatter nlFix ()
  {
    m_aPW.print (m_aSettings.getNewLineString ());
    m_bAtBeginningOfLine = true;
    return this;
  }

  /**
   * Cause the {@link IJSGeneratable} object to generate source for itself
   *
   * @param aGeneratable
   *        the {@link IJSGeneratable} object
   * @return this
   */
  @Nonnull
  public JSFormatter generatable (@Nonnull final IJSGeneratable aGeneratable)
  {
    aGeneratable.generate (this);
    return this;
  }

  /**
   * Produces {@link IJSGeneratable}s separated by ','
   *
   * @param aCont
   *        contained of {@link IJSGeneratable}
   * @return this
   */
  @Nonnull
  public JSFormatter generatable (@Nonnull final Iterable <? extends IJSGeneratable> aCont)
  {
    boolean bFirst = true;
    for (final IJSGeneratable aItem : aCont)
    {
      if (bFirst)
        bFirst = false;
      else
        plain (',');
      generatable (aItem);
    }
    return this;
  }

  /**
   * Cause the {@link IJSDeclaration} to generate source for itself
   *
   * @param aDeclaration
   *        the {@link IJSDeclaration} object
   * @return this
   */
  @Nonnull
  public JSFormatter decl (@Nonnull final IJSDeclaration aDeclaration)
  {
    aDeclaration.declare (this);
    return this;
  }

  /**
   * Cause the {@link IJSStatement} to generate source for itself
   *
   * @param aStatement
   *        the {@link IJSStatement} object
   * @return this
   */
  @Nonnull
  public JSFormatter stmt (@Nonnull final IJSStatement aStatement)
  {
    aStatement.state (this);
    return this;
  }

  /**
   * Cause the {@link JSVar} to generate source for itself
   *
   * @param aVar
   *        the {@link JSVar} object
   * @return this
   * @deprecated Since 8.4.3; Use {@link #variable(JSVar)} instead.
   */
  @Nonnull
  @Deprecated
  public JSFormatter var (@Nonnull final JSVar aVar)
  {
    return variable (aVar);
  }

  /**
   * Cause the {@link JSVar} to generate source for itself
   *
   * @param aVar
   *        the {@link JSVar} object
   * @return this
   */
  @Nonnull
  public JSFormatter variable (@Nonnull final JSVar aVar)
  {
    aVar.bind (this);
    return this;
  }

  public void pkg (@Nonnull final JSPackage aPackage)
  {
    // for all declarations in the current package
    for (final IHasJSCode aObj : aPackage.directMembers ())
      if (aObj instanceof IJSDeclaration)
        decl ((IJSDeclaration) aObj);
      else
        if (aObj instanceof IJSStatement)
          stmt ((IJSStatement) aObj);
        else
          if (aObj instanceof JSPackage)
          {
            // Nested package
            pkg ((JSPackage) aObj);
          }
          else
            if (aObj instanceof IHasJSCodeWithSettings)
              plain (((IHasJSCodeWithSettings) aObj).getJSCode (m_aSettings));
            else
              plain (aObj.getJSCode ());
  }
}
