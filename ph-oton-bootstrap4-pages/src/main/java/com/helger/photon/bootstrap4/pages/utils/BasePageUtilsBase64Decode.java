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
package com.helger.photon.bootstrap4.pages.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.base64.Base64;
import com.helger.commons.charset.CharsetHelper;
import com.helger.commons.locale.LocaleFormatter;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.mime.MimeTypeDeterminator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.css.property.CCSSProperties;
import com.helger.css.propertyvalue.CCSSValue;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.EJSEvent;
import com.helger.photon.ajax.decl.AjaxFunctionDeclaration;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.form.BootstrapFormHelper;
import com.helger.photon.bootstrap4.grid.BootstrapRow;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.form.RequestFieldBoolean;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCCharsetSelect;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.servlet.response.EContentDispositionType;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * Base64 decoder
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 * @since 8.3.2
 */
public class BasePageUtilsBase64Decode <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  public static class Decoded extends AbstractSessionWebSingleton
  {
    private byte [] m_aData;
    private IMimeType m_aMimeType;

    @Deprecated
    @UsedViaReflection
    public Decoded ()
    {}

    public static Decoded getInstance ()
    {
      return getSessionSingleton (Decoded.class);
    }

    public void setData (@Nullable final byte [] aData, @Nullable final IMimeType aMimeType)
    {
      m_aData = aData;
      m_aMimeType = aMimeType;
    }

    public boolean hasData ()
    {
      return m_aData != null && m_aMimeType != null;
    }
  }

  private static final String FIELD_CHARSET = "charset";
  private static final String FIELD_DECODE = "decode";
  private static final String FIELD_SHOW_AS_STRING = "showasstring";
  private static final boolean DEFAULT_SHOW_AS_STRING = false;
  private static final AjaxFunctionDeclaration AJAX_GET_DECODED;

  static
  {
    AJAX_GET_DECODED = addAjax ( (aRequestScope, aAjaxResponse) -> {
      final Decoded aDecoded = Decoded.getInstance ();
      if (aDecoded.hasData ())
      {
        aAjaxResponse.setContent (aDecoded.m_aData);
        aAjaxResponse.setMimeType (aDecoded.m_aMimeType);
        aAjaxResponse.setContentDispositionType (EContentDispositionType.ATTACHMENT);
        aAjaxResponse.setContentDispositionFilename ("base64-decoded-file.dat");
        aAjaxResponse.disableCaching ();
      }
      else
        aAjaxResponse.createBadRequest ();
    });
  }

  public BasePageUtilsBase64Decode (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_UTILS_BASE64_DECODE.getAsMLT ());
  }

  public BasePageUtilsBase64Decode (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageUtilsBase64Decode (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageUtilsBase64Decode (@Nonnull @Nonempty final String sID,
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

    final boolean bShowAsString = aWPEC.params ().isCheckBoxChecked (FIELD_SHOW_AS_STRING, DEFAULT_SHOW_AS_STRING);

    final FormErrorList aFormErrors = new FormErrorList ();
    if (aWPEC.params ().hasStringValue (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM))
    {
      final String sCharset = aWPEC.params ().getAsString (FIELD_CHARSET);
      final Charset aCharset = CharsetHelper.getCharsetFromNameOrNull (sCharset);
      final String sDecode = aWPEC.params ().getAsString (FIELD_DECODE);

      if (aCharset == null && bShowAsString)
        aFormErrors.addFieldError (FIELD_CHARSET, "To show result as String, a Charset must be selected!");

      if (StringHelper.hasNoText (sDecode))
        aFormErrors.addFieldError (FIELD_DECODE, "Please provide a String to decode!");

      byte [] aDecoded = null;
      if (aFormErrors.isEmpty ())
      {
        aDecoded = Base64.safeDecode (sDecode);
        if (aDecoded == null)
          aFormErrors.addFieldError (FIELD_DECODE, "The provided String to decode is not Base64 encoded!");
      }

      if (aFormErrors.isEmpty ())
      {
        aNodeList.addChild (success ("Content decoded from " +
                                     sDecode.length () +
                                     " characters to " +
                                     aDecoded.length +
                                     " bytes (=" +
                                     LocaleFormatter.getFormattedPercent ((double) aDecoded.length / sDecode.length (), 2, aDisplayLocale) +
                                     ")!" +
                                     (bShowAsString ? " Showing result in charset '" + aCharset.name () + "'" : "")));
        if (bShowAsString)
        {
          // Show as string
          final String sDecoded = new String (aDecoded, aCharset);
          final HCTextArea aTextArea = new HCTextArea ().setReadOnly (true)
                                                        .setValue (sDecoded)
                                                        .addStyle (CCSSProperties.FONT_FAMILY.newValue (CCSSValue.MONOSPACE));
          BootstrapFormHelper.markAsFormControl (aTextArea);
          aNodeList.addChild (aTextArea);
        }
        else
        {
          // Publish for download
          IMimeType aMimeType = MimeTypeDeterminator.getInstance ().getMimeTypeFromBytes (aDecoded, null);
          if (aMimeType == null)
            aMimeType = CMimeType.APPLICATION_OCTET_STREAM;

          // Remember in session
          Decoded.getInstance ().setData (aDecoded, aMimeType);

          // Print download link
          aNodeList.addChild (success (a (AJAX_GET_DECODED.getInvocationURL (aRequestScope)).addChild ("Download decoded result file")));
        }
      }
    }

    if (aFormErrors.isNotEmpty ())
      aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
    aForm.setEncTypeFileUpload ();
    aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("Content to decode")
                                                 .setCtrl (new HCTextArea (new RequestField (FIELD_DECODE)).setRows (10)
                                                                                                           .addClass (CBootstrapCSS.TEXT_MONOSPACE)
                                                                                                           .setPlaceholder ("Text to be Base64 decoded"))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_DECODE)));

    final HCCheckBox aShowAsString = new HCCheckBox (new RequestFieldBoolean (FIELD_SHOW_AS_STRING, DEFAULT_SHOW_AS_STRING));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Show result as String?")
                                                 .setCtrl (aShowAsString)
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_SHOW_AS_STRING)));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Charset")
                                                 .setCtrl (new HCCharsetSelect (new RequestField (FIELD_CHARSET,
                                                                                                  StandardCharsets.UTF_8.name ()),
                                                                                true,
                                                                                aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_CHARSET)));
    final BootstrapRow aCharsetRow = (BootstrapRow) aForm.getLastChild ();
    if (!bShowAsString)
      aCharsetRow.addStyle (CCSSProperties.DISPLAY_NONE);
    aShowAsString.addEventHandler (EJSEvent.CLICK, JQuery.idRef (aCharsetRow).toggle ());
    aForm.addChild (new BootstrapSubmitButton ().addChild ("Create Base64 decoded version").setIcon (EDefaultIcon.YES));
  }
}
