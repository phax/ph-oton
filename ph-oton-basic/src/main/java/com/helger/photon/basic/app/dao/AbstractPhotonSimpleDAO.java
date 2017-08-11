package com.helger.photon.basic.app.dao;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.functional.ISupplier;
import com.helger.commons.io.relative.FileRelativeIO;
import com.helger.dao.simple.AbstractSimpleDAO;
import com.helger.photon.basic.app.io.WebFileIO;

public abstract class AbstractPhotonSimpleDAO extends AbstractSimpleDAO
{
  protected AbstractPhotonSimpleDAO (@Nullable final String sFilename)
  {
    // Kind of a test fix, because when no filename is present, WebFilIO also
    // must not be inited. Any DAO IO is fine
    super (sFilename != null ? WebFileIO.getDataIO () : FileRelativeIO.createForCurrentDir (), () -> sFilename);
  }

  protected AbstractPhotonSimpleDAO (@Nonnull final ISupplier <String> aFilenameProvider)
  {
    super (WebFileIO.getDataIO (), aFilenameProvider);
  }
}
