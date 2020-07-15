/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.fineupload5.ui;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.uictrls.fineupload5.FineUploader5Core;

public class FineUploader5UI extends FineUploader5Core
{
  public static final String DEFAULT_TEMPLATE = "qq-template";

  private String m_sElementID;
  private String m_sListElementID;
  private IJSExpression m_aShowMessage;
  private IJSExpression m_aShowConfirm;
  private IJSExpression m_aShowPrompt;
  private String m_sTemplateID = DEFAULT_TEMPLATE;

  public FineUploader5UI (@Nonnull final Locale aDisplayLocale)
  {
    super (aDisplayLocale);
  }

  @Nullable
  public String getElementID ()
  {
    return m_sElementID;
  }

  /**
   * Container element for the default drop zone
   *
   * @param sElementID
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setElementID (@Nullable final String sElementID)
  {
    m_sElementID = sElementID;
    return this;
  }

  @Nullable
  public String getListElementID ()
  {
    return m_sListElementID;
  }

  /**
   * Container element for the item list.
   *
   * @param sListElementID
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setListElementID (@Nullable final String sListElementID)
  {
    m_sListElementID = sListElementID;
    return this;
  }

  @Nullable
  public IJSExpression getShowMessage ()
  {
    return m_aShowMessage;
  }

  /**
   * Provide a function here to display a message to the user when the uploader
   * receives an error or the user attempts to leave the page. The provided
   * function may return a promise if one wishes to do asynchronous work whilst
   * waiting for user input.<br>
   * Default: <code>function(message) { window.alert(message); }</code>
   *
   * @param aShowMessage
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setShowMessage (@Nullable final IJSExpression aShowMessage)
  {
    m_aShowMessage = aShowMessage;
    return this;
  }

  @Nullable
  public IJSExpression getShowConfirm ()
  {
    return m_aShowConfirm;
  }

  /**
   * Provide a function here to prompt the user to confirm deletion of a file.
   * The provided function may return a promise if one wishes to do asynchronous
   * work whilst waiting for user input.<br>
   * Default: <code>function(message) { window.confirm(message); }</code>
   *
   * @param aShowConfirm
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setShowConfirm (@Nullable final IJSExpression aShowConfirm)
  {
    m_aShowConfirm = aShowConfirm;
    return this;
  }

  @Nullable
  public IJSExpression getShowPrompt ()
  {
    return m_aShowPrompt;
  }

  /**
   * Provide a function here to prompt the user for a filename when pasting
   * file(s). The provided function may return a promise if one wishes to do
   * asynchronous work whilst waiting for user input.<br>
   * Default:
   * <code>function(message, defaultValue) { window.prompt(message, defaultValue); }</code>
   *
   * @param aShowPrompt
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setShowPrompt (@Nullable final IJSExpression aShowPrompt)
  {
    m_aShowPrompt = aShowPrompt;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getTemplateID ()
  {
    return m_sTemplateID;
  }

  /**
   * This points to the container element that contains the template to use for
   * one or more Fine Uploader UI instances. You can either specify a string,
   * which is the element ID (the ID of the container element on the page) or an
   * Element that points to the container element.
   *
   * @param sTemplateID
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5UI setTemplateID (@Nonnull @Nonempty final String sTemplateID)
  {
    ValueEnforcer.notEmpty (sTemplateID, "TemplateID");
    m_sTemplateID = sTemplateID;
    return this;
  }

  @Override
  protected void extendJSONPart (@Nonnull @Nonempty final String sKey,
                                 @Nonnull final JSAssocArray aAssocArray,
                                 @Nullable final Locale aDisplayLocale)
  {
    if (sKey.equals (KEY_DELETE_FILE))
    {
      if (aDisplayLocale != null)
      {
        aAssocArray.add ("confirmMessage", EFineUploader5UIText.DELETE_FILE_CONFIRM_MESSAGE.getDisplayText (aDisplayLocale));
        aAssocArray.add ("deletingFailedText", EFineUploader5UIText.DELETE_FILE_DELETING_FAILED_TEXT.getDisplayText (aDisplayLocale));
        aAssocArray.add ("deletingStatusText", EFineUploader5UIText.DELETE_FILE_DELETING_STATUS_TEXT.getDisplayText (aDisplayLocale));
      }
    }
  }

  @Override
  protected void extendJSON (@Nonnull final JSAssocArray aRoot, @Nullable final Locale aDisplayLocale)
  {
    if (StringHelper.hasNoText (m_sElementID))
      aRoot.add ("element", JSHtml.documentGetElementById (m_sElementID));
    if (StringHelper.hasNoText (m_sListElementID))
      aRoot.add ("listElement", JSHtml.documentGetElementById (m_sListElementID));
    if (m_aShowMessage != null)
      aRoot.add ("showMessage", m_aShowMessage);
    if (m_aShowConfirm != null)
      aRoot.add ("showConfirm", m_aShowConfirm);
    if (m_aShowPrompt != null)
      aRoot.add ("showPrompt", m_aShowPrompt);
    if (!m_sTemplateID.equals (DEFAULT_TEMPLATE))
      aRoot.add ("template", JSHtml.documentGetElementById (m_sTemplateID));

    if (aDisplayLocale != null)
    {
      final JSAssocArray aSub = new JSAssocArray ();
      aSub.add ("uploadButton", EFineUploader5UIText.UPLOAD_BUTTON.getDisplayText (aDisplayLocale));
      aSub.add ("cancelButton", EFineUploader5UIText.CANCEL_BUTTON.getDisplayText (aDisplayLocale));
      aSub.add ("retryButton", EFineUploader5UIText.RETRY_BUTTON.getDisplayText (aDisplayLocale));
      aSub.add ("failUpload", EFineUploader5UIText.FAIL_UPLOAD.getDisplayText (aDisplayLocale));
      aSub.add ("dragZone", EFineUploader5UIText.DRAG_ZONE.getDisplayText (aDisplayLocale));
      aSub.add ("dropProcessing", EFineUploader5UIText.DROP_PROCESSING.getDisplayText (aDisplayLocale));
      aSub.add ("formatProgress", EFineUploader5UIText.FORMAT_PROGRESS.getDisplayText (aDisplayLocale));
      aSub.add ("waitingForResponse", EFineUploader5UIText.WAITING_FOR_RESPONSE.getDisplayText (aDisplayLocale));
      aRoot.add ("text", aSub);
    }
  }
}
