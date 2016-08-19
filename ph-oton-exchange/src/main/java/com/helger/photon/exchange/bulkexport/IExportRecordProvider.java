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
package com.helger.photon.exchange.bulkexport;

import javax.annotation.Nonnull;

import com.helger.commons.collection.ext.CommonsArrayList;

/**
 * The main record provider.
 *
 * @author Philip Helger
 */
public interface IExportRecordProvider
{
  /**
   * @return The optional header record. Never <code>null</code>.
   */
  @Nonnull
  default Iterable <? extends IExportRecord> getHeaderRecords ()
  {
    return new CommonsArrayList<> ();
  }

  /**
   * @return An iterator for all body records. Never <code>null</code>.
   */
  @Nonnull
  default Iterable <? extends IExportRecord> getBodyRecords ()
  {
    return new CommonsArrayList<> ();
  }

  /**
   * @return The optional footer record. Never <code>null</code>.
   */
  @Nonnull
  default Iterable <? extends IExportRecord> getFooterRecords ()
  {
    return new CommonsArrayList<> ();
  }
}
