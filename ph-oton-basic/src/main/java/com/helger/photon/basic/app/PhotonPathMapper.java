/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.concurrent.SimpleReadWriteLock;
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
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

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

    s_aRWLock.writeLocked ( () -> {
      s_aMap.put (sApplicationID, sPath);
    });
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
    if (StringHelper.hasNoText (sApplicationID))
      return EChange.UNCHANGED;

    return s_aRWLock.writeLocked ( () -> {
      if (s_aMap.remove (sApplicationID) == null)
        return EChange.UNCHANGED;

      // Check if we deleted the default
      if (sApplicationID.equals (s_sDefaultAppID))
        s_sDefaultAppID = null;
      return EChange.CHANGED;
    });
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
    return s_aRWLock.writeLocked ( () -> {
      if (s_aMap.isEmpty ())
        return EChange.UNCHANGED;

      s_aMap.clear ();
      s_sDefaultAppID = null;
      return EChange.CHANGED;
    });
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

    return s_aRWLock.readLocked ( () -> s_aMap.get (sApplicationID));
  }

  /**
   * @return A copy of all contained mapping entries. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, String> getApplicationIDToPathMap ()
  {
    return s_aRWLock.readLocked ( () -> CollectionHelper.newMap (s_aMap));
  }

  /**
   * @return <code>true</code> if mappings are already defined,
   *         <code>false</code> otherwise.
   */
  public static boolean containsMappings ()
  {
    return s_aRWLock.readLocked ( () -> !s_aMap.isEmpty ());
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

    s_aRWLock.writeLocked ( () -> {
      if (!s_aMap.containsKey (sApplicationID))
        throw new IllegalArgumentException ("The passed application ID '" + sApplicationID + "' is unknown!");
      s_sDefaultAppID = sApplicationID;
    });
  }

  /**
   * @return The default application ID. May be <code>null</code> if not set.
   * @see #setDefaultApplicationID(String)
   */
  @Nullable
  public static String getDefaultApplicationID ()
  {
    return s_aRWLock.readLocked ( () -> s_sDefaultAppID);
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
    return s_aRWLock.readLocked ( () -> s_aMap.get (s_sDefaultAppID));
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
