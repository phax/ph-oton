/**
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
package com.helger.photon.bootstrap4.pages.settings;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Base page to be able to change the log level for all logs during execution
 * time.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSettingsLogLevel <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    MSG_NO_LOG4J2 ("Log4J2 ist nicht im Classpath", "Log4J2 is not in the classpath"),
    MSG_HEADER ("Hier kann das Log4J 2.x Log-Level dynamisch geändert werden.\n" +
                "Hinweis: das überschreibt alle benutzerdefinierten Log-Levels.\n" +
                "Hinweis: diese Einstellung gilt nur für die Laufzeit des Programms.",
                "On this page you can change the root log level of the underlying Log4J 2.x logging on the fly.\n" +
                                                                                       "Note: this will overwrite any custom log levels.\n" +
                                                                                       "Note: the setting is only active during this application session."),
    MSG_ERR_NO_LEVEL ("Es muss ein Log-Level ausgewählt werden", "A log level must be selected"),
    MSG_ERR_INVALID_LEVEL ("Das angegebene Log-Level ist ungültig", "The provided log level is invalid"),
    MSG_NO_CHANGE ("Das Log-Level wurde nicht geändert. Es ist noch immer ''{0}''", "The log level was not changed. It is still ''{0}''"),
    MSG_CHANGE_SUCCESS ("Das Log-Level wurde von ''{0}'' auf ''{1}'' geändert.", "The log level was changed from ''{0}'' to ''{1}''"),
    MSG_EXISTING_LEVEL ("Derzeitiges Log-Level", "Existing log level"),
    MSG_FIELD_LEVEL ("Neues Log-Level", "New log level"),
    MSG_SUBMIT_BUTTON ("Log-Level ändern", "Change log level");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageSettingsLogLevel (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SETTINGS_LOG_LEVEL.getAsMLT ());
  }

  public BasePageSettingsLogLevel (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSettingsLogLevel (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSettingsLogLevel (@Nonnull @Nonempty final String sID,
                                   @Nonnull final IMultilingualText aName,
                                   @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    if (GenericReflection.getClassFromNameSafe ("org.apache.logging.log4j.core.config.Configurator") != null)
    {
      new InternalLog4J2Handler ().handle (aWPEC);
    }
    else
    {
      final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
      aWPEC.getNodeList ().addChild (warn (EText.MSG_NO_LOG4J2.getDisplayText (aDisplayLocale)));
    }
  }
}
