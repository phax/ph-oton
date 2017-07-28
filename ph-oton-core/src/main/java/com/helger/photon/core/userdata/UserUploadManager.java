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
package com.helger.photon.core.userdata;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.scope.IScope;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * A per-session manager, that handles all the uploaded files while the process
 * to which the files belong is still in process.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class UserUploadManager extends AbstractSessionWebSingleton
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (UserUploadManager.class);

  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, TemporaryUserDataObject> m_aMap = new CommonsHashMap<> ();

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

    m_aRWLock.writeLocked ( () -> {
      // Remove an eventually existing old UDO with the same filename - avoid
      // bloating the list
      final TemporaryUserDataObject aOldUDO = m_aMap.remove (sFieldName);
      if (aOldUDO != null)
        _deleteUDO (aOldUDO);

      // Add the new one
      m_aMap.put (sFieldName, aUDO);
    });
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
  public ICommonsOrderedMap <String, UserDataObject> confirmUploadedFiles (@Nullable final String... aFieldNames)
  {
    final ICommonsOrderedMap <String, UserDataObject> ret = new CommonsLinkedHashMap<> ();
    if (aFieldNames != null)
    {
      m_aRWLock.writeLocked ( () -> {
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
      });
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
    return m_aRWLock.writeLocked ( () -> {
      if (StringHelper.hasNoText (sFieldName))
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
      return null;
    });
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
  public ICommonsList <String> cancelUploadedFiles (@Nullable final String... aFieldNames)
  {
    final ICommonsList <String> ret = new CommonsArrayList<> ();
    if (ArrayHelper.isNotEmpty (aFieldNames))
    {
      m_aRWLock.writeLocked ( () -> {
        for (final String sFieldName : aFieldNames)
        {
          final TemporaryUserDataObject aUDO = m_aMap.remove (sFieldName);
          if (aUDO != null)
          {
            _deleteUDO (aUDO);
            ret.add (sFieldName);
          }
        }
      });
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

    return m_aRWLock.readLocked ( () -> m_aMap.get (sFieldName));
  }

  /**
   * @return The number of uploaded files. Always &ge; 0.
   */
  @Nonnegative
  public int getUploadedFileCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.size ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).getToString ();
  }
}
