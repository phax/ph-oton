/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * DataTables text array
 *
 * @author Philip Helger
 */
@Translatable
public enum EDataTablesText implements IHasDisplayText
{
  // aria lables
  ARIA_ORDERABLE (": aktivieren, um zu sortieren", ": Activate to sort"),
  ARIA_ORDERABLE_REVERSE (": aktivieren, um Sortierung zu invertieren", ": Activate to invert sorting"),
  ARIA_ORDERABLE_REMOVE (": aktivieren, um Sortierung zu entfernen", ": Activate to remove sorting"),
  // paginate
  PAGINATE_FIRST ("Erste", "First"),
  PAGINATE_PREVIOUS ("Zurück", "Previous"),
  PAGINATE_NEXT ("Weiter", "Next"),
  PAGINATE_LAST ("Letzte", "Last"),
  // entries
  ENTRIES_N ("Einträge", "entries"),
  ENTRIES_1 ("Eintrag", "entry"),
  // main
  EMPTY_TABLE ("Keine Einträge vorhanden", "No data available in table"),
  INFO ("_START_ bis _END_ von _TOTAL_ _ENTRIES-TOTAL_", "Showing _START_ to _END_ of _TOTAL_ _ENTRIES-TOTAL_"),
  INFO_EMPTY ("0 bis 0 von 0 _ENTRIES-TOTAL_", "Showing 0 to 0 of 0 _ENTRIES-TOTAL_"),
  INFO_FILTERED ("(gefiltert von _MAX_ _ENTRIES-MAX_)", "(filtered from _MAX_ total _ENTRIES-MAX_)"),
  INFO_POSTFIX ("", ""),
  THOUSANDS (" ", ","),
  LENGTH_MENU ("_MENU_ _ENTRIES_ pro Seite", "_MENU_ _ENTRIES_ per page"),
  LOADING_RECORDS ("Wird geladen...", "Loading..."),
  PROCESSING ("Bitte warten...", "Processing..."),
  SEARCH ("Suchen:", "Search:"),
  SEARCH_PLACEHOLDER ("Einträge suchen", "Search entries"),
  ZERO_RECORDS ("Keine passenden Einträge vorhanden.", "No matching records found."),
  // For length menu
  ALL ("Alle", "all");

  private final IMultilingualText m_aTP;

  EDataTablesText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
