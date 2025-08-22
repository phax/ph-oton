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
package com.helger.photon.core.smtp;

import java.util.Comparator;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.iface.IHasSize;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.CollectionFind;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.collection.commons.ICommonsSet;
import com.helger.dao.DAOException;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.io.dao.AbstractPhotonSimpleDAO;
import com.helger.smtp.CSMTP;
import com.helger.smtp.settings.ISMTPSettings;
import com.helger.smtp.settings.SMTPSettings;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This class manages {@link NamedSMTPSettings} objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class NamedSMTPSettingsManager extends AbstractPhotonSimpleDAO implements IHasSize
{
  private static final String ELEMENT_ROOT = "namedsmtpsettingslist";
  private static final String ELEMENT_ITEM = "namedsmtpsettings";
  private static boolean s_bCreateDefaults = true;

  private final ICommonsMap <String, NamedSMTPSettings> m_aMap = new CommonsHashMap <> ();

  public static boolean isCreateDefaults ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bCreateDefaults);
  }

  public static void setCreateDefaults (final boolean bCreateDefaults)
  {
    RW_LOCK.writeLocked ( () -> s_bCreateDefaults = bCreateDefaults);
  }

  public NamedSMTPSettingsManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  public boolean isReloadable ()
  {
    return true;
  }

  @Override
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
                                                       CSMTP.CHARSET_SMTP_OBJ,
                                                       false)));
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eNamedSMTPSettings : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addItem (MicroTypeConverter.convertToNative (eNamedSMTPSettings, NamedSMTPSettings.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final NamedSMTPSettings aNamedSMTPSettings : m_aMap.getSortedByKey (Comparator.naturalOrder ()).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aNamedSMTPSettings, ELEMENT_ITEM));
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
    return m_aRWLock.readLockedInt (m_aMap::size);
  }

  public boolean isEmpty ()
  {
    return m_aRWLock.readLockedBoolean (m_aMap::isEmpty);
  }

  /**
   * @return A copy of all contained SMTP settings as map from ID to object. Never <code>null</code>
   *         but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsMap <String, NamedSMTPSettings> getAllSettings ()
  {
    return m_aRWLock.readLockedGet (m_aMap::getClone);
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

    return m_aRWLock.readLockedBoolean ( () -> m_aMap.containsKey (sID));
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

    return m_aRWLock.readLockedGet ( () -> m_aMap.get (sID));
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

    return m_aRWLock.readLockedGet ( () -> CollectionFind.findFirst (m_aMap.values (),
                                                                     x -> x.getName ().equals (sName)));
  }

  /**
   * Create a new settings object.
   *
   * @param sName
   *        The name of the settings. May neither be <code>null</code> nor empty.
   * @param aSettings
   *        The main SMTP settings. May not be <code>null</code>.
   * @return The created {@link NamedSMTPSettings} object and never <code>null</code>.
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
                                      aSettings.getCharsetName (),
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
   *        The ID of the settings object to be updated. May be <code>null</code>.
   * @param sName
   *        The new name of the settings. May neither be <code>null</code> nor empty.
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

    AuditHelper.onAuditModifySuccess (NamedSMTPSettings.OT,
                                      aNamedSettings.getID (),
                                      aNamedSettings.getName (),
                                      aSettings.getHostName (),
                                      Integer.valueOf (aSettings.getPort ()),
                                      aSettings.getCharsetName (),
                                      Boolean.valueOf (aSettings.isSSLEnabled ()),
                                      Boolean.valueOf (aSettings.isSTARTTLSEnabled ()),
                                      Long.valueOf (aSettings.getConnectionTimeoutMilliSecs ()),
                                      Long.valueOf (aSettings.getTimeoutMilliSecs ()));
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
    return m_aRWLock.writeLockedGet ( () -> {
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
    final ICommonsSet <String> aAllIDs = m_aRWLock.readLockedGet (m_aMap::copyOfKeySet);

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
    return new ToStringGenerator (this).append ("Map", m_aMap).getToString ();
  }
}
