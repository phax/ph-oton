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
package com.helger.photon.security.lock;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;

/**
 * This class contains the result of a locking operation.
 *
 * @author Philip Helger
 * @param <IDTYPE>
 *        The type of the ID of the locked object.
 */
@Immutable
public final class LockResult <IDTYPE> implements ILockedIndicator
{
  private final IDTYPE m_aObjID;
  private final ELocked m_eLocked;
  private final boolean m_bIsNewLock;
  private final ICommonsList <IDTYPE> m_aUnlockedObjects;

  public LockResult (@NonNull final IDTYPE aObjID,
                     @NonNull final ELocked eLocked,
                     final boolean bIsNewLock,
                     @Nullable final List <IDTYPE> aUnlockedObjects)
  {
    m_aObjID = ValueEnforcer.notNull (aObjID, "ObjectID");
    m_eLocked = ValueEnforcer.notNull (eLocked, "Locked");
    m_bIsNewLock = bIsNewLock;
    m_aUnlockedObjects = new CommonsArrayList <> (aUnlockedObjects);
  }

  /**
   * @return The ID of the locked object. Never <code>null</code>.
   */
  @NonNull
  public IDTYPE getLockedObjectID ()
  {
    return m_aObjID;
  }

  public boolean isLocked ()
  {
    return m_eLocked.isLocked ();
  }

  public boolean isNotLocked ()
  {
    return m_eLocked.isNotLocked ();
  }

  /**
   * @return <code>true</code> if the lock was just created or
   *         <code>false</code> if the object was already locked.
   */
  public boolean isNewLock ()
  {
    return m_bIsNewLock;
  }

  /**
   * @return A list with all objects that were unlocked during the locking
   *         process. Never <code>null</code> but maybe empty.
   */
  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IDTYPE> getUnlockedObjects ()
  {
    return m_aUnlockedObjects.getClone ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final LockResult <?> rhs = (LockResult <?>) o;
    return m_aObjID.equals (rhs.m_aObjID) &&
           m_eLocked.equals (rhs.m_eLocked) &&
           m_bIsNewLock == rhs.m_bIsNewLock &&
           m_aUnlockedObjects.equals (rhs.m_aUnlockedObjects);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aObjID)
                                       .append (m_eLocked)
                                       .append (m_bIsNewLock)
                                       .append (m_aUnlockedObjects)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("objID", m_aObjID)
                                       .append ("locked", m_eLocked)
                                       .append ("isNewLock", m_bIsNewLock)
                                       .appendIf ("unlockedObjects", m_aUnlockedObjects, CollectionHelper::isNotEmpty)
                                       .getToString ();
  }

  @NonNull
  public static <IDTYPE> LockResult <IDTYPE> createFailure (@NonNull final IDTYPE aObjID)
  {
    return new LockResult <> (aObjID, ELocked.NOT_LOCKED, false, null);
  }
}
