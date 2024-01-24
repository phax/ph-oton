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
package com.helger.photon.security.user;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IHasDescription;
import com.helger.security.authentication.subject.IAuthSubject;
import com.helger.security.password.hash.PasswordHash;
import com.helger.tenancy.IBusinessObject;

/**
 * Interface for a single user
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IUser extends IBusinessObject, IHasDescription, IAuthSubject
{
  /**
   * The maximum string length of the user ID. Must be 20 and not 45, because of
   * the creationuserid etc. fields. (don't blame yourself if you don't
   * understand the connex here ;-) )
   *
   * @since 8.3.7
   */
  int USER_ID_MAX_LENGTH = 20;

  /**
   * The maximum string length of the login name.
   *
   * @since 8.3.7
   */
  int LOGIN_NAME_MAX_LENGTH = 200;

  /**
   * The maximum string length of the email address.
   *
   * @since 8.3.7
   */
  int EMAIL_ADDRESS_MAX_LENGTH = 200;

  /**
   * The maximum string length of the desired locale.
   *
   * @since 8.4.5
   */
  int DESIRED_LOCALE_MAX_LENGTH = 20;

  /**
   * @return <code>true</code> if the user has the ID
   *         {@link com.helger.photon.security.CSecurity#USER_ADMINISTRATOR_ID}
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

  default boolean hasFirstName ()
  {
    return StringHelper.hasText (getFirstName ());
  }

  /**
   * @return The last name of the user. May be <code>null</code>.
   */
  @Nullable
  String getLastName ();

  default boolean hasLastName ()
  {
    return StringHelper.hasText (getLastName ());
  }

  /**
   * @return The display name of the user. May be empty if both first and last
   *         name are empty but never <code>null</code>.
   */
  @Nonnull
  default String getDisplayName ()
  {
    return StringHelper.getConcatenatedOnDemand (getFirstName (), " ", getLastName ());
  }

  /**
   * @return The desired locale of the user. May be <code>null</code>.
   */
  @Nullable
  Locale getDesiredLocale ();

  /**
   * @return The desired locale of the user as a String. May be
   *         <code>null</code>.
   * @since 8.3.2
   */
  @Nullable
  default String getDesiredLocaleAsString ()
  {
    final Locale aLocale = getDesiredLocale ();
    return aLocale != null ? aLocale.toString () : null;
  }

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
   * @return <code>true</code> if this user is enabled, <code>false</code> if it
   *         is disabled
   * @see #isDisabled()
   */
  default boolean isEnabled ()
  {
    return !isDisabled ();
  }

  /**
   * @return <code>true</code> if this user is disabled, <code>false</code> if
   *         it is enabled
   * @see #isEnabled()
   */
  boolean isDisabled ();
}
