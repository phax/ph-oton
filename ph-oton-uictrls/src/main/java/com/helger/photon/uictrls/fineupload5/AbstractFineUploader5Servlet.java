/**
 * Copyright (C) 2006-2016 BRZ GmbH
 * http://www.brz.gv.at
 *
 * All rights reserved
 */
package com.helger.photon.uictrls.fineupload5;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.state.ESuccess;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.http.EHTTPMethod;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.core.app.error.InternalErrorBuilder;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.xml.serialize.write.XMLWriterSettings;

public abstract class AbstractFineUploader5Servlet extends AbstractUnifiedResponseServlet
{
  public static final String FIELD_FILENAME = "filename";
  public static final String JSON_SUCCESS = "success";
  public static final String JSON_ERROR = "error";
  public static final String JSON_PREVENT_RETRY = "preventRetry";
  public static final String JSON_RESET = "reset";
  public static final String JSON_NEW_UUID = "newUuid";

  public static class Response
  {
    private final ESuccess m_eSuccess;
    private final String m_sErrorMsg;
    private final ETriState m_ePreventRetry;
    private final ETriState m_eReset;
    private final String m_sNewUUID;

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

    @Nonnull
    public static Response createSuccess ()
    {
      return createSuccess (null);
    }

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

  public static final Set <EHTTPMethod> ALLOWED_METHDOS = CollectionHelper.makeUnmodifiable (EnumSet.of (EHTTPMethod.POST,
                                                                                                         EHTTPMethod.DELETE));

  public AbstractFineUploader5Servlet ()
  {}

  @Override
  @Nonnull
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return ALLOWED_METHDOS;
  }

  /**
   * Handle the uploaded file.
   * 
   * @param aRequestScope
   *        The request scope.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected abstract Response handleUploadedFile (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    try
    {
      final Response aResponse = handleUploadedFile (aRequestScope);

      // Main send response
      // Mime type should be text/plain according to
      // http://docs.fineuploader.com/branch/master/endpoint_handlers/traditional.html
      aUnifiedResponse.disableCaching ()
                      .setContentAndCharset (aResponse.getAsJsonString (), XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ)
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
