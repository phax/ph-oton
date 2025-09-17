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
package com.helger.photon.jetty;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;

import org.eclipse.jetty.util.resource.MountedPathResourceFactory;
import org.eclipse.jetty.util.resource.PathResourceFactory;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;

import com.helger.base.enforce.ValueEnforcer;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A special Jetty {@link ResourceFactory} that can deal with JAR based URLs.
 *
 * @author Philip Helger
 * @since 10.0.1
 */
public class PhotonResourceFactory implements ResourceFactory
{
  private final PathResourceFactory m_aPRF = new PathResourceFactory ();
  private final MountedPathResourceFactory m_aMPRF = new MountedPathResourceFactory ();
  private final PhotonFileSystemCache m_aFSCache;

  public PhotonResourceFactory (@Nonnull final PhotonFileSystemCache aFSCache)
  {
    ValueEnforcer.notNull (aFSCache, "FSCache");
    m_aFSCache = aFSCache;
  }

  @Nullable
  public Resource newResource (@Nullable final URI uri)
  {
    if (uri == null)
      return null;

    final String sUri = uri.toString ();
    final int nIndex = sUri.indexOf ("!/");
    if (nIndex >= 0)
    {
      // Make sure FS is loaded
      try
      {
        m_aFSCache.ensureFileSystemIsPresent (sUri.substring (0, nIndex), uri);
      }
      catch (final IOException ex)
      {
        throw new UncheckedIOException (ex);
      }
      // Now it's safe to load the resource
      return m_aMPRF.newResource (uri);
    }

    return m_aPRF.newResource (uri);
  }
}
