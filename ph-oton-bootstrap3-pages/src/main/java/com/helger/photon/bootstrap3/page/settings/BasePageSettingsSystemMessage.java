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
package com.helger.photon.bootstrap3.page.settings;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.appbasics.app.systemmsg.ESystemMessageType;
import com.helger.appbasics.app.systemmsg.SystemMessageManager;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.state.EChange;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.CHCParam;
import com.helger.html.hc.IHCElementWithChildren;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCH2;
import com.helger.html.hc.html.HCHiddenField;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.uicore.html.select.HCSystemMessageTypeSelect;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webbasics.form.RequestField;
import com.helger.webbasics.mgr.MetaSystemManager;
import com.helger.webctrls.autosize.HCTextAreaAutosize;

public class BasePageSettingsSystemMessage <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
  {
    SAVE_SUCCESS ("Die neue Systemnachricht wurde erfolgreich gespeichert", "The new system message was saved successfully."),
    LAST_UPDATE ("Letzte Aktualisierung: {0}", "Last update: {0}"),
    CURRENT_MESSAGE_TYPE ("Aktuelle Systemnachricht vom Typ ''{0}''", "Current system message of type ''{0}''"),
    NO_SYSTEM_MESSAGE ("Keine Systemnachricht gesetzt.", "No system message present.");

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

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgs (this, m_aTP, aContentLocale, aArgs);
    }
  }

  private static final String FIELD_MESSAGE_TYPE = "msgtype";
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
                                        @Nonnull final IReadonlyMultiLingualText aName,
                                        @Nullable final IReadonlyMultiLingualText aDescription)
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
    return SystemMessageUIHelper.createDefaultBox ();
  }

  @Override
  public void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final SystemMessageManager aSystemMsgMgr = MetaSystemManager.getSystemMessageMgr ();

    boolean bShowList = true;
    if (aWPEC.hasAction (CHCParam.ACTION_EDIT))
    {
      if (aWPEC.hasSubAction (CHCParam.ACTION_SAVE))
      {
        if (checkCSRFNonce (aWPEC).isContinue ())
        {
          // Save message
          final ESystemMessageType eNewMessageType = ESystemMessageType.getFromIDOrDefault (aWPEC.getAttributeAsString (FIELD_MESSAGE_TYPE));
          final String sNewMessage = aWPEC.getAttributeAsString (FIELD_MESSAGE);
          final EChange eChange = aSystemMsgMgr.setSystemMessage (eNewMessageType, sNewMessage);
          if (eChange.isChanged ())
          {
            aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SAVE_SUCCESS.getDisplayText (aDisplayLocale)));
          }
        }
      }
      else
      {
        // Show input form
        final AbstractHCForm <?> aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));

        final String sSystemMessage = aSystemMsgMgr.getSystemMessage ();
        aForm.addChild (new HCSystemMessageTypeSelect (new RequestField (FIELD_MESSAGE_TYPE,
                                                                         aSystemMsgMgr.getMessageType ().getID ()),
                                                       aDisplayLocale));
        aForm.addChild (new HCTextAreaAutosize (new RequestField (FIELD_MESSAGE, sSystemMessage)));
        aForm.addChild (new HCHiddenField (CHCParam.PARAM_ACTION, CHCParam.ACTION_EDIT));
        aForm.addChild (new HCHiddenField (CHCParam.PARAM_SUBACTION, CHCParam.ACTION_SAVE));
        aForm.addChild (createCSRFNonceField ());

        final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
        aToolbar.addButtonCancel (aDisplayLocale);
        aToolbar.addSubmitButtonSave (aDisplayLocale);
        bShowList = false;
      }
    }

    if (bShowList)
    {
      // Add last update datetime
      final LocalDateTime aLastUpdateDT = aSystemMsgMgr.getLastUpdateDT ();
      if (aLastUpdateDT != null)
      {
        aNodeList.addChild (new HCH2 ().addChild (EText.LAST_UPDATE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                            PDTToString.getAsString (aLastUpdateDT,
                                                                                                                     aDisplayLocale))));
      }

      final AbstractHCForm <?> aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));

      if (aSystemMsgMgr.hasSystemMessage ())
      {
        // Show current message
        aForm.addChild (new HCDiv ().addChild (EText.CURRENT_MESSAGE_TYPE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                  aSystemMsgMgr.getMessageType ()
                                                                                                               .getDisplayText (aDisplayLocale))));
        aForm.addChild (new HCDiv ().addChild (renderCurrentSystemMessage (aWPEC)));
      }
      else
      {
        // No message present
        aForm.addChild (new BootstrapInfoBox ().addChild (EText.NO_SYSTEM_MESSAGE.getDisplayText (aDisplayLocale)));
      }

      aForm.addChild (new HCHiddenField (CHCParam.PARAM_ACTION, CHCParam.ACTION_EDIT));

      final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addSubmitButton (EWebBasicsText.MSG_BUTTON_EDIT.getDisplayText (aDisplayLocale), EDefaultIcon.EDIT);
    }
  }
}
