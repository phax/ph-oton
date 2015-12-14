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
package com.helger.photon.basic.app;

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
  @GuardedBy ("s_aRWLock")
  private static String s_sDefaultAppID;

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
   * Remove the path mapped to the specified application ID. If the specified
   * application ID is the default one, the default application ID is set to
   * <code>null</code>.
   *
   * @param sApplicationID
   *        Application ID to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the path was successfully removed
   */
  @Nonnull
  public static EChange removePathMapping (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasText (sApplicationID))
    {
      s_aRWLock.writeLock ().lock ();
      try
      {
        if (s_aMap.remove (sApplicationID) != null)
        {
          // Check if we deleted the default
          if (sApplicationID.equals (s_sDefaultAppID))
            s_sDefaultAppID = null;
          return EChange.CHANGED;
        }
      }
      finally
      {
        s_aRWLock.writeLock ().unlock ();
      }
    }
    return EChange.UNCHANGED;
  }

  /**
   * Remove all contained path mappings (clearing).
   *
   * @return {@link EChange#CHANGED} if at least one path was removed,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public static EChange removeAllPathMappings ()
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      if (s_aMap.isEmpty ())
        return EChange.UNCHANGED;

      s_aMap.clear ();
      s_sDefaultAppID = null;
      return EChange.CHANGED;
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

  /**
   * @return <code>true</code> if mappings are already defined,
   *         <code>false</code> otherwise.
   */
  public static boolean containsMappings ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return !s_aMap.isEmpty ();
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Set the default application ID. This must be called AFTER the path mapping
   * was registered.
   *
   * @param sApplicationID
   *        The application ID to set as the default. May neither be
   *        <code>null</code> nor empty.
   * @see #setPathMapping(String, String)
   * @see #getDefaultApplicationID()
   */
  public static void setDefaultApplicationID (@Nonnull @Nonempty final String sApplicationID)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");

    s_aRWLock.writeLock ().lock ();
    try
    {
      if (!s_aMap.containsKey (sApplicationID))
        throw new IllegalArgumentException ("The passed application ID '" + sApplicationID + "' is unknown!");
      s_sDefaultAppID = sApplicationID;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return The default application ID. May be <code>null</code> if not set.
   * @see #setDefaultApplicationID(String)
   */
  @Nullable
  public static String getDefaultApplicationID ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_sDefaultAppID;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the path mapped to the default application ID
   *
   * @return <code>null</code> if no default application ID is set. A path
   *         starting with a slash but not ending with a slash otherwise.
   * @see #getDefaultApplicationID()
   */
  @Nullable
  public static String getPathOfDefaultApplicationID ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aMap.get (s_sDefaultAppID);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the path mapped to the specified application ID or (if no such
   * application ID is present) return the path of the default application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found and no default path is
   *         specified. A path starting with a slash but not ending with a slash
   *         otherwise.
   * @see #getPathOfApplicationID(String)
   * @see #getPathOfDefaultApplicationID()
   */
  @Nullable
  public static String getPathOfApplicationIDOrDefault (@Nullable final String sApplicationID)
  {
    final String sPath = getPathOfApplicationID (sApplicationID);
    return sPath != null ? sPath : getPathOfDefaultApplicationID ();
  }
}
