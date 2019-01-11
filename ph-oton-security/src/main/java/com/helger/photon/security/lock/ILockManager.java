/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.security.lock;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.state.EChange;

/**
 * Base interface for a manager that handles object locking. See
 * {@link DefaultLockManager} for a per-instance implementation and
 * {@link ObjectLockManager} for a singleton version.
 *
 * @author Philip Helger
 * @param <IDTYPE>
 *        The type of object to be locked. E.g. String or TypedObject. Must
 *        implement equals and hashCode!
 */
public interface ILockManager <IDTYPE>
{
  /**
   * Get the lock information of the given object.
   *
   * @param aObjID
   *        The object to query for lock owner.
   * @return <code>null</code> if the object is not locked, the lock information
   *         otherwise
   */
  @Nullable
  ILockInfo getLockInfo (@Nullable IDTYPE aObjID);

  /**
   * Get the user ID who locked the given object.
   *
   * @param aObjID
   *        The object to query for lock owner.
   * @return <code>null</code> if the object is not locked, the user ID
   *         otherwise
   */
  @Nullable
  String getLockUserID (@Nullable IDTYPE aObjID);

  /**
   * Get the date and time when the given object was locked.
   *
   * @param aObjID
   *        The object to query for lock owner.
   * @return <code>null</code> if the object is not locked, the locking date
   *         time otherwise
   */
  @Nullable
  LocalDateTime getLockDateTime (@Nullable IDTYPE aObjID);

  /**
   * Lock the object with the given ID. If the passed object is already locked
   * by this user, this method has no effect. This is an atomic action.
   *
   * @param aObjID
   *        The object ID to lock. May not be <code>null</code>.
   * @return {@link ELocked#LOCKED} if the object is locked by the current user
   *         after the call to this method, {@link ELocked#NOT_LOCKED} if the
   *         object was already locked by another user.
   */
  @Nonnull
  ELocked lockObject (@Nonnull IDTYPE aObjID);

  /**
   * Lock the object with the given ID. If the passed object is already locked
   * by this user, this method has no effect. This is an atomic action.
   *
   * @param aObjID
   *        The object ID to lock. May not be <code>null</code>.
   * @param sUserID
   *        The id of the user who locked the object. May be <code>null</code>.
   * @return {@link ELocked#LOCKED} if the object is locked by the specified
   *         user after the call to this method, {@link ELocked#NOT_LOCKED} if
   *         the object was already locked by another user or no user ID was
   *         provided.
   */
  @Nonnull
  ELocked lockObject (@Nonnull IDTYPE aObjID, @Nullable String sUserID);

  /**
   * Lock the object with the given ID and unlock all other objects. If the
   * passed object is already locked by this user, this method has no effect.
   * This is an atomic action.
   *
   * @param aObjID
   *        The object ID to lock. May not be <code>null</code>.
   * @return The locking result and never <code>null</code>. If
   *         {@link ILockedIndicator#isLocked()} is <code>true</code>, the
   *         object is locked by the current user after the call to this method.
   *         If {@link ILockedIndicator#isNotLocked()} is <code>true</code> the
   *         object was already locked by another user.
   */
  @Nonnull
  LockResult <IDTYPE> lockObjectAndUnlockAllOthers (@Nonnull IDTYPE aObjID);

  /**
   * Lock the object with the given ID and unlock all other objects. If the
   * passed object is already locked by this user, this method has no effect.
   * This is an atomic action.
   *
   * @param aObjID
   *        The object ID to lock. May not be <code>null</code>.
   * @param sUserID
   *        The id of the user who locked the object. May be <code>null</code>.
   * @return {@link ELocked#LOCKED} if the object is locked by the specified
   *         user after the call to this method, {@link ELocked#NOT_LOCKED} if
   *         the object was already locked by another user or no user ID was
   *         provided.
   */
  @Nonnull
  LockResult <IDTYPE> lockObjectAndUnlockAllOthers (@Nonnull IDTYPE aObjID, @Nullable String sUserID);

