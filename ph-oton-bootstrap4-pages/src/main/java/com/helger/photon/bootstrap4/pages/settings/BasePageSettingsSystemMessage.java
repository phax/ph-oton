/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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

import java.time.LocalDateTime;
import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.state.EChange;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.ext.BootstrapSystemMessage;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.mgr.PhotonBasicManager;
import com.helger.photon.core.systemmsg.ESystemMessageType;
import com.helger.photon.core.systemmsg.SystemMessageManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCSystemMessageTypeSelect;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.autosize.HCTextAreaAutosize;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class BasePageSettingsSystemMessage <WPECTYPE extends IWebPageExecutionContext> extends
                                           AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    LABEL_SEVERITY ("Typ", "Severity"),
    LABEL_MESSAGE ("Nachricht", "Message"),
    SAVE_SUCCESS ("Die neue Systemnachricht wurde erfolgreich gespeichert",
                  "The new system message was saved successfully."),
    LAST_UPDATE ("Letzte Aktualisierung: {0}", "Last update: {0}"),
    CURRENT_MESSAGE_TYPE ("Aktuelle Systemnachricht vom Typ ''{0}''", "Current system message of type ''{0}''"),
    NO_SYSTEM_MESSAGE ("Keine Systemnachricht gesetzt.", "No system message present.");

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

  private static final String FIELD_SEVERITY = "msgtype";
  private static final String FIELD_MESSAGE = "msg";

  public BasePageSettingsSystemMessage (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SETTINGS_SYSTEMMESSAGE.getAsMLT ());
  }

  public BasePageSettingsSystemMessage (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSettingsSystemMessage (@Nonnull @Nonempty final String sID,
                                        @Nonnull final String sName,
                                        @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSettingsSystemMessage (@Nonnull @Nonempty final String sID,
                                        @Nonnull final IMultilingualText aName,
                                        @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  /**
   * @param aWPEC
   *        Current execution context
   * @return May be <code>null</code>
   */
  @Nullable
  @OverrideOnDemand
  protected IHCElementWithChildren <?> renderCurrentSystemMessage (@Nonnull final WPECTYPE aWPEC)
  {
    return BootstrapSystemMessage.createDefault ();
  }

  @Override
  public void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final SystemMessageManager aSystemMsgMgr = PhotonBasicManager.getSystemMessageMgr ();

    boolean bShowList = true;
    if (aWPEC.hasAction (CPageParam.ACTION_EDIT))
    {
      if (aWPEC.hasSubAction (CPageParam.ACTION_SAVE))
      {
        if (getCSRFHandler ().checkCSRFNonce (aWPEC).isContinue ())
        {
          // Save message
          final ESystemMessageType eNewMessageType = ESystemMessageType.getFromIDOrDefault (aWPEC.params ()
                                                                                                 .getAsString (FIELD_SEVERITY));
          final String sNewMessage = aWPEC.params ().getAsString (FIELD_MESSAGE);
          final EChange eChange = aSystemMsgMgr.setSystemMessage (eNewMessageType, sNewMessage);
          if (eChange.isChanged ())
          {
            aWPEC.postRedirectGetInternal (success (EText.SAVE_SUCCESS.getDisplayText (aDisplayLocale)));
          }
        }
      }
      else
      {
        // Show input form
        final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
        aForm.setLeft (-1, -1, -1, 2, 1);

        final String sSystemMessage = aSystemMsgMgr.getSystemMessage ();
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_SEVERITY.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCSystemMessageTypeSelect (new RequestField (FIELD_SEVERITY,
                                                                                                                aSystemMsgMgr.getMessageType ()
                                                                                                                             .getID ()),
                                                                                              aDisplayLocale)));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_MESSAGE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCTextAreaAutosize (new RequestField (FIELD_MESSAGE,
                                                                                                         sSystemMessage))));
        aForm.addChild (div (BootstrapSystemMessage.getDefaultFormatter ().getDisplayText (aDisplayLocale)));
        aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_EDIT));
        aForm.addChild (new HCHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE));
        aForm.addChild (getCSRFHandler ().createCSRFNonceField ());

        final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
        // Submit before cancel
        aToolbar.addSubmitButtonSave (aDisplayLocale);
        aToolbar.addButtonCancel (aDisplayLocale);
        bShowList = false;
      }
    }

    if (bShowList)
    {
      // Add last update datetime
      final LocalDateTime aLastUpdateDT = aSystemMsgMgr.getLastUpdateDT ();
      if (aLastUpdateDT != null)
      {
        aNodeList.addChild (getUIHandler ().createActionHeader (EText.LAST_UPDATE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                          PDTToString.getAsString (aLastUpdateDT,
                                                                                                                                   aDisplayLocale))));
      }

      final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));

      if (aSystemMsgMgr.hasSystemMessage ())
      {
        // Show current message
        aForm.addChild (getUIHandler ().createDataGroupHeader (EText.CURRENT_MESSAGE_TYPE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                  aSystemMsgMgr.getMessageType ()
                                                                                                                               .getDisplayText (aDisplayLocale))));
        aForm.addChild (div (renderCurrentSystemMessage (aWPEC)));
      }
      else
      {
        // No message present
        aForm.addChild (info (EText.NO_SYSTEM_MESSAGE.getDisplayText (aDisplayLocale)));
      }

      aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_EDIT));

      final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_EDIT.getDisplayText (aDisplayLocale), EDefaultIcon.EDIT);
    }
  }
}
