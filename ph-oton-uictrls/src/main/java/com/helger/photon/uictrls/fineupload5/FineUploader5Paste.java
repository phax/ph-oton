/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.html.JSHtml;

/**
 * Wrapper for Fine Uploader 5.x paste part
 *
 * @author Philip Helger
 */
public class FineUploader5Paste implements IFineUploader5Part
{
  public static final String DEFAULT_PASTE_DEFAULT_NAME = "pasted_image";

  private String m_sPasteDefaultName = DEFAULT_PASTE_DEFAULT_NAME;
  private String m_sPasteTargetElementID;

  public FineUploader5Paste ()
  {}

  @Nonnull
  @Nonempty
  public String getDefaultName ()
  {
    return m_sPasteDefaultName;
  }

  /**
   * The default name given to pasted images.
   *
   * @param sDefaultName
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Paste setDefaultName (@Nonnull @Nonempty final String sDefaultName)
  {
    ValueEnforcer.notEmpty (sDefaultName, "DefaultName");
    m_sPasteDefaultName = sDefaultName;
    return this;
  }

  @Nullable
  public String getTargetElementID ()
  {
    return m_sPasteTargetElementID;
  }

  /**
   * Enable this feature by providing any HTMLElement here.
   *
   * @param sTargetElementID
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Paste setTargetElementID (@Nullable final String sTargetElementID)
  {
    m_sPasteTargetElementID = sTargetElementID;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();
    if (!m_sPasteDefaultName.equals (DEFAULT_PASTE_DEFAULT_NAME))
      aSub.add ("defaultName", m_sPasteDefaultName);
    if (StringHelper.hasText (m_sPasteTargetElementID))
      aSub.add ("targetElement", JSHtml.documentGetElementById (m_sPasteTargetElementID));
    return aSub;
  }
}
