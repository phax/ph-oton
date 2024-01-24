/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.string.StringHelper;

/**
 * Represents a single web accessible object, that was provided by the user.
 * Think of this as a file descriptor. A {@link UserDataObject} always resides
 * on the file system and can be accessed by regular file IO.
 *
 * @author Philip Helger
 */
@Immutable
public class UserDataObject extends AbstractUserDataObject
{
  public UserDataObject (@Nonnull @Nonempty final String sPath)
  {
    super (sPath, false);
  }

  @Nonnull
  @ReturnsMutableCopy
  public UserDataObject getCloneWithDifferentPath (@Nonnull @Nonempty final String sPath)
  {
    return new UserDataObject (sPath);
  }

  /**
   * Create the {@link UserDataObject} only if the provided path is neither
   * <code>null</code> nor empty.
   * 
   * @param sPath
   *        The path to be used. May be <code>null</code>.
   * @return <code>null</code> if an empty path was provided, a
   *         non-<code>null</code> object otherwise.
   */
  @Nullable
  public static UserDataObject createConditional (@Nullable final String sPath)
  {
    if (StringHelper.hasNoText (sPath))
      return null;
    return new UserDataObject (sPath);
  }
}
