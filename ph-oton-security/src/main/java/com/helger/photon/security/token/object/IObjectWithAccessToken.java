package com.helger.photon.security.token.object;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.attr.MapBasedAttributeContainer;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.object.IObject;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Base interface for a application and user token.
 *
 * @author Philip Helger
 */
public interface IObjectWithAccessToken extends IObject, IAuthSubject
{
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
   * @return A non-<code>null</code> container with all custom attributes.
   *         Modifications to the returned container have no effect on this
   *         object.
   */
  @Nonnull
  @ReturnsMutableCopy
  Map <String, String> getAllAttributes ();

  /**
   * A mutable version of the custom attribute container to change values.
   *
   * @return A non-<code>null</code> writable container with all custom
   *         attributes.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  MapBasedAttributeContainer <String, String> getMutableAttributes ();
}
