/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.app.dao;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.id.IHasID;
import com.helger.dao.DAOException;
import com.helger.dao.wal.AbstractMapBasedWALDAO;
import com.helger.photon.app.io.WebFileIO;

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
public abstract class AbstractPhotonMapBasedWALDAO <INTERFACETYPE extends IHasID <String> & Serializable, IMPLTYPE extends INTERFACETYPE>
                                                   extends
                                                   AbstractMapBasedWALDAO <INTERFACETYPE, IMPLTYPE>
{
  public AbstractPhotonMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                       @Nullable final String sFilename) throws DAOException
  {
    this (aImplClass, sFilename, new InitSettings <> ());
  }

  public AbstractPhotonMapBasedWALDAO (@Nonnull final Class <IMPLTYPE> aImplClass,
                                       @Nullable final String sFilename,
                                       @Nonnull final InitSettings <IMPLTYPE> aInitSettings) throws DAOException
  {
    super (aImplClass, WebFileIO.getDataIO (), sFilename, aInitSettings);
  }

  public boolean isReloadable ()
  {
    return true;
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      internalRemoveAllItemsNoCallback ();
      initialRead ();
    });
  }
}
