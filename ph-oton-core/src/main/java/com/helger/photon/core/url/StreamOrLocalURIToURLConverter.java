/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.url;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.resource.URLResource;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * The default implementation of {@link IWebURIToURLConverter} that uses
 * {@link LinkHelper#getStreamURL(IRequestWebScopeWithoutResponse, String)} to
 * convert relative URIs to URLs. If you are using a different stream servlet
 * path, you may need to provide your own implementation and use it in
 * {@link com.helger.photon.core.ajax.response.AjaxHtmlResponse}!
 *
 * @author Philip Helger
 */
public class StreamOrLocalURIToURLConverter implements IWebURIToURLConverter
{
  public StreamOrLocalURIToURLConverter ()
  {}

  public static final boolean isProjectRelativeURI (@Nonnull @Nonempty final String sURI)
  {
    // Absolute paths are project relative files and therefore are relative to
    // the servlet context directory
    return StringHelper.startsWith (sURI, '/');
  }

  @Nonnull
  public IReadableResource getAsResource (@Nonnull @Nonempty final String sURI)
  {
    return getAsResourceStatic (sURI);
  }

  @Nonnull
  public static IReadableResource getAsResourceStatic (@Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      return new URLResource (URLHelper.getAsURL (sURI));

    // Absolute paths are project relative files and therefore are relative to
    // the servlet context directory
    if (isProjectRelativeURI (sURI))
      return WebFileIO.getServletContextIO ().getResource (sURI);

    // Defaults to class path
    return new ClassPathResource (sURI);
  }

  @Nonnull
  public SimpleURL getAsURL (@Nonnull @Nonempty final String sURI)
  {
    return getAsURLStatic (sURI);
  }

  @Nonnull
  public static SimpleURL getAsURLStatic (@Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      return new SimpleURL (sURI);

    // Absolute paths stays
    if (isProjectRelativeURI (sURI))
    {
      // Just add the context
      return LinkHelper.getURLWithContext (sURI);
    }

    // It's relative and therefore streamed
    final StringBuilder aPrefix = new StringBuilder (LinkHelper.getStreamServletPath ());
    if (!StringHelper.startsWith (sURI, '/'))
      aPrefix.append ('/');
    return LinkHelper.getURLWithContext (aPrefix.append (sURI).toString ());
  }

  @Nonnull
  public SimpleURL getAsURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sURI)
  {
    return getAsURLStatic (aRequestScope, sURI);
  }

  @Nonnull
  public static SimpleURL getAsURLStatic (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                          @Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      return new SimpleURL (sURI);

    // Absolute paths stay
    if (isProjectRelativeURI (sURI))
      return LinkHelper.getURLWithContext (aRequestScope, sURI);

    // Relative paths will get streamed
    return LinkHelper.getStreamURL (aRequestScope, sURI);
  }
}
