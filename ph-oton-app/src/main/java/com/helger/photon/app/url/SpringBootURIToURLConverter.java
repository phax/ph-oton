/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.url.URLHelper;
import com.helger.io.resource.ClassPathResource;
import com.helger.io.resource.IReadableResource;
import com.helger.io.resource.URLResource;
import com.helger.photon.io.WebFileIO;
import com.helger.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.annotation.Nonnull;

/**
 * The implementation of {@link IWebURIToURLConverter} to work with SpringBoot, assuming the
 * resources are located in the <code>/static/<code> folder.
 *
 * @author Philip Helger
 */
public class SpringBootURIToURLConverter implements IWebURIToURLConverter
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SpringBootURIToURLConverter.class);

  private static final String PREFIX = "/static/";
  private static final int PREFIX_LEN = PREFIX.length ();

  public SpringBootURIToURLConverter ()
  {}

  /**
   * Absolute paths are project relative files and therefore are relative to the servlet context
   * directory
   *
   * @param sURI
   *        The String to check. May neither be <code>null</code> nor empty.
   * @return <code>true</code> if the URI starts with '/'
   */
  public static final boolean isProjectRelativeURI (@Nonnull @Nonempty final String sURI)
  {
    return StringHelper.startsWith (sURI, PREFIX);
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
        ret = WebFileIO.getServletContextIO ().getResource (sURI.substring (PREFIX_LEN));
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
        ret = LinkHelper.getURLWithContext (sURI.substring (PREFIX_LEN - 1));
      }
      else
      {
        // It's relative and therefore streamed
        final StringBuilder sPrefix = new StringBuilder ().append (LinkHelper.getStreamServletPath ());
        if (!StringHelper.startsWith (sURI, '/'))
          sPrefix.append ('/');
        ret = LinkHelper.getURLWithContext (sPrefix.append (sURI).toString ());
      }
    }
    return ret;
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

    final SimpleURL ret;

    // If the URL is absolute, use it
    if (LinkHelper.hasKnownProtocol (sURI))
      ret = new SimpleURL (sURI);
    else
    {
      // Absolute paths stay
      if (isProjectRelativeURI (sURI))
      {
        ret = LinkHelper.getURLWithContext (aRequestScope, sURI.substring (PREFIX_LEN - 1));
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
