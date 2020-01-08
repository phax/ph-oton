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

import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x cors part
 *
 * @author Philip Helger
 */
public class FineUploader5Cors implements IFineUploader5Part
{
  public static final boolean DEFAULT_CORS_ALLOW_XDR = false;
  public static final boolean DEFAULT_CORS_EXPECTED = false;
  public static final boolean DEFAULT_CORS_SEND_CREDENTIALS = false;

  private boolean m_bCorsAllowXdr = DEFAULT_CORS_ALLOW_XDR;
  private boolean m_bCorsExpected = DEFAULT_CORS_EXPECTED;
  private boolean m_bCorsSendCredentials = DEFAULT_CORS_SEND_CREDENTIALS;

  public FineUploader5Cors ()
  {}

  public boolean isAllowXdr ()
  {
    return m_bCorsAllowXdr;
  }

  /**
   * Enable or disable cross-origin requests from IE9 and older where
   * XDomainRequest must be used.
   *
   * @param bAllowXdr
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Cors setAllowXdr (final boolean bAllowXdr)
  {
    m_bCorsAllowXdr = bAllowXdr;
    return this;
  }

  public boolean isExpected ()
  {
    return m_bCorsExpected;
  }

  /**
   * Enable or disable cross-domain requests.
   *
   * @param bExpected
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Cors setExpected (final boolean bExpected)
  {
    m_bCorsExpected = bExpected;
    return this;
  }

  public boolean isSendCredentials ()
  {
    return m_bCorsSendCredentials;
  }

  /**
   * Enable or disable sending credentials along with each cross-domain request.
   * Ignored if allowXdr is true and IE9 is being used.
   *
   * @param bSendCredentials
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Cors setSendCredentials (final boolean bSendCredentials)
  {
    m_bCorsSendCredentials = bSendCredentials;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_bCorsAllowXdr != DEFAULT_CORS_ALLOW_XDR)
      aSub.add ("allowXdr", m_bCorsAllowXdr);
    if (m_bCorsExpected != DEFAULT_CORS_EXPECTED)
      aSub.add ("expected", m_bCorsExpected);
    if (m_bCorsSendCredentials != DEFAULT_CORS_SEND_CREDENTIALS)
      aSub.add ("sendCredentials", m_bCorsSendCredentials);

    return aSub;
  }
}
