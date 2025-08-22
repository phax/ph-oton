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

import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.jscode.JSAssocArray;
import com.helger.http.EHttpMethod;
import com.helger.http.url.ISimpleURL;
import com.helger.http.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Wrapper for Fine Uploader 5.x request part
 *
 * @author Philip Helger
 */
public class FineUploader5Request implements IFineUploader5Part
{
  public static final ISimpleURL DEFAULT_REQUEST_ENDPOINT = new SimpleURL ("/server/upload");
  public static final String DEFAULT_REQUEST_FILENAME_PARAM = "qqfilename";
  public static final boolean DEFAULT_REQUEST_FORCE_MULTIPART = true;
  public static final String DEFAULT_REQUEST_INPUT_NAME = "qqfile";
  public static final EHttpMethod DEFAULT_REQUEST_METHOD = EHttpMethod.POST;
  public static final boolean DEFAULT_REQUEST_PARAMS_IN_BODY = true;
  public static final String DEFAULT_REQUEST_UUID_NAME = "qquuid";
  public static final String DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME = "qqtotalfilesize";

  private final ICommonsOrderedMap <String, String> m_aRequestCustomHeaders = new CommonsLinkedHashMap <> ();
  private ISimpleURL m_aRequestEndpoint = DEFAULT_REQUEST_ENDPOINT;
  private String m_sRequestFilenameParam = DEFAULT_REQUEST_FILENAME_PARAM;
  private boolean m_bRequestForceMultipart = DEFAULT_REQUEST_FORCE_MULTIPART;
  private String m_sRequestInputName = DEFAULT_REQUEST_INPUT_NAME;
  private EHttpMethod m_eRequestMethod = DEFAULT_REQUEST_METHOD;
  private final ICommonsOrderedMap <String, String> m_aRequestParams = new CommonsLinkedHashMap <> ();
  private boolean m_bRequestParamsInBody = DEFAULT_REQUEST_PARAMS_IN_BODY;
  private String m_sRequestUUIDName = DEFAULT_REQUEST_UUID_NAME;
  private String m_sRequestTotalFileSizeName = DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME;

  public FineUploader5Request ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllCustomHeaders ()
  {
    return m_aRequestCustomHeaders.getClone ();
  }

  /**
   * Additional headers sent along with each upload request.
   *
   * @param aCustomHeaders
   *        Custom headers to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aRequestCustomHeaders.setAll (aCustomHeaders);
    return this;
  }

  /**
   * Additional headers sent along with each upload request.
   *
   * @param aCustomHeaders
   *        Custom headers to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5Request addCustomHeaders (@Nullable final Map <String, String> aCustomHeaders)
  {
    m_aRequestCustomHeaders.putAllIfNotNull (aCustomHeaders);
    return this;
  }

  /**
   * Additional headers sent along with each upload request.
   *
   * @param sKey
   *        Custom header name
   * @param sValue
   *        Custom header value
   * @return this
   */
  @Nonnull
  public FineUploader5Request addCustomHeader (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aRequestCustomHeaders.put (sKey, sValue);
    return this;
  }

  @Nonnull
  public ISimpleURL getEndpoint ()
  {
    return m_aRequestEndpoint;
  }

