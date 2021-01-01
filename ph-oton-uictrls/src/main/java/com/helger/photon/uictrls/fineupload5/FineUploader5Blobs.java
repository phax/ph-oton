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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x blobs part
 *
 * @author Philip Helger
 */
public class FineUploader5Blobs implements IFineUploader5Part
{
  public static final String DEFAULT_BLOBS_DEFAULT_NAME = "Misc data";

  private String m_sBlobsDefaultName = DEFAULT_BLOBS_DEFAULT_NAME;

  public FineUploader5Blobs ()
  {}

  @Nonnull
  public String getDefaultName ()
  {
    return m_sBlobsDefaultName;
  }

  /**
   * The default name to be used for nameless Blobs.
   *
   * @param sDefaultName
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Blobs setDefaultName (@Nonnull @Nonempty final String sDefaultName)
  {
    ValueEnforcer.notEmpty (sDefaultName, "DefaultName");
    m_sBlobsDefaultName = sDefaultName;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (!m_sBlobsDefaultName.equals (DEFAULT_BLOBS_DEFAULT_NAME))
      aSub.add ("defaultName", m_sBlobsDefaultName);

    return aSub;
  }
}
