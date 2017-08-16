package com.helger.photon.core.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.stream.NonBlockingByteArrayInputStream;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.json.IJson;
import com.helger.json.serialize.JsonReader;
import com.helger.json.serialize.JsonWriterSettings;
import com.helger.web.scope.IRequestWebScope;
import com.helger.xservlet.handler.IXServletHandler;

/**
 * Default Servlet handler for CSP reporting. Used in
 * {@link DefaultCSPReportingServlet}.
 *
 * @author Philip Helger
 */
public class CSPReportingXServletHandler implements IXServletHandler
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CSPReportingXServletHandler.class);

  private final Consumer <? super IJson> m_aJsonHandler;

  public CSPReportingXServletHandler ()
  {
    this (CSPReportingXServletHandler::logCSPReport);
  }

  public CSPReportingXServletHandler (final Consumer <? super IJson> aJsonHandler)
  {
    m_aJsonHandler = ValueEnforcer.notNull (aJsonHandler, "JsonHandler");
  }

  public static void logCSPReport (@Nonnull final IJson aJson)
  {
    s_aLogger.warn ("CSP report: " + aJson.getAsJsonString (new JsonWriterSettings ().setIndentEnabled (true)));
  }

  public void onRequest (@Nonnull final HttpServletRequest aHttpRequest,
                         @Nonnull final HttpServletResponse aHttpResponse,
                         @Nonnull final EHttpVersion eHttpVersion,
                         @Nonnull final EHttpMethod eHttpMethod,
                         @Nonnull final IRequestWebScope aRequestScope) throws ServletException, IOException
  {
    // Read all request body bytes
    final byte [] aBytes = StreamHelper.getAllBytes (aHttpRequest.getInputStream ());

    // Try to parse as JSON
    final IJson aJson = JsonReader.readFromStream (new NonBlockingByteArrayInputStream (aBytes));
    if (aJson != null)
      m_aJsonHandler.accept (aJson);
    else
      s_aLogger.error ("Failed to parse CSP report JSON: " + new String (aBytes, StandardCharsets.ISO_8859_1));
  }
}
