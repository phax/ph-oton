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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hchtml.forms.HCCheckBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Page with global HTML output settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSettingsHTML <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_FORMAT_HTML ("HTML formatieren?", "Format HTML?"),
    MSG_FORMAT_CSS ("CSS formatieren?", "Format CSS?"),
    MSG_FORMAT_JS ("JS formatieren?", "Format JS?"),
    MSG_CONSISTENCY_CHECKS_ENABLED ("Konsistenzprüfungen aktiv?", "Consistency checks enabled?"),
    MSG_EXTRACT_OUT_OF_BAND_NODES ("Out-of-band Knoten extrahieren?", "Extract out-of-band nodes?"),
    MSG_CHANGE_SUCCESS ("Die Einstellungen wurden erfolgreich gespeichert.", "Changes were changed successfully.");

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
  }

  private static final String FIELD_FORMAT_HTML = "formathtml";
  private static final String FIELD_FORMAT_CSS = "formatcss";
  private static final String FIELD_FORMAT_JS = "formatjs";
  private static final String FIELD_CONSISTENCY_CHECKS_ENABLED = "consistencychecks";
  private static final String FIELD_EXTRACT_OUT_OF_BAND_NODES = "extractoobnodes";

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
    final IHCConversionSettings aConversionSettings = HCSettings.getConversionSettings ();

    if (aWPEC.hasAction (CPageParam.ACTION_SAVE))
    {
      // Save changes
      final boolean bFormatHTML = aWPEC.getCheckBoxAttr (FIELD_FORMAT_HTML, aConversionSettings.getXMLWriterSettings ()
                                                                                               .getIndent ()
                                                                                               .isIndent ());
      final boolean bFormatCSS = aWPEC.getCheckBoxAttr (FIELD_FORMAT_CSS, !aConversionSettings.getCSSWriterSettings ()
                                                                                              .isOptimizedOutput ());
      final boolean bFormatJS = aWPEC.getCheckBoxAttr (FIELD_FORMAT_JS, aConversionSettings.getJSWriterSettings ()
                                                                                           .isIndentAndAlign ());
      final boolean bConsistencyChecksEnabled = aWPEC.getCheckBoxAttr (FIELD_CONSISTENCY_CHECKS_ENABLED,
                                                                       aConversionSettings.areConsistencyChecksEnabled ());
      final boolean bExtractOutOfBandNodes = aWPEC.getCheckBoxAttr (FIELD_EXTRACT_OUT_OF_BAND_NODES,
                                                                    aConversionSettings.isExtractOutOfBandNodes ());

      // Apply the settings
      HCSettings.getMutableConversionSettings ()
                .setXMLWriterSettingsOptimized (!bFormatHTML)
                .setCSSWriterSettingsOptimized (!bFormatCSS)
                .setJSWriterSettingsOptimized (!bFormatJS)
                .setConsistencyChecksEnabled (bConsistencyChecksEnabled)
                .setExtractOutOfBandNodes (bExtractOutOfBandNodes);

      aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.MSG_CHANGE_SUCCESS.getDisplayText (aDisplayLocale)));
    }

    final BootstrapForm aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));

    // HCSettings
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORMAT_HTML.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (FIELD_FORMAT_HTML,
                                                                             aConversionSettings.getXMLWriterSettings ()
                                                                                                .getIndent ()
                                                                                                .isIndent ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORMAT_CSS.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (FIELD_FORMAT_CSS,
                                                                             !aConversionSettings.getCSSWriterSettings ()
                                                                                                 .isOptimizedOutput ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_FORMAT_JS.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (FIELD_FORMAT_JS,
                                                                             aConversionSettings.getJSWriterSettings ()
                                                                                                .isIndentAndAlign ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_CONSISTENCY_CHECKS_ENABLED.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (FIELD_CONSISTENCY_CHECKS_ENABLED,
                                                                             aConversionSettings.areConsistencyChecksEnabled ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_EXTRACT_OUT_OF_BAND_NODES.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCCheckBox (FIELD_EXTRACT_OUT_OF_BAND_NODES,
                                                                             aConversionSettings.isExtractOutOfBandNodes ())));
    }

    final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_SAVE);
    aToolbar.addSubmitButtonSave (aDisplayLocale);
  }
}
