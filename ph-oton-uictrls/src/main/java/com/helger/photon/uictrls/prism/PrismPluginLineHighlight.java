/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.prism;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

public class PrismPluginLineHighlight implements IPrismPlugin
{
  private String m_sLine;

  public PrismPluginLineHighlight ()
  {}

  @NonNull
  public PrismPluginLineHighlight setLine (@Nullable final String s)
  {
    m_sLine = s;
    return this;
  }

  public void applyOnPre (@NonNull final IMicroElement aPreElement,
                          @NonNull final HCHasCSSClasses aPreClasses,
                          @NonNull final HCHasCSSStyles aPreStyles)
  {
    if (StringHelper.isNotEmpty (m_sLine))
      aPreElement.setAttribute ("data-line", m_sLine);
  }
}
