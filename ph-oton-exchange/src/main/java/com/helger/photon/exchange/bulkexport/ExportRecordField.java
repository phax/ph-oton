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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A single field for exporting.
 *
 * @author Philip Helger
 */
@Immutable
public class ExportRecordField implements IExportRecordField
{
  private final EExportDataType m_eFieldType;
  private final Object m_aValue;

  protected ExportRecordField (@Nonnull final EExportDataType eFieldType, @Nullable final Object aValue)
  {
    m_eFieldType = ValueEnforcer.notNull (eFieldType, "FieldType");
    m_aValue = aValue;
  }

  @Nonnull
  public EExportDataType getFieldType ()
  {
    return m_eFieldType;
  }

  @Nullable
  public Object getFieldValue ()
  {
    return m_aValue;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ExportRecordField rhs = (ExportRecordField) o;
    return m_eFieldType.equals (rhs.m_eFieldType) && EqualsHelper.equals (m_aValue, rhs.m_aValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eFieldType).append (m_aValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("fieldType", m_eFieldType).append ("value", m_aValue).getToString ();
  }

  @Nonnull
  public static EExportDataType autoDetermineType (@Nullable final Object aValue)
  {
    if (aValue != null)
    {
      if (aValue instanceof LocalDate)
        return EExportDataType.DATE;
      if (aValue instanceof LocalTime)
        return EExportDataType.TIME;
      if (aValue instanceof LocalDateTime || aValue instanceof ZonedDateTime || aValue instanceof OffsetDateTime)
        return EExportDataType.DATETIME;
      if (aValue instanceof Boolean)
        return EExportDataType.BOOLEAN;
      if (aValue instanceof Byte ||
          aValue instanceof Short ||
          aValue instanceof Integer ||
          aValue instanceof Long ||
          aValue instanceof BigInteger)
        return EExportDataType.INT;
      if (aValue instanceof Float || aValue instanceof Double || aValue instanceof BigDecimal)
        return EExportDataType.DOUBLE;
    }

    // Default to text
    return EExportDataType.TEXT;
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final Object aValue)
  {
    return new ExportRecordField (autoDetermineType (aValue), aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final String sValue)
  {
    return new ExportRecordField (EExportDataType.TEXT, sValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final LocalDate aValue)
  {
    return new ExportRecordField (EExportDataType.DATE, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final LocalTime aValue)
  {
    return new ExportRecordField (EExportDataType.TIME, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final LocalDateTime aValue)
  {
    return new ExportRecordField (EExportDataType.DATETIME, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final ZonedDateTime aValue)
  {
    return new ExportRecordField (EExportDataType.DATETIME, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final OffsetDateTime aValue)
  {
    return new ExportRecordField (EExportDataType.DATETIME, aValue);
  }

  @Nonnull
  public static ExportRecordField create (final boolean bValue)
  {
    return create (Boolean.valueOf (bValue));
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final Boolean aValue)
  {
    return new ExportRecordField (EExportDataType.BOOLEAN, aValue);
  }

  @Nonnull
  public static ExportRecordField create (final int nValue)
  {
    return create (Integer.valueOf (nValue));
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final Integer aValue)
  {
    return new ExportRecordField (EExportDataType.INT, aValue);
  }

  @Nonnull
  public static ExportRecordField create (final long nValue)
  {
    return create (Long.valueOf (nValue));
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final Long aValue)
  {
    return new ExportRecordField (EExportDataType.INT, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final BigInteger aValue)
  {
    return new ExportRecordField (EExportDataType.INT, aValue);
  }

  @Nonnull
  public static ExportRecordField create (final double dValue)
  {
    return create (Double.valueOf (dValue));
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final Double aValue)
  {
    return new ExportRecordField (EExportDataType.DOUBLE, aValue);
  }

  @Nonnull
  public static ExportRecordField create (@Nullable final BigDecimal aValue)
  {
    return new ExportRecordField (EExportDataType.DOUBLE, aValue);
  }
}
