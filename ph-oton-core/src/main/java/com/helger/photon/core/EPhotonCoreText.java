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
package com.helger.photon.core;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * Contains some global texts.
 *
 * @author Philip Helger
 */
@Translatable
public enum EPhotonCoreText implements IHasDisplayTextWithArgs
{
  // Menu object filters
  MENU_OBJECT_FILTER_USER_LOGGED_IN ("ein Benutzer ist angemeldet", "a user is logged in"),
  MENU_OBJECT_FILTER_NO_USER_LOGGED_IN ("es ist kein Benutzer angemeldet", "no user is logged in"),
  MENU_OBJECT_FILTER_USER_ASSIGNED_TO_GROUP ("ein Benutzer ist der Benutzergruppe {0} zugeordnet",
                                             "a user is assigned to user group {0}"),
  MENU_OBJECT_FILTER_USER_HAS_ROLE ("ein Benutzer hat die Rolle {0}", "a user has role {0}"),

  // System messages
  SYSTEM_MESSAGE_TYPE_INFO ("Information", "Information"),
  SYSTEM_MESSAGE_TYPE_WARNING ("Warnung", "Warning"),
  SYSTEM_MESSAGE_TYPE_ERROR ("Fehler", "Error"),
  SYSTEM_MESSAGE_TYPE_SUCCESS ("Erfolg", "Success"),

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
  IMAGE_NONE ("keines", "none"),
  SAVE_CHANGES_SUCCESS ("Die Änderungen wurden erfolgreich gespeichert!", "The changes were saved successfully!"),
  ERR_INCORRECT_INPUT ("Aufgrund fehlender oder falscher Eingaben konnte nicht gespeichert werden! Überprüfen Sie Ihre Eingaben und folgen Sie den Aufforderungen zur Fehlerbehebung.",
                       "Due to missing or incorrect input saving was not possible. Check your input and follow the suggestions for error correction."),
  PLEASE_SELECT ("(Wählen Sie einen Eintrag)", "(Select an item)"),
  SELECT_NONE ("(Kein)", "(None)"),
  CALENDAR_OPEN ("Kalender...", "Calendar..."),
  LOADING_MSG ("Daten werden geladen...", "Loading..."),
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
  ACTIONS ("Aktionen", "Actions"),
  INTERNAL_ERROR_TITLE ("Interner Fehler", "Internal error"),
  INTERNAL_ERROR_DESCRIPTION ("Entschuldigung!\n" +
                              "Es ist ein interner Fehler aufgetreten.\n" +
                              "\n" +
                              "Diese Fehlermeldung wurde automatisch an die zuständigen Personen weitergeleitet.\n" +
                              "Notieren Sie sich bitte ihre persönliche Fehlernummer ''{0}'' für eine mögliche spätere Analyse.\n" +
                              "\n" +
                              "Sie können mit Ihrer Arbeit fortfahren.\n" +
                              "Falls dieser Fehler wieder auftritt, vermeiden Sie bitte die Schritte die zu diesem Fehler geführt haben, und warten bis er behoben ist.",
                              "Sorry!\n" +
                                                                                                                                                                          "An internal error was encountered.\n" +
                                                                                                                                                                          "\n" +
                                                                                                                                                                          "An automated error report was already sent to the technical responsible.\n" +
                                                                                                                                                                          "Write down your personal error number ''{0}'' for possible investigation.\n" +
                                                                                                                                                                          "\n" +
                                                                                                                                                                          "You can continue your work.\n" +
                                                                                                                                                                          "In case this error occurs again avoid the actions leading to it until the problem is solved."),

  BACK_TO_OVERVIEW ("Zurück zur Übersicht", "Back to the list"),
  BUTTON_YES ("Ja", "Yes"),
  BUTTON_NO ("Nein", "No"),
  BUTTON_BACK ("Zurück", "Back"),
  BUTTON_NEXT ("Weiter", "Next"),
  BUTTON_SAVE ("Speichern", "Save"),
  BUTTON_SAVE_ALL ("Alles Speichern", "Save all"),
  BUTTON_SAVE_AS ("Speichern unter...", "Save as..."),
  BUTTON_SAVE_CLOSE ("Speichern und schließen", "Save and close"),
  BUTTON_LOAD ("Laden", "Load"),
  BUTTON_RESET ("Zurücksetzen", "Reset"),
  BUTTON_CANCEL ("Abbrechen", "Cancel"),
  BUTTON_CLOSE ("Schließen", "Close"),
  BUTTON_SELECT ("Auswählen...", "Select..."),
  BUTTON_BROWSE ("Durchsuchen...", "Browse..."),
  BUTTON_DELETE ("Löschen", "Delete"),
  BUTTON_DELETE_ALL ("Alle löschen", "Delete all"),
  BUTTON_UPLOAD ("Hochladen", "Upload"),
  BUTTON_REFRESH ("Aktualisieren", "Refresh"),
  BUTTON_REGISTER ("Registrieren", "Register"),
  BUTTON_SIGN_UP ("Registrieren", "Sign up"),
  BUTTON_EDIT ("Bearbeiten", "Edit"),
  BUTTON_SEND ("Absenden", "Send"),
  BUTTON_RESEND ("Erneut versenden", "Resend"),
  BUTTON_RESEND_ALL ("Alle erneut versenden", "Resend all"),
  BUTTON_ENABLE ("Aktivieren", "Enable"),
  BUTTON_DISABLE ("Deaktivieren", "Disable"),
  BUTTON_PRINT ("Drucken", "Print"),
  BUTTON_PRINT_PREVIEW ("Druckvorschau", "Print preview"),
  BUTTON_PRINT_VIEW ("Druckansicht", "Print view"),
  BUTTON_DOWNLOAD ("Herunterladen", "Download"),
  TRUE ("Wahr", "True"),
  FALSE ("Falsch", "False");

  private final IMultilingualText m_aTP;

  EPhotonCoreText (final String sDE, final String sEN)
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

  @Nullable
  public static String getYesOrNo (final boolean bYes, @Nonnull final Locale aContentLocale)
  {
    return (bYes ? BUTTON_YES : BUTTON_NO).getDisplayText (aContentLocale);
  }
}
