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
package com.helger.html.css;

import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;

/**
 * Interface for a CSS class provider.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface ICSSClassProvider
{
  /**
   * @return The desired CSS class. May be <code>null</code> to indicate no
   *         class.
   */
  @Nullable
  String getCSSClass ();
}
