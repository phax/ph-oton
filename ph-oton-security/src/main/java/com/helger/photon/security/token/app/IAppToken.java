package com.helger.photon.security.token.app;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.photon.basic.object.IObject;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Base interface for an application token.
 *
 * @author Philip Helger
 */
public interface IAppToken extends IObject, Serializable
{
  /**
   * @return The main token to access this application from the outside. May be
   *         <code>null</code> if all tokens are expired or revoked.
   */
  @Nullable
  IAccessToken getActiveAccessToken ();

  /**
   * @return A list of all tokens used by this application. The latest, active
   *         token is always the last one. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  List <? extends IAccessToken> getAllAccessTokens ();

  /**
   * @return The name of the application that owns this token. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getOwnerName ();

  /**
   * @return The URL of the owning application. For informational purposes only.
   *         May be <code>null</code>.
   */
  @Nullable
  String getOwnerURL ();

  /**
   * @return The contact person or email of the owning application. For
   *         informational purposes only. May be <code>null</code>.
   */
  @Nullable
  String getOwnerContact ();

  /**
   * @return The contact email address of the owning application. For
   *         informational purposes only. May be <code>null</code>.
   */
  @Nullable
  String getOwnerContactEmail ();
}
