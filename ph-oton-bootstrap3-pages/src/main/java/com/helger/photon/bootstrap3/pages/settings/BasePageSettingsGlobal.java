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
package com.helger.photon.bootstrap3.pages.settings;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.GlobalDebug;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.CHCParam;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCCheckBox;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTableForm;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.html.table.IHCTableForm;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.smtp.EmailGlobalSettings;

/**
 * Page with global basic settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSettingsGlobal <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
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
    MSG_EMAIL_TRANSPORT_LISTENER ("TransportListener", "TransportListener"),
    MSG_EMAIL_EMAILDATA_TRANSPORT_LISTENER ("EmailDataTransportListener", "EmailDataTransportListener"),
    MSG_NONE ("keiner", "none"),
    MSG_CHANGE_SUCCESS ("Die Einstellungen wurden erfolgreich gespeichert.", "Changes were changed successfully.");

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
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

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID,
                                 @Nonnull final String sName,
                                 @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSettingsGlobal (@Nonnull @Nonempty final String sID,
                                 @Nonnull final IReadonlyMultiLingualText aName,
                                 @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (aWPEC.hasAction (CHCParam.ACTION_SAVE))
    {
      // Save changes
      final boolean bGlobalDebug = aWPEC.getCheckBoxAttr (FIELD_GLOBAL_DEBUG, GlobalDebug.isDebugMode ());
      final boolean bGlobalProduction = aWPEC.getCheckBoxAttr (FIELD_GLOBAL_PRODUCTION, GlobalDebug.isProductionMode ());

      GlobalDebug.setDebugModeDirect (bGlobalDebug);
      GlobalDebug.setProductionModeDirect (bGlobalProduction);

      aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.MSG_CHANGE_SUCCESS.getDisplayText (aDisplayLocale)));
    }

    final AbstractHCForm <?> aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));
    final ITabBox <?> aTabBox = new BootstrapTabBox ();

    // GlobalDebug
    {
      final IHCTableForm <?> aTable = new BootstrapTableForm (new HCCol (170), HCCol.star ());
      aTable.createItemRow ()
            .setLabel (EText.MSG_GLOBAL_DEBUG.getDisplayText (aDisplayLocale))
            .setCtrl (new HCCheckBox (FIELD_GLOBAL_DEBUG, GlobalDebug.isDebugMode ()));
      aTable.createItemRow ()
            .setLabel (EText.MSG_GLOBAL_PRODUCTION.getDisplayText (aDisplayLocale))
            .setCtrl (new HCCheckBox (FIELD_GLOBAL_PRODUCTION, GlobalDebug.isProductionMode ()));
      aTabBox.addTab (EText.MSG_HEADER_GLOBAL.getDisplayText (aDisplayLocale), aTable);
    }

    // Email global settings
    {
      final IHCTableForm <?> aTable = new BootstrapTableForm (new HCCol (200), HCCol.star ());
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_MAILQUEUE_LENGTH.getDisplayText (aDisplayLocale))
            .setCtrl (Long.toString (EmailGlobalSettings.getMaxMailQueueLength ()));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_MAX_SEND_COUNT.getDisplayText (aDisplayLocale))
            .setCtrl (Long.toString (EmailGlobalSettings.getMaxMailSendCount ()));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_USE_SSL.getDisplayText (aDisplayLocale))
            .setCtrl (EPhotonCoreText.getYesOrNo (EmailGlobalSettings.isUseSSL (), aDisplayLocale));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_USE_STARTTLS.getDisplayText (aDisplayLocale))
            .setCtrl (EPhotonCoreText.getYesOrNo (EmailGlobalSettings.isUseSTARTTLS (), aDisplayLocale));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_CONNECTION_TIMEOUT.getDisplayText (aDisplayLocale))
            .setCtrl (Long.toString (EmailGlobalSettings.getConnectionTimeoutMilliSecs ()) + "ms");
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_SOCKET_TIMEOUT.getDisplayText (aDisplayLocale))
            .setCtrl (Long.toString (EmailGlobalSettings.getTimeoutMilliSecs ()) + "ms");
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_CONNECTION_LISTENER.getDisplayText (aDisplayLocale))
            .setCtrl (EmailGlobalSettings.getConnectionListener () == null ? HCEM.create (EText.MSG_NONE.getDisplayText (aDisplayLocale))
                                                                          : new HCTextNode (String.valueOf (EmailGlobalSettings.getConnectionListener ())));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_TRANSPORT_LISTENER.getDisplayText (aDisplayLocale))
            .setCtrl (EmailGlobalSettings.getTransportListener () == null ? HCEM.create (EText.MSG_NONE.getDisplayText (aDisplayLocale))
                                                                         : new HCTextNode (String.valueOf (EmailGlobalSettings.getTransportListener ())));
      aTable.createItemRow ()
            .setLabel (EText.MSG_EMAIL_EMAILDATA_TRANSPORT_LISTENER.getDisplayText (aDisplayLocale))
            .setCtrl (EmailGlobalSettings.getEmailDataTransportListener () == null ? HCEM.create (EText.MSG_NONE.getDisplayText (aDisplayLocale))
                                                                                  : new HCTextNode (String.valueOf (EmailGlobalSettings.getEmailDataTransportListener ())));
      aTabBox.addTab (EText.MSG_HEADER_EMAIL.getDisplayText (aDisplayLocale), aTable);
    }

    aForm.addChild (aTabBox);

    final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addHiddenField (CHCParam.PARAM_ACTION, CHCParam.ACTION_SAVE);
    aToolbar.addSubmitButtonSave (aDisplayLocale);
  }
}
