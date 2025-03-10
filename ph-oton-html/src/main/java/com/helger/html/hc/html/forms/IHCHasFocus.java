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
package com.helger.html.hc.html.forms;

import javax.annotation.Nonnull;

/**
 * Base interface for focusable objects. Must not necessarily be an IHCElement!
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCHasFocus <IMPLTYPE extends IHCHasFocus <IMPLTYPE>>
{
  /**
   * @return <code>true</code> if this element is focused, <code>false</code>
   *         otherwise. By default an element is not focused.
   */
  boolean isAutoFocus ();

  /**
   * Change the auto focused state of this element.
   *
   * @param bAutoFocus
   *        <code>true</code> to auto focus this element, <code>false</code> to
   *        not focus the element.
   * @return this
   */
  @Nonnull
  IMPLTYPE setAutoFocus (boolean bAutoFocus);
}
