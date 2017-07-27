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
package com.helger.photon.exchange.bulkimport;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.photon.exchange.EExchangeFileType;

/**
 * Base class for {@link IBulkImport} implementations.
 *
 * @author Philip Helger
 */
public abstract class AbstractBulkImport implements IBulkImport
{
  private final int m_nHeaderRowsToSkip;
  private final ICommonsList <IHasDisplayText> m_aColumnNames;
  private final ICommonsOrderedSet <EExchangeFileType> m_aFileTypes;

  protected AbstractBulkImport (@Nonnegative final int nHeaderRowsToSkip,
                                @Nonnull @Nonempty final List <IHasDisplayText> aColumnNames,
                                @Nonnull @Nonempty final EExchangeFileType... aFileTypes)
  {
    ValueEnforcer.isGE0 (nHeaderRowsToSkip, "HeaderRowsToSkip");
    ValueEnforcer.notEmptyNoNullValue (aColumnNames, "ColumnNames");
    ValueEnforcer.notEmptyNoNullValue (aFileTypes, "FileTypes");
    m_nHeaderRowsToSkip = nHeaderRowsToSkip;
    m_aColumnNames = new CommonsArrayList<> (aColumnNames);
    m_aFileTypes = new CommonsLinkedHashSet<> (aFileTypes);
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
  public final ICommonsList <String> getColumnDescriptions (@Nonnull final Locale aContentLocale)
  {
    final ICommonsList <String> ret = new CommonsArrayList<> (getColumnCount ());
    for (final IHasDisplayText aColumn : m_aColumnNames)
      ret.add (aColumn.getDisplayText (aContentLocale));
    return ret;
  }

  @Override
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public final ICommonsList <EExchangeFileType> getSupportedFileTypes ()
  {
    return m_aFileTypes.getCopyAsList ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("HeaderRowsToSkip", m_nHeaderRowsToSkip)
                                       .append ("ColumnNames", m_aColumnNames)
                                       .append ("FileTypes", m_aFileTypes)
                                       .getToString ();
  }
}
