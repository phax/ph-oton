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
import javax.mail.event.ConnectionListener;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.form.BootstrapViewForm;
import com.helger.photon.bootstrap4.nav.BootstrapTabBox;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.smtp.EmailGlobalSettings;
import com.helger.smtp.listener.IEmailDataTransportListener;

/**
 * Page with global basic settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSettingsGlobal <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_HEADER_GLOBAL ("Globale Einstellungen", "Global settings"),
    MSG_GLOBAL_DEBUG ("Debug-Modus", "Debug mode"),
    MSG_GLOBAL_PRODUCTION ("Produktions-Modus", "Production mode"),
    MSG_HEADER_EMAIL ("E-Mail Standard Einstellungen", "Email default settings"),
    MSG_EMAIL_MAILQUEUE_LENGTH ("E-Mail Queue Länge", "Email queue length"),
    MSG_EMAIL_MAX_SEND_COUNT ("Maximal versendete Mails", "Maximum send count"),
    MSG_EMAIL_USE_SSL ("SSL", "SSL"),
    MSG_EMAIL_USE_STARTTLS ("STARTTLS", "STARTTLS"),
    MSG_EMAIL_CONNECTION_TIMEOUT ("Verbindungs-Timeout", "Connection timeout"),
    MSG_EMAIL_SOCKET_TIMEOUT ("Socket-Timeout", "Socket timeout"),
    MSG_EMAIL_CONNECTION_LISTENER ("ConnectionListener", "ConnectionListener"),
    MSG_EMAIL_EMAILDATA_TRANSPORT_LISTENER ("EmailDataTransportListener", "EmailDataTransportListener"),
    MSG_NONE ("keiner", "none"),
    MSG_CHANGE_SUCCESS ("Die Einstellungen wurden erfolgreich gespeichert.", "Changes were changed successfully.");

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

  private static final String FIELD_GLOBAL_DEBUG = "global-debug";
  private static final String FIELD_GLOBAL_PRODUCTION = "global-production";

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SETTINGS_GLOBAL.getAsMLT ());
  }

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID,
                                 @Nonnull final IMultilingualText aName,
                                 @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (aWPEC.hasAction (CPageParam.ACTION_SAVE))
    {
      // Save changes
      final boolean bGlobalDebug = aWPEC.params ().isCheckBoxChecked (FIELD_GLOBAL_DEBUG, GlobalDebug.isDebugMode ());
      final boolean bGlobalProduction = aWPEC.params ().isCheckBoxChecked (FIELD_GLOBAL_PRODUCTION, GlobalDebug.isProductionMode ());

      GlobalDebug.setDebugModeDirect (bGlobalDebug);
      GlobalDebug.setProductionModeDirect (bGlobalProduction);

      aWPEC.postRedirectGetInternal (success (EText.MSG_CHANGE_SUCCESS.getDisplayText (aDisplayLocale)));
    }

    final BootstrapForm aForm0 = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    // GlobalDebug
    {
      final BootstrapViewForm aForm = new BootstrapViewForm ();
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_GLOBAL_DEBUG.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_GLOBAL_DEBUG,
                                                                                                      GlobalDebug.isDebugMode ()))));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_GLOBAL_PRODUCTION.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_GLOBAL_PRODUCTION,
                                                                                                      GlobalDebug.isProductionMode ()))));
      aTabBox.addTab ("global", EText.MSG_HEADER_GLOBAL.getDisplayText (aDisplayLocale), aForm);
    }

    // Email global settings
    {
      final BootstrapViewForm aForm = new BootstrapViewForm ();
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_MAILQUEUE_LENGTH.getDisplayText (aDisplayLocale))
                                                   .setCtrl (Long.toString (EmailGlobalSettings.getMaxMailQueueLength ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_MAX_SEND_COUNT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (Long.toString (EmailGlobalSettings.getMaxMailSendCount ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_USE_SSL.getDisplayText (aDisplayLocale))
                                                   .setCtrl (EPhotonCoreText.getYesOrNo (EmailGlobalSettings.isUseSSL (), aDisplayLocale)));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_USE_STARTTLS.getDisplayText (aDisplayLocale))
                                                   .setCtrl (EPhotonCoreText.getYesOrNo (EmailGlobalSettings.isUseSTARTTLS (),
                                                                                         aDisplayLocale)));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_CONNECTION_TIMEOUT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (Long.toString (EmailGlobalSettings.getConnectionTimeoutMilliSecs ()) + "ms"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_SOCKET_TIMEOUT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (Long.toString (EmailGlobalSettings.getTimeoutMilliSecs ()) + "ms"));
      {
        final HCNodeList aCtrl = new HCNodeList ();
        for (final ConnectionListener aListener : EmailGlobalSettings.getAllConnectionListeners ())
          aCtrl.addChild (div (String.valueOf (aListener)));
        if (!aCtrl.hasChildren ())
          aCtrl.addChild (em (EText.MSG_NONE.getDisplayText (aDisplayLocale)));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_CONNECTION_LISTENER.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aCtrl));
      }
      {
        final HCNodeList aCtrl = new HCNodeList ();
        for (final IEmailDataTransportListener aListener : EmailGlobalSettings.getAllEmailDataTransportListeners ())
          aCtrl.addChild (div (String.valueOf (aListener)));
        if (!aCtrl.hasChildren ())
          aCtrl.addChild (em (EText.MSG_NONE.getDisplayText (aDisplayLocale)));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_EMAILDATA_TRANSPORT_LISTENER.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aCtrl));
      }
      aTabBox.addTab ("email", EText.MSG_HEADER_EMAIL.getDisplayText (aDisplayLocale), aForm);
    }

    aForm0.addChild (aTabBox);

    final BootstrapButtonToolbar aToolbar = aForm0.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_SAVE);
    aToolbar.addSubmitButtonSave (aDisplayLocale);
  }
}
