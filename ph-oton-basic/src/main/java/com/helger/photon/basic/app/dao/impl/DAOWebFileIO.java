/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.dao.impl;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.IDAOIO;
import com.helger.photon.basic.app.io.IMutableFileRelativeIO;
import com.helger.photon.basic.app.io.WebFileIO;

/**
 * Implementation of {@link IDAOIO} based on {@link WebFileIO}.
 *
 * @author Philip Helger
 */
@Immutable
public class DAOWebFileIO implements IDAOIO
{
  private static final DAOWebFileIO s_aInstance = new DAOWebFileIO ();

  private IMutableFileRelativeIO m_aFileIO;

  protected DAOWebFileIO ()
  {
    // Save this instance because in certain cases the deinitialization order
    // (upon shutdown) leads to a non-existing DataIO! For WAL based DAOs it is
    // important to have the DataIO available upon shutdown to write their last
    // changes to disk.
    if (WebFileIO.isInited ())
      m_aFileIO = WebFileIO.getDataIO ();
  }

  @Nonnull
  public static DAOWebFileIO getInstance ()
  {
    return s_aInstance;
  }

  @Nonnull
  public IMutableFileRelativeIO getFileIO ()
  {
    IMutableFileRelativeIO ret = m_aFileIO;
    if (ret == null)
    {
      // Remember upon first access - in case the WebFileIO is not yet
      // initialized in the ctor
      ret = m_aFileIO = WebFileIO.getDataIO ();
    }
    return ret;
  }

  @Nonnull
  public FileOperationManager getFileOperationMgr ()
  {
    return WebFileIO.getFileOpMgr ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("FileIO", m_aFileIO).getToString ();
  }
}
