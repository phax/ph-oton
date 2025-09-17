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
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.io.stream.StreamHelper;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;

import jakarta.annotation.Nonnull;

/**
 * A specific cache for {@link FileSystem} objects.
 *
 * @author Philip Helger
 * @since 10.0.1
 */
public class PhotonFileSystemCache implements AutoCloseable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonFileSystemCache.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final ICommonsMap <String, FileSystem> m_aFSCache = new CommonsHashMap <> ();

  public PhotonFileSystemCache ()
  {}

  public void ensureFileSystemIsPresent (@Nonnull @Nonempty final String sKey, @Nonnull final URI aUri)
                                                                                                        throws IOException
  {
    // Try in read-lock for performance
    final boolean bContained = m_aRWLock.readLockedBoolean ( () -> m_aFSCache.containsKey (sKey));

    if (!bContained)
    {
      // Try again in write lock
      m_aRWLock.writeLockedThrowing ( () -> {
        if (!m_aFSCache.containsKey (sKey))
        {
          LOGGER.info ("Loading FileSystem for URI '" + aUri + "'");
          m_aFSCache.put (sKey, FileSystems.newFileSystem (aUri, Collections.emptyMap ()));
        }
      });
    }
  }

  public void close ()
  {
    m_aRWLock.writeLocked ( () -> {
      // Close all
      for (final var aFS : m_aFSCache.values ())
        StreamHelper.close (aFS);
      // And forget them
      m_aFSCache.clear ();
    });
  }
}
