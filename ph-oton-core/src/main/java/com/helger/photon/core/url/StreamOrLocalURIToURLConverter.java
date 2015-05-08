/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.URLResource;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.IURIToURLConverter;
import com.helger.commons.url.ReadonlySimpleURL;
import com.helger.commons.url.URLProtocolRegistry;
import com.helger.commons.url.URLUtils;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * The default implementation of {@link IURIToURLConverter} that uses
 * {@link LinkUtils#getStreamURL(IRequestWebScopeWithoutResponse, String)} to
 * convert relative URIs to URLs. If you are using a different stream servlet
 * path, you may need to provide your own implementation and use it in
 * {@link com.helger.photon.core.ajax.response.AjaxDefaultResponse}!
 *
 * @author Philip Helger
 */
public class StreamOrLocalURIToURLConverter implements IWebURIToURLConverter
{
  private static final StreamOrLocalURIToURLConverter s_aInstance = new StreamOrLocalURIToURLConverter ();

  protected StreamOrLocalURIToURLConverter ()
  {}

  /**
   * @return The default instance of this class. Never <code>null</code>.
   */
  @Nonnull
  public static StreamOrLocalURIToURLConverter getInstance ()
  {
    return s_aInstance;
  }

  private static boolean _isProjectRelativeURI (@Nonnull @Nonempty final String sURI)
  {
    // Absolute paths are project relative files and therefore are relative to
    // the servlet context directory
    return sURI.startsWith ("/");
  }

  @Nonnull
  public IReadableResource getAsResource (@Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sURI))
      return new URLResource (URLUtils.getAsURL (sURI));

    // Absolute paths are project relative files and therefore are relative to
    // the servlet context directory
    if (_isProjectRelativeURI (sURI))
      return WebFileIO.getServletContextIO ().getResource (sURI);

    // Defaults to class path
    return new ClassPathResource (sURI);
  }

  @Nonnull
  public ISimpleURL getAsURL (@Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sURI))
      return new ReadonlySimpleURL (sURI);

    // Absolute paths stays
    if (_isProjectRelativeURI (sURI))
    {
      // Just add the context
      return LinkUtils.getURLWithContext (sURI);
    }

    // It's relative and therefore streamed
    final StringBuilder aPrefix = new StringBuilder (LinkUtils.getStreamServletPath ());
    if (!StringHelper.startsWith (sURI, '/'))
      aPrefix.append ('/');
    return LinkUtils.getURLWithContext (aPrefix.append (sURI).toString ());
  }

  @Nonnull
  public ISimpleURL getAsURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                              @Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notEmpty (sURI, "URI");

    // If the URL is absolute, use it
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sURI))
      return new ReadonlySimpleURL (sURI);

    // Absolute paths stay
    if (_isProjectRelativeURI (sURI))
      return LinkUtils.getURLWithContext (aRequestScope, sURI);

    // Relative paths will get streamed
    return LinkUtils.getStreamURL (aRequestScope, sURI);
  }
}
