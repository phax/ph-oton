/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Default implementation of {@link IExportRecord}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ExportRecord implements IExportRecord
{
  private final ICommonsList <IExportRecordField> m_aFields = new CommonsArrayList <> ();

  public ExportRecord ()
  {}

  public ExportRecord (@Nonnull final IExportRecordField... aFields)
  {
    ValueEnforcer.notNull (aFields, "Fields");

    for (final IExportRecordField aField : aFields)
      addField (aField);
  }

  public ExportRecord (@Nonnull final Iterable <? extends IExportRecordField> aFields)
  {
    ValueEnforcer.notNull (aFields, "Fields");

    for (final IExportRecordField aField : aFields)
      addField (aField);
  }

  @Nonnull
  public EChange removeFieldAtIndex (@Nonnegative final int nIndex)
  {
    return m_aFields.removeAtIndex (nIndex);
  }

  @Nonnull
  public ExportRecord addField (@Nonnull final IExportRecordField aField)
  {
    ValueEnforcer.notNull (aField, "Field");
    m_aFields.add (aField);
    return this;
  }

  @Nonnull
  public ExportRecord addField (@Nullable final Object aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final String sValue)
  {
    return addField (ExportRecordField.create (sValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final LocalDate aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final LocalTime aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final LocalDateTime aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final ZonedDateTime aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final OffsetDateTime aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (final boolean bValue)
  {
    return addField (ExportRecordField.create (bValue));
  }

  @Nonnull
  public ExportRecord addField (final Boolean aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (final int nValue)
  {
    return addField (ExportRecordField.create (nValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final Integer aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (final long nValue)
  {
    return addField (ExportRecordField.create (nValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final Long aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final BigInteger aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (final double dValue)
  {
    return addField (ExportRecordField.create (dValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final Double aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addField (@Nullable final BigDecimal aValue)
  {
    return addField (ExportRecordField.create (aValue));
  }

  @Nonnull
  public ExportRecord addNullField ()
  {
    return addField (new ExportRecordField (EExportDataType.TEXT, null));
  }

  @Nonnull
  public ExportRecord addNullFields (@Nonnegative final int nFields)
  {
    ValueEnforcer.isGE0 (nFields, "Fields");
    for (int i = 0; i < nFields; ++i)
      addNullField ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IExportRecordField> getAllFields ()
  {
    return m_aFields.getClone ();
  }

  public boolean hasFields ()
  {
    return m_aFields.isNotEmpty ();
  }

  @Nonnegative
  public int getFieldCount ()
  {
    return m_aFields.size ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ExportRecord rhs = (ExportRecord) o;
    return m_aFields.equals (rhs.m_aFields);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aFields).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("fields", m_aFields).getToString ();
  }
}
