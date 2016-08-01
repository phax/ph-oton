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
package com.helger.photon.uictrls.datatables.column;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.name.IHasName;

/**
 * DataTables column type
 *
 * @author Philip Helger
 */
public enum EDataTablesColumnType implements IHasName
{
  /**
   * Date / time values. Note that DataTables' built in date parsing uses
   * Javascript's Date.parse() method which supports only a very limited subset
   * of dates. Additional date format support can be added through the use of
   * plug-ins.
   */
  DATE ("date"),
  /** Simple number sorting. */
  NUM ("num"),
  /**
   * Numeric sorting of formatted numbers. Numbers which are formatted with
   * thousands separators, currency symbols or a percentage indicator will be
   * sorted numerically automatically by DataTables.
   */
  NUM_FMT ("num-fmt"),
  /** As per the {@link #NUM} option, but with HTML tags also in the data. */
  HTML_NUM ("html-num"),
  /**
   * As per the {@link #NUM_FMT} option, but with HTML tags also in the data.
   */
  HTML_NUM_FMT ("html-num-fmt"),
  /** Basic string processing for HTML tags */
  HTML ("html"),
  /**
   * Fall back type if the data in the column does not match the requirements
   * for the other data types (above).
   */
  STRING ("string");

  private final String m_sName;

  private EDataTablesColumnType (@Nonnull @Nonempty final String sName)
  {
    m_sName = sName;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }
}
