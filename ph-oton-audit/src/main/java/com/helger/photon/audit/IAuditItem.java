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
package com.helger.photon.audit;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.base.state.ESuccess;
import com.helger.base.state.ISuccessIndicator;
import com.helger.security.authentication.subject.user.IHasUserID;

/**
 * Base interface for a single audit item
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IAuditItem extends IHasUserID, ISuccessIndicator, Serializable
{
  /**
   * @return The date and time when the audit item was created
   */
  @NonNull
  LocalDateTime getDateTime ();

  /**
   * @return The audit action type. Never <code>null</code>.
   */
  @NonNull
  EAuditActionType getType ();

  /**
   * @return The ID of the audit action type. Never <code>null</code>.
   */
  @NonNull
  @Nonempty
  default String getTypeID ()
  {
    return getType ().getID ();
  }

  /**
   * @return Success or error?
   */
  @NonNull
  ESuccess getSuccess ();

  default boolean isSuccess ()
  {
    return getSuccess ().isSuccess ();
  }

  /**
   * @return The performed action in a textual representation like
   *         "action(param1,param2,...)"
   */
  @NonNull
  String getAction ();
}
