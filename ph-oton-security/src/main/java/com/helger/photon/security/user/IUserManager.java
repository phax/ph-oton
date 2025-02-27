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
package com.helger.photon.security.user;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.photon.io.mgr.IPhotonManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;

/**
 * Base interface for managing all available users.
 *
 * @author Philip Helger
 * @since 8.2.4
 */
public interface IUserManager extends IPhotonManager <IUser>
{
  /**
   * Create default users. This is only for internal testing purposes.
   */
  void createDefaultsForTest ();

  /**
   * @return The user callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  CallbackList <IUserModificationCallback> userModificationCallbacks ();

  /**
   * Create a new user.
   *
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor
   *        empty.This login name must be unique over all existing users.
   * @param sEmailAddress
   *        The email address. May be <code>null</code>.
   * @param sPlainTextPassword
   *        The plain text password to be used. May neither be <code>null</code>
   *        nor empty.
   * @param sFirstName
   *        The users first name. May be <code>null</code>.
   * @param sLastName
   *        The users last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description for the user. May be <code>null</code>.
   * @param aDesiredLocale
   *        The users default locale. May be <code>null</code>.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   * @return The created user or <code>null</code> if another user with the same
   *         email address is already present or if serialization failed.
   */
  @Nullable
  IUser createNewUser (@Nonnull @Nonempty String sLoginName,
                       @Nullable String sEmailAddress,
                       @Nonnull String sPlainTextPassword,
                       @Nullable String sFirstName,
                       @Nullable String sLastName,
                       @Nullable String sDescription,
                       @Nullable Locale aDesiredLocale,
                       @Nullable Map <String, String> aCustomAttrs,
                       boolean bDisabled);

  /**
   * Create a predefined user.
   *
   * @param sID
   *        The ID to use
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor empty.
   *        This login name must be unique over all existing users.
   * @param sEmailAddress
   *        The email address. May be <code>null</code>.
   * @param sPlainTextPassword
   *        The plain text password to be used. May neither be <code>null</code>
   *        nor empty.
   * @param sFirstName
   *        The users first name. May be <code>null</code>.
   * @param sLastName
   *        The users last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description for the user. May be <code>null</code>.
   * @param aDesiredLocale
   *        The users default locale. May be <code>null</code>.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   * @return The created user or <code>null</code> if another user with the same
   *         email address is already present or if serialization failed.
   */
  @Nullable
  IUser createPredefinedUser (@Nonnull @Nonempty String sID,
                              @Nonnull @Nonempty String sLoginName,
                              @Nullable String sEmailAddress,
                              @Nonnull String sPlainTextPassword,
                              @Nullable String sFirstName,
                              @Nullable String sLastName,
                              @Nullable String sDescription,
                              @Nullable Locale aDesiredLocale,
                              @Nullable Map <String, String> aCustomAttrs,
                              boolean bDisabled);

  /**
   * Get the user with the specified ID.
   *
   * @param sUserID
   *        The user ID to resolve. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   */
  @Nullable
  IUser getUserOfID (@Nullable String sUserID);

  /**
   * Get the user with the specified ID but only if the user is neither deleted
   * nor disabled.
   *
   * @param sUserID
   *        The user ID to resolve. May be <code>null</code>.
   * @return <code>null</code> if no such user exists or if the user exists but
   *         is either deleted or disabled.
   */
  @Nullable
  default IUser getActiveUserOfID (@Nullable final String sUserID)
  {
    final IUser aUser = getUserOfID (sUserID);
    return aUser == null || aUser.isDeleted () || aUser.isDisabled () ? null : aUser;
  }

  /**
   * Get the user with the specified login name
   *
   * @param sLoginName
   *        The login name to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   */
  @Nullable
  IUser getUserOfLoginName (@Nullable final String sLoginName);

  /**
   * Get the user with the specified email address
   *
   * @param sEmailAddress
   *        The email address to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   * @see #getUserOfEmailAddressIgnoreCase(String)
   */
  @Nullable
  IUser getUserOfEmailAddress (@Nullable final String sEmailAddress);

  /**
   * Get the user with the specified email address
   *
   * @param sEmailAddress
   *        The email address to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   * @see #getUserOfEmailAddress(String)
   * @since 8.1.3
   */
  @Nullable
  IUser getUserOfEmailAddressIgnoreCase (@Nullable final String sEmailAddress);

