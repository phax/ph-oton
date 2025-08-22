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

import com.helger.annotation.Nonempty;

import jakarta.annotation.Nonnull;

/**
 * JS function parameter, without a variable type prefix.
 *
 * @author Philip Helger
 * @since 9.3.0
 */
public class JSParam extends AbstractJSVariable <JSParam>
{
  /**
   * Constructor
   *
   * @param sName
   *        name of the variable
   */
  public JSParam (@Nonnull @Nonempty final String sName)
  {
    super (EJSVarMode.PARAM, sName, null);
  }
}
