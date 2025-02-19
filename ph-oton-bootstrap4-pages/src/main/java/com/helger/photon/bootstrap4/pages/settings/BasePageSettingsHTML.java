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

import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.formlabel.ELabelType;
import com.helger.photon.uicore.html.formlabel.HCFormLabelHelper;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Page with global HTML output settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSettingsHTML <WPECTYPE extends IWebPageExecutionContext> extends
                                  AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_HTML_VERSION ("HTML-Version", "HTML version"),
    MSG_FORMAT_HTML ("HTML formatieren?", "Format HTML?"),
    MSG_FORMAT_CSS ("CSS formatieren?", "Format CSS?"),
    MSG_FORMAT_JS ("JS formatieren?", "Format JS?"),
    MSG_CONSISTENCY_CHECKS_ENABLED ("Konsistenzprüfungen aktiv?", "Consistency checks enabled?"),
    MSG_EXTRACT_OUT_OF_BAND_NODES ("Out-of-band Knoten extrahieren?", "Extract out-of-band nodes?"),
    MSG_AUTO_COMPLETE_FOR_PASSWORD_EDITS ("Auto-Vervollständigung für Passwort-Felder?",
                                          "Auto complete for password fields?"),
    MSG_ON_DOCUMENT_READY_PROVIDER ("JavaScript document.ready Ersteller", "JavaScript document.ready provider"),
    MSG_SCRIPT_INLINE_MODE ("<script> Modus", "<script> mode"),
    MSG_STYLE_INLINE_MODE ("<style> Modus", "<style> mode"),
    MSG_NEW_LINE_MODE ("Zeilenumbruch", "New line"),
    MSG_OUT_OF_BAND_DEBUGGING ("Out-of-band Knoten debuggen?", "Debug out-of-band nodes?"),
    MSG_SCRIPTS_IN_BODY ("<script>-Element in <body>?", "Put <script> elements in <body>?"),
    MSG_USE_REGULAR_RESOURCES ("Nicht-optimierte JS/CSS inkludieren?", "Include non-minified JS/CSS?"),
    MSG_USE_NONCE_INLINE_SCRIPT ("'nonce'-Attribut von Inline-Scripts setzen?",
                                 "Set 'nonce' attributes of inline script elements?"),
    MSG_USE_NONCE_INLINE_STYLE ("'nonce'-Attribut von Inline-Styles setzen?",
                                "Set 'nonce' attributes of inline style elements?"),
    MSG_NONE ("keines", "none"),
    MSG_FORM_LABEL_SUFFIX_OPTIONAL ("Formular Label Suffix für optionale Felder",
                                    "Form label suffix for optional fields"),
    MSG_FORM_LABEL_SUFFIX_ALTERNATIVE ("Formular Label Suffix für alternative Felder",
                                       "Form label suffix for alternative fields"),
    MSG_FORM_LABEL_SUFFIX_MANDATORY ("Formular Label Suffix für Pflichtfelder",
                                     "Form label suffix for mandatory fields"),
    MSG_FORM_LABEL_END ("Formular Label Abschluss", "Form label end"),
    MSG_BUTTON_WEBRESBUNDLE ("ResourceBundle Einstellungen", "ResourceBundle settings"),
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

  private static final String FIELD_FORMAT_HTML = "formathtml";
  private static final String FIELD_FORMAT_CSS = "formatcss";
  private static final String FIELD_FORMAT_JS = "formatjs";
  private static final String FIELD_CONSISTENCY_CHECKS_ENABLED = "consistencychecks";
  private static final String FIELD_EXTRACT_OUT_OF_BAND_NODES = "extractoobnodes";
  private static final String FIELD_AUTO_COMPLETE_FOR_PASSWORD_EDITS = "autocompletepw";
  private static final String FIELD_OUT_OF_BAND_DEBUG = "oobdebug";
  private static final String FIELD_SCRIPTS_IN_BODY = "scriptsinbody";
  private static final String FIELD_USE_REGULAR_RESOURCES = "useregular";
  private static final String FIELD_USE_NONCE_INLINE_SCRIPT = "nonceinlinescript";
  private static final String FIELD_USE_NONCE_INLINE_STYLE = "nonceinlinestyle";

  public BasePageSettingsHTML (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SETTINGS_HTML.getAsMLT ());
  }

  public BasePageSettingsHTML (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSettingsHTML (@Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSettingsHTML (@Nonnull @Nonempty final String sID,
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

    // We don't care about the nonce here
    final IHCConversionSettings aConversionSettings = HCSettings.getConversionSettings ();

    final boolean bFormSubmitted = aWPEC.hasAction (CPageParam.ACTION_SAVE);
    if (bFormSubmitted)
    {
      // Save changes
      final boolean bFormatHTML = aWPEC.params ()
                                       .isCheckBoxChecked (FIELD_FORMAT_HTML,
                                                           aConversionSettings.getXMLWriterSettings ()
                                                                              .getIndent ()
                                                                              .isIndent ());
      final boolean bFormatCSS = aWPEC.params ()
                                      .isCheckBoxChecked (FIELD_FORMAT_CSS,
                                                          !aConversionSettings.getCSSWriterSettings ()
                                                                              .isOptimizedOutput ());
      final boolean bFormatJS = aWPEC.params ()
                                     .isCheckBoxChecked (FIELD_FORMAT_JS,
                                                         aConversionSettings.getJSWriterSettings ()
                                                                            .isIndentAndAlign ());
      final boolean bConsistencyChecksEnabled = aWPEC.params ()
                                                     .isCheckBoxChecked (FIELD_CONSISTENCY_CHECKS_ENABLED,
                                                                         aConversionSettings.areConsistencyChecksEnabled ());
      final boolean bExtractOutOfBandNodes = aWPEC.params ()
                                                  .isCheckBoxChecked (FIELD_EXTRACT_OUT_OF_BAND_NODES,
                                                                      aConversionSettings.isExtractOutOfBandNodes ());
      final boolean bAutoCompleteForPasswordEdits = aWPEC.params ()
                                                         .isCheckBoxChecked (FIELD_AUTO_COMPLETE_FOR_PASSWORD_EDITS,
                                                                             !HCSettings.isAutoCompleteOffForPasswordEdits ());
      final boolean bOOBDebug = aWPEC.params ()
                                     .isCheckBoxChecked (FIELD_OUT_OF_BAND_DEBUG,
                                                         HCSettings.isOutOfBandDebuggingEnabled ());
      final boolean bScriptsInBody = aWPEC.params ()
                                          .isCheckBoxChecked (FIELD_SCRIPTS_IN_BODY, HCSettings.isScriptsInBody ());
      final boolean bUseRegularResources = aWPEC.params ()
                                                .isCheckBoxChecked (FIELD_USE_REGULAR_RESOURCES,
                                                                    HCSettings.isUseRegularResources ());
      final boolean bUseNonceInlineScript = aWPEC.params ()
                                                 .isCheckBoxChecked (FIELD_USE_NONCE_INLINE_SCRIPT,
                                                                     HCSettings.isUseNonceInInlineScript ());
      final boolean bUseNonceInlineStyle = aWPEC.params ()
                                                .isCheckBoxChecked (FIELD_USE_NONCE_INLINE_STYLE,
                                                                    HCSettings.isUseNonceInInlineStyle ());

      // Apply the settings
      HCSettings.getMutableConversionSettings ()
                .setXMLWriterSettingsOptimized (!bFormatHTML)
                .setCSSWriterSettingsOptimized (!bFormatCSS)
                .setJSWriterSettingsOptimized (!bFormatJS)
                .setConsistencyChecksEnabled (bConsistencyChecksEnabled)
                .setExtractOutOfBandNodes (bExtractOutOfBandNodes);
      HCSettings.setAutoCompleteOffForPasswordEdits (!bAutoCompleteForPasswordEdits);
      HCSettings.setOutOfBandDebuggingEnabled (bOOBDebug);
      HCSettings.setScriptsInBody (bScriptsInBody);
      HCSettings.setUseRegularResources (bUseRegularResources);
      HCSettings.setUseNonceInInlineScript (bUseNonceInlineScript);
      HCSettings.setUseNonceInInlineStyle (bUseNonceInlineStyle);

      aWPEC.postRedirectGetInternal (success (EText.MSG_CHANGE_SUCCESS.getDisplayText (aDisplayLocale)));
    }
    else
    {
      final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC, bFormSubmitted));
      aForm.setLeft (3);

      {
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_HTML_VERSION.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aConversionSettings.getHTMLVersion ().name ()));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_FORMAT_HTML.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_FORMAT_HTML,
                                                                                                        aConversionSettings.getXMLWriterSettings ()
                                                                                                                           .getIndent ()
                                                                                                                           .isIndent ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_FORMAT_CSS.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_FORMAT_CSS,
                                                                                                        !aConversionSettings.getCSSWriterSettings ()
                                                                                                                            .isOptimizedOutput ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_FORMAT_JS.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_FORMAT_JS,
                                                                                                        aConversionSettings.getJSWriterSettings ()
                                                                                                                           .isIndentAndAlign ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_CONSISTENCY_CHECKS_ENABLED.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_CONSISTENCY_CHECKS_ENABLED,
                                                                                                        aConversionSettings.areConsistencyChecksEnabled ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_EXTRACT_OUT_OF_BAND_NODES.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_EXTRACT_OUT_OF_BAND_NODES,
                                                                                                        aConversionSettings.isExtractOutOfBandNodes ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_AUTO_COMPLETE_FOR_PASSWORD_EDITS.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_AUTO_COMPLETE_FOR_PASSWORD_EDITS,
                                                                                                        !HCSettings.isAutoCompleteOffForPasswordEdits ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_ON_DOCUMENT_READY_PROVIDER.getDisplayText (aDisplayLocale))
                                                     .setCtrl (String.valueOf (HCSettings.getOnDocumentReadyProvider ())));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_SCRIPT_INLINE_MODE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (HCSettings.getScriptInlineMode ().name ()));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_STYLE_INLINE_MODE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (HCSettings.getStyleInlineMode ().name ()));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_NEW_LINE_MODE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (HCSettings.getNewLineMode ().name ()));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_OUT_OF_BAND_DEBUGGING.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_OUT_OF_BAND_DEBUG,
                                                                                                        HCSettings.isOutOfBandDebuggingEnabled ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_SCRIPTS_IN_BODY.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_SCRIPTS_IN_BODY,
                                                                                                        HCSettings.isScriptsInBody ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_USE_REGULAR_RESOURCES.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_USE_REGULAR_RESOURCES,
                                                                                                        HCSettings.isUseRegularResources ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_USE_NONCE_INLINE_SCRIPT.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_USE_NONCE_INLINE_SCRIPT,
                                                                                                        HCSettings.isUseNonceInInlineScript ()))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox (EText.MSG_USE_NONCE_INLINE_STYLE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_USE_NONCE_INLINE_STYLE,
                                                                                                        HCSettings.isUseNonceInInlineStyle ()))));

        final Function <String, IHCNode> aFormatter = s -> StringHelper.hasNoText (s) ? em (EText.MSG_NONE.getDisplayText (aDisplayLocale))
                                                                                      : code (s);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORM_LABEL_SUFFIX_OPTIONAL.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aFormatter.apply (HCFormLabelHelper.getSuffixString (ELabelType.OPTIONAL))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORM_LABEL_SUFFIX_ALTERNATIVE.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aFormatter.apply (HCFormLabelHelper.getSuffixString (ELabelType.ALTERNATIVE))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORM_LABEL_SUFFIX_MANDATORY.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aFormatter.apply (HCFormLabelHelper.getSuffixString (ELabelType.MANDATORY))));
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORM_LABEL_END.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aFormatter.apply (HCFormLabelHelper.getDefaultLabelEnd ())));
      }

      final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_SAVE);
      aToolbar.addSubmitButtonSave (aDisplayLocale);
      aToolbar.addButton (EText.MSG_BUTTON_WEBRESBUNDLE.getDisplayText (aDisplayLocale),
                          aWPEC.getLinkToMenuItem (BootstrapPagesMenuConfigurator.MENU_ADMIN_APPINFO_WEBRESBUNDLE));
    }
  }
}
