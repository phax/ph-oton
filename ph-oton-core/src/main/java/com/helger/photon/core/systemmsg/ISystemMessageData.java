/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.core.systemmsg;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;

/**
 * System message data read-only interface.
 *
 * @author Philip Helger
 * @since 7.0.5
 */
public interface ISystemMessageData extends Serializable
{
  /**
   * @return The date and time when the system message was last modified. May be
   *         <code>null</code>.
   */
  @Nullable
  LocalDateTime getLastUpdateDT ();

  /**
   * @return The type of system message. Never <code>null</code>.
   */
  @Nonnull
  ESystemMessageType getMessageType ();

  /**
   * @return The ID of the type of system message. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  default String getMessageTypeID ()
  {
    return getMessageType ().getID ();
  }

  /**
   * @return The system message text itself. May be <code>null</code>.
   */
  @Nullable
  String getMessage ();

  /**
   * @return <code>true</code> if a system message text is available,
   *         <code>false</code> if not.
   */
  default boolean hasMessage ()
  {
    return StringHelper.hasText (getMessage ());
  }
}
