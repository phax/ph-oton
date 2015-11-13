package com.helger.photon.security.token.user;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.security.token.accesstoken.IObjectWithAccessToken;
import com.helger.photon.security.token.app.IAppToken;

/**
 * Base interface for a user token. A user token is always subordinated to an
 * application token and can have roles and additional properties.
 *
 * @author Philip Helger
 */
public interface IUserToken extends IObjectWithAccessToken
{
  /**
   * @return The application token to which this user token belongs. Never
   *         <code>null</code>.
   */
  @Nonnull
  IAppToken getAppToken ();

  /**
   * @return The name of the application that owns this token. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getUserName ();
}
