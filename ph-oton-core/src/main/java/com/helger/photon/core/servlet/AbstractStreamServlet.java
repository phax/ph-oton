/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.mime.EMimeContentType;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.state.EContinue;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.ResponseHelperSettings;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.xml.serialize.write.XMLWriterSettings;
import com.helger.xml.util.mime.MimeTypeInfoManager;

/**
 * Simple servlet to forward requests to pages normally not visible to the
 * Web-Server. It simply reads the content of a file and passes it to an output
 * stream.
 *
 * @author Philip Helger
 * @since 3.7.0
 */
public abstract class AbstractStreamServlet extends AbstractObjectDeliveryServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractStreamServlet.class);
  private final IMutableStatisticsHandlerCounter m_aStatsRequests = StatisticsManager.getCounterHandler (getClass ().getName () +
                                                                                                         "$requests");
  private final IMutableStatisticsHandlerKeyedCounter m_aStatsSuccess = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                  "$success");
  private final IMutableStatisticsHandlerKeyedCounter m_aStatsNotFound = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                   "$notfound");
  private final IMutableStatisticsHandlerKeyedCounter m_aStatsMIMEType = StatisticsManager.getKeyedCounterHandler (getClass ().getName () +
                                                                                                                   "$mimetype");
  private static final String REQUEST_ATTR_OBJECT_DELIVERY_RESOURCE = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL +
                                                                      "stream.resource";

  protected AbstractStreamServlet ()
  {}

  /**
   * Resolve the resource specified by the given file name.
   *
   * @param aRequestScope
   *        The request to get request values from. Never <code>null</code>.
   * @param sFilename
   *        The name of the file to be resolved.
   * @return The non-<code>null</code> resource provider.
   */
  @Nonnull
  protected abstract IReadableResource getResource (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                                    @Nonnull String sFilename);

  @Override
  @OverridingMethodsMustInvokeSuper
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // Check for valid filenames, path traversal etc.
    if (super.initRequestState (aRequestScope, aUnifiedResponse).isBreak ())
      return EContinue.BREAK;

    // Resolve the resource from the passed filename
    final String sFilename = aRequestScope.getAttributeAsString (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME);
    final IReadableResource aRes = getResource (aRequestScope, sFilename);
    if (!aRes.exists ())
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Not streaming '" + sFilename + "' because no such resource exists.");

      m_aStatsNotFound.increment (sFilename);
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }

    // Resource exists - save in request scope
    aRequestScope.setAttribute (REQUEST_ATTR_OBJECT_DELIVERY_RESOURCE, aRes);
    return EContinue.CONTINUE;
  }

  @Override
  @Nullable
  protected LocalDateTime getLastModificationDateTime (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // We have an existing resource
    final IReadableResource aRes = aRequestScope.<IReadableResource> getCastedAttribute (REQUEST_ATTR_OBJECT_DELIVERY_RESOURCE);

    // Try to convert the resource to a file, because only files have a last
    // modification DateTime
    final File aFile = aRes.getAsFile ();
    if (aFile != null)
    {
      final long nLastModified = aFile.lastModified ();
      if (nLastModified > 0)
      {
        return PDTFactory.createLocalDateTime (getUnifiedMillis (nLastModified));
      }
    }
    return null;
  }

  /**
   * Check if the object allows for HTTP caching (by setting the appropriate
   * HTTP headers)
   *
   * @param aRequestScope
   *        Request scope
   * @param sFilename
   *        request file name
   * @return <code>true</code> if the file allows for caching,
   *         <code>false</code> if not
   */
  @OverrideOnDemand
  protected boolean objectsAllowsForHTTPCaching (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                 @Nonnull final String sFilename)
  {
    return true;
  }

  /**
   * Determine the MIME type of the resource to deliver.
   *
   * @param sFilename
   *        The passed filename to stream. Never <code>null</code>.
   * @param aResource
   *        The resolved resource. Never <code>null</code>.
   * @return <code>null</code> if no MIME type could be determined
   */
  @OverrideOnDemand
  @Nullable
  protected String determineMimeType (@Nonnull final String sFilename, @Nonnull final IReadableResource aResource)
  {
    return MimeTypeInfoManager.getDefaultInstance ().getPrimaryMimeTypeStringForFilename (sFilename);
  }

  @Override
  protected void onDeliverResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final UnifiedResponse aUnifiedResponse,
                                    @Nonnull final String sFilename) throws IOException
  {
    m_aStatsRequests.increment ();

    // We have an existing resource
    final IReadableResource aRes = aRequestScope.<IReadableResource> getCastedAttribute (REQUEST_ATTR_OBJECT_DELIVERY_RESOURCE);

    // Try to set content type no matter whether caching is used or not!
    final String sMimeType = determineMimeType (sFilename, aRes);
    if (sMimeType != null)
    {
      m_aStatsMIMEType.increment (sMimeType);
      aUnifiedResponse.setMimeTypeString (sMimeType);
      if (EMimeContentType.TEXT.isTypeOf (sMimeType))
      {
        // Important for FileBrowser HTML pages
        aUnifiedResponse.setCharset (XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ);
      }
    }
    else
      s_aLogger.warn ("Failed to determine MIME type for filename '" + sFilename + "'");

    // HTTP caching possible?
    if (objectsAllowsForHTTPCaching (aRequestScope, sFilename))
    {
      aUnifiedResponse.enableCaching (ResponseHelperSettings.getExpirationSeconds ());
    }
    else
    {
      aUnifiedResponse.disableCaching ();
    }

    // Do the main copying from InputStream to OutputStream
    aUnifiedResponse.setContent (aRes);
    m_aStatsSuccess.increment (sFilename);

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Successfully streamed resource '" + sFilename + "' via " + getClass ().getName ());
  }
}
