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

import java.nio.charset.Charset;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.charset.CharsetManager;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.email.EmailAddressHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.vendor.VendorInfo;
import com.helger.html.hcapi.IHCNode;
import com.helger.html.hcapi.impl.HCNodeList;
import com.helger.html.hchtml.base.IHCCell;
import com.helger.html.hchtml.impl.HCA;
import com.helger.html.hchtml.impl.HCCheckBox;
import com.helger.html.hchtml.impl.HCEdit;
import com.helger.html.hchtml.impl.HCEditPassword;
import com.helger.html.hchtml.impl.HCRow;
import com.helger.html.hchtml.impl.HCTable;
import com.helger.photon.bootstrap3.EBootstrapIcon;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButton;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.core.smtp.CNamedSMTPSettings;
import com.helger.photon.core.smtp.NamedSMTPSettings;
import com.helger.photon.core.smtp.NamedSMTPSettingsManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCCharsetSelect;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.autonumeric.HCAutoNumericInt;
import com.helger.photon.uictrls.autosize.HCTextAreaAutosize;
import com.helger.photon.uictrls.datatables.DTCol;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.famfam.EFamFamIcon;
import com.helger.smtp.CSMTP;
import com.helger.smtp.EmailGlobalSettings;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.settings.ReadOnlySMTPSettings;
import com.helger.validation.error.FormErrors;
import com.helger.web.port.CNetworkPort;

