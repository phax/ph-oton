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
package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.JSAssocArray;

public class DTPResponsiveBreakpoint
{
  private final String m_sName;
  private final String m_sWidth;

  public DTPResponsiveBreakpoint (@Nonnull @Nonempty final String sName, @Nonnull @Nonempty final String sWidth)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
    m_sWidth = ValueEnforcer.notEmpty (sWidth, "Width");
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public String getWidth ()
  {
    return m_sWidth;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    return new JSAssocArray ().add ("name", m_sName).add ("width", m_sWidth);
  }
}
