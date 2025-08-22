/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.jscode.JSAssocArray;

import jakarta.annotation.Nonnull;

/**
 * Wrapper for Fine Uploader 5.x resume part
 *
 * @author Philip Helger
 */
public class FineUploader5Resume implements IFineUploader5Part
{
  public static final int DEFAULT_RESUME_RECORDS_EXPIRE_IN = 7;
  public static final boolean DEFAULT_RESUME_ENABLED = false;
  public static final String DEFAULT_RESUME_PARAM_NAMES_RESUMING = "qqresume";

  private int m_nResumeRecordsExpireIn = DEFAULT_RESUME_RECORDS_EXPIRE_IN;
  private boolean m_bResumeEnabled = DEFAULT_RESUME_ENABLED;
  private String m_sResumeParamNamesResuming = DEFAULT_RESUME_PARAM_NAMES_RESUMING;

  public FineUploader5Resume ()
  {}

  @Nonnegative
  public int getRecordsExpireIn ()
  {
    return m_nResumeRecordsExpireIn;
  }

  /**
   * The number of days before a persistent resume record will expire.
   *
   * @param nRecordsExpireIn
   *        New value. Must be &ge; 0.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Resume setRecordsExpireIn (@Nonnegative final int nRecordsExpireIn)
  {
    ValueEnforcer.isGE0 (nRecordsExpireIn, "RecordsExpireIn");
    m_nResumeRecordsExpireIn = nRecordsExpireIn;
    return this;
  }

  public boolean isEnabled ()
  {
    return m_bResumeEnabled;
  }

  /**
   * Enable or disable the ability to resume failed or stopped chunked uploads.
   *
   * @param bEnabled
   *        New value.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Resume setEnabled (final boolean bEnabled)
  {
    m_bResumeEnabled = bEnabled;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getParamNameResuming ()
  {
    return m_sResumeParamNamesResuming;
  }

  /**
   * Sent with the first request of the resume with a value of true.
   *
   * @param sParamNameResuming
   *        New value. May neither be <code>null</code> nor empty.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Resume setParamNameResuming (@Nonnull @Nonempty final String sParamNameResuming)
  {
    ValueEnforcer.notEmpty (sParamNameResuming, "ParamNameResuming");
    m_sResumeParamNamesResuming = sParamNameResuming;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();
    if (m_nResumeRecordsExpireIn != DEFAULT_RESUME_RECORDS_EXPIRE_IN)
      aSub.add ("recordsExpireIn", m_nResumeRecordsExpireIn);
    if (m_bResumeEnabled != DEFAULT_RESUME_ENABLED)
      aSub.add ("enabled", m_bResumeEnabled);

    final JSAssocArray aParamNames = new JSAssocArray ();
    if (!m_sResumeParamNamesResuming.equals (DEFAULT_RESUME_PARAM_NAMES_RESUMING))
      aParamNames.add ("resuming", m_sResumeParamNamesResuming);
    if (!aParamNames.isEmpty ())
      aSub.add ("paramNames", aParamNames);

    return aSub;
  }
}
