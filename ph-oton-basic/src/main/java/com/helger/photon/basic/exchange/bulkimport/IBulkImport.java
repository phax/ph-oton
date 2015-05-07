/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.exchange.bulkimport;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.io.IInputStreamProvider;
import com.helger.photon.basic.exchange.EExchangeFileType;

/**
 * Base interface for a bulk import action.
 * 
 * @author Philip Helger
 */
public interface IBulkImport
{
  /**
   * @return A non-<code>null</code>, non-empty set of all supported file types.
   */
  @Nonnull
  @Nonempty
  List <EExchangeFileType> getSupportedFileTypes ();

  /**
   * @return The initial rows to skip.
   */
  @Nonnegative
  int getHeaderRowsToSkip ();

  /**
   * @return The number of columns the input data is required to have
   */
  @Nonnegative
  int getColumnCount ();

  /**
   * Get a list of all column descriptions.
   * 
   * @param aContentLocale
   *        The locale to be used.
   * @return The non-<code>null</code>, non-empty list of column descriptions.
   */
  @Nonnull
  @Nonempty
  List <String> getColumnDescriptions (@Nonnull Locale aContentLocale);

  /**
   * Read the objects from the passed input stream.
   * 
   * @param aIIS
   *        The input stream provider to read from. May not be <code>null</code>
   *        .
   * @param aDisplayLocale
   *        The display locale. May not be <code>null</code>.
   * @return The import result. Never <code>null</code>.
   */
  @Nonnull
  BulkImportResult importObjects (@Nonnull IInputStreamProvider aIIS, @Nonnull Locale aDisplayLocale);
}
