/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Global mapper between application ID and the respective application servlet
 * paths.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonPathMapper
{
  public static final class PathEntry
  {
    private String m_sApplicationServletPath;
    private String m_sAjaxServletPath;
    private String m_sAPIServletPath;

    private PathEntry ()
    {}

    @Nullable
    public String getApplicationServletPath ()
    {
      return m_sApplicationServletPath;
    }

    private void _setApplicationServletPath (@Nullable final String sApplicationServletPath)
    {
      m_sApplicationServletPath = sApplicationServletPath;
    }

    @Nullable
    public String getAjaxServletPath ()
    {
      return m_sAjaxServletPath;
    }

    private void _setAjaxServletPath (@Nullable final String sAjaxServletPath)
    {
      m_sAjaxServletPath = sAjaxServletPath;
    }

    @Nullable
    public String getAPIServletPath ()
    {
      return m_sAPIServletPath;
    }

    private void _setAPIServletPath (@Nullable final String sAPIServletPath)
    {
      m_sAPIServletPath = sAPIServletPath;
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (null).append ("ApplicationServletPath", m_sApplicationServletPath)
                                         .append ("AjaxServletPath", m_sAjaxServletPath)
                                         .append ("APIServletPath", m_sAPIServletPath)
                                         .getToString ();
    }
  }

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static String s_sDefaultAppID;
  @GuardedBy ("s_aRWLock")
  private static ICommonsMap <String, PathEntry> s_aMap = new CommonsHashMap <> ();

  private PhotonPathMapper ()
  {}

  /**
   * Set an application servlet path mapping. This overwrites existing mappings.
   *
   * @param sApplicationID
   *        Application ID to use. May neither be <code>null</code> nor empty.
   * @param sApplicationServletPath
   *        The path to use. May neither be <code>null</code> nor empty. Must
   *        start with a "slash" but may not end with a slash. Valid example is
   *        e.g. <code>/public</code>
   */
  public static void setApplicationServletPathMapping (@Nonnull @Nonempty final String sApplicationID,
                                                       @Nonnull @Nonempty final String sApplicationServletPath)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notEmpty (sApplicationServletPath, "ApplicationServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sApplicationServletPath, '/'),
                          "ApplicationServletPath must be empty or start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sApplicationServletPath, '/'),
                           "ApplicationServletPath must not end with a slash");

    s_aRWLock.writeLocked ( () -> s_aMap.computeIfAbsent (sApplicationID, x -> new PathEntry ())
                                        ._setApplicationServletPath (sApplicationServletPath));
  }

  /**
   * Set an ajax servlet path mapping. This overwrites existing mappings.
   *
   * @param sApplicationID
   *        Application ID to use. May neither be <code>null</code> nor empty.
   * @param sAjaxServletPath
   *        The path to use. May neither be <code>null</code> nor empty. Must
   *        start with a "slash" but may not end with a slash. Valid example is
   *        e.g. <code>/publicajax</code>
   */
  public static void setAjaxServletPathMapping (@Nonnull @Nonempty final String sApplicationID,
                                                @Nonnull @Nonempty final String sAjaxServletPath)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notEmpty (sAjaxServletPath, "AjaxServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sAjaxServletPath, '/'),
                          "AjaxServletPath must be empty or start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sAjaxServletPath, '/'), "AjaxServletPath must not end with a slash");

    s_aRWLock.writeLocked ( () -> s_aMap.computeIfAbsent (sApplicationID, x -> new PathEntry ())
                                        ._setAjaxServletPath (sAjaxServletPath));
  }

  /**
   * Set an API servlet path mapping. This overwrites existing mappings.
   *
   * @param sApplicationID
   *        Application ID to use. May neither be <code>null</code> nor empty.
   * @param sAPIServletPath
   *        The path to use. May neither be <code>null</code> nor empty. Must
   *        start with a "slash" but may not end with a slash. Valid example is
   *        e.g. <code>/publicapi</code>
   * @since 7.0.2
   */
  public static void setAPIServletPathMapping (@Nonnull @Nonempty final String sApplicationID,
                                               @Nonnull @Nonempty final String sAPIServletPath)
  {
    ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    ValueEnforcer.notEmpty (sAPIServletPath, "APIServletPath");
    ValueEnforcer.isTrue (StringHelper.startsWith (sAPIServletPath, '/'),
                          "APIServletPath must be empty or start with a slash");
    ValueEnforcer.isFalse (StringHelper.endsWith (sAPIServletPath, '/'), "APIServletPath must not end with a slash");

    s_aRWLock.writeLocked ( () -> s_aMap.computeIfAbsent (sApplicationID, x -> new PathEntry ())
                                        ._setAPIServletPath (sAPIServletPath));
  }

  /**
   * Remove the all paths mapped to the specified application ID. If the
   * specified application ID is the default one, the default application ID is
   * set to <code>null</code>.
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
   * Remove all contained path mappings (clearing). Also resets the default
   * application ID.
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
   * Get the application servlet path mapped to the specified application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found. A path starting with a
   *         slash but not ending with a slash otherwise.
   */
  @Nullable
  public static String getApplicationServletPathOfApplicationID (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;

    return s_aRWLock.readLocked ( () -> {
      final PathEntry aEntry = s_aMap.get (sApplicationID);
      return aEntry == null ? null : aEntry.getApplicationServletPath ();
    });
  }

  /**
   * Get the ajax servlet path mapped to the specified application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found. A path starting with a
   *         slash but not ending with a slash otherwise.
   */
  @Nullable
  public static String getAjaxServletPathOfApplicationID (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;

    return s_aRWLock.readLocked ( () -> {
      final PathEntry aEntry = s_aMap.get (sApplicationID);
      return aEntry == null ? null : aEntry.getAjaxServletPath ();
    });
  }

  /**
   * Get the API servlet path mapped to the specified application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found. A path starting with a
   *         slash but not ending with a slash otherwise.
   * @since 7.0.2
   */
  @Nullable
  public static String getAPIServletPathOfApplicationID (@Nullable final String sApplicationID)
  {
    if (StringHelper.hasNoText (sApplicationID))
      return null;

    return s_aRWLock.readLocked ( () -> {
      final PathEntry aEntry = s_aMap.get (sApplicationID);
      return aEntry == null ? null : aEntry.getAPIServletPath ();
    });
  }

  /**
   * @return <code>true</code> if mappings are already defined,
   *         <code>false</code> otherwise.
   */
  public static boolean containsAnyMapping ()
  {
    return s_aRWLock.readLocked ( () -> s_aMap.isNotEmpty ());
  }

  /**
   * @return A copy of all contained application servlet mapping entries. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, PathEntry> getApplicationIDToPathEntryMap ()
  {
    return s_aRWLock.readLocked ( () -> new CommonsHashMap <> (s_aMap.getClone ()));
  }

  /**
   * @return A copy of all contained application servlet mapping entries. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, String> getApplicationIDToApplicationServletPathMap ()
  {
    return s_aRWLock.readLocked ( () -> new CommonsHashMap <> (s_aMap.entrySet (),
                                                               x -> x.getKey (),
                                                               x -> x.getValue ().getApplicationServletPath ()));
  }

  /**
   * @return A copy of all contained ajax servlet mapping entries. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, String> getApplicationIDToAjaxServletPathMap ()
  {
    return s_aRWLock.readLocked ( () -> new CommonsHashMap <> (s_aMap.entrySet (),
                                                               x -> x.getKey (),
                                                               x -> x.getValue ().getAjaxServletPath ()));
  }

  /**
   * @return A copy of all contained API servlet mapping entries. Never
   *         <code>null</code>.
   * @since 7.0.2
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, String> getApplicationIDToAPIServletPathMap ()
  {
    return s_aRWLock.readLocked ( () -> new CommonsHashMap <> (s_aMap.entrySet (),
                                                               x -> x.getKey (),
                                                               x -> x.getValue ().getAPIServletPath ()));
  }

  /**
   * Set the default application ID. This must be called AFTER the path mapping
   * was registered.
   *
   * @param sApplicationID
   *        The application ID to set as the default. May neither be
   *        <code>null</code> nor empty.
   * @see #setApplicationServletPathMapping(String, String)
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
   * Get the application servlet path mapped to the default application ID
   *
   * @return <code>null</code> if no default application ID is set. A path
   *         starting with a slash but not ending with a slash otherwise.
   * @see #getDefaultApplicationID()
   */
  @Nullable
  public static String getApplicationServletPathOfDefaultApplicationID ()
  {
    return getApplicationServletPathOfApplicationID (getDefaultApplicationID ());
  }

  /**
   * Get the ajax servlet path mapped to the default application ID
   *
   * @return <code>null</code> if no default application ID is set. A path
   *         starting with a slash but not ending with a slash otherwise.
   * @see #getDefaultApplicationID()
   */
  @Nullable
  public static String getAjaxServletPathOfDefaultApplicationID ()
  {
    return getAjaxServletPathOfApplicationID (getDefaultApplicationID ());
  }

  /**
   * Get the API servlet path mapped to the default application ID
   *
   * @return <code>null</code> if no default application ID is set. A path
   *         starting with a slash but not ending with a slash otherwise.
   * @see #getDefaultApplicationID()
   * @since 7.0.2
   */
  @Nullable
  public static String getAPIServletPathOfDefaultApplicationID ()
  {
    return getAPIServletPathOfApplicationID (getDefaultApplicationID ());
  }

  /**
   * Get the application servlet path mapped to the specified application ID or
   * (if no such application ID is present) return the path of the default
   * application ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found and no default path is
   *         specified. A path starting with a slash but not ending with a slash
   *         otherwise.
   * @see #getApplicationServletPathOfApplicationID(String)
   * @see #getApplicationServletPathOfDefaultApplicationID()
   */
  @Nullable
  public static String getApplicationServletPathOfApplicationIDOrDefault (@Nullable final String sApplicationID)
  {
    final String sPath = getApplicationServletPathOfApplicationID (sApplicationID);
    return sPath != null ? sPath : getApplicationServletPathOfDefaultApplicationID ();
  }

  /**
   * Get the ajax servlet path mapped to the specified application ID or (if no
   * such application ID is present) return the path of the default application
   * ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found and no default path is
   *         specified. A path starting with a slash but not ending with a slash
   *         otherwise.
   * @see #getAjaxServletPathOfApplicationID(String)
   * @see #getAjaxServletPathOfDefaultApplicationID()
   */
  @Nullable
  public static String getAjaxServletPathOfApplicationIDOrDefault (@Nullable final String sApplicationID)
  {
    final String sPath = getAjaxServletPathOfApplicationID (sApplicationID);
    return sPath != null ? sPath : getAjaxServletPathOfDefaultApplicationID ();
  }

  /**
   * Get the API servlet path mapped to the specified application ID or (if no
   * such application ID is present) return the path of the default application
   * ID
   *
   * @param sApplicationID
   *        Application ID to check. May be <code>null</code>.
   * @return <code>null</code> if no mapping was found and no default path is
   *         specified. A path starting with a slash but not ending with a slash
   *         otherwise.
   * @see #getAPIServletPathOfApplicationID(String)
   * @see #getAPIServletPathOfDefaultApplicationID()
   * @since 7.0.2
   */
  @Nullable
  public static String getAPIServletPathOfApplicationIDOrDefault (@Nullable final String sApplicationID)
  {
    final String sPath = getAPIServletPathOfApplicationID (sApplicationID);
    return sPath != null ? sPath : getAPIServletPathOfDefaultApplicationID ();
  }
}
