/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.concurrent.Immutable;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;

/**
 * This class contains constants for DataTables.
 *
 * @author Philip Helger
 */
@Immutable
public final class CDataTables
{
  public static final ICSSClassProvider CSS_CLASS_DATATABLE = DefaultCSSClassProvider.create ("dataTable");
  public static final ICSSClassProvider CSS_CLASS_DATATABLES_LENGTH = DefaultCSSClassProvider.create ("dataTables_length");
  public static final ICSSClassProvider CSS_CLASS_DATATABLES_FILTER = DefaultCSSClassProvider.create ("dataTables_filter");
  public static final ICSSClassProvider CSS_CLASS_DATATABLES_INFO = DefaultCSSClassProvider.create ("dataTables_info");
  public static final ICSSClassProvider CSS_CLASS_DATATABLES_PAGINATE = DefaultCSSClassProvider.create ("dataTables_paginate");

  private CDataTables ()
  {}
}
