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
package com.helger.photon.uictrls.datatables.plugins;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.JSLet;
import com.helger.html.jscode.JSPackage;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.IDataTablesPlugin;

/**
 * Abstract implementation of {@link IDataTablesPlugin}.
 *
 * @author Philip Helger
 */
public abstract class AbstractDataTablesPlugin implements IDataTablesPlugin
{
  private final String m_sName;

  /**
   * @param sName
   *        Name of the plugin. May neither be <code>null</code> empty. This
   *        name is only internally used to make sure that no such
   */
  protected AbstractDataTablesPlugin (@NonNull @Nonempty final String sName)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
  }

  @NonNull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  public boolean canBeApplied (@NonNull final DataTables aDT)
  {
    return true;
  }

  public void finalizeDataTablesSettings (@NonNull final DataTables aDT)
  {
    // empty
  }

  public void addInitJS (@NonNull final DataTables aDT, @NonNull final JSPackage aJSCode, @NonNull final JSLet aJSTable)
  {
    // empty
  }

  public void registerExternalResources (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    // empty
  }
}
