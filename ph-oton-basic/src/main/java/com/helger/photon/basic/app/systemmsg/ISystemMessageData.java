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
package com.helger.photon.basic.app.systemmsg;

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
 * @since 7.1.0
 */
public interface ISystemMessageData extends Serializable
{
  @Nullable
  LocalDateTime getLastUpdateDT ();

  @Nonnull
  ESystemMessageType getMessageType ();

  @Nonnull
  @Nonempty
  default String getMessageTypeID ()
  {
    return getMessageType ().getID ();
  }

  @Nullable
  String getMessage ();

  default boolean hasMessage ()
  {
    return StringHelper.hasText (getMessage ());
  }
}
