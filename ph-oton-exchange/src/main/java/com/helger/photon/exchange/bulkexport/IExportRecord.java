/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.exchange.bulkexport;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.ICommonsList;

/**
 * Represents a single record to be exported.
 *
 * @author Philip Helger
 */
public interface IExportRecord
{
  /**
   * @return A list of all fields in the correct order.
   */
  @Nonnull
  ICommonsList <? extends IExportRecordField> getAllFields ();

  /**
   * @return <code>true</code> if at least one field is present
   */
  boolean hasFields ();

  /**
   * @return The number of fields in this record.
   */
  @Nonnegative
  int getFieldCount ();
}
