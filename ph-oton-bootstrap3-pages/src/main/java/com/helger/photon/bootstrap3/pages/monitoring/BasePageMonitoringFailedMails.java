/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCA_MailTo;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
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
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandler;
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandlerDelete;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.smtp.data.IEmailAttachment;
import com.helger.smtp.data.IEmailAttachmentList;
import com.helger.smtp.data.IEmailData;
import com.helger.smtp.failed.FailedMailData;
import com.helger.smtp.failed.FailedMailQueue;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.transport.MailSendDetails;
import com.helger.smtp.transport.MailTransportError;

/**
 * Show all failed mails.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageMonitoringFailedMails <WPECTYPE extends IWebPageExecutionContext>
                                           extends AbstractBootstrapWebPageForm <FailedMailData, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayTextWithArgs
  {
    MSG_ID ("ID", "ID"),
    MSG_ERROR_DT ("Fehler-Datum", "Error date"),
    MSG_SMTP_SETTINGS ("SMTP-Einstellungen", "SMTP settings"),
    MSG_SENDING_DT ("Sendedatum", "Sending date"),
    MSG_EMAIL_TYPE ("E-Mail Typ", "Email type"),
    MSG_FROM ("Von", "From"),
    MSG_REPLY_TO ("Antwort an", "Reply to"),
    MSG_TO ("An", "To"),
    MSG_CC ("Cc", "Cc"),
    MSG_BCC ("Bcc", "Bcc"),
    MSG_SUBJECT ("Betreff", "Subject"),
    MSG_BODY ("Inhalt", "Body"),
    MSG_ATTACHMENTS ("Beilagen", "Attachments"),
    MSG_ERROR ("Fehlermeldung", "Error message"),
    RESENT_SUCCESS ("Das E-Mail wurde erneut versendet.", "The email was scheduled for resending."),
    RESENT_ALL_SUCCESS_1 ("Es wurde 1 E-Mail erneut versendet.", "1 email was scheduled for resending."),
    RESENT_ALL_SUCCESS_N ("Es wurden {0} E-Mails erneut versendet.", "{0} emails were scheduled for resending."),
    DELETE_QUERY ("Soll das E-Mail wirklich gelöscht werden?", "Should the email really be deleted?"),
    DELETE_SUCCESS ("Das E-Mail wurde erfolgreich gelöscht.", "The email was successfully deleted."),
    DELETE_ALL_SUCCESS_1 ("Es wurde 1 E-Mail erfolgreich gelöscht.", "1 email was successfully deleted."),
    DELETE_ALL_SUCCESS_N ("Es wurden {0} E-Mails erfolgreich gelöscht.", "{0} emails were successfully deleted."),
    MSG_BUTTON_RESEND_DEFAULT_SETTINGS ("Erneut versenden (mit aktuellen SMTP-Einstellungen)",
                                        "Resend (with current SMTP settings)"),
    MSG_BUTTON_RESEND_ALL_DEFAULT_SETTINGS ("Alle erneut versenden (mit aktuellen SMTP-Einstellungen)",
                                            "Resend all (with current SMTP settings)");

    @Nonnull
    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private static final Logger s_aLogger = LoggerFactory.getLogger (BasePageMonitoringFailedMails.class);
  private static final String ACTION_RESEND = "resend";
  private static final String ACTION_RESEND_DEFAULT_SETTINGS = "resend-default-settings";
  private static final String ACTION_RESEND_ALL = "resend-all";
  private static final String ACTION_RESEND_ALL_DEFAULT_SETTINGS = "resend-all-default-settings";

  private final FailedMailQueue m_aFailedMailQueue;

  private void _init ()
  {
    setDeleteHandler (new AbstractBootstrapWebPageActionHandlerDelete <FailedMailData, WPECTYPE> ()
    {
      @Override
      protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                      @Nonnull final BootstrapForm aForm,
                                      @Nonnull final FailedMailData aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayText (aDisplayLocale)));
      }

      @Override
      protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final FailedMailData aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

        // Delete a single failed mail without querying
        if (m_aFailedMailQueue.remove (aSelectedObject.getID ()) != null)
        {
          s_aLogger.info ("Deleted single failed mail with ID " + aSelectedObject.getID () + "!");
          aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayText (aDisplayLocale)));
        }
      }
    });
    addCustomHandler (CPageParam.ACTION_DELETE_ALL,
                      new AbstractBootstrapWebPageActionHandler <FailedMailData, WPECTYPE> (false)
                      {
                        public boolean handleAction (final WPECTYPE aWPEC, final FailedMailData aSelectedObject)
                        {
                          final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

                          // Delete all failed mails
                          final List <FailedMailData> aFailedMails = m_aFailedMailQueue.removeAll ();
                          if (!aFailedMails.isEmpty ())
                          {
                            s_aLogger.info ("Deleted " + aFailedMails.size () + " failed mails!");
                            final String sSuccessMsg = aFailedMails.size () == 1 ? EText.DELETE_ALL_SUCCESS_1.getDisplayText (aDisplayLocale)
                                                                                 : EText.DELETE_ALL_SUCCESS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                      Integer.toString (aFailedMails.size ()));
                            aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (sSuccessMsg));
                          }
                          return true;
                        }
                      });
    final AbstractBootstrapWebPageActionHandler <FailedMailData, WPECTYPE> aResendHdl = new AbstractBootstrapWebPageActionHandler <FailedMailData, WPECTYPE> (true)
    {
      public boolean handleAction (final WPECTYPE aWPEC, final FailedMailData aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

        // Resend a single failed mail
        final FailedMailData aFailedMailData = m_aFailedMailQueue.remove (aSelectedObject.getID ());
        if (aFailedMailData != null)
        {
          final ISMTPSettings aDefaultSMTPSettings = aWPEC.hasAction (ACTION_RESEND_DEFAULT_SETTINGS) ? PhotonCoreManager.getSMTPSettingsMgr ()
                                                                                                                         .getDefaultSMTPSettings ()
                                                                                                      : null;
          s_aLogger.info ("Trying to resend single failed mail with ID " +
                          aFailedMailData.getID () +
                          (aDefaultSMTPSettings != null ? " with default settings" : "") +
                          "!");

          // Main resend
          final ISMTPSettings aSMTPSettings = aDefaultSMTPSettings != null ? aDefaultSMTPSettings
                                                                           : aFailedMailData.getSMTPSettings ();
          ScopedMailAPI.getInstance ().queueMail (aSMTPSettings, aFailedMailData.getEmailData ());

          // Success message
          aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.RESENT_SUCCESS.getDisplayText (aDisplayLocale)));
        }
        return true;
      }
    };
    addCustomHandler (ACTION_RESEND, aResendHdl);
    addCustomHandler (ACTION_RESEND_DEFAULT_SETTINGS, aResendHdl);
    final AbstractBootstrapWebPageActionHandler <FailedMailData, WPECTYPE> aResendAllHdl = new AbstractBootstrapWebPageActionHandler <FailedMailData, WPECTYPE> (false)
    {
      public boolean handleAction (final WPECTYPE aWPEC, final FailedMailData aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

        // Resend all failed mails
        final List <FailedMailData> aFailedMails = m_aFailedMailQueue.removeAll ();
        if (!aFailedMails.isEmpty ())
        {
          final ISMTPSettings aDefaultSMTPSettings = aWPEC.hasAction (ACTION_RESEND_ALL_DEFAULT_SETTINGS) ? PhotonCoreManager.getSMTPSettingsMgr ()
                                                                                                                             .getDefaultSMTPSettings ()
                                                                                                          : null;
          s_aLogger.info ("Trying to resend " +
                          aFailedMails.size () +
                          " failed mails" +
                          (aDefaultSMTPSettings != null ? " with default settings" : "") +
                          "!");

          // Main resend
          for (final FailedMailData aFailedMailData : aFailedMails)
          {
            ScopedMailAPI.getInstance ()
                         .queueMail (aDefaultSMTPSettings != null ? aDefaultSMTPSettings
                                                                  : aFailedMailData.getSMTPSettings (),
                                     aFailedMailData.getEmailData ());
          }

          // Success message
          final String sSuccessMsg = aFailedMails.size () == 1 ? EText.RESENT_ALL_SUCCESS_1.getDisplayText (aDisplayLocale)
                                                               : EText.RESENT_ALL_SUCCESS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                    Integer.toString (aFailedMails.size ()));
          aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (sSuccessMsg));
        }
        return true;
      }
    };
    addCustomHandler (ACTION_RESEND_ALL, aResendAllHdl);
    addCustomHandler (ACTION_RESEND_ALL_DEFAULT_SETTINGS, aResendAllHdl);
  }

  public BasePageMonitoringFailedMails (@Nonnull @Nonempty final String sID,
                                        @Nonnull final FailedMailQueue aFailedMailQueue)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_FAILED_MAILS.getAsMLT ());
    m_aFailedMailQueue = ValueEnforcer.notNull (aFailedMailQueue, "FailedMailQueue");
    _init ();
  }

  public BasePageMonitoringFailedMails (@Nonnull @Nonempty final String sID,
                                        @Nonnull final String sName,
                                        @Nonnull final FailedMailQueue aFailedMailQueue)
  {
    super (sID, sName);
    m_aFailedMailQueue = ValueEnforcer.notNull (aFailedMailQueue, "FailedMailQueue");
    _init ();
  }

  public BasePageMonitoringFailedMails (@Nonnull @Nonempty final String sID,
                                        @Nonnull final String sName,
                                        @Nullable final String sDescription,
                                        @Nonnull final FailedMailQueue aFailedMailQueue)
  {
    super (sID, sName, sDescription);
    m_aFailedMailQueue = ValueEnforcer.notNull (aFailedMailQueue, "FailedMailQueue");
    _init ();
  }

  public BasePageMonitoringFailedMails (@Nonnull @Nonempty final String sID,
                                        @Nonnull final IMultilingualText aName,
                                        @Nullable final IMultilingualText aDescription,
                                        @Nonnull final FailedMailQueue aFailedMailQueue)
  {
    super (sID, aName, aDescription);
    m_aFailedMailQueue = ValueEnforcer.notNull (aFailedMailQueue, "FailedMailQueue");
    _init ();
  }

  @Override
  protected boolean isObjectLockingEnabled ()
  {
    return true;
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final FailedMailData aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return false;
    return true;
  }

  @Override
  @Nullable
  protected FailedMailData getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return m_aFailedMailQueue.getFailedMailOfID (sID);
  }

  @Override
  protected void modifyViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final FailedMailData aSelectedObject,
                                    @Nonnull final BootstrapButtonToolbar aToolbar)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aToolbar.addButton (EPhotonCoreText.BUTTON_RESEND.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref ()
                             .add (CPageParam.PARAM_ACTION, ACTION_RESEND)
                             .add (CPageParam.PARAM_OBJECT, aSelectedObject.getID ()),
                        EDefaultIcon.YES);
    aToolbar.addButton (EText.MSG_BUTTON_RESEND_DEFAULT_SETTINGS.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref ()
                             .add (CPageParam.PARAM_ACTION, ACTION_RESEND_DEFAULT_SETTINGS)
                             .add (CPageParam.PARAM_OBJECT, aSelectedObject.getID ()),
                        EDefaultIcon.YES);
    aToolbar.addButton (EPhotonCoreText.BUTTON_DELETE.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref ()
                             .add (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE)
                             .add (CPageParam.PARAM_OBJECT, aSelectedObject.getID ()),
                        EDefaultIcon.DELETE);
  }

  @Nullable
  private static IHCNode _getAsString (@Nonnull final List <? extends IEmailAddress> aList)
  {
    if (aList.isEmpty ())
      return null;

    final HCNodeList ret = new HCNodeList ();
    for (final IEmailAddress aEmailAddress : aList)
      ret.addChild (new HCDiv ().addChild (aEmailAddress.getDisplayName ()));
    return ret;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final FailedMailData aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final IEmailData aEmailData = aSelectedObject.getEmailData ();
    final MailTransportError aError = aSelectedObject.getTransportError ();

    final BootstrapViewForm aTable = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ID.getDisplayText (aDisplayLocale))
                                                  .setCtrl (aSelectedObject.getID ()));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ERROR_DT.getDisplayText (aDisplayLocale))
                                                  .setCtrl (PDTToString.getAsString (aSelectedObject.getErrorDateTime (),
                                                                                     aDisplayLocale)));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SMTP_SETTINGS.getDisplayText (aDisplayLocale))
                                                  .setCtrl (aSelectedObject.getSMTPServerDisplayText ()));
    aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SENDING_DT.getDisplayText (aDisplayLocale))
                                                  .setCtrl (PDTToString.getAsString (aSelectedObject.getOriginalSentDateTime (),
                                                                                     aDisplayLocale)));
    if (aEmailData != null)
    {
      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EMAIL_TYPE.getDisplayText (aDisplayLocale))
                                                    .setCtrl (aEmailData.getEmailType ().getID ()));

      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FROM.getDisplayText (aDisplayLocale))
                                                    .setCtrl (aEmailData.getFrom ().getDisplayName ()));

      final IHCNode aReplyTo = _getAsString (aEmailData.getAllReplyTo ());
      if (aReplyTo != null)
        aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_REPLY_TO.getDisplayText (aDisplayLocale))
                                                      .setCtrl (aReplyTo));

      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_TO.getDisplayText (aDisplayLocale))
                                                    .setCtrl (_getAsString (aEmailData.getAllTo ())));

      final IHCNode aCc = _getAsString (aEmailData.getAllCc ());
      if (aCc != null)
        aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_CC.getDisplayText (aDisplayLocale))
                                                      .setCtrl (aCc));

      final IHCNode aBcc = _getAsString (aEmailData.getAllBcc ());
      if (aBcc != null)
        aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_BCC.getDisplayText (aDisplayLocale))
                                                      .setCtrl (aBcc));

      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SUBJECT.getDisplayText (aDisplayLocale))
                                                    .setCtrl (aEmailData.getSubject ()));

      ICommonsList <? extends IHCNode> aBody = null;
      switch (aEmailData.getEmailType ())
      {
        case TEXT:
          aBody = HCExtHelper.nl2divList (aEmailData.getBody ());
          break;
        case HTML:
          aBody = new CommonsArrayList <> (new HCTextNode (aEmailData.getBody ()));
          break;
      }
      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_BODY.getDisplayText (aDisplayLocale))
                                                    .setCtrl (aBody));

      // Show attachment details
      final IEmailAttachmentList aAttachments = aEmailData.getAttachments ();
      if (aAttachments != null && !aAttachments.isEmpty ())
      {
        final HCNodeList aAttachmentNodeList = new HCNodeList ();
        for (final IEmailAttachment aAttachment : aAttachments.getAllAttachments ())
        {
          String sText = aAttachment.getFilename ();
          if (aAttachment.hasCharset ())
            sText += " (" + aAttachment.getCharset ().name () + ")";
          if (aAttachment.hasContentType ())
            sText += " [" + aAttachment.getContentType () + "]";
          sText += "; disposition=" + aAttachment.getDisposition ().getID ();
          aAttachmentNodeList.addChild (new HCDiv ().addChild (sText));
        }
        aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ATTACHMENTS.getDisplayText (aDisplayLocale))
                                                      .setCtrl (aAttachmentNodeList));
      }
    }
    if (aError != null)
    {
      BootstrapTable aDetailsTable = null;
      if (aError.hasAnyDetails ())
      {
        aDetailsTable = new BootstrapTable ();
        aDetailsTable.addHeaderRow ().addCells ("Empfänger Adresse", "Adresse gültig", "Fehlermeldung");

        for (final MailSendDetails aMailSendDetail : aError.getAllDetails ())
        {
          final HCRow aRow = aDetailsTable.addBodyRow ();
          aRow.addCell (HCA_MailTo.createLinkedEmail (aMailSendDetail.getAddress ()));
          aRow.addCell ().addChild (aMailSendDetail.isAddressValid () ? EDefaultIcon.YES.getAsNode ()
                                                                      : EDefaultIcon.NO.getAsNode ());
          aRow.addCell (HCExtHelper.nl2divList (aMailSendDetail.getErrorMessage ()));
        }
      }
      aTable.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ERROR.getDisplayText (aDisplayLocale))
                                                    .setCtrl (new BootstrapErrorBox ().addChild (aError.getThrowable ()
                                                                                                       .getMessage ()),
                                                              aDetailsTable));

    }
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final FailedMailData aSelectedObject,
                                                 @Nonnull final FormErrorList aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final FailedMailData aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrorList aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final boolean bDisabled = m_aFailedMailQueue.getAllFailedMails ().isEmpty ();
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aToolbar.addChild (new BootstrapButton ().addChild (EPhotonCoreText.BUTTON_RESEND_ALL.getDisplayText (aDisplayLocale))
                                             .setOnClick (aWPEC.getSelfHref ().add (CPageParam.PARAM_ACTION,
                                                                                    ACTION_RESEND_ALL))
                                             .setIcon (EDefaultIcon.YES)
                                             .setDisabled (bDisabled));
    aToolbar.addChild (new BootstrapButton ().addChild (EText.MSG_BUTTON_RESEND_ALL_DEFAULT_SETTINGS.getDisplayText (aDisplayLocale))
                                             .setOnClick (aWPEC.getSelfHref ().add (CPageParam.PARAM_ACTION,
                                                                                    ACTION_RESEND_ALL_DEFAULT_SETTINGS))
                                             .setIcon (EDefaultIcon.YES)
                                             .setDisabled (bDisabled));
    aToolbar.addChild (new BootstrapButton ().addChild (EPhotonCoreText.BUTTON_DELETE_ALL.getDisplayText (aDisplayLocale))
                                             .setOnClick (aWPEC.getSelfHref ().add (CPageParam.PARAM_ACTION,
                                                                                    CPageParam.ACTION_DELETE_ALL))
                                             .setIcon (EDefaultIcon.DELETE)
                                             .setDisabled (bDisabled));
    aToolbar.addChild (new BootstrapButton ().addChild (EWebPageText.PAGE_NAME_SETTINGS_SMTP.getDisplayText (aDisplayLocale))
                                             .setOnClick (aWPEC.getLinkToMenuItem (BootstrapPagesMenuConfigurator.MENU_ADMIN_SETTINGS_SMTP))
                                             .setIcon (EBootstrapIcon.ARROW_RIGHT));

    aNodeList.addChild (aToolbar);

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.DESCENDING),
                                        new DTCol (EText.MSG_ERROR_DT.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                       aDisplayLocale),
                                        new DTCol (EText.MSG_SMTP_SETTINGS.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_SUBJECT.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_ERROR.getDisplayText (aDisplayLocale))).setID (getID ());
    for (final FailedMailData aItem : m_aFailedMailQueue.getAllFailedMails ())
    {
      final ISimpleURL aViewURL = createViewURL (aWPEC, aItem);
      final IEmailData aEmailData = aItem.getEmailData ();
      final Throwable aThrowable = aItem.getTransportThrowable ();

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewURL).addChild (aItem.getID ()));
      aRow.addCell (PDTToString.getAsString (aItem.getErrorDateTime (), aDisplayLocale));
      aRow.addCell (aItem.getSMTPServerDisplayText ());
      aRow.addCell (aEmailData == null ? null : aEmailData.getSubject ());
      aRow.addCell (aThrowable == null ? null : aThrowable.getMessage ());
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
