package com.helger.photon.security.token.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.security.token.object.IObjectWithAccessToken;

/**
 * Base interface for an application token.
 *
 * @author Philip Helger
 */
public interface IAppToken extends IObjectWithAccessToken
{
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
