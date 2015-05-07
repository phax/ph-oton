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
package com.helger.webbasics.userdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.scopes.IScope;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.web.scopes.singleton.SessionWebSingleton;

/**
 * A per-session manager, that handles all the uploaded files while the process
 * to which the files belong is still in process.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserUploadManager extends SessionWebSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (UserUploadManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, TemporaryUserDataObject> m_aMap = new HashMap <String, TemporaryUserDataObject> ();

  @Deprecated
  @UsedViaReflection
  public UserUploadManager ()
  {}

  @Nonnull
  public static UserUploadManager getInstance ()
  {
    return getSessionSingleton (UserUploadManager.class);
  }

  @Nonnull
  private static FileIOError _deleteUDO (@Nonnull final TemporaryUserDataObject aUDO)
  {
    s_aLogger.info ("Deleting uploaded file " + aUDO);
    final FileIOError aError = WebFileIO.getFileOpMgr ().deleteFile (aUDO.getAsFile ());
    if (aError.isFailure ())
      s_aLogger.error ("Failed to delete UDO " + aUDO.getPath () + ": " + aError.getErrorCode ());
    return aError;
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    // Delete all unconfirmed files
    for (final TemporaryUserDataObject aUDO : m_aMap.values ())
      _deleteUDO (aUDO);
    m_aMap.clear ();
  }

  /**
   * Add an uploaded file. Existing UDOs with the same field name are
   * overwritten and the underlying file is deleted. By default an uploaded file
   * is not confirmed and will be deleted when the session expires. By
   * confirming the uploaded image it is safe for later reuse.
   *
   * @param sFieldName
   *        The ID of the uploaded file. May neither be <code>null</code> nor
   *        empty.
   * @param aUDO
   *        The user data object to be added. May not be <code>null</code>.
   * @see #confirmUploadedFiles(String...)
   */
  public void addUploadedFile (@Nonnull @Nonempty final String sFieldName, @Nonnull final TemporaryUserDataObject aUDO)
  {
    ValueEnforcer.notEmpty (sFieldName, "FieldName");
    ValueEnforcer.notNull (aUDO, "UDO");

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Remove an eventually existing old UDO with the same filename - avoid
      // bloating the list
      final TemporaryUserDataObject aOldUDO = m_aMap.remove (sFieldName);
      if (aOldUDO != null)
        _deleteUDO (aOldUDO);

      // Add the new one
      m_aMap.put (sFieldName, aUDO);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Confirm the uploaded files with the passed field names.
   *
   * @param aFieldNames
   *        The field names to be confirmed. May be <code>null</code>.
   * @return A map from the passed field name to the (non-temporary) user data
   *         objects for all resolved IDs. Never <code>null</code>. Field names
   *         that could not be resolved are not returned. If a field name is
   *         contained more than once, it is returned only once.
   * @see #cancelUploadedFiles(String...)
   */
  @Nonnull
  @ReturnsMutableCopy
  public Map <String, UserDataObject> confirmUploadedFiles (@Nullable final String... aFieldNames)
  {
    final Map <String, UserDataObject> ret = new LinkedHashMap <String, UserDataObject> ();
    if (aFieldNames != null)
    {
      m_aRWLock.writeLock ().lock ();
      try
      {
        for (final String sFieldName : aFieldNames)
        {
          // Remove an eventually existing old UDO
          final TemporaryUserDataObject aUDO = m_aMap.remove (sFieldName);
          if (aUDO != null)
          {
            s_aLogger.info ("Confirmed uploaded file " + aUDO);
            // Convert from temporary to real UDO
            ret.put (sFieldName, new UserDataObject (aUDO.getPath ()));
          }
        }
      }
      finally
      {
        m_aRWLock.writeLock ().unlock ();
      }
    }
    return ret;
  }

  /**
   * Confirm a single uploaded file with the passed field name.
   *
   * @param sFieldName
   *        The field name to be confirmed. May be <code>null</code>.
   * @return The (non-temporary) user data object for the resolved field name.
   *         May be <code>null</code> if the passed field name is not contained.
   * @see #cancelUploadedFiles(String...)
   */
  @Nullable
  public UserDataObject confirmUploadedFile (@Nullable final String sFieldName)
  {
    if (StringHelper.hasText (sFieldName))
    {
      m_aRWLock.writeLock ().lock ();
      try
      {
        // Remove an eventually existing old UDO
        final TemporaryUserDataObject aUDO = m_aMap.remove (sFieldName);
        if (aUDO != null)
        {
          s_aLogger.info ("Confirmed uploaded file " + aUDO);
          // Convert from temporary to real UDO
          return new UserDataObject (aUDO.getPath ());
        }
      }
      finally
      {
        m_aRWLock.writeLock ().unlock ();
      }
    }
    return null;
  }

  /**
   * Remove all uploaded files and delete the underlying UDO objects. This is
   * usually called, when the operation is cancelled without saving.
   *
   * @param aFieldNames
   *        The IDs to be removed and deleted.
   * @return A non-<code>null</code> list with all field names that could be
   *         resolved and were removed.
   * @see #confirmUploadedFiles(String...)
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <String> cancelUploadedFiles (@Nullable final String... aFieldNames)
  {
    final List <String> ret = new ArrayList <String> ();
    if (ArrayHelper.isNotEmpty (aFieldNames))
    {
      m_aRWLock.writeLock ().lock ();
      try
      {
        for (final String sFieldName : aFieldNames)
        {
          final TemporaryUserDataObject aUDO = m_aMap.remove (sFieldName);
          if (aUDO != null)
          {
            _deleteUDO (aUDO);
            ret.add (sFieldName);
          }
        }
      }
      finally
      {
        m_aRWLock.writeLock ().unlock ();
      }
    }
    return ret;
  }

  /**
   * Get the user data object matching the specified field name.
   *
   * @param sFieldName
   *        The ID to be searched. May be <code>null</code>.
   * @return <code>null</code> if the passed field name could not be resolved.
   */
  @Nullable
  public TemporaryUserDataObject getUploadedFile (@Nullable final String sFieldName)
  {
    if (StringHelper.hasNoText (sFieldName))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sFieldName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The number of uploaded files. Always &ge; 0.
   */
  @Nonnegative
  public int getUploadedFileCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
