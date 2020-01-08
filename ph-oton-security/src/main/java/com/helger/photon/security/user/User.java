/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.security.user;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.StubObject;
import com.helger.security.password.hash.PasswordHash;
import com.helger.tenancy.AbstractBusinessObject;

/**
 * Default implementation of the {@link IUser} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class User extends AbstractBusinessObject implements IUser
{
  public static final ObjectType OT = new ObjectType ("user");

  private String m_sLoginName;
  private String m_sEmailAddress;
  private PasswordHash m_aPasswordHash;
  private String m_sFirstName;
  private String m_sLastName;
  private String m_sDescription;
  private Locale m_aDesiredLocale;
  private LocalDateTime m_aLastLoginDT;
  private int m_nLoginCount;
  private int m_nConsecutiveFailedLoginCount;
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
               @Nullable final Map <String, String> aCustomAttrs,
               final boolean bDisabled)
  {
    this (StubObject.createForCurrentUser (aCustomAttrs),
          sLoginName,
          sEmailAddress,
          aPasswordHash,
          sFirstName,
          sLastName,
          sDescription,
          aDesiredLocale,
          (LocalDateTime) null,
          0,
          0,
          bDisabled);
  }

  // Internal use only
  protected User (@Nonnull @Nonempty final String sID,
                  @Nonnull @Nonempty final String sLoginName,
                  @Nullable final String sEmailAddress,
                  @Nonnull final PasswordHash aPasswordHash,
                  @Nullable final String sFirstName,
                  @Nullable final String sLastName,
                  @Nullable final String sDescription,
                  @Nullable final Locale aDesiredLocale,
                  @Nullable final Map <String, String> aCustomAttrs,
                  final boolean bDisabled)
  {
    this (StubObject.createForCurrentUserAndID (sID, aCustomAttrs),
          sLoginName,
          sEmailAddress,
          aPasswordHash,
          sFirstName,
          sLastName,
          sDescription,
          aDesiredLocale,
          (LocalDateTime) null,
          0,
          0,
          bDisabled);
  }

  /**
   * For deserialization only.
   *
   * @param aStubObject
   *        Stub init object
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
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   */
  protected User (@Nonnull final StubObject aStubObject,
                  @Nonnull @Nonempty final String sLoginName,
                  @Nullable final String sEmailAddress,
                  @Nonnull final PasswordHash aPasswordHash,
                  @Nullable final String sFirstName,
                  @Nullable final String sLastName,
                  @Nullable final String sDescription,
                  @Nullable final Locale aDesiredLocale,
                  @Nullable final LocalDateTime aLastLoginDT,
                  @Nonnegative final int nLoginCount,
                  @Nonnegative final int nConsecutiveFailedLoginCount,
                  final boolean bDisabled)
  {
    super (aStubObject);
    ValueEnforcer.isGE0 (nLoginCount, "LoginCount");
    ValueEnforcer.isGE0 (nConsecutiveFailedLoginCount, "ConsecutiveFailedLoginCount");
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
    setDisabled (bDisabled);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return User.OT;
  }

  public boolean isAdministrator ()
  {
    return CSecurity.USER_ADMINISTRATOR_ID.equals (getID ());
  }

  @Nonnull
  @Nonempty
  public String getLoginName ()
  {
    return m_sLoginName;
  }

  @Nonnull
  protected EChange setLoginName (@Nonnull @Nonempty final String sLoginName)
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
  protected EChange setEmailAddress (@Nullable final String sEmailAddress)
  {
    if (EqualsHelper.equals (sEmailAddress, m_sEmailAddress))
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
  protected EChange setPasswordHash (@Nonnull final PasswordHash aPasswordHash)
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
  protected EChange setFirstName (@Nullable final String sFirstName)
  {
    if (EqualsHelper.equals (sFirstName, m_sFirstName))
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
  protected EChange setLastName (@Nullable final String sLastName)
  {
    if (EqualsHelper.equals (sLastName, m_sLastName))
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
  protected EChange setDescription (@Nullable final String sDescription)
  {
    if (EqualsHelper.equals (sDescription, m_sDescription))
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
  protected EChange setDesiredLocale (@Nullable final Locale aDesiredLocale)
  {
    if (EqualsHelper.equals (aDesiredLocale, m_aDesiredLocale))
      return EChange.UNCHANGED;
    m_aDesiredLocale = aDesiredLocale;
    return EChange.CHANGED;
  }

  @Nullable
  public LocalDateTime getLastLoginDateTime ()
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

  protected void onSuccessfulLogin ()
  {
    m_aLastLoginDT = PDTFactory.getCurrentLocalDateTime ();
    m_nLoginCount++;
    m_nConsecutiveFailedLoginCount = 0;
  }

  protected void onFailedLogin ()
  {
    m_nConsecutiveFailedLoginCount++;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  protected EChange setDisabled (final boolean bDisabled)
  {
    if (bDisabled == m_bDisabled)
      return EChange.UNCHANGED;
    m_bDisabled = bDisabled;
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
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
                            .append ("disabled", m_bDisabled)
                            .getToString ();
  }
}
