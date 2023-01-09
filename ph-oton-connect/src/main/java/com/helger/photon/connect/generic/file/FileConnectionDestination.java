/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.connect.generic.file;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.state.EChange;
import com.helger.security.authentication.credentials.IAuthCredentials;

public final class FileConnectionDestination implements IFileConnectionDestination
{
  public FileConnectionDestination ()
  {}

  @Nullable
  public File openConnection (@Nonnull final IAuthCredentials aCredentials)
  {
    return new File (".");
  }

  @Nonnull
  public EChange closeConnection (@Nullable final File aChannel)
  {
    return EChange.UNCHANGED;
  }
}
