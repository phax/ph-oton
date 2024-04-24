/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datatables;

import com.helger.photon.uictrls.datatables.DataTablesLayout;

/**
 * The data tables "layout" to be used for Bootstrap.
 *
 * @author Philip Helger
 */
public class BootstrapDataTablesLayout extends DataTablesLayout
{
  public BootstrapDataTablesLayout ()
  {
    // This is the default
    adder ().at (EPlace.TOP_START).set (AREA_PAGE_LENGTH);
    adder ().at (EPlace.TOP_END).set (AREA_SEARCH);
    adder ().at (EPlace.BOTTOM_START).set (AREA_PAGE_LENGTH);
    adder ().at (EPlace.BOTTOM_END).set (AREA_PAGING);
  }
}