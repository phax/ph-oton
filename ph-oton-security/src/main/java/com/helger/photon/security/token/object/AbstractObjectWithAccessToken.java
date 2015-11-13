package com.helger.photon.security.token.object;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.attr.MapBasedAttributeContainer;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;
import com.helger.photon.basic.object.AbstractObject;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Abstract base implementation if {@link IObjectWithAccessToken}.
 *
 * @author Philip Helger
 */
public abstract class AbstractObjectWithAccessToken extends AbstractObject implements IObjectWithAccessToken
{
  private final List <AccessToken> m_aAccessTokens;
  private final MapBasedAttributeContainer <String, String> m_aAttributes;

  // Status vars
  private AccessToken m_aActiveAccessToken;

  @Nullable
  protected static AccessToken _getIfNotRevoked (@Nullable final AccessToken aAccessToken)
  {
    return aAccessToken != null && !aAccessToken.isRevoked () ? aAccessToken : null;
  }

  public AbstractObjectWithAccessToken (@Nonnull final StubObject aStubObject,
                                        @Nonnull @Nonempty final List <AccessToken> aAccessTokens,
                                        @Nullable final Map <String, String> aCustomAttrs)
  {
    super (aStubObject);
    m_aAccessTokens = ValueEnforcer.notEmptyNoNullValue (aAccessTokens, "AccessTokens");
    m_aAttributes = new MapBasedAttributeContainer <> (aCustomAttrs);
    m_aActiveAccessToken = _getIfNotRevoked (CollectionHelper.getLastElement (aAccessTokens));
  }

  @Nonnull
  @Nonempty
  public List <AccessToken> getAllAccessTokens ()
  {
    return CollectionHelper.newList (m_aAccessTokens);
  }

  @Nullable
  public IAccessToken getActiveAccessToken ()
  {
    return m_aActiveAccessToken;
  }

  @Nullable
  public String getActiveTokenString ()
  {
    return m_aActiveAccessToken == null ? null : m_aActiveAccessToken.getTokenString ();
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
    m_aActiveAccessToken.setNotAfter (aRevocationDT);
    m_aActiveAccessToken = null;
    return EChange.CHANGED;
  }

  public void createNewAccessToken (@Nullable final String sTokenString)
  {
    if (m_aActiveAccessToken != null)
      m_aActiveAccessToken.markRevoked (LoggedInUserManager.getInstance ().getCurrentUserID (),
                                        PDTFactory.getCurrentLocalDateTime (),
                                        "A new access token was created");
    final AccessToken aNewToken = AccessToken.createAccessTokenValidFromNow (sTokenString);
    m_aAccessTokens.add (aNewToken);
    m_aActiveAccessToken = aNewToken;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllAttributes ()
  {
    return m_aAttributes.getAllAttributes ();
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public MapBasedAttributeContainer <String, String> getMutableAttributes ()
  {
    return m_aAttributes;
  }

  // equals and hashCode are derived

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("AccessTokens", m_aAccessTokens)
                            .append ("Attributes", m_aAttributes)
                            .append ("ActiveAccessToken", m_aActiveAccessToken)
                            .toString ();
  }
}
