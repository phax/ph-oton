/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.mgrs.sysmsg;

import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.state.EChange;

/**
 * Base interface for system message manager.
 *
 * @author Philip Helger
 * @since 10.2.0
 */
public interface ISystemMessageManager
{
  /**
   * @return The date and time when the system message was last modified. May be <code>null</code>.
   */
  @Nullable
  LocalDateTime getLastUpdateDT ();

  /**
   * @return The type of system message. Never <code>null</code>.
   */
  @NonNull
  ESystemMessageType getMessageType ();

  /**
   * @return The system message text itself. May be <code>null</code>.
   */
  @Nullable
  String getSystemMessage ();

  /**
   * @return <code>true</code> if a system message text is available, <code>false</code> if not.
   */
  boolean hasSystemMessage ();

  @NonNull
  EChange setSystemMessage (@NonNull ESystemMessageType eMessageType, @Nullable String sMessage);

}
