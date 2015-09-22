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
package com.helger.photon.core.app.path;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;

/**
 * Global mapper between application ID and the respective servlet paths.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonPathMapper
{
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static Map <String, String> s_aMap = new HashMap <> ();

  private PhotonPathMapper ()
  {}

  /**
   * Set a path mapping.
   *
   * @param sApplicationID
   *        Application ID to use. May neither be <code>null</code> nor empty.
   * @param sPath
   *        The path to use. May neither be <code>null</code> nor empty. Must
   *        start with a "slash" but may not end with a slash. Valid example is
   *        e.g. <code>/public</code>
   */
  public static void setPathMapping (@Nonnull @Nonempty final String sApplicationID,
                                     @Nonnull @Nonempty final String sPath)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.isTrue (sPath.startsWith ("/"), "Path must be empty or start with a slash");
    ValueEnforcer.isTrue (!sPath.endsWith ("/"), "Path must not end with a slash");

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aMap.put (sApplicationID, sPath);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Remove the path mapped to the specified application ID
   *
   * @param sApplicationID
   *        Application ID to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the path was successfully removed
   */
  @Nonnull
  public static EChange removePathMapping (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return EChange.UNCHANGED;

    s_aRWLock.writeLock ().lock ();
    try
    {
      return EChange.valueOf (s_aMap.remove (sApplicationID) != null);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Get the path mapped to the specified application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found. A path starting with a
   *         slash but not ending with a slash otherwise.
   */
  @Nullable
  public static String getPathOfApplicationID (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;

    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aMap.get (sApplicationID);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return A copy of all contained mapping entries. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, String> getApplicationIDToPathMap ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (s_aMap);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }
}
