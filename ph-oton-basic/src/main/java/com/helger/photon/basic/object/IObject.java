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
package com.helger.photon.basic.object;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.type.ITypedObject;
import com.helger.photon.basic.datetime.IHasCreationInfo;
import com.helger.photon.basic.datetime.IHasDeletionInfo;
import com.helger.photon.basic.datetime.IHasLastModificationInfo;

/**
 * Base interface for all objects
 *
 * @author Philip Helger
 */
public interface IObject extends
                         ITypedObject <String>,
                         IHasCreationInfo,
                         IHasLastModificationInfo,
                         IHasDeletionInfo,
                         Serializable
{
  /**
   * @return The <code>null</code>-able creation date time of the object.
   */
  @Nullable
  LocalDateTime getCreationDateTime ();

  /**
   * @return The user ID who created the object. May be <code>null</code> in
   *         case of migrated content.
   */
  @Nullable
  String getCreationUserID ();

  default boolean isLastChangeAfter (@Nonnull final LocalDateTime aDT)
  {
    final LocalDateTime aCreationDT = getCreationDateTime ();
    if (aCreationDT != null && aCreationDT.isAfter (aDT))
      return true;
    final LocalDateTime aLastModDT = getLastModificationDateTime ();
    if (aLastModDT != null && aLastModDT.isAfter (aDT))
      return true;
    final LocalDateTime aDeletionDT = getDeletionDateTime ();
    if (aDeletionDT != null && aDeletionDT.isAfter (aDT))
      return true;
    return false;
  }

  /**
   * @return <code>true</code> if this object is deleted, <code>false</code> if
   *         not.
   */
  default boolean isDeleted ()
  {
    return hasDeletionDateTime ();
  }

  /**
   * Check if the object was deleted at the specified local date time. This is
   * true, if the deletion time is &le; than the specified local date time.
   *
   * @param aDT
   *        The time to check for deletion. May not be <code>null</code>.
   * @return <code>true</code> if this object was deleted, <code>false</code> if
   *         not.
   */
  default boolean isDeleted (@Nonnull final LocalDateTime aDT)
  {
    ValueEnforcer.notNull (aDT, "LocalDateTime");
    return hasDeletionDateTime () && getDeletionDateTime ().compareTo (aDT) <= 0;
  }
}
