package com.helger.photon.security.token.app;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.datetime.PDTFactory;
import com.helger.photon.basic.object.AbstractObject;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

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

  // Status vars
  private AccessToken m_aActiveAccessToken;

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

  @Nullable
  private static AccessToken _getIfNotRevoked (@Nullable final AccessToken aAccessToken)
  {
    return aAccessToken != null && !aAccessToken.isRevoked () ? aAccessToken : null;
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
    m_aActiveAccessToken = _getIfNotRevoked (CollectionHelper.getLastElement (aAccessTokens));
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nullable
  public IAccessToken getActiveAccessToken ()
  {
    return m_aActiveAccessToken;
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

  @Nonnull
  public EChange revokeActiveAccessToken (@Nonnull @Nonempty final String sRevocationUserID,
                                          @Nonnull final LocalDateTime aRevocationDT,
                                          @Nonnull @Nonempty final String sRevocationReason)
  {
    if (m_aActiveAccessToken == null)
    {
      // No active token present
      return EChange.UNCHANGED;
    }
    m_aActiveAccessToken.markRevoked (sRevocationUserID, aRevocationDT, sRevocationReason);
    m_aActiveAccessToken = null;
    return EChange.CHANGED;
  }

  public void createNewAccessToken ()
  {
    if (m_aActiveAccessToken != null)
      m_aActiveAccessToken.markRevoked (LoggedInUserManager.getInstance ().getCurrentUserID (),
                                        PDTFactory.getCurrentLocalDateTime (),
                                        "A new access token was created");
    final AccessToken aNewToken = AccessToken.createNewAccessTokenValidFromNow ();
    m_aAccessTokens.add (aNewToken);
    m_aActiveAccessToken = aNewToken;
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
                            .append ("ActiveAccessToken", m_aActiveAccessToken)
                            .toString ();
  }
}
