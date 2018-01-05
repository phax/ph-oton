/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x deleteFile part
 *
 * @author Philip Helger
 */
public class FineUploader5DeleteFile implements IFineUploader5Part
{
  public static final boolean DEFAULT_DELETE_FILE_ENABLED = false;
  public static final ISimpleURL DEFAULT_DELETE_FILE_ENDPOINT = new SimpleURL ("/server/upload");
  public static final EHttpMethod DEFAULT_DELETE_FILE_METHOD = EHttpMethod.DELETE;

  private final ICommonsOrderedMap <String, String> m_aDeleteFileCustomHeaders = new CommonsLinkedHashMap <> ();
  private boolean m_bDeleteFileEnabled = DEFAULT_DELETE_FILE_ENABLED;
  private ISimpleURL m_aDeleteFileEndpoint = DEFAULT_DELETE_FILE_ENDPOINT;
  private EHttpMethod m_eDeleteFileMethod = DEFAULT_DELETE_FILE_METHOD;
  private final ICommonsOrderedMap <String, String> m_aDeleteFileParams = new CommonsLinkedHashMap <> ();

  public FineUploader5DeleteFile ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomHeaders ()
  {
    return m_aDeleteFileCustomHeaders.getClone ();
  }

  /**
   * Any additional headers to attach to all delete file requests.
   *
   * @param aCustomHeaders
   *        Custom headers to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile setCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aDeleteFileCustomHeaders.setAll (aCustomHeaders);
    return this;
  }

  /**
   * Any additional headers to attach to all delete file requests.
   *
   * @param aCustomHeaders
   *        Custom headers to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile addCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aDeleteFileCustomHeaders.addAll (aCustomHeaders);
    return this;
  }

  /**
   * Any additional headers to attach to all delete file requests.
   *
   * @param sKey
   *        Custom header name
   * @param sValue
   *        Custom header value
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile addCustomHeader (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aDeleteFileCustomHeaders.put (sKey, sValue);
    return this;
  }

  public boolean isEnabled ()
  {
    return m_bDeleteFileEnabled;
  }

  /**
   * Enable or disable deletion of uploaded files.
   *
   * @param bEnabled
   *        New value.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5DeleteFile setEnabled (final boolean bEnabled)
  {
    m_bDeleteFileEnabled = bEnabled;
    return this;
  }

  @Nonnull
  public ISimpleURL getEndpoint ()
  {
    return m_aDeleteFileEndpoint;
  }

  /**
   * The endpoint to which delete file requests are sent.
   *
   * @param aEndpoint
   *        New value. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5DeleteFile setEndpoint (@Nonnull final ISimpleURL aEndpoint)
  {
    ValueEnforcer.notNull (aEndpoint, "Endpoint");
    m_aDeleteFileEndpoint = aEndpoint;
    return this;
  }

  @Nonnull
  public EHttpMethod getMethod ()
  {
    return m_eDeleteFileMethod;
  }

  /**
   * Set the method to use for delete requests. Accepts 'POST' or 'DELETE'.
   *
   * @param eMethod
   *        New value. May not be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5DeleteFile setMethod (@Nonnull final EHttpMethod eMethod)
  {
    ValueEnforcer.notNull (eMethod, "Method");
    m_eDeleteFileMethod = eMethod;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllParams ()
  {
    return m_aDeleteFileParams.getClone ();
  }

  /**
   * Any additional parameters to attach to delete file requests.
   *
   * @param aParams
   *        New parameters to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile setParams (@Nullable final Map <String, String> aParams)
  {
    m_aDeleteFileParams.setAll (aParams);
    return this;
  }

  /**
   * Any additional parameters to attach to delete file requests.
   *
   * @param aParams
   *        New parameters to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile addParams (@Nullable final Map <String, String> aParams)
  {
    m_aDeleteFileParams.addAll (aParams);
    return this;
  }

  /**
   * Any additional parameters to attach to delete file requests.
   *
   * @param sKey
   *        Parameter name
   * @param sValue
   *        Parameter value
   * @return this
   */
  @Nonnull
  public FineUploader5DeleteFile addParam (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aDeleteFileParams.put (sKey, sValue);
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_aDeleteFileCustomHeaders.isNotEmpty ())
      aSub.add ("customHeaders", getAsJSAA (m_aDeleteFileCustomHeaders));
    if (m_bDeleteFileEnabled != DEFAULT_DELETE_FILE_ENABLED)
      aSub.add ("enabled", m_bDeleteFileEnabled);
    if (!m_aDeleteFileEndpoint.equals (DEFAULT_DELETE_FILE_ENDPOINT))
      aSub.add ("endpoint", m_aDeleteFileEndpoint.getAsStringWithEncodedParameters ());
    if (!m_eDeleteFileMethod.equals (DEFAULT_DELETE_FILE_METHOD))
      aSub.add ("method", m_eDeleteFileMethod.getName ());
    if (m_aDeleteFileParams.isNotEmpty ())
      aSub.add ("params", getAsJSAA (m_aDeleteFileParams));

    return aSub;
  }
}
