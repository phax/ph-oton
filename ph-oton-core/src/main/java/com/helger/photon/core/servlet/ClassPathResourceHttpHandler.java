package com.helger.photon.core.servlet;

import javax.annotation.Nonnull;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.url.URLHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class ClassPathResourceHttpHandler extends AbstractResourceDeliveryHttpHandler
{
  @Override
  @Nonnull
  protected IReadableResource getResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                           @Nonnull final String sFilename)
  {
    // URL decode is required because requests contain e.g. "%20"
    final String sFilename1 = URLHelper.urlDecode (sFilename);

    return new ClassPathResource (sFilename1);
  }
}