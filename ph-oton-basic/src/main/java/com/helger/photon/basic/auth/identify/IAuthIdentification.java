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
package com.helger.photon.basic.auth.identify;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.photon.basic.auth.subject.IAuthSubject;

/**
 * The interface representing the successful login of a subject (e.g. user) at a
 * given time.
 *
 * @author Philip Helger
 */
public interface IAuthIdentification extends Serializable
{
  /**
   * @return The identified subject. May be <code>null</code> if no subject
   *         matched the credentials.
   */
  @Nullable
  IAuthSubject getSubject ();

  /**
   * Method to retrieve the time stamp of when this object was created.
   *
   * @return The date and time the identification occurred. Never
   *         <code>null</code>.
   */
  @Nonnull
  LocalDateTime getIdentificationDate ();
}
