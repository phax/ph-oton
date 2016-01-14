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
package com.helger.photon.core.smtp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.charset.CCharset;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.lang.IHasSize;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.settings.SMTPSettings;

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

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

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
    s_aRWLock.writeLocked ( () -> {
      s_bCreateDefaults = bCreateDefaults;
    });
  }

  public NamedSMTPSettingsManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      m_aMap.clear ();
      initialRead ();
    });
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
  public int getSize ()
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
    return m_aRWLock.readLocked ( () -> CollectionHelper.newMap (m_aMap));
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

    return m_aRWLock.readLocked ( () -> m_aMap.get (sID));
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

    return m_aRWLock.readLocked ( () -> {
      for (final NamedSMTPSettings aSettings : m_aMap.values ())
        if (aSettings.getName ().equals (sName))
          return aSettings;
      return null;
    });
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

    m_aRWLock.writeLocked ( () -> {
      _addItem (aNamedSettings);
      markAsChanged ();
    });

    AuditHelper.onAuditCreateSuccess (NamedSMTPSettings.OT,
                                      aNamedSettings.getID (),
                                      aNamedSettings.getName (),
                                      aSettings.getHostName (),
                                      Integer.valueOf (aSettings.getPort ()),
                                      aSettings.getCharset (),
                                      Boolean.valueOf (aSettings.isSSLEnabled ()),
                                      Boolean.valueOf (aSettings.isSTARTTLSEnabled ()),
                                      Long.valueOf (aSettings.getConnectionTimeoutMilliSecs ()),
                                      Long.valueOf (aSettings.getTimeoutMilliSecs ()));
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
      AuditHelper.onAuditModifyFailure (NamedSMTPSettings.OT, sID, "no-such-id");
      return EChange.UNCHANGED;
    }

    return m_aRWLock.writeLocked ( () -> {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aNamedSettings.setName (sName));
      eChange = eChange.or (aNamedSettings.setSMTPSettings (aSettings));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
      AuditHelper.onAuditModifySuccess (NamedSMTPSettings.OT,
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
    });
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
    return m_aRWLock.writeLocked ( () -> {
      final EChange eChange = EChange.valueOf (m_aMap.remove (sID) != null);
      if (eChange.isChanged ())
      {
        markAsChanged ();
        AuditHelper.onAuditDeleteSuccess (NamedSMTPSettings.OT, sID);
      }
      else
        AuditHelper.onAuditDeleteFailure (NamedSMTPSettings.OT, sID, "no-such-id");
      return eChange;
    });
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
    final Set <String> aAllIDs = m_aRWLock.readLocked ( () -> CollectionHelper.newSet (m_aMap.keySet ()));

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
