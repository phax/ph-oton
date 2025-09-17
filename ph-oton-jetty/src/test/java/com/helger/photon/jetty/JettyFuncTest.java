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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.util.Collections;

import org.eclipse.jetty.util.resource.MountedPathResourceFactory;
import org.junit.Test;

import com.helger.base.CGlobal;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.io.resource.ClassPathResource;

/**
 * Test SPI definitions
 *
 * @author Philip Helger
 */
public final class JettyFuncTest
{
  @Test
  public void testResourcesManual () throws Exception
  {
    final MountedPathResourceFactory aRF = new MountedPathResourceFactory ();
    // Make sure to pick a resource that is provided in a JAR file
    final URI aURI = new ClassPathResource (ClassHelper.getPathFromClass (CGlobal.class) + ".class",
                                            CGlobal.class.getClassLoader ()).getAsURL ().toURI ();
    try
    {
      aRF.newResource (aURI);
      fail ();
    }
    catch (final FileSystemNotFoundException ex)
    {
      // Expected
    }

    // Explicitly load as file system
    try (final FileSystem aFS = FileSystems.newFileSystem (aURI, Collections.emptyMap ()))
    {
      assertNotNull (aRF.newResource (aURI));
    }
  }

  @Test
  public void testResourcesPhoton () throws Exception
  {
    try (final PhotonFileSystemCache aFSCache = new PhotonFileSystemCache ())
    {
      final PhotonResourceFactory aRF = new PhotonResourceFactory (aFSCache);
      // Make sure to pick a resource that is provided in a JAR file
      final URI aURI = new ClassPathResource (ClassHelper.getPathFromClass (CGlobal.class) + ".class",
                                              CGlobal.class.getClassLoader ()).getAsURL ().toURI ();
      // Works automagically
      assertNotNull (aRF.newResource (aURI));
    }
  }
}
