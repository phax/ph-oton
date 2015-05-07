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
package com.helger.webbasics.form;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.random.VerySecureRandom;
import com.helger.commons.state.EValidity;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scopes.singleton.SessionWebSingleton;

@ThreadSafe
public class FormUniqueSubmitManager extends SessionWebSingleton
{
  /**
   * Number of bytes used to create a unique ID - results in a Unique ID string
   * of twice the length
   */
  public static final int UNIQUE_ID_BYTES = 8;

  private static final Logger s_aLogger = LoggerFactory.getLogger (FormUniqueSubmitManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, String> m_aMap = new HashMap <String, String> ();

  @Deprecated
  @UsedViaReflection
  public FormUniqueSubmitManager ()
  {}

  @Nonnull
  public static FormUniqueSubmitManager getInstance ()
  {
    return getSessionSingleton (FormUniqueSubmitManager.class);
  }

  /**
   * @return Create a new unique form submission ID based on a
   *         {@value #UNIQUE_ID_BYTES} random bytes.
   */
  @Nonnull
  @Nonempty
  public static String createUniqueFormSubmissionID ()
  {
    final byte [] aBytes = new byte [UNIQUE_ID_BYTES];
    VerySecureRandom.getInstance ().nextBytes (aBytes);
    return StringHelper.getHexEncoded (aBytes);
  }

  @Nonnull
  @Nonempty
  public String createAndRegisterUniqueFormSubmissionID (@Nonnull @Nonempty final String sFormName)
  {
    ValueEnforcer.notEmpty (sFormName, "FormName");

    final String sUniqueID = createUniqueFormSubmissionID ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      // Overwrite any existing previous entry
      m_aMap.put (sFormName, sUniqueID);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    s_aLogger.info ("Created unique ID " + sUniqueID + " for form '" + sFormName + "'");
    return sUniqueID;
  }

  public boolean isUniqueFormSubmissionIDRegistered (@Nullable final String sFormName)
  {
    if (StringHelper.hasNoText (sFormName))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sFormName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public String getUniqueFormSubmissionID (@Nullable final String sFormName)
  {
    if (StringHelper.hasNoText (sFormName))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sFormName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllUniqueFormSubmissionIDs ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aMap);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public EValidity checkAndUnregisterUniqueFormSubmissionID (@Nullable final String sFormName,
                                                             @Nullable final String sUniqueID)
  {
    if (StringHelper.hasNoText (sFormName))
      return EValidity.INVALID;
    if (StringHelper.hasNoText (sUniqueID))
      return EValidity.INVALID;

    m_aRWLock.writeLock ().lock ();
    try
    {
      final String sRegisteredUniqueID = m_aMap.get (sFormName);
      if (!sUniqueID.equals (sRegisteredUniqueID))
      {
        // Different (or no) ID stored!
        return EValidity.INVALID;
      }

      // It is valid, so we can remove it
      m_aMap.remove (sFormName);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return EValidity.VALID;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("map", m_aMap).toString ();
  }
}
