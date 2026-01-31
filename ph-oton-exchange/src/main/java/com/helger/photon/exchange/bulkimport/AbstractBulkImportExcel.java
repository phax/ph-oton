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
package com.helger.photon.exchange.bulkimport;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.misc.Translatable;
import com.helger.base.state.ETriState;
import com.helger.base.string.StringParser;
import com.helger.base.string.StringReplace;
import com.helger.photon.exchange.EExchangeFileType;
import com.helger.poi.excel.ExcelReadHelper;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

/**
 * Abstract base implementation of a bulk import based on MS Excel files.
 *
 * @author Philip Helger
 */
public abstract class AbstractBulkImportExcel extends AbstractBulkImport
{
  @Translatable
  private enum EText implements IHasDisplayTextWithArgs
  {
    MSG_ERR_NO_STRING ("Zeile {0}, Spalte {1}: Zeichenkette erwartet", "Row {0}, Column {1}: expected string"),
    MSG_ERR_NO_INT ("Zeile {0}, Spalte {1}: Ganzzahl erwartet", "Row {0}, Column {1}: expected integer"),
    MSG_ERR_NO_DATE ("Zeile {0}, Spalte {1}: Datum erwartet", "Row {0}, Column {1}: expected date"),
    MSG_ERR_NO_BOOLEAN ("Zeile {0}, Spalte {1}: Wahrheitswert erwartet", "Row {0}, Column {1}: expected boolean");

    private final IMultilingualText m_aTP;

    EText (@NonNull final String sDE, @NonNull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@NonNull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  protected AbstractBulkImportExcel (@Nonnegative final int nHeaderRowsToSkip,
                                     @NonNull @Nonempty final List <IHasDisplayText> aColumns,
                                     @NonNull @Nonempty final EExchangeFileType... aFileTypes)
  {
    super (nHeaderRowsToSkip, aColumns, aFileTypes);
  }

  @Nullable
  protected static final String getNormalizedString (@Nullable final String s)
  {
    if (s == null)
      return null;
    final StringBuilder aSB = new StringBuilder (s.length ());
    for (final char c : s.toCharArray ())
      if (Character.getType (c) != Character.CONTROL)
        aSB.append (c);
    return StringReplace.replaceAllRepeatedly (aSB.toString ().trim (), "  ", " ");
  }

  @Nullable
  protected static final String getCellAsString (@NonNull final Cell aCell)
  {
    final String sValue = ExcelReadHelper.getCellValueString (aCell);
    if (sValue == null)
      return null;

    final StringBuilder aSB = new StringBuilder (sValue.length ());
    for (final char c : sValue.toCharArray ())
      if (Character.getType (c) != Character.CONTROL)
        aSB.append (c);
    return StringReplace.replaceAllRepeatedly (aSB.toString ().trim (), "  ", " ");
  }

  @Nullable
  protected static final Integer getCellAsInteger (@Nonnegative final int nRowIndex,
                                                   @NonNull final Cell aCell,
                                                   @NonNull final BulkImportResult aRes,
                                                   @NonNull final Locale aDisplayLocale)
  {
    final Object aValue = ExcelReadHelper.getCellValueObject (aCell);
    if (aValue == null)
      return null;
    if (aValue instanceof final Integer aInt)
      return aInt;

    Integer ret = null;
    if (aValue instanceof final String sValue)
      ret = StringParser.parseIntObj (sValue);

    if (ret == null)
      aRes.addWarning (EText.MSG_ERR_NO_INT.getDisplayTextWithArgs (aDisplayLocale,
                                                                    Integer.toString (nRowIndex),
                                                                    Integer.toString (aCell.getColumnIndex ())));
    return ret;
  }

  @Nullable
  protected static final Date getCellAsDate (@Nonnegative final int nRowIndex,
                                             @NonNull final Cell aCell,
                                             @NonNull final BulkImportResult aRes,
                                             @NonNull final Locale aDisplayLocale)
  {
    final Date aValue = ExcelReadHelper.getCellValueJavaDate (aCell);
    if (aValue == null && ExcelReadHelper.getCellValueObject (aCell) != null)
    {
      aRes.addWarning (EText.MSG_ERR_NO_DATE.getDisplayTextWithArgs (aDisplayLocale,
                                                                     Integer.toString (nRowIndex),
                                                                     Integer.toString (aCell.getColumnIndex ())));
      return null;
    }
    return aValue;
  }

  @NonNull
  protected static final ETriState getCellAsBoolean (@Nonnegative final int nRowIndex,
                                                     @NonNull final Cell aCell,
                                                     @NonNull final BulkImportResult aRes,
                                                     @NonNull final Locale aDisplayLocale)
  {
    final Object aValue = ExcelReadHelper.getCellValueObject (aCell);
    if (aValue == null)
      return ETriState.UNDEFINED;
    if (aValue instanceof final Boolean aBoolean)
      return ETriState.valueOf (aBoolean);

    aRes.addWarning (EText.MSG_ERR_NO_BOOLEAN.getDisplayTextWithArgs (aDisplayLocale,
                                                                      Integer.toString (nRowIndex),
                                                                      Integer.toString (aCell.getColumnIndex ())));
    return ETriState.UNDEFINED;
  }
}
