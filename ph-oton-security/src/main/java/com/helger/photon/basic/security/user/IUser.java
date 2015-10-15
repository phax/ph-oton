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
package com.helger.photon.basic.security.user;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.IAttributeContainer;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.text.IHasDescription;
import com.helger.commons.type.ITypedObject;
import com.helger.datetime.domain.IHasCreationDateTime;
import com.helger.datetime.domain.IHasDeletionDateTime;
import com.helger.datetime.domain.IHasLastModificationDateTime;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.security.password.hash.PasswordHash;

/**
 * Interface for a single user
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IUser extends ITypedObject <String>, IHasDisplayName, IHasDescription, IHasCreationDateTime, IHasLastModificationDateTime, IHasDeletionDateTime, IAuthSubject, IAttributeContainer <String, Object>
{
  /**
   * @return <code>true</code> if the user has the ID
   *         {@link com.helger.photon.basic.security.CSecurity#USER_ADMINISTRATOR_ID}
   *         , <code>false</code> otherwise
   */
  boolean isAdministrator ();

  /**
   * @return The login name of the user.
   */
  @Nonnull
  @Nonempty
  String getLoginName ();

  /**
   * The email address is optional since 2.6.3
   *
   * @return The email address of the user. May be <code>null</code>.
   */
  @Nullable
  String getEmailAddress ();

  /**
   * @return The hashed password of the user. Never <code>null</code>.
   */
  @Nonnull
  PasswordHash getPasswordHash ();

  /**
   * @return The first name of the user. May be <code>null</code>.
   */
  @Nullable
  String getFirstName ();

  /**
   * @return The last name of the user. May be <code>null</code>.
   */
  @Nullable
  String getLastName ();

  /**
   * @return The display name of the user. May be empty if both first and last
   *         name are empty but never <code>null</code>.
   */
  @Nonnull
  String getDisplayName ();

  /**
   * @return The desired locale of the user. May be <code>null</code>.
   */
  @Nullable
  Locale getDesiredLocale ();

  /**
   * @return The date time when the user last logged in. May be
   *         <code>null</code> if the user never logged in.
   * @since 2.4.2
   */
  @Nullable
  LocalDateTime getLastLoginDateTime ();

  /**
   * @return The number of times the user logged in. Always &ge; 0.
   * @since 2.4.2
   */
  @Nonnegative
  int getLoginCount ();

  /**
   * @return The number of consecutive failed logins of this user.
   * @since 2.6.3
   */
  @Nonnegative
  int getConsecutiveFailedLoginCount ();

  /**
   * @return <code>true</code> if this user is deleted, <code>false</code> if it
   *         is active
   */
  boolean isDeleted ();

  /**
   * @return <code>true</code> if this user is enabled, <code>false</code> if it
   *         is disabled
   * @see #isDisabled()
   */
  boolean isEnabled ();

  /**
   * @return <code>true</code> if this user is disabled, <code>false</code> if
   *         it is enabled
   * @see #isEnabled()
   */
  boolean isDisabled ();
}
