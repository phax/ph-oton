/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import com.helger.dao.wal.AbstractWALDAO;
import com.helger.photon.io.WebFileIO;

/**
 * Special {@link AbstractWALDAO} that uses the {@link WebFileIO#getDataIO()}
 * for read/write.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type to be serialized
 */
public abstract class AbstractPhotonWALDAO <DATATYPE> extends AbstractWALDAO <DATATYPE>
{
  protected AbstractPhotonWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass, @Nullable final String sFilename)
  {
    this (aDataTypeClass, () -> sFilename);
  }

  protected AbstractPhotonWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass,
                                  @Nonnull final Supplier <String> aFilenameProvider)
  {
    super (aDataTypeClass, WebFileIO.getDataIO (), aFilenameProvider);
  }
}
