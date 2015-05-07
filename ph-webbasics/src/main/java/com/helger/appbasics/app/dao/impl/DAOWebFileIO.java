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
package com.helger.appbasics.app.dao.impl;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.app.dao.IDAOIO;
import com.helger.appbasics.app.io.IPathRelativeIO;
import com.helger.appbasics.app.io.WebFileIO;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.string.ToStringGenerator;

@Immutable
public class DAOWebFileIO implements IDAOIO
{
  private static final DAOWebFileIO s_aInstance = new DAOWebFileIO ();

  protected DAOWebFileIO ()
  {}

  @Nonnull
  public static DAOWebFileIO getInstance ()
  {
    return s_aInstance;
  }

  @Nonnull
  public IPathRelativeIO getFileIO ()
  {
    return WebFileIO.getDataIO ();
  }

  @Nonnull
  public FileOperationManager getFileOperationMgr ()
  {
    return WebFileIO.getFileOpMgr ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
