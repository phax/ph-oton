package com.helger.photon.basic.app.dao;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.id.IHasID;
import com.helger.dao.DAOException;
import com.helger.dao.wal.AbstractMapBasedWALDAO;
import com.helger.photon.basic.app.io.WebFileIO;

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
