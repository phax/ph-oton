/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.uictrls.datatables;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.photon.core.app.context.ILayoutExecutionContext;

/**
 * Interface for bootstrap datatables configurator.
 *
 * @author Philip Helger
 */
public interface IBootstrapDataTablesConfigurator
{
  /**
   * Configure the default datatables
   *
   * @param aLEC
   *        Current layout execution context
   * @param aTable
   *        Source table
   * @param aDataTables
   *        The newly created datatables object
   */
  void configure (@Nonnull ILayoutExecutionContext aLEC,
                  @Nonnull IHCTable <?> aTable,
                  @Nonnull BootstrapDataTables aDataTables);
}
