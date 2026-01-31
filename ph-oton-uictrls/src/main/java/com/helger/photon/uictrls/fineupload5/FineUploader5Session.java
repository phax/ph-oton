/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.jscode.JSAssocArray;
import com.helger.url.ISimpleURL;

/**
 * Wrapper for Fine Uploader 5.x session part
 *
 * @author Philip Helger
 */
public class FineUploader5Session implements IFineUploader5Part
{
  public static final boolean DEFAULT_SESSION_REFRESH_ON_RESET = true;

  private final ICommonsOrderedMap <String, String> m_aSessionCustomHeaders = new CommonsLinkedHashMap <> ();
  private ISimpleURL m_aSessionEndpoint;
  private final ICommonsOrderedMap <String, String> m_aSessionParams = new CommonsLinkedHashMap <> ();
  private boolean m_bSessionRefreshOnReset = DEFAULT_SESSION_REFRESH_ON_RESET;

  public FineUploader5Session ()
  {}

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomHeaders ()
  {
    return m_aSessionCustomHeaders.getClone ();
  }

  /**
   * Any additional headers you would like included with the GET request sent to your server.
   * Ignored in IE9 and IE8 if the endpoint is cross-origin.
   *
   * @param aCustomHeaders
   *        Custom headers to be set.
   * @return this
   */
  @NonNull
  public FineUploader5Session setCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aSessionCustomHeaders.setAll (aCustomHeaders);
    return this;
  }

  /**
   * Any additional headers you would like included with the GET request sent to your server.
   * Ignored in IE9 and IE8 if the endpoint is cross-origin.
   *
   * @param aCustomHeaders
   *        Custom headers to be added.
   * @return this
   */
  @NonNull
  public FineUploader5Session addCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aSessionCustomHeaders.putAllIfNotNull (aCustomHeaders);
    return this;
  }

  /**
   * Any additional headers you would like included with the GET request sent to your server.
   * Ignored in IE9 and IE8 if the endpoint is cross-origin.
   *
   * @param sKey
   *        Custom header name
   * @param sValue
   *        Custom header value
   * @return this
   */
  @NonNull
  public FineUploader5Session addCustomHeader (@NonNull @Nonempty final String sKey, @NonNull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aSessionCustomHeaders.put (sKey, sValue);
    return this;
  }

  @Nullable
  public ISimpleURL getEndpoint ()
  {
    return m_aSessionEndpoint;
  }

  /**
   * If non-null, Fine Uploader will send a GET request on startup to this endpoint, expecting a
   * JSON response containing data about the initial file list to populate.
   *
   * @param aEndpoint
   *        New value. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  public FineUploader5Session setEndpoint (@Nullable final ISimpleURL aEndpoint)
  {
    ValueEnforcer.notNull (aEndpoint, "Endpoint");
    m_aSessionEndpoint = aEndpoint;
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllParams ()
  {
    return m_aSessionParams.getClone ();
  }

  /**
   * Any parameters you would like passed with the associated GET request to your server.
   *
   * @param aParams
   *        New parameters to be set.
   * @return this
   */
  @NonNull
  public FineUploader5Session setParams (@Nullable final Map <String, String> aParams)
  {
    m_aSessionParams.setAll (aParams);
    return this;
  }

  /**
   * Any parameters you would like passed with the associated GET request to your server.
   *
   * @param aParams
   *        New parameters to be added.
   * @return this
   */
  @NonNull
  public FineUploader5Session addParams (@Nullable final Map <String, String> aParams)
  {
    m_aSessionParams.putAllIfNotNull (aParams);
    return this;
  }

  /**
   * Any parameters you would like passed with the associated GET request to your server.
   *
   * @param sKey
   *        Parameter name
   * @param sValue
   *        Parameter value
   * @return this
   */
  @NonNull
  public FineUploader5Session addParam (@NonNull @Nonempty final String sKey, @NonNull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aSessionParams.put (sKey, sValue);
    return this;
  }

  public boolean isRefreshOnReset ()
  {
    return m_bSessionRefreshOnReset;
  }

  /**
   * Set this to false if you do not want the file list to be retrieved from the server as part of a
   * reset.
   *
   * @param bRefreshOnReset
   *        New value
   * @return this for chaining
   */
  @NonNull
  public FineUploader5Session setRefreshOnReset (final boolean bRefreshOnReset)
  {
    m_bSessionRefreshOnReset = bRefreshOnReset;
    return this;
  }

  @NonNull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_aSessionCustomHeaders.isNotEmpty ())
      aSub.add ("customHeaders", getAsJSAA (m_aSessionCustomHeaders));
    if (m_aSessionEndpoint != null)
      aSub.add ("endpoint", m_aSessionEndpoint.getAsString ());
    if (m_aSessionParams.isNotEmpty ())
      aSub.add ("params", getAsJSAA (m_aSessionParams));
    if (m_bSessionRefreshOnReset != DEFAULT_SESSION_REFRESH_ON_RESET)
      aSub.add ("refreshOnReset", m_bSessionRefreshOnReset);

    return aSub;
  }
}
