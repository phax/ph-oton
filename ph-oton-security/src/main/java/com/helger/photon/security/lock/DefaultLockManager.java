/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;

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
  private static final Logger LOGGER = LoggerFactory.getLogger (DefaultLockManager.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private ICurrentUserIDProvider m_aCurrentUserIDProvider;
  @GuardedBy ("m_aRWLock")
  // Key: lockedObjectID, value: lock-info
  private final ICommonsMap <IDTYPE, ILockInfo> m_aLockedObjs = new CommonsHashMap <> ();
  private final AtomicBoolean m_aSilentMode = new AtomicBoolean (GlobalDebug.DEFAULT_SILENT_MODE);

  public DefaultLockManager (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    setCurrentUserIDProvider (aCurrentUserIDProvider);
  }

  public boolean isSilentMode ()
  {
    return m_aSilentMode.get ();
  }

  public boolean setSilentMode (final boolean bSilentMode)
  {
    return m_aSilentMode.getAndSet (bSilentMode);
  }

  public final void setCurrentUserIDProvider (@Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider)
  {
    ValueEnforcer.notNull (aCurrentUserIDProvider, "CurrentUserIDProvider");
    m_aRWLock.writeLockedGet ( () -> m_aCurrentUserIDProvider = aCurrentUserIDProvider);
  }

  @Nullable
  private String _getCurrentUserID ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aCurrentUserIDProvider.getCurrentUserID ());
  }

  @Nullable
  public final ILockInfo getLockInfo (@Nullable final IDTYPE aObjID)
  {
    return m_aRWLock.readLockedGet ( () -> m_aLockedObjs.get (aObjID));
  }

  @Nullable
  public final String getLockUserID (@Nullable final IDTYPE aObjID)
  {
    final ILockInfo aLock = getLockInfo (aObjID);
    return aLock != null ? aLock.getLockUserID () : null;
  }

  @Nullable
  public final LocalDateTime getLockDateTime (@Nullable final IDTYPE aObjID)
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
    ICommonsList <IDTYPE> aUnlockedObjects = null;

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (bUnlockOtherObjects)
      {
        // Unlock other objects first
        aUnlockedObjects = new CommonsArrayList <> ();
        // Unlock all except the object to be locked
        _unlockAllObjects (sUserID, new CommonsHashSet <> (aObjID), aUnlockedObjects);
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

    if (!isSilentMode ())
      if (LOGGER.isInfoEnabled ())
      {
        if (CollectionHelper.isNotEmpty (aUnlockedObjects))
          LOGGER.info ("Before locking, unlocked all objects of user '" + sUserID + "': " + aUnlockedObjects + " except '" + aObjID + "'");
        if (bIsNewLock)
          LOGGER.info ("User '" + sUserID + "' locked object '" + aObjID + "'");
      }

    return new LockResult <> (aObjID, eLocked, bIsNewLock, aUnlockedObjects);
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
  public final LockResult <IDTYPE> lockObjectAndUnlockAllOthers (@Nonnull final IDTYPE aObjID, @Nullable final String sUserID)
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
      if (!isSilentMode ())
        if (LOGGER.isWarnEnabled ())
          LOGGER.warn ("User '" + sUserID + "' could not unlock object '" + aObjID + "' because it is not locked");
      return EChange.UNCHANGED;
    }

    // Not locked by current user?
    if (!aCurrentLock.getLockUserID ().equals (sUserID))
    {
      // This may happen if the user was manually unlocked!
      if (!isSilentMode ())
        if (LOGGER.isWarnEnabled ())
          LOGGER.warn ("User '" +
                       sUserID +
                       "' could not unlock object '" +
                       aObjID +
                       "' because it is locked by '" +
                       aCurrentLock.getLockUserID () +
                       "'");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLocked ( () -> {
      // this user locked the object -> unlock it
      if (m_aLockedObjs.remove (aObjID) == null)
        throw new IllegalStateException ("Internal inconsistency: removing '" + aObjID + "' from lock list failed!");
    });

    if (!isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("User '" + sUserID + "' unlocked object '" + aObjID + "'");
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <IDTYPE> unlockAllObjectsOfCurrentUser ()
  {
    return unlockAllObjectsOfCurrentUserExcept ((Set <IDTYPE>) null);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <IDTYPE> unlockAllObjectsOfCurrentUserExcept (@Nullable final Set <IDTYPE> aObjectsToKeepLocked)
  {
    final String sCurrentUserID = _getCurrentUserID ();
    return unlockAllObjectsOfUserExcept (sCurrentUserID, aObjectsToKeepLocked);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <IDTYPE> unlockAllObjectsOfUser (@Nullable final String sUserID)
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
  public final ICommonsList <IDTYPE> unlockAllObjectsOfUserExcept (@Nullable final String sUserID,
                                                                   @Nullable final Set <IDTYPE> aObjectsToKeepLocked)
  {
    final ICommonsList <IDTYPE> aUnlockedObjects = new CommonsArrayList <> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.writeLocked ( () -> _unlockAllObjects (sUserID, aObjectsToKeepLocked, aUnlockedObjects));

      if (aUnlockedObjects.isNotEmpty ())
        if (!isSilentMode ())
          if (LOGGER.isInfoEnabled ())
            LOGGER.info ("Unlocked all objects of user '" +
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
  public final ICommonsSet <IDTYPE> getAllLockedObjects ()
  {
    return m_aRWLock.readLockedGet (m_aLockedObjs::copyOfKeySet);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsMap <IDTYPE, ILockInfo> getAllLockInfos ()
  {
    return m_aRWLock.readLockedGet (m_aLockedObjs::getClone);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <IDTYPE> getAllLockedObjectsOfCurrentUser ()
  {
    final String sCurrentUserID = _getCurrentUserID ();
    return getAllLockedObjectsOfUser (sCurrentUserID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <IDTYPE> getAllLockedObjectsOfUser (@Nullable final String sUserID)
  {
    final ICommonsSet <IDTYPE> ret = new CommonsHashSet <> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.readLocked ( () -> {
        for (final Map.Entry <IDTYPE, ILockInfo> aEntry : m_aLockedObjs.entrySet ())
          if (aEntry.getValue ().getLockUserID ().equals (sUserID))
            ret.add (aEntry.getKey ());
      });
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("CurrentUserIDProvider", m_aCurrentUserIDProvider)
                                       .append ("LockedObjects", m_aLockedObjs)
                                       .append ("SilentMode", m_aSilentMode)
                                       .getToString ();
  }
}
