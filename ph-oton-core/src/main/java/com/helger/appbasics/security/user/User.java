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
package com.helger.appbasics.security.user;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.joda.time.DateTime;

import com.helger.appbasics.security.CSecurity;
import com.helger.appbasics.security.password.hash.PasswordHash;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.attrs.MapBasedAttributeContainer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.datetime.PDTFactory;

/**
 * Default implementation of the {@link IUser} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class User extends MapBasedAttributeContainer implements IUser
{
  private final String m_sID;
  private final DateTime m_aCreationDT;
  private DateTime m_aLastModificationDT;
  private DateTime m_aDeletionDT;
  private String m_sLoginName;
  private String m_sEmailAddress;
  private PasswordHash m_aPasswordHash;
  private String m_sFirstName;
  private String m_sLastName;
  private String m_sDescription;
  private Locale m_aDesiredLocale;
  private DateTime m_aLastLoginDT;
  private int m_nLoginCount;
  private int m_nConsecutiveFailedLoginCount;
  private boolean m_bDeleted;
  private boolean m_bDisabled;

  /**
   * Create a new user
   *
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor empty.
   * @param sEmailAddress
   *        Email address of the user. May be <code>null</code>.
   * @param aPasswordHash
   *        Password hash of the user. May not be <code>null</code>.
   * @param sFirstName
   *        The first name. May be <code>null</code>.
   * @param sLastName
   *        The last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description text. May be <code>null</code>.
   * @param aDesiredLocale
   *        The desired locale. May be <code>null</code>.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   */
  public User (@Nonnull @Nonempty final String sLoginName,
               @Nullable final String sEmailAddress,
               @Nonnull final PasswordHash aPasswordHash,
               @Nullable final String sFirstName,
               @Nullable final String sLastName,
               @Nullable final String sDescription,
               @Nullable final Locale aDesiredLocale,
               @Nullable final Map <String, ?> aCustomAttrs,
               final boolean bDisabled)
  {
    this (GlobalIDFactory.getNewPersistentStringID (),
          PDTFactory.getCurrentDateTime (),
          (DateTime) null,
          (DateTime) null,
          sLoginName,
          sEmailAddress,
          aPasswordHash,
          sFirstName,
          sLastName,
          sDescription,
          aDesiredLocale,
          (DateTime) null,
          0,
          0,
          aCustomAttrs,
          false,
          bDisabled);
  }

  // Internal use only
  User (@Nonnull @Nonempty final String sID,
        @Nonnull @Nonempty final String sLoginName,
        @Nullable final String sEmailAddress,
        @Nonnull final PasswordHash aPasswordHash,
        @Nullable final String sFirstName,
        @Nullable final String sLastName,
        @Nullable final String sDescription,
        @Nullable final Locale aDesiredLocale,
        @Nullable final Map <String, ?> aCustomAttrs,
        final boolean bDisabled)
  {
    this (sID,
          PDTFactory.getCurrentDateTime (),
          (DateTime) null,
          (DateTime) null,
          sLoginName,
          sEmailAddress,
          aPasswordHash,
          sFirstName,
          sLastName,
          sDescription,
          aDesiredLocale,
          (DateTime) null,
          0,
          0,
          aCustomAttrs,
          false,
          bDisabled);
  }

  /**
   * For deserialization only.
   *
   * @param sID
   *        user ID
   * @param aCreationDT
   *        The creation date and time
   * @param aLastModificationDT
   *        The last modification date and time
   * @param aDeletionDT
   *        The deletion date and time
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor empty.
   * @param sEmailAddress
   *        Email address of the user. May be <code>null</code>.
   * @param aPasswordHash
   *        Password hash of the user. May not be <code>null</code>.
   * @param sFirstName
   *        The first name. May be <code>null</code>.
   * @param sLastName
   *        The last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description text. May be <code>null</code>.
   * @param aDesiredLocale
   *        The desired locale. May be <code>null</code>.
   * @param aLastLoginDT
   *        The date time when the user last logged in.
   * @param nLoginCount
   *        The number of times the user logged in. Must be &ge; 0.
   * @param nConsecutiveFailedLoginCount
   *        The number of consecutive failed logins. Must be &ge; 0.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bDeleted
   *        <code>true</code> if the user is deleted, <code>false</code> if nto
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   */
  User (@Nonnull @Nonempty final String sID,
        @Nonnull final DateTime aCreationDT,
        @Nullable final DateTime aLastModificationDT,
        @Nullable final DateTime aDeletionDT,
        @Nonnull @Nonempty final String sLoginName,
        @Nullable final String sEmailAddress,
        @Nonnull final PasswordHash aPasswordHash,
        @Nullable final String sFirstName,
        @Nullable final String sLastName,
        @Nullable final String sDescription,
        @Nullable final Locale aDesiredLocale,
        @Nullable final DateTime aLastLoginDT,
        @Nonnegative final int nLoginCount,
        @Nonnegative final int nConsecutiveFailedLoginCount,
        @Nullable final Map <String, ?> aCustomAttrs,
        final boolean bDeleted,
        final boolean bDisabled)
  {
    ValueEnforcer.notEmpty (sID, "ID");
    ValueEnforcer.notNull (aCreationDT, "CreationDT");
    ValueEnforcer.isGE0 (nLoginCount, "LoginCount");
    ValueEnforcer.isGE0 (nConsecutiveFailedLoginCount, "ConsecutiveFailedLoginCount");
    m_sID = sID;
    m_aCreationDT = aCreationDT;
    m_aLastModificationDT = aLastModificationDT;
    m_aDeletionDT = aDeletionDT;
    setLoginName (sLoginName);
    setEmailAddress (sEmailAddress);
    setPasswordHash (aPasswordHash);
    setFirstName (sFirstName);
    setLastName (sLastName);
    setDescription (sDescription);
    setDesiredLocale (aDesiredLocale);
    m_aLastLoginDT = aLastLoginDT;
    m_nLoginCount = nLoginCount;
    m_nConsecutiveFailedLoginCount = nConsecutiveFailedLoginCount;
    setAttributes (aCustomAttrs);
    m_bDeleted = bDeleted;
    setDisabled (bDisabled);
  }

  @Nonnull
  public ObjectType getTypeID ()
  {
    return CSecurity.TYPE_USER;
  }

  public boolean isAdministrator ()
  {
    return CSecurity.USER_ADMINISTRATOR_ID.equals (m_sID);
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public DateTime getCreationDateTime ()
  {
    return m_aCreationDT;
  }

  @Nullable
  public DateTime getLastModificationDateTime ()
  {
    return m_aLastModificationDT;
  }

  void updateLastModified ()
  {
    m_aLastModificationDT = PDTFactory.getCurrentDateTime ();
  }

  @Nullable
  public DateTime getDeletionDateTime ()
  {
    return m_aDeletionDT;
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return getLoginName ();
  }

  @Nonnull
  @Nonempty
  public String getLoginName ()
  {
    return m_sLoginName;
  }

  @Nonnull
  EChange setLoginName (@Nonnull @Nonempty final String sLoginName)
  {
    ValueEnforcer.notEmpty (sLoginName, "loginName");

    if (sLoginName.equals (m_sLoginName))
      return EChange.UNCHANGED;
    m_sLoginName = sLoginName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getEmailAddress ()
  {
    return m_sEmailAddress;
  }

  @Nonnull
  EChange setEmailAddress (@Nullable final String sEmailAddress)
  {
    if (EqualsUtils.equals (sEmailAddress, m_sEmailAddress))
      return EChange.UNCHANGED;
    m_sEmailAddress = sEmailAddress;
    return EChange.CHANGED;
  }

  @Nonnull
  @Nonempty
  public PasswordHash getPasswordHash ()
  {
    return m_aPasswordHash;
  }

  @Nonnull
  EChange setPasswordHash (@Nonnull final PasswordHash aPasswordHash)
  {
    ValueEnforcer.notNull (aPasswordHash, "PasswordHash");

    if (aPasswordHash.equals (m_aPasswordHash))
      return EChange.UNCHANGED;
    m_aPasswordHash = aPasswordHash;
    return EChange.CHANGED;
  }

  @Nullable
  public String getFirstName ()
  {
    return m_sFirstName;
  }

  @Nonnull
  EChange setFirstName (@Nullable final String sFirstName)
  {
    if (EqualsUtils.equals (sFirstName, m_sFirstName))
      return EChange.UNCHANGED;
    m_sFirstName = sFirstName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getLastName ()
  {
    return m_sLastName;
  }

  @Nonnull
  EChange setLastName (@Nullable final String sLastName)
  {
    if (EqualsUtils.equals (sLastName, m_sLastName))
      return EChange.UNCHANGED;
    m_sLastName = sLastName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getDescription ()
  {
    return m_sDescription;
  }

  @Nonnull
  EChange setDescription (@Nullable final String sDescription)
  {
    if (EqualsUtils.equals (sDescription, m_sDescription))
      return EChange.UNCHANGED;
    m_sDescription = sDescription;
    return EChange.CHANGED;
  }

  @Nullable
  public Locale getDesiredLocale ()
  {
    return m_aDesiredLocale;
  }

  @Nonnull
  EChange setDesiredLocale (@Nullable final Locale aDesiredLocale)
  {
    if (EqualsUtils.equals (aDesiredLocale, m_aDesiredLocale))
      return EChange.UNCHANGED;
    m_aDesiredLocale = aDesiredLocale;
    return EChange.CHANGED;
  }

  @Nullable
  public DateTime getLastLoginDateTime ()
  {
    return m_aLastLoginDT;
  }

  @Nonnegative
  public int getLoginCount ()
  {
    return m_nLoginCount;
  }

  @Nonnegative
  public int getConsecutiveFailedLoginCount ()
  {
    return m_nConsecutiveFailedLoginCount;
  }

  void onSuccessfulLogin ()
  {
    m_aLastLoginDT = PDTFactory.getCurrentDateTime ();
    m_nLoginCount++;
    m_nConsecutiveFailedLoginCount = 0;
  }

  void onFailedLogin ()
  {
    m_nConsecutiveFailedLoginCount++;
  }

  @Nonnull
  public String getDisplayName ()
  {
    return StringHelper.getConcatenatedOnDemand (m_sFirstName, " ", m_sLastName);
  }

  public boolean isDeleted ()
  {
    return m_bDeleted;
  }

  @Nonnull
  EChange setDeleted (final boolean bDeleted)
  {
    if (bDeleted == m_bDeleted)
      return EChange.UNCHANGED;
    m_bDeleted = bDeleted;
    m_aDeletionDT = bDeleted ? PDTFactory.getCurrentDateTime () : null;
    return EChange.CHANGED;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  EChange setDisabled (final boolean bDisabled)
  {
    if (bDisabled == m_bDisabled)
      return EChange.UNCHANGED;
    m_bDisabled = bDisabled;
    return EChange.CHANGED;
  }

  public boolean isEnabled ()
  {
    return !m_bDisabled;
  }

  @Nonnull
  EChange setEnabled (final boolean bEnabled)
  {
    return setDisabled (!bEnabled);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final User rhs = (User) o;
    return m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("ID", m_sID)
                            .append ("creationDT", m_aCreationDT)
                            .appendIfNotNull ("lastModificationDT", m_aLastModificationDT)
                            .appendIfNotNull ("deletionDT", m_aDeletionDT)
                            .append ("loginName", m_sLoginName)
                            .appendIfNotNull ("emailAddress", m_sEmailAddress)
                            .append ("passwordHash", m_aPasswordHash)
                            .appendIfNotNull ("firstName", m_sFirstName)
                            .appendIfNotNull ("lastName", m_sLastName)
                            .appendIfNotNull ("description", m_sDescription)
                            .appendIfNotNull ("desiredLocale", m_aDesiredLocale)
                            .appendIfNotNull ("lastLoginDT", m_aLastLoginDT)
                            .append ("loginCount", m_nLoginCount)
                            .append ("consecutiveFailedLoginCount", m_nConsecutiveFailedLoginCount)
                            .append ("deleted", m_bDeleted)
                            .append ("disabled", m_bDisabled)
                            .toString ();
  }
}
