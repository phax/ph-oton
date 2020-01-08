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
package com.helger.photon.uictrls.fineupload5;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;

/**
 * Wrapper for Fine Uploader 5.x form part
 *
 * @author Philip Helger
 */
public class FineUploader5Form implements IFineUploader5Part
{
  public static final String DEFAULT_FORM_ELEMENT_ID = "qq-form";
  public static final boolean DEFAULT_FORM_AUTO_UPLOAD = false;
  public static final boolean DEFAULT_FORM_INTERCEPT_SUBMIT = true;

  private String m_sFormElementID = DEFAULT_FORM_ELEMENT_ID;
  private boolean m_bFormAutoUpload = DEFAULT_FORM_AUTO_UPLOAD;
  private boolean m_bFormInterceptSubmit = DEFAULT_FORM_INTERCEPT_SUBMIT;

  public FineUploader5Form ()
  {}

  @Nonnull
  @Nonempty
  public String getElementID ()
  {
    return m_sFormElementID;
  }

  /**
   * This can be the ID of the &lt;form&gt; or a reference to the &lt;form&gt;
   * element.
   *
   * @param sElementID
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Form setElementID (@Nonnull @Nonempty final String sElementID)
  {
    ValueEnforcer.notEmpty (sElementID, "ElementID");
    m_sFormElementID = sElementID;
    return this;
  }

  public boolean isAutoUpload ()
  {
    return m_bFormAutoUpload;
  }

  /**
   * If Fine Uploader is able to attach to a form, this value takes the place of
   * the base autoUpload option.
   *
   * @param bAutoUpload
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Form setAutoUpload (final boolean bAutoUpload)
  {
    m_bFormAutoUpload = bAutoUpload;
    return this;
  }

  public boolean isInterceptSubmit ()
  {
    return m_bFormInterceptSubmit;
  }

  /**
   * Set this to false if you do not want Fine Uploader to intercept attempts to
   * submit your form. By default, Fine Uploader will intercept submit attempts
   * and instead upload all submitted files, including data from your form in
   * each upload request.
   *
   * @param bInterceptSubmit
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Form setInterceptSubmit (final boolean bInterceptSubmit)
  {
    m_bFormInterceptSubmit = bInterceptSubmit;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();
    if (!m_sFormElementID.equals (DEFAULT_FORM_ELEMENT_ID))
      aSub.add ("element", JSHtml.documentGetElementById (m_sFormElementID));
    if (m_bFormAutoUpload != DEFAULT_FORM_AUTO_UPLOAD)
      aSub.add ("autoUpload", m_bFormAutoUpload);
    if (m_bFormInterceptSubmit != DEFAULT_FORM_INTERCEPT_SUBMIT)
      aSub.add ("interceptSubmit", m_bFormInterceptSubmit);
    return aSub;
  }
}