public class BasePageSettingsSMTP <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageForm <NamedSMTPSettings, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
   BUTTON_CREATE_NEW ("Neue SMTP-Einstellungen anlegen", "Create new SMTP settings"),
   HEADER_NAME ("Name", "Name"),
   HEADER_HOST ("Host-Name", "Host name"),
   HEADER_USERNAME ("Benutzername", "User name"),
   HEADER_DETAILS ("Details von SMTP-Einstellungen ''{0}''", "Details of SMTP settings ''{0}''"),
   LABEL_NAME ("Name", "Name"),
   LABEL_HOSTNAME ("Host", "Host name"),
   LABEL_PORT ("Port", "Port"),
   PORT_DEFAULT ("[Standard Port]", "[default port]"),
   LABEL_USERNAME ("Benutzername", "User name"),
   LABEL_PASSWORD ("Passwort", "Password"),
   LABEL_CHARSET ("Zeichensatz", "Character set"),
   LABEL_SSL ("Verwende SSL?", "Use SSL?"),
   LABEL_STARTTLS ("Verwende STARTTLS?", "Use STARTTLS?"),
   LABEL_CONNECTION_TIMEOUT ("Verbindungs-Timeout (ms)", "Connection timeout (ms)"),
   LABEL_SOCKET_TIMEOUT ("Sende-Timeout (ms)", "Socket timeout (ms)"),
   LABEL_DEBUG_SMTP ("SMTP Debug", "Debug SMTP"),
   MSG_NO_PASSWORD_SET ("keines gesetzt", "none defined"),
   TITLE_CREATE ("Neue SMTP-Einstellungen anlegen", "Create new SMTP settings"),
   TITLE_EDIT ("SMTP-Einstellungen ''{0}'' bearbeiten", "Edit SMTP settings ''{0}''"),
   ERROR_NAME_EMPTY ("Es muss ein Name für diese SMTP-Einstellungen angegeben werden!", "A name must be provided for these SMTP settings!"),
   ERROR_HOSTNAME_EMPTY ("Es muss ein Host-Name oder eine IP-Adresse des SMTP-Servers angegeben werden!", "A name or IP address of the SMTP server must be provided!"),
   ERROR_PORT_INVALID ("Der angegebene Port ist ungültig. Gültige Ports liegen zwischen {0} und {1}!", "The provided port is invalid. Valid ports must be between {0} and {1}!"),
   ERROR_CHARSET_INVALID ("Der ausgewählte Zeichensatz ist ungültig!", "The selected character set is invalid!"),
   ERROR_CONNECTION_TIMEOUT_INVALID ("Das Verbindungs-Timeout muss größer oder gleich 0 sein!", "The connection timeout must be greater or equal to 0!"),
   ERROR_SOCKET_TIMEOUT_INVALID ("Das Verbindungs-Timeout muss größer oder gleich 0 sein!", "The connection timeout must be greater or equal to 0!"),
   SUCCESS_CREATE ("Die neue SMTP-Einstellungen wurden erfolgreich angelegt!", "Successfully created the new SMTP settings!"),
   SUCCESS_EDIT ("Die SMTP-Einstellungen wurde erfolgreich bearbeitet!", "Sucessfully edited the SMTP settings!"),
   DELETE_QUERY ("Sollen die SMTP-Einstellungen ''{0}'' wirklich gelöscht werden?", "Are you sure to delete the SMTP settings ''{0}''?"),
   DELETE_SUCCESS ("Die SMTP-Einstellungen ''{0}'' wurden erfolgreich gelöscht!", "The SMTP settings ''{0}'' were successfully deleted!"),
   DELETE_ERROR ("Fehler beim Löschen der SMTP-Einstellungen ''{0}''!", "Error deleting the SMTP settings ''{0}''!"),
   TITLE_TEST_MAIL ("Test-E-Mail mit den SMTP-Einstellungen ''{0}'' versenden", "Send test email with SMTP settings ''{0}''"),
   MSG_SEND_TEST_MAIL ("Test-E-Mail senden", "Send test mail"),
   BUTTON_SEND_TEST_MAIL ("Test-E-Mail senden", "Send test mail"),
   MSG_SENDER ("Absender", "Sender"),
   MSG_RECEIVER ("Empfänger", "Receiver"),
   MSG_SUBJECT ("Betreff", "Subject"),
   MSG_BODY ("Inhalt", "Body"),
   TEST_SUBJECT ("Test-E-Mail", "Test email"),
   TEST_BODY ("Das ist eine automatisch generierte Test-E-Mail", "This is an automatically generated test email"),
   ERR_SENDER_INVALID ("Es muss eine gültige E-Mail-Adresse angegeben werden.", "A valid email address must be provided"),
   ERR_RECEIVER_INVALID ("Es muss eine gültige E-Mail-Adresse angegeben werden.", "A valid email address must be provided"),
   ERR_SUBJECT_INVALID ("Es muss Betreff angegeben werden.", "An email subject must be provided"),
   ERR_BODY_INVALID ("Es muss eine gültige Nachricht angegeben werden.", "A valid email message must be provided"),
   SUCCESS_TEST_MAIL ("Die Test-Nachricht wurde zum Versand übermittelt.", "The test email message was scheduled for sending.");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgsStatic (this, m_aTP, aContentLocale, aArgs);
    }
  }

  private static final long serialVersionUID = -1l;

  private static final String FIELD_NAME = "name";
  private static final String FIELD_HOSTNAME = "hostname";
  private static final String FIELD_PORT = "port";
  private static final String FIELD_USERNAME = "username";
  private static final String FIELD_PASSWORD = "password";
  private static final String FIELD_CHARSET = "charset";
  private static final String FIELD_SSL = "ssl";
  private static final String FIELD_STARTTLS = "starttls";
  private static final String FIELD_CONNECTION_TIMEOUT = "ctimeout";
  private static final String FIELD_SOCKET_TIMEOUT = "stimeout";
  private static final String FIELD_DEBUG_SMTP = "debugsmtp";

  private static final String FIELD_TEST_SENDER = "tsender";
  private static final String FIELD_TEST_RECEIVER = "treceiver";
  private static final String FIELD_TEST_SUBJECT = "tsubject";
  private static final String FIELD_TEST_BODY = "tbody";

  private static final String DEFAULT_CHARSET = CSMTP.CHARSET_SMTP;
  private static final String ACTION_TEST_MAIL = "testmail";

  private final transient NamedSMTPSettingsManager m_aMgr;

  public BasePageSettingsSMTP (@Nonnull final NamedSMTPSettingsManager aMgr, @Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SETTINGS_SMTP.getAsMLT ());
    m_aMgr = ValueEnforcer.notNull (aMgr, "NamedSMTPSettingsManager");
  }

  public BasePageSettingsSMTP (@Nonnull final NamedSMTPSettingsManager aMgr,
                               @Nonnull @Nonempty final String sID,
                               @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    m_aMgr = ValueEnforcer.notNull (aMgr, "NamedSMTPSettingsManager");
  }

  public BasePageSettingsSMTP (@Nonnull final NamedSMTPSettingsManager aMgr,
                               @Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    m_aMgr = ValueEnforcer.notNull (aMgr, "NamedSMTPSettingsManager");
  }

  public BasePageSettingsSMTP (@Nonnull final NamedSMTPSettingsManager aMgr,
                               @Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    m_aMgr = ValueEnforcer.notNull (aMgr, "NamedSMTPSettingsManager");
  }

  @Override
  protected boolean isObjectLockingEnabled ()
  {
    return true;
  }

  @Override
  @Nullable
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC,
                                         @Nonnull final NamedSMTPSettings aSelectedObject)
  {
    return aSelectedObject.getName ();
  }

  @Override
  @Nullable
  protected NamedSMTPSettings getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return m_aMgr.getSettings (sID);
  }

  private static boolean _canDelete (@Nullable final NamedSMTPSettings aSettings)
  {
    return aSettings != null && !aSettings.getID ().equals (CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_ID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final NamedSMTPSettings aSelectedObject)
  {
    if (eFormAction.isDelete ())
      return _canDelete (aSelectedObject);
    return true;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final NamedSMTPSettings aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final ISMTPSettings aSettings = aSelectedObject.getSMTPSettings ();

    aNodeList.addChild (createActionHeader (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                         aSelectedObject.getName ())));

    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getName ()));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_HOSTNAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSettings.getHostName ()));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_PORT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSettings.getPort () > 0 ? Integer.toString (aSettings.getPort ())
                                                                                    : EText.PORT_DEFAULT.getDisplayText (aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERNAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSettings.getUserName ()));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_PASSWORD.getDisplayText (aDisplayLocale))
                                                 .setCtrl (StringHelper.hasText (aSettings.getPassword ()) ? "***"
                                                                                                           : EText.MSG_NO_PASSWORD_SET.getDisplayText (aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_CHARSET.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSettings.getCharset ()));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_SSL.getDisplayText (aDisplayLocale))
                                                 .setCtrl (EPhotonCoreText.getYesOrNo (aSettings.isSSLEnabled (),
                                                                                       aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_STARTTLS.getDisplayText (aDisplayLocale))
                                                 .setCtrl (EPhotonCoreText.getYesOrNo (aSettings.isSTARTTLSEnabled (),
                                                                                       aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_CONNECTION_TIMEOUT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (Long.toString (aSettings.getConnectionTimeoutMilliSecs ())));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_SOCKET_TIMEOUT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (Long.toString (aSettings.getTimeoutMilliSecs ())));
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final NamedSMTPSettings aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final String sName = aWPEC.getAttributeAsString (FIELD_NAME);
    final String sHostName = aWPEC.getAttributeAsString (FIELD_HOSTNAME);
    final String sPort = aWPEC.getAttributeAsString (FIELD_PORT);
    final int nPort = StringParser.parseInt (sPort, CGlobal.ILLEGAL_UINT);
    final String sUserName = aWPEC.getAttributeAsString (FIELD_USERNAME);
    String sPassword = aWPEC.getAttributeAsString (FIELD_PASSWORD);
    if (sPassword == null && aSelectedObject != null)
    {
      // Password is not changed
      sPassword = aSelectedObject.getSMTPSettings ().getPassword ();
    }
    final String sCharset = aWPEC.getAttributeAsString (FIELD_CHARSET);
    Charset aCharset = null;
    try
    {
      aCharset = CharsetManager.getCharsetFromName (sCharset);
    }
    catch (final IllegalArgumentException ex)
    {}
    final boolean bSSLEnabled = aWPEC.getCheckBoxAttr (FIELD_SSL, EmailGlobalSettings.isUseSSL ());
    final boolean bSTARTTLSEnabled = aWPEC.getCheckBoxAttr (FIELD_STARTTLS, EmailGlobalSettings.isUseSTARTTLS ());
    final long nConnectionTimeoutMS = aWPEC.getAttributeAsLong (FIELD_CONNECTION_TIMEOUT, CGlobal.ILLEGAL_ULONG);
    final long nSocketTimeoutMS = aWPEC.getAttributeAsLong (FIELD_SOCKET_TIMEOUT, CGlobal.ILLEGAL_ULONG);
    final boolean bDebugSMTP = aWPEC.getCheckBoxAttr (FIELD_DEBUG_SMTP, EmailGlobalSettings.isDebugSMTP ());

    if (StringHelper.hasNoText (sName))
      aFormErrors.addFieldError (FIELD_NAME, EText.ERROR_NAME_EMPTY.getDisplayText (aDisplayLocale));

    if (StringHelper.hasNoText (sHostName))
      aFormErrors.addFieldError (FIELD_HOSTNAME, EText.ERROR_HOSTNAME_EMPTY.getDisplayText (aDisplayLocale));

    if (nPort < CNetworkPort.MINIMUM_PORT_NUMBER || nPort > CNetworkPort.MAXIMUM_PORT_NUMBER)
      aFormErrors.addFieldError (FIELD_PORT,
                                 EText.ERROR_PORT_INVALID.getDisplayTextWithArgs (aDisplayLocale,
                                                                                  Integer.toString (CNetworkPort.MINIMUM_PORT_NUMBER),
                                                                                  Integer.toString (CNetworkPort.MAXIMUM_PORT_NUMBER)));

    if (aCharset == null)
      aFormErrors.addFieldError (FIELD_CHARSET, EText.ERROR_CHARSET_INVALID.getDisplayText (aDisplayLocale));

    if (nConnectionTimeoutMS < 0)
      aFormErrors.addFieldError (FIELD_CONNECTION_TIMEOUT,
                                 EText.ERROR_CONNECTION_TIMEOUT_INVALID.getDisplayText (aDisplayLocale));

    if (nSocketTimeoutMS < 0)
      aFormErrors.addFieldError (FIELD_SOCKET_TIMEOUT,
                                 EText.ERROR_CONNECTION_TIMEOUT_INVALID.getDisplayText (aDisplayLocale));

    if (aFormErrors.isEmpty ())
    {
      // All fields are valid -> save
      final ReadOnlySMTPSettings aSMTPSettings = new ReadOnlySMTPSettings (sHostName,
                                                                           nPort,
                                                                           sUserName,
                                                                           sPassword,
                                                                           sCharset,
                                                                           bSSLEnabled,
                                                                           bSTARTTLSEnabled,
                                                                           nConnectionTimeoutMS,
                                                                           nSocketTimeoutMS,
                                                                           bDebugSMTP);

      if (eFormAction.isEdit ())
      {
        // We're editing an existing object
        if (m_aMgr.updateSettings (aSelectedObject.getID (), sName, aSMTPSettings).isChanged ())
          aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_EDIT.getDisplayText (aDisplayLocale)));
      }
      else
      {
        // We're creating a new object
        m_aMgr.addSettings (sName, aSMTPSettings);
        aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_CREATE.getDisplayText (aDisplayLocale)));
      }
    }
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final NamedSMTPSettings aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final ISMTPSettings aSettings = aSelectedObject == null ? null : aSelectedObject.getSMTPSettings ();

    aForm.addChild (createActionHeader (eFormAction.isEdit () ? EText.TITLE_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         aSelectedObject.getName ())
                                                              : EText.TITLE_CREATE.getDisplayText (aDisplayLocale)));

    {
      final String sName = EText.LABEL_NAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_NAME,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getName ())).setPlaceholder (sName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_NAME)));
    }

    {
      final String sHostName = EText.LABEL_HOSTNAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sHostName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_HOSTNAME,
                                                                                           aSettings == null ? null
                                                                                                             : aSettings.getHostName ())).setPlaceholder (sHostName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_HOSTNAME)));
    }

    {
      // Default port: empty string
      final String sPort = EText.LABEL_PORT.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sPort)
                                                   .setCtrl (new HCAutoNumericInt (new RequestField (FIELD_PORT,
                                                                                                     aSettings == null ||
                                                                                                                 aSettings.getPort () < 0 ? ""
                                                                                                                                          : Integer.toString (aSettings.getPort ())),
                                                                                   aDisplayLocale).setMin (CNetworkPort.MINIMUM_PORT_NUMBER)
                                                                                                  .setMax (CNetworkPort.MAXIMUM_PORT_NUMBER)
                                                                                                  .setThousandSeparator (""))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_PORT)));
    }

    {
      final String sUserName = EText.LABEL_USERNAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sUserName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_USERNAME,
                                                                                           aSettings == null ? null
                                                                                                             : aSettings.getUserName ())).setPlaceholder (sUserName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_USERNAME)));
    }

    {
      final String sPassword = EText.LABEL_PASSWORD.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPassword)
                                                   .setCtrl (new HCEditPassword (FIELD_PASSWORD).setPlaceholder (sPassword))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_PASSWORD)));
    }

    {
      final String sCharset = EText.LABEL_CHARSET.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sCharset)
                                                   .setCtrl (new HCCharsetSelect (new RequestField (FIELD_CHARSET,
                                                                                                    aSettings == null ? DEFAULT_CHARSET
                                                                                                                      : aSettings.getCharset ()),
                                                                                  true,
                                                                                  aDisplayLocale))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_CHARSET)));
    }

    {
      final String sSSL = EText.LABEL_SSL.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sSSL)
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_SSL,
                                                                                                      aSettings == null ? EmailGlobalSettings.isUseSSL ()
                                                                                                                        : aSettings.isSSLEnabled ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_SSL)));
    }

    {
      final String sSTARTTLS = EText.LABEL_STARTTLS.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sSTARTTLS)
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_STARTTLS,
                                                                                                      aSettings == null ? EmailGlobalSettings.isUseSTARTTLS ()
                                                                                                                        : aSettings.isSTARTTLSEnabled ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_STARTTLS)));
    }

    {
      final String sConnectionTimeout = EText.LABEL_CONNECTION_TIMEOUT.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sConnectionTimeout)
                                                   .setCtrl (new HCAutoNumericInt (new RequestField (FIELD_CONNECTION_TIMEOUT,
                                                                                                     aSettings == null ? EmailGlobalSettings.getConnectionTimeoutMilliSecs ()
                                                                                                                       : aSettings.getConnectionTimeoutMilliSecs ()),
                                                                                   aDisplayLocale).setMin (0)
                                                                                                  .setThousandSeparator (""))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_CONNECTION_TIMEOUT)));
    }

    {
      final String sSocketTimeout = EText.LABEL_SOCKET_TIMEOUT.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sSocketTimeout)
                                                   .setCtrl (new HCAutoNumericInt (new RequestField (FIELD_SOCKET_TIMEOUT,
                                                                                                     aSettings == null ? EmailGlobalSettings.getTimeoutMilliSecs ()
                                                                                                                       : aSettings.getTimeoutMilliSecs ()),
                                                                                   aDisplayLocale).setMin (0)
                                                                                                  .setThousandSeparator (""))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_SOCKET_TIMEOUT)));
    }

    {
      final String sDebugSMTP = EText.LABEL_DEBUG_SMTP.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sDebugSMTP)
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_DEBUG_SMTP,
                                                                                                      aSettings == null ? EmailGlobalSettings.isDebugSMTP ()
                                                                                                                        : aSettings.isDebugSMTP ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_DEBUG_SMTP)));
    }
  }

  @Nullable
  @OverrideOnDemand
  protected IHCNode getTestMailIcon ()
  {
    return EFamFamIcon.EMAIL_GO.getAsNode ();
  }

  @Override
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final NamedSMTPSettings aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     aSelectedObject.getName ())));
  }

  @Override
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final NamedSMTPSettings aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (m_aMgr.removeSettings (aSelectedObject.getID ()).isChanged ())
      aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            aSelectedObject.getName ())));
    else
      aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                        aSelectedObject.getName ())));
  }

  @Override
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC,
                                         @Nullable final NamedSMTPSettings aSelectedObject)
  {
    if (aWPEC.hasAction (ACTION_TEST_MAIL) && aSelectedObject != null)
    {
      final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
      final HCNodeList aNodeList = aWPEC.getNodeList ();
      final FormErrors aFormErrors = new FormErrors ();

      if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
      {
        final String sSender = aWPEC.getAttributeAsString (FIELD_TEST_SENDER);
        final String sReceiver = aWPEC.getAttributeAsString (FIELD_TEST_RECEIVER);
        final String sSubject = aWPEC.getAttributeAsString (FIELD_TEST_SUBJECT);
        final String sBody = aWPEC.getAttributeAsString (FIELD_TEST_BODY);

        if (!EmailAddressHelper.isValid (sSender))
          aFormErrors.addFieldError (FIELD_TEST_SENDER, EText.ERR_SENDER_INVALID.getDisplayText (aDisplayLocale));
        if (!EmailAddressHelper.isValid (sReceiver))
          aFormErrors.addFieldError (FIELD_TEST_RECEIVER, EText.ERR_RECEIVER_INVALID.getDisplayText (aDisplayLocale));
        if (StringHelper.hasNoText (sSubject))
          aFormErrors.addFieldError (FIELD_TEST_SUBJECT, EText.ERR_SUBJECT_INVALID.getDisplayText (aDisplayLocale));
        if (StringHelper.hasNoText (sBody))
          aFormErrors.addFieldError (FIELD_TEST_BODY, EText.ERR_BODY_INVALID.getDisplayText (aDisplayLocale));

        if (aFormErrors.isEmpty ())
        {
          final EmailData aMailData = new EmailData (EEmailType.TEXT);
          aMailData.setFrom (sSender);
          aMailData.setTo (sReceiver);
          aMailData.setSubject (sSubject);
          aMailData.setBody (sBody);
          ScopedMailAPI.getInstance ().queueMail (aSelectedObject.getSMTPSettings (), aMailData);

          aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_TEST_MAIL.getDisplayText (aDisplayLocale)));

          return true;
        }
      }

      // Show question
      final BootstrapForm aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));

      aForm.addChild (createActionHeader (EText.TITLE_TEST_MAIL.getDisplayTextWithArgs (aDisplayLocale,
                                                                                        aSelectedObject.getName ())));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.MSG_SENDER.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_TEST_SENDER,
                                                                                           VendorInfo.getVendorEmail ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_TEST_SENDER)));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.MSG_RECEIVER.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_TEST_RECEIVER,
                                                                                           VendorInfo.getVendorEmail ())))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_TEST_RECEIVER)));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.MSG_SUBJECT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_TEST_SUBJECT,
                                                                                           EText.TEST_SUBJECT.getDisplayText (aDisplayLocale))))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_TEST_SUBJECT)));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.MSG_BODY.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCTextAreaAutosize (new RequestField (FIELD_TEST_BODY,
                                                                                                       EText.TEST_BODY.getDisplayText (aDisplayLocale))).setRows (5))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_TEST_BODY)));

      final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_TEST_MAIL);
      aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
      aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
      aToolbar.addSubmitButton (EText.BUTTON_SEND_TEST_MAIL.getDisplayText (aDisplayLocale), EDefaultIcon.YES);
      aToolbar.addButtonCancel (aDisplayLocale);
      return false;
    }
    return true;
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Toolbar on top
    final IButtonToolbar <?> aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));
    aToolbar.addChild (new BootstrapButton ().addChild (EWebPageText.PAGE_NAME_MONITORING_FAILED_MAILS.getDisplayText (aDisplayLocale))
                                             .setOnClick (aWPEC.getLinkToMenuItem (BootstrapPagesMenuConfigurator.MENU_ADMIN_MONITORING_FAILEDMAILS))
                                             .setIcon (EBootstrapIcon.ARROW_RIGHT));

    final HCTable aTable = new HCTable (new DTCol (EText.HEADER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_HOST.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_USERNAME.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID ());
    for (final NamedSMTPSettings aCurObject : m_aMgr.getAllSettings ().values ())
    {
      final ISMTPSettings aSettings = aCurObject.getSMTPSettings ();
      final ISimpleURL aViewLink = createViewURL (aWPEC, aCurObject);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (aCurObject.getName ()));
      aRow.addCell (aSettings.getHostName () + (aSettings.getPort () > 0 ? ":" + aSettings.getPort () : ""));
      aRow.addCell (aSettings.getUserName ());

      final IHCCell <?> aActionCell = aRow.addCell ();
      aActionCell.addChild (createEditLink (aWPEC,
                                            aCurObject,
                                            EWebPageText.OBJECT_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                             aCurObject.getName ())));
      if (_canDelete (aCurObject))
        aActionCell.addChild (createDeleteLink (aWPEC,
                                                aCurObject,
                                                EWebPageText.OBJECT_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                   aCurObject.getName ())));
      else
        aActionCell.addChild (createEmptyAction ());

      aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                          .add (CPageParam.PARAM_ACTION, ACTION_TEST_MAIL)
                                          .add (CPageParam.PARAM_OBJECT,
                                                aCurObject.getID ())).setTitle (EText.MSG_SEND_TEST_MAIL.getDisplayText (aDisplayLocale))
                                                                     .addChild (getTestMailIcon ()));
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
