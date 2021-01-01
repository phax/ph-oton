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
package com.helger.html.js;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.system.ENewLineMode;

/**
 * Read only interface with all JS writer settings.
 *
 * @author Philip Helger
 */
public interface IJSWriterSettings extends Serializable
{
  /**
   * @return <code>true</code> if the code should be indented and aligned.
   */
  boolean isIndentAndAlign ();

  /**
   * @return <code>true</code> if comments should be generated at all.
   */
  boolean isGenerateComments ();

  /**
   * @return The string used for indenting a single level. By default it is 2
   *         spaces.
   */
  @Nonnull
  @Nonempty
  String getIndent ();

  /**
   * @return The newline mode to be used. By default it is the platform specific
   *         new line mode. Never <code>null</code>.
   */
  @Nonnull
  ENewLineMode getNewLineMode ();

  /**
   * @return The string representing the new line mode. A shortcut for <code>
   *         {@link #getNewLineMode()}.getText()</code>.
   */
  @Nonnull
  @Nonempty
  String getNewLineString ();
}
