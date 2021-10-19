/*
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
package com.helger.photon.bootstrap4.pages.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.base64.Base64;
import com.helger.commons.charset.EUnicodeBOM;
import com.helger.commons.locale.LocaleFormatter;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.ajax.decl.AjaxFunctionDeclaration;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.form.BootstrapFormHelper;
import com.helger.photon.bootstrap4.nav.BootstrapTabBox;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.ext.BootstrapFileUpload;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.servlet.response.EContentDispositionType;
import com.helger.web.fileupload.IFileItem;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * Base64 encoder
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 * @since 8.3.2
 */
public class BasePageUtilsBase64Encode <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  public static class Encoded extends AbstractSessionWebSingleton
  {
    private String m_sData;

    @Deprecated
    @UsedViaReflection
    public Encoded ()
    {}

    public static Encoded getInstance ()
    {
      return getSessionSingleton (Encoded.class);
    }

    public void setData (@Nullable final String sData)
    {
      m_sData = sData;
    }

    public boolean hasData ()
    {
      return m_sData != null;
    }
  }

  private static final String PARAM_TAB = "tab";
  private static final String FIELD_FILE = "file";
  private static final String FIELD_TEXT = "text";
  private static final String FIELD_SHOW_AS_STRING = "showasstring";
  private static final boolean DEFAULT_SHOW_AS_STRING = true;
  private static AjaxFunctionDeclaration AJAX_GET_ENCODED;

  static
  {
    AJAX_GET_ENCODED = addAjax ( (aRequestScope, aAjaxResponse) -> {
      final Encoded aEncoded = Encoded.getInstance ();
      if (aEncoded.hasData ())
      {
        aAjaxResponse.setContentAndCharset (aEncoded.m_sData, StandardCharsets.UTF_8);
        aAjaxResponse.setMimeType (CMimeType.TEXT_PLAIN);
        aAjaxResponse.setContentDispositionType (EContentDispositionType.ATTACHMENT);
        aAjaxResponse.setContentDispositionFilename ("base64-encoded-file.txt");
        aAjaxResponse.disableCaching ();
      }
      else
        aAjaxResponse.createBadRequest ();
    });
  }

  public BasePageUtilsBase64Encode (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_UTILS_BASE64_ENCODE.getAsMLT ());
  }

  public BasePageUtilsBase64Encode (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageUtilsBase64Encode (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageUtilsBase64Encode (@Nonnull @Nonempty final String sID,
                                    @Nonnull final IMultilingualText aName,
                                    @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  public void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();

    // Default for UI
    final String sTab = aWPEC.params ().getAsString (PARAM_TAB);
    final boolean bFileSelected = StringHelper.hasNoText (sTab) || FIELD_FILE.equals (sTab);

    final FormErrorList aFormErrors = new FormErrorList ();
    if (aWPEC.params ().hasStringValue (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM))
    {
      final IFileItem aFile = aWPEC.params ().getAsFileItem (FIELD_FILE);
      final String sText = aWPEC.params ().getAsString (FIELD_TEXT);
      final boolean bShowAsString = aWPEC.params ().isCheckBoxChecked (FIELD_SHOW_AS_STRING, DEFAULT_SHOW_AS_STRING);

      byte [] aBytesToEncode = null;
      if (bFileSelected)
      {
        if (aFile == null || StringHelper.hasNoText (aFile.getName ()))
          aFormErrors.addFieldError (FIELD_FILE, "No file was selected");
        else
          aBytesToEncode = aFile.directGet ();
      }
      else
      {
        if (StringHelper.hasNoText (sText))
          aFormErrors.addFieldError (FIELD_TEXT, "No text to encode was provided");
        else
          aBytesToEncode = sText.getBytes (StandardCharsets.UTF_8);
      }

      if (aBytesToEncode != null)
      {
        final int nOfs = 0;
        final int nLen = aBytesToEncode.length;
        final EUnicodeBOM eBOM = EUnicodeBOM.getFromBytesOrNull (aBytesToEncode);
        if (eBOM != null)
          aNodeList.addChild (warn ("The selected file contains a BOM: " + eBOM.name ()));

        String sEncoded;
        try
        {
          sEncoded = Base64.encodeBytes (aBytesToEncode, nOfs, nLen, Base64.DO_BREAK_LINES);
        }
        catch (final IOException ex)
        {
          sEncoded = "";
        }
        aNodeList.addChild (success ((aFile != null ? "File '" + aFile.getName () + "'" : "Uploaded text") +
                                     " was encoded from " +
                                     aBytesToEncode.length +
                                     " bytes to " +
                                     sEncoded.length () +
                                     " characters (=" +
                                     LocaleFormatter.getFormattedPercent ((double) sEncoded.length () / aBytesToEncode.length,
                                                                          2,
                                                                          aDisplayLocale) +
                                     ")!"));

        if (bShowAsString)
        {
          // Show as string
          final int nLines = StringHelper.getLineCount (sEncoded);

          final HCTextArea aTextArea = new HCTextArea ("result").setReadOnly (true)
                                                                .setRows (Math.max (nLines, 5))
                                                                .setValue (sEncoded)
                                                                .addClass (CBootstrapCSS.TEXT_MONOSPACE)
                                                                .addClass (CBootstrapCSS.MB_3);
          BootstrapFormHelper.markAsFormControl (aTextArea);
          aNodeList.addChild (aTextArea);
        }
        else
        {
          // Publish for download

          // Remember in session
          Encoded.getInstance ().setData (sEncoded);

          // Print download link
          aNodeList.addChild (success (a (AJAX_GET_ENCODED.getInvocationURL (aRequestScope)).addChild ("Download encoded result file")));
        }
      }
    }

    if (aFormErrors.isNotEmpty ())
      aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));

    final BootstrapTabBox aTabBox = aNodeList.addAndReturnChild (new BootstrapTabBox ());

    {
      final BootstrapForm aForm = getUIHandler ().createFormSelf (aWPEC);
      aForm.setEncTypeFileUpload ();
      aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
      aForm.addChild (new HCHiddenField (PARAM_TAB, FIELD_FILE));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("File to encode")
                                                   .setCtrl (new BootstrapFileUpload (FIELD_FILE,
                                                                                      aDisplayLocale).setCustomPlaceholder ("No file selected"))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_FILE)));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Show result as String?")
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_SHOW_AS_STRING,
                                                                                                      DEFAULT_SHOW_AS_STRING)))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_SHOW_AS_STRING)));

      aForm.addChild (new BootstrapSubmitButton ().addChild ("Create Base64 encoded version").setIcon (EDefaultIcon.YES));
      aTabBox.addTab ("file", "Upload file", aForm, bFileSelected);
    }

    {
      final BootstrapForm aForm = getUIHandler ().createFormSelf (aWPEC);
      aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
      aForm.addChild (new HCHiddenField (PARAM_TAB, FIELD_TEXT));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("Text to encode")
                                                   .setCtrl (new HCTextArea (new RequestField (FIELD_TEXT)).setRows (10)
                                                                                                           .addClass (CBootstrapCSS.TEXT_MONOSPACE)
                                                                                                           .setPlaceholder ("Text to be Base64 encoded"))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_TEXT)));

      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Show result as String?")
                                                   .setCtrl (new HCCheckBox (new RequestFieldBoolean (FIELD_SHOW_AS_STRING,
                                                                                                      DEFAULT_SHOW_AS_STRING)))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_SHOW_AS_STRING)));

      aForm.addChild (new BootstrapSubmitButton ().addChild ("Create Base64 encoded version").setIcon (EDefaultIcon.YES));
      aTabBox.addTab ("text", "Edit text to encode", aForm, !bFileSelected);
    }
  }
}
