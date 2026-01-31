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
package com.helger.photon.uictrls.datatables;

/**
 * DataTables filter (search) type
 *
 * @author Philip Helger
 */
public enum EDataTablesFilterType
{
  /** A row matches the filter, if at least one search term is contained. */
  ANY_TERM_PER_ROW,
  /** A row matches the filter, if all search terms are contained */
  ALL_TERMS_PER_ROW;

  /** Default type: {@link #ALL_TERMS_PER_ROW} */
  public static final EDataTablesFilterType DEFAULT = ALL_TERMS_PER_ROW;
}
