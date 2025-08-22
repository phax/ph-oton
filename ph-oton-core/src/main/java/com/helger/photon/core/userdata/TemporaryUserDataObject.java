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
package com.helger.photon.core.userdata;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.string.StringHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A special version of {@link AbstractUserDataObject} creating a "temporary
 * only" file. A {@link TemporaryUserDataObject} always resides on the file
 * system and can be accessed by regular file IO.
 *
 * @author Philip Helger
 */
@Immutable
public class TemporaryUserDataObject extends AbstractUserDataObject
{
  public TemporaryUserDataObject (@Nonnull @Nonempty final String sPath)
  {
    super (sPath, true);
  }

  @Nonnull
  @ReturnsMutableCopy
  public TemporaryUserDataObject getCloneWithDifferentPath (@Nonnull @Nonempty final String sPath)
  {
    return new TemporaryUserDataObject (sPath);
  }

  @Nullable
  public static TemporaryUserDataObject createConditional (@Nullable final String sPath)
  {
    if (StringHelper.isEmpty (sPath))
      return null;
    return new TemporaryUserDataObject (sPath);
  }
}
