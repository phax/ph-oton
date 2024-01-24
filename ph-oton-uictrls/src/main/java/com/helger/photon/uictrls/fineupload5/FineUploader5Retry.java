/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x retry part
 *
 * @author Philip Helger
 */
public class FineUploader5Retry implements IFineUploader5Part
{
  public static final int DEFAULT_RETRY_AUTO_ATTEMPT_DELAY = 5;
  public static final boolean DEFAULT_RETRY_ENABLE_AUTO = false;
  public static final int DEFAULT_RETRY_MAX_AUTO_ATTEMPTS = 3;
  public static final String DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY = "preventRetry";

  private int m_nRetryAutoAttemptDelay = DEFAULT_RETRY_AUTO_ATTEMPT_DELAY;
  private boolean m_bRetryEnableAuto = DEFAULT_RETRY_ENABLE_AUTO;
  private int m_nRetryMaxAutoAttempts = DEFAULT_RETRY_MAX_AUTO_ATTEMPTS;
  private String m_sRetryPreventRetryResponseProperty = DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY;

  public FineUploader5Retry ()
  {}

  @Nonnegative
  public int getAutoAttemptDelay ()
  {
    return m_nRetryAutoAttemptDelay;
  }

  /**
   * The number of seconds to wait between auto retry attempts.
   *
   * @param nAutoAttemptDelay
   *        Number of seconds. Must be &ge; 0.
   * @return this
   */
  @Nonnull
  public FineUploader5Retry setAutoAttemptDelay (@Nonnegative final int nAutoAttemptDelay)
  {
    ValueEnforcer.isGE0 (nAutoAttemptDelay, "AutoAttemptDelay");
    m_nRetryAutoAttemptDelay = nAutoAttemptDelay;
    return this;
  }

  public boolean isEnableAuto ()
  {
    return m_bRetryEnableAuto;
  }

  /**
   * Enable or disable retrying uploads that receive any error response.
   *
   * @param bEnableAuto
   *        <code>true</code> or <code>false</code>
   * @return this
   */
  @Nonnull
  public FineUploader5Retry setEnableAuto (final boolean bEnableAuto)
  {
    m_bRetryEnableAuto = bEnableAuto;
    return this;
  }

  @Nonnegative
  public int getMaxAutoAttempts ()
  {
    return m_nRetryMaxAutoAttempts;
  }

  /**
   * The maximum number of times to attempt to retry a failed upload.
   *
   * @param nMaxAutoAttempts
   *        The number of retry attempts. Must be &ge; 0.
   * @return this
   */
  @Nonnull
  public FineUploader5Retry setMaxAutoAttempts (@Nonnegative final int nMaxAutoAttempts)
  {
    ValueEnforcer.isGE0 (nMaxAutoAttempts, "MaxAutoAttempts");
    m_nRetryMaxAutoAttempts = nMaxAutoAttempts;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getPreventRetryResponseProperty ()
  {
    return m_sRetryPreventRetryResponseProperty;
  }

  /**
   * This property will be looked for in the server response and, if found and
   * true, will indicate that no more retries should be attempted for this item.
   *
   * @param sPreventRetryResponseProperty
   *        property name
   * @return this
   */
  @Nonnull
  public FineUploader5Retry setPreventRetryResponseProperty (@Nonnull @Nonempty final String sPreventRetryResponseProperty)
  {
    ValueEnforcer.notEmpty (sPreventRetryResponseProperty, "PreventRetryResponseProperty");
    m_sRetryPreventRetryResponseProperty = sPreventRetryResponseProperty;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_nRetryAutoAttemptDelay != DEFAULT_RETRY_AUTO_ATTEMPT_DELAY)
      aSub.add ("autoAttemptDelay", m_nRetryAutoAttemptDelay);
    if (m_bRetryEnableAuto != DEFAULT_RETRY_ENABLE_AUTO)
      aSub.add ("enableAuto", m_bRetryEnableAuto);
    if (m_nRetryMaxAutoAttempts != DEFAULT_RETRY_MAX_AUTO_ATTEMPTS)
      aSub.add ("maxAutoAttempts", m_nRetryMaxAutoAttempts);
    if (!m_sRetryPreventRetryResponseProperty.equals (DEFAULT_RETRY_PREVENT_RETRY_RESPONSE_PROPERTY))
      aSub.add ("preventRetryResponseProperty", m_sRetryPreventRetryResponseProperty);
    return aSub;
  }
}
