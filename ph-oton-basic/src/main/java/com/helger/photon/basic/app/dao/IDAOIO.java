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
package com.helger.photon.basic.app.dao;

import javax.annotation.Nonnull;

import com.helger.commons.io.file.FileOperationManager;
import com.helger.photon.basic.app.io.IMutablePathRelativeIO;

/**
 * The DAO file IO API. Used in simple DAOs and WAL (write ahead logging) DAOs.
 *
 * @author Philip Helger
 */
public interface IDAOIO
{
  /**
   * @return The base file IO object. Never <code>null</code>.
   * @since 2.7.4
   */
  @Nonnull
  IMutablePathRelativeIO getFileIO ();

  /**
   * @return The file operation manager to be used. Never <code>null</code>.
   * @since 2.7.4
   */
  @Nonnull
  FileOperationManager getFileOperationMgr ();
}
