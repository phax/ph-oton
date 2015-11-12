package com.helger.photon.security.token.user;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.object.IObject;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.app.IAppToken;

/**
 * Base interface for a user token. A user token is always subordinated to an
 * application token and can have roles and additional properties.
 *
 * @author Philip Helger
 */
public interface IUserToken extends IObject, IAuthSubject
{
  /**
   * @return The application token to which this user token belongs. Never
   *         <code>null</code>.
   */
  @Nonnull
  IAppToken getAppToken ();

  /**
   * @return A list of all tokens used by this user. The latest, active token is
   *         always the last one. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  List <? extends IAccessToken> getAllAccessTokens ();

  /**
   * @return The main token to access this application from the outside. May be
   *         <code>null</code> if all are revoked.
   */
  @Nullable
  IAccessToken getActiveAccessToken ();

  /**
   * @return The token string of the active access token. May be
   *         <code>null</code> if no active access token is present (which can
   *         be the case if all access tokens were revoked or if no access token
   *         is present).
   * @see #getActiveAccessToken()
   */
  @Nullable
  String getActiveTokenString ();

  /**
   * @return The name of the application that owns this token. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getUserName ();
}
