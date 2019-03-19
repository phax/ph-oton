/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.fineupload5.servlet;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.state.ESuccess;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.core.interror.InternalErrorBuilder;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * <p>
 * Abstract fine upload servlet that abstracts the necessary JSON response.
 * </p>
 * <h2>Deleting Files</h2>
 * <p>
 * If you have enabled this feature, you will need to handle the corresponding
 * DELETE or POST requests server-side. The method is configurable via the
 * method property of the deleteFile option.
 * </p>
 * <p>
 * For DELETE requests, the UUID of the file to delete will be specified as the
 * last element of the URI path. Any custom parameters specified will be added
 * to the query string. For POST requests, the UUID is sent as a "qquuid"
 * parameter, and a "_method" parameter is sent with a value of "DELETE". All
 * POST request parameters are sent in the request payload.
 * </p>
 * <p>
 * Success of the request will depend solely on the response code. Acceptable
 * response codes that indicate success are 200, 202, and 204 for DELETE
 * requests and 200-204 for POST requests.
 * </p>
 * <p>
 * If you would like to enable the delete file feature for cross-origin
 * environments in IE9 or older, you will need to set the allowXdr property of
 * the cors client-side option and adjust your server-side code appropriately.
 * Keep in mind that the Content-Type will be absent from the request header,
 * and credentials (cookies) and non-simple headers cannot be sent.
 * </p>
 *
 * @author Philip Helger
 */
public abstract class AbstractFineUploader5Servlet implements IXServletSimpleHandler
{
  @Immutable
  public static class Response
  {
    public static final String JSON_SUCCESS = "success";
    public static final String JSON_ERROR = "error";
    public static final String JSON_PREVENT_RETRY = "preventRetry";
    public static final String JSON_RESET = "reset";
    public static final String JSON_NEW_UUID = "newUuid";

    private final ESuccess m_eSuccess;
    private final String m_sErrorMsg;
    private final ETriState m_ePreventRetry;
    private final ETriState m_eReset;
    private final String m_sNewUUID;

    /**
     * @param eSuccess
     *        Success state. May not be <code>null</code>.
     * @param sErrorMsg
     *        Optional error message if a failure occurred.
     * @param ePreventRetry
     *        prevent Fine Uploader from making any further attempts to retry
     *        uploading the file. Only suitable in case of an error. May not be
     *        <code>null</code>.
     * @param eReset
     *        fail this attempt and restart with the first chunk on the next
     *        attempt. Only applies if chunking is enabled. Note that, if resume
     *        is also enabled, and this is the first chunk of a resume attempt,
     *        this will result in the upload starting with the first chunk
     *        immediately. Only suitable in case of an error. May not be
     *        <code>null</code>.
     * @param sNewUUID
     *        When you would like to override the UUID for this file provided by
     *        Fine Uploader. Only suitable in case of success. May be
     *        <code>null</code>.
     */
    protected Response (@Nonnull final ESuccess eSuccess,
                        @Nullable final String sErrorMsg,
                        @Nonnull final ETriState ePreventRetry,
                        @Nonnull final ETriState eReset,
                        @Nullable final String sNewUUID)
    {
      m_eSuccess = ValueEnforcer.notNull (eSuccess, "Success");
      m_sErrorMsg = sErrorMsg;
      m_ePreventRetry = ValueEnforcer.notNull (ePreventRetry, "PreventRetry");
      m_eReset = ValueEnforcer.notNull (eReset, "Reset");
      m_sNewUUID = sNewUUID;
    }

    @Nonnull
    protected IJsonObject getAsJson ()
    {
      final JsonObject ret = new JsonObject ();
      ret.add (JSON_SUCCESS, m_eSuccess.isSuccess ());
      if (StringHelper.hasText (m_sErrorMsg))
        ret.add (JSON_ERROR, m_sErrorMsg);
      if (m_ePreventRetry.isDefined ())
        ret.add (JSON_PREVENT_RETRY, m_ePreventRetry.getAsBooleanValue ());
      if (m_eReset.isDefined ())
        ret.add (JSON_RESET, m_eReset.getAsBooleanValue ());
      if (StringHelper.hasText (m_sNewUUID))
        ret.add (JSON_NEW_UUID, m_sNewUUID);
      return ret;
    }

    @Nonnull
    public String getAsJsonString ()
    {
      return getAsJson ().getAsJsonString ();
    }

    /**
     * Create a success response without a new UUID
     *
     * @return Never <code>null</code>.
     */
    @Nonnull
    public static Response createSuccess ()
    {
      return createSuccess (null);
    }

    /**
     * Create a success response.
     *
     * @param sNewUUID
     *        New UUID to use. May be <code>null</code>.
     * @return Never <code>null</code>.
     */
    @Nonnull
    public static Response createSuccess (@Nullable final String sNewUUID)
    {
      return new Response (ESuccess.SUCCESS, null, ETriState.UNDEFINED, ETriState.UNDEFINED, sNewUUID);
    }

    @Nonnull
    public static Response createError (@Nullable final String sErrorMsg)
    {
      return createError (sErrorMsg, ETriState.UNDEFINED, ETriState.UNDEFINED);
    }

    @Nonnull
    public static Response createError (@Nullable final String sErrorMsg,
                                        final boolean bPreventRetry,
                                        final boolean bReset)
    {
      return createError (sErrorMsg, ETriState.valueOf (bPreventRetry), ETriState.valueOf (bReset));
    }

    @Nonnull
    public static Response createError (@Nullable final String sErrorMsg,
                                        @Nonnull final ETriState ePreventRetry,
                                        @Nonnull final ETriState eReset)
    {
      return new Response (ESuccess.FAILURE, sErrorMsg, ePreventRetry, eReset, null);
    }
  }

  public AbstractFineUploader5Servlet ()
  {}

  /**
   * Handle the uploaded or deleted file.
   *
   * @param aRequestScope
   *        The request scope.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract Response handleUploadedFile (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    try
    {
      final Response aResponse = handleUploadedFile (aRequestScope);

      // Main send response
      // Mime type should be text/plain according to
      // http://docs.fineuploader.com/branch/master/endpoint_handlers/traditional.html
      aUnifiedResponse.disableCaching ()
                      .setContentAndCharset (aResponse.getAsJsonString (), StandardCharsets.UTF_8)
                      .setMimeType (CMimeType.TEXT_PLAIN);
    }
    catch (final Throwable t)
    {
      new InternalErrorBuilder ().addCustomData ("Error reason", "Internal error in fine uploader 5 servlet")
                                 .setThrowable (t)
                                 .setRequestScope (aRequestScope)
                                 .handle ();
      aUnifiedResponse.setStatus (HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
