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
package com.helger.photon.core.resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.state.EContinue;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.servlet.AbstractObjectDeliveryHttpHandler;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class ResourceBundleDeliveryHttpHandler extends AbstractObjectDeliveryHttpHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ResourceBundleDeliveryHttpHandler.class);

  @Nonnull
  private static String _getBundleIDFromFilename (@Nonnull final String sFilename)
  {
    // Cut leading path ("/") and file extension
    return FilenameHelper.getBaseName (sFilename);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    if (super.initRequestState (aRequestScope, aUnifiedResponse).isBreak ())
      return EContinue.BREAK;

    // Allow only valid bundle IDs
    final String sBundleID = _getBundleIDFromFilename (aRequestScope.attrs ()
                                                                    .getAsString (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME));
    if (!PhotonCoreManager.getWebSiteResourceBundleMgr ().containsResourceBundleOfID (sBundleID))
    {
      LOGGER.info ("Failed to resolve resource bundle with ID '" + sBundleID + "'");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }
    return EContinue.CONTINUE;
  }

  /**
   * @return The number of days to cache the result.
   */
  @Nonnegative
  protected int getCachingDays ()
  {
    return 30;
  }

  @Override
  protected void onDeliverResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final UnifiedResponse aUnifiedResponse,
                                    @Nonnull final String sFilename) throws IOException
  {
    final String sBundleID = _getBundleIDFromFilename (sFilename);
    final WebSiteResourceBundleSerialized aBundle = PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                     .getResourceBundleOfID (sBundleID);

    final int nCachingDays = getCachingDays ();
    aUnifiedResponse.enableCaching (CGlobal.SECONDS_PER_DAY * nCachingDays)
                    .setMimeType (aBundle.getMimeType ())
                    .setContent (aBundle)
                    .setCharset (StandardCharsets.UTF_8);
  }
}
