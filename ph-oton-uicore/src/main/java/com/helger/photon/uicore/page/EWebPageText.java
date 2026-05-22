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
package com.helger.photon.uicore.page;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.misc.Translatable;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

/**
 * Contains some web page base class texts.
 *
 * @author Philip Helger
 */
@Translatable
public enum EWebPageText implements IHasDisplayTextWithArgs
{
  OBJECT_COPY ("Kopiere ''{0}''", "Copy ''{0}''"),
  OBJECT_DELETE ("Lösche ''{0}''", "Delete ''{0}''"),
  OBJECT_UNDELETE ("''{0}'' wiederherstellen", "Undelete ''{0}''"),
  OBJECT_EDIT ("Bearbeite ''{0}''", "Edit ''{0}''"),
  IMAGE_NONE ("keines", "none"),
  LOCKING_FAILED ("Auf das Objekt{0} kann nicht exklusiv zugegriffen werden, da es derzeit von {1} gesperrt ist.",
                  "Failed to exclusively access object{0} because it is currently locked by {1}!"),
  LOCKING_OTHER_USER ("einem anderen Benutzer", "another user"),
  PAGE_NAME_CHANGE_PASSWORD ("Passwort ändern", "Change Password"),
  PAGE_NAME_SECURITY ("Sicherheit", "Security"),
  PAGE_NAME_SECURITY_ROLES ("Rollenverwaltung", "Role Management"),
  PAGE_NAME_SECURITY_USER_GROUPS ("Benutzergruppenverwaltung", "User Group Management"),
  PAGE_NAME_SECURITY_USERS ("Benutzerverwaltung", "User Management"),
  PAGE_NAME_SECURITY_APP_TOKENS ("App-Token Verwaltung", "App Token Management"),
  PAGE_NAME_SECURITY_USER_TOKENS ("Benutzer-Token Verwaltung", "User Token Management"),
  PAGE_NAME_MONITORING ("Überwachung", "Monitoring"),
  PAGE_NAME_MONITORING_AUDIT ("Audit-Einträge", "Audit Items"),
  PAGE_NAME_MONITORING_FAILED_MAILS ("Ungesendete E-Mails", "Failed Emails"),
  PAGE_NAME_MONITORING_LOCKED_OBJECTS ("Gesperrte Objekte", "Locked Objects"),
  PAGE_NAME_MONITORING_LOGIN_INFO ("Angemeldete Benutzer", "Logged in Users"),
  PAGE_NAME_MONITORING_SESSIONS ("Sessions", "Sessions"),
  PAGE_NAME_MONITORING_STATISTICS ("Statistiken", "Statistics"),
  PAGE_NAME_MONITORING_SYSTEMMIGRATIONS ("System Migrationen", "System Migrations"),
  PAGE_NAME_SYSINFO ("System Informationen", "System Information"),
  PAGE_NAME_SYSINFO_CACERTS ("System-Truststore", "System Truststore"),
  PAGE_NAME_SYSINFO_ENV_VARS ("Umgebungsvariablen", "Environment Variables"),
  PAGE_NAME_SYSINFO_NETWORK ("Netzwerk", "Network"),
  PAGE_NAME_SYSINFO_REQUEST ("Request", "Request"),
  PAGE_NAME_SYSINFO_SECURITY ("Sicherheit", "Security"),
  PAGE_NAME_SYSINFO_SERVLETCONTEXT ("Servlet Context", "Servlet Context"),
  PAGE_NAME_SYSINFO_SYSPROPS ("Systemeinstellungen", "System Properties"),
  PAGE_NAME_SYSINFO_THIRDPARTYLIBS ("Externe Module", "Thirdparty Libraries"),
  PAGE_NAME_SYSINFO_THREADS ("Threads", "Threads"),
  PAGE_NAME_APPINFO ("App Informationen", "App Information"),
  PAGE_NAME_APPINFO_ACTIONS ("Web Aktionen", "Web Actions"),
  PAGE_NAME_APPINFO_AJAX_FUNCTIONS ("AJAX Funktionen", "Ajax Functions"),
  PAGE_NAME_APPINFO_API ("APIs", "APIs"),
  PAGE_NAME_APPINFO_CONFIG_FILES ("Konfigurationsdateien", "Configuration Files"),
  PAGE_NAME_APPINFO_GO ("Go Mappings", "Go Mappings"),
  PAGE_NAME_APPINFO_PATH_MAPPER ("AppID-Pfad-Mapping", "AppID Path Mapping"),
  PAGE_NAME_APPINFO_SCHEDULER ("Geplante Tasks", "Scheduled Actions"),
  PAGE_NAME_APPINFO_GLOBAL_SCOPE ("Globaler Scope", "Global Scope"),
  PAGE_NAME_APPINFO_SERVLETSTATUS ("Servlet Status", "Servlet Status"),
  PAGE_NAME_APPINFO_WEBRESBUNDLE ("Resource Bundles", "Resource Bundles"),
  PAGE_NAME_DATA ("System Daten", "System Data"),
  PAGE_NAME_DATA_COUNTRIES ("Länder", "Countries"),
  PAGE_NAME_DATA_CURRENCIES ("Währungen", "Currencies"),
  PAGE_NAME_DATA_LANGUAGES ("Sprachen", "Languages"),
  PAGE_NAME_DATA_TIMEZONES ("Zeitzonen", "Time Zones"),
  PAGE_NAME_UTILS ("Werkzeuge", "Utilities"),
  PAGE_NAME_UTILS_BASE64_DECODE ("Base64 Decode", "Base64 Decoder"),
  PAGE_NAME_UTILS_BASE64_ENCODE ("Base64 Encode", "Base64 Encoder"),
  PAGE_NAME_UTILS_HTTP_CLIENT ("http(s) Client", "http(s) Client"),
  PAGE_NAME_UTILS_PORT_CHECKER ("Port Checker", "Port Checker"),
  PAGE_NAME_SETTINGS ("System Einstellungen", "System Settings"),
  PAGE_NAME_SETTINGS_GLOBAL ("Globale Einstellungen", "Global Settings"),
  PAGE_NAME_SETTINGS_HTML ("HTML-Ausgabe Einstellungen", "HTML Output Settings"),
  PAGE_NAME_SETTINGS_LOG_LEVEL ("Log Level ändern", "Log Level"),
  PAGE_NAME_SETTINGS_SMTP ("SMTP-Einstellungen", "SMTP Settings"),
  PAGE_NAME_SETTINGS_SYSTEMMESSAGE ("Systemnachricht", "System Message");

  private final IMultilingualText m_aTP;

  EWebPageText (@NonNull final String sDE, @NonNull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @NonNull
  public IMultilingualText getAsMLT ()
  {
    return m_aTP;
  }

  @Nullable
  public String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
