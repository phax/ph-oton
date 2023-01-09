/*
 * Copyright (C) 2018-2023 Philip Helger (www.helger.com)
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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsLogLevel.EText;
import com.helger.photon.bootstrap4.traits.IHCBootstrap4Trait;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCExtSelect;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Keep in separate class, so that the stuff is only loaded once we checked
 * Log4J is present.
 *
 * @author Philip Helger
 */
final class InternalLog4J2Handler implements IHCBootstrap4Trait
{
  enum ELevel implements IHasID <String>, IHasDisplayName
  {
    /**
     * No events will be logged.
     */
    OFF (Level.OFF),

    /**
     * A severe error that will prevent the application from continuing.
     */
    FATAL (Level.FATAL),

    /**
     * An error in the application, possibly recoverable.
     */
    ERROR (Level.ERROR),

    /**
     * An event that might possible lead to an error.
     */
    WARN (Level.WARN),

    /**
     * An event for informational purposes.
     */
    INFO (Level.INFO),

    /**
     * A general debugging event.
     */
    DEBUG (Level.DEBUG),

    /**
     * A fine-grained debug message, typically capturing the flow through the
     * application.
     */
    TRACE (Level.TRACE),

    /**
     * All events should be logged.
     */
    ALL (Level.ALL);

    private final Level m_aLevel;

    ELevel (@Nonnull final Level aLevel)
    {
      m_aLevel = aLevel;
    }

    @Nonnull
    @Nonempty
    public String getID ()
    {
      return "L" + Integer.toString (m_aLevel.intLevel ());
    }

    @Nonnull
    @Nonempty
    public String getDisplayName ()
    {
      return name () + " (" + m_aLevel.intLevel () + ")";
    }

    @Nonnull
    public Level getLevel ()
    {
      return m_aLevel;
    }

    @Nullable
    public static ELevel getFromIDOrNull (@Nullable final String sID)
    {
      return EnumHelper.getFromIDOrNull (ELevel.class, sID);
    }

    @Nullable
    public static ELevel getFromLevelOrNull (@Nullable final Level aLevel)
    {
      if (aLevel != null)
        for (final ELevel e : values ())
          if (e.m_aLevel.equals (aLevel))
            return e;
      return null;
    }
  }

  private static class LevelSelect extends HCExtSelect
  {
    public LevelSelect (@Nonnull final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
    {
      super (aRF);
      for (final ELevel e : ELevel.values ())
        addOption (e.getID (), e.getDisplayName ());
      addOptionPleaseSelect (aDisplayLocale);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger (InternalLog4J2Handler.class);
  private static final String FIELD_NEW_LEVEL = "level";

  public void handle (@Nonnull final IWebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (info (HCExtHelper.nl2divList (EText.MSG_HEADER.getDisplayText (aDisplayLocale))));

    final FormErrorList aFormErrors = new FormErrorList ();

    final LoggerContext aLoggerContext = LoggerContext.getContext (false);
    final LoggerConfig aLoggerConfig = aLoggerContext.getConfiguration ().getRootLogger ();
    final Level aExistingLevel = aLoggerConfig.getLevel ();
    ELevel eExistingLevel = ELevel.getFromLevelOrNull (aExistingLevel);

    if (aWPEC.hasAction (CPageParam.ACTION_PERFORM))
    {
      final String sNewLevel = aWPEC.params ().getAsStringTrimmed (FIELD_NEW_LEVEL);
      final ELevel eNewLevel = ELevel.getFromIDOrNull (sNewLevel);

      if (StringHelper.hasNoText (sNewLevel))
        aFormErrors.addFieldError (FIELD_NEW_LEVEL, EText.MSG_ERR_NO_LEVEL.getDisplayText (aDisplayLocale));
      else
        if (eNewLevel == null)
          aFormErrors.addFieldError (FIELD_NEW_LEVEL, EText.MSG_ERR_INVALID_LEVEL.getDisplayText (aDisplayLocale));

      if (aFormErrors.isEmpty ())
      {
        if (eNewLevel == eExistingLevel)
        {
          LOGGER.info ("No change in log levels. Sticking with " + eExistingLevel);
          aNodeList.addChild (info (EText.MSG_NO_CHANGE.getDisplayTextWithArgs (aDisplayLocale, eExistingLevel.getDisplayName ())));
        }
        else
        {
          // Log before in case we're changing to something less verbose than
          // info
          LOGGER.info ("Changing log levels from " + eExistingLevel + " to " + eNewLevel);
          Configurator.setRootLevel (eNewLevel.getLevel ());
          AuditHelper.onAuditExecuteSuccess ("change-log-level", eExistingLevel.getDisplayName (), eNewLevel.getDisplayName ());
          aNodeList.addChild (info (EText.MSG_CHANGE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                     eExistingLevel.getDisplayName (),
                                                                                     eNewLevel.getDisplayName ())));
          // Log here in case we're changing to something from less verbose than
          // info
          LOGGER.info ("Finished changing log levels from " + eExistingLevel + " to " + eNewLevel);

          // For display purposes
          eExistingLevel = eNewLevel;
        }
      }
    }

    // Show form
    final BootstrapForm aForm = aNodeList.addAndReturnChild (new BootstrapForm (aWPEC));

    if (eExistingLevel != null)
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EXISTING_LEVEL.getDisplayText (aDisplayLocale))
                                                   .setCtrl (badgeInfo (eExistingLevel.getDisplayName ())));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.MSG_FIELD_LEVEL.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new LevelSelect (new RequestField (FIELD_NEW_LEVEL,
                                                                                              eExistingLevel != null ? eExistingLevel.getID ()
                                                                                                                     : null),
                                                                            aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_NEW_LEVEL)));
    aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
    aForm.addChild (new BootstrapSubmitButton ().addChild (EText.MSG_SUBMIT_BUTTON.getDisplayText (aDisplayLocale)));
  }
}
