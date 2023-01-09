/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.modal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;

/**
 * Modal dialog backdrop option
 *
 * @author Philip Helger
 */
public enum EBootstrapModalOptionBackdrop
{
  TRUE (JSExpr.TRUE),
  FALSE (JSExpr.FALSE),
  STATIC (JSExpr.lit ("static"));

  private final IJSExpression m_aJSExpr;

  EBootstrapModalOptionBackdrop (@Nullable final IJSExpression aJSExpr)
  {
    m_aJSExpr = aJSExpr;
  }

  @Nonnull
  public IJSExpression getJSExpression ()
  {
    return m_aJSExpr;
  }
}
