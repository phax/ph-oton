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
package com.helger.photon.core;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;

/**
 * Contains some global texts.
 *
 * @author Philip Helger
 */
@Translatable
public enum EWebBasicsText implements IHasDisplayText, IHasDisplayTextWithArgs
{
  // Misc texts
  PAGE_HELP_TITLE ("Hilfe zu ''{0}'' anzeigen", "Show help for ''{0}''"),
  DOWNLOAD ("Download", "Download"),
  EMAIL_ADDRESS ("E-Mail-Adresse", "Email address"),
  LOGIN_HEADER ("Login", "Login"),
  LOGIN_ERROR_MSG ("Die Anmeldung ist fehlgeschlagen!", "User login failed!"),
  LOGIN_FIELD_USERNAME ("Benutzername", "User name"),
  LOGIN_FIELD_PASSWORD ("Passwort", "Password"),
  LOGIN_BUTTON_SUBMIT ("Anmelden", "Login"),
  LOGIN_LOGOUT ("Abmelden", "Logout"),

  MSG_SAVE_CHANGES_SUCCESS ("Die Änderungen wurden erfolgreich gespeichert!", "The changes were saved successfully!"),
  MSG_ERR_INCORRECT_INPUT ("Auf Grund fehlender oder falscher Eingaben konnte nicht gespeichert werden! Überprüfen Sie Ihre Eingaben und folgen Sie den Aufforderungen zur Fehlerbehebung.", "Due to missing or incorrect input saving was not possible. Check your input and follow the suggestions for error correction."),
  PLEASE_SELECT ("(Wählen Sie einen Eintrag)", "(Select an item)"),
  SELECT_NONE ("(Kein)", "(None)"),
  OPEN_CALENDAR ("Kalender...", "Calendar..."),
  TAB_PROGRESS_MSG ("Daten werden geladen...", "Loading..."),
  PAGING_PAGE (" Seite ", " Page "),
  PAGING_OF (" von {0}", " of {0}"),
  PAGING_TOOLTIP_PAGE_START ("Erste Seite", "First page"),
  PAGING_TOOLTIP_PAGE_PREV ("Vorherige Seite", "Previous page"),
  PAGING_TOOLTIP_PAGE_NEXT ("Nächste Seite", "Next page"),
  PAGING_TOOLTIP_PAGE_END ("Letzte Seite", "Last page"),
  PAGING_LABEL_PAGE_SIZE ("Einträge pro Seite: ", "Entries per page: "),
  PAGING_TOOLTIP_ACCEPT ("Anwenden", "Apply"),
  PAGING_SHOW_RANGE ("Einträge {0} - {1} von insgesamt {2}", "Entries {0} - {1} of total {2}"),
  TREE_FILTER ("Einträge werden gefiltert...", "Filtering entries..."),
  TREE_EXPAND_ALL ("Daten werden geladen...", "Loading..."),
  TREE_COLLAPSE_ALL ("Daten werden geladen...", "Loading..."),
  TREE_EXPAND ("Daten werden geladen...", "Loading..."),
  TREE_COLLAPSE ("Daten werden geladen...", "Loading..."),
  TREE_LABEL_FILTER ("Einträge filtern", "Filter entries"),
  TREE_TREEITEM_COLLAPSE ("Eintrag zuklappen", "Collapse item"),
  TREE_TREEITEM_EXPAND ("Eintrag aufklappen", "Expand item"),
  TREE_TREEITEMS_COLLAPSE ("Alle Einträge zuklappen", "Collapse all items"),
  TREE_TREEITEMS_EXPAND ("Alle Einträge aufklappen", "Expand all items"),
  SELECT_SHOW_ALL ("Alle anzeigen...", "Show all..."),
  FILE_SELECT ("Dateiauswahl", "File Selection"),
  MSG_ACTIONS ("Aktionen", "Actions"),
  INTERNAL_ERROR_TITLE ("Interner Fehler", "Internal error"),
  INTERNAL_ERROR_DESCRIPTION ("Entschuldigung!\n"
                              + "Es ist ein interner Fehler aufgetreten.\n"
                              + "\n"
                              + "Diese Fehlermeldung wurde automatisch and die zuständigen Personen weitergeleitet.\n"
                              + "Notieren Sie sich bitte ihre persönliche Fehlernummer ''{0}'' für eine mögliche spätere Analyse.\n"
                              + "\n"
                              + "Sie können mit Ihrer Arbeit fortfahren.\n"
                              + "Falls dieser Fehler wieder auftritt, vermeiden Sie bitte die Schritte die zu diesem Fehler geführt haben, und warten bis er behoben ist.", "Sorry!\n"
                                                                                                                                                                            + "An internal error was encountered.\n"
                                                                                                                                                                            + "\n"
                                                                                                                                                                            + "An automated error report was already sent to the technical responsible.\n"
                                                                                                                                                                            + "Write down your personal error number ''{0}'' for possible investigation.\n"
                                                                                                                                                                            + "\n"
                                                                                                                                                                            + "You can continue your work.\n"
                                                                                                                                                                            + "In case this error occurs again avoid the actions leading to it until the problem is solved."),

  MSG_BACK_TO_OVERVIEW ("Zurück zur Übersicht", "Back to the list"),
  MSG_BUTTON_YES ("Ja", "Yes"),
  MSG_BUTTON_NO ("Nein", "No"),
  MSG_BUTTON_BACK ("Zurück", "Back"),
  MSG_BUTTON_NEXT ("Weiter", "Next"),
  MSG_BUTTON_SAVE ("Speichern", "Save"),
  MSG_BUTTON_SAVE_ALL ("Alles Speichern", "Save all"),
  MSG_BUTTON_SAVE_AS ("Speichern unter...", "Save as..."),
  MSG_BUTTON_SAVE_CLOSE ("Speichern und schließen", "Save and close"),
  MSG_BUTTON_RESET ("Zurücksetzen", "Reset"),
  MSG_BUTTON_CANCEL ("Abbrechen", "Cancel"),
  MSG_BUTTON_CLOSE ("Schließen", "Close"),
  MSG_BUTTON_SELECT ("Auswählen...", "Select..."),
  MSG_BUTTON_DELETE ("Löschen", "Delete"),
  MSG_BUTTON_DELETE_ALL ("Alle löschen", "Delete all"),
  MSG_BUTTON_UPLOAD ("Hochladen", "Upload"),
  MSG_BUTTON_REFRESH ("Aktualisieren", "Refresh"),
  MSG_BUTTON_REGISTER ("Registrieren", "Register"),
  MSG_BUTTON_SIGN_UP ("Registrieren", "Sign up"),
  MSG_BUTTON_EDIT ("Bearbeiten", "Edit"),
  MSG_BUTTON_RESEND ("Erneut versenden", "Resend"),
  MSG_BUTTON_RESEND_ALL ("Alle erneut versenden", "Resend all"),
  MSG_TRUE ("Wahr", "True"),
  MSG_FALSE ("Falsch", "False");

  private final TextProvider m_aTP;

  private EWebBasicsText (final String sDE, final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }

  @Nullable
  public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
  {
    return DefaultTextResolver.getTextWithArgs (this, m_aTP, aContentLocale, aArgs);
  }

  @Nullable
  public static String getYesOrNo (final boolean bYes, @Nonnull final Locale aContentLocale)
  {
    return (bYes ? MSG_BUTTON_YES : MSG_BUTTON_NO).getDisplayText (aContentLocale);
  }
}
