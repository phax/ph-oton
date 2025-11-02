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
package com.helger.photon.app.mock;

import java.io.File;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.collection.stack.NonBlockingStack;
import com.helger.photon.app.PhotonAppInit;
import com.helger.photon.io.WebFileIO;
import com.helger.photon.io.WebIOIntIDFactory;

/**
 * Static test init and shutdown code for this project
 *
 * @author Philip Helger
 */
@Immutable
public final class PhotonAppTestInit
{
  private PhotonAppTestInit ()
  {}

  @NonNull
  @ReturnsMutableCopy
  public static NonBlockingStack <Runnable> init (@NonNull final File aDataPath,
                                                  @NonNull @Nonempty final String sServletContextPath)
  {
    final NonBlockingStack <Runnable> aCleansing = new NonBlockingStack <> ();

    PhotonAppInit.startUp ();

    // Init the base path once
    // don't check access rights in test, for performance reasons
    WebFileIO.initPaths (aDataPath, sServletContextPath, false);
    aCleansing.push (WebFileIO::resetPaths);

    // Init the IDs
    if (!GlobalIDFactory.hasPersistentIntIDFactory ())
    {
      GlobalIDFactory.setPersistentIntIDFactory (new WebIOIntIDFactory ("ph-oton-app.id"));
      aCleansing.push ( () -> GlobalIDFactory.setPersistentIntIDFactory (null));
    }

    return aCleansing;
  }
}
