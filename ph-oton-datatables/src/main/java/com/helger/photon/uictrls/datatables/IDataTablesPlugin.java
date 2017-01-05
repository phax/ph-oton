/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;

/**
 * Base interface for DataTables plugins.
 *
 * @author Philip Helger
 */
public interface IDataTablesPlugin
{
  /**
   * @return The name used as the key for the JS options.
   */
  @Nonnull
  @Nonempty
  String getName ();

  /**
   * Check if this plugin can be applied to the passed datatables.
   *
   * @param aDT
   *        DataTables to use. Never <code>null</code>.
   * @return <code>true</code> if it is applicable, <code>false</code> if not.
   */
  boolean canBeApplied (@Nonnull DataTables aDT);

  /**
   * Apply all necessary information to the owning DataTables object. This can
   * be used to e.g. wave stuff into the DOM.
   *
   * @param aDT
   *        DataTables to use. Never <code>null</code>.
   */
  void finalizeDataTablesSettings (@Nonnull DataTables aDT);

  /**
   * Add custom plugin JS initialization code.
   *
   * @param aDT
   *        DataTables to use. Never <code>null</code>.
   * @param aJSCode
   *        The JS package to append to. Never <code>null</code>.
   * @param aJSTable
   *        The reference to the DataTables JS object
   */
  void addInitJS (@Nonnull DataTables aDT, @Nonnull JSPackage aJSCode, @Nonnull JSVar aJSTable);

  /**
   * Register custom resources required for this plugin.
   *
   * @param aConversionSettings
   *        Conversion settings to use. Never <code>null</code>.
   */
  void registerExternalResources (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  /**
   * @return The initialization parameters to be added to the DT init
   *         JavaScript.
   */
  @Nullable
  IJSExpression getInitParams ();
}
