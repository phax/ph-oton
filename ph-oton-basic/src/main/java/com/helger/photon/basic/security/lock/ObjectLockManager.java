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
package com.helger.photon.basic.security.lock;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.joda.time.DateTime;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.security.login.LoggedInUserManager;

/**
 * Singleton of {@link ILockManager}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class ObjectLockManager extends AbstractGlobalSingleton implements ILockManager <String>
{
  private final DefaultLockManager <String> m_aMgr = new DefaultLockManager <String> (LoggedInUserManager.getInstance ());

  @Deprecated
  @UsedViaReflection
  public ObjectLockManager ()
  {}

  @Nonnull
  public static ObjectLockManager getInstance ()
  {
    return getGlobalSingleton (ObjectLockManager.class);
  }

  @Nullable
  public static ObjectLockManager getInstanceIfInstantiated ()
  {
    return getGlobalSingletonIfInstantiated (ObjectLockManager.class);
  }

  @Nonnull
  public DefaultLockManager <String> getDefaultLockMgr ()
  {
    return m_aMgr;
  }

  @Nullable
  public ILockInfo getLockInfo (@Nullable final String sObjID)
  {
    return m_aMgr.getLockInfo (sObjID);
  }

  @Nullable
  public String getLockUserID (@Nullable final String sObjID)
  {
    return m_aMgr.getLockUserID (sObjID);
  }

  @Nullable
  public DateTime getLockDateTime (@Nullable final String sObjID)
  {
    return m_aMgr.getLockDateTime (sObjID);
  }

  @Nonnull
  public ELocked lockObject (@Nonnull final String sObjID)
  {
    return m_aMgr.lockObject (sObjID);
  }

  @Nonnull
  public ELocked lockObject (@Nonnull final String sObjID, @Nullable final String sUserID)
  {
    return m_aMgr.lockObject (sObjID, sUserID);
  }

  @Nonnull
  public LockResult <String> lockObjectAndUnlockAllOthers (@Nonnull final String sObjID)
  {
    return m_aMgr.lockObjectAndUnlockAllOthers (sObjID);
  }

  @Nonnull
  public LockResult <String> lockObjectAndUnlockAllOthers (@Nonnull final String sObjID, @Nullable final String sUserID)
  {
    return m_aMgr.lockObjectAndUnlockAllOthers (sObjID, sUserID);
  }

  @Nonnull
  public EChange unlockObject (@Nonnull final String sObjID)
  {
    return m_aMgr.unlockObject (sObjID);
  }

  @Nonnull
  public EChange unlockObject (@Nonnull final String sUserID, @Nonnull final String sObjID)
  {
    return m_aMgr.unlockObject (sUserID, sObjID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> unlockAllObjectsOfCurrentUser ()
  {
    return m_aMgr.unlockAllObjectsOfCurrentUser ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> unlockAllObjectsOfCurrentUserExcept (@Nullable final Set <String> aObjectsToKeepLocked)
  {
    return m_aMgr.unlockAllObjectsOfCurrentUserExcept (aObjectsToKeepLocked);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> unlockAllObjectsOfUser (@Nullable final String sUserID)
  {
    return m_aMgr.unlockAllObjectsOfUser (sUserID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> unlockAllObjectsOfUserExcept (@Nullable final String sUserID,
                                                     @Nullable final Set <String> aObjectsToKeepLocked)
  {
    return m_aMgr.unlockAllObjectsOfUserExcept (sUserID, aObjectsToKeepLocked);
  }

  public boolean isObjectLockedByCurrentUser (@Nullable final String sObjID)
  {
    return m_aMgr.isObjectLockedByCurrentUser (sObjID);
  }

  public boolean isObjectLockedByOtherUser (@Nullable final String sObjID)
  {
    return m_aMgr.isObjectLockedByOtherUser (sObjID);
  }

  public boolean isObjectLockedByAnyUser (@Nullable final String sObjID)
  {
    return m_aMgr.isObjectLockedByAnyUser (sObjID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllLockedObjects ()
  {
    return m_aMgr.getAllLockedObjects ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, ILockInfo> getAllLockInfos ()
  {
    return m_aMgr.getAllLockInfos ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllLockedObjectsOfCurrentUser ()
  {
    return m_aMgr.getAllLockedObjectsOfCurrentUser ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllLockedObjectsOfUser (@Nullable final String sUserID)
  {
    return m_aMgr.getAllLockedObjectsOfUser (sUserID);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("mgr", m_aMgr).toString ();
  }
}
