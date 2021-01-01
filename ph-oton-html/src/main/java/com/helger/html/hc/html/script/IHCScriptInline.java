/**
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
package com.helger.html.hc.html.script;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.config.EHCScriptInlineMode;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IJSWriterSettings;

/**
 * Interface for inline SCRIPTs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCScriptInline <IMPLTYPE extends IHCScriptInline <IMPLTYPE>> extends IHCScript <IMPLTYPE>
{
  @Nonnull
  IMPLTYPE setJSCodeProvider (@Nonnull IHasJSCode aProvider);

  /**
   * @return The JS code passed in the constructor. Never <code>null</code>.
   */
  @Nonnull
  IHasJSCode getJSCodeProvider ();

  /**
   * @param aSettings
   *        The settings to be used. May be <code>null</code> to use the
   *        default.
   * @return The text representation of the JS code passed in the constructor.
   *         May be <code>null</code>.
   */
  @Nullable
  String getJSCode (@Nonnull final IJSWriterSettings aSettings);

  /**
   * @return The masking mode. Never <code>null</code>.
   */
  @Nonnull
  EHCScriptInlineMode getMode ();

  /**
   * Set the masking mode.
   *
   * @param eMode
   *        The mode to use. MAy not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setMode (@Nonnull EHCScriptInlineMode eMode);

  boolean isEmitAfterFiles ();

  @Nonnull
  IMPLTYPE setEmitAfterFiles (boolean bEmitAfterFiles);
}
