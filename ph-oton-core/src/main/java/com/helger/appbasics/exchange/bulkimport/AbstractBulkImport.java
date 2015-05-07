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
package com.helger.appbasics.exchange.bulkimport;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.appbasics.exchange.EExchangeFileType;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.name.IHasDisplayText;

/**
 * Base class for {@link IBulkImport} implementations.
 *
 * @author Philip Helger
 */
public abstract class AbstractBulkImport implements IBulkImport
{
  private final int m_nHeaderRowsToSkip;
  private final List <IHasDisplayText> m_aColumnNames;
  private final Set <EExchangeFileType> m_aFileTypes;

  protected AbstractBulkImport (@Nonnegative final int nHeaderRowsToSkip,
                                @Nonnull @Nonempty final List <IHasDisplayText> aColumnNames,
                                @Nonnull @Nonempty final EExchangeFileType... aFileTypes)
  {
    ValueEnforcer.isGE0 (nHeaderRowsToSkip, "HeaderRowsToSkip");
    ValueEnforcer.notEmptyNoNullValue (aColumnNames, "ColumnNames");
    ValueEnforcer.notEmptyNoNullValue (aFileTypes, "FileTypes");
    m_nHeaderRowsToSkip = nHeaderRowsToSkip;
    m_aColumnNames = aColumnNames;
    m_aFileTypes = CollectionHelper.newOrderedSet (aFileTypes);
  }

  @Override
  @Nonnegative
  public final int getHeaderRowsToSkip ()
  {
    return m_nHeaderRowsToSkip;
  }

  @Override
  @Nonnegative
  public final int getColumnCount ()
  {
    return m_aColumnNames.size ();
  }

  @Override
  @Nonnull
  @Nonempty
  public final List <String> getColumnDescriptions (@Nonnull final Locale aContentLocale)
  {
    final List <String> ret = new ArrayList <String> (getColumnCount ());
    for (final IHasDisplayText aColumn : m_aColumnNames)
      ret.add (aColumn.getDisplayText (aContentLocale));
    return ret;
  }

  @Override
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public final List <EExchangeFileType> getSupportedFileTypes ()
  {
    return CollectionHelper.newList (m_aFileTypes);
  }
}
