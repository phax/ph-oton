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
package com.helger.photon.io.dao;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.id.IHasID;
import com.helger.dao.DAOException;
import com.helger.dao.wal.AbstractMapBasedWALDAO;
import com.helger.photon.io.WebFileIO;

/**
 * Special {@link AbstractMapBasedWALDAO} that uses the
 * {@link WebFileIO#getDataIO()} for read/write.
 *
 * @author Philip Helger
 * @param <INTERFACETYPE>
 *        Interface type to be handled
 * @param <IMPLTYPE>
 *        Implementation type to be handled
 */
public abstract class AbstractPhotonMapBasedWALDAO <INTERFACETYPE extends IHasID <String>, IMPLTYPE extends INTERFACETYPE>
                                                   extends
                                                   AbstractMapBasedWALDAO <INTERFACETYPE, IMPLTYPE>
{
  public AbstractPhotonMapBasedWALDAO (@NonNull final Class <IMPLTYPE> aImplClass,
                                       @Nullable final String sFilename) throws DAOException
  {
    this (aImplClass, sFilename, new InitSettings <> ());
  }

  public AbstractPhotonMapBasedWALDAO (@NonNull final Class <IMPLTYPE> aImplClass,
                                       @Nullable final String sFilename,
                                       @NonNull final InitSettings <IMPLTYPE> aInitSettings) throws DAOException
  {
    super (aImplClass, WebFileIO.getDataIO (), sFilename, aInitSettings);
  }

  @Override
  public boolean isReloadable ()
  {
    return true;
  }

  @Override
  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      internalRemoveAllItemsNoCallback ();
      initialRead ();
    });
  }
}
