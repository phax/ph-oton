package com.helger.photon.security.token.app;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.object.AbstractObject;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;

/**
 * A single token for granting another application access to this application.
 *
 * @author Philip Helger
 */
public class AppToken extends AbstractObject implements IAppToken
{
  public static final ObjectType OT = new ObjectType ("apptoken");

  private final List <AccessToken> m_aAccessTokens;
  private String m_sOwnerName;
  private String m_sOwnerURL;
  private String m_sOwnerContact;
  private String m_sOwnerContactEmail;

  public AppToken (@Nonnull @Nonempty final String sOwnerName,
                   @Nullable final String sOwnerURL,
                   @Nullable final String sOwnerContact,
                   @Nullable final String sOwnerContactEmail)
  {
    this (StubObject.createForCurrentUser (),
          CollectionHelper.newList (AccessToken.createNewAccessTokenValidFromNow ()),
          sOwnerName,
          sOwnerURL,
          sOwnerContact,
          sOwnerContactEmail);
  }

  AppToken (@Nonnull final StubObject aStubObject,
            @Nonnull @Nonempty final List <AccessToken> aAccessTokens,
            @Nonnull @Nonempty final String sOwnerName,
            @Nullable final String sOwnerURL,
            @Nullable final String sOwnerContact,
            @Nullable final String sOwnerContactEmail)
  {
    super (aStubObject);
    m_aAccessTokens = ValueEnforcer.notEmptyNoNullValue (aAccessTokens, "AccessTokens");
    setOwnerName (sOwnerName);
    setOwnerURL (sOwnerURL);
    setOwnerContact (sOwnerContact);
    setOwnerContactEmail (sOwnerContactEmail);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nonnull
  @Nonempty
  public List <AccessToken> getAllAccessTokens ()
  {
    return CollectionHelper.newList (m_aAccessTokens);
  }

  @Nonnull
  @Nonempty
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

  @Nullable
  public String getOwnerContactEmail ()
  {
    return m_sOwnerContactEmail;
  }

  @Nonnull
  public EChange setOwnerContactEmail (@Nullable final String sOwnerContactEmail)
  {
    if (EqualsHelper.equals (sOwnerContactEmail, m_sOwnerContactEmail))
      return EChange.UNCHANGED;
    m_sOwnerContactEmail = sOwnerContactEmail;
    return EChange.CHANGED;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("AccessTokens", m_aAccessTokens)
                            .append ("OwnerName", m_sOwnerName)
                            .appendIfNotNull ("OwnerURL", m_sOwnerURL)
                            .appendIfNotNull ("OwnerContact", m_sOwnerContact)
                            .appendIfNotNull ("OwnerContactEmail", m_sOwnerContactEmail)
                            .toString ();
  }
}
