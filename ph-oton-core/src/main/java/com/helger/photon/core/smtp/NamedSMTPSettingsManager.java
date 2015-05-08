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
package com.helger.photon.core.smtp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.IHasSize;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.smtp.ISMTPSettings;
import com.helger.smtp.impl.SMTPSettings;

/**
 * This class manages {@link NamedSMTPSettings} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class NamedSMTPSettingsManager extends AbstractSimpleDAO implements IHasSize
{
  private static final String ELEMENT_NAMEDSMTPSETTINGSLIST = "namedsmtpsettingslist";
  private static final String ELEMENT_NAMEDSMTPSETTINGS = "namedsmtpsettings";

  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  private static boolean s_bCreateDefaults = true;

  private final Map <String, NamedSMTPSettings> m_aMap = new HashMap <String, NamedSMTPSettings> ();

  public static boolean isCreateDefaults ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bCreateDefaults;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setCreateDefaults (final boolean bCreateDefaults)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_bCreateDefaults = bCreateDefaults;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public NamedSMTPSettingsManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMap.clear ();
      initialRead ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    if (!isCreateDefaults ())
      return EChange.UNCHANGED;

    // Create default item with as little data as possible
    _addItem (new NamedSMTPSettings (CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_ID,
                                     CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_NAME,
                                     new SMTPSettings (CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_HOST,
                                                       CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_PORT,
                                                       null,
                                                       null,
                                                       CCharset.CHARSET_UTF_8,
                                                       false)));
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eNamedSMTPSettings : aDoc.getDocumentElement ()
                                                      .getAllChildElements (ELEMENT_NAMEDSMTPSETTINGS))
      _addItem (MicroTypeConverter.convertToNative (eNamedSMTPSettings, NamedSMTPSettings.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_NAMEDSMTPSETTINGSLIST);
    for (final NamedSMTPSettings aNamedSMTPSettings : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aNamedSMTPSettings, ELEMENT_NAMEDSMTPSETTINGS));
    return aDoc;
  }

  private void _addItem (@Nonnull final NamedSMTPSettings aNamedSMTPSettings)
  {
    final String sUserID = aNamedSMTPSettings.getID ();
    if (m_aMap.containsKey (sUserID))
      throw new IllegalArgumentException ("NamedSMTPSettings ID " + sUserID + " is already in use!");
    m_aMap.put (sUserID, aNamedSMTPSettings);
  }

  @Nonnegative
  public int size ()
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

  public boolean isEmpty ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.isEmpty ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return A copy of all contained SMTP settings as map from ID to object.
   *         Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Map <String, NamedSMTPSettings> getAllSettings ()
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

  /**
   * Check if the specified settings ID is contained or not.
   *
   * @param sID
   *        The ID to check. May be <code>null</code>.
   * @return <code>true</code> if the passed ID is contained
   */
  public boolean containsSettings (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the settings of the specified ID
   *
   * @param sID
   *        The ID to search. May be <code>null</code>.
   * @return <code>null</code> if no such settings are contained.
   */
  @Nullable
  public NamedSMTPSettings getSettings (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the settings with the default ID.
   *
   * @return <code>null</code> if no such settings are contained.
   */
  @Nullable
  public NamedSMTPSettings getDefaultSettings ()
  {
    return getSettings (CNamedSMTPSettings.NAMED_SMTP_SETTINGS_DEFAULT_ID);
  }

  /**
   * Get the SMTP settings with the default ID.
   *
   * @return <code>null</code> if no such settings are contained.
   */
  @Nullable
  public ISMTPSettings getDefaultSMTPSettings ()
  {
    final NamedSMTPSettings aSettings = getDefaultSettings ();
    return aSettings == null ? null : aSettings.getSMTPSettings ();
  }

  /**
   * Get the settings with the specified name
   *
   * @param sName
   *        The name to search. May be <code>null</code>.
   * @return <code>null</code> if no such settings are contained.
   */
  @Nullable
  public NamedSMTPSettings getSettingsOfName (@Nullable final String sName)
  {
    if (StringHelper.hasNoText (sName))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final NamedSMTPSettings aSettings : m_aMap.values ())
        if (aSettings.getName ().equals (sName))
          return aSettings;
      return null;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Create a new settings object.
   *
   * @param sName
   *        The name of the settings. May neither be <code>null</code> nor
   *        empty.
   * @param aSettings
   *        The main SMTP settings. May not be <code>null</code>.
   * @return The created {@link NamedSMTPSettings} object and never
   *         <code>null</code>.
   */
  @Nonnull
  public NamedSMTPSettings addSettings (@Nonnull @Nonempty final String sName, @Nonnull final ISMTPSettings aSettings)
  {
    final NamedSMTPSettings aNamedSettings = new NamedSMTPSettings (sName, aSettings);

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addItem (aNamedSettings);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    AuditUtils.onAuditCreateSuccess (NamedSMTPSettings.OT,
                                     aNamedSettings.getID (),
                                     aNamedSettings.getName (),
                                     aSettings.getHostName (),
                                     Integer.toString (aSettings.getPort ()),
                                     aSettings.getCharset (),
                                     Boolean.toString (aSettings.isSSLEnabled ()),
                                     Boolean.toString (aSettings.isSTARTTLSEnabled ()),
                                     Long.toString (aSettings.getConnectionTimeoutMilliSecs ()),
                                     Long.toString (aSettings.getTimeoutMilliSecs ()));
    return aNamedSettings;
  }

  /**
   * Update an existing settings object.
   *
   * @param sID
   *        The ID of the settings object to be updated. May be
   *        <code>null</code>.
   * @param sName
   *        The new name of the settings. May neither be <code>null</code> nor
   *        empty.
   * @param aSettings
   *        The new SMTP settings. May not be <code>null</code>.
   * @return {@link EChange#CHANGED} if something was changed.
   */
  @Nullable
  public EChange updateSettings (@Nullable final String sID,
                                 @Nonnull @Nonempty final String sName,
                                 @Nonnull final ISMTPSettings aSettings)
  {
    final NamedSMTPSettings aNamedSettings = getSettings (sID);
    if (aNamedSettings == null)
    {
      AuditUtils.onAuditModifyFailure (NamedSMTPSettings.OT, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aNamedSettings.setName (sName));
      eChange = eChange.or (aNamedSettings.setSMTPSettings (aSettings));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditModifySuccess (NamedSMTPSettings.OT,
                                     aNamedSettings.getID (),
                                     aNamedSettings.getName (),
                                     aSettings.getHostName (),
                                     Integer.toString (aSettings.getPort ()),
                                     aSettings.getCharset (),
                                     Boolean.toString (aSettings.isSSLEnabled ()),
                                     Boolean.toString (aSettings.isSTARTTLSEnabled ()),
                                     Long.toString (aSettings.getConnectionTimeoutMilliSecs ()),
                                     Long.toString (aSettings.getTimeoutMilliSecs ()));
    return EChange.CHANGED;
  }

  /**
   * Remove the SMTP settings with the specified ID.
   *
   * @param sID
   *        The ID to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if a removal was performed.
   */
  @Nullable
  public EChange removeSettings (@Nullable final String sID)
  {
    EChange eChange;
    m_aRWLock.writeLock ().lock ();
    try
    {
      eChange = EChange.valueOf (m_aMap.remove (sID) != null);
      if (eChange.isChanged ())
        markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    if (eChange.isChanged ())
      AuditUtils.onAuditDeleteSuccess (NamedSMTPSettings.OT, sID);
    else
      AuditUtils.onAuditDeleteFailure (NamedSMTPSettings.OT, sID, "no-such-id");
    return eChange;
  }

  /**
   * Remove all contained SMTP settings
   *
   * @return {@link EChange#CHANGED} if a removal was performed.
   */
  @Nullable
  public EChange removeAllSettings ()
  {
    // Get all available settings IDs
    Set <String> aAllIDs;
    m_aRWLock.readLock ().lock ();
    try
    {
      aAllIDs = CollectionHelper.newSet (m_aMap.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }

    // Batch remove all settings
    EChange eChange = EChange.UNCHANGED;
    beginWithoutAutoSave ();
    try
    {
      for (final String sID : aAllIDs)
        eChange = eChange.or (removeSettings (sID));
    }
    finally
    {
      endWithoutAutoSave ();
    }
    return eChange;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
