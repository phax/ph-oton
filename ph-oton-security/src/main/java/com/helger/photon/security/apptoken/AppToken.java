package com.helger.photon.security.apptoken;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.random.VerySecureRandom;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.object.AbstractBaseObject;
import com.helger.photon.security.object.StubObject;

/**
 * A single token for granting another application access to this application.
 *
 * @author Philip Helger
 */
public class AppToken extends AbstractBaseObject
{
  public static final ObjectType OT = new ObjectType ("apptoken");

  private final String m_sToken;
  private String m_sOwnerName;
  private String m_sOwnerURL;
  private String m_sOwnerContact;

  public AppToken (@Nonnull @Nonempty final String sOwnerName, @Nullable final String sOwnerURL, @Nullable final String sOwnerContact)
  {
    this (StubObject.createForCurrentUser (), createNewToken (), sOwnerName, sOwnerURL, sOwnerContact);
  }

  AppToken (@Nonnull final StubObject aStubObject,
            @Nonnull @Nonempty final String sToken,
            @Nonnull @Nonempty final String sOwnerName,
            @Nullable final String sOwnerURL,
            @Nullable final String sOwnerContact)
  {
    super (aStubObject);
    // The token is read-only
    m_sToken = ValueEnforcer.notEmpty (sToken, "Token");
    setOwnerName (sOwnerName);
    setOwnerURL (sOwnerURL);
    setOwnerContact (sOwnerContact);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nonnull
  public String getToken ()
  {
    return m_sToken;
  }

  @Nonnull
  public String getOwnerName ()
  {
    return m_sOwnerName;
  }

  @Nonnull
  public EChange setOwnerName (@Nonnull @Nonempty final String sOwnerName)
  {
    ValueEnforcer.notEmpty (sOwnerName, "OwnerName");
    if (sOwnerName.equals (m_sOwnerName))
      return EChange.UNCHANGED;
    m_sOwnerName = sOwnerName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getOwnerURL ()
  {
    return m_sOwnerURL;
  }

  @Nonnull
  public EChange setOwnerURL (@Nullable final String sOwnerURL)
  {
    if (EqualsHelper.equals (sOwnerURL, m_sOwnerURL))
      return EChange.UNCHANGED;
    m_sOwnerURL = sOwnerURL;
    return EChange.CHANGED;
  }

  @Nullable
  public String getOwnerContact ()
  {
    return m_sOwnerContact;
  }

  @Nonnull
  public EChange setOwnerContact (@Nullable final String sOwnerContact)
  {
    if (EqualsHelper.equals (sOwnerContact, m_sOwnerContact))
      return EChange.UNCHANGED;
    m_sOwnerContact = sOwnerContact;
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("token", m_sToken)
                            .append ("ownerName", m_sOwnerName)
                            .append ("ownerURL", m_sOwnerURL)
                            .append ("ownerContact", m_sOwnerContact)
                            .toString ();
  }

  @Nonnull
  @Nonempty
  public static String createNewToken ()
  {
    final byte [] aBytes = new byte [64];
    VerySecureRandom.getInstance ().nextBytes (aBytes);
    // Returns a 128 byte string
    return StringHelper.getHexEncoded (aBytes);
  }
}
