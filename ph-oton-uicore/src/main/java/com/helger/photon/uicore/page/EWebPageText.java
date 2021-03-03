/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

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
  PAGE_NAME_CHANGE_PASSWORD ("Passwort ändern", "Change password"),
  PAGE_NAME_SECURITY ("Sicherheit", "Security"),
  PAGE_NAME_SECURITY_ROLES ("Rollenverwaltung", "Role management"),
  PAGE_NAME_SECURITY_USER_GROUPS ("Benutzergruppenverwaltung", "User group management"),
  PAGE_NAME_SECURITY_USERS ("Benutzerverwaltung", "User management"),
  PAGE_NAME_SECURITY_APP_TOKENS ("App-Token Verwaltung", "App token management"),
  PAGE_NAME_SECURITY_USER_TOKENS ("Benutzer-Token Verwaltung", "User token management"),
  PAGE_NAME_MONITORING ("Überwachung", "Monitoring"),
  PAGE_NAME_MONITORING_AUDIT ("Audit-Einträge", "Audit items"),
  PAGE_NAME_MONITORING_FAILED_MAILS ("Ungesendete E-Mails", "Failed mails"),
  PAGE_NAME_MONITORING_LOCKED_OBJECTS ("Gesperrte Objekte", "Locked objects"),
  PAGE_NAME_MONITORING_LOGIN_INFO ("Angemeldete Benutzer", "Logged in users"),
  PAGE_NAME_MONITORING_SESSIONS ("Sessions", "Sessions"),
  PAGE_NAME_MONITORING_STATISTICS ("Statistiken", "Statistics"),
  PAGE_NAME_MONITORING_SYSTEMMIGRATIONS ("System Migrationen", "System migrations"),
  PAGE_NAME_SYSINFO ("System Informationen", "System information"),
  PAGE_NAME_SYSINFO_CACERTS ("System-Truststore", "System truststore"),
  PAGE_NAME_SYSINFO_ENV_VARS ("Umgebungsvariablen", "Environment variables"),
  PAGE_NAME_SYSINFO_NETWORK ("Netzwerk", "Network"),
  PAGE_NAME_SYSINFO_REQUEST ("Request", "Request"),
  PAGE_NAME_SYSINFO_SECURITY ("Sicherheit", "Security"),
  PAGE_NAME_SYSINFO_SERVLETCONTEXT ("ServletContext", "ServletContext"),
  PAGE_NAME_SYSINFO_SYSPROPS ("Systemeinstellungen", "System properties"),
  PAGE_NAME_SYSINFO_THIRDPARTYLIBS ("Externe Module", "Thirdparty libraries"),
  PAGE_NAME_SYSINFO_THREADS ("Threads", "Threads"),
  PAGE_NAME_APPINFO ("App Informationen", "App information"),
  PAGE_NAME_APPINFO_ACTIONS ("Web Aktionen", "Web actions"),
  PAGE_NAME_APPINFO_AJAX_FUNCTIONS ("AJAX Funktionen", "Ajax functions"),
  PAGE_NAME_APPINFO_API ("APIs", "APIs"),
  PAGE_NAME_APPINFO_CONFIG_FILES ("Konfigurationsdateien", "Configuration files"),
  PAGE_NAME_APPINFO_GO ("Go Mappings", "Go mappings"),
  PAGE_NAME_APPINFO_PATH_MAPPER ("AppID-Pfad-Mapping", "AppID path mapping"),
  PAGE_NAME_APPINFO_SCHEDULER ("Geplante Tasks", "Scheduled actions"),
  PAGE_NAME_APPINFO_GLOBAL_SCOPE ("Globaler Scope", "Global Scope"),
  PAGE_NAME_APPINFO_SERVLETSTATUS ("Servlet Status", "Servlet status"),
  PAGE_NAME_APPINFO_WEBRESBUNDLE ("Resource Bundles", "Resource bundles"),
  PAGE_NAME_DATA ("System Daten", "System data"),
  PAGE_NAME_DATA_COUNTRIES ("Länder", "Countries"),
  PAGE_NAME_DATA_CURRENCIES ("Währungen", "Currencies"),
  PAGE_NAME_DATA_LANGUAGES ("Sprachen", "Languages"),
  PAGE_NAME_DATA_TIMEZONES ("Zeitzonen", "Time zones"),
  PAGE_NAME_UTILS ("Werkzeuge", "Utilities"),
  PAGE_NAME_UTILS_HTTP_CLIENT ("http(s) Client", "http(s) client"),
  PAGE_NAME_UTILS_PORT_CHECKER ("Port Checker", "Port checker"),
  PAGE_NAME_SETTINGS ("System Einstellungen", "System settings"),
  PAGE_NAME_SETTINGS_GLOBAL ("Globale Einstellungen", "Global settings"),
  PAGE_NAME_SETTINGS_HTML ("HTML Einstellungen", "HTML settings"),
  PAGE_NAME_SETTINGS_LOG_LEVEL ("Log Level", "Log level"),
  PAGE_NAME_SETTINGS_SMTP ("SMTP-Einstellungen", "SMTP settings"),
  PAGE_NAME_SETTINGS_SYSTEMMESSAGE ("Systemnachricht", "System message");

  private final IMultilingualText m_aTP;

  EWebPageText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nonnull
  public IMultilingualText getAsMLT ()
  {
    return m_aTP;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