  /**
   * Unlock the object with the given ID. Unlocking is only possible, if the
   * current session user locked the object.
   *
   * @param aObjID
   *        The object ID to unlock.
   * @return <code>true</code> if the object was successfully unlocked,
   *         <code>false</code> if either the object is not locked or the object
   *         is locked by another user than the current session user.
   */
  @Nonnull
  EChange unlockObject (@Nonnull IDTYPE aObjID);

  /**
   * Manually unlock a special object locked by a special user. This manual
   * version is only required for especially unlocking a user!
   *
   * @param sUserID
   *        The user who locked the object.
   * @param aObjID
   *        The object to be unlocked.
   * @return <code>true</code> if unlocking succeeded, <code>false</code>
   *         otherwise.
   */
  @Nonnull
  EChange unlockObject (@Nonnull String sUserID, @Nonnull IDTYPE aObjID);

  /**
   * Unlock all objects of the current user.
   *
   * @return The list of all unlocked object IDs. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IDTYPE> unlockAllObjectsOfCurrentUser ();

  /**
   * Unlock all objects of the current user except for the passed objects.
   *
   * @param aObjectsToKeepLocked
   *        An optional set of objects which should not be unlocked. May be
   *        <code>null</code> or empty.
   * @return The list of all unlocked object IDs. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IDTYPE> unlockAllObjectsOfCurrentUserExcept (@Nullable Set <IDTYPE> aObjectsToKeepLocked);

  /**
   * Unlock all objects of the passed user.
   *
   * @param sUserID
   *        The user ID who's object are to be unlocked. May be
   *        <code>null</code> or empty.
   * @return The list of all unlocked object IDs. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IDTYPE> unlockAllObjectsOfUser (@Nullable String sUserID);

  /**
   * Unlock all objects of the passed user except for the passed objects.
   *
   * @param sUserID
   *        The user ID who's object are to be unlocked. May be
   *        <code>null</code> or empty.
   * @param aObjectsToKeepLocked
   *        An optional set of objects which should not be unlocked. May be
   *        <code>null</code> or empty.
   * @return The list of all unlocked object IDs. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IDTYPE> unlockAllObjectsOfUserExcept (@Nullable String sUserID,
                                                      @Nullable Set <IDTYPE> aObjectsToKeepLocked);

  /**
   * Check if the object with the given ID is locked by the current user.
   *
   * @param aObjID
   *        The object ID to check.
   * @return <code>true</code> if the object is locked by the current user,
   *         <code>false</code> if the object is either not locked or locked by
   *         another user.
   */
  boolean isObjectLockedByCurrentUser (@Nullable IDTYPE aObjID);

  /**
   * Check if the object with the given ID is locked by any but the current
   * user.
   *
   * @param aObjID
   *        The object ID to check.
   * @return <code>true</code> if the object is locked by any user that is not
   *         the currently logged in user, <code>false</code> if the object is
   *         either not locked or locked by the current user.
   */
  boolean isObjectLockedByOtherUser (@Nullable IDTYPE aObjID);

  /**
   * Check if the object with the given ID is locked by any user.
   *
   * @param aObjID
   *        The object ID to check.
   * @return <code>true</code> if the object is locked by any user,
   *         <code>false</code> if the object is not locked.
   */
  boolean isObjectLockedByAnyUser (@Nullable IDTYPE aObjID);

  /**
   * @return A non-<code>null</code> set of all locked objects of all users.
   *         Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsSet <IDTYPE> getAllLockedObjects ();

  /**
   * @return A non-<code>null</code> map of all locked objects of all users.
   *         Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsMap <IDTYPE, ILockInfo> getAllLockInfos ();

  /**
   * @return A non-<code>null</code> set of all locked objects of the current
   *         user. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsSet <IDTYPE> getAllLockedObjectsOfCurrentUser ();

  /**
   * @param sUserID
   *        The user ID to be queried for all locked objects. May be
   *        <code>null</code>.
   * @return A non-<code>null</code> set of all locked objects of the passed
   *         user. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsSet <IDTYPE> getAllLockedObjectsOfUser (@Nullable String sUserID);
}
