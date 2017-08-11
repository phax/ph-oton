package com.helger.photon.basic.app.dao;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.functional.ISupplier;
import com.helger.dao.wal.AbstractWALDAO;
import com.helger.photon.basic.app.io.WebFileIO;

/**
 * Special {@link AbstractWALDAO} that uses the {@link WebFileIO#getDataIO()}
 * for read/write.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type to be serialized
 */
public abstract class AbstractPhotonWALDAO <DATATYPE extends Serializable> extends AbstractWALDAO <DATATYPE>
{
  protected AbstractPhotonWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass, @Nullable final String sFilename)
  {
    this (aDataTypeClass, () -> sFilename);
  }

  protected AbstractPhotonWALDAO (@Nonnull final Class <DATATYPE> aDataTypeClass,
                                  @Nonnull final ISupplier <String> aFilenameProvider)
  {
    super (aDataTypeClass, WebFileIO.getDataIO (), aFilenameProvider);
  }
}