  /**
   * @return A non-<code>null</code> collection of all contained enabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IUser> getAllActiveUsers ();

  /**
   * @return The number of all contained enabled and not-deleted users
   */
  @Nonnegative
  long getActiveUserCount ();

  /**
   * @return A non-<code>null</code> collection of all contained disabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IUser> getAllDisabledUsers ();

  /**
   * @return A non-<code>null</code> collection of all contained not deleted
   *         users
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IUser> getAllNotDeletedUsers ();

  /**
   * @return A non-<code>null</code> collection of all contained deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IUser> getAllDeletedUsers ();

  /**
   * @return <code>true</code> if any non-deleted, enabled user exists.
   */
  boolean containsAnyActiveUser ();

  /**
   * Change the modifiable data of a user
   *
   * @param sUserID
   *        The ID of the user to be modified. May be <code>null</code>.
   * @param sNewLoginName
   *        The new login name. May not be <code>null</code>.
   * @param sNewEmailAddress
   *        The new email address. May be <code>null</code>.
   * @param sNewFirstName
   *        The new first name. May be <code>null</code>.
   * @param sNewLastName
   *        The new last name. May be <code>null</code>.
   * @param sNewDescription
   *        Optional new description for the user. May be <code>null</code>.
   * @param aNewDesiredLocale
   *        The new desired locale. May be <code>null</code>.
   * @param aNewCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bNewDisabled
   *        <code>true</code> if the user is disabled
   * @return {@link EChange}
   */
  @Nonnull
  EChange setUserData (@Nullable String sUserID,
                       @Nonnull @Nonempty String sNewLoginName,
                       @Nullable String sNewEmailAddress,
                       @Nullable String sNewFirstName,
                       @Nullable String sNewLastName,
                       @Nullable String sNewDescription,
                       @Nullable Locale aNewDesiredLocale,
                       @Nullable Map <String, String> aNewCustomAttrs,
                       boolean bNewDisabled);

  /**
   * Change the modifiable data of a user
   *
   * @param sUserID
   *        The ID of the user to be modified. May be <code>null</code>.
   * @param sNewPlainTextPassword
   *        The new password in plain text. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setUserPassword (@Nullable String sUserID, @Nonnull String sNewPlainTextPassword);

  @Nonnull
  EChange updateUserLastLogin (@Nullable String sUserID);

  @Nonnull
  EChange updateUserLastFailedLogin (@Nullable String sUserID);

  /**
   * Delete the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to delete
   * @return {@link EChange#CHANGED} if the user was deleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange deleteUser (@Nullable String sUserID);

  /**
   * Undelete the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to undelete
   * @return {@link EChange#CHANGED} if the user was undeleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange undeleteUser (@Nullable String sUserID);

  /**
   * disable the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to disable
   * @return {@link EChange#CHANGED} if the user was disabled,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange disableUser (@Nullable String sUserID);

  /**
   * Enable the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to enable
   * @return {@link EChange#CHANGED} if the user was enabled,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange enableUser (@Nullable String sUserID);

  /**
   * Check if the passed combination of user ID and password matches.
   *
   * @param sUserID
   *        The ID of the user
   * @param sPlainTextPassword
   *        The plan text password to validate.
   * @return <code>true</code> if the password hash matches the stored hash for
   *         the specified user, <code>false</code> otherwise.
   */
  default boolean areUserIDAndPasswordValid (@Nullable final String sUserID, @Nullable final String sPlainTextPassword)
  {
    // No password is not allowed
    if (sPlainTextPassword == null)
      return false;

    // Is there such a user?
    final IUser aUser = getUserOfID (sUserID);
    if (aUser == null)
      return false;

    // Now compare the hashes
    final String sPasswordHashAlgorithm = aUser.getPasswordHash ().getAlgorithmName ();
    final IPasswordSalt aSalt = aUser.getPasswordHash ().getSalt ();
    final PasswordHash aPasswordHash = GlobalPasswordSettings.createUserPasswordHash (sPasswordHashAlgorithm, aSalt, sPlainTextPassword);
    return aUser.getPasswordHash ().equals (aPasswordHash);
  }
}
