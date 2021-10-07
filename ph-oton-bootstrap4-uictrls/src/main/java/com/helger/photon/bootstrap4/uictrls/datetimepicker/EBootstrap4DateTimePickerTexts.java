/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * Texts used.
 *
 * @author Philip Helger
 */
@Translatable
public enum EBootstrap4DateTimePickerTexts implements IHasDisplayTextWithArgs
{
  TODAY ("Heute", "Go to today"),
  CLEAR ("Auswahl löschen", "Clear selection"),
  CLOSE ("Schließen", "Close the picker"),
  SELECTMONTH ("Monat wählen", "Select Month"),
  PREVMONTH ("Vorheriger Monat", "Previous Month"),
  NEXTMONTH ("Nächster Monat", "Next Month"),
  SELECTYEAR ("Jahr wählen", "Select Year"),
  PREVYEAR ("Nächstes Jahr", "Previous Year"),
  NEXTYEAR ("Vorheriges Jahr", "Next Year"),
  SELECTDECADE ("Dekade wählen", "Select Decade"),
  PREVDECADE ("Vorherige Dekade", "Previous Decade"),
  NEXTDECADE ("Nächste Dekade", "Next Decade"),
  PREVCENTURY ("Vorheriges Jahrhundert", "Previous Century"),
  NEXTCENTURY ("Nächstes Jahrhundert", "Next Century"),
  INCREMENTHOUR ("Nächste Stunde", "Increment Hour"),
  PICKHOUR ("Stunde wählen", "Pick Hour"),
  DECREMENTHOUR ("Vorherige Stunde", "Decrement Hour"),
  INCREMENTMINUTE ("Nächste Minute", "Increment Minute"),
  PICKMINUTE ("Minute wählen", "Pick Minute"),
  DECREMENTMINUTE ("Vorherige Minute", "Decrement Minute"),
  INCREMENTSECOND ("Nächste Sekunde", "Increment Second"),
  PICKSECOND ("Sekunde wählen", "Pick Second"),
  DECREMENTSECOND ("Vorherige Sekunde", "Decrement Second");

  private final IMultilingualText m_aTP;

  EBootstrap4DateTimePickerTexts (@Nonnull @Nonempty final String sDE, @Nonnull @Nonempty final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }

  @Nonnull
  public IMultilingualText getMultilingualText ()
  {
    return m_aTP;
  }
}
