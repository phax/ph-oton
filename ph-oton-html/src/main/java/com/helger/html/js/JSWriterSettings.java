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
package com.helger.html.js;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.system.ENewLineMode;
import com.helger.base.tostring.ToStringGenerator;

/**
 * Settings for the textual representation of JSDOM objects
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class JSWriterSettings implements IJSWriterSettings, ICloneable <JSWriterSettings>
{
  private boolean m_bIndentAndAlign;
  private boolean m_bGenerateComments;
  private String m_sIndent;
  private ENewLineMode m_eNewLineMode;

  public JSWriterSettings ()
  {
    m_bIndentAndAlign = JSWriterDefaultSettings.isIndentAndAlign ();
    m_bGenerateComments = JSWriterDefaultSettings.isGenerateComments ();
    m_sIndent = JSWriterDefaultSettings.getIndent ();
    m_eNewLineMode = JSWriterDefaultSettings.getNewLineMode ();
  }

  public JSWriterSettings (@NonNull final IJSWriterSettings aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_bIndentAndAlign = aOther.isIndentAndAlign ();
    m_bGenerateComments = aOther.isGenerateComments ();
    m_sIndent = aOther.getIndent ();
    m_eNewLineMode = aOther.getNewLineMode ();
  }

  public boolean isIndentAndAlign ()
  {
    return m_bIndentAndAlign;
  }

  @NonNull
  public JSWriterSettings setIndentAndAlign (final boolean bIndentAndAlign)
  {
    m_bIndentAndAlign = bIndentAndAlign;
    return this;
  }

  public boolean isGenerateComments ()
  {
    return m_bGenerateComments;
  }

  @NonNull
  public JSWriterSettings setGenerateComments (final boolean bGenerateComments)
  {
    m_bGenerateComments = bGenerateComments;
    return this;
  }

  /**
   * This is a wrapper around {@link #setIndentAndAlign(boolean)}, { and
   * {@link #setGenerateComments(boolean)}
   *
   * @param bMinimumCodeSize
   *        true for minimum code size
   * @return this
   */
  @NonNull
  public JSWriterSettings setMinimumCodeSize (final boolean bMinimumCodeSize)
  {
    setIndentAndAlign (!bMinimumCodeSize);
    setGenerateComments (!bMinimumCodeSize);
    return this;
  }

  @NonNull
  @Nonempty
  public String getIndent ()
  {
    return m_sIndent;
  }

  @NonNull
  public JSWriterSettings setIndent (@NonNull @Nonempty final String sIndent)
  {
    m_sIndent = ValueEnforcer.notEmpty (sIndent, "Indent");
    return this;
  }

  @NonNull
  public JSWriterSettings setNewLineMode (@NonNull final ENewLineMode eNewLineMode)
  {
    m_eNewLineMode = ValueEnforcer.notNull (eNewLineMode, "NewLineMode");
    return this;
  }

  @NonNull
  public ENewLineMode getNewLineMode ()
  {
    return m_eNewLineMode;
  }

  @NonNull
  @Nonempty
  public String getNewLineString ()
  {
    return m_eNewLineMode.getText ();
  }

  @NonNull
  @ReturnsMutableCopy
  public JSWriterSettings getClone ()
  {
    return new JSWriterSettings (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("IndentAndAlign", m_bIndentAndAlign)
                                       .append ("GenerateComments", m_bGenerateComments)
                                       .append ("Indent", m_sIndent)
                                       .append ("NewLineMode", m_eNewLineMode)
                                       .getToString ();
  }

  @NonNull
  @ReturnsMutableCopy
  public static JSWriterSettings createCloneOnDemand (@Nullable final IJSWriterSettings aSettings)
  {
    return aSettings == null ? new JSWriterSettings () : new JSWriterSettings (aSettings);
  }
}
