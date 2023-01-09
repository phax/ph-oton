/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.app.url;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.io.resource.URLResource;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLHelper;
import com.helger.photon.app.io.WebFileIO;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * The default implementation of {@link IWebURIToURLConverter} that uses
 * {@link LinkHelper#getStreamURL(IRequestWebScopeWithoutResponse, String)} to
 * convert relative URIs to URLs. If you are using a different stream servlet
 * path, you may need to provide your own implementation and use it!
 *
 * @author Philip Helger
 */
public class StreamOrLocalURIToURLConverter implements IWebURIToURLConverter
{
  private static final Logger LOGGER = LoggerFactory.getLogger (StreamOrLocalURIToURLConverter.class);

  public StreamOrLocalURIToURLConverter ()
  {}

  /**
   * Absolute paths are project relative files and therefore are relative to the
   * servlet context directory
   *
   * @param sURI
   *        The String to check. May neither be <code>null</code> nor empty.
   * @return <code>true</code> if the URI starts with '/'
   */
  public static final boolean isProjectRelativeURI (@Nonnull @Nonempty final String sURI)
  {
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

    final IReadableResource ret;

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
    {
      LOGGER.info ("Opening '" + sURI + "' via a URLResource");
      ret = new URLResource (URLHelper.getAsURL (sURI));
    }
    else
    {
      // Absolute paths are project relative files and therefore are relative to
      // the servlet context directory
      if (isProjectRelativeURI (sURI))
      {
        // Cut "/" to avoid recognition as absolute file on Linux!
        ret = WebFileIO.getServletContextIO ().getResource (sURI.substring (1));
      }
      else
      {
        // Defaults to class path
        ret = new ClassPathResource (sURI);
      }
    }
    return ret;
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

    final SimpleURL ret;

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      ret = new SimpleURL (sURI);
    else
    {
      // Absolute paths stays
      if (isProjectRelativeURI (sURI))
      {
        // Just add the context
        ret = LinkHelper.getURLWithContext (sURI);
      }
      else
      {
        // It's relative and therefore streamed
        String sPrefix = LinkHelper.getStreamServletPath ();
        if (!StringHelper.startsWith (sURI, '/'))
          sPrefix += '/';
        ret = LinkHelper.getURLWithContext (sPrefix + sURI);
      }
    }
    return ret;
  }

  @Nonnull
  public SimpleURL getAsURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull @Nonempty final String sURI)
  {
    return getAsURLStatic (aRequestScope, sURI);
  }

  @Nonnull
  public static SimpleURL getAsURLStatic (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                          @Nonnull @Nonempty final String sURI)
  {
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notEmpty (sURI, "URI");

    final SimpleURL ret;

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      ret = new SimpleURL (sURI);
    else
    {
      // Absolute paths stay
      if (isProjectRelativeURI (sURI))
      {
        ret = LinkHelper.getURLWithContext (aRequestScope, sURI);
      }
      else
      {
        // Relative paths will get streamed
        ret = LinkHelper.getStreamURL (aRequestScope, sURI);
      }
    }
    return ret;
  }
}
