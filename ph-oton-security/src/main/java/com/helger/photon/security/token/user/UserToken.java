package com.helger.photon.security.token.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.object.AbstractObjectWithAccessToken;

/**
 * A single token for granting a machine user access to this application.
 *
 * @author Philip Helger
 */
public class UserToken extends AbstractObjectWithAccessToken implements IUserToken
{
  public static final ObjectType OT = new ObjectType ("usertoken");

  private final IAppToken m_aAppToken;
  private String m_sUserName;

  public UserToken (@Nullable final String sTokenString,
                    @Nullable final Map <String, String> aCustomAttrs,
                    @Nonnull final IAppToken aAppToken,
                    @Nonnull @Nonempty final String sUserName)
  {
    this (StubObject.createForCurrentUser (),
          CollectionHelper.newList (AccessToken.createAccessTokenValidFromNow (sTokenString)),
          aCustomAttrs,
          aAppToken,
          sUserName);
  }

  UserToken (@Nonnull final StubObject aStubObject,
             @Nonnull @Nonempty final List <AccessToken> aAccessTokens,
             @Nullable final Map <String, String> aCustomAttrs,
             @Nonnull final IAppToken aAppToken,
             @Nonnull @Nonempty final String sUserName)
  {
    super (aStubObject, aAccessTokens, aCustomAttrs);
    m_aAppToken = ValueEnforcer.notNull (aAppToken, "AppToken");
    setUserName (sUserName);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    return m_sUserName;
  }

  @Nonnull
  public IAppToken getAppToken ()
  {
    return m_aAppToken;
  }

  @Nonnull
  @Nonempty
  public String getUserName ()
  {
    return m_sUserName;
  }

  @Nonnull
  public EChange setUserName (@Nonnull @Nonempty final String sUserName)
  {
    ValueEnforcer.notEmpty (sUserName, "UserName");
    if (sUserName.equals (m_sUserName))
      return EChange.UNCHANGED;
    m_sUserName = sUserName;
    return EChange.CHANGED;
  }

  // equals and hashCode are derived

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("AppToken", m_aAppToken).append ("UserName", m_sUserName).toString ();
  }
}
