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
package com.helger.appbasics.security.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.security.login.ICurrentUserIDProvider;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ELockType;
import com.helger.commons.annotations.MustBeLocked;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of a locking manager.
 *
 * @author Philip Helger
 * @param <IDTYPE>
 *        The type of object to be locked. E.g. String or TypedObject. Must
 *        implement equals and hashCode!
 */
@ThreadSafe
public class DefaultLockManager <IDTYPE> implements ILockManager <IDTYPE>
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DefaultLockManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private ICurrentUserIDProvider m_aCurrentUserIDProvider;

  // Key: lockedObjectID, value: lock-info
  private final Map <IDTYPE, ILockInfo> m_aLockedObjs = new HashMap <IDTYPE, ILockInfo> ();

  public DefaultLockManager (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    setCurrentUserIDProvider (aCurrentUserIDProvider);
  }

  public final void setCurrentUserIDProvider (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aCurrentUserIDProvider = ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nullable
  private String _getCurrentUserID ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aCurrentUserIDProvider.getCurrentUserID ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public final ILockInfo getLockInfo (@Nullable final IDTYPE aObjID)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aLockedObjs.get (aObjID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public final String getLockUserID (@Nullable final IDTYPE aObjID)
  {
    final ILockInfo aLock = getLockInfo (aObjID);
    return aLock != null ? aLock.getLockUserID () : null;
  }

  @Nullable
  public final DateTime getLockDateTime (@Nullable final IDTYPE aObjID)
  {
    final ILockInfo aLock = getLockInfo (aObjID);
    return aLock != null ? aLock.getLockDateTime () : null;
  }

  @Nonnull
  public final ELocked lockObject (@Nonnull final IDTYPE aObjID)
  {
    ValueEnforcer.notNull (aObjID, "ObjectID");

    final String sCurrentUserID = _getCurrentUserID ();
    return lockObject (aObjID, sCurrentUserID);
  }

  @Nonnull
  private LockResult <IDTYPE> _lockObjectAndUnlockOthers (@Nonnull final IDTYPE aObjID,
                                                          @Nullable final String sUserID,
                                                          final boolean bUnlockOtherObjects)
  {
    if (StringHelper.hasNoText (sUserID))
      return LockResult.createFailure (aObjID);

    ELocked eLocked;
    boolean bIsNewLock = false;
    List <IDTYPE> aUnlockedObjects = null;

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (bUnlockOtherObjects)
      {
        // Unlock other objects first
        aUnlockedObjects = new ArrayList <IDTYPE> ();
        // Unlock all except the object to be locked
        _unlockAllObjects (sUserID, CollectionHelper.newSet (aObjID), aUnlockedObjects);
      }

      final ILockInfo aCurrentLock = m_aLockedObjs.get (aObjID);
      if (aCurrentLock != null)
      {
        // Object is already locked.
        // Check whether the current user locked the object
        eLocked = ELocked.valueOf (aCurrentLock.getLockUserID ().equals (sUserID));
      }
      else
      {
        // Object is not locked so far - lock it now!
        m_aLockedObjs.put (aObjID, new LockInfo (sUserID));
        eLocked = ELocked.LOCKED;
        bIsNewLock = true;
      }
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isInfoEnabled ())
    {
      if (CollectionHelper.isNotEmpty (aUnlockedObjects))
        s_aLogger.info ("Before locking, unlocked all objects of user '" +
                        sUserID +
                        "': " +
                        aUnlockedObjects +
                        " except '" +
                        aObjID +
                        "'");
      if (bIsNewLock)
        s_aLogger.info ("User '" + sUserID + "' locked object '" + aObjID + "'");
    }

    return new LockResult <IDTYPE> (aObjID, eLocked, bIsNewLock, aUnlockedObjects);
  }

  @Nonnull
  public final ELocked lockObject (@Nonnull final IDTYPE aObjID, @Nullable final String sUserID)
  {
    ValueEnforcer.notNull (aObjID, "ObjectID");

    // Don't unlock other objects
    final LockResult <IDTYPE> aLockResult = _lockObjectAndUnlockOthers (aObjID, sUserID, false);
    return ELocked.valueOf (aLockResult);
  }

  @Nonnull
  public final LockResult <IDTYPE> lockObjectAndUnlockAllOthers (@Nonnull final IDTYPE aObjID)
  {
    ValueEnforcer.notNull (aObjID, "ObjectID");

    final String sCurrentUserID = _getCurrentUserID ();
    return lockObjectAndUnlockAllOthers (aObjID, sCurrentUserID);
  }

  @Nonnull
  public final LockResult <IDTYPE> lockObjectAndUnlockAllOthers (@Nonnull final IDTYPE aObjID,
                                                                 @Nullable final String sUserID)
  {
    ValueEnforcer.notNull (aObjID, "ObjectID");

    // Unlock other objects
    return _lockObjectAndUnlockOthers (aObjID, sUserID, true);
  }

  @Nonnull
  public final EChange unlockObject (@Nonnull final IDTYPE aObjID)
  {
    final String sCurrentUserID = _getCurrentUserID ();
    if (StringHelper.hasNoText (sCurrentUserID))
      return EChange.UNCHANGED;

    return unlockObject (sCurrentUserID, aObjID);
  }

  @Nonnull
  public final EChange unlockObject (@Nonnull final String sUserID, @Nonnull final IDTYPE aObjID)
  {
    ValueEnforcer.notNull (sUserID, "UserID");
    ValueEnforcer.notNull (aObjID, "ObjectID");

    // Get the locking information of the objects
    final ILockInfo aCurrentLock = getLockInfo (aObjID);
    if (aCurrentLock == null)
    {
      // Object is not locked at all
      s_aLogger.warn ("User '" + sUserID + "' could not unlock object '" + aObjID + "' because it is not locked");
      return EChange.UNCHANGED;
    }

    // Not locked by current user?
    if (!aCurrentLock.getLockUserID ().equals (sUserID))
    {
      // This may happen if the user was manually unlocked!
      s_aLogger.warn ("User '" +
                      sUserID +
                      "' could not unlock object '" +
                      aObjID +
                      "' because it is locked by '" +
                      aCurrentLock.getLockUserID () +
                      "'");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      // this user locked the object -> unlock it
      if (m_aLockedObjs.remove (aObjID) == null)
        throw new IllegalStateException ("Internal inconsistency: removing '" + aObjID + "' from lock list failed!");
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isInfoEnabled ())
      s_aLogger.info ("User '" + sUserID + "' unlocked object '" + aObjID + "'");
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <IDTYPE> unlockAllObjectsOfCurrentUser ()
  {
    return unlockAllObjectsOfCurrentUserExcept ((Set <IDTYPE>) null);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <IDTYPE> unlockAllObjectsOfCurrentUserExcept (@Nullable final Set <IDTYPE> aObjectsToKeepLocked)
  {
    final String sCurrentUserID = _getCurrentUserID ();
    return unlockAllObjectsOfUserExcept (sCurrentUserID, aObjectsToKeepLocked);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <IDTYPE> unlockAllObjectsOfUser (@Nullable final String sUserID)
  {
    return unlockAllObjectsOfUserExcept (sUserID, (Set <IDTYPE>) null);
  }

  @MustBeLocked (ELockType.WRITE)
  private void _unlockAllObjects (@Nonnull @Nonempty final String sUserID,
                                  @Nullable final Set <IDTYPE> aObjectsToKeepLocked,
                                  @Nonnull final List <IDTYPE> aUnlockedObjects)
  {
    // determine locks to be removed
    for (final Map.Entry <IDTYPE, ILockInfo> aEntry : m_aLockedObjs.entrySet ())
    {
      final String sLockUserID = aEntry.getValue ().getLockUserID ();
      if (sLockUserID.equals (sUserID))
      {
        // Object is locked by current user
        final IDTYPE aObjID = aEntry.getKey ();
        if (aObjectsToKeepLocked == null || !aObjectsToKeepLocked.contains (aObjID))
          aUnlockedObjects.add (aObjID);
      }
    }

    // remove locks
    for (final IDTYPE aObjID : aUnlockedObjects)
      if (m_aLockedObjs.remove (aObjID) == null)
        throw new IllegalStateException ("Internal inconsistency: user '" +
                                         sUserID +
                                         "' failed to unlock '" +
                                         aObjID +
                                         "' from " +
                                         aUnlockedObjects);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <IDTYPE> unlockAllObjectsOfUserExcept (@Nullable final String sUserID,
                                                           @Nullable final Set <IDTYPE> aObjectsToKeepLocked)
  {
    final List <IDTYPE> aUnlockedObjects = new ArrayList <IDTYPE> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.writeLock ().lock ();
      try
      {
        _unlockAllObjects (sUserID, aObjectsToKeepLocked, aUnlockedObjects);
      }
      finally
      {
        m_aRWLock.writeLock ().unlock ();
      }

      if (!aUnlockedObjects.isEmpty ())
        if (s_aLogger.isInfoEnabled ())
          s_aLogger.info ("Unlocked all objects of user '" +
                          sUserID +
                          "': " +
                          aUnlockedObjects +
                          (CollectionHelper.isEmpty (aObjectsToKeepLocked) ? "" : " except " + aObjectsToKeepLocked));
    }
    return aUnlockedObjects;
  }

  public final boolean isObjectLockedByCurrentUser (@Nullable final IDTYPE aObjID)
  {
    final String sLockUserID = getLockUserID (aObjID);
    if (sLockUserID == null)
    {
      // Object is not locked at all
      return false;
    }
    final String sCurrentUserID = _getCurrentUserID ();
    return sLockUserID.equals (sCurrentUserID);
  }

  public final boolean isObjectLockedByOtherUser (@Nullable final IDTYPE aObjID)
  {
    final String sLockUser = getLockUserID (aObjID);
    if (sLockUser == null)
    {
      // Object is not locked at all
      return false;
    }
    final String sCurrentUserID = _getCurrentUserID ();
    return !sLockUser.equals (sCurrentUserID);
  }

  public final boolean isObjectLockedByAnyUser (@Nullable final IDTYPE aObjID)
  {
    return getLockUserID (aObjID) != null;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <IDTYPE> getAllLockedObjects ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newSet (m_aLockedObjs.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Map <IDTYPE, ILockInfo> getAllLockInfos ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aLockedObjs);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <IDTYPE> getAllLockedObjectsOfCurrentUser ()
  {
    final String sCurrentUserID = _getCurrentUserID ();
    return getAllLockedObjectsOfUser (sCurrentUserID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <IDTYPE> getAllLockedObjectsOfUser (@Nullable final String sUserID)
  {
    final Set <IDTYPE> ret = new HashSet <IDTYPE> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final Map.Entry <IDTYPE, ILockInfo> aEntry : m_aLockedObjs.entrySet ())
          if (aEntry.getValue ().getLockUserID ().equals (sUserID))
            ret.add (aEntry.getKey ());
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("currentUserIDProvider", m_aCurrentUserIDProvider)
                                       .append ("lockedObjects", m_aLockedObjs)
                                       .toString ();
  }
}
