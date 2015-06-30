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
package com.helger.photon.basic.mock;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.cleanup.CommonsCleanup;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.id.factory.MemoryIntIDFactory;
import com.helger.photon.basic.app.io.WebFileIO;

/**
 * Static test init and shutdown code for this project
 *
 * @author Philip Helger
 */
@Immutable
public final class PhotonBasicTestInit
{
  private PhotonBasicTestInit ()
  {}

  public static void init (@Nonnull final File aDataPath, @Nonnull final File aServletContextPath)
  {
    // Init the base path once
    // don't check access rights in test, for performance reasons
    WebFileIO.initPaths (aDataPath, aServletContextPath, false);

    // Init the IDs
    if (!GlobalIDFactory.hasPersistentIntIDFactory ())
      GlobalIDFactory.setPersistentIntIDFactory (new MemoryIntIDFactory ());
  }

  public static void shutdown ()
  {
    // Init the base path once
    WebFileIO.resetPaths ();

    // Clean commons
    CommonsCleanup.cleanup ();
  }
}
