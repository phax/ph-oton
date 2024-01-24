/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.tinymce4.type;

import javax.annotation.Nonnull;

import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSExpr;

/**
 * All TinyMCE4 resize options
 *
 * @author Philip Helger
 */
public enum ETinyMCE4Resize
{
  DISABLE (JSExpr.FALSE),
  VERTICALLY (JSExpr.TRUE),
  BOTH (JSExpr.lit ("both"));

  private final IJSExpression m_aExpr;

  ETinyMCE4Resize (@Nonnull final IJSExpression aExpr)
  {
    m_aExpr = aExpr;
  }

  @Nonnull
  public IJSExpression getValue ()
  {
    return m_aExpr;
  }
}