  /**
   * The endpoint to send upload requests to.
   *
   * @param aRequestEndpoint
   *        The new action URL. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setEndpoint (@Nonnull final ISimpleURL aRequestEndpoint)
  {
    ValueEnforcer.notNull (aRequestEndpoint, "RequestEndpoint");
    m_aRequestEndpoint = aRequestEndpoint;
    return this;
  }

  @Nonnull
  public String getFilenameParam ()
  {
    return m_sRequestFilenameParam;
  }

  /**
   * The name of the parameter passed if the original filename has been edited or a Blob is being
   * sent.
   *
   * @param sFilenameParam
   *        New value. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setFilenameParam (@Nonnull @Nonempty final String sFilenameParam)
  {
    ValueEnforcer.notEmpty (sFilenameParam, "FilenameParam");
    m_sRequestFilenameParam = sFilenameParam;
    return this;
  }

  public boolean isForceMultipart ()
  {
    return m_bRequestForceMultipart;
  }

  /**
   * Force all uploads to use multipart encoding
   *
   * @param bForceMultipart
   *        <code>true</code> to force
   * @return this
   */
  @Nonnull
  public FineUploader5Request setForceMultipart (final boolean bForceMultipart)
  {
    m_bRequestForceMultipart = bForceMultipart;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getInputName ()
  {
    return m_sRequestInputName;
  }

  /**
   * The attribute of the input element which will contain the file name. For non-multipart-encoded
   * upload requests, this will be included as a parameter in the query string of the URI with a
   * value equal to the file name.
   *
   * @param sInputName
   *        New value. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setInputName (@Nonnull @Nonempty final String sInputName)
  {
    ValueEnforcer.notEmpty (sInputName, "InputName");
    m_sRequestInputName = sInputName;
    return this;
  }

  @Nonnull
  public EHttpMethod getMethod ()
  {
    return m_eRequestMethod;
  }

  /**
   * Specify a method to use when sending files to a traditional endpoint. This option is ignored in
   * older browsers (such as IE9 and older).
   *
   * @param eMethod
   *        New value. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setMethod (@Nonnull final EHttpMethod eMethod)
  {
    ValueEnforcer.notNull (eMethod, "Method");
    m_eRequestMethod = eMethod;
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, String> getAllParams ()
  {
    return m_aRequestParams.getClone ();
  }

  /**
   * The parameters that shall be sent with each upload request.
   *
   * @param aParams
   *        New parameters to be set.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setParams (@Nullable final Map <String, String> aParams)
  {
    m_aRequestParams.setAll (aParams);
    return this;
  }

  /**
   * The parameters that shall be sent with each upload request.
   *
   * @param aParams
   *        New parameters to be added.
   * @return this
   */
  @Nonnull
  public FineUploader5Request addParams (@Nullable final Map <String, String> aParams)
  {
    m_aRequestParams.putAllIfNotNull (aParams);
    return this;
  }

  /**
   * The parameters that shall be sent with each upload request.
   *
   * @param sKey
   *        Parameter name
   * @param sValue
   *        Parameter value
   * @return this
   */
  @Nonnull
  public FineUploader5Request addParam (@Nonnull @Nonempty final String sKey, @Nonnull final String sValue)
  {
    ValueEnforcer.notEmpty (sKey, "Key");
    ValueEnforcer.notNull (sValue, "Value");

    m_aRequestParams.put (sKey, sValue);
    return this;
  }

  public boolean isParamsInBody ()
  {
    return m_bRequestParamsInBody;
  }

  /**
   * Enable or disable sending parameters in the request body. If false, parameters are sent in the
   * URL. Otherwise, parameters are sent in the request body.
   *
   * @param bRequestParamsInBody
   *        <code>true</code> to put request params in body
   * @return this
   */
  @Nonnull
  public FineUploader5Request setParamsInBody (final boolean bRequestParamsInBody)
  {
    m_bRequestParamsInBody = bRequestParamsInBody;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getUUIDName ()
  {
    return m_sRequestUUIDName;
  }

  /**
   * The name of the parameter the uniquely identifies each associated item. The value is a Level 4
   * UUID.
   *
   * @param sUUIDName
   *        New value. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setUUIDName (@Nonnull @Nonempty final String sUUIDName)
  {
    ValueEnforcer.notEmpty (sUUIDName, "UUIDName");
    m_sRequestUUIDName = sUUIDName;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getTotalFileSizeName ()
  {
    return m_sRequestTotalFileSizeName;
  }

  /**
   * The name of the parameter passed that specifies the total file size in bytes.
   *
   * @param sTotalFileSizeName
   *        New value. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public FineUploader5Request setTotalFileSizeName (@Nonnull @Nonempty final String sTotalFileSizeName)
  {
    ValueEnforcer.notEmpty (sTotalFileSizeName, "TotalFileSizeName");
    m_sRequestTotalFileSizeName = sTotalFileSizeName;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();
    if (m_aRequestCustomHeaders.isNotEmpty ())
      aSub.add ("customHeaders", getAsJSAA (m_aRequestCustomHeaders));
    if (!m_aRequestEndpoint.equals (DEFAULT_REQUEST_ENDPOINT))
      aSub.add ("endpoint", m_aRequestEndpoint.getAsStringWithEncodedParameters ());
    if (!m_sRequestFilenameParam.equals (DEFAULT_REQUEST_FILENAME_PARAM))
      aSub.add ("filenameParam", m_sRequestFilenameParam);
    if (m_bRequestForceMultipart != DEFAULT_REQUEST_FORCE_MULTIPART)
      aSub.add ("forceMultipart", m_bRequestForceMultipart);
    if (!m_sRequestInputName.equals (DEFAULT_REQUEST_INPUT_NAME))
      aSub.add ("inputName", m_sRequestInputName);
    if (!m_eRequestMethod.equals (DEFAULT_REQUEST_METHOD))
      aSub.add ("method", m_eRequestMethod.getName ());
    if (m_aRequestParams.isNotEmpty ())
      aSub.add ("params", getAsJSAA (m_aRequestParams));
    if (m_bRequestParamsInBody != DEFAULT_REQUEST_PARAMS_IN_BODY)
      aSub.add ("paramsInBody", m_bRequestParamsInBody);
    if (!m_sRequestUUIDName.equals (DEFAULT_REQUEST_UUID_NAME))
      aSub.add ("uuidName", m_sRequestUUIDName);
    if (!m_sRequestTotalFileSizeName.equals (DEFAULT_REQUEST_TOTAL_FILE_SIZE_NAME))
      aSub.add ("totalFileSizeName", m_sRequestTotalFileSizeName);
    return aSub;
  }
}
