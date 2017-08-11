package com.helger.photon.core.resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.CGlobal;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.state.EContinue;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.servlet.AbstractObjectDeliveryHttpHandler;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class ResourceBundleDeliveryHttpHandler extends AbstractObjectDeliveryHttpHandler
{
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
      ResourceBundleServlet.s_aLogger.info ("Failed to resolve resource bundle with ID '" + sBundleID + "'");
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