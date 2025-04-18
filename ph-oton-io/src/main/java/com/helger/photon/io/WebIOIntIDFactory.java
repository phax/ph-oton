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
package com.helger.photon.io;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.id.factory.FileIntIDFactory;

/**
 * A special {@link FileIntIDFactory} that uses {@link WebFileIO} to get the
 * filename.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class WebIOIntIDFactory extends FileIntIDFactory
{
  public WebIOIntIDFactory (@Nonnull final String sFilename)
  {
    super (WebFileIO.getDataIO ().getFile (sFilename));
  }
}
