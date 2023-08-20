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
package com.helger.photon.io.dao;

import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.io.relative.FileRelativeIO;
import com.helger.dao.simple.AbstractSimpleDAO;
import com.helger.photon.io.WebFileIO;

/**
 * Special {@link AbstractSimpleDAO} that uses the {@link WebFileIO#getDataIO()}
 * for read/write.
 *
 * @author Philip Helger
 */
public abstract class AbstractPhotonSimpleDAO extends AbstractSimpleDAO
{
  protected AbstractPhotonSimpleDAO (@Nullable final String sFilename)
  {
    // Kind of a test fix, because when no filename is present, WebFilIO also
    // must not be inited. Any DAO IO is fine
    super (sFilename != null ? WebFileIO.getDataIO () : FileRelativeIO.createForCurrentDir (), () -> sFilename);
  }

  protected AbstractPhotonSimpleDAO (@Nonnull final Supplier <String> aFilenameProvider)
  {
    super (WebFileIO.getDataIO (), aFilenameProvider);
  }
}
